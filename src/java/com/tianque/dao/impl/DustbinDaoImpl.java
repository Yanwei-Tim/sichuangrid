package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.DustbinDao;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("dustbinDao")
public class DustbinDaoImpl extends AbstractBaseDao implements DustbinDao {

	@Override
	public PageInfo<Dustbin> findDustbinForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis, String partType,
			Long partTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer countNum = 0;
		List<Dustbin> list = null;
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("partTypeId", partTypeId);
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dustbin.countDustbin", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		list = getSqlMapClientTemplate().queryForList("dustbin.findDustbin",
				map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<Dustbin> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Dustbin> pageInfo = new PageInfo<Dustbin>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Dustbin getDustbinById(Long id) {
		return (Dustbin) getSqlMapClientTemplate().queryForObject(
				"dustbin.getDustbinById", id);
	}

	@Override
	public Dustbin addDustbin(Dustbin dustbin) {
		Long id = (Long) getSqlMapClientTemplate().insert("dustbin.addDustbin",
				dustbin);
		return getDustbinById(id);
	}

	@Override
	public Dustbin updateDustbin(Dustbin dustbin) {
		checkIsNull(dustbin);
		getSqlMapClientTemplate().update("dustbin.updateDustbin", dustbin);
		dustbin = getDustbinById(dustbin.getId());
		return dustbin;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("dustbin.updateEmphasiseById", map);
	}

	private void checkIsNull(Dustbin dustbin) {
		if (dustbin == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == dustbin.getDustbinCode()) {
				throw new BusinessValidationException("编码不能为空!");
			}

			if (null == dustbin.getOrganization()
					|| null == dustbin.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public void deleteDustbinById(Long id) {
		getSqlMapClientTemplate().delete("dustbin.deleteDustbinbyId", id);
	}

	@Override
	public Dustbin getDustbinByUnitName(String dustbinCode, Long orgId) {
		Dustbin dustbin = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("dustbinCode", dustbinCode);
		dustbin = (Dustbin) getSqlMapClientTemplate().queryForObject(
				"dustbin.getDustbinByUnitName", query);
		return dustbin;
	}

	@Override
	public Dustbin getDustbinByDustbinCode(String dustbinCode, Long orgId) {
		Dustbin dustbin = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("dustbinCode", dustbinCode);
		dustbin = (Dustbin) getSqlMapClientTemplate().queryForObject(
				"dustbin.getDustbinByDustbinCode", query);
		return dustbin;
	}

	@Override
	public PageInfo<Dustbin> searchDustbinPagerBySearchVo(
			SearchDustbinVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order, String partType) {
		// searchVo.setPartType(partType);
		// searchVo.setTypeName(PropertyTypesYinchuan.PART_TYPE);
		// if("publicFacilities".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.PUBLIC_FACILITIES);
		// }
		// else if("roadTraffic".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.ROAD_TRAFFIC);
		// }
		// else if("cityEnvironmrnt".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.CITY_ENVIRONMENT);
		// }
		// else if("landscaping".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.LANDSCAPING);
		// }
		// else if("houseLand".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.HOUSELAND);
		// }
		// else if("otherFacilities".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.OTHER_FACILITIES);
		// }
		// else if("expansionComponents".equals(partType)){
		// searchVo.setPartTypeName(PropertyTypesYinchuan.EXPANSION_COMPONENTS);
		// }
		searchVo.setSortField(sortField);
		searchVo.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dustbin.countSearchDustbinPagerBySearchVo", searchVo);
		List<Dustbin> resultList = getSqlMapClientTemplate().queryForList(
				"dustbin.searchDustbinPagerBySearchVo", searchVo,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<Dustbin>(pageSize, pageSize, countNum, resultList);
	}
}
