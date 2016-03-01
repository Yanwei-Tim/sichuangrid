package com.tianque.job.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.core.redis.core.RedisTemplate;
import com.tianque.core.util.StringUtil;

/**
 * @ClassName: RedisDefaultPageCacheClearJob
 * @Description: 默认列表缓存清理job
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月21日 下午3:35:05
 */
@Component("redisPeopleDefaultPageCacheClearJob")
public class RedisPeopleDefaultPageCacheClearJob {
	private Logger logger = LoggerFactory
			.getLogger(RedisPeopleDefaultPageCacheClearJob.class);

	@Autowired
	private RedisTemplate redisTemplate;

	private CacheNameSpaceEnum namespace = CacheNameSpaceEnum.PEOPLE_DEFAULTPAGE;

	public void doClearCache() {
		try {
			List<Object> list = (List<Object>) redisTemplate.getList(
					RedisTemplate.CACHE_INDEX_KEY_SUFFIX, namespace, null);
			int count = 0;
			for (int i = 0; list != null && i < list.size(); i++) {
				String key = (String) list.get(i);
				if (!StringUtil.isStringAvaliable(key)) {
					continue;
				}
				count++;
				redisTemplate.del(key, namespace);
			}
			logger.error("redis缓存清理[namespace=" + namespace + "],共清理[" + count
					+ "]个集合！");
		} catch (Exception e) {
			logger.error("redis缓存清理[namespace=" + namespace + "]发生异常！", e);
		}
	}
}
