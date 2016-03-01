package com.tianque.baseInfo.superiorVisit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.domain.vo.VisitTypeVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("superiorVisitDao")
public class SuperiorVisitDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<SuperiorVisit, SearchSuperiorVisitVo> implements
		SuperiorVisitDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<SuperiorVisit> findSuperiorVisitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String order, Long isEmphasis) {
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		SuperiorVisit superiorVisit = new SuperiorVisit();
		superiorVisit.setOrgInternalCode(orgInternalCode);
		superiorVisit.setIsEmphasis(isEmphasis);
		if (StringUtil.isStringAvaliable(sortField)) {
			superiorVisit.setOrder(order);
			superiorVisit.setSortField(sortField);
		}
		List list = getSqlMapClientTemplate().queryForList("superiorVisit.findSuperiorVisits",
				superiorVisit, (pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"superiorVisit.countSuperiorVisits", superiorVisit);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VisitTypeVo> findVisitTypeById(Long superiorVisitId) {
		return (List<VisitTypeVo>) getSqlMapClientTemplate().queryForList(
				"superiorVisit.getSimpleVisitTypeById", superiorVisitId);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("superiorVisit.updateEmphasiseById", map);
//		if (isEmphasis.equals(IsEmphasis.Emphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_ON));
//		}
//		if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_OUT));
//		}

	}

	@Override
	public void addSuperiorVisitType(Long superiorVisitId, Long visitType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("superiorVisitId", superiorVisitId);
		map.put("superiorVisitType", visitType);
		getSqlMapClientTemplate().insert("superiorVisit.addSuperiorVisitType", map);
	}

	@Override
	public void deleteVisitTypeBySuperiorVisitId(Long superiorVisitId) {
		getSqlMapClientTemplate().delete("superiorVisit.deleteVisitTypeById", superiorVisitId);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update("superiorVisit.updateDeathAndLogoutStateById", map);

//		activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.CANCEL_DEATH));

	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("superiorvisits", id);
	}

}
