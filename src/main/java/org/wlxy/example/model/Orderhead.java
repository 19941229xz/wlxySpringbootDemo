package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Orderhead" ,description = "订单表头")
@Data  // 自动生成get set 和构造器
public class Orderhead implements Serializable {
	// 主键id
	@ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 订单商品总件数
	@ApiModelProperty(value = "订单商品总件数" ,name = "totalProductCount")
	private Integer totalProductCount;
	// 订单第一件商品的名称
	@ApiModelProperty(value = "订单第一件商品的名称" ,name = "firstProductName")
	private String firstProductName;
	// 订单第一件商品的图片
	@ApiModelProperty(value = "订单第一件商品的图片" ,name = "firstProductImg")
	private String firstProductImg;
	// 订单商品总价（含运费）
	@ApiModelProperty(value = "订单商品总价（含运费）" ,name = "totalPrice")
	private Double totalPrice;
	// 用户id
	@ApiModelProperty(value = "用户id" ,name = "userId")
	private Integer userId;
	// 订单创建时间
	@ApiModelProperty(value = "订单创建时间" ,name = "createTime")
	private Date createTime;
	// 订单状态
	@ApiModelProperty(value = "订单状态" ,name = "state")
	private String state;
	// 普通折扣减免
	@ApiModelProperty(value = "普通折扣减免" ,name = "discount")
	private Double discount;
	// 秒杀折扣减免
	@ApiModelProperty(value = "秒杀折扣减免" ,name = "killDiscount")
	private Double killDiscount;
	// 优惠券减免
	@ApiModelProperty(value = "优惠券减免" ,name = "coupon")
	private Double coupon;

}