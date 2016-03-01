package com.tianque.partyBuilding.baseInfos.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.baseInfos.domain.BasicCase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchBasicCaseVo;

/**
 * 基本情况表:数据操作层接口
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
public interface BasicCaseDao extends BaseDao<BasicCase, SearchBasicCaseVo> {

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
