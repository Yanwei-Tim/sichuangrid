package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;

public interface SearchOtherAttentionPersonnelService {

	public PageInfo<OtherAttentionPersonnel> searchOtherAttentionPersonnel(
			SearchOtherAttentionPersonnelVo queryQopulation, int pageNum,
			int pageSize, String sortField, String order);

	public List<OtherAttentionPersonnel> searchOtherAttentionPersonnelForExport(
			SearchOtherAttentionPersonnelVo queryQopulation, Integer page,
			Integer rows, String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchOtherAttentionPersonnelVo personnelVo);

}
