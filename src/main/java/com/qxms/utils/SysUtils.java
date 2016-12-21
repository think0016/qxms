package com.qxms.utils;

import java.util.List;

import com.qxms.model.Menu;
import com.qxms.model.User;
import com.qxms.service.MenuService;
import com.qxms.service.UserService;

public class SysUtils {
	public static final UserService userService = new UserService();
	public static final MenuService menuservice = new MenuService();
	
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(String uid){
		User user = userService.findByUid(uid);
		//List<Role> list=user.getRolelist();
		
		List<Menu> menuList = null;
		if(userService.isAdmin(user)){
			//超级管理员
			menuList = menuservice.findAllShowMenu();
			
		}else{
			menuList = menuservice.findShowMenu(uid);
		};
		
//		@SuppressWarnings("unchecked")
//		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
//		if (menuList == null){
//			User user = getUser();
//			if (user.isAdmin()){
//				menuList = menuDao.findAllList(new Menu());
//			}else{
//				Menu m = new Menu();
//				m.setUserId(user.getId());
//				menuList = menuDao.findByUserId(m);
//			}
//			putCache(CACHE_MENU_LIST, menuList);
//		}
		return menuList;
	}
}
