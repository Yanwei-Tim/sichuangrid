package com.tianque.plugin.taskList.domain;

import com.tianque.domain.Organization;

public class OrgTreeNode implements java.io.Serializable {
	private static final long serialVersionUID = -1199326525461759192L;
	private String id;
	private String pId;
	private String name;
	private boolean isParent = true;
	private boolean checked = false;
	private boolean open = false;
	private String orginternalcode;

	public OrgTreeNode(){
		
	}
	
	public OrgTreeNode(Organization organization){
		this.id=organization.getId().toString();
		this.pId=organization.getParentOrg().getId().toString();
		this.name=organization.getOrgName();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getOrginternalcode() {
		return orginternalcode;
	}

	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

}
