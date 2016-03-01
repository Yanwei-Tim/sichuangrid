package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnTwoVo;

/**
 * 统计队伍信息
 */
public interface SearchPrimaryOrganizationDataColumnService {

	/**
	 * 统计各类队伍信息
	 * 
	 * @param parentOrgId
	 * @return
	 */
	List<PrimaryOrganizationDataColumnTwoVo> getPrimaryOrganizationDataColumnVoList(
			Long parentOrgId);

}
