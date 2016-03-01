package com.tianque.core.redis.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.core.redis.connection.RedisConnectionFactory;
import com.tianque.core.redis.core.RedisTemplate;

/**
 * @ClassName: RedisIsOpenAspect
 * @Description: redis是否启用AOP拦截
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月14日 上午9:08:24
 */
@Aspect
@Component
public class RedisIsOpenAspect {
	@Autowired
	private RedisTemplate redisTemplate;
	private Logger logger = LoggerFactory.getLogger(RedisIsOpenAspect.class);

	@Around("execution(public * com.tianque.core.redis.core.RedisTemplate.*(..))")
	public Object redisTemplateAround(ProceedingJoinPoint pjp) throws Throwable {
		if (!RedisConnectionFactory.isUseRedis()) {
			return null;
		}
		Object retVal = null;
		try {
			retVal = pjp.proceed();
		} catch (Exception e) {
			Object[] argObjs = pjp.getArgs();
			if (argObjs != null && argObjs.length > 1 && argObjs[1] != null
					&& argObjs[1] instanceof CacheNameSpaceEnum) {
				redisTemplate.addClearListKey(argObjs[0].toString(),
						(CacheNameSpaceEnum) argObjs[1]);
			}
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}
