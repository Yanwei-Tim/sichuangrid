package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;

@Repository("ServiceRecordRelyMemberDao")
public class ServiceRecordRelyMemberDaoImpl extends AbstractBaseDao implements
		ServiceRecordRelyMemberDao {
	@Override
	public Long addServiceRecordRelyMember(ServiceRecordRelyMember serviceRecordRelyMember) {
		return (Long) getSqlMapClientTemplate().insert(
				"serviceRecordRelyMember.addServiceRecordRelyMember", serviceRecordRelyMember);

	}

	@Override
	public int deleteServiceRecordRelyMembers(Long recordId) {
		return getSqlMapClientTemplate().delete(
				"serviceRecordRelyMember.deleteServiceRecordRelyMembers", recordId);
	}

	@Override
	public List<ServiceRecordRelyMember> findServiceMembers(Long recordId) {

		return getSqlMapClientTemplate().queryForList("serviceRecordRelyMember.findServiceMembers",
				recordId);
	}

	@Override
	public List<Integer> findServiceRecordsInServiceTeam(Long id) {
		return (List) getSqlMapClientTemplate().queryForList(
				"serviceRecordRelyMember.findServiceRecordsInServiceTeam", id);
	}

	@Override
	public int deleteServiceRecordRelyMembersById(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceRecordRelyMember.deleteServiceRecordRelyMembersById", id);
	}

	@Override
	public void updateRepeatData(ServiceRecordRelyMember serviceRecordRelyMember) {
		getSqlMapClientTemplate().update("serviceRecordRelyMember.updateRepeatData",
				serviceRecordRelyMember);

	}

	@Override
	public List<ServiceRecordRelyMember> getServiceRecordRelyMembersByMemberIdAndRecordId(
			ServiceRecordRelyMember serviceRecordRelyMember) {

		return getSqlMapClientTemplate().queryForList(
				"serviceRecordRelyMember.getServiceRecordRelyMembersByMemberIdAndRecordId",
				serviceRecordRelyMember);
	}
}
