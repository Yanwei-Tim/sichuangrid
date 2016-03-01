package com.tianque.plugin.init;

import java.io.InputStream;
import java.util.List;

import com.tianque.init.ContextType;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.init.xml.XmlUtil;
import com.tianque.util.SqlScriptExcuteUtil;

public class DatabasePluginInitialization extends DatabaseInitialization {

	private ContextType contextType;

	public DatabasePluginInitialization(ContextType contextType) {
		super(contextType);
		this.contextType = contextType;
	}

	@Override
	public void init() throws Exception {
		initPluginSql();
	}

	public void initPluginSql() throws Exception {
		for (InputStream inputStream : XmlUtil.getSqlInputStreams(contextType)) {
			List<String> sqls = loadSqlFile(inputStream);
			SqlScriptExcuteUtil.executeBatchSql(sqls, getDataSource());
		}
	}

}
