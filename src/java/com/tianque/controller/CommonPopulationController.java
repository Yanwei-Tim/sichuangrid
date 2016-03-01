package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressService;
import com.tianque.controller.vo.CommonPopulation;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.vo.IdCardNoNativeAddress;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.IdCardNoNativeAddressService;
import com.tianque.util.PropertyUtil;

@Transactional
@Scope("prototype")
@Controller("commonPopulationController")
@Namespace("/commonPopulation/commonPopulationManage")
public class CommonPopulationController extends BaseAction {

	private CommonPopulation commonPopulation;

	private Long populationId;

	private Long orgId;

	private String idCardNo;

	private String[] idCardNos;

	private String actualPopulationType;

	private boolean exist = false;

	private ActualPopulation actualPopulation;

	private List<ActualPopulation> actualPopulations;

	private boolean populationState = false;

	private String exsistedIdCardMessage;

	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;

	@Autowired
	private IdCardNoNativeAddressService idCardNoNativeAddressService;
	@Autowired
	private PermanentAddressService permanentAddressService;

	@Autowired
	private BaseInfoService baseInfoService;

	@Action(value = "getCommonPopulationByIdCardNo", results = { @Result(name = "success", type = "json", params = {
			"root", "commonPopulation", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getCommonPopulationByIdCardNo() throws Exception {
		IdCardNoNativeAddress idCardNoNativeAddress = new IdCardNoNativeAddress();
		Map<String, IdCardNoNativeAddress> map = idCardNoNativeAddress
				.getIdCardNoNativeAddressByIdCardNo(commonPopulation
						.getIdCardNo());
		IdCardNoNativeAddress province = map.get("province");
		IdCardNoNativeAddress city = map.get("city");
		IdCardNoNativeAddress district = map.get("district");

		PermanentAddress getProvince = permanentAddressService
				.getPermanentAddressByAddressNo(province.getSixthIdCardNo());
		PermanentAddress getCity = permanentAddressService
				.getPermanentAddressByAddressNo(city.getSixthIdCardNo());
		PermanentAddress getDistrict = permanentAddressService
				.getPermanentAddressByAddressNo(district.getSixthIdCardNo());

		// IdCardNoNativeAddress getProvince = idCardNoNativeAddressService
		// .getIdCardNoNativeAddressBySixthIdCardNo(province);
		// IdCardNoNativeAddress getCity = idCardNoNativeAddressService
		// .getIdCardNoNativeAddressBySixthIdCardNo(city);
		// IdCardNoNativeAddress getDistrict = idCardNoNativeAddressService
		// .getIdCardNoNativeAddressBySixthIdCardNo(district);
		
		commonPopulation.setProvince(getProvince == null ? null : getProvince
				.getAddressName());
		commonPopulation.setCity(getCity == null ? null : getCity
				.getAddressName());
		commonPopulation.setDistrict(getDistrict == null ? null : getDistrict
				.getAddressName());
		return SUCCESS;
	}

	@Action(value = "isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType", results = { @Result(name = "success", type = "json", params = {
			"root", "exist" }) })
	public String isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType()
			throws Exception {
		exist = actualPopulationMutexService
				.isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
						populationId, orgId, idCardNo, actualPopulationType);
		return SUCCESS;
	}

	@Action(value = "getActualPopulationByOrgIdAndIdCardNo", results = { @Result(name = "success", type = "json", params = {
			"root", "actualPopulation", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getActualPopulationByOrgIdAndIdCardNo() throws Exception {
		actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(orgId, idCardNo);
		if (actualPopulation == null) {
			Countrymen countrymen = baseInfoService.existBaseInfo(idCardNo);
			if (countrymen != null) {
				actualPopulation = new ActualPopulation();
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
						actualPopulation, countrymen);
			}
		}
		return SUCCESS;
	}

	@Action(value = "getActualPopulationByOrgIdAndIdCardNoIncludeLogout", results = { @Result(name = "success", type = "json", params = {
			"root", "actualPopulation", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getActualPopulationByOrgIdAndIdCardNoIncludeLogout()
			throws Exception {
		actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoIncludeLogout(orgId,
						idCardNo);
		if (actualPopulation == null) {
			Countrymen countrymen = baseInfoService.existBaseInfo(idCardNo);
			if (countrymen != null) {
				actualPopulation = new ActualPopulation();
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
						actualPopulation, countrymen);
			}
		}
		return SUCCESS;
	}

	@Action(value = "getActualPopulationbyOrgIdAndIdCardNoExistedMessage", results = { @Result(name = "success", type = "json", params = {
			"root", "exsistedIdCardMessage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getActualPopulationbyOrgIdAndIdCardNoExistedMessage()
			throws Exception {
		exsistedIdCardMessage = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoExistedMessage(orgId,
						idCardNo, actualPopulationType, populationId);
		return SUCCESS;
	}

	@Action(value = "getActualPopulationIncludeUnsettledPopulationByOrgIdAndIdCardNo", results = { @Result(name = "success", type = "json", params = {
			"root", "actualPopulation", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getActualPopulationIncludeUnsettledPopulationByOrgIdAndIdCardNo()
			throws Exception {
		actualPopulation = actualPopulationProcessorService
				.getActualPopulationIncludeUnsettledPopulationByOrgIdAndIdCardNo(
						orgId, idCardNo);
		return SUCCESS;
	}

	@Action(value = "getActualPopulationByOrgIdAndIdCardNoForList", results = { @Result(name = "success", type = "json", params = {
			"root", "actualPopulations", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getActualPopulationByOrgIdAndIdCardNoForList()
			throws Exception {
		actualPopulations = new ArrayList<ActualPopulation>();
		for (int i = 0; i < idCardNos.length; i++) {
			List actualPopulationList = actualPopulationProcessorService
					.getActualPopulationByOrgIdAndIdCardNoForList(orgId,
							idCardNos[i]);
			if (null != actualPopulationList
					&& actualPopulationList.size() != 0) {
				actualPopulations.addAll(actualPopulationList);
			}
		}
		return SUCCESS;
	}

	@Action(value = "isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "populationState", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId()
			throws Exception {
		for (int i = 0; i < idCardNos.length; i++) {
			populationState = actualPopulationProcessorService
					.isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId(orgId,
							idCardNos[i]);
			if (!populationState) {
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	@Action(value = "findActualPopulationsByOrgIdAndIdCardNo", results = { @Result(name = "success", type = "json", params = {
			"root", "actualPopulations", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findActualPopulationsByOrgIdAndIdCardNo() throws Exception {
		actualPopulations = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoList(orgId, idCardNo);
		return SUCCESS;
	}

	public CommonPopulation getCommonPopulation() {
		return commonPopulation;
	}

	public void setCommonPopulation(CommonPopulation commonPopulation) {
		this.commonPopulation = commonPopulation;
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

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public ActualPopulation getActualPopulation() {
		return actualPopulation;
	}

	public void setActualPopulation(ActualPopulation actualPopulation) {
		this.actualPopulation = actualPopulation;
	}

	public List<ActualPopulation> getActualPopulations() {
		return actualPopulations;
	}

	public void setActualPopulations(List<ActualPopulation> actualPopulations) {
		this.actualPopulations = actualPopulations;
	}

	public boolean isPopulationState() {
		return populationState;
	}

	public void setPopulationState(boolean populationState) {
		this.populationState = populationState;
	}

	public String[] getIdCardNos() {
		return idCardNos;
	}

	public void setIdCardNos(String[] idCardNos) {
		this.idCardNos = idCardNos;
	}

	public String getExsistedIdCardMessage() {
		return exsistedIdCardMessage;
	}

	public void setExsistedIdCardMessage(String exsistedIdCardMessage) {
		this.exsistedIdCardMessage = exsistedIdCardMessage;
	}

}
