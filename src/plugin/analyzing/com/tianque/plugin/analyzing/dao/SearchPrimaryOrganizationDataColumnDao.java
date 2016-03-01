package com.tianque.plugin.analyzing.dao;

import java.util.List;

import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnTwoVo;

/**
 * 统计各类队伍信息
 * 
 * @author wangshirui
 */
public interface SearchPrimaryOrganizationDataColumnDao {

	/**
	 * 根据内部代码，统计队伍信息
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	List<PrimaryOrganizationDataColumnTwoVo> searchPrimaryOrganizationDataColumnByOrgInternalCode(
			Long parentOrgId);

}
