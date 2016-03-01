package com.tianque.baseInfo.excelimportlog.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLog;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogVO;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("excelImportLogDao")
public class ExcelImportLogDaoImpl extends AbstractBaseDao implements
		ExcelImportLogDao {
	protected static Logger logger = LoggerFactory
			.getLogger(ExcelImportLogDaoImpl.class);

	@Override
	public ExcelImportLog addImportLog(ExcelImportLog excelImportLog) {
		Long id = (Long) getSqlMapClientTemplate().insert("excelImportLog.addImportLog",
				excelImportLog);
		return getExcelimportlogById(id);
	}

	@Override
	public ExcelImportLog updateImportLogCurrentNum(String uuid,
			Number currentDealNum, Number errorCountNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("currentDealNum", currentDealNum);
		map.put("errorCountNum", errorCountNum);
		getSqlMapClientTemplate().update(
				"excelImportLog.updateImportLogCurrentNum", map);
		return null;
	}

	@Override
	public ExcelImportLog updateImportStatus(String uuid, Number status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("status", status);
		map.put("updateDate", new Date());
		getSqlMapClientTemplate().update("excelImportLog.updateImportStatus",
				map);
		return null;
	}

	@Override
	public PageInfo<ExcelImportLog> searchImportLog(Integer pageNum,
			Integer pageSize, String sortField, String order,
			String createUser, Integer status) {

		PageInfo<ExcelImportLog> pageInfo = new PageInfo<ExcelImportLog>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("createUser", createUser);
		map.put("status", status);
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject("excelImportLog.countSearchImportLog", map);
			int pageCount = 0;
			if ((countNum % pageSize) == 0) {
				pageCount = countNum / pageSize;
			} else {
				pageCount = countNum / pageSize + 1;
			}
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			if (countNum > 0) {
				// List<ExcelImportLog> list = getSqlMapClientTemplate()
				// .queryForList("excelImportLog.searchImportLog", map,
				// (pageNum - 1) * pageSize, pageSize);
				List<ExcelImportLog> list = getSqlMapClientTemplate()
						.queryForList("excelImportLog.searchImportLogHasName",
								map, (pageNum - 1) * pageSize, pageSize);
				pageInfo.setResult(list);
			} else {
				pageInfo.setResult(new ArrayList<ExcelImportLog>());
			}
			pageInfo.setTotalRowSize(countNum);
			pageInfo.setCurrentPage(pageNum);
			pageInfo.setPerPageSize(pageSize);
			return pageInfo;
		
	}

	@Override
	public PageInfo<ExcelImportLog> selectExcelimportlog(
			ExcelImportLogVO excelImportLogVO, Integer pageNum,
			Integer pageSize, String sortField, String order) {
			PageInfo<ExcelImportLog> pageInfo = new PageInfo<ExcelImportLog>();
			Integer countNum = 0;
			countNum = (Integer) getSqlMapClientTemplate().queryForObject(
					"excelImportLog.countSelectExcelimportlog",
					excelImportLogVO);
			int pageCount = 0;
			if ((countNum % pageSize) == 0) {
				pageCount = countNum / pageSize;
			} else {
				pageCount = countNum / pageSize + 1;
			}
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			if (countNum > 0) {
				excelImportLogVO.setSortField(sortField);
				excelImportLogVO.setOrder(order);
				List<ExcelImportLog> list = getSqlMapClientTemplate()
						.queryForList("excelImportLog.selectExcelimportlog",
								excelImportLogVO, (pageNum - 1) * pageSize,
								pageSize);
				pageInfo.setResult(list);
			} else {
				pageInfo.setResult(new ArrayList<ExcelImportLog>());
			}
			pageInfo.setTotalRowSize(countNum);
			pageInfo.setCurrentPage(pageNum);
			pageInfo.setPerPageSize(pageSize);
			return pageInfo;
	}

	@Override
	public void excelImportLogClean(Date cleanTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cleanTime", cleanTime);
		getSqlMapClientTemplate().delete("excelImportLog.excelImportLogClean",
				map);

	}

	@Override
	public ExcelImportLog getExcelimportlogById(Long id) {
		return (ExcelImportLog) getSqlMapClientTemplate().queryForObject("excelImportLog.getExcelimportlogById", id);
	}
}
