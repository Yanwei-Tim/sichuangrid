package com.tianque.sms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.dao.SmstrashDao;
import com.tianque.sms.domain.Smstrash;
import com.tianque.sms.domain.vo.SearchSmstrashVo;

/**
 * 垃圾短信箱:数据操作层
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
@Repository("smstrashDao")
public class SmstrashDaoImpl extends AbstractBaseDao implements SmstrashDao {

	@Override
	public PageInfo<Smstrash> findSmstrashsBySearchVo(
			SearchSmstrashVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {

		Map<String, Object> map = createMap(searchVo);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smstrash.countSmstrashsForPage", map);
		map.put("sortField", sortField);
		map.put("order", order);
		List<Smstrash> resultList = getSqlMapClientTemplate().queryForList(
				"smstrash.findSmstrashsForPage", map, (pageNum - 1) * pageSize,
				pageSize);
		return new PageInfo<Smstrash>(pageNum, pageSize, countNum, resultList);
	}

	private Map<String, Object> createMap(SearchSmstrashVo searchVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchVo != null) {
			map.put("content", searchVo.getContent());
			map.put("fromType", searchVo.getFromType());
			map.put("maxTime", searchVo.getMaxTime());
			map.put("minTime", searchVo.getMinTime());
			map.put("mobile", searchVo.getMobile());
		}
		return map;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public Smstrash restoreSMS(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
