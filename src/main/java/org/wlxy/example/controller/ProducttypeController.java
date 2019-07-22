package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.*;
import org.wlxy.example.model.Producttype;
import org.wlxy.example.service.ProducttypeService;

import javax.validation.Valid;

@Api(value = "producttype模块接口",description = "这是一个商品分类模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class ProducttypeController {

    @Autowired
    ProducttypeService producttypeService;

    @ApiOperation("查询所有商品分类 支持多条件分页排序查询")
    @PostMapping("/getAllProducttype")
    public Object getAllUser(@RequestBody PageParam<Producttype> pageParam){
        return MyRsp.success(producttypeService.getAllProducttype(pageParam));
    }

    @GetMapping("/removeProducttypeById/{id}")
    public Object removeProducttypeByProducttypeName(@PathVariable("id") int id){

        return producttypeService.removeProducttypeById(id)?MyRsp.success(null):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addProducttype")
    public Object addProducttype(@RequestBody @Valid Producttype producttypeParam){
        Producttype producttype=(Producttype)producttypeService.addProducttype(producttypeParam);

        return producttype!=null?MyRsp.success(producttype).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateProducttype")
    public Object updateProducttype(@RequestBody@Valid Producttype producttype){
        return producttypeService.updateProducttype(producttype)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getProducttypeById/{id}")
    public Object getProducttypeById(@PathVariable("id") int id){

        Producttype producttype=producttypeService.getProducttypeById(id);
        return producttype!=null?MyRsp.success(producttype):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }


    /**
     *  static controller
     */
    @GetMapping("/addProducttypeViewNum/{id}")
    public Object addProducttypeViewNum(@PathVariable("id") int producttypeId){

//        boolean flag=producttypeService.addProducttypeViewNum(producttypeId);
        return producttypeService.addProducttypeViewNum(producttypeId)
                ?MyRsp.success(null):MyRsp.error().msg("商品分类模块异常");
    }

}