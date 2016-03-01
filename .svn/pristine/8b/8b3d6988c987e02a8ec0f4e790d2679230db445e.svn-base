package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchOverseaPersonnelDao;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;

@Repository("searchOverseaPersonnelDao")
public class SearchOverseaPersonnelDaoImpl extends AbstractBaseDao implements
		SearchOverseaPersonnelDao {
	@Override
	public PageInfo<OverseaPersonnel> searchOverseaPersonnel(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order) {
		if (searchOverseaPersonnelVo == null)
			return emptyOverseaPersonnelPage(pageSize);
		searchOverseaPersonnelVo.setSortField(sortField);
		searchOverseaPersonnelVo.setOrder(order);
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchOverseaPersonnel.countSearchOverseaPersonnel",
				searchOverseaPersonnelVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<OverseaPersonnel> list = getSqlMapClientTemplate()
					.queryForList(
							"searchOverseaPersonnel.searchOverseaPersonnel",
							searchOverseaPersonnelVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<OverseaPersonnel> fastSearchOverseaPersonnel(
			SearchOverseaPersonnelVo overseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order) {
		if (overseaPersonnelVo == null)
			return emptyOverseaPersonnelPage(pageSize);
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchOverseaPersonnel.countFastSearchOverseaPersonnel",
				getOverseaPersonnelVoMap(overseaPersonnelVo, sortField, order));
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<OverseaPersonnel> list = getSqlMapClientTemplate()
					.queryForList(
							"searchOverseaPersonnel.fastSearchOverseaPersonnel",
							getOverseaPersonnelVoMap(overseaPersonnelVo,
									sortField, order),
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<OverseaPersonnel> emptyOverseaPersonnelPage(int pageSize) {
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		return pageInfo;
	}

	@Override
	public List<OverseaPersonnel> searchOverseaPersonnelForExport(
			SearchOverseaPersonnelVo condition, Integer page, Integer rows,
			String sortField, String order) {
		condition.setSortField(sortField);
		condition.setOrder(order);
		Long logout = condition.getLogOut();
		if (logout == null) {
			condition.setLogOut(0L);
		}
		if (page == null) {

			return getSqlMapClientTemplate().queryForList(
					"searchOverseaPersonnel.searchOverseaPersonnel", condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchOverseaPersonnel.searchOverseaPersonnel", condition,
					(page - 1) * rows, rows);
		}
	}

	public List<OverseaPersonnel> findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchOverseaPersonnel.findOverseaNameAndPinyinAndOrgInternalCode",
						map);
	}

	private Map getOverseaPersonnelVoMap(
			SearchOverseaPersonnelVo overseaPersonnelVo, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", overseaPersonnelVo.getOrgInternalCode());
		map.put("logOut", overseaPersonnelVo.getLogOut());
		map.put("englishName", overseaPersonnelVo.getEnglishName());
		map.put("certificateNo", overseaPersonnelVo.getCertificateNo());
		map.put("fastSearchKeyWords", overseaPersonnelVo
				.getFastSearchKeyWords());
		map.put("sortField", sortField);
		map.put("order", order);
		return map;
	}

	@Override
	public Integer getCount(SearchOverseaPersonnelVo searchOverseaPersonnelVo) {
		// TODO Auto-generated method stub
		if (null == searchOverseaPersonnelVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchOverseaPersonnel.countSearchOverseaPersonnel",
				searchOverseaPersonnelVo);
	}
}
