package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.Product;

import java.util.List;

public interface ProductDao {


    List<Product> getAllProduct(Product product);

    @Delete("delete from product where id = #{id}")
    int removeProductById(int id);

    int addProduct(Product product);

    int updateProduct(Product product);

    @Select("select * from product where id =#{id}")
    Product getProductById(int id);

    @Select("select * from product where productName =#{productName}")
    Product getProductByProductName(String productName);




}