package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.CensusRegisterFamilyDao;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.exception.base.BusinessValidationException;

@Repository("censusRegisterFamilyDao")
@SuppressWarnings("unchecked")
public class CensusRegisterFamilyDaoImpl extends AbstractBaseDao implements
		CensusRegisterFamilyDao {

	@Override
	public void addFamilyHouse(Long familyId, Long propertydictid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyid", familyId);
		map.put("propertydictid", propertydictid);
		getSqlMapClientTemplate().insert(
				"censusRegisterFamily.addFamilyHouseType", map);
	}

	@Override
	public void deleteFamilyHouseTypeByFamilyId(Long familyId) {
		getSqlMapClientTemplate().delete(
				"censusRegisterFamily.deleteFamilyHouseTypeByFamilyId",
				familyId);
	}

	@Override
	public List<Long> getHouseFamilyTypeIdsByFamilyId(Long familyid) {
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"censusRegisterFamily.getHouseMarchTypeIds", familyid);
	}

	public void deleteCensusRegisterFamilyById(Long id) {
		if (null == id || 0 >= id) {
			return;
		}
		getSqlMapClientTemplate().delete(
				"censusRegisterFamily.deleteCensusRegisterFamilyById", id);
	}

	public PageInfo<CensusRegisterFamily> findCensusRegisterFamilyForPageByOrgId(
			Long orgId, int pageNum, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"censusRegisterFamily.countCensusRegisterFamily", map),
				getSqlMapClientTemplate().queryForList(
						"censusRegisterFamily.findCensusRegisterFamily", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	public CensusRegisterFamily updateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		getSqlMapClientTemplate().update(
				"censusRegisterFamily.updateCensusRegisterFamily",
				censusRegisterFamily);
		return findCensusRegisterFamilyById((long) censusRegisterFamily.getId());
	}

	@Override
	public CensusRegisterFamily findCensusRegisterFamilyByCardNoAndOrgId(
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNoList", idCardNoList);
		map.put("orgId", orgId);
		CensusRegisterFamily censusRegisterFamily = (CensusRegisterFamily) getSqlMapClientTemplate()
				.queryForObject(
						"censusRegisterFamily.findCensusRegisterFamilyByCardNoAndOrgId",
						map);
		return censusRegisterFamily;
	}

	@Override
	public CensusRegisterFamily findCensusRegisterFamilyByCardNoAndOrgId(
			String idCardNo, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("orgId", orgId);
		return (CensusRegisterFamily) getSqlMapClientTemplate()
				.queryForObject(
						"censusRegisterFamily.findCensusRegisterFamilyByIdCardNoAndOrgId",
						map);
	}

	@Override
	public CensusRegisterFamily findCensusRegisterFamilyByAccountNumberAndOrgId(
			String accountNumber, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNumber", accountNumber);
		map.put("orgId", orgId);
		return (CensusRegisterFamily) getSqlMapClientTemplate()
				.queryForObject(
						"censusRegisterFamily.findCensusRegisterFamilyByAccountNumberAndOrgId",
						map);
	}

	@Override
	public CensusRegisterFamily findCensusRegisterFamilyById(Long id) {
		return (CensusRegisterFamily) getSqlMapClientTemplate().queryForObject(
				"censusRegisterFamily.findCensusRegisterFamilyById", id);
	}

	@Override
	public CensusRegisterFamily addCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"censusRegisterFamily.addCensusRegisterFamily",
				censusRegisterFamily);
		return findCensusRegisterFamilyById(id);
	}

	private PageInfo<CensusRegisterFamily> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<CensusRegisterFamily> list) {
		PageInfo<CensusRegisterFamily> pageInfo = new PageInfo<CensusRegisterFamily>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private void checkAddCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		if (censusRegisterFamily == null
				|| censusRegisterFamily.getAccountNumber() == null
				|| "".equals(censusRegisterFamily.getAccountNumber().trim())) {
			throw new BusinessValidationException("户口号不能为空!");
		}
		if (censusRegisterFamily.getAccountNumber().length() > 50) {
			throw new BusinessValidationException("户口号长度不能大于50位!");
		}
		if (censusRegisterFamily.getIdCardNo() != null
				&& censusRegisterFamily.getIdCardNo().length() > 18) {
			throw new BusinessValidationException("身份证号码长度不能大于18位!");
		}
		if (censusRegisterFamily.getHouseholdName() != null
				&& censusRegisterFamily.getHouseholdName().length() > 20) {
			throw new BusinessValidationException("户主姓名长度不能大于20位!");
		}
		if (censusRegisterFamily.getProvince() != null
				&& censusRegisterFamily.getProvince().length() > 20) {
			throw new BusinessValidationException("户籍地址省长度不能大于20位!");
		}
		if (censusRegisterFamily.getCity() != null
				&& censusRegisterFamily.getCity().length() > 20) {
			throw new BusinessValidationException("户籍地址市长度不能大于20位!");
		}
		if (censusRegisterFamily.getDistrict() != null
				&& censusRegisterFamily.getDistrict().length() > 20) {
			throw new BusinessValidationException("户籍地址区县长度不能大于20位!");
		}
		if (censusRegisterFamily.getHomePhone() != null
				&& censusRegisterFamily.getHomePhone().length() > 20) {
			throw new BusinessValidationException("住宅电话长度不能大于20位!");
		}
		if (censusRegisterFamily.getOrganization() == null
				|| censusRegisterFamily.getOrganization().getId() == null) {
			throw new BusinessValidationException("所属网格不能为空!");
		}
	}

	@Override
	public CensusRegisterFamily updateCensusRegisterFamilyIdcardById(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		getSqlMapClientTemplate().update(
				"censusRegisterFamily.updateCensusRegisterFamilyIdcardById",
				censusRegisterFamily);
		return findCensusRegisterFamilyById((long) censusRegisterFamily.getId());
	}

}
