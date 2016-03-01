package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Enterprise;

public interface EnterpriseService {

	public Enterprise addEnterprise(Enterprise enterprise);

	public Enterprise getEnterpriseById(Long id);

	public PageInfo<Enterprise> findEnterprisesForListPageByOrganizationId(Long organizationId,
			int pageSize, int pageNum, String sortField, String order);

	public PageInfo<Enterprise> findEnterprisesForListPageByOrganizationIdAndKeyType(
			Long organizationId, String keyType, int pageSize, int pageNum, String sortField,
			String order, Long isEmphasis);

	public Enterprise updateEnterprise(Enterprise enterprise);

	public boolean deleteEnterpriseById(Long id);

	public Enterprise findEnterpriseByNameAndOrgId(String name, Long orgId);

	public Enterprise updateEnterpriseByName(String name, Long id, Enterprise domain);

	public Enterprise findEnterpriseByNameAndOrgIdAndId(String name, Long orgId, Long id);

	boolean hasDuplicateEnterpriseNameAndKeyType(Long ownerOrgId, String enterpriseName,
			Long exceptedId, String keyType);

	public Enterprise getEnterpriseByNameAndOrgIdAndKeyType(String name, Long orgId, String keyType);

	Enterprise updateEmphasis(Enterprise enterprise);

	public List<Long> deleteEnterpriseById(List<Long> personIds);

	public boolean hasRelatePlacce(List<Long> personIds);

	public void shiftEnterprise(Long[] ids, Long orgId, String keyType);

	public void updateEmphasiseByIds(Long[] ids, Enterprise location);

	public int getEnterpriseCountByOrgIdAndKeyType(Long orgId, String keyType);

	/**
	 * 新增方法，为数据管理准备
	 * 
	 * @param orgId
	 * @param chineseName
	 * @return
	 */
	public List<Long> deleteEnterpriseByIds(Long[] ids);

	public Enterprise hasDuplicateEnterprise(Long orgId, String name, String type);

	public Enterprise hasDuplicateFireSafetyEnterprise(Long orgId, String name);

}
