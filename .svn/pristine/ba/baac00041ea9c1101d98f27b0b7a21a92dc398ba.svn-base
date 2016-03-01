package com.tianque.newVillage.service;

import java.util.List;

import com.tianque.newVillage.domain.BasicYearInfo;

/**
 * @ClassName: BasicYearInfoService
 * @Description: 每年需要审核的新农村基本信息Service
 */
public interface BasicYearInfoService {
	/**
	 * 
	 * @param basicYearInfo
	 * @return BasicYearInfo
	 */
	public BasicYearInfo addBasicYearInfo(BasicYearInfo basicYearInfo);

	/**
	 * 
	 * @param id
	 * @return BasicYearInfo
	 */
	public BasicYearInfo getBasicYearInfoById(Long id);

	/**
	 * 
	 * @param id
	 */
	public void deleteBasicYearInfoById(String[] id);

	/**
	 * 修改 资金投入信息
	 * 
	 * @param BasicYearInfoVo
	 * @return BasicYearInfo
	 */
	public BasicYearInfo updateBasicYearInfo(BasicYearInfo basicYearInfo);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public BasicYearInfo getBasicYearInfoByBasicId(Long id);

	/***
	 * 根据年份,组织机构查出所有BasicYearInfo
	 */
	public List<BasicYearInfo> getBasicYearInfoByYearAndOrgId(Integer year,
			Long orgId);

}
