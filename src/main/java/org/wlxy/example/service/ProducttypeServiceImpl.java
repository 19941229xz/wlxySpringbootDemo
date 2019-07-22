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
import org.wlxy.example.dao.ProducttypeDao;
import org.wlxy.example.model.Producttype;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProducttypeServiceImpl implements ProducttypeService {

    @Autowired
    ProducttypeDao producttypeDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllProducttype(PageParam<Producttype> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Producttype> producttypeList=producttypeDao.getAllProducttype(pageParam.getModel());
        PageInfo<Producttype> producttypePageInfo = new PageInfo<Producttype>(producttypeList);

        return producttypePageInfo;
    
    }

	@CacheEvict(value = "producttypes",key = "#p0")
    @Override
    public boolean removeProducttypeById(int id){
    	return producttypeDao.removeProducttypeById(id)==1;
    }

	@CachePut(value = "producttypes",key = "#p0.id")
    @Override
    public Object addProducttype(Producttype producttype){
        producttypeDao.addProducttype(producttype);

        return producttypeDao.getProducttypeById(producttype.getId());
    }

	@Override
    public boolean updateProducttype(Producttype producttype){
    	if(StringUtils.isEmpty(producttype.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改producttype时，id不能为空");
        }

        return producttypeDao.updateProducttype(producttype)==1;
    }

	@Cacheable(key = "#p0",value="producttypes")
    @Override
    @Transactional(readOnly = true)
    public Producttype getProducttypeById(int id){
    	return producttypeDao.getProducttypeById(id);
    	
    }

    /**
     * static
     */

    @Override
    public boolean addProducttypeViewNum(int producttypeId) {

        int viewNumOld = producttypeDao.getProducttypeById(producttypeId).getViewNum();

        Producttype producttype = new Producttype();
        producttype.setViewNum(++viewNumOld);
        producttype.setId(producttypeId);



        return producttypeDao.updateProducttype(producttype)==1;
    }


}
