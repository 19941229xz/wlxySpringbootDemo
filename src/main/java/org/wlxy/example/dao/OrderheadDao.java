package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.Orderhead;

import java.util.List;

public interface OrderheadDao {


    List<Orderhead> getAllOrderhead(Orderhead orderhead);

    @Delete("delete from orderhead where id = #{id}")
    int removeOrderheadById(int id);

    int addOrderhead(Orderhead orderhead);

    int updateOrderhead(Orderhead orderhead);

    @Select("select * from orderhead where id =#{id}")
    Orderhead getOrderheadById(int id);

    @Select("select * from orderhead where orderheadName =#{orderheadName}")
    Orderhead getOrderheadByOrderheadName(String orderheadName);




}