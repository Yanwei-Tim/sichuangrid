package com.tianque.baseInfo.publicComplexPlaces.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.publicComplexPlaces.dao.SearchPublicComplexPlacesDao;
import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPublicComplexPlacesVo;

@Repository("searchPublicComplexPlacesDao")
public class SearchPublicComplexPlacesDaoImpl extends AbstractBaseDao implements
		SearchPublicComplexPlacesDao {

	@Override
	public PageInfo findPublicComplexPlacessByQueryCondition(
			SearchPublicComplexPlacesVo searchPlaceVo, Integer page, Integer rows,
			String sidx, String sord) {
		/*
		 * Map<String,Object>map = new HashMap<String, Object>();
		 * map.put("orgInternalCode",searchPlaceVo.getOrgInternalCode() );
		 * map.put("unitName", searchPlaceVo.getUnitName());
		 * map.put("placeAddress", searchPlaceVo.getPlaceAddress());
		 * map.put("licenseDate", searchPlaceVo.getLicenseDate());
		 * map.put("endLicenseDate", searchPlaceVo.getEndLicenseDate());
		 * map.put("manager", searchPlaceVo.getManager());
		 * map.put("openingDate", searchPlaceVo.getOpeningDate());
		 * map.put("endOpeningDate", searchPlaceVo.getEndOpeningDate());
		 * map.put("category", searchPlaceVo.getCategory());
		 * map.put("registrationNumber",searchPlaceVo.getRegistrationNumber() );
		 * map.put("placeLevel", searchPlaceVo.getPlaceLevel());
		 * map.put("buildingStructure", searchPlaceVo.getBuildingStructure());
		 * map.put("totalArea",searchPlaceVo.getTotalArea() );
		 * map.put("endTotalArea",searchPlaceVo.getEndTotalArea() );
		 * map.put("operationArea", searchPlaceVo.getOperationArea());
		 * map.put("endOperationArea", searchPlaceVo.getEndOperationArea());
		 * map.put("cloakroom", searchPlaceVo.getCloakroom());
		 * map.put("vouchedPeopleCount",searchPlaceVo.getVouchedPeopleCount() );
		 * map.put("endVouchedPeopleCount",
		 * searchPlaceVo.getEndVouchedPeopleCount());
		 * map.put("privateRoomCount",searchPlaceVo.getPrivateRoomCount() );
		 * map.put("endPrivateRoomCount",
		 * searchPlaceVo.getEndPrivateRoomCount()); map.put("surrounding",
		 * searchPlaceVo.getSurrounding());
		 * map.put("passageway",searchPlaceVo.getPassageway());
		 * map.put("innerStructure", searchPlaceVo.getInnerStructure());
		 * map.put("isEmphasis", searchPlaceVo.getIsEmphasis());
		 * map.put("order", sord); map.put("sortField", sidx);
		 */
		searchPlaceVo.setSortField(sidx);
		searchPlaceVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPublicComplexPlaces.countPlaces", searchPlaceVo);

		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<PublicComplexPlaces> list = getSqlMapClientTemplate().queryForList(
				"searchPublicComplexPlaces.searchPlaces", searchPlaceVo,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	private PageInfo createPageInfo(Integer page, Integer rows,
			Integer countNum, List<PublicComplexPlaces> list) {
		PageInfo<PublicComplexPlaces> pageInfo = new PageInfo<PublicComplexPlaces>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public List<PublicComplexPlaces> searchPublicComplexPlacesForExport(
			SearchPublicComplexPlacesVo searchPublicComplexPlacesVo, Integer page,
			Integer rows, String sidx, String sord) {
		if (null != searchPublicComplexPlacesVo) {
			searchPublicComplexPlacesVo.setSortField(sidx);
			searchPublicComplexPlacesVo.setOrder(sord);
		}
		if (rows == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchPublicComplexPlaces.searchPlaces", searchPublicComplexPlacesVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchPublicComplexPlaces.searchPlaces", searchPublicComplexPlacesVo,
					(page - 1) * rows, rows);
		}

	}

	@Override
	public Integer getCount(SearchPublicComplexPlacesVo searchPublicComplexPlacesVo) {
		// TODO Auto-generated method stub
		if (null == searchPublicComplexPlacesVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPublicComplexPlaces.countPlaces", searchPublicComplexPlacesVo);
	}

}
