package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchEstateInformationDao;
import com.tianque.domain.EstateInformation;
import com.tianque.domain.vo.SearchEstateInformationVo;

@Repository("searchEstateInformationDao")
public class SearchEstateInformationDaoImpl extends AbstractBaseDao implements
		SearchEstateInformationDao {

	@Override
	public PageInfo<EstateInformation> searchEstateInformations(
			SearchEstateInformationVo searchEstateInformationVo, int pageNum, int pageSize,
			String sortField, String order) {
		if (searchEstateInformationVo == null) {
			return emptyEstateInformationPage(pageSize);
		}
		Map<String, Object> map = createCondition(searchEstateInformationVo);
		Integer countNum = (Integer) this.getSqlMapClientTemplate().queryForObject(
				"searchEstateInformation.countEstateInformation", map);
		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<EstateInformation> list = new ArrayList<EstateInformation>();
		if (countNum > 0) {
			list = getSqlMapClientTemplate().queryForList(
					"searchEstateInformation.searchEstateInformations", map,
					(pageNum - 1) * pageSize, pageSize);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<EstateInformation> emptyEstateInformationPage(int pageSize) {
		PageInfo<EstateInformation> pageInfo = new PageInfo<EstateInformation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<EstateInformation>());
		return pageInfo;
	}

	private Map<String, Object> createCondition(SearchEstateInformationVo searchEstateInformationVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", searchEstateInformationVo.getName());
		map.put("idCardNo", searchEstateInformationVo.getIdCardNo());
		map.put("orgInternalCode", searchEstateInformationVo.getOrgInternalCode());
		map.put("proprietaryNo", searchEstateInformationVo.getProprietaryNo());
		map.put("housePropertyPlace", searchEstateInformationVo.getHousePropertyPlace());
		return map;
	}

	private PageInfo<EstateInformation> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List<EstateInformation> list) {
		if (null == list || list.size() < 1) {
			return emptyEstateInformationPage(pageSize);
		}
		PageInfo<EstateInformation> pageInfo = new PageInfo<EstateInformation>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public List<EstateInformation> searchEstateInformationForExport(
			SearchEstateInformationVo searchEstateInformationVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map<String, Object> map = createCondition(searchEstateInformationVo);
		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchEstateInformation.searchEstateInformations", map);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchEstateInformation.searchEstateInformations", map,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

}
