package com.tianque.baseInfo.dangerousGoodsPractitioner.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.vo.PageInfo;

public interface DangerousGoodsPractitionerService extends
		BaseInfoPopulationTemplateService {

	public DangerousGoodsPractitioner addDangerousGoodsPractitioner(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	/**
	 * 
	 * @Title: addDangerousGoodsPractitionerForMobile
	 * @Description: TODO提取新方法 增加危化品从业单位人员
	 * @param @param dangerousGoodsPractitioner
	 * @param @return 设定文件
	 * @return DangerousGoodsPractitioner 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午02:55:47
	 */
	public DangerousGoodsPractitioner addDangerousGoodsPractitionerForMobile(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	public DangerousGoodsPractitioner updateDangerousGoodsPractitioner(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerBaseInfo(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerBusiness(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	public boolean deleteDangerousGoodsPractitioner(Long id);

	public DangerousGoodsPractitioner getSimpleDangerousGoodsPractitionerById(
			Long id);

	public DangerousGoodsPractitioner getDangerousGoodsPractitionerById(Long id);

	public PageInfo<DangerousGoodsPractitioner> findDangerousGoodsPractitionersForPageByOrgId(
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerByName(
			String name, Long id, DangerousGoodsPractitioner domain);

	public DangerousGoodsPractitioner getDangerousGoodsPractitionerByIdCardNo(
			String idCardNo, Long orgId);

	public List<DangerousGoodsPractitioner> updateDeathByIds(
			List<Long> populationIds, Boolean death);

	public List<Long> deleteDangerousGoodsPractitionerByIds(List<Long> personIds);

	public List<Long> deleteDangerousGoodsPractitionerByIds(Long[] personIds);

	public boolean existDangerousGoodsPractitioner(Long orgId, String idCardNo,
			Long hopeId);

	public DangerousGoodsPractitioner findDangerousGoodsPractitionerByIdCardNoAndOrgId(
			String idCardNo, Long orgId);

	DangerousGoodsPractitioner hasDuplicateDangerousGoodsPractitioner(
			Long orgId, String idCardNo);

	public DangerousGoodsPractitioner addDangerousGoodsPractitionerBaseInfo(
			DangerousGoodsPractitioner population);

	public void moveTempByIds(String[] moveids, Long targetOrgId);

	/**
	 * 
	 * @Title: DangerousGoodsPractitionerForMobile
	 * @Description: TODO修改危化品从业人员信息 手机端
	 * @param @param population
	 * @param @return 设定文件
	 * @return DangerousGoodsPractitioner 返回类型
	 * @author wanggz
	 * @date 2014-9-15 下午06:09:11
	 */
	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerForMobile(
			DangerousGoodsPractitioner population);

}
