package com.tianque.baseInfo.actualCompany.service;

import java.util.List;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;

/**
 * 实有单位业务处理
 */
public interface ActualCompanyService {
	/**
	 * 新增实有单位
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
	 * 删除实有单位根据idList
	 * 
	 * @param idList0
	 */
	public void deleteActualCompany(List<Long> idList);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteActualCompanyByIds(Long[] idList);

	/**
	 * 修改实有单位
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
	public PageInfo<ActualCompany> findActualCompanyForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sortField, String sord, Boolean logOut);

	/**
	 * 判断实有单位是否重复，根据单位名称
	 * 
	 * @param orgId
	 * @param computerName
	 * @param exceptedId
	 * @return boolean
	 */
	public boolean hasDuplicateActualCompany(Long orgId, String computerName, Long exceptedId);

	/**
	 * 修改实有单位，根据idList
	 * 
	 * @param idList
	 * @param isEmphasis
	 * @return
	 */
	public List<ActualCompany> updateEmphasisByIdList(List<Long> idList, ActualCompany actualCompany);

	/**
	 * 根据companyName获取实有单位对象
	 * 
	 * @param companyName
	 * @param orgId
	 * @return ActualCompany
	 */
	public ActualCompany getActualCompanyByCompanyName(String companyName, Long orgId);

	public List<ActualCompany> countActualCompanyByOrgId(Long orgId);

	public PageInfo<ActualCompany> searchActualUnitforGisByorgIdAndQueryStr(String queryStr,
			Long orgId, Integer page, Integer rows, String sidx, String sord);

	public ActualCompany updateactualCompanyInfoForGis(Long Id, GisInfo gisInfo);

	public ActualCompany unBindActualCompanyOnMap(Long houseId);

	public ActualCompany getActualUnitDatialInfoByUnitId(Long unitId, Long orgId);

	public PageInfo<ActualCompany> searchKeyUnitListSearchByOrgId(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public List<ActualCompany> findAllBindingActualUnitByOrgInternalCodeForGis(final Long orgId);

	public int countBindingActualUnitBybuildingIdForGis(final Long buildingId);

	public List<ActualCompany> searchAllRoundCompany(GisInfo minOption, GisInfo maxOption);

	/**
	 * 查找和楼宇绑定的实有单位
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
	 * 查找未绑定的实有单位
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return
	 */
	PageInfo<ActualCompany> findUnBoundedByOrgId(Long orgId, Integer pageNum, Integer pageSize,
			String sidx, String sord, String queryStr);

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

	List<ActualCompany> findActualCompanyListByBuildingId(Long builddatasId);

	/**
	 * 数据管理中新增方法
	 * 
	 * @param orgId
	 * @param name
	 * @return
	 */
	public ActualCompany hasDuplicateActualCompany(Long orgId, String name);

	public PageInfo findActualCompanysPractitioners(ActualCompany location,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo findActualCompanysAddPractitionersForList(
			ActualCompany location, Integer page, Integer rows, String sidx,
			String sord);

	public void saveActualCompanysPractitioners(Long id, String locationIds);

	public String getActualCompanysAddPractitionersById(Long id);

	public void delPersonForPractitionerst(Long id, String locationIds);
}
