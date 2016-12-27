package com.qxms.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Role;
import com.qxms.model.TreeNode;
import com.qxms.model.User;
import com.qxms.service.MenuService;
import com.qxms.service.RoleService;

public class RoleController extends Controller {

	public static final RoleService roleService = new RoleService();
	public static final MenuService menuService = new MenuService();

	public void index() {
		redirect("/role/list");
	}
	
	public void list() {
		List<Role> rolelist = roleService.findAllRole();
		setAttr("rolelist", rolelist);
		render("list.jsp");
	}

	public void form(){
		String roleid = getPara(0);
		if(!StringUtils.isEmpty(roleid)){
			Role role = roleService.findRoleById(roleid);
			setAttr("role", role);
			setAttr("subtitle", "角色修改");
		}else{
			setAttr("subtitle", "角色添加");
		}
		render("form.jsp");
	}
	
	public void saverole(){
		User cuser = (User)getSession().getAttribute("cache_user");
		
		String roleid = getPara("roleid");
		String rolename = getPara("rolename");
		String remarks = getPara("remarks");
		
		boolean update = false;
		Role role = new Role();
		if (StringUtils.isEmpty(roleid) == false) {
			update = true;
			//user = userService.findByUid(uid);
			role = roleService.findRoleById(roleid);
		}
		
		role.setRolename(rolename);
		role.setRemarks(remarks);
		if(!update){
			role.setCreateBy(cuser.getUid());
			role.setCreateDate(new Date());
		}
		
		boolean flag = roleService.save(role, update);
		
		if (flag) {
			setAttr("infomsg", "添加成功");
			forwardAction("/role/list");
		} else {
			keepPara();
			setAttr("errormsg", "添加角色失败");
			forwardAction("/role/form");
		}
	}
	
	public void delete(){
		String roleid = getPara(0);
		boolean flag = roleService.delete(roleid);
		if(flag){
			renderText("1");
		}else{
			renderText("2");
		}
	}
	
	public void grantform(){
		String roleid = getPara(0);
		
		if(StringUtils.isEmpty(roleid)){
			setAttr("errormsg", "错误请求");
			forwardAction("/role/list");
		}else{
			Role role = roleService.findRoleById(roleid);
			
			if(role == null){
				setAttr("errormsg", "错误请求");
				forwardAction("/role/list");
			}else if("global".equals(role.getRoleType())){
				setAttr("errormsg", "超级管理员角色不能执行此项操作");
				forwardAction("/role/list");
			}else{
				// 菜单树信息				
				List<TreeNode> nodes = roleService.getAuthorizationTreedata(menuService.findAllMenu(),role.getMenu());
				Gson gson = new Gson();
				String json = gson.toJson(nodes);

				setAttr("treejson", json);
				setAttr("role", role);
				render("grantform.jsp");
			}
			
		}
	}
	
	public void grant(){
		String returnmsg = "";
		
		String roleid = getPara("roleid");
		String menuid = getPara("menuid");
		
		if(StringUtils.isEmpty(roleid) || StringUtils.isEmpty(menuid) ){
			returnmsg = "错误请求";
		}else{
			boolean flag = roleService.rolegrant(roleid, menuid);
			if(flag){
				returnmsg = "1";
			}
		}
		
		renderText(returnmsg);
	}
}
