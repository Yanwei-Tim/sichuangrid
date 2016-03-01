package com.tianque.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.KeyPlaceDao;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Hospital;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.School;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.service.KeyPlaceService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @author 傅豪
 */
@Service("placeService")
@Transactional
public class KeyPlaceServiceImpl implements KeyPlaceService {
	@Autowired
	KeyPlaceDao keyPlaceDao;
	@Autowired
	private OrganizationDubboService orgnizationService;

	@Override
	public KeyPlace addKeyPlace(KeyPlace keyPlace) {
		if (null == keyPlace.getId() || null == keyPlace.getType()
				|| null == keyPlace.getName()
				|| null == keyPlace.getOrgInternalCode()) {
			return null;
		}
		return keyPlaceDao.addKeyPlace(keyPlace);
	}

	@Override
	public KeyPlace deleteKeyPlace(KeyPlace keyPlace) {
		if (null == keyPlace.getId() || null == keyPlace.getType()) {
			return null;
		}
		keyPlaceDao.deleteKeyPlaceByIdAndType(keyPlace);
		return keyPlace;
	}

	@Override
	public void deleteKeyPlaceByIdAndInType(Long id, String types) {
		if (id != null && types != null) {
			keyPlaceDao.deleteKeyPlaceByIdAndInType(id, types);
		}
	}

	@Override
	public KeyPlace updateKeyPlace(KeyPlace keyPlace) {
		if (null == keyPlace.getId() || null == keyPlace.getType()
				|| null == keyPlace.getName()
				|| null == keyPlace.getOrgInternalCode()) {
			return null;
		}
		return keyPlaceDao.updateKeyPlace(keyPlace);
	}

