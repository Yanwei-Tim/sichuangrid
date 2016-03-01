package com.tianque.baseInfo.householdStaff.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.householdStaff.commLog.CommonLog;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public interface HouseholdStaffService extends
		BaseInfoPopulationTemplateService {

	public HouseholdStaff findHouseholdStaffByCardNoAndOrgId(String idCardNo,
			Long orgId);

	public List<HouseholdStaff> findHouseholdStaffList(
			HouseholdStaffVo householdStaffVo, Long orgId, String sidx,
			String sord);

	public void deleteHouseholdStaffByIds(Long[] id);

	public void regrantFamilyHouse(HouseholdStaff population,
			List<Long> familyHouseIdList);

	public void addFamilyHouse(HouseholdStaff population,
			List<Long> familyHouseIdList);

	public List<PropertyDict> getHouseFamilyTypeList(
			HouseholdStaff householdStaff);

	/**
	 * @description 根据与户主关系id 查询是否选择是户主.
	 * @param id
	 * @return.
	 */
	public boolean whetherHousehold(Long id);

	/**
	 * 根据 户口号 & orgId & 网格级别(镇级) 查询该户口号是否在该网格级别已存在
	 * 
	 * @param accountNumber
	 * @param orgId
	 * @param internalId
	 * @return.
	 */
	public void accountNumberInTheOrgLevelIsPresent(String accountNumber,
			Long orgId);

	/**
	 * 根据户籍家庭ID删除户籍人员
	 * 
	 * @param id
	 * @param orgId
	 *            .
	 */
	public void deleteHouseholdStaffByFamilyId(Long familyId, Long orgId);

	/**
	 * 分页查询
	 * 
	 * @param householdStaff
	 *            .
	 * @param pageNum
	 *            .
	 * @param pageSize
	 *            .
	 */
	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgId(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<HouseholdStaff> fastFindHouseholdStaffForPageByOrgId(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultListTest(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, CommonLog commonLog);

	/**
	 * 分页查询
	 * 
	 * @param householdStaff
	 *            .
	 * @param pageNum
	 *            .
	 * @param pageSize
	 *            .
	 */
	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgIdAndId(
			HouseholdStaffVo householdStaffVo, Long orgId, Long id,
			Boolean exceptHead, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 根据户籍人员ID删除人员基本信息
	 * 
	 * @param id
	 *            .
	 */
	public void deleteHouseholdStaffById(Long id);

	/**
	 * 户籍人员基本信息修改
	 * 
	 * @param householdStaff
	 * @return.
	 */
	public HouseholdStaff updateHouseholdStaff(HouseholdStaff householdStaff);

	/**
	 * 户籍人员基本信息修改
	 * 
	 * @param householdStaff
	 * @return.
	 */
	public HouseholdStaff transferHousePopulationBusinessInfo(
			HouseholdStaff householdStaff);

	public HouseholdStaff getHouseholdStaffById(Long id);

	/**
	 * 根据户籍家庭ID&所属网格ID修改户籍人员户口号
	 * 
	 * @param accountNumber
	 * @param orgId
	 * @param familyId
	 * @return.
	 */
	public void updateAccountNumberByFamilyIdAndOrgId(String accountNumber,
			Long orgId, Long familyId);

	/**
	 * 根据身份证号&所属网格查询户籍人员是否存在
	 * 
	 * @param cardNo
	 * @return.
	 */
	public boolean hasDuplicateHouseholdStaff(String idCardNo, Long orgId,
			Long id);

	/**
	 * 新增户籍人员
	 * 
	 * @param householdStaff
	 * @return.
	 */
	public HouseholdStaff addHouseholdStaff(HouseholdStaff householdStaff);

	/**
	 * 添加户籍人员
	 * 
	 * @param importHouseholdStaff
	 * @return.
	 */
	public HouseholdStaff setHouseholdStaff(HouseholdStaff householdStaff);

	/**
	 * 
	 * @Title: addHouseholdStaffForMobile
	 * @Description: TODO提取新方法。新增户籍人口
	 * @param @param householdStaff
	 * @param @return 设定文件
	 * @return HouseholdStaff 返回类型
	 * @author wanggz
	 * @date 2014-6-18 下午03:00:08
	 */
	public HouseholdStaff addHouseholdStaffForMobile(
			HouseholdStaff householdStaff);

	public List<HouseholdStaff> updateDeathOfHouseholdStaffByIdList(
			List<Long> idList, Boolean death);

	public List<Long> updateLogOutOfHouseholdStaffByIds(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids);

	public HouseholdStaff updateHousePopulationBusinessInfo(
			HouseholdStaff population);

	public List<HouseholdStaff> findhouseholdStaffWhenIsOldFetchHouseId(
			int pageSize, String shardCode);

	public Integer countHouseholdStaffWhenIsOldFetchHouseId(String shardCode);

	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomen(
			int pageSize, String shardCode);

	public Integer countHouseholdStaffWhenIsNurturesWomen(String shardCode);

	public List<HouseholdStaff> findHouseholdStaffsExceptHeadByFamilyId(
			Long familyId, Long orgId);

	public void addFamilyMemberByIdCardNo(Long orgId, Long familyId,
			String idCardNo, Long relationshipWithHeadId, String accountNumber);

	/**
	 * 当存在时1.根据acountNumber获得familyId 2.
	 * 根据idCardNo和orgInternalCode更新houseHoldStaff表的accountNumber及familyId及relationshipwithhead字段
	 * 当不存在时1.创建一个户籍家庭
	 */
	public void moveHouseMember(Long orgId, String idCardNo, Long id,
			String accountNumber);

	/**
	 * 获取户籍人口信息
	 * 
	 * @param idcardNo
	 * @param claimOrgId
	 * @return
	 */
	public HouseholdStaff getHouseholdStaffByIdCardNo(String idCardNo,
			Long claimOrgId);

	/**
	 * 根据业务人员ID和类型查找户籍人口
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public HouseholdStaff getHouseholdStaffByIdAndBusinessType(Long id,
			String type, Long orgId);

	/**
	 * 更新户籍人员业务信息和五好家庭
	 * 
	 * @param population
	 *            户籍人员信息
	 * @param houseMarchTypeList
	 *            五好家庭
	 * @return
	 */
	public HouseholdStaff updateHousePopulationBusinessInfo(
			HouseholdStaff population, List<Long> houseMarchTypeList);

	public HouseholdStaff updateHouseholdStaffBaseInfo(HouseholdStaff population);

	public HouseholdStaff addHouseholdStaffBaseInfo(HouseholdStaff population);

	/**
	 * 转为流动人口
	 * 
	 * @param id
	 */
	public void toFloatingPopulation(Long id);

	/**
	 * 转为流动人口
	 * 
	 * @param id
	 */
	public void toFloatingPopulationByIds(List<Long> ids);

	public Countrymen copyAndUpdate(HouseholdStaff householdStaff);

	/**
	 * 清空户主信息
	 * 
	 * @param family
	 */
	public void emptyCensusRegisterFamily(CensusRegisterFamily family);

	public Integer getCount(HouseholdStaffVo householdStaffVo);

	/**
	 * 根据baseinfoID获取户籍人员
	 * 
	 * @param baseInfoId
	 * @param orgId
	 * @return
	 */
	public HouseholdStaff getHouseholdStaffByBaseInfoId(Long baseInfoId,
			Long orgId);

	/**
	 * 根据baseinfoID获取户籍人员
	 * 
	 * @param baseInfoId
	 * @return
	 */
	public List<HouseholdStaff> getHouseholdStaffByBaseInfoId(Long baseInfoId);

	public boolean getActualPopulationHasTypes(Long id);

	/**
	 * 根据户籍家庭id清楚户籍信息
	 * 
	 * @param id
	 */
	public void deleteHouseholdStaffHouseFamilyInfo(Long houseFamilyId,
			Long orgId);

	public boolean moveMongodb(Integer beginId);

	public void moveToShardTable();

	public List<HouseholdStaff> findHouseholdStaffByIds(List<Long> ids,
			String shardCode);

	/** job optimize */
	public List<HouseholdStaff> findhouseholdStaffWhenIsOldOptmize(
			List<String> shardCodes, int fetchNum);

	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomenOptmize(
			List<String> shardCodes, int fetchNum);

	public Organization findOrgByAddress(List<String> shardCodes, Long address);

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
