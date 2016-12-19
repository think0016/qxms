package com.qxms.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Department;
import com.qxms.model.TreeNode;
import com.qxms.model.User;
import com.qxms.service.DepartmentService;
import com.qxms.service.UserService;

public class DepartmentController extends Controller {

	public static final DepartmentService departmentService = new DepartmentService();
	public static final UserService userService = new UserService();

	public void index() {
		redirect("/department/list");
	}
	
	
	public void list() {
		
		String did = getPara(0);
		List<Department> dlist = departmentService.findAllDepartment();
		List<Department> list = null;
		if(StringUtils.isEmpty(did)==false){
			list = departmentService.findDepartmentListBypids(did);
		}else{
			did = "1";
			list = dlist;
		}
		
		List<TreeNode> nodes = departmentService.gettreedata(dlist);

		Gson gson = new Gson();
		String json = gson.toJson(nodes);
		
		setAttr("departmentlist", list);
		setAttr("treejson", json);
		setAttr("did", did);
		render("list.jsp");
	}

	public void form(){
		
		String did = getPara(0);
		
		Department department = new Department();
		if (!StringUtils.isEmpty(did)) {
			department = departmentService.findByDid(did);
			//user = userService.findByUid(uid);
		}	
		
		List<Department> dlist = departmentService.findAllDepartment();
		
		setAttr("dlist", dlist);
		setAttr("department", department);
		render("form.jsp");
	}
	
	public void savedepartment(){
		User cuser = (User)getSession().getAttribute("user");
		
		String did = getPara("did");
		String pid = getPara("pid");
		String dname = getPara("dname");
		String manager = getPara("manager");
		String sort = getPara("sort");
		String remarks = getPara("remarks");
		
		boolean update = false;
		Department department = new Department();
		if (StringUtils.isEmpty(did) == false) {
			update = true;
			//user = userService.findByUid(uid);
			department = departmentService.findByDid(did);
		}
		if (StringUtils.isEmpty(sort)) {
			sort = "30";
		}
		
		// 获取pidpath
		String pidpath = "0";
		if(!"0".equals(pid)){
			Department p_department = departmentService.findByDid(pid);
			pidpath = p_department.getParentDids()+","+pid;
		}

		
		department.setDname(dname);
		department.setParentDid(new Integer(pid));
		department.setParentDids(pidpath);
		department.setManager(manager);
		department.setSort(new Integer(sort));
		department.setRemarks(remarks);
		if(!update){
			department.setCreateBy(cuser.getUid());
			department.setCreateDate(new Date());
		}		
		
		boolean flag = departmentService.save(department, update);
		
		if (flag) {
			setAttr("infomsg", "添加成功");
			forwardAction("/department/list");
		} else {
			keepPara();
			setAttr("errormsg", "添加部门失败");
			forwardAction("/department/form");
		}
	}
	
	public void delete(){
		String did = getPara(0);
		boolean flag = departmentService.delete(did);
		if(flag){
			renderText("1");
		}else{
			renderText("2");
		}
		
	}
}
