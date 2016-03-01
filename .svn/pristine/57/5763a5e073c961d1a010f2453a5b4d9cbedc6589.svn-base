package com.tianque.openLayersMap.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.HourseInfoDao;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.service.HourseInfoService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.ServiceImplModeType;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 二维地图 房屋个性化方法（新增，删除。查看）的实现
 * 
 * @author zhanghuafei
 * 
 */
@Transactional
@Service("hourseInfoService")
public class HourseInfoServiceImpl implements HourseInfoService {

	@Autowired
	private HourseInfoDao hourseInfoDao;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private BuilddatasService builddatasService;
	@Autowired
	private Map<String, MapBindingManageService<BuildDataInfoVo>> mapBindingManageService;

	@Override
	public HourseInfo addHourseInfo(HourseInfo hourseInfo, String gisType) {
		validate(hourseInfo);
		Organization organization = organizationDubboService
				.getSimpleOrgById(hourseInfo.getOrganization().getId());

		hourseInfo.setOrgInternalCode(organization.getOrgInternalCode());
		if (hourseInfo.getIsTransformat()) {
			if (GisType.M3D.equals(gisType)) {
				String[] lonlat2 = GisTransformatUtil.transformat2DPoint(
						hourseInfo.getLon(), hourseInfo.getLat());
				hourseInfo.setLon2(lonlat2[0]);
				hourseInfo.setLat2(lonlat2[1]);
			} else {
				String lon2 = hourseInfo.getLon();
				String lat2 = hourseInfo.getLat();
				hourseInfo.setLon2(lon2);
				hourseInfo.setLat2(lat2);
				String[] lonlat3 = GisTransformatUtil.transformat25DPoint(lon2,
						lat2);
				hourseInfo.setLon(lonlat3[0]);
				hourseInfo.setLat(lonlat3[1]);
			}
		} else {
			hourseInfo.setLon2(hourseInfo.getLon());
			hourseInfo.setLat2(hourseInfo.getLat());
		}
		HourseInfo result = null;
		// 如果热区id不为空时，则根据热区id查询房屋
		if (hourseInfo.getFeatureId() != null
				&& hourseInfo.getFeatureId() != "") {
			HourseInfo existHiuseInfo = getHourseInfoByFeatureId(hourseInfo
					.getFeatureId());
			if (existHiuseInfo != null) {
				hourseInfo.setId(existHiuseInfo.getId());
				result = hourseInfoDao.updateHourseInfo(hourseInfo);
				if (result != null) {
					result.setOrganization(organization);
				}
				return result;
			}
		}
		result = hourseInfoDao.addHourseInfo(hourseInfo);
		if (result != null) {
			result.setOrganization(organization);
		}
		return result;
	}

	@Override
	public void deleteHourseInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		hourseInfoDao.deleteHourseInfoById(id);
		/** 通过builId查询楼宇信息 */
		List<Builddatas> builddatasList = builddatasService
				.findBuildDatasByBuildId(id.toString());
		if (builddatasList != null && builddatasList.size() != 0) {
			// throw new BusinessValidationException("楼宇信息获取失败!");
			mapBindingManageService.get(ServiceImplModeType.BUILDDATA_BING)
					.unBoundTwoDimensionMap(builddatasList.get(0).getId(),
							null,
							builddatasList.get(0).getOrganization().getId());
		}

	}

	@Override
	public HourseInfo getHourseInfoAndBoundObjectNumById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("房屋id不能为空!");
		}
		return hourseInfoDao.getHourseInfoAndBoundObjectNumById(id);
	}

	/**
	 * 房屋信息的校验
	 * 
	 * @param hourseInfo
	 */
	private void validate(HourseInfo hourseInfo) {
		if (validateHelper.emptyString(hourseInfo.getLon())) {
			throw new BusinessValidationException("经度不能为空");
		}
		if (validateHelper.emptyString(hourseInfo.getLat())) {
			throw new BusinessValidationException("纬度不能为空");
		}
		if (hourseInfo.getOrganization() == null
				|| hourseInfo.getOrganization().getId() == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
	}

	@Override
	public HourseInfo getHourseInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		return hourseInfoDao.getHourseInfoById(id);
	}

	@Override
	public List<HourseInfo> findUnboundHouseInfoByOrganizationId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("id不能为空");
		}
		return hourseInfoDao.findUnboundHouseInfoByOrganizationId(orgId);
	}

	@Override
	public HourseInfo getHourseInfoByFeatureId(String featureId) {
		if (featureId == null) {
			throw new BusinessValidationException("featureId不能为空");
		}
		return hourseInfoDao.getHourseInfoByFeatureId(featureId);
	}

}
