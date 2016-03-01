package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.WorkDiaryDao;
import com.tianque.working.domain.WorkDiary;

@Repository
public class WorkDiaryDaoImpl extends AbstractBaseDao implements WorkDiaryDao {

	@Override
	public WorkDiary addWorkDiary(WorkDiary workDiary) {
		validateWorkDiary(workDiary);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"workDiary.addWorkDiary", workDiary);
		return getWorkDiaryById(id);
	}

	private void validateWorkDiary(WorkDiary workDiary) {
		if (null == workDiary) {
			throw new BusinessValidationException("工作日志不能为空");
		}
		if (null == workDiary.getObjectId()) {
			throw new BusinessValidationException("工作日志类型不能为空");
		}
		if (null == workDiary.getObjectType()) {
			throw new BusinessValidationException("工作日志类型不能为空");
		}
		if (null == workDiary.getWorkUserName()) {
			throw new BusinessValidationException("录入人员姓名不能为空");
		}
	}

	@Override
	public WorkDiary getWorkDiaryById(Long id) {
		if (id == null || id.longValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
		return (WorkDiary) getSqlMapClientTemplate().queryForObject(
				"workDiary.getWorkDiaryById", id);
	}

	@Override
	public PageInfo<WorkDiary> findWorkDiarysForPageByOrgInternalCode(
			String orgInternalCode, Integer pageSize, Integer pageNum,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"workDiary.countWorkDiarysForPageByOrgInternalCode", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<WorkDiary> list = getSqlMapClientTemplate().queryForList(
				"workDiary.findWorkDiarysForPageByOrgInternalCode", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<WorkDiary> pageInfo = new PageInfo<WorkDiary>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	@Override
	public PageInfo<WorkDiary> findWorkDiarysForPageByOrgId(Long orgId,
			Integer pageSize, Integer pageNum, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgId", orgId);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"workDiary.countWorkDiarysForPageByOrgId", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<WorkDiary> list = getSqlMapClientTemplate().queryForList(
				"workDiary.findWorkDiarysForPageByOrgId", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<WorkDiary> pageInfo = new PageInfo<WorkDiary>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	@Override
	public WorkDiary updateWorkDiary(WorkDiary workDiary) {
		if (workDiary == null || workDiary.getId() == null
				|| workDiary.getId().longValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate()
				.update("workDiary.updateWorkDiary", workDiary);
		return this.getWorkDiaryById(workDiary.getId());
	}

	@Override
	public int deleteWorkDiaryById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		int deleteCount = 0;
		WorkDiary workDiary = getWorkDiaryById(id);

		if (workDiary != null && workDiary.getId() != null) {
			deleteCount = getSqlMapClientTemplate().delete(
					"workDiary.deleteWorkDiaryById", id);
		}
		return deleteCount;
	}

	@Override
	public List<WorkDiary> getWorkDiaryByObjectTypeAndObjectId(
			String objectType, Long objectId) {
		if (objectId == null || objectId.longValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("objectType", objectType);
		map.put("objectId", objectId);
		return getSqlMapClientTemplate().queryForList(
				"workDiary.getWorkDiaryByObjectTypeAndObjectId", map);
	}

	@Override
	public int deleteWorkDiaryByIds(String[] ids) {
		if (null == ids || ids.length == 0) {
			throw new BusinessValidationException("ID为空");
		}
		// int deleteCount = 0;
		// WorkDiary workDiary = getWorkDiaryById(id);

		// if (workDiary != null && workDiary.getId() != null) {

		// }
		return getSqlMapClientTemplate().delete(
				"workDiary.deleteWorkDiaryByIds", ids);
	}
}
