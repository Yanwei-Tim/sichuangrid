package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SocietySecurity;

public interface SocietySecurityDao {
	SocietySecurity getSocietySecurityById(Long id);

	SocietySecurity addSocietySecurity(SocietySecurity societySecurity);

	void deleteSocietySecurityById(Long id);

	SocietySecurity updateSocietySecurity(SocietySecurity societySecurity);

	PageInfo<SocietySecurity> findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord);
}
