package com.tianque.baseInfo.idleYouth.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.vo.PageInfo;

public interface IdleYouthService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加 一条 闲散青少年 信息
	 * 
	 * @param idleYouth
	 * @return
	 */
	public IdleYouth addIdleYouth(IdleYouth idleYouth);

	/**
	 * 修改 一条 闲散青少年 信息
	 * 
	 * @param idleYouth
	 * @return
	 */
	public IdleYouth updateIdleYouth(IdleYouth idleYouth);

	/**
	 * 分页 查询 闲散青少年
	 * 
	 * @param query
	 * @param idleYouth
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IdleYouth> findIdleYouthsForPageByOrganizationId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 得到 一条 闲散青少年 信息
	 * 
	 * @param id
	 * @return
	 */
	public IdleYouth getIdleYouthById(Long id);

	public IdleYouth getIdleYouthByIdCardNoAndOrganizationId(String idCardNo,
			Long organizationId);

	public IdleYouth updateIdleYouthByIdCardNoAndOrgId(String name, Long id,
			IdleYouth domain);

	public boolean hasDuplicateIdleYouth(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 更改关注/取消关注
	 * 
	 * @param ids
	 * @param isEmphasis
	 */
	public List<IdleYouth> updateEmphasiseByIds(List<Long> ids, Long isEmphasis);

	public List<IdleYouth> updateDeathByIds(List<Long> personIds, Boolean death);

	public void deleteIdleYouthByIds(Long[] personIds);

	public Long addStaffType(Long idleYouthId, Long staffTypeId);

	public void regrantStaffTypeIds(Long idleYouthId, List<Long> staffTypeIds);

	public IdleYouth getFullIdleYouthById(Long id);

	public String[][] getExportPopertyArray();

	public List<IdleYouth> findIdleYouthsByBIRTHDAY();

	IdleYouth getIdleYouthByIdCardNo(String idCardNo, Long orgId);

	IdleYouth hasDuplicateIdleYouth(Long orgId, String idCardNo);

	public IdleYouth updateIdleYouthBaseInfo(IdleYouth population);

	public IdleYouth addIdleYouthBaseInfo(IdleYouth population);

	public IdleYouth updateIdleYouthBusinessInfo(IdleYouth population,
			List<Long> staffTypeIds);

	public void moveTempByIds(String[] moveids, Long targetOrgId);

	public Integer countIdleYouthsByBirthdayForMark();

	public List<IdleYouth> findIdleYouthsByBirthdayForMark(int pageNum,
			int pageSize);
}
