package com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.controller;

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

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.baseInfo.otherAttentionPersonnel.service.OtherAttentionPersonnelService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.domain.OtherAttentionPersonnelTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 其他人员控制类。
 */
@Namespace("/plugin/dataManage/otherAttentionPersonnelTempManage")
@Transactional
@Controller("otherAttentionPersonnelTempController")
@Scope("request")
public class OtherAttentionPersonnelTempController extends
		AbstractDataManageController<OtherAttentionPersonnelTemp, OtherAttentionPersonnel> {
	@Autowired
	private OtherAttentionPersonnelService otherAttentionPersonnelService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Override
	public List getCompareList(OtherAttentionPersonnelTemp temp, OtherAttentionPersonnel target) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(temp, target);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", temp.getAttentionExtent() == null ? "-1" : ""
				+ temp.getAttentionExtent().getId());
		map.put("realValue", target.getAttentionExtent() == null ? "-1" : ""
				+ target.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注原因");
		map.put("tempValue", "" + temp.getAttentionReason());
		map.put("realValue", "" + target.getAttentionReason());
		map.put("argType", "str");
		map.put("submitName", "controlActuality");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "工作情况");
		map.put("tempValue", "" + temp.getWorkCondition());
		map.put("realValue", "" + target.getWorkCondition());
		map.put("argType", "str");
		map.put("submitName", "controlActuality");
		compareList.add(map);

		return compareList;
	}

	@Override
	public OtherAttentionPersonnel getTargetById(Long id) {
		return otherAttentionPersonnelService.getSimpleOtherAttentionPersonnelById(id);
	}

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public OtherAttentionPersonnelTemp getTemp() {
		return temp;
	}

	public void setTemp(OtherAttentionPersonnelTemp temp) {
		this.temp = temp;
	}

	public OtherAttentionPersonnelTemp getPopulation() {
		return population;
	}

	public void setPopulation(OtherAttentionPersonnelTemp population) {
		this.population = population;
	}

}
