package com.tianque.integrativeQuery.population.domain;

import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.core.base.BaseDomain;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;
import com.tianque.domain.vo.SearchDruggyVo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.domain.vo.SearchFPersonnelVo;
import com.tianque.domain.vo.SearchFloatingPopulationVo;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.domain.vo.SearchHouseholdStaffVo;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.domain.vo.SearchMPersonnelVo;
import com.tianque.domain.vo.SearchMentalPatientVo;
import com.tianque.domain.vo.SearchNurturesWomenVo;
import com.tianque.domain.vo.SearchOptimalObjectVo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;
import com.tianque.domain.vo.SearchPositiveInfoVo;
import com.tianque.domain.vo.SearchQPersonnelVo;
import com.tianque.domain.vo.SearchRectificativePersonVo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.domain.vo.SearchUnemployedPeopleVo;

/**
 * 人口综合查询
 */
public class PopulationQueryVo extends BaseDomain {
	// 实有
	private SearchHouseholdStaffVo searchHouseholdStaffVo;
	private SearchFloatingPopulationVo searchFloatingPopulationVo;
	private BaseUnsettledPopulationSearchCondition searchUnsettledPopulation;

	// 重点人员
	private SearchPositiveInfoVo searchPositiveInfoVo;
	private SearchRectificativePersonVo searchRectificativePersonVo;
	private SearchDruggyVo searchDruggyVo;
	private SearchMentalPatientVo searchMentalPatientVo;
	private SearchIdleYouthVo searchIdleYouthVo;
	private SearchSuperiorVisitVo searchSuperiorVisitVo;
	private SearchDangerousGoodsPractitioner searchDangerousGoodsPractitionerVo;
	private SearchOtherAttentionPersonnelVo searchOtherAttentionPersonnelVo;
	private SearchAidspopulationsVo searchAidspopulationsVo;

	// 民政
	private SearchElderlyPeopleVo searchElderlyPeopleVo;
	private SearchHandicappedVo searchHandicappedVo;
	private SearchAidNeedPopulationVo searchAidNeedPopulationVo;
	private SearchOptimalObjectVo searchOptimalObjectVo;
	private SearchUnemployedPeopleVo searchUnemployedPeopleVo;

	// 计生
	private SearchNurturesWomenVo searchNurturesWomenVo;

	// FXJ
	private SearchFPersonnelVo searchFPersonnelVo;
	private SearchQPersonnelVo searchQPersonnelVo;
	private SearchMPersonnelVo searchMPersonnelVo;

	public final static String HASHOUSE = "true";
	public final static String HASNOHOUSE = "false";
	public final static String NOCHECKED = "";

	public final static String ALL_ACTUAL_POPULATION = "-1";

	public static Boolean getIsHasHouse(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return Boolean.valueOf(str);
	}

	public SearchHouseholdStaffVo getSearchHouseholdStaffVo() {
		return searchHouseholdStaffVo;
	}

	public void setSearchHouseholdStaffVo(
			SearchHouseholdStaffVo searchHouseholdStaffVo) {
		this.searchHouseholdStaffVo = searchHouseholdStaffVo;
	}

	public SearchFloatingPopulationVo getSearchFloatingPopulationVo() {
		return searchFloatingPopulationVo;
	}

	public void setSearchFloatingPopulationVo(
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		this.searchFloatingPopulationVo = searchFloatingPopulationVo;
	}

	public BaseUnsettledPopulationSearchCondition getSearchUnsettledPopulation() {
		return searchUnsettledPopulation;
	}

	public void setSearchUnsettledPopulation(
			BaseUnsettledPopulationSearchCondition searchUnsettledPopulation) {
		this.searchUnsettledPopulation = searchUnsettledPopulation;
	}

	public SearchPositiveInfoVo getSearchPositiveInfoVo() {
		return searchPositiveInfoVo;
	}

	public void setSearchPositiveInfoVo(
			SearchPositiveInfoVo searchPositiveInfoVo) {
		this.searchPositiveInfoVo = searchPositiveInfoVo;
	}

	public SearchRectificativePersonVo getSearchRectificativePersonVo() {
		return searchRectificativePersonVo;
	}

	public void setSearchRectificativePersonVo(
			SearchRectificativePersonVo searchRectificativePersonVo) {
		this.searchRectificativePersonVo = searchRectificativePersonVo;
	}

	public SearchDruggyVo getSearchDruggyVo() {
		return searchDruggyVo;
	}

	public void setSearchDruggyVo(SearchDruggyVo searchDruggyVo) {
		this.searchDruggyVo = searchDruggyVo;
	}

	public SearchMentalPatientVo getSearchMentalPatientVo() {
		return searchMentalPatientVo;
	}

