package com.tianque.baseInfo.mentalPatient.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.domain.ServiceSupervisionMeasures;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("mentalPatientDao")
public class MentalPatientDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<MentalPatient, SearchMentalPatientVo> implements
		MentalPatientDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	public PageInfo<MentalPatient> findMentalPatientsForPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize, String sortField, String order,
			Long isEmphasis) {
		if (orgInternalCode == null || "".equals(orgInternalCode.trim())) {
			return emptyPage(pageSize);
		}
		MentalPatient mentalPatient = new MentalPatient();
		mentalPatient.setOrgInternalCode(orgInternalCode);
		mentalPatient.setIsEmphasis(isEmphasis);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"mentalPatient.countMentalPatientsForPage", mentalPatient);
		if (StringUtil.isStringAvaliable(sortField)) {
			mentalPatient.setSortField(sortField);
		}
		mentalPatient.setOrder(order);
		@SuppressWarnings("unchecked")
		List<MentalPatient> list = getSqlMapClientTemplate().queryForList(
				"mentalPatient.findMentalPatientsForPage", mentalPatient, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<MentalPatient> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List<MentalPatient> list) {
		PageInfo<MentalPatient> pageInfo = new PageInfo<MentalPatient>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<MentalPatient> emptyPage(int pageSize) {
		PageInfo<MentalPatient> pageInfo = new PageInfo<MentalPatient>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MentalPatient>());
		return pageInfo;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("mentalPatient.updateEmphasiseById", map);
//		if (isEmphasis.equals(IsEmphasis.Emphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_ON));
//		}
//		if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_OUT));
//		}
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update("mentalPatient.updateDeathAndLogoutStateById", map);
//		activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.CANCEL_DEATH));
	}

	@Override
	public PageInfo findServiceSupervisionMeasuresPatientForList(
			Integer pageNum, Integer pageSize, String sortField, String order,
			ServiceSupervisionMeasures serviceSupervisionMeasures) {
		if (serviceSupervisionMeasures == null || serviceSupervisionMeasures.getMentalPatient() == null || serviceSupervisionMeasures.getMentalPatient().getId() == null
				|| serviceSupervisionMeasures.getType() == null || "".equals(serviceSupervisionMeasures.getType())) {
			return emptyPage(pageSize);
		}
		serviceSupervisionMeasures.setType((serviceSupervisionMeasures.getType().toUpperCase()));
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"mentalPatient.findServiceSupervisionMeasuresPatientForListCount", serviceSupervisionMeasures);
		if (StringUtil.isStringAvaliable(sortField)) {
			serviceSupervisionMeasures.setSortField(sortField);
		}
		serviceSupervisionMeasures.setOrder(order);
		List<MentalPatient> list = getSqlMapClientTemplate().queryForList(
				"mentalPatient.findServiceSupervisionMeasuresPatientForList", serviceSupervisionMeasures, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public ServiceSupervisionMeasures getServiceSupervisionMeasuresById(Long id) {
		if (id == null) {
			return new ServiceSupervisionMeasures();
		}
		return (ServiceSupervisionMeasures) getSqlMapClientTemplate().queryForObject("mentalPatient.getServiceSupervisionMeasuresById", id);
	}

	@Override
	public ServiceSupervisionMeasures addServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures) {
		if (serviceSupervisionMeasures == null || serviceSupervisionMeasures.getMentalPatient() == null || serviceSupervisionMeasures.getMentalPatient().getId() == null
				|| serviceSupervisionMeasures.getType() == null || "".equals(serviceSupervisionMeasures.getType())) {
			return null;
		}
		serviceSupervisionMeasures.setType((serviceSupervisionMeasures.getType().toUpperCase()));
		Long id = (Long) getSqlMapClientTemplate().insert("mentalPatient.addServiceSupervisionMeasures", serviceSupervisionMeasures);
		return getServiceSupervisionMeasuresById(id);
	}

	@Override
	public ServiceSupervisionMeasures updateServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures) {
		getSqlMapClientTemplate().update("mentalPatient.updateServiceSupervisionMeasures", serviceSupervisionMeasures);
		return getServiceSupervisionMeasuresById(serviceSupervisionMeasures.getId());
	}

	@Override
	public void deleteServiceSupervisionMeasuresById(Long id) {
		if (id == null) {
			return;
		}
		getSqlMapClientTemplate().delete("mentalPatient.deleteServiceSupervisionMeasuresById", id);
	}

	@Override
	public void deleteServiceSupervisionMeasuresByIdAndType(Long id,
			String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id+"");
		map.put("type", type);
		getSqlMapClientTemplate().delete("mentalPatient.deleteServiceSupervisionMeasuresByIdAndType", map);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("mentalpatients", id);
	}

}