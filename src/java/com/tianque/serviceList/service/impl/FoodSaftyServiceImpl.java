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
import com.tianque.serviceList.dao.FoodSaftyDao;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.FoodSafty;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.FoodSaftyService;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ReplyService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.serviceList.validater.FoodSaftyValidatorImpl;
import com.tianque.serviceList.validater.PolicyPropagandaValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;

@Service("foodSaftyServiceImpl")
@Transactional
public class FoodSaftyServiceImpl implements FoodSaftyService{
	@Autowired 
	private FoodSaftyDao foodSaftyDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceListAttachService attachService;
	@Autowired
	private ReplyAttachService replyAttachService;
	@Autowired
	private ReplyService replyService;
	@Autowired 
	private FoodSaftyValidatorImpl validatorImpl;
	@Autowired 
	private GridConfigTaskService configTaskService;
	@Autowired 
	private PropertyDictService propertyDictService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	
	private static final Integer TYPE=ServiceListEnum.getValue("foodSafty");
	@Override
	public FoodSafty addFoodSafty(FoodSafty info) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillFoodSaftyOrg(info);
			info.setTelephone(ThreadVariable.getUser().getMobile());
			String[] attachFileNames = info.getAttachFileNames();
			if(info.getIsSign()==null){
				info.setIsSign(0);
			}
			if(info.getIsSign()==null){
				info.setIsReply(0);
			}
			validatorImpl.validatorFoodSafty(info);
			info= foodSaftyDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("FoodSaftyServiceImpl的addFoodSafty方法出错，原因：",
					"新增信息不全", e);
		}
	}
	
	@Override
	public PageInfo<FoodSafty> getFoodSaftyListByQuery(
			FoodSafty foodSafty, Integer page, Integer rows,
			String sidx, String sord) {
		if(foodSafty==null){
			foodSafty=new FoodSafty();
		}
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		
		fillFoodSaftyOrg(foodSafty);
		if(isGridConfigTaskSearch(foodSafty)){
			if(!StringUtil.isEmpty(foodSafty.getMode())&&
					"true".equals(foodSafty.getMode())){
				foodSafty.setFunOrgId(foodSafty.getOrganization().getId());
				foodSafty.setMode("gridConfigService");
			}
			foodSafty.getOrganization().setOrgInternalCode(null);
		}else if(orgTypeDict.getId().equals(foodSafty.getOrganization().getOrgType().getId())){
			foodSafty.getOrganization().setOrgInternalCode(organizationDubboService
						.getOrgInternalCodeById(foodSafty.getOrganization().getParentOrg().getId()));
		}else{
			foodSafty.getOrganization().setOrgInternalCode(foodSafty.getOrganization().getOrgInternalCode());
		}
		PageInfo<FoodSafty> infos=foodSaftyDao.findPagerBySearchVo(foodSafty, page, rows, sidx, sord);
		//设置督办时间
//		infos=supervise(infos);
		return infos;
	}
	//设置督办时间
	private PageInfo<FoodSafty> supervise(PageInfo<FoodSafty> infos ){
		List<FoodSafty> foodSafties= infos.getResult();
		for (FoodSafty foodSafty:foodSafties) {
//			propaganda.setSupervise(5);
		}
		return infos;
	}
	public void fillFoodSaftyOrg(FoodSafty foodSafty) {
		if (foodSafty.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(foodSafty.getOrganization()
				.getId());
		String[] orgs = organization.getFullOrgName().split(ModuleConstants.SEPARATOR);
		if (orgs.length > 2) {
			String org = orgs[orgs.length - 2] + ModuleConstants.SEPARATOR + orgs[orgs.length - 1];
			organization.setFullOrgName(org);
		}
		foodSafty.setOrganization(organization);
	}
	
	@Override
	public FoodSafty updateFoodSafty(
			FoodSafty foodSafty) {
		fillFoodSaftyOrg(foodSafty);
		validatorImpl.validatorFoodSafty(foodSafty);
		String[] attachIds=foodSafty.getDeleteAttachIds().split(",");
		for (int i = 0; i < attachIds.length; i++) {
			if(!attachIds[i].equals("")){
				attachService.deleteServiceListAttch(Long.parseLong(attachIds[i]));
			}
		}
		attachService.addServiceListAttch(foodSafty.getAttachFileNames(), foodSafty.getId(),TYPE);
		return foodSaftyDao.update(foodSafty);
	}

	@Override
	public void deleteFoodSaftyByIds(String ids) {
		String[]id= ids.split(",");
		for (String objId:id){
			List<Reply>replies=replyService.getReplyByIdAndType(Long.parseLong(objId), TYPE);
			for (Reply reply:replies) {
				replyAttachService.deleteReplyAttchByIds(reply.getId(), TYPE);
			}
			replyService.deleteReplyByIds(Long.parseLong(objId), TYPE);
			foodSaftyDao.delete(Long.parseLong(objId));
			attachService.deleteServiceListAttchByIds(Long.parseLong(objId),TYPE);
		}
	}

	@Override
	public FoodSafty getFoodSaftyById(Long id) {
		FoodSafty foodSafty=foodSaftyDao.get(id);
		if(foodSafty!=null){
			foodSafty.setPhotos(attachService.getServiceListAttchByIdAndType(id, TYPE));
			User user = permissionDubboService.getFullUserByUerName(foodSafty.getCreateUser());
			if(user!=null){
				foodSafty.setCreateUser(user.getName());
			}
		}
		fillOrganization(foodSafty);
		return foodSafty;
	}
	
	public void fillOrganization(FoodSafty foodSafty) {
		if (foodSafty.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(foodSafty
				.getOrganization().getId());
		organization = ControllerHelper.proccessRelativeOrgNameByOrg(organization, organizationDubboService);
		foodSafty.setOrganization(organization);
	}

	@Override
	public FoodSafty signFoodSafty(
			FoodSafty foodSafty) {
		FoodSafty fSafty=foodSaftyDao.get(foodSafty.getId());
		fSafty.setUpdateDate(new Date());
		fSafty.setUpdateUser(foodSafty.getSignPeople());
		fSafty.setSignContent(foodSafty.getSignContent());
		fSafty.setSignDate(foodSafty.getSignDate());
		fSafty.setSignPeople(foodSafty.getSignPeople());
		fSafty.setIsSign(1);
		return foodSaftyDao.update(fSafty);
	}

	@Override
	public FoodSafty replyFoodSafty(
			FoodSafty foodSafty) {
		FoodSafty fSafty=foodSaftyDao.get(foodSafty.getId());
		fSafty.setIsReply(1);
		foodSaftyDao.update(fSafty);
		Reply reply=foodSafty.getReply();
		reply.setServiceId(foodSafty.getId());
		reply.setServiceType(TYPE);
		reply=replyService.addReply(reply);
		replyAttachService.addReplyAttch(foodSafty.getAttachFileNames(), reply.getId(),TYPE);
		return foodSafty;
	}
	
	//判断是否为网格配置后的查询
		private boolean isGridConfigTaskSearch(FoodSafty foodSafty){
			Long funId=foodSafty.getOrganization().getId();
			if(foodSafty.getMode()==null){
				return false;
			}
			if(foodSafty.getMode().equals("gridConfigService")&&
					funId.equals(ThreadVariable.getOrganization().getId())){
				return true;
			}
			if(foodSafty.getMode().equals("true")&&configTaskService.isHasGridTaskList(funId,Constants.TASKLIST_KEY)){
				return true;
			}
			return false;
		}
}
