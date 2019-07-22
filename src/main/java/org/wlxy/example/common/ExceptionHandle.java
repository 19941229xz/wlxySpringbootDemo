package org.wlxy.example.common;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        e.printStackTrace();
        if(e instanceof MyException){
            return MyRsp.wrapper((MyException)e);
        }else if(e instanceof ArithmeticException){
            return MyRsp.error().msg("这是一个算数异常");
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex= (MethodArgumentNotValidException)e;
            return MyRsp.error().msg(ex.getBindingResult()
            .getFieldError().getDefaultMessage());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException ex= (HttpRequestMethodNotSupportedException)e;
            return MyRsp.error().msg(ex.getMessage());
        }else if(e instanceof HttpMessageNotReadableException){
            HttpMessageNotReadableException ex= (HttpMessageNotReadableException)e;
            return MyRsp.error().msg("json数据格式可能发生错误，请检查："+ex.getMessage());
        }else if(e instanceof UnauthorizedException){

            return MyRsp.error().msg("未授权");
        }else if(e instanceof AuthenticationException){

            return MyRsp.error().msg("身份认证失败");
        }else {
            return MyRsp.error().msg("这是一个未知异常");
        }



    }



    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public Object handleException(AuthenticationException e){
        return MyRsp.error().msg(e.getMessage());
    }

}
