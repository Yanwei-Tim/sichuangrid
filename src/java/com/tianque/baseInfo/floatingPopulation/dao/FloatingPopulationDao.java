package com.tianque.baseInfo.floatingPopulation.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchFloatingPopulationVo;
import com.tianque.service.util.PopulationCatalog;

public interface FloatingPopulationDao
		extends
		BaseInfoPopulationBaseDao<FloatingPopulation, SearchFloatingPopulationVo> {

	public FloatingPopulation findFloatingPopulationByCardNoAndOrgId(
			List<String> idCardNoList, Long orgId);

	public FloatingPopulation addFloatingPopulation(
			FloatingPopulation floatingPopulation);

	public PageInfo<FloatingPopulation> searchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public PageInfo<FloatingPopulation> fastSearchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public List<FloatingPopulation> searchAllFloatingPopulations(String sidx,
			String sord, SearchFloatingPopulationVo searchFloatingPopulationVo);

	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long logOut, Boolean isDeath);

	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgInternalCodeDefaultList(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long logOut, Boolean isDeath);

	public FloatingPopulation updateFloatingPopulation(
			FloatingPopulation floatingPopulation);

	public FloatingPopulation getFloatingPopulationById(Long id);

	public void deleteFloatingPopulationById(Long id);

	public FloatingPopulation getFloatingPopulationByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId);

	public FloatingPopulation updateFloatingPopulationBusinessInfo(
			FloatingPopulation population);

	public FloatingPopulation updateFloatingPopulationBaseInfo(
			FloatingPopulation population);

	public List<FloatingPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18);

	public List<FloatingPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18);

	public List<FloatingPopulation> findGisHouseHoldStaffById(Long id);

	public PageInfo<FloatingPopulation> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, String personType, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<FloatingPopulation> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			String orgInternalCode, List personType, Integer page,
			Integer rows, String sidx, String sord);

	public void updateActualPopulationToHasHouseState(Long populationId,
			String address);

	public Boolean updateActualPopulationToHasHouseState(
			FloatingPopulation floatingPopulation);

	public PageInfo<FloatingPopulation> findFloatingPopulationByorgCodeForGis(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List<FloatingPopulation> findAllBindingFloatingPopulationByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode);

	public List<FloatingPopulation> findFloatingPopulationsWhenIsOldForMark(
			int pageSize, Organization org);

	public Integer countFloatingPopulationsWhenIsOldForMark();

	public void updateDeathStateById(Long id, Boolean death);

	public List<FloatingPopulation> findFloatingPopulationsWhenIsNurtuesWomenForMark(
			int pageSize, Organization org);

	public Integer countFloatingPopulationsWhenIsNurtuesWomenForMark(String shardCode);

	/**
	 * 根据人口id和房屋信息houseInfo信息更新实口的人口的住房信息 更新实口信息之后 发送jms消息，同步其他的信息
	 * 
	 * @param populationId
	 * @param houseInfo
	 */
	public void updateActualPopulationToHasHouseState(Long populationId,
			HouseInfo houseInfo);

	/**
	 * 从房屋角度删除人员时，更新人员的房屋信息
	 * 
	 * @param populationId
	 * @param isHaveHouse
	 * @param noHouseReason
	 */
	public void updateFloatingPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason);

	public FloatingPopulation getFloatingPopulationByIdAndBusinessType(Long id,
			String type);

	/**
	 * 注销转为户籍人口的流动人口
	 * 
	 * @param logoutDetail
	 * @param id
	 */
	public void updateLogOutPopulationById(LogoutDetail logoutDetail, Long id);

	/**
	 * 流口转户籍时用
	 * 
	 * @param id
	 */
	public void deleteFloatingPopulation(Long id);

	/**
	 * 流口转户籍时用
	 * 
	 * @param map
	 */
	public void updateGroupFamily(Map map);

	/**
	 * 根据baseinfoid和orgId获取流动人员
	 * 
	 * @return
	 */
	public FloatingPopulation getFloatingPopulationByBaseInfoId(
			Long baseInfoId, Long orgId);

	/**
	 * 根据baseinfoid获取流动人员
	 * 
	 * @return
	 */
	public List<FloatingPopulation> getFloatingPopulationByBaseInfoId(
			Long baseInfoId);

	public Integer getCount(
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public void updateBaseHouseInfoAndRemark(FloatingPopulation population);

	/**
	 * 获得到期的流动人口
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<FloatingPopulation> findFloatingPopulationByExpectedDatedue(
			int pageNum, int pageSize, Long remindTime);

	public Integer countFloatingPopulationByExpectedDatedue(Long remindTime);

	public FloatingPopulation updateIsRemindByid(Long id);

	/**
	 * 修改出生日期和性别
	 * 
	 * @param baseInfoId
	 * @param birthday
	 * @param genderId
	 */
	public void updateBirthdayAndGender(Long baseInfoId, Date birthday,
			Long genderId);

	public List<FloatingPopulation> findFloatingPopulationByIds(List<Long> ids);

	public void updateTableUpdateDateById(Long id);

	/**
	 * @param addressId
	 * @return
	 */
	public List<Organization> findOrgByAddress(Long addressId);
}