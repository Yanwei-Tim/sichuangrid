package com.tianque.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileContentType {

	private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	private FileContentType() {
	}

	static {
		getAllFileType();
	}

	private static void getAllFileType() {
		FILE_TYPE_MAP.put("jpg", "FFD8FF");
		FILE_TYPE_MAP.put("gif", "47494638");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("png", "89504E47");
		FILE_TYPE_MAP.put("pdf", "255044462D312E");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("bmp", "424D");
		FILE_TYPE_MAP.put("xls", "D0CF11E0");
		FILE_TYPE_MAP.put("doc", "D0CF11E0");
		FILE_TYPE_MAP.put("ppt", "D0CF11E0");
		FILE_TYPE_MAP.put("cebx", "40584441");
		FILE_TYPE_MAP.put("mp3", "494433030000000");
		FILE_TYPE_MAP.put("wma", "3026B2758E66CF11A6D");
		FILE_TYPE_MAP.put("wav", "52494646");
		FILE_TYPE_MAP.put("rm", "2E524D46");
		FILE_TYPE_MAP.put("amr", "2321414D52");
		FILE_TYPE_MAP.put("3gpp", "0000001C66747970336");
		FILE_TYPE_MAP.put("voice", "FFE348E4");
	}

	public static boolean upLoadFileVerification(File upLoadFile)
			throws Exception, FileNotFoundException, IOException {
		if (!upLoadFile.exists()) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^.*?\\.(jpg|gif|zip|png|jpeg|pdf|pptx|rtf|tar|vsd|xlsx|tif|rar|bmp|xls|docx|doc|txt|ceb|mp3|wma|wav|rm|amr|3gpp)$");
		Matcher matcher = pattern.matcher(upLoadFile.getName().toLowerCase());
		if (!matcher.matches()) {
			return false;
		}
		if (upLoadFile.getName().toLowerCase().endsWith(".txt")) {
			return true;
		}
		if (!isPermitFileTpye(upLoadFile)) {
			return false;
		}
		return true;
	}

	private final static boolean isPermitFileTpye(File file) throws Exception,
			FileNotFoundException, IOException {
		boolean flag = false;
		byte[] fileHeadInfo = new byte[50];
		InputStream inputStream = new FileInputStream(file);
		inputStream.read(fileHeadInfo);
		flag = isPermitFileTypeByHeadInfo(fileHeadInfo);
		inputStream.close();
		return flag;
	}

	private final static boolean isPermitFileTypeByHeadInfo(byte[] fileHeadInfo)
			throws Exception {
		String fileTypeHex = String.valueOf(getFileHexHeadInfo(fileHeadInfo));
		Iterator<Entry<String, String>> entryIterator = FILE_TYPE_MAP
				.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Entry<String, String> entry = entryIterator.next();
			String fileTypeHexValue = entry.getValue();
			if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return true;
			}
		}
		return false;
	}

	private final static String getFileHexHeadInfo(byte[] fileHeadInfo)
			throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		if (fileHeadInfo == null || fileHeadInfo.length <= 0) {
			return null;
		}
		for (int i = 0; i < fileHeadInfo.length; i++) {
			int value = fileHeadInfo[i] & 0xFF;
			String hexValue = Integer.toHexString(value);
			if (hexValue.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hexValue);
		}
		return stringBuilder.toString();
	}

}
