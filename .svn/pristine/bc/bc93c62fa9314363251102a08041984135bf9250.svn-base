package com.tianque.publicNotice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.PublicNoticeVo;
import com.tianque.publicNotice.domain.PublicNotice;
import com.tianque.publicNotice.domain.PublicNoticeAttachFiles;
import com.tianque.publicNotice.domain.PublicNoticeBenchVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PublicNoticeDubboService;

@Namespace("/sysadmin/publicNoticeManage")
@Transactional
@Scope("request")
@Controller("publicNoticeController")
public class PublicNoticeController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(PublicNoticeController.class);
	private PublicNotice publicNotice;
	private PublicNoticeVo publicNoticeVo;
	private PublicNoticeAttachFiles publicNoticeAttachFile;
	@Autowired
	private PublicNoticeDubboService publicNoticeDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private String[] attachFiles;// 附件
	private String validityDate;
	private Long organizationId;
	private int publicNoticeLevel;// level=0代表是本级，level=1代表是下辖。
	private String publicNoticeIds;
	private Date overdueTiem;
	private String newDate;
	private String overdueInput;
	private List<PublicNoticeAttachFiles> isPublicNoticeAttachFile = new ArrayList<PublicNoticeAttachFiles>();// 用于查看的时候
	private PublicNoticeBenchVo publicNoticeBenchVo;// 工作台通知通告数据（一条最新的通知通告，五条最新的未读通知通告）

	public PageInfo<PublicNotice> emptyPage(int pageSize) {
		PageInfo<PublicNotice> pageInfo = new PageInfo<PublicNotice>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicNotice>());
		return pageInfo;
	}

	public void fastSearchPublicNotice() {
		publicNoticeVo.setOrganizationCode(ThreadVariable.getOrganization()
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				publicNoticeDubboService.findByStartEndDateAndTitle(
						publicNoticeVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
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

	@Action(value = "dispatch", results = {
			@Result(name = "maintainPublicNotice", location = "/sysadmin/publicNoticeManage/maintainPublicNoticeDlg.jsp"),
			@Result(name = "update", location = "/sysadmin/publicNoticeManage/maintainPublicNoticeDlg.jsp"),
			@Result(name = "search", location = "/sysadmin/publicNoticeManage/searchPublicNotice.jsp"),
			@Result(name = "searchReceive", location = "/sysadmin/publicNoticeManage/searchPublicNoticeReceive.jsp"),
			@Result(name = "viewPublic", location = "/sysadmin/publicNoticeManage/publicNoticeView.jsp"),
			@Result(name = "viewRecordContent", location = "/sysadmin/publicNoticeManage/publicNoticeContentView.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintainPublicNotice";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getPublicNoticeInfo();
			return "update";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getPublicNoticeInfo();
			return "viewPublic";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if ("viewRecordContent".equals(mode)) {
			// fateson 新增 显示通知内容页面
			getPublicNoticeInfo();
			return "viewRecordContent";
		} else if ("readReceive".equals(mode)) {
			// 阅读
			getPublicNoticeInfo();
			publicNoticeDubboService.updatePublicNoticeIsRead(publicNotice
					.getId());
			return "viewRecordContent";
		} else if ("searchReceive".equals(mode)) {
			return "searchReceive";
		}

		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	@Action(value = "maintainPublicNotice", results = {
			@Result(type = "json", name = "success", params = { "root",
					"publicNotice", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainPublicNotice() throws Exception {

		Calendar coverdueDate = Calendar.getInstance();
		coverdueDate.setTime(publicNotice.getEditorDate());

		// 增加
		if ("add".equals(mode)) {
			if (validityDate.equals("不限")) {
				publicNotice.setOverdueDate(null);
			} else if (!validityDate.equals("-1")) {
				int date = Integer.parseInt(getValidityDate());
				coverdueDate.add(coverdueDate.DAY_OF_YEAR, date);
				publicNotice.setOverdueDate(coverdueDate.getTime());
			}
			publicNotice = publicNoticeDubboService
					.addPublicNotice(publicNotice);
			publicNotice.setNoticeFiles(publicNoticeDubboService
					.addAttachFileByPublicNoticeId(publicNotice.getId(),
							attachFiles));
			// 修改
		} else if ("edit".equals(mode)) {
			publicNotice.setNoticeFiles(publicNoticeDubboService
					.updatePublicNoticeAttachFile(publicNotice.getId(),
							attachFiles));
			publicNotice = publicNoticeDubboService
					.updatePublicNotice(publicNotice);
		}
		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	@Action(value = "maintainPublicNoticeByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root",
					"publicNotice", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainPublicNoticeByEncrypt() throws Exception {
		Calendar coverdueDate = Calendar.getInstance();
		coverdueDate.setTime(publicNotice.getEditorDate());

		// 增加
		if ("add".equals(mode)) {
			if (validityDate.equals("不限")) {
				publicNotice.setOverdueDate(null);
			} else if (!validityDate.equals("-1")) {
				int date = Integer.parseInt(getValidityDate());
				coverdueDate.add(coverdueDate.DAY_OF_YEAR, date);
				publicNotice.setOverdueDate(coverdueDate.getTime());
			}
			publicNotice = publicNoticeDubboService
					.addPublicNotice(publicNotice);
			publicNotice.setNoticeFiles(publicNoticeDubboService
					.addAttachFileByPublicNoticeId(publicNotice.getId(),
							attachFiles));
			// 修改
		} else if ("edit".equals(mode)) {
			publicNotice.setNoticeFiles(publicNoticeDubboService
					.updatePublicNoticeAttachFile(publicNotice.getId(),
							attachFiles));
			publicNotice = publicNoticeDubboService
					.updatePublicNotice(publicNotice);
		}
		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	@Action(value = "updateNewOverdue", results = { @Result(type = "json", name = "success", params = {
			"root", "newDate" }) })
	public String updateNewDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(publicNotice.getEditorDate());
		if (validityDate.equals("不限")) {
			newDate = null;

		} else if (!validityDate.equals("-1")) {
			int date = Integer.parseInt(getValidityDate());
			calendar.add(calendar.DAY_OF_YEAR, date);
			SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
			newDate = formats.format(calendar.getTime());

		}
		return SUCCESS;
	}

	@Action(value = "publicNoticeList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageForOrgcodeAndLevel() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				publicNoticeDubboService
						.findfindPublicNoticeForPageByOrgInternalCode(
								organizationId, publicNoticeLevel, page, rows,
								sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	@Action(value = "publicNoticeReceiveList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String publicNoticeReceiveList() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				publicNoticeDubboService.getPublicNoticeReceiveList(
						publicNoticeVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
		return SUCCESS;
	}

	@Action(value = "publicNoticeOfBenchList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"publicNoticeBenchVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String publicNoticeOfBenchList() throws Exception {
		publicNoticeBenchVo = publicNoticeDubboService
				.getPublicNoticeReceiveList(rows, sidx, sord);
		return SUCCESS;
	}

	@Action(value = "getOverdueTime", results = { @Result(name = "success", type = "json", params = {
			"root", "overdueTiem" })

	})
	public String getOverdueTime() throws Exception {
		overdueTiem = new Date();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (publicNoticeVo == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			fastSearchPublicNotice();
		}
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = publicNoticeIds.split(",");
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

	@Action(value = "deletePublicNotice", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePublicNotice() throws Exception {
		for (Long publicNoticeId : analyzePopulationIds()) {
			isPublicNoticeAttachFile = publicNoticeDubboService
					.attachFileList(publicNoticeId);
			if (isPublicNoticeAttachFile != null
					&& isPublicNoticeAttachFile.size() > 0) {
				publicNoticeDubboService
						.deletePublicNoticeAttachFileByPublicNoticeId(analyzePopulationIds());
			}
		}
		publicNoticeDubboService
				.deletePublicNoticeByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "deletePublicNoticeByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePublicNoticeByEncrypt() throws Exception {
		for (Long publicNoticeId : analyzePopulationIds()) {
			isPublicNoticeAttachFile = publicNoticeDubboService
					.attachFileList(publicNoticeId);
			if (isPublicNoticeAttachFile != null
					&& isPublicNoticeAttachFile.size() > 0) {
				publicNoticeDubboService
						.deletePublicNoticeAttachFileByPublicNoticeId(analyzePopulationIds());
			}
		}
		publicNoticeDubboService
				.deletePublicNoticeByIds(analyzePopulationIds());
		return SUCCESS;
	}

	// 查询
	@Action(value = "searchPublicNotice", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchPublicNotice() throws Exception {
		if (publicNoticeVo == null) {
			gridPage = new GridPage(emptyPage(rows == 0 ? 20 : rows));
			return SUCCESS;
		}
		publicNoticeVo.setOrganizationCode(ThreadVariable.getOrganization()
				.getOrgInternalCode());
		PageInfo<PublicNoticeVo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(publicNoticeDubboService
						.searchPublicNotice(publicNoticeVo, page, rows, sidx,
								sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	/*
	 * private boolean validateAttachFiles(){ if(attachFiles!=null){
	 * if(attachFiles.length>5){ appendErrorMessage("附件最多只能上传5个"); return false;
	 * } } return true; }
	 */
	public void getPublicNoticeInfo() {
		publicNotice = this.publicNoticeDubboService
				.getPublicNoticeById(publicNotice.getId());
		publicNotice.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(publicNotice
						.getOrganization().getId(), organizationDubboService));
		if (publicNotice.getOverdueDate() == null) {
			overdueInput = "不限";
		} else {
			overdueInput = new SimpleDateFormat("yyyy-MM-dd")
					.format(publicNotice.getOverdueDate());
		}
		isPublicNoticeAttachFile = publicNoticeDubboService
				.attachFileList(publicNotice.getId());

	}

	@Action(value = "downloadPublicNoticeAttachFile", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"inputStream", "contentType",
					"application/octet-stream;charset=GBK",
					"contentDisposition",
					"inline;filename=${downloadFileName}", "bufferSize", "4096" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String downloadPublicNoticeAttachFile() throws Exception {
		if (null == publicNoticeAttachFile
				|| null == publicNoticeAttachFile.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		publicNoticeAttachFile = publicNoticeDubboService
				.getPublicNoticeAttachFileById(publicNoticeAttachFile.getId());
		if (null == publicNoticeAttachFile) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator + publicNoticeAttachFile.getFileActualUrl());
		downloadFileName = new String(publicNoticeAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return SUCCESS;

	}

	@Action(value = "getPublicNoticeAttachFilesById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"publicNotice", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPublicNoticeAttachFilesById() throws Exception {
		publicNotice = publicNoticeDubboService
				.getPublicNoticeById(publicNotice.getId());
		if (publicNotice != null) {
			publicNotice.setNoticeFiles(publicNoticeDubboService
					.addAttachFileByPublicNoticeId(publicNotice.getId(),
							attachFiles));
		}
		return SUCCESS;
	}

	@Action(value = "deletePublicNoticeAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePublicNoticeAttachFile() throws Exception {
		if (null != publicNoticeAttachFile) {
			publicNoticeDubboService
					.deletePublicNoticeAttachFileById(publicNoticeAttachFile
							.getId());
		}
		return SUCCESS;
	}

	public PublicNotice getPublicNotice() {
		return publicNotice;
	}

	public void setPublicNotice(PublicNotice publicNotice) {
		this.publicNotice = publicNotice;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public int getPublicNoticeLevel() {
		return publicNoticeLevel;
	}

	public void setPublicNoticeLevel(int publicNoticeLevel) {
		this.publicNoticeLevel = publicNoticeLevel;
	}

	public String getPublicNoticeIds() {
		return publicNoticeIds;
	}

	public void setPublicNoticeIds(String publicNoticeIds) {
		this.publicNoticeIds = publicNoticeIds;
	}

	public PublicNoticeVo getPublicNoticeVo() {
		return publicNoticeVo;
	}

	public void setPublicNoticeVo(PublicNoticeVo publicNoticeVo) {
		this.publicNoticeVo = publicNoticeVo;
	}

	public Date getOverdueTiem() {
		return overdueTiem;
	}

	public void setOverdueTiem(Date overdueTiem) {
		this.overdueTiem = overdueTiem;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public List<PublicNoticeAttachFiles> getIsPublicNoticeAttachFile() {
		return isPublicNoticeAttachFile;
	}

	public void setIsPublicNoticeAttachFile(
			List<PublicNoticeAttachFiles> isPublicNoticeAttachFile) {
		this.isPublicNoticeAttachFile = isPublicNoticeAttachFile;
	}

	public PublicNoticeAttachFiles getPublicNoticeAttachFile() {
		return publicNoticeAttachFile;
	}

	public void setPublicNoticeAttachFile(
			PublicNoticeAttachFiles publicNoticeAttachFile) {
		this.publicNoticeAttachFile = publicNoticeAttachFile;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getOverdueInput() {
		return overdueInput;
	}

	public void setOverdueInput(String overdueInput) {
		this.overdueInput = overdueInput;
	}

	public PublicNoticeBenchVo getPublicNoticeBenchVo() {
		return publicNoticeBenchVo;
	}

	public void setPublicNoticeBenchVo(PublicNoticeBenchVo publicNoticeBenchVo) {
		this.publicNoticeBenchVo = publicNoticeBenchVo;
	}

}
