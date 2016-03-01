package com.tianque.baseInfo.overseaPersonnel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;
import com.tianque.service.util.PopulationCatalog;

@Repository("overseaPersonnelDao")
public class OverseaPersonnelDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<OverseaPersonnel, SearchOverseaPersonnelVo>
		implements OverseaPersonnelDao {

	@Override
	public OverseaPersonnel addOverseaPersonnel(
			OverseaPersonnel overseaPersonnel) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"overseaPersonnel.addOverseaPersonnel", overseaPersonnel);
		return getOverseaPersonnelById(id);
	}

	@Override
	public OverseaPersonnel getOverseaPersonnelById(Long id) {
		if (id == null)
			return null;

		return (OverseaPersonnel) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnel.getOverseaPersonnelById", id);
	}

	@Override
	public int deleteOverseaPersonnelById(Long id) {
		if (id == null)
			return 0;
		OverseaPersonnel overseaPersonnel = getOverseaPersonnelById(id);
		int deleteCount = getSqlMapClientTemplate().delete(
				"overseaPersonnel.deleteOverseaPersonnelById", id);
		if (overseaPersonnel != null && overseaPersonnel.getId() != null) {
		}
		return deleteCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<OverseaPersonnel> findOverseaPersonnelForListPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order, Long logOut) {
		if (orgInternalCode == null || "".equals(orgInternalCode.trim())) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("logOut", logOut);
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnel.countOverseaPersonnelForListPage", map);
		int pageCount = 0;
		if (countNum % pageSize == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<OverseaPersonnel> list = getSqlMapClientTemplate()
					.queryForList(
							"overseaPersonnel.findOverseaPersonnelForListPage",
							map, (pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);

		} else {
			pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<OverseaPersonnel> emptyPage(int pageSize) {
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		return pageInfo;
	}

	@Override
	public OverseaPersonnel getOverseaPersonnelByCertificateNoAndOrganizationId(
			String certificateNo, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("certificateNo", certificateNo);
		map.put("organizationId", organizationId);
		return (OverseaPersonnel) getSqlMapClientTemplate()
				.queryForObject(
						"overseaPersonnel.getOverseaPersonnelByCertificateNoAndOrganizationId",
						map);
	}

	@Override
	// public OverseaPersonnel getOverseaPersonnelByCertificate(List<String>
	// certificateList, Long orgId) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("organizationId", orgId);
	// map.put("certificateList", certificateList);
	//
	// return (OverseaPersonnel) getSqlMapClientTemplate().queryForObject(
	// "overseaPersonnel.getOverseaPersonnelByCertificate", map);
	// }
	public OverseaPersonnel getOverseaPersonnelByCertificate(
			Long certificateType, String certificateNo, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationId", orgId);
		map.put("certificateType", certificateType);
		map.put("certificateNo", certificateNo);

		return (OverseaPersonnel) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnel.getOverseaPersonnelByCertificate", map);
	}

	@Override
	public OverseaPersonnel updateOverseaPersonnel(
			OverseaPersonnel overseaPersonnel) {
		getSqlMapClientTemplate().update(
				"overseaPersonnel.updateOverseaPersonnel", overseaPersonnel);
		return getOverseaPersonnelById(overseaPersonnel.getId());
	}

	@Override
	public PageInfo<OverseaPersonnel> findOVerseaPersonnelByOrgInternalCodeForGis(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnel.countOverseaPersonnelByOrgForGis", map);
		List<OverseaPersonnel> list = getSqlMapClientTemplate().queryForList(
				"overseaPersonnel.findOverseaPersonnelByOrgForGis", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<OverseaPersonnel> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<OverseaPersonnel> list) {
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public OverseaPersonnel findGisOverseaStaffById(Long personId) {
		return (OverseaPersonnel) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnel.findGisOverseaStaffById", personId);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", address);
		getSqlMapClientTemplate().update(
				"overseaPersonnel.updateActualPopulationToHasHouseState", map);
	}

	@Override
	public List<OverseaPersonnel> findAllBindingOverseaStaffByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("personType", catalog.getParentCatalog());
		map.put("populationType", catalog.getCatalog());
		map.put("defaultLivingHouse", Boolean.TRUE);
		return getSqlMapClientTemplate().queryForList(
				"overseaPersonnel.findAllBindingOverseaStaffByorgCodeForGis",
				map);
	}

}
