package com.tianque.plugin.analysisNew.dao;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.PrimaryOrganizationDataColumnTwoNewVo;

/**
 * 统计各类队伍信息
 * 
 * @author wangshirui
 */
public interface SearchPrimaryOrganizationDataColumnNewDao {

	/**
	 * 根据内部代码，统计队伍信息
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	List<PrimaryOrganizationDataColumnTwoNewVo> searchPrimaryOrganizationDataColumnByOrgInternalCode(
			Long parentOrgId);

}
