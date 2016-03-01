package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.EstateInformation;
import com.tianque.domain.vo.SearchEstateInformationVo;

public interface SearchEstateInformationDao {
	/**
	 * 根据查询条件查询房产信息
	 * 
	 * @param searchEstateInformationVo
	 *        查询条件
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<EstateInformation> searchEstateInformations(
			SearchEstateInformationVo searchEstateInformationVo, int pageNum, int pageSize,
			String sortField, String order);

	public List<EstateInformation> searchEstateInformationForExport(
			SearchEstateInformationVo searchEstateInformationVo, Integer page, Integer rows,
			String sidx, String sord);
}
