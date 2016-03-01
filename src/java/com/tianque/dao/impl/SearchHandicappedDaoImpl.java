package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.handicapped.dao.SearchHandicappedDao;
import com.tianque.core.base.AbstractBaseDao;

@Repository("searchHandicappedDao")
public class SearchHandicappedDaoImpl extends AbstractBaseDao implements SearchHandicappedDao {

	@Override
	public List findHandicappedNameAndPinyinAndOrgInternalCode(String name, String orgInternalCode) {

		return null;
	}

}