	@Override
	public KeyPlace execute(DocumentType type, String mode, Object object) {
		KeyPlace keyPlace = new KeyPlace();
		if (object == null) {
			return keyPlace;
		}
		if (DocumentType.HOSPITAL.equals(type)) {
			Hospital hospital = (Hospital) object;
			keyPlace = setKeyPlace(hospital.getId(), hospital.getOrganization()
					.getId(), hospital.getHospitalName(),
					hospital.getFullPinyin(), hospital.getSimplePinyin(),
					hospital.getAddress(), hospital.getCreateDate(),
					hospital.getOrgInternalCode(), type, null);
			processKeyPlace(keyPlace, mode);
			return keyPlace;

		} else if (DocumentType.SCHOOLS.equals(type)) {
			School school = (School) object;
			keyPlace = setKeyPlace(school.getId(), school.getOrganization()
					.getId(), school.getChineseName(), school.getFullPinyin(),
					school.getSimplePinyin(), school.getAddress(),
					school.getCreateDate(), school.getOrgInternalCode(), type,
					school.getPresident());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.ENTERPRISEKEY.equals(type)
				|| DocumentType.PROTECTIONKEY.equals(type)
				|| DocumentType.SAFETYPRODUCTIONKEY.equals(type)
				|| DocumentType.FIRESAFETYKEY.equals(type)
				|| DocumentType.SECURITYKEY.equals(type)
				|| DocumentType.ENTERPRISEDOWNKEY.equals(type)) {
			Enterprise enterprise = (Enterprise) object;
			keyPlace = setKeyPlace(enterprise.getId(), enterprise
					.getOrganization().getId(), enterprise.getName(),
					enterprise.getFullPinyin(), enterprise.getSimplePinyin(),
					enterprise.getAddress(), enterprise.getCreateDate(),
					enterprise.getOrgInternalCode(), type,
					enterprise.getLegalPerson());
			processKeyPlace(keyPlace, mode);
		} else if (DocumentType.OTHERLOCALES.equals(type)) {
			OtherLocale otherLocale = (OtherLocale) object;
			keyPlace = setKeyPlace(otherLocale.getId(), otherLocale
					.getOrganization().getId(), otherLocale.getName(),
					otherLocale.getFullPinyin(), otherLocale.getSimplePinyin(),
					otherLocale.getAddress(), otherLocale.getCreateDate(),
					otherLocale.getOrgInternalCode(), type,
					otherLocale.getContactPerson());
			processKeyPlace(keyPlace, mode);
		} else if (DocumentType.DANGEROUSCHEMICALSUNIT.equals(type)) {
			DangerousChemicalsUnit dangerousChemicalsUnit = (DangerousChemicalsUnit) object;
			keyPlace = setKeyPlace(dangerousChemicalsUnit.getId(),
					dangerousChemicalsUnit.getOrganization().getId(),
					dangerousChemicalsUnit.getUnitName(),
					dangerousChemicalsUnit.getFullPinyin(),
					dangerousChemicalsUnit.getSimplePinyin(),
					dangerousChemicalsUnit.getUnitAddress(),
					dangerousChemicalsUnit.getCreateDate(),
					dangerousChemicalsUnit.getOrgInternalCode(), type,
					dangerousChemicalsUnit.getSuperintendent());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.INTERNETBAR.equals(type)) {
			InternetBar internetBar = (InternetBar) object;
			keyPlace = setKeyPlace(internetBar.getId(), internetBar
					.getOrganization().getId(), internetBar.getPlaceName(),
					internetBar.getFullPinyin(), internetBar.getSimplePinyin(),
					internetBar.getPlaceAddress(), internetBar.getCreateDate(),
					internetBar.getOrganization().getOrgInternalCode(), type,
					internetBar.getManager());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.PUBLICCOMPLEXPLACES.equals(type)) {
			PublicComplexPlaces publicComplexPlaces = (PublicComplexPlaces) object;
			keyPlace = setKeyPlace(publicComplexPlaces.getId(),
					publicComplexPlaces.getOrganization().getId(),
					publicComplexPlaces.getPlaceName(),
					publicComplexPlaces.getFullPinyin(),
					publicComplexPlaces.getSimplePinyin(),
					publicComplexPlaces.getPlaceAddress(),
					publicComplexPlaces.getCreateDate(),
					publicComplexPlaces.getOrgInternalCode(), type,
					publicComplexPlaces.getManager());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.PUBLICPLACE.equals(type)) {
			PublicPlace publicPlace = (PublicPlace) object;
			keyPlace = setKeyPlace(publicPlace.getId(), publicPlace
					.getOrganization().getId(), publicPlace.getPlaceName(),
					publicPlace.getFullPinyin(), publicPlace.getSimplePinyin(),
					publicPlace.getPlaceAddress(), publicPlace.getCreateDate(),
					publicPlace.getOrgInternalCode(), type,
					publicPlace.getManager());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.RENTALHOUSE.equals(type)) {
			RentalHouse publicPlace = (RentalHouse) object;
			keyPlace = setKeyPlace(publicPlace.getId(), publicPlace
					.getOrganization().getId(), publicPlace.getName(),
					publicPlace.getFullPinyin(), publicPlace.getSimplePinyin(),
					publicPlace.getAddress(), publicPlace.getCreateDate(),
					publicPlace.getOrgInternalCode(), type,
					publicPlace.getRentalPerson());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.ACTUALCOMPANY.equals(type)) {// 实有单位
			ActualCompany actualCompany = (ActualCompany) object;
			keyPlace = setKeyPlace(actualCompany.getId(), actualCompany
					.getOrganization().getId(), actualCompany.getCompanyName(),
					actualCompany.getFullPinyin(),
					actualCompany.getSimplePinyin(),
					actualCompany.getCompanyAddress(),
					actualCompany.getCreateDate(),
					actualCompany.getOrgInternalCode(), type,
					actualCompany.getCorporateRepresentative());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.NEWSOCIETYFEDERATIONS.equals(type)) {// 社会组织
			NewSocietyOrganizations newSocietyOrganizations = (NewSocietyOrganizations) object;
			keyPlace = setKeyPlace(newSocietyOrganizations.getId(),
					newSocietyOrganizations.getOrganization().getId(),
					newSocietyOrganizations.getName(),
					newSocietyOrganizations.getFullPinyin(),
					newSocietyOrganizations.getSimplePinyin(),
					newSocietyOrganizations.getAddress(),
					newSocietyOrganizations.getCreateDate(),
					newSocietyOrganizations.getOrgInternalCode(), type,
					newSocietyOrganizations.getLegalPerson());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.NEWECONOMICORGANIZATIONS.equals(type)) {// 新经济组织
			NewEconomicOrganizations newEconomicOrganizations = (NewEconomicOrganizations) object;
			keyPlace = setKeyPlace(newEconomicOrganizations.getId(),
					newEconomicOrganizations.getOrganization().getId(),
					newEconomicOrganizations.getName(),
					newEconomicOrganizations.getFullPinyin(),
					newEconomicOrganizations.getSimplePinyin(),
					newEconomicOrganizations.getResidence(),
					newEconomicOrganizations.getCreateDate(),
					newEconomicOrganizations.getOrgInternalCode(), type,
					newEconomicOrganizations.getChief());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.SCENICSPOTS.equals(type)) {// 旅游景点
			ScenicSpot scenicSpot = (ScenicSpot) object;
			keyPlace = setKeyPlace(scenicSpot.getId(), scenicSpot
					.getOrganization().getId(), scenicSpot.getName(),
					scenicSpot.getFullPinyin(), scenicSpot.getSimplePinyin(),
					scenicSpot.getAddress(), scenicSpot.getCreateDate(),
					scenicSpot.getOrgInternalCode(), type,
					scenicSpot.getPersonLiable());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.SCENICEQUIPMENT.equals(type)) {// 景区配置设施
			ScenicEquipment scenicEquipment = (ScenicEquipment) object;
			keyPlace = setKeyPlace(scenicEquipment.getId(), scenicEquipment
					.getOrganization().getId(), scenicEquipment.getEquipName(),
					scenicEquipment.getFullPinyin(),
					scenicEquipment.getSimplePinyin(),
					scenicEquipment.getEquipAddress(),
					scenicEquipment.getCreateDate(), scenicEquipment
							.getOrganization().getOrgInternalCode(), type,
					scenicEquipment.getManager());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		} else if (DocumentType.SCENICTRAFFIC.equals(type)) {// 景区交通
			ScenicTraffic scenicTraffic = (ScenicTraffic) object;
			keyPlace = setKeyPlace(scenicTraffic.getId(), scenicTraffic
					.getOrganization().getId(), scenicTraffic.getTrafficName(),
					scenicTraffic.getFullPinYin(),
					scenicTraffic.getSimplePinYin(),
					scenicTraffic.getManagerUnit(),
					scenicTraffic.getCreateDate(), scenicTraffic
							.getOrganization().getOrgInternalCode(), type,
					scenicTraffic.getManagerPeople());
			processKeyPlace(keyPlace, mode);
			return keyPlace;
		}
		return null;
	}

