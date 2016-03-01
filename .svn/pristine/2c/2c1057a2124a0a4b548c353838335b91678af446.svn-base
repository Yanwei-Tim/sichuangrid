package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SecurityPropaganda;

public interface SecurityPropagandaDao {
	SecurityPropaganda getSecurityPropagandaById(Long id);

	SecurityPropaganda addSecurityPropaganda(SecurityPropaganda securityPropaganda);

	void deleteSecurityPropagandaById(Long id);

	SecurityPropaganda updateSecurityPropaganda(SecurityPropaganda securityPropaganda);

	PageInfo<SecurityPropaganda> findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord);
}
