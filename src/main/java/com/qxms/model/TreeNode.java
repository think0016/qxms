package com.qxms.model;

public class TreeNode {
	private int id;
	private int pId;
	private String name;
	
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

}
