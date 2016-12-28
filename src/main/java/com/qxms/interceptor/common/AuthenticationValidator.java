package com.qxms.interceptor.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qxms.model.User;
import com.qxms.service.sys.MenuService;
import com.qxms.service.sys.UserService;

public class AuthenticationValidator implements Interceptor {
	public static final UserService userService = new UserService();
	public static final MenuService menuService = new MenuService();
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		// System.out.println("[ActionKey]:"+inv.getActionKey());
		// System.out.println("[ControllerKey]:"+inv.getControllerKey());
		// System.out.println("[ViewPath]:"+inv.getViewPath());
		// System.out.println("[MethodName]:"+inv.getMethodName());
		
		User user = (User)inv.getController().getSession().getAttribute("cache_user");
		//Object isAdmin = (Object)inv.getController().getSession().getAttribute("isAdmin");
		//以后要添加缓存机制
		
		boolean isAdmin = true;
		if(inv.getController().getSession().getAttribute("isAdmin") == null){
			isAdmin = userService.isAdmin(user);			
		}else{
			isAdmin = (boolean)inv.getController().getSession().getAttribute("isAdmin");
		}
		
		boolean flag = true;
		if(!isAdmin){
			flag = menuService.VerifyAuthenticationByHref(user.getUid().toString(), inv.getActionKey());
		}
		
		if(flag){
			inv.invoke();
		}else{
			inv.getController().redirect("/forbidpage");
		}
	}

}
