package com.tianque.working.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.vo.SignificantIssuedealVo;

public interface SignificantIssuedealService {

	SignificantIssuedeal getSignificantIssuedealById(Long id);

	SignificantIssuedeal addSignificantIssuedeal(SignificantIssuedeal significantIssuedeal);

	boolean deleteSignificantIssuedealById(Long id);

	SignificantIssuedeal updateSignificantIssuedeal(SignificantIssuedeal significantIssuedeal);

	PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryId(
			Long orgId, Long directoryId, Integer page, Integer rows, String sidx, String sord);

	PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryParentId(
			Long orgId, Long directoryId, Integer page, Integer rows, String sidx, String sord);

	SignificantIssuedeal reportedSignificantIssuedealById(Long id, Long orgId, Long expiredEntering);

	boolean backSignificantIssuedeal(Long id);

	public PageInfo<SignificantIssuedeal> getSignificantIssuedealsByInvestigationDate(
			SignificantIssuedealVo significantIssuedealVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public List<SignificantIssuedeal> findSignificantIssuedealsForPrint(Long orgId,
			String[] selectids);

	public List<SignificantIssuedeal> findSignificantIssuedeals(Long orgId, Long directoryId);

	/**
	 * 分页查询信息
	 */
	public PageInfo<SignificantIssuedeal> searchSignificantIssuedealWorkingRecord(
			SignificantIssuedealVo searchActivityWorkingRecord, Integer pageNum, Integer pageSize,
			String sortField, String order, String allDailyDirectoryId);

}
