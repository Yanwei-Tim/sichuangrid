package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.primaryOrg.constant.GridTeamConstant;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.GridTeamMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.GirdTeamVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

/**
 *  * 二维地图  网格员队伍管理统计方法的实现
 * @author songzhiqiang
 *
 */
@Service("gridTeamMapSearchService")
public class GridTeamMapSearchServiceImpl extends
AbstractTownshipsUnderSearchService<GirdTeamVo> {
	@Autowired
	private GridTeamMapDao gridTeamMapDao;
	@Autowired
	private Gis2DLayerService gis2DLayerService;

	@Override
	public PageInfo<GirdTeamVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (null == orgId || null == typeName) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<GirdTeamVo> pageInfo = new PageInfo<GirdTeamVo>();
		PropertyDict propertyDict = null;
		if(typeName.equals(GisGlobalValueMap.FULLTIME)){//专职
			propertyDict =  propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.GRID_POSITIONTYPE,
							GridTeamConstant.GRID_TEAM_POSITION_TYPE_FULL);
		}else{//兼职
			propertyDict =  propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.GRID_POSITIONTYPE,
							GridTeamConstant.GRID_TEAM_POSITION_TYPE_PART);
		}
		pageInfo = gridTeamMapDao.getGridTeamInfoByType(org.getOrgInternalCode(), propertyDict.getId(), pageNum, pageSize);
		List<GirdTeamVo> listVo = pageInfo.getResult();
		List<Long> list = new ArrayList<Long>();
		Double[] doublePoint = null;
		for(GirdTeamVo girdTeam : listVo){
			orgId = girdTeam.getOrganization().getId();
			girdTeam.setOrgFullName(ControllerHelper.getOrganizationRelativeName(orgId,
					organizationDubboService));
			girdTeam.setMemeberName(girdTeam.getMemeberName()+"("+propertyDict.getDisplayName()+")");
			if(list.contains(orgId)){
				doublePoint = optimizationMethod(orgId, 1);
				if(null != doublePoint){
					girdTeam.setLon(doublePoint[0]+"");
					girdTeam.setLat(doublePoint[1]+"");
				}
				continue;
			}
			list.add(orgId);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<GirdTeamVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<GirdTeamVo> pageInfo = new PageInfo<GirdTeamVo>();
		pageInfo = gridTeamMapDao.getGridTeamInfoByType(org.getOrgInternalCode(), null, pageNum, pageSize);
		List<GirdTeamVo> listVo = pageInfo.getResult();
		List<Long> list = new ArrayList<Long>();
		Double[] doublePoint = null;
		//没有处理字典项没找到情况
		for(GirdTeamVo girdTeam : listVo){
			orgId = girdTeam.getOrganization().getId();
			girdTeam.setOrgFullName(ControllerHelper.getOrganizationRelativeName(orgId,
					organizationDubboService));
			girdTeam.setMemeberName(girdTeam.getMemeberName()+"("+propertyDictService.getPropertyDictById(girdTeam.getPositionType().getId()).getDisplayName()+")");
			if(list.contains(orgId)){
				doublePoint = optimizationMethod(orgId, 1);
				if(null != doublePoint){
					girdTeam.setLon(doublePoint[0]+"");
					girdTeam.setLat(doublePoint[1]+"");
				}
				continue;
			}
			list.add(orgId);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<GirdTeamVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<GirdTeamVo> pageInfo = new PageInfo<GirdTeamVo>();
		pageInfo = gridTeamMapDao.getGridTeamInfoByType(org.getOrgInternalCode(), null, pageNum, pageSize);
		List<GirdTeamVo> listVo = pageInfo.getResult();
		List<Long> list = new ArrayList<Long>();
		Double[] doublePoint = null;
		//没有处理字典项没找到情况
		for(GirdTeamVo girdTeam : listVo){
			orgId = girdTeam.getOrganization().getId();
			girdTeam.setOrgFullName(ControllerHelper.getOrganizationRelativeName(orgId,
					organizationDubboService));
			girdTeam.setMemeberName(girdTeam.getMemeberName()+"("+propertyDictService.getPropertyDictById(girdTeam.getPositionType().getId()).getDisplayName()+")");
			if(list.contains(orgId)){
				doublePoint = optimizationMethod(orgId, 1);
				if(null != doublePoint){
					girdTeam.setLon(doublePoint[0]+"");
					girdTeam.setLat(doublePoint[1]+"");
				}
				continue;
			}
			list.add(orgId);
		}
		return pageInfo;
	}
	
	/**
	 * 判断坐标是否在地图区域内
	 * @return
	 */
	private static final boolean contains(Double minX,Double minY,Double maxX,Double maxY,String points,Double[] point){
		boolean isContains = false;
		Double x = point[0];
		Double y = point[1];
		if (x < minX || x > maxX || y < minY || y > maxY){
			return isContains;
		}
		points=points.replace(" ", ",");
		String [] ps=points.split(",");
		int n=ps.length/2;
		double[] xarr=new double[n];
		double[] yarr=new double[n];
		 for(int i=0;i<n;i++){
			 xarr[i]=Double.valueOf(ps[i*2]).doubleValue();
			 yarr[i]=Double.valueOf(ps[i*2+1]).doubleValue();
		 }
		int i, j = 0;
		for (i = 0, j = n - 1; i < n; j = i++) {
			if (((yarr[i] > y) != (yarr[j] > y))
					&& (x < (xarr[j] - xarr[i]) * (y - yarr[i])
							/ (yarr[j] - yarr[i]) + xarr[i]))
				isContains = !isContains;
		}
		return isContains;
	}
	
	/**
	 * 随机生存中心点
	 * @return
	 */
	private static final Double[] getPoint(Double minX,Double maxX,Double minY,Double maxY){
		Random random = new Random();
		double x = random.nextDouble()*(maxX - minX) + minX;
		double y = random.nextDouble()*(maxY - minY) + minY;
		Double[] returnVal = {x,y};
		return returnVal;
	}
	
	/**
	 * 根据当前数据生存多少地图标注点
	 * @param size
	 * @return
	 */
	private Double[] getLatLonByRandom(int size,Double minX,Double maxX,Double minY,Double maxY,String points){
		while(true){
			Double[] point = getPoint(minX,maxX,minY,maxY);
			if(contains(minX,minY,maxX,maxY,points,point)){
				return point;
			}
		}
	}
	
	/**
	 * 简化获得随机地图标注点方法
	 * @return
	 */
	private final Double[] optimizationMethod(Long orgId,int size){
		Gis2DLayer gis2DLayer = gis2DLayerService.getByOrgId(orgId, "2D");
		if(null == gis2DLayer){
			return null;
		}
		String points = gis2DLayer.getPoints2();
		points = points.substring(9,points.length()-2);
		return getLatLonByRandom(size, gis2DLayer.getMinLon2(), gis2DLayer.getMaxLon2(), gis2DLayer.getMinLat2(), gis2DLayer.getMaxLat2(),points);
	}

}
