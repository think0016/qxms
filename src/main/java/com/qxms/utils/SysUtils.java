package com.qxms.utils;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.ehcache.CacheKit;
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

	/**
	 * 获取当前用户授权菜单(HTML)
	 * 
	 * @return
	 */
	public static String getMenuList_HTML(String uid) {
		User user = userService.getCacheUser(uid);
		// List<Role> list=user.getRolelist();

		String MenuHTML = CacheKit.get("menulist", uid);
		if (MenuHTML == null) {// 缓存中没有就重新生成
			List<Menu> menuList = new ArrayList<Menu>();
			if (userService.isAdmin(user)) {
				// 超级管理员
				menuList = menuservice.findAllShowMenu();

			} else {
				menuList = menuservice.findShowMenu(uid);
			}

			List<Menu> list = new ArrayList<Menu>();

			int mid = 10001;
			MenuService.sortList(list, menuList, mid, true);

			StringBuffer html = new StringBuffer("<ul class=\"sidebar-menu\"><li class=\"header\"></li>");
			int n1 = 0;
			// 先开始第一层
			for (int i = 0; i < list.size();) {

				// 是否有下一层
				if ((i + 1) < list.size()
						&& list.get(i).getMenuid().intValue() == list.get((i + 1)).getParentId().intValue()) {
					html.append("<li id=\"menu-"+list.get(i).getMenuid()+"\" class=\"treeview\">");
					html.append("<a href=\"" + list.get(i).getTrueHref() + "\"><i class=\"fa fa-star\"></i> <span>"
							+ list.get(i).getMname() + "</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a>");
					html.append("<ul class=\"treeview-menu\">");
					for (int j = i + 1; j < list.size();) {
						n1 = j;
						//System.out.println("j:" + j);
						if (list.get(i).getMenuid().intValue() == list.get(j).getParentId().intValue()) {

							// 是否有下一层
							if ((j + 1) < list.size() && list.get(j).getMenuid().intValue() == list.get((j + 1))
									.getParentId().intValue()) {
								html.append("<li id=\"menu-"+list.get(j).getMenuid()+"\"><a href=\"" + list.get(j).getTrueHref()
										+ "\"><i class=\"fa fa-circle-o\"></i> <span>" + list.get(j).getMname() + "</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a>");
								html.append("<ul class=\"treeview-menu\">");

								for (int k = j + 1; k < list.size();) {
									//System.out.println("k:" + k);
									n1 = k;

									if (list.get(j).getMenuid().intValue() == list.get(k).getParentId().intValue()) {

										// 是否有下一层
										if ((k + 1) < list.size() && list.get(k).getMenuid().intValue() == list
												.get((k + 1)).getParentId().intValue()) {
											html.append("<li id=\"menu-"+list.get(k).getMenuid()+"\"><a href=\"" + list.get(k).getTrueHref()
													+ "\"><i class=\"fa fa-circle-o\"></i> <span>" + list.get(k).getMname()
													+ "</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a>");
											html.append("<ul class=\"treeview-menu\">");

											for (int m = k + 1; m < list.size();) {
												//System.out.println("m:" + m);
												n1 = m;

												if (list.get(k).getMenuid().intValue() == list.get(m).getParentId()
														.intValue()) {
													html.append("<li><a href=\"" + list.get(m).getTrueHref()
															+ "\"><i class=\"fa fa-circle-o\"></i> <span>"
															+ list.get(m).getMname() + "</span></a>");

													html.append("</li>");
												} else {
													// System.out.println("B"+n1);
													n1 = n1 - 1;
													break;
												}

												m = n1 + 1;
											}

											html.append("</ul>");
										}else{
											html.append("<li id=\"menu-"+list.get(k).getMenuid()+"\"><a href=\"" + list.get(k).getTrueHref()
													+ "\"><i class=\"fa fa-circle-o\"></i> <span>" + list.get(k).getMname()
													+ "</span></a>");
										}
										// 是否有下一层END
										html.append("</li>");
									} else {
										// System.out.println("B"+n1);
										n1 = n1 - 1;
										break;
									}

									k = n1 + 1;
								}

								html.append("</ul>");
							}else{
								html.append("<li id=\"menu-"+list.get(j).getMenuid()+"\"><a href=\"" + list.get(j).getTrueHref()
										+ "\"><i class=\"fa fa-circle-o\"></i> <span>" + list.get(j).getMname() + "</span></a>");
								
							}
							// 是否有下一层END
							html.append("</li>");
						} else {
							n1 = n1 - 1;
							break;
						}

						j = n1 + 1;
					}
					html.append("</ul>");
				} else {
					html.append("<li id=\"menu-"+list.get(i).getMenuid()+"\" class=\"treeview\">");
					html.append("<a href=\"" + list.get(i).getTrueHref() + "\"><i class=\"fa fa-circle-o\"></i> <span>"
							+ list.get(i).getMname() + "</span></a>");
					// n1++;
				}

				html.append("</li>");

				i = n1 + 1;
			}
			html.append("</ul>");
			// for (int i = 0; i < list.size();i++) {
			// System.out.println(list.get(i).getMname()+"("+list.get(i).getMenuid()+"):::::"+list.get(i).getParentId());
			// }

			MenuHTML = html.toString();
			CacheKit.put("menulist", uid, MenuHTML);
		}
		return MenuHTML;
	}
}
