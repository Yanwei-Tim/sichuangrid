package com.tianque.xichang.working.steadyWork.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;
import com.tianque.xichang.working.steadyWork.domain.vo.PeopleInfoVo;

public interface SteadyWorkService {

	SteadyWork addSteadyWork(SteadyWork steadyWork, PeopleInfoVo peopleInfoVo);

	SteadyWork updateSteadyWork(SteadyWork steadyWork, PeopleInfoVo peopleInfoVo);

	boolean deleteSteadyWorkByIds(List<Long> ids);

	SteadyWork getSteadyWorkById(Long id);

	PageInfo<SteadyWork> findUndoSteadyWorkForPageByOrgInternalCode(
			SteadyWork steadyWork, Long orgId, Integer state, Pager pager);

	/**
	 * 根据条件查询
	 * 
	 * @param steadyWork
	 * @param peopleInfo
	 * @param genaratePager
	 * @return
	 */
	PageInfo findSteadyWorkForPageByCondition(SteadyWork steadyWork,
			PeopleInfo peopleInfo, Integer state, Pager genaratePager);

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param steadyWork
	 * @return
	 */
	String hasDuplicateSerialNumber(Long id, String serialNumber);

}
