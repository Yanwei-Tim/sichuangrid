package com.tianque.baseInfo.rentalHouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.excel.definition.HouseContext;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PropertyUtil;

@Controller("rentalHouseController")
@Scope("prototype")
@Transactional
public class RentalHouseController extends SearchBaseAction {

	@Autowired
	private RentalHouseService rentalHouseInfoService;
	@Autowired
	private ActualHouseService houseInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TemporaryPopulationService temporaryPopulationService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;

	private RentalHouse houseInfo;

	private HouseInfo actualHouse;

	private Object population;

	private Long orgId;

	private String address;

	private String houseIds;

	private List<Long> houseInfoIds;

	private SearchHouseInfoVo searchHouseInfoVo;

	private Boolean hasDuplicateHouseInfo;

	private boolean pageOnly;

	private String dailogName;

	private Boolean hasActualPopulation = true;
	private Long houseId;
	private String type;
	private String ids;
	// 新增修改默认
	private Long organizationId;
	private String operateSource;

	// 新增、修改跳转
	public String prepareDispatch() throws Exception {
		// 点击下一步时，type=ACTUALHOUSE
		if (BaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {
			HouseInfo hs = new HouseInfo();
			hs = this.houseInfoService.getHouseInfoById(id);
			houseInfo = this.rentalHouseInfoService.getHouseInfoByHouseId(hs
					.getId());
			// 当实有房屋关联出租房时，并且点击左边标签页时，添加出租房基本信息并更新实有房屋
			if (houseInfo == null && hs != null) {
				houseInfo = new RentalHouse();
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						houseInfo, hs, new String[] { "id" });
				houseInfo.setHouseId(hs.getId());
				houseInfo = this.rentalHouseInfoService.addHouseInfo(houseInfo);
				hs.setIsRentalHouse(Boolean.TRUE);
				houseInfoService.updateHouseInfo(hs);
			}
		} else if (BaseInfoTables.RENTALHOUSE_KEY.equals(type)) {
			// 点击左边标签页面时，type=RENTALHOUSE
			houseInfo = this.rentalHouseInfoService.getHouseInfoById(id);
		}
		if (houseInfo == null) {
			houseInfo = new RentalHouse();
			houseInfo.setOrganization(new Organization());
			houseInfo.getOrganization().setId(organizationId);
		}
		houseInfo.setOrganization(organizationDubboService.getFullOrgById(houseInfo
				.getOrganization().getId()));

		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * id加密修改（实有房屋修改有用到）
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String prepareDispatchByEncrypt() throws Exception {
		// 点击下一步时，type=ACTUALHOUSE
		if (BaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {
			HouseInfo hs = new HouseInfo();
			hs = this.houseInfoService.getHouseInfoById(id);
			houseInfo = this.rentalHouseInfoService.getHouseInfoByHouseId(hs
					.getId());
			// 当实有房屋关联出租房时，并且点击左边标签页时，添加出租房基本信息并更新实有房屋
			if (houseInfo == null && hs != null) {
				houseInfo = new RentalHouse();
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						houseInfo, hs, new String[] { "id" });
				houseInfo.setHouseId(hs.getId());
				houseInfo = this.rentalHouseInfoService.addHouseInfo(houseInfo);
				hs.setIsRentalHouse(Boolean.TRUE);
				houseInfoService.updateHouseInfo(hs);
			}
		} else if (BaseInfoTables.RENTALHOUSE_KEY.equals(type)) {
			// 点击左边标签页面时，type=RENTALHOUSE
			houseInfo = this.rentalHouseInfoService.getHouseInfoById(id);
		}
		if (houseInfo == null) {
			houseInfo = new RentalHouse();
			houseInfo.setOrganization(new Organization());
			houseInfo.getOrganization().setId(organizationId);
		}
		houseInfo.setOrganization(organizationDubboService.getFullOrgById(houseInfo
				.getOrganization().getId()));

		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			houseInfo = this.rentalHouseInfoService.getHouseInfoById(houseInfo
					.getId());
		}
		return getMode();
	}

