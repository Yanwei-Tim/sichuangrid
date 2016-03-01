package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HelpPersonnelDao;
import com.tianque.domain.HelpPersonnel;

@Repository("helpPersonnelDao")
public class HelpPersonnelDaoImpl extends AbstractBaseDao implements HelpPersonnelDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public HelpPersonnel addHelpPersonnel(HelpPersonnel helpPersonnel) {
		if (helpPersonnel.getPersonId() == 0) {
			String key = CacheKeyGenerator.generateCacheKeyFromString(HelpPersonnel.class, UUID
					.randomUUID().toString());
			cacheService.set(key, helpPersonnel);
			helpPersonnel.setHelpPersonnelUuid(key);
			return helpPersonnel;
		}
		Long id = (Long) getSqlMapClientTemplate().insert("helpPersonnel.addHelpPersonnel",
				helpPersonnel);
		return getHelpPersonnel(id);
	}

	@Override
	public void deleteHelpPersonnel(Long personnelId) {
		getSqlMapClientTemplate().delete("helpPersonnel.deleteHelpPersonnelById", personnelId);
	}

	@Override
	public PageInfo<HelpPersonnel> findHelpPersonnel(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType) {
		PageInfo<HelpPersonnel> pageInfo = new PageInfo<HelpPersonnel>();
		Map<String, Object> query = new HashMap<String, Object>();
		if (StringUtil.isStringAvaliable(sortField)) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		query.put("personType", personnelType);
		query.put("personId", personnelId);
		List list = getSqlMapClientTemplate().queryForList("helpPersonnel.findHelpPersonnel",
				query, (pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"helpPersonnel.countHelpPersonnel", query);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public HelpPersonnel updateHelpPersonnel(HelpPersonnel helpPersonnel) {
		getSqlMapClientTemplate().update("helpPersonnel.updateHelpPersonnel", helpPersonnel);
		return getHelpPersonnel(helpPersonnel.getId());
	}

	@Override
	public HelpPersonnel getHelpPersonnel(Long id) {
		return (HelpPersonnel) getSqlMapClientTemplate().queryForObject(
				"helpPersonnel.getHelpPersonnelById", id);
	}

	@Override
	public List<HelpPersonnel> findperHelpPersonnelForList(Long personnelId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", personnelId);
		map.put("personType", type);
		List<HelpPersonnel> perList = (List<HelpPersonnel>) getSqlMapClientTemplate().queryForList(
				"helpPersonnel.findHelpPersonnelForList", map);
		return perList;

	}

	@Override
	public void deleteHelpPersonnel(Long personnelId, String personnelType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", personnelId);
		map.put("personType", personnelType);
		getSqlMapClientTemplate().delete("helpPersonnel.deleteHelpPersonnel", map);
	}

	@Override
	public List<HelpPersonnel> searchPersonInCharegeForAutoComplete(Long personnelId,
			String personnelType, String tag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", personnelId);
		map.put("personType", personnelType);
		map.put("name", tag);
		List<HelpPersonnel> perList = (List<HelpPersonnel>) getSqlMapClientTemplate().queryForList(
				"helpPersonnel.searchHelpPersonnelForAutoComplete", map);
		return perList;
	}

}
