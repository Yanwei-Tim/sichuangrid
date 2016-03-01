package com.tianque.issueAbutmentJoint.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issueAbutmentJoint.constants.IssueJointOrganization;
import com.tianque.issueAbutmentJoint.dao.IssueDataColumnVoDAO;
import com.tianque.issueAbutmentJoint.domain.IssueDataColumnVo;
import com.tianque.issueAbutmentJoint.service.IssueDataColumnVoService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;

/**
 * @Description:对接事件已办结统计业务层实现
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-24 下午04:04:27
 */
@Service("issueDataColumnVoService")
@Transactional
public class IssueDataColumnVoServiceImpl implements IssueDataColumnVoService {

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueDataColumnVoDAO issueDataColumnVoDAO;

	@Override
	public void createIssueJointAnalysisChartData() {
		try {
			// 四川省-->成都市-->青羊区-->草堂街道办事处
			issueJointAnalysisChartDataStatistics(
					IssueJointOrganization.CTJD_ORGNAME,
					IssueJointOrganization.CTJD_FULL_ORG_NAME,
					OrganizationLevel.TOWN);
			// 四川省-->攀枝花市
			issueJointAnalysisChartDataStatistics(
					IssueJointOrganization.PZHS_ORGNAME,
					IssueJointOrganization.PZHS_FULL_ORG_NAME,
					OrganizationLevel.CITY);
			// 四川省-->攀枝花市-->东区
			issueJointAnalysisChartDataStatistics(
					IssueJointOrganization.PZHS_DQ_ORGNAME,
					IssueJointOrganization.PZHS_DQ_FULL_ORG_NAME,
					OrganizationLevel.DISTRICT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException(
					"IssueDataColumnVoServiceImpl类的createIssueJointAnalysisChartData方法错误",
					"统计对接事件数量错误", e);
		}
	}

	private void issueJointAnalysisChartDataStatistics(String orgName,
			String orgFullName, int orgLevel) {
		Organization organization = findOrg(orgName, orgFullName, orgLevel);

		if (organization != null && organization.getId() != null) {
			int year = CalendarUtil.getYear(new Date());
			int month = CalendarUtil.getMonth(new Date());
			// 获取对接事件统计数据
			List<IssueDataColumnVo> issueDataColumnVos = findIssueJointDataColumnVo(
					organization.getId(), organization.getOrgInternalCode(),
					year, month);
			int issueSum = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			for (IssueDataColumnVo issueDataColumnVo : issueDataColumnVos) {
				map.clear();
				map.put("year", year);
				map.put("month", month);
				map.put("orgId", issueDataColumnVo.getOrg().getId());
				map.put("issueTypeId", issueDataColumnVo.getIssueType().getId());
				map.put("issuetypedomainId", issueDataColumnVo.getIssueType()
						.getPropertyDomain().getId());
				IssueDataColumnVo issueAnalysis = issueDataColumnVoDAO
						.getIssueDataColumnVoByYearAndMonthAndIssueTypeAndOrgId(map);
				if (issueAnalysis != null
						&& issueAnalysis.getIssueSum() != null) {
					issueSum = issueAnalysis.getIssueSum()
							+ issueDataColumnVo.getIssueSum();
					map.clear();
					map.put("issueSum", issueSum);
					map.put("id", issueAnalysis.getId());
					issueDataColumnVoDAO.updateIssueDataColumnVoById(map);
				} else {
					issueDataColumnVoDAO
							.addIssueDataColumnVo(issueDataColumnVo);
				}
			}
		}
	}

	/**
	 * 获取 事件对接组织机构
	 * 
	 * @return
	 */
	@Override
	public Organization findOrg(String orgName, String orgFullName, int orgLevel) {
		// 乡镇层级
		PropertyDict townOrglevel = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL, orgLevel).get(0);
		// 行政部门
		PropertyDict adminOrgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		// 事件对接组织机构
		Organization organization = organizationDubboService
				.findOrganizationByOrgTypeAndOrgLevelAndOrgName(
						adminOrgType.getId(), townOrglevel.getId(), orgName,
						orgFullName);
		return organization;

	}

