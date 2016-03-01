package com.tianque.publicSecurity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.constant.PublicSecurityType;
import com.tianque.publicSecurity.service.BayonetService;
import com.tianque.publicSecurity.service.PublicSecurityTransferService;
import com.tianque.publicSecurity.service.SkynetService;
import com.tianque.publicSecurity.service.SnapshotSystemService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("publicSecurityTransferService")
public class PublicSecurityTransferServiceImpl implements
		PublicSecurityTransferService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SkynetService skynetService;
	@Autowired
	private BayonetService bayonetService;
	@Autowired
	private SnapshotSystemService snapshotSystemService;

	@Override
	public List<ErrorMessageVo> transfer(String type, List<Long> ids,
			Long toOrgId) {
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
		invoke(type, ids, toOrgId, context);
		return (List<ErrorMessageVo>) map.get(Constants.ERRORLIST);
	}

	// 处理转移
	private void invoke(String type, List<Long> ids, Long toOrgId,
			Context context) {
		try {
			Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
			if (org != null) {
				Long orgId = 0L;
				for (Long id : ids) {
					if (type.equals(PublicSecurityType.SKYNET)) {
						orgId = skynetService.get(id).getOrganization().getId();
						skynetService.updateByParam(id, toOrgId,
								org.getOrgInternalCode());
						validate(type, id, toOrgId, context,
								skynetService.get(id).getSkynetNo(), orgId);
					} else if (type.equals(PublicSecurityType.BAYONET)) {
						orgId = bayonetService.get(id).getOrganization()
								.getId();
						bayonetService.updateByParam(id, toOrgId,
								org.getOrgInternalCode());
						validate(type, id, toOrgId, context, bayonetService
								.get(id).getBayonetNo(), orgId);
					} else if (type.equals(PublicSecurityType.SNAPSHOTSYSTEM)) {
						orgId = snapshotSystemService.get(id).getOrganization()
								.getId();
						snapshotSystemService.updateByParam(id, toOrgId,
								org.getOrgInternalCode());
						validate(type, id, toOrgId, context,
								snapshotSystemService.get(id).getSnapshotNo(),
								orgId);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"SkynetServiceImpl类的invoke方法出错", "处理被转移对象的信息出错！", e);
		}
	}

	// 校验
	private void validate(String type, Long id, Long toOrgId, Context context,
			String code, Long orgId) {
		List<ErrorMessageVo> errorList = (List<ErrorMessageVo>) context
				.getMap().get(Constants.ERRORLIST);
		List<Long> errorIdList = (List<Long>) context.getMap().get(
				Constants.ERRORIDLIST);
		String orgName = organizationDubboService.getSimpleOrgById(toOrgId)
				.getOrgName();// 目标网格名称
		String orgInternalCode = "";
		if (orgId != null) {
			orgInternalCode = organizationDubboService.getSimpleOrgById(orgId)
					.getOrgInternalCode();// 原网格编码
		}

		// 校验转移网格是否存在相同编号的数据
		if (id != null) {
			if (type.equals(PublicSecurityType.SKYNET)) {
				skynetService.transferValidate(id, toOrgId, code, orgId,
						errorList, errorIdList, orgName, orgInternalCode);
			} else if (type.equals(PublicSecurityType.BAYONET)) {
				bayonetService.transferValidate(id, toOrgId, code, orgId,
						errorList, errorIdList, orgName, orgInternalCode);
			} else if (type.equals(PublicSecurityType.SNAPSHOTSYSTEM)) {
				snapshotSystemService
						.transferValidate(id, toOrgId, code, orgId, errorList,
								errorIdList, orgName, orgInternalCode);
			}
		}
	}

}
