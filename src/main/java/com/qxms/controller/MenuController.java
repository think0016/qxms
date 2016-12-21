package com.qxms.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Menu;
import com.qxms.model.Role;
import com.qxms.model.TreeNode;
import com.qxms.model.User;
import com.qxms.service.MenuService;

public class MenuController extends Controller {

	public static final MenuService menuService = new MenuService();

	public void index() {
		redirect("/menu/list");
	}

	public void list() {

		
		// 菜单树信息
		List<Menu> mlist = menuService.findAllMenu();
		List<TreeNode> nodes = menuService.gettreedata(mlist);
		Gson gson = new Gson();
		String json = gson.toJson(nodes);

		setAttr("treejson", json);
		render("list.jsp");
	}

	public void form() {
		String menuid = getPara(0);
		
		if (!StringUtils.isEmpty(menuid)) {
			Menu menu = menuService.findMenuById(menuid);
			setAttr("menu", menu);
		}
		// 菜单树信息
		List<Menu> mlist = menuService.findAllMenu();
		List<TreeNode> nodes = menuService.gettreedata(mlist);
		Gson gson = new Gson();
		String json = gson.toJson(nodes);

		setAttr("treejson", json);
		render("form.jsp");
	}

	public void savemenu() {
		User cuser = (User) getSession().getAttribute("cache_user");

		String menuid = getPara("menuid");
		String pmenuid = getPara("pmenuid");
		String mname = getPara("mname");
		String href = getPara("href");
		String sort = getPara("sort");
		String mtype = getPara("mtype");
		String remarks = getPara("remarks");

		if (StringUtils.isEmpty(sort)) {
			sort = "30";
		}
		boolean update = false;
		Menu menu = new Menu();
		if (!StringUtils.isEmpty(menuid)) {
			menu = menuService.findMenuById(menuid);
			update = true;

		}

		// 获取pidpath
		Menu p_menu = menuService.findMenuById(pmenuid);
		String pidpath = p_menu.getParentIds() + "," + pmenuid;

		menu.setMname(mname);
		menu.setHref(href);
		menu.setParentId(new Integer(pmenuid));
		menu.setParentIds(pidpath);
		menu.setSort(new Integer(sort));
		menu.setMtype(new Integer(mtype));
		menu.setRemarks(remarks);
		if (!update) {
			menu.setCreateBy(cuser.getUid());
			menu.setCreateDate(new Date());
		}

		boolean flag = menuService.save(menu, update);

		if (flag) {
			setAttr("infomsg", "添加成功");
			forwardAction("/menu/list");
		} else {
			keepPara();
			setAttr("errormsg", "添加部门失败");
			forwardAction("/menu/form");
		}
	}

	public void delete() {
		String menuid = getPara("menuid");
		boolean flag = menuService.delete(menuid);
		if (flag) {
			renderText("1");
		} else {
			renderText("2");
		}
	}
}
