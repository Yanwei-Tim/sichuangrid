package com.tianque.mobile.mobileDictionary.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.mobile.mobileDictionary.dao.MobileDictionaryDao;
import com.tianque.mobile.mobileDictionary.domain.MobileDictionary;
import com.tianque.mobile.mobileDictionary.domain.vo.MobileDictionaryVo;

@Repository("mobileDictionaryDao")
public class MobileDictionaryDaoImpl extends AbstractBaseDao implements
		MobileDictionaryDao {

	@Override
	public Integer countMobileDictionary(Integer type) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"mobileDictionary.countMobileDictionary",type);
	}

	@Override
	public List<MobileDictionary> findMobileDictionaryLists(MobileDictionary mobileDictionary) throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"mobileDictionary.findMobileDictionary",mobileDictionary);
	}

	@Override
	public void addMobileDictionary(MobileDictionary mobileDictionary)
			throws Exception {
		if (!StringUtil.isStringAvaliable(mobileDictionary.getCreateUser())) {
			mobileDictionary.setCreateUser("admin");
		}
		if (mobileDictionary.getCreateDate() == null) {
			mobileDictionary.setCreateDate(new Date());
		}

		getSqlMapClientTemplate().insert(
				"mobileDictionary.addMobileDictionary", mobileDictionary);
	}

	@Override
	public void updateMobileDictionary(MobileDictionary mobileDictionary)
			throws Exception {
		if (mobileDictionary != null
				&& !StringUtil.isStringAvaliable(mobileDictionary
						.getUpdateUser())) {
			mobileDictionary.setUpdateUser("admin");
		}
		if (mobileDictionary != null
				&& mobileDictionary.getUpdateDate() == null) {
			mobileDictionary.setUpdateDate(new Date());
		}

		getSqlMapClientTemplate().update(
				"mobileDictionary.updateMobileDictionary", mobileDictionary);
	}

	@Override
	public List<MobileDictionaryVo> findAllDictsForList() {
		return getSqlMapClientTemplate().queryForList(
				"mobileDictionary.findAllDictsForList");
	}
	
	@Override
	public List<MobileDictionaryVo> findIncrementDictsForList(Date renewDate) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("createDate", renewDate);
		return getSqlMapClientTemplate().queryForList(
				"mobileDictionary.findIncrementDictsForList",map);
	}
}
