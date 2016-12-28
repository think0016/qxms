package com.qxms.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.qxms.model.Menu;
import com.qxms.model.Role;
import com.qxms.model.RoleMenu;
import com.qxms.model.TreeNode;

public class RoleService {
	
	
	public Role findRoleById(String roleid){
		String sql = "select * from `qx_role` where `del_flag` = 0 and `roleid` = ?";
		return Role.dao.findFirst(sql, roleid);
	}
	
	public List<Role> findAllRole() {
		Map<String, String> param = new HashMap<String, String>();
		return this.findRoleList(param);
	}
	
	public List<Role> findRoleList(Map<String, String> param) {
		String sql = "select * from `qx_role` where `del_flag` = 0 ";
		
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
		//sql +="ORDER BY sort DESC ";
		return Role.dao.find(sql);
	}
	
	public boolean save(Role role, boolean update){
		boolean flag = true;
		if (update) {
			flag = role.update();
		} else {
			flag = role.save();

		}
		return flag;
	}
	
	public boolean delete(String roleid){
		boolean flag = true;
		Role role = this.findRoleById(roleid);
		role.setDelFlag(new Integer("1"));
		role.update();
		return flag;
	}
	
	public List<TreeNode> getAuthorizationTreedata(List<Menu> alllist,List<Menu> menulist){
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		for (int i = 0; i < alllist.size(); i++) {
			Menu menu = alllist.get(i);
			TreeNode node = new TreeNode(menu.getMenuid(),menu.getParentId(),menu.getMname());
			node.setOpen(true);
			if(menulist.contains(menu)){
				node.setChecked(true);
			}
			treenodes.add(node);
		}
		return treenodes;
	}
	
	/**
	 * 授权操作
	 * @param roleid
	 * @param menuid
	 * @return
	 */
	public boolean rolegrant(String roleid , String menuid){
		String[] menuid_arr = StringUtils.split(menuid, ",");
		
		//先删除role_menu
		String sql = "DELETE FROM `qx_role_menu` WHERE `role_id`=?";
		//List<RoleMenu> rolemenu = RoleMenu.dao.;
		Db.update(sql,roleid);
		
		//添加role_menu
		for (int i = 0; i < menuid_arr.length; i++) {
			RoleMenu rolemenu = new RoleMenu();
			rolemenu.setMenuId(new Integer(menuid_arr[i]));
			rolemenu.setRoleId(new Integer(roleid));
			rolemenu.save();
		}		
		
		return true;
	}
	
}
