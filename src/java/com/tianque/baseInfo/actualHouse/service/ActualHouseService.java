package com.tianque.baseInfo.actualHouse.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;

public interface ActualHouseService {
	/**
	 * 新增实有房屋信息
	 * 
	 * @param houseInfo
	 *            实有房屋信息
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo addHouseInfo(HouseInfo houseInfo);

	/** 实有房屋数据认领时添加 */
	public HouseInfo addActualHouse(HouseInfo houseInfo);

	/**
	 * 修改实有房屋信息
	 * 
	 * @param houseInfo
	 *            实有房屋信息
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo updateHouseInfo(HouseInfo houseInfo);

	/**
	 * 根据ID获取实有房屋信息
	 * 
	 * @param id
	 * @return @
	 */
	public HouseInfo getHouseInfoById(Long id);

	public HouseInfo getActualHouseById(Long id);

	/**
	 * 根据房屋编号获取实有房屋信息
	 * 
	 * @param houseCode
	 *            房屋编号
	 * @param orgId
	 *            组织ID
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long orgId);

	/**
	 * 删除实有房屋信息
	 * 
	 * @param idList
	 *            @
	 */
	public void deleteHouseInfosByIdList(List<Long> idList);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteActualHouseByIds(Long[] ids);

	/**
	 * 删除实有房屋信息
	 * 
	 * @param houseId
	 *            @
	 */
	public void deleteHouseInfosById(Long houseId);

	/**
	 * 校验房屋编号是否存在
	 * 
	 * @param orgId
	 * @param houseCode
	 * @param houseId
	 * @return @
	 */
	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long houseId);

	/**
	 * 分页查询实有房屋信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param searchHouseInfoVo
	 * @return @
	 */
	public PageInfo<HouseInfo> findHouseInfosForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 导出查询实有房屋信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param searchHouseInfoVo
	 * @return @
	 */
	public PageInfo<HouseInfo> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchHouseInfoVo searchHouseInfoVo);

	/**
	 * 获取所有实有房屋信息
	 * 
	 * @param searchHouseInfoVo
	 * @return @
	 */
	public List<HouseInfo> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo);

	/**
	 * 对地图上矩形范围内的 所以户籍信息查询
	 * 
	 * @param minOption
	 * @param maxOption
	 * @return
	 */
	public List<HouseInfo> searchMapScope(GisInfo minOption, GisInfo maxOption);

	/************************************ 以下是实有房屋与楼宇绑定 ****************************************/

	/**
	 * 根据楼宇编号查询绑定的房屋信息
	 */
	PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(Long builddatasId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	/**
	 * 根据网格编号找到未绑定的房屋信息
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return
	 */
	PageInfo<HouseInfo> findUnBoundedByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, String queryStr);

	/**
	 * 更改房屋绑定
	 * 
	 * @param houseIds
	 * @param builddatasId
	 * @return
	 */
	List<Long> updateHouseInfo(List<Long> houseIds, Long builddatasId);

	/**
	 * 根据楼宇编号查询楼宇绑定的房屋
	 * 
	 * @param buildingId
	 * @return
	 */
	List<HouseInfo> findHouseInfosListByBuildingId(Long buildingId);

	/**
	 * 根据房屋用途和楼宇编号，查询房屋个数
	 * 
	 * @param builddatasid
	 *            楼宇编号
	 * @param houseUse
	 *            房屋用途
	 * @return
	 */
	Long countHouseInfoByBuilddatasidAndHouseUses(Long builddatasid,
			Long houseUse);

	Long countHouseInfoByBuilddatasidAndisrentalhouse(Long builddatasid,
			int isrentalhouse);

	public void changeHouseCode(Long orgId, String oldHouseCode,
			String houseCode);

	/**
	 * 查询房屋记录存在几条
	 * 
	 * @param orgId
	 * @param houseCode
	 * @return
	 */
	public int exist(Long orgId, String houseCode);

	/**
	 * 根据房屋编号模糊查询房屋列表
	 * 
	 * @param houseCode
	 * @param organizationId
	 * @return
	 */
	public List<HouseInfo> findHouseInfosByHouseCodeAndOrganizationId(
			String houseCode, Long organizationId);

	/**
	 * 根据组织机构id查询房屋的分页信息，houseId为要排除的房屋id
	 * 
	 * @param orgId
	 * @param houseId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo findHouseInfosForPage(Long orgId, Long houseId,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * @param address
	 *            --- 房屋地址
	 * @param id
	 *            ---- orgid
	 * @return
	 */
	public HouseInfo getHouseInfoByHouseAddressAndOrgId(String address, Long id);

	boolean hasDuplicateHouseInfoForAddress(Long orgId, String houseCode,
			Long houseId);

	public List<HouseInfo> findHouseInfosByHouseAddressAndOrgId(String address,
			Long id);

	/**
	 * 
	 * @Title: findHouseInfosByHouseAddressAndOrgIdForMobile
	 * @Description: TODO为手机端新增一个查询地址的方法
	 * @param @param address
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<HouseInfo> 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午03:20:18
	 */
	public PageInfo<HouseInfo> findHouseInfosByHouseAddressAndOrgIdForMobile(
			String address, Long id);

	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo);

	public List<HouseInfo> checkIsHasHouseByHouseCodeAndOrgId(String houseCode,
			Long id);

	/**
	 * 通过房屋的准确地址和网格ID查询房屋信息
	 */
	public PageInfo<HouseInfo> findHouseInfoByHouseAddressAndOrgId(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String address, Long orgId, Long id);

	/**
	 * 修改简单的房屋信息
	 */
	public void updateSimpleHouseInfo(HouseInfo houseInfo);

	public void moveToMongodb();

	public void deleteMongodb();


	/**
	 * 更新房屋人数
	 */
	public void updateHouseInfoMemberNum(String shardCode, Long houseId, int num);

	/**
	 * 实口的类型 与ID 取得注销状态
	 */
	public Long getLogOutByPopulationTypeAndPopulationId(String shardCode,
			String populationType, Long populationId);

	/***
	 * 计算人房关联比例
	 */
	public String dealHousePeopleProportion(Long orgId, String houseType);
}
