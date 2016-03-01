package com.tianque.plugin.dataManage.location.builddatasTemp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.core.base.BaseDomain;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.builddatasTemp.domain.BuilddatasTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("builddatasTempService")
public class BuilddatasTempServiceImpl extends AbstractDataManageService {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {
		BuilddatasTemp temp = (BuilddatasTemp) population;
		Builddatas domain = new Builddatas();
		String[] exceptArgs = { "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		domain.setOrginternalcode(domain.getOrganization().getOrgInternalCode());
		return domain;
	}

	@Override
	public TargetDataVo getTargetDataVo(Object population) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validCanClaim(BaseDomain population) {
	}

	@Override
	protected void asynchronousProcess(BaseDomain temp) {
	}

}
