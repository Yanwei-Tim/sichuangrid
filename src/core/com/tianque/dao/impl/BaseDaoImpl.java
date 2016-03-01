package com.tianque.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.cache.PageInfoCacheHelper;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.PageInfoUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.core.vo.SearchVo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.dao.BaseDao;
import com.tianque.domain.Session;

@Repository("abstractBaseDao")
public class BaseDaoImpl<T extends BaseDomain> extends AbstractBaseDao
		implements BaseDao<T, SearchVo> {

	@Autowired
	protected PageInfoCacheHelper pageInfoCacheHelper;
	private Class<T> entityClass;

	/**
	 * 自定义名称空间
	 */
	protected String prefix;

	public List<Long> batchInsertDatas(List<T> datas, String statement) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setCreateDate(session.getAccessTime());
			datas.get(i).setCreateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		return batchInsertData(datas, statement);
	}

	public void batchUpdateDatas(List<T> datas, Object queryCondition,
			String statement) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setUpdateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		batchUpdateData(datas, statement);
	}

	public void batchDeleteData(List datas, String sqlAlias) {
		super.batchDeleteData(datas, sqlAlias);
	}

	public BaseDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Override
	public T get(Long id) {
		return get(id, getSelectByIdSqlId(entityClass.getSimpleName()));
	}

	@Override
	public T add(T entity) {
		return add(entity, getInsertSqlId(getClassNameByEntity(entity)));
	}

	@Override
	public T update(T entity) {
		return update(entity, getUpdateSqlId(getClassNameByEntity(entity)));
	}

	@Override
	public boolean delete(Long id) {
		return delete(id, getDeleteSqlId(entityClass.getSimpleName()));
	}

	@Override
	public boolean delete(List<Long> ids, String statement) {
		return getSqlMapClientTemplate().delete(genarateStatement(statement),
				ids) > 0;
	}

	@Override
	public PageInfo<T> findPagerBySearchVo(Object object, Pager pager,
			String statement) {
		PageInfo<T> pageInfo = PageInfoUtil.emptyPage(pager.getPageSize());
		if (object instanceof SearchVo) {
			pageInfo = findPageBySearchVo((SearchVo) object, pager, statement);
		}

		if (object instanceof Map) {
			pageInfo = findPageByMap((Map) object, pager, statement);
		}
		return pageInfo;
	}

	private PageInfo<T> findPageBySearchVo(SearchVo searchVo, Pager pager,
			String statement) {
		searchVo.setSortField(pager.getSortField());
		searchVo.setOrder(pager.getOrder());
		Integer countNum = countForObject(searchVo, "count" + statement);

		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getStatement(entityClass.getSimpleName()).append("find")
						.append(statement).toString(), searchVo,
				(pager.getPageNum() - 1) * pager.getPageSize(),
				pager.getPageSize());

		return new PageInfo<T>(pager.getPageNum(), pager.getPageSize(),
				countNum, resultList);
	}

	protected PageInfo<T> findPageByMap(Map map, Pager pager, String statement) {
		map.put("sortField", pager.getSortField());
		map.put("order", pager.getOrder());
		Integer countNum = countForObject(map, "count" + statement);

		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getStatement(entityClass.getSimpleName()).append("find")
						.append(statement).toString(), map,
				(pager.getPageNum() - 1) * pager.getPageSize(),
				pager.getPageSize());

		return new PageInfo<T>(pager.getPageNum(), pager.getPageSize(),
				countNum, resultList);
	}

	@Override
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Pager pager) {
		return findPagerBySearchVo(searchVo, pager, entityClass.getSimpleName()
				+ "sBySearchVo");
	}

	private StringBuilder getStatement(String className) {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append(getSqlNamespaceByClassName(className))
				.append(".");
	}

	/**
	 * 通过class获取slqMap的命名空间
	 * 
	 * @param className
	 * 
	 * @return 例子：className : Druggy =>druggy,如果prefix合法,则使用prefix作为命名空间
	 * 
	 */
	private String getSqlNamespaceByClassName(String className) {
		if (StringUtil.isStringAvaliable(prefix)) {
			return prefix;
		}
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	/**
	 * 获取通过Id获取对象的sql语句的Id
	 * 
	 * @param className
	 * @return 例子druggy.getDruggyById
	 */
	private String getSelectByIdSqlId(String className) {
		return "get" + className + "ById";

	}

	/**
	 * 获取插入的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.addDruggy
	 */
	private String getInsertSqlId(String className) {
		return "add" + className;
	}

	/**
	 * 获取删除语句的sqlId
	 * 
	 * @param className
	 * @return 例子：deleteMeetingWorkingRecordById
	 */
	private String getDeleteSqlId(String className) {
		return "delete" + className + "ById";
	}

	/**
	 * 获取修改的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.updateDruggy
	 */
	private String getUpdateSqlId(String className) {
		return "update" + className;
	}

	private String getClassNameByEntity(T entity) {
		return entity.getClass().getSimpleName();
	}

	// 接下来的方法是通用方法，也就是说后缀可以由开发者自己传递进来
	private String genarateStatement(String statement) {
		return getStatement(entityClass.getSimpleName()).append(statement)
				.toString();
	}

	@Override
	public boolean isUpdateSuccess(Object object, String statement) {
		return getSqlMapClientTemplate().update(genarateStatement(statement),
				object) > 0;
	}

	@Override
	public T update(T entity, String statement) {
		getSqlMapClientTemplate().update(genarateStatement(statement), entity);
		return get(entity.getId());
	}

	@Override
	public T get(Object object, String statement) {
		return (T) getSqlMapClientTemplate().queryForObject(
				genarateStatement(statement), object);
	}

	@Override
	public boolean delete(Object object, String statement) {
		return getSqlMapClientTemplate().delete(genarateStatement(statement),
				object) > 0;
	}

	@Override
	public Integer countForObject(Object object, String statement) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				genarateStatement(statement), object);
	}

	@Override
	public List findForList(Object object, String statement) {
		return getSqlMapClientTemplate().queryForList(
				genarateStatement(statement), object);
	}

	@Override
	public List findForList(Object object, Pager pager, String statement) {
		SearchVo searchVo = (SearchVo) object;
		searchVo.setSortField(pager.getSortField());
		searchVo.setOrder(pager.getOrder());
		return getSqlMapClientTemplate().queryForList(
				genarateStatement(statement), object,
				(pager.getPageNum() - 1) * pager.getPageSize(),
				pager.getPageSize());
	}

	@Override
	public T add(T entity, String statement) {
		return get((Long) getSqlMapClientTemplate().insert(
				genarateStatement(statement), entity));
	}

	@Override
	public void add(Object object, String statement) {
		getSqlMapClientTemplate().insert(genarateStatement(statement), object);
	}

	@Override
	public boolean isAddSuccess(Object object, String statement) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				genarateStatement(statement), object);
		return id != null && id > 0;
	}

	@Override
	public PageInfo<T> findPagerUsingCacheBySearchVo(Long orgId, Object object,
			Pager pager, String statement, String moduleName) {
		object = convert(object, pager);
		List<T> result = findForList(object, pager, "find" + statement);
		Integer countNum = 0;
		if (CollectionUtils.isEmpty(result)) {
			new PageInfo<T>(pager.getPageNum(), pager.getPageSize(), countNum,
					result);
		} else {
			countNum = pageInfoCacheHelper.getCounter(moduleName, orgId);
			if (countNum <= 0
					|| !pageInfoCacheHelper.isExpired(moduleName, orgId)) {
				pageInfoCacheHelper.storeCounterTimeoutFlag(moduleName, orgId);
				countNum = countForObject(object, "count" + statement);
				pageInfoCacheHelper.storeCounter(moduleName, orgId, countNum);
			}
		}
		return new PageInfo<T>(pager.getPageNum(), pager.getPageSize(),
				countNum, result);

	}

	private Object convert(Object object, Pager pager) {
		if (object instanceof Map) {
			Map map = (Map) object;
			map.put("sortField", pager.getSortField());
			map.put("order", pager.getOrder());
			return map;
		} else if (object instanceof SearchVo) {
			SearchVo searchVo = (SearchVo) object;
			searchVo.setSortField(pager.getSortField());
			searchVo.setOrder(pager.getOrder());
			return searchVo;
		} else {
			return null;
		}
	}

	protected PageInfo<T> findPageUsingCacheByMap(Map map, Pager pager,
			String statement) {
		map.put("sortField", pager.getSortField());
		map.put("order", pager.getOrder());
		Integer countNum = countForObject(map, "count" + statement);

		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getStatement(entityClass.getSimpleName()).append("find")
						.append(statement).toString(), map,
				(pager.getPageNum() - 1) * pager.getPageSize(),
				pager.getPageSize());

		return new PageInfo<T>(pager.getPageNum(), pager.getPageSize(),
				countNum, resultList);
	}
}
