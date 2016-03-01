package com.tianque.serviceList.domain;

public class DrugsSafty extends ServiceListCommon{
	/**
     * 参与人员
     */
    private String personnel;
    
    /**
   	 * 判断是否走网格配置清单查询
   	 */
   	private String mode;
   	
   	/**
   	 * 登录的职能部门的网格id
   	 */
   	private Long funOrgId;

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getFunOrgId() {
		return funOrgId;
	}

	public void setFunOrgId(Long funOrgId) {
		this.funOrgId = funOrgId;
	}
    
	
}