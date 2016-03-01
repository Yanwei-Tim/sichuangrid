package com.tianque.baseInfo.base.dao;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.service.util.PopulationCatalog;

public interface CountrymenDao {

	Countrymen getCountrymenByPopulationIdAndPopulationCatalog(
			Long populationId, PopulationCatalog populationCatalog);

	List<Countrymen> findCountrymensByPopulationIdAndPopulationType(
			Long populationId, String populationType, String orgInternalCode,
			String shardCode);

	List<Countrymen> findCountrymensByIdCardNoAndOrgInternalCode(
			final String idCardNo, final String orgInternalCode,
			String shardCode);
}
