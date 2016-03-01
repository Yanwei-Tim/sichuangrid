package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.vo.PageInfo;
import com.tianque.issue.state.IssueState;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.IssueTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

@SuppressWarnings({ "unchecked" })
@Repository("issueTwoDimensionMapDao")
public class IssueTwoDimensionMapDaoImpl extends AbstractDao implements
		IssueTwoDimensionMapDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<IssueInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			Long orgId, String orgInternalCode,
			ScreenCoordinateVo screenCoordinateVo, Integer pageNum,
			Integer pageSize, String sidx, String sord, Long dealState,
			String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("searchInfoVo",
				(screenCoordinateVo != null) ? screenCoordinateVo
						.getSearchInfoVo() : null);
		map.put("dealState", dealState);
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgInternalCode);
		map.put("tableType", type);
		map.put("isBound", "false");
		if (type.equals(GisGlobalValueMap.PERSONFORTHING)) {// 我的待办
			return queryForPageInfo(
					"issueTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					"issueTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					pageNum, pageSize, map);
		} else if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 我的已办
			return queryForPageInfo(
					"issueTwoDimensionMap.countMyDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					"issueTwoDimensionMap.findMyDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					pageNum, pageSize, map);
		} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 我的已办结
			return queryForPageInfo(
					"issueTwoDimensionMap.countMyFinishTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					"issueTwoDimensionMap.findMyFinishTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					pageNum, pageSize, map);
		} else if (type.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			return queryForPageInfo(
					"issueTwoDimensionMap.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					"issueTwoDimensionMap.findMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					pageNum, pageSize, map);
		} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办结
			return queryForPageInfo(
					"issueTwoDimensionMap.countMyJurisdictionsDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					"issueTwoDimensionMap.findMyJurisdictionsDoTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					pageNum, pageSize, map);
		}
		return null;
	}

	@Override
	public List<IssueInfoVo> getIssueTypeByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"issueTwoDimensionMap.loadIssueHasTypes", id);
	}

	@Override
	public PageInfo<IssueInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", null);
		map.put("sortField", "centerlon");
		if (mainTableName.equals("issues")) {// 我的事项
			map.put("tableType", "issues");
			return queryForPageInfo(
					"issueTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					"issueTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					pageNum, pageSize, map);
		}
		if (mainTableName.equals("jurisdictionsIssue")) {// 下辖事项
			map.put("tableType", "jurisdictionsIssue");
			return queryForPageInfo(
					"issueTwoDimensionMap.countMyJurisdictionsTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					"issueTwoDimensionMap.findMyJurisdictionsTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					pageNum, pageSize, map);
		}
		return null;
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("tableType", type);
		if (type.equals(GisGlobalValueMap.PERSONFORTHING)) {// 我的待办
			return (Integer) queryForCount(
					"issueTwoDimensionMap.countMyNendDoBoundedTwoDimensionMapInfoByOrgInternalCode",
					map);
		} else if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 我的已办
			return (Integer) queryForCount(
					"issueTwoDimensionMap.countMyDoBoundedTwoDimensionMapInfoByOrgInternalCode",
					map);
		} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 我的已办结
			return (Integer) queryForCount(
					"issueTwoDimensionMap.countMyFinishBoundedTwoDimensionMapInfoByOrgInternalCode",
					map);
		} else if (type.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			// 乡镇以上下辖事件加上缓存
			String cacheKey = MemCacheConstant
					.generateCacheKeyFromMethodName(
							Integer.class,
							MemCacheConstant.COUNT_MYJURISDICTIONSNEEDDOBOUNDEDTWODIMENSIONMAPINFOBYORGINTERNALCODE,
							type, orgInternalCode == null ? "null"
									: orgInternalCode);
			Integer count = (Integer) cacheService.get(
					MemCacheConstant.MAP_NAMESPACE, cacheKey);
			if (count == null) {
				count = (Integer) queryForCount(
						"issueTwoDimensionMap.countMyJurisdictionsNeedDoBoundedTwoDimensionMapInfoByOrgInternalCode",
						map);
				cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey,
						count);
			}
			return count;
		} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办结
			String paramString = "typeName:" + type + ",orgInternalCode:"
					+ (orgInternalCode == null ? "null" : orgInternalCode);
			String cacheKey = MemCacheConstant
					.generateCacheKeyFromMethodName(
							Integer.class,
							MemCacheConstant.COUNT_MYJURISDICTIONSFINISHBOUNDEDTWODIMENSIONMAPINFOBYORGINTERNALCODE,
							type, orgInternalCode == null ? "null"
									: orgInternalCode);
			Integer count = (Integer) cacheService.get(
					MemCacheConstant.MAP_NAMESPACE, cacheKey);
			if (count == null) {
				count = (Integer) queryForCount(
						"issueTwoDimensionMap.countMyJurisdictionsFinishBoundedTwoDimensionMapInfoByOrgInternalCode",
						map);
				cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey,
						count);
			}
			return count;
		}
		return null;
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue) {

		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				null, searchValue, null, null, null);
		map.put("tableType", "jurisdictionsIssue");
		return (Integer) queryForCount(
				"issueTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue",
				map);
	}

	@Override
	public boolean updateTwoDimensionMap(IssueInfoVo infoVo) {
		return updateForBind("issueTwoDimensionMap.updateTwoDimensionMap",
				infoVo) > 0;
	}

	@Override
	public PageInfo<IssueInfoVo> findMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
			Long orgId, Long typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		Map<String, Object> map = getIssueMap(orgId, null, typeName, null,
				null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countTwoDimensionMapPageInfoByOrgIdAndTypeName",
				"issueTwoDimensionMap.findTwoDimensionMapPageInfoByOrgIdAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
			Long orgId, Long typeName) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGIDANDTYPENAME,
						typeName.toString(),
						orgId == null ? "null" : orgId.toString());
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getIssueMap(orgId, null, typeName, null,
					null, null);
			map.put("tableType", GisGlobalValueMap.PERSONFORTHING);
			count = (Integer) queryForCount(
					"issueTwoDimensionMap.countTwoDimensionMapPageInfoByOrgIdAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public PageInfo<IssueInfoVo> findMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getIssueMap(orgId, null, null, null, null,
				dealStateList);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList",
				"issueTwoDimensionMap.findMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<IssueInfoVo> findMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getIssueMap(orgId, null, null, null, null,
				dealStateList);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList",
				"issueTwoDimensionMap.findMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDTYPENAME,
						dealStateList.toString(),
						orgId == null ? "null" : Long.toString(orgId));
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("targeOrgId", orgId);
			map.put("dealStateList", dealStateList);
			map.put("tableType", GisGlobalValueMap.PERSONALREADYTHING);
			count = (Integer) queryForCount(
					"issueTwoDimensionMap.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList",
					map);
			count += (Integer) queryForCount(
					"issueTwoDimensionMap.countMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList",
					map);

			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public PageInfo<IssueInfoVo> findMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
				dealState, null, null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState",
				"issueTwoDimensionMap.findMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState",
				pageNum, pageSize, map);
	}

	private Map getIssueMap(Long orgId, String orgInternalCode, Long dealState,
			String sidx, String sord, List dealStateList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("targeOrgInternalCode", orgInternalCode);
		map.put("dealState", dealState);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("orgInternalCode", orgInternalCode);
		map.put("dealStateList", dealStateList);
		return map;
	}

	@Override
	public Integer countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState) {
		String paramString = "dealState:"
				+ dealState
				+ ",targeOrgInternalCode:"
				+ (orgInternalCode == null ? "null" : orgInternalCode
						+ ",targeOrgId:" + orgId);
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_MYJURISDICTIONSNEEDDOTWODIMENSIONMAPPAGEINFOBYORGIDANDDEALSTATE,
						dealState.toString(), orgInternalCode == null ? "null"
								: orgInternalCode, orgId.toString());
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);

		if (count == null) {
			Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
					dealState, null, null, null);
			map.put("tableType", GisGlobalValueMap.FORTHING);
			map.put("status", IssueState.COMPLETE);
			count = (Integer) queryForCount(
					"issueTwoDimensionMap.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public PageInfo<IssueInfoVo> findMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
				dealState, null, null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
				"issueTwoDimensionMap.findMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<IssueInfoVo> findMyJurisdictionsHistoryDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
				dealState, null, null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyJurisdictionsHistoryDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
				"issueTwoDimensionMap.findMyJurisdictionsHistoryDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<IssueInfoVo> findMyFinishTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
				dealState, null, null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyFinishTwoDimensionMapPageInfoByOrgIdAndDealState",
				"issueTwoDimensionMap.findMyFinishTwoDimensionMapPageInfoByOrgIdAndDealState",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<IssueInfoVo> findMyFinishHistoryTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
				dealState, null, null, null);

		return queryForPageInfo(
				"issueTwoDimensionMap.countMyFinishHistoryTwoDimensionMapPageInfoByOrgIdAndDealState",
				"issueTwoDimensionMap.findMyFinishHistoryTwoDimensionMapPageInfoByOrgIdAndDealState",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState) {
		String paramString = "dealState:"
				+ dealState
				+ ",targeOrgInternalCode:"
				+ (orgInternalCode == null ? "null" : orgInternalCode
						+ ",targeOrgId:" + orgId);
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_MYJURISDICTIONSDONETWODIMENSIONMAPPAGEINFOBYORGIDANDDEALSTATE,
						dealState.toString(), orgInternalCode == null ? "null"
								: orgInternalCode, orgId.toString());
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
					dealState, null, null, null);
			map.put("tableType", GisGlobalValueMap.ALREADYTHING);
			count = (Integer) queryForCount(
					"issueTwoDimensionMap.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
					map);
			count += (Integer) queryForCount(
					"issueTwoDimensionMap.countMyJurisdictionsDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealState",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);

		}
		return count;
	}

	@Override
	public List<IssueInfoVo> findIssueInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo) {
		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				screenCoordinateVo, null, null, null, null);
		return queryForList(
				"issueTwoDimensionMap.findIssueInfoVosByOrgCodeAndMaxAndMinLonLatArrys",
				map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoSumByOrgInternalCode(
			String orgInternalCode) {
		Integer sumNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"issueTwoDimensionMap.countTwoDimensionMapInfoSumByOrgInternalCode",
						orgInternalCode);
		return sumNum;
	}

	@Override
	public IssueInfoVo getViewPopupInfoById(Long id) {
		return (IssueInfoVo) getSqlMapClientTemplate().queryForObject(
				"issueTwoDimensionMap.getViewPopupInfoById", id);
	}

	@Override
	public IssueInfoVo getAlreadyViewPopupInfoById(Long id) {
		return (IssueInfoVo) getSqlMapClientTemplate().queryForObject(
				"issueTwoDimensionMap.getAlreadyViewPopupInfoById", id);
	}

	@Override
	public IssueInfoVo getAlreadyThingViewPopupInfoById(Long id) {
		return (IssueInfoVo) getSqlMapClientTemplate().queryForObject(
				"issueTwoDimensionMap.getAlreadyThingViewPopupInfoById", id);
	}

	@Override
	public Integer countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_MYDONETWODIMENSIONMAPPAGEINFOBYORGIDANDDEALSTATE,
						dealState.toString(), orgInternalCode == null ? "null"
								: orgInternalCode, orgId.toString());
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getIssueMap(orgId, orgInternalCode,
					dealState, null, null, null);
			map.put("tableType", GisGlobalValueMap.GONETHROUGH);
			count = (Integer) queryForCount(
					"issueTwoDimensionMap.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealState",
					map);
			count += (Integer) queryForCount(
					"issueTwoDimensionMap.countMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealState",
					map);

			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;

	}

}
