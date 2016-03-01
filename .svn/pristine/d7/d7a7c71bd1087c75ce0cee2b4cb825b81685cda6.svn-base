package com.tianque.baseInfo.scenicManage.scenicSpot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.vo.SearchScenicSpotVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("searchScenicSpotDao")
public class SearchScenicSpotDaoImpl extends AbstractBaseDao implements
		SearchScenicSpotDao {

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@SuppressWarnings("unchecked")
	private PageInfo<ScenicSpot> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ScenicSpot> pageInfo = new PageInfo<ScenicSpot>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ScenicSpot> findScenicSpotListForSearch(
			SearchScenicSpotVo scenicSpotVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (isStrotFieldAvaliable(sortField)) {
			scenicSpotVo.setSortField(sortField);
			scenicSpotVo.setOrder(order);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchScenicSpot.countScenicSpot", scenicSpotVo);
		List<ScenicSpot> list = getSqlMapClientTemplate().queryForList(
				"searchScenicSpot.findScenicSpotList", scenicSpotVo,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchScenicSpot.findScenicSpotByNameAndPinyinAndOrgInternalCode",
						map);
	}

}
