package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.utils.Utils;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.CensusRegisterFamilyDao;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.CensusRegisterFamilyService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("censusRegisterFamilyService")
public class CensusRegisterFamilyServiceImpl implements
		CensusRegisterFamilyService {

	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CensusRegisterFamilyDao censusRegisterFamilyDao;
	@Autowired
	private PropertyDictService propertyDictService;

	public void deleteCensusRegisterFamilyById(Long id, Long orgId) {
		if (null == id || 0 >= id) {
			return;
		}
		householdStaffService.deleteHouseholdStaffByFamilyId(id, orgId);
		censusRegisterFamilyDao.deleteCensusRegisterFamilyById(id);
	}

	public PageInfo<CensusRegisterFamily> findCensusRegisterFamilyForPageByOrgId(
			Long orgId, int pageNum, int pageSize) {
		PageInfo<CensusRegisterFamily> censusRegisterFamilyList = censusRegisterFamilyDao
				.findCensusRegisterFamilyForPageByOrgId(orgId, pageNum,
						pageSize);
		censusRegisterFamilyList
				.setResult(setHouseMarchListForList(censusRegisterFamilyList
						.getResult()));
		return censusRegisterFamilyList;
	}

	public CensusRegisterFamily getCensusRegisterFamilyById(Long id) {
		if (null == id || 0 >= id) {
			return null;
		}
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.findCensusRegisterFamilyById(id));
	}

	public CensusRegisterFamily updateCensusRegisterFamilyById(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		if (censusRegisterFamily.getId() == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkUpdateCensusRegisterFamily(censusRegisterFamily);
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.updateCensusRegisterFamily(censusRegisterFamily));
	}

	public CensusRegisterFamily updateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		CensusRegisterFamily cFamily = complexValue(censusRegisterFamily);
		checkUpdateCensusRegisterFamily(cFamily);
		householdStaffService.updateAccountNumberByFamilyIdAndOrgId(
				cFamily.getAccountNumber(), cFamily.getOrganization().getId(),
				cFamily.getId());

		autoFill(cFamily);
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.updateCensusRegisterFamily(cFamily));
	}

	private CensusRegisterFamily complexValue(
			CensusRegisterFamily censusRegisterFamily) {
		CensusRegisterFamily cFamily = getCensusRegisterFamilyById(censusRegisterFamily
				.getId());
		if (cFamily == null) {
			throw new BusinessValidationException(
					"censusRegisterFamily.getId()不能为空!");
		}
		cFamily.setIdCardNo(censusRegisterFamily.getIdCardNo());
		cFamily.setAccountNumber(censusRegisterFamily.getAccountNumber());
		cFamily.setHouseholdName(censusRegisterFamily.getHouseholdName());
		cFamily.setHomePhone(censusRegisterFamily.getHomePhone());
		cFamily.setProvince(censusRegisterFamily.getProvince());
		cFamily.setCity(censusRegisterFamily.getCity());
		cFamily.setDistrict(censusRegisterFamily.getDistrict());
		cFamily.setMobileNumber(censusRegisterFamily.getMobileNumber());
		cFamily.setTelePhone(censusRegisterFamily.getTelePhone());
		if (censusRegisterFamily.getCurrentAddress() != null) {
			cFamily.setCurrentAddress(censusRegisterFamily.getCurrentAddress());
		}
		cFamily.setOrgInternalCode(censusRegisterFamily.getOrgInternalCode());
		return cFamily;
	}

	private void updateIdCardNoExist(CensusRegisterFamily censusRegisterFamily) {
		if (hasDuplicateCensusRegisterFamily(
				censusRegisterFamily.getIdCardNo(), censusRegisterFamily
						.getOrganization().getId(),
				censusRegisterFamily.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码!");
		}
	}

	public boolean accountNumberWhetherExist(String accountNumber, Long orgId,
			Long id) {
		CensusRegisterFamily family = getCensusRegisterFamilyByOrgIdAndAccountNumber(
				accountNumber, orgId);
		if (family == null) {
			return false;
		}
		return id == null ? family != null : (family != null && !id
				.equals(family.getId()));
	}

	private void updateAccountNumberIfThere(
			CensusRegisterFamily censusRegisterFamily) {
		CensusRegisterFamily family = getCensusRegisterFamilyByOrgIdAndAccountNumber(
				censusRegisterFamily.getAccountNumber(), censusRegisterFamily
						.getOrganization().getId());
		if (family != null
				&& !family.getId().equals(censusRegisterFamily.getId())) {
			throw new BusinessValidationException("该网格下已存在相同户口号!");
		}
	}

	private void checkUpdateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		updateIdCardNoExist(censusRegisterFamily);
		updateAccountNumberIfThere(censusRegisterFamily);
	}

	@Override
	public CensusRegisterFamily addCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		checkAccountNumberIsRepeat(censusRegisterFamily.getAccountNumber(),
				censusRegisterFamily.getOrganization().getId());
		checkCardNoIsRepeat(censusRegisterFamily.getIdCardNo(),
				censusRegisterFamily.getOrganization().getId());
		// householdStaffService.checkCardNoIsRepeat(censusRegisterFamily
		// .getIdCardNo(), censusRegisterFamily.getOrganization().getId());
		autoFill(censusRegisterFamily);
		return censusRegisterFamilyDao
				.addCensusRegisterFamily(censusRegisterFamily);
	}

	private void autoFill(CensusRegisterFamily censusRegisterFamily) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(censusRegisterFamily.getOrganization()
						.getId());
		censusRegisterFamily.setOrgInternalCode(organization
				.getOrgInternalCode());
	}

	public CensusRegisterFamily getCensusRegisterFamilyByOrgIdAndAccountNumber(
			String accountNumber, Long orgId) {
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.findCensusRegisterFamilyByAccountNumberAndOrgId(accountNumber,
						orgId));
	}

	private void checkAccountNumberIsRepeat(String accountNumber, Long orgId) {
		if (getCensusRegisterFamilyByOrgIdAndAccountNumber(accountNumber, orgId) != null) {
			throw new BusinessValidationException("该网格下已存在相同户口号!");
		}
	}

	public CensusRegisterFamily getCensusRegisterFamilyByOrgIdAndIdCardNo(
			String idCardNo, Long orgId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		// List<String> idCardNoList = new ArrayList<String>();
		// idCardNoList.add(idCardNo);
		// if (idCardNo.length() == 18) {
		// idCardNoList.add(IdCardUtil.idCardNo18to15(idCardNo));
		// } else if (idCardNo.length() == 15) {
		// idCardNoList.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
		// idCardNoList.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		// }
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.findCensusRegisterFamilyByCardNoAndOrgId(idCardNo, orgId));
	}

	private void checkCardNoIsRepeat(String idCardNo, Long orgId) {
		if (getCensusRegisterFamilyByOrgIdAndIdCardNo(idCardNo, orgId) != null) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码的户主!");
		}
	}

	public boolean hasDuplicateCensusRegisterFamily(String idCardNo,
			Long orgId, Long id) {
		CensusRegisterFamily censusRegisterFamily = getCensusRegisterFamilyByOrgIdAndIdCardNo(
				idCardNo, orgId);
		return id == null ? censusRegisterFamily != null
				: (censusRegisterFamily != null && !id
						.equals(censusRegisterFamily.getId()));
	}

	public void checkAddCensusRegisterFamily(
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
				&& !"".equals(censusRegisterFamily.getIdCardNo().trim())) {
			ValidateHelper validateHelper = new ValidateHelper();
			boolean idCardIllegal = (validateHelper
					.illegalIdcard(censusRegisterFamily.getIdCardNo()));
			if (idCardIllegal) {
				throw new BusinessValidationException("户主身份证号码输入不正确!");
			}
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
	public void addFamilyHouse(Long familyId, Long typeId) {
		censusRegisterFamilyDao.addFamilyHouse(familyId, typeId);
	}

	@Override
	public void deleteFamilyHouseTypeByFamilyId(Long familyId) {
		censusRegisterFamilyDao.deleteFamilyHouseTypeByFamilyId(familyId);
	}

	@Override
	public List<PropertyDict> getHouseFamilyTypesByFamilyId(Long familyid) {
		List<Long> typeIds = censusRegisterFamilyDao
				.getHouseFamilyTypeIdsByFamilyId(familyid);
		Long[] ids = new Long[typeIds.size()];
		for (int i = 0; i < typeIds.size(); i++) {
			ids[i] = typeIds.get(i);
		}
		return propertyDictService.findPropertyDictByIds(ids);
	}

	@Override
	public CensusRegisterFamily updateCensusRegisterFamilyIdcardById(
			CensusRegisterFamily censusRegisterFamily) {
		checkAddCensusRegisterFamily(censusRegisterFamily);
		if (censusRegisterFamily.getId() == null) {
			throw new BusinessValidationException(
					"censusRegisterFamily.getId()不能为空!");
		}
		return setHouseMarchListForObj(censusRegisterFamilyDao
				.updateCensusRegisterFamilyIdcardById(censusRegisterFamily));
	}

	// 为单个实体设置字典项列表
	private CensusRegisterFamily setHouseMarchListForObj(
			CensusRegisterFamily censusRegisterFamily) {
		if (censusRegisterFamily != null
				&& censusRegisterFamily.getId() != null) {
			List<Long> familyTypedictIdList = censusRegisterFamilyDao
					.getHouseFamilyTypeIdsByFamilyId(censusRegisterFamily
							.getId());
			if (familyTypedictIdList != null && familyTypedictIdList.size() > 0) {
				censusRegisterFamily.setHouseMarchList(propertyDictService
						.findPropertyDictByIds(Utils
								.analyticalIds(familyTypedictIdList)));
			}
		}
		return censusRegisterFamily;
	}

	// 为List中的对象设置字典项列表
	private List<CensusRegisterFamily> setHouseMarchListForList(
			List<CensusRegisterFamily> censusRegisterFamilies) {
		if (censusRegisterFamilies != null && censusRegisterFamilies.size() > 0) {
			for (CensusRegisterFamily censusRegisterFamily : censusRegisterFamilies) {
				censusRegisterFamily = setHouseMarchListForObj(censusRegisterFamily);
			}
		}
		return censusRegisterFamilies;
	}

}
