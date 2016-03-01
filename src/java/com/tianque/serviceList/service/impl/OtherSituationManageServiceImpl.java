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
import com.tianque.serviceList.dao.OtherSituationManageDao;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.FoodSafty;
import com.tianque.serviceList.domain.OtherSituationManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.OtherSituationManageService;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ReplyService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.serviceList.validater.OtherSituationManageValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;

@Service("otherSituationManageServiceImpl")
@Transactional
public class OtherSituationManageServiceImpl implements OtherSituationManageService{
	@Autowired 
	private OtherSituationManageDao otherSituationManageDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceListAttachService attachService;
	@Autowired
	private ReplyAttachService replyAttachService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private OtherSituationManageValidatorImpl validatorImpl;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired 
	private GridConfigTaskService configTaskService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	private static final Integer TYPE=ServiceListEnum.getValue("otherSituation");
	@Override
	public OtherSituationManage addOtherSituationManage(OtherSituationManage info) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillOtherSituationManageOrg(info);
			info.setTelephone(ThreadVariable.getUser().getMobile());
			String[] attachFileNames = info.getAttachFileNames();
			if(info.getIsSign()==null){
				info.setIsSign(0);
			}
			if(info.getIsSign()==null){
				info.setIsReply(0);
			}
			validatorImpl.validatorOtherSituationManage(info);
			info= otherSituationManageDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("OtherSituationManageServiceImpl的addOtherSituationManage方法出错，原因：",
					"新增信息不全", e);
		}
	}
	@Override
	public OtherSituationManage addOtherSituationManage(OtherSituationManage info,String[] attachFileNames) {
		if (info == null) {
			throw new BusinessValidationException("新增失败！");
		}
		try {
			fillOtherSituationManageOrg(info);
			info.setIsSign(0);
			info.setIsReply(0);
			validatorImpl.validatorOtherSituationManage(info);
			info= otherSituationManageDao.add(info);
			attachService.addServiceListAttch(attachFileNames, info.getId(),TYPE);
			return info;
		} catch (Exception e) {
			throw new ServiceValidationException("OtherSituationManageServiceImpl的addOtherSituationManage方法出错，原因：",
					"新增信息不全", e);
		}
	}
	@Override
	public PageInfo<OtherSituationManage> getOtherSituationManageListByQuery(
			OtherSituationManage otherSituationManage, Integer page, Integer rows,
			String sidx, String sord) {
		if(otherSituationManage==null){
			otherSituationManage=new OtherSituationManage();
		}
		fillOtherSituationManageOrg(otherSituationManage);
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		if(isGridConfigTaskSearch(otherSituationManage)){
			if(!StringUtil.isEmpty(otherSituationManage.getMode())&&
					"true".equals(otherSituationManage.getMode())){
				otherSituationManage.setFunOrgId(otherSituationManage.getOrganization().getId());
				otherSituationManage.setMode("gridConfigService");
			}
			otherSituationManage.getOrganization().setOrgInternalCode(null);
		}else if(orgTypeDict.getId().equals(otherSituationManage.getOrganization().getOrgType().getId())){
			otherSituationManage.getOrganization().setOrgInternalCode(organizationDubboService
						.getOrgInternalCodeById(otherSituationManage.getOrganization().getParentOrg().getId()));
		}else{
			otherSituationManage.getOrganization().setOrgInternalCode(otherSituationManage.getOrganization().getOrgInternalCode());
		}
		PageInfo<OtherSituationManage> infos=otherSituationManageDao.findPagerBySearchVo(otherSituationManage, page, rows, sidx, sord);
		//设置督办时间
//		infos=supervise(infos);
		return infos;
	}
	//设置督办时间
	private PageInfo<OtherSituationManage> supervise(PageInfo<OtherSituationManage> infos ){
		List<OtherSituationManage> propagandas= infos.getResult();
		for (OtherSituationManage propaganda:propagandas) {
//			propaganda.setSupervise(5);
		}
		return infos;
	}
	public void fillOtherSituationManageOrg(OtherSituationManage otherSituationManage) {
		if (otherSituationManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(otherSituationManage.getOrganization()
				.getId());
		String[] orgs = organization.getFullOrgName().split(ModuleConstants.SEPARATOR);
		if (orgs.length > 2) {
			String org = orgs[orgs.length - 2] + ModuleConstants.SEPARATOR + orgs[orgs.length - 1];
			organization.setFullOrgName(org);
		}
		otherSituationManage.setOrganization(organization);
	}
	
	@Override
	public OtherSituationManage updateOtherSituationManage(
			OtherSituationManage otherSituationManage) {
		fillOtherSituationManageOrg(otherSituationManage);
		validatorImpl.validatorOtherSituationManage(otherSituationManage);
		String[] attachIds=otherSituationManage.getDeleteAttachIds().split(",");
		for (int i = 0; i < attachIds.length; i++) {
			if(!attachIds[i].equals("")){
				attachService.deleteServiceListAttch(Long.parseLong(attachIds[i]));
			}
		}
		attachService.addServiceListAttch(otherSituationManage.getAttachFileNames(), otherSituationManage.getId(),TYPE);
		return otherSituationManageDao.update(otherSituationManage);
	}

	@Override
	public void deleteOtherSituationManageByIds(String ids) {
		String[]id= ids.split(",");
		for (String objId:id){
			List<Reply>replies=replyService.getReplyByIdAndType(Long.parseLong(objId), TYPE);
			for (Reply reply:replies) {
				replyAttachService.deleteReplyAttchByIds(reply.getId(), TYPE);
			}
			replyService.deleteReplyByIds(Long.parseLong(objId), TYPE);
			otherSituationManageDao.delete(Long.parseLong(objId));
			attachService.deleteServiceListAttchByIds(Long.parseLong(objId),TYPE);
		}
	}

	@Override
	public OtherSituationManage getOtherSituationManageById(Long id) {
		OtherSituationManage otherSituationManage=otherSituationManageDao.get(id);
		if(otherSituationManage!=null){
			otherSituationManage.setPhotos(attachService.getServiceListAttchByIdAndType(id, TYPE));
			User user = permissionDubboService.getFullUserByUerName(otherSituationManage.getCreateUser());
			if(user!=null){
				otherSituationManage.setCreateUser(user.getName());
			}
		}
		fillOrganization(otherSituationManage);
		return otherSituationManage;
	}
	
	public void fillOrganization(OtherSituationManage otherSituationManage) {
		if (otherSituationManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织结构获取异常！");
		}
		Organization organization = organizationDubboService.getFullOrgById(otherSituationManage
				.getOrganization().getId());
		organization = ControllerHelper.proccessRelativeOrgNameByOrg(organization, organizationDubboService);
		otherSituationManage.setOrganization(organization);
	}

	@Override
	public OtherSituationManage signOtherSituationManage(
			OtherSituationManage otherSituationManage) {
		OtherSituationManage propaganda=otherSituationManageDao.get(otherSituationManage.getId());
		propaganda.setUpdateDate(new Date());
		propaganda.setUpdateUser(otherSituationManage.getSignPeople());
		propaganda.setSignContent(otherSituationManage.getSignContent());
		propaganda.setSignDate(otherSituationManage.getSignDate());
		propaganda.setSignPeople(otherSituationManage.getSignPeople());
		propaganda.setIsSign(1);
		return otherSituationManageDao.update(propaganda);
	}

	@Override
	public OtherSituationManage replyOtherSituationManage(
			OtherSituationManage otherSituationManage) {
		OtherSituationManage propaganda=otherSituationManageDao.get(otherSituationManage.getId());
		propaganda.setIsReply(1);
		otherSituationManageDao.update(propaganda);
		Reply reply=otherSituationManage.getReply();
		reply.setServiceId(otherSituationManage.getId());
		reply.setServiceType(TYPE);
		reply=replyService.addReply(reply);
		replyAttachService.addReplyAttch(otherSituationManage.getAttachFileNames(), reply.getId(),TYPE);
		return otherSituationManage;
	}
	
	//判断是否为网格配置后的查询
			private boolean isGridConfigTaskSearch(OtherSituationManage otherSituationManage){
				Long funId=otherSituationManage.getOrganization().getId();
				if(otherSituationManage.getMode()==null){
					return false;
				}
				if(otherSituationManage.getMode().equals("gridConfigService")&&
						funId.equals(ThreadVariable.getOrganization().getId())){
					return true;
				}
				if(otherSituationManage.getMode().equals("true")&&configTaskService.isHasGridTaskList(funId,Constants.TASKLIST_KEY)){
					return true;
				}
				return false;
			}
				
}
