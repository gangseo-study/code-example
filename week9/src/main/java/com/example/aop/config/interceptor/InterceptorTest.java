package com.example.aop.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InterceptorTest implements HandlerInterceptor {

    /**
     * preHandle: handler(컨트롤러)가 실행되기 이전에 실행된다.
     * - 반환값이 true이면 preHandle 이후 handler가 정상적으로 실행된다.
     * - 하지만 반환값이 false라면 handler로 넘어가지 않는다.
     * - 인터셉터가 여러 개 등록된 경우 등록한 순서대로 preHandle이 호출된다.
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("InterceptorTest PreHandle: " + request.getServletPath());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    /**
     * postHandle:hanlder가 실행된 이후 실행된다.
     * - handler에서 exception이 발생한 경우 실행되지 않는다.
     * - View에 Model이 반환되기 전에 반환될 Model에 추가적인 데이터 가공을 할 수 있다.
     * - 인터셉터가 여러 개 등록된 경우 등록한 순서의 반대로 postHandle이 호출된다.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("InterceptorTest PostHandle: " + response.getStatus());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * afterCompletion: View까지 렌더링 된 이후 호출된다.
     * - handler 중간에 exception이 발생해도 호출된다.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) System.out.println("ex: " + ex.getMessage());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
