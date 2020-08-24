package com.example.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Zuul过滤器，必须继承ZuulFilter父类。
 * 当前类型的对象必须交由Spring容器管理。使用@Component注解描述。
 * 继承父类后，必须实现父类中定义的4个抽象方法。
 * shouldFilter、 run、 filterType、 filterOrder
 */
@Component
public class HttpRequestPreFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestPreFilter.class);

    /**
     * 返回boolean类型。代表当前filter是否生效。
     * 默认值为false。
     * 返回true代表开启filter。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run方法就是过滤器的具体逻辑。
     * return 可以返回任意的对象，当前实现忽略。（spring-cloud-zuul官方解释）
     * 直接返回null即可。
     */
    @Override
    public Object run() throws ZuulException {
        // 登录校验逻辑
        // 1）获取zuul提供的请求上下文对象（即是请求全部内容）
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 2) 从上下文中获取request对象
        HttpServletRequest request = currentContext.getRequest();
        // 3) 从请求中获取token
        String token = request.getParameter("access-token");
        // 4) 判断（如果没有token，认为用户还没有登录，返回401状态码）
        if (token == null || "".equals(token.trim())) {
            // 没有token，认为登录校验失败，进行拦截
            currentContext.setSendZuulResponse(false);
            // 返回401状态码。也可以考虑重定向到登录页
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            try {
                HttpServletResponse response = currentContext.getResponse();
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("非法请求！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 如果校验通过，可以考虑吧用户信息放入上下文，继续向后执行
        return null;
    }

    /**
     * 过滤器的类型。可选值有：
     * pre - 前置过滤
     * route - 路由后过滤
     * error - 异常过滤
     * post - 远程服务调用后过滤
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 同种类的过滤器的执行顺序。
     * 按照返回值的自然升序执行。
     */
    @Override
    public int filterOrder() {
        return 0;
    }
}
