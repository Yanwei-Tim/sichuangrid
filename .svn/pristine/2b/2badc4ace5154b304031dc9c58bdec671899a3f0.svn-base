package com.tianque.baseInfo.goodSamaritan.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("goodSamaritanDao")
public class GoodSamaritanDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<GoodSamaritan, GoodSamaritan> implements
		GoodSamaritanDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<GoodSamaritan> findGoodSamaritansForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		map.put("attentionPopulationType", BaseInfoTables.GOOD_SAMARITAN_KEY);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"goodSamaritan.countGoodSamaritans", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<GoodSamaritan> list = getSqlMapClientTemplate().queryForList(
				"goodSamaritan.findGoodSamaritans", map, (page - 1) * rows,
				rows);
		PageInfo<GoodSamaritan> pageInfo = new PageInfo<GoodSamaritan>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	// @Override
	// public void updateDeathAndLogoutStateById(Long id, boolean death,
	// Long logoutState) {
	// Map map = new HashMap();
	// map.put("id", id);
	// map.put("isEmphasis", logoutState);
	// map.put("death", death);
	// getSqlMapClientTemplate().update(
	// "goodSamaritan.updateDeathAndLogoutStateById", map);
	// // activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
	// // OperateMode.CANCEL_DEATH));
	//
	// }
	//
	// private boolean validateObjId(Long id) {
	// if (null == id || id < 0L) {
	// return false;
	// }
	// return true;
	// }
	//
	// @Override
	// public GoodSamaritan updateGoodSamaritanEmphasis(GoodSamaritan
	// goodSamaritan) {
	// if (!validateObjId(goodSamaritan.getId())) {
	// throw new BusinessValidationException("更新其他人员关注时，id不合法，不能更新");
	// }
	//
	// if (getSqlMapClientTemplate().update(
	// "goodSamaritan.updateGoodSamaritanEmphasis", goodSamaritan) > 0) {
	// goodSamaritan = get(goodSamaritan.getId());
	// return goodSamaritan;
	// }
	// return null;
	// }
}
