package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.CommonPeopleDao;

@Repository("commonPeopleDao")
public class CommonPeopleDaoImpl extends AbstractBaseDao implements CommonPeopleDao {

	public List<People> searchCommonPopulationByName(String name, String orgInternalCode) {
		if (null == name) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name + "%");
		map.put("orgInternalCode", orgInternalCode + "%");
		return (List<People>) getSqlMapClientTemplate().queryForList(
				"commonPeople.searchCommonPopulationByName", map);
	}

	public PageInfo<People> searchCommonPopulationByName(String name, String orgInternalCode,
			Integer pageNum, Integer pageSize) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name + "%");
		map.put("orgInternalCode", orgInternalCode + "%");
		PageInfo<People> page = new PageInfo<People>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"commonPeople.countCommonPopulation", map);
		int pageCount = (countNum % pageSize == 0 ? countNum / pageSize : countNum / pageSize + 1);
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<People> list = getSqlMapClientTemplate().queryForList(
					"commonPeople.searchCommonPopulationByName", map, (pageNum - 1) * pageSize,
					pageSize);
			page.setResult(list);

		} else {
			page.setResult(new ArrayList<People>());
		}
		page.setPerPageSize(pageSize);
		page.setTotalRowSize(countNum);
		page.setCurrentPage(pageNum);
		return page;
	}

}
