package com.tianque.plugin.dataManage.population.unemployedPeopleTemp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDaoImp;
import com.tianque.plugin.dataManage.population.unemployedPeopleTemp.domain.UnemployedPeopleTemp;
import com.tianque.sysadmin.service.PropertyDictService;

@Repository("unemployedPeopleTempDao")
public class UnemployedPeopleTempDaoImpl extends AbstractDataManageDaoImp<UnemployedPeopleTemp> {

	@Autowired
	private PropertyDictService ropertyDictService;

	@Override
	public Long addTemp(UnemployedPeopleTemp populationTemp) {

		Long id = (Long) getSqlMapClientTemplate().insert(
				"unemployedPeopleTemp.addUnemployedPeopleTemp", populationTemp);
		addEmployment(populationTemp, id);
		addTraining(populationTemp, id);

		return id;
	}

	@Override
	public UnemployedPeopleTemp getTempById(Class t, Long id) {
		UnemployedPeopleTemp unemployedPeopleTemp = (UnemployedPeopleTemp) getSqlMapClientTemplate()
				.queryForObject("unemployedPeopleTemp.getUnemployedPeopleTempById", id);
		unemployedPeopleTemp
				.setEmploymentIntention(getEmploymentIntentionTempList(unemployedPeopleTemp.getId()));
		unemployedPeopleTemp.setTrainingIntention(getTrainingIntentionTempList(unemployedPeopleTemp
				.getId()));
		return unemployedPeopleTemp;
	}

	@Override
	public void deleteTempById(Class t, Long id) {
		getSqlMapClientTemplate().delete("unemployedPeopleTemp.deleteTraining", id);
		getSqlMapClientTemplate().delete("unemployedPeopleTemp.deleteEmployment", id);
		getSqlMapClientTemplate().delete("unemployedPeopleTemp.deleteUnemployedPeopleTempById", id);
	}

	@Override
	public void updateTempBusiness(UnemployedPeopleTemp temp) {

		regrantEmploymentIntentionTempIds(temp);
		regrantTrainingIntentionTempIds(temp);
		getSqlMapClientTemplate().update("unemployedPeopleTemp.updateUnemployedPeopleTempBusiness",
				temp);

	}

	private void addEmployment(UnemployedPeopleTemp populationTemp, Long id) {
		if (null != populationTemp.getEmploymentIntention()
				&& populationTemp.getEmploymentIntention().size() > 0) {
			Map<String, Object> map;
			for (PropertyDict dict : populationTemp.getEmploymentIntention()) {
				map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("employmentId", dict.getId());
				getSqlMapClientTemplate().insert("unemployedPeopleTemp.addEmployment", map);
			}

		}
	}

	private void addTraining(UnemployedPeopleTemp populationTemp, Long id) {
		if (null != populationTemp.getTrainingIntention()
				&& populationTemp.getTrainingIntention().size() > 0) {
			Map<String, Object> map;
			for (PropertyDict dict : populationTemp.getTrainingIntention()) {
				map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("trainingId", dict.getId());
				getSqlMapClientTemplate().insert("unemployedPeopleTemp.addTraining", map);
			}

		}
	}

	private List<PropertyDict> getEmploymentIntentionTempList(Long id) {
		List<Long> employmentIntentionIds = getEmploymentIntentionTempIdsByEmploymentId(id);
		List<PropertyDict> employmentIntention = new ArrayList<PropertyDict>();
		for (Long employmentIntentionId : employmentIntentionIds) {
			employmentIntention.add(ropertyDictService.getPropertyDictById(employmentIntentionId));
		}
		return employmentIntention;
	}

	private List<PropertyDict> getTrainingIntentionTempList(Long id) {
		List<Long> trainingIntentionIds = getTrainingIntentionTempIdsByEmploymentId(id);
		List<PropertyDict> trainingIntention = new ArrayList<PropertyDict>();
		for (Long trainingIntentionId : trainingIntentionIds) {
			trainingIntention.add(ropertyDictService.getPropertyDictById(trainingIntentionId));
		}
		return trainingIntention;
	}

	public List<Long> getEmploymentIntentionTempIdsByEmploymentId(Long employmentId) {
		return getSqlMapClientTemplate().queryForList(
				"unemployedPeopleTemp.getEmploymentIntentionTempIdsByEmploymentId", employmentId);
	}

	public List<Long> getTrainingIntentionTempIdsByEmploymentId(Long employmentId) {
		return getSqlMapClientTemplate().queryForList(
				"unemployedPeopleTemp.getTrainingIntentionTempIdsByEmploymentId", employmentId);
	}

	public void regrantEmploymentIntentionTempIds(UnemployedPeopleTemp temp) {

		getSqlMapClientTemplate().delete("unemployedPeopleTemp.deleteEmployment", temp.getId());
		addEmployment(temp, temp.getId());
	}

	public void regrantTrainingIntentionTempIds(UnemployedPeopleTemp temp) {
		getSqlMapClientTemplate().delete("unemployedPeopleTemp.deleteTraining", temp.getId());
		addTraining(temp, temp.getId());

	}
}
