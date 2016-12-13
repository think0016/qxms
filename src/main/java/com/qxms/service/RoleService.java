package com.qxms.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.Role;

public class RoleService {
	
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
	

}
