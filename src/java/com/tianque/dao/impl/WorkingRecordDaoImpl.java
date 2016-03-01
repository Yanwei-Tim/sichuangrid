package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.WorkingRecordDao;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.vo.SearchWorkingRecordVo;

@Repository("workingRecordDao")
public class WorkingRecordDaoImpl extends
		BaseDaoImpl<WorkingRecord, WorkingRecord> implements WorkingRecordDao {

	@Override
	public PageInfo<WorkingRecord> findWorkingRecordForPageByOrgCodeAndDailyDirectoryId(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String dailyDirectoryId,
			boolean displayLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("order", sord);
		map.put("sortField", sidx);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"workingRecord.countWorkingRecordByOrgCodeAndDailyDirectoryId",
				map);
		List<WorkingRecord> list = getSqlMapClientTemplate().queryForList(
				"workingRecord.findWorkingRecordByOrgCodeAndDailyDirectoryId",
				map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public WorkingRecord addWorkingRecord(WorkingRecord workingRecord) {
		if (!validateWhenAdd(workingRecord)) {
			throw new BusinessValidationException();
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"workingRecord.addWorkingRecord", workingRecord);
		WorkingRecord workingRecordRtn = getWorkingRecordById(id);
		return workingRecordRtn;
	}

	private boolean validateWhenAdd(WorkingRecord workingRecord) {
		if (workingRecord.getOrganization() == null
				|| workingRecord.getOrganization().getId() == null) {
			return false;
		}
		if (workingRecord.getOrganization() == null
				|| workingRecord.getOrganization().getOrgInternalCode() == null) {
			return false;
		}
		if (workingRecord.getDailyDirectory() == null
				|| workingRecord.getDailyDirectory().getId() == null) {
			return false;
		}
		if (workingRecord.getCreateUser() == null) {
			return false;
		}
		if (workingRecord.getCreateDate() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteWorkingRecordById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		} else {
			getSqlMapClientTemplate().delete(
					"workingRecord.deleteWorkingRecordById", id);
		}
	}

	@Override
	public PageInfo findPagerBySearchVo(SearchWorkingRecordVo searchVo,
			String orgInternalCode, String allDailyDirectoryId,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("dailyDirectoryId", allDailyDirectoryId);
		map.put("sortField", sortField);
		map.put("order", order);
		if (null != searchVo.getWorkingRecordType()) {
			map.put("workingRecordType", searchVo.getWorkingRecordType());
		}
		if (null != searchVo.getName() && !"".equals(searchVo.getName())) {
			map.put("name", searchVo.getName());
		}
		if (null != searchVo.getCreateDateBegin()) {
			map.put("createDateBegin", searchVo.getCreateDateBegin());
		}
		if (null != searchVo.getCreateDateEnd()) {
			map.put("createDateEnd", searchVo.getCreateDateEnd());
		}
		if (null != searchVo.getHasAttach()) {
			map.put("hasAttach", searchVo.getHasAttach());
		}
		if (null != searchVo.getDisplayLevel()) {
			map.put("displayLevel", searchVo.getDisplayLevel());
		}
		if (null != searchVo.getProceedSite()) {
			map.put("proceedSite", searchVo.getProceedSite());
		}
		if (null != searchVo.getSubject()) {
			map.put("subject", searchVo.getSubject());
		}
		if (null != searchVo.getWorkingUnit()) {
			map.put("workingUnit", searchVo.getWorkingUnit());
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"workingRecord.countWorkingRecordsBySearchVo", map);
		List<WorkingRecord> list = getSqlMapClientTemplate().queryForList(
				"workingRecord.searchWorkingRecordsBySearchVo", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public WorkingRecord getWorkingRecordById(Long id) {
		WorkingRecord workingRecord = (WorkingRecord) getSqlMapClientTemplate()
				.queryForObject("workingRecord.getWorkingRecordById", id);
		return workingRecord;
	}

	@Override
	public Long countSubTablesValue(Long directoryId) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"workingRecord.countSubTablesValue", directoryId);
	}

	@Override
	public List<WorkingRecord> findWorkingRecordByDailyDirectoryId(
			Long directoryId, Long organizationId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("dailyDirectoryId", directoryId);
		map.put("organizationId", organizationId);
		return (List<WorkingRecord>) getSqlMapClientTemplate().queryForList(
				"workingRecord.findWorkingRecordByDailyDirectoryId", map);
	}

	@Override
	protected void checkEntityWhenAdd(WorkingRecord entity) {
		if (entity == null) {
			throw new BusinessValidationException("工作台帐为空");
		}
		if (entity.getOrganization() == null
				|| entity.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织机构id为空");
		}
		if (entity.getOrgInternalCode() == null
				|| "".equals(entity.getOrgInternalCode())) {
			throw new BusinessValidationException("组织机构代码为空");
		}
		if (entity.getDailyDirectory() == null
				|| entity.getDailyDirectory().getId() == null) {
			throw new BusinessValidationException("台帐目录为空");
		}
		if (entity.getCreateUser() == null || "".equals(entity.getCreateUser())) {
			throw new BusinessValidationException("创建用户为空");
		}
		if (entity.getCreateDate() == null) {
			throw new BusinessValidationException("创建日期为空");
		}
	}

	@Override
	protected void checkEntityWhenUpdate(WorkingRecord entity) {
		if (entity == null) {
			throw new BusinessValidationException("工作台帐为空");
		}
		if (entity.getOrganization() == null
				|| entity.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织机构id为空");
		}
		if (entity.getOrgInternalCode() == null
				|| "".equals(entity.getOrgInternalCode())) {
			throw new BusinessValidationException("组织机构代码为空");
		}
		if (entity.getDailyDirectory() == null
				|| entity.getDailyDirectory().getId() == null) {
			throw new BusinessValidationException("台帐目录为空");
		}
		if (entity.getCreateUser() == null || "".equals(entity.getCreateUser())) {
			throw new BusinessValidationException("创建用户为空");
		}
		if (entity.getCreateDate() == null) {
			throw new BusinessValidationException("创建日期为空");
		}
		if (entity.getId() == null) {
			throw new BusinessValidationException("台帐id为空");
		}
	}

	private PageInfo<WorkingRecord> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<WorkingRecord> pageInfo = new PageInfo<WorkingRecord>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public int countWorkingRecordsByDailyDirectoryId(String orgCode, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("dailyDirectoryId", id);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"workingRecord.countWorkingRecordsByCodeAndId", map);
	}

	@Override
	public List<WorkingRecord> findWorkingRecordByIds(List<Long> listIds) {
		String ids = "";
		for (Long id : listIds) {
			ids += id + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		List<WorkingRecord> list = getSqlMapClientTemplate().queryForList(
				"workingRecord.findWorkingRecordByIds", ids);
		return list;
	}

}
