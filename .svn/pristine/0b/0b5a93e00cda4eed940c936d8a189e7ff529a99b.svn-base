package com.tianque.core.redis.core;

import java.util.List;

import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.core.base.BaseDomain;

/**
 * @ClassName: RedisOperations
 * @Description: redis 操作列表接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月8日 上午9:05:44
 */
public interface RedisTemplate<T extends BaseDomain> {

	public static final String REDIS_SET_SUCCESS_RESULT = "OK";

	public static final String CACHE_INDEX_KEY_SUFFIX = "_INDEX_KEY";

	/**
	 * @Description: 存储数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @throws
	 */
	public boolean set(final String key, final CacheNameSpaceEnum namespace,
			final Object value) throws Exception;

	/**
	 * @Description: 存储数据，带失效时间
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param seconds
	 *            有效期（单位：秒）
	 * @param value
	 *            值
	 * @throws
	 */
	public boolean set(final String key, final CacheNameSpaceEnum namespace,
			final int seconds, final Object value) throws Exception;

	/**
	 * @Description: 当键不存在时存储数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @throws
	 */
	public Long setWhenNotExists(final String key,
			final CacheNameSpaceEnum namespace, final Object value)
			throws Exception;

	/**
	 * @Description: 获取数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return 值
	 * @throws
	 */
	public Object get(final String key, final CacheNameSpaceEnum namespace,
			final Class classzs) throws Exception;

	/**
	 * @Description: 删除指定键数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return
	 * @throws
	 */
	public long del(final String key, final CacheNameSpaceEnum namespace)
			throws Exception;

	/**
	 * @Description: list集合存入
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param list
	 *            集合
	 * @throws
	 */
	public void createList(final String key,
			final CacheNameSpaceEnum namespace, final List<T> list)
			throws Exception;

	/**
	 * @Description: 根据键获取集合
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return 集合
	 * @throws
	 */
	public List<T> getList(final String key,
			final CacheNameSpaceEnum namespace, final Class classzs)
			throws Exception;

	/**
	 * @Description: 分页获取数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @return 集合
	 * @throws
	 */
	public List<T> getListForPage(final String key,
			final CacheNameSpaceEnum namespace, final int pageNum,
			final int pageSize, final Class classzs) throws Exception;

	/**
	 * @Description: 获取集合长度
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return 集合长度
	 * @throws
	 */
	public long getListSize(final String key, final CacheNameSpaceEnum namespace)
			throws Exception;

	/**
	 * @Description: 向集合前面追加
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param obj
	 *            追加的对象
	 * @throws
	 */
	public void addListFirst(final String key,
			final CacheNameSpaceEnum namespace, final Object obj)
			throws Exception;

	/**
	 * @Description: 向集合后面追加
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param obj
	 *            追加的对象
	 * @throws
	 */
	public void addListLast(final String key,
			final CacheNameSpaceEnum namespace, final Object obj)
			throws Exception;

	/**
	 * @Description: 从list中移除对象
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param index
	 *            索引,从0开始
	 * @throws
	 */
	public void removeList(final String key,
			final CacheNameSpaceEnum namespace, final int... index)
			throws Exception;

	/**
	 * @Description: 更新指定索引的对象
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @param obj
	 *            对象
	 * @param index
	 *            索引
	 * @throws
	 */
	public boolean updateList(final String key,
			final CacheNameSpaceEnum namespace, final Object obj,
			final int index) throws Exception;

	/**
	 * @Description: 返回并删除名称为key的list中的尾元素
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return 尾部对象
	 * @throws
	 */
	public Object delListLast(final String key,
			final CacheNameSpaceEnum namespace, final Class classzs)
			throws Exception;

	/**
	 * @Description: 添加需要清理缓存的key（redis发生异常是清理缓存）
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @throws
	 */
	public void addClearListKey(String key, final CacheNameSpaceEnum namespace);

	/**
	 * @Description: 缓存数据，string类型
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param key
	 *            键
	 * @return 结果
	 * @throws
	 */
	public String get(String key, final CacheNameSpaceEnum namespace)
			throws Exception;
}
