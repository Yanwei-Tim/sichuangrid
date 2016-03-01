package com.tianque.baseInfo.excelimportlog.service;

import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLog;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogVO;
import com.tianque.core.vo.PageInfo;

public interface ExcelImportLogService {

	public PageInfo<ExcelImportLog> searchImportLog(Integer pageNum,
			Integer pageSize, String sidx, String sord, Integer status);

	public ExcelImportLog addImportLog(String uuid, String fileName,
			String fileType, Integer importDataNum, String importModuleName);

	public ExcelImportLog updateImportLogCurrentNum(String uuid,
			Integer currentDealNum, Integer errorCountNum);

	public ExcelImportLog updateImportStatus(String uuid, Number status);

	public PageInfo<ExcelImportLog> selectExcelimportlog(
			ExcelImportLogVO excelImportLogVO, Integer pageNum,
			Integer pageSize, String sortField, String order);

	/**
	 * 删除过期的导入日志从表中取过期的时间是多长
	 */
	public void excelImportLogClean();

	public ExcelImportLog getExcelimportlogById(Long id);
}
