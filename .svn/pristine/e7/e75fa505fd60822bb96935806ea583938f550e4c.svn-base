package com.tianque.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.OtherLocale;

public interface OtherLocaleDao {

	public OtherLocale addOtherLocale(OtherLocale otherLocale);

	public OtherLocale getOtherLocaleById(Long id);

	public OtherLocale updateOtherLocale(OtherLocale otherLocale);

	public int deleteOtherLocaleById(Long id);

	public PageInfo<OtherLocale> findOtherLocalesForPageByOrgInternalCode(String orgInternalCode,
			Integer pageSize, Integer pageNum, String sidx, String sord, Long isEmphasis);

	public Integer countExsistedOtherLocale(String name, Long orgId, Long id);

	public OtherLocale getOtherLocaleByName(String name, Long id);

	public OtherLocale updateOtherLocaleById(OtherLocale otherLocale);

	public void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutDate);
}
