package org.wlxy.example.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
import org.wlxy.example.dao.OrderdetailDao;
import org.wlxy.example.model.Orderdetail;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderdetailServiceImpl implements OrderdetailService {


	@Autowired
    OrderdetailDao orderdetailDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllOrderdetail(PageParam<Orderdetail> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Orderdetail> orderdetailList=orderdetailDao.getAllOrderdetail(pageParam.getModel());
        PageInfo<Orderdetail> orderdetailPageInfo = new PageInfo<Orderdetail>(orderdetailList);

        return orderdetailPageInfo;
    
    }

	@CacheEvict(value = "orderdetails",key = "#p0")
    @Override
    public boolean removeOrderdetailById(int id){
    	return orderdetailDao.removeOrderdetailById(id)==1;
    }

	@CachePut(value = "orderdetails",key = "#p0.id")
    @Override
    public Object addOrderdetail(Orderdetail orderdetail){
        orderdetailDao.addOrderdetail(orderdetail);

        return orderdetailDao.getOrderdetailById(orderdetail.getId());
    }

    @CacheEvict(value = "orderdetails",key = "#p0.id")
	@Override
    public boolean updateOrderdetail(Orderdetail orderdetail){
    	if(StringUtils.isEmpty(orderdetail.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改orderdetail时，id不能为空");
        }

        return orderdetailDao.updateOrderdetail(orderdetail)==1;
    }

	@Cacheable(key = "#p0",value="orderdetails")
    @Override
    @Transactional(readOnly = true)
    public Orderdetail getOrderdetailById(int id){
    	return orderdetailDao.getOrderdetailById(id);
    	
    }




}
