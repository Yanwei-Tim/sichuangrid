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
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.service.ServiceListAttachService;

@Service("serviceListAttachServiceImpl")
@Transactional
public class ServiceListAttachServiceImpl implements ServiceListAttachService{
	@Autowired
	ServiceListAttachDao attachDao;
	
	@Override
	public void addServiceListAttch(String[] attachFileNames, Long objectId,Integer serviceType) {
		if (attachFileNames == null || attachFileNames.length <= 0||objectId==null||serviceType==null) {
			return;
		}
		ServiceListAttch serviceListAttch = null;
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
				serviceListAttch = new ServiceListAttch();
				serviceListAttch.setPath(storedFile.getStoredFilePath()
						+ File.separator + storedFile.getStoredFileName());
				serviceListAttch.setName(storedFile.getStoredTruthFileName());
				serviceListAttch.setServiceType(serviceType);
				serviceListAttch.setServiceId(objectId);
				attachDao.add(serviceListAttch);
			}
		}
	}
	@Override
	public void deleteServiceListAttchByIds(Long id,Integer type) {
		attachDao.deleteServiceListAttchByIds(id,type);
	}

	@Override
	public ServiceListAttch getServiceListAttchById(Long id) {
		return attachDao.get(id);
	}
	
	@Override
	public ArrayList<ServiceListAttch> getServiceListAttchByIdAndType(Long id, Integer type) {
		return attachDao.getServiceListAttchByIdAndType(id, type);
	}
	
	@Override
	public void deleteServiceListAttch(Long id) {
		attachDao.delete(id);
	}
	
}
