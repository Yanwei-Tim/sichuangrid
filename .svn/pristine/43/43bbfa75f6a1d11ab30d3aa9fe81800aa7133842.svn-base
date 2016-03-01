package com.tianque.shard.util;

import com.tianque.exception.base.BusinessValidationException;

public class IdConversionShardUtil {

	/**
	 * id加上分表code
	 * 
	 * @param id
	 * @param shardCode
	 * @return
	 */
	public static Long IdAdditionalShard(Long id, String shardCode) {
		return Long.parseLong(shardCode + id);
	}

	/**
	 * 根据ID获取shardCode
	 * 
	 * @param id
	 * @return
	 */
	public static String getShardCodeById(Long id) {
		String temp = Long.toString(id);
		if (temp.length() < 4)
			throw new BusinessValidationException("分表ID错误！");
		return temp.substring(0, 4);
	}
}
