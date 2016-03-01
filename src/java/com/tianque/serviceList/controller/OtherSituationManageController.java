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
import com.tianque.serviceList.domain.OtherSituationManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.OtherSituationManageService;
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
@Namespace("/serviceList/otherSituationManageManage")
@Controller("otherSituationManageController")
public class OtherSituationManageController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(OtherSituationManageController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("otherSituation");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OtherSituationManageService otherSituationManageService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private OtherSituationManage otherSituationManage;
	//批量删除ids
	private String ids;
	//附件Id
	private Long attachFileId;
	
	private String attachFile;
	private String attachFiles;
	/**
	 * 食药工商回复列表
	 * @return
	 * @throws Exception
	 */
	private List<Reply> serviceListReplyList;
	
	@Action(value = "addOtherSituationManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addOtherSituationManageMobile() throws Exception {
			if (otherSituationManage == null) {
				logger.error("其它新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				otherSituationManage.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				otherSituationManage.setAttachFileNames(filenames);
			}
			otherSituationManage = otherSituationManageService.addOtherSituationManage(otherSituationManage);
			return SUCCESS;
	}
	
	@Action(value = "updateOtherSituationManageSignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updateOtherSituationManageSignDetailMobile() throws Exception {

		otherSituationManageService.signOtherSituationManage(otherSituationManage);
		return SUCCESS;
	}
	
	@Action(value = "viewOtherSituationManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "otherSituationManage", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewOtherSituationManageMobile() throws Exception {

		otherSituationManage=otherSituationManageService.getOtherSituationManageById(otherSituationManage.getId());
		return SUCCESS;
	}
	
	@Action(value = "listOtherSituationManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listOtherSituationManageMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(otherSituationManageService.getOtherSituationManageListByQuery
				(otherSituationManage, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/otherSituationManage/maintainOtherSituationManage.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/otherSituationManage/signOtherSituationManage.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/otherSituationManage/replyOtherSituationManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(otherSituationManage==null){
				otherSituationManage=new OtherSituationManage();
			}
			otherSituationManage.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewOtherSituationManage();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewOtherSituationManage();
			otherSituationManage.setSignPeople(ThreadVariable.getUser().getName());
			otherSituationManage.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(otherSituationManage==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			otherSituationManage.setReply(reply);
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
	@Action(value = "getOtherSituationManageList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getOtherSituationManageList() throws Exception {
			PageInfo<OtherSituationManage> pageInfo = otherSituationManageService.getOtherSituationManageListByQuery(
					otherSituationManage, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/otherSituationManage/viewOtherSituationManageReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存其他情况
	 * 
	 * @return
	 */
	@Action(value = "addOtherSituationManage", results = {
			@Result(name = "success", type = "json", params = { "root", "otherSituationManage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addOtherSituationManage() throws Exception {
			if (otherSituationManage == null) {
				logger.error("其他情况新增参数错误！");
				return ERROR;
			}
			otherSituationManage = otherSituationManageService.addOtherSituationManage(otherSituationManage);
			return SUCCESS;
	}

	/**
	 *	更新其他情况
	 * 
	 * @return
	 */
	@Action(value = "updateOtherSituationManage", results = { @Result(name = "success", type = "json", params = {
			"root", "otherSituationManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updateOtherSituationManage() throws Exception {
			if (otherSituationManage == null) {
				logger.error("其他情况修改参数错误！");
				return ERROR;
			}
			otherSituationManage = otherSituationManageService.updateOtherSituationManage(otherSituationManage);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewOtherSituationManage", results = {
			@Result(name = "success", type = "json", params = { "root", "otherSituationManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewOtherSituationManage() throws Exception {
			if(otherSituationManage==null||otherSituationManage.getId()==null){
				logger.error("其他情况单例查询参数错误:参数不正确");
				return ERROR;
			}
			otherSituationManage=otherSituationManageService.getOtherSituationManageById(otherSituationManage.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除其他情况
	 * 
	 * @return
	 */
	@Action(value = "deleteOtherSituationManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deleteOtherSituationManage() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("其他情况批量删除参数错误！");
				return ERROR;
			}
			otherSituationManageService.deleteOtherSituationManageByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收其他情况
	 * 
	 * @return
	 */
	@Action(value = "signOtherSituationManage", results = { @Result(name = "success", type = "json", params = {
			"root", "otherSituationManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signOtherSituationManage() throws Exception {
			if (otherSituationManage == null) {
				logger.error("其他情况修改参数错误！");
				return ERROR;
			}
			otherSituationManage = otherSituationManageService.signOtherSituationManage(otherSituationManage);
			return SUCCESS;
	}
	
	/**
	 *	回复其他情况
	 * 
	 * @return
	 */
	@Action(value = "replyOtherSituationManage", results = { @Result(name = "success", type = "json", params = {
			"root", "otherSituationManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyOtherSituationManage() throws Exception {
			if (otherSituationManage == null||otherSituationManage.getReply()==null) {
				logger.error("其他情况修改参数错误！");
				return ERROR;
			}
			otherSituationManage = otherSituationManageService.replyOtherSituationManage(otherSituationManage);
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
	@Action(value = "viewOtherSituationManageDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/otherSituationManage/viewOtherSituationManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewOtherSituationManageDetail() throws Exception {
			if(otherSituationManage==null||otherSituationManage.getId()==null){
				logger.error("其他情况单例查询参数错误:参数不正确");
				return ERROR;
			}
			otherSituationManage=otherSituationManageService.getOtherSituationManageById(otherSituationManage.getId());
			return SUCCESS;
	}
	
	public OtherSituationManage getOtherSituationManage() {
		return otherSituationManage;
	}

	public void setOtherSituationManage(OtherSituationManage otherSituationManage) {
		this.otherSituationManage = otherSituationManage;
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
