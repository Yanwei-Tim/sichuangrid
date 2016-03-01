package com.tianque.plugin.dataManage.population.overseaPersonnelTemp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.domain.Organization;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.domain.OverseaPersonnelTemp;
import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.service.OverseaPersonnelTempService;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/plugin/dataManage/overseaPersonnelTempManage")
@Controller("overseaPersonnelTempController")
@Scope("request")
@Transactional
public class OverseaPersonnelTempController extends
		AbstractDataManageController<OverseaPersonnelTemp, OverseaPersonnel> {
	private Logger logger = Logger
			.getLogger(OverseaPersonnelTempController.class);

	private OverseaPersonnelTemp overseaPersonnel;
	private String ajaxUrl;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OverseaPersonnelTempService overseaPersonnelTempService;
	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	@Qualifier("overseaPersonnelTempService")
	private AbstractDataManageService actualPopulationDataManageService;

	@Resource(name = "overseaPersonnelTempService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(actualPopulationDataManageService);
	}

	/**
	 * 测试认领处修改上一步下一步页面跳转
	 */
	@Actions({
			@Action(value = "dispatch", results = {
					@Result(name = "view", location = "/template/dataManage/population/overseaPersonnelTempManage/overseaPersonnelViewTabDlg.ftl"),
					@Result(name = "update", location = "/template/dataManage/population/common/SpecialSubmitForOversea.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }),
					@Result(name = "search", location = "/template/dataManage/common/searchDlg.ftl") }),
			@Action(value = "viewCommonPopulation", results = { @Result(name = "view", location = "/template/dataManage/population/overseaPersonnelTempManage/viewMaintainOverseaPersonnelDlg.ftl") }) })
	public String dispatch() throws Exception {
		ajaxUrl = "hasDuplicate";
		if (null != id) {
			overseaPersonnel = overseaPersonnelTempService.getTempById(id);
			Organization org = ReflectionUtil
					.getTargetOrganization(overseaPersonnel);
			if (null != getTargetOrgId()) {
				org.setId(getTargetOrgId());
			}
			org.setOrgName(ControllerHelper.getOrganizationRelativeName(
					org.getId(), organizationDubboService));

		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return DialogMode.VIEW_MODE;
		}
		ajaxUrl = "hasDuplicate";
		if (mode.equals("search")) {
			return "search";
		}

		return mode;

	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	@Action(value = "maintainOverseaPersonnelPopulationInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"overseaPersonnel", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainOverseaPersonnelPopulationInfo() throws Exception {
		overseaPersonnel = overseaPersonnelTempService
				.updateTemp(overseaPersonnel);
		return SUCCESS;
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public OverseaPersonnelTemp getTemp() {
		return temp;
	}

	public void setTemp(OverseaPersonnelTemp temp) {
		this.temp = temp;
	}

	public OverseaPersonnelTemp getPopulation() {
		return population;
	}

	public void setPopulation(OverseaPersonnelTemp population) {
		this.population = population;
	}

	@Override
	public OverseaPersonnel getTargetById(Long id) {
		return overseaPersonnelService.getOverseaPersonnelById(id);
	}

	@Override
	public List getCompareList(OverseaPersonnelTemp temp,
			OverseaPersonnel target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "英文名");
		map.put("tempValue", temp.getEnglishName());
		map.put("realValue", target.getEnglishName());
		map.put("argType", "str");
		map.put("submitName", "englishName");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "姓名");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "性别");
		map.put("tempValue", "" + temp.getGender().getId());
		map.put("realValue", "" + target.getGender().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@GENDER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "gender.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "出生日期");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getBirthday()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getBirthday()));
		map.put("argType", "strDate");
		map.put("submitName", "birthday");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系手机");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "固定电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "国籍");
		map.put("tempValue", "" + temp.getNationality());
		map.put("realValue", "" + target.getNationality());
		map.put("argType", "str");
		map.put("submitName", "nationality");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "证件种类");
		map.put("tempValue", temp.getCertificateType() == null ? "-1" : ""
				+ temp.getCertificateType().getId());
		map.put("realValue", target.getCertificateType() == null ? "-1" : ""
				+ target.getCertificateType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "certificateType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "证件号码");
		map.put("tempValue", "" + temp.getCertificateNo());
		map.put("realValue", "" + target.getCertificateNo());
		map.put("argType", "str");
		map.put("submitName", "certificateNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "证件有效期");
		map.put("tempValue",
				"" + DateUtil.formateYMD(temp.getCertificateStrartDay())
						+ " 至 "
						+ DateUtil.formateYMD(temp.getCertificateEndDay()));
		map.put("realValue",
				DateUtil.formateYMD(target.getCertificateStrartDay()) + " 至 "
						+ DateUtil.formateYMD(target.getCertificateEndDay()));
		map.put("argType", "strs");
		map.put("submitName", new String[] { "certificateStrartDay",
				"certificateEndDay" });
		map.put("certificateStrartDay",
				"" + DateUtil.formateYMD(target.getCertificateStrartDay()));
		map.put("certificateStrartDayTemp",
				"" + DateUtil.formateYMD(temp.getCertificateStrartDay()));
		map.put("certificateEndDay",
				"" + DateUtil.formateYMD(target.getCertificateEndDay()));
		map.put("certificateEndDayTemp",
				"" + DateUtil.formateYMD(temp.getCertificateEndDay()));
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "有无住房");
		map.put("tempValue", "" + target.getIsHaveHouse());
		map.put("realValue", "" + target.getIsHaveHouse());
		map.put("argType", "boolean");
		map.put("submitName", "isHaveHouse");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "无房原因");
		map.put("tempValue", "" + target.getNoHouseReason());
		map.put("realValue", "" + target.getNoHouseReason());
		map.put("argType", "str");
		map.put("submitName", "noHouseReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "流入原因");
		map.put("tempValue", "" + temp.getInflowReason());
		map.put("realValue", "" + target.getInflowReason());
		map.put("argType", "str");
		map.put("submitName", "inflowReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "职业");
		map.put("tempValue", temp.getProfession() == null ? "-1" : ""
				+ temp.getProfession().getId());
		map.put("realValue", target.getProfession() == null ? "-1" : ""
				+ target.getProfession().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@PROFESSION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "profession.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "工作单位或就读学校");
		map.put("tempValue", "" + temp.getWorkUnit());
		map.put("realValue", "" + target.getWorkUnit());
		map.put("argType", "str");
		map.put("submitName", "workUnit");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "抵达时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getArrivalTime()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getArrivalTime()));
		map.put("argType", "strDate");
		map.put("submitName", "arrivalTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "离开时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getLeaveTime()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getLeaveTime()));
		map.put("argType", "strDate");
		map.put("submitName", "leaveTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "备注");
		map.put("tempValue", "" + temp.getRemark());
		map.put("realValue", "" + target.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		compareList.add(map);
		return compareList;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public OverseaPersonnelTemp getOverseaPersonnel() {
		return overseaPersonnel;
	}

	public void setOverseaPersonnel(OverseaPersonnelTemp overseaPersonnel) {
		this.overseaPersonnel = overseaPersonnel;
	}

	@Override
	public String getTempClassName() {
		return "overseaPersonnelTemp";
	}

}
