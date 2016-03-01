package com.tianque.xichang.working.poorPeople.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;

/**
 * @ClassName: PoorPeopleMembersDao
 * @Description: 三本台账-困难群众台账 家庭成员-DAO接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 26, 2013 4:07:21 PM
 */
public interface PoorPeopleMembersDao {

	PoorPeopleMembers addPoorPeopleMembers(PoorPeopleMembers poorPeopleMembers);

	void deletePoorPeopleMembers(String id);

	public void deletePoorPeopleMembersByIds(String[] ids);

	PoorPeopleMembers getPoorPeopleMembersById(Long id);

	PoorPeopleMembers updatePoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers);

	PageInfo findPoorPeopleMembersForList(PoorPeopleMembers poorPeopleMembers);

}
