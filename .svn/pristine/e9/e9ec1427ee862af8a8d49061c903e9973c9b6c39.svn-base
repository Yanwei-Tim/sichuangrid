package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.PropertyDict;

public interface CensusRegisterFamilyService {

	/**
	 * 删除户籍家庭
	 * 
	 * @param id
	 *            .
	 */
	public void deleteCensusRegisterFamilyById(Long id, Long orgId);

	public PageInfo<CensusRegisterFamily> findCensusRegisterFamilyForPageByOrgId(
			Long orgId, int pageNum, int pageSize);

	public CensusRegisterFamily getCensusRegisterFamilyById(Long id);

	public void checkAddCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily);

	/**
	 * 修改户籍家庭
	 * 
	 * @param censusRegisterFamily
	 * @return.
	 */
	public CensusRegisterFamily updateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily);

	/**
	 * 根据户口号&属所网格获取户籍家庭
	 * 
	 * @param accountNumber
	 * @param orgId
	 * @return.
	 */
	public CensusRegisterFamily getCensusRegisterFamilyByOrgIdAndAccountNumber(
			String accountNumber, Long orgId);

	/**
	 * 新增户籍家庭
	 * 
	 * @param censusRegisterFamily
	 * @return.
	 */
	public CensusRegisterFamily addCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily);

	public void addFamilyHouse(Long id, Long fhi);

	public void deleteFamilyHouseTypeByFamilyId(Long id);

	public List<PropertyDict> getHouseFamilyTypesByFamilyId(Long id);

	/**
	 * 修改户籍家庭信息
	 * 
	 * @param censusRegisterFamily
	 * @return
	 */
	public CensusRegisterFamily updateCensusRegisterFamilyIdcardById(
			CensusRegisterFamily censusRegisterFamily);
}
