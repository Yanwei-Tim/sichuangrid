package com.tianque.baseInfo.unemployedPeople.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchUnemployedPeopleVo;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("unemployedPeopleDao")
public class UnemployedPeopleDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<UnemployedPeople, SearchUnemployedPeopleVo>
		implements UnemployedPeopleDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<UnemployedPeople> findUnemployedPeopleForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		UnemployedPeople unemployedPeople = new UnemployedPeople();
		unemployedPeople.setIsEmphasis(isEmphasis);
		unemployedPeople.setOrgInternalCode(orgInternalCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"unemployedPeople.countUnemployedpeople", unemployedPeople);
		if (isStrotFieldAvaliable(sidx)) {
			unemployedPeople.setSortField(sidx);
		}
		unemployedPeople.setOrder(sord);
		List<ElderlyPeople> list = getSqlMapClientTemplate().queryForList(
				"unemployedPeople.findUnemployedpeople", unemployedPeople,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<UnemployedPeople> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<UnemployedPeople> pageInfo = new PageInfo<UnemployedPeople>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public UnemployedPeople getFullUnemployedPeopleById(Long id) {
		UnemployedPeople people = (UnemployedPeople) getSqlMapClientTemplate()
				.queryForObject("unemployedPeople.getUnemployedPeopleById", id);
		return people;
	}

	@Override
	public UnemployedPeople getUnemployedPeopleByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		return (UnemployedPeople) getSqlMapClientTemplate()
				.queryForObject(
						"unemployedPeople.getUnemployedPeopleByIdCardNoAndOrganizationId",
						map);
	}

	@Override
	public PageInfo<UnemployedPeople> searchUnemployedPeoples(Integer pageNum,
			Integer pageSize, SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		if (searchUnemployedPeopleVo == null)
			return emptyUnemployedPeoplePage(pageSize);
		PageInfo<UnemployedPeople> pageInfo = new PageInfo<UnemployedPeople>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"unemployedPeople.countSearchUnemployedPeople",
				searchUnemployedPeopleVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<UnemployedPeople> list = getSqlMapClientTemplate()
					.queryForList("unemployedPeople.searchUnemployedPeople",
							searchUnemployedPeopleVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<UnemployedPeople>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<UnemployedPeople> searchAllUnemployedPeoples(
			SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		List<UnemployedPeople> list = getSqlMapClientTemplate().queryForList(
				"unemployedPeople.searchUnemployedPeople",
				searchUnemployedPeopleVo);
		return list;
	}

	private PageInfo<UnemployedPeople> emptyUnemployedPeoplePage(int pageSize) {
		PageInfo<UnemployedPeople> pageInfo = new PageInfo<UnemployedPeople>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnemployedPeople>());
		return pageInfo;
	}

	@Override
	public void addEmploymentIntention(Long unemployedPeopleId,
			Long propertyDictId) {
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("unemployedPeopleId", unemployedPeopleId);
		query.put("propertyDictId", propertyDictId);
		getSqlMapClientTemplate().insert(
				"unemployedPeople.addEmploymentIntention", query);

	}

	@Override
	public void addTrainingIntention(Long unemployedPeopleId,
			Long propertyDictId) {
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("unemployedPeopleId", unemployedPeopleId);
		query.put("propertyDictId", propertyDictId);
		getSqlMapClientTemplate().insert(
				"unemployedPeople.addTrainingIntention", query);

	}

	@Override
	public void deleteEmploymentIntentionByUnemployedPeopleId(
			Long unemployedPeopleId) {
		getSqlMapClientTemplate()
				.delete("unemployedPeople.deleteEmploymentIntentionByUnemployedPeopleId",
						unemployedPeopleId);

	}

	@Override
	public void deleteTrainingIntentionByUnemployedPeopleId(
			Long unemployedPeopleId) {
		getSqlMapClientTemplate().delete(
				"unemployedPeople.deleteTrainingIntentionByUnemployedPeopleId",
				unemployedPeopleId);

	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", logoutState);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"unemployedPeople.updateDeathAndLogoutStateById", map);

		// activeMQMessageSender.send(new BusinesPopulationMsg(get(id),
		// OperateMode.CANCEL_DEATH));

	}

	@Override
	public List<Long> getEmploymentIntentionIdsByEmploymentId(Long employmentId) {
		return getSqlMapClientTemplate().queryForList(
				"unemployedPeople.getEmploymentIntentionIdsByEmploymentId",
				employmentId);
	}

	@Override
	public List<Long> getTrainingIntentionIdsByEmploymentId(Long employmentId) {
		return getSqlMapClientTemplate().queryForList(
				"unemployedPeople.getTrainingIntentionIdsByEmploymentId",
				employmentId);
	}

	@Override
	public Integer getCount(SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		// TODO Auto-generated method stub
		if (null == searchUnemployedPeopleVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"unemployedPeople.countSearchUnemployedPeople",
				searchUnemployedPeopleVo);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("unemployedpeople", id);
	}

}
