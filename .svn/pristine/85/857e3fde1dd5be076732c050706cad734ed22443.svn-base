package com.tianque.issue.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.issue.domain.PeaceVillage;
import com.tianque.issue.domain.vo.SearchPeaceVillageVo;

/**
 * 村社区平安三联创:数据操作层接口
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
public interface PeaceVillageDao extends
		BaseDao<PeaceVillage, SearchPeaceVillageVo> {

	public List<PeaceVillage> getPeaceVillageListBySearchVo(
			SearchPeaceVillageVo searchPeaceVillageVo);

	public PeaceVillage getPeaceVillageByOrgAndYearMonth(
			PeaceVillage peaceVillage);

	/*************** 迁移合并方法 ******************/
	public int updatePeaceVillage(PeaceVillage peaceVillage);
}
