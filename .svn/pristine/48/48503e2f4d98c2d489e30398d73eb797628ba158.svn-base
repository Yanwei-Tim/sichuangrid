package com.tianque.core.redis.test;

import com.tianque.core.redis.core.RedisTemplate;

public class RedisTestThread implements Runnable {

	private RedisTemplate redisTemplate;

	public RedisTestThread(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run() {
		try {
			new RedisTest().mainTest(redisTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
