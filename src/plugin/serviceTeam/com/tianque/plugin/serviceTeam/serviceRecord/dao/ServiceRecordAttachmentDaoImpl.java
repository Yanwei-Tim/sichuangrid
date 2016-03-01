package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;

@Repository("ServiceRecordAttachmentDao")
public class ServiceRecordAttachmentDaoImpl extends AbstractBaseDao implements
		ServiceRecordAttachmentDao {
	@Override
	public ServiceRecordAttachment addServiceRecordAttachment(
			ServiceRecordAttachment serviceRecordAttachment) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceRecordAttachment.addServiceRecordAttachment", serviceRecordAttachment);
		return getServiceRecordAttachmentById(id);
	}

	@Override
	public ServiceRecordAttachment getServiceRecordAttachmentById(Long id) {
		return (ServiceRecordAttachment) getSqlMapClientTemplate().queryForObject(
				"serviceRecordAttachment.getServiceRecordAttachmentById", id);
	}

	@Override
	public List<ServiceRecordAttachment> findServiceRecordAttachments(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"serviceRecordAttachment.findServiceRecordAttachments", id);
	}

	@Override
	public int deleteServiceRecordHasAttachments(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceRecordAttachment.deleteServiceRecordHasAttachments", id);
	}

	@Override
	public int deleteServiceRecordHasAttachment(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceRecordAttachment.deleteServiceRecordHasAttachment", id);
	}
}
