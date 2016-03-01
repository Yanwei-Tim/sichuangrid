package com.tianque.newVillage.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.newVillage.domain.LeadingEnterprise;

public interface LeadingEnterpriseDao {

	/***
	 * 新增
	 */
	public LeadingEnterprise addLeadingEnterprise(
			LeadingEnterprise leadingEnterprise);

	/***
	 * 修改
	 */
	public LeadingEnterprise updateLeadingEnterprise(
			LeadingEnterprise leadingEnterprise);

	/***
	 * 批量删除
	 */
	public void deleteLeadingEnterpriseByIds(String[] ids);

	/***
	 * 单个查询
	 */
	public LeadingEnterprise getLeadingEnterpriseById(Long id);

	/***
	 * 列表查询
	 */
	public PageInfo<LeadingEnterprise> findLeadingEnterpriseForPageInfo(
			String enterpriseName, Long searchType, Organization organization,
			Integer page, Integer rows, String sidx, String sord);
}
