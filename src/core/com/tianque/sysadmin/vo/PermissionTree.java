package com.tianque.sysadmin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.Permission;

public class PermissionTree implements Serializable {

	private String name;
	private String ename;
	private String baseUrl;
	private String leaderUrl;
	private String gridUrl;
	private String pename;
	private Long indexId;

	private List<PermissionTree> children;

	public PermissionTree() {

	}

	public PermissionTree(Permission permission) {
		this.name = permission.getCname();
		this.ename = permission.getEname();
		this.baseUrl = permission.getNormalUrl();
		this.leaderUrl = permission.getLeaderUrl();
		this.gridUrl = permission.getGridUrl();
		this.pename = "A_P_NAME";
		this.indexId = permission.getIndexId();
	}

	public List<PermissionTree> getChildren() {
		if (children == null) {
			children = new ArrayList<PermissionTree>();
		}
		return children;
	}

	public void setChildren(List<PermissionTree> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getLeaderUrl() {
		return leaderUrl;
	}

	public void setLeaderUrl(String leaderUrl) {
		this.leaderUrl = leaderUrl;
	}

	public String getGridUrl() {
		return gridUrl;
	}

	public void setGridUrl(String gridUrl) {
		this.gridUrl = gridUrl;
	}

	public String getPename() {
		return pename;
	}

	public void setPename(String pename) {
		this.pename = pename;
	}

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

}
