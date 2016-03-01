package com.tianque.plugin.dataManage.base.dao;

import java.util.Map;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.base.vo.SearchVo;

public interface AbstractDataManageDao<T extends BaseDomain> {
	/**
	 * 分页查询，列表显示
	 * 
	 * @return PageInfo
	 */
	public PageInfo findTemps(Class t, SearchVo searchVo, Integer page,
			Integer rows);

	/**
	 * 根据id获取数据
	 * 
	 * @param T
	 * @param id
	 * @return
	 */
	public T getTempById(Class t, Long id);

	/**
	 * 修改人员基础信息
	 * 
	 * @return T
	 */
	public void updateTempBase(T temp);

	/**
	 * 修改人员业务信息
	 * 
	 * @return T
	 */
	public void updateTempBusiness(T temp);

	/**
	 * 修改场所信息
	 * 
	 * @return T
	 */
	public void updateLocationTempBase(T temp);

	/**
	 * 修改部件信息
	 * 
	 * @return T
	 */
	public void updateDustbinTempBase(T temp);

	public void updateBuilddatasTempBase(T temp);

	/** 修改认领明细 */
	public void updateClaimDetail(T temp, ClaimDetail claimDetail);

	/** 新增Temp */
	public Long addTemp(T temp);

	/**
	 * 批量分布认领数据
	 * 
	 * @param table
	 * @param tempIds
	 * @param targertOrgId
	 * @return
	 */
	public Integer stepClaimTempByIds(String table, Long[] tempIds,
			Long targertOrgId, String orgCode);

	/**
	 * 删除数据
	 * 
	 * @param t
	 * @param analyzePopulationIds
	 */
	public void deleteTempById(Class t, Long id);

	/**
	 * 通过Orgid和name来查询场所
	 * 
	 * @param placeName
	 * @param id
	 * @return
	 */
	public T getLocationByNameAndOrgId(T temp);

	public Map<String, Object> hasDuplicate(Map<String, Object> queryMap);

	/**
	 * 根据表名和id数组 撤销认领
	 * 
	 * @param table
	 * @param ids
	 * @param detail
	 */
	public void unDoClaimDetailByTableAndIds(String table, Long[] ids,
			ClaimDetail detail);

	public void updateTheSamePopulationPassiveClaim(T temp,
			ClaimDetail passiveClaim);

	/**
	 * 认领成功修改temp表的orgId
	 * 
	 * @param map
	 */
	public void updateTempDataOrgId(Map map);
}
