package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.HourseInfoDao;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 二维地图 房屋查询方法的实现
 * 
 * @author zhanghuafei
 * 
 */
@Transactional
@Service("hourseInfoMapSearchService")
public class HourseInfoTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<HourseInfo> {

	@Autowired
	private HourseInfoDao hourseInfoDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PageInfo<HourseInfo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<HourseInfo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<HourseInfo> infos = hourseInfoDao
				.findTwoDimensionMapHoursePageInfoByOrgCodeAndScreenCoordinateVo(
						orgInternalCode, screenCoordinateVo, pageNum, pageSize,
						sidx, sord);
		for (HourseInfo info : infos.getResult()) {
			if (null != info.getTypeId() && !"".equals(info.getTypeId())) {
				int internalId = propertyDictService.getPropertyDictById(
						Long.valueOf(info.getTypeId())).getInternalId();
				info.getBuildDataInfoVo().getType()
						.setId(Long.valueOf(internalId));
				info.setTypeId(internalId + "");
			}
		}
		return infos;
	}

	@Override
	public PageInfo<HourseInfo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		// TODO Auto-generated method stub
		return null;
	}
}
