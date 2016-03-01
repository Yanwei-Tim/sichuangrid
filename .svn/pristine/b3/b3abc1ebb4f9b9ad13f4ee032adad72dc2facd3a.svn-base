package com.tianque.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.core.base.DruidSpringMonitor;

abstract public class AbstractBaseDao extends SqlMapClientDaoSupport implements
		DruidSpringMonitor {
	public final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "sqlMapClient")
	public void setSqlMapClientBase(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	protected List<Long> batchInsertData(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		List<Long> ids = new ArrayList<Long>();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				ids.addAll(batchInsertFor500(batchList, sqlAlias));
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			ids.addAll(batchInsertFor500(batchList, sqlAlias));
		}
		return ids;

	}

	private List<Long> batchInsertFor500(final List datas, final String sqlAlias) {
		return (List) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					@Override
					public Object doInSqlMapClient(SqlMapExecutor excutor)
							throws SQLException {
						List<Long> ids = new ArrayList<Long>();
						excutor.startBatch();
						for (Object data : datas) {
							ids.add((Long) excutor.insert(sqlAlias, data));
						}
						excutor.executeBatch();
						return ids;
					}

				});
	}

	protected void batchUpdateData(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchUpdateFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchUpdateFor500(batchList, sqlAlias);
		}

	}

	private void batchUpdateFor500(final List datas, final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.update(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}

		});
	}

	protected void batchDeleteData(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchDeleteFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchDeleteFor500(batchList, sqlAlias);
		}

	}

	private void batchDeleteFor500(final List datas, final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.delete(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}

		});
	}
}
