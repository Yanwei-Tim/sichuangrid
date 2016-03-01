package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.Enterprise;

public interface EnterpriseDao {

	public Enterprise addEnterprise(Enterprise enterprise);

	public Enterprise getEnterpriseById(Long id);

	public PageInfo<Enterprise> findEnterprisesForListPageByOrgInternalCode(String orgInternalCode,
			int pageSize, int pageNum, String sortField, String order);

	public PageInfo<Enterprise> findEnterprisesForListPageByOrgInternalCodeAndKeyType(
			String orgInternalCode, String keyType, int pageSize, int pageNum, String sortField,
			String order, Long isEmphasis);

	public Enterprise updateEnterprise(Enterprise enterprise);

	public int deleteEnterpriseById(Long id);

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

	public Enterprise findEnterpriseByNameAndOrgId(String name, Long orgId);

	public Enterprise findEnterpriseByNameAndOrgIdAndId(String name, Long orgId, Long id);

	public Enterprise updateEmphasis(Enterprise enterprise);

	public Enterprise getEnterpriseByNameAndOrgIdAndKeyType(String name, Long orgId, String keyType);

	public int getEnterpriseCountByOrgIdAndKeyType(Long orgId, String keyType);

	public int getEnterpriseCountByOrgCodeAndKeyType(String orgCode, String keyType);

	public void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutDate);

}
