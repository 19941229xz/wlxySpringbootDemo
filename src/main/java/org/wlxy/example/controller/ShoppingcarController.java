package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.*;
import org.wlxy.example.model.Shoppingcar;
import org.wlxy.example.service.ShoppingcarService;

import javax.validation.Valid;

@Api(value = "shoppingcar模块接口",description = "这是一个购物车模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class ShoppingcarController {

	@Autowired
    ShoppingcarService shoppingcarService;

	@ApiOperation("查询所有购物车 支持多条件分页排序查询")
    @PostMapping("/getAllShoppingcar")
    public Object getAllShoppingcar(@RequestBody PageParam<Shoppingcar> pageParam){
        return MyRsp.success(shoppingcarService.getAllShoppingcar(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeShoppingcarById/{id}")
    public Object removeShoppingcarByShoppingcarName(@PathVariable("id") int id){

        return shoppingcarService.removeShoppingcarById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addShoppingcar")
    public Object addShoppingcar(@RequestBody @Valid Shoppingcar shoppingcarParam){
        Shoppingcar shoppingcar=(Shoppingcar)shoppingcarService.addShoppingcar(shoppingcarParam);

        return shoppingcar!=null?MyRsp.success(shoppingcar).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateShoppingcar")
    public Object updateShoppingcar(@RequestBody@Valid Shoppingcar shoppingcar){
        return shoppingcarService.updateShoppingcar(shoppingcar)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getShoppingcarById/{id}")
    public Object getShoppingcarById(@PathVariable("id") int id){

        Shoppingcar shoppingcar=shoppingcarService.getShoppingcarById(id);
        return shoppingcar!=null?MyRsp.success(shoppingcar):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}