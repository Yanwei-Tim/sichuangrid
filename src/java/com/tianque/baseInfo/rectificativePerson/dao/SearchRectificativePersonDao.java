package com.tianque.baseInfo.rectificativePerson.dao;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;

public interface SearchRectificativePersonDao {
	public PageInfo<RectificativePerson> searchRectificativePerson(
			SearchRectificativePersonVo condition, int pageNum, int pageSize,
			String sortField, String order);

	public List<RectificativePerson> searchRectificativePersonForExport(
			SearchRectificativePersonVo rectificativePersonCondition,
			Integer page, Integer rows, String sidx, String sord);

	public List findRectificativePersonNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	public List<RectificativePerson> searchRectificativePerson(Date endDate);

	public Long getExecuteTypeCount(String orgInternalCode, Long type);

	public Long getHelpedCount(String orgInternalCode, Long executeType);

	public Long getNotHelpedCount(String orgInternalCode, Long executeType);

	public Long getCount(String orgInternalCode);

	public Integer getCounts(SearchRectificativePersonVo personVo);

}
