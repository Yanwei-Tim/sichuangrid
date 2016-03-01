package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.OtherLocale;

public interface OtherLocaleService {

	public OtherLocale addOtherLocale(OtherLocale otherLocale);

	public OtherLocale getOtherLocaleById(Long id);

	public OtherLocale updateOtherLocale(OtherLocale otherLocale);

	public boolean deleteOtherLocaleById(Long id);

	public PageInfo<OtherLocale> findOtherLocalesForPage(Long orgId, Integer pageSize,
			Integer pageNum, String sidx, String sord, Long isEmphasis);

	public Integer countExsistedOtherLocale(String name, Long orgId, Long id);

	public OtherLocale updateOtherLocaleByName(String name, Long id, OtherLocale domain);

	boolean hasDuplicateOtherLocaleName(Long ownerOrgId, String otherLocaleName, Long exceptedId);

	public OtherLocale updateOtherLocaleById(OtherLocale otherLocale);

	public List<Long> deleteOtherLocaleById(List<Long> placeIds);

	public boolean hasRelatePlacce(List<Long> placeIds);

	public void shiftOtherLocale(Long[] ids, Long orgId);

	public void updateEmphasiseByIds(Long[] ids, OtherLocale location);

	public OtherLocale hasDuplicateOtherLocale(Long ownerOrgId, String otherLocaleName);

	public void deleteOtherLocaleByIds(Long[] ids);
}
