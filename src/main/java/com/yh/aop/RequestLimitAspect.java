package com.yh.aop;

import com.yh.annotation.RequestLimit;
import com.yh.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求限制切面
 * @Author: yinhui
 * @Date: 2019/12/30 10:17
 * @Version 1.0
 */
@Aspect
@Component
public class RequestLimitAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 针对有@RequestLimit的注解进行请求重复限制
     * 为什么要加包限制，直接注解切面不可以吗？
     * 答：自定义注解命名的时候，可能你取的名字很大众化，其他的jar包，也就是你项目引入的jar包，可能有重名的注解，如果要是不加包限制的话，
     * 那估计就会出现意想不到的效果。所以，我们就看到，那么多的切面代码，这地方的写法都是千篇一律 都是使用 && 符号。限制包，
     * 然后限制使用的是哪个注解。
     * @param point
     * @param limit
     */
    @Before("execution(public * com.yh.controller..*.*(..)) && @annotation(limit)")
    public void requestLimt(JoinPoint point, RequestLimit limit) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

//        // 记录下请求内容
//        RequestInfo info = new RequestInfo();
//        info.setUrl(request.getRequestURI());
//        info.setIp(request.getRemoteAddr());
//        info.setClientPort(request.getRemotePort());
//        info.setServerPort(request.getServerPort());
//        info.setHttpMethod(request.getMethod());
//        info.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        info.setArgs(Arrays.toString(joinPoint.getArgs()));

        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        //用户ID
        String userId = "123";

        if(StringUtils.isBlank(uri)){
            throw new Exception("请求重复验证异常");
        }

        String key = "req_limit:"+userId.concat(":").concat(uri);

        boolean setResult = RedisUtils.setnx(key,ip,limit.seconds());
        if(!setResult){
            logger.info("用户[" + userId + "]访问地址[" + uri + "]请求重复[" + limit.seconds() + "]");
            throw new Exception("您有尚未完成交易订单");
        }
    }
}
