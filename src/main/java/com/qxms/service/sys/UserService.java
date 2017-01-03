package com.qxms.service.sys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.plugin.ehcache.CacheKit;
import com.qxms.model.Department;
import com.qxms.model.Role;
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

		return User.dao.find(sql);
	}
	
	/**
	 * 查找当前部门及其下属部门下的所有用户
	 */
	public List<User> findUserList_did(String did) {	
		
		List<Department> dlist= Department.dao.find("select * from `qx_department` where `del_flag` = 0 AND `parent_dids` like '%"+did+"%'");
		
		String sql = "select * from `qx_user` where `status` = 1 AND ( `did`=?";

		for (int i = 0; i < dlist.size(); i++) {
			sql += " OR `did`="+dlist.get(i).getDid().toString()+" "; 
		}
		sql += ")"; 
		return User.dao.find(sql,did);
	}
	
	public int save(User user, boolean update) {
		int flag = 0;
		if (update) {
			boolean flag1 = user.update();
			if (flag1) {
				// 先删除所有角色关联
				String sql = "select * from `qx_user_role` `q` where `q`.`user_id` = ?";
				List<UserRole> list= UserRole.dao.find(sql,user.getUid().toString());
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
	
	public int delete(String uid){
		int flag = 0;
		User user = this.findByUid(uid);
		if(!this.isAdmin(user)){
			user.setStatus(new Integer(0));
			if(user.update()){
				flag = 1;
			};
		}else{
			flag = 2;//超管权限不可删除
		}
		return flag;		
	}
	
	public boolean isAdmin(User user){
		List<Role> list=user.getRolelist();
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			Role role = list.get(i);
			if("global".equals(role.getRoleType())){
				flag = true;
				break;
			};
		}				
		return flag;
	}
	
	/**
	 * 获取缓存中用户信息，没有就查数据库
	 * @param uid
	 * @return
	 */
	public User getCacheUser(String uid){
		User user= CacheKit.get("userlist", uid);
		if(user == null){
			user = this.findByUid(uid);
		}
		return user;
	}
}
