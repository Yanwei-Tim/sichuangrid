/**
 * 
 */
package com.tianque.serviceList.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.serviceList.domain.UnlicensedManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.UnlicensedManageService;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ReplyService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Scope("request")
@Namespace("/serviceList/unlicensedManageManage")
@Controller("unlicensedManageController")
public class UnlicensedManageController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(UnlicensedManageController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("unlicensed");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private UnlicensedManageService unlicensedManageService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private UnlicensedManage unlicensedManage;
	//批量删除ids
	private String ids;
	//附件Id
	private Long attachFileId;
	/** 附件名字 */
	private String[] attachFileNames;
	private String attachFile;
	private String attachFiles;
	/**
	 * 食药工商回复列表
	 * @return
	 * @throws Exception
	 */
	private List<Reply> serviceListReplyList;
	
	@Action(value = "addUnlicensedManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addUnlicensedManageMobile() throws Exception {
			if (unlicensedManage == null) {
				logger.error("取缔无证无照经营协管新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				unlicensedManage.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				unlicensedManage.setAttachFileNames(filenames);
			}
			unlicensedManage = unlicensedManageService.addUnlicensedManage(unlicensedManage);
			return SUCCESS;
	}
	@Action(value = "updateUnlicensedManageSignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updateUnlicensedManageSignDetailMobile() throws Exception {

		unlicensedManageService.signUnlicensedManage(unlicensedManage);
		return SUCCESS;
	}
	
	@Action(value = "viewUnlicensedManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "unlicensedManage", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewUnlicensedManageMobile() throws Exception {

		unlicensedManage=unlicensedManageService.getUnlicensedManageById(unlicensedManage.getId());
		return SUCCESS;
	}
	
	@Action(value = "listUnlicensedManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listUnlicensedManageMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(unlicensedManageService.getUnlicensedManageListByQuery
				(unlicensedManage, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/unlicensedManage/maintainUnlicensedManage.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/unlicensedManage/signUnlicensedManage.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/unlicensedManage/replyUnlicensedManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(unlicensedManage==null){
				unlicensedManage=new UnlicensedManage();
			}
			unlicensedManage.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewUnlicensedManage();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewUnlicensedManage();
			unlicensedManage.setSignPeople(ThreadVariable.getUser().getName());
			unlicensedManage.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(unlicensedManage==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			unlicensedManage.setReply(reply);
			return "reply";
		}
		logger.error("没有该请求！");
		errorMessage="没有该请求";
		return ERROR;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getUnlicensedManageList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getUnlicensedManageList() throws Exception {
			PageInfo<UnlicensedManage> pageInfo = unlicensedManageService.getUnlicensedManageListByQuery(
					unlicensedManage, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/unlicensedManage/viewUnlicensedManageReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存取缔无证无照经营协管
	 * 
	 * @return
	 */
	@Action(value = "addUnlicensedManage", results = {
			@Result(name = "success", type = "json", params = { "root", "unlicensedManage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addUnlicensedManage() throws Exception {
			if (unlicensedManage == null) {
				logger.error("取缔无证无照经营协管新增参数错误！");
				return ERROR;
			}
			unlicensedManage = unlicensedManageService.addUnlicensedManage(unlicensedManage);
			return SUCCESS;
	}

	/**
	 *	更新取缔无证无照经营协管
	 * 
	 * @return
	 */
	@Action(value = "updateUnlicensedManage", results = { @Result(name = "success", type = "json", params = {
			"root", "unlicensedManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updateUnlicensedManage() throws Exception {
			if (unlicensedManage == null) {
				logger.error("取缔无证无照经营协管修改参数错误！");
				return ERROR;
			}
			unlicensedManage = unlicensedManageService.updateUnlicensedManage(unlicensedManage);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewUnlicensedManage", results = {
			@Result(name = "success", type = "json", params = { "root", "unlicensedManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewUnlicensedManage() throws Exception {
			if(unlicensedManage==null||unlicensedManage.getId()==null){
				logger.error("取缔无证无照经营协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			unlicensedManage=unlicensedManageService.getUnlicensedManageById(unlicensedManage.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除取缔无证无照经营协管
	 * 
	 * @return
	 */
	@Action(value = "deleteUnlicensedManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deleteUnlicensedManage() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("取缔无证无照经营协管批量删除参数错误！");
				return ERROR;
			}
			unlicensedManageService.deleteUnlicensedManageByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收取缔无证无照经营协管
	 * 
	 * @return
	 */
	@Action(value = "signUnlicensedManage", results = { @Result(name = "success", type = "json", params = {
			"root", "unlicensedManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signUnlicensedManage() throws Exception {
			if (unlicensedManage == null) {
				logger.error("取缔无证无照经营协管修改参数错误！");
				return ERROR;
			}
			unlicensedManage = unlicensedManageService.signUnlicensedManage(unlicensedManage);
			return SUCCESS;
	}
	
	/**
	 *	回复取缔无证无照经营协管
	 * 
	 * @return
	 */
	@Action(value = "replyUnlicensedManage", results = { @Result(name = "success", type = "json", params = {
			"root", "unlicensedManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyUnlicensedManage() throws Exception {
			if (unlicensedManage == null||unlicensedManage.getReply()==null) {
				logger.error("取缔无证无照经营协管修改参数错误！");
				return ERROR;
			}
			unlicensedManage = unlicensedManageService.replyUnlicensedManage(unlicensedManage);
			return SUCCESS;
	}
	
	/**
	 * 附件下载
	 * @return
	 * @throws Exception
	 */
	@Action(value = "downLoadAttachFile")
	public String downLoadActualFile() throws Exception {
		if (attachFileId == null) {
			throw new BusinessValidationException("参数为空");
		}
		ServiceListAttch attachFile = serviceListAttachService
				.getServiceListAttchById(attachFileId);
		if (attachFile == null) {
			throw new BusinessValidationException("附件不存在");
		}
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot() + File.separator
					+ attachFile.getPath());
			downloadFileName = new String(attachFile.getName().getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}
	
	/**
	 * 附件下载
	 * @return
	 * @throws Exception
	 */
	@Action(value = "downLoadReplyActualFile")
	public String downLoadReplyActualFile() throws Exception {
		if (attachFileId == null) {
			throw new BusinessValidationException("参数为空");
		}
		ReplyAttch replyAttch = replyAttachService
				.getReplyAttchById(attachFileId);
		if (replyAttch == null) {
			throw new BusinessValidationException("附件不存在");
		}
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot() + File.separator
					+ replyAttch.getPath());
			downloadFileName = new String(replyAttch.getName().getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}
	
	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action(value = "viewUnlicensedManageDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/unlicensedManage/viewUnlicensedManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewUnlicensedManageDetail() throws Exception {
			if(unlicensedManage==null||unlicensedManage.getId()==null){
				logger.error("政策法规宣传单例查询参数错误:参数不正确");
				return ERROR;
			}
			unlicensedManage=unlicensedManageService.getUnlicensedManageById(unlicensedManage.getId());
			return SUCCESS;
	}
	
	public UnlicensedManage getUnlicensedManage() {
		return unlicensedManage;
	}

	public void setUnlicensedManage(UnlicensedManage unlicensedManage) {
		this.unlicensedManage = unlicensedManage;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getAttachFileId() {
		return attachFileId;
	}

	public void setAttachFileId(Long attachFileId) {
		this.attachFileId = attachFileId;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	public String getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}
	public List<Reply> getServiceListReplyList() {
		return serviceListReplyList;
	}
	public void setServiceListReplyList(List<Reply> serviceListReplyList) {
		this.serviceListReplyList = serviceListReplyList;
	}
	
}
