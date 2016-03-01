package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.SearchWorkBulletinDao;
import com.tianque.working.domain.WorkBulletin;
import com.tianque.working.vo.SearchWorkBulletinVo;

@Repository("searchWorkBulletinDao")
public class SearchWorkBulletinDaoImpl extends AbstractBaseDao implements SearchWorkBulletinDao {

	@Override
	public PageInfo<WorkBulletin> searchWorkBulletin(SearchWorkBulletinVo searchWorkBulletinVo,
			Integer page, Integer rows, String sidx, String sord) {
		if (searchWorkBulletinVo == null)
			return emptyWorkBulletinpage(page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createUser", searchWorkBulletinVo.getCreateUser());
		map.put("orgInternalCode", searchWorkBulletinVo.getOrgInternalCode());
		map.put("bulletinName", searchWorkBulletinVo.getBulletinName());
		map.put("bulletinDate", searchWorkBulletinVo.getBulletinDate());
		map.put("fillDate", searchWorkBulletinVo.getFillDate());
		map.put("updateUser", searchWorkBulletinVo.getUpdateUser());
		map.put("updateDate", searchWorkBulletinVo.getUpdateDate());

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchWorkBulletin.countWorkBulletins", map);

		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;

		List<WorkBulletin> issues = getSqlMapClientTemplate().queryForList(
				"searchWorkBulletin.searchWorkBulletins", map, (page - 1) * rows, rows);
		PageInfo<WorkBulletin> pageInfo = new PageInfo<WorkBulletin>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private PageInfo<WorkBulletin> emptyWorkBulletinpage(int pageSize) {
		PageInfo<WorkBulletin> pageInfo = new PageInfo<WorkBulletin>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<WorkBulletin>());
		return pageInfo;
	}

}
