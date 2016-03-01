package com.tianque.baseInfo.positiveInfo.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;

public interface SearchPositiveInfosDao {

	public PageInfo<PositiveInfo> searchAttentionPersonnel(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order);

	public List<PositiveInfo> searchAttentionPersonnelForExport(
			SearchPositiveInfoVo searchSuperiorVistVo, Integer page,
			Integer rows, String sortField, String order);

	public List<PositiveInfo> findPositiveInfoByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	/**
	 * 根据条件查询某部门数据
	 * 
	 * @param orgInternalCode
	 * @param map
	 *            <String, Object> isResettlementdate
	 *            1.值为空，不为查询条件；2.值为0,未安置；3.值为1，已安置 isHelped
	 *            1.值为空，不为查询条件；2.值为0，未帮教；3.值为1，已帮教 positiveInfoType
	 *            1.值为空，不为查询条件；2.按照值分别查询刑释、解教人员。
	 * @return
	 */
	public int getCountPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public List<PositiveInfo> findPositiveInfos();

	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			Map<String, Object> map);

	public PageInfo<PositiveInfo> searchFastPositiveInfo(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order);

	public Integer getCount(SearchPositiveInfoVo searchPositiveInfoVo);
}
