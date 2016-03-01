package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.state.IssueState;
import com.tianque.openLayersMap.dao.IssueTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.state.IssueQueryState;

/**
 * 
 * @功能：搜索出事件详情信息，包括图层、辖区、精确搜索
 * @edit by longzhendong
 */
@Service("issueMapSearchService")
public class IssueTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<IssueInfoVo> {

	@Autowired
	private IssueTwoDimensionMapDao issueTwoDimensionMapDao;

	@Override
	public PageInfo<IssueInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String type,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		Long dealState = getDealState(type);

		PageInfo<IssueInfoVo> page = issueTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
						orgId, orgInternalCode, screenCoordinateVo, pageNum,
						pageSize, sidx, sord, dealState, type);
		setIssueTypeNameAndStatus(page.getResult());
		return page;
	}

	/**
	 * 得到事件类型
	 * 
	 * @param list
	 */
	private void setIssueTypeNameAndStatus(List<IssueInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			String issueTypeName = "";
			IssueInfoVo issueInfoVo = list.get(i);
			List<IssueInfoVo> issueInfoVos = issueTwoDimensionMapDao
					.getIssueTypeByIssueId(issueInfoVo.getIssueId());
			for (int j = 0; j < issueInfoVos.size(); j++) {
				if (issueTypeName != "") {
					issueTypeName += ",";
				}
				issueTypeName += issueInfoVos.get(j).getIssueType();
			}
			issueInfoVo.setIssueType(issueTypeName);
			getStatus(issueInfoVo);
		}
	}

	private void getStatus(IssueInfoVo issueInfoVo) {
		if (issueInfoVo == null || issueInfoVo.getStatus() == null) {
			return;
		}
		if (issueInfoVo.getStatus().equals("1")) {
			issueInfoVo.setStatus("办理中");
		} else if (issueInfoVo.getStatus().equals("250")) {
			issueInfoVo.setStatus("关闭");
		} else if (issueInfoVo.getStatus().equals("200")) {
			issueInfoVo.setStatus("已处理");
		} else if (issueInfoVo.getStatus().equals("300")) {
			issueInfoVo.setStatus("已验证");
		} else {
			issueInfoVo.setStatus("办理中");
		}
	}

	@Override
	public PageInfo<IssueInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null || mainTableName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<IssueInfoVo> page = issueTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord, mainTableName);
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0"))
			setIssueTypeNameAndStatus(page.getResult());
		return page;
	}

	@Override
	public PageInfo<IssueInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<IssueInfoVo> issueInfoVo = new PageInfo<IssueInfoVo>();
		if (typeName.equals(GisGlobalValueMap.PERSONFORTHING)) {// 个人待办
			issueInfoVo = personForThing(pageNum, pageSize, sidx, sord, org);
		} else if (typeName.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 个人已办
			issueInfoVo = personAlreadyThing(pageNum, pageSize, sidx, sord, org);
		} else if (typeName.equals(GisGlobalValueMap.GONETHROUGH)) {// 个人已办结
			issueInfoVo = goneThrough(pageNum, pageSize, sidx, sord, org);
		} else if (typeName.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			issueInfoVo = forThing(pageNum, pageSize, sidx, sord, org);
		} else if (typeName.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办
			issueInfoVo = alreadyThing(pageNum, pageSize, sidx, sord, org);
		}
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0"))
			setIssueTypeNameAndStatus(issueInfoVo.getResult());
		return issueInfoVo;
	}

	private PageInfo<IssueInfoVo> personForThing(Integer pageNum,
			Integer pageSize, String sidx, String sord, Organization org) {
		PageInfo<IssueInfoVo> issueInfoVo;
		Long dealState;
		dealState = IssueQueryState.MY_NEED_DO;
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
			dealState = new Long(IssueState.STEPCOMPLETE_CODE);
		}
		issueInfoVo = issueTwoDimensionMapDao
				.findMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
						org.getId(), dealState, pageNum, pageSize, sidx, sord);
		return issueInfoVo;
	}

	private PageInfo<IssueInfoVo> personAlreadyThing(Integer pageNum,
			Integer pageSize, String sidx, String sord, Organization org) {
		PageInfo<IssueInfoVo> issueInfoVoList;// 近期在办列表
		PageInfo<IssueInfoVo> issueInfoVoListOfHistory;// 历史已办结列表
		List<Long> dealStateList = new ArrayList<Long>();
		dealStateList.add(IssueQueryState.MY_DONE_ONE);
		dealStateList.add(IssueQueryState.MY_DONE_THREE);
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
			dealStateList = new ArrayList<Long>();
			dealStateList.add(new Long(IssueState.STEPCOMPLETE_CODE));
		}
		issueInfoVoList = issueTwoDimensionMapDao
				.findMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
						org.getId(), dealStateList, pageNum, pageSize, sidx,
						sord);
		issueInfoVoListOfHistory = issueTwoDimensionMapDao
				.findMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList(
						org.getId(), dealStateList, pageNum, pageSize, sidx,
						sord);
		List<IssueInfoVo> issueList = issueInfoVoList.getResult();
		for (IssueInfoVo issueInfoVo : issueInfoVoListOfHistory.getResult()) {
			issueList.add(issueInfoVo);
		}
		issueInfoVoList.setResult(issueList);
		return issueInfoVoList;
	}

	private PageInfo<IssueInfoVo> forThing(Integer pageNum, Integer pageSize,
			String sidx, String sord, Organization org) {
		PageInfo<IssueInfoVo> issueInfoVo;
		Long dealState;
		dealState = IssueQueryState.MY_JURISDICTIONS_NEED_DO;
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
			dealState = new Long(IssueState.STEPCOMPLETE_CODE);
		}
		issueInfoVo = issueTwoDimensionMapDao
				.findMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
						org.getId(), org.getOrgInternalCode(), dealState,
						pageNum, pageSize, sidx, sord);
		return issueInfoVo;
	}

	private PageInfo<IssueInfoVo> alreadyThing(Integer pageNum,
			Integer pageSize, String sidx, String sord, Organization org) {
		PageInfo<IssueInfoVo> issueInfoVoList;// 近期已办列表
		PageInfo<IssueInfoVo> issueInfoVoListOfHistory;// 历史已办结列表
		Long dealState;
		dealState = IssueQueryState.MY_JURISDICTIONS_DONE;
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
			dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
		}
		issueInfoVoList = issueTwoDimensionMapDao
				.findMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
						org.getId(), org.getOrgInternalCode(), dealState,
						pageNum, pageSize, sidx, sord);
		issueInfoVoListOfHistory = issueTwoDimensionMapDao
				.findMyJurisdictionsHistoryDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
						org.getId(), org.getOrgInternalCode(), dealState,
						pageNum, pageSize, sidx, sord);
		List<IssueInfoVo> issueList = issueInfoVoList.getResult();
		for (IssueInfoVo issueInfoVo : issueInfoVoListOfHistory.getResult()) {
			issueList.add(issueInfoVo);
		}
		issueInfoVoList.setResult(issueList);

		return issueInfoVoList;
	}

	private PageInfo<IssueInfoVo> goneThrough(Integer pageNum,
			Integer pageSize, String sidx, String sord, Organization org) {
		PageInfo<IssueInfoVo> issueInfoVoList;// 近期已办结列表
		PageInfo<IssueInfoVo> issueInfoVoListOfHistory;// 历史已办结列表
		Long dealState;
		dealState = IssueQueryState.MY_JURISDICTIONS_DONE;
		if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
			dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
		}
		issueInfoVoList = issueTwoDimensionMapDao
				.findMyFinishTwoDimensionMapPageInfoByOrgIdAndDealState(
						org.getId(), org.getOrgInternalCode(), dealState,
						pageNum, pageSize, sidx, sord);
		issueInfoVoListOfHistory = issueTwoDimensionMapDao
				.findMyFinishHistoryTwoDimensionMapPageInfoByOrgIdAndDealState(
						org.getId(), org.getOrgInternalCode(), dealState,
						pageNum, pageSize, sidx, sord);
		List<IssueInfoVo> issueList = issueInfoVoList.getResult();
		for (IssueInfoVo issueInfoVo : issueInfoVoListOfHistory.getResult()) {
			issueList.add(issueInfoVo);
		}
		issueInfoVoList.setResult(issueList);

		return issueInfoVoList;
	}

	private Long getDealState(String type) {
		Long dealState = null;
		if (type.equals(GisGlobalValueMap.PERSONFORTHING)
				|| type.equals(GisGlobalValueMap.PERSONALREADYTHING)
				|| type.equals(GisGlobalValueMap.FORTHING)) {// 我的待办,我的已办，下辖待办
			dealState = new Long(IssueState.STEPCOMPLETE_CODE);
		} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 我的已办结
			dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
		} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办结
			dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
		}
		return dealState;
	}
}
