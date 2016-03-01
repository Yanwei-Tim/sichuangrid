package com.tianque.working.vo;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.working.domain.MyDepartmentGrop;

/**
 * 作为数据容器存放数据返回到前端（写发文页面右侧的部门列表）
 * 
 * @author liudongdong
 */
public class MyDepartmentAndOrgList {
	private MyDepartmentGrop myDepartmentGrop;
	private List<Organization> orgList;

	public MyDepartmentGrop getMyDepartmentGrop() {
		return myDepartmentGrop;
	}

	public void setMyDepartmentGrop(MyDepartmentGrop myDepartmentGrop) {
		this.myDepartmentGrop = myDepartmentGrop;
	}

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

}
