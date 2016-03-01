package com.tianque.peopleLog.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;

public interface SearchPeopleLogDao {
	/**
	 * 根据查询条件分页查询我的日志
	 * 
	 * @param searchPeopleLogVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findPeopleLogByQueryCondition(SearchPeopleLogVo searchPeopleLogVo,
			Integer page, Integer rows, String sidx, String sord);
}
