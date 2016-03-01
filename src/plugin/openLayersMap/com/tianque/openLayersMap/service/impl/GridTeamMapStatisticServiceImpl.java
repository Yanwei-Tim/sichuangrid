package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.primaryOrg.constant.GridTeamConstant;
import com.tianque.baseInfo.primaryOrg.dao.GridTeamDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.GridTeamMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 *  * 二维地图  网格员队伍管理统计方法的实现
 * @author songzhiqiang
 *
 */
@Service("gridTeamMapStatisticService")
public class GridTeamMapStatisticServiceImpl extends
AbstractTownshipsUpStatisticService {
	@Autowired
	private GridTeamDao gridTeamDao;
	@Autowired
	private GridTeamMapDao gridTeamMapDao;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
			@Override
			public Integer call(Gis2DLayer gis2dLayer,
					SearchInfoVo searchVo) {
				return gridTeamMapDao
						.get2DMapInfoByRigthSerachType(gis2dLayer.getOrgInternalCode());
			}
		});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return gridTeamMapDao.get2DMapInfoBySerachType(gis2dLayer.getOrgInternalCode(),
										searchVo.getSearchValue());
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		String orgCode  = organization.getOrgInternalCode();
		//总数 
		int sum = gridTeamDao.countGridTeam(orgCode).intValue();
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.GRID_POSITIONTYPE,
						GridTeamConstant.GRID_TEAM_POSITION_TYPE_FULL);
		//专职数
		int fullTimeSum = gridTeamDao.countGridTeamByOrgAndPositionType(orgCode, propertyDict.getId()).intValue();
		propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.GRID_POSITIONTYPE,
						GridTeamConstant.GRID_TEAM_POSITION_TYPE_PART);
		//兼职数
		int partTimeSum = gridTeamDao.countGridTeamByOrgAndPositionType(orgCode, propertyDict.getId()).intValue();
		StatisticInfoVo infoSum = null;
		GisTypeManage sumTypeManager = null;
		
		for(int i = 0;i < 3;i++){
			infoSum = new StatisticInfoVo();
			sumTypeManager = new GisTypeManage();
			if(i == 0){
				sumTypeManager.setTableName("gridTeam");
				sumTypeManager.setName("网格员");
				infoSum.setGisTypeManage(sumTypeManager);
				infoSum.setSumNum(sum);
			}else if( i == 1){
				sumTypeManager.setTableName("fullTime");
				sumTypeManager.setName("专职");
				infoSum.setGisTypeManage(sumTypeManager);
				infoSum.setSumNum(fullTimeSum);
			}else{
				sumTypeManager.setTableName("partTime");
				sumTypeManager.setName("兼职");
				infoSum.setGisTypeManage(sumTypeManager);
				infoSum.setSumNum(partTimeSum);
			}
			satisticInfoVoList.add(infoSum);
		}
		return satisticInfoVoList;
	}

	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		return null;
	}

	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		return null;
	}

	@Override
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long id) {
		return null;
	}

	@Override
	public Integer statisticInfoSumByOrgId(Long orgId) {
		return null;
	}

	@Override
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName) {
		return null;
	}

	

}
