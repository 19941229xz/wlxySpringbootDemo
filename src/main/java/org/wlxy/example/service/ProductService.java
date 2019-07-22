package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Product;


public interface ProductService {



	public Object getAllProduct(PageParam<Product> pageParam);

    public boolean removeProductById(int id);

    public Object addProduct(Product product);

    public boolean updateProduct(Product product);

    public Product getProductById(int id);




}