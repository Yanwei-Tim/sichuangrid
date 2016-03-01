package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.GpsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.GpsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 二维地图GPS查询、详情查看方法的实现
 */
@Service("gpsMapSearchService")
public class GpsTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<GpsInfoVo> {

	@Autowired
	private GpsTwoDimensionMapDao gpsTwoDimensionMapDao;
	@Autowired
	private PermissionService permissionService;

	@Override
	public PageInfo<GpsInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		/*
		 * PageInfo<GpsInfoVo> pageInfo = gpsTwoDimensionMapDao .
		 * findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName
		 * (null, screenCoordinateVo, typeName, pageNum, pageSize, sidx, sord);
		 */
		SearchInfoVo searchInfoVo = (screenCoordinateVo.getSearchInfoVo() == null) ? new SearchInfoVo()
				: screenCoordinateVo.getSearchInfoVo();
		searchInfoVo.setOrgId(orgId);
		PageInfo<GpsInfoVo> pageInfo = findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo(
				screenCoordinateVo, searchInfoVo, pageNum, pageSize, sidx, sord);

		setGpsInfoVoTypeName(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<GpsInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		/*
		 * PageInfo<GpsInfoVo> pageInfo = gpsTwoDimensionMapDao .
		 * findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue
		 * ( null, screenCoordinateVo, stringBuffer.toString(), pageNum,
		 * pageSize, sidx, sord);
		 */
		SearchInfoVo searchInfoVo = (screenCoordinateVo.getSearchInfoVo() == null) ? new SearchInfoVo()
				: screenCoordinateVo.getSearchInfoVo();
		searchInfoVo.setOrgId(orgId);
		searchInfoVo.setSearchValue(searchValue);
		return findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo(
				screenCoordinateVo, searchInfoVo, pageNum, pageSize, sidx, sord);
	}

	private PageInfo<GpsInfoVo> findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo(
			ScreenCoordinateVo screenCoordinateVo, SearchInfoVo searchInfoVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (searchInfoVo == null || searchInfoVo.getOrgId() == null) {
			throw new BusinessValidationException("请选择一个组织机构!");
		}
		checkForPoint(screenCoordinateVo);
		String searchValue = searchInfoVo.getSearchValue();
		StringBuffer stringBuffer = new StringBuffer("'" + searchValue + "'");
		Organization org = organizationDubboService
				.getSimpleOrgById(searchInfoVo.getOrgId());
		Organization organization = new Organization();
		organization.setOrgInternalCode(org.getOrgInternalCode());
		User user = new User();
		user.setOrganization(organization);
		user.setName(searchValue);
		List<User> userList = permissionService.findUsersBylockStatus("0",
				user, 1, 10000, sidx, sord).getResult();
		if (searchValue != null && !searchValue.trim().equals("")) {
			user.setName(null);
			user.setUserName(searchValue);
			userList.addAll(permissionService.findUsersBylockStatus("0", user,
					1, 10000, sidx, sord).getResult());
		}
		for (User domain : userList) {
			stringBuffer.append(",'" + domain.getUserName() + "'");
		}
		searchInfoVo.setSearchValue(stringBuffer.toString());
		PageInfo<GpsInfoVo> pageInfo = gpsTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo(
						screenCoordinateVo, searchInfoVo, pageNum, pageSize,
						sidx, sord);
		insertUserInfo(pageInfo, userList);
		return pageInfo;
	}

	@Override
	public PageInfo<GpsInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		/*
		 * if (orgId == null || typeName == null) { throw new
		 * BusinessValidationException("参数错误!"); } String orgInternalCode =
		 * getOrgInternalCode(orgId); PageInfo<GpsInfoVo> pageInfo =
		 * gpsTwoDimensionMapDao
		 * .findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
		 * orgInternalCode, typeName, pageNum, pageSize, sidx, sord);
		 * 
		 * return setGpsInfoVoTypeName(pageInfo);
		 */
		return null;
	}

	private void insertUserInfo(PageInfo<GpsInfoVo> gpsPageInfo,
			List<User> userList) {
		for (GpsInfoVo gpsInfoVo : gpsPageInfo.getResult()) {
			for (User user : userList) {
				if (user.getUserName().equals(gpsInfoVo.getUserName())) {
					Organization organization = organizationDubboService
							.getSimpleOrgById(user.getOrganization().getId());
					gpsInfoVo.setName(user.getName());
					gpsInfoVo.setTelephone(user.getMobile());
					gpsInfoVo.setOrgName(organization.getOrgName());
					break;
				}
			}
		}
	}

	private PageInfo<GpsInfoVo> setGpsInfoVoTypeName(
			PageInfo<GpsInfoVo> pageInfo) {

		// for (int i = 0; i < pageInfo.getResult().size(); i++) {//
		// 得到GpsInfoVo的typeName
		// GisTypeManage gisTypeManage = new GisTypeManage();
		// gisTypeManage.setKey(pageInfo.getResult().get(i).getType());
		// gisTypeManage =
		// sysGisTypeManageService.getGisTypeManagesByKey(gisTypeManage);
		// pageInfo.getResult().get(i).setTypeName(gisTypeManage.getName());
		// }
		return pageInfo;
	}

}
