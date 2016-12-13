package com.qxms.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		validateRequiredString("username", "msg1", "用户名或密码不能为空");
		validateRequiredString("password", "msg1", "用户名或密码不能为空");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.keepPara("username");
		c.render("login.jsp");
	}

}
