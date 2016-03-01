package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ServiceMemberHasObjectDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.supervisorManage.supervisorInfo.SupervisorInfoVo;

@Repository("serviceMemberHasObjectDao")
public class ServiceMemberHasObjectDaoImpl extends AbstractBaseDao implements
		ServiceMemberHasObjectDao {
	@Override
	public PageInfo<SupervisorInfoVo> findSupervisorforList(
			SupervisorInfoVo supervisorInfoVo, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", supervisorInfoVo.getPopulationId());
		map.put("populationType", supervisorInfoVo.getPopulationType());
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceMemberHasObject.countSupervisorforList", map);
		@SuppressWarnings("unchecked")
		List<SupervisorInfoVo> list = getSqlMapClientTemplate().queryForList(
				"serviceMemberHasObject.findSupervisorforList", map,
				(pageNum - 1) * pageSize, pageSize);
		return createSupervisorPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SupervisorInfoVo> createSupervisorPageInfo(int pageNum,
			int pageSize, Integer countNum, List<SupervisorInfoVo> list) {
		PageInfo<SupervisorInfoVo> pageInfo = new PageInfo<SupervisorInfoVo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<SupervisorInfoVo> searchSupervisor(
			SupervisorInfoVo supervisorInfoVo, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", supervisorInfoVo.getName());
		map.put("teamName", supervisorInfoVo.getTeamName());
		map.put("teamClazz",
				null != supervisorInfoVo.getTeamClazz()
						&& null != supervisorInfoVo.getTeamClazz().getId() ? supervisorInfoVo
						.getTeamClazz() : null);
		map.put("teamType",
				null != supervisorInfoVo.getTeamType()
						&& null != supervisorInfoVo.getTeamType().getId() ? supervisorInfoVo
						.getTeamType() : null);
		map.put("populationId", supervisorInfoVo.getPopulationId());
		map.put("populationType", supervisorInfoVo.getPopulationType());
		map.put("orginternalcode", supervisorInfoVo.getOrgInternalCode());

		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceMemberHasObject.countSupervisor", map);
		@SuppressWarnings("unchecked")
		List<SupervisorInfoVo> list = getSqlMapClientTemplate().queryForList(
				"serviceMemberHasObject.searchSupervisor", map,
				(pageNum - 1) * pageSize, pageSize);
		return createSupervisorPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public void addSupervisor(SupervisorInfoVo supervisorInfoVo) {
		getSqlMapClientTemplate().insert(
				"serviceMemberHasObject.addServiceMemberHasObject",
				supervisorInfoVo);
		if (getSqlMapClientTemplate().queryForObject(
				"serviceTeamHasObject.getServiceTeamHasObject",
				supervisorInfoVo) == null) {
			getSqlMapClientTemplate().insert(
					"serviceTeamHasObject.addServiceTeamHasObject",
					supervisorInfoVo);
		}
	}

	@Override
	public SupervisorInfoVo viewSupervisor(Long memberId) {
		return (SupervisorInfoVo) getSqlMapClientTemplate().queryForObject(
				"serviceMemberHasObject.viewSupervisor", memberId);
	}

	@Override
	public List<SupervisorInfoVo> searchSupervisorsForExport(
			SupervisorInfoVo supervisorInfoVo, Integer page, Integer rows,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", supervisorInfoVo.getPopulationId());
		map.put("populationType", supervisorInfoVo.getPopulationType());
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"serviceMemberHasObject.findSupervisorforList", map);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"serviceMemberHasObject.findSupervisorforList", map,
					(page - 1) * rows, rows);

		}
	}

	@Override
	public int deleteSupervisors(SupervisorInfoVo population) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", population.getMemberId());
		map.put("teamId", population.getTeamId());
		map.put("populationId", population.getPopulationId());
		map.put("populationType", population.getPopulationType());
		if (population.getMemberId() == null
				|| population.getPopulationId() == null
				|| population.getPopulationType() == null) {
			throw new BusinessValidationException(
					"人员populationId或者人员类型populationType或服务对象memberId不能为空");
		}
		return getSqlMapClientTemplate().delete(
				"serviceMemberHasObject.deleteServiceMemberHasObject", map);
	}

}
