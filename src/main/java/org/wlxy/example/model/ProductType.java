package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Producttype" ,description = "商品分类")
@Data  // 自动生成get set 和构造器
public class Producttype implements Serializable {
	// 主键id
	@ApiModelProperty(value = "主键id" ,name = "id")
	private Integer id;
	// 商品分类名称
	@ApiModelProperty(value = "商品分类名称" ,name = "producttypeName")
	private String producttypeName;
	// 浏览量
	@ApiModelProperty(value = "浏览量" ,name = "viewNum")
	private Integer viewNum;
	// 商品分类图片
	@ApiModelProperty(value = "商品分类图片" ,name = "typeImg")
	private String typeImg;
	// 创建时间
	@ApiModelProperty(value = "创建时间" ,name = "createTime")
	private Date createTime;
	// 商品分类描述
	@ApiModelProperty(value = "商品分类描述" ,name = "miaoshu")
	private String miaoshu;

}