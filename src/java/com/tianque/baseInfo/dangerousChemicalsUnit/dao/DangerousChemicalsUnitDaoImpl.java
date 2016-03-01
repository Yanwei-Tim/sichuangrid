package com.tianque.baseInfo.dangerousChemicalsUnit.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("dangerousChemicalsUnitDao")
public class DangerousChemicalsUnitDaoImpl extends AbstractBaseDao implements
		DangerousChemicalsUnitDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<DangerousChemicalsUnit> findDangerChemUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer countNum = 0;
		List<DangerousChemicalsUnit> list = null;
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);

		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dangerousChemicalsUnit.countDangerousChemicalsUnit", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		map.put("importantLocationType", "DANGEROUSCHEMICALSUNIT");
		list = getSqlMapClientTemplate().queryForList(
				"dangerousChemicalsUnit.findDangerousChemicalsUnit", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<DangerousChemicalsUnit> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<DangerousChemicalsUnit> pageInfo = new PageInfo<DangerousChemicalsUnit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public DangerousChemicalsUnit getDangerousChemicalsUnitById(Long id) {
		return (DangerousChemicalsUnit) getSqlMapClientTemplate()
				.queryForObject(
						"dangerousChemicalsUnit.getDangerousChemicalsUnitById",
						id);

	}

	@Override
	public DangerousChemicalsUnit addDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"dangerousChemicalsUnit.addDangerousChemicalsUnit",
				dangerousChemicalsUnit);
		return getDangerousChemicalsUnitById(id);
	}

	@Override
	public DangerousChemicalsUnit updateDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		checkIsNull(dangerousChemicalsUnit);
		getSqlMapClientTemplate().update(
				"dangerousChemicalsUnit.updateDangerousChemicalsUnit",
				dangerousChemicalsUnit);
		dangerousChemicalsUnit = getDangerousChemicalsUnitById(dangerousChemicalsUnit
				.getId());
		return dangerousChemicalsUnit;
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logOutReason) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logOutReason);
		map.put("logOutTime", new Date());
		getSqlMapClientTemplate().update(
				"dangerousChemicalsUnit.updateEmphasiseById", map);
	}

	private void checkIsNull(DangerousChemicalsUnit dangerousChemicalsUnit) {
		if (dangerousChemicalsUnit == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == dangerousChemicalsUnit.getUnitName()) {
				throw new BusinessValidationException("姓名不能为空!");
			}

			if (null == dangerousChemicalsUnit.getOrganization()
					|| null == dangerousChemicalsUnit.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public void deleteDangerousChemicalsUnitById(Long id) {
		// SiteMsg msg = new SiteMsg(getDangerousChemicalsUnitById(id),
		// OperateMode.DELETE);
		getSqlMapClientTemplate().delete(
				"dangerousChemicalsUnit.deleteDangerousChemicalsUnitbyId", id);
		// activeMQMessageSender.send(msg);
	}

	@Override
	public DangerousChemicalsUnit getDangerousChemicalsUnitByUnitName(
			String unitName, Long orgId) {
		DangerousChemicalsUnit dangerousChemicalsUnit = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("unitName", unitName);
		dangerousChemicalsUnit = (DangerousChemicalsUnit) getSqlMapClientTemplate()
				.queryForObject(
						"dangerousChemicalsUnit.getDangerousChemicalsUnitByUnitName",
						query);
		return dangerousChemicalsUnit;
	}

	@Override
	public DangerousChemicalsUnit getDangerousChemicalsUnitByUnitNameAndOrgId(
			String unitName, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitName", unitName);
		map.put("orgId", id);
		return (DangerousChemicalsUnit) getSqlMapClientTemplate()
				.queryForObject(
						"dangerousChemicalsUnit.getDangerousChemicalsUnitByMap",
						map);
	}

}
