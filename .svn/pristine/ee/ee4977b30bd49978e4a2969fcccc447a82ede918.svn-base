package com.tianque.sysAttachment.service;

import java.util.List;

import com.tianque.sysAttachment.domain.SysAttachment;

public interface SysAttachmentService {
	/**
	 * 添加附件信息
	 * 
	 * @param sysAttachment
	 * @return
	 */
	public Long addSysAttachment(SysAttachment sysAttachment);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public SysAttachment getSysAttachment(Long id);

	/**
	 * 根据类型和文件名 查询对应的附件信息
	 * 
	 * @param type
	 * @param fileName
	 * @return
	 */
	public List<SysAttachment> querySysAttachmentByTypeAndFileNameForList(
			String targetType, String realName, Long tragetId);

	/**
	 * 根据ID 删除对应的附件信息
	 * 
	 * @param id
	 */
	public void deleteSysAttachment(Long id);

	/**
	 * 根据关联ID删除附件信息
	 * 
	 * @param targetId
	 */
	public void deleteSysAttachmentByTargetId(Long targetId);

	/**
	 * 根据关联ID和业务类型删除附件信息
	 * 
	 * @param targetId
	 * @param targetType
	 */
	public void deleteSysAttachmentByTargetIdAndType(Long targetId,
			String targetType);
}
