package com.tianque.xichang.working.peopleAspiration.service;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;
import com.tianque.xichang.working.peopleAspiration.domain.vo.SearchPeopleAspirationsVo;

public interface PeopleAspirationService extends
		BaseService<PeopleAspirations, SearchPeopleAspirationsVo> {

	/**
	 * 校验当前网格下是否已存在该身份证
	 * 
	 * @param id
	 * @param orgId
	 * @param idCardNo
	 * @return
	 */
	String exsistedIdCardByIdAndIdCardNo(Long id, Long orgId, String idCardNo);

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param id
	 * @param orgId
	 * @param serialNumber
	 * @return
	 */
	String hasDuplicateSerialNumber(Long id, Long orgId, String serialNumber);

	/**
	 * 已办：根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param searchPeopleAspirationsVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo findDonePagerBySearchVo(SearchPeopleAspirationsVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 修改
	 * 
	 * @param peopleAspirations
	 * @return
	 */
	PeopleAspirations updatePeopleAspirations(
			PeopleAspirations peopleAspirations);

}
