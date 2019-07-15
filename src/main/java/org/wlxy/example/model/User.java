package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(value = "user" ,description = "用户实体类")
@Data  // 自动生成get set 和构造器
public class User implements Serializable {

    @ApiModelProperty(value = "用户名" ,name = "userName")
    @NotEmpty(message = "用户名不能为空")
    @Size(max=12,min=6,message = "用户名长度必须是6-12位")
    private String userName;

    @ApiModelProperty(value = "用户密码" ,name = "password")
//    @Size(min = 5,max = 10,message = "密码必须是5到10位数")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,10})$",message = "密码必须是包含数字和字母的6到10位")
    private String password;

//    @Valid
//    private Banji banji;


    @ApiModelProperty(value = "用户主键" ,name = "id")
    private int id;

    @ApiModelProperty(value = "用户角色id" ,name = "roleId")
    @Null(message = "不允许直接修改用户角色")
    private String roleId;
}
