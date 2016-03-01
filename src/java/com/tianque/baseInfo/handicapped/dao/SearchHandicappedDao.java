package com.tianque.baseInfo.handicapped.dao;

import java.util.List;

public interface SearchHandicappedDao {

	public List findHandicappedNameAndPinyinAndOrgInternalCode(String name, String orgInternalCode);

}
