package com.cars.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationLookUp extends HandlerInterceptorAdapter {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		System.out.println("Got request in the Interceptor for this request url: "+request.getRequestURL());
		if(  null != request.getHeader("isAuthenticated")) 
			return (Boolean.valueOf(request.getHeader("isAuthenticated")) == true ? true : false);
		else 
			//response.setStatus("");
			return false;
	}
	
}
