package com.tianque.baseInfo.orgLocationTrack.service;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.domain.OrgLocationTracks;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;

public interface OrgLocationTracksService {

	/**
	 * 轨迹页面列表
	 * 
	 * @param orgLocationOrgId
	 *            组织场所所在组织id
	 * @param orgLocationName
	 *            组织场所名称
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<OrgLocationTracks> findOrgLocationTracksByOrgIdAndName(
			Integer orgLocationOrgId, String orgLocationName, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 组织场所轨迹记录
	 * 
	 * @param 临时对象
	 *            组织场所对象
	 * @param orgLocationType
	 *            组织场所类型
	 * @param orgLocationinitType
	 *            组织场所内置类型
	 * @param oldOrg
	 *            组织场所原组织对象
	 * @param operationType
	 *            操作类型
	 * @param operationContent
	 *            操作内容
	 */
	public void addLocationTracks(LocationTracksEntity locationTracksEntity,
			String orgLocationType, Integer orgLocationinitType, Organization oldOrg,
			Integer operationType, String operationContent);

	/**
	 * 新经济组织特有的轨迹方法
	 * @param location
	 * @param orgLocationType
	 * @param orgLocationinitType
	 * @param oldOrg
	 * @param operationType
	 * @param operationContent
	 */
	public void addNewEconomicOrganizationsLocationTracks(People location, String orgLocationType,
			Integer orgLocationinitType, Organization oldOrg, Integer operationType,
			String operationContent);
}
