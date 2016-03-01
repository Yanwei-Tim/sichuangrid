package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;

public interface SearchDustbinDao {
	public PageInfo<Dustbin> findDustbinByQueryCondition(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<Dustbin> fastSearchDustbin(SearchDustbinVo searchDustbinVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String partType);

	public List<Dustbin> searchDustbinForExport(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String partType);

	public Integer getCount(SearchDustbinVo searchDustbinVo);
}
