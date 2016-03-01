package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.openLayersMap.dao.GpsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.GpsInfoVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 二维地图 GPS周边搜索方法的实现
 * 
 */
@Service("gpsMapCircumSearchService")
public class GpsCircumSearchServiceImpl extends
		AbstractCircumSearchService<GpsInfoVo> {

	@Autowired
	private GpsTwoDimensionMapDao gpsTwoDimensionMapDao;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Override
	protected PageInfo<GpsInfoVo> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		String searchValue = circumInfoVo.getQueryStr();
		StringBuffer stringBuffer = new StringBuffer("'" + searchValue + "'");
		User user = new User();
		Organization organization = new Organization();
		organization.setOrgInternalCode(ThreadVariable.getUser()
				.getOrganization().getOrgInternalCode());
		user.setOrganization(organization);
		user.setName(searchValue);
		List<User> userList = permissionService.findUsersBylockStatus("0",
				user, 1, 10000, sidx, sord).getResult();
		if (searchValue != null && !searchValue.trim().equals("")) {
			user.setName(null);
			user.setUserName(searchValue);
			userList.addAll(permissionService.findUsersBylockStatus("0",
					user, 1, 10000, sidx, sord).getResult());
		}
		for (User domain : userList) {
			stringBuffer.append(",'" + domain.getUserName() + "'");
		}
		circumInfoVo.setQueryStr(stringBuffer.toString());

		PageInfo<GpsInfoVo> pageInfo = gpsTwoDimensionMapDao
				.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
						circumInfoVo, pageNum, pageSize, sidx, sord);
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName("deviceInformation");
		GisFunction gisFunction = sysGisFunctionService
				.getGisFunctionByModuleIdAndFunctionType(gisModuleVo.getId(),
						circumInfoVo.getFunctionType());
		for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到keyPlaceInfoVo的typeName
			pageInfo.getResult().get(i).setViewUrl(gisFunction.getDetailsUrl());
		}
		insertUserInfo(pageInfo, userList);
		return pageInfo;
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
}