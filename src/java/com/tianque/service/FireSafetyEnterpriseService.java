package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.FireSafetyEnterprise;

public interface FireSafetyEnterpriseService {

	public FireSafetyEnterprise addFireSafetyEnterprise(FireSafetyEnterprise enterprise);

	public FireSafetyEnterprise getFireSafetyEnterpriseById(Long id);

	public PageInfo<FireSafetyEnterprise> findFireSafetyEnterprisesForListPageByOrganizationIdAndKeyType(
			Long organizationId, String keyType, int pageSize, int pageNum, String sortField,
			String order, Long isEmphasis);

	public FireSafetyEnterprise updateFireSafetyEnterprise(FireSafetyEnterprise enterprise);

	public boolean deleteFireSafetyEnterpriseById(Long id);

	public FireSafetyEnterprise updateFireSafetyEnterpriseByName(String name, Long id,
			FireSafetyEnterprise domain);

	boolean hasDuplicateFireSafetyEnterpriseNameAndKeyType(Long ownerOrgId, String enterpriseName,
			Long exceptedId, String keyType);

	public FireSafetyEnterprise getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(String name,
			Long orgId, String keyType);

	FireSafetyEnterprise updateEmphasis(FireSafetyEnterprise enterprise);

	public void shiftFireSafetyEnterprise(Long[] ids, Long orgId, String keyType);

	public void updateEmphasiseByIds(Long[] ids, FireSafetyEnterprise location);

	/**
	 * 新增方法，为数据管理准备
	 * 
	 * @param orgId
	 * @param chineseName
	 * @return
	 */
	public List<Long> deleteFireSafetyEnterpriseByIds(Long[] ids);

	public List<Long> deleteFireSafetyEnterpriseById(List<Long> personIds);
}
