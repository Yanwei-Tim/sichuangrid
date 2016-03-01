package com.tianque.baseInfo.buildDatas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.BuilddatasSearchVo;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("builddatasDao")
public class BuilddatasDaoImpl extends BaseDaoImpl<Builddatas, Builddatas>
		implements BuilddatasDao {

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Builddatas> findBuilddatasByOrgInternalCode(
			String orgInternalCode, Integer isBind, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("isBind", isBind);
		params.put("orgInternalCode", orgInternalCode);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBuilddatasByOrgInternalCode", params);

		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		if (sortField != null) {
			params.put("sortField", sortField);
			params.put("order", order);
		}

		params.put("startRow", (pageNum - 1) * pageSize);
		params.put("endRow", pageNum * pageSize);

		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.findBuilddatasByOrgInternalCode", params);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public Builddatas addBuilddatas(Builddatas builddatas) {
		if (builddatas == null)
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"builddatas.addBuilddatas", builddatas);
		return getBuilddatasById(id);
	}

	@Override
	public Builddatas updateBuilddatas(Builddatas builddatas) {
		if (builddatas == null || builddatas.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("builddatas.updateBuilddatas",
				builddatas);
		return getBuilddatasById(builddatas.getId());
	}

	@Override
	public Builddatas getBuilddatasById(Long id) {
		if (id == null)
			throw new BusinessValidationException("参数错误");
		return (Builddatas) getSqlMapClientTemplate().queryForObject(
				"builddatas.getBuilddatasById", id);
	}

	@Override
	public void deleteBuilddatasById(Long id) {
		if (id == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("builddatas.deleteBuilddatasById", id);
	};

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Builddatas> searchBuilddatas(
			BuilddatasSearchVo builddatasSearchVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		builddatasSearchVo.setOrginternalcode(builddatasSearchVo
				.getOrginternalcode());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBuilddatasBySearch", builddatasSearchVo);
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (sortField != null) {
			builddatasSearchVo.setSortField(sortField);
			builddatasSearchVo.setOrder(order);
		}
		builddatasSearchVo.setStartRow((pageNum - 1) * pageSize);
		builddatasSearchVo.setEndRow(pageNum * pageSize);
		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.searchBuilddatas", builddatasSearchVo);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public Builddatas getBuilddatasByBuildId(String buildId) {
		if (buildId == null)
			throw new BusinessValidationException("参数错误");
		return (Builddatas) getSqlMapClientTemplate().queryForObject(
				"builddatas.getBuilddatasByBuildId", buildId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Builddatas> findUnBoundBuilddatasByStr(String str,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String orgInternalCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("str", str);
		params.put("orgInternalCode", orgInternalCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countUnBoundBuilddatas", params);
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (sortField != null) {
			params.put("sortField", sortField);
			params.put("order", order);
		}
		params.put("startRow", (pageNum - 1) * pageSize);
		params.put("endRow", pageNum * pageSize);
		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.findUnBoundBuilddatasByStr", params);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public Builddatas updateBuilddatasByBuilddingId(Builddatas builddatas) {
		if (builddatas == null || builddatas.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update(
				"builddatas.updateBuilddatasByBuildingId", builddatas);
		return getBuilddatasById(builddatas.getId());
	}

	@Override
	public void updateBuilddatasByFeatureId(String featureId) {
		getSqlMapClientTemplate().update(
				"builddatas.updateBuilddatasByFeatureId", featureId);
	}

	@Override
	public Integer countBoundBuildDatasByLonAndLatArrays(
			Double[] lonAndLatArrays) {
		Map<String, Object> pointMap = new HashMap<String, Object>();
		pointMap.put("maxX", lonAndLatArrays[0]);
		pointMap.put("minX", lonAndLatArrays[2]);
		pointMap.put("maxY", lonAndLatArrays[1]);
		pointMap.put("minY", lonAndLatArrays[3]);
		Integer boundNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBoundBuildDatasByLonAndLatArrays", pointMap);
		return boundNum;
	}

	@Override
	public Integer countBuildDatasByLonAndLatArrays(Double[] lonAndLatArrays) {
		Map<String, Object> pointMap = new HashMap<String, Object>();
		pointMap.put("maxX", lonAndLatArrays[0]);
		pointMap.put("minX", lonAndLatArrays[2]);
		pointMap.put("maxY", lonAndLatArrays[1]);
		pointMap.put("minY", lonAndLatArrays[3]);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBuildDatasByLonAndLatArrays", pointMap);
		return countNum;
	}

	@Override
	public Integer countBoundBuildDatas(String orgCode) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBoundBuilddatasByOrgCode", orgCode);
		return countNum;
	}

	@Override
	public Integer countUnBoundBuilddatasByOrgCode(String orgCode) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countUnBoundBuilddatasByOrgCode", orgCode);
		return countNum;
	}

	@Override
	public Integer countBoundBuildDatasByOrgCode(String orginternalcode) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBoundBuildDatasByOrgCode", orginternalcode);
		return countNum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Builddatas> findBoundBuilddatasByOrgCode(String orgCode,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBoundBuilddatasByOrgCode");
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (sortField != null) {
			params.put("sortField", sortField);
			params.put("order", order);
		}
		params.put("orginternalcode", orgCode);
		params.put("startRow", (pageNum - 1) * pageSize);
		params.put("endRow", pageNum * pageSize);
		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.findBoundBuilddatasByOrgCode", params);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Builddatas> findUnBoundBuilddatasByOrgCode(String orgCode,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countUnBoundBuilddatasByOrgCode");
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (sortField != null) {
			params.put("sortField", sortField);
			params.put("order", order);
		}
		params.put("orginternalcode", orgCode);
		params.put("startRow", (pageNum - 1) * pageSize);
		params.put("endRow", pageNum * pageSize);
		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.findUnBoundBuilddatasByOrgCode", params);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public void boundBuilddatas(Builddatas builddatas) {
		getSqlMapClientTemplate().update("builddatas.boundBuilddatas",
				builddatas);

	}

	@Override
	public void unboundBuilddatas(Builddatas builddatas) {
		getSqlMapClientTemplate().update("builddatas.unboundBuilddatas",
				builddatas);
	}

	@Override
	public PageInfo<Builddatas> searchBuilddatasByNameAndAddress(
			BuilddatasSearchVo builddatasSearchVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		builddatasSearchVo.setOrginternalcode(builddatasSearchVo
				.getOrginternalcode());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"builddatas.countBuilddatasByNameAndAddress",
				builddatasSearchVo);
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (sortField != null) {
			builddatasSearchVo.setSortField(sortField);
			builddatasSearchVo.setOrder(order);
		}
		builddatasSearchVo.setStartRow((pageNum - 1) * pageSize);
		builddatasSearchVo.setEndRow(pageNum * pageSize);
		List<Builddatas> result = getSqlMapClientTemplate().queryForList(
				"builddatas.searchBuilddatasByNameAndAddress",
				builddatasSearchVo);
		PageInfo<Builddatas> pageInfo = new PageInfo<Builddatas>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public List<Builddatas> findBuildDatasByBuildId(String builId) {
		 
		return getSqlMapClientTemplate().queryForList("builddatas.findBuildDatasByBuildId",builId);
	}
}
