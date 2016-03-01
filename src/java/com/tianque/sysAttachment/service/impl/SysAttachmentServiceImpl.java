package com.tianque.sysAttachment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysAttachment.dao.SysAttachmentDAO;
import com.tianque.sysAttachment.domain.SysAttachment;
import com.tianque.sysAttachment.service.SysAttachmentService;

@Service("sysAttachmentService")
@Transactional
public class SysAttachmentServiceImpl implements SysAttachmentService {
	@Autowired
	private SysAttachmentDAO sysAttachmentDao;

	@Override
	public Long addSysAttachment(SysAttachment sysAttachment) {
		if (sysAttachment == null) {
			throw new BusinessValidationException("参数错误!");
		}
		return sysAttachmentDao.addSysAttachment(sysAttachment);
	}

	@Override
	public SysAttachment getSysAttachment(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数错误!");
		}
		return sysAttachmentDao.getSysAttachment(id);
	}

	@Override
	public List<SysAttachment> querySysAttachmentByTypeAndFileNameForList(
			String targetType, String realName, Long tragetId) {
		if (!StringUtil.isStringAvaliable(targetType)
				|| !StringUtil.isStringAvaliable(realName) || tragetId == null) {
			throw new BusinessValidationException("参数错误!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetType", targetType);
		map.put("realName", realName);
		map.put("tragetId", tragetId);
		return sysAttachmentDao.querySysAttachmentByTypeAndFileNameForList(map);
	}

	@Override
	public void deleteSysAttachment(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数错误!");
		}
		sysAttachmentDao.deleteSysAttachment(id);
	}

	@Override
	public void deleteSysAttachmentByTargetId(Long targetId) {
		if (null == targetId) {
			throw new BusinessValidationException("参数错误!");
		}
		sysAttachmentDao.deleteSysAttachmentByTargetId(targetId);
	}

	@Override
	public void deleteSysAttachmentByTargetIdAndType(Long targetId,
			String targetType) {
		if (null == targetId || StringUtils.isBlank(targetType)) {
			throw new BusinessValidationException("参数错误!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetId", targetId);
		map.put("targetType", targetType);
		sysAttachmentDao.deleteSysAttachmentByTargetIdAndType(map);
	}
}
