package com.tianque.core.redis.test;

import com.tianque.core.redis.core.RedisTemplate;

public class RedisTestListThread implements Runnable {

	private RedisTemplate redisTemplate;

	public RedisTestListThread(RedisTemplate redisTemplate) {
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
