package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.FireSafetyEnterprise;

public interface FireSafetyEnterpriseDao {

	public FireSafetyEnterprise addFireSafetyEnterprise(FireSafetyEnterprise enterprise);

	public FireSafetyEnterprise getFireSafetyEnterpriseById(Long id);

	public PageInfo<FireSafetyEnterprise> findFireSafetyEnterprisesForListPageByOrgInternalCodeAndKeyType(
			String orgInternalCode, String keyType, int pageSize, int pageNum, String sortField,
			String order, Long isEmphasis);

	public FireSafetyEnterprise updateFireSafetyEnterprise(FireSafetyEnterprise enterprise);

	public int deleteFireSafetyEnterpriseById(Long id);

	/**
	 * 添加 企业综治工作室成员
	 * 
	 * @param comprehensiveManageMember
	 * @return
	 */
	public ComprehensiveManageMember addComprehensiveManageMember(
			ComprehensiveManageMember comprehensiveManageMember);

	public ComprehensiveManageMember getComprehensiveManageMemberById(Long id);

	public List<ComprehensiveManageMember> findComprehensiveManageMembersByEnterpriseId(Long id);

	public int deleteComprehensiveManageMembersById(Long id);

	public int deleteComprehensiveManageMembersByEnterpriseId(Long id);

	public ComprehensiveManageMember updateComprehensiveManageMemberMember(
			ComprehensiveManageMember comprehensiveManageMember);

	public FireSafetyEnterprise updateEmphasis(FireSafetyEnterprise enterprise);

	public FireSafetyEnterprise getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(String name,
			Long orgId, String keyType);

	public void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutDate);
}
