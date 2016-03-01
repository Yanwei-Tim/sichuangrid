package com.tianque.baseInfo.landscaping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.landscaping.domain.Landscaping;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("landscapingDao")
public class LandscapingDaoImpl extends AbstractBaseDao implements
		LandscapingDao {

	@Override
	public PageInfo<Landscaping> findLandscapingForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer countNum = 0;
		List<Landscaping> list = null;
		map.put("orgInternalCode", orgInternalCode);

		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"landscaping.countLandscaping", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		list = getSqlMapClientTemplate().queryForList(
				"landscaping.findLandscaping", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<Landscaping> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Landscaping> pageInfo = new PageInfo<Landscaping>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Landscaping getLandscapingById(Long id) {

		return (Landscaping) getSqlMapClientTemplate().queryForObject(
				"landscaping.getLandscapingById", id);
	}

	@Override
	public Landscaping addLandscaping(Landscaping landscaping) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"landscaping.addLandscaping", landscaping);
		return getLandscapingById(id);
	}

	@Override
	public Landscaping updateLandscaping(Landscaping landscaping) {
		checkIsNull(landscaping);
		try {
			getSqlMapClientTemplate().update("landscaping.updateLandscaping",
					landscaping);
		} catch (Exception e) {
			e.printStackTrace();
		}
		landscaping = getLandscapingById(landscaping.getId());
		return landscaping;
	}

	private void checkIsNull(Landscaping landscaping) {
		if (landscaping == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == landscaping.getObjName()) {
				throw new BusinessValidationException("部件名称不能为空!");
			}

			if (null == landscaping.getOrganization()
					|| null == landscaping.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public void deleteLandscapingById(Long id) {
		getSqlMapClientTemplate().delete("landscaping.deleteLandscapingbyId",
				id);
	}

	@Override
	public Landscaping getLandscapingByObjNum(String objNum, Long orgId) {
		Landscaping landscaping = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("objNum", objNum);
		landscaping = (Landscaping) getSqlMapClientTemplate().queryForObject(
				"landscaping.getLandscapingByObjNum", query);
		return landscaping;
	}
	/*
	 * @Override public void updateEmphasiseById(Long id, Boolean
	 * isEmphasis,String logOutReason) { Map map = new HashMap(); map.put("id",
	 * id); map.put("isEmphasis", isEmphasis); map.put("logOutReason",
	 * logOutReason); try{
	 * getSqlMapClientTemplate().update("landscaping.updateEmphasiseById",map);
	 * }catch (Exception e) { e.printStackTrace(); } }
	 */

}
