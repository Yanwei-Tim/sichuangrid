package com.tianque.sms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sms.dao.SmstrafficmanageDao;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmstrafficmanageVo;
import com.tianque.sms.service.SmsUplinkService;
import com.tianque.sms.service.SmstrafficmanageService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 流量管理:业务逻辑层
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
@Repository("smstrafficmanageService")
public class SmstrafficmanageServiceImpl extends
		BaseServiceImpl<Smstrafficmanage, SearchSmstrafficmanageVo> implements
		SmstrafficmanageService {

	@Autowired
	@Qualifier("smstrafficmanageValidator")
	private DomainValidator<Smstrafficmanage> domainValidator;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SmsUplinkService smsUplinkService;

	@Resource(name = "smstrafficmanageDao")
	public void setBaseDao(SmstrafficmanageDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Smstrafficmanage add(Smstrafficmanage smstrafficmanage) {
		smstrafficmanageValidator(smstrafficmanage);
		try {
			smstrafficmanage = getBaseDao().add(smstrafficmanage);
			return smstrafficmanage;
		} catch (Exception e) {
			throw new ServiceValidationException("新增流量管理信息出现错误", e);
		}
	}

	@Override
	public Smstrafficmanage update(Smstrafficmanage smstrafficmanage) {
		smstrafficmanageValidator(smstrafficmanage);
		try {
			smstrafficmanage = getBaseDao().update(smstrafficmanage);
			return smstrafficmanage;
		} catch (Exception e) {
			throw new ServiceValidationException("更新流量管理信息出现错误", e);
		}
	}

	private void smstrafficmanageValidator(Smstrafficmanage smstrafficmanage) {
		ValidateResult baseDataValidator = domainValidator
				.validate(smstrafficmanage);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public PageInfo<Smstrafficmanage> findSmstrafficmanageByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		if (orgId == null) {
			throw new BusinessValidationException("网格id不能为空!");
		}
		List<Smstrafficmanage> smstraList = findSmstrafficmanageByOrgId(orgId);

		return new PageInfo<Smstrafficmanage>(pageNum, pageSize,
				smstraList == null ? 0 : smstraList.size(), smstraList);
	}

	@Override
	public Smstrafficmanage getSmstrafficmanagesByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("网格id不能为空!");
		}
		return ((SmstrafficmanageDao) getBaseDao())
				.getSmstrafficmanagesByOrgId(orgId);
	}

	@Override
	public List<Smstrafficmanage> findSmstrafficmanageByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("网格id不能为空!");
		}
		List<Organization> orgList = organizationDubboService
				.findOrganizationsByParentId(orgId);
		if (orgList == null) {
			return new ArrayList<Smstrafficmanage>();
		}
		List<Smstrafficmanage> tempList = new ArrayList<Smstrafficmanage>();
		String orgids = "";
		for (int i = 0; i < orgList.size(); i++) {
			orgids += "," + orgList.get(i).getId();
			if (i != 0 && i % 500 == 0) {
				tempList.addAll(((SmstrafficmanageDao) getBaseDao())
						.findSmstrafficmanageByOrgId(orgids.substring(1)));
				orgids = "";
			}

		}
		if ("".equals(orgids)) {
			tempList.addAll(((SmstrafficmanageDao) getBaseDao())
					.findSmstrafficmanageByOrgId(orgids.substring(1)));
		}
		Map<Long, Smstrafficmanage> smstraMap = new HashMap<Long, Smstrafficmanage>();
		if (tempList != null) {
			for (Smstrafficmanage t : tempList) {
				smstraMap.put(t.getOrgId(), t);
			}
		}
		List<Smstrafficmanage> smstraList = new ArrayList<Smstrafficmanage>();
		for (Organization org : orgList) {
			createSmstrafficmanage(org, smstraMap, smstraList);
		}
		return smstraList;
	}

	private void createSmstrafficmanage(Organization org,
			Map<Long, Smstrafficmanage> smstraMap,
			List<Smstrafficmanage> smstraList) {
		Smstrafficmanage smstrafficmanage = new Smstrafficmanage();
		smstrafficmanage.setOrgName(org.getOrgName());
		smstrafficmanage.setId(org.getId());
		smstrafficmanage.setChinaUnicomTraffic(smstraMap.get(org.getId())
				.getChinaUnicomTraffic());
		smstrafficmanage.setMobileTraffic(smstraMap.get(org.getId())
				.getMobileTraffic());
		smstrafficmanage.setTelecomTraffic(smstraMap.get(org.getId())
				.getTelecomTraffic());
		smstrafficmanage.setSmallUnicom(smstraMap.get(org.getId())
				.getSmallUnicom());
		smstraList.add(smstrafficmanage);
	}

	@Override
	public Smstrafficmanage getNowSmstrafficmanagesByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("网格id不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("网格不存在!");
		}
		Smstrafficmanage smstra = ((SmstrafficmanageDao) getBaseDao())
				.getNowSmstrafficmanagesByOrgId(orgId);
		if (null == smstra) {
			smstra = new Smstrafficmanage();
		}
		smstra.setOrgId(orgId);
		smstra.setOrgName(org.getOrgName());
		return smstra;
	}

	@Override
	public PageInfo<Smstrafficmanage> findOrgTraffic(Long orgId,
			Long sendStatus, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (null == org) {
			return new PageInfo();
		}
		String orgIds = org.getId().toString();
		List<Long> orgIdList = new ArrayList<Long>();
		List<Smstrafficmanage> smstraList = new ArrayList<Smstrafficmanage>();
		smstraList.add(createSmstrafficmanage(org));
		orgIdList.add(org.getId());
		fillValue(smstraList, orgIdList, orgIds);
		return new PageInfo<Smstrafficmanage>(pageNum, pageSize, 1, smstraList);
	}

	@Override
	public PageInfo<Smstrafficmanage> findParentOrgTraffic(Long orgId,
			Long sendStatus, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		List<Organization> orgList = organizationDubboService
				.findOrganizationsByParentId(orgId);
		if (null == orgList || orgList.size() <= 0) {
			return new PageInfo();
		}
		String orgIds = "";
		List<Long> orgIdList = new ArrayList<Long>();
		List<Smstrafficmanage> smstraList = new ArrayList<Smstrafficmanage>();
		for (Organization org : orgList) {
			orgIdList.add(org.getId());
			orgIds += "," + org.getId();
			smstraList.add(createSmstrafficmanage(org));
		}
		fillValue(smstraList, orgIdList, orgIds.substring(1));
		return new PageInfo<Smstrafficmanage>(pageNum, pageSize,
				orgList.size(), smstraList);
	}

	private void fillValue(List<Smstrafficmanage> smstraList,
			List<Long> orgIdList, String orgIds) {
		List<Map<String, Object>> hasSmsList = smsUplinkService
				.findDifferPhoneTypeMessagesNumberByOrgIds(orgIdList);
		List<Smstrafficmanage> smsList = ((SmstrafficmanageDao) getBaseDao())
				.findParentOrgTraffic(orgIds);
		Map<Long, Smstrafficmanage> map = new HashMap<Long, Smstrafficmanage>();
		Map<Long, Map<String, Object>> hasMap = new HashMap<Long, Map<String, Object>>();
		if (null != hasSmsList) {
			for (Map<String, Object> h : hasSmsList) {
				hasMap.put(Long.valueOf(h.get("ORGID").toString()), h);
			}
		}
		if (null != smsList) {
			for (Smstrafficmanage s : smsList) {
				map.put(s.getId(), s);
			}
		}
		for (Smstrafficmanage smstra : smstraList) {
			Long orgId = smstra.getId();
			smstra.setChinaUnicomTraffic(map.get(orgId) == null
					|| map.get(orgId).getChinaUnicomTraffic() == null ? 0L
					: map.get(orgId).getChinaUnicomTraffic());
			smstra.setMobileTraffic(map.get(orgId) == null
					|| map.get(orgId).getMobileTraffic() == null ? 0L : map
					.get(orgId).getMobileTraffic());
			smstra.setTelecomTraffic(map.get(orgId) == null ? 0L : map.get(
					orgId).getTelecomTraffic());
			smstra.setHasChinaUnicomTraffic(hasMap.get(orgId) == null
					|| hasMap.get(orgId).get("HASCHINAUNICOMTRAFFIC") == null ? 0L
					: Long.valueOf(hasMap.get(orgId)
							.get("HASCHINAUNICOMTRAFFIC").toString()));
			smstra.setHasMobileTraffic(hasMap.get(orgId) == null
					|| hasMap.get(orgId).get("HASMOBILETRAFFIC") == null ? 0L
					: Long.valueOf(hasMap.get(orgId).get("HASMOBILETRAFFIC")
							.toString()));
			smstra.setHasTelecomTraffic(hasMap.get(orgId) == null
					|| hasMap.get(orgId).get("HASTELECOMTRAFFIC") == null ? 0L
					: Long.valueOf(hasMap.get(orgId).get("HASTELECOMTRAFFIC")
							.toString()));
		}
	}

	private Smstrafficmanage createSmstrafficmanage(Organization org) {
		Smstrafficmanage smstrafficmanage = new Smstrafficmanage();
		smstrafficmanage.setOrgName(org.getOrgName());
		smstrafficmanage.setId(org.getId());
		return smstrafficmanage;
	}

	@Override
	public Smstrafficmanage updateSmstrafficmanageByOrgId(
			Smstrafficmanage smstrafficmanage, Long orgId) {
		if (smstrafficmanage == null || orgId == null) {
			throw new BusinessValidationException("部门id或者smstrafficmanage不能为空!");
		}
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(orgId);

		Long telecomTraffic = 0L;
		Long chinaUnicomTraffic = 0L;
		Long mobileTraffic = 0L;

		for (int i = 0; i < organizations.size(); i++) {
			Smstrafficmanage smstraffic = getSmstrafficmanagesByOrgId(organizations
					.get(i).getId());
			if (smstraffic == null) {
				return update(smstrafficmanage);
			}
			if (smstraffic.getTelecomTraffic() != null)
				telecomTraffic += smstraffic.getTelecomTraffic();
			if (smstraffic.getChinaUnicomTraffic() != null)
				chinaUnicomTraffic += smstraffic.getChinaUnicomTraffic();
			if (smstraffic.getMobileTraffic() != null)

				mobileTraffic += smstraffic.getMobileTraffic();
		}

		if (smstrafficmanage.getTelecomTraffic() < telecomTraffic) {
			throw new BusinessValidationException("电信流量不能小于" + telecomTraffic);
		} else if (smstrafficmanage.getChinaUnicomTraffic() < chinaUnicomTraffic) {
			throw new BusinessValidationException("联通流量不能小于"
					+ chinaUnicomTraffic);
		} else if (smstrafficmanage.getMobileTraffic() < mobileTraffic) {
			throw new BusinessValidationException("移动流量不能小于" + mobileTraffic);
		}

		return update(smstrafficmanage);
	}
}
