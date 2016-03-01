package com.tianque.baseInfo.actualHouse.dao;

import java.util.regex.Pattern;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.mongodb.dao.AbstractMongodbDao;

@Repository("actualHouseFromMongodbDao")
public class ActualHouseFromMongodbDaoImpl extends
		AbstractMongodbDao<HouseInfo> implements ActualHouseFromMongodbDao {

	public ActualHouseFromMongodbDaoImpl() {
		this.entityClass = HouseInfo.class;
	}

	public PageInfo<HouseInfo> queryByOrgCodeForPage(String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String indexName) {
		orgCode = orgCode.replace(".", "@");
		Pattern pattern = Pattern.compile("^" + orgCode);
		Query<HouseInfo> query = datastore.createQuery(entityClass).filter(
				"orgCode", pattern);
		query.hintIndex(indexName);
		return queryByQueryForPage(query, pageNum, pageSize, sidx, sord);
	}

	public PageInfo<HouseInfo> fastSearchForPage(
			SearchHouseInfoVo searchHouseInfoVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		String orgCode = searchHouseInfoVo.getOrgInternalCode().replace(".",
				"@");
		Pattern pattern = Pattern.compile("^" + orgCode);
		Query<HouseInfo> query = datastore.createQuery(HouseInfo.class).filter(
				"orgCode", pattern);
		query.or(
				query.criteria("houseCode").contains(
						searchHouseInfoVo.getHouseCodeAndAddress()),
				query.criteria("address").contains(
						searchHouseInfoVo.getHouseCodeAndAddress()));
		query.hintIndex("idx_common");
		return queryByQueryForPage(query, pageNum, pageSize, sidx, sord);
	}
}
