package com.qxms.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

	public void setRoleid(java.lang.Integer roleid) {
		set("roleid", roleid);
	}

	public java.lang.Integer getRoleid() {
		return get("roleid");
	}

	public void setRolename(java.lang.String rolename) {
		set("rolename", rolename);
	}

	public java.lang.String getRolename() {
		return get("rolename");
	}

	public void setRoleType(java.lang.String roleType) {
		set("role_type", roleType);
	}

	public java.lang.String getRoleType() {
		return get("role_type");
	}

	public void setCreateBy(java.lang.Integer createBy) {
		set("create_by", createBy);
	}

	public java.lang.Integer getCreateBy() {
		return get("create_by");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
	}

	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public void setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
	}

	public java.lang.String getRemarks() {
		return get("remarks");
	}

	public void setDelFlag(java.lang.Integer delFlag) {
		set("del_flag", delFlag);
	}

	public java.lang.Integer getDelFlag() {
		return get("del_flag");
	}

}
