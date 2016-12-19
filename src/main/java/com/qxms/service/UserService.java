package com.qxms.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.User;
import com.qxms.model.UserRole;

public class UserService {

	public User findByloginname(String loginname) {
		String sql = "select * from `qx_user` where login_name=?";
		return User.dao.findFirst(sql, loginname);
	}
	
	public User findByUid(String uid) {
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

		// int i = 0;
		while (entries.hasNext()) {
			// if (i == 0) {
			// sql += "where ";
			// } else if (i > 0) {
			// sql += "and ";
			// }
			sql += "and ";
			Map.Entry<String, String> entry = entries.next();
			sql += "`" + entry.getKey() + "`='" + entry.getValue() + "' ";
		}
		System.out.println(sql);
		return User.dao.find(sql);
	}

	public int save(User user, boolean update) {
		int flag = 0;
		if (update) {
			boolean flag1 = user.update();
			if (flag1) {
				// 先删除所有角色关联
				String sql = "select * from `qx_user_role` `q` where `q`.`user_id` = ?";
				List<UserRole> list= UserRole.dao.find(sql);
				for (int i = 0; i < list.size(); i++) {
					list.get(i).delete();
				}
				
				// 添加角色关联
				String[] roles = user.getRole();
				for (int i = 0; i < roles.length; i++) {
					UserRole ur = new UserRole();
					ur.setUserId(user.getUid());
					ur.setRoleId(new Integer(roles[i]));
					ur.save();
				}
			}
			flag = 1;
		} else {
			boolean flag1 = user.save();
			if (flag1) {
				// 添加角色关联
				String[] roles = user.getRole();
				for (int i = 0; i < roles.length; i++) {
					UserRole ur = new UserRole();
					ur.setUserId(user.getUid());
					ur.setRoleId(new Integer(roles[i]));
					ur.save();
				}
			}
			flag = 1;
		}
		return flag;
	}
	
	public boolean delete(String uid){
		User user = this.findByUid(uid);
		user.setStatus(new Integer(0));
		return user.update();		
	}
}
