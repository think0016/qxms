package com.qxms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class TestInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.println("测试拦截器:");
		System.out.println(inv.getActionKey());
		System.out.println(inv.getMethodName());
		System.out.println(inv.getViewPath());
		System.out.println(inv.getControllerKey());
	}

}
