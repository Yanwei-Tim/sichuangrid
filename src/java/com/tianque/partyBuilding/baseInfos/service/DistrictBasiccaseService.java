package com.tianque.partyBuilding.baseInfos.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.baseInfos.domain.DistrictBasiccase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchDistrictBasiccaseVo;

/**
 * 社区基本情况表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
public interface DistrictBasiccaseService extends
		BaseService<DistrictBasiccase, SearchDistrictBasiccaseVo> {

	/**
	 * 根据id和组织机构id查询社区基本情况信息
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	public DistrictBasiccase findDistrictBasiccaseByIdAndOrgId(Long id,
			Long orgId);

	public DistrictBasiccase deletePhotosAndOrgById(String imgUrl, Long id,
			Long orgId);

}
