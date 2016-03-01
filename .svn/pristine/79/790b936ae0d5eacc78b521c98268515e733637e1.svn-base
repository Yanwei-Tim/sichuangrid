package com.tianque.baseInfo.companyPlace.service;

import java.util.List;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;

public interface CompanyPlaceBusinessService {
	public void addCompanyPlaceBusiness(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey);

	/**
	 * 为手机端 新增业务和附件
	 * 
	 * @Title: addCompanyPlaceBusinessForMobile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param vo
	 * @param @param companyPlaceId
	 * @param @param modulKey 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-7-19 上午02:20:57
	 */
	public void addCompanyPlaceBusinessForMobile(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey);

	public void updateCompanyPlaceBusiness(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey);

	/**
	 * 
	 * @Title: updateCompanyPlaceBusinessForMobile
	 * @Description: TODO为手机端修改单位场所业务信息提供方法（不删除已有附件）
	 * @param @param vo
	 * @param @param companyPlaceId
	 * @param @param modulKey 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-7-9 上午09:45:01
	 */
	public void updateCompanyPlaceBusinessForMobile(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey);

	public CompanyPlaceBusiness getCompanyPlaceBusiness(Long id);

	public CompanyPlaceBusinessVO queryCompanyPlaceBusinessVOByCompanyPlaceIdForList(
			Long companyPlaceId);

	/**
	 * 根据场所编号删除对应的业务
	 * 
	 * @param companyPlaceId
	 */
	public void deleteCompanyPlaceBusinessByCompanyPlaceID(Long companyPlaceId);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	public void deleteCompanyPlaceBusinessByID(Long id);

	/**
	 * 根据场所ID和所属业务、类型进行删除
	 * 
	 * @param companyPlaceId
	 * @param isKeyType
	 * @param modulKey
	 */
	public void deleteCompanyPlaceBusinessByIdAndIsKeyType(Long companyPlaceId,
			Long isKeyType, String modulKey);

	public List<CompanyPlaceBusiness> queryCompanyPlaceBusinessByCompanyPlaceIdForList(
			Long compnayPlaceId);
}
