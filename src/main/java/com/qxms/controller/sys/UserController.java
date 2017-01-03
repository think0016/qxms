package com.qxms.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.qxms.interceptor.common.AuthenticationValidator;
import com.qxms.model.Department;
import com.qxms.model.Role;
import com.qxms.model.TreeNode;
import com.qxms.model.User;
import com.qxms.service.sys.DepartmentService;
import com.qxms.service.sys.RoleService;
import com.qxms.service.sys.SystemService;
import com.qxms.service.sys.UserService;

public class UserController extends Controller {
	public static final UserService userService = new UserService();
	public static final DepartmentService departmentService = new DepartmentService();
	public static final RoleService roleservice = new RoleService();
	// public static final SystemService systemService = new SystemService();

	public void index() {
		redirect("/user/list");
	}

	@Before({ AuthenticationValidator.class })
	public void list() {

		String did = getPara(0);

		// List<User> list = null;
		// if (did != null) {
		// Map<String, String> param = new HashMap<String, String>();
		// param.put("did", did);
		// list = userService.findUserList(param);
		// } else {
		did = "0";
		// list = userService.findAllUser();
		// }

		// 部门树信息
		List<Department> dlist = departmentService.findAllDepartment();
		List<TreeNode> nodes = departmentService.gettreedata(dlist);

		Gson gson = new Gson();
		String json = gson.toJson(nodes);

		// setAttr("userlist", list);
		setAttr("treejson", json);
		setAttr("did", did);
		render("list.jsp");
	}

	public void getjsonlist() {
		String did = getPara("did");
		List<User> list = null;
		if (!"0".equals(did)) {
			// Map<String, String> param = new HashMap<String, String>();
			// param.put("did", did);
			// list = userService.findUserList(param);
			list = userService.findUserList_did(did);
		} else {
			// did = "1";
			list = userService.findAllUser();
		}

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			User e = list.get(i);
			Department department = e.getDepartment();
			String[] temp = { e.getUid().toString(), e.getLoginName(), e.getTurename(), department.getDname(),
					e.getFormaterLogindate(""), "" };
			result.add(temp);
		}
		Gson gson = new Gson();
		String json = "{\"data\":" + gson.toJson(result) + "}";

		renderText(json);
	}

	@Before({ AuthenticationValidator.class })
	public void form() {

		// 先判断是修改还是新增
		User user = new User();
		String uid = getPara(0);
		String did = getPara(1);// 部门筛选
		if (!StringUtils.isEmpty(uid) && !"0".equals(uid)) {
			user = userService.findByUid(uid);
			setAttr("user", user);
			setAttr("department", departmentService.findByDid(user.getDid().toString()));
			setAttr("subtitle", "用户修改");
			setAttr("userroleid", user.getRolelist().get(0).getRoleid().toString());
		} else {
			if (!StringUtils.isEmpty(did) && !"0".equals(did)) {
				setAttr("department", departmentService.findByDid(did));
			} else {
				setAttr("department", departmentService.findByDid("10001"));
			}
			setAttr("subtitle", "用户添加");
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

		render("form.jsp");
	}

	@Before({ AuthenticationValidator.class })
	public void pwform() {

		setAttr("user", getSession().getAttribute("cache_user"));

		render("pwform.jsp");
	}
	
	/**
	 * 验证重名action
	 */
	public void yzloginname(){
		String loginname = getPara("loginname");
		Map<String, String> param = new HashMap<String, String>();
		param.put("login_name", loginname);
		List<User> rs = userService.findUserList(param);
		if(rs.size()>0){
			renderText("0");
		}else{
			renderText("1");
		}
	}
	
	public void saveuser() {
		String uid = getPara("uid");
		String did = getPara("did");
		String loginname = getPara("loginname");
		String password = getPara("password");
		String truename = getPara("truename");
		String gender = getPara("gender");
		String email = getPara("email");
		String remark = getPara("remark");
		String rolex = getPara("role");
		String[] role = { rolex };
		// String[] role = getParaValues("role");

		boolean update = false;
		boolean lnflag = false;// 重名标记
		User user = new User();
		if (!StringUtils.isEmpty(uid)) {
			update = true;
			user = userService.findByUid(uid);
			user.setTurename(truename);
			user.setEmail(email);
			user.setGender(gender);
			user.setRemarks(remark);
			user.setRole(role);
		} else {
			Map<String, String> param = new HashMap<String, String>();
			param.put("login_name", loginname);
			List<User> rs = userService.findUserList(param);
			if (rs.size() > 0) {
				lnflag = true;
			}
			user.setDid(new Integer(did));
			user.setLoginName(loginname);
			user.setTurename(truename);
			user.setEmail(email);
			user.setGender(gender);
			user.setPassword(SystemService.entryptPassword(password));
			user.setRemarks(remark);
			user.setRegisterdate(new Date());
			user.setRole(role);
		}

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

		if(update){
			//清除缓存
			CacheKit.remove("menulist", uid);
			CacheKit.remove("userlist", uid);
		}
		// boolean flag = user.save();

		// redirect("/user/list");
	}

	public void resetpassword() {
		// String uid = getPara("uid");
		// String loginname = getPara("loginname");
		User user = (User) getSession().getAttribute("cache_user");
		String opassword = getPara("opassword");
		String npassword = getPara("npassword");

		// 先验证旧密码
		// User user = userService.findByup(loginname, opassword);
		// User user = userService.findByloginname(loginname);
		if (user == null || !SystemService.validatePassword(opassword, user.getPassword())) {
			setAttr("errormsg", "密码不正确");
			keepPara();
			forwardAction("/user/pwform");
		} else {
			user.setPassword(SystemService.entryptPassword(npassword));
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
		int flag = userService.delete(uid);
		CacheKit.remove("menulist", uid);
		CacheKit.remove("userlist", uid);
		renderText(flag + "");
	}

	public void userinfo() {
		User user = (User) getSession().getAttribute("cache_user");

		Department dept = departmentService.findByDid(user.getDid().toString());
		List<Role> rlist = user.getRolelist();
		setAttr("department", dept);
		setAttr("rlist", rlist);
		setAttr("user", user);
		render("userinfoform.jsp");
	}

	/**
	 * 保存个人信息
	 */
	public void saveuserinfo() {
		User user = (User) getSession().getAttribute("cache_user");

		String email = getPara("email");
		String remarks = getPara("remarks");
		String truename = getPara("truename");

		user.setTurename(truename);
		user.setRemarks(remarks);
		user.setEmail(email);

		if (user.update()) {
			renderText("1");
		} else {
			renderText("2");
		}

	}
}
