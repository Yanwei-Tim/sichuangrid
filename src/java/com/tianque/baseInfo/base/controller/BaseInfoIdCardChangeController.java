package com.tianque.baseInfo.base.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoIdCardChangeSerivce;
import com.tianque.core.base.BaseAction;

/**
 * 
 * @Description:户籍、流动、未落户修改身份证号码控制层
 * @author zhangyouwei@hztian.com
 * @date: 2014-6-30 下午03:59:08
 */
@Namespace("/baseinfo/baseinfoIdCardChange")
@Controller("baseInfoIdCardChangeController")
@Scope("prototype")
public class BaseInfoIdCardChangeController extends BaseAction {
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory
			.getLogger(BaseInfoIdCardChangeController.class);
	/** 实有人口信息 */
	private Countrymen countrymen;
	/** 实有人口id */
	private Long populationId;
	private Long orgId;
	/** 身份证 */
	private String idCardNo;
	/** 实有人口类型 */
	private String actualPopulationType;
	@Autowired
	private BaseInfoIdCardChangeSerivce baseInfoIdCardChangeSerivce;

	/**
	 * 验证身份证号码是否重复
	 * 
	 * @return
	 */
	@Action(value = "exsistedIdCard", results = {
			@Result(name = "success", type = "json", params = { "root",
					"countrymen", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String exsistedIdCard() throws Exception {
		if (idCardNo == null || "".equals(idCardNo)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		countrymen = baseInfoIdCardChangeSerivce.existBaseInfo(
				actualPopulationType, idCardNo, orgId);
		return SUCCESS;
	}

	/**
	 * 修改身份证号码
	 * 
	 * @return
	 */
	@Action(value = "baseinfoIdCardChange", results = {
			@Result(name = "success", type = "json", params = { "root",
					"countrymen", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String baseinfoIdCardChange() throws Exception {
		if (countrymen == null || countrymen.getId() == null
				|| idCardNo == null || "".equals(countrymen.getIdCardNo())
				|| "".equals(countrymen.getActualPopulationType())
				|| "".equals(idCardNo)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		countrymen = baseInfoIdCardChangeSerivce.updateBaseInfoIdCardNo(
				countrymen, idCardNo);
		return SUCCESS;
	}

	public Countrymen getCountrymen() {
		return countrymen;
	}

	public void setCountrymen(Countrymen countrymen) {
		this.countrymen = countrymen;
	}

	public org.slf4j.Logger getLogger() {
		return logger;
	}

	public void setLogger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getActualPopulationType() {
		return actualPopulationType;
	}

	public void setActualPopulationType(String actualPopulationType) {
		this.actualPopulationType = actualPopulationType;
	}

}
