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
import com.tianque.serviceList.domain.PyramidSalesManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.PyramidSalesManageService;
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
@Namespace("/serviceList/pyramidSalesManageManage")
@Controller("pyramidSalesManageController")
public class PyramidSalesManageController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(PyramidSalesManageController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("pyramidSales");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PyramidSalesManageService pyramidSalesManageService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private PyramidSalesManage pyramidSalesManage;
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
	
	@Action(value = "addPyramidSalesManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addPyramidSalesManageMobile() throws Exception {
			if (pyramidSalesManage == null) {
				logger.error("打击非法传销协管新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				pyramidSalesManage.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				pyramidSalesManage.setAttachFileNames(filenames);
			}
			pyramidSalesManage = pyramidSalesManageService.addPyramidSalesManage(pyramidSalesManage);
			return SUCCESS;
	}
	@Action(value = "updatePyramidSalesManageSignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updatePyramidSalesManageSignDetailMobile() throws Exception {

		pyramidSalesManageService.signPyramidSalesManage(pyramidSalesManage);
		return SUCCESS;
	}
	
	@Action(value = "viewPyramidSalesManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "pyramidSalesManage", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewPyramidSalesManageMobile() throws Exception {

		pyramidSalesManage=pyramidSalesManageService.getPyramidSalesManageById(pyramidSalesManage.getId());
		return SUCCESS;
	}
	
	@Action(value = "listPyramidSalesManageMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listPyramidSalesManageMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(pyramidSalesManageService.getPyramidSalesManageListByQuery
				(pyramidSalesManage, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/pyramidSalesManage/maintainPyramidSalesManage.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/pyramidSalesManage/signPyramidSalesManage.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/pyramidSalesManage/replyPyramidSalesManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(pyramidSalesManage==null){
				pyramidSalesManage=new PyramidSalesManage();
			}
			pyramidSalesManage.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewPyramidSalesManage();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewPyramidSalesManage();
			pyramidSalesManage.setSignPeople(ThreadVariable.getUser().getName());
			pyramidSalesManage.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(pyramidSalesManage==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			pyramidSalesManage.setReply(reply);
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
	@Action(value = "getPyramidSalesManageList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getPyramidSalesManageList() throws Exception {
			PageInfo<PyramidSalesManage> pageInfo = pyramidSalesManageService.getPyramidSalesManageListByQuery(
					pyramidSalesManage, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/pyramidSalesManage/viewPyramidSalesManageReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存打击非法传销协管
	 * 
	 * @return
	 */
	@Action(value = "addPyramidSalesManage", results = {
			@Result(name = "success", type = "json", params = { "root", "pyramidSalesManage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addPyramidSalesManage() throws Exception {
			if (pyramidSalesManage == null) {
				logger.error("打击非法传销协管新增参数错误！");
				return ERROR;
			}
			pyramidSalesManage = pyramidSalesManageService.addPyramidSalesManage(pyramidSalesManage);
			return SUCCESS;
	}

	/**
	 *	更新打击非法传销协管
	 * 
	 * @return
	 */
	@Action(value = "updatePyramidSalesManage", results = { @Result(name = "success", type = "json", params = {
			"root", "pyramidSalesManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updatePyramidSalesManage() throws Exception {
			if (pyramidSalesManage == null) {
				logger.error("打击非法传销协管修改参数错误！");
				return ERROR;
			}
			pyramidSalesManage = pyramidSalesManageService.updatePyramidSalesManage(pyramidSalesManage);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewPyramidSalesManage", results = {
			@Result(name = "success", type = "json", params = { "root", "pyramidSalesManage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewPyramidSalesManage() throws Exception {
			if(pyramidSalesManage==null||pyramidSalesManage.getId()==null){
				logger.error("打击非法传销协管单例查询参数错误:参数不正确");
				return ERROR;
			}
			pyramidSalesManage=pyramidSalesManageService.getPyramidSalesManageById(pyramidSalesManage.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除打击非法传销协管
	 * 
	 * @return
	 */
	@Action(value = "deletePyramidSalesManage", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deletePyramidSalesManage() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("打击非法传销协管批量删除参数错误！");
				return ERROR;
			}
			pyramidSalesManageService.deletePyramidSalesManageByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收打击非法传销协管
	 * 
	 * @return
	 */
	@Action(value = "signPyramidSalesManage", results = { @Result(name = "success", type = "json", params = {
			"root", "pyramidSalesManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signPyramidSalesManage() throws Exception {
			if (pyramidSalesManage == null) {
				logger.error("打击非法传销协管修改参数错误！");
				return ERROR;
			}
			pyramidSalesManage = pyramidSalesManageService.signPyramidSalesManage(pyramidSalesManage);
			return SUCCESS;
	}
	
	/**
	 *	回复打击非法传销协管
	 * 
	 * @return
	 */
	@Action(value = "replyPyramidSalesManage", results = { @Result(name = "success", type = "json", params = {
			"root", "pyramidSalesManage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyPyramidSalesManage() throws Exception {
			if (pyramidSalesManage == null||pyramidSalesManage.getReply()==null) {
				logger.error("打击非法传销协管修改参数错误！");
				return ERROR;
			}
			pyramidSalesManage = pyramidSalesManageService.replyPyramidSalesManage(pyramidSalesManage);
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
	@Action(value = "viewPyramidSalesManageDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/pyramidSalesManage/viewPyramidSalesManage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewPyramidSalesManageDetail() throws Exception {
			if(pyramidSalesManage==null||pyramidSalesManage.getId()==null){
				logger.error("政策法规宣传单例查询参数错误:参数不正确");
				return ERROR;
			}
			pyramidSalesManage=pyramidSalesManageService.getPyramidSalesManageById(pyramidSalesManage.getId());
			return SUCCESS;
	}
	
	public PyramidSalesManage getPyramidSalesManage() {
		return pyramidSalesManage;
	}

	public void setPyramidSalesManage(PyramidSalesManage pyramidSalesManage) {
		this.pyramidSalesManage = pyramidSalesManage;
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
