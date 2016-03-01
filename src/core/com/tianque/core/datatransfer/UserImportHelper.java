package com.tianque.core.datatransfer;

public class UserImportHelper {
	private final static String USER_IMPORT_TEMPLETE = "userData";
	public final static int SELF = 1;// 导入本层级行政部门帐号
	public final static int OTHER = 2;// 导入本层级职能部门或者下级行政部门帐号
	public final static String OTHER_DATA = "账号";// 该行以下的数据为本层级职能部门或者下级行政部门帐号
	
	public final static String DISCRIPT_NAME = "区县以下账号";
	public final static String DEFAULT_PASSWORD= "11111111";

	public static boolean isUserImport(String dataType) {
		return USER_IMPORT_TEMPLETE.equalsIgnoreCase(dataType);
	}
}
