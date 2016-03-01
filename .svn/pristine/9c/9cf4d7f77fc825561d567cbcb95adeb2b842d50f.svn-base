package com.tianque.xichang.working.steadyWork.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.xichang.working.steadyWork.dao.PeopleInfoDao;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;

@Repository("peopleInfoDao")
public class PeopleInfoDaoImpl extends BaseDaoImpl<PeopleInfo> implements
		PeopleInfoDao {

	@Override
	public void addPeopleInfo(PeopleInfo peopleInfo) {
		add(peopleInfo);
	}

	@Override
	public boolean deletePeopleInfoBySteadyWorkId(Long steadyWorkId) {
		return delete(steadyWorkId, "deletePeopleInfoBySteadyWorkId");
	}

	@Override
	public List<PeopleInfo> findPeopleInfoBySteadyWorkId(Long steadyWorkId) {
		return findForList(steadyWorkId, "findPeopleInfoBySteadyWorkId");
	}

	@Override
	public boolean deletePeopleInfoBySteadyWorkIds(List<Long> ids) {
		return delete(ids, "deletePeopleInfoBySteadyWorkIds");
	}

}
