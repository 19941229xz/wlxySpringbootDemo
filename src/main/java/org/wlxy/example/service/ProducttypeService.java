package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Producttype;


public interface ProducttypeService {



	Object getAllProducttype(PageParam<Producttype> pageParam);

    boolean removeProducttypeById(int id);

    Object addProducttype(Producttype producttype);

    boolean updateProducttype(Producttype producttype);

    Producttype getProducttypeById(int id);

    /**
     *  static
     */
    boolean addProducttypeViewNum(int producttypeId);
}