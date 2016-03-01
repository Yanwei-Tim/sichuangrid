package com.tianque.working.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.vo.SignificantIssuedealVo;

public interface SignificantIssuedealDao {
	public SignificantIssuedeal addSignificantIssuedeal(SignificantIssuedeal significantIssuedeal);

	public SignificantIssuedeal getSignificantIssuedealById(Long id);

	public boolean deleteSignificantIssuedealById(Long id);

	public SignificantIssuedeal updateSignificantIssuedeal(SignificantIssuedeal significantIssuedeal);

	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsByOrgIdAndDirectoryId(
			Long organizationId, String directoryId, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer page, Integer rows, String sidx,
			String sord);

	public SignificantIssuedeal updateSignificantIssuedealForStautsAndReportTimeById(Long id,
			Long status, Date now, Long expiredEntering);

	public PageInfo<SignificantIssuedeal> getSignificantIssuedealsByInvestigationDate(
			SignificantIssuedealVo significantIssuedealVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public List<SignificantIssuedeal> findSignificantIssuedealsForPrint(Long organizationId,
			String selectids);

	public List<SignificantIssuedeal> findSignificantIssuedeals(Long orgId, Long directoryId);

	/**
	 * 分页查询信息
	 */
	public PageInfo<SignificantIssuedeal> searchSignificantIssuedealWorkingRecord(
			SignificantIssuedealVo searchSignificantIssuedealWorkingRecord, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId);

	public SignificantIssuedeal getSignificantIssuedealByFromId(Long fromId);
}
