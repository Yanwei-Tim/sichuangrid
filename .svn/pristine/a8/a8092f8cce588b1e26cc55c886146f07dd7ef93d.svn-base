package com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.controller;

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

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.domain.DangerousGoodsPractitionerTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/*
 * 数据管理-危险品从业人员控制类*
 */
@Namespace("/plugin/dataManage/dangerousGoodsPractitionerTempManage")
@Transactional
@Scope("request")
@Controller("dangerousGoodsPractitionerTempController")
public class DangerousGoodsPractitionerTempController extends
		AbstractDataManageController<DangerousGoodsPractitionerTemp, DangerousGoodsPractitioner> {
	@Autowired
	private DangerousGoodsPractitionerService dangerousGoodsPractitionerService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public DangerousGoodsPractitionerTemp getPopulation() {
		return population;
	}

	public void setPopulation(DangerousGoodsPractitionerTemp population) {
		this.population = population;
	}

	public DangerousGoodsPractitionerTemp getTemp() {
		return temp;
	}

	public void setTemp(DangerousGoodsPractitionerTemp temp) {
		this.temp = temp;
	}

	@Override
	public DangerousGoodsPractitioner getTargetById(Long id) {
		return dangerousGoodsPractitionerService.getSimpleDangerousGoodsPractitionerById(id);
	}

	@Override
	public List getCompareList(DangerousGoodsPractitionerTemp dangerousGoodsPractitionerTemp,
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(
				dangerousGoodsPractitionerTemp, dangerousGoodsPractitioner);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "危险从业类别");
		map.put("tempValue",
				dangerousGoodsPractitionerTemp.getDangerousWorkingType() == null ? "-1" : ""
						+ dangerousGoodsPractitionerTemp.getDangerousWorkingType().getId());
		map.put("realValue", dangerousGoodsPractitioner.getDangerousWorkingType() == null ? "-1"
				: "" + dangerousGoodsPractitioner.getDangerousWorkingType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "dangerousWorkingType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "从业资格证书");
		map.put("tempValue", "" + dangerousGoodsPractitionerTemp.getWorkingCertificate());
		map.put("realValue", "" + dangerousGoodsPractitioner.getWorkingCertificate());
		map.put("argType", "str");
		map.put("submitName", "workingCertificate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "从业资格证书号");
		map.put("tempValue", "" + dangerousGoodsPractitionerTemp.getWorkingCertificateNo());
		map.put("realValue", "" + dangerousGoodsPractitioner.getWorkingCertificateNo());
		map.put("argType", "str");
		map.put("submitName", "workingCertificateNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效开始日期");
		map.put("tempValue",
				"" + DateUtil.formateYMD(dangerousGoodsPractitionerTemp.getPeriodOfValidityStart()));
		map.put("realValue",
				"" + DateUtil.formateYMD(dangerousGoodsPractitioner.getPeriodOfValidityStart()));
		map.put("argType", "strDate");
		map.put("submitName", "periodOfValidityStart");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效结束日期 ");
		map.put("tempValue",
				"" + DateUtil.formateYMD(dangerousGoodsPractitionerTemp.getPeriodOfValidityEnd()));
		map.put("realValue",
				"" + DateUtil.formateYMD(dangerousGoodsPractitioner.getPeriodOfValidityEnd()));
		map.put("argType", "strDate");
		map.put("submitName", "periodOfValidityEnd");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", dangerousGoodsPractitionerTemp.getAttentionExtent() == null ? "-1"
				: "" + dangerousGoodsPractitionerTemp.getAttentionExtent().getId());
		map.put("realValue", dangerousGoodsPractitioner.getAttentionExtent() == null ? "-1" : ""
				+ dangerousGoodsPractitioner.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "企业法人代表");
		map.put("tempValue", "" + dangerousGoodsPractitionerTemp.getLegalPerson());
		map.put("realValue", "" + dangerousGoodsPractitioner.getLegalPerson());
		map.put("argType", "str");
		map.put("submitName", "legalPerson");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "企业法人联系手机");
		map.put("tempValue", "" + dangerousGoodsPractitionerTemp.getLegalPersonMobileNumber());
		map.put("realValue", "" + dangerousGoodsPractitioner.getLegalPersonMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "legalPersonMobileNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "企业法人联系电话");
		map.put("tempValue", "" + dangerousGoodsPractitionerTemp.getLegalPersonTelephone());
		map.put("realValue", "" + dangerousGoodsPractitioner.getLegalPersonTelephone());
		map.put("argType", "str");
		map.put("submitName", "legalPersonTelephone");
		compareList.add(map);

		return compareList;
	}

}
