package com.tianque.working.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;

public interface KeyAreasOfInvestigationInfoDao {
	public KeyAreasOfInvestigationInfo addKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo);

	public KeyAreasOfInvestigationInfo getKeyAreasOfInvestigationInfoById(Long id);

	public boolean deleteKeyAreasOfInvestigationInfoById(Long id);

	public KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo);

	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosByOrgIdAndDirectoryId(
			Long organizationId, String directoryId, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer page, Integer rows, String sidx,
			String sord);

	public KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfoForStautsAndReportTimeById(
			Long id, Long status, Date now, Long expiredEntering);

	public PageInfo<KeyAreasOfInvestigationInfo> getKeyAreasOfInvestigationInfosByInvestigationDate(
			KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPrint(
			Long organizationId, String selectids);

	public Integer countKeyAreasOfInvestigationInfoById(Long orgId, Long id);

	List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfos(Long orgId, Long directoryId);

	/**
	 * 根据条件分页查询信息
	 */
	public PageInfo<KeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchActivityWorkingRecord, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId);

}
