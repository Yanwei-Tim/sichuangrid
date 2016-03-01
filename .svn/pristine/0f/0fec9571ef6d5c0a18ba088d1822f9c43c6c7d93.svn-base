package com.tianque.baseInfo.rectificativePerson.service;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;

public interface SearchRectificativePersonService {

	public Map<String, Long> getExecuteTypeCount(String orgInternalCode);

	public Map<String, Long> helpedCount(String orgInternalCode);

	public Map<String, Long> notHelpedCount(String orgInternalCode);

	public Long getCount(String orgInternalCode);

	public Integer getCounts(SearchRectificativePersonVo personVo);

	public List<PersonnelAreaDataVo> returnDataList(Long orgId);

	public HighchartColumnVo returnColumnList(Long orgId);

	public List<Object[]> returnPieList(Long orgId);

	public String[][] getExportPopertyArray();

	public PageInfo<RectificativePerson> searchRectificativePerson(
			SearchRectificativePersonVo condition, int pageNum, int pageSize,
			String sortField, String order);

	public List findRectificativePersonNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	public List<RectificativePerson> searchRectificativePersonForExport(
			SearchRectificativePersonVo rectificativePersonCondition,
			Integer page, Integer rows, String sidx, String sord);
}
