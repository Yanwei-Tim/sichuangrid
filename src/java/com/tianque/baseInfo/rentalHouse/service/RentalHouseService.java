package com.tianque.baseInfo.rentalHouse.service;

import java.util.List;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;

public interface RentalHouseService {
	/**
	 * 新增出租房信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息 @
	 */
	public RentalHouse addHouseInfo(RentalHouse rentalHouse);

	/** 数据认领新增出租房 */
	public RentalHouse addRentalHouse(RentalHouse rentalHouse);

	/**
	 * 修改出租房基本信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息 @
	 */
	public RentalHouse updateHouseBaseInfo(RentalHouse rentalHouse);

	/**
	 * 
	 * @Title: updateHouseBaseInfoForMobile
	 * @Description: TODO为手机端增加修改方法
	 * @param @param rentalHouse
	 * @param @return
	 * @param @ 设定文件
	 * @return RentalHouse 返回类型
	 * @author wanggz
	 * @date 2014-5-27 下午08:30:14
	 */
	public RentalHouse updateHouseBaseInfoForMobile(RentalHouse rentalHouse);

	/**
	 * 修改出租房业务信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息 @
	 */
	public RentalHouse updateHouseBusinessInfo(RentalHouse rentalHouse);

	/**
	 * 获取出租房信息
	 * 
	 * @param id
	 * @return @
	 */
	public RentalHouse getHouseInfoById(Long id);

	/**
	 * 根据房屋编号和组织ID获取出租房信息
	 * 
	 * @param houseCode
	 *            房屋编号
	 * @param orgId
	 *            组织ID
	 * @return @
	 */
	public RentalHouse getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long orgId);

	/**
	 * 更新注销状态
	 * 
	 * @param houseIds
	 *            出租房ID
	 * @param isEmphasis
	 *            注销状态 @
	 */
	public void updateEmphasiseByIds(List<Long> houseIds, Long isEmphasis);

	/**
	 * 删除出租房信息(将相应的实有房屋也删除)
	 * 
	 * @param idList
	 *            @
	 */
	public void deleteRentalHouseByIds(Long[] idList);

	/**
	 * 判断房屋编号是否存在
	 * 
	 * @param orgId
	 * @param houseCode
	 * @param houseId
	 * @return @
	 */
	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long houseId);

	/**
	 * 分页查询出租房信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return @
	 */
	public PageInfo<RentalHouse> findHouseInfosForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 导出查询出租房信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param searchHouseInfoVo
	 * @return @
	 */
	public PageInfo<RentalHouse> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchHouseInfoVo searchHouseInfoVo);

	/**
	 * 获取所有出租房信息
	 * 
	 * @param searchRentalHouseInfoVo
	 * @return @
	 */
	public List<RentalHouse> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo);

	public RentalHouse serchRentalHouseByHouseCode(String houseCode, Long orgId);

	/**
	 * 删除出租房
	 * 
	 * @param idList
	 */
	public void deleteRentalHousesByIdList(List<Long> idList);

	public void shiftRentalHouse(Long[] ids, Long id);

	public RentalHouse getHouseInfoByHouseId(Long houseId);

	/**
	 * 据房屋id和组织ID获取出租房信息
	 * 
	 * @param id
	 * @param id2
	 * @return
	 */
	public RentalHouse getHouseInfoByHouseIdAndOrgId(Long id, Long id2);

	boolean hasDuplicateHouseInfoForAddress(Long orgId, String houseCode,
			Long houseId);

	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo);

	public RentalHouse getRentalHouseInfoByHouseId(Long id);

	/**
	 * 将旧出租房ID修改为新出租房ID
	 */
	public void updateRentalHouseInfoByNewHouseId(Long oldHouseId,
			Long newHouseId);

	public RentalHouse getRentalHouseById(Long id);
	
	/***
	 * 根据Id删除出租房信息
	 */
	public void deleteRentalhouseInfoById(Long id);
}
