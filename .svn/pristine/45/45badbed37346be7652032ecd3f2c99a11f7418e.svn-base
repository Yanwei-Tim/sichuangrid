package com.tianque.dao;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;

public interface BaseDao<T extends BaseDomain, SearchVo extends BaseDomain> {

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(Long id);

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public T add(T entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public T update(T entity);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean delete(Long id);

	/**
	 * 根据ID集合删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 * @param statement
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean delete(List<Long> ids, String statement);

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	public PageInfo<T> findPagerBySearchVo(SearchVo searchVo, Pager pager);

	/**
	 * 根据SearchVo进行查询,count的结果会存入缓存，默认2小时更新一次(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public PageInfo<T> findPagerUsingCacheBySearchVo(Long orgId, Object object,
			Pager pager, String statement, String moduleName);

	/**
	 * 更新实体对象
	 * 
	 * @param object
	 *            对象
	 * @return 更新成功返回true，否则返回false
	 */
	public boolean isUpdateSuccess(Object object, String statement);

	/**
	 * 更新实体对象
	 * 
	 * @param map
	 *            对象
	 */
	public T update(T entity, String statement);

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * @param suffix
	 *            后缀
	 * @return Pager对象
	 */
	public PageInfo<T> findPagerBySearchVo(Object object, Pager pager,
			String statement);

	/**
	 * 根据传入的条件查询数据
	 * 
	 * @param object
	 * @param statement
	 * @return
	 */
	public List findForList(Object object, String statement);

	/**
	 * 根据传入的条件分页查询数据
	 * 
	 * @param object
	 * @param statement
	 * @return
	 */
	public List findForList(Object object, Pager pager, String statement);

	/**
	 * 根据传入的条件统计数据
	 * 
	 * @param object
	 * @param statement
	 * @return
	 */
	public Integer countForObject(Object object, String statement);

	/**
	 * 根据传入的条件删除数据
	 * 
	 * @param object
	 * @param statement
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean delete(Object object, String statement);

	/**
	 * 根据传入的条件获得一个实体对象
	 * 
	 * @param object
	 * @param statement
	 * @return
	 */
	public T get(Object object, String statement);

	/**
	 * 插入一个实体对象
	 * 
	 * @param object
	 * @param statement
	 * @return
	 */
	public T add(T entity, String statement);

	/**
	 * 执行insert，为研判分析服务
	 * 
	 * @param statement
	 */
	public void add(Object object, String statement);

	/**
	 * 执行insert，判断新增是否成功，成功返回true
	 * 
	 * @param statement
	 */
	public boolean isAddSuccess(Object object, String statement);

}
