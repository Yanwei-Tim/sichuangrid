package com.tianque.baseInfo.companyPlace.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;

public interface CompanyPlaceBaseSevice {
	/***
	 * 分页查询 *param : CompanyPlaceVo
	 * **/
	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlaceVo companyPlaceVo, String modulKey,
			DefaultSortAndPage defaultSortAndPage);

	/**
	 * 删除的方法
	 * **/
	public void delteCompanyPlaceBaseByIds(String baseIds, String modulKey);

	/** 新增 把baseid返回回去 */
	public CompanyPlace addCompanyPlaceBase(CompanyPlace companyPlace,
			String modulKey, Long id);

	public CompanyPlace addCompanyPlaceBaseForMobile(CompanyPlace companyPlace,
			String modulKey, Long id);

	/** 根据baseid查询 基础信息和场所信息 */
	public CompanyPlace getCompanyPlaceInfoByCid(Long cid);

	/** 根据id修改基础信息 */
	public void updateCompanyPlaceBaseById(CompanyPlace companyPlace);

	/*** 根据id修改基础信息和场所信息 */
	public void updateCompanyPalceForAll(CompanyPlace companyPlace,
			String modulKey);

	/**
	 * 设置关注状态
	 * 
	 * @param map
	 * @return
	 */
	public void updateCompanyPlaceIsEmphasis(List<Long> ids,
			CompanyPlace companyPlace, String modulKey);

	/***
	 * 查询列表，主要提供给导出
	 * 
	 * * @param vo org.id , modulKey
	 * 
	 * @return
	 */
	public List<CompanyPlace> queryCompanyPlaceForList(
			CompanyPlaceVo companyPlaceVo, String modulKey);

	/** 数据转移校验 **/
	public void transferValidate(Long id, Long toOrgId, Long orgId,
			String type, List<ErrorMessageVo> errorList, List<Long> errorIdList);

	/** 根据名称和组织结构编码获取单位场所ID */
	public String getCompanyPlaceBaseIdByNameAndOrgid(CompanyPlace companyPlace);

	/*** 根据场所Id（cid）修改组织机构 **/
	public void updateCompanyPlaceBaseOrgById(CompanyPlace companyPlace);

	/**
	 * 根据名称和Orgid模糊匹配
	 * 
	 * @param param
	 *            :name , org.id
	 * @return
	 */

	public List<CompanyPlaceBase> queryCompanyplaceInfoLikeNameForList(
			CompanyPlace companyPlace);

	/**
	 * 根据cid删除base表中记录
	 * 
	 * @param ids
	 */
	public void batchDeleteCompanyPlaceBaseByIds(List<Long> ids);

	/**
	 * 检查同一个网格是否存在该单位
	 * 
	 * @param companyPlace
	 *            name , orgid ,id可选
	 * @return
	 */
	public boolean checkCompanyPlaceHas(CompanyPlace companyPlace);

	public CompanyPlace hasDuplicateCompanyPlace(Long orgId, String placeName,
			Long typeId);

	/** 根据Cid删除base **/
	public void deleteCompanyPlaceBaseById(Long id);

	/**
	 * 
	 * @Title: statisticsBaseInfo
	 * @Description: TODO领导视图 统计
	 * @param @param orgId
	 * @param @param tableName
	 * @param @param isGrid
	 * @param @return 设定文件
	 * @return List<StatisticsBaseInfoAddCountVo> 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午02:55:48
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfo(Long orgId,
			String moduleKey);

	/**
	 * 
	 * @Title: statisticsSummary
	 * @Description: TODO领导视图 线形图
	 * @param @param orgId
	 * @param @param tableName
	 * @param @return 设定文件
	 * @return List<StatisticsBaseInfoAddCountVo> 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午03:15:23
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsSummary(Long orgId,
			String moduleKey);
}
