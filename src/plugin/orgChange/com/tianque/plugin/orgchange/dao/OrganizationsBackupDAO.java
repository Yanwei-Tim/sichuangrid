package com.tianque.plugin.orgchange.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.OrganizationsBackupVo;

@DynamicIbatisDAO(value = "OrganizationsBackupDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("OrganizationsBackupDAO")
public interface OrganizationsBackupDAO {

	public int updateChangeInfo(OrganizationsBackupVo organizationsBackupVo);
}
