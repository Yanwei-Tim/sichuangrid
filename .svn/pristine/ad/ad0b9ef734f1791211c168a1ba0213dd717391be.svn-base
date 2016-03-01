package com.tianque.baseInfo.excelimportlog.dao;

import java.util.Date;

import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLog;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogVO;
import com.tianque.core.vo.PageInfo;

public interface ExcelImportLogDao {

	public ExcelImportLog addImportLog(ExcelImportLog excelImportLog);

	public ExcelImportLog updateImportLogCurrentNum(String uuid,
			Number currentDealNum, Number errorCountNum);

	public ExcelImportLog updateImportStatus(String uuid, Number status);

	public PageInfo<ExcelImportLog> searchImportLog(Integer pageNum,
			Integer pageSize, String sortField, String order,
			String createUser, Integer status);

	public PageInfo<ExcelImportLog> selectExcelimportlog(
			ExcelImportLogVO excelImportLogVO, Integer pageNum,
			Integer pageSize, String sortField, String order);

	/**
	 * 删除过期的导入日志从表中取过期的时间是多长
	 * 
	 * @param cleanTime
	 */
	public void excelImportLogClean(Date cleanTime);

	public ExcelImportLog getExcelimportlogById(Long id);
}
