package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.School;

public interface SchoolService {
	public PageInfo<School> finallSchoolList(School school, Integer page,
			Integer rows, String sidx, String sord, Long isEmphasis);

	public School addSchool(School school);

	public School updateSchool(School school);

	public boolean deleteSchoolById(Long id);

	public School getSchoolById(Long id);

	public School findSchoolByChineseNameAndOrgId(String chineseName, Long orgId);

	public List findSchoolByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public School updateSchoolByName(String chineseName, Long id, School domain);

	boolean hasDuplicateSchoolName(Long ownerOrgId, String hospitalName,
			Long exceptedId);

	School updateSchoolByEmphasis(School school);

	public List<Long> deleteSchoolById(List<Long> personIds);

	public List<Long> deleteSchoolByIds(Long[] ids);

	public boolean hasRelatePlacce(List<Long> personIds);

	public void shiftSchool(Long[] ids, Long orgId);

	public void updateEmphasiseByIds(Long[] ids, School location);

	/**
	 * 新增方法，为数据管理准备
	 * 
	 * @param orgId
	 * @param chineseName
	 * @return
	 */
	public School hasDuplicateSchool(Long orgId, String chineseName);

	/**
	 * 新增方法，自动检索填充学校名称
	 */
	public List<String> autoCompleteSchoolName(String schoolName);
}
