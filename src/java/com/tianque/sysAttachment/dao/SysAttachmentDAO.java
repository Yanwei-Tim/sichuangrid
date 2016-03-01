package com.tianque.sysAttachment.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.sysAttachment.domain.SysAttachment;

@DynamicIbatisDAO(value = "SysAttachmentDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("SysAttachmentDAO")
public interface SysAttachmentDAO {
	public Long addSysAttachment(SysAttachment sysAttachment);

	public SysAttachment getSysAttachment(Long id);

	public List<SysAttachment> querySysAttachmentByTypeAndFileNameForList(
			Map<String, Object> map);

	public void deleteSysAttachment(Long id);

	public void deleteSysAttachmentByTargetId(Long targetId);

	public void deleteSysAttachmentByTargetIdAndType(Map<String, Object> map);
}
