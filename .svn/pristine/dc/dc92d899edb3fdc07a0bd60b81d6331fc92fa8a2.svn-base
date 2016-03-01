package com.tianque.plugin.dataManage.dustbin.dustbinTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.Dustbin;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.dustbin.dustbinTemp.domain.DustbinTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("dustbinTempService")
public class DustbinTempServiceImpl extends AbstractDataManageService {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {
		DustbinTemp temp = (DustbinTemp) population;
		Dustbin domain = new Dustbin();
		String[] exceptArgs = { "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
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
