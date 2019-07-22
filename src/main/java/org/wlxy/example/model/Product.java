package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Product" ,description = "商品")
@Data  // 自动生成get set 和构造器
public class Product implements Serializable {
    // 主键id
    @ApiModelProperty(value = "主键id" ,name = "id")
    private Integer id;
    // 商品名称
    @ApiModelProperty(value = "商品名称" ,name = "productName")
    private String productName;
    // 正常情况时的价格
    @ApiModelProperty(value = "正常情况时的价格" ,name = "normalPrice")
    private Double normalPrice;
    //
    @ApiModelProperty(value = "" ,name = "discount")
    private Double discount;
    // 是否参与折扣活动 1参加 0不参加
    @ApiModelProperty(value = "是否参与折扣活动 1参加 0不参加" ,name = "isInDiscount")
    private Integer isInDiscount;
    // 商品分类id
    @ApiModelProperty(value = "商品分类id" ,name = "typeId")
    private Integer typeId;
    // 上架时间
    @ApiModelProperty(value = "上架时间" ,name = "createTime")
    private Date createTime;
    // 是否参与秒杀活动 1参加 0不参加
    @ApiModelProperty(value = "是否参与秒杀活动 1参加 0不参加" ,name = "isInKill")
    private Integer isInKill;
    // 秒杀的折扣
    @ApiModelProperty(value = "秒杀的折扣" ,name = "killDiscount")
    private Double killDiscount;
    // 商品图片
    @ApiModelProperty(value = "商品图片" ,name = "productImg")
    private String productImg;
    // 浏览量
    @ApiModelProperty(value = "浏览量" ,name = "viewNum")
    private Integer viewNum;
    // 库存量
    @ApiModelProperty(value = "库存量" ,name = "deserveNum")
    private Integer deserveNum;
    // 商品描述
    @ApiModelProperty(value = "商品描述" ,name = "miaoshu")
    private String miaoshu;
    // 下单数
    @ApiModelProperty(value = "下单数" ,name = "orderCount")
    private Integer orderCount;

}