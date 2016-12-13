package com.qxms.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.qxms.interceptor.LoginValidator;
import com.qxms.model.User;
import com.qxms.service.UserService;

public class IndexController extends Controller {
	public static final UserService userService = new UserService();
	
	public void index() {

		render("index.jsp");
	}

	@Clear
	public void login() {
		render("login.jsp");
	}

	@Clear
	@Before(LoginValidator.class)
	public void login1() {
		String username = getPara("username");
		String password = getPara("password");
		User user = userService.findByup(username, password);
		if(user == null){
			setAttr("msg1", "用户名或密码不正确");
			//setAttr("msg1", "用户名或密码不正确");
			keepPara();
			//forwardAction("/login");
			render("login.jsp");
		}else{
			//renderText(user.getTurename()+"登陆成功");
			getSession().setAttribute("user", user);
			getSession().setAttribute("username", user.getTurename());
			//forwardAction("/");
			redirect("/");
		}

	}

	@Clear
	public void logout() {
		getSession().removeAttribute("user");
		redirect("/login");
	}
	
}
