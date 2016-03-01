package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.BuildDataService;
import com.tianque.openLayersMap.service.HourseInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 二维地图 楼宇个性化方法的实现
 * 
 * @date 2013-3-18
 */
@Service("buildDataService")
public class BuildDataServiceImpl extends BaseService implements
		BuildDataService {

	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HourseInfoService hourseInfoService;

	@Override
	public BuildDataInfoVo getBuildDataInfoByBuildingid(Long hourseId) {
		if (hourseId == null) {
			throw new BusinessValidationException("请选中楼宇");
		}
		return buildDataTwoDimensionMapDao
				.getBuildDataInfoByHourseInfoId(hourseId);
	}

	@Override
	public PageInfo<BuildDataInfoVo> findUnBoundBuilddatasByStr(
			String queryStr, Integer pageNum, Integer pageSize,
			String sortField, String order, Long orgId) {
		if (queryStr == null) {
			throw new BusinessValidationException("查询字符串不能为空！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return buildDataTwoDimensionMapDao.findUnBoundBuilddatasByStr(queryStr,
				pageNum, pageSize, sortField, order, org.getOrgInternalCode());
	}

	@Override
	public BuildDataInfoVo getBuildDataInfoVoByCenterLonLat(String centerLon,
			String centerLat, String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon)
				|| !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}

		return buildDataTwoDimensionMapDao.getBuildDataInfoVoByCenterLonLat(
				centerLon, centerLat, gisType);
	}

	@Override
	public BuildDataInfoVo getBuildDataInfoVoByHourseInfoId(
			BuildDataInfoVo buildDataInfoVo, Long orgId) {
		if (!StringUtil.isStringAvaliable(buildDataInfoVo.getLon())
				|| !StringUtil.isStringAvaliable(buildDataInfoVo.getLat())) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}

		HourseInfo hourseInfo = hourseInfoService
				.getHourseInfoByFeatureId(buildDataInfoVo.getFeatureId());
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (hourseInfo == null) {// 如果建筑物为空，那么点击热区的同时新增建筑物
			hourseInfo = new HourseInfo();
			hourseInfo.setOrganization(org);
			hourseInfo.setLon(buildDataInfoVo.getLon());
			hourseInfo.setLat(buildDataInfoVo.getLat());
			hourseInfo.setFeatureId(buildDataInfoVo.getFeatureId());
			hourseInfo = hourseInfoService.addHourseInfo(hourseInfo,
					buildDataInfoVo.getGisType());
		}

		return buildDataTwoDimensionMapDao
				.getBuildDataInfoByHourseInfoId(hourseInfo.getId());
	}
}
