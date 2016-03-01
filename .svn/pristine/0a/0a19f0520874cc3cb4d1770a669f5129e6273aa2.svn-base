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
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.DrugsSaftyService;
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
@Namespace("/serviceList/drugsSaftyManage")
@Controller("drugsSaftyController")
public class DrugsSaftyController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(DrugsSaftyController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("drugsSafty");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DrugsSaftyService drugsSaftyService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private DrugsSafty drugsSafty;
	
	private List<Reply> serviceListReplyList;
	//批量删除ids
	private String ids;
	//附件Id
	private Long attachFileId;
	private String attachFile;
	private String attachFiles;
	@Action(value = "addDrugsSaftyMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addDrugsSaftyMobile() throws Exception {

			if (drugsSafty == null) {
				logger.error("药品安全协管新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				drugsSafty.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				drugsSafty.setAttachFileNames(filenames);
			}
			drugsSafty = drugsSaftyService.addDrugsSafty(drugsSafty);
			return SUCCESS;
	}
	
	@Action(value = "updateDrugsSaftySignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updateDrugsSaftySignDetailMobile() throws Exception {

		drugsSaftyService.signDrugsSafty(drugsSafty);
		return SUCCESS;
	}
	
	@Action(value = "viewDrugsSaftyMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "drugsSafty", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewDrugsSaftyMobile() throws Exception {

		drugsSafty=drugsSaftyService.getDrugsSaftyById(drugsSafty.getId());
		return SUCCESS;
	}
	
	@Action(value = "listDrugsSaftyMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listDrugsSaftyMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(drugsSaftyService.getDrugsSaftyListByQuery
				(drugsSafty, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/drugysSafty/maintainDrugsSafty.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/drugysSafty/signDrugsSafty.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/drugysSafty/replyDrugsSafty.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(drugsSafty==null){
				drugsSafty=new DrugsSafty();
			}
			drugsSafty.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewDrugsSafty();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewDrugsSafty();
			drugsSafty.setSignPeople(ThreadVariable.getUser().getName());
			drugsSafty.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(drugsSafty==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			drugsSafty.setReply(reply);
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
	@Action(value = "getDrugsSaftyList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getDrugsSaftyList() throws Exception {
			PageInfo<DrugsSafty> pageInfo = drugsSaftyService.getDrugsSaftyListByQuery(
					drugsSafty, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/drugysSafty/viewDrugsSaftyReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存药品安全协管
	 * 
	 * @return
	 */
	@Action(value = "addDrugsSafty", results = {
			@Result(name = "success", type = "json", params = { "root", "drugsSafty",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addDrugsSafty() throws Exception {
			if (drugsSafty == null) {
				logger.error("药品安全协管新增参数错误！");
				return ERROR;
			}
			drugsSafty = drugsSaftyService.addDrugsSafty(drugsSafty);
			return SUCCESS;
	}

	/**
	 *	更新药品安全协管
	 * 
	 * @return
	 */
	@Action(value = "updateDrugsSafty", results = { @Result(name = "success", type = "json", params = {
			"root", "drugsSafty", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updateDrugsSafty() throws Exception {
			if (drugsSafty == null) {
				logger.error("药品安全协管修改参数错误！");
				return ERROR;
			}
			drugsSafty = drugsSaftyService.updateDrugsSafty(drugsSafty);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewDrugsSafty", results = {
			@Result(name = "success", type = "json", params = { "root", "drugsSafty",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewDrugsSafty() throws Exception {
			if(drugsSafty==null||drugsSafty.getId()==null){
				logger.error("药品安全协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			drugsSafty=drugsSaftyService.getDrugsSaftyById(drugsSafty.getId());
			return SUCCESS;
	}
	
	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action(value = "viewDrugsSaftyDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/drugysSafty/viewDrugsSafty.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewDrugsSaftyDetail() throws Exception {
			if(drugsSafty==null||drugsSafty.getId()==null){
				logger.error("药品安全协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			drugsSafty=drugsSaftyService.getDrugsSaftyById(drugsSafty.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除药品安全协管
	 * 
	 * @return
	 */
	@Action(value = "deleteDrugsSafty", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deleteDrugsSafty() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("药品安全协管批量删除参数错误！");
				return ERROR;
			}
			drugsSaftyService.deleteDrugsSaftyByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收药品安全协管
	 * 
	 * @return
	 */
	@Action(value = "signDrugsSafty", results = { @Result(name = "success", type = "json", params = {
			"root", "drugsSafty", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signDrugsSafty() throws Exception {
			if (drugsSafty == null) {
				logger.error("药品安全协管修改参数错误！");
				return ERROR;
			}
			drugsSafty = drugsSaftyService.signDrugsSafty(drugsSafty);
			return SUCCESS;
	}
	
	/**
	 *	回复药品安全协管
	 * 
	 * @return
	 */
	@Action(value = "replyDrugsSafty", results = { @Result(name = "success", type = "json", params = {
			"root", "drugsSafty", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyDrugsSafty() throws Exception {
			if (drugsSafty == null||drugsSafty.getReply()==null) {
				logger.error("药品安全协管修改参数错误！");
				return ERROR;
			}
			drugsSafty = drugsSaftyService.replyDrugsSafty(drugsSafty);
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
	
	public DrugsSafty getDrugsSafty() {
		return drugsSafty;
	}

	public void setDrugsSafty(DrugsSafty drugsSafty) {
		this.drugsSafty = drugsSafty;
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
