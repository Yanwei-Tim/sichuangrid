package com.tianque.peopleLog.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;

public interface SearchCommentLogService {

	public PageInfo<CommentLog> findCommentLogByCondition(SearchPeopleLogVo searchPeopleLogVo,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<PeopleLog> findSubLogByQueryCondition(Long orgId,
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows, String sidx,
			String sord, boolean isPeer);
}
