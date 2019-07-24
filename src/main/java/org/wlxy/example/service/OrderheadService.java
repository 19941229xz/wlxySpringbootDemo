package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Orderhead;
import org.wlxy.example.model.Shoppingcar;

import java.util.List;


public interface OrderheadService {



	public Object getAllOrderhead(PageParam<Orderhead> pageParam);

    public boolean removeOrderheadById(int id);

    public Object addOrderhead(Orderhead orderhead);

    public boolean updateOrderhead(Orderhead orderhead);

    public Orderhead getOrderheadById(int id);


    Orderhead createOrder(List<Shoppingcar> shoppingcarList);
}