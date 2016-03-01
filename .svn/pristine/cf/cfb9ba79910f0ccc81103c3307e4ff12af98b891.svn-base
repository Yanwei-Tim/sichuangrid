package com.tianque.core.base;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.cache.PageInfoCacheHelper;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Session;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("baseDao")
public class BaseDaoImpl<T extends BaseDomain, SearchVo extends BaseDomain>
		extends AbstractBaseDao implements BaseDao<T, SearchVo> {

	protected static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	@Autowired
	protected PageInfoCacheHelper pageInfoCacheHelper;
	protected Class<T> entityClass;

	public List<Long> batchInsertDate(List<T> datas) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setCreateDate(session.getAccessTime());
			datas.get(i).setCreateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		return batchInsertDate(datas,
				getInsertSqlId(entityClass.getSimpleName()));
	}

	public void batchUpdateDate(List<T> datas) {
		Session session = ThreadVariable.getSession();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setUpdateUser(session.getUserName());
			datas.get(i).setUpdateDate(session.getAccessTime());
		}
		batchUpdateDate(datas, getUpdateSqlId(entityClass.getSimpleName()));
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
		String className = entityClass.getSimpleName();
		return (T) getSqlMapClientTemplate().queryForObject(
				getSelectByIdSqlId(className), id);
	}
	
	@Override
	public T getShard(Long id) {
		String className = entityClass.getSimpleName();
		Map<String, Object> map = new HashMap<String, Object>();

		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		return (T) getSqlMapClientTemplate().queryForObject(
				getSelectByIdSqlId(className), map);
	}

	@Override
	public T add(T entity) {
		checkEntityWhenAdd(entity);
		String insertSqlMap = getInsertSqlId(getClassNameByEntity(entity));
		return get((Long) getSqlMapClientTemplate()
				.insert(insertSqlMap, entity));
	}
	
	@Override
	public T addShard(T entity) {
		checkEntityWhenAdd(entity);
		String insertSqlMap = getInsertSqlId(getClassNameByEntity(entity));
		getSqlMapClientTemplate().insert(insertSqlMap, entity);
		return getShard(entity.getId() );
	}

	@Override
	public T update(T entity) {
		checkEntityWhenUpdate(entity);
		getSqlMapClientTemplate().update(
				getUpdateSqlId(getClassNameByEntity(entity)), entity);
		return get(entity.getId());
	}

	@Override
	public void delete(Long id) {

		String className = entityClass.getSimpleName();
		getSqlMapClientTemplate().delete(getDeleteSqlId(className), id);
	}
	
	@Override
	public void deleteShard(Long id) {

		String className = entityClass.getSimpleName();
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().delete(getDeleteSqlId(className), map);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	@Override
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		String className = entityClass.getSimpleName();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				getCountResulSizeBySearchVoSqlId(className), searchVo);

		List<T> resultList = getSqlMapClientTemplate().queryForList(
				getFindPageInfoBySearchVoSqlId(className), searchVo,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<T>(pageNum, pageSize, countNum, resultList);
	}

	private String getFindPageInfoBySearchVoSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "find" + className
				+ "s" + "BySearchVo";
	}

	private String getCountResulSizeBySearchVoSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "count"
				+ className + "s" + "BySearchVo";
	}

	/**
	 * 通过class获取slqMap的命名空间
	 * 
	 * @param className
	 * @return 例子：className : Druggy =>druggy
	 */
	protected String getSqlNamespaceByClassName(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	/**
	 * 获取通过Id获取对象的sql语句的Id
	 * 
	 * @param className
	 * @return 例子druggy.getDruggyById
	 */
	protected String getSelectByIdSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "get" + className
				+ "ById";

	}

	/**
	 * 获取插入的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.addDruggy
	 */
	protected String getInsertSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "add" + className;
	}
	
	protected String getSequence(T entity) {
		return getSqlNamespaceByClassName(getClassNameByEntity(entity)) + "." + "getId";
	}

	/**
	 * 获取删除语句的sqlId
	 * 
	 * @param className
	 * @return 例子：deleteMeetingWorkingRecordById
	 */
	protected String getDeleteSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "delete"
				+ className + "ById";
	}

	/**
	 * 获取修改的sql的Id
	 * 
	 * @param className
	 * @return 例子：druggy.updateDruggy
	 */
	protected String getUpdateSqlId(String className) {
		return getSqlNamespaceByClassName(className) + "." + "update"
				+ className;
	}

	private String getClassNameByEntity(T entity) {
		return entity.getClass().getSimpleName();
	}

	protected void checkEntityWhenUpdate(T entity) {

	}

	protected void checkEntityWhenAdd(T entity) {
		try {
			Method method = entity.getClass().getMethod("validate");
			ValidateResult vresult = (ValidateResult) method.invoke(entity);
			if (null != vresult && vresult.hasError()) {
				throw new OperationFailedException(vresult.getErrorMessages());
			}
		} catch (Exception e) {
			throw new OperationFailedException(e.getMessage());
		}
	}

	@Override
	public List<T> findListBySearchVo(SearchVo searchVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);

		return getSqlMapClientTemplate().queryForList(
				getFindPageInfoBySearchVoSqlId(entityClass.getSimpleName()),
				searchVo, (pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public Integer countBySearchVo(SearchVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getCountResulSizeBySearchVoSqlId(entityClass.getSimpleName()),
				searchVo);
	}

	@Override
	public PageInfo<T> findPagerUsingCacheBySearchVo(Long orgId,
			SearchVo searchVo, Integer pageNum, Integer pageSize, String sidx,
			String sord, String moduleName) {
		List<T> result = getPageResultList(moduleName, orgId, pageNum,
				pageSize, sidx, sord, searchVo, null, null);
		Integer countNum = 0;
		if (CollectionUtils.isEmpty(result)) {
			return new PageInfo<T>(pageNum, pageSize, countNum, result);
		} else {
			countNum = pageInfoCacheHelper.getCounter(moduleName, orgId);
			if (countNum <= 0
					|| !pageInfoCacheHelper.isExpired(moduleName, orgId)) {
				pageInfoCacheHelper.storeCounterTimeoutFlag(moduleName, orgId);
				countNum = countBySearchVo(searchVo);
				pageInfoCacheHelper.storeCounter(moduleName, orgId, countNum);
			}
		}
		return new PageInfo<T>(pageNum, pageSize, countNum, result);
	}

	private List<T> getPageResultList(String moduleName, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			SearchVo searchVo, String statement, Object object) {
		List<T> result = (List<T>) RedisDefaultPageUtils.getPageInfo(
				moduleName, orgId, pageNum, pageSize, entityClass);
		if (CollectionUtils.isEmpty(result)) {
			int newPageSize = RedisDefaultPageUtils.getRedisPageSize(pageNum,
					pageSize);
			if (searchVo != null) {
				result = findListBySearchVo(searchVo, pageNum, newPageSize,
						sidx, sord);
			} else {
				result = findList(object, pageNum, newPageSize, statement);
			}
			try {
				RedisDefaultPageUtils.setPageInfo(pageNum, pageSize,
						moduleName, orgId, result);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return result;
	}

	@Override
	public PageInfo<T> findPagerUsingCacheBySearchVo(Long orgId, Object object,
			Integer pageNum, Integer pageSize, String statement,
			String moduleName) {
		List<T> result = getPageResultList(moduleName, orgId, pageNum,
				pageSize, null, null, null, statement, object);
		Integer countNum = 0;
		if (CollectionUtils.isEmpty(result)) {
			return new PageInfo<T>(pageNum, pageSize, countNum, result);
		} else {
			countNum = pageInfoCacheHelper.getCounter(moduleName, orgId);
			if (countNum <= 0
					|| !pageInfoCacheHelper.isExpired(moduleName, orgId)) {
				pageInfoCacheHelper.storeCounterTimeoutFlag(moduleName, orgId);
				countNum = countObject(object, statement);
				pageInfoCacheHelper.storeCounter(moduleName, orgId, countNum);
			}
		}
		return new PageInfo<T>(pageNum, pageSize, countNum, result);
	}

	@Override
	public List<T> findList(Object object, Integer pageNum, Integer pageSize,
			String statement) {
		return getSqlMapClientTemplate().queryForList(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".find" + statement, object,
				(pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public Integer countObject(Object object, String statement) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getSqlNamespaceByClassName(entityClass.getSimpleName())
						+ ".count" + statement, object);
	}
}
