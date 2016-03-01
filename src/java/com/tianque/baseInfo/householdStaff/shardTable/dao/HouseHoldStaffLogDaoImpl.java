package com.tianque.baseInfo.householdStaff.shardTable.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.householdStaff.shardTable.vo.HouseHoldStaffLogVo;
import com.tianque.core.base.AbstractBaseDao;

@Repository("houseHoldStaffLogDao")
public class HouseHoldStaffLogDaoImpl extends
		AbstractBaseDao<HouseHoldStaffLogVo> implements HouseHoldStaffLogDao {

	@Override
	public List<HouseHoldStaffLogVo> findHouseHoldStaffLogByStartIdAndEndId(
			Long startId, Long endId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("startId", startId);
		map.put("endId", endId);
		return (List<HouseHoldStaffLogVo>) getSqlMapClientTemplate()
				.queryForList(
						"houseHoldStaffLog.findHouseHoldStaffLogByStartIdAndEndId",
						map);
	}

	@Override
	public HouseHoldStaffLogVo getHouseHoldStaffById(Long id) {
		return (HouseHoldStaffLogVo) getSqlMapClientTemplate().queryForObject(
				"houseHoldStaffLog.getHouseHoldStaffById", id);
	}

	@Override
	public Long getMaxHouseHoldStaffLogId() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"houseHoldStaffLog.getMaxHouseHoldStaffLogId");
	}

	@Override
	public void addHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo) {
		getSqlMapClientTemplate().insert("houseHoldStaffLog.addHouseHoldStaff",
				houseHoldStaffLogVo);

	}

	@Override
	public void updateHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo) {
		getSqlMapClientTemplate().update(
				"houseHoldStaffLog.updateHouseHoldStaff", houseHoldStaffLogVo);
	}

	@Override
	public void deleteHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo) {
		getSqlMapClientTemplate().delete(
				"houseHoldStaffLog.deleteHouseHoldStaff", houseHoldStaffLogVo);
	}

	@Override
	public void updateAssociatedTable(HouseHoldStaffLogVo houseHoldStaffLogVo) {
		getSqlMapClientTemplate().update(
				"houseHoldStaffLog.updateHousehasactualpopulation",
				houseHoldStaffLogVo);
		getSqlMapClientTemplate().update(
				"houseHoldStaffLog.updatePopulationtypes", houseHoldStaffLogVo);
	}

}
