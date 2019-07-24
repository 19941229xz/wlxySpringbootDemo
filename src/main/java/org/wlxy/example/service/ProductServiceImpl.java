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
import org.wlxy.example.dao.ProductDao;
import org.wlxy.example.model.Product;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {


	@Autowired
    ProductDao productDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllProduct(PageParam<Product> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Product> productList=productDao.getAllProduct(pageParam.getModel());
        PageInfo<Product> productPageInfo = new PageInfo<Product>(productList);

        return productPageInfo;
    
    }

	@CacheEvict(value = "products",key = "#p0")
    @Override
    public boolean removeProductById(int id){
    	return productDao.removeProductById(id)==1;
    }

	@CachePut(value = "products",key = "#p0.id")
    @Override
    public Object addProduct(Product product){
        productDao.addProduct(product);

        return productDao.getProductById(product.getId());
    }

    @CacheEvict(value = "products",key = "#p0.id")
	@Override
    public boolean updateProduct(Product product){
    	if(StringUtils.isEmpty(product.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改product时，id不能为空");
        }

        return productDao.updateProduct(product)==1;
    }

	@Cacheable(key = "#p0",value="products")
    @Override
    @Transactional(readOnly = true)
    public Product getProductById(int id){

    	return productDao.getProductById(id);
    	
    }




}
