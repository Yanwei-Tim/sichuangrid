package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.PrimaryOrganizationDataColumnTwoNewVo;

/**
 * 统计队伍信息
 */
public interface SearchPrimaryOrganizationDataColumnNewService {

	/**
	 * 统计各类队伍信息
	 * 
	 * @param parentOrgId
	 * @return
	 */
	List<PrimaryOrganizationDataColumnTwoNewVo> getPrimaryOrganizationDataColumnVoList(
			Long parentOrgId);

}
