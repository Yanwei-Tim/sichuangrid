package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.issue.domain.PeaceVillage;
import com.tianque.issue.domain.vo.SearchPeaceVillageVo;

/**
 * 村社区平安三联创:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
public interface PeaceVillageService extends
		BaseService<PeaceVillage, SearchPeaceVillageVo> {

	public List<PeaceVillage> getPeaceVillageListBySearchVo(
			SearchPeaceVillageVo searchPeaceVillageVo);

	public void savePeaceVillage(List<PeaceVillage> peaceVillageList);

	public PeaceVillage getPeaceVillageByOrgAndYearMonth(
			PeaceVillage peaceVillage);

	/*************************** 迁移合并方法 *************************/
	public int updatePeaceVillage(PeaceVillage peaceVillage);

}
