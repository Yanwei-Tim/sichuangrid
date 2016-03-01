package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SecurityPropaganda;

public interface SecurityPropagandaService {
	public SecurityPropaganda getSecurityPropagandaById(Long id);

	public SecurityPropaganda addSecurityPropaganda(SecurityPropaganda securityPropaganda);

	public void deleteSecurityPropagandaById(Long id);

	public SecurityPropaganda updateSecurityPropaganda(SecurityPropaganda securityPropaganda);

	public PageInfo<SecurityPropaganda> findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows, String sidx, String sord);

	public Long getDailyDirectoryType();
}