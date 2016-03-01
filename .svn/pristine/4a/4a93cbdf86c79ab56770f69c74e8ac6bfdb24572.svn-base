package com.tianque.baseInfo.buildDatas.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.buildDatas.domain.BuildLayout;
import com.tianque.baseInfo.buildDatas.domain.BuildLayoutCell;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.BuilddatasSearchVo;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.baseInfo.buildDatas.service.BuildLayoutService;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @author dingbo@hztianque.com 2012-07-24
 */

@Namespace("/builddatasManage")
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("builddatasController")
public class BuilddatasController extends BaseAction {

	@Autowired
	private BuilddatasService builddatasService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private BuildLayoutService buildLayoutService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	private KeyPlaceService keyPlaceService;

	private Builddatas builddatas;
	private Organization organization;
	private BuilddatasSearchVo builddatasSearchVo;
	private String idList;
	private java.util.List<Long> deleteIds;
	private List<Long> houseIds;
	/** 楼宇编号 */
	private Long buildingid;
	private String buildLayoutStr;

	/** 布局 */
	private BuildLayout buildLayout;
	/** 布局列表 */
	private List<BuildLayout> buildLayoutList;

	/** 房屋列表 */
	private List<HouseInfo> houseInfoList;
	/** 实有单位列表 */
	private List<ActualCompany> actualCompanyList;
	/** 重点场所列表 */
	private List<KeyPlace> keyPlaceList;

	/** 未被选中的房屋列表 */
	private List<HouseInfo> notSelectHouseInfoList;
	@Autowired
	private ShardConversion shardConversion;

	private Boolean flag;
	private String idArr;
	private String queryStr;

	private Integer isBind;

