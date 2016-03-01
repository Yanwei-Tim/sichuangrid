package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.WorkBulletinsDao;
import com.tianque.working.domain.WorkBulletin;

/**
 * 工作简报基础信息
 * 
 * @author wangshirui
 */
@Repository("workBulletinsDao")
public class WorkBulletinsDaoImpl extends AbstractBaseDao implements WorkBulletinsDao {

	@Override
	public void deleteWorkBulletinById(Long id) {
		getSqlMapClientTemplate().delete("WORKBULLETINS.deleteWorkBulletinById", id);
	}

	@Override
	public WorkBulletin addWorkBulletin(WorkBulletin record) {
		Long id = (Long) getSqlMapClientTemplate().insert("workBulletin.addWorkBulletins", record);
		record.setId(id);
		return record;
	}

	@Override
	public WorkBulletin findWorkBulletinById(Long id) {
		if (id == null) {
			return null;
		}
		WorkBulletin key = new WorkBulletin();
		key.setId(id);
		WorkBulletin record = (WorkBulletin) getSqlMapClientTemplate().queryForObject(
				"workBulletin.findByWorkBulletinId", key);
		return record;
	}

	@Override
	public int updateWorkBulletin(WorkBulletin record) {
		int rows = getSqlMapClientTemplate().update("workBulletin.updateWorkBulletin", record);
		return rows;
	}

	@Override
	public PageInfo<WorkBulletin> findWorkBulletinForPageByOrgInternalCode(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		// Integer countNum = 11;
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"WORKBULLETINS.countWorkBulletins", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<WorkBulletin> list = getSqlMapClientTemplate().queryForList(
				"WORKBULLETINS.findWorkBulletins", map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);

	}

	private PageInfo<WorkBulletin> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<WorkBulletin> pageInfo = new PageInfo<WorkBulletin>();
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