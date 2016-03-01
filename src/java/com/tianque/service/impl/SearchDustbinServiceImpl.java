package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchDustbinDao;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;
import com.tianque.service.SearchDustbinService;

@Service("searchDustbinService")
@Transactional
public class SearchDustbinServiceImpl extends AbstractBaseService implements
		SearchDustbinService {

	@Autowired
	private SearchDustbinDao searchDustbinDao;

	@Override
	public PageInfo<Dustbin> findDustbinByQueryCondition(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			return searchDustbinDao.findDustbinByQueryCondition(
					searchDustbinVo, pageNum, pageSize, sidx, sord);
		} catch (Exception e) {
			return dealException(this, "findDustbinByQueryCondition",
					"操作失败，请重试", e);
		}
	}

	@Override
	public PageInfo<Dustbin> fastSearchDustbin(SearchDustbinVo searchDustbinVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String partType) {
		try {
			return searchDustbinDao.fastSearchDustbin(searchDustbinVo, pageNum,
					pageSize, sortField, order, partType);
		} catch (Exception e) {
			return dealException(this, "fastSearchDustbin", "操作失败，请重试", e);
		}
	}

	@Override
	public List<Dustbin> searchDustbinForExport(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String partType) {
		try {
			return searchDustbinDao.searchDustbinForExport(searchDustbinVo,
					pageNum, pageSize, sidx, sord, partType);
		} catch (Exception e) {
			return dealException(this, "searchDustbinForExport", "操作失败，请重试", e);
		}
	}

	@Override
	public Integer getCount(SearchDustbinVo searchDustbinVo) {
		try {
			return searchDustbinDao.getCount(searchDustbinVo);
		} catch (Exception e) {
			return dealException(this, "getCount", "操作失败，请重试", e);
		}
	}

}
