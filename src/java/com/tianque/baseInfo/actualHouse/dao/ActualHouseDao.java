package com.tianque.baseInfo.actualHouse.dao;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;

/**
 * 实有房屋数据访问接口
 */
public interface ActualHouseDao extends BaseDao<HouseInfo, SearchHouseInfoVo> {

	/**
	 * 修改简单的房屋信息
	 */
	public void updateHouseInfoSimple(HouseInfo houseInfo);

	/**
	 * 根据房屋准确地址和网格ID查询房屋信息，分页
	 */
	public PageInfo<HouseInfo> findHouseInfoByHouseAddressAndOrgId(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String address, Long orgId, Long id);

	/**
	 * 新增实有房屋信息
	 * 
	 * @param houseInfo
	 *            实有房屋信息
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo addHouseInfo(HouseInfo houseInfo);

	/**
	 * 修改实有房屋信息
	 * 
	 * @param houseInfo
	 *            实有房屋信息
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo updateHouseInfo(HouseInfo houseInfo);

	/**
	 * 修改实口房屋信息
	 * 
	 * @param houseInfo
	 *            实有房屋信息
	 * @return HouseInfo 实有房屋信息 @
	 */
	public HouseInfo updateHouseInfoForPopulation(HouseInfo houseInfo);

	/**
	 * 根据ID获取实有房屋信息
	 * 
	 * @param id
	 * @return
	 */
	public HouseInfo getHouseInfoById(Long id);

	/**
	 * 根据ID获取实有房屋信息
	 * 
	 * @param id
	 * @return
	 */
	public List<HouseInfo> getHouseInfoByIds(List<Long> ids);

	/**
	 * 删除实有房屋信息
	 * 
	 * @param id
	 */
	public void deleteHouseInfoById(Long id, String shardCode);

	/**
	 * 分页查询实有房屋信息
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HouseInfo> findHouseInfosForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据房屋编号和类型获取实有房屋信息
	 * 
	 * @param houseCode
	 * @param organizationId
	 * @return
	 */
	public HouseInfo getHouseInfoByHouseCodeAndOrganizationId(String houseCode,
			Long organizationId);

	/**
	 * 分页导出查询实有房屋信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param searchHouseInfoVo
	 * @return
	 */
	public PageInfo<HouseInfo> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHouseInfoVo searchHouseInfoVo);

	/**
	 * 导出所有实有房屋信息
	 * 
	 * @param searchHouseInfoVo
	 * @return
	 */
	public List<HouseInfo> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo);

	public Integer countHouseByBuildingId(Long buildingId, String shardCode);

	public PageInfo findHouseInfosForPageByTag(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord, String tag);

	public HouseInfo updateHousePropertyForGis(HouseInfo houseInfo);

	public PageInfo findHouseInfosByBuildingIdForPage(Long buildingId,
			Integer page, Integer rows, String sidx, String sord);

	public void unbindHousePropertyById(Long houseId);

	/**
	 * 对地图上矩形范围内的 所以户籍信息查询
	 * 
	 * @param minOption
	 * @param maxOption
	 * @return
	 */
	public List<HouseInfo> searchMapScope(GisInfo minOption, GisInfo maxOption);

	/**
	 * 根据网格信息统计该网格下房屋数量
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public int countUnRentHouseByOrgInternalCode(String orgInternalCode);

	/**
	 * 根据网格信息统计该网格下出租房屋数量
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public int countRentHouseByOrgInternalCode(String orgInternalCode);

	/************************************ 以下是实有房屋与楼宇绑定 ****************************************/

	/**
	 * 分页根据楼宇编号查询绑定的房屋信息
	 */
	PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(Long builddatasId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	/**
	 * 根据组织内部编号查询未被绑定的房屋信息
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return
	 */
	PageInfo<HouseInfo> findHouseInfosListForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String queryStr);

	/**
	 * 更新房屋绑定数据
	 * 
	 * @param houseInfo
	 * @return
	 */
	Boolean updateBuildDatasId(HouseInfo houseInfo, String shardCode);

	List<HouseInfo> findHouseInfosListByBuildingId(Long builddatasId,
			String shardCode);

	Long countHouseInfoByBuilddatasidAndHouseUse(Long builddatasid,
			Long houseUse, String shardCode);

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
	public int countHouseByOrgIdAndHouseCode(Long orgId, String houseCode);

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
	 * 楼宇绑定地图同步房屋
	 */
	void bound(Long id, String centerLat, String centerLon, String shardCode);

	/**
	 * 楼宇基础绑定地图同步房屋
	 */
	void unbound(Long id, String shardCode);

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
	public PageInfo<HouseInfo> findHouseInfosForPage(Long orgId, Long houseId,
			Integer page, Integer rows, String sidx, String sord);

	public HouseInfo getHouseInfoByHouseAddressAndOrganizationId(
			String address, Long orgId);

	/**
	 * 同步更新房屋的建筑物名称
	 */
	public void synchronizationBuildName(Long id, String buildingName,
			OpenLayersMapInfo openLayersMapInfo, String shardCode);

	public List<HouseInfo> findHouseInfosByHouseAddressAndOrgId(String address,
			Long organizationId);

	/**
	 * 
	 * @Title: findHouseInfosByHouseAddressAndOrgIdForMobile
	 * @Description: TODO为手机端新增查询地址方法
	 * @param @param address
	 * @param @param organizationId
	 * @param @return 设定文件
	 * @return List<HouseInfo> 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午03:22:26
	 */
	public PageInfo<HouseInfo> findHouseInfosByHouseAddressAndOrgIdForMobile(
			String address, Long organizationId);

	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo);

	public List<HouseInfo> checkIsHasHouseByHouseCodeAndOrgId(String houseCode,
			Long organizationId);

	public List<HouseInfo> findHouseInfosByStartIdAndEndId(Long startId,
			Long endId);

	public Integer getMaxHouseInfoId();

	/** 只用一次 */
	public List<Long> findIdsFromRecoverDataInfosByStartDateAndType(
			Date startDate, String type);

	// 房屋人数
	public void updateHouseInfoMemberNum(String shardCode, Long houseId, int num);

	public Long getLogOutByPopulationTypeAndPopulationId(String shardCode,
			String populationType, Long populationId);

	/***
	 * 查询统计住宅总数
	 */
	public Integer countResidential(String orgCode, Long buildingUses,
			String shardCode, String houseType);

	/***
	 * 统计人房关联住宅数
	 */
	public Integer countRelation(String orgCode, Long buildingUses,
			String shardCode, String houseType);
}
