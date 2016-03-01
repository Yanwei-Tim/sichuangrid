package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.KeyAreasOfInvestigationInfoWorkingRecordDao;
import com.tianque.domain.workingRecord.KeyAreasOfInvestigationInfoWorkingRecord;

@Repository("keyAreasOfInvestigationInfoWorkingRecordDao")
public class KeyAreasOfInvestigationInfoWorkingRecordDaoImpl extends AbstractBaseDao implements
		KeyAreasOfInvestigationInfoWorkingRecordDao {

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord addKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("keyAreasOfInvestigationInfoWorkingRecord.addKeyAreasOfInvestigationInfoWorkingRecord",
						keyAreasOfInvestigationInfoWorkingRecord);
		return getKeyAreasOfInvestigationInfoWorkingRecordById(id);
	}

	@Override
	public void deleteKeyAreasOfInvestigationInfoWorkingRecord(Long id) {
		getSqlMapClientTemplate()
				.delete("keyAreasOfInvestigationInfoWorkingRecord.deleteKeyAreasOfInvestigationInfoWorkingRecordById",
						id);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfoWorkingRecord> findKeyAreasOfInvestigationInfoWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx, String sord,
			String dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgId", orgId);
		map.put("dailyDirectoryId", dailyDirectoryId);

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfoWorkingRecord.countKeyAreasOfInvestigationInfoWorkingRecord",
						map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<KeyAreasOfInvestigationInfoWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList(
						"keyAreasOfInvestigationInfoWorkingRecord.findKeyAreasOfInvestigationInfoWorkingRecord",
						map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord getKeyAreasOfInvestigationInfoWorkingRecordById(
			Long id) {
		return (KeyAreasOfInvestigationInfoWorkingRecord) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfoWorkingRecord.getKeyAreasOfInvestigationInfoWorkingRecordById",
						id);
	}

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord updateKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		getSqlMapClientTemplate()
				.update("keyAreasOfInvestigationInfoWorkingRecord.updateKeyAreasOfInvestigationInfoWorkingRecord",
						keyAreasOfInvestigationInfoWorkingRecord);
		return getKeyAreasOfInvestigationInfoWorkingRecordById(keyAreasOfInvestigationInfoWorkingRecord
				.getId());
	}

	private PageInfo<KeyAreasOfInvestigationInfoWorkingRecord> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<KeyAreasOfInvestigationInfoWorkingRecord> pageInfo = new PageInfo<KeyAreasOfInvestigationInfoWorkingRecord>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}
}
