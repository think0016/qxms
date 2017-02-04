package com.qxms.controller.sys;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.upload.UploadFile;
import com.qxms.common.utils.MoeFileUtils;
import com.qxms.common.utils.UrlEncoderUtils;
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
		String msgtype = getPara(1);

		if (!StringUtils.isEmpty(msgtype)) {
			String msg = UrlEncoderUtils.decode(getPara(2), "utf-8");
			if ("1".equals(msgtype)) {// 正常信息
				setAttr("infomsg", msg);
			} else {
				setAttr("errormsg", msg);
			}
		}

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
		render("list.html");
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
			String[] temp = { e.getUid().toString(), e.getLoginName(), e.getNickname(), department.getDname(),
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

			setAttr("department", departmentService.findByDid(user.getDid().toString()));
			setAttr("subtitle", "用户修改");
			List<Role> rolelist = user.getRolelist();
			if (rolelist.size() > 0) {
				setAttr("userroleid", rolelist.get(0).getRoleid().toString());
			}

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

		setAttr("user", user);
		setAttr("roles", rlist);
		setAttr("treejson", json);

		render("form.html");
	}

	@Before({ AuthenticationValidator.class })
	public void pwform() {

		setAttr("user", getSession().getAttribute("cache_user"));
		render("pwform.html");
	}

	/**
	 * 验证重名action
	 */
	public void yzloginname() {
		String loginname = getPara("loginname");
		Map<String, String> param = new HashMap<String, String>();
		param.put("login_name", loginname);
		List<User> rs = userService.findOUserList(param);
		if (rs.size() > 0) {
			renderText("0");
		} else {
			renderText("1");
		}
	}

	public void saveuser() {
		String uid = getPara("uid");
		String did = getPara("did");
		String loginname = getPara("loginname");
		String password = getPara("password");
		// String truename = getPara("truename");
		String nickname = getPara("nickname");
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
			user.setNickname(nickname);
			;
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
			user.setNickname(nickname);
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
				// setAttr("infomsg", "添加成功");
				// forwardAction("/user/list");
				String url = "/user/list/0-1-";
				if (update) {
					url = url + UrlEncoderUtils.encode("修改成功", "utf-8");
				} else {
					url = url + UrlEncoderUtils.encode("添加成功", "utf-8");
				}
				redirect(url);
			} else {
				if (update) {
					setAttr("errormsg", "修改失败");
				} else {
					setAttr("errormsg", "添加失败");
				}

				keepPara();
				forwardAction("/user/form");
			}
		} else {
			setAttr("errormsg", "登录名已经存在");
			keepPara();
			forwardAction("/user/form");
		}

		if (update) {
			// 清除缓存
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

		String result = "";
		// 先验证旧密码
		// User user = userService.findByup(loginname, opassword);
		// User user = userService.findByloginname(loginname);
		if (user == null || !SystemService.validatePassword(opassword, user.getPassword())) {
			// setAttr("errormsg", "密码不正确");
			// keepPara();
			// forwardAction("/user/pwform");
			result = "0";//
		} else {
			user.setPassword(SystemService.entryptPassword(npassword));
			if (user.update()) {
				// setAttr("infomsg", "修改密码成功");
				// forwardAction("/index");
				// String url = "/index/1-";
				// url = url + UrlEncoderUtils.encode("修改密码成功", "utf-8");
				// redirect(url);
				result = "1";
			} else {
				// setAttr("errormsg", "密码不正确");
				// keepPara();
				// forwardAction("/user/pwform");
				result = "10";
			}
		}
		renderText(result);
	}

	public void delete() {
		String uid = getPara("uid");
		if (StringUtils.isEmpty(uid)) {
			renderText("0");
		} else {
			int flag = userService.delete(uid);
			CacheKit.remove("menulist", uid);
			CacheKit.remove("userlist", uid);
			renderText(flag + "");
		}
	}

	public void userinfo() {
		User user = (User) getSession().getAttribute("cache_user");

		Department dept = departmentService.findByDid(user.getDid().toString());
		List<Role> rlist = user.getRolelist();
		setAttr("department", dept);
		setAttr("rlist", rlist);
		setAttr("user", user);
		render("userinfoform.html");
	}

	/**
	 * 保存个人信息
	 */
	public void saveuserinfo() {
		User user = (User) getSession().getAttribute("cache_user");

		String email = getPara("email");
		String remarks = getPara("remarks");
		String nickname = getPara("nickname");
		String headphoto = getPara("headphotourl");
		
		user.setHeadphoto(headphoto);
		user.setNickname(nickname);
		user.setRemarks(remarks);
		user.setEmail(email);

		if (user.update()) {
			renderText("1");
		} else {
			renderText("2");
		}
	}

	/**
	 * 上传头像
	 */
	public void uploadheadphoto() {
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("status", "0");
		result.put("msg", "");
		
		UploadFile uf = getFile("headphoto");

		// System.out.println(uf.getContentType());
		// System.out.println(uf.getOriginalFileName());
		// System.out.println(uf.getParameterName());
		// System.out.println(uf.getFile().getPath());
		// System.out.println(JFinal.me().getServletContext().getRealPath("upload/headphoto/"));

		if (MoeFileUtils.isImg(uf.getContentType())) {
			String path = JFinal.me().getServletContext().getRealPath("upload/headphoto") + "\\";
			String filename = ((User) getSession().getAttribute("cache_user")).getUid().toString() + "_"
					+ (new Date().getTime()) + "." + MoeFileUtils.getExtname(uf.getOriginalFileName());

			File srcFile = uf.getFile();
			File destFile = new File(path + filename);

			try {
				FileUtils.moveFile(srcFile, destFile);
				result.put("status", "1");
				result.put("msg", filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(uf.getContentType() + "ERROR!!!");
		}

		Gson gson = new Gson();
		String json = gson.toJson(result);
		renderText(json);
	}
}
