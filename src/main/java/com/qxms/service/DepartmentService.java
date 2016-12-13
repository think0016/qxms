package com.qxms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.Department;
import com.qxms.model.TreeNode;

public class DepartmentService {
	
	public List<Department> findAllDepartment() {
		Map<String, String> param = new HashMap<String, String>();
		return this.findDepartmentList(param);
	}
	
	public List<Department> findDepartmentList(Map<String, String> param) {
		String sql = "select * from `qx_department` where `del_flag` = 0 ";
		
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
		sql +="ORDER BY sort DESC ";
		return Department.dao.find(sql);
	}
	
	public List<TreeNode> gettreedata(List<Department> list){
		
		List<TreeNode> rs= new ArrayList<TreeNode>();
		
		for (int i = 0; i < list.size(); i++) {
			Department dep = list.get(i);
			
			TreeNode node = new TreeNode(dep.getDid(),dep.getParentDid(),dep.getDname());
			rs.add(node);
		}
		
		return rs;
	}
	
	
}
