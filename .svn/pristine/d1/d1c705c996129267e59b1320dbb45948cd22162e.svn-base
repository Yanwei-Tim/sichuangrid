package com.tianque.init;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.util.SqlScriptExcuteUtil;

public class Migration {
	protected final static Logger logger = LoggerFactory.getLogger(Migration.class);
	private static String currentVersion = GridProperties.CURRENT_VERSION;
	private static String contextType = null;
	private static String fromVersion = null;
	private static String versionStack = new StringBuffer(
			GlobalValue.getProjectPath(Migration.class)).append(File.separator).append("..")
			.append(File.separator).append("..").append(File.separator).append("..")
			.append(File.separator).append("database").append(File.separator)
			.append("versionTrack").toString();

	static class SqlFilter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			if (pathname.getName().endsWith(".sql"))
				return true;
			else
				return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			return;
		}
		try {
			parseArgs(args);
			List<String> versions = getVersions(fromVersion, currentVersion);
			DataSource dataSource = getDataSource();
			for (String version : versions) {
				if (isUpdateDataBase(version)) {
					File file = new File(versionStack + File.separator + version);
					if (file.exists() && file.isDirectory()) {
						File[] sqlFiles = file.listFiles(new Migration.SqlFilter());
						Arrays.sort(sqlFiles, new Comparator<File>() {
							@Override
							public int compare(File file1, File file2) {
								if (file1.getName().compareTo(file2.getName()) > 0) {
									return 1;
								}
								return 0;
							}
						});
						for (int i = 0; i < sqlFiles.length; i++) {
							excuteSqlFile(dataSource,
									version + File.separator + sqlFiles[i].getName());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.warn("migration出错:" + e.getMessage());
		}

	}

	private static void parseArgs(String[] args) {
		contextType = args[0];
		fromVersion = args[1];
	}

	private static boolean isUpdateDataBase(String version) {
		String[] versionSplit = version.split("-");
		if (!version.endsWith("final") && versionSplit[0].endsWith("d")) {
			return true;
		}
		return false;
	}

	private static List getVersions(String fromVersion, String toVersion) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder = documentBuilderFactory.newDocumentBuilder();
		InputStream is = new FileInputStream(versionStack + File.separator + "versionTrack.xml");
		Document doc = dombuilder.parse(is);
		Element root = doc.getDocumentElement();
		NodeList versionList = root.getChildNodes();
		List list = new ArrayList();
		boolean isAddToVersionList = false;
		for (int i = 0; i < versionList.getLength(); i++) {
			Node versionNode = versionList.item(i);
			if (versionNode.hasAttributes()) {
				String versionValue = versionNode.getAttributes().item(0).getNodeValue();
				if (fromVersion.equals(versionValue)) {
					isAddToVersionList = true;
					continue;
				}
				if (isAddToVersionList) {
					list.add(versionValue);
				}
				if (toVersion.equals(versionValue)) {
					isAddToVersionList = false;
				}
			}
		}
		return list;
	}

	private static DataSource getDataSource() {
		DataSource dataSource = null;
		if ("development".equals(contextType)) {
			logger.error("migration执行开发环境");
			dataSource = SqlScriptExcuteUtil.getDataSource(ContextType.development);
		} else if ("test".equals(contextType)) {
			logger.error("migration执行测试环境");
			dataSource = SqlScriptExcuteUtil.getDataSource(ContextType.test);
		} else if ("production".equals(contextType)) {
			logger.error("migration执行生产环境");
			dataSource = SqlScriptExcuteUtil.getDataSource(ContextType.production);
		}
		return dataSource;
	}

	private static void excuteSqlFile(DataSource dataSource, String fileName) throws Exception,
			SQLException {
		List<String> sqls = SqlScriptExcuteUtil.loadSqlFile(versionStack + File.separator
				+ fileName);
		logger.error("升级文件：" + versionStack + File.separator + fileName);
		SqlScriptExcuteUtil.executeBatchSql(sqls, dataSource);
	}
}
