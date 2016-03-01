package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ExamineScroseStanalDao;

@Repository("examineScroseStanalDao")
public class ExamineScroseStanalDaoImpl extends AbstractBaseDao implements ExamineScroseStanalDao {

	@Override
	public int countAll(List<Long> orgIdList, String year) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		Map query = new HashMap();
		query.put("orgIdList", orgIdList);
		query.put("year", year);
		return (Integer) getSqlMapClientTemplate().queryForObject("examineScroseStanal.countAll",
				query);
	}

	@Override
	public int examineScroseStanal(List<Long> orgIdList, String year, Integer beginScrose, Integer endScrose) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		Map query = new HashMap();
		query.put("orgIdList", orgIdList);
		query.put("year", year);
		if (beginScrose != null)
			query.put("beginScrose", beginScrose);
		if (endScrose != null)
			query.put("endScrose", endScrose);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"examineScroseStanal.examineScroseStanal", query);
	}

}
