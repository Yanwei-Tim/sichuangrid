package com.tianque.mobileErrorLogs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.mobileErrorLogs.domain.MobileErrorLogs;
import com.tianque.mobileErrorLogs.service.MobileErrorLogsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @author weiminglong
 *2016年1月7日下午2:39:49
 */
@Scope("request")
@Namespace("/mobileErrorLogsManage")
@Controller("mobileErrorLogsController")
public class MobileErrorLogsController extends BaseAction{
	@Autowired
	private MobileErrorLogsService  mobileErrorLogsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private MobileErrorLogs mobileErrorLogs;
	/** 组织id **/
	private Long orgId;
	private Date occurDate;
	/** 手机端传来的文件 **/
	private File uploadFile;
	/* 一个参数  */
	private String[] attachFiles;
	/* 多个参数  */
	private String[] attachFile;
	/** 附件名字 */
	private String[] attachFileNames;
	/**上传错误的用户*/
	private User user;
	private Organization organization;
	private static Logger logger = LoggerFactory
			.getLogger(MobileErrorLogsController.class);
	
	@Action(value = "prepareMobileErrorLogs", results = {
			@Result(name = "search", location = "/sysadmin/mobileErrorLogsManage/searchMobileErrorLogsDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root","errorMessage" })})
	public String prepareMobileErrorLogs() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}
		return ERROR;
	}
	
	@Action(value = "advancedSearchMobileErrorLogs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String advancedSearchMobileErrorLogs() {
		if (null == mobileErrorLogs) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		try {
			organization = organizationDubboService
					.getSimpleOrgById(mobileErrorLogs.getOrgId());
			mobileErrorLogs.setOrgCode(organization.getOrgInternalCode());
			gridPage = new GridPage(
					mobileErrorLogsService.advancedSearchMobileErrorLogs(mobileErrorLogs,
							page, rows,sidx, sord));
		} catch (Exception e) {
			errorMessage = "查询出错";
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value = "addMobileErrorLogs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"true", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"false" }) })
	public String addMobileErrorLogs() throws Exception {
			if (attachFile != null && attachFile.length != 0) {
				String[] strTmp = attachFile[0].split(",");
				inputStream = iterableAttachFilesForMobile(strTmp);
			}else if (attachFiles != null && attachFiles.length != 0) {
				String[] strTmp = attachFiles[0].split(",");
				inputStream = iterableAttachFilesForMobile(strTmp);
			}else{
				return ERROR;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formatDate = sdf.format(mobileErrorLogs.getOccurDate());
			String fileName = formatDate+".txt";
			File file = createMobileErrorLogsFile(fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file,true);
			IOUtils.copy(inputStream, fileOutputStream);
			fileOutputStream.close();
			inputStream.close();
			mobileErrorLogs.setErrorLogsPath("uploadFile\\mobileErrorLogs\\"+formatDate+".txt");
			mobileErrorLogs.setName(user.getName());
			organization = organizationDubboService
					.getSimpleOrgById(mobileErrorLogs.getOrgId());
			mobileErrorLogs.setOrgCode(organization.getOrgInternalCode());
			mobileErrorLogs = mobileErrorLogsService
					.addMobileErrorLogs(mobileErrorLogs);
			return SUCCESS;
	}
	
	private InputStream iterableAttachFilesForMobile(String[] files) throws Exception {
		if (files != null && files.length > 0) {
			for (String attachFileName : files) {
				 inputStream = new FileInputStream(FileUtil.getWebRoot() + File.separator
						+ GridProperties.TMP+File.separator
						+ (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator
						+ attachFileName);
				
			}
		}
		return inputStream;
	}
	
	public static File createMobileErrorLogsFile(String uploadFileFileName)
			throws Exception {
		File directoryFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.MOBILEERRORLOGS );
		if (!(directoryFile.exists() && directoryFile.isDirectory())) {
			directoryFile.mkdirs();
		}
		File storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.MOBILEERRORLOGS + File.separator 
				+ uploadFileFileName);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}
	
	@Action(value = "findMobileErrorLogs", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMobileErrorLogs() throws Exception {
		organization = organizationDubboService
				.getSimpleOrgById(orgId);
		MobileErrorLogs mobileErrorLogs = new MobileErrorLogs();
		mobileErrorLogs.setOrgCode(organization.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				mobileErrorLogsService.findMobileErrorLogs(mobileErrorLogs,
						page, rows,sidx, sord), organizationDubboService, 
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}
	
	@Action(value = "downLoadMobileErrorLogs", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadActualFile() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = sdf.format(occurDate);
		String firstPart = fileName.substring(0, 4);
		String middlePart = fileName.substring(5, 7);
		String lastPart = fileName.substring(8);
	    String errorlogsPath = "uploadFile"+File.separator+"mobileErrorLogs"+File.separator+firstPart+"-"+middlePart+"-"+lastPart+".txt";
			if (errorlogsPath != null) {
				try {
					String downFilePath = FileUtil.getWebRoot() + File.separator + errorlogsPath;
					downloadFileName = new String((firstPart+"-"+middlePart+"-"+lastPart+".txt").getBytes("gbk"), "ISO8859-1");
					File storedFile = new File(downFilePath);
					inputStream = new java.io.FileInputStream(storedFile);
				} catch (Exception e) {
					this.errorMessage = e.getMessage();
					return ERROR;
				}
				return "streamSuccess";
			}
		return "";
	}
	
	private PageInfo<MobileErrorLogs> emptyPage(int pageSize) {
		PageInfo<MobileErrorLogs> pageInfo = new PageInfo<MobileErrorLogs>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MobileErrorLogs>());
		return pageInfo;
	}

	public MobileErrorLogs getMobileErrorLogs() {
		return mobileErrorLogs;
	}

	public void setMobileErrorLogs(MobileErrorLogs mobileErrorLogs) {
		this.mobileErrorLogs = mobileErrorLogs;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String[] getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String[] attachFile) {
		this.attachFile = attachFile;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
