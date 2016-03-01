package com.tianque.peopleLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.dao.PeopleLogDao;
import com.tianque.peopleLog.dao.SearchPeopleLogDao;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;

@Service("searchPeopLogService")
@Transactional
public class SearchPeopleLogServiceImpl extends AbstractBaseService implements
		SearchPeopleLogService {
	@Autowired
	private SearchPeopleLogDao searchPeopleLogDao;
	@Autowired
	private PeopleLogDao peopleLogDao;

	@Override
	public PageInfo<PeopleLog> findPeopleLogByQueryCondition(
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			PageInfo<PeopleLog> peopleLogs = searchPeopleLogDao
					.findPeopleLogByQueryCondition(searchPeopleLogVo, page,
							rows, sidx, sord);
			if (null != peopleLogs.getResult()
					&& peopleLogs.getResult().size() > 0) {
				for (PeopleLog peopleLog : peopleLogs.getResult()) {
					if (peopleLog.getIsAttachment())
						peopleLog
								.setPeopleLogFiles(peopleLogDao
										.findPeopleLogAttachFilesByPeopleLogId(peopleLog
												.getId()));
				}
			}
			return peopleLogs;
		} catch (Exception e) {
			return dealException(this, "findPeopleLogByQueryCondition",
					"查找我的日志数据错误", e);
		}
	}

}
