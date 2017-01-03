package com.qxms.utils;

import java.util.List;

import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.qxms.model.Menu;
import com.qxms.model.User;
import com.qxms.service.sys.MenuService;
import com.qxms.service.sys.UserService;

public class SysUtils {
	public static final UserService userService = new UserService();
	public static final MenuService menuservice = new MenuService();

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList(String uid) {
		User user = userService.getCacheUser(uid);
		// List<Role> list=user.getRolelist();

		List<Menu> menuList = CacheKit.get("menulist", uid);

		if (menuList == null) {
			if (userService.isAdmin(user)) {
				// 超级管理员
				menuList = menuservice.findAllShowMenu();

			} else {
				menuList = menuservice.findShowMenu(uid);
			}
			
			CacheKit.put("menulist", uid, menuList);
		}

		return menuList;
	}
}
