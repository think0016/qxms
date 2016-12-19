package com.qxms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qxms.model.User;

public class VerifyLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub		
		
		User user = (User)inv.getController().getSession().getAttribute("user");
		
		if(user == null){
			System.out.println("---未登录---");
			//inv.getController().redirect("/login");
			inv.getController().setAttr("msg1", "登录失效请重新登录");
			inv.getController().forwardAction("/login");
		}else{
			inv.invoke();
		}
	}

}
