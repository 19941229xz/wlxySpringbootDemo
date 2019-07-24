package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Orderdetail;


public interface OrderdetailService {



	public Object getAllOrderdetail(PageParam<Orderdetail> pageParam);

    public boolean removeOrderdetailById(int id);

    public Object addOrderdetail(Orderdetail orderdetail);

    public boolean updateOrderdetail(Orderdetail orderdetail);

    public Orderdetail getOrderdetailById(int id);




}