package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.UsedInfo;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;

public interface ServiceRecordDao {

	/**
	 * 新增服务记录信息
	 * 
	 * @param ServiceRecord
	 *            服务记录domain
	 * @return Long
	 */
	public Long addServiceRecord(ServiceRecord serviceRecord);

	/**
	 * 返回单个服务记录
	 * 
	 * @param id
	 * @return ServiceRecordVo 服务记录vo
	 */
	public ServiceRecordVo getServiceRecordById(Long id);

	/**
	 * 获取服务记录页面
	 * 
	 * @param ServiceRecordVo
	 * @param pageNum
	 * @param pageSize
	 * @return PageInfo
	 */
	public PageInfo<ServiceRecordVo> findServiceRecords(
			ServiceRecordVo serviceRecordVo, Integer pageNum, Integer pageSize);

	/**
	 * 获取服务记录数量
	 */
	public Integer countServiceRecords(ServiceRecordVo serviceRecordVo);

	/**
	 * 更新服务记录
	 * 
	 * @param serviceRecord
	 * @return ServiceRecordVo
	 */
	public ServiceRecordVo updateServiceRecord(ServiceRecord serviceRecord);

	/**
	 * 删除服务记录
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceRecord(Long id);

	/**
	 * 获取最早时间
	 */
	public Date getMinTime();

	/**
	 * 查询团队下记录数
	 */
	public Integer countServiceRecordsForTeam(Long id);

	/**
	 * 查询成员下记录数
	 */
	public Integer countServiceRecordsForTeamMember(Long id);

	/**
	 * 获得所有记录
	 */
	public List<ServiceRecordVo> getServiceRecordObjects(
			ServiceRecordVo serviceRecordVo);

	/**
	 * 根据记录ID查找所在团队
	 */
	public Integer findServiceTeamInServiceRecords(Long id);

	/** 重复成员合并后更新服务记录的记录所属人字段 */
	public void updateMemberInRecord(ServiceRecord serviceRecord);

	public Integer getCount(ServiceRecordVo serviceRecordVo);

	/**
	 * 数据转移时，根据类型和id修改网格到目标网格
	 * */
	public void updateServiceRecordsOrg(Long orgId, String orgCode,
			String objectType, Long objectId);

	/**
	 * 数据转移时，修改原纪录的网格和objectid与目标网格的记录合并
	 * */
	public void updateServiceRecordsOrgAndObejctId(Long orgId, String orgCode,
			String objectType, Long oldObjectId);

	public void updateServiceRecordRelyObject(String objectType,
			Long oldObjectId, Long newObjectId);

	/**
	 * 数据转移时，修改原纪录的网格和objectid与目标网格的记录合并
	 * */
	public void updateServiceRecordsOrgAndObejctId(Long orgId, String orgCode,
			String objectType, Long oldObjectId, String newObjectType);

	public void updateServiceRecordRelyObject(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType);

	public void deleteServiceRecordHasObject(Long objectId, String objectType);

	public List<UsedInfo> getSpecialCrowdCountForUsedInfo(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId);

}
