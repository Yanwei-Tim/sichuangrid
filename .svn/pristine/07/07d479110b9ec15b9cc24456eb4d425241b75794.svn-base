package com.tianque.openLayersMap.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.shard.util.ShardConversion;

/**
 * 二维地图 人口信息查询、详情查看方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("personMapSearchService")
public class PersonTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<PersonInfoVo> {

	@Autowired
	private PersonTwoDimensionMapDao personTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<PersonInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
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
		gisTypeManage.setInnerKey(GisGlobalValueMap.PERSONAL_MODE);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);

		PageInfo<PersonInfoVo> pageInfo = personTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord, "", gisTypeManageList,
						shardConversion.getShardCode(orgId));
		if (pageInfo != null) {
			setDisplayNameAndOrgCode(pageInfo.getResult(), orgInternalCode);
		}
		if (pageSize < 1000) {
			if (pageInfo != null && pageInfo.getResult() != null) {
				for (int i = 0; i < pageInfo.getResult().size(); i++) {
					if ("境外人员".equals(pageInfo.getResult().get(i)
							.getPersonTypeName())) {// 如果境外人员则显示的姓名
						pageInfo.getResult()
								.get(i)
								.setName(
										pageInfo.getResult().get(i)
												.getEnglishName());
					}
				}
			}
		}
		return pageInfo;

	}

	@Override
	public PageInfo<PersonInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("网格id不能为空或者类型名称不能为空");
		}
		String orgInternalCode = getOrgInternalCode(orgId);
		String personTypeName = GisGlobalValueMap.keyPersonal.get(typeName);
		PageInfo<PersonInfoVo> pageInfo = (PageInfo<PersonInfoVo>) personTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, typeName, pageNum, pageSize, sidx,
						sord, personTypeName,
						shardConversion.getShardCode(orgId));
		setDisplayNameAndOrgCode(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}

	private void setDisplayNameAndOrgCode(List<PersonInfoVo> list,
			String orgInternalCode) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (Iterator<PersonInfoVo> it = list.iterator(); it.hasNext();) {
			PersonInfoVo bean = it.next();
			PropertyDict dict = null;
			if (bean.getGenderId() != null
					&& bean.getGenderId().getId() != null) {
				dict = propertyDictService.getPropertyDictById(bean
						.getGenderId().getId());
			}
			PropertyDict dict2 = null;
			if (bean.getCertificateTypeId() != null
					&& bean.getCertificateTypeId().getId() != null) {
				dict2 = propertyDictService.getPropertyDictById(bean
						.getCertificateTypeId().getId());
			}
			if (dict != null) {
				bean.setGender(dict.getDisplayName());
				bean.setGenderId(dict);
			}
			if (dict2 != null) {
				bean.setCertificateType(dict2.getDisplayName());
				bean.setCertificateTypeId(dict2);
			}
			if (!"境外人员".equals(bean.getPersonTypeName())) {
				bean.setCertificateType("身份证");
			}
			bean.setOrgInternalCode(orgInternalCode);
		}
	}

	@Override
	public PageInfo<PersonInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("网格id不能为空或者类型名称不能为空");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		String personTypeName = GisGlobalValueMap.keyPersonal.get(typeName);
		PageInfo<PersonInfoVo> pageInfo = personTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
						orgInternalCode, screenCoordinateVo, typeName,
						personTypeName, pageNum, pageSize, sidx, sord,
						shardConversion.getShardCode(orgId));
		setDisplayNameAndOrgCode(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}
}
