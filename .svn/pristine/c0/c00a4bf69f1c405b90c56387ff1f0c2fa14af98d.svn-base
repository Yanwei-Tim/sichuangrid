package com.tianque.incident.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.incident.dao.IncidentDealPlanDao;
import com.tianque.incident.domain.IncidentDealPlan;
import com.tianque.incident.domain.IncidentDealTeam;
import com.tianque.incident.domain.IncidentPlanStep;
import com.tianque.incident.domain.IncidentType;

@Service("incidentDealPlanDao")
public class IncidentDealPlanDaoImpl extends AbstractBaseDao implements IncidentDealPlanDao {

	@Override
	public IncidentDealPlan findIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		return (IncidentDealPlan) getSqlMapClientTemplate().queryForObject(
				"incidentDealPlan.findIncidentDealPlan", incidentDealPlan);
	}

	@Override
	public IncidentDealTeam findIncidentDealTeams(IncidentDealTeam incidentDealTeam) {
		return (IncidentDealTeam) getSqlMapClientTemplate().queryForObject(
				"incidentDealPlan.findIncidentDealTeams", incidentDealTeam);
	}

	@Override
	public PageInfo<IncidentDealTeam> findIncidentDealTeamsList(IncidentDealTeam incidentDealTeam,
			Integer pageNum, Integer pageSize) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"incidentDealPlan.countIncidentDealTeams", incidentDealTeam);
		pageNum = getPageNum(pageNum, pageSize, count);
		List<IncidentDealTeam> idtList = (List<IncidentDealTeam>) getSqlMapClientTemplate()
				.queryForList("incidentDealPlan.findIncidentDealTeams", incidentDealTeam);
		return getPagInfoOfIncidentDealPlan(pageNum, pageSize, count, idtList);
	}

	private PageInfo<IncidentDealTeam> getPagInfoOfIncidentDealPlan(Integer pageNum,
			Integer pageSize, Integer count, List<IncidentDealTeam> list) {
		PageInfo<IncidentDealTeam> pageInfo = new PageInfo<IncidentDealTeam>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(count);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<IncidentPlanStep> findIncidentPlanSteps(IncidentPlanStep incidentPlanStep,
			Integer pageNum, Integer pageSize) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"incidentDealPlan.countIncidentPlanStep", incidentPlanStep);
		pageNum = getPageNum(pageNum, pageSize, count);

		List<IncidentPlanStep> idtList = (List<IncidentPlanStep>) getSqlMapClientTemplate()
				.queryForList("incidentDealPlan.findIncidentPlanStep", incidentPlanStep);
		return getPagInfoOfIncidentPlanStep(pageNum, pageSize, count, idtList);
	}

	private PageInfo<IncidentPlanStep> getPagInfoOfIncidentPlanStep(Integer pageNum,
			Integer pageSize, Integer count, List<IncidentPlanStep> list) {
		PageInfo<IncidentPlanStep> pageInfo = new PageInfo<IncidentPlanStep>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(count);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void addIncidentDealPlan(IncidentDealPlan incidentDealPlan) {

		getSqlMapClientTemplate().insert("incidentDealPlan.addIncidentDealPlan", incidentDealPlan);
	}

	@Override
	public void addIncidentDealTeam(IncidentDealTeam incidentDealTeam) {
		getSqlMapClientTemplate().insert("incidentDealPlan.addIncidentDealTeams", incidentDealTeam);
	}

	@Override
	public void addIncidentPlanSteps(IncidentPlanStep incidentPlanStep) {
		Long id = (Long) getSqlMapClientTemplate().insert("incidentDealPlan.addIncidentPlanStep",
				incidentPlanStep);
	}

	@Override
	public void deleteIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		getSqlMapClientTemplate().delete("incidentDealPlan.deleteIncidentDealPlan",
				incidentDealPlan);
	}

	@Override
	public void deleteIncidentDealTeams(Map<String, Object> idtMap) {
		getSqlMapClientTemplate().delete("incidentDealPlan.deleteIncidentDealTeams", idtMap);
	}

	@Override
	public void deleteIncidentPlanStep(Map<String, Object> idtMap) {
		getSqlMapClientTemplate().delete("incidentDealPlan.deleteIncidentPlanStep", idtMap);
	}

	@Override
	public IncidentDealPlan updateIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		getSqlMapClientTemplate().update("incidentDealPlan.updateIncidentDealPlan",
				incidentDealPlan);
		return findIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public IncidentDealPlan updateIncidentDealPlanTow(IncidentDealPlan incidentDealPlan) {
		getSqlMapClientTemplate().update("incidentDealPlan.updateIncidentDealPlanTow",
				incidentDealPlan);
		return findIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public IncidentDealTeam updateIncidentDealTeam(IncidentDealTeam incidentDealTeam) {
		getSqlMapClientTemplate().update("incidentDealPlan.updateIncidentDealTeam",
				incidentDealTeam);
		return findIncidentDealTeams(incidentDealTeam);
	}

	@Override
	public IncidentPlanStep updateIncidentPlanStep(IncidentPlanStep incidentPlanStep) {
		getSqlMapClientTemplate().update("incidentDealPlan.updateIncidentPlanStep",
				incidentPlanStep);
		return findIncidentPlanStep(incidentPlanStep);
	}

	private Integer getPageNum(Integer pageNum, Integer pageSize, Integer count) {
		int pageCount = 0;
		if ((count % pageSize) == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	@Override
	public List<IncidentType> getIncidentTypeTreeListByPropertyDictId(Long propertyDictId) {
		return (List<IncidentType>) getSqlMapClientTemplate().queryForList(
				"incidentDealPlan.getIncidentTypeTreeListByPropertyDictId", propertyDictId);
	}

	@Override
	public IncidentPlanStep findIncidentPlanStep(IncidentPlanStep incidentPlanStep) {

		return (IncidentPlanStep) getSqlMapClientTemplate().queryForObject(
				"incidentDealPlan.findIncidentPlanStep", incidentPlanStep);
	}

	@Override
	public IncidentPlanStep findMaxSeqIndexIncidentPlanStep(IncidentPlanStep incidentDealPlan) {
		IncidentPlanStep incidentPlanStep2 = (IncidentPlanStep) getSqlMapClientTemplate()
				.queryForObject("incidentDealPlan.findMaxSeqIndexIncidentPlanStep",
						incidentDealPlan);
		if (incidentPlanStep2 == null) {
			incidentPlanStep2 = new IncidentPlanStep();
			incidentPlanStep2.setIncidentDealPlan(incidentDealPlan.getIncidentDealPlan());
			incidentPlanStep2.setSeqIndex(0);
		}
		return incidentPlanStep2;
	}

	@Override
	public void updateIncidentPlanStepSeqIndex(String seqIndexIdListStr) {
		String idArray[] = seqIndexIdListStr.split(",");
		IncidentPlanStep incidentDealPlan = new IncidentPlanStep();
		int arrLen = idArray.length;
		for (int i = 0; i < arrLen; i++) {
			incidentDealPlan.setId(Long.valueOf(idArray[i]));
			incidentDealPlan.setSeqIndex(arrLen - i);
			getSqlMapClientTemplate().update("incidentDealPlan.updateIncidentPlanStepSeqIndex",
					incidentDealPlan);
		}

	}

	@Override
	public List<IncidentDealTeam> findIncidentDealTeamsListByIncidentDealPlanId(
			IncidentDealTeam incidentDealTeam) {
		List<IncidentDealTeam> dealTeamList = (List<IncidentDealTeam>) getSqlMapClientTemplate()
				.queryForList("incidentDealPlan.findIncidentDealTeams", incidentDealTeam);
		return dealTeamList;
	}

	@Override
	public List<IncidentPlanStep> findIncidentPlanStepsByIncidentDealPlanId(
			IncidentPlanStep incidentPlanStep) {
		List<IncidentPlanStep> planStepList = (List<IncidentPlanStep>) getSqlMapClientTemplate()
				.queryForList("incidentDealPlan.findIncidentPlanStep", incidentPlanStep);
		return planStepList;
	}

}
