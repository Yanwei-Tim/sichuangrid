package com.tianque.baseInfo.base.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.base.BaseDomain;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.ActualPopulationMsg;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.jms.msg.BusinesPopulationMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;

public class BaseInfoPopulationBaseDaoImpl<T extends Countrymen, SearchVo extends BaseDomain>
		extends BaseDaoImpl<T, SearchVo> implements
		BaseInfoPopulationBaseDao<T, SearchVo> {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	@Autowired
	private ShardConversion shardConversion;

	public List<Long> batchInsertDate(List<T> datas) {
		List<Long> ids = super.batchInsertDate(datas);
		return ids;
	}

	public void batchUpdateDate(List<T> datas) {
		super.batchUpdateDate(datas);
		// activeMQMessageSender.send(getMsgObject(coventListToString(datas),
		// OperateMode.EDIT));
	}

	public void updateLogOutByMap(Map<String, Object> map) {
		PopulationCatalog catalog = (PopulationCatalog) map.get("catalog");
		map.put("sysDate", new Date());
		if (PopulationCatalog.ALL_ACTUAL_POPULATION.equals(catalog
				.getParentCatalog())) {
			getSqlMapClientTemplate()
					.update("populationBaseInfo.updateActualPopulationLogOutByPopulationTypeAndId",
							map);
		} else {
			getSqlMapClientTemplate()
					.update("populationBaseInfo.updateBussPopulationLogOutByPopulationTypeAndId",
							map);
		}
		// sendEmphasiseChangeJMSMsg((Long) map.get("id"),
		// ((LogoutDetail) map.get("logoutDetail")).getLogout());
	}

	public void sendEmphasiseChangeJMSMsg(Long id, Long logout) {
		if (logout.equals(IsEmphasis.Emphasis)) {
			activeMQMessageSender
					.send(getMsgObject(get(id), OperateMode.LOG_ON));
		}
		if (logout.equals(IsEmphasis.IsNotEmphasis)) {
			activeMQMessageSender.send(getMsgObject(get(id),
					OperateMode.LOG_OUT));
		}

	};

	@Override
	public Map<String, Object> getIdCardNoAndOrgIdByPopulationTableAndId(
			String table, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
		map.put("id", id);
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject(
				"populationBaseInfo.getIdCardNoAndOrgIdByPopulationTableAndId",
				map);
	}

	@Override
	public Map<String, Object> getActualPopulationLogoutByIdCardNoAndOrgId(
			Map<String, Object> map) {
		return (Map<String, Object>) getSqlMapClientTemplate()
				.queryForObject(
						"populationBaseInfo.getActualPopulationLogoutByIdCardNoAndOrgId",
						map);
	}

	@Override
	public T add(T entity) {
		String actualPopulationType = entity.getActualPopulationType();
		T temp = super.add(entity);
		temp.setActualPopulationType(actualPopulationType);
		// activeMQMessageSender.send(getMsgObject(temp, OperateMode.ADD));
		return temp;
	}

	@Override
	public T addShard(T entity) {
		String actualPopulationType = entity.getActualPopulationType();
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				super.getSequence(entity));
		id = IdConversionShardUtil.IdAdditionalShard(id, entity.getShardCode());
		entity.setId(id);
		T temp = super.addShard(entity);
		temp.setActualPopulationType(actualPopulationType);
		return temp;
	}

	@Override
	public T update(T entity) {
		super.update(entity);
		// activeMQMessageSender.send(getMsgObject(entity, OperateMode.EDIT));
		return get(entity.getId());
	}

	@Override
	public T get(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return super.get(id);
	}

	@Override
	public T getShard(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return super.getShard(id);
	}

	@Override
	public void delete(Long id) {
		// activeMQMessageSender.send(getMsgObject(get(id),
		// OperateMode.DELETE));
		super.delete(id);
	}

	@Override
	public void deleteShard(Long id) {
		super.deleteShard(id);
	}

	public void delete(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	public void updateDeathAndLogoutDeatilById(Long id, Boolean death,
			LogoutDetail logoutDetail) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("logoutDetail", logoutDetail);
		map.put("death", death);
		getSqlMapClientTemplate()
				.update(updateDeathAndLogoutStateById(entityClass
						.getSimpleName()),
						map);
		// activeMQMessageSender.send(getMsgObject(get(id),
		// OperateMode.CANCEL_DEATH));
	}

	public T updateBaseInfo(T entity) {
		getSqlMapClientTemplate().update(
				getSqlNamespaceByClassName(entityClass.getSimpleName()) + "."
						+ "updateBaseInfo", entity);
		entity = get(entity.getId());
		// activeMQMessageSender.send(getMsgObject(entity, OperateMode.EDIT));
		return entity;
	}

	public T updateBusiness(T entity) {
		getSqlMapClientTemplate().update(
				getSqlNamespaceByClassName(entityClass.getSimpleName()) + "."
						+ "updateBusiness", entity);
		return get(entity.getId());
	}

	public T updateBusinessShard(T entity) {
		String shardCode = IdConversionShardUtil.getShardCodeById(entity
				.getId());
		entity.setShardCode(shardCode);
		getSqlMapClientTemplate().update(
				getSqlNamespaceByClassName(entityClass.getSimpleName()) + "."
						+ "updateBusiness", entity);
		return getShard(entity.getId());
	}

	public T getByIdCard(List<String> idCardNoList, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("idCardNoList", idCardNoList);
		return (T) getSqlMapClientTemplate().queryForObject(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".getByIdCard", query);
	}

	public T getByOrgIdAndIdCardNo(Long orgId, String idCardNo) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("idCardNo", idCardNo);
		map.put("shardCode", shardConversion.getShardCode(orgId));
		return (T) getSqlMapClientTemplate().queryForObject(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".getByOrgIdAndIdCardNo", map);
	}

	private String updateDeathAndLogoutStateById(String className) {
		return getSqlNamespaceByClassName(className) + "."
				+ "updateDeathAndLogoutStateById";
	}

	private BaseMsg getMsgObject(Countrymen population, OperateMode mode) {
		if (population == null)
			return null;
		if (AttentionPopulation.class.isAssignableFrom(entityClass)) {
			return new BusinesPopulationMsg((AttentionPopulation) population,
					mode);
		} else if (ActualPopulation.class.isAssignableFrom(entityClass)) {
			return new ActualPopulationMsg((ActualPopulation) population, mode);
		} else {
			return null;
		}
	}

	private String getMoveDataSqlId(T population) {
		return lowerFirst(population.getClass().getSimpleName())
				+ ".updateDataMove" + population.getClass().getSimpleName()
				+ "ToOrg";

	}

	private String lowerFirst(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	@Override
	public void updateData(String id, Long targetOrgId, String orgCode, T t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", targetOrgId);
		map.put("orgInternalCode", orgCode);
		String sqlidString = getMoveDataSqlId(t);
		getSqlMapClientTemplate().update(sqlidString, map);
	}

	public Long getIdByBaseinfoIdAndOrgId(Long baseInfoId, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("baseInfoId", baseInfoId);
		query.put("shardCode", shardConversion.getShardCode(orgId));
		return (Long) getSqlMapClientTemplate().queryForObject(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".getIdByBaseinfoIdAndOrgId", query);
	}

	public Long getIdByBaseinfoIdAndOrgId(Long baseInfoId, Long orgId,
			String shardCode) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("baseInfoId", baseInfoId);
		query.put("shardCode", shardCode);
		return (Long) getSqlMapClientTemplate().queryForObject(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".getIdByBaseinfoIdAndOrgId", query);
	}

	@Override
	public Long countServiceMemberHasObject(String populationType, Long objectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("id", objectId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"attentionPopulationBaseInfo.hasServiceTeamMemberQuery", map);
	}

	@Override
	public Date findServiceLastRecordTime(String populationType, Long objectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("id", objectId);
		return (Date) getSqlMapClientTemplate().queryForObject(
				"attentionPopulationBaseInfo.hasServiceTeamRecordQuery", map);
	}

}
