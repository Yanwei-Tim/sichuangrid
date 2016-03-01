package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.DailyDirectoryDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyDirectoryAttachFile;

@Repository("dailyDirectoryDao")
public class DailyDirectoryDaoImpl extends AbstractBaseDao implements
		DailyDirectoryDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public DailyDirectory addDailyDirectory(DailyDirectory dailyDirectory) {
		if (!validateByadd(dailyDirectory))
			throw new BusinessValidationException();
		Long id = (Long) getSqlMapClientTemplate().insert(
				"dailyDirectory.addDailyDirectory", dailyDirectory);
		DailyDirectory directory = getSimpleDailyDirectoryById(id);
		String key = "";
		if (null == directory.getParentDailyDirectory()
				|| null == directory.getParentDailyDirectory().getId()) {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
							+ dailyDirectory.getDailyYear().getId());
		} else {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailySubDirectoryByParentId_"
							+ directory.getParentDailyDirectory().getId());
		}
		cacheService.remove(key);
		return directory;
	}

	private boolean validateByadd(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null)
			return false;
		if (!StringUtil.isStringAvaliable(dailyDirectory.getFullName()))
			return false;
		if (dailyDirectory.getDailyYear() == null
				|| dailyDirectory.getDailyYear().getId() == null)
			return false;

		return true;
	}

	@Override
	public int deleteDailyDirectoryById(Long dailyDirectoryId) {
		DailyDirectory directory = getSimpleDailyDirectoryById(dailyDirectoryId);
		String key = "";
		if (null == directory.getParentDailyDirectory()
				|| null == directory.getParentDailyDirectory().getId()) {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
							+ directory.getDailyYear().getId());
		} else {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailySubDirectoryByParentId_"
							+ directory.getParentDailyDirectory().getId());
		}
		int count = getSqlMapClientTemplate().delete(
				"dailyDirectory.deleteDailyDirectoryById", dailyDirectoryId);
		cacheService.remove(key);
		return count;
	}

	@Override
	public DailyDirectory getSimpleDailyDirectoryById(Long id) {
		DailyDirectory result = (DailyDirectory) getSqlMapClientTemplate()
				.queryForObject("dailyDirectory.getSimpleDailyDirectoryById",
						id);
		return result;
	}

	@Override
	public DailyDirectory updateDailyDirectory(DailyDirectory dailyDirectory) {
		if (!validateByUpdate(dailyDirectory))
			throw new BusinessValidationException();
		getSqlMapClientTemplate().update("dailyDirectory.updateDailyDirectory",
				dailyDirectory);
		DailyDirectory directory = getSimpleDailyDirectoryById(dailyDirectory
				.getId());
		String key = "";
		if (null == directory.getParentDailyDirectory()
				|| null == directory.getParentDailyDirectory().getId()) {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
							+ directory.getDailyYear().getId());
		} else {
			key = CacheKeyGenerator.generateCacheKeyFromString(
					DailyDirectory.class, "findDailySubDirectoryByParentId_"
							+ directory.getParentDailyDirectory().getId());
		}
		cacheService.remove(key);
		return directory;
	}

	private boolean validateByUpdate(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null || dailyDirectory.getId() == null)
			return false;
		if (!StringUtil.isStringAvaliable(dailyDirectory.getFullName()))
			return false;
		if (dailyDirectory.getDailyYear() == null
				|| dailyDirectory.getDailyYear().getId() == null)
			return false;

		return true;
	}

	@Override
	public List<DailyDirectory> findDailyAllTrunkDirectory() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trunkDirectory", true);
		map.put("sortField", "indexId");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList(
				"dailyDirectory.findDailyDirectorys", map);
	}

	@Override
	public PageInfo<DailyDirectory> findDailySubDirectoryByParentIdForPage(
			Long id, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.countDailyDirectorys", map);

		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;

		List<DailyDirectory> list = getSqlMapClientTemplate().queryForList(
				"dailyDirectory.findDailyDirectorys", map);
		PageInfo<DailyDirectory> pageInfo = new PageInfo<DailyDirectory>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public List<DailyDirectory> findDailySubDirectoryByParentId(Long id) {
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailySubDirectoryByParentId_" + id);
		List<DailyDirectory> dailyDirectories = (List<DailyDirectory>) cacheService
				.get(key);
		if (dailyDirectories == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", id);
			map.put("sortField", "indexid");
			map.put("order", "asc");
			dailyDirectories = getSqlMapClientTemplate().queryForList(
					"dailyDirectory.findDailyDirectorys", map);
			cacheService.set(key, dailyDirectories);
		}
		return dailyDirectories;
	}

	@Override
	public List<DailyDirectory> findDailyDirectoryByDailyYearId(Long id) {
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_" + id);
		List<DailyDirectory> dailyDirectories = (List<DailyDirectory>) cacheService
				.get(key);
		if (null == dailyDirectories) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dailyYearId", id);
			map.put("sortField", "indexId");
			map.put("order", "asc");
			dailyDirectories = getSqlMapClientTemplate().queryForList(
					"dailyDirectory.findDailyDirectorys", map);
			cacheService.set(key, cacheService);
		}
		return dailyDirectories;
	}

	@Override
	public List<DailyDirectory> findDailyTrunkDirectoryByDailyYearId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyYearId", id);
		map.put("trunkDirectory", true);
		map.put("sortField", "indexId");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList(
				"dailyDirectory.findDailyDirectorys", map);
	}

	@Override
	public List<DailyDirectory> findRangeDailyDirectoryByDailyYearId(Long id,
			List<Long> dailydirectoryIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyYearId", id);
		map.put("dailyDirectoryIds", dailydirectoryIds);
		return getSqlMapClientTemplate().queryForList(
				"dailyDirectory.findRangeDailyDirectoryByDailyYearId", map);
	}

	@Override
	public Integer countExsistedTrunkDirectory(String shortName,
			Long dailyYearId, Long besidesId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shortName", shortName);
		map.put("dailyYearId", dailyYearId);
		map.put("besidesId", besidesId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.countExsistedTrunkDirectory", map);
		return countNum;
	}

	@Override
	public Integer countExsistedSubDirectory(String shortName, Long parentId,
			Long besidesId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (shortName != null) {
			map.put("fullName", shortName);
		}
		map.put("parentId", parentId);
		map.put("dailyYearId", besidesId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.countExsistedSubDirectory", map);
		return countNum;
	}

	@Override
	public Integer getIndexIdByParentId(Long parentId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getIndexIdByParentId", parentId);
	}

	@Override
	public Integer getDailyTrunkDirectotyIndexIdByDailyYearId(Long dailyYearId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getDailyTrunkDirectoryIndexIdByDailyYearId",
				dailyYearId);
	}

	@Override
	public void moveDailySubDirectoryToFirst(Long id, Long parentId,
			Integer indexId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("parentId", parentId);
		map.put("indexId", indexId);
		getSqlMapClientTemplate()
				.update("dailyDirectory.moveAllIndexNext", map);
		getSqlMapClientTemplate().update(
				"dailyDirectory.moveDailyDirectoryIndexToFirst", map);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailySubDirectoryByParentId_"
						+ parentId);
		cacheService.remove(key);
	}

	@Override
	public void moveDailySubDirectoryToEnd(Long id, Long parentId,
			Integer indexId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("parentId", parentId);
		map.put("indexId", indexId);
		getSqlMapClientTemplate().update("dailyDirectory.moveAllIndexPrevious",
				map);
		getSqlMapClientTemplate().update("dailyDirectory.moveToEnd", map);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailySubDirectoryByParentId_"
						+ parentId);
		cacheService.remove(key);
	}

	@Override
	public void moveDailySubDirectoryToPrevious(Long id, Long parentId,
			Integer indexId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("id", id);
		query.put("parentId", parentId);
		query.put("ownIndexId", indexId);

		Long privOrNextId = (Long) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getBeforeDailySubDirectoryId", query);

		processMove(id, indexId, privOrNextId);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailySubDirectoryByParentId_"
						+ parentId);
		cacheService.remove(key);
	}

	public void moveDailySubDirectoryToNext(Long id, Long parentId,
			Integer indexId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("id", id);
		query.put("parentId", parentId);
		query.put("ownIndexId", indexId);

		Long privOrNextId = (Long) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getAfterDailySubDirectoryId", query);

		processMove(id, indexId, privOrNextId);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailySubDirectoryByParentId_"
						+ parentId);
		cacheService.remove(key);
	}

	@Override
	public void moveDailyTrunkDirectoryToFirst(Long id, Long dailyYearId,
			Integer indexId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("dailyYearId", dailyYearId);
		map.put("indexId", indexId);
		getSqlMapClientTemplate().update(
				"dailyDirectory.moveAllDailyTrunkDirectoryIndexNext", map);
		getSqlMapClientTemplate().update(
				"dailyDirectory.moveDailyDirectoryIndexToFirst", map);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
						+ dailyYearId);
		cacheService.remove(key);
	}

	@Override
	public void moveDailyTrunkDirectoryToEnd(Long id, Long dailyYearId,
			Integer indexId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("dailyYearId", dailyYearId);
		map.put("indexId", indexId);
		getSqlMapClientTemplate().update(
				"dailyDirectory.moveAllDailyTrunkDirectoryIndexPrevious", map);
		getSqlMapClientTemplate().update(
				"dailyDirectory.moveDailyTrunkDirectoryToEnd", map);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
						+ dailyYearId);
		cacheService.remove(key);
	}

	@Override
	public void moveDailyTrunkDirectoryToNext(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("ownIndexId", indexId);
		query.put("dailyYearId", dailyYearId);
		Long privOrNextId = (Long) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getAfterDailyTrunkDirectoryId", query);
		processMove(id, indexId, privOrNextId);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
						+ dailyYearId);
		cacheService.remove(key);
	}

	public void moveDailyTrunkDirectoryToPrevious(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("ownIndexId", indexId);
		query.put("dailyYearId", dailyYearId);
		Long privOrNextId = (Long) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.getBeforeDailyTrunkDirectoryId", query);
		processMove(id, indexId, privOrNextId);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
						+ dailyYearId);
		cacheService.remove(key);
	}

	private void processMove(Long id, Integer indexId, Long privOrNextId) {
		if (privOrNextId != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("indexId", indexId);
			map.put("dailyDirectoryId", privOrNextId);

			getSqlMapClientTemplate().update("dailyDirectory.changeIndexOwn",
					map);
			getSqlMapClientTemplate().update(
					"dailyDirectory.updateIndexByDailyDirectoryId", map);
		}
	}

	@Override
	public int isYesrsCount(Long yearId) {
		int sun = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.isYearCount", yearId);
		int num = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.issueYearCount", yearId);
		int kum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.keyAreasYearCount", yearId);
		return sun + num + kum;
	}

	@Override
	public void deleteDailyDirectoryByYearId(Long yearId) {
		getSqlMapClientTemplate().delete(
				"dailyDirectory.deleteDailyDirectoryByYearId", yearId);
		String key = CacheKeyGenerator.generateCacheKeyFromString(
				DailyDirectory.class, "findDailyDirectoryByDailyYearId_"
						+ yearId);
		cacheService.remove(key);
	}

	public int isIssueinspectByDailyDirectoryId(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyDirectory.isIssueinspectByDailyDirectoryId", id);
	}

	@Override
	public List<DailyDirectory> findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId(
			Long orgId, Long type, Long directoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("type", type);
		map.put("directoryId", directoryId);
		return (List<DailyDirectory>) getSqlMapClientTemplate()
				.queryForList(
						"dailyDirectory.findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId",
						map);
	}

	@Override
	public DailyDirectoryAttachFile addDailyDirectoryAttachFile(
			DailyDirectoryAttachFile dailyDirectoryAttachFile) {
		if (!validateByAddDailyDirectoryAttachFile(dailyDirectoryAttachFile)) {
			throw new BusinessValidationException();
		}
		Long fileId = (Long) getSqlMapClientTemplate().insert(
				"dailyDirectory.addDailyDirectoryAttachFile",
				dailyDirectoryAttachFile);
		return this.getDailyDirectoryAttachFilesByFileId(fileId);
	}

	private boolean validateByAddDailyDirectoryAttachFile(
			DailyDirectoryAttachFile attachFile) {
		if (attachFile == null)
			return false;
		if (!StringUtil.isStringAvaliable(attachFile.getFileName()))
			return false;
		if (!StringUtil.isStringAvaliable(attachFile.getFileActualUrl()))
			return false;
		if (attachFile.getDailyDirectory() == null
				|| attachFile.getDailyDirectory().getId() == null)
			return false;
		if (attachFile.getDailyYear() == null
				|| attachFile.getDailyYear().getId() == null)
			return false;
		return true;
	}

	@Override
	public void deleteDailyDirectoryAttachFileByFileId(Long fileId) {
		getSqlMapClientTemplate()
				.delete("dailyDirectory.deleteDailyDirectoryAttachFileByFileId",
						fileId);
	}

	@Override
	public void deleteDailyDirectoryAttachFileByDailyDirectoryId(
			Long dailyDirectoryId) {
		getSqlMapClientTemplate()
				.delete("dailyDirectory.deleteDailyDirectoryAttachFileByDailyDirectoryId",
						dailyDirectoryId);
	}

	@Override
	public List<DailyDirectoryAttachFile> findDailyDirectoryAttachFilesByDailyDirectoryId(
			Long dailyDirectoryId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"dailyDirectory.findDailyDirectoryAttachFilesByDailyDirectoryId",
						dailyDirectoryId);
	}

	@Override
	public DailyDirectoryAttachFile getDailyDirectoryAttachFilesByFileId(
			Long fileId) {
		return (DailyDirectoryAttachFile) getSqlMapClientTemplate()
				.queryForObject(
						"dailyDirectory.getDailyDirectoryAttachFilesByFileId",
						fileId);
	}

	@Override
	public List<DailyDirectory> findDailyDirectorysForRemind() {
		return getSqlMapClientTemplate().queryForList(
				"dailyDirectory.findDailyDirectorysForRemind");
	}

	@Override
	public DailyDirectory getDailyDirectoryByDailyYearIdAndTypeIdAndReportedTypeId(
			Long dailyYearId, Long typeId, Long reportedTypeId) {
		if (null == dailyYearId || null == typeId || null == reportedTypeId) {
			throw new BusinessValidationException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyYearId", dailyYearId);
		map.put("typeId", typeId);
		map.put("reportedTypeId", reportedTypeId);
		return (DailyDirectory) getSqlMapClientTemplate()
				.queryForObject(
						"dailyDirectory.getDailyDirectoryByDailyYearIdAndTypeIdAndReportedTypeId",
						map);
	}

	@Override
	public DailyDirectory getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(
			Long orgId, Long typeId, Long reportTypeId, Long directoryId) {
		if (null == orgId || null == typeId || null == directoryId) {
			throw new BusinessValidationException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("typeId", typeId);
		map.put("reportTypeId", reportTypeId);
		map.put("directoryId", directoryId);
		return (DailyDirectory) getSqlMapClientTemplate()
				.queryForObject(
						"dailyDirectory.getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId",
						map);
	}

	@Override
	public List<DailyDirectory> findLeafDailyDirectorysByOrgIdAndYearDate(
			Long orgId, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("yearDate", year);
		return getSqlMapClientTemplate()
				.queryForList(
						"dailyDirectory.findLeafDailyDirectorysByOrgIdAndYearDate",
						map);
	}

	@Override
	public List<DailyDirectory> searchDirectorys(String searchText,
			Long dailyYearId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		map.put("dailyYearId", dailyYearId);
		return getSqlMapClientTemplate().queryForList(
				"dailyDirectory.searchDirectorys", map);
	}

}
