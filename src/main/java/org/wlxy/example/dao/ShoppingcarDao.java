package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.Shoppingcar;

import java.util.List;

public interface ShoppingcarDao {


    List<Shoppingcar> getAllShoppingcar(Shoppingcar shoppingcar);

    @Delete("delete from shoppingcar where id = #{id}")
    int removeShoppingcarById(int id);

    int addShoppingcar(Shoppingcar shoppingcar);

    int updateShoppingcar(Shoppingcar shoppingcar);

    @Select("select * from shoppingcar where id =#{id}")
    Shoppingcar getShoppingcarById(int id);

    @Select("select * from shoppingcar where shoppingcarName =#{shoppingcarName}")
    Shoppingcar getShoppingcarByShoppingcarName(String shoppingcarName);




}