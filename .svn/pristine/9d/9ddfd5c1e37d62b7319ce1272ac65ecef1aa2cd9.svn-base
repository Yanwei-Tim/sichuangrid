package com.tianque.baseInfo.goodSamaritan.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.core.vo.PageInfo;

public interface GoodSamaritanService extends BaseInfoPopulationTemplateService {
	PageInfo<GoodSamaritan> findGoodSamaritansForPageByOrgId(
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	Boolean existGoodSamaritan(Long organizationId, String idCardNo, Long id);

	GoodSamaritan updateGoodSamaritanBaseInfo(GoodSamaritan population);

	GoodSamaritan addGoodSamaritanBaseInfo(GoodSamaritan population);

	GoodSamaritan updateGoodSamaritanBusiness(GoodSamaritan population);

	List<Long> deleteGoodSamaritanByIds(List<Long> asList);

	GoodSamaritan getSimpleGoodSamaritanById(Long id);

	GoodSamaritan getGoodSamaritanById(Long id);

	List<GoodSamaritan> updateDeathByIds(List<Long> asList, boolean death);

	GoodSamaritan updateGoodSamaritan(GoodSamaritan population);

	GoodSamaritan addGoodSamaritan(GoodSamaritan population);

	GoodSamaritan getGoodSamaritanByIdCardNo(String idCardNo, Long id);

	GoodSamaritan updateGoodSamaritanByName(String idCardNo, Long id,
			GoodSamaritan domain);

	/** 数据认领判断是否有重复数据 */
	public GoodSamaritan hasDuplicateGoodSamaritan(Long orgId, String idCardNo);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteGoodSamaritanByIds(Long[] ids);
}
