package com.tianque.baseInfo.positiveInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;

@Repository("positiveInfoDao")
public class PositiveInfoDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<PositiveInfo, SearchPositiveInfoVo>
		implements PositiveInfoDao {

	@Override
	public PageInfo<PositiveInfo> findPosiviteInfosForPage(
			PositiveInfo positiveInfo, int pageNum, int pageSize,
			String sortField, String order, Long isEmphasis) {
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		if (positiveInfo != null) {
			if (StringUtil.isStringAvaliable(sortField)) {
				positiveInfo.setSortField(sortField);
				positiveInfo.setOrder(order);
			}
		}
		positiveInfo.setIsEmphasis(isEmphasis);
		List list = getSqlMapClientTemplate().queryForList(
				"positiveInfo.findPositiveInfo", positiveInfo,
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"positiveInfo.countPositiveInfo", positiveInfo);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("positiveInfo.updateEmphasiseById",
				map);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"positiveInfo.updateDeathAndLogoutStateById", map);
	}

	@Override
	public List<PositiveInfo> findPositiveInfoByDate(Long positiveTypeId,
			long time) {
		Map map = new HashMap();
		map.put("positiveTypeId", positiveTypeId);
		map.put("time", time);
		return getSqlMapClientTemplate().queryForList(
				"positiveInfo.findPositiveInfoByDate", map);
	}

	private PageInfo<PositiveInfo> emptyPage(int pageSize) {
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PositiveInfo>());
		return pageInfo;
	}

	@Override
	public PageInfo<PositiveInfo> findPositiveInfosForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis) {
		if (orgInternalCode == null || "".equals(orgInternalCode.trim())) {
			return emptyPage(pageSize);
		}
		PositiveInfo positiveInfo = new PositiveInfo();
		positiveInfo.setOrgInternalCode(orgInternalCode);
		positiveInfo.setIsEmphasis(isEmphasis);
		if (StringUtil.isStringAvaliable(sortField)) {
			positiveInfo.setSortField(sortField);
		}
		positiveInfo.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"positiveInfo.countPositiveInfosForPage", positiveInfo);
		@SuppressWarnings("unchecked")
		List<PositiveInfo> list = getSqlMapClientTemplate().queryForList(
				"positiveInfo.findPositiveInfosForPage", positiveInfo,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PositiveInfo> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<PositiveInfo> list) {
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("positiveinfos", id);
	}

}
