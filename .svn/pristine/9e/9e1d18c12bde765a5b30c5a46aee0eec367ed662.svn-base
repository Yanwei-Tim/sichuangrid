package com.tianque.mobile.mobileDictionary.dao;

import java.util.Date;
import java.util.List;

import com.tianque.mobile.mobileDictionary.domain.MobileDictionary;
import com.tianque.mobile.mobileDictionary.domain.vo.MobileDictionaryVo;

public interface MobileDictionaryDao {

	public Integer countMobileDictionary(Integer type) throws Exception;

	public List<MobileDictionary> findMobileDictionaryLists(MobileDictionary mobileDictionary) throws Exception;

	public void addMobileDictionary(MobileDictionary mobileDictionary)
			throws Exception;

	public void updateMobileDictionary(MobileDictionary mobileDictionary)
			throws Exception;

	public List<MobileDictionaryVo> findAllDictsForList();
	
	public List<MobileDictionaryVo> findIncrementDictsForList(Date renewDate);

}
