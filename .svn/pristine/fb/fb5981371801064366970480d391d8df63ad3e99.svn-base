package com.tianque.plugin.dataManage.base;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.facade.organization.OrganizationServiceFacade;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.base.vo.ClaimResult;
import com.tianque.plugin.dataManage.base.vo.ClaimResultList;
import com.tianque.plugin.dataManage.base.vo.SearchVo;
import com.tianque.plugin.dataManage.population.handicappedTemp.domain.HandicappedTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public abstract class AbstractDataManageController<T extends BaseDomain, Target extends BaseDomain>
		extends BaseAction {
	private Logger logger = Logger
			.getLogger(AbstractDataManageController.class);

	private AbstractDataManageService<T, Target> abstractDataManageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private SearchVo searchVo;// 搜索对象
	protected T temp;// 公用的传入对象
	protected T population;// 人口页面专用对象(很多包含的原页面采用population对象，对此无法更改只能用population)
	protected Target target;
	private String tempClassName;// 用于区分是哪个temp
	private String actualClassName;// 用于区分是哪个基础对象,通过这个可以直接引入对应的基本信息的修改页面
	private String tempIds;// 选中的所有认领数据Id，以，区分
	private Long targetOrgId;// 要认领的目标网格的id
	private static String previousOrNextOrSave; // 数据认领中用来判断是否是保存事件
	private ClaimResultList claimResultList;// 认领结果的list
	// 人口会用到，从哪个地方来的，从list页面点击修改的话只会修改temp中数据，从claim结果来的话会同时修改基础表
	private String from;
	public static final String FROM_LIST = "FROM_LIST";
	public static final String FROM_CLAIM = "FROM_CLAIM";
	public static final String FROM_STEP = "FROM_STEP";// 分步认领
	public static final String FROM_CLAIM_TO_ORG = "FROM_CLAIM_TO_ORG";// 直接认领到网格
	/** 区分人房场所三大类 */
	private String bigType;
	private String viewBusinessPage;// 人口的业务信息的查看页面，是在基础信息中的路径 stepClaimDispatch
	private String updatePage;// 修改页面
	/** 对比列表 */
	private List compareList = new ArrayList();
	private Long tempId;
	private Long targetId;
	private String ajaxUrl;// 页面上唯一性属性验证的路径
	private Long organizationId;// 页面上的组织id
	private Boolean hasDuplicate;// 是否有重复数据
	private Long districtOrgId;// 数据在县区的orgId
	private String uniqueValue;// 数据唯一的参考值
	/** 部分人员的类型集合 */
	protected String compareStaffTypeIds;
	@Autowired
	private PropertyDictService propertyDictService;
	private Long currentOrgId;
	private Long currentOrgIdForDistrict;
	@Autowired
	private OrganizationServiceFacade organizationServiceFacade;
	@Autowired
	private HandicappedService handicappedService;
	private Map<Long, String> disablilityLevelMap;
	private Handicapped handicapped;

	/**
	 * 分页查询，列表显示
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findTemps", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage" }) })
	public String findTemps() throws Exception {
		searchVo.setMode(mode);
		bigType = getBigType();
		searchVo.setBigType(bigType);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				abstractDataManageService.findTemps(getRuntimeType(), searchVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}

	/** 测试认领处修改上一步下一步页面跳转 */
	@Actions({
			@Action(value = "dispatch", results = {
					@Result(name = "updateBase", location = "/template/dataManage/population/common/commonPopulationDlg.ftl"),
					@Result(name = "updateBusiness", location = "/template/dataManage/population/common/commonBusinessPopulation.ftl"),
					@Result(name = "updateBaseHouse", location = "/template/dataManage/location/common/actualHouseTempDlg.ftl"),
					@Result(name = "updateRentalHouse", location = "/template/dataManage/location/common/rentalHouseTempDlg.ftl"),
					@Result(name = "view", location = "/template/dataManage/population/common/commonViewTabDlg.ftl"),
					@Result(name = "updateLocation", location = "/template/dataManage/location/common/commonLocationSubmitFrame.ftl"),
					@Result(name = "updateDustbin", location = "/template/dataManage/location/common/commonDustbinSubmitFrame.ftl"),
					@Result(name = "updateBuilddatas", location = "/template/dataManage/location/builddatasTempManage/builddatasTempDlg.ftl"),
					@Result(name = "locationView", location = "/template/dataManage/location/common/locationView.ftl"),
					@Result(name = "search", location = "/template/dataManage/common/searchDlg.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }),
					@Result(name = "viewBaseData", location = "/template/dataManage/location/common/actualHouseTempView.ftl"),
					@Result(name = "viewRentalData", location = "/template/dataManage/location/common/rentalHouseTempView.ftl") }),
			@Action(value = "viewCommonPopulation", results = { @Result(name = "view", location = "/template/dataManage/population/common/viewCommonPopulation.ftl") }),
			@Action(value = "viewBussiness", results = { @Result(name = "view", location = "/template/dataManage/population/common/viewBussiness.ftl") }) })
	public String dispatch() throws Exception {
		tempClassName = StringUtil.firstCharLowCase(getRuntimeType()
				.getSimpleName());

		ajaxUrl = "hasDuplicate";
		if (DialogMode.SEARCH_MODE.equals(mode)) {// updater wanggz 将字符串search改为常量控制
			return DialogMode.SEARCH_MODE;
		}

		if (null != id) {
			population = abstractDataManageService.getTempById(
					getRuntimeType(), id);

			Organization org = ReflectionUtil.getTargetOrganization(population);
			if (null != targetOrgId) {
				org.setId(targetOrgId);
			}
			org.setOrgName(ControllerHelper.getOrganizationRelativeName(
					org.getId(), organizationDubboService));

			viewBusinessPage = DataManageBaseInfoUtil
					.getBaseinfoViewPage(StringUtil
							.firstCharLowCase(tempClassName));

			if (population instanceof HandicappedTemp
					&& ("updateBusiness".equals(mode) || DialogMode.VIEW_MODE
							.equals(mode))) {
				handicapped = new Handicapped();
				handicapped
						.setDisabilityTypes(propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE));
				handicapped
						.setDisabilitys(propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.DISABILITY_STATUS));
				HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
				handicappedSdisabilityType.setId(population.getId());
				handicappedSdisabilityType.setDataManage(true);
				List<HandicappedSdisabilityType> hdtList = handicappedService
						.queryDisabilityLevelById(handicappedSdisabilityType);
				if (disablilityLevelMap == null) {
					disablilityLevelMap = new HashMap<Long, String>();
				}
				disablilityLevelMap.clear();
				for (HandicappedSdisabilityType hst : hdtList) {
					if (DialogMode.VIEW_MODE.equals(mode)) {
						PropertyDict pd = propertyDictService
								.getPropertyDictById(hst.getHandicappedslevel());
						String displayName = "";
						if (pd != null) {
							displayName = pd.getDisplayName();
						}
						disablilityLevelMap.put(hst.getHandicappedstype(),
								displayName);
					} else {
						disablilityLevelMap.put(hst.getHandicappedstype(),
								hst.getHandicappedslevel() + "");
					}
				}
				handicapped = (Handicapped) population;
			}
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			if (DataManageBaseInfoTypes.LOCATION.equals(getBigType())
					|| DataManageBaseInfoTypes.HOUSE.equals(getBigType())
					|| DataManageBaseInfoTypes.DUSTBIN.equals(getBigType())
					|| DataManageBaseInfoTypes.BUILDDATAS.equals(getBigType())) {
				return "locationView";
			}
			return DialogMode.VIEW_MODE;
		}
		if ("viewBaseData".equals(mode) || "viewRentalData".equals(mode)) {
			return mode;
		}
		// 房屋或人口修改
		if ("updateBase".equals(mode) || "updateBusiness".equals(mode)
				|| "updateBaseHouse".equals(mode)
				|| "updateRentalHouse".equals(mode)) {
			actualClassName = tempClassName.replace("Temp", "");
			return mode;
		}
		// 场所修改
		if ("updateLocation".equals(mode)) {
			// 得到修改页面
			updatePage = DataManageBaseInfoUtil.getUpdatePage(StringUtil
					.firstCharLowCase(tempClassName));
			return mode;
		}
		if ("updateDustbin".equals(mode)) {
			updatePage = DataManageBaseInfoUtil.getUpdatePage(StringUtil
					.firstCharLowCase(tempClassName));
			return mode;
		}
		if ("updateBuilddatas".equals(mode)) {
			updatePage = DataManageBaseInfoUtil.getUpdatePage(StringUtil
					.firstCharLowCase(tempClassName));
			return mode;
		}
		return ERROR;
	}

	/** 测试认领处的基础修改 */
	@Action(value = "updateTempBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateTempBase() throws Exception {
		population = abstractDataManageService.updateTempBase(getRuntimeType(),
				population);
		return SUCCESS;
	}

	/** 测试认领处的业务修改 */
	@Action(value = "updateTempBusiness", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "claimSuccess", type = "json", params = { "root",
					"claimResultList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateTempBusiness() throws Exception {
		population = abstractDataManageService.updateTempBusiness(
				getRuntimeType(), population);
		if ("save".equals(previousOrNextOrSave)) {
			return updateAndClaim(temp, true);
		} else {
			return "claimSuccess";
		}
	}

	private String updateAndClaim(T temp, boolean fromRepeat) throws Exception {
		if (null != from && FROM_CLAIM.equals(from)) {
			ClaimResult<T> result = abstractDataManageService.updateAndClaim(
					population, fromRepeat);
			buildClaimResultListByClaimResult(result, true);
			return "claimSuccess";
		}
		return SUCCESS;
	}

	/** 测试认领处的场所信息修改 */
	@Action(value = "updateLocationTempBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "claimSuccess", type = "json", params = { "root",
					"claimResultList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateLocationTempBase() throws Exception {
		population = abstractDataManageService.updateLocationTempBase(
				getRuntimeType(), population);
		return updateAndClaim(temp, true);
	}

	@Action(value = "updateDustbinTempBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDustbinTempBase() throws Exception {
		population = abstractDataManageService.updateDustbinTempBase(
				getRuntimeType(), population);
		return updateAndClaim(temp, true);
	}

	@Action(value = "updateBuilddatasTempBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateBuilddatasTempBase() throws Exception {
		population = abstractDataManageService.updateBuilddatasTempBase(
				getRuntimeType(), population);
		return updateAndClaim(temp, true);
	}

	// /**
	// * 验证同一个区县下是否有名称相同的单位、场所
	// *
	// * @return
	// */
	// @Action(value = "hasDuplicateInternetBarLocation", results = {
	// @Result(name = "success", type = "json", params = { "root",
	// "hasDuplicateLocation" }),
	// @Result(name = "error", type = "json", params = { "root",
	// "errorMessage" }) })
	// public String hasDuplicateLocation() {
	// // if (null == organizationId || null == population) {
	// // errorMessage = "组织机构ID,或location为空";
	// // return ERROR;
	// // }
	// // hasDuplicateLocation =
	// // abstractDataManageService.hasDuplicateLocation(
	// // population, population.getId());
	// // return SUCCESS;
	// }

	/**
	 * 认领页面跳转（org选择）
	 * 
	 * @return
	 */
	@Action(value = "claimDispatch", results = {
			@Result(name = "success", location = "/template/dataManage/claimOrgSelectDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String claimDispatch() throws Exception {

		// try {
		// temp = abstractDataManageService.getTempById(getRuntimeType(),
		// analyzeTempIds()[0]);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return SUCCESS;
	}

	/**
	 * 删除数据
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "deleteTempByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteTempByIds() throws Exception {
		abstractDataManageService.deleteTempByIds(getRuntimeType(),
				analyzeTempIds());
		return SUCCESS;
	}

	/**
	 * 认领数据（在org选择完后的操作）
	 * 
	 * @return
	 */
	@Action(value = "claimTempByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"claimResultList" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String claimTempByIds() throws Exception {

		claimResultList = abstractDataManageService.claimTempByIds(
				getRuntimeType(), analyzeTempIds(), targetOrgId);
		return SUCCESS;
	}

	@Action(value = "compareRepeatData", results = { @Result(name = "success", type = "json", params = {
			"root", "claimResultList" }) })
	public String compareRepeatData() throws Exception {
		temp.setId(tempId);
		if (compareStaffTypeIds != null && !(compareStaffTypeIds.equals(""))
				&& compareStaffTypeIds.split(",").length > 0) {
			Method method = temp.getClass().getSuperclass()
					.getMethod("setStaffType", List.class);
			method.invoke(temp,
					this.LongToPropertyDict(compareStaffTypeIds.split(",")));
		}
		ClaimDetail claimDetail = ReflectionUtil.getTargetClaimDetail(temp);
		claimDetail.setInId(targetId);
		Method method = temp.getClass().getDeclaredMethod("setClaimDetail",
				ClaimDetail.class);
		method.invoke(temp, claimDetail);
		ClaimResult<T> reuslt = abstractDataManageService.updateAndClaim(temp,
				true);

		buildClaimResultListByClaimResult(reuslt, true);

		return SUCCESS;
	}

	private void buildClaimResultListByClaimResult(ClaimResult<T> reuslt,
			boolean fromRepeat) {
		claimResultList = new ClaimResultList();
		List successlist = new ArrayList();
		List errorlist = new ArrayList();
		if (reuslt.getClaimState().getSuccessful()) {
			successlist.add(reuslt);
			if (fromRepeat) {
				if (reuslt.getTemp() instanceof People) {
					abstractDataManageService.updateTempBase(getRuntimeType(),
							reuslt.getTemp());
					abstractDataManageService.updateTempBusiness(
							getRuntimeType(), reuslt.getTemp());
				} else {
					abstractDataManageService.updateLocationTempBase(
							getRuntimeType(), reuslt.getTemp());
				}
			}
		} else {
			errorlist.add(reuslt);
		}
		claimResultList.setSuccessList(successlist);
		claimResultList.setErrorList(errorlist);
	}

	@Action(value = "stepClaimTempByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"claimResultList" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String stepClaimTempByIds() throws Exception {

		abstractDataManageService.stepClaimTempByIds(getRuntimeType(),
				analyzeTempIds(), targetOrgId);
		return SUCCESS;
	}

	@Action(value = "compareClaim", results = { @Result(name = "success", location = "/template/dataManage/common/compareClaimDlg.ftl") })
	public String compareClaim() throws Exception {
		// 1.获得temp数据
		temp = abstractDataManageService.getTempById(getRuntimeType(), tempId);
		// 2.获得基础表数据
		Organization org = new Organization();
		org.setId(targetOrgId);
		ReflectionUtil.setTargetOrganization(temp, org);
		TargetDataVo targetDataVo = abstractDataManageService
				.getTargetDataVo(temp);
		// ClaimDetail claimDetail =
		// ReflectionUtil.getTargetClaimDetail(temp);
		// targetId = claimDetail.getInId();
		targetId = targetDataVo.getId();
		target = getTargetById(targetId);
		// 3.获得对比list数据
		compareList = getCompareList(temp, target);
		return SUCCESS;
	}

	@Action(value = "hasDuplicate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicate" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicate() throws Exception {
		if (null == uniqueValue) {
			hasDuplicate = Boolean.FALSE;
		} else {
			hasDuplicate = abstractDataManageService.hasDuplicate(
					getRuntimeType(), tempId, districtOrgId, uniqueValue);
		}
		return SUCCESS;
	}

	/**
	 * 撤销认领数据
	 * 
	 * @return
	 */
	@Action(value = "updateTempForUnClaim", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateTempForUnClaim() throws Exception {
		if (!StringUtil.isStringAvaliable(tempIds)) {
			this.errorMessage = "参数不正确!";
			return ERROR;
		}
		abstractDataManageService.unDoClaimTempByIds(getRuntimeType(),
				analyzeTempIds());
		return SUCCESS;
	}

	@Action(value = "getDistrictOrgIdForSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "currentOrgIdForDistrict" }) })
	public String getDistrictOrgIdForSearch() throws Exception {
		currentOrgIdForDistrict = organizationServiceFacade
				.getDistrictorgId(currentOrgId);
		return SUCCESS;
	}

	public abstract Target getTargetById(Long id);

	public abstract List getCompareList(T temp, Target target);

	/** 获得类型,想findTemps这种方法不会传泛型对象，此时需要这个方法传入之后需要的类型 */
	private Class<T> getRuntimeType() {
		Class<T> entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
		}
		return entityClass;
	}

	/** 将前台传入的多个id分解成数组 */
	private Long[] analyzeTempIds() {
		String[] deleteId = tempIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
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

	/** 定义实口、业务、场所各自的service类 */
	public void replaceDataManageService(
			AbstractDataManageService<T, Target> abstractDataManageService) {
		this.abstractDataManageService = abstractDataManageService;
	}

	public SearchVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}

	public String getTempClassName() {
		return tempClassName;
	}

	public void setTempClassName(String tempClassName) {
		this.tempClassName = tempClassName;
	}

	public String getActualClassName() {
		return actualClassName;
	}

	public void setActualClassName(String actualClassName) {
		this.actualClassName = actualClassName;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public AbstractDataManageService<T, Target> getAbstractDataManageService() {
		return abstractDataManageService;
	}

	public void setAbstractDataManageService(
			AbstractDataManageService<T, Target> abstractDataManageService) {
		this.abstractDataManageService = abstractDataManageService;
	}

	public String getTempIds() {
		return tempIds;
	}

	public void setTempIds(String tempIds) {
		this.tempIds = tempIds;
	}

	public Long getTargetOrgId() {
		return targetOrgId;
	}

	public void setTargetOrgId(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	public ClaimResultList getClaimResultList() {
		return claimResultList;
	}

	public void setClaimResultList(ClaimResultList claimResultList) {
		this.claimResultList = claimResultList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getBigType() {
		return bigType;
	}

	public void setBigType(String bigType) {
		this.bigType = bigType;
	}

	public String getViewBusinessPage() {
		return viewBusinessPage;
	}

	public void setViewBusinessPage(String viewBusinessPage) {
		this.viewBusinessPage = viewBusinessPage;
	}

	public List getCompareList() {
		return compareList;
	}

	public void setCompareList(List compareList) {
		this.compareList = compareList;
	}

	public Long getTempId() {
		return tempId;
	}

	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}

	public String getUpdatePage() {
		return updatePage;
	}

	public void setUpdatePage(String updatePage) {
		this.updatePage = updatePage;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Boolean getHasDuplicate() {
		return hasDuplicate;
	}

	public void setHasDuplicate(Boolean hasDuplicate) {
		this.hasDuplicate = hasDuplicate;
	}

	public Long getDistrictOrgId() {
		return districtOrgId;
	}

	public void setDistrictOrgId(Long districtOrgId) {
		this.districtOrgId = districtOrgId;
	}

	public String getUniqueValue() {
		return uniqueValue;
	}

	public void setUniqueValue(String uniqueValue) {
		this.uniqueValue = uniqueValue;
	}

	private List<PropertyDict> LongToPropertyDict(String[] ids) {
		List<PropertyDict> staffTypeList = new ArrayList<PropertyDict>();
		for (String id : ids) {
			staffTypeList.add(propertyDictService.getPropertyDictById(new Long(
					id)));
		}
		return staffTypeList;
	}

	public String getCompareStaffTypeIds() {
		return compareStaffTypeIds;
	}

	public void setCompareStaffTypeIds(String compareStaffTypeIds) {
		this.compareStaffTypeIds = compareStaffTypeIds;
	}

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public Long getCurrentOrgIdForDistrict() {
		return currentOrgIdForDistrict;
	}

	public void setCurrentOrgIdForDistrict(Long currentOrgIdForDistrict) {
		this.currentOrgIdForDistrict = currentOrgIdForDistrict;
	}

	public Map<Long, String> getDisablilityLevelMap() {
		return disablilityLevelMap;
	}

	public void setDisablilityLevelMap(Map<Long, String> disablilityLevelMap) {
		this.disablilityLevelMap = disablilityLevelMap;
	}

	public static String getPreviousOrNextOrSave() {
		return previousOrNextOrSave;
	}

	public static void setPreviousOrNextOrSave(String previousOrNextOrSave) {
		AbstractDataManageController.previousOrNextOrSave = previousOrNextOrSave;
	}

	public Handicapped getHandicapped() {
		return handicapped;
	}

	public void setHandicapped(Handicapped handicapped) {
		this.handicapped = handicapped;
	}

}