	public KeyPlace setKeyPlace(Long id, Long orgId, String name,
			String fullPinyin, String simplePinyin, String address,
			Date createDate, String orgInternalCode, DocumentType type,
			String chargePerson) {
		KeyPlace keyPlace = new KeyPlace();
		keyPlace.setId(id);
		keyPlace.setOrgId(orgId);
		if (name == null) {
			name = " ";
		}
		keyPlace.setName(name);
		keyPlace.setFullPinyin(fullPinyin);
		keyPlace.setSimplePinyin(simplePinyin);
		keyPlace.setAddress(address);
		keyPlace.setCreateDate(createDate);
		keyPlace.setOrgInternalCode(orgInternalCode);
		keyPlace.setType(type.toString());
		keyPlace.setChargePerson(chargePerson);
		return keyPlace;
	}

	private void processKeyPlace(KeyPlace keyPlace, String mode) {
		if (mode.equals("ADD")) {
			this.addKeyPlace(keyPlace);
		} else if (mode.equals("EDIT")) {
			this.updateKeyPlace(keyPlace);
		} else if (mode.equals("DELETE")) {
			this.deleteKeyPlace(keyPlace);
		}
	}

	@Override
	public List<KeyPlace> searchKeyPlaceByName(String name,
			String orgInternalCode) {
		if (null == name || orgInternalCode == null) {
			return null;
		}
		Map<String, String> query = new HashMap<String, String>();
		query.put("name", name);
		query.put("orgInternalCode", orgInternalCode + "%");

		return keyPlaceDao.searchKeyPlaceByName(query);
	}

