package com.tianque.plugin.serviceTeam.serviceTeamManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务团队数据访问层实现类
 * 
 * @author yangpengdian
 */
@Repository("serviceTeamDao")
public class ServiceTeamDaoImpl extends AbstractBaseDao implements
		ServiceTeamDao {

	@Override
	public ServiceTeamVo addServiceTeam(ServiceTeam serviceTeam) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceTeam.addServiceTeam", serviceTeam);
		return getServiceTeamById(id);
	}

	@Override
	public ServiceTeamVo getServiceTeamById(Long id) {
		return (ServiceTeamVo) getSqlMapClientTemplate().queryForObject(
				"serviceTeam.getServiceTeamById", id);
	}

	@Override
	public PageInfo<ServiceTeam> findServiceTeams(ServiceTeamVo serviceTeamVo,
			Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeam.countFindServiceTeams", serviceTeamVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeam>();
		}
		List<ServiceTeam> list = getSqlMapClientTemplate().queryForList(
				"serviceTeam.findServiceTeams", serviceTeamVo,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeam>(pageNum, pageSize, countNum, list);
	}

	@Override
	public Integer countServiceTeams(ServiceTeamVo serviceTeamVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeam.countFindServiceTeams", serviceTeamVo);
	}

	@Override
	public ServiceTeamVo updateServiceTeam(ServiceTeam serviceTeam) {
		getSqlMapClientTemplate().update("serviceTeam.updateServiceTeam",
				serviceTeam);
		return getServiceTeamById(serviceTeam.getId());
	}

	@Override
	public int deleteServiceTeam(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceTeam.deleteServiceTeam", id);
	}

	@Override
	public void logOutServiceTeam(ServiceTeam serviceTeam) {
		getSqlMapClientTemplate().update("serviceTeam.logOutServiceTeam",
				serviceTeam);
	}

	@Override
	public List<Map> countServiceObjectsForTeam(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"serviceTeamHasObjects.countServiceObjectsForTeam", id);
	}

	@Override
	public Integer countAllServiceTeamObjects(ServiceTeamVo serviceTeamVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamHasObjects.countAllServiceTeamObjects",
				serviceTeamVo);
	}

	@Override
	public Integer findServiceTeamMemberHasObjects(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceMemberWithObject.findServiceTeamMemberHasObjects",
				serviceTeamMemberVo);
	}

	@Override
	public List<ServiceTeam> searchServiceTeamsForExport(
			ServiceTeamVo serviceTeamVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (null == page) {
			return getSqlMapClientTemplate().queryForList(
					"serviceTeam.findServiceTeams", serviceTeamVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"serviceTeam.findServiceTeams", serviceTeamVo,
					(page - 1) * rows, rows);
		}
	}

	@Override
	public int deleteServiceTeamByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return getSqlMapClientTemplate().delete(
				"serviceTeam.deleteServiceTeamByIds", ids);
	}
}
