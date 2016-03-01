package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.dao.SearchActualCompanyDao;
import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchActualCompanyVo;
import com.tianque.service.SearchActualCompanyService;

@Service("searchActualCompanyService")
@Transactional
public class SearchActualCompanyServiceImpl implements
		SearchActualCompanyService {
	@Autowired
	private SearchActualCompanyDao searchActualCompanyDao;

	@Override
	public PageInfo<ActualCompany> findActualCompanyByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchActualCompanyVo.setFullPinyin(searchActualCompanyVo
				.getCompanyName());
		searchActualCompanyVo.setSimplePinyin(searchActualCompanyVo
				.getCompanyName());
		return searchActualCompanyDao.findActualCompanyByQueryCondition(
				searchActualCompanyVo, pageNum, pageSize, sidx, sord);
	}

	@Override
	public List<ActualCompany> searchActualCompanysForExport(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		return searchActualCompanyDao.searchActualCompanysForExport(
				searchActualCompanyVo, pageNum, pageSize, sidx, sord);
	}

	@Override
	public List<ActualCompany> findIssueRelateObjectListByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, String sidx,
			String sord) {
		searchActualCompanyVo.setFullPinyin(searchActualCompanyVo
				.getCompanyName());
		searchActualCompanyVo.setSimplePinyin(searchActualCompanyVo
				.getCompanyName());
		return searchActualCompanyDao
				.findIssueRelateObjectListByQueryCondition(
						searchActualCompanyVo, sidx, sord);
	}

	@Override
	public Integer getCount(SearchActualCompanyVo searchVo) {
		return searchActualCompanyDao.getCount(searchVo);
	}

}
