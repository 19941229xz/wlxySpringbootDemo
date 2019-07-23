package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Shoppingcar;


public interface ShoppingcarService {



	public Object getAllShoppingcar(PageParam<Shoppingcar> pageParam);

    public boolean removeShoppingcarById(int id);

    public Object addShoppingcar(Shoppingcar shoppingcar);

    public boolean updateShoppingcar(Shoppingcar shoppingcar);

    public Shoppingcar getShoppingcarById(int id);




}