package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.List;

import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;

public interface ServiceRecordAttachmentDao {
	/**
	 * 新增服务记录附件
	 * 
	 * @param ServiceRecordAttachment
	 * @return ServiceRecordAttachment
	 */
	public ServiceRecordAttachment addServiceRecordAttachment(
			ServiceRecordAttachment serviceRecordAttachment);

	/**
	 * 返回单个服务记录附件
	 * 
	 * @param id
	 * @return ServiceRecordAttachment
	 */
	public ServiceRecordAttachment getServiceRecordAttachmentById(Long id);

	/**
	 * 根据服务id获取所有该记录的附件
	 * 
	 * @param id
	 * @return List<ServiceRecordAttachment>
	 */
	public List<ServiceRecordAttachment> findServiceRecordAttachments(Long id);

	/**
	 * 根据附件Id删除附件
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceRecordHasAttachment(Long id);

	/**
	 * 删除与附件的关联关系
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceRecordHasAttachments(Long id);
}
