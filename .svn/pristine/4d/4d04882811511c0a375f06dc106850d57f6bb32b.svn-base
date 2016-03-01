package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.dao.SearchHouseForAutoCompleteDao;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.shard.util.ShardConversion;

@Repository("searchHouseForAutoCompleteDao")
public class SearchHouseForAutoCompleteDaoImpl extends AbstractBaseDao
		implements SearchHouseForAutoCompleteDao {
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByAddress(
			String orgCode, String address, Long houseType, int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(address)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("address", address + "%");
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate().queryForList(
					"searchHouseForAutoComplete.searchHouseInfoByAddress",
					condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByHouseCode(
			String orgCode, String houseCode, Long houseType, int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(houseCode)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("houseCode", houseCode + "%");
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate().queryForList(
					"searchHouseForAutoComplete.searchHouseInfoByHouseCode",
					condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunity(
			String orgCode, String community, Long houseType, int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(community)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("community", community + "%");
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate().queryForList(
					"searchHouseForAutoComplete.searchHouseInfoByCommunity",
					condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlock(
			String orgCode, String community, String block, Long houseType,
			int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(block)
				&& StringUtil.isStringAvaliable(community)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("community", community);
			condition.put("block", block + "%");
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate()
					.queryForList(
							"searchHouseForAutoComplete.searchHouseInfoByCommunityAndBlock",
							condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlockAndUnit(
			String orgCode, String community, String block, String unit,
			Long houseType, int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(unit)
				&& StringUtil.isStringAvaliable(community)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("community", community);
			condition.put("block", block);
			condition.put("unit", unit + "%");
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate()
					.queryForList(
							"searchHouseForAutoComplete.searchHouseInfoByCommunityAndBlockAndUnit",
							condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

	@Override
	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlockAndUnitAndRoom(
			String orgCode, String community, String block, String unit,
			String room, Long houseType, int rows) {
		if (StringUtil.isStringAvaliable(orgCode)
				&& StringUtil.isStringAvaliable(community)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orgCode", orgCode + "%");
			condition.put("community", community);
			condition.put("block", block);
			condition.put("unit", unit);
			if (StringUtil.isStringAvaliable(room)) {
				condition.put("room", room + "%");
			} else {
				condition.put("room", "");
			}
			condition.put("houseType", houseType);
			condition.put("shardCode",
					shardConversion.getShardCodeByOrgCode(orgCode));
			return getSqlMapClientTemplate()
					.queryForList(
							"searchHouseForAutoComplete.searchHouseInfoByCommunityAndBlockAndUnitAndRoom",
							condition, 0, rows);
		} else {
			return new ArrayList<HouseSimpleInfoForSearch>();
		}
	}

}
