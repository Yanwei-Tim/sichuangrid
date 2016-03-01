package com.tianque.plugin.weChat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.plugin.weChat.constant.IssueConstant;
import com.tianque.plugin.weChat.dao.StatisticAnalysisDao;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.StatisticAnalysisService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.util.TimeUtil;
import com.tianque.plugin.weChat.vo.StatisticAnalysisDetailVo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisVo;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**统计分析服务类*/
@Service("statisticAnalysisService")
@Transactional
public class StatisticAnalysisServiceImpl extends AbstractBaseService implements
		StatisticAnalysisService {
	@Autowired
	private StatisticAnalysisDao statisticAnalysisDao;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private PropertyDictDubboService propertyDictService;
	@Autowired
	private IssueTypeService issueTypeService;

	@Override
	public List<StatisticAnalysisVo> findStatisticAnalysis(StatisticAnalysisVo statisticAnalysisVo) {
		List<StatisticAnalysisVo> mainList = new ArrayList<StatisticAnalysisVo>();
		try {
			statisticAnalysisVo
					.setEndDate(TimeUtil.getOtherDay(statisticAnalysisVo.getEndDate(), 1));
			if (statisticAnalysisVo.getOrg() == null
					|| statisticAnalysisVo.getOrg().getId() == null) {
				throw new SerialException("统计的组织机构为空");
			}
			Organization org = organizationService.getSimpleOrgById(statisticAnalysisVo.getOrg()
					.getId());
			if (org == null) {
				throw new SerialException("组织机构为空");
			}
			List<TencentUser> userList = tencentUserService.getTencentUserListByOrgCode(org
					.getOrgInternalCode());
			for (TencentUser tencentUser : userList) {
				StatisticAnalysisVo vo = new StatisticAnalysisVo();
				statisticAnalysisVo.setWeChatUserId(tencentUser.getWeChatUserId());
				List<StatisticAnalysisDetailVo> detailList = statisticAnalysisDao
						.findStatisticAnalysisDetails(statisticAnalysisVo);
				vo.setWeChatUserId(tencentUser.getWeChatUserId());
				vo.setWeChatUserName(tencentUser.getName()
						+ "("
						+ organizationService.getSimpleOrgById(
								tencentUser.getOrganization().getId()).getOrgName() + ")");
				vo = buildTotalCount(detailList, vo);
				//vo.setStatisticList(detailList);
				mainList.add(vo);
			}
		} catch (Exception e) {
			logger.error("微信分组单位统计错误，原因：" + e.getMessage());
			return null;
		}

		return mainList;
	}

	@Override
	public PageInfo<StatisticAnalysisDetailVo> findStatisticAnalysisToFans(
			StatisticAnalysisVo statisticAnalysisVo, Integer page, Integer rows, String sidx,
			String sord) {
		if (statisticAnalysisVo.getOrg() == null || statisticAnalysisVo.getOrg().getId() == null) {
			throw new ServiceException("统计的组织机构为空");
		}
		try {
			statisticAnalysisVo
					.setEndDate(TimeUtil.getOtherDay(statisticAnalysisVo.getEndDate(), 1));
			PageInfo<StatisticAnalysisDetailVo> pageInfo = statisticAnalysisDao
					.findFansStatisticAnalysisDetails(statisticAnalysisVo, page, rows, sidx, sord);
			return pageInfo;

		} catch (Exception e) {
			logger.error("微信单位人员统计错误，原因：" + e.getMessage());
			throw new ServiceException("微信单位人员统计错误");
		}
	}

	@Override
	public List<StatisticAnalysisVo> findStatisticAnalysisToIssueTypeDomain(
			StatisticAnalysisVo statisticAnalysisVo) {
		List<StatisticAnalysisVo> mainList = new ArrayList<StatisticAnalysisVo>();
		try {
			statisticAnalysisVo
					.setEndDate(TimeUtil.getOtherDay(statisticAnalysisVo.getEndDate(), 1));
			if (statisticAnalysisVo.getOrg() == null
					|| statisticAnalysisVo.getOrg().getId() == null) {
				throw new SerialException("统计的组织机构为空");
			}
			List<TencentUser> userList = tencentUserService
					.getTencentUserListByOrgId(statisticAnalysisVo.getOrg().getId());
			for (TencentUser tencentUser : userList) {
				statisticAnalysisVo.setWeChatUserId(tencentUser.getWeChatUserId());
				statisticAnalysisVo.setSourceKind(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.SOURCE_KIND,
								IssueConstants.WECHAT_INPUT));
				List<IssueTypeDomain> issueTypeDomainIds = issueTypeService
						.findIssueTypeDomainsByModuleAndSystemsensitive(IssueTypes.CORE_MODULE,
								IssueConstant.SYSTEM_SENSITIVE_TRUE);

				statisticAnalysisVo.setIssueTypeDomainIds(issueTypeDomainIds);
				List<StatisticAnalysisDetailVo> detailList = statisticAnalysisDao
						.findStatisticAnalysisToIssueTypeDomain(statisticAnalysisVo);
				for (int i = 0; i < detailList.size(); i++) {
					StatisticAnalysisVo statisticVo = new StatisticAnalysisVo();
					statisticVo.setStatisticAnalysisDetailVo(detailList.get(i));
					mainList.add(statisticVo);
				}
			}
		} catch (Exception e) {
			logger.error("微信事件类型统计错误，原因：" + e.getMessage());
			return null;
		}
		return mainList;
	}

	@Override
	public List<StatisticAnalysisVo> findStatisticAnalysisToIssueType(
			StatisticAnalysisVo statisticAnalysisVo) {
		List<StatisticAnalysisVo> mainList = new ArrayList<StatisticAnalysisVo>();
		try {
			statisticAnalysisVo
					.setEndDate(TimeUtil.getOtherDay(statisticAnalysisVo.getEndDate(), 1));
			if (statisticAnalysisVo.getOrg() == null
					|| statisticAnalysisVo.getOrg().getId() == null) {
				throw new SerialException("统计的组织机构为空");
			}
			List<TencentUser> userList = tencentUserService
					.getTencentUserListByOrgId(statisticAnalysisVo.getOrg().getId());
			for (TencentUser tencentUser : userList) {
				statisticAnalysisVo.setWeChatUserId(tencentUser.getWeChatUserId());
				statisticAnalysisVo.setSourceKind(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.SOURCE_KIND,
								IssueConstants.WEB_INPUT));
				IssueTypeDomain issueTypeDomain = issueTypeService
						.getIssueTypeDoaminByDomainName(statisticAnalysisVo
								.getIssueTypeDomainName());
				statisticAnalysisVo.setIssueTypeDomainId(issueTypeDomain.getId());
				List<StatisticAnalysisDetailVo> detailList = statisticAnalysisDao
						.findStatisticAnalysisToIssueType(statisticAnalysisVo);
				for (int i = 0; i < detailList.size(); i++) {
					StatisticAnalysisVo statisticVo = new StatisticAnalysisVo();
					statisticVo.setStatisticAnalysisDetailVo(detailList.get(i));
					mainList.add(statisticVo);
				}
			}
		} catch (Exception e) {
			logger.error("统计错误，原因：" + e.getMessage());
			return null;
		}
		return mainList;
	}

	/**
	 * 合计
	 */
	private StatisticAnalysisVo buildTotalCount(List<StatisticAnalysisDetailVo> detailList,
			StatisticAnalysisVo totalVo) {
		if (totalVo == null) {
			return new StatisticAnalysisVo();
		}
		long totalReportNum = 0;
		long totalAcceptNum = 0;
		long totalAvailabilityNum = 0;
		StatisticAnalysisDetailVo detailTotalVo = new StatisticAnalysisDetailVo();
		for (StatisticAnalysisDetailVo detailVo : detailList) {
			totalReportNum += detailVo.getReportNum();
			totalAcceptNum += detailVo.getAcceptNum();
			totalAvailabilityNum += detailVo.getAvailabilityNum();

		}
		detailTotalVo.setGroupName("合计");
		detailTotalVo.setReportNum(totalReportNum);
		detailTotalVo.setAcceptNum(totalAcceptNum);
		detailTotalVo.setAvailabilityNum(totalAvailabilityNum);
		detailList.add(detailTotalVo);
		totalVo.setStatisticList(detailList);
		return totalVo;
	}

}
