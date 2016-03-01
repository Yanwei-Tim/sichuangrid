package com.tianque.baseInfo.aidNeedPopulation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("aidNeedPopulationDao")
public class AidNeedPopulationDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<AidNeedPopulation, SearchAidNeedPopulationVo> implements
		AidNeedPopulationDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<AidNeedPopulation> findAidNeedPopulationForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String order, Long isEmphasis) {
		AidNeedPopulation aidNeedPopulation = new AidNeedPopulation();
		aidNeedPopulation.setOrgInternalCode(orgInternalCode);
		aidNeedPopulation.setSortField(sortField);
		aidNeedPopulation.setOrder(order);
		aidNeedPopulation.setIsEmphasis(isEmphasis);
		PageInfo<AidNeedPopulation> pageInfo = findAidNeedPopulationForPageByObject(
				aidNeedPopulation, pageNum, pageSize);
		return pageInfo;
	}

	private PageInfo<AidNeedPopulation> findAidNeedPopulationForPageByObject(
			AidNeedPopulation aidNeedPopulation, Integer pageNum, Integer pageSize) {
		Integer countAidNeedPopulation = 0;
		countAidNeedPopulation = (Integer) getSqlMapClientTemplate().queryForObject(
				"aidNeedPopulation.countAidNeedPopulation", aidNeedPopulation);
		int pageCount = 0;
		if ((countAidNeedPopulation % pageSize) == 0) {
			pageCount = countAidNeedPopulation / pageSize;
		} else {
			pageCount = countAidNeedPopulation / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<AidNeedPopulation> list = getSqlMapClientTemplate().queryForList(
				"aidNeedPopulation.findAidNeedPopulation", aidNeedPopulation,
				(pageNum - 1) * pageSize, pageSize);
		PageInfo<AidNeedPopulation> pageInfo = new PageInfo<AidNeedPopulation>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countAidNeedPopulation);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public AidNeedPopulation getAidNeedPopulationByIdCardNoAndOrganizationId(String idCardNo15,
			String idCardNo18, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		AidNeedPopulation aidNeedPopulation = (AidNeedPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"aidNeedPopulation.getAidNeedPopulationByIdCardNoAndOrganizationId", map);
		return aidNeedPopulation;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		getSqlMapClientTemplate().update("aidNeedPopulation.updateEmphasiseById", map);
//		if (isEmphasis.equals(IsEmphasis.Emphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_ON));
//		}
//		if (isEmphasis.equals(IsEmphasis.IsNotEmphasis)) {
//			activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.LOG_OUT));
//		}
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, boolean death, Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update("aidNeedPopulation.updateDeathAndLogoutStateById", map);

//		activeMQMessageSender.send(new BusinesPopulationMsg(get(id), OperateMode.CANCEL_DEATH));

	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("aidneedpopulation", id);
	}

}
