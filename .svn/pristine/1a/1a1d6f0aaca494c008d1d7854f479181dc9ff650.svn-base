package com.tianque.plugin.serviceTeam.serviceObject.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectHouseVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectLocationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectPopulationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectVo;

public interface ServiceObjectDao {

	/**
	 * 人员对象列表
	 * 
	 * @param serviceObjectVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ServiceObjectPopulationVo> findPopulations(ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize);

	/**
	 * 人员总数
	 * 
	 * @param serviceObjectVo
	 */
	public Integer countFindPopulationsList(ServiceObjectVo serviceObjectVo);

	/**
	 * 人员列表
	 * 
	 * @param serviceObjectVo
	 */
	public List<ServiceObjectPopulationVo> findPopulationsList(ServiceObjectVo serviceObjectVo);

	/**
	 * 场所对象列表
	 * 
	 * @param serviceObjectVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ServiceObjectLocationVo> findLocations(ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize);

	/**
	 * 场所总数
	 * 
	 * @param serviceObjectVo
	 */
	public Integer countFindLocationsList(ServiceObjectVo serviceObjectVo);

	/**
	 * 场所列表
	 * 
	 * @param serviceObjectVo
	 */
	public List<ServiceObjectLocationVo> findLocationsList(ServiceObjectVo serviceObjectVo);

	/**
	 * 房屋对象列表
	 * 
	 * @param serviceObjectVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ServiceObjectHouseVo> findHouses(ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize);

	/**
	 * 场所总数
	 * 
	 * @param serviceObjectVo
	 */
	public Integer countFindHousesList(ServiceObjectVo serviceObjectVo);

	/**
	 * 场所列表
	 * 
	 * @param serviceObjectVo
	 */
	public List<ServiceObjectHouseVo> findHousesList(ServiceObjectVo serviceObjectVo);
}
