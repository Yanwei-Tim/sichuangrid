package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.publicPlace.dao.SearchPublicPlaceDao;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPublicPlaceVo;

@Repository("searchPublicPlaceDao")
public class SearchPublicPlaceDaoImpl extends AbstractBaseDao implements
		SearchPublicPlaceDao {

	@Override
	public PageInfo findPublicPlacesByQueryCondition(
			SearchPublicPlaceVo searchPlaceVo, Integer page, Integer rows,
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
				"searchPublicPlace.countPlaces", searchPlaceVo);

		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<PublicPlace> list = getSqlMapClientTemplate().queryForList(
				"searchPublicPlace.searchPlaces", searchPlaceVo,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	private PageInfo createPageInfo(Integer page, Integer rows,
			Integer countNum, List<PublicPlace> list) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public List<PublicPlace> searchPublicPlaceForExport(
			SearchPublicPlaceVo searchPublicPlaceVo, Integer page,
			Integer rows, String sidx, String sord) {
		/* Map<String, Object> map = new HashMap<String, Object>(); */
		if (null != searchPublicPlaceVo) {
			/*
			 * map.put("orgInternalCode",
			 * searchPublicPlaceVo.getOrgInternalCode()); map.put("unitName",
			 * searchPublicPlaceVo.getUnitName()); map.put("placeAddress",
			 * searchPublicPlaceVo.getPlaceAddress()); map.put("manager",
			 * searchPublicPlaceVo.getManager()); map.put("licenseDate",
			 * searchPublicPlaceVo.getLicenseDate()); map.put("openingDate",
			 * searchPublicPlaceVo.getOpeningDate()); map.put("category",
			 * searchPublicPlaceVo.getCategory()); map.put("registrationNumber",
			 * searchPublicPlaceVo.getRegistrationNumber());
			 * map.put("placeLevel", searchPublicPlaceVo.getPlaceAddress());
			 * map.put("buildingStructure",
			 * searchPublicPlaceVo.getBuildingStructure()); map.put("totalArea",
			 * searchPublicPlaceVo.getTotalArea()); map.put("operationArea",
			 * searchPublicPlaceVo.getOperationArea()); map.put("cloakroom",
			 * searchPublicPlaceVo.getCloakroom());
			 * map.put("vouchedPeopleCount",
			 * searchPublicPlaceVo.getVouchedPeopleCount());
			 * map.put("privateRoomCount",
			 * searchPublicPlaceVo.getPrivateRoomCount());
			 * map.put("surrounding", searchPublicPlaceVo.getSurrounding());
			 * map.put("passageway", searchPublicPlaceVo.getPassageway());
			 * map.put("innerStructure",
			 * searchPublicPlaceVo.getInnerStructure()); map.put("remark",
			 * searchPublicPlaceVo.getRemark()); map.put("isEmphasis",
			 * searchPublicPlaceVo.getIsEmphasis());
			 * map.put("attentionExtentId",
			 * searchPublicPlaceVo.getAttentionExtentId()); map.put("sortField",
			 * sidx); map.put("sord", sord);
			 */
			searchPublicPlaceVo.setSortField(sidx);
			searchPublicPlaceVo.setOrder(sord);
		}
		if (rows == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchPublicPlace.searchPlaces", searchPublicPlaceVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchPublicPlace.searchPlaces", searchPublicPlaceVo,
					(page - 1) * rows, rows);
		}

	}

	@Override
	public Integer getCount(SearchPublicPlaceVo searchPublicPlaceVo) {
		// TODO Auto-generated method stub
		if (null == searchPublicPlaceVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPublicPlace.countPlaces", searchPublicPlaceVo);
	}

}
