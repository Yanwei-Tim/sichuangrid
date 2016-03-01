package com.tianque.baseInfo.primaryOrg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.primaryOrg.dao.PrimaryOrganizationsDao;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.baseInfo.primaryOrg.validator.PrimaryOrgValidatorImpl;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("primaryOrgService")
@Transactional
public class PrimaryOrgServiceImpl extends AbstractBaseService implements
		PrimaryOrgService {
	@Qualifier("primaryOrgValidator")
	@Autowired
	private PrimaryOrgValidatorImpl primaryOrgValidator;
	@Autowired
	private PrimaryOrganizationsDao primaryOrgDao;
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PrimaryMembersOrgTypeService primaryMembersOrgTypeService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	String throwMsg;

	private void dealPrimaryOrgVo(PrimaryOrgVo primaryOrgVo) {
		String isCommissionOrganization = primaryOrgVo
				.getIsCommissionOrganization();
		PropertyDict propertyDict = null;
		if (StringUtil.isStringAvaliable(isCommissionOrganization)) {
			if (BasicOrgType.COMPREHENSIVE_KEY.equals(isCommissionOrganization)) {// 综治办
				// 获取综治办大类组织字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZOGNZHIBAN);
			} else if (BasicOrgType.COMMISSION_KEY
					.equals(isCommissionOrganization)) {// 综治委
				// 获得综治委大类字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZOGNZHIWEI);
			} else if (BasicOrgType.COMMISSIONMEMBER_KEY
					.equals(isCommissionOrganization)) {// 综治成员单位
				// 获得综治成员单位大类字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZONGZHICHENGYUANDANWEI);
			}
		}
		if (propertyDict != null) {
			primaryOrgVo.setTeamType(propertyDict);
		}
	}

	@Override
	public PageInfo<PrimaryOrganizations> findPrimaryOrgs(
			PrimaryOrgVo primaryOrgVo, Integer internalId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			dealPrimaryOrgVo(primaryOrgVo);
			if (internalId != null
					&& internalId == BasicOrgType.MASS_TREAT_TEAM) {
				primaryOrgVo.setInternalId(internalId);
			}
			if (primaryOrgVo.getTeamType() != null
					&& primaryOrgVo.getTeamType().getId() != null
					&& massedutyTeamTypeIsPostulantduty(primaryOrgVo
							.getTeamType().getId())) {
				primaryOrgVo.setPostulantduty(1);

			}
			if (primaryOrgVo.getMemberCountMin() != null
					|| primaryOrgVo.getMemberCountMax() != null) {
				return primaryOrgDao.findPrimaryOrgsBySearch(
						fillArgs(primaryOrgVo, sidx, sord), pageNum, pageSize);
			} else {
				sidx = createSidxByDomainName(
						primaryOrgVo.getTeamTypeDomainName(), sidx);
				return primaryOrgDao.findPrimaryOrgs(
						fillArgs(primaryOrgVo, sidx, sord), pageNum, pageSize);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的findPrimaryOrg方法出现异常，原因：",
					"组织列表信息出现错误", e);
		}
	}

	private String createSidxByDomainName(String domainName, String sidx) {
		if (!sidx.equals("id")) {
			return sidx;
		}
		String defaultSort = "b.orgInternalCode asc, b.seq asc,b.id";
		if (!StringUtil.isStringAvaliable(domainName)) {
			return defaultSort;
		}
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(domainName);
		if (propertyDicts == null || propertyDicts.size() < 1) {
			return defaultSort;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" b.orgInternalCode asc, b.seq asc,decode(b.teamType");
		for (PropertyDict propertyDict : propertyDicts) {
			sb.append(",").append(propertyDict.getId()).append(",")
					.append(propertyDict.getDisplaySeq());
		}
		sb.append(",0) asc,b.id");
		return sb.toString();
	}

	@Override
	public PageInfo<PrimaryOrgVo> findPrimaryOrgsForOrgOption(
			PrimaryOrgVo primaryOrgVo, Integer internalId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		try {
			if (internalId != null
					&& internalId == BasicOrgType.MASS_TREAT_TEAM) {
				primaryOrgVo.setInternalId(internalId);
			}
			if (primaryOrgVo.getTeamType() != null
					&& primaryOrgVo.getTeamType().getId() != null
					&& massedutyTeamTypeIsPostulantduty(primaryOrgVo
							.getTeamType().getId())) {
				primaryOrgVo.setPostulantduty(1);

			}
			sidx = createSidxByDomainName(primaryOrgVo.getTeamTypeDomainName(),
					sidx);
			return primaryOrgDao.findPrimaryOrgsForOrgOption(
					fillArgs(primaryOrgVo, sidx, sord), pageNum, pageSize);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的findPrimaryOrg方法出现异常，原因：",
					"组织列表信息出现错误", e);
		}
	}

	/** 判断是群防群治的小类是否是平“安志愿者” */
	private boolean massedutyTeamTypeIsPostulantduty(Long id) {

		PropertyDict propertyDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.MASSEDUTY_TYPE, id);
		if (propertyDict != null
				&& BasicOrgType.POSTULANTDUTY.equals(propertyDict
						.getDisplayName())) {
			return true;
		}

		return false;
	}

	@Override
	@Transactional
	public int deletePrimaryOrg(String selectedIds) {
		if (selectedIds == null || selectedIds.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> deleteIds = getDeleteIds(selectedIds);
		int count = -1;
		if (isDeleteAble(deleteIds)) {
			count = 0;
			for (Long deleteId : deleteIds) {
				if (null != getPrimaryOrgById(deleteId)) {
					int deleteCount = primaryOrgDao.deletePrimaryOrg(deleteId);
					count += deleteCount;
				}
			}
		}
		return count;
	}

	@Override
	public PrimaryOrgVo getPrimaryOrgById(Long id) {
		try {
			return primaryOrgDao.getPrimaryOrgById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的getPrimaryOrgById方法出现异常，原因：",
					"根据ID获取组织出现错误", e);
		}
	}

	@Override
	@Transactional
	public PrimaryOrgVo addPrimaryOrg(PrimaryOrganizations primaryOrg) {
		int count = primaryOrgDao.countPrimaryOrgsWhenAdd(primaryOrg);
		if (count > 0) {
			throw new BusinessValidationException("该组织名称已存在");
		}
		try {
			autoFilled(primaryOrg);
			primaryOrgValidator(primaryOrg);
			Organization organization = organizationDubboService
					.getSimpleOrgById(primaryOrg.getOrg().getId());
			if (organization.getParentOrg() != null
					&& organization.getParentOrg().getId() != null) {
				primaryOrg.setParentId(organization.getParentOrg().getId());
			}
			return primaryOrgDao.addPrimaryOrg(primaryOrg);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的addPrimaryOrg方法出现异常，原因：",
					"新增组织出现错误", e);
		}
	}

	@Override
	@Transactional
	public PrimaryOrgVo updatePrimaryOrg(PrimaryOrganizations primaryOrg) {
		int count = primaryOrgDao.countPrimaryOrgsWhenAdd(primaryOrg);
		if (count > 0) {
			throw new BusinessValidationException("该组织详细名称已存在");
		}
		autoFilled(primaryOrg);
		primaryOrgValidator(primaryOrg);
		try {
			return primaryOrgDao.updatePrimaryOrg(primaryOrg);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的updatePrimaryOrg方法出现异常，原因：",
					"修改组织出现错误", e);
		}
	}

	/**
	 * 给VO设置参数
	 * 
	 * @param primaryOrgVo
	 * @param sidx
	 * @param sord
	 * @return primaryOrgVo
	 */
	private PrimaryOrgVo fillArgs(PrimaryOrgVo primaryOrgVo, String sidx,
			String sord) {
		Organization org = orgService.getSimpleOrgById(primaryOrgVo.getOrg()
				.getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		primaryOrgVo.setOrg(org);
		primaryOrgVo.setSortField(sidx);
		primaryOrgVo.setOrder(sord);
		return primaryOrgVo;
	}

	/**
	 * 获得要进行删除操作的id组
	 * 
	 * @return
	 */
	private List<Long> getDeleteIds(String selectedIds) {
		String[] deleteIdStrs = selectedIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (String deleteIdStr : deleteIdStrs) {
			Long deleteId = Long.parseLong(deleteIdStr);
			deleteIds.add(deleteId);
		}
		return deleteIds;
	}

	/**
	 * 填充属性
	 * 
	 * @param primaryOrg
	 */
	private void autoFilled(PrimaryOrganizations primaryOrg) {
		autoFillOrganizationInternalCode(primaryOrg);
	}

	/**
	 * 填充组织机构编码
	 * 
	 * @param primaryOrg
	 */
	private void autoFillOrganizationInternalCode(
			PrimaryOrganizations primaryOrg) {
		Organization org = orgService.getSimpleOrgById(primaryOrg.getOrg()
				.getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		primaryOrg.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * 是否可以删除
	 * 
	 * @param deleteIds
	 * @return true 可以 false 不可以
	 */
	private boolean isDeleteAble(List<Long> deleteIds) {
		PrimaryMembersOrgType primaryMembersOrgType = new PrimaryMembersOrgType();
		for (Long id : deleteIds) {
			primaryMembersOrgType.setPrimaryOrgId(id);
			primaryMembersOrgType.setIsFourLevelPlatform(0L);
			if (primaryMembersOrgTypeService
					.countFindPrimaryOrgMembers(primaryMembersOrgType) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 对象验证
	 * 
	 * @param primaryOrg
	 */
	private void primaryOrgValidator(PrimaryOrganizations primaryOrg) {
		ValidateResult baseDataValidator = primaryOrgValidator
				.validate(primaryOrg);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public Integer getCount(PrimaryOrgVo primaryOrgVo) {
		return primaryOrgDao.getCount(fillArgs(primaryOrgVo, null, null));
	}

	@Override
	public PrimaryOrgVo setPrimaryOrgSeq(Long id, Integer seq) {
		try {
			if (id == null) {
				throw new BusinessValidationException("参数错误");
			}
			return this.primaryOrgDao.setPrimaryOrgSeq(id, seq);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的setPrimaryOrgSeq方法出现异常，原因：",
					"设置排序出现错误", e);
		}
	}

	@Override
	@Transactional
	public int synchronizePrimaryOrgMembersToMasseduty(String selectedIds) {

		if (selectedIds == null || selectedIds.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		List<Long> synchronizeIds = getDeleteIds(selectedIds);
		int count = 0;
		for (Long synchronizeId : synchronizeIds) {
			if (null != getPrimaryOrgById(synchronizeId)) {
				int deleteCount = primaryOrgDao
						.synchronizePrimaryOrgMembersToMasseduty(synchronizeId);
				count += deleteCount;
			}
		}
		return count;
	}

	@Override
	public List<PrimaryOrganizations> getPrimaryOrganizationByprimaryMemberId(
			Long primaryMembersId) {
		if (primaryMembersId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return primaryOrgDao
				.getPrimaryOrganizationByprimaryMemberId(primaryMembersId);
	}

	@Override
	public List<PrimaryOrganizations> getPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryMemberVo) {
		if (primaryMemberVo == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (null == primaryMemberVo || null == primaryMemberVo.getOrg()) {
			throw new BusinessValidationException("找不到指定的网格！");
		}
		primaryMemberVo.setOrg(orgService.getSimpleOrgById(primaryMemberVo
				.getOrg().getId()));
		List<PrimaryOrganizations> organizations = primaryOrgDao
				.getPrimaryOrgaInfonByDetailName(primaryMemberVo);
		// 填充字典项
		organizations = setDictForList(organizations);
		if (null != organizations && organizations.size() > 0) {
			for (PrimaryOrganizations primaryOrganizations : organizations) {
				primaryOrganizations.setOrganizationId(primaryOrganizations
						.getId()
						+ "-"
						+ primaryOrganizations.getIsFourLevelPlatform());
			}
		}
		// 添加四级平台信息
		List<PrimaryOrganizations> fourTeamsOrganizations = primaryOrgDao
				.getFourTeamsPrimaryOrgaInfonByDetailName(primaryMemberVo);
		if (fourTeamsOrganizations != null && fourTeamsOrganizations.size() > 0) {
			organizations.addAll(fourTeamsOrganizations);
		}
		return organizations;
	}

	/****************************** 迁移合并方法 ***************************/
	/**
	 * 
	 * @Title: findAllPrimaryOrgForOrgChange
	 * @Description: TODO根据orgId查询党委部门
	 * @param @param oldOrgId
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<PrimaryOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-16 下午06:06:17
	 */
	public List<PrimaryOrganizations> findAllPrimaryOrgForOrgChange(
			Long oldOrgId, Long newOrgId, String deptMinValue) {
		try {
			if (oldOrgId == null || newOrgId == null) {
				throw new BusinessValidationException("请确认源orgId和目标orgId是否完善");
			}
			return primaryOrgDao.findAllPrimaryOrgForOrgChange(oldOrgId,
					newOrgId, deptMinValue);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的findAllPrimaryOrgForOrgChange方法出现异常，原因：",
					"查询重复数据出现错误", e);
		}
	}

	/**
	 * 
	 * @Title: findPrimaryOrgByOrgIdAndDetailnameForOrgChange
	 * @Description: TODO查询目标部门的重复党委部门数据
	 * @param @param primaryOrgVo
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<PrimaryOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午09:23:37
	 */
	public List<PrimaryOrganizations> findPrimaryOrgByOrgIdAndDetailnameForOrgChange(
			PrimaryOrgVo primaryOrgVo, Long newOrgId) {
		try {
			if (primaryOrgVo == null
					|| primaryOrgVo.getTeamClazz() == null
					|| primaryOrgVo.getTeamClazz().getId() == null
					|| primaryOrgVo.getTeamType() == null
					|| primaryOrgVo.getTeamType().getId() == null
					|| !StringUtil.isStringAvaliable(primaryOrgVo
							.getDetailName()) || newOrgId == null) {
				throw new BusinessValidationException("查询条件不完善");
			}
			return primaryOrgDao
					.findPrimaryOrgByOrgIdAndDetailnameForOrgChange(
							primaryOrgVo, newOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryOrgServiceImpl的findPrimaryOrgByOrgIdAndDetailnameForOrgChange方法出现异常，原因：",
					"查询目标数据出现错误", e);
		}
	}

	// 给列表的对象填充字典项
	private List<PrimaryOrganizations> setDictForList(
			List<PrimaryOrganizations> pOrgList) {
		if (pOrgList != null && pOrgList.size() > 0) {
			for (PrimaryOrganizations pOrg : pOrgList) {
				if (pOrg != null && pOrg.getTeamClazz() != null
						&& pOrg.getTeamClazz().getId() != null) {
					PropertyDict dict = propertyDictService
							.getPropertyDictById(pOrg.getTeamClazz().getId());
					if (dict != null) {
						pOrg.setDisplayName(dict.getDisplayName());
					}
				}
			}
		}
		return pOrgList;
	}
}