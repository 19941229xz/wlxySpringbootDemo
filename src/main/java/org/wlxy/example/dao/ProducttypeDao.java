package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.Producttype;

import java.util.List;

public interface ProducttypeDao {


    List<Producttype> getAllProducttype(Producttype producttype);

    @Delete("delete from producttype where id = #{id}")
    int removeProducttypeById(int id);

    int addProducttype(Producttype producttype);

    int updateProducttype(Producttype producttype);

    @Select("select * from producttype where id =#{id}")
    Producttype getProducttypeById(int id);

    @Select("select * from producttype where producttypeName =#{producttypeName}")
    Producttype getProducttypeByProducttypeName(String producttypeName);




}