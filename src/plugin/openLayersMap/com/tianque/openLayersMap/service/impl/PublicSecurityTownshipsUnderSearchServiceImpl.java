package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.publicSecurity.constant.PublicSecurityType;

/**
 * 
 * @功能：搜索:图层、精确搜索
 */
@Service("publicSecurityMapSearchService")
public class PublicSecurityTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<PublicSecurityInfoVo> {

	@Autowired
	private PublicSecurityTwoDimensionMapDao publicSecurityTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;

	@Override
	public PageInfo<PublicSecurityInfoVo> findPageInfoByOrgIdAndTypeName(
			Long orgId, String typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<PublicSecurityInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<PublicSecurityInfoVo> pageInfo = publicSecurityTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
						orgInternalCode, screenCoordinateVo, typeName, pageNum,
						pageSize, sidx, sord);
		setPublicSecurityType(pageInfo.getResult());
		return pageInfo;
	}

	private void setPublicSecurityType(List<PublicSecurityInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			PublicSecurityInfoVo publicSecurityInfoVo = list.get(i);
			if (publicSecurityInfoVo.getType() != null) {
				if (publicSecurityInfoVo.getType().equalsIgnoreCase(
						PublicSecurityType.SKYNET)) {
					publicSecurityInfoVo.setTypeName("天网");
				} else if (publicSecurityInfoVo.getType().equalsIgnoreCase(
						PublicSecurityType.BAYONET)) {
					publicSecurityInfoVo.setTypeName("卡口");
				} else
					publicSecurityInfoVo.setTypeName("抓拍系统");
			}
		}
	}

	@Override
	public PageInfo<PublicSecurityInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		if (pageSize > 1000)
			pageSize = 1000;

		String orgInternalCode = getOrgInternalCode(orgId);

		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(mainTableName);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);

		PageInfo<PublicSecurityInfoVo> pageInfo = publicSecurityTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord, gisTypeManageList);
		setPublicSecurityType(pageInfo.getResult());
		return pageInfo;
	}

}
