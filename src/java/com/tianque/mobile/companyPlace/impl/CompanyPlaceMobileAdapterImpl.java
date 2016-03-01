package com.tianque.mobile.companyPlace.impl;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.controller.CompanyPlaceBusinessController;
import com.tianque.baseInfo.companyPlace.controller.CompanyPlaceController;
import com.tianque.baseInfo.companyPlace.controller.RouterController;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.ContaminationInfo;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVoForMobile;
import com.tianque.baseInfo.companyPlace.service.ContaminationInfoService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.companyPlace.CompanyPlaceMobileAdapter;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceRecord.controller.ServiceRecordController;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.service.KeyPlaceService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 
 * @ClassName: CompanyPlaceMobileAdapterImpl
 * @Description: TODO单位场所改造 ，手机端 实现类
 * @author wanggz
 * @date 2014-6-16 下午02:36:17
 */

@Transactional
@Scope("request")
@Controller("companyPlaceMobileAdapter")
@Namespace("/mobile/companyPlaceManage")
public class CompanyPlaceMobileAdapterImpl extends BaseMobileAction implements
		CompanyPlaceMobileAdapter {

	@Autowired
	private CompanyPlaceController companyPlaceController;
	private Organization org; // 组织机构ID
	private CompanyPlaceVo companyPlaceVo; // 查询对象
	private String modulKey;// 模块名称
	private CompanyPlace companyPlace; // 实体对象
	@Autowired
	private CompanyPlaceBusinessController companyPlaceBusinessController;
	private CompanyPlaceBusinessVO companyPlaceBusinessVO;
	private Long cid;
	private CompanyPlaceVoForMobile companyPlaceVoForMobile;
	@Autowired
	private RouterController routerForCompanyPlaceController;
	private ServiceMemberVo serviceMemberVo;
	private ServiceTeamMemberVo serviceTeamMemberVo;
	private ServiceMemberWithObject serviceMemberWithObject;
	private String objectIds;
	private String populationType;
	private ServiceRecordVo serviceRecordVo;
	@Autowired
	private ServiceRecordController serviceRecordController;
	private ServiceRecord serviceRecord;
	private String[] attachFiles;
	private String attachFile;
	private String[] attachFileNew;
	private String recordIds;
	@Autowired
	private KeyPlaceService keyPlaceService;
	private KeyPlace keyPlace;
	private Long id;
	private String type;
	@Autowired
	private ContaminationInfoService contaminationInfoService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	@Action(value = "findCompanyPlaceListForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findCompanyPlaceListForMobile() throws Exception {
		if (org == null || org.getId() == null || companyPlaceVo == null
				|| companyPlaceVo.getOrg() == null
				|| companyPlaceVo.getOrg().getId() == null || rows == null
				|| page == null || sidx == null || sord == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		companyPlaceController.setCompanyPlaceVo(companyPlaceVo);
		companyPlaceController.setOrg(org);
		companyPlaceController.setRows(rows);
		companyPlaceController.setPage(page);
		companyPlaceController.setSidx(sidx);
		companyPlaceController.setSord(sord);
		if (modulKey != null && !"".equals(modulKey)) {
			companyPlaceController.setModulKey(modulKey);
		}
		companyPlaceController.queryCompanyPlaceList();
		gridPage = companyPlaceController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 手机端 新增单位场所
	 */
	@Override
	@Action(value = "addCompanyPlaceForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Transactional
	public String addCompanyPlaceForMobile() throws Exception {
		if (companyPlace == null || companyPlace.getName() == null
				|| "".equals(companyPlace.getName())
				|| companyPlace.getAddress() == null
				|| "".equals(companyPlace.getAddress())
				|| companyPlace.getType() == null
				|| companyPlace.getType().getId() == null
				|| companyPlace.getClassifiCation() == null
				|| companyPlace.getClassifiCation().getId() == null
				|| companyPlace.getCategory() == null
				|| companyPlace.getCategory().getId() == null
				|| modulKey == null || "".equals(modulKey)
				|| companyPlace.getOrg() == null
				|| companyPlace.getOrg().getId() == null
				|| companyPlace.getClassifiCationEn() == null
				|| "".equals(companyPlace.getClassifiCationEn())
				||logisticsValidate()) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		
		companyPlaceController.setCompanyPlace(companyPlace);
		companyPlaceController.setModulKey(modulKey);
		companyPlaceController.addCompanyPlaceOperationForMobile();
		companyPlace = companyPlaceController.getCompanyPlace();
		if (companyPlaceBusinessVO != null) {
			if (companyPlace.getCid() != null) {
				companyPlaceBusinessController.setCompanyPlaceId(companyPlace
						.getCid());
			}
			companyPlaceBusinessController.setModulKey(modulKey);
			companyPlaceBusinessController.setMode(DialogMode.ADD_MODE);

			if (companyPlaceBusinessVO.getProKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getProKeyAttachFilesForMobile())) {
				String[] pFile = companyPlaceBusinessVO
						.getProKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setProKeyAttachFiles(pFile);
			}
			if (companyPlaceBusinessVO.getFireSafetyKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getFireSafetyKeyAttachFilesForMobile())) {
				String[] fFile = companyPlaceBusinessVO
						.getFireSafetyKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setFireSafetyKeyAttachFiles(fFile);
			}
			if (companyPlaceBusinessVO.getSecurityKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getSecurityKeyAttachFilesForMobile())) {
				String[] sFile = companyPlaceBusinessVO
						.getSecurityKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setSecurityKeyAttachFiles(sFile);
			}
			if (companyPlaceBusinessVO.getPollSourceAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getPollSourceAttachFilesForMobile())) {
				String[] poFile = companyPlaceBusinessVO
						.getPollSourceAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setPollSourceAttachFiles(poFile);
			}
			companyPlaceBusinessController
					.setCompanyPlaceBusinessVO(companyPlaceBusinessVO);
			companyPlaceBusinessController.saveCompanyPlaceBusinessForMobile();
		}
		return SUCCESS;
	}
	//寄递物流点的验证
	private boolean logisticsValidate(){
		PropertyDict dict=propertyDictService.
			getPropertyDictByDomainNameAndDictId(PropertyTypes.COMPANY_PLACE_CLASSIFICATION, companyPlace.getClassifiCation().getId());
		if(!dict.getDisplayName().equals(BaseInfoTables.NEW_LOGISTICS_DISPLAYNAME)){
			return false;
		}
		if(!StringUtil.isStringAvaliable(companyPlace.getUserName())){
			return true;
		}
		if(!StringUtil.isStringAvaliable(companyPlace.getTelePhone())){
			return true;
		}
		if(companyPlace.getOperatingBrand()==null){
			return true;
		}
		if(companyPlace.getOperatingMode()==null){
			return true;
		}
		return false;
	}
	/**
	 * 单位场所修改 手机端
	 */
	@Override
	@Action(value = "updateCompanyPlaceByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Transactional
	public String updateCompanyPlaceByIdForMobile() throws Exception {
		if (companyPlace == null || companyPlace.getName() == null
				|| "".equals(companyPlace.getName())
				|| companyPlace.getAddress() == null
				|| "".equals(companyPlace.getAddress())
				|| companyPlace.getType() == null
				|| companyPlace.getType().getId() == null
				|| companyPlace.getClassifiCation() == null
				|| companyPlace.getClassifiCation().getId() == null
				|| companyPlace.getCategory() == null
				|| companyPlace.getCategory().getId() == null
				|| modulKey == null || "".equals(modulKey)
				|| companyPlace.getOrg() == null
				|| companyPlace.getOrg().getId() == null
				|| companyPlace.getClassifiCationEn() == null
				|| "".equals(companyPlace.getClassifiCationEn())
				|| companyPlace.getBaseId() == null
				|| companyPlace.getCid() == null
				||logisticsValidate()) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		companyPlaceController.setModulKey(modulKey);
		companyPlaceController.setCompanyPlace(companyPlace);
		companyPlaceController.updateCompanyPlaceOperation();

		if (companyPlaceBusinessVO != null) {
			if (companyPlace.getCid() != null) {
				companyPlaceBusinessController.setCompanyPlaceId(companyPlace
						.getCid());
			}
			companyPlaceBusinessController.setModulKey(modulKey);
			companyPlaceBusinessController.setMode(DialogMode.EDIT_MODE);
			if (companyPlaceBusinessVO.getProKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getProKeyAttachFilesForMobile())) {
				String[] pFile = companyPlaceBusinessVO
						.getProKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setProKeyAttachFiles(pFile);
			}
			if (companyPlaceBusinessVO.getFireSafetyKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getFireSafetyKeyAttachFilesForMobile())) {
				String[] fFile = companyPlaceBusinessVO
						.getFireSafetyKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setFireSafetyKeyAttachFiles(fFile);
			}
			if (companyPlaceBusinessVO.getSecurityKeyAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getSecurityKeyAttachFilesForMobile())) {
				String[] sFile = companyPlaceBusinessVO
						.getSecurityKeyAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setSecurityKeyAttachFiles(sFile);
			}
			if (companyPlaceBusinessVO.getPollSourceAttachFilesForMobile() != null
					&& !"".equals(companyPlaceBusinessVO
							.getPollSourceAttachFilesForMobile())) {
				String[] poFile = companyPlaceBusinessVO
						.getPollSourceAttachFilesForMobile().split("\\|");
				companyPlaceBusinessVO.setPollSourceAttachFiles(poFile);
			}
			companyPlaceBusinessController
					.setCompanyPlaceBusinessVO(companyPlaceBusinessVO);
			companyPlaceBusinessController.saveCompanyPlaceBusinessForMobile();
		}
		return SUCCESS;
	}

	/**
	 * 删除数据
	 */
	@Override
	@Action(value = "deleteCompanyPlaceByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteCompanyPlaceByIdForMobile() throws Exception {
		if (cid == null || modulKey == null || "".equals(modulKey)) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		companyPlaceController.setCids(String.valueOf(cid));
		companyPlaceController.setModulKey(modulKey);
		companyPlaceController.delCompanyPlaceOperationByEncrypt();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findCompanyPlaceByIdForMobile
	 * @Description: TODO通过id查看单位场所信息 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 上午10:02:24
	 */
	@Override
	@Action(value = "findCompanyPlaceByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"companyPlaceVoForMobile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findCompanyPlaceByIdForMobile() throws Exception {
		if (cid == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		companyPlaceController.setCids(String.valueOf(cid));
		companyPlaceController.setMode(DialogMode.VIEW_MODE);
		companyPlaceController.dispatchOperate();
		companyPlace = companyPlaceController.getCompanyPlace();// 通过id获取到单位场所对象

		companyPlaceBusinessController.setCompanyPlaceId(cid);
		companyPlaceBusinessController.setId(cid);
		companyPlaceBusinessController.setMode(DialogMode.VIEW_MODE);
		companyPlaceBusinessController.dispatch();
		companyPlaceBusinessVO = companyPlaceBusinessController
				.getCompanyPlaceBusinessVO();// 通过单位场所id获取业务信息
		if (companyPlaceBusinessVO != null
				&& companyPlaceBusinessVO.getPollSourceCompanyPlaceBusiness() != null
				&& companyPlaceBusinessVO.getPollSourceCompanyPlaceBusiness()
						.getId() != null) {
			companyPlaceBusinessVO = setPollSourceInfo(companyPlaceBusinessVO,
					cid);
		}
		companyPlaceVoForMobile = new CompanyPlaceVoForMobile();
		companyPlaceVoForMobile.setCompanyPlace(companyPlace);
		companyPlaceVoForMobile
				.setCompanyPlaceBusinessVO(companyPlaceBusinessVO);
		return SUCCESS;
	}

	// 添加污染源信息
	private CompanyPlaceBusinessVO setPollSourceInfo(CompanyPlaceBusinessVO vo,
			Long cid) {
		ContaminationInfo contaminationInfo = contaminationInfoService
				.getContaminationInfoByBusinessId(vo
						.getPollSourceCompanyPlaceBusiness().getId());
		KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(cid,
				ModulTypes.POLLUTIONSOURCE_KEY);
		if (keyPlace != null && keyPlace.getOpenLayersMapInfo() != null) {
			contaminationInfo.setOpenLayersMapInfo(keyPlace
					.getOpenLayersMapInfo());
		}
		vo.setContaminationInfo(contaminationInfo);

		return vo;
	}

	/**
	 * 
	 * @Title: findCompanyPlaceServiceMembersListForMobile
	 * @Description: TODO查询单位场所治安负责人列表 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午01:55:36
	 */
	@Override
	@Action(value = "findCompanyPlaceServiceMembersListForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findCompanyPlaceServiceMembersListForMobile()
			throws Exception {
		if (serviceMemberVo == null || serviceMemberVo.getObjectType() == null
				|| "".equals(serviceMemberVo.getObjectType())
				|| serviceMemberVo.getObjectId() == null || rows == null
				|| page == null || sidx == null || sord == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		routerForCompanyPlaceController.setServiceMemberVo(serviceMemberVo);
		routerForCompanyPlaceController.setRows(rows);
		routerForCompanyPlaceController.setPage(page);
		routerForCompanyPlaceController.setSidx(sidx);
		routerForCompanyPlaceController.setSord(sord);
		routerForCompanyPlaceController.findServiceMembersByServiceMemberVo();
		gridPage = routerForCompanyPlaceController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findServiceMemberFromTeamsForMobile
	 * @Description: TODO查询治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午04:59:51
	 */
	@Override
	@Action(value = "findServiceMemberFromTeamsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceMemberFromTeamsForMobile() throws Exception {
		if (serviceTeamMemberVo == null || serviceTeamMemberVo.getOrg() == null
				|| serviceTeamMemberVo.getOrg().getId() == null
				|| serviceTeamMemberVo.getOrg().getOrgInternalCode() == null
				|| "".equals(serviceTeamMemberVo.getOrg().getOrgInternalCode())
				|| serviceTeamMemberVo.getObjectType() == null
				|| "".equals(serviceTeamMemberVo.getObjectType())
				|| serviceTeamMemberVo.getObjectId() == null
				|| serviceTeamMemberVo.getOrgScope() == null
				|| "".equals(serviceTeamMemberVo.getOrgScope())) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		routerForCompanyPlaceController
				.setServiceTeamMemberVo(serviceTeamMemberVo);
		routerForCompanyPlaceController.setRows(rows);
		routerForCompanyPlaceController.setPage(page);
		routerForCompanyPlaceController.setSidx(sidx);
		routerForCompanyPlaceController.setSord(sord);
		routerForCompanyPlaceController.findServiceMemberFromTeams();
		gridPage = routerForCompanyPlaceController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: addObjectAndMemberRelationForMobile
	 * @Description: TODO添加治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午10:51:49
	 */
	@Override
	@Action(value = "addObjectAndMemberRelationForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addObjectAndMemberRelationForMobile() throws Exception {
		if (serviceMemberWithObject == null
				|| serviceMemberWithObject.getMemberId() == null
				|| serviceMemberWithObject.getObjectType() == null
				|| "".equals(serviceMemberWithObject.getObjectType())
				|| serviceMemberWithObject.getObjectName() == null
				|| "".equals(serviceMemberWithObject.getObjectName())
				|| serviceMemberWithObject.getObjectId() == null
				|| serviceMemberWithObject.getTeamMember() == null
				|| serviceMemberWithObject.getOnDuty() == null
				|| serviceMemberWithObject.getTeamId() == null
				|| serviceMemberWithObject.getObjectLogout() == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		routerForCompanyPlaceController
				.setServiceMemberWithObject(serviceMemberWithObject);
		routerForCompanyPlaceController.addObjectAndMemberRelation();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: leaveOrBackOnDutyForMobile
	 * @Description: TODO卸任治安负责人 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午12:53:09
	 */
	@Override
	@Action(value = "leaveOrBackOnDutyForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String leaveOrBackOnDutyForMobile() throws Exception {
		if (serviceMemberWithObject == null
				|| serviceMemberWithObject.getId() == null
				|| serviceMemberWithObject.getOnDuty() == null
				|| serviceMemberWithObject.getMemberId() == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		routerForCompanyPlaceController
				.setServiceMemberWithObject(serviceMemberWithObject);
		routerForCompanyPlaceController.leaveOrBackOnDuty();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findServiceRecordsForMobile
	 * @Description: TODO查询巡查情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午01:46:11
	 */
	@Override
	@Action(value = "findServiceRecordsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceRecordsForMobile() throws Exception {
		if (objectIds == null || "".equals(objectIds) || populationType == null
				|| "".equals(populationType) || serviceRecordVo == null
				|| serviceRecordVo.getOrganization() == null
				|| serviceRecordVo.getOrganization().getId() == null
				|| serviceRecordVo.getDisplayYear() == null
				|| "".equals(serviceRecordVo.getDisplayYear()) || rows == null
				|| page == null || sidx == null || sord == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		serviceRecordController.setObjectIds(objectIds);
		serviceRecordController.setPopulationType(populationType);
		serviceRecordController.setServiceRecordVo(serviceRecordVo);
		serviceRecordController.findServiceRecords();
		gridPage = serviceRecordController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: addServiceRecordForMobile
	 * @Description: TODO新增巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午01:17:07
	 */
	@Override
	@Action(value = "addServiceRecordForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addServiceRecordForMobile() throws Exception {
		if (serviceRecord == null || serviceRecord.getUserOrgId() == null
				|| serviceRecord.getOrganization() == null
				|| serviceRecord.getOrganization().getId() == null
				|| serviceRecord.getMemberIds() == null
				|| serviceRecord.getOccurDate() == null
				|| serviceRecord.getOccurPlace() == null
				|| "".equals(serviceRecord.getOccurPlace())
				|| serviceRecord.getServiceMembers() == null
				|| "".equals(serviceRecord.getServiceMembers())
				|| serviceRecord.getRecordType() == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.setAttachFiles(attachFiles);
		// serviceRecordController.setAttachFile(attachFile);
		serviceRecordController.addServiceRecordForMobile();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: updateServiceRecordForMobile
	 * @Description: TODO修改巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午02:32:47
	 */
	@Override
	@Action(value = "updateServiceRecordForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateServiceRecordForMobile() throws Exception {
		if (serviceRecord == null || serviceRecord.getId() == null
				|| serviceRecord.getUserOrgId() == null
				|| serviceRecord.getOrganization() == null
				|| serviceRecord.getOrganization().getId() == null
				|| serviceRecord.getMemberIds() == null
				|| serviceRecord.getOccurDate() == null
				|| serviceRecord.getOccurPlace() == null
				|| "".equals(serviceRecord.getOccurPlace())
				|| serviceRecord.getServiceMembers() == null
				|| "".equals(serviceRecord.getServiceMembers())
				|| serviceRecord.getRecordType() == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.setAttachFiles(attachFiles);
		// serviceRecordController.setAttachFile(attachFile);
		serviceRecordController.updateServiceRecord();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: deleteServiceRecordsForMobile
	 * @Description: TODO删除巡场情况 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午03:05:29
	 */
	@Override
	@Action(value = "deleteServiceRecordsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteServiceRecordsForMobile() throws Exception {
		if (recordIds == null || "".equals(recordIds)) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		serviceRecordController.setRecordIds(recordIds);
		serviceRecordController.deleteServiceRecords();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findServiceRecordByIdForMobile
	 * @Description: TODO通过id查找巡场情况数据 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-21 下午03:22:14
	 */
	@Override
	@Action(value = "findServiceRecordByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceRecordVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceRecordByIdForMobile() throws Exception {
		if (serviceRecord == null || serviceRecord.getId() == null) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.viewServiceTeam();
		serviceRecordVo = serviceRecordController.getServiceRecordVo();
		return SUCCESS;
	}

	/**
	 * 根据ID和TYPE查询对应的keyplace信息
	 * 
	 * @return
	 */
	@Override
	@Action(value = "getKeyPlaceByIdAndType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"keyPlace", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getKeyPlaceByIdAndTypeForMobile() throws Exception {
		keyPlace = keyPlaceService.getKeyPlaceByIdAndType(id, type);
		return SUCCESS;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public CompanyPlaceVo getCompanyPlaceVo() {
		return companyPlaceVo;
	}

	public void setCompanyPlaceVo(CompanyPlaceVo companyPlaceVo) {
		this.companyPlaceVo = companyPlaceVo;
	}

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}

	public CompanyPlace getCompanyPlace() {
		return companyPlace;
	}

	public void setCompanyPlace(CompanyPlace companyPlace) {
		this.companyPlace = companyPlace;
	}

	public CompanyPlaceBusinessVO getCompanyPlaceBusinessVO() {
		return companyPlaceBusinessVO;
	}

	public void setCompanyPlaceBusinessVO(
			CompanyPlaceBusinessVO companyPlaceBusinessVO) {
		this.companyPlaceBusinessVO = companyPlaceBusinessVO;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public CompanyPlaceVoForMobile getCompanyPlaceVoForMobile() {
		return companyPlaceVoForMobile;
	}

	public void setCompanyPlaceVoForMobile(
			CompanyPlaceVoForMobile companyPlaceVoForMobile) {
		this.companyPlaceVoForMobile = companyPlaceVoForMobile;
	}

	public ServiceMemberVo getServiceMemberVo() {
		return serviceMemberVo;
	}

	public void setServiceMemberVo(ServiceMemberVo serviceMemberVo) {
		this.serviceMemberVo = serviceMemberVo;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public ServiceMemberWithObject getServiceMemberWithObject() {
		return serviceMemberWithObject;
	}

	public void setServiceMemberWithObject(
			ServiceMemberWithObject serviceMemberWithObject) {
		this.serviceMemberWithObject = serviceMemberWithObject;
	}

	public String getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(String objectIds) {
		this.objectIds = objectIds;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public ServiceRecordVo getServiceRecordVo() {
		return serviceRecordVo;
	}

	public void setServiceRecordVo(ServiceRecordVo serviceRecordVo) {
		this.serviceRecordVo = serviceRecordVo;
	}

	public ServiceRecord getServiceRecord() {
		return serviceRecord;
	}

	public void setServiceRecord(ServiceRecord serviceRecord) {
		this.serviceRecord = serviceRecord;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getRecordIds() {
		return recordIds;
	}

	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String[] getAttachFileNew() {
		return attachFileNew;
	}

	public void setAttachFileNew(String[] attachFileNew) {
		this.attachFileNew = attachFileNew;
	}

	public KeyPlace getKeyPlace() {
		return keyPlace;
	}

	public void setKeyPlace(KeyPlace keyPlace) {
		this.keyPlace = keyPlace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}