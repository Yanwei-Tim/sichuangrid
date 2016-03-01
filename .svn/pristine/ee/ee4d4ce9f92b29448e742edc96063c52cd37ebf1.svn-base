package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;

public interface SearchMentalPatientService {

	public List<PersonnelAreaDataVo> returnDataList(Long orgId);

	public HighchartColumnVo returnColumnList(Long orgId);

	public List<Object[]> returnPieList(Long orgId);

	public PageInfo<MentalPatient> searchMentalPatient(
			SearchMentalPatientVo condition, int pageNum, int pageSize,
			String sortField, String order);

	public List<MentalPatient> searchMentalPatientForExport(
			SearchMentalPatientVo mentalPatientCondition, Integer page,
			Integer rows, String sidx, String sord);

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public String[][] getExportPopertyArray();

	public Long getCount(String orgInternalCode);

	public Integer getCount(SearchMentalPatientVo searchMentalPatientVo);
}
