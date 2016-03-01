package com.tianque.partyBuilding.baseInfos.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.baseInfos.domain.BasicCase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchBasicCaseVo;

/**
 * 基本情况表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
public interface BasicCaseService extends BaseService<BasicCase, SearchBasicCaseVo> {

	/**
	 * 查询BasicCase的信息
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	BasicCase findBasicCaseByIdAndOrgId(Long id, Long orgId);

	BasicCase getByIdAndOrgIdAndType(Long id, Long orgId, String type);

}
