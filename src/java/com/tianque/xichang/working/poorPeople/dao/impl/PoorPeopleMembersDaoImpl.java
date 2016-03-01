package com.tianque.xichang.working.poorPeople.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.poorPeople.dao.PoorPeopleMembersDao;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;

/**
 * @ClassName: PoorPeopleMembersDao
 * @Description: 三本台账-困难群众台账 家庭成员-DAO接口实现
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 26, 2013 4:07:21 PM
 */
@Repository("poorPeopleMembersDao")
public class PoorPeopleMembersDaoImpl extends AbstractBaseDao implements
		PoorPeopleMembersDao {

	@Override
	public PoorPeopleMembers addPoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"poorPeopleMembers.addPoorPeopleMembers", poorPeopleMembers);
		return getPoorPeopleMembersById(id);
	}

	@Override
	public void deletePoorPeopleMembers(String id) {
		getSqlMapClientTemplate().delete(
				"poorPeopleMembers.deletePoorPeopleMembers", id);
	}

	@Override
	public PageInfo findPoorPeopleMembersForList(
			PoorPeopleMembers poorPeopleMembers) {
		PageInfo<PoorPeopleMembers> pageInfo = new PageInfo<PoorPeopleMembers>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"poorPeopleMembers.findPoorPeopleMembersForListCount",
				poorPeopleMembers);
		int pageCount = 0;
		if ((countNum % poorPeopleMembers.getRows()) == 0) {
			pageCount = countNum / poorPeopleMembers.getRows();
		} else {
			pageCount = countNum / poorPeopleMembers.getRows() + 1;
		}
		poorPeopleMembers
				.setPage(poorPeopleMembers.getPage() > pageCount ? pageCount
						: poorPeopleMembers.getPage());
		if (countNum > 0) {
			List<PoorPeopleMembers> list = getSqlMapClientTemplate()
					.queryForList(
							"poorPeopleMembers.findPoorPeopleMembersForList",
							poorPeopleMembers,
							(poorPeopleMembers.getPage() - 1)
									* poorPeopleMembers.getRows(),
							poorPeopleMembers.getRows());
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PoorPeopleMembers>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(poorPeopleMembers.getPage());
		pageInfo.setPerPageSize(poorPeopleMembers.getRows());
		return pageInfo;
	}

	@Override
	public PoorPeopleMembers getPoorPeopleMembersById(Long id) {
		return (PoorPeopleMembers) getSqlMapClientTemplate().queryForObject(
				"poorPeopleMembers.getPoorPeopleMembersById", id);
	}

	@Override
	public PoorPeopleMembers updatePoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers) {
		getSqlMapClientTemplate().update(
				"poorPeopleMembers.updatePoorPeopleMembers", poorPeopleMembers);
		return getPoorPeopleMembersById(poorPeopleMembers.getId());
	}

	@Override
	public void deletePoorPeopleMembersByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		getSqlMapClientTemplate().delete(
				"poorPeopleMembers.deletePoorPeopleMembersByIds", ids);

	}

}
