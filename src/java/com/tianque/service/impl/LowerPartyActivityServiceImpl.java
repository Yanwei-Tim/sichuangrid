package com.tianque.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.LowerPartyActivityDao;
import com.tianque.dao.PartyOrgActivityDao;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.service.LowerPartyActivityService;

@Service("lowerPartyActivityServiceImpl")
public class LowerPartyActivityServiceImpl extends LogableService implements
		LowerPartyActivityService {

	@Autowired
	private PartyOrgActivityDao partyOrgActivityDao;

	@Autowired
	private LowerPartyActivityDao lowerPartyActivityDao;

	@Override
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		// map中key yearActivity 活动年份 orgInternalCode

		map.put("yearActivity", year);
		map.put("orgInternalCode", orgInternalCode);

		return lowerPartyActivityDao.findPartyOrgActivityForPageByOrgId(
				pageNum, pageSize, sidx, sord, map);

	}

}
