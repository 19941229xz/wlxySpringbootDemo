package org.wlxy.example.common;

import lombok.Data;

@Data
public class MyException extends RuntimeException {


    private  int code;
    private  String msg;


    public MyException(HttpCode httpCode){
        this.code=httpCode.getCode();
        this.msg=httpCode.getMsg();
    }


    public MyException msg(String msg){  // 自定义msg  链式函数
        this.msg=msg;
        return this;
    }

}
