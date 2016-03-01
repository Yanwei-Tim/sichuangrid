package com.tianque.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Hospital;
import com.tianque.domain.vo.SearchHospitalVo;

public interface HospitalDao {
	/**
	 * 新增医院信息
	 * 
	 * @param Hospital
	 * @return
	 */
	public Hospital addHospital(Hospital hospital);

	/**
	 * 通过id查找上网服务信息
	 * 
	 * @param id
	 * @return
	 */
	public Hospital getHospitalById(Long id);

	/**
	 * 通过id删除上网服务信息
	 * 
	 * @param id
	 */
	public void deleteHospitalById(Long id);

	/**
	 * 修改上网服务信息
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
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate);

	/**
	 * 通过名字和orgId查找医院
	 * 
	 * @param placeName
	 * @param id
	 * @return
	 */
	public Hospital getHospitalByPlaceNameAndOrgId(String hospitalName, Long id);

	public PageInfo<Hospital> searchHospitalForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHospitalVo searchHospitalVo);

	public Integer getCount(SearchHospitalVo searchHospitalVo);

}
