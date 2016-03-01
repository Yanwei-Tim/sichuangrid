package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.vo.SearchOtherLocaleVo;

public interface SearchOtherLocaleDao {
	public PageInfo<OtherLocale> searchOtherLocale(
			SearchOtherLocaleVo searchOtherLocaleVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<OtherLocale> searchOtherLocaleForExport(
			SearchOtherLocaleVo searchOtherLocaleVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List findOtherLocaleNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public Integer getCount(SearchOtherLocaleVo searchOtherLocaleVo);
}
