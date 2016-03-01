package com.tianque.dao;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.vo.PageInfo;

public interface HouseInfoDao {
	public HouseInfo addHouseInfo(HouseInfo houseInfo);

	public HouseInfo updateHouseInfo(HouseInfo houseInfo);

	public HouseInfo getSimpleHouseInfoById(Long id);

	public HouseInfo findGisHouseInfoById(Long houseId);

	public void deleteInfrastructureByHouseInfoId(Long houseInfoId);

	public HouseInfo getHouseInfoByHouseCodeAndOrganizationId(String houseCode,
			Long organizationId);

	public HouseInfo unBundHouseInfoById(Long id, String shardCode);

	public PageInfo<HouseInfo> getGisByQueryStrAndOrgId(String orgCode,
			String queryStr, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public List<HouseInfo> countActualHouseByOrgCode(String orgInternalCode);

	public PageInfo<HouseInfo> getkeyHouseInfoForGisByorgInternalCode(
			String orgInternalCode, String houseType, Integer page,
			Integer rows, String sidx, String sord);

	public List<HouseInfo> findAllBindingHouseInfoByOrgInternalCode(
			final String orgInternalCode);

	public List<HouseInfo> findAllBindingHouseInfoByBuildingId(
			final Long buildingId);

	List<LayoutTagVo> searchAllHouseName4LayoutTag(String orgInternalCode,
			Long builddatasid);

	/**
	 * 获取房屋门牌号和产权人姓名，格式如“room-name”
	 * 
	 * @param id
	 * @return
	 */
	String getHouseRoomAndNameById(Long id);

	/**
	 * 通過網格ID和房屋地址查詢房屋信息
	 */
	public List<HouseInfo> findHouseInfoByOrgIdAddress(Long orgId,
			String address);
}
