package com.tianque.baseInfo.handicapped.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHandicappedVo;

public interface HandicappedDao extends
		BaseInfoPopulationBaseDao<Handicapped, SearchHandicappedVo> {

	public PageInfo<Handicapped> findHandicappedsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis);

	public Handicapped getHandicappedByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId);

	/**
	 * 收索残疾人信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchHandicappedVo
	 * @return
	 */
	public PageInfo<Handicapped> searchHandicappeds(Integer pageNum,
			Integer pageSize, SearchHandicappedVo searchHandicappedVo);

	/**
	 * 
	 * @Title: searchHandicappedsForMobile
	 * @Description: TODO为手机端增加查询残疾人的方法（去重）
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param searchHandicappedVo
	 * @param @return 设定文件
	 * @return PageInfo<Handicapped> 返回类型
	 * @author wanggz
	 * @date 2014-9-23 上午10:42:34
	 */
	public PageInfo<Handicapped> searchHandicappedsForMobile(Integer pageNum,
			Integer pageSize, SearchHandicappedVo searchHandicappedVo);

	/**
	 * 根据map条件更新残疾人的注销信息
	 * 
	 * @param id
	 *            ，要更新的残疾人的id
	 * @param isEmphasis
	 *            ，注销的状态
	 */
	public void updateEmphasiseById(Long id, Long isEmphasis);

	public List<Handicapped> searchAllHandicappeds(
			SearchHandicappedVo searchHandicappedVo);

	public void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	public Integer getCount(SearchHandicappedVo searchHandicappedVo);

	public void deleteDisbilityType(
			HandicappedSdisabilityType handicappedSdisabilityType);

	public void saveDisabilityType(
			HandicappedSdisabilityType handicappedSdisabilityType);

	public List<HandicappedSdisabilityType> queryDisabilityLevelById(
			HandicappedSdisabilityType handicappedSdisabilityType);

	public void saveHandicappedSdisabilityTypeToReal(
			HandicappedSdisabilityType handicappedSdisabilityType);
	
	public void updateTableUpdateDateById(Long id);
}