package com.tianque.plugin.orgchange.common;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.StringUtil;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgMapping;

/**
 * 组织机构变更功能工具类，里面包含公共的方法和常量
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月30日
 */
public class OrgChangeUtils {
	// 迁移合并进度状态
	public final static int ORGCHANGE_DONE = 100;// 执行完成
	public final static int ORGCHANGE_EXCEPTION = 1;// 执行出现异常
	// 迁移合并记录是否已经执行
	public final static Integer IS_DEAL = 0;// 未执行
	// 日志类型
	public final static int LOG_INIT = 0;// 初始化
	public final static int LOG_SUCCESS = 1;// 成功
	public final static int LOG_ERROR = 2;// 失败

	// 组织机构变更类型
	public final static int TRANSFER = 1;// 迁移
	public final static int MERGE = 2;// 合并

	// 组织机构变更脚本执行方式
	public final static int EXECUTE_DEFAULT = 0;// 默认
	public final static int EXECUTE_CUSTOM = 1;// 自定义

	public static final String ORGCHANGE_MEGER = "meger";// 组织机构合并
	public static final String ORGCHANGE_TRANSFER = "transfer";// 组织机构迁移

	// 脚本替换宏
	public final static String TABLENAME = "#TABLENAME#";// 业务表名
	public final static String ORGIDCOLUMN = "#ORGIDCOLUMN#";// 业务表组织机构编号字段名
	public final static String NEWORGID = "#NEWORGID#";// 变更后组织机构ID
	public final static String ORGCODECOLUMN = "#ORGCODECOLUMN#";// 业务表组织机构内部编码字段名
	public final static String NEWORGCODE = "#NEWORGCODE#";// 变更后组织机构内部编码值
	public final static String OLDORGID = "#OLDORGID#";// 变更前组织机构ID
	public final static String OLDORGCODE = "#OLDORGCODE#";// 变更前组织机构内部编码值
	public final static String NEWPARENTID = "#NEWPARENTID#";// 变更之后组织机构父节点ID
	/** 变更前对应县的组织机构ID */
	public final static String OLDDISTRICTORGID = "#OLDDISTRICTORGID#";
	/** 变更后对应县的组织机构id */
	public final static String NEWDISTRICTORGID = "#NEWDISTRICTORGID#";

	/** 缓存所用宏 */
	public final static String ORGCHANGE_PROGRESS = "orgchangeProgress";

	/**
	 * 字符窜中##之间的转为大写， 例： <br>
	 * select count(*) from #tablename# where orgcode='#' and #orgidcolumn# =
	 * #oldorgid# <br>
	 * 改为<br>
	 * select count(*) from TESTTABLE where orgcode='#' and ORGID = 127
	 * 
	 * @param orgMapping
	 * @param script
	 * @return
	 */
	public static String replaceScript(OrgMapping orgMapping, String script) {
		if (StringUtil.isStringAvaliable(script)) {
			ModuleTable moduleTable = orgMapping.getModuleTable();

			Map<String, String> map = new HashMap<String, String>();
			map.put(TABLENAME, moduleTable.getTableName());
			map.put(ORGIDCOLUMN, moduleTable.getOrgIdColumn());
			map.put(OLDORGID, orgMapping.getOldOrgId().toString());
			map.put(NEWORGID, orgMapping.getNewOrgId().toString());
			map.put(NEWORGCODE, "'" + orgMapping.getNewOrgCode() + "'");
			map.put(OLDORGCODE, "'" + orgMapping.getOldOrgCode() + "'");
			map.put(ORGCODECOLUMN, moduleTable.getOrgCodeColumn());
			if (orgMapping.getNewParentOrgId() != null) {
				map.put(NEWPARENTID, orgMapping.getNewParentOrgId().toString());
			}
			if (orgMapping.getOldDistrictOrgId() != null
					&& orgMapping.getNewDistrictOrgId() != null) {
				map.put(OLDDISTRICTORGID, orgMapping.getOldDistrictOrgId()
						.toString());
				map.put(NEWDISTRICTORGID, orgMapping.getNewDistrictOrgId()
						.toString());
			}
			return replaceMapValue(map, script);
		} else {
			return "";
		}
	}

	public static String replaceMapValue(Map<String, String> map, String script) {
		int beginIndex = 0, endIndex = 0;
		String oldStr = "", newStr = "";
		// 字符窜中##之间的转为大写再替换相应的值，例：orgId = #oldOrgId#转为orgId = 1
		while (true) {
			beginIndex = script.indexOf("#", beginIndex);
			endIndex = script.indexOf("#", beginIndex + 1);
			if (beginIndex > -1 && endIndex > -1) {
				oldStr = script.substring(beginIndex, endIndex + 1);
				newStr = oldStr.toUpperCase();
				// 防止查询条件里包含“#”字符
				if (map.containsKey(newStr)) {
					script = script.replace(oldStr, map.get(newStr));
				} else {
					beginIndex = beginIndex + 1;
				}
			} else {
				break;
			}
		}
		return script;
	}

	public static String getFirstWord(String str) {
		if (StringUtil.isStringAvaliable(str)) {
			String[] temp = str.trim().split(" ");
			if (temp.length > 0) {
				return temp[0].toUpperCase();
			}
		}
		return "";
	}

	public static String getWhereIgnoreCase(String str) {
		if (StringUtil.isStringAvaliable(str)) {
			String[] temp = str.trim().split(" ");
			for (String string : temp) {
				if (string.equalsIgnoreCase("where")) {
					str = str.replace(string, string.toUpperCase());
				} else if (string.equalsIgnoreCase("delete")) {
					str = str.replace(string, string.toUpperCase());
				} else if (string.equalsIgnoreCase("update")) {
					str = str.replace(string, string.toUpperCase());
				} else if (string.equalsIgnoreCase("from")) {
					str = str.replace(string, string.toUpperCase());
				}
			}
		}
		return str;
	}

	/***
	 * 将String数组转换成字符串
	 */
	public static String getStringByData(String[] ids) {
		String str = "";
		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != ids.length - 1) {
					str += ids[i] + ",";
				} else {
					str += ids[i];
				}
			}
		}
		return str;
	}

}
