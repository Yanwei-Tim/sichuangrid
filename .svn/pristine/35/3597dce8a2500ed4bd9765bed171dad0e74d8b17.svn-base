package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.SearchWorkDiaryDao;
import com.tianque.working.domain.WorkDiary;
import com.tianque.working.vo.SearchWorkDiaryVo;

@Repository
public class SearchWorkDiaryDaoImpl extends AbstractBaseDao implements
		SearchWorkDiaryDao {

	@Override
	public PageInfo<WorkDiary> searchWorkDiary(
			SearchWorkDiaryVo searchWorkDiaryVo, Integer pageNum,
			Integer pageSize, String sidx, String sord, boolean searchChild) {
		if (searchWorkDiaryVo == null
				|| searchWorkDiaryVo.getOrganization().getId() == null
				|| searchWorkDiaryVo.getOrganization().getId().longValue() == 0L) {
			throw new BusinessValidationException("ordId不能为空");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		if (searchChild) {
			map.put("orgInternalCode", searchWorkDiaryVo.getOrgInternalCode());
		} else {
			map.put("orgId", searchWorkDiaryVo.getOrganization().getId());
		}
		map.put("diaryType", searchWorkDiaryVo.getDiaryType());
		map.put("workPlace", searchWorkDiaryVo.getWorkPlace());
		map.put("workTimeEnd", searchWorkDiaryVo.getWorkTimeEnd());
		map.put("workTimeStart", searchWorkDiaryVo.getWorkTimeStart());
		map.put("workUser", searchWorkDiaryVo.getWorkUser());
		map.put("workContent", searchWorkDiaryVo.getWorkContent());
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = 0;
		if (searchChild) {
			countNum = (Integer) getSqlMapClientTemplate().queryForObject(
					"searchWorkDiary.countWorkDiarysForPageByOrgInternalCode",
					map);
		} else {
			countNum = (Integer) getSqlMapClientTemplate().queryForObject(
					"searchWorkDiary.countWorkDiarysForPageByOrgId", map);
		}

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<WorkDiary> list = null;
		if (searchChild) {
			list = getSqlMapClientTemplate().queryForList(
					"searchWorkDiary.searchWorkDiarysForPageByOrgInternalCode",
					map, (pageNum - 1) * pageSize, pageSize);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"searchWorkDiary.searchWorkDiarysForPageByOrgId", map,
					(pageNum - 1) * pageSize, pageSize);
		}

		PageInfo<WorkDiary> pageInfo = new PageInfo<WorkDiary>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

}
