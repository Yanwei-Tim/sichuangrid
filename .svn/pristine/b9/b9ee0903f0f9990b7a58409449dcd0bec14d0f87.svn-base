package com.tianque.baseInfo.buildDatas.dao;

import java.util.List;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.BuilddatasSearchVo;
import com.tianque.core.vo.PageInfo;

public interface BuilddatasDao {
	public PageInfo<Builddatas> findBuilddatasByOrgInternalCode(String orgInternalCode,
			Integer isBind, Integer pageNum, Integer pageSize, String sortField, String order);

	public Builddatas addBuilddatas(Builddatas builddatas);

	public Builddatas getBuilddatasById(Long id);

	public Builddatas updateBuilddatas(Builddatas builddatas);

	public void deleteBuilddatasById(Long id);

	public PageInfo<Builddatas> searchBuilddatas(BuilddatasSearchVo builddatasSearchVo,
			Integer page, Integer rows, String sidx, String sord);

	public Builddatas getBuilddatasByBuildId(String BuildId);

	public PageInfo<Builddatas> findUnBoundBuilddatasByStr(String str, Integer pageNum,
			Integer pageSize, String sortField, String order, String orgInternalCode);

	public Builddatas updateBuilddatasByBuilddingId(Builddatas builddatas);

	public void updateBuilddatasByFeatureId(String featureId);

	// 根据坐标集统计已绑定楼宇数量
	public Integer countBoundBuildDatasByLonAndLatArrays(Double[] lonAndLatArrays);

	// 根据坐标集统计楼宇数量
	public Integer countBuildDatasByLonAndLatArrays(Double[] lonAndLatArrays);

	/**
	 * 通过orgCode查询已绑定的楼宇数量
	 * 
	 * @param orgCode
	 * @return
	 */
	public Integer countBoundBuildDatasByOrgCode(String orgCode);

	// 已绑楼宇数量根据orgcode
	public Integer countBoundBuildDatas(String orgCode);

	// 未绑定楼宇数量根据orgcode
	public Integer countUnBoundBuilddatasByOrgCode(String orgCode);

	// 分页查询已绑定楼宇
	public PageInfo<Builddatas> findBoundBuilddatasByOrgCode(String orgCode, Integer pageNum,
			Integer pageSize, String sortField, String order);

	// 分页查询未绑定楼宇
	public PageInfo<Builddatas> findUnBoundBuilddatasByOrgCode(String orgCode, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public void boundBuilddatas(Builddatas builddatas);

	public void unboundBuilddatas(Builddatas builddatas);

	/**
	 * 根据名称和地址西搜索楼宇
	 * 
	 * @param builddatasSearchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<Builddatas> searchBuilddatasByNameAndAddress(
			BuilddatasSearchVo builddatasSearchVo, Integer page, Integer rows, String sidx,
			String sord);
	
	public List<Builddatas> findBuildDatasByBuildId(String builId);

}
