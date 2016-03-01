package com.tianque.openLayersMap.controller;

import java.lang.reflect.Field;
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

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.domian.vo.TableNameNoteVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysManageTableNameService;
import com.tianque.openLayersMap.service.TownshipsUpStatisticService;
import com.tianque.openLayersMap.util.BigModeType;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.ServiceImplModeType;

/**
 * 二维地图子类类别管理
 * 
 * @author yubin
 */
@Namespace("/gis/gisTypeManage")
@Transactional
@SuppressWarnings("serial")
@Scope("request")
@Controller("sysGisTypeManageController")
public class SysGisTypeManageController extends BaseAction {
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private Map<String, TownshipsUpStatisticService> searchService;
	@Autowired
	private SysManageTableNameService manageTableNameService;

	private GisTypeManage gisTypeManage;
	private List<GisTypeManage> gisTypeManageList;
	private List<StatisticInfoVo> satisticInfoVoList;
	private String ids;
	private Boolean exist;
	private GisFunction function; // 精确搜索对象
	private List<GisFunction> gisFunctionList;
	private Organization organization;
	private String tableName;
	private String innerKey;
	private List<String> columList;
	private List<TableNameNoteVo> tableColumNameNoteVos;

	/**
	 * 根据mode得到新增修改查等操作的时候页面的显示
	 */
	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/openLayersMap/system/sysManage/maintainTypeDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() throws Exception {
		if (null == mode) {
			this.errorMessage = "mode不能为空!";
			return ERROR;
		}
		if (mode.equals(DialogMode.EDIT_MODE) || mode.equals(DialogMode.VIEW_MODE)) {
			if (gisTypeManage == null || gisTypeManage.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			gisTypeManage = sysGisTypeManageService.getGisTypeManageById(gisTypeManage.getId());

			function = sysGisFunctionService.getGisFunctionBySonClassIdAndFunctionType(
					gisTypeManage.getId(), FunctionType.REFINESEARCH);
		} else if (mode.equals("manage")) {
			return "manage";
		}
		return SUCCESS;
	}

	@Action(value = "findGisTypeManageById", results = {
			@Result(name = "success", type = "json", params = { "root", "gisTypeManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findGisTypeManageById() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gisTypeManage = sysGisTypeManageService.getGisTypeManageById(gisTypeManage.getId());
		return SUCCESS;
	}

	@Action(value = "addGisTypeManage", results = {
			@Result(name = "success", type = "json", params = { "root", "gisTypeManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addGisTypeManage() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getName() == null
				|| gisTypeManage.getKey() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gisTypeManage = sysGisTypeManageService.addGisTypeManage(gisTypeManage);
		sysGisFunctionService.addFunction(function, null, gisTypeManage.getId());
		return SUCCESS;
	}

	@Action(value = "updateGisTypeManage", results = {
			@Result(name = "success", type = "json", params = { "root", "gisTypeManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateGisTypeManage() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getName() == null
				|| gisTypeManage.getKey() == null || gisTypeManage.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gisTypeManage = sysGisTypeManageService.updateGisTypeManage(gisTypeManage);
		sysGisFunctionService.updateFunction(function, null, gisTypeManage.getId());
		return SUCCESS;
	}

	@Action(value = "deleteGisTypeManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteGisTypeManage() throws Exception {
		if (ids == null || ids.trim().equals("")) {
			errorMessage = "参数错误";
			return ERROR;
		}
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			sysGisTypeManageService.deleteGisTypeManageById(Long.parseLong(idArray[i]));
			sysGisFunctionService.deleteGisFunctionBySonClassId(Long.parseLong(idArray[i]));
		}
		return SUCCESS;
	}

	/**
	 * 根据innerKey进行分页显示子类管理列表信息
	 */
	@Action(value = "findGisTypeManageForPageByInnerType", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findGisTypeManageForPageByInnerType() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getInnerKey() == null) {
			gridPage = new GridPage<GisTypeManage>(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage<GisTypeManage>(
					sysGisTypeManageService.findGisTypesForPageByInnerType(page, rows,
							gisTypeManage));
		}
		return SUCCESS;
	}

	/**
	 * 辖区分布列表
	 */
	@Action(value = "getNeedGisTypeManagesByInnerTypeAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "satisticInfoVoList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getNeedGisTypeManagesByInnerTypeAndOrgId() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getInnerKey() == null || organization == null
				|| organization.getId() == null) {
			satisticInfoVoList = new ArrayList<StatisticInfoVo>();
			return SUCCESS;
		} else {
			if (gisTypeManage.getInnerKey().equals(// 事件列表
					GisGlobalValueMap.ISSUE_MODE)) {
				satisticInfoVoList = searchService.get(
						GisGlobalValueMap.KEY_ISSUE_Statistic + "Service").statisticInfoByOrgId(
						organization.getId());
			} else if (gisTypeManage.getInnerKey().equals(// 房屋信息
					GisGlobalValueMap.HOURSE_MODE)) {
				satisticInfoVoList = getHousePropertyList();
			} else if (gisTypeManage.getInnerKey().equals(GisGlobalValueMap.PERSONAL_MODE)) {
				List<StatisticInfoVo> personList = new ArrayList<StatisticInfoVo>();
				List<StatisticInfoVo> keyPersonList = new ArrayList<StatisticInfoVo>();
				List<StatisticInfoVo> list = new ArrayList<StatisticInfoVo>();
				personList = searchService.get(ServiceImplModeType.PERSON_STATISTIC)
						.statisticInfoByOrgId(organization.getId());
				keyPersonList = searchService.get(ServiceImplModeType.KEY_PERSON_STATISTIC)
						.statisticInfoByOrgId(organization.getId());
				list.addAll(personList);
				list.addAll(keyPersonList);
				satisticInfoVoList = list;
			} else if(gisTypeManage.getInnerKey().equals(GisGlobalValueMap.PLACE_MODE)){// 重点场所列表
				
			} else if(gisTypeManage.getInnerKey().equals(GisGlobalValueMap.GIRDTEAM_MODE)){//网格员队伍管理列表
				satisticInfoVoList = searchService.get(ServiceImplModeType.GRID_TEAM_STATISTIC)
						.statisticInfoByOrgId(organization.getId());
			}
		}
		return SUCCESS;
	}

	/**
	 * 得到辖区分布住房列表数据
	 * 
	 * @return List<StatisticInfoVo>
	 */
	private List<StatisticInfoVo> getHousePropertyList() throws Exception {
		// 得到出租房和非出租房数据
		satisticInfoVoList = searchService.get(ServiceImplModeType.HOUSEPROPERTY_STATISTIC)
				.statisticInfoByOrgId(organization.getId());
		return satisticInfoVoList;
	}

	/**
	 * 改变子类是否在地图上显示的状态
	 */
	@Action(value = "changeCheckedOfGisTypeManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String changeCheckedOfGisTypeManage() throws Exception {
		if (ids == null || ids.trim().equals("") || gisTypeManage == null
				|| gisTypeManage.getChecked() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			gisTypeManage.setId(Long.valueOf(idArray[i]));
			sysGisTypeManageService.updateGisTypeManageStatus(gisTypeManage);
		}
		return SUCCESS;
	}

	/**
	 * 判断子类类型管理模块中是否存在相同的类型名称和key
	 */
	@Action(value = "hasDuplicateGisTypeManage", results = {
			@Result(name = "success", type = "json", params = { "root", "exist", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String hasDuplicateGisTypeManage() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getName() == null
				|| gisTypeManage.getKey() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		exist = sysGisTypeManageService.isHasDuplicateGisTypeManage(gisTypeManage);
		return SUCCESS;
	}

	/**
	 * 根据key得到子类管理对象
	 */
	@Action(value = "getGisTypeManagesByKey", results = {
			@Result(name = "success", type = "json", params = { "root", "gisTypeManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getGisTypeManagesByKey() throws Exception {
		if (gisTypeManage == null || gisTypeManage.getKey() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gisTypeManage = sysGisTypeManageService.getGisTypeManagesByKey(gisTypeManage);
		return SUCCESS;
	}

	@Action(value = "deleteGisTypeManageByInnkey", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteGisTypeManageByInnkey() throws Exception {
		sysGisTypeManageService.deleteGisTypeManageByInnerKeyOfNull();
		return SUCCESS;
	}

	/**
	 * 设置空pageInfo
	 * 
	 * @param pageSize
	 * @return PageInfo<GisTypeManage>
	 */
	private PageInfo<GisTypeManage> emptyPage(int pageSize) {
		PageInfo<GisTypeManage> pageInfo = new PageInfo<GisTypeManage>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<GisTypeManage>());
		return pageInfo;
	}

	/**
	 * 根据tableName获得字段名，及其注释
	 */
	@Action(value = "getCodeByTableName", results = {
			@Result(name = "success", location = "/openLayersMap/system/sysManage/gisTableColumn.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getCodeByTableName() throws Exception {
		if (tableName == null) {
			this.errorMessage = "表名不能为空";
			return ERROR;
		}
		tableColumNameNoteVos = manageTableNameService.findColumnNameAndComments(tableName);
		return SUCCESS;
	}

	/**
	 * 根据tableName得到对应的映射实体的属性
	 */
	@Action(value = "getBeanColumn", results = {
			@Result(name = "success", location = "/openLayersMap/system/sysManage/gisBeanCloumn.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getBeanColumn() throws Exception {
		if (tableName == null) {
			if (innerKey.equals("keyPerson")) {
				Field[] fields = KeyPersonInfoVo.class.getDeclaredFields();
				columList = getColumn(fields);
			} else if (innerKey.equals("keyPlaces")) {
				Field[] fields = KeyPlaceInfoVo.class.getDeclaredFields();
				columList = getColumn(fields);
			} else if (innerKey.equals(GisGlobalValueMap.PERSONAL_MODE)) {
				Field[] fields = PersonInfoVo.class.getDeclaredFields();
				columList = getColumn(fields);
			}
		} else {
			columList = getColumnList();
		}
		return SUCCESS;
	}

	/**
	 * 判断表名是否特殊业务如果是则得到相应的columList
	 * 
	 * @return columList
	 */
	private List<String> getColumnList() {
		Class<?> clazz = null;
		if (tableName.equals(BigModeType.HOURSEINFO)) {
			clazz = HourseInfo.class;
		} else if (tableName.equals(BigModeType.PERSON)) {
			clazz = PersonInfoVo.class;
		} else if (tableName.equals(BigModeType.GRIDLAYER)) {
			clazz = Gis2DLayer.class;
		} else if (tableName.equals(BigModeType.HOUSEPROPERTY)) {
			clazz = HousePropertyInfoVo.class;
		} else if (tableName.equals(BigModeType.ISSUE)) {
			clazz = IssueInfoVo.class;
		} else if (tableName.equals(BigModeType.KEY_PERSON)) {
			clazz = KeyPersonInfoVo.class;
		} else if (tableName.equals(BigModeType.KEY_PLACE)) {
			clazz = KeyPlaceInfoVo.class;
		} else if (tableName.equals(BigModeType.HOUSEINFO)) {
			clazz = HousePropertyInfoVo.class;
		} else if (tableName.equals("householdStaffs")) {
			clazz = PersonInfoVo.class;
		} else if (tableName.equals("issues")) {
			clazz = IssueInfoVo.class;
		} else if (tableName.equals("buildDatas")) {
			clazz = BuildDataInfoVo.class;
		}
		if (clazz != null)
			columList = getColumn(clazz.getDeclaredFields());
		return columList;
	}

	/**
	 * 得到字段相应的字符串
	 * 
	 * @param fields
	 * @return List<String>
	 */
	public List<String> getColumn(Field[] fields) {
		List<String> columnList = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String str = fields[i].getName();
			columnList.add(str);
		}
		return columnList;
	}

	public GisTypeManage getGisTypeManage() {
		return gisTypeManage;
	}

	public void setGisTypeManage(GisTypeManage gisTypeManage) {
		this.gisTypeManage = gisTypeManage;
	}

	public List<GisTypeManage> getGisTypeManageList() {
		return gisTypeManageList;
	}

	public void setGisTypeManageList(List<GisTypeManage> gisTypeManageList) {
		this.gisTypeManageList = gisTypeManageList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getExist() {
		return exist;
	}

	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public GisFunction getFunction() {
		return function;
	}

	public void setFunction(GisFunction function) {
		this.function = function;
	}

	public List<GisFunction> getGisFunctionList() {
		return gisFunctionList;
	}

	public void setGisFunctionList(List<GisFunction> gisFunctionList) {
		this.gisFunctionList = gisFunctionList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<StatisticInfoVo> getSatisticInfoVoList() {
		return satisticInfoVoList;
	}

	public void setSatisticInfoVoList(List<StatisticInfoVo> satisticInfoVoList) {
		this.satisticInfoVoList = satisticInfoVoList;
	}

	public List<TableNameNoteVo> getTableColumNameNoteVos() {
		return tableColumNameNoteVos;
	}

	public void setTableColumNameNoteVos(List<TableNameNoteVo> tableColumNameNoteVos) {
		this.tableColumNameNoteVos = tableColumNameNoteVos;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumList() {
		return columList;
	}

	public void setColumList(List<String> columList) {
		this.columList = columList;
	}

	public String getInnerKey() {
		return innerKey;
	}

	public void setInnerKey(String innerKey) {
		this.innerKey = innerKey;
	}
}
