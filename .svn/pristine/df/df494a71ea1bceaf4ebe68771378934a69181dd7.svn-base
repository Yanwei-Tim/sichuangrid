package com.tianque.baseInfo.idleYouth.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIdleYouthVo;

public interface SearchIdleYouthDao {
	public PageInfo<IdleYouth> searchIdleYouth(
			SearchIdleYouthVo idleYouthSearchCondition, int pageNum,
			int pageSize, String sortField, String order);

	public List<IdleYouth> searchIdleYouthsForExport(
			SearchIdleYouthVo idleYouthSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public List findIdleYouthNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	/**
	 * 根据条件查询某部门数据
	 * 
	 * @param orgInternalCode
	 * @param map
	 *            <String, Object> isHelped 1.值为空，不为查询条件；2.值为0，未帮教；3.值为1，已帮教
	 *            staffType 1.值为空，不为查询条件；2.按照类型值分别查询。
	 * @return
	 */
	public int getCountIdleyouthByOrgInternalCodeAndMap(String orgInternalCode,
			Map<String, Object> map);

	public List<IdleYouth> findIdleYouth();

	public Integer getCount(SearchIdleYouthVo idleYouthVo);
}
