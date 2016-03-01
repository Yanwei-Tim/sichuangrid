package com.tianque.controller.mobileUpdate;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.MobileUpdateSearchVo;
import com.tianque.service.mobileUpdate.MobileUpdateService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("mobileUpdateController")
@Namespace("/sysadmin/mobileUpdate")
public class MobileUpdateController extends FileUploadForMobileUpdateAction {
	@Autowired
	private MobileUpdateService mobileUpdateService;

	private MobileUpdate mobileUpdate;
	private MobileUpdateSearchVo mobileUpdateSearchVo;

	private Long id;
	private String mode;
	private Long categoryId;
	private String categoryName;
	private Long oldCategoryId;

	private String oldOrgDepartmentNo;

	private Organization organization;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Long orgId;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/sysadmin/mobileUpdate/mobileUpdateDlg.jsp"),
			@Result(name = "error", location = "/common/error500.jsp") })
	public String dispatch() throws Exception {
		if (StringUtils.isEmpty(mode)) {
			return ERROR;
		} else {
			if (DialogMode.ADD_MODE.equals(mode)) {

			} else {
				if (id == null) {
					return ERROR;
				} else {
					mobileUpdate = getMobileUpdateById(id);
				}
			}
			return SUCCESS;
		}
	}

	private MobileUpdate getMobileUpdateById(Long id) {
		MobileUpdate mobileUpdate = mobileUpdateService.getMobileUpdateById(id);
		if (mobileUpdate != null && mobileUpdate.getOrganization() != null
				&& mobileUpdate.getOrganization().getId() != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(mobileUpdate.getOrganization().getId());
			if (org != null) {
				mobileUpdate.setOrganization(org);
			}
		}
		return mobileUpdate;
	}

	@Action(value = "getMobileUpdateByCategoryId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"mobileUpdate" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMobileUpdateByCategoryId() throws Exception {
		if (categoryId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		mobileUpdate = mobileUpdateService
				.getMobileUpdateByCategoryId(categoryId);
		return SUCCESS;
	}

	@Action(value = "getMobileUpdateByCategoryName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"mobileUpdate" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMobileUpdateByCategoryName() throws Exception {
		if (StringUtils.isEmpty(categoryName)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		mobileUpdate = mobileUpdateService
				.getMobileUpdateByCategoryName(categoryName);
		return SUCCESS;
	}

	@Action(value = "findOrganizationById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findOrganizationById() throws Exception {
		if (orgId != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			boolean flag = judgeDeptNo(organization);
			if (!flag) {
				errorMessage = "该部门不存在或部门编号为空，无法进行操作";
				return ERROR;
			}
		}
		return SUCCESS;
	}

	private boolean judgeDeptNo(Organization organization) {
		boolean flag = false;// true 代表有部门编号 false代表没有
		if (organization != null) {
			if (organization.getDepartmentNo() != null
					&& !"".equals(organization.getDepartmentNo())) {
				flag = true;
			}
		}
		return flag;
	}

	@Action(value = "addOrUpdateMobileUpdate")
	public void addOrUpdateMobileUpdate() throws Exception {

		if (mobileUpdate == null
				|| (DialogMode.EDIT_MODE.equals(mode) && mobileUpdate.getId() == null)) {
			errorMessage = "参数错误";
			return;
		}
		if (null == mobileUpdate.getOrganization()
				|| null == mobileUpdate.getOrganization().getId()) {
			errorMessage = "组织机构为空，无法进行操作";
			return;
		}
		organization = organizationDubboService.getSimpleOrgById(mobileUpdate
				.getOrganization().getId());
		if (organization.getDepartmentNo() == null
				|| "".equals(organization.getDepartmentNo())) {
			errorMessage = "该部门编号为空，无法进行操作";
			return;
		}
		mobileUpdate.setDepartmentNo(organization.getDepartmentNo());
		String departmentNo = organization.getDepartmentNo();
		if (getUpload() != null) {
			proccessUploadFileByMobile(departmentNo + ".apk");
			String path = "/" + GridProperties.UPLOAD_FILE_FOLDER + "/"
					+ getFileSuffix().substring(1).toLowerCase() + "/"
					+ departmentNo + ".apk";
			mobileUpdate.setUrl(path);
		}
		mobileUpdate.setUserName(ThreadVariable.getSession().getUserName());
		if (DialogMode.ADD_MODE.equals(mode)) {
			mobileUpdateService.addMobileUpdate(mobileUpdate);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			mobileUpdateService.updateMobileUpdate(mobileUpdate,
					oldOrgDepartmentNo);
		}
		errorMessage = "true";
		printJsonResult(errorMessage);
	}

	@Action(value = "deleteMobileUpdate", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteMobileUpdate() throws Exception {
		if (id == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		mobileUpdateService.deleteMobileUpdate(id);
		return SUCCESS;
	}

	@Action(value = "findMobileUpdateBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMobileUpdateBySearchVo() throws Exception {
		if (mobileUpdateSearchVo == null) {
			mobileUpdateSearchVo = new MobileUpdateSearchVo();
		}
		PageInfo pageInfo = mobileUpdateService.findMobileUpdateBySearchVo(
				mobileUpdateSearchVo, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findMobileUpdateBySearchVoForMobile
	 * @Description: TODO手机端新增获取更新版本信息
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-7-2 上午10:50:50
	 */
	@Action(value = "findMobileUpdateBySearchVoForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMobileUpdateBySearchVoForMobile() throws Exception {
		if (mobileUpdateSearchVo == null) {
			mobileUpdateSearchVo = new MobileUpdateSearchVo();
		}
		PageInfo pageInfo = mobileUpdateService.findMobileUpdateBySearchVo(
				mobileUpdateSearchVo, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void printJsonResult(Object result) throws Exception {
		// JSONObject jsonObject = JSONObject.fromObject(result);
		// String str = "{data:" + result + "}";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(result);
	}

	public MobileUpdate getMobileUpdate() {
		return mobileUpdate;
	}

	public void setMobileUpdate(MobileUpdate mobileUpdate) {
		this.mobileUpdate = mobileUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MobileUpdateSearchVo getMobileUpdateSearchVo() {
		return mobileUpdateSearchVo;
	}

	public void setMobileUpdateSearchVo(
			MobileUpdateSearchVo mobileUpdateSearchVo) {
		this.mobileUpdateSearchVo = mobileUpdateSearchVo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getOldCategoryId() {
		return oldCategoryId;
	}

	public void setOldCategoryId(Long oldCategoryId) {
		this.oldCategoryId = oldCategoryId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOldOrgDepartmentNo() {
		return oldOrgDepartmentNo;
	}

	public void setOldOrgDepartmentNo(String oldOrgDepartmentNo) {
		this.oldOrgDepartmentNo = oldOrgDepartmentNo;
	}

}
