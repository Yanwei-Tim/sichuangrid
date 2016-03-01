package com.tianque.plugin.account.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.plugin.account.constants.CommonalityParameter;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.factory.ThreeRecordsIssueManageStrategyFactory;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.strategy.ThreeRecordsIssueManageStrategy;

public class ThreeRecordsIssueOperationUtil {

	public static String[] getViewprocessParams(Integer viewProcess) {
		return ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
				CommonalityParameter.LAST_ORG, CommonalityParameter.TARGE_ORG,
				CommonalityParameter.CURRENT_ORG,
				CommonalityParameter.CREATE_ORG }
				: new String[] { CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG,
						CommonalityParameter.CREATE_ORG };
	}

	public static boolean isSimpleContentDeal(int dealCode) {
		return ThreeRecordsIssueOperate.INSTRUCT.getCode() == dealCode
				|| ThreeRecordsIssueOperate.URGENT.getCode() == dealCode;
	}

	public static boolean isSuperviseDeal(int dealCode) {
		return ThreeRecordsIssueOperate.NORMAL_SUPERVISE.getCode() == dealCode
				|| ThreeRecordsIssueOperate.YELLOW_SUPERVISE.getCode() == dealCode
				|| ThreeRecordsIssueOperate.RED_SUPERVISE.getCode() == dealCode;
	}

	/**
	 * 根据id获取日志文件，如果ID获取不到，即是新增，返回一个空的LIST作为容器以便后续处理
	 * 
	 * @param issueLogAttachFiles
	 * @param id
	 * @return
	 */
	public static List<ThreeRecordsIssueAttachFile> lookupLogFilesFromAllLogFile(
			Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles,
			Long id) {
		if (issueLogAttachFiles.containsKey(id)) {
			return issueLogAttachFiles.get(id);
		} else {
			List<ThreeRecordsIssueAttachFile> files = new ArrayList<ThreeRecordsIssueAttachFile>();
			issueLogAttachFiles.put(id, files);
			return files;
		}
	}

	public static boolean isLogAttachFile(ThreeRecordsIssueAttachFile file) {
		return file.getIssueLog() != null && file.getIssueLog().getId() != null;
	}

	/**
	 * 如果idsStr为空，即是新增，返回一个空的数组作为容器以便后续处理
	 * 
	 * @param issueLogAttachFiles
	 * @param id
	 * @return
	 */
	public static Long[] parseStringToLongArray(String idsStr) {
		if (StringUtil.isStringAvaliable(idsStr)) {
			String[] ids = idsStr.split(",");
			List<Long> list = new ArrayList<Long>();
			for (int index = 0; index < ids.length; index++) {
				if (StringUtil.isStringAvaliable(ids[index])) {
					list.add(Long.valueOf(ids[index]));
				}
			}
			Long[] result = new Long[list.size()];
			return list.toArray(result);
		} else {
			return new Long[] {};
		}
	}

	public static ThreeRecordsIssueManageStrategy getActualIssueManageStrategy(
			ThreeRecordsIssueManageStrategy strategy, String moduleName,
			ThreeRecordsIssueManageStrategyFactory factory) {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(moduleName);
		}
		return strategy;
	}

	public static PageInfo createEmptyIssueVoPageInfo(int pageSize,
			int pageIndex) {
		PageInfo result = new PageInfo();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	// 判断当前登录用户是否是职能部门
	public static boolean orgIsFunctional(Organization userOrg) {
		boolean flg = false;
		if (userOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			flg = true;
		}
		return flg;
	}

	// 根据登陆用户的组织机构设定内置编码
	// 如果是职能部门，需要判断
	public static Organization setQueryOrg(Organization selectOrg) {
		if (selectOrg == null || selectOrg.getId() == null) {
			return selectOrg;
		}
		Organization userOrg = ThreadVariable.getUser().getOrganization();
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(userOrg)) {
			// 登陆的用户是职能部门
			Long userParentOrgId = userOrg.getParentOrg().getId();
			if (selectOrg.getId().equals(userParentOrgId)) {
				// 如果选择的组织机构是当前职能部门的父类节点，则采用职能部门的节点,并且只有自己本级
				return userOrg;
			}
		}
		return selectOrg;
	}

	// 如果当前登录的是职能部门，那么只显示本层级的
	public static String setSeachValue(String seachValue, Organization userOrg) {
		if (!StringUtil.isStringAvaliable(seachValue)) {
			return ThreeRecordsIssueConstants.QUERY_PRESENT;
		}
		if (ThreeRecordsIssueOperationUtil.orgIsFunctional(userOrg)) {
			// 单独为职能部门设置查询条件
			return ThreeRecordsIssueConstants.QUERY_PRESENT;
		}
		return seachValue;
	}

}
