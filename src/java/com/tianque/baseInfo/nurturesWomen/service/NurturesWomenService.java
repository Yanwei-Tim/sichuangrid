package com.tianque.baseInfo.nurturesWomen.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchNurturesWomenVo;

public interface NurturesWomenService extends BaseInfoPopulationTemplateService {

	public PageInfo<NurturesWomen> findNurturesWomenForPageByOrgId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	public NurturesWomen getNurturesWomenById(Long id);

	public NurturesWomen addNurturesWomen(NurturesWomen nurturesWomen);

	public boolean hasDuplicateNurturesWomen(Long orgId, String idCardNo,
			Long exceptedId);

	public NurturesWomen hasDuplicateNurturesWomen(Long orgId, String idCardNo);

	public NurturesWomen updateNurturesWomen(NurturesWomen nurturesWomen);

	/**
	 * 
	 * @Title: updateNurturesWomeForMobile
	 * @Description: TODO为手机端增加方法
	 * @param @param nurturesWomen
	 * @param @return 设定文件
	 * @return NurturesWomen 返回类型
	 * @author wanggz
	 * @date 2014-5-19 下午06:32:36
	 */
	public NurturesWomen updateNurturesWomeForMobile(NurturesWomen nurturesWomen);

	public NurturesWomen updateNurturesWomenBaseInfo(NurturesWomen nurturesWomen);

	public NurturesWomen updateNurturesWomenBusiness(NurturesWomen nurturesWomen);

	public void deleteNurturesWomenByIdList(List<Long> idList);

	public void deleteNurturesWomenByIds(Long[] ids);

	public List<NurturesWomen> updateDeathOfNurturesWomenByIdList(
			List<Long> idList, Boolean death);

	public PageInfo<NurturesWomen> searchNurturesWomen(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo);

	public List<NurturesWomen> searchAllNurturesWomen(String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo);

	public NurturesWomen getNurturesWomenByIdCardNoAndOrganizationId(
			String idCardNo, Long orgId);

	public String[][] getExportPopertyArray();

	public NurturesWomen addNurturesWomenBaseInfo(NurturesWomen population);

	public NurturesWomen addNurturesWomenForJob(Countrymen countrymen,
			Long sourcesState);

	public Integer getCount(SearchNurturesWomenVo searchNurturesWomenVo);

	public Long getNurturesWomenByBaseinfoIdAndOrgId(Long baseinfoId, Long orgId);
}
