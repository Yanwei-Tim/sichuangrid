package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Hospital;
import com.tianque.domain.vo.SearchHospitalVo;

public interface HospitalService {
	/**
	 * 新增医院信息
	 * 
	 * @param Hospital
	 * @return
	 */
	public Hospital addHospital(Hospital hospital);

	/**
	 * 通过id查找医院信息
	 * 
	 * @param id
	 * @return
	 */
	public Hospital getHospitalById(Long id);

	/**
	 * 通过id删除医院信息
	 * 
	 * @param id
	 */
	public void deleteHospitalByIds(Long[] ids);

	/**
	 * 修改医院信息
	 * 
	 * @param Hospital
	 * @return
	 */
	public Hospital updateHospital(Hospital hospital);

	/**
	 * 修改医院的注销状态
	 * 
	 * @param id
	 *            医院id
	 * @param isEmphasis
	 *            注销状态
	 * @param logoutReason
	 *            注销原因
	 * @param logoutDate
	 *            注销时间
	 */
	public Hospital updateEmphasiseById(Long id, Hospital location);

	/**
	 * 是否有重复的医院
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param placeName
	 *            医院名称
	 * @param exceptedId
	 *            医院id
	 * @return
	 */
	public Boolean hasDuplicateHospital(Long orgId, String hospitalName,
			Long exceptedId);

	/**
	 * 数据管理用
	 * 
	 * @param orgId
	 * @param placeName
	 * @return
	 */
	public Hospital hasDuplicateHospital(Long orgId, String hospitalName);

	/**
	 * 通过searchHospitalVo 查询医院场所的分页对象
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param searchHospitalVo
	 * @return
	 */
	public PageInfo<Hospital> searchHospitalForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHospitalVo searchHospitalVo);

	/**
	 * 修改医院信息
	 * 
	 * @param Hospital
	 * @return
	 */
	public Hospital updateHospitalForImport(Hospital hospital);

	public Integer getCount(SearchHospitalVo searchHospitalVo);
	
	public Hospital updateHospitalByName(String hospitalName, Long orgId, Hospital domain);
}
