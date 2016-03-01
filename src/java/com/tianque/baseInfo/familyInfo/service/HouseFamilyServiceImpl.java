package com.tianque.baseInfo.familyInfo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.familyInfo.dao.HouseFamilyDao;
import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.householdStaff.dao.HouseholdStaffDao;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.utils.PopulationCopyUtil;
import com.tianque.baseInfo.utils.Utils;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchFamilyHouseVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("houseFamilyService")
@Transactional
public class HouseFamilyServiceImpl implements HouseFamilyService {

	@Autowired
	private HouseFamilyDao houseFamilyDao;

	@Autowired
	private HouseholdStaffDao houseHoldStaffDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private HouseholdStaffService houseHoldStaffService;

	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private ShardConversion shardConversion;

	@Autowired
	private PropertyDictService propertyDictService;

	private static final String HEADOFHOUSEHOLDID = "户主";

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, final String sord) {
		PageInfo<HouseFamily> results = houseFamilyDao.findHouseFamilyByOrgId(
				organizationDubboService.getSimpleOrgById(orgId), pageNum,
				pageSize, "id", "desc", shardConversion.getShardCode(orgId),
				getHeadOfHouseHoldId(HEADOFHOUSEHOLDID));
		return dealHouseFamily(results, sord, sidx);
	}

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		return houseFamilyDao.findHouseFamilyByOrgIdAndSearchCondition(
				searchFamilyHouseVo,
				organizationDubboService.getSimpleOrgById(orgId), pageNum,
				pageSize, sidx, sord, shardConversion.getShardCode(orgId),
				getHeadOfHouseHoldId(HEADOFHOUSEHOLDID));
	}

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndMinuteSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, final String sord) {
		if (null == searchFamilyHouseVo) {
			return findHouseFamilyByOrgId(orgId, pageNum, pageSize, sidx, sord);
		}
		PageInfo<HouseFamily> results = houseFamilyDao
				.findHouseFamilyByOrgIdAndMinuteSearchCondition(
						searchFamilyHouseVo,
						organizationDubboService.getSimpleOrgById(orgId),
						pageNum, pageSize, sidx, sord,
						shardConversion.getShardCode(orgId),
						getHeadOfHouseHoldId(HEADOFHOUSEHOLDID));
		return dealHouseFamily(results, sord, sidx);
	}

	public PageInfo<HouseFamily> dealHouseFamily(PageInfo<HouseFamily> results,
			final String sord, String sidx) {
		List<HouseFamily> houseFamilys = results.getResult();
		for (HouseFamily houseFamily : houseFamilys) {
			String idCardNo = houseFamily.getIdCardNo();
			if (idCardNo != null && !"".equals(idCardNo)) {
				Countrymen countrymen = baseInfoService
						.getBaseInfoByIdCardNo(idCardNo);
				if (countrymen != null) {
					PopulationCopyUtil.copyBaseInfoMessage(countrymen,
							houseFamily);
					HouseholdStaff householdStaff = houseHoldStaffService
							.getHouseholdStaffByBaseInfoId(countrymen.getId(),
									houseFamily.getOrganization().getId());
					if (householdStaff != null) {
						houseFamily.setId(householdStaff.getId());
					}
				}
			}
			if (StringUtil.isStringAvaliable(houseFamily.getOrgInternalCode())) {
				houseFamily.getCensusRegisterFamily().setOrgInternalCode(
						houseFamily.getOrgInternalCode());
			}
		}
		final String sidex;
		if ("hou.workUnit".equals(sidx)) {
			sidex = "workUnit";
		} else if ("hou.mobileNumber".equals(sidx)) {
			sidex = "mobileNumber";
		} else if ("hou.telephone".equals(sidx)) {
			sidex = "telephone";
		} else {
			sidex = sidx;
		}
		if (!"cen.id".equals(sidx)) {
			Collections.sort(houseFamilys, new Comparator<HouseFamily>() {
				@Override
				public int compare(HouseFamily o1, HouseFamily o2) {
					if ("asc".equals(sord.toLowerCase())) {
						Object obj = invokeMemthod(o1);
						if (obj instanceof Long) {
							Object objTemp = invokeMemthod(o2);
							if (objTemp instanceof Long) {
								return (Long) obj > (Long) objTemp ? 1 : -1;
							} else {
								return 1;
							}
						} else {
							return obj.toString().compareTo(
									invokeMemthod(o2).toString());
						}
					} else {
						Object obj = invokeMemthod(o2);
						if (obj instanceof Long) {
							Object objTemp = invokeMemthod(o1);
							if (objTemp instanceof Long) {
								return (Long) obj > (Long) objTemp ? 1 : -1;
							} else {
								return 1;
							}

						} else {
							return obj.toString().compareTo(
									invokeMemthod(o1).toString());
						}
					}
				}

				private Object invokeMemthod(Object arg0) {
					Object obj = null;
					try {
						obj = arg0
								.getClass()
								.getMethod(
										"get"
												+ (sidex.charAt(0) + "")
														.toUpperCase()
												+ sidex.substring(1))
								.invoke(arg0);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					if (obj instanceof PropertyDict) {
						obj = ((PropertyDict) obj).getId();
					}
					if (obj instanceof Date) {
						obj = ((Date) obj).getTime();
					}
					return obj == null ? 0 : obj;
				}
			});
		}

		return results;
	}

	@Override
	public List<HouseFamily> findFamilyMemberInfoById(Long familyId) {
		HouseFamily houseFamily = houseFamilyDao
				.findHouseFamilyByIdAndOrgId(familyId);
		List<PropertyDict> houseMarchTypeList = getHouseMarchTypeByFamilyId(familyId);
		houseFamily.setHouseMarchType(houseMarchTypeList);
		List<HouseFamily> houseFamilyList = houseFamilyDao
				.findFamilyMemberInforById(familyId, shardConversion
						.getShardCode(houseFamily.getOrganization().getId()));
		if (houseFamilyList != null && houseFamilyList.size() > 0) {
			houseFamilyList.get(0).setOrganization(
					organizationDubboService.getSimpleOrgById(houseFamilyList
							.get(0).getOrganization().getId()));
			houseFamilyList.get(0).setHouseMarchType(houseMarchTypeList);
		}
		return houseFamilyList;
	}

	@Override
	public HouseFamily findHouseFamilyById(Long familyId) {
		HouseFamily houseFamily = houseFamilyDao
				.findHouseFamilyByIdAndOrgId(familyId);
		List<PropertyDict> houseMarchTypeList = getHouseMarchTypeByFamilyId(familyId);
		houseFamily.setHouseMarchType(houseMarchTypeList);
		houseFamily.setOrganization(organizationDubboService
				.getSimpleOrgById(houseFamily.getCensusRegisterFamily()
						.getOrganization().getId()));
		return houseFamily;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.HOUSEHOLDFAMILY_KEY)
	public void deleteHouseFamilyByIds(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("id没有获得");
		}
		String[] strs = ids.split(",");
		for (String idStr : strs) {
			long id = Long.parseLong(idStr);
			deleteHouseholdStaffHouseFamilyInfo(id);
			deleteHouseFamilyById(id);
		}
	}

	/**
	 * 删除户籍人口的户籍家庭信息
	 * 
	 * @param ids
	 */
	private void deleteHouseholdStaffHouseFamilyInfo(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			HouseFamily houseFamily = houseFamilyDao
					.findHouseFamilyByIdAndOrgId(id);
			List<PropertyDict> houseMarchTypeList = getHouseMarchTypeByFamilyId(id);
			houseFamily.setHouseMarchType(houseMarchTypeList);
			houseHoldStaffService.deleteHouseholdStaffHouseFamilyInfo(id,
					houseFamily.getOrganization().getId());
		} catch (Exception e) {
			throw new ServiceValidationException("根据户籍家庭清除户籍人口户籍家庭信息出现错误", e);
		}

	}

	private void deleteHouseFamilyById(long id) {
		try {
			houseFamilyDao.deleteHouseFamilyById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HouseFamilyServiceImpl的deleteHouseFamilyById方法出现异常，原因：",
					"删除户籍家庭出现错误", e);
		}
	}

	@Override
	public void changeAccountNumber(String newAccNo, Long orgId, Long id,
			Long shardOrgId) {
		houseFamilyDao.changeAccountNumber(newAccNo, id);
		houseHoldStaffDao.updateAccountNumberByFamilyIdAndOrgId(newAccNo,
				orgId, id, shardConversion.getShardCode(shardOrgId));
	}

	@Override
	public PageInfo findHouseFamilyMembersByOrgIdAndId(Long id, Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		return houseFamilyDao.findHouseFamilyMembersByOrgIdAndId(id, orgId,
				page, rows, sidx, sord, shardConversion.getShardCode(orgId));
	}

	@Override
	public void changeHouseHold(HouseholdStaff oldHouseHold,
			HouseholdStaff newHouseHold, Long id, Long orgId, Long shardOrgId) {
		HouseholdStaff newHouseholdStaff = houseHoldStaffService
				.getHouseholdStaffById(newHouseHold.getId());
		houseFamilyDao.changeHouseHold(newHouseholdStaff, id);
		houseHoldStaffDao.changeOldHouseHold(oldHouseHold, id,
				shardConversion.getShardCode(shardOrgId));
		houseHoldStaffDao.changeNewHouseHold(newHouseHold, id,
				shardConversion.getShardCode(shardOrgId));
	}

	@Override
	public boolean existAccountNumber(String newAccNo, Long orgId) {

		return houseFamilyDao.existAccountNumber(newAccNo, orgId);
	}

	@Override
	public int haveRepatCardOrNo(String card) {
		return houseFamilyDao.haveRepatCardOrNo(card);
	}

	private List<PropertyDict> getHouseMarchTypeByFamilyId(Long familyId) {
		List<Long> dictIds = houseFamilyDao.getHouseMarchType(familyId);
		List<PropertyDict> houseMarchTypeList = propertyDictService
				.findPropertyDictByIds(Utils.analyticalIds(dictIds));
		return houseMarchTypeList;
	}

	private Long getHeadOfHouseHoldId(String displayName) {
		List<PropertyDict> dictList = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.RELATION_SHIP_WITH_HEAD);
		for (PropertyDict dict : dictList) {
			if (dict.getDisplayName().equals(displayName)) {
				return dict.getId();
			}
		}
		return null;
	}

}
