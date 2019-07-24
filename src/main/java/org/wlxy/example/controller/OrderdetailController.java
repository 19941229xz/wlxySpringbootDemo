package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.*;
import org.wlxy.example.model.Orderdetail;
import org.wlxy.example.service.OrderdetailService;

import javax.validation.Valid;

@Api(value = "orderdetail模块接口",description = "这是一个订单详情模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class OrderdetailController {

	@Autowired
    OrderdetailService orderdetailService;

	@ApiOperation("查询所有订单详情 支持多条件分页排序查询")
    @PostMapping("/getAllOrderdetail")
    public Object getAllOrderdetail(@RequestBody PageParam<Orderdetail> pageParam){
        return MyRsp.success(orderdetailService.getAllOrderdetail(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeOrderdetailById/{id}")
    public Object removeOrderdetailByOrderdetailName(@PathVariable("id") int id){

        return orderdetailService.removeOrderdetailById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addOrderdetail")
    public Object addOrderdetail(@RequestBody @Valid Orderdetail orderdetailParam){
        Orderdetail orderdetail=(Orderdetail)orderdetailService.addOrderdetail(orderdetailParam);

        return orderdetail!=null?MyRsp.success(orderdetail).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateOrderdetail")
    public Object updateOrderdetail(@RequestBody@Valid Orderdetail orderdetail){
        return orderdetailService.updateOrderdetail(orderdetail)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getOrderdetailById/{id}")
    public Object getOrderdetailById(@PathVariable("id") int id){

        Orderdetail orderdetail=orderdetailService.getOrderdetailById(id);
        return orderdetail!=null?MyRsp.success(orderdetail):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}