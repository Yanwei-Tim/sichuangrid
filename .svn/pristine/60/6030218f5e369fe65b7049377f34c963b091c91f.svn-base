package com.tianque.gis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.GisPopulationService;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.SearchGisPopulationService;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;

/**
 * @author Administrator
 */

@Namespace("/gis/populationInfoManage")
@Transactional
@Scope("request")
@Controller("gisPopulationInfoController")
public class GisPopulationInfoController extends BaseAction {
	@Autowired
	private Map<String, SearchGisPopulationService> searchPopulationServiceMap;
	@Autowired
	private GisService gisService;
	@Autowired
	private GisPopulationService gisPopulationService;

	private PageInfo<PopulationVo> pageInfo;
	private String type;
	private Long orgId;
	private Long houseId;
	private String queryStr;
	private String personType;
	private HouseInfoVo houseInfoVo;
	private List<PopulationVo> populationList;
	private PopulationVo populationVo;
	private Long personId;
	private String currentTime;
	private Boolean showed;

	/**
	 * 根据传入的serviceName 查找相应的列表
	 * 
	 * @return success
	 */
	@Action(value = "getPopulationListByOrgId", results = {
			@Result(name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getPageInfoForSearchPopulation() throws Exception {
		if (type.equals(GisGlobalValue.IMPORTANT_POPULATION)
				|| type.equals(GisGlobalValue.NURTURES_WOMAN)
				|| type.equals(GisGlobalValue.CAREED_POPULATION)) {
			pageInfo = searchPopulationServiceMap.get(getServiceName())
					.getFurtherStepGisPopulationInfoByPersonTypeList(
							orgId,
							changePersonTypeMapToPersonTypeList(GisGlobalValue
									.getGisPersonType(type)), queryStr, page,
							rows, sidx, sord);
			gridPage = new GridPage<PopulationVo>(pageInfo);
			return SUCCESS;
		} else if (type.equals(PopulationCatalog.ALL_ACTUAL_POPULATION)) {
			pageInfo = gisService.searchActulaPersonByName(orgId, page, rows,
					sidx, sord);
			gridPage = new GridPage<PopulationVo>(pageInfo);
			return SUCCESS;
		}
		pageInfo = searchPopulationServiceMap.get(getServiceName())
				.findPopulationsByOrgId(orgId, page, rows, sidx, sord);
		gridPage = new GridPage<PopulationVo>(pageInfo);
		return SUCCESS;
	}

	/**
	 * 获取绑定后的房屋内人员信息 根据houseId查出来该房屋的下的所有实口。
	 * 
	 * @return
	 */
	@Action(value = "getPopulationInfosByHouseId", results = {
			@Result(name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getPopulationInfosByHouseId() throws Exception {
		if (null != houseId) {
			pageInfo = gisService.getPopulationInfosByHouseId(houseId, page,
					rows, sidx, sord);
			gridPage = new GridPage<PopulationVo>(pageInfo);
			return SUCCESS;
		} else {
			return ERROR;
		}

	}

	/**
	 * 根据检索条查找人员 通过orgId和条件去检索,从实口的四张表中检索符合的数据。
	 * 
	 * @return
	 */
	@Action(value = "searchPersonByNameAndOrgId", results = {
			@Result(name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchPersonByNameAndOrgId() throws Exception {
		pageInfo = gisService.searchPersonByName(orgId, queryStr, page, rows,
				sidx, sord);
		gridPage = new GridPage<PopulationVo>(pageInfo);
		return SUCCESS;
	}

	/**
	 * 人员二次过滤搜索
	 * 
	 * @return
	 */
	@Action(value = "getFurtherStepSearch", results = {
			@Result(name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getFurtherStepGisPopulationInfoByPersonType()
			throws Exception {
		if (personType.equals(GisGlobalValue.IMPORTANT_POPULATION)
				|| personType.equals(GisGlobalValue.NURTURES_WOMAN)
				|| personType.equals(GisGlobalValue.CAREED_POPULATION)) {
			pageInfo = searchPopulationServiceMap.get(getServiceName())
					.getFurtherStepGisPopulationInfoByPersonTypeList(
							orgId,
							changePersonTypeMapToPersonTypeList(GisGlobalValue
									.getGisPersonType(personType)), queryStr,
							page, rows, sidx, sord);
			gridPage = new GridPage<PopulationVo>(pageInfo);
			return SUCCESS;
		} else {
			// 单条过滤
			pageInfo = searchPopulationServiceMap.get(getServiceName())
					.getFurtherStepGisPopulationInfoByPersonType(orgId,
							personType, queryStr, page, rows, sidx, sord);
			gridPage = new GridPage<PopulationVo>(pageInfo);
			return SUCCESS;
		}
	}

	private List<String> changePersonTypeMapToPersonTypeList(
			Map<String, String> map) {
		Set<Map.Entry<String, String>> set = map.entrySet();
		List<String> personTypelist = new ArrayList<String>();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			personTypelist.add(entry.getKey());
		}
		return personTypelist;
	}

	/**
	 * 人员详细信息
	 * 
	 * @return
	 */
	@Action(value = "getPopulationDatialInfoByPersonId", results = {
			@Result(name = "success", location = "/gis3D/population/populationViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errormessage" }) })
	public String getPopulationInfoByPersonId() throws Exception {
		if (null != personId) {
			// 获取某条户籍人口下的业务人员记录 ,
			populationList = searchPopulationServiceMap.get(getServiceName())
					.findPopulationByPersonId(personId);
			populationVo = populationList.get(0);
			getHouseInfoByHouseId();
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * 房屋信息以及人员数量
	 * 
	 * @return
	 */
	@Action(value = "getHouseInfoByHouseId", results = {
			@Result(name = "success", location = "/gis3D/houseInfo/houseViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHouseInfoByHouseId() throws Exception {
		if (null != houseId) {
			houseInfoVo = gisService.getHouseInfoByHouseId(houseId);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "listAllBindingPopulation", results = {
			@Result(name = "success", params = { "root", "populationList",
					"ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String listAllBindingPopulation() throws Exception {
		if (null == orgId) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}
		populationList = new ArrayList<PopulationVo>();
		String[] serviceNames = { PopulationType.HOUSEHOLD_STAFF,
				PopulationType.FLOATING_POPULATION,
				PopulationType.UNSETTLED_POPULATION,
				PopulationType.OVERSEA_STAFF };
		for (String sn : serviceNames) {
			if (type.equals(sn)) {
				sn += "Service";
				populationList.addAll(searchPopulationServiceMap.get(sn)
						.findGisPopulationByOrgid(orgId));
			}
		}
		return SUCCESS;
	}

	@Action(value = "listPopulation", results = {
			@Result(name = "success", params = { "root", "populationList",
					"ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String listPopulation() throws Exception {
		if (null == orgId) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}
		if (!(type == null || "".equals(type))) {
			populationList = new ArrayList<PopulationVo>();
			populationList.addAll(gisPopulationService
					.queryPopultionsForGis(type));
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	private String getServiceName() {
		String servoceName = type;
		if (type.equals(PopulationCatalog.ALL_ACTUAL_POPULATION)
				|| type.equals(GisGlobalValue.IMPORTANT_POPULATION)
				|| type.equals(GisGlobalValue.NURTURES_WOMAN)
				|| type.equals(GisGlobalValue.CAREED_POPULATION)) {
			servoceName = "searchPopulation";
			return servoceName + "Service";
		} else {
			return servoceName + "Service";
		}
	}

	public PageInfo<PopulationVo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<PopulationVo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public HouseInfoVo getHouseInfoVo() {
		return houseInfoVo;
	}

	public void setHouseInfoVo(HouseInfoVo houseInfoVo) {
		this.houseInfoVo = houseInfoVo;
	}

	public List<PopulationVo> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(List<PopulationVo> populationList) {
		this.populationList = populationList;
	}

	public PopulationVo getPopulationVo() {
		return populationVo;
	}

	public void setPopulationVo(PopulationVo populationVo) {
		this.populationVo = populationVo;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	public Boolean getShowed() {
		return showed;
	}

}
