package com.tianque.baseInfo.actualCompany.dao;

/**
 * 实有单位ActualCompany
 */
import java.util.List;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.domain.ActualCompanyPractitioners;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;

public interface ActualCompanyDao {
	/**
	 * 新增
	 * 
	 * @param actualCompany
	 * @return actualCompany
	 */
	public ActualCompany addActualCompany(ActualCompany actualCompany);

	/**
	 * 根据id获取实有单位对象
	 * 
	 * @param id
	 * @return actualCompany
	 */
	public ActualCompany getActualCompanyById(Long id);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteActualCompany(Long id);

	/**
	 * 修改
	 * 
	 * @param actualCompany
	 * @return actualCompany
	 */
	public ActualCompany updateActualCompany(ActualCompany actualCompany);

	public ActualCompany updateKeyCrucialPosition(ActualCompany actualCompany);

	public ActualCompany updatePreventionFacilities(ActualCompany actualCompany);

	/**
	 * 查找数据用于显示
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @param logOut
	 * @return PageInfo<ActualCompany>
	 */
	public PageInfo<ActualCompany> findActualCompanyForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut);

	/**
	 * 根据companyName获取实有单位对象
	 * 
	 * @param companyName
	 * @param orgId
	 * @return ActualCompany
	 */
	public ActualCompany getActualCompanyByCompanyNameAndOrganizationId(String companyName,
			Long orgId);

	public List<ActualCompany> countActualCompanyByorgInternalCode(String orgInternalCode);

	public PageInfo<ActualCompany> searchActualUnitforGisByorgIdAndQueryStr(String queryStr,
			String orgInternalCode, Integer page, Integer rows, String sidx, String sord);

	public ActualCompany updateactualCompanyInfoForGis(ActualCompany unitIdAndGisInfo);

	public ActualCompany unBindActualCompanyOnMap(Long unitId);

	public ActualCompany getActualUnitDatialInfoByUnitId(Long id, Long orgId);

	public PageInfo<ActualCompany> searchKeyUnitListSearchByOrgInternalCode(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord);

	public List<ActualCompany> findAllBindingActualUnitByOrgInternalCodeForGis(
			final String orgInternalCode);

	public int countBindingActualUnitBybuildingIdForGis(final Long buildingId);

	public List<ActualCompany> searchAllRoundCompany(GisInfo minOption, GisInfo maxOption);

	/**
	 * 根据楼宇id查找绑定的实有房屋
	 * 
	 * @param builddatasId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ActualCompany> findActualCompanyByBuilddatasIdForPage(Long builddatasId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据组织机构id查询
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return
	 */
	PageInfo<ActualCompany> findunBoundActualCompanyListForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord, String queryStr);

	/**
	 * 绑定实有单位
	 * 
	 * @param houseIds
	 * @param builddatasId
	 */
	void boundActualCompany(List<Long> houseIds, Long builddatasId);

	/**
	 * 解除绑定实有单位
	 * 
	 * @param houseIds
	 * @param builddatasId
	 */
	void unboundActualCompany(List<Long> houseIds);

	List<LayoutTagVo> searchAllActualCompanyName4LayoutTag(String orgInternalCode, Long builddatasId);

	List<ActualCompany> findActualCompanyListByBuildingId(Long builddatasId);

	String getActualCompanyNameById(Long id);

	public PageInfo findActualCompanysPractitioners(ActualCompany location,
			Integer pageNum, Integer pageSize, String sortField, String sord);

	public PageInfo findActualCompanysAddPractitionersForList(
			ActualCompany location, Integer pageNum, Integer pageSize,
			String sortField, String sord);

	public void deleteActualCompanyPractitioners(Long id);

	public void saveActualCompanyPractitioners(ActualCompanyPractitioners acp);

	public List<ActualCompanyPractitioners> queryActualCompanysAddPractitionersById(
			Long id);

	public void delPersonForPractitionerst(ActualCompanyPractitioners acp);
}
