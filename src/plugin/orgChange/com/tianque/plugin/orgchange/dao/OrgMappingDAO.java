package com.tianque.plugin.orgchange.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.OrgMapping;

/**
 * 组织机构变更映射DAO
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@DynamicIbatisDAO(value = "OrgMappingDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("OrgMappingDAO")
public interface OrgMappingDAO {

	public abstract Long addOrgMapping(OrgMapping orgMapping);

	public abstract List<OrgMapping> queryOrgMappingForList(Long orgChangeInfoId);

	public abstract int deleteOrgMapping(Long orgChangeId);

}
