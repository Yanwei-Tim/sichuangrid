package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.List;

import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;

public interface ServiceRecordRelyMemberDao {
	/**
	 * 新增记录所属人关联
	 * 
	 * @param ServiceRecordRelyMember
	 * @return Long
	 */
	public Long addServiceRecordRelyMember(
			ServiceRecordRelyMember serviceRecordRelyMember);

	/**
	 * 删除与服务所属人的关联关系
	 * 
	 * @param recordId
	 * @return int 删除条数
	 */
	public int deleteServiceRecordRelyMembers(Long recordId);

	/**
	 * 删除与服务所属人的关联关系
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceRecordRelyMembersById(Long id);

	/**
	 * 根据记录id查找该记录的所有记录所属人
	 * 
	 * @param RecordId
	 * @return List<ServiceRecordRelyMember>
	 */
	public List<ServiceRecordRelyMember> findServiceMembers(Long recordId);

	/**
	 * 查找团队成员下记录
	 */
	public List<Integer> findServiceRecordsInServiceTeam(Long memberId);

	/** 根据记录id和成员id将全部重复成员的id修改为标准id */
	public void updateRepeatData(ServiceRecordRelyMember serviceRecordRelyMember);

	/** 根据记录id和成员id将全部重复成员关联关系列表搜出来 */
	public List<ServiceRecordRelyMember> getServiceRecordRelyMembersByMemberIdAndRecordId(
			ServiceRecordRelyMember serviceRecordRelyMember);
}
