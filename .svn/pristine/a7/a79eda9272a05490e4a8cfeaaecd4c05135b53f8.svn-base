package com.tianque.plugin.dataManage.population.unemployedPeopleTemp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.baseInfo.unemployedPeople.service.UnemployedPeopleService;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.unemployedPeopleTemp.domain.UnemployedPeopleTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.sysadmin.service.PropertyDictService;

/*
 * 数据管理-失业人员控制类*
 */
@Namespace("/plugin/dataManage/unemployedPeopleTempManage")
@Transactional
@Scope("request")
@Controller("unemployedPeopleTempController")
public class UnemployedPeopleTempController extends
		AbstractDataManageController<UnemployedPeopleTemp, UnemployedPeople> {
	@Autowired
	private UnemployedPeopleService unemployedPeopleService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	@Qualifier("unemployedPeopleTempService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "unemployedPeopleTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	private List<Long> trainingIntentionIds;// 操作这个关系表uPeopleTempHasTIntention
	private List<Long> employmentIntentionIds;// 操作这个关系表uPeopleTempHasEIntention

	/**
	 * 转换List<Long>为 List<PropertyDict>
	 * 
	 * @param ids
	 * @return List<PropertyDict>
	 */
	private List<PropertyDict> LongToPropertyDict(List<Long> ids) {
		List<PropertyDict> list = new ArrayList<PropertyDict>();
		for (Long id : ids) {
			list.add(propertyDictService.getPropertyDictById(id));
		}
		return list;
	}

	private void initPopulation() {
		if (population != null) {
			if (trainingIntentionIds != null && trainingIntentionIds.size() > 0) {
				population.setTrainingIntention(this.LongToPropertyDict(trainingIntentionIds));
			}
			if (employmentIntentionIds != null && employmentIntentionIds.size() > 0) {
				population.setEmploymentIntention(this.LongToPropertyDict(employmentIntentionIds));
			}
		}
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public UnemployedPeopleTemp getPopulation() {
		return population;
	}

	public void setPopulation(UnemployedPeopleTemp population) {
		this.population = population;
		initPopulation();
	}

	public UnemployedPeopleTemp getTemp() {
		return temp;
	}

	public void setTemp(UnemployedPeopleTemp temp) {
		this.temp = temp;
	}

	public List<Long> getTrainingIntentionIds() {
		return trainingIntentionIds;
	}

	public void setTrainingIntentionIds(List<Long> trainingIntentionIds) {
		this.trainingIntentionIds = trainingIntentionIds;
		initPopulation();
	}

	public List<Long> getEmploymentIntentionIds() {
		return employmentIntentionIds;
	}

	public void setEmploymentIntentionIds(List<Long> employmentIntentionIds) {
		this.employmentIntentionIds = employmentIntentionIds;
		initPopulation();
	}

	@Override
	public UnemployedPeople getTargetById(Long id) {
		return unemployedPeopleService.getUnemployedPeopleById(id);
	}

	@Override
	public List getCompareList(UnemployedPeopleTemp unemployedPeopleTemp,
			UnemployedPeople unemployedPeople) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(unemployedPeopleTemp,
				unemployedPeople);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "失业原因");
		map.put("tempValue", unemployedPeopleTemp.getUnemploymentReason() == null ? "-1" : ""
				+ unemployedPeopleTemp.getUnemploymentReason().getId());
		map.put("realValue", unemployedPeople.getUnemploymentReason() == null ? "-1" : ""
				+ unemployedPeople.getUnemploymentReason().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "unemploymentReason.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "失业时间");
		map.put("tempValue", "" + DateUtil.formateYMD(unemployedPeopleTemp.getUnemploymentDate()));
		map.put("realValue", "" + DateUtil.formateYMD(unemployedPeople.getUnemploymentDate()));
		map.put("argType", "strDate");
		map.put("submitName", "unemploymentDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "人员类型");
		map.put("tempValue", unemployedPeopleTemp.getUnemployedPeopleType() == null ? "-1" : ""
				+ unemployedPeopleTemp.getUnemployedPeopleType().getId());
		map.put("realValue", unemployedPeople.getUnemployedPeopleType() == null ? "-1" : ""
				+ unemployedPeople.getUnemployedPeopleType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "unemployedPeopleType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "失业/就业证号");
		map.put("tempValue", "" + unemployedPeopleTemp.getRegisterNumber());
		map.put("realValue", "" + unemployedPeople.getRegisterNumber());
		map.put("argType", "str");
		map.put("submitName", "registerNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "参加工作时间");
		map.put("tempValue", "" + DateUtil.formateYMD(unemployedPeopleTemp.getUpEnterWorkDate()));
		map.put("realValue", "" + DateUtil.formateYMD(unemployedPeople.getUpEnterWorkDate()));
		map.put("argType", "strDate");
		map.put("submitName", "upEnterWorkDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "原工作单位名称");
		map.put("tempValue", "" + unemployedPeopleTemp.getOriginalWorkUnit());
		map.put("realValue", "" + unemployedPeople.getOriginalWorkUnit());
		map.put("argType", "str");
		map.put("submitName", "originalWorkUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "原单位类型");
		map.put("tempValue", "" + unemployedPeopleTemp.getOriginalWorkUnitType());
		map.put("realValue", "" + unemployedPeople.getOriginalWorkUnitType());
		map.put("argType", "str");
		map.put("submitName", "originalWorkUnitType");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "职称");
		map.put("tempValue", "" + unemployedPeopleTemp.getTitle());
		map.put("realValue", "" + unemployedPeople.getTitle());
		map.put("argType", "str");
		map.put("submitName", "title");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "技术等级");
		map.put("tempValue", unemployedPeopleTemp.getTechnologyLevel() == null ? "-1" : ""
				+ unemployedPeopleTemp.getTechnologyLevel().getId());
		map.put("realValue", unemployedPeople.getTechnologyLevel() == null ? "-1" : ""
				+ unemployedPeople.getTechnologyLevel().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL");
		map.put("argType", "PropertyDict");
		map.put("submitName", "technologyLevel.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", unemployedPeopleTemp.getAttentionExtent() == null ? "-1" : ""
				+ unemployedPeopleTemp.getAttentionExtent().getId());
		map.put("realValue", unemployedPeople.getAttentionExtent() == null ? "-1" : ""
				+ unemployedPeople.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "技能特长");
		map.put("tempValue", "" + unemployedPeopleTemp.getSpecialtySkills());
		map.put("realValue", "" + unemployedPeople.getSpecialtySkills());
		map.put("argType", "str");
		map.put("submitName", "specialtySkills");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "参加过的培训项目及时间");
		map.put("tempValue", "" + unemployedPeopleTemp.getParticipatedPrograms());
		map.put("realValue", "" + unemployedPeople.getParticipatedPrograms());
		map.put("argType", "str");
		map.put("submitName", "participatedPrograms");
		compareList.add(map);

		return compareList;
	}

}