	private Long orgId;
	private Integer houseNum;
	private Integer lettinghouseNum;
	private Integer actualCompanyNum;
	private Integer keyPlaceNum;
	private List<Object> typeColorList;
	private String personType;
	private List<LayoutTagVo> layoutTagList;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PermissionFilter(ename = "builddatasManagement")
	public String findBuilddatasByOrgInternalCode() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择一个组织机构";
			return ERROR;
		}
		organization.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(organization.getId()).getOrgInternalCode());
		PageInfo pageInfo = builddatasService.findBuilddatasByOrgInternalCode(
				organization.getOrgInternalCode(), isBind, page, rows, sidx,
				sord);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	@PermissionFilter(ename = "addBuilddatas")
	public String addBuilddatas() throws Exception {
		if (builddatas == null || builddatas.getOrganization() == null
				|| builddatas.getOrganization().getId() == null
				|| this.errorMessage != null) {
			return ERROR;
		}
		builddatas.setOrginternalcode(organizationDubboService
				.getSimpleOrgById(builddatas.getOrganization().getId())
				.getOrgInternalCode());
		builddatas = builddatasService.addBuilddatas(builddatas);
		prepareBuilddatas();
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateBuilddatas")
	public String updateBuilddatas() throws Exception {
		if (builddatas == null || builddatas.getOrganization() == null
				|| builddatas.getOrganization().getId() == null
				|| this.errorMessage != null) {
			return ERROR;
		}
		builddatas.setOrginternalcode(organizationDubboService
				.getSimpleOrgById(builddatas.getOrganization().getId())
				.getOrgInternalCode());
		builddatas = builddatasService.updateBuilddatas(builddatas);
		prepareBuilddatas();
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteBuilddatas")
	public String deleteMultiBuilddatas() throws Exception {
		if (idList == null) {
			this.errorMessage = "请选中一条或多条要删除的记录!";
			return ERROR;
		}
		String[] deleteId = idList.split(",");
		java.util.List<Long> idList = new java.util.ArrayList<Long>();
		if (deleteId != null)
			for (int i = 0; i < deleteId.length; i++) {
				if (deleteId[i] == null || deleteId[i].trim().length() == 0)
					continue;
				idList.add(Long.parseLong(deleteId[i]));
			}

		if (idList.size() == 0) {
			this.errorMessage = "请选中一条或多条要删除的记录!";
			return ERROR;
		}
		deleteIds = builddatasService.deleteMultiBuilddatas(idList);
		return SUCCESS;
	}

	/**
	 * id加密删除
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteBuilddatas")
	public String deleteMultiBuilddatasByEncrypt() throws Exception {
		if (idList == null) {
			this.errorMessage = "请选中一条或多条要删除的记录!";
			return ERROR;
		}
		String[] deleteId = idList.split(",");
		java.util.List<Long> idList = new java.util.ArrayList<Long>();
		if (deleteId != null)
			for (int i = 0; i < deleteId.length; i++) {
				if (deleteId[i] == null || deleteId[i].trim().length() == 0)
					continue;
				idList.add(Long.parseLong(deleteId[i]));
			}

		if (idList.size() == 0) {
			this.errorMessage = "请选中一条或多条要删除的记录!";
			return ERROR;
		}
		deleteIds = builddatasService.deleteMultiBuilddatas(idList);
		return SUCCESS;
	}

	private void prepareBuilddatas() {
		if (builddatas != null) {
			organization = builddatas.getOrganization();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService)
									.getId()));
			builddatas.setOrganization(organization);
		}
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			builddatas = new Builddatas();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService)
									.getId()));
			builddatas.setOrganization(organization);
		} else if (DialogMode.VIEW_MODE.equals(mode)
				|| DialogMode.EDIT_MODE.equals(mode)) {
			if (builddatas == null || builddatas.getId() == null) {
				return ERROR;
			}
			builddatas = builddatasService
					.getBuilddatasById(builddatas.getId());
			prepareBuilddatas();
		} else if ("gisEdit".equals(mode)) {
			if (builddatas == null || builddatas.getBuildingid() == null) {
				return ERROR;
			}
			builddatas = builddatasService.getBuilddatasByBuildId(builddatas
					.getBuildingid());
			prepareBuilddatas();
		} else if ("bind".equals(mode)) {
			return "bind";
		}
		return SUCCESS;
	}

	/** id加密查看 、修改 */
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)
				|| DialogMode.EDIT_MODE.equals(mode)) {
			if (builddatas == null || builddatas.getId() == null) {
				return ERROR;
			}
			builddatas = builddatasService
					.getBuilddatasById(builddatas.getId());
			prepareBuilddatas();
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PermissionFilter(ename = "searchBuilddatas")
	public String searchBuilddatas() throws Exception {
		if (builddatasSearchVo == null) {
			this.errorMessage = "请正确填写参数";
			return ERROR;
		}
		if (builddatasSearchVo.getOrginternalcode() == null) {
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			builddatasSearchVo.setOrginternalcode(organization
					.getOrgInternalCode());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				builddatasService.searchBuilddatas(builddatasSearchVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PermissionFilter(ename = "searchBuilddatasByNameAndAddress")
	public String searchBuilddatasByNameAndAddress() throws Exception {
		if (builddatasSearchVo == null) {
			this.errorMessage = "请正确填写参数";
			return ERROR;
		}
		if (builddatasSearchVo.getOrginternalcode() == null) {
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			builddatasSearchVo.setOrginternalcode(organization
					.getOrgInternalCode());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				builddatasService.searchBuilddatasByNameAndAddress(
						builddatasSearchVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organization.getId()));
		return SUCCESS;
	}

	/**
	 * 根据热区ID查询楼宇
	 * 
	 * @return
	 */
	public String getBuilddatasByBuildId() throws Exception {
		if (builddatas == null || builddatas.getBuildingid() == null) {
			this.errorMessage = "请选择楼宇";
			return ERROR;
		}
		builddatas = builddatasService.getBuilddatasByBuildId(builddatas
				.getBuildingid());
		return SUCCESS;
	}

	/**
	 * 生成布局
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "generatedLayout")
	public String generatedLayout() throws Exception {

		if (builddatas == null) {
			this.errorMessage = "请选择楼宇";
			return ERROR;
		}

		if (builddatas.getId() == null) {
			builddatas = builddatasService.getBuilddatasByBuildId(builddatas
					.getBuildingid());
		} else {
			builddatas = builddatasService
					.getBuilddatasById(builddatas.getId());
		}

		// 房屋
		houseInfoList = actualHouseService
				.findHouseInfosListByBuildingId(builddatas.getId());
		houseNum = houseInfoList.size();
		lettinghouseNum = countLettinghouseNum(houseInfoList);

		// 单位
		actualCompanyList = actualCompanyService
				.findActualCompanyListByBuildingId(builddatas.getId());
		actualCompanyNum = actualCompanyList.size();

		// 场所
		Long buiildingId = null;
		if (null != builddatas.getBuildingid()) {
			buiildingId = Long.valueOf(builddatas.getBuildingid());
			keyPlaceList = keyPlaceService
					.findKeyPlaceListByBuilddatasId(buiildingId);
			keyPlaceNum = keyPlaceList.size();
		}

		typeColorList = buildLayoutService.findBuildLayoutTypeData(
				builddatas.getId(), builddatas.getOrganization().getId());

		// 判断是否已生成布局,layout表示已生成
		if (mode.equals("layout")) {
			buildLayout = buildLayoutService
					.getBuildLayoutByBuildId(builddatas.getId());

//			organization = new Organization();
//			organization = buildLayoutList.get(0).getOrg();
			// 创建未被选中的房屋信息，生成前台界面select选择框
			//createNotSelectHouseInfoList(buildLayout);
			return "layout";
		} else {

			if (buildLayout == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
		}
		return "layout";
	}

	private void createNotSelectHouseInfoList(BuildLayout buildLayout) {
		if (buildLayout == null || buildLayout.getLayoutCellList() == null 
				|| buildLayout.getLayoutCellList().size() <= 0) {
			return;
		}
		notSelectHouseInfoList = new ArrayList<HouseInfo>(houseInfoList);
		for (BuildLayoutCell layoutInfoCell : buildLayout.getLayoutCellList()) {
			for (HouseInfo houseInfo : notSelectHouseInfoList) {
				if (houseInfo.getId().equals(layoutInfoCell.getHousePropertyId())) {
					notSelectHouseInfoList.remove(houseInfo);
					break;
				}
			}
		}
	}

	public String findHouseIdByPersonTypeAndHouseIds() throws Exception {
		if (personType == null || idList == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Set<String> keySet = GisGlobalValue.keyPopulation.keySet();
		Iterator<String> it = keySet.iterator();
		String key;
		for (; it.hasNext();) {
			key = it.next();
			if (GisGlobalValue.keyPopulation.get(key).equals(personType)) {
				personType = key;
				break;
			}
		}
		houseIds = buildLayoutService.findHouseIdByPersonTypeAndHouseIds(
				personType, idList.split(","));
		return SUCCESS;
	}

	/**
	 * 获取出租房个数
	 * 
	 * @param houseInfoList
	 * @return
	 */
	private Integer countLettinghouseNum(List<HouseInfo> houseInfoList) {
		Integer num = 0;
		for (HouseInfo domain : houseInfoList) {
			if (domain.getIsRentalHouse()) {
				num++;
			}
		}
		return num;
	}

	public String addBuildLayout() throws Exception {
		if (builddatas == null || buildLayout == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if(buildLayout.getId()!=null){
			buildLayoutService.updateBuildLayout(buildLayout);
		}else{
			buildLayout.setOrg(organizationDubboService.getSimpleOrgById(buildLayout.getOrg().getId()));
			buildLayoutService.addBuildLayout(buildLayout, builddatas);
		}
		return SUCCESS;
	}
	
	public String changeLayoutData() throws Exception {
		buildLayoutService.changeLayoutData();
		return SUCCESS;
	}

	public String deleteLayoutAndUpdateBuilddatasByBuildingId()
			throws Exception {
		if (builddatas == null || builddatas.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		builddatasService
				.deleteLayoutAndUpdateBuilddatasByBuildingId(builddatas.getId());
		return SUCCESS;
	}

	private List<Long> formStringArr(String[] ids) {
		List<Long> idLongs = new ArrayList<Long>();
		for (String id : ids) {
			if (id.length() > 0) {
				idLongs.add(Long.valueOf(id));
			}
		}
		return idLongs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getUnBoundedLocationList() throws Exception {

		if (null == organization.getId()) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}

		PageInfo<HouseInfo> pageInfo = actualHouseService.findUnBoundedByOrgId(
				organization.getId(), page, rows, sidx, sord, queryStr);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getUnBoundedActualCompanyList() throws Exception {

		if (null == organization.getId()) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}

		PageInfo<ActualCompany> pageInfo = actualCompanyService
				.findUnBoundedByOrgId(organization.getId(), page, rows, sidx,
						sord, queryStr);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findHouseInfosByBuildingIdForPage() throws Exception {
		if (null == orgId) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}
		if (!StringUtil.isStringAvaliable(String.valueOf(builddatas.getId()))) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				actualHouseService.findHouseInfosByBuilddatasIdForPage(
						builddatas.getId(), page, rows, sidx, sord,
						shardConversion.getShardCode(orgId)),
				organizationDubboService, new String[] { "organization" },
				orgId));
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findUnitInfosByBuildingIdForPage() throws Exception {
		if (null == orgId) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}
		if (!StringUtil.isStringAvaliable(String.valueOf(builddatas.getId()))) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				actualCompanyService.findActualCompanyByBuilddatasIdForPage(
						builddatas.getId(), page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				orgId));
		return SUCCESS;
	}

	public String findBoundKeyPlace() throws Exception {
		if (null == orgId) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}
		if (!StringUtil.isStringAvaliable(String.valueOf(builddatas.getId()))) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				keyPlaceService.findBoundKeyPlaceByBuilddatasIdForPage(
						builddatas.getId(), page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				orgId));
		return SUCCESS;
	}

	public String findUnboundKeyPlace() throws Exception {
		if (null == organization.getId()) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}

		PageInfo<KeyPlace> pageInfo = keyPlaceService
				.findUnBoundKeyPlaceByOrgId(organization.getId(), page, rows,
						sidx, sord, queryStr);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	/**
	 * 更新房屋绑定
	 * 
	 * @return
	 */
	public String updateHouseInfo() throws Exception {
		if (idArr == null || "".equals(idArr)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			Long builddatasId;
			if (builddatas == null || builddatas.getId() == null) {
				builddatasId = null;
			} else {
				builddatasId = builddatas.getId();
			}
			actualHouseService
					.updateHouseInfo(formStringArr(ids), builddatasId);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	public String updateHouseInfoByEncrypt() throws Exception {
		if (idArr == null || "".equals(idArr)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			Long builddatasId;
			if (builddatas == null || builddatas.getId() == null) {
				builddatasId = null;
			} else {
				builddatasId = builddatas.getId();
			}
			actualHouseService
					.updateHouseInfo(formStringArr(ids), builddatasId);
		}
		return SUCCESS;
	}

	public String boundKeyPlace() throws Exception {
		if (idArr == null || "".equals(idArr) || builddatas == null
				|| builddatas.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			Long builddatasId;
			builddatasId = builddatas.getId();
			keyPlaceService.boundKeyPlace(formStringArr(ids), builddatasId);
		}
		return SUCCESS;
	}

	public String unboundKeyPlace() throws Exception {
		if (idArr == null || "".equals(idArr)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			keyPlaceService.unboundKeyPlace(formStringArr(ids));
		}
		return SUCCESS;
	}

	public String unboundActualCompany() throws Exception {
		if (idArr == null || "".equals(idArr)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			actualCompanyService.unboundActualCompany(formStringArr(ids));
		}
		return SUCCESS;
	}

	public String updateActualCompanyInfo() throws Exception {
		if (idArr == null || "".equals(idArr) || builddatas == null
				|| builddatas.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] ids = idArr.split(",");
		if (null != ids && ids.length > 0) {
			Long builddatasId;
			builddatasId = builddatas.getId();
			actualCompanyService.boundActualCompany(formStringArr(ids),
					builddatasId);
		}
		return SUCCESS;
	}

	/**
	 * 绑定楼宇
	 * 
	 * @return
	 */
	public String boundBuilding() throws Exception {
		if (builddatas == null || builddatas.getId() == null) {
			this.errorMessage = "楼宇不能为空";
			return ERROR;
		}
		if (builddatas.getOpenLayersMapInfo() == null
				|| builddatas.getOpenLayersMapInfo().getCenterLat() == null
				|| builddatas.getOpenLayersMapInfo().getCenterLon() == null) {
			this.errorMessage = "经纬度不能为空";
			return ERROR;
		}
		builddatasService.boundBuilddatas(builddatas);
		return SUCCESS;
	}

	/**
	 * 解除楼宇绑定
	 * 
	 * @return
	 */
	public String unboundBuilding() throws Exception {
		if (builddatas == null || builddatas.getId() == null) {
			this.errorMessage = "楼宇为空";
			return ERROR;
		}
		builddatasService.unboundBuilddatas(builddatas);
		return SUCCESS;
	}

	public String findLayoutAllTags() throws Exception {
		if (null == orgId) {
			errorMessage = "网格ID为空，请选择网格";
			return ERROR;
		}
		if (builddatas == null || builddatas.getId() == null) {
			this.errorMessage = "楼宇为空";
			return ERROR;
		}
		layoutTagList = buildLayoutService.findLayoutAllTags(orgId,
				builddatas.getId());
		return SUCCESS;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public List<Long> getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(List<Long> houseIds) {
		this.houseIds = houseIds;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getIdArr() {
		return idArr;
	}

	public void setIdArr(String idArr) {
		this.idArr = idArr;
	}

	private List<BuildLayout> createBuildLayoutList() {
		buildLayoutList = new ArrayList<BuildLayout>();
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		JSONArray jsonArray = JSONArray.fromObject(buildLayoutStr);
		List<BuildLayout> cellList = (List<BuildLayout>) JSONArray.toCollection(jsonArray);
		for (BuildLayout buildLayout : cellList) {                 //cell格式：第几行,第几列,占几列,占几行,房屋ID,房间名";
			buildLayout.setBuildId(builddatas.getId());
//			buildLayout.setLayoutRow(Long.parseLong(cell[0]));
//			buildLayout.setLayoutColumn(Long.parseLong(content[1]));
//			buildLayout.setLayoutColSpan(Long.parseLong(content[2]));
//			buildLayout.setLayoutRowSpan(Long.parseLong(content[3]));
//			buildLayout.setHousePropertyId(Long.parseLong(content[4]));
//			buildLayout.setRoom(content[5]);
//			if (content.length > 6) {
//				buildLayout.setBuildType(Integer.parseInt(content[6]));
//			} else {
//				buildLayout.setBuildType(0);
//			}
//			if (content.length > 7) {
//				buildLayout.setKeyPlaceType(content[7]);
//			}
//			buildLayout.setTotalCol(layoutCol);
//			buildLayout.setTotalRow(layoutRow);
//			buildLayout.setOrg(organization);
//			buildLayoutList.add(buildLayout);
		}
		return buildLayoutList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Builddatas getBuilddatas() {
		return builddatas;
	}

	public void setBuilddatas(Builddatas builddatas) {
		this.builddatas = builddatas;
	}

	public BuilddatasSearchVo getBuilddatasSearchVo() {
		return builddatasSearchVo;
	}

	public void setBuilddatasSearchVo(BuilddatasSearchVo builddatasSearchVo) {
		this.builddatasSearchVo = builddatasSearchVo;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public java.util.List<Long> getDeleteIds() {
		return deleteIds;
	}

	public void setIds(java.util.List<Long> deleteIds) {
		this.deleteIds = deleteIds;
	}

	public BuildLayout getBuildLayout() {
		return buildLayout;
	}

	public void setBuildLayout(BuildLayout buildLayout) {
		this.buildLayout = buildLayout;
	}

	public Long getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(Long buildingid) {
		this.buildingid = buildingid;
	}

	public String getBuildLayoutStr() {
		return buildLayoutStr;
	}

	public void setBuildLayoutStr(String buildLayoutStr) {
		this.buildLayoutStr = buildLayoutStr;
	}

	public List<BuildLayout> getBuildLayoutList() {
		return buildLayoutList;
	}

	public void setBuildLayoutList(List<BuildLayout> buildLayoutList) {
		this.buildLayoutList = buildLayoutList;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<HouseInfo> getHouseInfoList() {
		return houseInfoList;
	}

	public void setHouseInfoList(List<HouseInfo> houseInfoList) {
		this.houseInfoList = houseInfoList;
	}

	public Integer getHouseNum() {
		return houseNum;
	}

	public Integer getLettinghouseNum() {
		return lettinghouseNum;
	}

	public List<Object> getTypeColorList() {
		return typeColorList;
	}

	public String getPersonType() {
		return personType;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	public void setLettinghouseNum(Integer lettinghouseNum) {
		this.lettinghouseNum = lettinghouseNum;
	}

	public void setTypeColorList(List<Object> typeColorList) {
		this.typeColorList = typeColorList;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public List<LayoutTagVo> getLayoutTagList() {
		return layoutTagList;
	}

	public void setLayoutTagList(List<LayoutTagVo> layoutTagList) {
		this.layoutTagList = layoutTagList;
	}

	public List<ActualCompany> getActualCompanyList() {
		return actualCompanyList;
	}

	public void setActualCompanyList(List<ActualCompany> actualCompanyList) {
		this.actualCompanyList = actualCompanyList;
	}

	public List<KeyPlace> getKeyPlaceList() {
		return keyPlaceList;
	}

	public void setKeyPlaceList(List<KeyPlace> keyPlaceList) {
		this.keyPlaceList = keyPlaceList;
	}

	public Integer getActualCompanyNum() {
		return actualCompanyNum;
	}

	public Integer getKeyPlaceNum() {
		return keyPlaceNum;
	}

	public List<HouseInfo> getNotSelectHouseInfoList() {
		return notSelectHouseInfoList;
	}

	public void setNotSelectHouseInfoList(List<HouseInfo> notSelectHouseInfoList) {
		this.notSelectHouseInfoList = notSelectHouseInfoList;
	}

}
