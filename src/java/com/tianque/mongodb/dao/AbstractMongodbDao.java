package com.tianque.mongodb.dao;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.WriteConcern;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;

abstract public class AbstractMongodbDao<T extends BaseDomain> implements
		MongodbCommonDao<T> {

	private final static Logger logger = LoggerFactory
			.getLogger(AbstractMongodbDao.class);

	protected Datastore datastore;

	protected Class<T> entityClass;

	@Resource(name = "mongodbTemplate")
	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	@Override
	public Query<T> createQuery() {
		return datastore.createQuery(entityClass);
	}

	@Override
	public UpdateOperations<T> createUpdateOperations() {
		return datastore.createUpdateOperations(entityClass);
	}

	@Override
	public Key<T> save(T entity) {
		return datastore.save(entity);
	}

	@Override
	public Iterable<Key<T>> batchSave(Collection<T> entitys) {
		return datastore.save(entitys);
	}

	@Override
	public Iterable<Key<T>> batchSave(Collection<T> entitys, WriteConcern wc) {
		return datastore.save(entitys, wc);
	}

	@Override
	public void delete(Iterable<Long> ids) {
		datastore.delete(entityClass, ids);
	}

	@Override
	public void delete(Long id) {
		datastore.delete(entityClass, id);
	}

	@Override
	public void delete(T entity) {
		datastore.delete(entity);
	}

	@Override
	public void delete(Query<T> query) {
		datastore.delete(query);
	}

	@Override
	public void update(final Query<T> query, final UpdateOperations<T> ops) {
		datastore.update(query, ops, false);
	}

	public PageInfo<T> queryByOrgCodeForPage(String orgCode, Integer pageNum,
			Integer pageSize, String sidx, String sord, String indexName) {
		orgCode = orgCode.replace(".", "@");
		Pattern pattern = Pattern.compile("^" + orgCode);
		Query<T> query = datastore.createQuery(entityClass)
				.filter("orgCode", pattern).field("logOut").equal(0);
		query.hintIndex(indexName);
		return queryByQueryForPage(query, pageNum, pageSize, sidx, sord);
	}

	protected PageInfo<T> queryByOrgParentidForPage(List<Long> orgList,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Query<T> query = datastore.createQuery(entityClass).filter(
				"organization.id in", orgList);
		return queryByQueryForPage(query, pageNum, pageSize, sidx, sord);
	}

	protected PageInfo<T> fastSearchForPage(String orgCode, Integer pageNum,
			Integer pageSize, String sidx, String sord, String condition) {
		orgCode = orgCode.replace(".", "@");
		Pattern orgCodePattern = Pattern.compile("^" + orgCode);
		Query<T> query = datastore.createQuery(entityClass)
				.filter("orgCode", orgCodePattern).field("logOut").equal(0);
		if (ValidateHelper.checkNumeric(condition)) {
			Pattern pattern = Pattern.compile("[/d]?" + condition + "[/d]?");
			query.filter("idCardNo", pattern);
			query.hintIndex("orgCode_1_idCardNo_1_logOut_1");
		} else if (!ValidateHelper.illegalLetter(condition)) {
			query.or(query.criteria("qp").contains(condition.toLowerCase()),
					query.criteria("jp").contains(condition.toLowerCase()));
			query.hintIndex("orgCode_1_name_1_qp_1_jp_1_logOut_1");
		} else if (StringUtil.isStringAvaliable(condition)) {
			Pattern pattern = Pattern.compile("[/w]?" + condition + "[/w]?");
			query.filter("name", pattern);
			query.hintIndex("orgCode_1_name_1_qp_1_jp_1_logOut_1");
		}
		return queryByQueryForPage(query, pageNum, pageSize, sidx, sord);
	}

	protected PageInfo<T> queryByQueryForPage(final Query<T> query,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		long start = System.currentTimeMillis();
		logger.info(query.toString());
		int countNum = (int) query.countAll();
		if (sord.equalsIgnoreCase("ASC")) {
			query.order(sidx);
		} else {
			query.order("-" + sidx);
		}
		query.offset((pageNum - 1) * pageSize).limit(pageNum * pageSize);
		List<T> result = query.asList();
		logger.info("cost:" + (System.currentTimeMillis() - start)
				+ "ms, explain" + query.explain());
		return new PageInfo<T>(pageNum, pageSize, countNum, result);
	}

	protected Collection<T> querByQueryForList(final Query<T> query,
			String sidx, String sord) {
		if (sord.equalsIgnoreCase("ASC")) {
			query.order(sidx);
		} else {
			query.order("-" + sidx);
		}
		return query.asList();
	}

	public T getById(Long id) {
		return datastore.createQuery(entityClass).field("id").equal(id).get();
	}
}
