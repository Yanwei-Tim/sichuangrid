package com.tianque.working.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;

public interface KeyAreasOfInvestigationInfoService {

	KeyAreasOfInvestigationInfo getKeyAreasOfInvestigationInfoById(Long id);

	KeyAreasOfInvestigationInfo addKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo);

	boolean deleteKeyAreasOfInvestigationInfoById(Long id);

	KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo);

	PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryId(
			Long orgId, Long directoryId, Integer page, Integer rows, String sidx, String sord);

	PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryParentId(
			Long orgId, Long directoryId, Integer page, Integer rows, String sidx, String sord);

	KeyAreasOfInvestigationInfo reportedKeyAreasOfInvestigationInfoById(Long id, Long orgId,
			Long expiredEntering);

	boolean backKeyAreasOfInvestigationInfo(Long id);

	public PageInfo<KeyAreasOfInvestigationInfo> getKeyAreasOfInvestigationInfosByInvestigationDate(
			KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPrint(Long orgId,
			String[] selectids);

	List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfos(Long orgId, Long directoryId);

	/**
	 * 根据条件分页查询信息
	 */
	public PageInfo<KeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchActivityWorkingRecord, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId);

}
