package com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;

/**
 * 服务成员数据访问层实现类
 * 
 * @author tengjia
 */
@Repository("serviceMemberWithObjectDao")
public class ServiceMemberWithObjectDaoImpl extends AbstractBaseDao implements
		ServiceMemberWithObjectDao {

	@Override
	public ServiceMemberWithObjectVo addObjectAndMemberRelation(
			ServiceMemberWithObject serviceObjectMember) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceMemberWithObject.addObjectAndMemberRelation",
				serviceObjectMember);
		return getServiceMemberWithObjectById(id);
	}

	@Override
	public ServiceMemberWithObjectVo getServiceMemberWithObjectById(Long id) {
		return (ServiceMemberWithObjectVo) getSqlMapClientTemplate()
				.queryForObject(
						"serviceMemberWithObject.getServiceMemberWithObjectById",
						id);
	}

	@Override
	public int deleteServiceMemberWithObject(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceMemberWithObject.deleteServiceMemberWithObject", id);
	}

	@Override
	public void deleteServiceMemberHasObject(
			ServiceMemberWithObject serviceMemberWithObject) {

		getSqlMapClientTemplate().delete(
				"serviceMemberWithObject.deleteServiceMemberHasObject",
				serviceMemberWithObject);

	}

	@Override
	public int updateServiceMemberWithObject(
			ServiceMemberWithObject serviceObjectMember) {

		return getSqlMapClientTemplate().update(
				"serviceMemberWithObject.updateServiceMemberWithObject",
				serviceObjectMember);
	}

	@Override
	public void updateServiceMemberHasObject(
			ServiceMemberWithObject serviceMemberWithObject) {
		getSqlMapClientTemplate().update(
				"serviceMemberWithObject.updateServiceTeamHasObjects",
				serviceMemberWithObject);

	}

	@Override
	public ServiceMemberWithObjectVo getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		return (ServiceMemberWithObjectVo) getSqlMapClientTemplate()
				.queryForObject(
						"serviceMemberWithObject.getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId",
						serviceMemberWithObjectVo);
	}

	@Override
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByMemberId(
			Long memberId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"serviceMemberWithObject.findServiceMemberWithObjectsByMemberId",
						memberId);
	}

	@Override
	public void updateRepeatData(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		getSqlMapClientTemplate().update(
				"serviceMemberWithObject.updateRepeatData",
				serviceMemberWithObjectVo);

	}

	@Override
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByTypeAndId(
			String objectType, Long objectId) {
		ServiceMemberWithObjectVo serviceMemberWithObjectVo = new ServiceMemberWithObjectVo();
		serviceMemberWithObjectVo.setObjectId(objectId);
		serviceMemberWithObjectVo.setObjectType(objectType);
		return getSqlMapClientTemplate()
				.queryForList(
						"serviceMemberWithObject.findServiceMemberWithObjectsByTypeAndId",
						serviceMemberWithObjectVo);
	}

	@Override
	public void updateOnDuty(ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		getSqlMapClientTemplate().update(
				"serviceMemberWithObject.updateOnDuty",
				serviceMemberWithObjectVo);

	}

	@Override
	public void updateServiceMemberWithObject(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		getSqlMapClientTemplate().update(
				"serviceMemberWithObject.updateServiceMemberWithObjectById",
				serviceMemberWithObjectVo);
	}

	/******************* 组织机构迁移合并 ****************************/
	/**
	 * 
	 * @Title: queryServiceTeamHasObjectsForList
	 * @Description: TODO查询重复的服务人员
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<ServiceMemberWithObject> 返回类型
	 * @author wanggz
	 * @date 2014-10-28 上午10:04:56
	 */
	public List<ServiceMemberWithObject> queryServiceMemberWithObjectForList(
			Map map) {

		return getSqlMapClientTemplate().queryForList(
				"serviceMemberWithObject.queryServiceMemberWithObjectForList",
				map);
	}

	@Override
	public List<ServiceMemberWithObjectVo> findServiceMembersWithObjectsByTypeAndId(
			Map map) {
		return getSqlMapClientTemplate()
				.queryForList(
						"serviceMemberWithObject.findServiceMembersWithObjectsByTypeAndId",
						map);
	}

}
