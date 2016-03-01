package com.tianque.plugin.taskList.serviceImpl;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.taskList.dao.GridConfigTaskDao;
import com.tianque.plugin.taskList.dao.TaskListTimeStandardDao;
import com.tianque.plugin.taskList.domain.TaskListTimeStandard;
import com.tianque.plugin.taskList.service.TaskListTimeStandardService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by hesimin on 2015/12/17.
 */
@Service("taskListTimeStandardService")
public class TaskListTimeStandardServiceImpl implements TaskListTimeStandardService {
	@Autowired
	private TaskListTimeStandardDao taskListTimeStandardDao;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GridConfigTaskDao gridConfigTaskDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public TaskListTimeStandard addTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard) {
		this.validate(taskListTimeStandard);
		if (StringUtils.isNotBlank(taskListTimeStandard.getItemNameIds())) {
			String[] itemNameIds = taskListTimeStandard.getItemNameIds().split(",");
			if (taskListTimeStandard.getItemNameDict() == null) {
				taskListTimeStandard.setItemNameDict(new PropertyDict());
			}
			List<PropertyDict> itemNameDictList = new ArrayList<PropertyDict>();
			for (String s : itemNameIds) {
				if (StringUtils.isBlank(s)) continue;
				PropertyDict itemNameDict = propertyDictDubboService.getPropertyDictById(Long.parseLong(s));
				taskListTimeStandard.setItemNameDict(itemNameDict);
				if (itemNameDict == null) {
					throw new BusinessValidationException("未找到项目名");
				}
				int count = taskListTimeStandardDao.countByOrgIdAndItemName(taskListTimeStandard.getOrg().getId(), taskListTimeStandard.getItemNameDict().getId());
				if (count > 0) {
					throw new BusinessValidationException("不能重复添加：" + taskListTimeStandard.getOrg().getOrgName() + " - " + taskListTimeStandard.getItemNameDict().getDisplayName());
				}
				itemNameDictList.add(itemNameDict);
			}
			for (PropertyDict dict : itemNameDictList) {
				taskListTimeStandard.setItemNameDict(dict);
				taskListTimeStandardDao.addTaskListTimeStandard(taskListTimeStandard);
			}
		}
		return taskListTimeStandard;
	}

	private void validate(TaskListTimeStandard taskListTimeStandard) {
		if (taskListTimeStandard == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (taskListTimeStandard.getOrg() == null || taskListTimeStandard.getOrg().getId() == null) {
			throw new BusinessValidationException("未找到职能部门组织");
		}
		taskListTimeStandard.setOrg(organizationDubboService.getSimpleOrgById(taskListTimeStandard.getOrg().getId()));
		if (taskListTimeStandard.getOrg() == null) {
			throw new BusinessValidationException("未找到职能部门组织");
		}
		if (taskListTimeStandard.getItemNameDict() == null || taskListTimeStandard.getItemNameDict().getId() == null) {
			throw new BusinessValidationException("项目名称未选择");
		}
	}

	@Override
	public TaskListTimeStandard getTaskListTimeStandardById(Long id) {
		return taskListTimeStandardDao.getTaskListTimeStandardById(id);
	}

	@Override
	public List<TaskListTimeStandard> getTaskListTimeStandardByFunOrgIdAndItemName(Long orgId, Integer itemNameDictInternalId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构参数不能为空");
		}
		if (itemNameDictInternalId == null) {
			throw new BusinessValidationException("项目名内部id不能为空");
		}
		String cacheKey = "TaskListTimeStandard_" + orgId.longValue() + "_" + itemNameDictInternalId.intValue();
		Object c = cacheService.get(cacheKey);
		if (c != null) {
			return (List<TaskListTimeStandard>) c;
		}
		List<PropertyDict> itemNameDictList = propertyDictDubboService.findPropertyDictByDomainNameAndInternalId(PropertyTypes.TASKLIST_ITEM_NAME, itemNameDictInternalId);
		if (itemNameDictList == null || itemNameDictList.size() == 0) {
			throw new BusinessValidationException("未找到项目名");
		}
		// 现在任务清单数据只有社区、网格层级新增，社区、网格以上层级不能获取到考核配置，因为此层级以上页面上展示的是混合数据（不同数据对应的考核配置不一样），不能确定是哪个考核配置
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PropertyDict orgLevelDict = propertyDictDubboService.getPropertyDictById(org.getOrgLevel().getId());
		PropertyDict orgTepyDict = propertyDictDubboService.getPropertyDictById(org.getOrgType().getId());
		List<Long> funOrgIds = null;
		if (orgTepyDict.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			if (!(orgLevelDict.getInternalId() == OrganizationLevel.TOWN || orgLevelDict.getInternalId() == OrganizationLevel.VILLAGE || orgLevelDict.getInternalId() == OrganizationLevel.GRID)) {
				return new ArrayList<TaskListTimeStandard>();
			}
			// 根据组织id查找任务清单配置，找到该组织属于哪个职能部门管辖
			funOrgIds = gridConfigTaskDao.getTaskListFunOrgIdByAreaOrgId(orgId);
		} else if (orgTepyDict.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			// 参数组织id，本身就职能部门，直接查该职能部门的时限配置
			funOrgIds = new ArrayList<Long>();
			funOrgIds.add(orgId);
		} else {
			throw new ServiceException("组织类别不是行政区域或职能部门");
		}

		// 可能会有重复的，去掉
		List<Long> notDuplicationFunOrgIds = new ArrayList<Long>();
		for (Long funOrgId : funOrgIds) {
			boolean has = false;
			for (Long notDuplicationFunOrgId : notDuplicationFunOrgIds) {
				if (funOrgId.equals(notDuplicationFunOrgId)) {
					has = true;
					break;
				}
			}
			if (!has) {
				notDuplicationFunOrgIds.add(funOrgId);
			}
		}

		// 可能会有多个，比如精神障碍患者有卫生所和派出所两个
		List<TaskListTimeStandard> taskListTimeStandardList = new ArrayList<TaskListTimeStandard>();
		for (Long functionOrgId : notDuplicationFunOrgIds) {
			Organization functionOrg = organizationDubboService.getSimpleOrgById(functionOrgId);
			while (taskListTimeStandardList.size() == 0 && functionOrg != null) {
				TaskListTimeStandard findTaskListTimeStandard = taskListTimeStandardDao.getTaskListTimeStandardByOrgIdAndItemNameDictId(functionOrg.getId(), itemNameDictList.get(0).getId());
				if (findTaskListTimeStandard != null) {
					// 找到当前组织考核时限配置
					findTaskListTimeStandard.setSysCurrTime(new Date().getTime());
					Organization organization = organizationDubboService.getSimpleOrgById(findTaskListTimeStandard.getOrg().getId());
					PropertyDict funOrgTypeDict = propertyDictDubboService.getPropertyDictById(organization.getFunctionalOrgType().getId());
					organization.setFunctionalOrgType(funOrgTypeDict);
					findTaskListTimeStandard.setOrg(organization);
					taskListTimeStandardList.add(findTaskListTimeStandard);
					break;
				} else {
					// 当前组织未找到考核时限配置，继续查找上一级的配置
					if (functionOrg.getParentFunOrg() != null) {
						functionOrg = organizationDubboService.getSimpleOrgById(functionOrg.getParentFunOrg().getId());
					} else {
						// 不存在上一级，结束查找
						break;
					}
				}
			}
		}
//		String orgCode = organizationDubboService.getSimpleOrgById(functionOrgId).getOrgInternalCode();
//		if (orgCode.endsWith(".")) {
//			orgCode = orgCode.substring(0, orgCode.length() - 1);
//		}
//		//截取出县级的orgCode
//		String[] orgCodeSplit = orgCode.split("[.]");
//		if (orgCodeSplit.length < 4) {
//			// 县级以上层级
//			return null;
//		}
//		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i < 4; i++) {
//			sb.append(".").append(orgCodeSplit[i]);
//		}
//		List<TaskListTimeStandard> taskListTimeStandardList = taskListTimeStandardDao.getTaskListTimeStandardByOrgCodeAndItemNameDictId(sb.toString(), itemNameDictList.get(0).getId());
//		if (taskListTimeStandardList != null) {
//			for (TaskListTimeStandard ts : taskListTimeStandardList) {
//				ts.setOrg(organizationDubboService.getSimpleOrgById(ts.getOrg().getId()));
//			}
//		}
		cacheService.add(cacheKey,60,taskListTimeStandardList);
		return taskListTimeStandardList;
	}

	@Override
	public void deleteTaskListTimeStandard(Long id) {
		taskListTimeStandardDao.deleteTaskListTimeStandard(id);
	}

	@Override
	public void deleteTaskListTimeStandardByIds(List<Long> ids) {
		taskListTimeStandardDao.deleteTaskListTimeStandardByIds(ids);
	}

	@Override
	public void updateTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard) {
		TaskListTimeStandard query = null;
		if (taskListTimeStandard.getId() != null) {
			query = taskListTimeStandardDao.getTaskListTimeStandardById(taskListTimeStandard.getId());
		}
		if (query == null) {
			throw new BusinessValidationException("未找到修改数据");
		}
		// 部门和项目名不能修改，使用原来的
		taskListTimeStandard.setOrg(query.getOrg());
		taskListTimeStandard.setItemNameDict(query.getItemNameDict());
		this.validate(taskListTimeStandard);
		if (!query.getOrg().getOrgInternalCode().startsWith(ThreadVariable.getOrganization().getOrgInternalCode())) {
			throw new BusinessValidationException("当前账号层级无权修该数据");
		}
		taskListTimeStandardDao.updateTaskListTimeStandard(taskListTimeStandard);
	}

	@Override
	public PageInfo<TaskListTimeStandard> findTaskListTimeStandard(String orgCode, Integer pageNum, Integer pageSize, String sortField, String order) {
		return taskListTimeStandardDao.findTaskListTimeStandard(orgCode, pageNum, pageSize, sortField, order);
	}
}
