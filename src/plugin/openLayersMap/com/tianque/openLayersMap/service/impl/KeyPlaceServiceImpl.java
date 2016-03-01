package com.tianque.openLayersMap.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 二维地图 重点场所个性化方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPlaceServiceMap")
public class KeyPlaceServiceImpl extends BaseService implements KeyPlaceService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyPlaceTwoDimensionMapDao keyPlaceTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;

	@Override
	public PageInfo<KeyPlaceInfoVo> findUnBoundKeyPlaceByOrgIdAndType(
			Long orgId, String type, Integer page, Integer rows, String sidx,
			String sord, String queryStr) {
		if (orgId == null || type == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return keyPlaceTwoDimensionMapDao
				.findUnBoundKeyPlaceByOrgInternalCodeAndType(
						org.getOrgInternalCode(), type, page, rows, sidx, sord,
						queryStr);
	}

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName) {
		if (null == orgInternalCode || null == typeName) {
			throw new BusinessValidationException("参数错误!");
		}
		return keyPlaceTwoDimensionMapDao
				.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, typeName);
	}

	@Override
	public Map<String, Integer> countKeyPlacesByCenterLonLat(String centerLon,
			String centerLat, String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon)
				|| !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		return keyPlaceTwoDimensionMapDao.countKeyPlacesByCenterLonLat(
				centerLon, centerLat, gisType);
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findBoundedKeyPalcesByOrgIdAndTypeName(
			Long orgId, Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		PageInfo<KeyPlaceInfoVo> pageInfo = keyPlaceTwoDimensionMapDao
				.findBoundKeyPlaceByOrgInternalCodeAndTypeName(
						organization.getOrgInternalCode(), buildDataId,
						typeName, pageNum, pageSize, sidx, sord);

		for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到keyPlaceInfoVo的typeName
			GisTypeManage gisTypeManage = new GisTypeManage();
			gisTypeManage.setKey(pageInfo.getResult().get(i).getType());
			gisTypeManage = sysGisTypeManageService
					.getGisTypeManagesByKey(gisTypeManage);
			pageInfo.getResult().get(i).setTypeName(gisTypeManage.getName());
		}
		return pageInfo;
	}

	@Override
	public Map<String, Integer> countKeyPlacesByHourseId(Long hourseId) {
		if (hourseId == null) {
			throw new BusinessValidationException("建筑物不能为空");
		}

		return keyPlaceTwoDimensionMapDao.countKeyPlacesByHourseId(hourseId);
	}

}
