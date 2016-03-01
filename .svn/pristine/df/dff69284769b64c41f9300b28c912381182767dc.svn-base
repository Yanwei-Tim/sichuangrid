package com.tianque.plugin.serviceTeam.serviceRecord.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.UsedInfo;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;

public interface ServiceRecordService {

	/**
	 * 新增服务记录
	 * 
	 * @param ServiceRecord
	 *            服务记录domain String[]
	 * @return ServiceRecordVo 服务记录vo
	 */
	public ServiceRecordVo addServiceRecord(ServiceRecord serviceRecord);

	/**
	 * 获取服务记录页面
	 * 
	 * @param ServiceRecordVo
	 * @param objectId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	public PageInfo<ServiceRecordVo> findServiceRecords(
			ServiceRecordVo serviceRecordVo, Long[] objectId,
			String objectType, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 根据id获取单个服务记录信息
	 * 
	 * @param id
	 * @return ServiceRecordVo @
	 */
	public ServiceRecordVo getServiceRecordById(Long id);

	/**
	 * 根据id获取对象依赖关系队列
	 * 
	 * @param id
	 * @return List<ServiceRecordRelyObject> @
	 */
	public List<ServiceRecordRelyObject> findServiceObjects(Long id);

	/**
	 * 根据id获取单个附件
	 * 
	 * @param id
	 * @return ServiceRecordAttachment @
	 */
	public ServiceRecordAttachment getServiceRecordAttachmentById(Long id);

	/**
	 * 根据服务id获取所有该记录的附件
	 * 
	 * @param id
	 * @return List<ServiceRecordAttachment> @
	 */
	public List<ServiceRecordAttachment> findServiceRecordAttachments(Long id);

	/**
	 * 修改服务记录信息
	 * 
	 * @param ServiceRecord
	 *            objectNames
	 * @return ServiceRecordVo @
	 */
	public ServiceRecordVo updateServiceRecord(ServiceRecord serviceRecord,Long serviceObjectId);

	/**
	 * 根据ID删除服务记录
	 * 
	 * @param deleteIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteServiceRecords(Long[] deleteIds);

	/**
	 * 根据ID删除附件
	 * 
	 * @param deleteIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteAttachmentByFileId(Long deleteId);

	/** 获取年份列表 */
	public List getDisplayYear();

	/**
	 * 根据记录id查找该记录的所有记录所属人
	 * 
	 * @param RecordId
	 * @return List<ServiceRecordRelyMember>
	 */
	public List<ServiceRecordRelyMember> findServiceMembers(Long recordId);

	/**
	 * 获取需要导出的服务记录列数据
	 */
	public List<ServiceRecordVo> getNeedExportDatas(boolean pageOnly,
			ServiceRecordVo serviceRecordVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 查询团队下记录数
	 */
	public Integer countServiceRecordsForTeam(Long id);

	/**
	 * 查询成员下记录数
	 */
	public Integer countServiceRecordsForTeamMember(Long id);

	public Integer getCount(ServiceRecordVo serviceRecordVo);

	/**
	 * 数据转移目标网格不存在相同业务信息时，根据类型和id修改网格到目标网格
	 * */
	public void updateServiceRecordsOrg(Long orgId, String orgCode,
			String objectType, Long objectId);

	/**
	 * 数据转移目标网格存在相同业务信息时，修改服务纪录的网格和objectid与目标网格的记录合并
	 * */
	public void updateServiceRecordByTransfer(Long orgId, String orgCode,
			String objectType, Long oldObjectId, Long newObjectId);

	/**
	 * 数据转移目标网格存在相同业务信息时，修改服务纪录的网格和objectid与目标网格的记录合并(用在转移时社区矫正和刑释解救)
	 * */
	public void updateServiceRecordByTransfer(Long orgId, String orgCode,
			String objectType, Long oldObjectId, Long newObjectId,
			String newObjectType);

	public void deleteServiceRecordHasObject(Long objectId, String objectType);

	/**
	 * 获取网格化服务管理信息系统使用情况的走访服务信息
	 * 
	 * @param beforDayStartDate
	 * @param beforDayEndDate
	 * @param beforWeekMonday
	 * @param beforWeekSunday
	 * @param monthStartDate
	 * @param monthEndDate
	 * @param orgId
	 * @param orgTypeId
	 * @return
	 */
	public List<UsedInfo> getSpecialCrowdCountForUsedInfo(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId);
}
