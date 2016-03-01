package com.tianque.service;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;

public interface SearchPositiveInfosService {
	public PageInfo<PositiveInfo> searchAttentionPersonnel(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order);

	public int getCountPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public List<PositiveInfo> findPositiveInfos();

	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			Map<String, Object> map);

	public String[][] getExportPopertyArray();

	/**
	 * 导出时获取数据
	 * 
	 * @param searchPositiveInfosVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public List<PositiveInfo> searchPositiveInfoForExport(
			SearchPositiveInfoVo searchPositiveInfosVo, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public Integer getCount(SearchPositiveInfoVo searchPositiveInfoVo);
}
