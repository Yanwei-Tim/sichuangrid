package com.tianque.viewObject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.FindStringOfWordUtil;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.viewObject.dao.ViewObjectDao;
import com.tianque.viewObject.vo.ViewObjectVo;

@Service("viewObjectService")
public class ViewObjectServiceImp implements ViewObjectService {

	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private ViewObjectDao viewObjectDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ViewObjectVo getViewObjectDefNum() {
		Long provinceId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.PROVINCE).get(0)).getId();
		Long cityId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.CITY).get(0)).getId();
		Long districtId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.DISTRICT).get(0)).getId();
		Long townId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN).get(0)).getId();
		Long villageId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.VILLAGE).get(0)).getId();
		Long gridId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID).get(0)).getId();
		Long adminTypeId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0)).getId();
		int userOrgLevelInternalId = ThreadVariable.getOrganization()
				.getOrgLevel().getInternalId();
		String userOrgCode = ThreadVariable.getOrganization()
				.getOrgInternalCode();
		Long functionTypeId = (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTIONAL_ORG).get(0)).getId();
		List<Map<String, Object>> wheleSql = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", provinceId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId > OrganizationLevel.PROVINCE) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 2));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", cityId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId > OrganizationLevel.CITY) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 3));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", districtId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId >= OrganizationLevel.DISTRICT) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 4));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", townId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId >= OrganizationLevel.TOWN) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 5));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", villageId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId >= OrganizationLevel.VILLAGE) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 6));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", gridId);
		map.put("type", adminTypeId);
		if (userOrgLevelInternalId >= OrganizationLevel.GRID) {
			map.put("orgCode", userOrgCode);
		} else {
			map.put("orgCode", FindStringOfWordUtil.findStringOfWordPlace(
					userOrgCode, ".", 1, 7));
		}
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", provinceId);
		map.put("type", functionTypeId);
		map.put("orgCode", userOrgCode);
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", cityId);
		map.put("type", functionTypeId);
		map.put("orgCode", userOrgCode);
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", districtId);
		map.put("type", functionTypeId);
		map.put("orgCode", userOrgCode);
		wheleSql.add(map);

		map = new HashMap<String, Object>();
		map.put("level", townId);
		map.put("type", functionTypeId);
		map.put("orgCode", userOrgCode);
		wheleSql.add(map);

		ViewObjectVo resultVo = new ViewObjectVo();
		List<Integer> listNum = organizationDubboService
				.getViewObjectDefNum(wheleSql);
		resultVo.setDefProvinceNum(listNum.get(0));
		resultVo.setDefCityNum(listNum.get(1));
		resultVo.setDefDistrictNum(listNum.get(2));
		resultVo.setDefTownNum(listNum.get(3));
		resultVo.setDefVillageNum(listNum.get(4));
		resultVo.setDefGridNum(listNum.get(5));
		resultVo.setDefProvinceFucDepartmentNum(listNum.get(6));
		resultVo.setDefCityFucDepartmentNum(listNum.get(7));
		resultVo.setDefDistrictFucDepartmentNum(listNum.get(8));
		resultVo.setDefTownFucDepartmentNum(listNum.get(9));
		return resultVo;
	}

	public ViewObjectVo saveViewObject(ViewObjectVo viewObjectVo) {
		return viewObjectDao.add(viewObjectVo);
	}

	public ViewObjectVo updateViewObject(ViewObjectVo viewObjectVo) {
		if (null == viewObjectVo || null == viewObjectVo.getId()) {
			throw new BusinessValidationException("无法定位要修改的对象，请重新输入对象的id");
		}
		return viewObjectDao.update(viewObjectVo);
	}

	@Override
	public ViewObjectVo ajaxGetSelectedNumWhenSelectByLine(
			String selectedRadio, String selectedLevel) {
		if (ViewObjectVo.SELECTBYLINE.equals(selectedRadio)) {
			if (selectedLevel.equals(ViewObjectVo.SELFLEVEL)) {
				return getReturnViewObjectVoByselfLevel();
			} else if (ViewObjectVo.DIRECTDOWNLEVEL.equals(selectedLevel)) {

			}
		}
		return null;
	}

	/**
	 * 对于本级来说所选数量只能为1
	 * 
	 * @return
	 */
	private ViewObjectVo getReturnViewObjectVoByselfLevel() {
		ViewObjectVo vo = new ViewObjectVo();

		Session session = ThreadVariable.getSession();
		Organization org;
		int level = 0;
		int type = 0;
		if (session != null && session.getOrganization() != null) {
			org = session.getOrganization();
			level = org.getOrgLevel().getInternalId();
			type = org.getOrgType().getInternalId();
		}
		if (0 != type && type == OrganizationType.ADMINISTRATIVE_REGION) {
			if (OrganizationLevel.PROVINCE == level) {
				vo.setProvinceNum(1);
			} else if (OrganizationLevel.CITY == level) {
				vo.setCityNum(1);
			} else if (OrganizationLevel.DISTRICT == level) {
				vo.setDistrictNum(1);
			} else if (OrganizationLevel.TOWN == level) {
				vo.setTownNum(1);
			} else if (OrganizationLevel.VILLAGE == level) {
				vo.setVillageNum(1);
			} else if (OrganizationLevel.GRID == level) {
				vo.setGridNum(1);
			}
		} else if (OrganizationType.FUNCTIONAL_ORG == type) {
			if (OrganizationLevel.PROVINCE == level) {
				vo.setProvinceFucDepartmentNum(1);
			} else if (OrganizationLevel.CITY == level) {
				vo.setCityFucDepartmentNum(1);
			} else if (OrganizationLevel.DISTRICT == level) {
				vo.setDistrictFucDepartmentNum(1);
			} else if (OrganizationLevel.TOWN == level) {
				vo.setTownFucDepartmentNum(1);
			}
		}
		return vo;
	}

	@Override
	public ViewObjectVo saveViewObjectVo(ViewObjectVo viewObjectVo) {
		if (viewObjectVo != null && viewObjectVo.getId() != null) {
			return viewObjectDao.update(viewObjectVo);
		}
		return viewObjectDao.add(viewObjectVo);
	}

	@Override
	public List<Long> getSelectedOrgIdsByViewObjectVo(ViewObjectVo viewObjectVo) {
		String selectRadio = viewObjectVo.getSelectedRadio();
		String[] excluseIds = viewObjectVo.getExclusiveIdStrs() != null ? viewObjectVo
				.getExclusiveIdStrs().split(",") : null;
		String[] selectedIds = viewObjectVo.getSelectedIdStrs() != null ? viewObjectVo
				.getSelectedIdStrs().split(",") : null;
		List<Long> resultList;
		if (ViewObjectVo.SELECTALL.equalsIgnoreCase(selectRadio)) {
			List<Long> allOrgIds = organizationDubboService
					.findAllRelatedOrgIdsByUserOrgId(OrganizationLevel.PROVINCE);
			Long[] excluseOrgIds = getLongsByStrings(excluseIds);
			allOrgIds.removeAll(Arrays.asList(excluseOrgIds));
			resultList = allOrgIds;

			return allOrgIds;
		}
		if (viewObjectVo.getSelectedCheckBoxStrs() != null
				&& !viewObjectVo.getSelectedCheckBoxStrs().isEmpty()) {
			resultList = organizationDubboService
					.getOrgIdsWhenSelectByLevel(getSelectedLevelList(viewObjectVo
							.getSelectedCheckBoxStrs()));
			resultList.removeAll(Arrays.asList(getLongsByStrings(excluseIds)));
		} else {
			return java.util.Arrays.asList(getLongsByStrings(selectedIds));
		}

		if (null != selectedIds && selectedIds.length > 0) {
			for (String str : selectedIds) {
				if (!str.isEmpty()) {
					resultList.add(Long.parseLong(str));
				}
			}
		}
		HashSet h = new HashSet(resultList);
		resultList.clear();
		resultList.addAll(h);
		return resultList;

	}

	public List<String> getselectedOrgNames(ViewObjectVo viewObjectVo,
			String type, String level) {
		List<Long> orgIds = this.getSelectedOrgIdsByViewObjectVo(viewObjectVo);
		Long typeId = null;
		Long levelId = null;
		if (StringUtil.isStringAvaliable(type)) {
			typeId = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_TYPE,
							Integer.valueOf(type)).get(0).getId();
		}
		if (StringUtil.isStringAvaliable(level)) {
			levelId = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							Integer.valueOf(level)).get(0).getId();
		}
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setOrgIdsList(ParametersConvertUtil
				.convertToListString(orgIds));
		organizationVo.setOrgLevelId(levelId);
		organizationVo.setOrgTypeId(typeId);
		return organizationDubboService
				.getSelectedOrgNamesByOrgIdsAndTypeLevel(organizationVo);
	}

	private List<Map<String, Object>> getSelectedLevelList(String strs) {
		String userOrg = ThreadVariable.getOrganization().getOrgInternalCode();
		int userOrgLevel = ThreadVariable.getOrganization().getOrgLevel()
				.getInternalId();
		if (ThreadVariable.getOrganization().getOrgType() != null
				&& ThreadVariable.getOrganization().getOrgType()
						.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& ThreadVariable.getOrganization().getParentOrg() != null) {
			userOrg = ThreadVariable.getOrganization().getParentOrg()
					.getOrgInternalCode();
			userOrgLevel = ThreadVariable.getOrganization().getParentOrg()
					.getOrgLevel().getInternalId();
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (String str : strs.split(",")) {
			map = new HashMap<String, Object>();
			if (!str.isEmpty()) {
				map.put("level", Integer.parseInt(str.split("-")[0]));
				map.put("type", Integer.parseInt(str.split("-")[1]));
				if (userOrgLevel >= Integer.parseInt(str.split("-")[0])) {
					map.put("orgCode", userOrg);
				} else {
					map.put("orgCode",
							FindStringOfWordUtil
									.findStringOfWordPlace(
											userOrg,
											".",
											1,
											(OrganizationLevel.COUNTRY - Integer
													.parseInt(str.split("-")[0])) / 10 + 1));
				}
				result.add(map);
			}
		}
		return result;
	}

	private Long[] getLongsByStrings(String[] strs) {
		if (strs == null || strs.length == 0) {
			return null;
		}
		List<Long> longList = new ArrayList<Long>();
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].isEmpty()) {
				longList.add(Long.parseLong(strs[i]));
			}
		}
		return longList.toArray(new Long[0]);
	}

	@Override
	public List<Long> getSelectedOrgIdsByViewObjectVoId(Long id) {
		return this.getSelectedOrgIdsByViewObjectVo(viewObjectDao.get(id));
	}

	@Override
	public ViewObjectVo getViewObjectById(Long id) {
		return viewObjectDao.get(id);
	}

	@Override
	public void deleteViewObjectById(Long id) {
		viewObjectDao.delete(id);
	}

	@Override
	public List<Long> getOrgIdsMobile(Long userOrgId, Integer levelType) {
		List<Long> orgIds = new ArrayList<Long>();
		if (levelType == 0) {
			orgIds = organizationDubboService
					.findAllRelatedOrgIdsByUserOrgId(OrganizationLevel.PROVINCE);
		}
		if (levelType == 1) {
			orgIds.add(ThreadVariable.getOrganization().getId());
		}
		if (levelType == 2) {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgInternalCode(ThreadVariable.getOrganization()
					.getOrgInternalCode());
			orgIds = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
		}
		return orgIds;
	}
}
