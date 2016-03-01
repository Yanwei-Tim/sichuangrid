package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.School;

public interface SchoolDao {
	public PageInfo<School> finallSchoolList(School school, Integer page,
			Integer rows, String sidx, String sord, Long isEmphasis);

	public School addSchool(School school);

	public School updateSchool(School school);

	public void deleteSchoolById(Long id);

	public School getSimpleSchoolById(Long id);

	public School findSchoolByChineseNameAndOrgId(String chineseName, Long orgId);

	public List<School> searchSchoolForExport(School school, Integer page,
			Integer rows, String sidx, String sord);

	public List findSchoolByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public School getSchoolByName(String chineseName, Long id);

	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate);

	public Integer getCount(School school);

	/**
	 * 新增方法，自动检索填充学校名称
	 */
	public List<String> autoCompleteSchoolName(String schoolName);
}