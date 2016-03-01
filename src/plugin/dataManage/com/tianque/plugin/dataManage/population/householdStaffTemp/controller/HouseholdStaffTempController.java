package com.tianque.plugin.dataManage.population.householdStaffTemp.controller;

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

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.householdStaffTemp.domain.HouseholdStaffTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 -户籍人口控制类
 */
@Namespace("/plugin/dataManage/householdStaffTempManage")
@Transactional
@Scope("request")
@Controller("householdStaffTempController")
public class HouseholdStaffTempController extends
		AbstractDataManageController<HouseholdStaffTemp, HouseholdStaff> {

	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	@Qualifier("actualPopulationDataManageService")
	private AbstractDataManageService actualPopulationDataManageService;

	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(actualPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public HouseholdStaffTemp getTemp() {
		return temp;
	}

	public void setTemp(HouseholdStaffTemp temp) {
		this.temp = temp;
	}

	public HouseholdStaffTemp getPopulation() {
		return population;
	}

	public void setPopulation(HouseholdStaffTemp population) {
		this.population = population;
	}

	@Override
	public HouseholdStaff getTargetById(Long id) {
		return householdStaffService.getHouseholdStaffById(id);
	}

	@Override
	public List getCompareList(HouseholdStaffTemp temp, HouseholdStaff target) {

		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(temp,
				target);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "户口号 ");
		map.put("tempValue", "" + temp.getAccountNumber());
		map.put("realValue", "" + target.getAccountNumber());
		map.put("argType", "str");
		map.put("submitName", "accountNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "与户主关系");
		map.put("tempValue", temp.getRelationShipWithHead() == null ? "-1" : ""
				+ temp.getRelationShipWithHead().getId());
		map.put("realValue", target.getRelationShipWithHead() == null ? "-1"
				: "" + target.getRelationShipWithHead().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD");
		map.put("argType", "PropertyDict");
		map.put("submitName", "relationShipWithHead.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "人户状态");
		map.put("tempValue", temp.getResidentStatus() == null ? "-1" : ""
				+ temp.getResidentStatus().getId());
		map.put("realValue", target.getResidentStatus() == null ? "-1" : ""
				+ target.getResidentStatus().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "residentStatus.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "户口类别");
		map.put("tempValue", temp.getResidenceType() == null ? "-1" : ""
				+ temp.getResidenceType().getId());
		map.put("realValue", target.getResidenceType() == null ? "-1" : ""
				+ target.getResidenceType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "residenceType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "住宅电话");
		map.put("tempValue", "" + temp.getHomePhone());
		map.put("realValue", "" + target.getHomePhone());
		map.put("argType", "str");
		map.put("submitName", "homePhone");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否外出");
		map.put("tempValue", "" + temp.getOutGone());
		map.put("realValue", "" + target.getOutGone());
		map.put("argType", "boolean");
		map.put("submitName", "outGone");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "外出原因");
		map.put("tempValue", temp.getOutReasons() == null ? "-1" : ""
				+ temp.getOutReasons().getId());
		map.put("realValue", target.getOutReasons() == null ? "-1" : ""
				+ target.getOutReasons().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@OUT_REASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "outReasons.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "外出时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getReasonsDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getReasonsDate()));
		map.put("argType", "strDate");
		map.put("submitName", "reasonsDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "外出去向");
		map.put("tempValue",
				""
						+ (temp.getOutProvince() == null ? "" : ""
								+ temp.getOutProvince())
						+ (temp.getOutCity() == null ? "" : ""
								+ temp.getOutCity())
						+ (temp.getOutDistrict() == null ? "" : ""
								+ temp.getOutDistrict()));
		map.put("realValue",
				""
						+ (target.getOutProvince() == null ? "" : ""
								+ target.getOutProvince())
						+ (target.getOutCity() == null ? "" : ""
								+ target.getOutCity())
						+ (target.getOutDistrict() == null ? "" : ""
								+ target.getOutDistrict()));
		map.put("argType", "strs");
		map.put("submitName", new String[] { "outProvince", "outCity",
				"outDistrict" });
		map.put("outProvince", "" + target.getOutProvince());
		map.put("outProvinceTemp", "" + temp.getOutProvince());
		map.put("outCity", "" + target.getOutCity());
		map.put("outCityTemp", "" + temp.getOutCity());
		map.put("outDistrict", "" + target.getOutDistrict());
		map.put("outDistrictTemp", "" + temp.getOutDistrict());
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "外出详址");
		map.put("tempValue", "" + temp.getGoOutDetailedAddress());
		map.put("realValue", "" + target.getGoOutDetailedAddress());
		map.put("argType", "str");
		map.put("submitName", "goOutDetailedAddress");
		compareList.add(map);

		return compareList;
	}

}
