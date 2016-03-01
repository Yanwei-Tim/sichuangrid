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
import com.tianque.plugin.taskList.service.GridConfigTaskService;
import com.tianque.serviceList.dao.UnlicensedManageDao;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.UnlicensedManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.UnlicensedManageService;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ReplyService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.serviceList.validater.UnlicensedManageValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;

@Service("unlicensedManageServiceImpl")
@Transactional
public class UnlicensedManageServiceImpl implements UnlicensedManageService{
	@Autowired 
	private UnlicensedManageDao unlicensedManageDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceListAttachService attachService;
	@Autowired
	private ReplyAttachService replyAttachService;
	@Autowired
	private ReplyService replyService;
	@Autowired 
	private UnlicensedManageValidatorImpl validatorImpl;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired 
	private GridConfigTaskService configTaskService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	
	private static final Integer TYPE=ServiceListEnum.getValue("unlicensed");
	@Override
	public UnlicensedManage addUnlicensedManage(UnlicensedManage info) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillUnlicensedManageOrg(info);
			info.setTelephone(ThreadVariable.getUser().getMobile());
			String[] attachFileNames = info.getAttachFileNames();
			if(info.getIsSign()==null){
				info.setIsSign(0);
			}
			if(info.getIsSign()==null){
				info.setIsReply(0);
			}
			validatorImpl.validatorUnlicensedManage(info);
			info= unlicensedManageDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("UnlicensedManageServiceImpl的addUnlicensedManage方法出错，原因：",
					"新增信息不全", e);
		}
	}
	@Override
	public UnlicensedManage addUnlicensedManage(UnlicensedManage info,String[] attachFileNames) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillUnlicensedManageOrg(info);
			info.setIsSign(0);
			info.setIsReply(0);
			validatorImpl.validatorUnlicensedManage(info);
			info= unlicensedManageDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			throw new ServiceValidationException("UnlicensedManageServiceImpl的addUnlicensedManage方法出错，原因：",
					"新增信息不全", e);
		}
	}
	@Override
	public PageInfo<UnlicensedManage> getUnlicensedManageListByQuery(
			UnlicensedManage unlicensedManage, Integer page, Integer rows,
			String sidx, String sord) {
		if(unlicensedManage==null){
			unlicensedManage=new UnlicensedManage();
		}
		fillUnlicensedManageOrg(unlicensedManage);
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		if(isGridConfigTaskSearch(unlicensedManage)){
			if(!StringUtil.isEmpty(unlicensedManage.getMode())&&
					"true".equals(unlicensedManage.getMode())){
				unlicensedManage.setFunOrgId(unlicensedManage.getOrganization().getId());
				unlicensedManage.setMode("gridConfigService");
			}
			unlicensedManage.getOrganization().setOrgInternalCode(null);
		}else if(orgTypeDict.getId().equals(unlicensedManage.getOrganization().getOrgType().getId())){
			unlicensedManage.getOrganization().setOrgInternalCode(organizationDubboService
						.getOrgInternalCodeById(unlicensedManage.getOrganization().getParentOrg().getId()));
		}else{
			unlicensedManage.getOrganization().setOrgInternalCode(unlicensedManage.getOrganization().getOrgInternalCode());
		}
		PageInfo<UnlicensedManage> infos=unlicensedManageDao.findPagerBySearchVo(unlicensedManage, page, rows, sidx, sord);
		//设置督办时间
//		infos=supervise(infos);
		return infos;
	}
	//设置督办时间
	private PageInfo<UnlicensedManage> supervise(PageInfo<UnlicensedManage> infos ){
		List<UnlicensedManage> propagandas= infos.getResult();
		for (UnlicensedManage propaganda:propagandas) {
//			propaganda.setSupervise(5);
		}
		return infos;
	}
	public void fillUnlicensedManageOrg(UnlicensedManage unlicensedManage) {
		if (unlicensedManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(unlicensedManage.getOrganization()
				.getId());
		String[] orgs = organization.getFullOrgName().split(ModuleConstants.SEPARATOR);
		if (orgs.length > 2) {
			String org = orgs[orgs.length - 2] + ModuleConstants.SEPARATOR + orgs[orgs.length - 1];
			organization.setFullOrgName(org);
		}
		unlicensedManage.setOrganization(organization);
	}
	
	@Override
	public UnlicensedManage updateUnlicensedManage(
			UnlicensedManage unlicensedManage) {
		fillUnlicensedManageOrg(unlicensedManage);
		validatorImpl.validatorUnlicensedManage(unlicensedManage);
		String[] attachIds=unlicensedManage.getDeleteAttachIds().split(",");
		for (int i = 0; i < attachIds.length; i++) {
			if(!attachIds[i].equals("")){
				attachService.deleteServiceListAttch(Long.parseLong(attachIds[i]));
			}
		}
		attachService.addServiceListAttch(unlicensedManage.getAttachFileNames(), unlicensedManage.getId(),TYPE);
		return unlicensedManageDao.update(unlicensedManage);
	}

	@Override
	public void deleteUnlicensedManageByIds(String ids) {
		String[]id= ids.split(",");
		for (String objId:id){
			List<Reply>replies=replyService.getReplyByIdAndType(Long.parseLong(objId), TYPE);
			for (Reply reply:replies) {
				replyAttachService.deleteReplyAttchByIds(reply.getId(), TYPE);
			}
			replyService.deleteReplyByIds(Long.parseLong(objId), TYPE);
			unlicensedManageDao.delete(Long.parseLong(objId));
			attachService.deleteServiceListAttchByIds(Long.parseLong(objId),TYPE);
		}
	}

	@Override
	public UnlicensedManage getUnlicensedManageById(Long id) {
		UnlicensedManage unlicensedManage=unlicensedManageDao.get(id);
		if(unlicensedManage!=null){
			unlicensedManage.setPhotos(attachService.getServiceListAttchByIdAndType(id, TYPE));
			User user = permissionDubboService.getFullUserByUerName(unlicensedManage.getCreateUser());
			if(user!=null){
				unlicensedManage.setCreateUser(user.getName());
			}
		}
		fillOrganization(unlicensedManage);
		return unlicensedManage;
	}
	
	public void fillOrganization(UnlicensedManage unlicensedManage) {
		if (unlicensedManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(unlicensedManage
				.getOrganization().getId());
		organization = ControllerHelper.proccessRelativeOrgNameByOrg(organization, organizationDubboService);
		unlicensedManage.setOrganization(organization);
	}

	@Override
	public UnlicensedManage signUnlicensedManage(
			UnlicensedManage unlicensedManage) {
		UnlicensedManage propaganda=unlicensedManageDao.get(unlicensedManage.getId());
		propaganda.setUpdateDate(new Date());
		propaganda.setUpdateUser(unlicensedManage.getSignPeople());
		propaganda.setSignContent(unlicensedManage.getSignContent());
		propaganda.setSignDate(unlicensedManage.getSignDate());
		propaganda.setSignPeople(unlicensedManage.getSignPeople());
		propaganda.setIsSign(1);
		return unlicensedManageDao.update(propaganda);
	}

	@Override
	public UnlicensedManage replyUnlicensedManage(
			UnlicensedManage unlicensedManage) {
		UnlicensedManage propaganda=unlicensedManageDao.get(unlicensedManage.getId());
		propaganda.setIsReply(1);
		unlicensedManageDao.update(propaganda);
		Reply reply=unlicensedManage.getReply();
		reply.setServiceId(unlicensedManage.getId());
		reply.setServiceType(TYPE);
		reply=replyService.addReply(reply);
		replyAttachService.addReplyAttch(unlicensedManage.getAttachFileNames(), reply.getId(),TYPE);
		return unlicensedManage;
	}
	
	//判断是否为网格配置后的查询
	private boolean isGridConfigTaskSearch(UnlicensedManage unlicensedManage){
		Long funId=unlicensedManage.getOrganization().getId();
		if(unlicensedManage.getMode()==null){
			return false;
		}
		if(unlicensedManage.getMode().equals("gridConfigService")&&
				funId.equals(ThreadVariable.getOrganization().getId())){
			return true;
		}
		if(unlicensedManage.getMode().equals("true")&&configTaskService.isHasGridTaskList(funId,Constants.TASKLIST_KEY)){
			return true;
		}
		return false;
	}
}
