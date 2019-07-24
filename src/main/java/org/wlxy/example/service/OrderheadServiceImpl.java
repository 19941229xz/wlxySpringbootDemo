package org.wlxy.example.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.wlxy.example.common.HttpCode;
import org.wlxy.example.common.MyException;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.OrderheadDao;
import org.wlxy.example.model.Orderdetail;
import org.wlxy.example.model.Orderhead;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.Shoppingcar;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderheadServiceImpl implements OrderheadService {


	@Autowired
    OrderheadDao orderheadDao;

	@Autowired
    ProductService productService;

	@Autowired
    OrderdetailService orderdetailService;

    @Autowired
    ShoppingcarService shoppingcarService;

	@Override
    @Transactional(readOnly = true)
	public Object getAllOrderhead(PageParam<Orderhead> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Orderhead> orderheadList=orderheadDao.getAllOrderhead(pageParam.getModel());
        PageInfo<Orderhead> orderheadPageInfo = new PageInfo<Orderhead>(orderheadList);

        return orderheadPageInfo;
    
    }

	@CacheEvict(value = "orderheads",key = "#p0")
    @Override
    public boolean removeOrderheadById(int id){
    	return orderheadDao.removeOrderheadById(id)==1;
    }

	@CachePut(value = "orderheads",key = "#p0.id")
    @Override
    public Object addOrderhead(Orderhead orderhead){
        orderheadDao.addOrderhead(orderhead);

        return orderheadDao.getOrderheadById(orderhead.getId());
    }

    @CacheEvict(value = "orderheads",key = "#p0.id")
	@Override
    public boolean updateOrderhead(Orderhead orderhead){
    	if(StringUtils.isEmpty(orderhead.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改orderhead时，id不能为空");
        }

        return orderheadDao.updateOrderhead(orderhead)==1;
    }

	@Cacheable(key = "#p0",value="orderheads")
    @Override
    @Transactional(readOnly = true)
    public Orderhead getOrderheadById(int id){
    	return orderheadDao.getOrderheadById(id);
    	
    }

    @Override
    public Orderhead createOrder(List<Shoppingcar> shoppingcarList) {

	    if(shoppingcarList.size()==0){
	        throw new MyException(HttpCode.ERROR).msg("生成订单失败，原因购物车数据长度0");
        }

	    int totalProductCount=0;
        String firstProductName=shoppingcarList.get(0).getProductName();
        String firstProductImg=shoppingcarList.get(0).getProductImg();
        double totalPrice=0.0;
        int userId=shoppingcarList.get(0).getUserId();

        //订单表头数据  初始化
        Orderhead orderhead = new Orderhead();

        Product product;//临时存放  商品数据
        double discount;
        double killDiscount;
        double discountTotal=0;
        double killDiscountTotal=0;
        for(Shoppingcar shoppingcar:shoppingcarList){
            totalProductCount+=shoppingcar.getProductNum();

            product=productService.getProductById(shoppingcar.getProductId());
            if(product==null){
                throw new MyException(HttpCode.ERROR).msg("您下单的商品查询不到");
            }

            if(product.getDeserveNum()<shoppingcar.getProductNum()){
                throw new MyException(HttpCode.ERROR).msg("您下单的商品"+shoppingcar.getProductName()+"库存不足！下单失败！");
            }

            discount = product.getIsInDiscount()==2?product.getDiscount():0;

            killDiscount=product.getIsInKill()==2?product.getKillDiscount():0;


            //计算总折扣
            discountTotal+=discount*shoppingcar.getProductNum();
            killDiscountTotal+=killDiscount*shoppingcar.getProductNum();

            totalPrice+=(product.getNormalPrice()-discount-killDiscount)*shoppingcar.getProductNum();
        }

        // 添加订单表头到数据库
        orderhead.setKillDiscount(killDiscountTotal);
        orderhead.setDiscount(discountTotal);
        orderhead.setFirstProductImg(firstProductImg);
        orderhead.setFirstProductName(firstProductName);
        orderhead.setTotalPrice(totalPrice);
        orderhead.setTotalProductCount(totalProductCount);
        orderhead.setUserId(userId);

        // sql  add
        orderheadDao.addOrderhead(orderhead);
        log.info("order head  id:"+orderhead.getId());
        // 如果订单表头创建成功  需要及时减去所有库存
        for(Shoppingcar shoppingcar:shoppingcarList){

            product=productService.getProductById(shoppingcar.getProductId());
            if(product==null){
                throw new MyException(HttpCode.ERROR).msg("您下单的商品查询不到");
            }
            //计算新库存
            int newDeserveNum = product.getDeserveNum()-shoppingcar.getProductNum();
            // 减掉库存
            product.setDeserveNum(newDeserveNum);
            // sql update
            boolean flag = productService.updateProduct(product);
            if(!flag){
                throw new MyException(HttpCode.ERROR).msg("库存扣取操作失败");
            }
        }


        // 添加订单详情到数据库(后期如果一个订单商品数量过多  可以将该方法设为异步操作 如果是分布式架构可采用消息队列提高后台响应速度)
        Orderdetail orderdetail;
        // 创建一个变量  存放插入成功的记录条数
        int successInsert=0;
        for(Shoppingcar shoppingcar:shoppingcarList){
            orderdetail = new Orderdetail();
            BeanUtils.copyProperties(shoppingcar,orderdetail);
            orderdetail.setOrderId(orderhead.getId());
            orderdetail.setId(0);
            // sql  add
            successInsert +=(orderdetailService.addOrderdetail(orderdetail)!=null?1:0);

        }

        if(successInsert!=shoppingcarList.size()){
            throw new MyException(HttpCode.ERROR).msg("订单详情数据录入不完整");
        }

        //订单生成成功  需要清空已被结算的购物车的商品
        for(Shoppingcar shoppingcar:shoppingcarList){
            shoppingcarService.removeShoppingcarById(shoppingcar.getId());
        }


        return orderheadDao.getOrderheadById(orderhead.getId());
    }


}
