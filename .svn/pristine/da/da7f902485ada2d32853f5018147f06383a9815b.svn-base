package com.tianque.service.bridge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.dao.IssueTypeDao;

@Repository("baseInfoDeleter")
public class BaseInfoDeleter {
	@Autowired
	private IssueTypeDao issueTypeDao;

	public boolean checkRelatePersonDelete(Long baseInfoId, String baseInfoType) {
		Integer count = issueTypeDao.getIsRelatePersons(baseInfoId, baseInfoType);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	public boolean checkRelatePlaceDelete(Long baseInfoId, String baseInfoType) {
		Integer count = issueTypeDao.getIsRelatePlaces(baseInfoId, baseInfoType);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	public List<Long> getRelatepersonId(List<Long> personsIds, String baseInfoType) {
		List<Long> list = issueTypeDao.getIsRelatePersonIds(personsIds, baseInfoType);
		return list;
	}

	public List<Long> getRelateplaceId(List<Long> RelatePlaceId, String RelatePlaceType) {
		List<Long> list = issueTypeDao.getIsRelatePlaceIds(RelatePlaceId, RelatePlaceType);
		return list;
	}

}