	public void setSearchMentalPatientVo(
			SearchMentalPatientVo searchMentalPatientVo) {
		this.searchMentalPatientVo = searchMentalPatientVo;
	}

	public SearchIdleYouthVo getSearchIdleYouthVo() {
		return searchIdleYouthVo;
	}

	public void setSearchIdleYouthVo(SearchIdleYouthVo searchIdleYouthVo) {
		this.searchIdleYouthVo = searchIdleYouthVo;
	}

	public SearchSuperiorVisitVo getSearchSuperiorVisitVo() {
		return searchSuperiorVisitVo;
	}

	public void setSearchSuperiorVisitVo(
			SearchSuperiorVisitVo searchSuperiorVisitVo) {
		this.searchSuperiorVisitVo = searchSuperiorVisitVo;
	}

	public SearchDangerousGoodsPractitioner getSearchDangerousGoodsPractitionerVo() {
		return searchDangerousGoodsPractitionerVo;
	}

	public void setSearchDangerousGoodsPractitionerVo(
			SearchDangerousGoodsPractitioner searchDangerousGoodsPractitionerVo) {
		this.searchDangerousGoodsPractitionerVo = searchDangerousGoodsPractitionerVo;
	}

	public SearchElderlyPeopleVo getSearchElderlyPeopleVo() {
		return searchElderlyPeopleVo;
	}

	public void setSearchElderlyPeopleVo(
			SearchElderlyPeopleVo searchElderlyPeopleVo) {
		this.searchElderlyPeopleVo = searchElderlyPeopleVo;
	}

	public SearchHandicappedVo getSearchHandicappedVo() {
		return searchHandicappedVo;
	}

	public void setSearchHandicappedVo(SearchHandicappedVo searchHandicappedVo) {
		this.searchHandicappedVo = searchHandicappedVo;
	}

	public SearchAidNeedPopulationVo getSearchAidNeedPopulationVo() {
		return searchAidNeedPopulationVo;
	}

	public void setSearchAidNeedPopulationVo(
			SearchAidNeedPopulationVo searchAidNeedPopulationVo) {
		this.searchAidNeedPopulationVo = searchAidNeedPopulationVo;
	}

	public SearchOptimalObjectVo getSearchOptimalObjectVo() {
		return searchOptimalObjectVo;
	}

	public void setSearchOptimalObjectVo(
			SearchOptimalObjectVo searchOptimalObjectVo) {
		this.searchOptimalObjectVo = searchOptimalObjectVo;
	}

	public SearchUnemployedPeopleVo getSearchUnemployedPeopleVo() {
		return searchUnemployedPeopleVo;
	}

	public void setSearchUnemployedPeopleVo(
			SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		this.searchUnemployedPeopleVo = searchUnemployedPeopleVo;
	}

	public SearchNurturesWomenVo getSearchNurturesWomenVo() {
		return searchNurturesWomenVo;
	}

	public void setSearchNurturesWomenVo(
			SearchNurturesWomenVo searchNurturesWomenVo) {
		this.searchNurturesWomenVo = searchNurturesWomenVo;
	}

	public void setSearchOtherAttentionPersonnelVo(
			SearchOtherAttentionPersonnelVo searchOtherAttentionPersonnelVo) {
		this.searchOtherAttentionPersonnelVo = searchOtherAttentionPersonnelVo;
	}

	public SearchOtherAttentionPersonnelVo getSearchOtherAttentionPersonnelVo() {
		return searchOtherAttentionPersonnelVo;
	}

	public void setSearchAidspopulationsVo(
			SearchAidspopulationsVo searchAidspopulationsVo) {
		this.searchAidspopulationsVo = searchAidspopulationsVo;
	}

	public SearchAidspopulationsVo getSearchAidspopulationsVo() {
		return searchAidspopulationsVo;
	}

	public SearchFPersonnelVo getSearchFPersonnelVo() {
		return searchFPersonnelVo;
	}

	public void setSearchFPersonnelVo(SearchFPersonnelVo searchFPersonnelVo) {
		this.searchFPersonnelVo = searchFPersonnelVo;
	}

	public SearchQPersonnelVo getSearchQPersonnelVo() {
		return searchQPersonnelVo;
	}

	public void setSearchQPersonnelVo(SearchQPersonnelVo searchQPersonnelVo) {
		this.searchQPersonnelVo = searchQPersonnelVo;
	}

	public SearchMPersonnelVo getSearchMPersonnelVo() {
		return searchMPersonnelVo;
	}

	public void setSearchMPersonnelVo(SearchMPersonnelVo searchMPersonnelVo) {
		this.searchMPersonnelVo = searchMPersonnelVo;
	}

}
