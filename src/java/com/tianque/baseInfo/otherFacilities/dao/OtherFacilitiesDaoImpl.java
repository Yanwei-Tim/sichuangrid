package com.tianque.baseInfo.otherFacilities.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.otherFacilities.domain.OtherFacilities;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("otherFacilitiesDao")
public class OtherFacilitiesDaoImpl extends AbstractBaseDao implements
		OtherFacilitiesDao {

	@Override
	public PageInfo<OtherFacilities> findOtherFacilitiesForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer countNum = 0;
		List<OtherFacilities> list = null;
		map.put("orgInternalCode", orgInternalCode);

		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"otherFacilities.countOtherFacilities", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		list = getSqlMapClientTemplate().queryForList(
				"otherFacilities.findOtherFacilities", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<OtherFacilities> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<OtherFacilities> pageInfo = new PageInfo<OtherFacilities>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public OtherFacilities getOtherFacilitiesById(Long id) {

		return (OtherFacilities) getSqlMapClientTemplate().queryForObject(
				"otherFacilities.getOtherFacilitiesById", id);
	}

	@Override
	public OtherFacilities addOtherFacilities(OtherFacilities otherFacilities) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"otherFacilities.addOtherFacilities", otherFacilities);
		return getOtherFacilitiesById(id);

	}

	@Override
	public OtherFacilities updateOtherFacilities(OtherFacilities otherFacilities) {
		checkIsNull(otherFacilities);
		getSqlMapClientTemplate().update(
				"otherFacilities.updateOtherFacilities", otherFacilities);
		otherFacilities = getOtherFacilitiesById(otherFacilities.getId());
		return otherFacilities;
	}

	/*
	 * @Override public void updateEmphasiseById(Long id, Boolean
	 * isEmphasis,String logOutReason) { Map map = new HashMap(); map.put("id",
	 * id); map.put("isEmphasis", isEmphasis); map.put("logOutReason",
	 * logOutReason); try{
	 * getSqlMapClientTemplate().update("otherFacilities.updateEmphasiseById"
	 * ,map); }catch (Exception e) { e.printStackTrace(); } }
	 */
	private void checkIsNull(OtherFacilities otherFacilities) {
		if (otherFacilities == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == otherFacilities.getObjName()) {
				throw new BusinessValidationException("部件名称不能为空!");
			}

			if (null == otherFacilities.getOrganization()
					|| null == otherFacilities.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public void deleteOtherFacilitiesById(Long id) {
		getSqlMapClientTemplate().delete(
				"otherFacilities.deleteOtherFacilitiesbyId", id);
	}

	@Override
	public OtherFacilities getOtherFacilitiesByObjNum(String objNum, Long orgId) {
		OtherFacilities otherFacilities = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("objNum", objNum);
		otherFacilities = (OtherFacilities) getSqlMapClientTemplate()
				.queryForObject("otherFacilities.getOtherFacilitiesByObjNum",
						query);
		return otherFacilities;
	}

}
