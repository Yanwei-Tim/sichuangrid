package com.tianque.baseInfo.mentalPatient.dao;

import java.util.List;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatientTypeCount;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;

public interface SearchMentalPatientDao {
	public PageInfo<MentalPatient> searchMentalPatient(SearchMentalPatientVo condition,
			int pageNum, int pageSize, String sortField, String order);

	public List<MentalPatient> searchMentalPatientForExport(
			SearchMentalPatientVo mentalPatientCondition, Integer page, Integer rows, String sidx,
			String sord);

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public List<MentalPatientTypeCount> findMentalPatientCountList(String orgInternalCode);

	public List<MentalPatientTypeCount> findHelped(String orgInternalCode);

	public Long getCount(String orgInternalCode);
	
	public Integer getCounts(SearchMentalPatientVo searchMentalPatientVo);

}
