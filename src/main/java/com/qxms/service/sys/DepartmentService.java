package com.qxms.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.qxms.model.Department;
import com.qxms.model.TreeNode;

public class DepartmentService {

	public Department findByDid(String did) {
		String sql = "select * from `qx_department` where `del_flag` = 0 and `did` = ?";
		return Department.dao.findFirst(sql, did);
	}

	public List<Department> findAllDepartment() {
		Map<String, String> param = new HashMap<String, String>();
		return this.findDepartmentList(param);
	}

	public List<Department> findDepartmentList(Map<String, String> param) {
		String sql = "select * from `qx_department` where `del_flag` = 0 ";

		Iterator<Entry<String, String>> entries = param.entrySet().iterator();

		while (entries.hasNext()) {
			sql += "and ";
			Map.Entry<String, String> entry = entries.next();
			sql += entry.getKey() + "=" + entry.getValue() + " ";
		}
		sql += "ORDER BY sort DESC ";
		return Department.dao.find(sql);
	}

	public List<TreeNode> gettreedata(List<Department> list) {

		List<TreeNode> rs = new ArrayList<TreeNode>();

		for (int i = 0; i < list.size(); i++) {
			Department dep = list.get(i);

			TreeNode node = new TreeNode(dep.getDid(), dep.getParentDid(), dep.getDname());
			rs.add(node);
		}
		
		
		return rs;
	}

	public List<Department> findDepartmentListBypids(String did) {
		String sql = "select * from `qx_department` where `del_flag` = 0 and (`parent_dids` like '%" + did
				+ "%' OR `did` = ?)";
		return Department.dao.find(sql, did);
	}

	public boolean save(Department department, boolean update) {
		boolean flag = true;
		if (update) {
			flag = department.update();
		} else {
			flag = department.save();

		}
		return flag;
	}
	
	public boolean delete(String did){
		boolean flag = true;
		List<Department> list = this.findDepartmentListBypids(did);
		for (int i = 0; i < list.size(); i++) {
			Department dept = list.get(i);
			dept.setDelFlag(new Integer("1"));
			dept.update();
		}
		
		return flag;
	}
	
	/**
	 * 树排序
	 * @param list
	 * @param sourcelist
	 * @param parentId
	 * @param cascade
	 */
	public static void sortList(List<Department> list, List<Department> sourcelist, int parentId, boolean cascade){
		for (int i = 0; i < sourcelist.size(); i++) {
			Department e = sourcelist.get(i);
			if (e.getParentDid() != null && e.getParentDid().intValue() == parentId) {
				list.add(e);
				if (cascade) {
					for (int j = 0; j < sourcelist.size(); j++) {
						Department child = sourcelist.get(j);
						if (child.getParentDid() != null && child.getParentDid().intValue() == e.getDid().intValue()) {
							sortList(list, sourcelist, e.getDid(), true);
							break;
						}
					}
				}
			}

		}
	}
}
