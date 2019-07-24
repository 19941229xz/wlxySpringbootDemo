package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.Orderdetail;

import java.util.List;

public interface OrderdetailDao {


    List<Orderdetail> getAllOrderdetail(Orderdetail orderdetail);

    @Delete("delete from orderdetail where id = #{id}")
    int removeOrderdetailById(int id);

    int addOrderdetail(Orderdetail orderdetail);

    int updateOrderdetail(Orderdetail orderdetail);

    @Select("select * from orderdetail where id =#{id}")
    Orderdetail getOrderdetailById(int id);

    @Select("select * from orderdetail where orderdetailName =#{orderdetailName}")
    Orderdetail getOrderdetailByOrderdetailName(String orderdetailName);




}