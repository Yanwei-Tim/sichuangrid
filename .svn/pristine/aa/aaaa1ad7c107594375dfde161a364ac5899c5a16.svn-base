/**
 * 
 */
package com.tianque.serviceList.service.impl;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.serviceList.dao.ReplyAttchDao;
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ServiceListAttachService;

@Service("replyAttachServiceImpl")
@Transactional
public class ReplyAttachServiceImpl implements ReplyAttachService{
	@Autowired
	ReplyAttchDao attachDao;
	
	@Override
	public void addReplyAttch(String[] attachFileNames, Long objectId,Integer serviceType) {
		if (attachFileNames == null || attachFileNames.length <= 0||objectId==null||serviceType==null) {
			return;
		}
		ReplyAttch replyAttch = null;
		StoredFile storedFile = null;
		for (String attachFileName : attachFileNames) {
			if (attachFileName.charAt(0) == ',') {
				attachFileName = attachFileName.substring(1);
				try {
					storedFile = FileUtil.copyTmpFileToStoredFile(attachFileName,
							GridProperties.DOWNLOAD_TEMP_FILE_FOLDER);
				} catch (Exception e) {
					e.printStackTrace();
				}
				replyAttch = new ReplyAttch();
				replyAttch.setPath(storedFile.getStoredFilePath()
						+ File.separator + storedFile.getStoredFileName());
				replyAttch.setName(storedFile.getStoredTruthFileName());
				replyAttch.setServiceType(serviceType);
				replyAttch.setReplyId(objectId);
				attachDao.add(replyAttch);
			}
		}
	}
	@Override
	public void deleteReplyAttchByIds(Long id,Integer type) {
		attachDao.deleteReplyAttchByIds(id,type);
	}

	@Override
	public ReplyAttch getReplyAttchById(Long id) {
		return attachDao.get(id);
	}
	
	@Override
	public ArrayList<ReplyAttch> getReplyAttchByIdAndType(Long id, Integer type) {
		return attachDao.getReplyAttchByIdAndType(id, type);
	}
	
	@Override
	public void deleteReplyAttch(Long id) {
		attachDao.delete(id);
	}
	
}
