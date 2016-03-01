package com.tianque.issue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.PeaceVillageDao;
import com.tianque.issue.domain.PeaceVillage;
import com.tianque.issue.domain.vo.SearchPeaceVillageVo;

/**
 * 村社区平安三联创:数据操作层
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
@Repository("peaceVillageDao")
public class PeaceVillageDaoImpl extends
		BaseDaoImpl<PeaceVillage, SearchPeaceVillageVo> implements
		PeaceVillageDao {

	@Override
	public List<PeaceVillage> getPeaceVillageListBySearchVo(
			SearchPeaceVillageVo searchPeaceVillageVo) {
		if (searchPeaceVillageVo == null
				|| searchPeaceVillageVo.getOrganization() == null
				|| searchPeaceVillageVo.getOrganization().getId() == null
				|| searchPeaceVillageVo.getYear() == null
				|| searchPeaceVillageVo.getMonth() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return getSqlMapClientTemplate().queryForList(
				"peaceVillage.getPeaceVillageListBySearchVo",
				searchPeaceVillageVo);
	}

	@Override
	public PeaceVillage getPeaceVillageByOrgAndYearMonth(
			PeaceVillage peaceVillage) {

		return (PeaceVillage) getSqlMapClientTemplate().queryForObject(
				"peaceVillage.getPeaceVillageByOrgAndYearMonth", peaceVillage);
	}

	/********************** 迁移合并方法 ***********************/
	@Override
	public int updatePeaceVillage(PeaceVillage peaceVillage) {
		return getSqlMapClientTemplate().update(
				"peaceVillage.updatePeaceVillage", peaceVillage);
	}
}
