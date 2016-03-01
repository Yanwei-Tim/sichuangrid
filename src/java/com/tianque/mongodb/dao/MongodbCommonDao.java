/**
 * 
 */
package com.tianque.mongodb.dao;

import java.util.Collection;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.WriteConcern;
import com.tianque.core.base.BaseDomain;

/**
 * @since
 * @author 曾利民
 * @version 1.0.0[2014年12月18日]
 */
public interface MongodbCommonDao<T extends BaseDomain> {

	public Query<T> createQuery();

	public UpdateOperations<T> createUpdateOperations();

	public Key<T> save(T entity);

	public Iterable<Key<T>> batchSave(Collection<T> entitys);

	public Iterable<Key<T>> batchSave(Collection<T> entitys, WriteConcern wc);

	public void update(final Query<T> query, final UpdateOperations<T> ops);

	public void delete(Iterable<Long> ids);

	public void delete(Long id);

	public void delete(T entity);

	public void delete(final Query<T> query);

	public T getById(Long id);
}
