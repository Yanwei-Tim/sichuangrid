package com.tianque.baseInfo.actualHouse.dao;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.mongodb.dao.MongodbCommonDao;

public interface ActualHouseFromMongodbDao extends MongodbCommonDao<HouseInfo> {

	public PageInfo<HouseInfo> queryByOrgCodeForPage(String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String indexName);

	public PageInfo<HouseInfo> fastSearchForPage(
			SearchHouseInfoVo searchHouseInfoVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);
}
