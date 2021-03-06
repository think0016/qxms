package com.qxms.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMenu<M extends BaseMenu<M>> extends Model<M> implements IBean {

	public void setMenuid(java.lang.Integer menuid) {
		set("menuid", menuid);
	}

	public java.lang.Integer getMenuid() {
		return get("menuid");
	}

	public void setParentId(java.lang.Integer parentId) {
		set("parent_id", parentId);
	}

	public java.lang.Integer getParentId() {
		return get("parent_id");
	}

	public void setParentIds(java.lang.String parentIds) {
		set("parent_ids", parentIds);
	}

	public java.lang.String getParentIds() {
		return get("parent_ids");
	}

	public void setMname(java.lang.String mname) {
		set("mname", mname);
	}

	public java.lang.String getMname() {
		return get("mname");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}

	public java.lang.Integer getSort() {
		return get("sort");
	}

	public void setHref(java.lang.String href) {
		set("href", href);
	}

	public java.lang.String getHref() {
		return get("href");
	}

	public void setIcon(java.lang.String icon) {
		set("icon", icon);
	}

	public java.lang.String getIcon() {
		return get("icon");
	}

	public void setMtype(java.lang.Integer mtype) {
		set("mtype", mtype);
	}

	public java.lang.Integer getMtype() {
		return get("mtype");
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
