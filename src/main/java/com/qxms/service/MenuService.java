package com.qxms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.Department;
import com.qxms.model.Menu;
import com.qxms.model.TreeNode;

public class MenuService {
	public Menu findMenuById(String menuid) {
		String sql = "select * from `qx_menu` where `del_flag` = 0 AND `menuid`=?";
		return Menu.dao.findFirst(sql, menuid);
	}

	public List<Menu> findAllMenu() {
		Map<String, String> param = new HashMap<String, String>();
		return this.findMenuList(param);
	}

	/**
	 * 获取用户菜单(超级管理员)
	 * 
	 * @return
	 */
	public List<Menu> findAllShowMenu() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("mtype", "0");
		return this.findMenuList(param);
	}

	/**
	 * 获取用户菜单(其他用户)
	 * 
	 * @return
	 */
	public List<Menu> findShowMenu(String uid) {
		String sql = "SELECT `e`.* FROM `qx_user` `a` " + "LEFT JOIN `qx_user_role` `b` ON `a`.`uid`=`b`.`user_id` "
				+ "LEFT JOIN `qx_role` `c` ON `c`.`roleid`=`b`.`role_id`  "
				+ "LEFT JOIN `qx_role_menu` `d` ON `d`.`role_id` = `c`.`roleid` "
				+ "LEFT JOIN `qx_menu` `e` ON `e`.`menuid` = `d`.`menu_id` "
				+ "WHERE `e`.`mtype` = '0' AND `a`.`uid` = ? GROUP BY `e`.`menuid` ORDER BY `e`.`sort` DESC";
		return Menu.dao.find(sql, uid);
	}

	public List<Menu> findMenuList(Map<String, String> param) {
		String sql = "select * from `qx_menu` where `del_flag` = 0 ";

		Iterator<Entry<String, String>> entries = param.entrySet().iterator();

		while (entries.hasNext()) {
			// if (i == 0) {
			// sql += "where ";
			// } else if (i > 0) {
			// sql += "and ";
			// }
			sql += "and ";
			Map.Entry<String, String> entry = entries.next();
			sql += entry.getKey() + "=" + entry.getValue() + " ";
		}
		sql += "ORDER BY sort DESC ";
		return Menu.dao.find(sql);
	}

	public List<TreeNode> gettreedata(List<Menu> list) {
		List<TreeNode> rs = new ArrayList<TreeNode>();
		for (int i = 0; i < list.size(); i++) {
			Menu menu = list.get(i);
			// String nodename = menu.getMname() +" ("+menu.getHref()+")";
			String nodename = menu.getMname();
			TreeNode node = new TreeNode(menu.getMenuid(), menu.getParentId(), nodename);
			rs.add(node);
		}
		return rs;
	}

	public boolean save(Menu menu, boolean update) {
		boolean flag = true;
		if (update) {
			flag = menu.update();
		} else {
			flag = menu.save();

		}
		return flag;
	}

	public boolean delete(String menuid) {
		boolean flag = true;
		List<Menu> list = this.findMenuListByPids(menuid);
		for (int i = 0; i < list.size(); i++) {
			Menu menu = list.get(i);
			menu.setDelFlag(new Integer(1));
			menu.update();
		}
		return flag;
	}

	public List<Menu> findMenuListByPids(String pid) {
		String sql = "select * from `qx_menu` where `del_flag` = 0 and (`parent_ids` like '%" + pid
				+ "%' OR `menuid` = ?)";
		return Menu.dao.find(sql, pid);
	}

	/**
	 * 树排序
	 * 
	 * @param list
	 * @param sourcelist
	 * @param parentId
	 * @param cascade
	 */
	public static void sortList(List<Menu> list, List<Menu> sourcelist, int parentId, boolean cascade) {
		for (int i = 0; i < sourcelist.size(); i++) {
			Menu e = sourcelist.get(i);
			if (e.getParentId() != null && e.getParentId().intValue() == parentId) {
				list.add(e);
				if (cascade) {
					for (int j = 0; j < sourcelist.size(); j++) {
						Menu child = sourcelist.get(j);
						if (child.getParentId() != null && child.getParentId().intValue() == e.getMenuid().intValue()) {
							sortList(list, sourcelist, e.getMenuid(), true);
							break;
						}
					}
				}
			}

		}
	}
}
