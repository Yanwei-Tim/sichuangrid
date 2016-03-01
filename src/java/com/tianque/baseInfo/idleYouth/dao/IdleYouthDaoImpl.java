package com.tianque.baseInfo.idleYouth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("idleYouthDao")
public class IdleYouthDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<IdleYouth, SearchIdleYouthVo> implements
		IdleYouthDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<IdleYouth> findIdleYouthsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis) {
		IdleYouth idleYouth = new IdleYouth();
		idleYouth.setOrgInternalCode(orgInternalCode);
		idleYouth.setOrder(order);
		idleYouth.setIsEmphasis(isEmphasis);
		idleYouth.setSortField(sortField);
		return findIdleYouthsForPageByObject(idleYouth, pageNum, pageSize);
	}

	private PageInfo<IdleYouth> findIdleYouthsForPageByObject(
			IdleYouth idleYouth, Integer pageNum, Integer pageSize) {
		Integer countIdleYouth = (Integer) getSqlMapClientTemplate()
				.queryForObject("idleYouth.countIdleYouths", idleYouth);

		int pageCount = 0;
		if ((countIdleYouth % pageSize) == 0) {
			pageCount = countIdleYouth / pageSize;
		} else {
			pageCount = countIdleYouth / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<IdleYouth> list = getSqlMapClientTemplate().queryForList(
				"idleYouth.findIdleYouths", idleYouth,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<IdleYouth> pageInfo = new PageInfo<IdleYouth>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countIdleYouth);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public IdleYouth getIdleYouthByIdCardNoAndOrganizationId(String idCardNo15,
			String idCardNo18, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		return (IdleYouth) getSqlMapClientTemplate().queryForObject(
				"idleYouth.getIdleYouthByIdCardNoAndOrganizationId", map);
	}

	@Override
	public Long addStaffType(Long idleYouthId, Long propertyDictId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idleYouthId", idleYouthId);
		map.put("propertyDictId", propertyDictId);

		Long rows = (Long) getSqlMapClientTemplate().insert(
				"idleYouth.addStaffType", map);
		return rows;
	}

	@Override
	public void deleteStaffTypeByIdleYouthId(Long idleYouthId) {
		getSqlMapClientTemplate().delete(
				"idleYouth.deleteStaffTypeByIdleYouthId", idleYouthId);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("idleYouth.updateEmphasiseById", map);
		// if (isEmphasis.equals(IsEmphasis.Emphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.LOG_ON));
		// }
		// if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.LOG_OUT));
		// }

	}

	@Override
	public List<IdleYouth> findIdleYouthsByBIRTHDAY() {
		return (List<IdleYouth>) getSqlMapClientTemplate().queryForList(
				"idleYouth.findIdleYouthsByBIRTHDAY");
	}

	@Override
	public List<IdleYouth> findIdleYouthsByBirthdayForMark(int pageNum,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", pageNum * pageSize);
		map.put("endRow", (pageNum + 1) * pageSize);
		map.put("whenExpireDate", CustomDateUtil.dateBeforeNowDate(35));
		return (List<IdleYouth>) getSqlMapClientTemplate().queryForList(
				"idleYouth.findIdleYouthsByBirthdayForMark", map);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"idleYouth.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));
	}

	@Override
	public List<Long> getStaffTypeIdsByIdleYouthId(Long id) {
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"idleYouth.getStaffTypeIdsByIdleYouthId", id);
	}

	@Override
	public Integer countIdleYouthsByBirthdayForMark() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whenExpireDate", CustomDateUtil.dateBeforeNowDate(35));
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"idleYouth.countIdleYouthsByBirthdayForMark", map);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("idleyouths", id);
	}
}
