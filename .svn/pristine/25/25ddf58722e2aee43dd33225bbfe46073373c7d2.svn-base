package com.tianque.plugin.serviceTeam.serviceTeamHasObjects.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务团队与对象DAO层
 * 
 * @author yangpengdian
 */
@Repository("serviceTeamHasObjectsDao")
public class ServiceTeamHasObjectsDaoImpl extends AbstractBaseDao implements
		ServiceTeamHasObjectsDao {

	@Override
	public int findServiceObjectInServiceTeam(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamHasObjects.findServiceObjectInServiceTeam",
				serviceTeamMemberVo);
	}

	@Override
	public void addObjectAndTeamRelation(
			ServiceTeamHasObjects serviceTeamHasObjects) {
		getSqlMapClientTemplate().insert(
				"serviceTeamHasObjects.addObjectAndTeamRelation",
				serviceTeamHasObjects);
	}

	@Override
	public void deleteServiceTeamHasObjects(
			ServiceTeamHasObjects serviceTeamHasObjects) {
		getSqlMapClientTemplate().delete(
				"serviceTeamHasObjects.deleteServiceTeamHasObjects",
				serviceTeamHasObjects);
	}

	@Override
	public void deleteServiceTeamWithObjects(
			ServiceTeamHasObjects serviceTeamHasObjects) {

		getSqlMapClientTemplate().delete(
				"serviceTeamHasObjects.deleteServiceTeamWithObjects",
				serviceTeamHasObjects);
	}

	@Override
	public void updateServiceTeamHasObjects(
			ServiceTeamHasObjects serviceTeamHasObjects) {

		getSqlMapClientTemplate().update(
				"serviceTeamHasObjects.updateServiceTeamHasObjects",
				serviceTeamHasObjects);
	}

	@Override
	public void updateServiceTeamWithObjects(
			ServiceTeamHasObjects serviceTeamHasObjects) {

		getSqlMapClientTemplate().update(
				"serviceTeamHasObjects.updateServiceTeamWithObjects",
				serviceTeamHasObjects);

	}

	@Override
	public void updateServiceMemberHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		getSqlMapClientTemplate().update(
				"serviceTeamHasObjects.updateServiceMemberHasObjectForMove",
				map);

	}

	@Override
	public void updateServiceTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		getSqlMapClientTemplate().update(
				"serviceTeamHasObjects.updateServiceTeamHasObjectForMove", map);

	}

	@Override
	public void updateServiceMemberHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		map.put("newObjectType", newObjectType);
		getSqlMapClientTemplate()
				.update(
						"serviceTeamHasObjects.updateServiceMemberHasObjectForMovePosAndRec",
						map);

	}

	@Override
	public void updateServiceTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		map.put("newObjectType", newObjectType);
		getSqlMapClientTemplate()
				.update(
						"serviceTeamHasObjects.updateServiceTeamHasObjectForMovePosAndRec",
						map);

	}

	/******************** 迁移合并方法 **************************/
	/**
	 * 
	 * @Title: queryServiceTeamHasObjectsForList
	 * @Description: TODO查询重复服务团队关系
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<ServiceTeamHasObjects> 返回类型
	 * @author wanggz
	 * @date 2014-10-28 上午10:31:28
	 */
	public List<ServiceTeamHasObjects> queryServiceTeamHasObjectsForList(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"serviceTeamHasObjects.queryServiceTeamHasObjectsForList", map);
	}

}
