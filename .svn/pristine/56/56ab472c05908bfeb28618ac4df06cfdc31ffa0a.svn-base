package com.tianque.plugin.orgchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.plugin.orgchange.dao.OrganizationsBackupDAO;
import com.tianque.plugin.orgchange.domain.OrganizationsBackupVo;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年10月22日
 */
@Service("organizationsBackupService")
public class OrganizationsBackupServiceImpl implements
		OrganizationsBackupService {
	@Autowired
	private OrganizationsBackupDAO OrganizationsBackupDAO;

	@Override
	public int updateChangeInfo(OrganizationsBackupVo organizationsBackupVo) {
		return OrganizationsBackupDAO.updateChangeInfo(organizationsBackupVo);
	}

}
