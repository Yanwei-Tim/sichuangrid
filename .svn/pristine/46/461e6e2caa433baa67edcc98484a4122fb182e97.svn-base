package com.tianque.plugin.dataManage.location.companyPlaceTemp.constant;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;

/**
 * 二级分类en
 * 
 * @author N-242
 * 
 */
public enum ClassifiCationEnType {

	PUBLICPLACE(ModulTypes.PublicPlace, ModulTypes.PUBLICPLACE_KEY), TRAFFICPLACE(
			ModulTypes.TrafficPlace, ModulTypes.TRAFFICPLACE_KEY), ENTERTAINMENTPLACE(
			ModulTypes.EntertainmentPlace, ModulTypes.ENTERTAINMENTPLACE_KEY), TRADEPLACE(
			ModulTypes.TradePlace, ModulTypes.TRADEPLACE_KEY), INTERNETBAR(
			ModulTypes.InternetServicesPlace,
			ModulTypes.INTERNETSERVICESPLACE_KEY), ACCOMMODATIONSERVICESPLACE(
			ModulTypes.AccommodationServicesPlace,
			ModulTypes.ACCOMMODATIONSERVICESPLACE_KEY), FOODSERVICESPLACE(
			ModulTypes.FoodServicesPlace, ModulTypes.FOODSERVICESPLACE_KEY), TRAVELINGPLACE(
			ModulTypes.TravelingPlace, ModulTypes.TRAVELINGPLACE_KEY), CONSTRUCTIONPLACE(
			ModulTypes.ConstructionPlace, ModulTypes.CONSTRUCTIONPLACE_KEY), OTHERPLACE(
			ModulTypes.OtherPlace, ModulTypes.OTHERPLACE_KEY), PARTYGOVERNMENTORGANCOMPANY(
			ModulTypes.PartyGovernmentOrganCompany,
			ModulTypes.PARTYGOVERNMENTORGANCOMPANY_KEY), SCHOOLS(
			ModulTypes.EducationCompany, ModulTypes.EDUCATIONCOMPANY_KEY), HOSPITAL(
			ModulTypes.MedicalHygieneCompany,
			ModulTypes.MEDICALHYGIENECOMPANY_KEY), DANGEROUSCHEMICALSUNIT(
			ModulTypes.DangerousStoreCompany,
			ModulTypes.DANGEROUSSTORECOMPANY_KEY), OTHERCOMPANY(
			ModulTypes.OtherCompany, ModulTypes.OTHERCOMPANY_KEY),
			LOGISTICS(ModulTypes.Logistics, ModulTypes.LOGISTICS_KEY);

	private String name;
	private String code;

	private ClassifiCationEnType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public static String getCodeByName(String name) {
		if (null == name || "".equals(name.trim()))
			return null;
		for (ClassifiCationEnType type : ClassifiCationEnType.values()) {
			if (type.getName().equals(name))
				return type.getCode();
		}
		return null;
	}
}
