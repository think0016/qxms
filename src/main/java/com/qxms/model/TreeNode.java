package com.qxms.model;

public class TreeNode {
	private int id;
	private int pId;
	private String name;
	private boolean checked;
	private boolean chkDisabled;
	private boolean open;
	
	
	public TreeNode(int id, int pId, String name) {
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	public TreeNode() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	
}
