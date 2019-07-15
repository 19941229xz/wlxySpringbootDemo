package org.wlxy.example.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Banji {


    @DecimalMin(value = "1",message = "班级id错误")
    private int id;

    @NotEmpty(message = "班级名称不能为空")
    private String banjiName;


}
