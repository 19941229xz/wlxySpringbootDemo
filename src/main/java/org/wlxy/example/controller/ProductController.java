package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.*;
import org.wlxy.example.model.Product;
import org.wlxy.example.service.ProductService;

import javax.validation.Valid;

@Api(value = "product模块接口",description = "这是一个商品模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class ProductController {

	@Autowired
    ProductService productService;

	@ApiOperation("查询所有商品 支持多条件分页排序查询")
    @PostMapping("/getAllProduct")
    public Object getAllProduct(@RequestBody PageParam<Product> pageParam){
        return MyRsp.success(productService.getAllProduct(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeProductById/{id}")
    public Object removeProductByProductName(@PathVariable("id") int id){

        return productService.removeProductById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addProduct")
    public Object addProduct(@RequestBody @Valid Product productParam){
        Product product=(Product)productService.addProduct(productParam);

        return product!=null?MyRsp.success(product).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateProduct")
    public Object updateProduct(@RequestBody@Valid Product product){
        return productService.updateProduct(product)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getProductById/{id}")
    public Object getProductById(@PathVariable("id") int id){

        Product product=productService.getProductById(id);
        return product!=null?MyRsp.success(product):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}