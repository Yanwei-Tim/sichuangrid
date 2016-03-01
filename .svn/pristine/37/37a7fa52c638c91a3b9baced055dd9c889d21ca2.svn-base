package com.tianque.baseInfo.buildDatas.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.dao.ActualCompanyDao;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.buildDatas.dao.BuildLayoutDao;
import com.tianque.baseInfo.buildDatas.dao.BuilddatasDao;
import com.tianque.baseInfo.buildDatas.domain.BuildLayout;
import com.tianque.baseInfo.buildDatas.domain.BuildLayoutCell;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.TypeColor;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.dao.HouseInfoDao;
import com.tianque.dao.KeyPlaceDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("buildLayoutService")
public class BuildLayoutServiceImpl implements BuildLayoutService {

	@Autowired
	private BuildLayoutDao buildLayoutDao;
	@Autowired
	private BuilddatasDao builddatasDao;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private KeyPlaceDao keyPlaceDao;
	@Autowired
	private HouseInfoDao houseInfoDao;
	@Autowired
	private ActualCompanyDao actualCompanyDao;
	@Autowired
	private OrganizationDubboService orgnizationService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public void addBuildLayout(BuildLayout buildLayout,
			Builddatas builddatas) {

		if (buildLayout == null || builddatas == null
				|| builddatas.getId() == null) {
			throw new BusinessValidationException("参数为空");
		}
		validate(buildLayout);
		buildLayoutDao.addBuildLayout(buildLayout);
		
		builddatas.setIsLayout(1L);
		builddatasDao.updateBuilddatas(builddatas);
	}
	
	@Override
	public void updateBuildLayout(BuildLayout buildLayout) {
		if (buildLayout == null || buildLayout.getId()==null) {
			throw new BusinessValidationException("参数为空");
		}
		validate(buildLayout);
		buildLayoutDao.updateBuildLayout(buildLayout);
	}

	@Override
	public BuildLayout getBuildLayoutByBuildId(Long buildId) {
		if (buildId == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		BuildLayout buildLayout = buildLayoutDao
				.getBuildLayoutByBuildId(buildId);
		if(buildLayout!=null){
			for (BuildLayoutCell buildLayoutCell : buildLayout.getLayoutCellList()) {
				if(buildLayoutCell.getBuildType()==null){
					continue;
				}
				// 房屋
				else if (buildLayoutCell.getBuildType() == 1) {
					buildLayoutCell.setRoom(houseInfoDao
							.getHouseRoomAndNameById(buildLayoutCell
									.getHousePropertyId()));
				}
				// 单位
				else if (buildLayoutCell.getBuildType() == 2) {
					buildLayoutCell.setRoom(actualCompanyDao
							.getActualCompanyNameById(buildLayoutCell
									.getHousePropertyId()));
				}
				// 场所
				else if (buildLayoutCell.getBuildType() == 3) {
					buildLayoutCell.setRoom(keyPlaceDao.getKeyPlaceNameByIdAndType(
							buildLayoutCell.getHousePropertyId(),
							buildLayoutCell.getKeyPlaceType()));
				}
			}
		}
		return buildLayout;
	}
	
	@Override
	public void deleteBuildLayoutById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		buildLayoutDao.deleteBuildLayoutById(id);
	}

	@Override
	public void deleteBuildLayoutByBuildId(Long buildId) {
		if (buildId == null) {
			throw new BusinessValidationException("请选中一条记录");
		}
		buildLayoutDao.deleteBuildLayoutByBuildId(buildId);
	}

	@Override
	public void changeLayoutData() {
		List<Long> buildIdList = buildLayoutDao.findBuildIds();
		List<BuildLayoutCell> cellList = null;
		BuildLayout buildLayout = new BuildLayout();
		for (Long buildId : buildIdList) {
			cellList = buildLayoutDao.findBuildLayoutCellByBuildId(buildId);
			JSONArray json = JSONArray.fromObject(cellList);
			buildLayout.setBuildId(buildId);
			buildLayout.setLayoutInfo(json.toString());
			buildLayoutDao.addBuildLayout(buildLayout);
		}
	}
	
