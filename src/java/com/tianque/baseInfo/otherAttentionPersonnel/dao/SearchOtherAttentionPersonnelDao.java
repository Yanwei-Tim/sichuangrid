package com.tianque.baseInfo.otherAttentionPersonnel.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;

public interface SearchOtherAttentionPersonnelDao {

	public PageInfo<OtherAttentionPersonnel> searchOtherAttentionPersonnel(
			SearchOtherAttentionPersonnelVo queryQopulation, int pageNum,
			int pageSize, String sortField, String order);

	public List<OtherAttentionPersonnel> searchOtherAttentionPersonnelForExport(
			SearchOtherAttentionPersonnelVo queryQopulation, Integer page,
			Integer rows, String sidx, String sord);

	public int getCountOtherAttentionPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchOtherAttentionPersonnelVo personnelVo);
}
