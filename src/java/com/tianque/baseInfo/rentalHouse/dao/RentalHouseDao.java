package com.tianque.baseInfo.rentalHouse.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;

/**
 * 出租房数据访问接口
 */
public interface RentalHouseDao {
	/**
	 * 新增出租房信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息
	 */
	public RentalHouse addHouseInfo(RentalHouse rentalHouse);

	/**
	 * 修改出租房基本信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息
	 */
	public RentalHouse updateHouseBaseInfo(RentalHouse rentalHouse);

	/**
	 * 
	 * @Title: updateHouseBaseInfoForMobile
	 * @Description: TODO为手机端增加修改方法
	 * @param @param rentalHouse
	 * @param @return 设定文件
	 * @return RentalHouse 返回类型
	 * @author wanggz
	 * @date 2014-5-27 下午08:27:46
	 */
	public RentalHouse updateHouseBaseInfoForMobile(RentalHouse rentalHouse);

	/**
	 * 修改出租房业务信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息
	 */
	public RentalHouse updateHouseBusinessInfo(RentalHouse rentalHouse);

	/**
	 * 更新出租房注销状态
	 * 
	 * @param houseId
	 *            出租房ID
	 * @param isEmphasis
	 *            注销状态
	 */
	public void updateEmphasiseById(Map<String, Object> map);

	/**
	 * 修改实口出租房业务信息
	 * 
	 * @param rentalHouse
	 *            出租房信息
	 * @return RentalHouse 出租房信息
	 */
	public RentalHouse updateHouseBusinessInfoForPopulation(
			RentalHouse rentalHouse);

	/**
	 * 根据ID获取出租房信息
	 * 
	 * @param id
	 *            主键
	 * @return RentalHouse 出租房信息
	 */
	public RentalHouse getHouseInfoById(Long id);

	/**
	 * 删除出租房信息
	 * 
	 * @param id
	 */
	public void deleteHouseInfoById(Long id);

	/**
	 * 分页查询出租房信息
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<RentalHouse> findHouseInfosForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据房屋编号类型和组织信息查询出租房信息
	 * 
	 * @param houseCode
	 * @param organizationId
	 * @return
	 */
	public RentalHouse getHouseInfoByHouseCodeAndOrganizationId(
			String houseCode, Long organizationId);

	/**
	 * 导出查询出租房信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param searchHouseInfoVo
	 * @return
	 */
	public PageInfo<RentalHouse> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHouseInfoVo searchHouseInfoVo);

	/**
	 * 导出时查询房屋信息
	 * 
	 * @param searchHouseInfoVo
	 * @return
	 */
	public List<RentalHouse> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo);

	public RentalHouse serchRentalHouseByHouseCode(String houseCode, Long orgId);

	public RentalHouse getHouseInfoByHouseCodeAndOrganizationId(
			String houseCode, Long id, Long logoutType);

	public RentalHouse getHouseInfoByHouseId(Long houseId);

	public RentalHouse getHouseInfoByHouseIdAndOrgId(Long id, Long id2);

	public RentalHouse getHouseInfoByAdressAndOrganizationId(String houseCode,
			Long orgId);

	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo);

	public RentalHouse getHouseInfoByHouseId(Long id, Long emphasis);

	public RentalHouse getRentalHouseInfoByHouseId(Long id);

	/**
	 * 将旧出租房ID修改为新出租房ID
	 */
	public void updateRentalHouseInfoByNewHouseId(Long oldHouseId,
			Long newHouseId);

	/***
	 * 根据id批量删除
	 * 
	 * @param idList
	 */
	public void deleteHouseInfoByIds(List<Long> idList);
}
