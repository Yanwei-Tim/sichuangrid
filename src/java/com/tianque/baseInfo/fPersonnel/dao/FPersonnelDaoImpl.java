package com.tianque.baseInfo.fPersonnel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("fPersonnelDao")
public class FPersonnelDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<FPersonnel, FPersonnel> implements
		FPersonnelDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<FPersonnel> findFPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		map.put("attentionPopulationType", BaseInfoTables.FPERSONNEL_KEY);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fPersonnel.countFPersonnels", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<FPersonnel> list = getSqlMapClientTemplate().queryForList(
				"fPersonnel.findFPersonnels", map, (page - 1) * rows, rows);
		PageInfo<FPersonnel> pageInfo = new PageInfo<FPersonnel>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"fPersonnel.updateDeathAndLogoutStateById", map);
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	private boolean validateObjId(Long id) {
		if (null == id || id < 0L) {
			return false;
		}
		return true;
	}

	@Override
	public FPersonnel updateFPersonnelEmphasis(FPersonnel FPersonnel) {
		if (!validateObjId(FPersonnel.getId())) {
			throw new BusinessValidationException("更新其他人员关注时，id不合法，不能更新");
		}

		if (getSqlMapClientTemplate().update(
				"fPersonnel.updateFPersonnelEmphasis", FPersonnel) > 0) {
			FPersonnel = get(FPersonnel.getId());
			return FPersonnel;
		}
		return null;
	}

}
