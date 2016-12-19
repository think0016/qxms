package com.qxms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Department;
import com.qxms.model.Role;
import com.qxms.model.TreeNode;
import com.qxms.model.User;
import com.qxms.service.DepartmentService;
import com.qxms.service.RoleService;
import com.qxms.service.SystemService;
import com.qxms.service.UserService;

public class UserController extends Controller {
	public static final UserService userService = new UserService();
	public static final DepartmentService departmentService = new DepartmentService();
	public static final RoleService roleservice = new RoleService();
	// public static final SystemService systemService = new SystemService();

	public void index() {
		redirect("/user/list");
	}

	public void list() {

		String did = getPara(0);

		List<User> list = null;
		if (did != null) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("did", did);
			list = userService.findUserList(param);
		} else {
			did = "1";
			list = userService.findAllUser();
		}

		// 部门树信息
		List<Department> dlist = departmentService.findAllDepartment();
		List<TreeNode> nodes = departmentService.gettreedata(dlist);

		Gson gson = new Gson();
		String json = gson.toJson(nodes);

		setAttr("userlist", list);
		setAttr("treejson", json);
		setAttr("did", did);
		render("list.jsp");
	}

	public void form() {

		// 先判断是修改还是新增
		User user = new User();
		String uid = getPara(0);
		if (!StringUtils.isEmpty(uid)) {
			user = userService.findByUid(uid);
		}

		// 部门树信息
		List<Department> dlist = departmentService.findAllDepartment();
		List<TreeNode> nodes = departmentService.gettreedata(dlist);
		Gson gson = new Gson();
		String json = gson.toJson(nodes);

		// 角色信息
		List<Role> rlist = roleservice.findAllRole();

		setAttr("roles", rlist);
		setAttr("treejson", json);
		setAttr("user", user);

		render("form.jsp");
	}

	public void pwform() {

		setAttr("user", getSession().getAttribute("user"));

		render("pwform.jsp");
	}

	public void saveuser() {
		String uid = getPara("uid");
		String did = getPara("did");
		String loginname = getPara("loginname");
		String password = getPara("password");
		String truename = getPara("truename");
		String email = getPara("email");
		String remark = getPara("remark");
		String[] role = getParaValues("role");

		boolean update = false;
		boolean lnflag = false;// 重名标记
		User user = new User();
		if (!StringUtils.isEmpty(uid)) {
			update = true;
			user = userService.findByUid(uid);
		} else {
			Map<String, String> param = new HashMap<String, String>();
			param.put("login_name", loginname);
			List<User> rs = userService.findUserList(param);
			if (rs.size() > 0) {
				lnflag = true;
			}
		}

		user.setDid(new Integer(did));
		user.setLoginName(loginname);
		user.setTurename(truename);
		user.setEmail(email);
		user.setPassword(SystemService.entryptPassword(password));
		user.setRemarks(remark);
		user.setRegisterdate(new Date());
		user.setRole(role);

		if (!lnflag) {
			int flag = userService.save(user, update);

			if (flag == 1) {
				setAttr("infomsg", "添加成功");
				forwardAction("/user/list");
			} else {
				setAttr("errormsg", "添加失败");
				keepPara();
				forwardAction("/user/form");
			}
		} else {
			setAttr("errormsg", "登录名已经存在");
			keepPara();
			forwardAction("/user/form");
		}

		// boolean flag = user.save();

		// redirect("/user/list");
	}

	public void resetpassword() {
		// String uid = getPara("uid");
		String loginname = getPara("loginname");
		String opassword = getPara("opassword");
		String npassword = getPara("npassword");

		// 先验证旧密码
		// User user = userService.findByup(loginname, opassword);
		User user = userService.findByloginname(loginname);
		if (user == null || !SystemService.validatePassword(opassword, user.getPassword())) {
			setAttr("errormsg", "密码不正确");
			keepPara();
			forwardAction("/user/pwform");
		} else {
			user.setPassword(npassword);
			if (user.update()) {
				setAttr("infomsg", "修改密码成功");
				forwardAction("/index");
			} else {
				setAttr("errormsg", "密码不正确");
				keepPara();
				forwardAction("/user/pwform");
			}
		}

	}

	public void delete() {
		String uid = getPara("uid");
		boolean flag = userService.delete(uid);
		if (flag) {
			renderText("1");
		} else {
			renderText("2");
		}

	}

}
