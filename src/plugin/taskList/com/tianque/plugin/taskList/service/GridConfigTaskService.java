package com.tianque.plugin.taskList.service;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.GridConfigTaskVo;
import com.tianque.plugin.taskList.domain.OrgTreeNode;

import java.util.List;

import org.exolab.core.service.ServiceException;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;


/**
 * 任务清单街道绑定网格员service层接口
 * 
 * @author weiminglong
 *
 */

public interface GridConfigTaskService {

	/**
	 * 查询登录账号是否有配置职能部门配置清单
	 * @return
	 */
	public Boolean isHasGridTaskList(String type);
	/**
	 * 根据职能部门id查询是否有配置职能部门配置清单
	 * @return
	 */
	public Boolean isHasGridTaskList(Long funId,String type);
	/**
	 * 加载职能部门配置清单树
	 */
	public List<Organization> getOrgSelectComponent(Long parentId,String type);

//	/**
//	 * 加载职能部门配置清单树(异步)
//	 */
//	public List<ExtTreeData> ajaxOrgConfigTaskTree(Long parentId,PropertyDict orgType);
	
	/**
	 * 查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<GridConfigTaskVo> findGridConfigTasks(
			Long parentId,String type,Integer pageNum,
			Integer pageSize, String sortField, String order);
	/**
	 * 配置清单查询子级org
	 * @param pId
	 * @return
	 */
	public List<OrgTreeNode> getOrganizationTreeByParentId(Long orgId, String pId,String mode,String type);
	
	/**
	 * 新增选择的org
	 * @param map
	 */
	public void addGridConfigTaskByOrgids(String ids,Organization funorg,String mode,String type)throws ServiceException;
	
	/**
	 * 更据职能部门ids删除选中的行政部门的id
	 */
	public void deleteCheckedOrg(String ids,String type);
}
