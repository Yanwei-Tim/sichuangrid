package com.tianque.init;

import java.io.File;
import java.io.FileInputStream;

import com.tianque.core.datatransfer.ExcelReader;
import com.tianque.core.datatransfer.data.ExcelData;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.vo.IdCardNoNativeAddress;
import com.tianque.service.IdCardNoNativeAddressService;

public class IdCardNoFromExcelInit implements Initialization {
	IdCardNoNativeAddressService idCardNoNativeAddressService;

	public IdCardNoFromExcelInit(IdCardNoNativeAddressService _idCardNoNativeAddressService) {
		idCardNoNativeAddressService = _idCardNoNativeAddressService;
	}

	@Override
	public void init() throws Exception {
		executeHashMapSheet(getExcelData(FileUtil.getWebRoot()
				+ "/WEB-INF/classes/idCardNoNativeAddress.xls"));
	}

	public void executeRow(String[] row) throws Exception {
		IdCardNoNativeAddress idCardNoNativeAddress = new IdCardNoNativeAddress(row[1], row[2]);
		idCardNoNativeAddressService.addIdCardNoNativeAddress(idCardNoNativeAddress);
	}

	public ExcelData getExcelData(String filePath) {
		ExcelData excelData = null;
		try {
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			excelData = ExcelReader.readFile(fileInputStream);
			return excelData;
		} catch (Exception e) {
			System.err.println("读取excel文件时出现问题!");
		}
		return excelData;
	}

	public void executeHashMapSheet(ExcelData excelData) throws Exception {
		String[][] table = excelData.getSheetDatas(0);
		if (table.length > 1) {
			executeTable(table);
		}
	}

	public void executeTable(String[][] table) throws Exception {
		for (int i = 0; i < table.length; i++) {
			String[] row = table[i];
			if (i != 0) {
				executeRow(row);
			}
		}
	}
}
