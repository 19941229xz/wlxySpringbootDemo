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
import org.wlxy.example.dao.ShoppingcarDao;
import org.wlxy.example.model.Shoppingcar;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ShoppingcarServiceImpl implements ShoppingcarService {


	@Autowired
    ShoppingcarDao shoppingcarDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllShoppingcar(PageParam<Shoppingcar> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Shoppingcar> shoppingcarList=shoppingcarDao.getAllShoppingcar(pageParam.getModel());
        PageInfo<Shoppingcar> shoppingcarPageInfo = new PageInfo<Shoppingcar>(shoppingcarList);

        return shoppingcarPageInfo;
    
    }

	@CacheEvict(value = "shoppingcars",key = "#p0")
    @Override
    public boolean removeShoppingcarById(int id){
    	return shoppingcarDao.removeShoppingcarById(id)==1;
    }

	@CachePut(value = "shoppingcars",key = "#p0.id")
    @Override
    public Object addShoppingcar(Shoppingcar shoppingcar){
        shoppingcarDao.addShoppingcar(shoppingcar);

        return shoppingcarDao.getShoppingcarById(shoppingcar.getId());
    }

	@Override
    public boolean updateShoppingcar(Shoppingcar shoppingcar){
    	if(StringUtils.isEmpty(shoppingcar.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改shoppingcar时，id不能为空");
        }

        return shoppingcarDao.updateShoppingcar(shoppingcar)==1;
    }

	@Cacheable(key = "#p0",value="shoppingcars")
    @Override
    @Transactional(readOnly = true)
    public Shoppingcar getShoppingcarById(int id){
    	return shoppingcarDao.getShoppingcarById(id);
    	
    }




}
