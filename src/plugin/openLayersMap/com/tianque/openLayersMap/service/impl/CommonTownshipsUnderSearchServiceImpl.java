package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.CommonTwoDimensionMapManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * 
 * @date 2013-3-18
 */
@Service("commonMapSearchService")
public class CommonTownshipsUnderSearchServiceImpl extends
		AbstractCommonTownshipsUnderSearchService<CommonEntityInfoVo> {
	@Autowired
	@Qualifier("commonTwoDimensionMapManageDao")
	private CommonTwoDimensionMapManageDao commonTwoDimensionMapManageDao;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	@Override
	public PageInfo<CommonEntityInfoVo> findPageInfoByOrgIdAndTypeName(
			Long orgId, String typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<CommonEntityInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		checkForPoint(screenCoordinateVo);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName(tableName);
		if (gisModuleVo == null || gisModuleVo.getId() == null) {
			throw new BusinessValidationException("系统错误");
		}
		GisFunction gisFunction = sysGisFunctionService
				.getGisFunctionByModuleIdAndFunctionType(gisModuleVo.getId(),
						functionType);

		return commonTwoDimensionMapManageDao
				.findCommonEntityInfoByGisModuleVoAndOrgCodeAndScreenCoordinate(
						gisFunction, organization.getOrgInternalCode(),
						gisModuleVo.getOrgFiled(), screenCoordinateVo,
						tableName, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<CommonEntityInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		checkForPoint(screenCoordinateVo);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName(tableName);
		if (gisModuleVo == null || gisModuleVo.getId() == null) {
			throw new BusinessValidationException("系统错误");
		}
		GisFunction gisFunction = sysGisFunctionService
				.getGisFunctionByModuleIdAndFunctionType(gisModuleVo.getId(),
						functionType);

		PageInfo<CommonEntityInfoVo> pageInfo = commonTwoDimensionMapManageDao
				.findTwoDimensionMapPageInfoByOrgCodeAndScreenCoordinateAndModuleTypeAndSearchValue(
						gisFunction, organization.getOrgInternalCode(),
						gisModuleVo.getOrgFiled(), screenCoordinateVo,
						tableName, searchValue, pageNum, pageSize, sidx, sord);
		return pageInfo;
	}

}
