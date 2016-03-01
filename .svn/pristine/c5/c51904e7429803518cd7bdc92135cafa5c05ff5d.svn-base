package com.tianque.plugin.orgchange.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;

/**
 * 组织机构迁移合并信息服务接口
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
public interface OrgChangeInfoService {

	public void addOrganizationChangeInfo(Organization organization,
			String orgIds, String operate);

	public PageResult<OrgChangeInfo> findNoDealInfoList(Integer isDeal,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public void deleteOrgChangeInfoByIds(Long changeId);

	public OrgChangeInfo getInfoById(Long id);

	public int getCount(OrgChangeInfo condition);

	public List<OrgChangeInfo> queryAllForList(OrgChangeInfo condition,
			int start, int count);

	public void updateInfo(OrgChangeInfo orgChangeInfo);

}
