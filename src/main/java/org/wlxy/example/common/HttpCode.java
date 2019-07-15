package org.wlxy.example.common;

public enum HttpCode {

    SUCCESS(200,"访问成功"),
    ITEM_NOT_FOUND(404,"查询不到数据"),
    ERROR(500,"服务器出小差了....")
    ;


    private int code;

    private String msg;

    public int getCode(){
        return  this.code;
    }


    public String getMsg(){
        return  this.msg;
    }

    HttpCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
