package com.tianque.baseInfo.householdStaff.shardTable.dao;

import java.util.List;

import com.tianque.baseInfo.householdStaff.shardTable.vo.HouseHoldStaffLogVo;

public interface HouseHoldStaffLogDao {

	public List<HouseHoldStaffLogVo> findHouseHoldStaffLogByStartIdAndEndId(
			Long startId, Long endId);

	public HouseHoldStaffLogVo getHouseHoldStaffById(Long id);

	public Long getMaxHouseHoldStaffLogId();

	public void addHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo);

	public void updateHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo);

	public void deleteHouseHoldStaff(HouseHoldStaffLogVo houseHoldStaffLogVo);

	public void updateAssociatedTable(HouseHoldStaffLogVo houseHoldStaffLogVo);

}
