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
import com.tianque.serviceList.domain.BusinessManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.BusinessManageService;
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
@Namespace("/serviceList/businessManageManage")
@Controller("businessManageController")
public class BusinessManageController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(BusinessManageController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("bussiness");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private BusinessManageService businessManageService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private BusinessManage businessManage;
	//批量删除ids
	private String ids;
	//附件Id
	private Long attachFileId;
	private String attachFile;
	private String attachFiles;
	private List<Reply> serviceListReplyList;
	
	@Action(value = "addBusinessManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "businessManage", "ignoreHierarchy",
					"false" , "excludeNullProperties", "true"}),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addBusinessManageMobile() throws Exception {
			if (businessManage == null) {
				logger.error("工商管理协管新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				businessManage.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				businessManage.setAttachFileNames(filenames);
			}
			businessManage = businessManageService.addBusinessManage(businessManage);
			return SUCCESS;
	}
	
	@Action(value = "updateBusinessManageSignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updateBusinessManageSignDetailMobile() throws Exception {

		businessManageService.signBusinessManage(businessManage);
		return SUCCESS;
	}
	
	@Action(value = "viewBusinessManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "businessManage", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewBusinessManageMobile() throws Exception {

		businessManage=businessManageService.getBusinessManageById(businessManage.getId());
		return SUCCESS;
	}
	
	@Action(value = "listBusinessManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listBusinessManageMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(businessManageService.getBusinessManageListByQuery
				(businessManage, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/businessManage/maintainBusinessManage.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/businessManage/signBusinessManage.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/businessManage/replyBusinessManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(businessManage==null){
				businessManage=new BusinessManage();
			}
			businessManage.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewBusinessManage();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewBusinessManage();
			businessManage.setSignPeople(ThreadVariable.getUser().getName());
			businessManage.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(businessManage==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			businessManage.setReply(reply);
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
	@Action(value = "getBusinessManageList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getBusinessManageList() throws Exception {
			PageInfo<BusinessManage> pageInfo = businessManageService.getBusinessManageListByQuery(
					businessManage, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/businessManage/viewBusinessManageReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存工商管理协管
	 * 
	 * @return
	 */
	@Action(value = "addBusinessManage", results = {
			@Result(name = "success", type = "json", params = { "root", "businessManage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addBusinessManage() throws Exception {
			if (businessManage == null) {
				logger.error("工商管理协管新增参数错误！");
				return ERROR;
			}
			businessManage = businessManageService.addBusinessManage(businessManage);
			return SUCCESS;
	}

	/**
	 *	更新工商管理协管
	 * 
	 * @return
	 */
	@Action(value = "updateBusinessManage", results = { @Result(name = "success", type = "json", params = {
			"root", "businessManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updateBusinessManage() throws Exception {
			if (businessManage == null) {
				logger.error("工商管理协管修改参数错误！");
				return ERROR;
			}
			businessManage = businessManageService.updateBusinessManage(businessManage);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewBusinessManage", results = {
			@Result(name = "success", type = "json", params = { "root", "businessManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewBusinessManage() throws Exception {
			if(businessManage==null||businessManage.getId()==null){
				logger.error("工商管理协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			businessManage=businessManageService.getBusinessManageById(businessManage.getId());
			return SUCCESS;
	}
	
	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action(value = "viewBusinessManageDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/businessManage/viewBusinessManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewBusinessManageDetail() throws Exception {
			if(businessManage==null||businessManage.getId()==null){
				logger.error("工商管理协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			businessManage=businessManageService.getBusinessManageById(businessManage.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除工商管理协管
	 * 
	 * @return
	 */
	@Action(value = "deleteBusinessManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deleteBusinessManage() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("工商管理协管批量删除参数错误！");
				return ERROR;
			}
			businessManageService.deleteBusinessManageByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收工商管理协管
	 * 
	 * @return
	 */
	@Action(value = "signBusinessManage", results = { @Result(name = "success", type = "json", params = {
			"root", "businessManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signBusinessManage() throws Exception {
			if (businessManage == null) {
				logger.error("工商管理协管修改参数错误！");
				return ERROR;
			}
			businessManage = businessManageService.signBusinessManage(businessManage);
			return SUCCESS;
	}
	
	/**
	 *	回复工商管理协管
	 * 
	 * @return
	 */
	@Action(value = "replyBusinessManage", results = { @Result(name = "success", type = "json", params = {
			"root", "businessManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyBusinessManage() throws Exception {
			if (businessManage == null||businessManage.getReply()==null) {
				logger.error("工商管理协管修改参数错误！");
				return ERROR;
			}
			businessManage = businessManageService.replyBusinessManage(businessManage);
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
	
	public BusinessManage getBusinessManage() {
		return businessManage;
	}

	public void setBusinessManage(BusinessManage businessManage) {
		this.businessManage = businessManage;
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
