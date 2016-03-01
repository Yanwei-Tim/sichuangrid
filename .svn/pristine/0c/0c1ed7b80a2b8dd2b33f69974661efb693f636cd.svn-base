package com.tianque.peopleLog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.PeopleLogAttachFiles;

@Repository("peopleLogDao")
public class PeopleLogDaoImpl extends AbstractBaseDao implements PeopleLogDao {

	@Override
	public PeopleLog addPeopleLog(PeopleLog peopleLog) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"peopleLog.addPeopleLog", peopleLog);
		return getPeopleLogById(id);
	}

	@Override
	public void deletePeopleLogById(long id) {
		getSqlMapClientTemplate().delete("peopleLog.deletePeopleLog", id);
	}

	@Override
	public PeopleLog getPeopleLogById(long id) {
		return (PeopleLog) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getPeopleLogById", id);
	}

	@Override
	public PeopleLog updatePeopleLog(PeopleLog peopleLog) {
		getSqlMapClientTemplate().update("peopleLog.editPeopleLog", peopleLog);
		return getPeopleLogById(peopleLog.getId());
	}

	@Override
	public List<PeopleLog> getPeopleLogByUserId(long userId) {
		return getSqlMapClientTemplate().queryForList(
				"PeopleLog.getPeopleLogByUesrId", userId);
	}

	private PageInfo<PeopleLog> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<PeopleLog> pageInfo = new PageInfo<PeopleLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, Integer commentNums) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("commentNums", commentNums);
		map.put("sortField", sortField);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countPeopleLog", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findAllPeopleLog", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByUserId(Long userId,
			Integer pageNum, Integer pageSize, String sortField, String sord,
			Integer commentNums) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("commentNums", commentNums);
		map.put("sortField", sortField);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countUserPeopleLog", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findUserAllPeopleLog", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public void deleteCommentLogByLogId(long logId) {
		getSqlMapClientTemplate().delete("commentLog.deleteCommentLogByLogId",
				logId);
	}

	public void commentPeopleLog(Integer commentNum, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentNum", commentNum);
		map.put("id", id);
		getSqlMapClientTemplate().update("peopleLog.commentPeopleLog", map);
	}

	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode4Bench(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, String orgName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countAllPeopleLog", map);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findPeopleLog", map, (pageNum - 1) * pageSize,
				pageSize);
		for (PeopleLog peopleLog : list) {
			peopleLog.getOrganization().setOrgName(orgName);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public void addPeopleLogAttachFiles(
			PeopleLogAttachFiles peopleLogAttachFiles) {
		getSqlMapClientTemplate().insert("peopleLog.addPeopleLogAttachFiles",
				peopleLogAttachFiles);
	}

	@Override
	public List<PeopleLogAttachFiles> findPeopleLogAttachFilesByPeopleLogId(
			Long peopleLogId) {
		List<PeopleLogAttachFiles> list = getSqlMapClientTemplate()
				.queryForList(
						"peopleLog.findPeopleLogAttachFilesByPeopleLogId",
						peopleLogId);
		return list;
	}

	@Override
	public PeopleLogAttachFiles getPeopleLogAttachFileById(Long id) {
		return (PeopleLogAttachFiles) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getPeopleLogAttachFileById", id);
	}

	@Override
	public void deletePeopleLogAttachFileById(Long id) {
		getSqlMapClientTemplate().delete(
				"peopleLog.deletePeopleLogAttachFileById", id);
	}

	@Override
	public void deletePeopleLogAttachFilesByPeopleLogId(Long peopleLogId) {
		getSqlMapClientTemplate().delete(
				"peopleLog.deletePeopleLogAttachFilesByPeopleLogId",
				peopleLogId);
	}

}
