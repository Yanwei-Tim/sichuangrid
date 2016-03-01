/**
 * 
 */
package com.tianque.serviceList.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.domain.PropagandaAndVerificationVo;
import com.tianque.plugin.taskList.service.GridConfigTaskService;
import com.tianque.serviceList.dao.DrugsSaftyDao;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.DrugsSaftyService;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ReplyService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.serviceList.validater.DrugsSaftyValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;

@Service("drugsSaftyServiceImpl")
@Transactional
public class DrugsSaftyServiceImpl implements DrugsSaftyService{
	@Autowired 
	private DrugsSaftyDao drugsSaftyDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceListAttachService attachService;
	@Autowired
	private ReplyAttachService replyAttachService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private DrugsSaftyValidatorImpl validatorImpl;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired 
	private GridConfigTaskService configTaskService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	
	private static final Integer TYPE=ServiceListEnum.getValue("drugsSafty");
	@Override
	public DrugsSafty addDrugsSafty(DrugsSafty info) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillDrugsSaftyOrg(info);
			info.setTelephone(ThreadVariable.getUser().getMobile());
			String[] attachFileNames = info.getAttachFileNames();
			if(info.getIsSign()==null){
				info.setIsSign(0);
			}
			if(info.getIsSign()==null){
				info.setIsReply(0);
			}
			validatorImpl.validate(info);
			info= drugsSaftyDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("DrugsSaftyServiceImpl的addDrugsSafty方法出错，原因：",
					"新增信息不全", e);
		}
	}
	
	@Override
	public PageInfo<DrugsSafty> getDrugsSaftyListByQuery(
			DrugsSafty drugsSafty, Integer page, Integer rows,
			String sidx, String sord) {
		if(drugsSafty==null){
			drugsSafty=new DrugsSafty();
		}
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		fillDrugsSaftyOrg(drugsSafty);
		if(isGridConfigTaskSearch(drugsSafty)){
			if(!StringUtil.isEmpty(drugsSafty.getMode())&&
					"true".equals(drugsSafty.getMode())){
				drugsSafty.setFunOrgId(drugsSafty.getOrganization().getId());
				drugsSafty.setMode("gridConfigService");
			}
			drugsSafty.getOrganization().setOrgInternalCode(null);
		}else if(orgTypeDict.getId().equals(drugsSafty.getOrganization().getOrgType().getId())){
			drugsSafty.getOrganization().setOrgInternalCode(organizationDubboService
						.getOrgInternalCodeById(drugsSafty.getOrganization().getParentOrg().getId()));
		}else{
			drugsSafty.getOrganization().setOrgInternalCode(drugsSafty.getOrganization().getOrgInternalCode());
		}
		PageInfo<DrugsSafty> infos=drugsSaftyDao.findPagerBySearchVo(drugsSafty, page, rows, sidx, sord);
		//设置督办时间
//		infos=supervise(infos);
		return infos;
	}
	//设置督办时间
	private PageInfo<DrugsSafty> supervise(PageInfo<DrugsSafty> infos ){
		List<DrugsSafty> drugsSafties= infos.getResult();
		for (DrugsSafty drugsSafty:drugsSafties) {
//			propaganda.setSupervise(5);
		}
		return infos;
	}
	public void fillDrugsSaftyOrg(DrugsSafty drugsSafty) {
		if (drugsSafty.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(drugsSafty.getOrganization()
				.getId());
		String[] orgs = organization.getFullOrgName().split(ModuleConstants.SEPARATOR);
		if (orgs.length > 2) {
			String org = orgs[orgs.length - 2] + ModuleConstants.SEPARATOR + orgs[orgs.length - 1];
			organization.setFullOrgName(org);
		}
		drugsSafty.setOrganization(organization);
	}
	
	@Override
	public DrugsSafty updateDrugsSafty(
			DrugsSafty drugsSafty) {
		fillDrugsSaftyOrg(drugsSafty);
		validatorImpl.validate(drugsSafty);
		String[] attachIds=drugsSafty.getDeleteAttachIds().split(",");
		for (int i = 0; i < attachIds.length; i++) {
			if(!attachIds[i].equals("")){
				attachService.deleteServiceListAttch(Long.parseLong(attachIds[i]));
			}
		}
		attachService.addServiceListAttch(drugsSafty.getAttachFileNames(), drugsSafty.getId(),TYPE);
		return drugsSaftyDao.update(drugsSafty);
	}

	@Override
	public void deleteDrugsSaftyByIds(String ids) {
		String[]id= ids.split(",");
		for (String objId:id){
			List<Reply>replies=replyService.getReplyByIdAndType(Long.parseLong(objId), TYPE);
			for (Reply reply:replies) {
				replyAttachService.deleteReplyAttchByIds(reply.getId(), TYPE);
			}
			replyService.deleteReplyByIds(Long.parseLong(objId), TYPE);
			drugsSaftyDao.delete(Long.parseLong(objId));
			attachService.deleteServiceListAttchByIds(Long.parseLong(objId),TYPE);
		}
	}

	@Override
	public DrugsSafty getDrugsSaftyById(Long id) {
		DrugsSafty drugsSafty=drugsSaftyDao.get(id);
		if(drugsSafty!=null){
			drugsSafty.setPhotos(attachService.getServiceListAttchByIdAndType(id, TYPE));
			User user = permissionDubboService.getFullUserByUerName(drugsSafty.getCreateUser());
			if(user!=null){
				drugsSafty.setCreateUser(user.getName());
			}
		}
		fillOrganization(drugsSafty);
		return drugsSafty;
	}
	
	public void fillOrganization(DrugsSafty drugsSafty) {
		if (drugsSafty.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(drugsSafty
				.getOrganization().getId());
		organization = ControllerHelper.proccessRelativeOrgNameByOrg(organization, organizationDubboService);
		drugsSafty.setOrganization(organization);
	}

	@Override
	public DrugsSafty signDrugsSafty(
			DrugsSafty drugsSafty) {
		DrugsSafty fSafty=drugsSaftyDao.get(drugsSafty.getId());
		fSafty.setUpdateDate(new Date());
		fSafty.setUpdateUser(drugsSafty.getSignPeople());
		fSafty.setSignContent(drugsSafty.getSignContent());
		fSafty.setSignDate(drugsSafty.getSignDate());
		fSafty.setSignPeople(drugsSafty.getSignPeople());
		fSafty.setIsSign(1);
		return drugsSaftyDao.update(fSafty);
	}

	@Override
	public DrugsSafty replyDrugsSafty(
			DrugsSafty drugsSafty) {
		DrugsSafty fSafty=drugsSaftyDao.get(drugsSafty.getId());
		fSafty.setIsReply(1);
		drugsSaftyDao.update(fSafty);
		Reply reply=drugsSafty.getReply();
		reply.setServiceId(drugsSafty.getId());
		reply.setServiceType(TYPE);
		reply=replyService.addReply(reply);
		replyAttachService.addReplyAttch(drugsSafty.getAttachFileNames(), reply.getId(),TYPE);
		return drugsSafty;
	}
	
	//判断是否为网格配置后的查询
		private boolean isGridConfigTaskSearch(DrugsSafty drugsSafty){
			Long funId=drugsSafty.getOrganization().getId();
			if(drugsSafty.getMode()==null){
				return false;
			}
			if(drugsSafty.getMode().equals("gridConfigService")&&
					funId.equals(ThreadVariable.getOrganization().getId())){
				return true;
			}
			if(drugsSafty.getMode().equals("true")&&configTaskService.isHasGridTaskList(funId,Constants.TASKLIST_KEY)){
				return true;
			}
			return false;
		}
			
}
