package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.CommonPeopleDao;
import com.tianque.service.CommonPeopleService;

@Service("commonPeopleService")
public class CommonPeopleServiceImpl implements CommonPeopleService {
	@Autowired
	private CommonPeopleDao commonPopulationDao;

	public PageInfo<People> searchNamePageInCommonPopulation(String name, String orgInternalCode,
			Integer pageNum, Integer pageSize) {
		if (null == name && orgInternalCode == null) {
			return null;
		}

		return commonPopulationDao.searchCommonPopulationByName(name, orgInternalCode, pageNum,
				pageSize);
	}

	@Override
	public List<People> searchNameInCommonPopulation(String name, String orgInternalCode) {
		if (null == name && orgInternalCode == null) {
			return null;
		}
		return commonPopulationDao.searchCommonPopulationByName(name, orgInternalCode);
	}

}
