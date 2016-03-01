package com.tianque.plugin.dataManage.population.floatingPopulationTemp.controller;

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

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.floatingPopulationTemp.domain.FloatingPopulationTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 流动人员控制类
 */
@Namespace("/plugin/dataManage/floatingPopulationTempManage")
@Transactional
@Scope("request")
@Controller("floatingPopulationTempController")
public class FloatingPopulationTempController extends
		AbstractDataManageController<FloatingPopulationTemp, FloatingPopulation> {
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	@Qualifier("actualPopulationDataManageService")
	private AbstractDataManageService actualPopulationDataManageService;

	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(actualPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public FloatingPopulationTemp getTemp() {
		return temp;
	}

	public void setTemp(FloatingPopulationTemp temp) {
		this.temp = temp;
	}

	public FloatingPopulationTemp getPopulation() {
		return population;
	}

	public void setPopulation(FloatingPopulationTemp population) {
		this.population = population;
	}

	@Override
	public FloatingPopulation getTargetById(Long id) {
		return floatingPopulationService.getFloatingPopulationById(id);
	}

	@Override
	public List getCompareList(FloatingPopulationTemp temp, FloatingPopulation target) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(temp, target);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "流入原因");
		map.put("tempValue", temp.getInflowingReason() == null ? "-1" : ""
				+ temp.getInflowingReason().getId());
		map.put("realValue", target.getInflowingReason() == null ? "-1" : ""
				+ target.getInflowingReason().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "inflowingReason.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "流入时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getInflowingDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getInflowingDate()));
		map.put("argType", "strDate");
		map.put("submitName", "inflowingDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "流入来源");
		map.put("tempValue",
				"" + temp.getInflowingProvince() == null ? "" : "" + temp.getInflowingProvince()
						+ temp.getInflowingCity() == null ? "" : "" + temp.getInflowingCity()
						+ temp.getInflowingDistrict() == null ? "" : ""
						+ temp.getInflowingDistrict());
		map.put("realValue",
				"" + target.getInflowingProvince() == null ? ""
						: "" + target.getInflowingProvince() + target.getInflowingCity() == null ? ""
								: "" + target.getInflowingCity() + target.getInflowingDistrict() == null ? ""
										: "" + target.getInflowingDistrict());
		map.put("argType", "strs");
		map.put("submitName", new String[] { "inflowingProvince", "inflowingCity",
				"inflowingDistrict" });
		map.put("inflowingProvince", "" + target.getInflowingProvince());
		map.put("inflowingProvinceTemp", "" + temp.getInflowingProvince());
		map.put("inflowingCity", "" + target.getInflowingCity());
		map.put("inflowingCityTemp", "" + temp.getInflowingCity());
		map.put("inflowingDistrict", "" + target.getInflowingDistrict());
		map.put("inflowingDistrictTemp", "" + temp.getInflowingDistrict());
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "登记证情况");
		map.put("tempValue", temp.getRegistrationType() == null ? "-1" : ""
				+ temp.getRegistrationType().getId());
		map.put("realValue", target.getRegistrationType() == null ? "-1" : ""
				+ target.getRegistrationType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "registrationType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "登记证编号");
		map.put("tempValue", "" + temp.getCertificateNumber());
		map.put("realValue", "" + target.getCertificateNumber());
		map.put("argType", "str");
		map.put("submitName", "certificateNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "登记日期");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getRegisterDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getRegisterDate()));
		map.put("argType", "strDate");
		map.put("submitName", "registerDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "预计到期日期");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getExpectedDatedue()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getExpectedDatedue()));
		map.put("argType", "strDate");
		map.put("submitName", "expectedDatedue");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "暂住处所");
		map.put("tempValue", temp.getStayLocationType() == null ? "-1" : ""
				+ temp.getStayLocationType().getId());
		map.put("realValue", target.getStayLocationType() == null ? "-1" : ""
				+ target.getStayLocationType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@STAY_LOCATION_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "stayLocationType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "经济来源");
		map.put("tempValue", temp.getEconomySource() == null ? "-1" : ""
				+ temp.getEconomySource().getId());
		map.put("realValue", target.getEconomySource() == null ? "-1" : ""
				+ target.getEconomySource().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ECONOMY_SOURCE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "economySource.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "已居住时限");
		map.put("tempValue", temp.getStayTimeLimit() == null ? "-1" : ""
				+ temp.getStayTimeLimit().getId());
		map.put("realValue", target.getStayTimeLimit() == null ? "-1" : ""
				+ target.getStayTimeLimit().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@STAY_TIME_LIMIT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "stayTimeLimit.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "预居住时限");
		map.put("tempValue", temp.getPreparedStayTimeLimit() == null ? "-1" : ""
				+ temp.getPreparedStayTimeLimit().getId());
		map.put("realValue", target.getPreparedStayTimeLimit() == null ? "-1" : ""
				+ target.getPreparedStayTimeLimit().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@PREPARED_STAY_TIME_LIMIT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "preparedStayTimeLimit.id");
		compareList.add(map);
		return compareList;
	}

}
