package com.tianque.rolelog.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.rolelog.domain.RoleUpdateLog;

@DynamicIbatisDAO(value = "RoleUpdateLogDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("RoleUpdateLogDAO")
public interface RoleUpdateLogDAO {
	
	public void addRoleUpdateLog(RoleUpdateLog roleLog);
}
