package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;

public interface CensusRegisterFamilyDao {

	/**
	 * 删除户籍家庭
	 * 
	 * @param id
	 *            .
	 */
	public void deleteCensusRegisterFamilyById(Long id);

	/**
	 * 分页查询户籍家庭
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @return.
	 */
	public PageInfo<CensusRegisterFamily> findCensusRegisterFamilyForPageByOrgId(
			Long orgId, int pageNum, int pageSize);

	/**
	 * 修改户籍家庭
	 * 
	 * @param censusRegisterFamily
	 * @return.
	 */
	public CensusRegisterFamily updateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily);

	/**
	 * 根据身份证号&所属网格获取户籍家庭
	 * 
	 * @param cardNo
	 * @return.
	 */
	public CensusRegisterFamily findCensusRegisterFamilyByCardNoAndOrgId(
			List<String> idCardNoList, Long orgId);

	/**
	 * 根据身份证号&所属网格获取户籍家庭
	 * 
	 * @param cardNo
	 * @return.
	 */
	public CensusRegisterFamily findCensusRegisterFamilyByCardNoAndOrgId(
			String idCardNo, Long orgId);

	/**
	 * 根据户口号&所属网格ID获取户籍家庭
	 * 
	 * @param id
	 * @return.
	 */
	public CensusRegisterFamily findCensusRegisterFamilyByAccountNumberAndOrgId(
			String accountNumber, Long orgId);

	/**
	 * 根据ID查询户籍家庭
	 * 
	 * @param id
	 * @return.
	 */
	public CensusRegisterFamily findCensusRegisterFamilyById(Long id);

	/**
	 * 新增户籍家庭
	 * 
	 * @param censusRegisterFamily
	 * @return.
	 */
	public CensusRegisterFamily addCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily);

	void addFamilyHouse(Long familyId, Long propertydictid);

	void deleteFamilyHouseTypeByFamilyId(Long familyId);

	List<Long> getHouseFamilyTypeIdsByFamilyId(Long familyid);

	/**
	 * 修改户主身份证号码
	 * 
	 * @param censusRegisterFamily
	 * @return
	 */
	public CensusRegisterFamily updateCensusRegisterFamilyIdcardById(
			CensusRegisterFamily censusRegisterFamily);
}
