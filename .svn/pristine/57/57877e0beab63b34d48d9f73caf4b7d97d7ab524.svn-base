package com.tianque.plugin.weChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.RedEnvelopeDao;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelope;
import com.tianque.plugin.weChat.vo.RedEnvelopeVo;

@Repository("redEnvelopeDao")
public class RedEnvelopeDaoImpl extends AbstractBaseDao implements RedEnvelopeDao {

	@Override
	public RedEnvelope addRedEnvelope(RedEnvelope redEnvelope) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("redEnvelope.addRedEnvelope", redEnvelope);
		return getRedEnvelopeById(id);
	}

	@Override
	public RedEnvelope updateRedEnvelope(RedEnvelope redEnvelope) {
		getSqlMapClientTemplate().update("redEnvelope.updateRedEnvelope", redEnvelope);
		return getRedEnvelopeById(redEnvelope.getId());
	}

	@Override
	public RedEnvelope getRedEnvelopeById(Long id) {
		return (RedEnvelope) getSqlMapClientTemplate().queryForObject(
				"redEnvelope.getRedEnvelopeById", id);
	}

	@Override
	public void deleteRedEnvelopeById(Long id) {
		getSqlMapClientTemplate().delete("redEnvelope.deleteRedEnvelopeById", id);
	}

	@Override
	public PageInfo<RedEnvelopeVo> findRedEnvelopeByPage(RedEnvelopeVo redEnvelopeVo, int pageNum,
			int pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("redEnvelopeVo", redEnvelopeVo);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"redEnvelope.countRedEnvelopeVoList", map);
		List<RedEnvelopeVo> list = getSqlMapClientTemplate().queryForList(
				"redEnvelope.findRedEnvelopeVoList", map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<RedEnvelopeVo> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<RedEnvelopeVo> pageInfo = new PageInfo<RedEnvelopeVo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
