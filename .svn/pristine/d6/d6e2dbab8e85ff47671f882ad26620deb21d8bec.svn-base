package com.tianque.plugin.taskList.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.taskList.constant.TaskConstant;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.dao.WorkingSituationDao;
import com.tianque.plugin.taskList.domain.WorkingSituation;
import com.tianque.plugin.taskList.domain.WorkingSituationVo;
import com.tianque.plugin.taskList.service.GridConfigTaskService;
import com.tianque.plugin.taskList.service.WorkingSituationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

/**
 * 民警带领下开展工作情况service层实现
 * 
 * @author GAOHU
 *
 */
@Transactional
@Service("workingSituationService")
public class WorkingSituationServiceImpl implements WorkingSituationService {

	@Autowired
	private WorkingSituationDao workingSituationDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	
	@Autowired
	private PropertyDictService propertyDictService;

	@Qualifier("workingSituationValidator")
	@Autowired
	private DomainValidator<WorkingSituation> domainValidator;

	@Autowired
	private GridConfigTaskService configTaskService;
	
	@Autowired
	private PermissionDubboService permissionDubboService;
	
	@Override
	public WorkingSituation getWorkingSituationById(Long id) {

		if (id == null) {
			throw new BusinessValidationException("参数异常，请稍后重试！");
		}
		try {
			WorkingSituation workingSituation=workingSituationDao.getWorkingSituationById(id);
			//判断权限，有权限不隐藏
			if(permissionDubboService.
					isUserHasPermission(ThreadVariable.getUser().getId(), "isPoliceNotHidCard")){
				return workingSituation;
			}
			workingSituation.setIdCard(IdCardUtil.hiddenIdCard(workingSituation.getIdCard()));
			return workingSituation;

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingSituationServiceImpl的getWorkingSituationById方法出现异常，原因：",
					"获取工作情况数据时出现错误", e);
		}

	}

	@Override
	public WorkingSituation addWorkingSituation(
			WorkingSituation workingSituation) {
		if (workingSituation == null) {
			throw new BusinessValidationException("参数异常，请稍后重试！");
		}
		/*
		 * ValidateResult dataValidator =
		 * validator.validateBaseInfo(emptyPopulation);
		 * 
		 * if (dataValidator.hasError()) {
		 * 
		 * throw new ServiceException(dataValidator.getErrorMessages()); }
		 */
		try {

			workingSituationValidate(workingSituation);
			autoFillOrganizationInternalCode(workingSituation);

			// workingSituation.setOccurrenceDate(CalendarUtil.now());

			workingSituation.setCellName(ThreadVariable.getUser().getName());
			workingSituation.setTelephone(ThreadVariable.getUser().getMobile());
			return workingSituationDao.addWorkingSituation(workingSituation);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingSituationServiceImpl的addWorkingSituation方法出现异常，原因：",
					"新增工作情况数据时出现错误", e);
		}
	}

	@Override
	public void deleteWorkingSituation(List<Long> ids) {
		if (ids == null) {
			throw new BusinessValidationException("参数异常，请稍后重试！");
		}
		try {

			workingSituationDao.deleteWorkingSituation(ids);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingSituationServiceImpl的deleteWorkingSituation方法出现异常，原因：",
					"删除工作情况数据时出现错误", e);
		}

	}

	@Override
	public WorkingSituation updateWorkingSituation(
			WorkingSituation workingSituation) {
		if (workingSituation == null) {
			throw new BusinessValidationException("参数异常，请稍后重试！");
		}
		try {

			return workingSituationDao.updateWorkingSituation(workingSituation);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingSituationServiceImpl的updateWorkingSituation方法出现异常，原因：",
					"更新工作情况数据时出现错误", e);
		}

	}

	@Override
	public PageInfo<WorkingSituation> searchWorkingSituation(
			WorkingSituationVo workingSituationVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		if (workingSituationVo == null) {
			throw new BusinessValidationException("查询条件为空，请稍后重试！");
		}
		try {
			PropertyDict orgTypeDict = propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.ORGANIZATION_TYPE,
							OrganizationType.FUNCTION_KEY);
			Organization org=organizationDubboService.getFullOrgById(workingSituationVo.getOrganization()
					.getId());
			
			if(isGridConfigTaskSearch(workingSituationVo)){
				if(!StringUtil.isEmpty(workingSituationVo.getMode())&&
						"true".equals(workingSituationVo.getMode())){
					workingSituationVo.setFunOrgId(org.getId());
					workingSituationVo.setMode("gridConfigTask");
				}
				workingSituationVo.setOrgInternalCode(null);
			}else if(orgTypeDict.getId().equals(org.getOrgType().getId())){
				workingSituationVo.setOrgInternalCode(organizationDubboService
							.getOrgInternalCodeById(org.getParentOrg().getId()));
			}else{
				workingSituationVo.setOrgInternalCode(org.getOrgInternalCode());
			}
			PageInfo<WorkingSituation>pageInfo=workingSituationDao
					.searchWorkingSituation(
							workingSituationVo, pageNum, pageSize,
							sortField, order);
			//隐藏身份证中间4位
			pageInfo=hiddenIdCard(pageInfo);
			return pageInfo;
			
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingSituationServiceImpl的searchWorkingSituation方法出现异常，原因：",
					"查询安全隐患时出现错误", e);
		}
	}
	//隐藏身份证中间4位
	private PageInfo<WorkingSituation> hiddenIdCard(PageInfo<WorkingSituation> pageInfo){
		//判断权限，有权限不隐藏
		if(permissionDubboService.
				isUserHasPermission(ThreadVariable.getUser().getId(), "isPoliceNotHidCard")){
			return pageInfo;
		}
		List<WorkingSituation>list=pageInfo.getResult();
		int index=0;
		for (WorkingSituation workingSituation:list) {
			workingSituation.setIdCard(IdCardUtil.hiddenIdCard(workingSituation.getIdCard()));
			list.set(index, workingSituation);
			index++;
		}
		pageInfo.setResult(list);
		return pageInfo;
	}
	//判断是否为网格配置后的查询
	private boolean isGridConfigTaskSearch(WorkingSituationVo workingSituationVo){
		Long funId=workingSituationVo.getOrganization().getId();
		if(workingSituationVo.getMode()==null){
			return false;
		}
		if(workingSituationVo.getMode().equals("gridConfigTask")&&
				funId.equals(ThreadVariable.getOrganization().getId())){
			return true;
		}
		if(workingSituationVo.getMode().equals("true")&&configTaskService.isHasGridTaskList(funId,Constants.TASKLIST_KEY)){
			return true;
		}
		return false;
	}
	
	private void autoFillOrganizationInternalCode(
			WorkingSituation workingSituation) {
		Organization org = organizationDubboService
				.getSimpleOrgById(workingSituation.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		workingSituation.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void workingSituationValidate(WorkingSituation workingSituation) {
		ValidateResult workingSituationValidator = domainValidator
				.validate(workingSituation);
		if (workingSituationValidator.hasError()) {
			throw new BusinessValidationException(
					workingSituationValidator.getErrorMessages());
		}
	}

	@Override
	public WorkingSituation updateWorkingSituationSignDetail(
			WorkingSituation workingSituation) {
		if (workingSituation == null) {
			throw new BusinessValidationException("参数异常，请稍后重试！");
		}
		try {
			workingSituation.setIshandle(TaskConstant.HANDLE_STATE);
			return workingSituationDao
					.updateWorkingSituationSignDetail(workingSituation);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HiddenDangerServiceImpl的updateHiddenDanger方法出现异常，原因：",
					"更新安全隐患时出现错误", e);
		}
	}

}
