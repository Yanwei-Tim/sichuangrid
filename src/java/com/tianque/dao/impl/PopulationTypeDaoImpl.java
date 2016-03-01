package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.PopulationTypeDao;
import com.tianque.domain.PopulationTypeBean;

@Repository("populationTypeDao")
public class PopulationTypeDaoImpl extends AbstractBaseDao implements
		PopulationTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PopulationTypeDao#addPopulationType(com.tianque.domain
	 * .PopulationType)
	 */
	@Override
	public void addPopulationType(PopulationTypeBean populationType) {
		this.getSqlMapClientTemplate().insert(
				"populationType.addPopulationType", populationType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PopulationTypeDao#deletePopulationTypeByActualIdAndType
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public void deletePopulationTypeByActualIdAndType(Long actualId,
			String actualType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("actualId", actualId);
		param.put("actualType", actualType);
		this.getSqlMapClientTemplate().delete(
				"populationType.deletePopulationTypeByActualIdAndType", param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PopulationTypeDao#deletePopulationTypeByPopulationIdAndType
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public void deletePopulationTypeByPopulationIdAndType(Long populationId,
			String populationType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("populationId", populationId);
		param.put("populationType", populationType);
		this.getSqlMapClientTemplate().delete(
				"populationType.deletePopulationTypeByPopulationIdAndType",
				param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PopulationTypeDao#getPopulationTypeByActualIdAndType(
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public List<PopulationTypeBean> getPopulationTypeByActualIdAndType(
			Long actualId, String actualType) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("actualId", actualId);
		query.put("actualType", actualType);
		return getSqlMapClientTemplate().queryForList(
				"populationType.getPopulationTypeByActualIdAndType", query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PopulationTypeDao#getPopulationTypeByPopulationIdAndType
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public PopulationTypeBean getPopulationTypeByPopulationIdAndType(
			Long populationId, String populationType) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("populationId", populationId);
		query.put("populationType", populationType);
		return (PopulationTypeBean) getSqlMapClientTemplate().queryForObject(
				"populationType.getPopulationTypeByPopulationIdAndType", query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.PopulationTypeDao#
	 * deletePopulationTypeByActualIdAndTypeAndPopulationType( java.lang.Long,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePopulationTypeByActualIdAndTypeAndPopulationType(
			Long actualId, String actualType, String populationType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("actualId", actualId);
		param.put("actualType", actualType);
		param.put("populationType", populationType);
		this.getSqlMapClientTemplate()
				.delete("populationType.deletePopulationTypeByActualIdAndTypeAndPopulationType",
						param);
	}

	@Override
	public PopulationTypeBean getActualPopulationTypeBeanByOrgIdAndIdCardNo(
			Long orgId, Long baseInfoId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("baseInfoId", baseInfoId);
		map.put("shardCode", shardCode);
		return (PopulationTypeBean) getSqlMapClientTemplate().queryForObject(
				"populationType.getActualPopulationTypeBeanByOrgIdAndIdCardNo",
				map);
	}

}
