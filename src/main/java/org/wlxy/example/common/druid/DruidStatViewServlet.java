package org.wlxy.example.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*",initParams = {
        @WebInitParam(name = "allow",value=""), //访问白名单
        @WebInitParam(name = "deny",value = ""),// 访问黑名单
        @WebInitParam(name = "resetEnble",value="true")
})
public class DruidStatViewServlet extends StatViewServlet {




}
