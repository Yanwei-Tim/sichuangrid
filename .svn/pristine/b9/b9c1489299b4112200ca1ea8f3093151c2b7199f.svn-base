package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.EstateInformation;
import com.tianque.domain.Organization;
import com.tianque.service.EstateInformationService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 房产信息控制类。
 */
@Transactional
@Scope("prototype")
@Controller("estateInformationController")
public class EstateInformationController extends BaseAction {
	private EstateInformation estateInformation;
	private Organization ownerOrg;
	private Long[] ids;
	/**
	 * subGrid 查询子列表(房主)时，首先把房产信息的id传过来，subGrid自动带的参数
	 */
	private Long id;
	private String deleteIds;
	private int existedCount;
	/**
	 * 接收身份证号和姓名但不使用
	 */
	private String[] inhabitant_idCardNo;
	private String[] inhabitant_name;
	@Autowired
	private EstateInformationService estateInformationService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			estateInformation = new EstateInformation();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			estateInformation = estateInformationService.getEstateInformationById(estateInformation
					.getId());
			ownerOrg = estateInformation.getOrganization();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			estateInformation = estateInformationService.getEstateInformationById(estateInformation
					.getId());
			ownerOrg = estateInformation.getOrganization();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if ("subInhabitant".equalsIgnoreCase(mode)) {
			estateInformation = new EstateInformation();
			estateInformation.setId(id);
			return "subInhabitant";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			estateInformation = estateInformationService.getEstateInformationById(estateInformation
					.getId());
			ownerOrg = estateInformation.getOrganization();
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 新增房产信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addEstateInformation")
	public String addEstateInformation() {
		estateInformation.setOrganization(ownerOrg);
		if (!validateInput()) {
			return ERROR;
		}
		if (null == ids || ids.length == 0) {
			this.errorMessage = "请输入一条房主信息";
			return ERROR;
		}
		estateInformation = estateInformationService.addEstateInformation(estateInformation);
		estateInformation.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				estateInformation.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 更新房产信息，更新时有新增房主信息，有把原有房主信息删除 deleteIds是删除的房主信息 ids是新增加的房主信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateEstateInformation")
	public String updateEstateInformation() {
		estateInformation.setOrganization(ownerOrg);
		if (!validateUpdateInhabitant()) {
			return ERROR;
		}
		estateInformation = estateInformationService.updateEstateInInformation(estateInformation);
		estateInformation.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				estateInformation.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 删除房产信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "deleteEstateInformation")
	public String deleteEstateInformation() {
		if (estateInformation == null || estateInformation.getId() == null) {
			this.errorMessage = "请选中一条记录!";
			return ERROR;
		}
		estateInformationService.deleteEstateInformationById(estateInformation.getId());
		return SUCCESS;
	}

	/**
	 * 根据网格编号查询房产信息
	 * 
	 * @return
	 */
	public String findEstateInformationByOrgInternalCode() {
		if (ownerOrg == null || ownerOrg.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.proccessRelativeOrgNameByPageInfo(
					estateInformationService.findEstateInformationsForPageByOrgInternalCode(
							ownerOrg.getId(), page, rows, sidx, sord), organizationDubboService));
		}
		return SUCCESS;
	}

	/**
	 * 根据房产id查询房主信息
	 * 
	 * @return
	 */
	public String findInhabitantsByEstateId() {
		return SUCCESS;
	}

	/**
	 * 获取房产证号的数量（验证唯一性时用）
	 * 
	 * @return
	 */
	public String getExistedProprietaryNoCount() {
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode) && estateInformation.getId() != null) {
			String proprietaryNo = estateInformation.getProprietaryNo();
			EstateInformation estateInformationUpdate = estateInformationService
					.getEstateInformationById(estateInformation.getId());
			if (proprietaryNo.equals(estateInformationUpdate.getProprietaryNo())) {
				existedCount = 0;
				return SUCCESS;
			}
		}
		estateInformation.setOrganization(ownerOrg);
		existedCount = estateInformationService.getExistedProprietaryNoCount(estateInformation);
		return SUCCESS;
	}

	/**
	 * 获取土地证号的数量（验证唯一性时用）
	 * 
	 * @return
	 */
	public String getExistedLandPermitCount() {
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode) && estateInformation.getId() != null) {
			String landPermit = estateInformation.getLandPermit();
			EstateInformation estateInformationUpdate = estateInformationService
					.getEstateInformationById(estateInformation.getId());
			if (landPermit.equals(estateInformationUpdate.getLandPermit())) {
				existedCount = 0;
				return SUCCESS;
			}
		}
		estateInformation.setOrganization(ownerOrg);
		existedCount = estateInformationService.getExistedLandPermitCount(estateInformation);
		return SUCCESS;
	}

	public String housePropertyInfoPrint() {
		estateInformation = estateInformationService.getEstateInformationById(estateInformation
				.getId());
		ownerOrg = estateInformation.getOrganization();
		return SUCCESS;
	}

	private boolean validateInput() {
		if (estateInformation == null) {
			this.errorMessage = "请输入一条房产信息";
			return false;
		}
		if (estateInformation.getOrganization() == null
				|| estateInformation.getOrganization().getId() == null) {
			this.errorMessage = "必须指定网格!";
			return false;
		}
		return true;
	}

	private boolean validateUpdateInhabitant() {
		if (estateInformation == null || estateInformation.getId() == null) {
			this.errorMessage = "请选中一条记录!";
			return false;
		}
		return validateInput();
	}

	private PageInfo<EstateInformation> emptyPage(int pageSize) {
		PageInfo<EstateInformation> pageInfo = new PageInfo<EstateInformation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<EstateInformation>());
		return pageInfo;
	}

	public EstateInformation getEstateInformation() {
		return estateInformation;
	}

	public void setEstateInformation(EstateInformation estateInformation) {
		this.estateInformation = estateInformation;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public int getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(int existedCount) {
		this.existedCount = existedCount;
	}

	public String[] getInhabitant_idCardNo() {
		return inhabitant_idCardNo;
	}

	public void setInhabitant_idCardNo(String[] inhabitantIdCardNo) {
		inhabitant_idCardNo = inhabitantIdCardNo;
	}

	public String[] getInhabitant_name() {
		return inhabitant_name;
	}

	public void setInhabitant_name(String[] inhabitantName) {
		inhabitant_name = inhabitantName;
	}

}
