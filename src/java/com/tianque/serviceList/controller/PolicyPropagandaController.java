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
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.PolicyPropagandaService;
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
@Namespace("/serviceList/policyPropagandaManage")
@Controller("policyPropagandaController")
public class PolicyPropagandaController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(PolicyPropagandaController.class);
	private static final Integer TYPE=ServiceListEnum.getValue("policyPropaganda");
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PolicyPropagandaService policyPropagandaService;
	
	@Autowired
	private ServiceListAttachService serviceListAttachService;
	
	@Autowired
	private ReplyAttachService replyAttachService;
	
	@Autowired
	private ReplyService replyService;
	
	private Reply reply;
	
	private PolicyPropaganda policyPropaganda;
	//批量删除ids
	private String ids;
	//附件Id
	private Long attachFileId;
	/**
	 * 任务清单回复列表
	 * @return
	 * @throws Exception
	 */
	private List<Reply> serviceListReplyList;
	/**
	 * 回复类型
	 */
	private Integer serviceType;
	/**
	 * 服务ID
	 */
	private Long serviceId;
	private String attachFile;
	private String attachFiles;
	@Action(value = "addPolicyPropagandaMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addPolicyPropagandaMobile() throws Exception {
			if (policyPropaganda == null) {
				logger.error("政策法规宣传新增参数错误！");
				return ERROR;
			}
			if(attachFile!=null){
				String[] filenames=attachFile.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				policyPropaganda.setAttachFileNames(filenames);
			}
			if(attachFiles!=null){
				String[] filenames=attachFiles.split(",");
				for(int i=0;i<filenames.length;i++){
					filenames[i]=","+filenames[i];
				}
				policyPropaganda.setAttachFileNames(filenames);
			}
			policyPropaganda = policyPropagandaService.addPolicyPropaganda(policyPropaganda);
			return SUCCESS;
	}
	@Action(value = "updatePolicyPropagandaSignDetailMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updatePolicyPropagandaSignDetailMobile() throws Exception {

		policyPropagandaService.signPolicyPropaganda(policyPropaganda);
		return SUCCESS;
	}
	
	@Action(value = "viewPolicyPropagandaMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "policyPropaganda", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String viewPolicyPropagandaMobile() throws Exception {

		policyPropaganda=policyPropaganda=policyPropagandaService.getPolicyPropagandaById(policyPropaganda.getId());
		return SUCCESS;
	}
	
	@Action(value = "listPolicyPropagandaMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String listPolicyPropagandaMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(policyPropagandaService.getPolicyPropagandaListByQuery
				(policyPropaganda, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));

		return SUCCESS;
	}
	
	
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/policyPropaganda/maintainPolicyPropaganda.jsp"),
			@Result(name = "sign", location = "/serviceList/foodAndDrugsManage/policyPropaganda/signPolicyPropaganda.jsp"),
			@Result(name = "reply", location = "/serviceList/foodAndDrugsManage/policyPropaganda/replyPolicyPropaganda.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String dispatchOperate() throws Exception {
		if(mode.equals("add")){
			if(policyPropaganda==null){
				policyPropaganda=new PolicyPropaganda();
			}
			policyPropaganda.setInputTime(new Date());
			return SUCCESS;
		}else if(mode.equals("edit")){
			viewPolicyPropaganda();
			return SUCCESS;
		}else if(mode.equals("sign")){
			viewPolicyPropaganda();
			policyPropaganda.setSignPeople(ThreadVariable.getUser().getName());
			policyPropaganda.setSignDate(new Date());
			return "sign";
		}else if(mode.equals("reply")){
			if(policyPropaganda==null){
				logger.error("参数错误！");
				errorMessage="参数错误";
				return ERROR;
			}
			reply=new Reply();
			reply.setReplyPeople(ThreadVariable.getUser().getName());
			reply.setReplyDate(new Date());
			policyPropaganda.setReply(reply);
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
	@Action(value = "getPolicyPropagandaList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getPolicyPropagandaList() throws Exception {
			PageInfo<PolicyPropaganda> pageInfo = policyPropagandaService.getPolicyPropagandaListByQuery(
					policyPropaganda, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
	}
	
	@Action(value = "getServiceListMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "serviceListReplyList", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String getServiceListMobile() throws Exception {
		serviceListReplyList=replyService.queryServiceListReplyByMobile(serviceId,serviceType);
		return SUCCESS;
	}
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@Action(value = "getReplyList", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/policyPropaganda/viewPolicyPropandaReply.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String getReplyList() throws Exception {
			reply.setServiceType(TYPE);
			serviceListReplyList = replyService.getReplyList(
					reply , page, rows, sidx, sord).getResult();
			return SUCCESS;
	}
	
	/**
	 * 保存政策法规宣传
	 * 
	 * @return
	 */
	@Action(value = "addPolicyPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root", "policyPropaganda",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String addPolicyPropaganda() throws Exception {
			if (policyPropaganda == null) {
				logger.error("政策法规宣传新增参数错误！");
				return ERROR;
			}
			policyPropaganda = policyPropagandaService.addPolicyPropaganda(policyPropaganda);
			return SUCCESS;
	}

	/**
	 *	更新政策法规宣传
	 * 
	 * @return
	 */
	@Action(value = "updatePolicyPropaganda", results = { @Result(name = "success", type = "json", params = {
			"root", "policyPropaganda", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String updatePolicyPropaganda() throws Exception {
			if (policyPropaganda == null) {
				logger.error("政策法规宣传修改参数错误！");
				return ERROR;
			}
			policyPropaganda = policyPropagandaService.updatePolicyPropaganda(policyPropaganda);
			return SUCCESS;
	}

	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewPolicyPropagandaDetail", results = {
			@Result(name = "success", location = "/serviceList/foodAndDrugsManage/policyPropaganda/viewPolicyPropaganda.jsp"),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewPolicyPropagandaDetail() throws Exception {
			if(policyPropaganda==null||policyPropaganda.getId()==null){
				logger.error("政策法规宣传单例查询参数错误:参数不正确");
				return ERROR;
			}
			policyPropaganda=policyPropagandaService.getPolicyPropagandaById(policyPropaganda.getId());
			return SUCCESS;
	}
	
	/**
	 * 查询企业
	 * 
	 * @return
	 */
	@Action(value = "viewPolicyPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root", "policyPropaganda",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String viewPolicyPropaganda() throws Exception {
			if(policyPropaganda==null||policyPropaganda.getId()==null){
				logger.error("政策法规宣传单例查询参数错误:参数不正确");
				return ERROR;
			}
			policyPropaganda=policyPropagandaService.getPolicyPropagandaById(policyPropaganda.getId());
			return SUCCESS;
	}
	
	/**
	 * 批量删除政策法规宣传
	 * 
	 * @return
	 */
	@Action(value = "deletePolicyPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "resultResponse.resultMsg" }) })
	public String deletePolicyPropaganda() throws Exception {
			if (!StringUtil.isStringAvaliable(ids)) {
				logger.error("政策法规宣传批量删除参数错误！");
				return ERROR;
			}
			policyPropagandaService.deletePolicyPropagandaByIds(ids);
			return SUCCESS;
	}
	
	/**
	 *	签收政策法规宣传
	 * 
	 * @return
	 */
	@Action(value = "signPolicyPropaganda", results = { @Result(name = "success", type = "json", params = {
			"root", "policyPropaganda", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String signPolicyPropaganda() throws Exception {
			if (policyPropaganda == null) {
				logger.error("政策法规宣传修改参数错误！");
				return ERROR;
			}
			policyPropaganda = policyPropagandaService.signPolicyPropaganda(policyPropaganda);
			return SUCCESS;
	}
	
	/**
	 *	回复政策法规宣传
	 * 
	 * @return
	 */
	@Action(value = "replyPolicyPropaganda", results = { @Result(name = "success", type = "json", params = {
			"root", "policyPropaganda", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String replyPolicyPropaganda() throws Exception {
			if (policyPropaganda == null||policyPropaganda.getReply()==null) {
				logger.error("政策法规宣传修改参数错误！");
				return ERROR;
			}
			policyPropaganda = policyPropagandaService.replyPolicyPropaganda(policyPropaganda);
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
	
	public PolicyPropaganda getPolicyPropaganda() {
		return policyPropaganda;
	}

	public void setPolicyPropaganda(PolicyPropaganda policyPropaganda) {
		this.policyPropaganda = policyPropaganda;
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



	public List<Reply> getServiceListReplyList() {
		return serviceListReplyList;
	}
	public void setServiceListReplyList(List<Reply> serviceListReplyList) {
		this.serviceListReplyList = serviceListReplyList;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
	
}
