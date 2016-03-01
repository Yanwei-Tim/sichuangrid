package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.CommonServiceInfoDao;
import com.tianque.newVillage.domain.CommonServiceInfo;

/**
 * @ClassName: CommonServiceInfoDaoImpl
 * @Description: 公共服务
 */
@Repository("commonServiceInfoDao")
public class CommonServiceInfoDaoImpl extends AbstractBaseDao implements
		CommonServiceInfoDao {

	@Override
	public CommonServiceInfo addCommonServiceInfo(
			CommonServiceInfo commonServiceInfo) {
		if (commonServiceInfo != null) {
			commonServiceInfo.setCreateUser(ThreadVariable.getUser().getName());
			commonServiceInfo.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"commonServiceInfo.addCommonServiceInfo", commonServiceInfo);
		return getCommonServiceInfoById(id);
	}

	@Override
	public CommonServiceInfo getCommonServiceInfoById(Long id) {
		return (CommonServiceInfo) getSqlMapClientTemplate().queryForObject(
				"commonServiceInfo.getCommonServiceInfo", id);
	}

	@Override
	public void deleteCommonServiceInfoById(String[] id) {
		getSqlMapClientTemplate().delete(
				"commonServiceInfo.deleteCommonServiceInfo", id);
	}

	@Override
	public CommonServiceInfo updateCommonServiceInfo(
			CommonServiceInfo commonServiceInfo) {
		if (commonServiceInfo != null) {
			commonServiceInfo.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update(
				"commonServiceInfo.updateCommonServiceInfo", commonServiceInfo);
		return getCommonServiceInfoById(commonServiceInfo.getId());
	}

	@Override
	public CommonServiceInfo getCommonServiceInfoByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (CommonServiceInfo) getSqlMapClientTemplate().queryForObject(
				"commonServiceInfo.getCommonServiceInfoByBasicId", map);
	}

}