	/**
	 * 获取事件对接组织机构 ，对接事件的统计数据
	 * 
	 * @param orgId
	 * @return
	 */
	private List<IssueDataColumnVo> findIssueJointDataColumnVo(Long orgId,
			String orgCode, int year, int month) {
		// 对接事件类型（大类）
		PropertyDomain issueJointType = propertyDomainService
				.getPropertyDomainByDomainName(PropertyTypes.ISSUE_JOINT_TYPE);
		// 对接事件类型（子类）
		PropertyDomain issueJointTypeSub = propertyDomainService
				.getPropertyDomainByDomainName(PropertyTypes.ISSUE_JOINT_TYPE_SUB);
		// 事件对接字典项集合
		List<PropertyDict> fullIssueJointTypesDicts = propertyDictService
				.findFullPropertyDictByDomainId(issueJointType.getId(),
						issueJointTypeSub.getId());
		// 事件字典项（拼装成事件对接字典项）
		List<PropertyDict> fullIssueTypesDicts = issueTypeService
				.findFullPropertyDictByDomainId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("sysDate", new Date());
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("beforeMonthLastDay", CalendarUtil.getBeforeMonthLastDay());
		map.put("nextMonthFirstDay", CalendarUtil.getNextMonthFirstDay());
		/** 对接事件的所有类型的统计 */
		List<IssueDataColumnVo> issueDataColumnVos = issueDataColumnVoDAO
				.queryIssueDataColumnVoToIssuejointForList(map);

		if (issueDataColumnVos != null && issueDataColumnVos.size() != 0) {
			// 填充大类小类名称
			for (IssueDataColumnVo issueDataColumnVo : issueDataColumnVos) {
				for (PropertyDict dict : fullIssueJointTypesDicts) {
					if (issueDataColumnVo != null
							&& dict != null
							&& issueDataColumnVo.getIssueType().getId()
									.equals(dict.getId())) {
						issueDataColumnVo.setIssueType(dict);
						break;
					}
				}
				// 修改大类小类id
				for (PropertyDict dict : fullIssueTypesDicts) {
					if (issueDataColumnVo.getIssueType().getDisplayName()
							.equals(dict.getDisplayName())
							&& issueDataColumnVo
									.getIssueType()
									.getPropertyDomain()
									.getDomainName()
									.equals(dict.getPropertyDomain()
											.getDomainName())) {
						issueDataColumnVo.getIssueType().setId(dict.getId());
						issueDataColumnVo.getIssueType().getPropertyDomain()
								.setId(dict.getPropertyDomain().getId());
						break;
					}
				}
			}
		}
		return issueDataColumnVos;
	}

	@Override
	public IssueDataColumnVo findIssueHandleStatsToCTJD(
			Organization organization, Date startDate, Date endDate) {
		try {
			IssueDataColumnVo issueDataColumnVo = null;
			if (organization != null && organization.getId() != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("endDate", endDate);
				map.put("startDate", startDate);
				map.put("orgCode", organization.getOrgInternalCode());
				Integer sum = issueDataColumnVoDAO
						.getIssueDataColumnVoToIssueHandleStatsToCTJD(map);
				issueDataColumnVo = new IssueDataColumnVo();
				issueDataColumnVo.setIssueSum(sum);
				issueDataColumnVo.setOrg(organization);
			}
			return issueDataColumnVo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueDataColumnVoServiceImpl类的findIssueHandleStatsToCTJD方法错误",
					e);
		}
	}

	@Override
	public List<IssueDataColumnVo> findIssueClassIficationStatToCTJD(
			Organization organization, Date startDate, Date endDate) {
		List<IssueDataColumnVo> issueDataColumnVos = null;
		if (organization != null && organization.getId() != null) {
			int year = CalendarUtil.getYear(startDate);
			int month = CalendarUtil.getMonth(startDate);
			issueDataColumnVos = findIssueJointDataColumnVo(
					organization.getId(), organization.getOrgInternalCode(),
					year, month);
		}

		return issueDataColumnVos;
	}
}
