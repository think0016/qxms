package com.qxms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AuthenticationValidator implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.println("[ActionKey]:"+inv.getActionKey());
		System.out.println("[ControllerKey]:"+inv.getControllerKey());
		System.out.println("[ViewPath]:"+inv.getViewPath());
		System.out.println("[MethodName]:"+inv.getMethodName());
		inv.invoke();
	}

}
