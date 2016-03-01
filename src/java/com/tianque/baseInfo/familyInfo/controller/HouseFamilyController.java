package com.tianque.baseInfo.familyInfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.familyInfo.service.HouseFamilyService;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Transactional
@Controller("houseFamilyController")
public class HouseFamilyController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HouseFamilyService houseFamilyService;
	@Autowired
	private HouseholdStaffService householdStaffService;

	private Long orgId;

	private String card;

	private HouseFamily houseFamily;

	private List<HouseFamily> houseFamilyList;

	/** 更换户口号时的新户口号 */
	private String newAccNo;

	/** 删除家庭时的ids */
	private String ids;

	/** 查询户籍家庭的户籍人员 */
	private List<HouseholdStaff> householdStaffs;

	/** 老户主 */
	private HouseholdStaff oldHouseHold;

	/** 新户主 */
	private HouseholdStaff newHouseHold;

	/** 新家庭成员 */
	private HouseholdStaff newFamilyMember;

	private Long shardOrgId;

	public String dispatchOperate() throws Exception {
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "edit";
		} else if ("maintain".equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "maintain";
		} else if ("change".equalsIgnoreCase(mode)) {
			/*
			 * householdStaffs = householdStaffService
			 * .findHouseholdStaffsByFamilyId(houseFamily.getId());
			 */

			householdStaffs = householdStaffService
					.findHouseholdStaffsExceptHeadByFamilyId(houseFamily
							.getId(), houseFamily.getOrganization().getId());

			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "change";
		} else if ("move".equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "move";
		}
		return ERROR;
	}

	/**
	 * ID 加密 重置户口、更换户主，管理成员维护
	 */
	@EncryptAnnotation
	public String dispatchOperateByEncrpt() throws Exception {
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "edit";
		} else if ("maintain".equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "maintain";
		} else if ("change".equalsIgnoreCase(mode)) {
			/*
			 * householdStaffs = householdStaffService
			 * .findHouseholdStaffsByFamilyId(houseFamily.getId());
			 */

			householdStaffs = householdStaffService
					.findHouseholdStaffsExceptHeadByFamilyId(houseFamily
							.getId(), houseFamily.getOrganization().getId());

			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "change";
		} else if ("move".equalsIgnoreCase(mode)) {
			houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
					.getId());
			return "move";
		}
		return ERROR;
	}

	/**
	 * 根据id删除户籍家庭
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String deleteByIds() throws Exception {
		houseFamilyService.deleteHouseFamilyByIds(ids);
		return "success";
	}

	/**
	 * 修改户口号
	 * 
	 * @return
	 */
	public String changeAccountNumber() throws Exception {
		if (null == houseFamily || null == newAccNo
				|| null == houseFamily.getId()) {
			return ERROR;
		}

		houseFamilyService.changeAccountNumber(newAccNo, orgId,
				houseFamily.getId(), shardOrgId);
		return "success";
	}

	/**
	 * 更换户主1更新户籍家庭2.更新新户主3.更新老户主
	 * 
	 * @return
	 */
	public String changeHouseHold() throws Exception {
		houseFamilyService.changeHouseHold(oldHouseHold, newHouseHold,
				houseFamily.getId(), orgId, shardOrgId);
		return "success";
	}

	@EncryptAnnotation
	public String changeHouseHoldByEncrypt() throws Exception {
		houseFamilyService.changeHouseHold(oldHouseHold, newHouseHold,
				houseFamily.getId(), orgId, shardOrgId);
		return "success";
	}

	public String addFamilyMemberByIdCardNo() throws Exception {
		householdStaffService.addFamilyMemberByIdCardNo(orgId,
				houseFamily.getId(), newFamilyMember.getIdCardNo(),
				newFamilyMember.getRelationShipWithHead().getId(),
				newFamilyMember.getAccountNumber());
		return SUCCESS;
	}

	/**
	 * 移除一个家庭成员到另一个家庭
	 * 
	 * @return
	 */
	public String moveHouseMember() throws Exception {
		householdStaffService.moveHouseMember(orgId, newFamilyMember
				.getIdCardNo(), newFamilyMember.getRelationShipWithHead()
				.getId(), newFamilyMember.getAccountNumber());
		return SUCCESS;
	}

	/**
	 * 根据户籍家庭id和orgId获取成员列表
	 * 
	 * @return
	 */
	public String findHouseFamilyMembersByOrgIdAndId() throws Exception {
		if (houseFamily.getId() == null)
			return ERROR;
		gridPage = new GridPage(
				houseFamilyService.findHouseFamilyMembersByOrgIdAndId(
						houseFamily.getId(), orgId, page, rows, sidx, sord));
		return SUCCESS;
	}

	public String existAccountNumber() throws Exception {
		boolean flag = houseFamilyService.existAccountNumber(newAccNo, orgId);
		if (flag) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String dispatchBusiness() {
		return null;
	}

	public String findHouseFamilyById() throws Exception {
		if (houseFamily.getId() == null)
			return ERROR;
		houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
				.getId());
		if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * ID加密 查看
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String findHouseFamilyByEncryptId() throws Exception {
		if (houseFamily.getId() == null)
			return ERROR;
		houseFamily = houseFamilyService.findHouseFamilyById(houseFamily
				.getId());
		if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	public String findHouseFamilyByOrgId() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					houseFamilyService.findHouseFamilyByOrgId(orgId, page,
							rows, sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	public String haveRepatCardOrNo() throws Exception {
		int theNumberOfCard = houseFamilyService.haveRepatCardOrNo(card);
		if (theNumberOfCard > 0) {
			return "success";
		} else {
			return "error";
		}
	}

	private PageInfo<HouseFamily> emptyPage(int pageSize) {
		PageInfo<HouseFamily> pageInfo = new PageInfo<HouseFamily>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseFamily>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public HouseFamily getHouseFamily() {
		return houseFamily;
	}

	public List<HouseFamily> getHouseFamilyList() {
		return houseFamilyList;
	}

	public void setHouseFamily(HouseFamily houseFamily) {
		this.houseFamily = houseFamily;
	}

	public void setHouseFamilyList(List<HouseFamily> houseFamilyList) {
		this.houseFamilyList = houseFamilyList;
	}

	public String getNewAccNo() {
		return newAccNo;
	}

	public void setNewAccNo(String newAccNo) {
		this.newAccNo = newAccNo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<HouseholdStaff> getHouseholdStaffs() {
		return householdStaffs;
	}

	public void setHouseholdStaffs(List<HouseholdStaff> householdStaffs) {
		this.householdStaffs = householdStaffs;
	}

	public HouseholdStaff getOldHouseHold() {
		return oldHouseHold;
	}

	public void setOldHouseHold(HouseholdStaff oldHouseHold) {
		this.oldHouseHold = oldHouseHold;
	}

	public HouseholdStaff getNewHouseHold() {
		return newHouseHold;
	}

	public void setNewHouseHold(HouseholdStaff newHouseHold) {
		this.newHouseHold = newHouseHold;
	}

	public HouseholdStaff getNewFamilyMember() {
		return newFamilyMember;
	}

	public void setNewFamilyMember(HouseholdStaff newFamilyMember) {
		this.newFamilyMember = newFamilyMember;
	}

	public void setShardOrgId(Long shardOrgId) {
		this.shardOrgId = shardOrgId;
	}

	public Long getShardOrgId() {
		return shardOrgId;
	}
}
