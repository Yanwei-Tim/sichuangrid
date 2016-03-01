package com.tianque.baseInfo.householdStaff.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.householdStaff.commLog.CommonLog;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.service.util.PopulationCatalog;

public interface HouseholdStaffDao extends
		BaseInfoPopulationBaseDao<HouseholdStaff, SearchHouseInfoVo> {

	public List<HouseholdStaff> findHouseholdStaffList(
			HouseholdStaffVo householdStaffVo, Long orgId, String sidx,
			String sord, String shardCode);

	/**
	 * @description 根据户籍家庭ID查询户籍人员.
	 * @param id
	 * @return.
	 */
	public List<HouseholdStaff> findHouseholdIdByFamilyId(Long[] id,
			String shardCode);

	/**
	 * 根据 户口号 & orgId & 网格级别 查询
	 * 
	 * @param accountNumber
	 * @param org
	 * @param shardCode
	 * @return.
	 */
	public List<HouseholdStaff> findHouseholdStaffByAccountNumberAndorgIdAndInternalId(
			String accountNumber, Organization org, String shardCode);

	public List<HouseholdStaff> findHouseholdStaffByAccountNumberAndOrgId(
			String accountNumber, Long orgId, String shardCode);

	/**
	 * 根据户籍家庭ID&所属网格ID修改户籍人员户口号
	 * 
	 * @param accountNumber
	 * @param orgId
	 * @param familyId
	 * @return.
	 */
	public void updateAccountNumberByFamilyIdAndOrgId(String accountNumber,
			Long orgId, Long familyId, String shardCode);

	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgInternalCode(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	public PageInfo<HouseholdStaff> fastFindHouseholdStaffForPageByOrgInternalCode(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgInternalCodeAndId(
			HouseholdStaffVo householdStaffVo, String orgInternalCode, Long id,
			String houseHold, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode);

	/**
	 * 根据组织ID和身份证号码查询户籍人口信息
	 * 
	 * @param excludePopulationId
	 * @param orgId
	 * @param idCardNo15
	 * @param idCardNo18
	 * @return
	 */
	public List<HouseholdStaff> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18);

	/**
	 * 根据组织ID和身份证号码查询户籍人口信息包括注销死亡
	 * 
	 * @param excludePopulationId
	 * @param orgId
	 * @param idCardNo15
	 * @param idCardNo18
	 * @return
	 */
	public List<HouseholdStaff> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18);

	public List<HouseholdStaff> findGisHouseHoldStaffById(Long id);

	public PageInfo<HouseholdStaff> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, String personType, Integer page,
			Integer rows, String sidx, String sord, String shardCode);

	public PageInfo<HouseholdStaff> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, List personType, Integer page,
			Integer rows, String sidx, String sord, String shardCode);

	public PageInfo<HouseholdStaff> findHouseholdStaffByorgCodeForGis(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String shardCode);

	public void updateActualPopulationToHasHouseState(Long populationId,
			String address, String shardCode);

	public List<HouseholdStaff> findAllBindingHouseholdStaffByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode, String shardCode);

	public List<HouseholdStaff> findhouseholdStaffWhenIsOldFetchHouseIdForMark(
			int pageSize, String shardCode);

	public Integer countHouseholdStaffWhenIsOldFetchHouseId(String shardCode);

	public Integer countHouseholdStaffWhenIsOldFetchHouseIdForMark(
			String shardCode);

	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomenForMark(
			int pageSize, String shardCode);

	public Integer countHouseholdStaffWhenIsNurturesWomenForMark(
			String shardCode);

	/**
	 * 根据id和死亡，注销状态修改户籍人口
	 * 
	 * @param id
	 * @param death
	 * @param logoutState
	 */
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState, String shardCode);

	/**
	 * 根据人员id和houseInfo更新对应实口的房屋信息 更新之后发送jms消息，同步所有的业务人员的信息
	 * 
	 * @param populationId
	 * @param houseInfo
	 */
	public void updateActualPopulationToHasHouseState(Long populationId,
			HouseInfo houseInfo, String shardCode);

	public void updateFloatingPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason,
			String shardCode);

	public List<HouseholdStaff> findHouseholdStaffsExceptHeadByFamilyId(
			Long familyId, String houseHold, String shardCode);

	public void changeOldHouseHold(HouseholdStaff oldHouseHold, Long id,
			String shardCode);

	public void changeNewHouseHold(HouseholdStaff newHouseHold, Long id,
			String shardCode);

	public void addFamilyMemberByIdCardNo(String orgCode, Long familyId,
			String idCardNo, Long relationshipWithHeadId, String accountNumber,
			String shardCode);

	public HouseholdStaff getByOrgInternalCodeAndIdCardNo(
			String orgInternalCode, String idCardNo, String shardCode);

	public void moveHouseMember(String orgInternalCode, String idCardNo,
			Long relationshipWithHeadId, String accountNumber, Long familyId,
			String shardCode);

	public HouseholdStaff getHouseholdStaffByIdAndBusinessType(Long id,
			String type, String shardCode);

	/**
	 * 注销转为流动人口的户籍人口
	 * 
	 * @param logoutDetail
	 * @param id
	 */
	public void updateLogOutPopulationById(LogoutDetail logoutDetail, Long id,
			String shardCode);

	/**
	 * 将户主关系设为空
	 * 
	 * @param id
	 * @param relationShipWithHeadId
	 */
	public void setRelationShipWithHeadNull(Long id,
			Long relationShipWithHeadId, String shardCode);

	/**
	 * 户籍转流口时用
	 * 
	 * @param id
	 */
	public void deleteHouseHoldStaff(Long id);

	/**
	 * 户籍转流口时用
	 * 
	 * @param map
	 */
	public void updateGroupFamily(Map map);

	public Integer getCount(HouseholdStaffVo householdStaffVo, String shardCode);

	/**
	 * 根据baseinfoID和orgId获取户籍人员
	 * 
	 * @param baseInfoId
	 * @param orgId
	 * @return
	 */
	public HouseholdStaff getHouseholdStaffByBaseInfoId(Long baseInfoId,
			Long orgId, String shardCode);

	/**
	 * 根据baseinfoID获取户籍人员
	 * 
	 * @param baseInfoId
	 * @return
	 */
	public List<HouseholdStaff> getHouseholdStaffByBaseInfoId(Long baseInfoId,
			String shardCode);

	public void updateBaseHouseInfoAndRemark(HouseholdStaff householdStaff);

	/**
	 * 根据户籍id清空户籍信息
	 * 
	 * @param houseFamilyId
	 */
	public void deleteHouseholdStaffHouseFamilyInfo(Long houseFamilyId,
			String shardCode);

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultListTest(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			CommonLog commonLog, String shardCode);

	/**
	 * 根据baseinfoId修改出生日期和性别
	 * 
	 * @param baseinfoId
	 * @param birthday
	 * @param id
	 */
	public void updateBirthdayAndGender(Long baseinfoId, Date birthday,
			Long genderId, String shardCode);

	public Long findAllcount();

	public List<HouseholdStaff> findAllHouseholdStaffList(int start, int end);

	public HouseholdStaff getHouseholdStaffById(Long id);

	public HouseholdStaff addHouseholdStaff(HouseholdStaff householdStaff);

	public HouseholdStaff updateHouseholdStaff(HouseholdStaff householdStaff);

	public List<HouseholdStaff> findHouseholdStaffByIds(List<Long> ids,
			String shardCode);

	public void updateTableUpdateDateById(Long id);

	public List<Organization> findOrgByAddress(Long addressId, String shardCode);

	/**
	 * 按户口号查询baseinfoid （用于房屋/出租屋住户管理按户口号查询）
	 * 
	 * @param shardCode
	 * @param accountNumber
	 *            户口号
	 * @return
	 */
	public List<Long> findBaseinfoIdByAccountNumber(String shardCode,
			String accountNumber);
}
