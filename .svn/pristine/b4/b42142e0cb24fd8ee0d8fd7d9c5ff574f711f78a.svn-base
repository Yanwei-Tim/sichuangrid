package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.FloorpersonDao;
import com.tianque.domain.Floorperson;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.service.FloorpersonService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkDiaryService;

@Service("floorpersonService")
@Transactional
public class FloorpersonServiceImpl extends AbstractBaseService implements FloorpersonService {

	@Autowired
	private FloorpersonDao floorpersonDao;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public Floorperson addFloorperson(Floorperson floorperson) {
		if (floorperson.getFloorpersonsTime() == null) {
			return null;
		}
		floorperson = floorpersonDao.addFloorperson(floorperson);
		String content = workDiaryService.assemblingContent(floorperson.getPlaceName(), "",
				floorperson.getEvents(), createWorkDiaryType(floorperson.getPlaceType()),
				floorperson.getPlaceTypeName(), floorperson.getPlaceType());
		workDiaryService.addWorkDiary(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WORKDIARY_DIARY_TYPE,
						createWorkDiaryType(floorperson.getPlaceType())),
				createObjectType(floorperson.getPlaceType()), floorperson.getId(), content,
				floorperson.getAddress(), floorperson.getPersonInChargeName(), floorperson
						.getFloorpersonsTime());
		return floorperson;
	}

	private String createWorkDiaryType(String type) {
		if (type.equals(BaseInfoTables.getKeytables().get(BaseInfoTables.LETTINGHOUSE_KEY))) {
			return WorkDiaryTypes.LETTINGHOUSE_FLOORPERSONS;
		}
		if (type.equals("newSocietyFederation")) {
			return WorkDiaryTypes.NEWSOCIETY_FLOORPERSONS;
		}
		if (type.equals("enterpriseKey")) {
			return WorkDiaryTypes.ENTERPRISE_FLOORPERSONS;
		}
		return WorkDiaryTypes.FLOORPERSONS;
	}

	private String createObjectType(String objectType) {
		if (objectType.equals(BaseInfoTables.getKeytables().get(BaseInfoTables.LETTINGHOUSE_KEY))) {
			return WorkDiaryTypes.TYPE_LETTINGHOUSE_FLOORPERSONS;
		}
		if (objectType.equals("newSocietyFederation")) {
			return WorkDiaryTypes.TYPE_NEWSOCIETY_FLOORPERSONS;
		}
		if (objectType.equals("enterpriseKey")) {
			return WorkDiaryTypes.TYPE_ENTERPRISE_FLOORPERSONS;
		}
		return WorkDiaryTypes.TYPE_FLOORPERSONS;
	}

	@Override
	public PageInfo<Floorperson> findFloorperson(Long palceId, int pageNum, int pageSize,
			String sortField, String order, String placeType) {
		return floorpersonDao.findFloorperson(palceId, pageNum, pageSize, sortField, order,
				placeType);
	}

	@Override
	public Floorperson updateFloorperson(Floorperson floorperson) {
		if (floorperson.getFloorpersonsTime() == null) {
			return null;
		}
		String content = workDiaryService.assemblingContent(floorperson.getPlaceName(), "",
				floorperson.getEvents(), createWorkDiaryType(floorperson.getPlaceType()),
				floorperson.getPlaceTypeName(), floorperson.getPlaceType());

		workDiaryService.updateWorkDiary(createObjectType(floorperson.getPlaceType()),
				floorperson.getId(), content, floorperson.getAddress(),
				floorperson.getPersonInChargeName(), floorperson.getFloorpersonsTime());

		return floorpersonDao.updateFloorperson(floorperson);
	}

	@Override
	public void deleteFloorperson(Long placeId, String placeType) {
		floorpersonDao.deleteFloorperson(placeId, placeType);
	}

	@Override
	public void deleteFloorpersonById(Long id) {
		floorpersonDao.deleteFloorpersonById(id);
	}

	@Override
	public Floorperson getFloorperson(Long id) {
		return floorpersonDao.getFloorperson(id);
	}

}
