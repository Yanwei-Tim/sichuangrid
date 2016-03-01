package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.service.TownshipsUpStatisticService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 乡镇级别上统计
 * 
 * @date 2013-3-15
 */
public abstract class AbstractTownshipsUpStatisticService extends
		AbstractTownshipsKeyPlaceService implements TownshipsUpStatisticService {

	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected PropertyDictService propertyDictService;
	@Autowired
	protected Gis2DLayerService gis2DLayerService;

	/**
	 * 应用于图层和搜索统计的统计对象赋值
	 * 
	 * @param gis2dLayer
	 *            地图区域对象
	 * @param boundNum
	 *            统计绑定的数量
	 * @return 统计对象
	 */
	protected StatisticInfoVo setStatisticInfoVoProperty(Gis2DLayer gis2dLayer,
			Integer boundNum) {
		StatisticInfoVo satisticInfoVo = new StatisticInfoVo();
		satisticInfoVo.setBoundMapNum(boundNum);
		if (gis2dLayer != null) {
			satisticInfoVo.setLon(gis2dLayer.getCenterX());
			satisticInfoVo.setLat(gis2dLayer.getCenterY());
			satisticInfoVo.setPoints(gis2dLayer.getPoints());
			satisticInfoVo.setLon2(gis2dLayer.getCenterX2());
			satisticInfoVo.setLat2(gis2dLayer.getCenterY2());
			satisticInfoVo.setPoints2(gis2dLayer.getPoints2());
			if (gis2dLayer.getOrganization() != null
					&& gis2dLayer.getOrganization().getId() != null) {
				satisticInfoVo
						.setOrganization(organizationDubboService
								.getSimpleOrgById(gis2dLayer.getOrganization()
										.getId()));
			}
		}
		return satisticInfoVo;
	}

	protected StatisticInfoVo setStatisticInfoVoProperty(Gis2DLayer gis2dLayer,
			Integer sumNum, String typeName) {
		StatisticInfoVo satisticInfoVo = new StatisticInfoVo();
		satisticInfoVo.setSumNum(sumNum);
		satisticInfoVo.setTypeName(typeName);
		if (gis2dLayer != null) {
			satisticInfoVo.setLon(gis2dLayer.getCenterX());
			satisticInfoVo.setLat(gis2dLayer.getCenterY());
			satisticInfoVo.setPoints(gis2dLayer.getPoints());
			satisticInfoVo.setLon2(gis2dLayer.getCenterX2());
			satisticInfoVo.setLat2(gis2dLayer.getCenterY2());
			satisticInfoVo.setPoints2(gis2dLayer.getPoints2());
			if (gis2dLayer.getOrganization() != null) {
				satisticInfoVo
						.setOrganization(organizationDubboService
								.getSimpleOrgById(gis2dLayer.getOrganization()
										.getId()));
			}
		}
		return satisticInfoVo;
	}

	/**
	 * 应用于辖区分布大类统计的统计对象赋值
	 * 
	 * @param countNum
	 *            统计绑定的数量
	 * @param type
	 *            绑定、解除绑定、各子类型
	 * @param moduleName
	 *            用于事件状态区分
	 * @return
	 */
	protected StatisticInfoVo setStatisticInfoVoProperty(Integer countNum,
			String type, String moduleName) {
		StatisticInfoVo satisticInfoVo = new StatisticInfoVo();
		satisticInfoVo.setSumNum(countNum);
		satisticInfoVo.setTypeName(type);
		satisticInfoVo.setModuleName(moduleName);
		return satisticInfoVo;
	}

	/**
	 * 得到最大最小经度纬度的坐标点
	 * 
	 * @param orgId
	 * @param points
	 * @return
	 */
	protected Double[] getMaxAndMinLonLatArrys(Long orgId, String points) {
		if (orgId == null || points == null) {
			throw new BusinessValidationException("参数错误!");
		}
		Double[] maxAndMinLonLatArray = OpenLayersGetPoints
				.getMaxAndMinLonLat(points);
		return maxAndMinLonLatArray;
	}

	/**
	 * 数据拼装--应用于辖区分布的饼状图
	 * 
	 * @param orgId
	 * @param sum
	 * @param i
	 * @param importantPersonlData
	 * @param importantPersonlCount
	 */
	protected void dataAssembly(Long orgId, double sum, int i,
			Object[] importantPersonlData, double importantPersonlCount) {
		if (sum == 0) {
			importantPersonlData[1] = 0;
		} else {
			importantPersonlData[1] = Double
					.parseDouble(new java.text.DecimalFormat("#.00")
							.format(importantPersonlCount / sum * 100));
		}
		importantPersonlData[0] = getOrgArraysNamesByParentId(orgId)[i]
				+ "( "
				+ new java.text.DecimalFormat("#")
						.format(importantPersonlCount) + " )";
	}

	/**
	 * 根据父类Id得到所有下级的组织机构
	 * 
	 * @param orgId
	 * @return String[] 得到所有下级的组织机构
	 */
	protected String[] getOrgArraysByParentId(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		String[] orgCodes = new String[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			orgCodes[i] = organizations.get(i).getOrgInternalCode();
		}
		return orgCodes;
	}

	/**
	 * 根据父类Id得到所有下级的组织机构的名称
	 * 
	 * @param orgId
	 * @return String[] 得到所有下级的组织机构的名称
	 */
	protected String[] getOrgArraysNamesByParentId(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		String[] orgNames = new String[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			orgNames[i] = organizations.get(i).getOrgName();
		}
		return orgNames;
	}

	protected List<Gis2DLayer> getGis2dLayers(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数为空!");
		}
		List<Gis2DLayer> gis2DLayers = gis2DLayerService.findUndersByOrgId(
				orgId, null);
		return gis2DLayers;
	}

	protected List<Gis2DLayer> getGis2DLayers(Long orgId,
			ScreenCoordinateVo screenVo) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空!");
		}
		checkForPoint(screenVo);
		List<Gis2DLayer> gis2DLayers = gis2DLayerService
				.findUndersByOrgIdAndScreenVo(orgId, screenVo, null);
		return gis2DLayers;
	}

	@SuppressWarnings("unchecked")
	protected Set<String> getModuleKeys(String key) {
		Map<String, String> keyMap = (Map<String, String>) GisGlobalValueMap.KEY_MODULE_MAP
				.get(key);
		Set<String> keySet = null;
		if (keyMap != null) {
			keySet = keyMap.keySet();
		}
		return keySet;
	}

	public List<StatisticInfoVo> statisticInfoBySearchVoGroupByGis2DLayer(
			SearchInfoVo searchVo, Callback callback) {
		List<StatisticInfoVo> gridSatisticInfoList = new ArrayList<StatisticInfoVo>();
		List<Gis2DLayer> gis2DLayers = gis2DLayerService
				.findUndersByOrgIdAndScreenVo(searchVo.getOrgId(),
						searchVo.getScreenVo(), null);
		if (gis2DLayers == null) {
			return gridSatisticInfoList;
		}
		String gisType = searchVo.getGisType();
		for (Gis2DLayer gis2dLayer : gis2DLayers) {
			if (StringUtil.isStringAvaliable(gisType)) {
				if (GisType.M3D.equals(gisType)) {
					gis2dLayer.setPoints2(null);
				} else if (gisType != null) {
					gis2dLayer.setPoints(null);
				}
			}
			Integer boundNum = callback.call(gis2dLayer, searchVo);
			gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
					boundNum));
		}
		return gridSatisticInfoList;

	}

	public List<StatisticInfoVo> statisticInfoByOrgIdAndTypeName(
			SearchInfoVo searchVo, Callback callback) {
		List<StatisticInfoVo> gridSatisticInfoList = new ArrayList<StatisticInfoVo>();
		List<Gis2DLayer> gis2DLayers = getGis2dLayers(searchVo.getOrgId());
		for (int i = 0; i < gis2DLayers.size(); i++) {
			Gis2DLayer gis2dLayer = gis2DLayers.get(i);
			if (gis2dLayer != null && gis2dLayer.getId() != null) {
				Integer sumNnm = callback.call(gis2dLayer, searchVo);
				gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
						sumNnm, searchVo.getTypeName()));
			}
		}
		return gridSatisticInfoList;
	}

	/**
	 * 根据组织机构id判断是否是网格层级
	 * 
	 * @param orgId
	 * @return
	 */
	protected Boolean isGridByOrgId(Long orgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		boolean isGrid = false;
		if (organization != null && organization.getOrgLevel() != null
				&& organization.getOrgLevel().getId() != null) {
			PropertyDict dict = propertyDictService
					.getPropertyDictById(organization.getOrgLevel().getId());
			if (dict != null && dict.getInternalId() == OrganizationLevel.GRID) {
				isGrid = true;
			}
		}
		return isGrid;
	}
	// @Override
	// public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Integer statisticInfoByOrgIdAndPoints(Long orgId, String points) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Integer statisticInfoSumByOrgId(Long orgId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<Object[]> getStatisticPieChartInfo(Long orgId, String
	// typeName) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
