package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SocietySecurity;

public interface SocietySecurityService {
	public SocietySecurity getSocietySecurityById(Long id);

	public SocietySecurity addSocietySecurity(SocietySecurity societySecurity);

	public void deleteSocietySecurityById(Long id);

	public SocietySecurity updateSocietySecurity(SocietySecurity societySecurity);

	public PageInfo<SocietySecurity> findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows, String sidx, String sord);

	public Long getDailyDirectoryType();
}