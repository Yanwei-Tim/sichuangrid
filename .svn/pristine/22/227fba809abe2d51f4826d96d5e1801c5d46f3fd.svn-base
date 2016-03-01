package com.tianque.usedInfoOptmize.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.phoenix.schema.ArgumentTypeMismatchException;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.DateUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.usedInfoOptmize.dao.UsedInfoDataDao;
import com.tianque.usedInfoOptmize.domain.UsedInfoData;

/**
 * @Description:信息系统使用情况报表dao
 * @author zhangyouwei@hztianque.com
 * @date: 2015-7-1 下午03:43:33
 */
@Repository("usedInfoDataDao")
public class UsedInfoDataDaoImpl extends AbstractBaseDao implements
		UsedInfoDataDao {

	private static final String  TMP_TABLE_WEEK = " usedinfoWeekTmp" ;
	
	@Override
	public void createUsedInfoMonthData(Date startDate, Date endDate,
			Long orgTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgTypeId", orgTypeId);
		map.put("createDate", new Date());
		map.put("createUser", ThreadVariable.getSession().getUserName());
		getSqlMapClientTemplate().insert(
				"usedInfoData.createUsedInfoMonthData", map);
	}

	@Override
	public void createUsedInfoWeekData(Date startDate, Date endDate,
			Long orgTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgTypeId", orgTypeId);
		map.put("createDate", new Date());
		map.put("createUser", ThreadVariable.getSession().getUserName());
		getSqlMapClientTemplate().insert("usedInfoData.createUsedInfoWeekData",
				map);
	}

	/**
	 * 删除月数据
	 */
	@Override
	public void deleteUsedInfoMonthData() {
		getSqlMapClientTemplate()
				.delete("usedInfoData.deleteUsedInfoMonthData");
	}

	/**
	 * 删除周数据
	 */
	@Override
	public void deleteUsedInfoWeekData() {
		getSqlMapClientTemplate().delete("usedInfoData.deleteUsedInfoWeekData");
	}

	@Override
	public List<UsedInfoData> findUsedInfoWeekDataByParentOrgId(Long parentOrgId) {
		return getSqlMapClientTemplate().queryForList(
				"usedInfoData.findUsedInfoWeekDataByParentOrgId", parentOrgId);
	}

	@Override
	public List<UsedInfoData> findUsedInfoMonthDataByParentOrgId(
			Long parentOrgId) {
		return getSqlMapClientTemplate().queryForList(
				"usedInfoData.findUsedInfoMonthDataByParentOrgId", parentOrgId);
	}

	@Override
	public void dropTempWeekTable() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tmpTableName", TMP_TABLE_WEEK);
		try {
			getSqlMapClientTemplate().update("usedInfoData.dropTempWeekTable",map);
		} catch (Exception e) {
			logger.error("创建活跃度周临时表错误，",e.getMessage());
		}
	}

	@Override
	public void createTempWeekData(Date startDate, Date endDate,
			Long orgTypeId) {
		if(startDate == null || endDate == null || orgTypeId == null){
			throw new RuntimeException("查询条件不足！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", DateUtil.toString(startDate, "yyyy-MM-dd HH:mm:ss") );
		map.put("endDate", DateUtil.toString(endDate, "yyyy-MM-dd HH:mm:ss") );
		map.put("orgTypeId", orgTypeId);
		map.put("createDate", DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss") );
		map.put("createUser", ThreadVariable.getSession().getUserName());
		map.put("tmpTableName", TMP_TABLE_WEEK);
		getSqlMapClientTemplate().insert("usedInfoData.createTempWeekData",
				map);
		
	}

	@Override
	public void mergeWeekData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tmpTableName", TMP_TABLE_WEEK);
		getSqlMapClientTemplate().insert("usedInfoData.mergeWeekData",
				map);
	}

}
