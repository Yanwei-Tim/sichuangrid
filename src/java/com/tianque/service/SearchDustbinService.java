package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;

public interface SearchDustbinService {

	/***
	 * 通过条件查询部件信息
	 * 
	 * @param searchDustbinVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<Dustbin> findDustbinByQueryCondition(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/***
	 * 快速检索部件信息
	 * 
	 * @param searchDustbinVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param partType
	 * @return
	 */
	public PageInfo<Dustbin> fastSearchDustbin(SearchDustbinVo searchDustbinVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String partType);

	/***
	 * 导出所需数据查询
	 * 
	 * @param searchDustbinVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param partType
	 * @return
	 */
	public List<Dustbin> searchDustbinForExport(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String partType);

	/***
	 * 获取数据条数
	 * 
	 * @param searchDustbinVo
	 * @return
	 */
	public Integer getCount(SearchDustbinVo searchDustbinVo);
}
