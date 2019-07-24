package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.*;
import org.wlxy.example.model.Orderhead;
import org.wlxy.example.model.Shoppingcar;
import org.wlxy.example.service.OrderheadService;

import javax.validation.Valid;
import java.util.List;

@Api(value = "orderhead模块接口",description = "这是一个订单表头模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class OrderheadController {

	@Autowired
    OrderheadService orderheadService;

	@ApiOperation("查询所有订单表头 支持多条件分页排序查询")
    @PostMapping("/getAllOrderhead")
    public Object getAllOrderhead(@RequestBody PageParam<Orderhead> pageParam){
        return MyRsp.success(orderheadService.getAllOrderhead(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeOrderheadById/{id}")
    public Object removeOrderheadByOrderheadName(@PathVariable("id") int id){

        return orderheadService.removeOrderheadById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addOrderhead")
    public Object addOrderhead(@RequestBody @Valid Orderhead orderheadParam){
        Orderhead orderhead=(Orderhead)orderheadService.addOrderhead(orderheadParam);

        return orderhead!=null?MyRsp.success(orderhead).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateOrderhead")
    public Object updateOrderhead(@RequestBody@Valid Orderhead orderhead){
        return orderheadService.updateOrderhead(orderhead)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getOrderheadById/{id}")
    public Object getOrderheadById(@PathVariable("id") int id){

        Orderhead orderhead=orderheadService.getOrderheadById(id);
        return orderhead!=null?MyRsp.success(orderhead):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }


    @PostMapping("/pay")
    public Object pay(@RequestBody List<Shoppingcar> shoppingcarList){

//        for (int i = 0; i < shoppingcarList.size(); i++) {
//            System.out.println(shoppingcarList.get(i).toString());
//        }
        Orderhead orderhead=orderheadService.createOrder(shoppingcarList);

	    return MyRsp.success(orderhead);
//        return orderhead!=null?MyRsp.success(orderhead):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }

	
}