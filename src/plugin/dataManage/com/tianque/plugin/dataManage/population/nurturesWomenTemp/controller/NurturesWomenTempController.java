package com.tianque.plugin.dataManage.population.nurturesWomenTemp.controller;

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

import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.nurturesWomenTemp.domain.NurturesWomenTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/*
 * 数据管理-育龄妇女控制类*
 */
@Namespace("/plugin/dataManage/nurturesWomenTempManage")
@Transactional
@Scope("request")
@Controller("nurturesWomenTempController")
public class NurturesWomenTempController extends
		AbstractDataManageController<NurturesWomenTemp, NurturesWomen> {
	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public NurturesWomenTemp getPopulation() {
		return population;
	}

	public void setPopulation(NurturesWomenTemp population) {
		this.population = population;
	}

	public NurturesWomenTemp getTemp() {
		return temp;
	}

	public void setTemp(NurturesWomenTemp temp) {
		this.temp = temp;
	}

	@Override
	public NurturesWomen getTargetById(Long id) {
		return nurturesWomenService.getNurturesWomenById(id);
	}

	@Override
	public List getCompareList(NurturesWomenTemp nurturesWomenTemp,
			NurturesWomen nurturesWomen) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(
				nurturesWomenTemp, nurturesWomen);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "初婚时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getFirstMarriageTime()));
		map.put("realValue",
				"" + DateUtil.formateYMD(nurturesWomen.getFirstMarriageTime()));
		map.put("argType", "strDate");
		map.put("submitName", "firstMarriageTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "离婚时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getMarriageRegistrationTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getMarriageRegistrationTime()));
		map.put("argType", "strDate");
		map.put("submitName", "marriageRegistrationTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "办理时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getSingleChildCDIssueTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getSingleChildCDIssueTime()));
		map.put("argType", "strDate");
		map.put("submitName", "singleChildCDIssueTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "再婚时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getMarriageOrDivorceTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getMarriageOrDivorceTime()));
		map.put("argType", "strDate");
		map.put("submitName", "marriageOrDivorceTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "领证时间");
		map.put("tempValue",
				"" + DateUtil.formateYMD(nurturesWomenTemp.getLicenseTime()));
		map.put("realValue",
				"" + DateUtil.formateYMD(nurturesWomen.getLicenseTime()));
		map.put("argType", "strDate");
		map.put("submitName", "licenseTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "避孕起始时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getContraceptiveTime()));
		map.put("realValue",
				"" + DateUtil.formateYMD(nurturesWomen.getContraceptiveTime()));
		map.put("argType", "strDate");
		map.put("submitName", "contraceptiveTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "发放时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getMaternityCardIssueTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getMaternityCardIssueTime()));
		map.put("argType", "strDate");
		map.put("submitName", "maternityCardIssueTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效期至");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getMaternityCardValidityTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getMaternityCardValidityTime()));
		map.put("argType", "strDate");
		map.put("submitName", "maternityCardValidityTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "查验时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getMaternityCardCheckTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getMaternityCardCheckTime()));
		map.put("argType", "strDate");
		map.put("submitName", "maternityCardCheckTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue",
				nurturesWomenTemp.getAttentionExtent() == null ? "-1" : ""
						+ nurturesWomenTemp.getAttentionExtent().getId());
		map.put("realValue", nurturesWomen.getAttentionExtent() == null ? "-1"
				: "" + nurturesWomen.getAttentionExtent().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "独生子女证号");
		map.put("tempValue", "" + nurturesWomenTemp.getSingleChildCardno());
		map.put("realValue", "" + nurturesWomen.getSingleChildCardno());
		map.put("argType", "str");
		map.put("submitName", "singleChildCardno");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "发证单位");
		map.put("tempValue", "" + nurturesWomenTemp.getCertifiedUnit());
		map.put("realValue", "" + nurturesWomen.getCertifiedUnit());
		map.put("argType", "str");
		map.put("submitName", "certifiedUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "避孕方法");
		map.put("tempValue", "" + nurturesWomenTemp.getContraceptiveMethod());
		map.put("realValue", "" + nurturesWomen.getContraceptiveMethod());
		map.put("argType", "str");
		map.put("submitName", "contraceptiveMethod");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "发放单位");
		map.put("tempValue", "" + nurturesWomenTemp.getMaternityCardUnit());
		map.put("realValue", "" + nurturesWomen.getMaternityCardUnit());
		map.put("argType", "str");
		map.put("submitName", "maternityCardUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "证编号");
		map.put("tempValue", "" + nurturesWomenTemp.getMaternityCardNo());
		map.put("realValue", "" + nurturesWomen.getMaternityCardNo());
		map.put("argType", "str");
		map.put("submitName", "maternityCardNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "查验情况");
		map.put("tempValue", "" + nurturesWomenTemp.getMaternityCardRemark());
		map.put("realValue", "" + nurturesWomen.getMaternityCardRemark());
		map.put("argType", "str");
		map.put("submitName", "maternityCardRemark");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "夫妻独生子女情况");
		map.put("tempValue",
				nurturesWomenTemp.getOnechildOfCouple() == null ? "-1" : ""
						+ nurturesWomenTemp.getOnechildOfCouple().getId());
		map.put("realValue", nurturesWomen.getOnechildOfCouple() == null ? "-1"
				: "" + nurturesWomen.getOnechildOfCouple().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "onechildOfCouple.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "领证情况");
		map.put("tempValue",
				nurturesWomenTemp.getLicenseSituation() == null ? "-1" : ""
						+ nurturesWomenTemp.getLicenseSituation().getId());
		map.put("realValue", nurturesWomen.getLicenseSituation() == null ? "-1"
				: "" + nurturesWomen.getLicenseSituation().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "licenseSituation.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "生育服务证号");
		map.put("tempValue", "" + nurturesWomenTemp.getFertilityServicesNo());
		map.put("realValue", "" + nurturesWomen.getFertilityServicesNo());
		map.put("argType", "str");
		map.put("submitName", "fertilityServicesNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男孩数");
		map.put("tempValue", "" + nurturesWomenTemp.getBoyNumber());
		map.put("realValue", "" + nurturesWomen.getBoyNumber());
		map.put("argType", "str");
		map.put("submitName", "boyNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "女孩数");
		map.put("tempValue", "" + nurturesWomenTemp.getGirlNumber());
		map.put("realValue", "" + nurturesWomen.getGirlNumber());
		map.put("argType", "str");
		map.put("submitName", "girlNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方身份证号码");
		map.put("tempValue", "" + nurturesWomenTemp.getManIdcardNo());
		map.put("realValue", "" + nurturesWomen.getManIdcardNo());
		map.put("argType", "str");
		map.put("submitName", "manIdcardNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方姓名");
		map.put("tempValue", "" + nurturesWomenTemp.getManName());
		map.put("realValue", "" + nurturesWomen.getManName());
		map.put("argType", "str");
		map.put("submitName", "manName");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方婚姻状况");
		map.put("tempValue",
				nurturesWomenTemp.getManMaritalState() == null ? "-1" : ""
						+ nurturesWomenTemp.getManMaritalState().getId());
		map.put("realValue", nurturesWomen.getManMaritalState() == null ? "-1"
				: "" + nurturesWomen.getManMaritalState().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manMaritalState.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方初婚时间");
		map.put("tempValue",
				""
						+ DateUtil.formateYMD(nurturesWomenTemp
								.getManFirstMarriageTime()));
		map.put("realValue",
				""
						+ DateUtil.formateYMD(nurturesWomen
								.getManFirstMarriageTime()));
		map.put("argType", "strDate");
		map.put("submitName", "manFirstMarriageTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方联系手机");
		map.put("tempValue", "" + nurturesWomenTemp.getManMobileNumber());
		map.put("realValue", "" + nurturesWomen.getManMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "manMobileNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方固定电话");
		map.put("tempValue", "" + nurturesWomenTemp.getManTelephone());
		map.put("realValue", "" + nurturesWomen.getManTelephone());
		map.put("argType", "str");
		map.put("submitName", "manTelephone");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方出生日期");
		map.put("tempValue",
				"" + DateUtil.formateYMD(nurturesWomenTemp.getManBirthday()));
		map.put("realValue",
				"" + DateUtil.formateYMD(nurturesWomen.getManBirthday()));
		map.put("argType", "strDate");
		map.put("submitName", "manBirthday");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方民族");
		map.put("tempValue", nurturesWomenTemp.getManNation() == null ? "-1"
				: "" + nurturesWomenTemp.getManNation().getId());
		map.put("realValue", nurturesWomen.getManNation() == null ? "-1" : ""
				+ nurturesWomen.getManNation().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@NATION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manNation.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方政治面貌");
		map.put("tempValue",
				nurturesWomenTemp.getManPoliticalBackground() == null ? "-1"
						: ""
								+ nurturesWomenTemp.getManPoliticalBackground()
										.getId());
		map.put("realValue",
				nurturesWomen.getManPoliticalBackground() == null ? "-1" : ""
						+ nurturesWomen.getManPoliticalBackground().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manPoliticalBackground.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方文化程度");
		map.put("tempValue", nurturesWomenTemp.getManSchooling() == null ? "-1"
				: "" + nurturesWomenTemp.getManSchooling().getId());
		map.put("realValue", nurturesWomen.getManSchooling() == null ? "-1"
				: "" + nurturesWomen.getManSchooling().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@SCHOOLING");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manSchooling.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方职业");
		map.put("tempValue", nurturesWomenTemp.getManCareer() == null ? "-1"
				: "" + nurturesWomenTemp.getManCareer().getId());
		map.put("realValue", nurturesWomen.getManCareer() == null ? "-1" : ""
				+ nurturesWomen.getManCareer().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@CAREER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manCareer.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方常住地址类型");
		map.put("tempValue",
				nurturesWomenTemp.getManCurrentAddressType() == null ? "-1"
						: ""
								+ nurturesWomenTemp.getManCurrentAddressType()
										.getId());
		map.put("realValue",
				nurturesWomen.getManCurrentAddressType() == null ? "-1" : ""
						+ nurturesWomen.getManCurrentAddressType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "manCurrentAddressType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "小区");
		map.put("tempValue", "" + nurturesWomenTemp.getManCommunity());
		map.put("realValue", "" + nurturesWomen.getManCommunity());
		map.put("argType", "str");
		map.put("submitName", "manCommunity");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "幢");
		map.put("tempValue", "" + nurturesWomenTemp.getManBlock());
		map.put("realValue", "" + nurturesWomen.getManBlock());
		map.put("argType", "str");
		map.put("submitName", "manBlock");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单元");
		map.put("tempValue", "" + nurturesWomenTemp.getManUnit());
		map.put("realValue", "" + nurturesWomen.getManUnit());
		map.put("argType", "str");
		map.put("submitName", "manUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "室");
		map.put("tempValue", "" + nurturesWomenTemp.getManRoom());
		map.put("realValue", "" + nurturesWomen.getManRoom());
		map.put("argType", "str");
		map.put("submitName", "manRoom");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方工作单位或就读学校");
		map.put("tempValue", "" + nurturesWomenTemp.getManWorkUnit());
		map.put("realValue", "" + nurturesWomen.getManWorkUnit());
		map.put("argType", "str");
		map.put("submitName", "manWorkUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方户籍地");
		map.put("tempValue",
				""
						+ (nurturesWomenTemp.getManProvince() == null ? "" : ""
								+ nurturesWomenTemp.getManProvince())
						+ (nurturesWomenTemp.getManCity() == null ? "" : ""
								+ nurturesWomenTemp.getManCity())
						+ (nurturesWomenTemp.getManDistrict() == null ? "" : ""
								+ nurturesWomenTemp.getManDistrict()));
		map.put("realValue",
				""
						+ (nurturesWomen.getManProvince() == null ? "" : ""
								+ nurturesWomen.getManProvince())
						+ (nurturesWomen.getManCity() == null ? "" : ""
								+ nurturesWomen.getManCity())
						+ (nurturesWomen.getManDistrict() == null ? "" : ""
								+ nurturesWomen.getManDistrict()));
		map.put("argType", "strs");
		map.put("submitName", new String[] { "province", "city", "district" });
		map.put("province", "" + nurturesWomen.getManProvince());
		map.put("provinceTemp", "" + nurturesWomenTemp.getManProvince());
		map.put("city", "" + nurturesWomen.getManCity());
		map.put("cityTemp", "" + nurturesWomenTemp.getManCity());
		map.put("district", "" + nurturesWomen.getManDistrict());
		map.put("districtTemp", "" + nurturesWomenTemp.getManDistrict());
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "男方户籍地详址");
		map.put("tempValue", "" + nurturesWomenTemp.getManNativeplaceAddress());
		map.put("realValue", "" + nurturesWomen.getManNativeplaceAddress());
		map.put("argType", "str");
		map.put("submitName", "manNativeplaceAddress");
		compareList.add(map);

		return compareList;
	}

}
