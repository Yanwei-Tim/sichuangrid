package com.tianque.working.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.working.domain.WorkBulletin;
import com.tianque.working.domain.WorkBulletinAttachFiles;
import com.tianque.working.service.WorkBulletinService;

/**
 * 工作简报
 * 
 * @author wangshirui
 */
@Scope("prototype")
@Transactional
@Controller("workBulletinController")
public class WorkBulletinController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(WorkBulletinController.class);

	private static final long serialVersionUID = 1866728398770748801L;
	@Autowired
	private WorkBulletinService workBulletinService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	/** 简报实体 */
	private WorkBulletin workBulletin;
	/** 简报列表 */
	private List<WorkBulletin> workBulletinList;
	/** 上传附件名称 */
	private String attachFiles;
	private Organization ownerOrg;

	private Long orgId;

	private Long attachFileId;
	private final static Long MAX_2M_FILESIZE = 2097152L;
	private final static String tmpUploadFilePath = "tmp";

	/**
	 * 添加简报信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addWorkBulletin")
	public String addWorkBulletin() throws Exception {
		if (!validateInput()) {
			return ERROR;
		}
		User user = permissionService.getFullUserById(ThreadVariable
				.getSession().getUserId());
		ownerOrg = user.getOrganization() == null ? null : user
				.getOrganization();

		getWorkBulletin().setUseId(user.getId());
		getWorkBulletin().setOrgId(workBulletin.getOrganization().getId());

		getWorkBulletin().setFillDate(workBulletin.getFillDate());
		getWorkBulletin().setOrgInternalCode(user.getOrgInternalCode());
		workBulletin = workBulletinService.addWorkBulletin(getWorkBulletin());
		String attachFilesArray[] = attachFiles.split(",");
		for (String fileName : attachFilesArray) {
			if (!addAttachFile(workBulletin, fileName))
				return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 修改简报信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateWorkBulletin")
	public String updateWorkBulletin() throws Exception {
		if (!validateInput()) {
			return ERROR;
		}
		User user = permissionService.getFullUserById(ThreadVariable
				.getSession().getUserId());
		ownerOrg = user.getOrganization() == null ? null : user
				.getOrganization();
		getWorkBulletin().setOrgId(workBulletin.getOrganization().getId());
		getWorkBulletin().setOrgInternalCode(user.getOrgInternalCode());
		getWorkBulletin().setCreateDate(workBulletin.getFillDate());
		getWorkBulletin().setCreateUser(String.valueOf(user.getId()));
		workBulletinService.updateWorkBulletin(getWorkBulletin());
		String attachFilesArray[] = attachFiles.split(",");
		for (String fileName : attachFilesArray) {
			if (!addAttachFile(workBulletin, fileName.trim()))
				return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 删除简报信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "deleteWorkBulletin")
	public String deleteWorkBulletin() throws Exception {
		if (workBulletin == null || workBulletin.getId() == null) {
			this.errorMessage = "请选中一条记录!";
			return ERROR;
		}
		workBulletinService.deleteWorkBulletinById(workBulletin.getId());
		return SUCCESS;
	}

	/**
	 * 查询单个简报信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "findWorkBulletinById")
	public String findWorkBulletinById() throws Exception {
		if (getWorkBulletin().getId() == null) {
			return ERROR;
		}
		workBulletinService.findWorkBulletinById(getWorkBulletin().getId());
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			User user = permissionService.getFullUserById(ThreadVariable
					.getSession().getUserId());
			this.getWorkBulletin().setUserName(user.getUserName());
			return SUCCESS;
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			if (getWorkBulletin().getId() == null) {
				return ERROR;
			}
			setWorkBulletin(workBulletinService
					.findWorkBulletinById(getWorkBulletin().getId()));
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (getWorkBulletin().getId() == null) {
				return ERROR;
			}
			setWorkBulletin(workBulletinService
					.findWorkBulletinById(getWorkBulletin().getId()));
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		}

		return SUCCESS;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */

	private boolean validateInput() {
		boolean bl = true;
		if (!StringUtil.isStringAvaliable(workBulletin.getBulletinName())) {
			appendErrorMessage("请输入简报名称");
			bl = false;
		} else if (!StringUtil.isStringAvaliable(workBulletin.getBulletinDate()
				.toString())) {
			appendErrorMessage("请输入简报日期");
			bl = false;
		}
		return bl;
	}

	private File getUploadFile(String uploadFileName) {
		String storedFilePath = tmpUploadFilePath + File.separator
				+ ThreadVariable.getUser().getId();
		File storedFile = new File(GlobalValue.getProjectPath(this.getClass())
				+ "/../../" + storedFilePath + File.separator + uploadFileName);
		if (storedFile.exists())
			return storedFile;
		else
			return null;
	}

	private boolean addAttachFile(WorkBulletin workBulletin, String fileName) {
		WorkBulletinAttachFiles workBulletinAttachFile = new WorkBulletinAttachFiles();
		workBulletinAttachFile.setBulletinId(workBulletin.getId());
		workBulletinAttachFile.setFileName(StringUtil.trim(fileName));
		StoredFile file = null;
		File source = getUploadFile(fileName);
		if (source != null) {
			long filesize = source.length();
			try {
				if (validateFileSize(workBulletin.getId(), filesize))
					file = FileUtil.copyTmpFileToStoredFile(
							StringUtil.trim(fileName),
							GridProperties.WORK_BULLETIN);
				else
					return false;
			} catch (Exception e) {
				throw new ServiceValidationException(e.getMessage(), e);
			}

			workBulletinAttachFile.setFileSize(filesize);
		} else
			return true;
		String fileActualPath = file.getStoredFilePath() + File.separator
				+ file.getStoredFileName();
		workBulletinAttachFile.setFileactualUrl(fileActualPath);
		if (!workBulletinService
				.addWorkBulletinAttachFile(workBulletinAttachFile))
			return false;
		return true;

	}

	private boolean validateFileSize(Long id, long length) {
		if (id == null)
			return false;
		Long totalSize = workBulletinService.getSumAllFileSizeById(id);

		if (totalSize != null && MAX_2M_FILESIZE < totalSize + length) {
			this.errorMessage = "总共文件不能超过2M!";
			return false;
		}

		return true;
	}

	public boolean deleteWorkBulletinAttachFile() {
		if (attachFileId == null)
			return false;
		return workBulletinService
				.deleteWorkBulletinAttachFileById(attachFileId);
	}

	public String downloadWorkBulletinAttachFile() throws Exception {
		if (attachFileId == null) {
			this.errorMessage = "请点击要下载的文件!";
			return ERROR;
		}
		WorkBulletinAttachFiles workBulletinAttachFiles = workBulletinService
				.findWorkBulletinAttachFilesById(attachFileId);
		if (workBulletinAttachFiles == null) {
			this.errorMessage = "您下载的文件不存在!";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(
				createStoreFile(workBulletinAttachFiles.getFileactualUrl()));
		downloadFileName = new String(workBulletinAttachFiles.getFileName()
				.getBytes(), "ISO8859-1");
		return SUCCESS;
	}

	private File createStoreFile(String filePath) throws Exception {
		String downFilePath = GridProperties.DOWNLOAD_TEMP_FILE_FOLDER;
		String[] filePaths = filePath.split("/");
		for (int i = 0; i < filePaths.length; i++) {
			downFilePath = downFilePath + File.separator + filePaths[i];
			if (i == filePaths.length - 1)
				downloadFileName = filePaths[i];
		}
		File storedFile = new File(downFilePath);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	public String findWorkBulletinsByOrgInternalCode() throws Exception {

		if (ownerOrg == null || ownerOrg.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					workBulletinService.findBulletinsForPageByOrgInternalCode(
							ownerOrg.getId(), page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					ownerOrg.getId()));

		}
		return SUCCESS;
	}

	private PageInfo<WorkBulletin> emptyPage(int pageSize) {
		PageInfo<WorkBulletin> pageInfo = new PageInfo<WorkBulletin>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<WorkBulletin>());
		return pageInfo;
	}

	public List<WorkBulletin> getWorkBulletinList() {
		if (null == workBulletinList) {
			workBulletinList = new ArrayList<WorkBulletin>();
		}
		return workBulletinList;
	}

	public void setWorkBulletinList(List<WorkBulletin> workBulletinList) {
		this.workBulletinList = workBulletinList;
	}

	public WorkBulletin getWorkBulletin() {
		if (null == workBulletin) {
			workBulletin = new WorkBulletin();
		}
		return workBulletin;
	}

	public void setWorkBulletin(WorkBulletin workBulletin) {
		this.workBulletin = workBulletin;
	}

	public String getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getAttachFileId() {
		return attachFileId;
	}

	public void setAttachFileId(Long attachFileId) {
		this.attachFileId = attachFileId;
	}

}