	@Override
	public PageInfo<KeyPlace> searchKeyPlacePageByName(String name,
			String orgInternalCode, Integer pageNum, Integer pageSize) {
		if (null == name || orgInternalCode == null) {
			return null;
		}
		return keyPlaceDao.searchKeyPlaceByName(name, orgInternalCode, pageNum,
				pageSize);
	}

	@Override
	public PageInfo<KeyPlace> findBoundKeyPlaceByBuilddatasIdForPage(
			Long builddatasid, int page, int rows, String sidx, String sord) {
		return keyPlaceDao.findBoundKeyPlaceByBuilddatasIdForPage(builddatasid,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<KeyPlace> findUnBoundKeyPlaceByOrgId(Long orgid, int page,
			int rows, String sidx, String sord, String queryStr) {
		return keyPlaceDao.findUnBoundKeyPlaceByOrgId(orgnizationService
				.getSimpleOrgById(orgid).getOrgInternalCode(), page, rows,
				sidx, sord, queryStr);
	}

	@Override
	public void boundKeyPlace(List<Long> keyPlaceIds, Long builddatasId) {
		keyPlaceDao.boundKeyPlace(keyPlaceIds, builddatasId);

	}

	@Override
	public void unboundKeyPlace(List<Long> keyPlaceIds) {
		keyPlaceDao.unboundKeyPlace(keyPlaceIds);

	}

	@Override
	public List<KeyPlace> findKeyPlaceListByBuilddatasId(Long buildDatasId) {
		if (buildDatasId == null) {
			throw new BusinessValidationException("楼宇编号为空");
		}
		return keyPlaceDao.findKeyPlaceListByBuilddatasId(buildDatasId);
	}

	@Override
	public void emphasisAndNotEmphasis(Long id, String type, Long emphasis) {
		KeyPlace keyPlace = new KeyPlace();
		keyPlace.setId(id);
		keyPlace.setType(type);
		keyPlace.setEmphasis(emphasis);
		keyPlaceDao.emphasisAndNotEmphasis(keyPlace);

	}

	@Override
	public void emphasisKeyPlacesByIdsAndTypes(List<Long> ids, String types,
			Long emphasis) {
		keyPlaceDao.emphasisKeyPlacesByIdsAndTypes(ids, types, emphasis);
	}

	@Override
	public List<KeyPlace> getKeyPlaceByIdInType(Long id, String types) {
		return keyPlaceDao.getKeyPlaceByIdInType(id, types);
	}

	@Override
	public KeyPlace getKeyPlaceByIdAndType(Long id, String type) {
		return keyPlaceDao.getKeyPlaceByIdAndType(id, type);
	}

	@Override
	public void updateKeyPlaceByIdAndOrgInType(Long id, Long orgId,
			String orgInternalCode, String types) {
		if (null == id || null == orgId || null == orgInternalCode
				|| "".equals(orgInternalCode) || null == types
				|| "".equals(types)) {
			throw new BusinessValidationException("参数错误");
		}
		keyPlaceDao.updateKeyPlaceByIdAndOrgInType(id, orgId, orgInternalCode,
				types);
	}

	@Override
	public void deleteKeyPlaceByIdsAndInType(List<Long> ids, String types) {
		keyPlaceDao.deleteKeyPlaceByIdsAndInType(ids, types);
	}

	@Override
	public void updateDomainByKeyplaces(KeyPlaceInfoVo keyPlaceInfoVo) {
		if (keyPlaceInfoVo == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", keyPlaceInfoVo.getId());
		map.put("lon", keyPlaceInfoVo.getLon());
		map.put("lat", keyPlaceInfoVo.getLat());
		map.put("lon2", keyPlaceInfoVo.getLon2());
		map.put("lat2", keyPlaceInfoVo.getLat2());
		map.put("buildDataId", keyPlaceInfoVo.getBuildDataId());
		map.put("buildingId", keyPlaceInfoVo.getBuildingId());
		map.put("type", keyPlaceInfoVo.getType());
		if (ModulTypes.allCompanyPlaceMapKey.contains(keyPlaceInfoVo.getType())) {
			map.remove("type");
			map.put("allType", ModulTypes.allCompanyPlaceMapKey);
			keyPlaceDao.updateDomainByKeyplaces(map);
		} else {
			keyPlaceDao.updateDomainByKeyplaces(map);
		}
	}
}
