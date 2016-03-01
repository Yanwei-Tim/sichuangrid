package com.tianque.openLayersMap.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.dao.Gis2DLayerDao;
import com.tianque.openLayersMap.datatransfer.dataconvert.Gis2DLayerImportThread;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;

/**
 * 地图区域管理service实现类
 */
@Transactional
@Service("gis2DLayerService")
public class Gis2DLayerServiceImpl extends BaseService implements
		Gis2DLayerService {
	@Autowired
	private Gis2DLayerDao gis2DLayerDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;

	private final static String SET_MODE = "set";
	private final static String GET_MODE = "get";
	private final static String DEL_MODE = "remove";

	@Override
	public Gis2DLayer addGis2DLayer(Gis2DLayer gis2DLayer) {
		if (gis2DLayer.getOrganization() == null
				|| gis2DLayer.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选中一个组织机构");
		}
		if (gis2DLayer.getPoints() == null) {
			throw new BusinessValidationException("所画区域集合为空");
		}
		setMinMaxLonLat(gis2DLayer);
		setParamForOrg(gis2DLayer);
		gis2DLayer = gis2DLayerDao.addGis2DLayer(gis2DLayer);
		return gis2DLayer;
	}

	@Override
	public Gis2DLayer saveGis2DLayer(Gis2DLayer gis2DLayer) {
		if (gis2DLayer == null || gis2DLayer.getPoints() == null) {
			throw new BusinessValidationException("所画区域集合为空");
		}
		return gis2DLayerDao.addGis2DLayer(gis2DLayer);
	}

	@Override
	public Gis2DLayer updateGis2DLayer(Gis2DLayer gis2DLayer) {
		if (gis2DLayer == null || gis2DLayer.getId() == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		if (gis2DLayer.getOrganization() == null
				|| gis2DLayer.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选中一个组织机构");
		}
		if (gis2DLayer.getPoints() == null) {
			throw new BusinessValidationException("所画区域集合为空");
		}
		setMinMaxLonLat(gis2DLayer);
		setParamForOrg(gis2DLayer);
		Gis2DLayer existLayer = gis2DLayerDao.getByOrgId(gis2DLayer
				.getOrganization().getId(), null);
		if (existLayer == null || existLayer.getId() == null) {
			return gis2DLayerDao.addGis2DLayer(gis2DLayer);
		} else {
			gis2DLayer.setId(existLayer.getId());
			return gis2DLayerDao.updateGis2DLayer(gis2DLayer);
		}
	}

	@Override
	public Gis2DLayer updatePoint(Gis2DLayer gis2DLayer) {
		if (gis2DLayer == null || gis2DLayer.getId() == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		try {
			if (gis2DLayer.getMaxLon() == null
					|| gis2DLayer.getMaxLon2() == null) {
				setMinMaxLonLat(gis2DLayer);
			}
			return gis2DLayerDao.updateGis2DLayer(gis2DLayer);
		} catch (Exception e) {
			logger.error("updateLonlat报错：", e);
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构为空!");
		}
		gis2DLayerDao.deleteByOrgId(orgId);

	}

	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id为空!");
		}
		gis2DLayerDao.deleteById(id);
	}

	@Override
	public void deleteUndersByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.COUNTRY) {// 如果组织机构层级为中国，删除数据
			OrganizationVo orgVo = new OrganizationVo();
			orgVo.setOrgInternalCode(org.getOrgInternalCode());
			List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
			for (int i = 0; i < orgIdList.size(); i++) {
				deleteByOrgId(orgIdList.get(i));
			}
		}
	}

	@Override
	public Gis2DLayer getByOrgDepartmentNo(String orgDepartNo, String gisType) {
		if (orgDepartNo == null) {
			throw new BusinessValidationException("请选择一个组织机构");
		}
		Organization org = organizationDubboService
				.getOrgByDepartmentNo(orgDepartNo);
		if (org != null) {
			return getByOrgId(org.getId(), gisType);
		}
		return null;
	}

	@Override
	public Gis2DLayer getByOrgId(Long orgId, String gisType) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构");
		}
		return gis2DLayerDao.getByOrgId(orgId, gisType);
	}

	@Override
	public Gis2DLayer getById(Long id, String gisType) {
		if (id == null) {
			throw new BusinessValidationException("请选择一个记录");
		}
		return gis2DLayerDao.getById(id, gisType);
	}

	@Override
	public PageInfo<Gis2DLayer> findForPageBySearchVo(SearchInfoVo searchVo,
			Integer page, Integer rows, String sidx, String sord) {
		if (searchVo == null) {
			throw new BusinessValidationException("参数错误");
		}
		List<Organization> orgList = null;
		if(searchVo.getOrgLevelId()!=null){
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgLevelId(searchVo.getOrgLevelId());
			organizationVo.setOrgInternalCode(searchVo.getOrgInternalCode());
			orgList = organizationDubboService.findOrgsBySearchVo(organizationVo);
			searchVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgList));
		}
		PageInfo<Gis2DLayer> pageInfo = gis2DLayerDao.findForPageBySearchVo(searchVo, page, rows, sidx,
				sord);
		if(orgList!=null){
			for (int i=0;i<pageInfo.getResult().size();i++) {
				for (Organization org : orgList) {
					if(org.getId().equals(pageInfo.getResult().get(i).getOrganization().getId())){
						pageInfo.getResult().get(i).setOrgName(org.getOrgName());
						break;
					}
				}
			}
		}
		return pageInfo;
	}

	@Override
	public Integer countByOrgCodeAndOrgLevel(String orgCode, Long orgLevelId) {
		if (orgCode == null || orgLevelId == null) {
			throw new BusinessValidationException("参数错误");
		}
		SearchInfoVo searchVo = new SearchInfoVo();
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setOrgInternalCode(orgCode);
		organizationVo.setOrgLevelId(orgLevelId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		searchVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgIdList));
		return gis2DLayerDao.countByOrgCodeAndOrgLevel(searchVo);
	}

	@Override
	public List<Gis2DLayer> findUndersByOrgIdAndScreenVo(Long orgId,
			ScreenCoordinateVo screenVo, String gisType) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误!");
		}
		SearchInfoVo searchVo = new SearchInfoVo();
		searchVo.setScreenVo(screenVo);
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(orgId);
		List<Long> orgList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
		if(orgList==null || orgList.size()==0){
			return new ArrayList<Gis2DLayer>();
		}
		searchVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgList));
		return gis2DLayerDao.findUndersBySearchVo(searchVo,gisType);
	}

	@Override
	public Gis2DLayer getByCenterPointAndZoom(Gis2DLayer domain, String gisType) {
		if (domain == null || domain.getZoom() == null
				|| domain.getCenterX() == null || domain.getCenterY() == null) {
			throw new BusinessValidationException("参数无效!");
		}
		List<Gis2DLayer> gis2DLayerList = gis2DLayerDao
				.findByCenterPointAndZoom(domain, gisType);
		if (gis2DLayerList.size() > 0) {
			Gis2DLayer result = getGis2DLayerByMinDistance(domain,
					gis2DLayerList);
			Organization org = domain.getOrganization();
			Long newOrgId = result.getOrganization().getId();
			if (newOrgId != null
					&& !newOrgId.equals((org == null) ? null : org.getId())) {
				result.setOrganization(organizationDubboService
						.getFullOrgById(newOrgId));
			}
			return result;
		}
		return null;
	}

	@Override
	public List<Gis2DLayer> findUndersByOrgId(Long orgId, String gisType) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误!");
		}
		SearchInfoVo searchVo = new SearchInfoVo();
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(orgId);
		List<Long> orgList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
		if(orgList==null || orgList.size()==0){
			return new ArrayList<Gis2DLayer>();
		}
		searchVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgList));
		return gis2DLayerDao.findUndersBySearchVo(searchVo,gisType);
	}

	@Override
	public List<Gis2DLayer> findByOrgCode(String orgCode, String gisType) {
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构编号为空");
		}
		return gis2DLayerDao.findByOrgCode(orgCode, gisType);
	}

	@Override
	public Long importGis2DLayer(String gisType, File upload,
			String uploadFileName) {
		try {
			Gis2DLayerImportThread gisLayerImportThread = new Gis2DLayerImportThread(
					ThreadVariable.getSession(), gisType, upload,
					uploadFileName);
			ThreadPool.getInstance().execute(gisLayerImportThread);
			return gisLayerImportThread.getTicketId();
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	/**
	 * 获得与中心点距离最短的Gis2DLayer对象
	 * 
	 * @param centerX
	 * @param centerY
	 * @param gis2DLayerList
	 * @return
	 */
	private Gis2DLayer getGis2DLayerByMinDistance(Gis2DLayer parameters,
			List<Gis2DLayer> gis2DLayerList) {
		Gis2DLayer gis2dLayerMin = new Gis2DLayer();
		double centerX = Double.parseDouble(parameters.getCenterX());
		double centerY = Double.parseDouble(parameters.getCenterY());
		String gisType = parameters.getGisType();
		Gis2DLayer gis2dLayer;
		Point point = getLonlat(gisType, gis2DLayerList.get(0));
		double tempNumX = point.getX() - centerX;
		double tempNumY = point.getY() - centerY;
		double tempDistance = Math.sqrt(tempNumX * tempNumX + tempNumY
				* tempNumY);
		for (int i = 0; i < gis2DLayerList.size(); i++) {
			gis2dLayer = gis2DLayerList.get(i);
			point = getLonlat(gisType, gis2dLayer);
			double numX = point.getX() - centerX;// 求获取的区域中心点X轴与当前地图中心点X轴的差值
			double numY = point.getY() - centerY;// 求获取的区域中心点Y轴与当前地图中心点Y轴的差值
			double distance = Math.sqrt(numX * numX + numY * numY);
			if (distance <= tempDistance) {
				tempDistance = distance;
				gis2dLayerMin = gis2dLayer;// 获取区域中心点距离最小的区域对象
			}
		}
		return gis2dLayerMin;
	}

	private Point getLonlat(String gisType, Gis2DLayer domain) {
		String lon = domain.getCenterX();
		String lat = domain.getCenterY();
		if (!gisType.equals(GisType.M3D) && domain.getCenterX2() != null) {
			lon = domain.getCenterX2();
			lat = domain.getCenterY2();
		}
		return new Point(lon, lat);
	}

	/**
	 * 给坐标点赋值
	 * 
	 * @param gis2DLayer
	 */
	private void setMinMaxLonLat(Gis2DLayer gis2DLayer) {
		String centerX = gis2DLayer.getCenterX();
		String centerY = gis2DLayer.getCenterY();
		String points = gis2DLayer.getPoints();
		if (GisType.M2D.equals(gis2DLayer.getGisType())) {
			gis2DLayer.setCenterX2(centerX);
			gis2DLayer.setCenterY2(centerY);
			gis2DLayer.setPoints2(points);
		}
		if (GisType.M2D.equals(gis2DLayer.getGisType())) {
			GisTransformatUtil.autoFillGis2DLayerBy3D(gis2DLayer);
		} else if (GisType.M3D.equals(gis2DLayer.getGisType())) {
			GisTransformatUtil.autoFillGis2DLayerBy2D(gis2DLayer);
		}
		Double[] pointsArrys = new Double[4];
		if (gis2DLayer.getPoints() != null) {
			pointsArrys = OpenLayersGetPoints.getMaxAndMinLonLat(gis2DLayer
					.getPoints());
			gis2DLayer.setMinLon(pointsArrys[2]);
			gis2DLayer.setMaxLon(pointsArrys[0]);
			gis2DLayer.setMinLat(pointsArrys[3]);
			gis2DLayer.setMaxLat(pointsArrys[1]);
		}
		if (gis2DLayer.getPoints2() != null) {
			if (!gis2DLayer.getPoints2().equals(gis2DLayer.getPoints())) {
				pointsArrys = OpenLayersGetPoints.getMaxAndMinLonLat(gis2DLayer
						.getPoints2());
			}
			gis2DLayer.setMinLon2(pointsArrys[2]);
			gis2DLayer.setMaxLon2(pointsArrys[0]);
			gis2DLayer.setMinLat2(pointsArrys[3]);
			gis2DLayer.setMaxLat2(pointsArrys[1]);
		}
	}

	/***
	 * 设置组织机构参数
	 * 
	 * @param gis2DLayer
	 */
	private void setParamForOrg(Gis2DLayer gis2DLayer) {
		Organization org = organizationDubboService.getSimpleOrgById(gis2DLayer
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("组织机构为空");
		}
		gis2DLayer.setOrganization(org);
		gis2DLayer.setOrgInternalCode(org.getOrgInternalCode());
	}

	private static Object cache(String mode, String key, Object obj) {
		CacheService cacheService = (CacheService) SpringBeanUtil
				.getBeanFromSpringByBeanName("cacheService");
		String cacheKey = MemCacheConstant.generateCacheKeyFromMethodName(
				Gis2DLayer.class, MemCacheConstant.GETUNDERGIS2DLAYERSBYORGID,
				key);
		if (SET_MODE.equals(mode)) {
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, obj);
		} else if (GET_MODE.equals(mode)) {
			return cacheService.get(MemCacheConstant.MAP_NAMESPACE, cacheKey);
		} else if (DEL_MODE.equals(mode)) {
			cacheService.remove(MemCacheConstant.MAP_NAMESPACE, cacheKey);
		}
		return null;
	}

}