	private void validate(BuildLayout layout) {
		if (layout == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		boolean orgIsNotNull = validateOrgIsNotNull(layout.getOrg());
		if (!orgIsNotNull) {
			throw new BusinessValidationException("所属网格不能为空");
		}
		if (layout.getBuildId() == null) {
			throw new BusinessValidationException("楼宇ID不能为空");
		}
		if (layout.getLayoutInfo() == null) {
			throw new BusinessValidationException("布局信息不能为空");
		}
		if (layout.getTotalRow() == null) {
			throw new BusinessValidationException("布局行数不能为空");
		}
		if (layout.getTotalCol() == null) {
			throw new BusinessValidationException("布局列数不能为空");
		}
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Object> findBuildLayoutTypeData(Long builddatasid, Long orgId) {
		List<PropertyDict> houseUsesPropertys = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.HOUSE_USES);
		Map<String, String> keyPopulationMap = GisGlobalValue.keyPopulation;
		List<Object> typeList = new ArrayList<Object>();
		Map<String, String> houseTypeColor = new LinkedHashMap<String, String>();
		Map<String, String> personTypeColor = new LinkedHashMap<String, String>();
		Map<String, Long> houseTypeNumber = new LinkedHashMap<String, Long>();
		Map<String, Long> personTypeNumber = new LinkedHashMap<String, Long>();
		Long number = 0L;
		String value, key;
		// 房屋用途
		int i = 0;

		for (; i < houseUsesPropertys.size(); i++) {
			number = actualHouseService
					.countHouseInfoByBuilddatasidAndHouseUses(builddatasid,
							houseUsesPropertys.get(i).getId());
			value = houseUsesPropertys.get(i).getDisplayName();
			houseTypeColor.put(value, TypeColor.colorList.get(i));
			houseTypeNumber.put(value, number);
		}

		/*
		 * // 房屋类别（出租房、非出租房） number =
		 * actualHouseService.countHouseInfoByBuilddatasidAndisrentalhouse
		 * (builddatasid, 1); houseTypeColor.put("出租房",
		 * TypeColor.colorList.get(i++)); houseTypeNumber.put("出租房", number);
		 * number =
		 * actualHouseService.countHouseInfoByBuilddatasidAndisrentalhouse
		 * (builddatasid, 0); houseTypeColor.put("非出租房",
		 * TypeColor.colorList.get(i++)); houseTypeNumber.put("非出租房", number);
		 * // 实有单位 number = (long)
		 * actualCompanyDao.findActualCompanyListByBuildingId
		 * (builddatasid).size(); houseTypeColor.put("实有单位",
		 * TypeColor.colorList.get(i++)); houseTypeNumber.put("实有单位", number);
		 * // 场所 Set<String> keyPlaceSet = GisGlobalValue.keyPlace.keySet();
		 * Iterator<String> keyPlaceIt = keyPlaceSet.iterator();
		 * 
		 * for (; keyPlaceIt.hasNext(); i++) { key = keyPlaceIt.next(); value =
		 * GisGlobalValue.getKeyPlaceNameByType(key); number = (long)
		 * keyPlaceDao.countKeyPlaceByBuilddatasIdAndKeyPlaceType(builddatasid,
		 * key); houseTypeColor.put(value, TypeColor.colorList.get(i));
		 * houseTypeNumber.put(value, number); }
		 */
		Iterator<Entry<String, String>> it = keyPopulationMap.entrySet().iterator();
		for (int j = 0; it.hasNext(); j++) {
			Entry<String, String> entry = it.next();
			key = entry.getKey();
			value = entry.getValue();
			if(BaseInfoTables.AIDSPOPULATIONS_KEY.equals(key)){
				continue;
			}
			number = buildLayoutDao
					.countKeyPersonInBuildingByBuilddatasidAndPersonType(
							builddatasid, key,
							shardConversion.getShardCode(orgId));
			personTypeColor.put(value, TypeColor.colorList.get(j));
			personTypeNumber.put(value, number);
		}
		typeList.add(personTypeColor);
		typeList.add(houseTypeColor);
		typeList.add(personTypeNumber);
		typeList.add(houseTypeNumber);
		return typeList;
	}

	@Override
	public List<Long> findHouseIdByPersonTypeAndHouseIds(String personType,
			String[] idArray) {
		if (personType == null || idArray == null) {
			throw new BusinessValidationException("参数错误");
		}
		List<Long> idList = new ArrayList<Long>();
		List<Long> ids = new ArrayList<Long>();
		Boolean haveKeyPerson = null;
		Long id = null;
		for (int i = 0; i < idArray.length; i++) {
			if (idArray[i] == null || idArray[i].trim().equals(""))
				continue;
			id = Long.parseLong(idArray[i]);
			for (int j = 0; j < ids.size(); j++) {
				if (id.equals(ids.get(j))) {
					id = null;
					break;
				}
			}
			if (id == null)
				continue;
			else
				ids.add(id);
			haveKeyPerson = buildLayoutDao
					.existKeyPersonInHouseByPersonTypeAndHouseId(personType, id);
			if (haveKeyPerson) {
				idList.add(id);
			}
		}
		return idList;
	}

	@Override
	public List<LayoutTagVo> findLayoutAllTags(Long orgId, Long builddatasId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构编号为空");
		}
		List<LayoutTagVo> laytouTagList = new ArrayList<LayoutTagVo>();
		laytouTagList.addAll(keyPlaceDao
				.searchAllKeyPlaceName4LayoutTag(orgnizationService
						.getSimpleOrgById(orgId).getOrgInternalCode(),
						builddatasId));
		laytouTagList.addAll(houseInfoDao
				.searchAllHouseName4LayoutTag(orgnizationService
						.getSimpleOrgById(orgId).getOrgInternalCode(),
						builddatasId));
		laytouTagList.addAll(actualCompanyDao
				.searchAllActualCompanyName4LayoutTag(orgnizationService
						.getSimpleOrgById(orgId).getOrgInternalCode(),
						builddatasId));
		return laytouTagList;
	}

}
