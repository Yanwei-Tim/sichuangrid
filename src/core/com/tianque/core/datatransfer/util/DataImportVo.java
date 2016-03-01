package com.tianque.core.datatransfer.util;

import java.util.Hashtable;

public class DataImportVo {

	private final Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	private static DataImportVo dataImportVo;

	private DataImportVo() {

	}

	private synchronized static DataImportVo get() {
		if (dataImportVo == null) {
			dataImportVo = new DataImportVo();
		}
		return dataImportVo;
	}

	public static Hashtable<String, Integer> getTable() {
		return get().table;
	}
}
