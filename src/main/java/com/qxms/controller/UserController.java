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
import com.qxms.service.UserService;

public class UserController extends Controller {
	public static final UserService userService = new UserService();
	public static final DepartmentService departmentService = new DepartmentService();
	public static final RoleService roleservice = new RoleService();

	public void index(){
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

		System.out.println("=======================");
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

		//先判断是修改还是新增
		User user = new User();
		String uid = getPara(0);
		if(!StringUtils.isEmpty(uid)){
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

	public void saveuser() {
		// loginname=asdasd password2=qwe123 remark=nn email=gtgtgtg
		// truename=gtgtg role[]={1,2} did=2 password=qwe123
		String did = getPara("did");
		String loginname = getPara("loginname");
		String password = getPara("password");
		String truename = getPara("truename");
		String email = getPara("email");
		String remark = getPara("remark");
		String[] role = getParaValues("role");

		// SimpleDateFormat time=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		// String now = time.format(new Date());

		User user = new User();
		user.setDid(new Integer(did));
		user.setLoginName(loginname);
		user.setTurename(truename);
		user.setEmail(email);
		user.setPassword(password);
		user.setRemarks(remark);
		user.setRegisterdate(new Date());		
		user.setRole(role);

		boolean flag = userService.save(user);
		// boolean flag = user.save();
		
		if(flag){
			setAttr("infomsg", "添加成功");
			forwardAction("/user/list");
		}else{
			keepPara();
			setAttr("errormsg", "添加失败");
			forwardAction("/user/form");
		}
		
		
		//redirect("/user/list");
	}
}
