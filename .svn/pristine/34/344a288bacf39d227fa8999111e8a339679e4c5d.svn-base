package com.tianque.baseInfo.aidNeedPopulation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;

@Repository("searchAidNeedPopulationDao")
public class SearchAidNeedPopulationDaoImpl extends AbstractBaseDao implements
		SearchAidNeedPopulationDao {
	public PageInfo<AidNeedPopulation> searchAidNeedPopulation(
			SearchAidNeedPopulationVo condition, int pageNum, int pageSize,
			String sortField, String order) {
		if (condition == null)
			return emptyAidNeedPopulationPage(pageSize);
		condition.setSortField(sortField);
		condition.setOrder(order);
		PageInfo<AidNeedPopulation> pageInfo = new PageInfo<AidNeedPopulation>();
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchAidNeedPopulation.countSearchAidNeedPopulation",
				condition);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<AidNeedPopulation> list = getSqlMapClientTemplate()
					.queryForList(
							"searchAidNeedPopulation.searchAidNeedPopulation",
							condition, (pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<AidNeedPopulation>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	private Map<String, Object> getConditionMap(
			SearchAidNeedPopulationVo condition, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("fastSearchKeyWords", condition.getFastSearchKeyWords());
		map.put("name", condition.getName());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("isDeath", condition.getIsDeath());
		map.put("isEmphasis", condition.getIsEmphasis());
		map.put("gender", condition.getGender());
		map.put("usedName", condition.getUsedName());
		map.put("birthdayEnd", condition.getBirthdayEnd());
		map.put("birthdayStart", condition.getBirthdayStart());
		map.put("currentAddress", condition.getCurrentAddress());
		map.put("mobileNumber", condition.getMobileNumber());
		map.put("telephone", condition.getTelephone());
		map.put("nativePlaceAddress", condition.getNativePlaceAddress());
		map.put("workUnit", condition.getWorkUnit());
		map.put("maritalState", condition.getMaritalState());
		map.put("statureStart", condition.getStatureStart());
		map.put("statureEnd", condition.getStatureEnd());
		map.put("nation", condition.getNation());
		map.put("faith", condition.getFaith());
		map.put("schooling", condition.getSchooling());
		map.put("bloodType", condition.getBloodType());
		map.put("email", condition.getEmail());
		map.put("remark", condition.getRemark());
		map.put("province", condition.getProvince());
		map.put("city", condition.getCity());
		map.put("district", condition.getDistrict());
		map.put("politicalBackground", condition.getPoliticalBackground());
		map.put("healthState", condition.getHealthState());
		map.put("career", condition.getCareer());
		map.put("aidReason", condition.getAidReason());
		map.put("isLowHouseholds",
				condition.getIsLowHouseholds() != null ? condition
						.getIsLowHouseholds().toBoolean() : condition
						.getIsLowHouseholds());
		map.put("difficultCardNo", condition.getDifficultCardNo());
		map.put("difficultType", condition.getDifficultType());
		map.put("subsidiesAmountStart", condition.getSubsidiesAmountStart());
		map.put("subsidiesAmountEnd", condition.getSubsidiesAmountEnd());
		map.put("subsidiesGetTimeStart", condition.getSubsidiesGetTimeStart());
		map.put("subsidiesGetTimeEnd", condition.getSubsidiesGetTimeEnd());
		map.put("subsidiesStartTimeStart",
				condition.getSubsidiesStartTimeStart());
		map.put("subsidiesStartTimeEnd", condition.getSubsidiesStartTimeEnd());
		map.put("subsidiesPopulationStart",
				condition.getSubsidiesStartTimeEnd());
		map.put("subsidiesPopulationEnd", condition.getSubsidiesPopulationEnd());
		map.put("sortField", sortField);
		map.put("order", order);
		return map;
	}

	private PageInfo<AidNeedPopulation> emptyAidNeedPopulationPage(int pageSize) {
		PageInfo<AidNeedPopulation> pageInfo = new PageInfo<AidNeedPopulation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<AidNeedPopulation>());
		return pageInfo;
	}

	@Override
	public List<AidNeedPopulation> searchAidNeedPopulationForExport(
			SearchAidNeedPopulationVo aidNeedSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		aidNeedSearchCondition.setSortField(sortField);
		aidNeedSearchCondition.setOrder(order);
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchAidNeedPopulation.searchAidNeedPopulation",
					aidNeedSearchCondition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchAidNeedPopulation.searchAidNeedPopulation",
					aidNeedSearchCondition, (pageNum - 1) * pageSize, pageSize);
		}
	}

	@Override
	public Integer getCount(SearchAidNeedPopulationVo searchAidNeedPopulationVo) {
		// TODO Auto-generated method stub
		if (null == searchAidNeedPopulationVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchAidNeedPopulation.countSearchAidNeedPopulation",
				searchAidNeedPopulationVo);
	}

}
