package com.tianque.xichang.working.steadyWork.dao;

import java.util.List;

import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;

public interface PeopleInfoDao {
	void addPeopleInfo(PeopleInfo peopleInfo);

	boolean deletePeopleInfoBySteadyWorkId(Long steadyWorkId);

	boolean deletePeopleInfoBySteadyWorkIds(List<Long> ids);

	List<PeopleInfo> findPeopleInfoBySteadyWorkId(Long steadyWorkId);
}