	public String dispatchRentalHouseByHouseId() throws Exception {
		houseInfo = this.rentalHouseInfoService.getHouseInfoByHouseId(houseInfo
				.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * id加密查看出租信息（实有房屋有用到）
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String dispatchRentalHouseByHouseIdByEncrypt() throws Exception {
		houseInfo = this.rentalHouseInfoService.getHouseInfoByHouseId(houseInfo
				.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	public String maintainRentalHouse() throws Exception {
		houseInfo.setIsRentalHouse(true);
		houseInfo.setHouseOperateSource(operateSource);
		houseInfo = this.rentalHouseInfoService
				.updateHouseBusinessInfo(houseInfo);
		return SUCCESS;
	}

	/**
	 * 新增出租房
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addActualHouse")
	public String addHouseInfoAndRentalHouse() throws Exception {
		HouseInfo tmpRentalHouse = (HouseInfo) temporaryPopulationService
				.getPopulationById(cacheId.get("houseInfoId"));
		HouseInfo temp = houseInfoService.getHouseInfoByHouseCodeAndOrgId(
				tmpRentalHouse.getHouseCode(), tmpRentalHouse.getOrganization()
						.getId());
		if (temp == null) {
			tmpRentalHouse = houseInfoService.addHouseInfo(tmpRentalHouse);
		} else {
			temp.setIsRentalHouse(true);
			PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, temp,
					tmpRentalHouse);
			tmpRentalHouse = houseInfoService.updateHouseInfo(temp);
		}
		PropertyUtil.copyPropertiesWithOutRecursion(HouseInfo.class, houseInfo,
				tmpRentalHouse);
		houseInfo.setImgUrl(tmpRentalHouse.getImgUrl());
		houseInfo = this.rentalHouseInfoService.addHouseInfo(houseInfo);
		houseInfoService.getHouseInfoByHouseCodeAndOrgId(
				houseInfo.getHouseCode(), houseInfo.getOrganization().getId())
				.setIsRentalHouse(true);
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeNameList(houseInfo
						.getOrganization().getId(), organizationDubboService,
						houseInfo.getOrganization().getId()));
		if ("actualHouseMaintanceDialog".equals(dailogName))
			houseInfo.setId(tmpRentalHouse.getId());
		return SUCCESS;
	}

	/**
	 * 查看出租房信息
	 * 
	 * @return
	 */
	public String viewHouseInfo() throws Exception {
		houseInfo.setHouseOperateSource(NewBaseInfoTables.RENTALHOUSE_KEY);
		houseInfo = this.rentalHouseInfoService.getHouseInfoById(houseInfo
				.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 新增出租房信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addRentalHouse")
	public String addHouseInfo() throws Exception {
		houseInfo.setHouseOperateSource(operateSource);
		houseInfo = rentalHouseInfoService.addHouseInfo(houseInfo);
		houseInfo.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(houseInfo.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 修改出租房信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateRentalHouse")
	public String updateHouseInfo() throws Exception {
		RentalHouse renHouseInfo = houseInfo;
		HouseInfo savedHouseInfo = houseInfoService.getHouseInfoById(houseInfo
				.getHouseId());
		PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, renHouseInfo,
				savedHouseInfo);

		renHouseInfo.setHouseOperateSource(operateSource);

		houseInfo = this.rentalHouseInfoService
				.updateHouseBusinessInfo(renHouseInfo);
		if ("actualHouseMaintanceDialog".equals(dailogName)) {
			HouseInfo actualHouse = houseInfoService.getHouseInfoById(houseInfo
					.getHouseId());
			PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
					houseInfo, actualHouse);
			houseInfo.setId(actualHouse.getId());
		}
		updateActualHouse();
		houseInfo.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(houseInfo.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	private void updateActualHouse() {
		HouseInfo savedHouseInfo = houseInfoService.getHouseInfoById(houseInfo
				.getHouseId());
		savedHouseInfo.setHouseOperateSource(operateSource);
		savedHouseInfo.setIsRentalHouse(Boolean.TRUE);
		houseInfoService.updateHouseInfo(savedHouseInfo);
	}

	/**
	 * 删除出租房信息
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteRentalHouse")
	public String deleteHouseInfo() throws Exception {
		rentalHouseInfoService.deleteRentalHouseByIds(analyzeHouseIds());
		return SUCCESS;
	}

	private List<Long> proccHouseIds() {
		String[] deleteId = houseIds.split(",");
		List<Long> idList;
		if (("").equals(deleteId[0])) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		return idList;
	}

	private Long[] analyzeHouseIds() {
		String[] deleteId = houseIds.split(",");
		List<Long> idList;
		if (("").equals(deleteId[0])) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	/**
	 * 出租房信息检索
	 * 
	 * @return
	 */
	public String searchHouseInfo() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		searchHouseInfoVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<HouseInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(rentalHouseInfoService
						.searchHouseInfos(page, rows, sidx, sord,
								searchHouseInfoVo), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 分页查询出租房信息
	 * 
	 * @return
	 */
	public String findHouseInfosForPage() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		PageInfo<HouseInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(rentalHouseInfoService
						.findHouseInfosForPage(orgId, page, rows, sidx, sord),
						organizationDubboService, new String[] { "organization" },
						orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 判断房屋编号是否存在
	 * 
	 * @return
	 */
	public String hasDuplicateHouseInfo() throws Exception {
		if (null == orgId) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicateHouseInfo = rentalHouseInfoService.hasDuplicateHouseInfo(
				orgId, houseInfo.getHouseCode(), houseInfo.getId());
		return SUCCESS;
	}

	public String hasDuplicateHouseInfoForAddress() throws Exception {
		if (null == orgId) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicateHouseInfo = rentalHouseInfoService
				.hasDuplicateHouseInfoForAddress(orgId, houseInfo.getAddress(),
						houseInfo.getId());
		return SUCCESS;
	}

	/**
	 * 导出出租房信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadRentalHouse")
	public String downloadRentalHouse() throws Exception {
		if (null == orgId) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (null == searchHouseInfoVo)
			searchHouseInfoVo = new SearchHouseInfoVo();
		searchHouseInfoVo.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		List<RentalHouse> houseInfoList = getNeedExportDatas(page);
		String[][] excelRentalColumArray = HouseContext
				.getRentalHouseInfoPropertyArray();
		inputStream = ExcelExportHelper.exportDateToExcel(
				excelRentalColumArray, houseInfoList);
		downloadFileName = new String("出租房信息".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModelType.TEMPORARYRESIDENT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出部分"
								+ ModelType.TEMPORARYRESIDENT + "信息",
						ObjectToJSON.convertJSON(new HouseInfo()));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downloadRentalHouse")
	public void downloadExcelExportAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchHouseInfoVo == null) {
			searchHouseInfoVo = new SearchHouseInfoVo();
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		searchHouseInfoVo.setOrgInternalCode(organization.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = rentalHouseInfoService.getCount(searchHouseInfoVo);
			String[][] excelDefines = HouseContext.getHouseInfoPropertyArray();
			exportDataAll(count, excelDefines, "出租房信息");
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModelType.TEMPORARYRESIDENT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出全部"
								+ ModelType.TEMPORARYRESIDENT + "信息",
						ObjectToJSON.convertJSON(new HouseInfo()));
	}

	/**
	 * 出租房注销或取消注销
	 * 
	 * @return
	 */
	public String updateEmphasiseById() throws Exception {
		rentalHouseInfoService.updateEmphasiseByIds(proccHouseIds(),
				houseInfo.getIsEmphasis());
		return SUCCESS;
	}

	/**
	 * id加密 出租房注销或取消注销
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String updateEmphasiseByEncryptId() throws Exception {
		rentalHouseInfoService.updateEmphasiseByIds(proccHouseIds(),
				houseInfo.getIsEmphasis());
		return SUCCESS;
	}

	@EncryptAnnotation
	public String houseHasActualPopulation() throws Exception {
		String[] deleteId = houseIds.split(",");
		for (int i = 0; i < deleteId.length; i++) {
			RentalHouse house = rentalHouseInfoService.getHouseInfoById(Long
					.valueOf(deleteId[i]));

			// fateson add 以前使用 house.getHouseCode() 查询 是否存在人口---现在使用 房屋地址
			/*
			 * List<HouseHasActualPopulation> houseHasActualPopulations =
			 * houseHasActualPopulationService
			 * .getHouseHasActualPopulationByHouseId(houseInfoService
			 * .getHouseInfoByHouseCodeAndOrgId( house.getHouseCode(),
			 * house.getOrganization().getId()).getId());
			 */

			List<HouseHasActualPopulation> houseHasActualPopulations = houseHasActualPopulationService
					.getHouseHasActualPopulationByHouseId(houseInfoService
							.getHouseInfoById(house.getHouseId()).getId());

			// fateson add 这行代码 在删除多行的时候有问题
			// if (null == houseHasActualPopulations
			// || houseHasActualPopulations.size() == 0) {
			// hasActualPopulation = false;
			// return SUCCESS;
			// }

			if (null != houseHasActualPopulations
					&& (!houseHasActualPopulations.isEmpty())) {
				hasActualPopulation = true;
				break;// 有一个房屋包含了人员 不用再往下面判断了
			} else {
				hasActualPopulation = false;
			}

		}
		return SUCCESS;
	}

	public String addHouseForMobile() throws Exception {
		if (!houseInfo.getIsRentalHouse()) {
			actualHouse = houseInfoService.addHouseInfo(houseInfo);
			return "actualHouse";
		} else {
			HouseInfo temp = houseInfoService.getHouseInfoByHouseCodeAndOrgId(
					houseInfo.getHouseCode(), houseInfo.getOrganization()
							.getId());
			if (temp == null) {
				HouseInfo rh = houseInfoService.addHouseInfo(houseInfo);
				houseInfo.setHouseId(rh.getId());
			} else {
				temp.setIsRentalHouse(true);
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, temp,
						houseInfo);
				houseInfoService.updateHouseInfo(temp);
			}
			houseInfo = rentalHouseInfoService.addHouseInfo(houseInfo);
			return SUCCESS;
		}

	}

	public String updateHouseForMobile() throws Exception {
		if (!houseInfo.getIsRentalHouse()) {
			actualHouse = houseInfoService.updateHouseInfo(houseInfo);
			return "actualHouse";
		} else {
			RentalHouse temp = rentalHouseInfoService
					.getRentalHouseInfoByHouseId(houseInfo.getId());
			if (null == temp) {
				HouseInfo savedHouseInfo = houseInfoService
						.getHouseInfoById(houseInfo.getId());
				PropertyUtil.copyPropertiesWithOutRecursion(HouseInfo.class,
						houseInfo, savedHouseInfo);
				houseInfo.setHouseId(savedHouseInfo.getId());
				houseInfo = this.rentalHouseInfoService.addHouseInfo(houseInfo);
				savedHouseInfo.setIsRentalHouse(Boolean.TRUE);
				houseInfoService.updateHouseInfo(savedHouseInfo);
				return SUCCESS;
			} else {
				HouseInfo saveHouseInfo = houseInfoService
						.getHouseInfoById(houseInfo.getId());
				PropertyUtil.copyPropertiesWithOutRecursion(HouseInfo.class,
						saveHouseInfo, houseInfo);
				houseInfoService.updateHouseInfo(saveHouseInfo);
				RentalHouse savedRentalHouse = rentalHouseInfoService
						.getRentalHouseInfoByHouseId(houseInfo.getId());
				PropertyUtil.copyPropertiesWithRecursion(RentalHouse.class,
						savedRentalHouse, houseInfo);
				houseInfo = rentalHouseInfoService
						.updateHouseBaseInfo(savedRentalHouse);
				houseInfo = rentalHouseInfoService
						.updateHouseBusinessInfo(savedRentalHouse);
				return SUCCESS;
			}
		}

	}

	public Boolean getHasDuplicateHouseInfo() {
		return hasDuplicateHouseInfo;
	}

	public void setHasDuplicateHouseInfo(Boolean hasDuplicateHouseInfo) {
		this.hasDuplicateHouseInfo = hasDuplicateHouseInfo;
	}

	public RentalHouse getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(RentalHouse houseInfo) {
		this.houseInfo = houseInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(String houseIds) {
		this.houseIds = houseIds;
	}

	public List<Long> getHouseInfoIds() {
		return houseInfoIds;
	}

	public void setHouseInfoIds(List<Long> houseInfoIds) {
		this.houseInfoIds = houseInfoIds;
	}

	public SearchHouseInfoVo getSearchHouseInfoVo() {
		return searchHouseInfoVo;
	}

	public void setSearchHouseInfoVo(SearchHouseInfoVo searchHouseInfoVo) {
		this.searchHouseInfoVo = searchHouseInfoVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Object getPopulation() {
		return population;
	}

	public void setPopulation(Object population) {
		this.population = population;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public Boolean getHasActualPopulation() {
		return hasActualPopulation;
	}

	public void setHasActualPopulation(Boolean hasActualPopulation) {
		this.hasActualPopulation = hasActualPopulation;
	}

	public HouseInfo getActualHouse() {
		return actualHouse;
	}

	public void setActualHouse(HouseInfo actualHouse) {
		this.actualHouse = actualHouse;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String shiftDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		ids = houseIds;
		houseInfo = new RentalHouse();
		houseInfo.setOrganization(organizationDubboService.getSimpleOrgById(orgId));
		return SUCCESS;
	}

	/**
	 * id加密房屋转移（实有房屋用到）
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String shiftDispatchByEncrypt() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		houseInfo = new RentalHouse();
		houseInfo.setOrganization(organizationDubboService.getSimpleOrgById(orgId));
		return SUCCESS;
	}

	public String shiftHouse() throws Exception {
		String[] id = houseIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		rentalHouseInfoService.shiftRentalHouse(ids, houseInfo
				.getOrganization().getId());
		return SUCCESS;
	}

	@Override
	public List getNeedExportDatas(int page) {
		// TODO Auto-generated method stub
		List<RentalHouse> list = new ArrayList<RentalHouse>();
		if (pageOnly) {
			list = rentalHouseInfoService.searchHouseInfos(page, rows, sidx,
					sord, searchHouseInfoVo).getResult();
		} else {
			list = rentalHouseInfoService
					.searchAllHouseInfos(searchHouseInfoVo);
		}
		return list;
	}

	public String getOperateSource() {
		return operateSource;
	}

	public void setOperateSource(String operateSource) {
		this.operateSource = operateSource;
	}

}
