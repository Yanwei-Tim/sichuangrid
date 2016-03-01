package com.tianque.openLayersMap.service;

import java.io.File;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;

/**
 * 地图区域管理service接口
 * 
 * @date 2013-3-15
 * 
 */
public interface Gis2DLayerService {

	/**
	 * 新增地图区域信息
	 */
	public Gis2DLayer addGis2DLayer(Gis2DLayer gis2dLayer);
	public Gis2DLayer saveGis2DLayer(Gis2DLayer gis2dLayer);

	/**
	 * 修改地图区域信息
	 */
	public Gis2DLayer updateGis2DLayer(Gis2DLayer gis2dLayer);
	public Gis2DLayer updatePoint(Gis2DLayer gis2DLayer);
	
	/**
	 * 通过组织机构Code删除区域
	 * @param orgCode
	 */
	public void deleteUndersByOrgId(Long orgId);
	/**
	 * 根据组织机构ID删除区域
	 * @param orgId
	 */
	public void deleteByOrgId(Long orgId);
	/**
	 * 根据ID删除区域
	 * @param orgId
	 */
	public void deleteById(Long id);

	/**
	 * 通过组织机构ID获取地图区域信息
	 * @param orgId
	 * @param gisType  地图类型
	 */
	public Gis2DLayer getByOrgId(Long orgId, String gisType);
	/**
	 * 通过组织机构国标获取地图区域信息
	 * @param orgId
	 * @param gisType  地图类型
	 */
	public Gis2DLayer getByOrgDepartmentNo(String orgDepartNo, String gisType);
	/**
	 * 根据组织机构模糊查询地图区域
	 * @param orgCode
	 * @param gisType  地图类型
	 */
	public List<Gis2DLayer> findByOrgCode(String orgCode, String gisType);
	
	/**
	 * 通过ID获取地图区域信息
	 * @param id
	 * @param gisType  地图类型
	 */
	public Gis2DLayer getById(Long id, String gisType);

	/**
	 * 获取下辖ORG的地图区域
	 * @param orgId
	 * @param screenVo  当前屏幕范围
	 * @param gisType  地图类型
	 */
	public List<Gis2DLayer> findUndersByOrgIdAndScreenVo(Long orgId, ScreenCoordinateVo screenVo, String gisType);
	/**
	 *获取下辖ORG的地图区域
	 * @param orgId
	 * @param gisType  地图类型
	 */
	public List<Gis2DLayer> findUndersByOrgId(Long orgId, String gisType);
	
	/**
	 * 获取里中心点最近的区域
	 * @param gis2DLayer
	 * @param gisType  地图类型
	 */
	public Gis2DLayer getByCenterPointAndZoom(Gis2DLayer domain, String gisType);
	
	/**
	 * 通过网格编号和组织机构层级查询当前屏幕内的地图区域信息
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 */
	public PageInfo<Gis2DLayer> findForPageBySearchVo(SearchInfoVo searchVo,Integer page, Integer rows, String sidx, String sord);

	/**
	 * 通过组织机构编号获取某一层级的地图区域数量
	 * @param orgCode
	 * @param OrgLevelId
	 */
	public Integer countByOrgCodeAndOrgLevel(String orgCode,Long orgLevelId);
	
	/**
	 * 导入区域
	 * @param gisType
	 * @param upload
	 * @param uploadFileName
	 */
	public Long importGis2DLayer(String gisType, File upload,String uploadFileName);

}
