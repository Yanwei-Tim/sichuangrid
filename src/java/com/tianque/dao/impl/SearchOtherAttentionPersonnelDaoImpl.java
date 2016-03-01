package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.otherAttentionPersonnel.dao.SearchOtherAttentionPersonnelDao;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("searchOtherAttentionPersonnelDao")
public class SearchOtherAttentionPersonnelDaoImpl extends AbstractBaseDao
		implements SearchOtherAttentionPersonnelDao {

	@Override
	public PageInfo<OtherAttentionPersonnel> searchOtherAttentionPersonnel(
			SearchOtherAttentionPersonnelVo queryPopulation, int pageNum,
			int pageSize, String sortField, String order) {
		if (queryPopulation == null) {
			return emptyPage(pageSize);
		}
		if (queryPopulation.getIsDeath() != null) {
			if (queryPopulation.getIsDeath() == -1) {
				queryPopulation.setIsDeath(null);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", queryPopulation.getOrgInternalCode());
		map.put("idCardNo", queryPopulation.getIdCardNo());
		map.put("queryQopulation", queryPopulation);
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("populationType", BaseInfoTables.OTHERATTENTIONPERSONNEL_KEY);//用于关联查询服务成员和管理记录
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchotherattentionpersonnel.countSearchOtherAttentionPersonnel",
						map);
		int pageCount = 0;
		count(countNum, pageSize, pageCount, pageNum);
		List<OtherAttentionPersonnel> list = getSqlMapClientTemplate()
				.queryForList(
						"searchotherattentionpersonnel.searchOtherAttentionPersonnels",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(countNum, pageSize, pageNum, list);
	}

	private int count(int countNum, int pageSize, int pageCount, int pageNum) {
		if (countNum / pageSize == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	private PageInfo<OtherAttentionPersonnel> createPageInfo(int countNum,
			int pageSize, int pageNum, List list) {
		PageInfo<OtherAttentionPersonnel> pageInfo = new PageInfo<OtherAttentionPersonnel>();
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private PageInfo<OtherAttentionPersonnel> emptyPage(int pageSize) {
		PageInfo<OtherAttentionPersonnel> pageInfo = new PageInfo<OtherAttentionPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherAttentionPersonnel>());
		return pageInfo;
	}

	@Override
	public List<OtherAttentionPersonnel> searchOtherAttentionPersonnelForExport(
			SearchOtherAttentionPersonnelVo queryQopulation, Integer page,
			Integer rows, String sortField, String order) {
		if (null == queryQopulation) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", queryQopulation.getOrgInternalCode());
		map.put("queryQopulation", queryQopulation);
		map.put("sortField", sortField);
		map.put("order", order);
		if (page == null) {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchotherattentionpersonnel.searchOtherAttentionPersonnels",
							map);
		} else {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchotherattentionpersonnel.searchOtherAttentionPersonnels",
							map, (page - 1) * rows, rows);
		}
	}

	@Override
	public int getCountOtherAttentionPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode 不能为空 ");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchotherattentionpersonnel.getCountOtherAttentionPersonnelByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public Integer getCount(SearchOtherAttentionPersonnelVo personnelVo) {
		if (null == personnelVo) {
			return null;
		}
		if (personnelVo.getIsDeath() != null) {
			if (personnelVo.getIsDeath() == -1) {
				personnelVo.setIsDeath(null);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", personnelVo.getOrgInternalCode());
		map.put("idCardNo", personnelVo.getIdCardNo());
		map.put("queryQopulation", personnelVo);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchotherattentionpersonnel.countSearchOtherAttentionPersonnel",
						map);

	}
}
