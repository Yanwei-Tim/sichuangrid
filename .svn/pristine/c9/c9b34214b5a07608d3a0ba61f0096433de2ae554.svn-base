package com.tianque.baseInfo.floatingPopulation.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchFloatingPopulationVo;

public interface FloatingPopulationService extends
		BaseInfoPopulationTemplateService {

	public FloatingPopulation findFloatingPopulationByCardNoAndOrgId(
			String idCardNo, Long orgId);

	public FloatingPopulation addFloatingPopulation(
			FloatingPopulation floatingPopulation);

	/**
	 * 
	 * @Title: addFloatingPopulationForMobile
	 * @Description: TODO提取新方法。为手机端增加一个添加流动人口的方法
	 * @param @param floatingPopulation
	 * @param @return 设定文件
	 * @return FloatingPopulation 返回类型
	 * @author wanggz
	 * @date 2014-6-18 下午03:15:46
	 */
	public FloatingPopulation addFloatingPopulationForMobile(
			FloatingPopulation floatingPopulation);

	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long logOut, Boolean isDeath);

	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgIdDefaultList(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long logOut, Boolean isDeath);

	public PageInfo<FloatingPopulation> searchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public PageInfo<FloatingPopulation> fastSearchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public List<FloatingPopulation> searchAllFloatingPopulations(String sidx,
			String sord, SearchFloatingPopulationVo searchFloatingPopulationVo);

	public FloatingPopulation updateFloatingPopulation(
			FloatingPopulation floatingPopulation);

	public FloatingPopulation getFloatingPopulationById(Long id);

	public void deleteFloatingPopulationById(Long id);

	public void deleteFloatingPopulationByIds(Long[] ids);

	public void deleteFloatingPopulationsByIdList(List<Long> idList);

	public List<FloatingPopulation> updateDeathOfFloatingPopulationByIdList(
			List<Long> idList, Boolean death);

	public List<Long> updateLogOutOfFloatingPopulationByIds(
			LogoutDetail logoutDetail, String populationType, Long[] ids);

	public boolean hasDuplicateFloatingPopulation(Long orgId, String idCardNo,
			Long exceptedId);

	public FloatingPopulation updateFloatingPopulationBusinessInfo(
			FloatingPopulation population);

	public FloatingPopulation updateFloatingPopulationBaseInfo(
			FloatingPopulation population);

	public Boolean updateActualPopulationToHasHouseState(
			FloatingPopulation floatingPopulation);

	/**
	 * 获得 大于60岁流动人口
	 * 
	 * @return
	 */
	public List<FloatingPopulation> findFloatingPopulationsWhenIsOld(
			int pageSize, Organization org);

	public Integer countFloatingPopulationsWhenIsOld();

	/**
	 * 获得 15-49岁之间的妇女
	 * 
	 * @return
	 */
	public List<FloatingPopulation> findFloatingPopulationsWhenIsNurtuesWomen(
			int pageSize, Organization org);

	public Integer countFloatingPopulationsWhenIsNurtuesWomen(String shardCode);

	/**
	 * 根据orgid 和idCarNo查找流动人口
	 * 
	 * @param organizationId
	 * @param idCardNo
	 * @return
	 */
	public FloatingPopulation getFloatingPopulationByIdCardNoAndOrgId(
			Long organizationId, String idCardNo);

	/**
	 * 根据业务人员id 和 类型查找流口
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public FloatingPopulation getFloatingPopulationByIdAndBusinessType(Long id,
			String type);

	public FloatingPopulation addFloatingPopulationBaseInfo(
			FloatingPopulation population);

	public void moveTempByIds(String[] moveids, Long targetOrgId);

	/**
	 * 转为户籍人口
	 * 
	 * @param id
	 */
	public void toHouseholdStaff(Long id);

	/**
	 * 转为户籍人口
	 * 
	 * @param id
	 */
	public void toHouseholdStaffByIds(List<Long> ids);

	public Countrymen copyAndUpdate(FloatingPopulation floatingPopulation);

	/**
	 * 根据基础信息ID查找流动人口
	 * 
	 * @param baseInfoId
	 * @param orgId
	 * @return
	 */
	public FloatingPopulation getFloatingPopulationByBaseInfoId(
			Long baseInfoId, Long orgId);

	/**
	 * 根据基础信息ID查找流动人口
	 * 
	 * @param baseInfoId
	 * @return
	 */
	public List<FloatingPopulation> getFloatingPopulationByBaseInfoId(
			Long baseInfoId);

	public Integer getCount(
			SearchFloatingPopulationVo searchFloatingPopulationVo);

	public boolean getActualPopulationHasTypes(Long id);

	/**
	 * 获得到期的流动人口
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<FloatingPopulation> findFloatingPopulationByExpectedDatedue(
			int pageNum, int pageSize);

	public Integer countFloatingPopulationByExpectedDatedue();

	public FloatingPopulation updateIsRemindByid(Long id);

	public List<FloatingPopulation> findFloatingPopulationByIds(List<Long> ids);

	public List<Organization> findOrgByAddress(Long addressId);

}