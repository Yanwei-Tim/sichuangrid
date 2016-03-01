package com.tianque.baseInfo.buildDatas.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.BuilddatasSearchVo;
import com.tianque.core.vo.PageInfo;

public interface BuilddatasService {

	public PageInfo<Builddatas> findBuilddatasByOrgInternalCode(
			String orgInternalCode, Integer isBind, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public Builddatas addBuilddatas(Builddatas builddatas);

	public Builddatas getBuilddatasById(Long id);

	public Builddatas updateBuilddatas(Builddatas builddatas);

	public List<Long> deleteMultiBuilddatas(List<Long> idList);

	public void deleteBuilddatasById(Long id, String shardCode);

	public PageInfo<Builddatas> searchBuilddatas(
			BuilddatasSearchVo builddatasSearchVo, Integer page, Integer rows,
			String sidx, String sord);

	public Builddatas getBuilddatasByBuildId(String BuildId);

	public PageInfo<Builddatas> findUnBoundBuilddatasByStr(String str,
			Integer pageNum, Integer pageSize, String sortField, String order,
			Long orgId);

	// 根据坐标集统计已绑定楼宇数量
	public Integer countBoundBuildDatasByLonAndLatArrays(
			Double[] lonAndLatArrays);

	// 根据坐标集统计楼宇总数
	public Integer countBuildDatasByLonAndLatArrays(Double[] lonAndLatArrays);

	/**
	 * 通过orgId查询已绑定的楼宇数量
	 * 
	 * @param orgId
	 * @return
	 */
	public Integer countBoundBuildDatasByOrgId(Long orgId);

	// 已绑定楼宇数量
	public Integer countBoundBuildDatas(String orgCode);

	public Integer countUnBoundBuilddatasByOrgCode(String orgCode);

	public PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(
			Long builddatas, int page, int rows, String sidx, String sord);

	public PageInfo<HouseInfo> findUnBoundedByOrgId(Long orgId, int page,
			int rows, String sidx, String sord, String queryStr);

	/**
	 * 删除布局时更新楼宇信息
	 * 
	 * @param id
	 *            楼宇编号
	 * @return
	 */
	Boolean deleteLayoutAndUpdateBuilddatasByBuildingId(Long id);

	public void boundBuilddatas(Builddatas builddatas);

	public void unboundBuilddatas(Builddatas builddatas);

	/**
	 * 根据名称地址搜索楼宇
	 * 
	 * @param builddatasSearchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<Builddatas> searchBuilddatasByNameAndAddress(
			BuilddatasSearchVo builddatasSearchVo, Integer page, Integer rows,
			String sidx, String sord);

	/***
	 * 通过buildId查询楼宇信息，返回list(用于楼宇建筑解除地图绑定，为了防止脏数据造成的错误)
	 */
	public List<Builddatas> findBuildDatasByBuildId(String builId);

}
