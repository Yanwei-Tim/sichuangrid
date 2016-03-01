package com.tianque.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.SyncActualPopulationToBusinessPopulationService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Controller("populationSpecializedInfoController")
@Scope("prototype")
public class PopulationSpecializedInfoController extends BaseAction {

	private Map<String, Object> population;
	private Map<String, Map<String, Object>> populationTypeInfo;
	private String[] populationType;
	private String populationSpecializedType;
	private String[] staffTypeIds;
	private Long[] trainingIntentionIds;
	private Long[] employmentIntentionIds;
	private List<Long> visitTypes;
	private String idCardNo;
	private Long orgId;
	private String actualId;
	private ActualPopulation actualPopulation;
	// 添加残疾人业务信息时，用于预存“残疾类别”和“残疾等级”
	private Handicapped handicapped;

	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private SyncActualPopulationToBusinessPopulationService populationSpecializedInfoService;
	private static final String POPULATIONMARK = "_P_";

	public String proccessPopulationSpecializedInfo() throws Exception {
		population = converterPopulation();
		actualPopulation = populationSpecializedInfoService
				.proccessPopulationSpecializedInfo(populationType, population);
		return SUCCESS;
	}

	public String getPopulationSpecializedInfoByOrgIdAndIdCardNo()
			throws Exception {
		populationTypeInfo = new HashMap<String, Map<String, Object>>();
		Set<String> set = populationTypeInfo.keySet();
		populationType = new String[set.size()];
		set.toArray(populationType);

		population = new HashMap<String, Object>();
		for (String type : populationType) {
			population.putAll(populationTypeInfo.get(type));
		}
		return SUCCESS;
	}

	public String getAllPopulationSpecializedInfoByOrgIdAndIdCardNo()
			throws Exception {
		populationTypeInfo = populationSpecializedInfoService
				.getAllPopulationSpecializedInfoByOrgIdAndIdCardNo(orgId,
						idCardNo, populationSpecializedType);
		population = new HashMap<String, Object>();
		if (populationTypeInfo != null
				&& populationTypeInfo.keySet().size() != 0) {
			population
					.putAll(populationTypeInfo.get(populationSpecializedType));
		}
		if ("handicapped".equals(populationSpecializedType)) {
			handicapped = new Handicapped();
			handicapped
					.setDisabilityTypes(propertyDictService
							.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE));
			handicapped
					.setDisabilitys(propertyDictService
							.findPropertyDictByDomainName(PropertyTypes.DISABILITY_STATUS));
		}
		this.setMode(DialogMode.EDIT_MODE);
		return SUCCESS;
	}

	private Map<String, Object> converterPopulation() {
		if (population != null && population.size() > 0) {
			String key;
			Map<String, Object> populationTemp = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : population.entrySet()) {
				key = entry.getKey();
				if (key.contains(POPULATIONMARK)) {
					key = key.replace(POPULATIONMARK, ".");
					populationTemp.put(key, entry.getValue());
				} else {
					populationTemp.put(key, entry.getValue());
				}
			}
			return populationTemp;
		}
		return null;
	}

	public ActualPopulation getActualPopulation() {
		return actualPopulation;
	}

	public void setActualPopulation(ActualPopulation actualPopulation) {
		this.actualPopulation = actualPopulation;
	}

	public Map getPopulation() {
		return population;
	}

	public void setPopulation(Map population) {
		this.population = population;
	}

	public String getActualId() {
		return actualId;
	}

	public void setActualId(String actualId) {
		this.actualId = actualId;
	}

	public String[] getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String[] populationType) {
		this.populationType = populationType;
	}

	public Map<String, Map<String, Object>> getPopulationTypeInfo() {
		return populationTypeInfo;
	}

	public void setPopulationTypeInfo(
			Map<String, Map<String, Object>> populationTypeInfo) {
		this.populationTypeInfo = populationTypeInfo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String[] getStaffTypeIds() {
		return staffTypeIds;
	}

	public String getPopulationSpecializedType() {
		return populationSpecializedType;
	}

	public void setPopulationSpecializedType(String populationSpecializedType) {
		this.populationSpecializedType = populationSpecializedType;
	}

	public void setStaffTypeIds(String[] staffTypeIds) {
		this.staffTypeIds = staffTypeIds;
		if (null == population) {
			population = new HashMap<String, Object>();
		}
		if (null != staffTypeIds) {
			population.put("staffTypeIds", staffTypeIds);
		}
	}

	public Long[] getTrainingIntentionIds() {
		return trainingIntentionIds;
	}

	public void setTrainingIntentionIds(Long[] trainingIntentionIds) {
		this.trainingIntentionIds = trainingIntentionIds;
		if (null == population) {
			population = new HashMap<String, Object>();
		}
		if (null != trainingIntentionIds) {
			population.put("trainingIntentionIds", trainingIntentionIds);
		}
	}

	public Long[] getEmploymentIntentionIds() {
		return employmentIntentionIds;
	}

	public void setEmploymentIntentionIds(Long[] employmentIntentionIds) {
		this.employmentIntentionIds = employmentIntentionIds;
		if (null == population) {
			population = new HashMap<String, Object>();
		}
		if (null != employmentIntentionIds) {
			population.put("employmentIntentionIds", employmentIntentionIds);
		}
	}

	public List<Long> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<Long> visitTypes) {
		this.visitTypes = visitTypes;
		if (null == population) {
			population = new HashMap<String, Object>();
		}
		if (null != visitTypes) {
			population.put("visitTypes", visitTypes);
		}
	}

	public Handicapped getHandicapped() {
		return handicapped;
	}

	public void setHandicapped(Handicapped handicapped) {
		this.handicapped = handicapped;
	}

}
