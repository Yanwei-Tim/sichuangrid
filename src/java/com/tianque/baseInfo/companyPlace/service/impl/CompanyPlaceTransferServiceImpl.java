package com.tianque.baseInfo.companyPlace.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constant.KeyPlaceType;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceService;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceTransferService;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("companyPlaceTransferService")
@Transactional
public class CompanyPlaceTransferServiceImpl extends LogableService implements
		CompanyPlaceTransferService {
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;
	@Autowired
	private CompanyPlaceService companyPlaceService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyPlaceService keyPlaceService;

	@Override
	public List<ErrorMessageVo> transfer(String type, List<Long> ids,
			Long toOrgId, String modulKey) {
		if (type == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Context context = new Context();
		Map map = context.getMap();
		map.put(Constants.ERRORLIST, new ArrayList<ErrorMessageVo>());
		map.put(Constants.ERRORIDLIST, new ArrayList<Long>());

		for (Long id : ids) {
			validate(type, id, toOrgId, context, null, null);
		}
		List<Long> errorIdList = (List<Long>) map.get(Constants.ERRORIDLIST);
		for (Long id : errorIdList) {
			if (ids.contains(id)) {
				ids.remove(id);
			}
		}
		invoke(type, ids, toOrgId, context, modulKey);
		List<ErrorMessageVo> messsges = (List<ErrorMessageVo>) map
				.get(Constants.ERRORLIST);
		return messsges;

	}

	private void invoke(String type, List<Long> ids, Long toOrgId,
			Context context, String modulKey) {
		Organization toOrg = organizationDubboService.getSimpleOrgById(toOrgId);
		for (Long cid : ids) {
			CompanyPlace companyPlace = companyPlaceBaseSevice
					.getCompanyPlaceInfoByCid(cid);
			companyPlace.setOrg(toOrg);
			companyPlace.setCompanyPlaceSource(modulKey);
			companyPlace.setOrginternalcode(toOrg.getOrgInternalCode());
			companyPlaceBaseSevice.updateCompanyPlaceBaseOrgById(companyPlace);
			// 同步company表中的层级
			// companyPlaceService.updateCompanyPlaceOrgByCid(companyPlace);
			// 更新keyplace
			keyPlaceService.updateKeyPlaceByIdAndOrgInType(cid, toOrg.getId(),
					toOrg.getOrgInternalCode(), KeyPlaceType.mapTypes);
		}
	}

	private void validate(String type, Long id, Long toOrgId, Context context,
			String code, Long orgId) {
		if (type == null || toOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		List<ErrorMessageVo> errorList = (List<ErrorMessageVo>) context
				.getMap().get(Constants.ERRORLIST);
		List<Long> errorIdList = (List<Long>) context.getMap().get(
				Constants.ERRORIDLIST);
		companyPlaceBaseSevice.transferValidate(id, toOrgId, orgId, type,
				errorList, errorIdList);

	}
}
