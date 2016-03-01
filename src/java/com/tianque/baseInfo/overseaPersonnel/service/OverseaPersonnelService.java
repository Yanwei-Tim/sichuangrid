package com.tianque.baseInfo.overseaPersonnel.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;

public interface OverseaPersonnelService extends
		BaseInfoPopulationTemplateService {

	OverseaPersonnel addOverseaPersonnel(OverseaPersonnel overseaPersonnel);

	PageInfo<OverseaPersonnel> findOverseaPersonnelForListPage(Long orgId,
			int pageNum, int pageSize, String sortField, String order,
			Long isEmphasis);

	OverseaPersonnel getOverseaPersonnelById(Long id);

	OverseaPersonnel updateOverseaPersonnel(OverseaPersonnel overseaPersonnel);

	OverseaPersonnel getOverseaPersonnelByCertificateNoAndOrganizationId(
			String certificateNo, Long organizationId);

	OverseaPersonnel getOverseaPersonnelByCertificateAndOrganizationId(
			Long certificateType, String certificateNo, Long organizationId);

	OverseaPersonnel updateOverseaPersonnelByCertificate(Long certificateType,
			String certificateNo, Long orgId, OverseaPersonnel domain);

	boolean hasDuplicateOverseaPersonnel(Long orgId, Long certificateType,
			String certificateNo, Long exceptedId);
	
	public OverseaPersonnel hasDuplicateOverseaPersonnel(Long orgId,
			Long certificateType, String certificateNo);

	OverseaPersonnel getOverseaPersonnelByIdCardNo(String idCardNo, Long orgId);

	OverseaPersonnel updateOverseaPersonnelById(
			OverseaPersonnel overseaPersonnel);

	public List<Long> deleteForOverseaPersonnelByIds(List<Long> personIds);

	public void deleteOverseaPersonnelByIds(Long[] ids);

	boolean hasRelatePersons(List<Long> personIds);

	PageInfo<OverseaPersonnel> searchOverseaPersonnel(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order);

	List<OverseaPersonnel> findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	PageInfo<OverseaPersonnel> fastSearchOverseaPersonnel(
			SearchOverseaPersonnelVo overseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order);

	List<OverseaPersonnel> searchOverseaPersonnelForExport(
			SearchOverseaPersonnelVo overseaPersonnelCondition, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 根据idCardNo和orgId查找境外人员
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	OverseaPersonnel getOverseaPersonnelByIdCardNoAndOrgId(String idCardNo,
			Long orgId);

	void moveTempByIds(String[] moveids, Long targetOrgId);

	public Integer getCount(SearchOverseaPersonnelVo searchOverseaPersonnelVo);
	/***
	 * 修改境外人员表中房屋关联信息
	 * @param populationId
	 * @param isHaveHouse
	 * @param noHouseReason
	 */
	public void updateOverseaPersonnelHouseInfo(
			Long populationId, Boolean isHaveHouse, String noHouseReason);

}
