package com.tianque.xichang.working.poorPeople.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;

/**
 * @ClassName: PoorPeopleMembersService
 * @Description: 三本台账-困难群众台账-维护家庭成员服务接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 26, 2013 3:57:40 PM
 */
public interface PoorPeopleMembersService {

	PageInfo findPoorPeopleMembersForList(PoorPeopleMembers poorPeopleMembers);

	PoorPeopleMembers addPoorPeopleMembers(PoorPeopleMembers poorPeopleMembers);

	PoorPeopleMembers updatePoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers);

	void deletePoorPeopleMembers(String ids);

	PoorPeopleMembers getPoorPeopleMembersById(
			PoorPeopleMembers poorPeopleMembers);

}
