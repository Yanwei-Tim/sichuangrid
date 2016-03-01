package com.tianque.openLayersMap.dao;


import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;

/**
 * 地图区域管理dao接口 
 */
public interface Gis2DLayerDao {


	/**
	 * 新增地图区域信息
	 */
	public Gis2DLayer addGis2DLayer(Gis2DLayer gis2dLayer);

	/**
	 * 修改地图区域信息
	 */
	public Gis2DLayer updateGis2DLayer(Gis2DLayer gis2dLayer);
	
	/**
	 * 通过组织机构Code删除区域
	 * @param orgCode
	 */
	public void deleteByOrgCode(String orgCode);
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
	 * @param searchVo
	 * @param gisType  地图类型
	 */
	public List<Gis2DLayer> findUndersBySearchVo(SearchInfoVo searchVo, String gisType);
	
	/**
	 * 通过当前地图级别和中心点查询地图区域信息
	 * @param gis2DLayer
	 * @param gisType  地图类型
	 */
	public List<Gis2DLayer> findByCenterPointAndZoom(Gis2DLayer domain, String gisType);

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
	 * @param searchVo
	 */
	public Integer countByOrgCodeAndOrgLevel(SearchInfoVo searchVo);
}
