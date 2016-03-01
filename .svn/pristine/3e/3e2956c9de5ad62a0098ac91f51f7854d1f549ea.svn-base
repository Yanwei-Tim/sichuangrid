package com.tianque.newVillage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.newVillage.dao.NewVillageBasicDao;
import com.tianque.newVillage.domain.NewVillageBasic;

@Repository("newVillageBasicDao")
public class NewVillageBasicDaoImpl extends AbstractBaseDao implements
		NewVillageBasicDao {

	@Override
	public NewVillageBasic addNewVillageBasic(NewVillageBasic newVillageBasic) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"newVillageBasic.addNewVillageBasic", newVillageBasic);
		return getNewVillageBasicById(id);
	}

	@Override
	public NewVillageBasic updateNewVillageBasic(NewVillageBasic newVillageBasic) {
		getSqlMapClientTemplate().update(
				"newVillageBasic.updateNewVillageBasic", newVillageBasic);
		return getNewVillageBasicById(newVillageBasic.getId());
	}

	@Override
	public void reportNewVillageBasic(Long id, Integer reportStatus,
			Date reportDate, String reportUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("reportStatus", reportStatus);
		map.put("reportDate", reportDate);
		map.put("reportUser", reportUser);
		getSqlMapClientTemplate().update("newVillageBasic.updateReportStatus",
				map);
	}

	@Override
	public void checkNewVillageBasic(Long id, Integer checkStatus,
			Date checkDate, String checkUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("checkStatus", checkStatus);
		map.put("checkDate", checkDate);
		map.put("checkUser", checkUser);
		getSqlMapClientTemplate().update("newVillageBasic.updateCheckStatus",
				map);
	}

	@Override
	public PageInfo<NewVillageBasic> findNewVillageBasicForList(
			NewVillageBasic newVillageBasic, Integer page, Integer rows,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", newVillageBasic.getOrganization().getId());
		map.put("year", newVillageBasic.getYear());
		map.put("quarter", newVillageBasic.getQuarter());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isPlaning", newVillageBasic.getIsPlaning());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"newVillageBasic.countVillageBasicForList", map);

		List<NewVillageBasic> list = getSqlMapClientTemplate().queryForList(
				"newVillageBasic.findVillageBasicForList", map,
				(page - 1) * rows, rows);
		PageInfo<NewVillageBasic> pageInfo = new PageInfo<NewVillageBasic>(
				page, rows, countNum, list);
		return pageInfo;
	}

	@Override
	public NewVillageBasic getNewVillageBasicById(Long id) {
		return (NewVillageBasic) getSqlMapClientTemplate().queryForObject(
				"newVillageBasic.getNewVillageBasicById", id);
	}

	@Override
	public void deleteNewVillageBasicByIds(String[] ids) {
		getSqlMapClientTemplate().delete(
				"newVillageBasic.deleteNewVillageBasicByIds", ids);
	}

	@Override
	public boolean getNewVillageBasicByYear(Long orgId, Integer year,
			Integer isPlaning, Integer quarter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("quarter", quarter);
		map.put("isPlaning", isPlaning);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"newVillageBasic.getNewVillageBasicByYear", map);
		return countNum == null || countNum == 0 ? true : false;
	}

	@Override
	public boolean findSameYearNewVillageBasics(Long orgId, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"newVillageBasic.findSameYearNewVillageBasics", map);
		return count == 0 ? false : true;
	}

}
