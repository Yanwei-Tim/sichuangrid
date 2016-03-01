package com.tianque.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.LowerPartyActivityDao;
import com.tianque.domain.PartyOrgActivity;

@Repository("lowerPartyActivityDaoImpl")
public class LowerPartyActivityDaoImpl extends AbstractBaseDao implements LowerPartyActivityDao {

	@Override
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(Integer pageNum,
			Integer pageSize, String sidx, String sord, Map<String, Object> map) {

		Integer countNum = (Integer) this.getSqlMapClientTemplate().queryForObject(
				"partyOrgActivity.countPartyOrgActivitys", map);
		if (!StringUtils.isEmpty(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<OptimalObject> list = this.getSqlMapClientTemplate().queryForList(
				"partyOrgActivity.findPartyOrgActivitys", map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);

	}

	private PageInfo<PartyOrgActivity> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
