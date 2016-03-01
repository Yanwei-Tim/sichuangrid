package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.KeyPersonService;
import com.tianque.shard.util.ShardConversion;

/**
 * 二维地图 重点人员个性化方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPersonService")
public class KeyPersonServiceImpl extends BaseService implements
		KeyPersonService {

	@Autowired
	private KeyPersonTwoDimensionMapDao keyPersonTwoDimensionMapDao;

	@Autowired
	private ShardConversion shardConversion;

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
			String orgInternalCode, String tableName) {
		if (null == orgInternalCode || null == tableName) {
			throw new BusinessValidationException("参数错误!");
		}
		return keyPersonTwoDimensionMapDao.countTwoDimensionMapPageInfoByOrgId(
				orgInternalCode, tableName,
				shardConversion.getShardCodeByOrgCode(orgInternalCode));
	}
}
