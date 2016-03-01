package com.tianque.domain.vo;

import java.util.Date;
import java.util.List;

import com.tianque.domain.Role;
import com.tianque.domain.vo.UserCountAboutVo;

/**
 * 账号统计下载辅助类
 * @author N-287
 *
 */
public class DownLoadUserAbout {

	private String orgName;
	private String userName;
	private String name;
	private String lastLoginTime;
	private String createDate;
	private String roles;
	private String pcOrMobile;
	private String activationTime;
	public DownLoadUserAbout(UserCountAboutVo vo){
		this.orgName = vo.getOrganization().getOrgName();
		this.userName = vo.getUserName();
		this.name = vo.getName();
		this.lastLoginTime = formateYMDHMS(vo.getLastLoginTime());
		this.createDate = formateYMDHMS(vo.getCreateDate());
		this.roles = getRole(vo.getRoles());
		this.pcOrMobile = getPcOrMobile(vo);
		this.activationTime = formateYMDHMS(vo.getActivationTime());
	}
	private String getRole(List<Role> roles){
		int j = 0;
		String roleNames = "";
		if(roles!=null && !roles.isEmpty()){
        	int n = roles.size();
            for(int m=0;m<n;m++){
            	j++;
                if(j!=n){
                    roleNames+=roles.get(m).getRoleName()+",";
                }else{
                	roleNames+=roles.get(m).getRoleName();
                }
            }
        }else{
        	roleNames = "系统管理员";
        }
		return roleNames;
	}
	
	/**
	 * 返回:2010-09-10 21:08:17
	 */
	private String formateYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", date);
	}
	
	private String getPcOrMobile(UserCountAboutVo vo){
		String pcOrMobile = "";
		if(vo.getPcusable()== 1 && vo.getMobileusable() == 1){
			pcOrMobile = "PC、手机";	
		}else if(vo.getPcusable() == 1 && vo.getMobileusable() == 0){
			pcOrMobile = "PC";	
		}else if(vo.getPcusable() == 0 && vo.getMobileusable() == 1){
			pcOrMobile = "手机";	
		}
		return pcOrMobile;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPcOrMobile() {
		return pcOrMobile;
	}
	public void setPcOrMobile(String pcOrMobile) {
		this.pcOrMobile = pcOrMobile;
	}
	public String getActivationTime() {
		return activationTime;
	}
	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime;
	}
	
}