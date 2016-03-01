package com.tianque.xichang.working.poorPeople.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;

/**
 * @ClassName: PoorPeopleService
 * @Description: 三本台账-困难群众台账-服务接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 23, 2013 5:56:19 PM
 */
public interface PoorPeopleService {

	PageInfo findUndoPoorPeopleForList(PoorPeople poorPeople, Long orgId,
			String module, String listType);

	PoorPeople addPoorPeople(PoorPeople poorPeople);

	PoorPeople updatePoorPeople(PoorPeople poorPeople);

	void deletePoorPeople(String ids);

	PoorPeople getPoorPeopleById(PoorPeople poorPeople);

	String exsistedIdCardByIdAndIdCardNo(PoorPeople poorPeople);

	String hasDuplicateSerialNumber(PoorPeople poorPeople);

}
