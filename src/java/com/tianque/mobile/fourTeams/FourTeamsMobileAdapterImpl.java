package com.tianque.mobile.fourTeams;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsManage.controller.FourTeamsController;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.mobile.base.BaseMobileAction;

/**
 * 
 * @ClassName: FourTeamsMobileAdapterImpl
 * @Description: TODO四支队伍手机端适配器实现类
 * @author wanggz
 * @date 2014-6-13 上午11:21:06
 */

@Transactional
@Scope("request")
@Controller("fourTeamsMobileAdapter")
@Namespace("/mobile/fourTeamsManage")
public class FourTeamsMobileAdapterImpl extends BaseMobileAction implements
		FourTeamsMobileAdapter {

	@Autowired
	private FourTeamsController fourTeamsController;

	private Organization organization;

	private FourTeams fourTeams;

	/**
	 * 查询四支队伍列表
	 */
	@Override
	@Action(value = "findFourTeamsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findFourTeamsForMobile() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "所查询组织机构不能为空！";
			return MOBILE_ERROR;
		}
		fourTeams.setOrganization(organization);
		fourTeamsController.setFourTeams(fourTeams);
		fourTeamsController.searchFourTeamsForIssues();
		gridPage = fourTeamsController.getGridPage();
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public FourTeams getFourTeams() {
		return fourTeams;
	}

	public void setFourTeams(FourTeams fourTeams) {
		this.fourTeams = fourTeams;
	}

}
