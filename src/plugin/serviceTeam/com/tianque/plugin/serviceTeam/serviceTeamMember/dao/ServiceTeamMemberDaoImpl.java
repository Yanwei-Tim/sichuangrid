package com.tianque.plugin.serviceTeam.serviceTeamMember.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMemberBase;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceMemberInTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务成员数据访问层实现类
 * 
 * @author tengjia
 */
@Repository("serviceTeamMemberDao")
public class ServiceTeamMemberDaoImpl extends AbstractBaseDao implements
		ServiceTeamMemberDao {

	@Override
	public ServiceTeamMemberVo addServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceTeamMember.addServiceTeamMemberBase",
				serviceTeamMemberBase);
		return getServiceTeamMemberBaseById(id);
	}

	@Override
	public ServiceTeamMemberVo addTeamAndMemberRelation(
			ServiceTeamMember serviceTeamMemberDetails) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceTeamMember.addTeamAndMemberRelation",
				serviceTeamMemberDetails);
		return getServiceTeamMemberDetailsById(id);
	}

	@Override
	public ServiceTeamMemberVo getServiceTeamMemberBaseById(Long id) {
		return (ServiceTeamMemberVo) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.getServiceTeamMemberBaseById", id);
	}

	@Override
	public ServiceTeamMemberVo getServiceTeamMemberDetailsById(Long id) {
		return (ServiceTeamMemberVo) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.getServiceTeamMemberDetailsById", id);
	}

	@Override
	public ServiceTeamMemberVo updateServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		getSqlMapClientTemplate().update(
				"serviceTeamMember.updateServiceTeamMemberBase",
				serviceTeamMemberBase);
		return getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
	}

	@Override
	public int deleteServiceTeamMemberBase(Long id) {
		if (id == null)
			return 0;
		int deleteCount = 0;
		ServiceTeamMemberVo serviceTeamMemberVo = getServiceTeamMemberBaseById(id);
		if (serviceTeamMemberVo != null
				&& serviceTeamMemberVo.getBaseId() != null) {
			deleteCount = getSqlMapClientTemplate().delete(
					"serviceTeamMember.deleteServiceTeamMemberBase", id);
		}
		return deleteCount;
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPage(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countServiceTeamMemberVoPage",
				serviceTeamMemberVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeamMemberVo>();
		}
		serviceTeamMemberVo.setSortField(sidx);
		serviceTeamMemberVo.setOrder(sord);
		List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
				.queryForList("serviceTeamMember.findServiceTeamMemberVoPage",
						serviceTeamMemberVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeamMemberVo>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageNotInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countServiceTeamMemberVoPageNotInTeam",
				serviceTeamMemberVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeamMemberVo>();
		}
		List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
				.queryForList(
						"serviceTeamMember.findServiceTeamMemberVoPageNotInTeam",
						serviceTeamMemberVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeamMemberVo>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public int reMoveTeamAndMemberRelation(Long memberId) {
		return getSqlMapClientTemplate().delete(
				"serviceTeamMember.reMoveTeamAndMemberRelation", memberId);
	}

	@Override
	public int updateServiceTeamMemberOnDuty(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		return getSqlMapClientTemplate().update(
				"serviceTeamMember.updateServiceTeamMemberOnDuty",
				serviceTeamMemberVo);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceMemberFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countfindServiceMemberFromTeams",
				serviceTeamMemberVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeamMemberVo>();
		}
		List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
				.queryForList("serviceTeamMember.findServiceMemberFromTeams",
						serviceTeamMemberVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeamMemberVo>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public List<ServiceTeamMemberVo> findServiceMembersForServiceRecord(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		if(StringUtil.isStringAvaliable(serviceTeamMemberVo.getSearchType())&&
				serviceTeamMemberVo.getSearchType().equals(DialogMode.ALLSEARCH_MODE)){
			List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
					.queryForList("serviceTeamMember.findServiceMembersforAuto",serviceTeamMemberVo);
			return list;
		}
		return getSqlMapClientTemplate().queryForList(
				"serviceTeamMember.findServiceMembersForServiceRecord",
				serviceTeamMemberVo);
	}

	@Override
	public boolean isDeleteAbleForTeam(Long id) {
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countServiceTeamHasMembers", id);
		return countNum < 1;
	}

	@Override
	public boolean isDeleteAbleForTeamMember(Long id) {
		int countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countServiceTeamMembersHasRecords", id);
		return countNum < 1;
	}

	@Override
	public PageInfo<ServiceMemberVo> findServiceMembersByServiceMemberVo(
			ServiceMemberVo serviceMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("onDuty", serviceMemberVo.getOnDuty());
		map.put("teamMember", serviceMemberVo.getTeamMember());
		map.put("objectTypeList", serviceMemberVo.getObjectTypeList());
		map.put("objectId", serviceMemberVo.getObjectId());
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"serviceMemberVo.countFindServiceMembersByServiceMemberVo",
						map);
		if (countNum == 0) {
			return new PageInfo<ServiceMemberVo>();
		}
		List<ServiceMemberVo> list = getSqlMapClientTemplate().queryForList(
				"serviceMemberVo.findServiceMembersByServiceMemberVo", map,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceMemberVo>(pageNum, pageSize, countNum, list);
	}

	@Override
	public Integer findServiceMembersNumByServiceMemberVo(
			ServiceMemberVo serviceMemberVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("onDuty", serviceMemberVo.getOnDuty());
		map.put("teamMember", serviceMemberVo.getTeamMember());
		map.put("objectTypeList", serviceMemberVo.getObjectTypeList());
		map.put("objectId", serviceMemberVo.getObjectId());
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"serviceMemberVo.countFindServiceMembersByServiceMemberVo",
						map);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBasesPageList(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		String contNumStr="serviceTeamMember.countFindServiceTeamMemberBasesPageList";
		String queryForListStr="serviceTeamMember.findServiceTeamMemberBasesPageList";
		//手机任务清单帮扶人员调用，关联服务团队表
		if(serviceTeamMemberVo.getSearchType()!=null&&
				serviceTeamMemberVo.getSearchType().equals("mobile")){
			contNumStr="serviceTeamMember.countServiceMembersforAutoPage";
			queryForListStr="serviceTeamMember.findServiceMembersforAutoPage";
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				contNumStr,serviceTeamMemberVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeamMemberVo>();
		}
		List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
				.queryForList(queryForListStr,
						serviceTeamMemberVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeamMemberVo>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public Integer getAllServiceTeamMembers(String orgCode) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.getAllServiceTeamMembers", orgCode);
	}

	@Override
	public List<ServiceTeamMemberVo> searchServiceTeamMemberForExport(
			ServiceTeamMemberVo serviceTeamMemberCondition, Integer page,
			Integer rows, String sidx, String sord) {
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"serviceTeamMember.searchServiceTeamMemberForExport",
					serviceTeamMemberCondition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"serviceTeamMember.searchServiceTeamMemberForExport",
					serviceTeamMemberCondition, (page - 1) * rows, rows);
		}
	}

	@Override
	public List<ServiceTeamMemberVo> getServiceTeamMemberDetailsByBaseId(
			Long baseId) {

		return getSqlMapClientTemplate()
				.queryForList(
						"serviceTeamMember.getServiceTeamMemberDetailsByBaseId",
						baseId);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findSameMembersByNameAndMobile(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page, Integer rows) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countFindSameMembersByNameAndMobile",
				serviceTeamMemberVo);
		List<ServiceTeamMemberVo> list = new ArrayList();
		if (page == null) {
			list = getSqlMapClientTemplate().queryForList(
					"serviceTeamMember.findSameMembersByNameAndMobile",
					serviceTeamMemberVo);
			return new PageInfo<ServiceTeamMemberVo>(1, countNum, countNum,
					list);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"serviceTeamMember.findSameMembersByNameAndMobile",
					serviceTeamMemberVo, (page - 1) * rows, rows);
			return new PageInfo<ServiceTeamMemberVo>(page, rows, countNum, list);
		}
	}

	@Override
	public List<ServiceTeamMemberVo> findServiceMembersByBaseId(Long baseId) {
		return getSqlMapClientTemplate().queryForList(
				"serviceTeamMember.findServiceMembersByBaseId", baseId);
	}

	@Override
	public void combine(ServiceTeamMemberVo combineVo) {
		getSqlMapClientTemplate()
				.update("serviceTeamMember.combine", combineVo);

	}

	@Override
	public List<ServiceMemberInTeamVo> getServiceMemberInTeamVoByMemberId(
			Long memberId) {

		return getSqlMapClientTemplate().queryForList(
				"serviceTeamMember.getServiceMemberInTeamVoByMemberId",
				memberId);
	}

	@Override
	public void updateServiceTeamMemberOnDutyAndPosition(
			ServiceTeamMemberVo serviceTeamMemberVo) {
		getSqlMapClientTemplate().update(
				"serviceTeamMember.updateServiceTeamMemberOnDutyAndPosition",
				serviceTeamMemberVo);

	}

	@Override
	public void changeOrg(ServiceTeamMemberBase serviceTeamMemberBase) {
		getSqlMapClientTemplate().update("serviceTeamMember.changeOrg",
				serviceTeamMemberBase);

	}

	@Override
	public int deleteServiceTeamMemberBaseByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return getSqlMapClientTemplate().delete(
				"serviceTeamMember.deleteServiceTeamMemberBaseByIds", ids);
	}

	@Override
	public PageInfo<ServiceTeamMemberVo> findServiceMembersFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceTeamMember.countFindServiceMembersFromTeams",
				serviceTeamMemberVo);
		if (countNum == 0) {
			return new PageInfo<ServiceTeamMemberVo>();
		}
		List<ServiceTeamMemberVo> list = getSqlMapClientTemplate()
				.queryForList("serviceTeamMember.findServiceMembersFromTeams",
						serviceTeamMemberVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceTeamMemberVo>(pageNum, pageSize, countNum,
				list);
	}
}
