package com.tianque.baseInfo.elderlyPeople.service;

import java.util.List;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;

public interface SearchElderlyPeopleService {

	public PageInfo<ElderlyPeople> searchElderlyPeople(
			SearchElderlyPeopleVo elderlyPeopleCondition, int pageNum,
			int pageSize, String sortField, String order);

	public List<ElderlyPeople> searchElderlyPeopleForExport(
			SearchElderlyPeopleVo elderlyPeopleCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchElderlyPeopleVo elderlyPeopleCondition);
}
