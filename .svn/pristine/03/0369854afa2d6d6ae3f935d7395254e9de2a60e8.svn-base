package com.tianque.baseInfo.base.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.core.base.BaseDao;
import com.tianque.core.base.BaseDomain;

public interface BaseInfoPopulationBaseDao<T extends BaseDomain, SearchVo extends BaseDomain>
		extends BaseDao<T, SearchVo> {
	void updateLogOutByMap(Map<String, Object> map);

	public Map<String, Object> getIdCardNoAndOrgIdByPopulationTableAndId(
			String type, Long id);

	public Map<String, Object> getActualPopulationLogoutByIdCardNoAndOrgId(
			Map<String, Object> map);

	public T updateBaseInfo(T entity);

	public T updateBusiness(T entity);
	
	public T updateBusinessShard(T entity);

	public void updateDeathAndLogoutDeatilById(Long id, Boolean death,
			LogoutDetail logoutDetail);

	public T getByIdCard(List<String> idCardNoList, Long orgId);

	public T getByOrgIdAndIdCardNo(Long orgId, String idCardNo);

	/**
	 * 转移的数据到其他组织机构
	 * 
	 * @param id
	 * @param targetOrgId
	 * @param orgCode
	 * @param t
	 *            TODO
	 */
	public void updateData(String id, Long targetOrgId, String orgCode, T t);

	public Long getIdByBaseinfoIdAndOrgId(Long baseInfoId, Long orgId);
	
	public Long getIdByBaseinfoIdAndOrgId(Long baseInfoId, Long orgId,
			String shardCode);

	/**
	 * 业务模块，查询服务成员的条数，用于列表显示：“有无服务成员”
	 * 
	 * @param populationType
	 * @param objectId
	 * @return
	 */
	public Long countServiceMemberHasObject(String populationType, Long objectId);

	/**
	 * 业务模块，查询服务成员的最新服务时间
	 * 
	 * @param populationType
	 * @param objectId
	 * @return
	 */
	public Date findServiceLastRecordTime(String populationType, Long objectId);
}
