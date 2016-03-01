package com.tianque.plugin.taskList.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.taskList.dao.GridConfigTaskDao;
import com.tianque.plugin.taskList.domain.OrgTreeNode;
/**
 * 网格员配置清单dao实现
 * 
 * @author weiminglong
 *
 */
@Repository("gridConfigTaskDao")
public class GridConfigTaskDaoImpl extends AbstractBaseDao implements GridConfigTaskDao {

	@Override
	public PageInfo<Organization> findGridConfigTasks(
			Long parentId, Long orgType,Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtil.isStringAvaliable(sortField)){
			map.put("sortField", sortField);
		}
		map.put("order", order);
		map.put("parentId", parentId);
		map.put("orgType", orgType);

		PageInfo<Organization> pageInfo = new PageInfo<Organization>();
        String countSum = "gridConfigTask.countGridConfigTask";
        String searchGridConfigTask = "gridConfigTask.searchGridConfigTask";
        pageInfo = getPageInfoByParamMap(pageInfo, countSum, searchGridConfigTask, pageNum, pageSize, map);
		
		return pageInfo;
	}
	
	/**
	 * 查询登录账号是否有配置职能部门配置清单
	 */
	@Override
	public Long countHasGridTaskList(Long orgId,String tableName) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("tableName", tableName);
		return (Long)getSqlMapClientTemplate().queryForObject("gridConfigTask.countHasGridTaskList",map);
	}

	/** 
	 * 加载职能部门配置清单树
	 */
	@Override
	public List<Organization> getOrgSelectComponent(Long funOrgId,Long parentId,String tableName) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("funOrgId", funOrgId);
		map.put("parentId", parentId);
		map.put("tableName", tableName);
		List<Organization> organizations=getSqlMapClientTemplate().queryForList("gridConfigTask.getOrgSelectComponent",map);
		return organizations;
	}

	/**
	 * 更据父id查询直属子级org
	 */
	@Override
	public List<OrgTreeNode> getOrganizationTreeByParentId(Long pId) {
		return getSqlMapClientTemplate().queryForList(
				"gridConfigTask.getAllOrganizationsTreeByParentId", pId);
	}

	/* 
	 * 更据orgids用sql批量插入选择的org数据
	 */
	@Override
	public void addGridConfigTaskByOrgids(Map<String, Object> map) {
		getSqlMapClientTemplate().insert(
				"gridConfigTask.insertGridConfigTaskByOrgids", map);
	}

	/* 
	 * 更据职能部门id查询选中的行政部门的id
	 */
	@Override
	public List<Long> getCheckedIds(Long funOrgId,String tableName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("funOrgId", funOrgId);
		map.put("tableName", tableName);
		return getSqlMapClientTemplate().queryForList(
				"gridConfigTask.getCheckedIds", map);
	}

	/* 
	 * 更据职能部门id删除选中的行政部门的id
	 */
	@Override
	public void deleteCheckedOrg(Long funOrgId,String tableName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("funOrgId", funOrgId);
		map.put("tableName", tableName);
		getSqlMapClientTemplate().delete(
			"gridConfigTask.deleteCheckedIds", map);
	}

	@Override
	public List<Long> getTaskListFunOrgIdByAreaOrgId(Long areaOrgId) {
		return getSqlMapClientTemplate().queryForList(
				"gridConfigTask.getTaskListFunOrgIdByAreaOrgId", areaOrgId);
	}
}
