package com.tianque.openLayersMap.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.core.vo.PageInfo;

abstract public class BaseDao<T> extends SqlMapClientDaoSupport {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String PAGE_FROM = "pageFrom";
	private static final String PAGE_TO = "pageTo";
	@Resource(name="sqlMapClient")
	public void setSqlMapClientBase(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	protected void batchInsertDate(List<?> datas, String sqlAlias) {
		List<Object> batchList = new ArrayList<Object>();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchInsertFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchInsertFor500(batchList, sqlAlias);
		}

	}
	@SuppressWarnings("unchecked")
	protected PageInfo<T> getPageInfoByParamMap(PageInfo<T> pageInfo, String countListStatementName, String getListStatementName, int pageNum, int pageSize, Map<String, Object> paramObject) {
		Long total = (Long) getSqlMapClientTemplate().queryForObject(countListStatementName, paramObject);
		pageNum = dealOutofMaxPageNum(total.intValue(), pageSize, pageNum);
		List<T> result = null;
		if(total != null && total.longValue() > 0) {
			paramObject.put(PAGE_FROM, (pageNum -1) * pageSize);
			paramObject.put(PAGE_TO, pageNum * pageSize);
			result = getSqlMapClientTemplate().queryForList(getListStatementName, paramObject);
		}
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(total);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}
	
	private int dealOutofMaxPageNum(int total, int pageSize, int pageNum) {
		int maxPage = 0;
		if ((total % pageSize) == 0) {
			maxPage = total / pageSize;
		} else {
			maxPage = total / pageSize + 1;
		}
		return pageNum > maxPage ? maxPage : pageNum;
	}
	
	private void batchInsertFor500(final List<Object> datas,
			final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.insert(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}

		});
	}

}
