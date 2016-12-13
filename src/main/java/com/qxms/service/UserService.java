package com.qxms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.User;
import com.qxms.model.UserRole;

public class UserService {
	
	public User findByUid(String uid){
		String sql = "select * from `qx_user` where uid=?";
		return User.dao.findFirst(sql, uid);
	}
	
	public User findByup(String username, String password) {
		String sql = "select * from `qx_user` where login_name=? and password=?";
		return User.dao.findFirst(sql, username, password);
	}

	public List<User> findAllUser() {
		Map<String, String> param = new HashMap<String, String>();
		return this.findUserList(param);
	}
	
	public List<User> findUserList(Map<String, String> param) {

		String sql = "select * from `qx_user` where `status` = 1 ";

		Iterator<Entry<String, String>> entries = param.entrySet().iterator();

		//int i = 0;
		while (entries.hasNext()) {
			// if (i == 0) {
			// sql += "where ";
			// } else if (i > 0) {
			// sql += "and ";
			// }
			sql += "and ";
			Map.Entry<String, String> entry = entries.next();
			sql += "`"+entry.getKey() + "`=" + entry.getValue() + " ";
		}
		
		return User.dao.find(sql);
	}
	
	public boolean save(User user){
		boolean flag1 = user.save();
		
		if(flag1){
			//添加角色关联
			String[] roles = user.getRole();
			for (int i = 0; i < roles.length; i++) {
				UserRole ur = new UserRole();
				ur.setUserId(user.getUid());
				ur.setRoleId(new Integer(roles[i]));
				ur.save();
			}			
		}
		return flag1;
	}
}
