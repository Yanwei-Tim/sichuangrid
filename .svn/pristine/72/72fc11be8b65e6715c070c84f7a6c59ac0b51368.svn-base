package com.tianque.plugin.orgchange.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.OrgChangeInfo;

/**
 * 组织机构变更信息DAO
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
@DynamicIbatisDAO(value = "OrgChangeInfoDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("OrgChangeInfoDAO")
public interface OrgChangeInfoDAO {

	public abstract Long addInfo(OrgChangeInfo info);

	public abstract void updateInfo(OrgChangeInfo info);

	public abstract List<OrgChangeInfo> queryAllForList(
			OrgChangeInfo condition, int start, int size);

	public abstract OrgChangeInfo getInfoById(Long id);

	public abstract Integer getCount(OrgChangeInfo condition);

	/** 表名列表显示 */
	public PageResult<OrgChangeInfo> queryNoDealInfoForPageResult(
			Map<String, Object> parameterMap, int pageNum, int pageSize);

	/***
	 * 批量删除
	 */
	public void deleteOrgChangeInfo(Long changeId);

}
