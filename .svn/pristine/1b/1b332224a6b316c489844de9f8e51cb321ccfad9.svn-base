package com.tianque.working.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.WorkDiary;

public interface WorkDiaryDao {

	WorkDiary addWorkDiary(WorkDiary workDiary);

	WorkDiary getWorkDiaryById(Long id);

	PageInfo<WorkDiary> findWorkDiarysForPageByOrgId(Long orgId, Integer rows,
			Integer page, String sidx, String sord);

	WorkDiary updateWorkDiary(WorkDiary workDiary);

	int deleteWorkDiaryById(Long id);

	public int deleteWorkDiaryByIds(String[] ids);

	List<WorkDiary> getWorkDiaryByObjectTypeAndObjectId(String objectType,
			Long objectId);

	PageInfo<WorkDiary> findWorkDiarysForPageByOrgInternalCode(
			String orgInternalCode, Integer rows, Integer page, String sidx,
			String sord);
}
