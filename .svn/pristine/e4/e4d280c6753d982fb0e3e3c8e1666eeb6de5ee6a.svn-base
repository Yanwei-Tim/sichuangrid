package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HelpPrecordDao;
import com.tianque.domain.HelpPrecord;

@Repository("helpPrecordDao")
public class HelpPrecordDaoImpl extends AbstractBaseDao implements HelpPrecordDao {

	@Override
	public HelpPrecord addHelpPrecord(HelpPrecord helpPrecord) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("helpPrecord.addHelpPrecord", helpPrecord);
		return getHelpPrecord(id);
	}

	@Override
	public PageInfo<HelpPrecord> findHelpPrecord(Long personnelId, int pageNum, int pageSize,
			String sortField, String order, String personnelType) {
		PageInfo<HelpPrecord> pageInfo = new PageInfo<HelpPrecord>();
		Map<String, Object> query = new HashMap<String, Object>();
		if (StringUtil.isStringAvaliable(sortField)) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		query.put("personnelId", personnelId);
		query.put("personnelType", personnelType);
		List list = getSqlMapClientTemplate().queryForList("helpPrecord.findHelpPrecord", query,
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"helpPrecord.countHelpPrecord", query);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public HelpPrecord getHelpPrecord(Long id) {
		return (HelpPrecord) getSqlMapClientTemplate().queryForObject(
				"helpPrecord.getHelpPrecordById", id);
	}

	@Override
	public HelpPrecord updateHelpPrecord(HelpPrecord helpPrecord) {
		getSqlMapClientTemplate().update("helpPrecord.updateHelpPrecord", helpPrecord);
		return getHelpPrecord(helpPrecord.getId());
	}

	@Override
	public void deleteHelpPrecord(Long personnelId, String personnelType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personnelId", personnelId);
		map.put("personnelType", personnelType);
		getSqlMapClientTemplate().delete("helpPrecord.deleteHelpPrecord", map);
	}

	@Override
	public void deleteHelpPrecordById(Long id) {
		getSqlMapClientTemplate().delete("helpPrecord.deleteHelpPrecordById", id);
	}

}
