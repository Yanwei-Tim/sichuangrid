package com.tianque.plugin.serviceTeam.serviceObject.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectHouseVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectLocationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectPopulationVo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectVo;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;

@Repository("serviceObjectDao")
public class ServiceObjectDaoImpl extends AbstractBaseDao implements
		ServiceObjectDao {
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<ServiceObjectPopulationVo> findPopulations(
			ServiceObjectVo serviceObjectVo, Integer pageNum, Integer pageSize) {

		Integer countNum = this.countFindPopulationsList(serviceObjectVo);

		List<ServiceObjectPopulationVo> list = getSqlMapClientTemplate()
				.queryForList("serviceObject.findPopulations", serviceObjectVo,
						(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceObjectPopulationVo>(pageNum, pageSize,
				countNum, list);
	}

	@Override
	public PageInfo<ServiceObjectLocationVo> findLocations(
			ServiceObjectVo serviceObjectVo, Integer pageNum, Integer pageSize) {
		Integer countNum = this.countFindLocationsList(serviceObjectVo);

		List<ServiceObjectLocationVo> list = getSqlMapClientTemplate()
				.queryForList("serviceObject.findLocations", serviceObjectVo,
						(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceObjectLocationVo>(pageNum, pageSize,
				countNum, list);
	}

	@Override
	public PageInfo<ServiceObjectHouseVo> findHouses(
			ServiceObjectVo serviceObjectVo, Integer pageNum, Integer pageSize) {
		Integer countNum = this.countFindHousesList(serviceObjectVo);

		List<ServiceObjectHouseVo> list = getSqlMapClientTemplate()
				.queryForList("serviceObject.findHouses", serviceObjectVo,
						(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<ServiceObjectHouseVo>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public Integer countFindPopulationsList(ServiceObjectVo serviceObjectVo) {
		if (serviceObjectVo.getTableName() != null
				&& ShardTables.isShardTables(serviceObjectVo.getTableName())
				&& serviceObjectVo.getOrganization() != null) {
			String shardCode = shardConversion.getShardCode(serviceObjectVo
					.getOrganization().getId());
			serviceObjectVo.setTableName(serviceObjectVo.getTableName() + "_"
					+ shardCode);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceObject.countFindPopulations", serviceObjectVo);
	}

	@Override
	public List<ServiceObjectPopulationVo> findPopulationsList(
			ServiceObjectVo serviceObjectVo) {
		return getSqlMapClientTemplate().queryForList(
				"serviceObject.findPopulations", serviceObjectVo);
	}

	@Override
	public Integer countFindLocationsList(ServiceObjectVo serviceObjectVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceObject.countFindLocations", serviceObjectVo);

	}

	@Override
	public List<ServiceObjectLocationVo> findLocationsList(
			ServiceObjectVo serviceObjectVo) {
		return getSqlMapClientTemplate().queryForList(
				"serviceObject.findLocations", serviceObjectVo);
	}

	@Override
	public Integer countFindHousesList(ServiceObjectVo serviceObjectVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceObject.countFindHouses", serviceObjectVo);
	}

	@Override
	public List<ServiceObjectHouseVo> findHousesList(
			ServiceObjectVo serviceObjectVo) {
		return getSqlMapClientTemplate().queryForList(
				"serviceObject.findHouses", serviceObjectVo);
	}

}
