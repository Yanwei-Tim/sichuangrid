package com.tianque.domain.workingRecord;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.beanutils.PropertyUtils;

import com.tianque.core.util.StringUtil;
import com.tianque.working.domain.ReportWorkingRecord;

@SuppressWarnings("serial")
public class DisputEsexamine extends ReportWorkingRecord {
	/***
	 * issuecount--纠纷排查数 dealcount---纠纷调处数 finishcount---成功数 stagescount--起数
	 * imptIssuecount--重大矛盾纠纷排查数 imptDealcount---重大矛盾纠纷调处数
	 * imptFinishcount---重大矛盾成功数
	 * province---省 city---市 county---县 twon--乡 vil---村 un---小计 year--年度累计
	 * total--总况 religion--民族 soldier--军转干部、复原退伍军人安置 remove--拆迁安 build--建筑工程质量
	 * assets--建筑工程质量、物业管理 economy--经济活动 labour--劳资 ltd--企业 environ--环境
	 * judicial--司法 admin--行政 school--学校 sea--海事 cadre--干部作风 vilth--村务 job--承包
	 * family--家庭 patient--医患 gangs--黑恶实例 issuelow--涉及矛盾纠纷人数—50人以下
	 * issuemid--涉及矛盾纠纷人数-50至100人 issuehigh--涉及矛盾纠纷人数-100人至500人
	 * issuehigher--涉及矛盾纠纷人数-500人以上 impt--重大矛盾纠纷 concentrate--集中排查 other--集中排查
	 * landBoundaries--土地矿产山林水利界限权属纠纷
	 */
	private long otherProvinceIssuecount;
	private long otherCityIssuecount;
	private long otherCountyIssuecount;
	private long otherTwonIssuecount;
	private long otherVilIssuecount;
	private long otherUnIssuecount;
	private long otherYearIssuecount;
	private long otherProvinceDealcount;
	private long otherCityDealcount;
	private long otherCountyDealcount;
	private long otherTwonDealcount;
	private long otherVilDealcount;
	private long otherUnDealcount;
	private long otherYearDealcount;

	private long totalProvinceIssuecount;
	private long totalProvinceDealcount;
	private String totalProvinceDealrate;
	private long totalProvinceFinishcount;
	private String totalProvinceFinishrate;
	private long religionProvinceIssuecount;
	private long religionProvinceDealcount;
	private long religionProvinceFinishcount;
	private long soldierProvinceIssuecount;
	private long soldierProvinceDealcount;
	private long soldierProvinceFinishcount;
	private long removeProvinceIssuecount;
	private long removeProvinceDealcount;
	private long landBoundariesProvinceIssuecount;
	// private long buildProvinceIssuecount;
	private long landBoundariesProvinceDealcount;
	private long buildProvinceDealcount;
	private long buildProvinceFinishcount;
	private long assetsProvinceIssuecount;
	private long assetsProvinceDealcount;
	private long assetsProvinceFinishcount;
	private long economyProvinceIssuecount;
	private long economyProvinceDealcount;
	private long economyProvinceFinishcount;
	private long labourProvinceIssuecount;
	private long labourProvinceDealcount;
	private long labourProvinceFinishcount;
	private long ltdProvinceIssuecount;
	private long ltdProvinceDealcount;
	private long ltdProvinceFinishcount;
	private long environProvinceIssuecount;
	private long environProvinceDealcount;
	private long environProvinceFinishcount;
	private long judicialProvinceIssuecount;
	private long judicialProvinceDealcount;
	private long judicialProvinceFinishcount;
	private long adminProvinceIssuecount;
	private long adminProvinceDealcount;
	private long adminProvinceFinishcount;
	private long schoolProvinceIssuecount;
	private long schoolProvinceDealcount;
	private long schoolProvinceFinishcount;
	private long seaProvinceIssuecount;
	private long seaProvinceDealcount;
	private long seaProvinceFinishcount;
	private long cadreProvinceIssuecount;
	private long cadreProvinceDealcount;
	private long cadreProvinceFinishcount;
	private long vilthProvinceIssuecount;
	private long vilthProvinceDealcount;
	private long vilthProvinceFinishcount;
	private long jobProvinceIssuecount;
	private long jobProvinceDealcount;
	private long jobProvinceFinishcount;
	private long familyProvinceIssuecount;
	private long familyProvinceDealcount;
	private long familyProvinceFinishcount;
	private long patientProvinceIssuecount;
	private long patientProvinceDealcount;
	private long patientProvinceFinishcount;
	private long gangsProvinceIssuecount;
	private long gangsProvinceDealcount;
	private long gangsProvinceFinishcount;
	private long issuelowProvinceStagescount;
	private long issuelowProvinceFinishcount;
	private long issuemidProvinceStagescount;
	private long issuemidProvinceFinishcount;
	private long issuehighProvinceStagescount;
	private long issuehighProvinceFinishcount;
	private long issuehigherProvinceStagescount;
	private long issuehigherProvinceFinishcount;
	private long imptProvinceImptIssuecount;
	private long imptProvinceImptDealcount;
	private long imptProvinceImptFinishcount;
	private String imptProvinceImptFinishrate;
	private long concentrateProvinceIssuecount;
	private long concentrateProvinceDealcount;
	private long concentrateProvinceFinishcount;

	private long totalCityIssuecount;
	private long totalCityDealcount;
	private String totalCityDealrate;
	private long totalCityFinishcount;
	private String totalCityFinishrate;
	private long religionCityIssuecount;
	private long religionCityDealcount;
	private long religionCityFinishcount;
	private long soldierCityIssuecount;
	private long soldierCityDealcount;
	private long soldierCityFinishcount;
	private long removeCityIssuecount;
	private long removeCityDealcount;
	private long landBoundariesCityIssuecount;
	private long landBoundariesCityDealcount;
	private long buildCityDealcount;
	private long buildCityFinishcount;
	private long assetsCityIssuecount;
	private long assetsCityDealcount;
	private long assetsCityFinishcount;
	private long economyCityIssuecount;
	private long economyCityDealcount;
	private long economyCityFinishcount;
	private long labourCityIssuecount;
	private long labourCityDealcount;
	private long labourCityFinishcount;
	private long ltdCityIssuecount;
	private long ltdCityDealcount;
	private long ltdCityFinishcount;
	private long environCityIssuecount;
	private long environCityDealcount;
	private long environCityFinishcount;
	private long judicialCityIssuecount;
	private long judicialCityDealcount;
	private long judicialCityFinishcount;
	private long adminCityIssuecount;
	private long adminCityDealcount;
	private long adminCityFinishcount;
	private long schoolCityIssuecount;
	private long schoolCityDealcount;
	private long schoolCityFinishcount;
	private long seaCityIssuecount;
	private long seaCityDealcount;
	private long seaCityFinishcount;
	private long cadreCityIssuecount;
	private long cadreCityDealcount;
	private long cadreCityFinishcount;
	private long vilthCityIssuecount;
	private long vilthCityDealcount;
	private long vilthCityFinishcount;
	private long jobCityIssuecount;
	private long jobCityDealcount;
	private long jobCityFinishcount;
	private long familyCityIssuecount;
	private long familyCityDealcount;
	private long familyCityFinishcount;
	private long patientCityIssuecount;
	private long patientCityDealcount;
	private long patientCityFinishcount;
	private long gangsCityIssuecount;
	private long gangsCityDealcount;
	private long gangsCityFinishcount;
	private long issuelowCityStagescount;
	// private long issuelowCityDealcount;
	private long issuelowCityFinishcount;
	private long issuemidCityStagescount;
	// private long issuemidCityDealcount;
	private long issuemidCityFinishcount;
	private long issuehighCityStagescount;
	// private long issuehighCityDealcount;
	private long issuehighCityFinishcount;
	private long issuehigherCityStagescount;
	// private long issuehigherCityDealcount;
	private long issuehigherCityFinishcount;
	private long imptCityImptIssuecount;
	private long imptCityImptDealcount;
	private long imptCityImptFinishcount;
	private String imptCityImptFinishrate;
	private long concentrateCityIssuecount;
	private long concentrateCityDealcount;
	private long concentrateCityFinishcount;

	private long totalCountyIssuecount;
	private long totalCountyDealcount;
	private long totalCountyFinishcount;
	private String totalCountyDealrate;
	private String totalCountyFinishrate;
	private long religionCountyIssuecount;
	private long religionCountyDealcount;
	private long religionCountyFinishcount;
	private long soldierCountyIssuecount;
	private long soldierCountyDealcount;
	private long soldierCountyFinishcount;
	private long removeCountyIssuecount;
	private long removeCountyDealcount;
	private long landBoundariesCountyIssuecount;
	// private long buildCountyIssuecount;
	private long buildCountyDealcount;
	private long buildCountyFinishcount;
	private long assetsCountyIssuecount;
	private long assetsCountyDealcount;
	private long assetsCountyFinishcount;
	private long economyCountyIssuecount;
	private long economyCountyDealcount;
	private long landBoundariesCountyDealcount;
	private long economyCountyFinishcount;
	private long labourCountyIssuecount;
	private long labourCountyDealcount;
	private long labourCountyFinishcount;
	private long ltdCountyIssuecount;
	private long ltdCountyDealcount;
	private long ltdCountyFinishcount;
	private long environCountyIssuecount;
	private long environCountyDealcount;
	private long environCountyFinishcount;
	private long judicialCountyIssuecount;
	private long judicialCountyDealcount;
	private long judicialCountyFinishcount;
	private long adminCountyIssuecount;
	private long adminCountyDealcount;
	private long adminCountyFinishcount;
	private long schoolCountyIssuecount;
	private long schoolCountyDealcount;
	private long schoolCountyFinishcount;
	private long seaCountyIssuecount;
	private long seaCountyDealcount;
	private long seaCountyFinishcount;
	private long cadreCountyIssuecount;
	private long cadreCountyDealcount;
	private long cadreCountyFinishcount;
	private long vilthCountyIssuecount;
	private long vilthCountyDealcount;
	private long vilthCountyFinishcount;
	private long jobCountyIssuecount;
	private long jobCountyDealcount;
	private long jobCountyFinishcount;
	private long familyCountyIssuecount;
	private long familyCountyDealcount;
	private long familyCountyFinishcount;
	private long patientCountyIssuecount;
	private long patientCountyDealcount;
	private long patientCountyFinishcount;
	private long gangsCountyIssuecount;
	private long gangsCountyDealcount;
	private long gangsCountyFinishcount;
	private long issuelowCountyStagescount;
	// private long issuelowCountyDealcount;
	private long issuelowCountyFinishcount;
	private long issuemidCountyStagescount;
	// private long issuemidCountyDealcount;
	private long issuemidCountyFinishcount;
	private long issuehighCountyStagescount;
	// private long issuehighCountyDealcount;
	private long issuehighCountyFinishcount;
	private long issuehigherCountyStagescount;
	// private long issuehigherCountyDealcount;
	private long issuehigherCountyFinishcount;
	private long imptCountyImptIssuecount;
	private long imptCountyImptDealcount;
	private long imptCountyImptFinishcount;
	private String imptCountyImptFinishrate;
	private long concentrateCountyIssuecount;
	private long concentrateCountyDealcount;
	private long concentrateCountyFinishcount;

	private long totalTwonIssuecount;
	private long totalTwonDealcount;
	private long totalTwonFinishcount;
	private String totalTwonDealrate;
	private String totalTwonFinishrate;
	private long religionTwonIssuecount;
	private long religionTwonDealcount;
	private long religionTwonFinishcount;
	private long soldierTwonIssuecount;
	private long soldierTwonDealcount;
	private long soldierTwonFinishcount;
	private long removeTwonIssuecount;
	private long removeTwonDealcount;
	private long landBoundariesTwonIssuecount;
	// private long buildTwonIssuecount;
	private long landBoundariesTwonDealcount;
	private long buildTwonDealcount;
	private long buildTwonFinishcount;
	private long assetsTwonIssuecount;
	private long assetsTwonDealcount;
	private long assetsTwonFinishcount;
	private long economyTwonIssuecount;
	private long economyTwonDealcount;
	private long economyTwonFinishcount;
	private long labourTwonIssuecount;
	private long labourTwonDealcount;
	private long labourTwonFinishcount;
	private long ltdTwonIssuecount;
	private long ltdTwonDealcount;
	private long ltdTwonFinishcount;
	private long environTwonIssuecount;
	private long environTwonDealcount;
	private long environTwonFinishcount;
	private long judicialTwonIssuecount;
	private long judicialTwonDealcount;
	private long judicialTwonFinishcount;
	private long adminTwonIssuecount;
	private long adminTwonDealcount;
	private long adminTwonFinishcount;
	private long schoolTwonIssuecount;
	private long schoolTwonDealcount;
	private long schoolTwonFinishcount;
	private long seaTwonIssuecount;
	private long seaTwonDealcount;
	private long seaTwonFinishcount;
	private long cadreTwonIssuecount;
	private long cadreTwonDealcount;
	private long cadreTwonFinishcount;
	private long vilthTwonIssuecount;
	private long vilthTwonDealcount;
	private long vilthTwonFinishcount;
	private long jobTwonIssuecount;
	private long jobTwonDealcount;
	private long jobTwonFinishcount;
	private long familyTwonIssuecount;
	private long familyTwonDealcount;
	private long familyTwonFinishcount;
	private long patientTwonIssuecount;
	private long patientTwonDealcount;
	private long patientTwonFinishcount;
	private long gangsTwonIssuecount;
	private long gangsTwonDealcount;
	private long gangsTwonFinishcount;
	private long issuelowTwonStagescount;
	// private long issuelowTwonDealcount;
	private long issuelowTwonFinishcount;
	private long issuemidTwonStagescount;
	// private long issuemidTwonDealcount;
	private long issuemidTwonFinishcount;
	private long issuehighTwonStagescount;
	// private long issuehighTwonDealcount;
	private long issuehighTwonFinishcount;
	private long issuehigherTwonStagescount;
	// private long issuehigherTwonDealcount;
	private long issuehigherTwonFinishcount;
	private long imptTwonImptIssuecount;
	private long imptTwonImptDealcount;
	private long imptTwonImptFinishcount;
	private long imptTwonImptDealrate;
	private String imptTwonImptFinishrate;
	private long concentrateTwonIssuecount;
	private long concentrateTwonDealcount;
	private long concentrateTwonFinishcount;

	private long totalVilIssuecount;
	private long totalVilDealcount;
	private long totalVilFinishcount;
	private String totalVilDealrate;
	private String totalVilFinishrate;
	private long religionVilIssuecount;
	private long religionVilDealcount;
	private long religionVilFinishcount;
	private long soldierVilIssuecount;
	private long soldierVilDealcount;
	private long soldierVilFinishcount;
	private long removeVilIssuecount;
	private long removeVilDealcount;
	private long landBoundariesVilIssuecount;
	// private long buildVilIssuecount;
	private long landBoundariesVilDealcount;
	private long buildVilDealcount;
	private long buildVilFinishcount;
	private long assetsVilIssuecount;
	private long assetsVilDealcount;
	private long assetsVilFinishcount;
	private long economyVilIssuecount;
	private long economyVilDealcount;
	private long economyVilFinishcount;
	private long labourVilIssuecount;
	private long labourVilDealcount;
	private long labourVilFinishcount;
	private long ltdVilIssuecount;
	private long ltdVilDealcount;
	private long ltdVilFinishcount;
	private long environVilIssuecount;
	private long environVilDealcount;
	private long environVilFinishcount;
	private long judicialVilIssuecount;
	private long judicialVilDealcount;
	private long judicialVilFinishcount;
	private long adminVilIssuecount;
	private long adminVilDealcount;
	private long adminVilFinishcount;
	private long schoolVilIssuecount;
	private long schoolVilDealcount;
	private long schoolVilFinishcount;
	private long seaVilIssuecount;
	private long seaVilDealcount;
	private long seaVilFinishcount;
	private long cadreVilIssuecount;
	private long cadreVilDealcount;
	private long cadreVilFinishcount;
	private long vilthVilIssuecount;
	private long vilthVilDealcount;
	private long vilthVilFinishcount;
	private long jobVilIssuecount;
	private long jobVilDealcount;
	private long jobVilFinishcount;
	private long familyVilIssuecount;
	private long familyVilDealcount;
	private long familyVilFinishcount;
	private long patientVilIssuecount;
	private long patientVilDealcount;
	private long patientVilFinishcount;
	private long gangsVilIssuecount;
	private long gangsVilDealcount;
	private long gangsVilFinishcount;
	private long issuelowVilStagescount;
	// private long issuelowVilDealcount;
	private long issuelowVilFinishcount;
	private long issuemidVilStagescount;
	// private long issuemidVilDealcount;
	private long issuemidVilFinishcount;
	private long issuehighVilStagescount;
	// private long issuehighVilDealcount;
	private long issuehighVilFinishcount;
	private long issuehigherVilStagescount;
	// private long issuehigherVilDealcount;
	private long issuehigherVilFinishcount;
	private long imptVilImptIssuecount;
	private long imptVilImptDealcount;
	private long imptVilImptFinishcount;
	private long imptVilImptDealrate;
	private String imptVilImptFinishrate;
	private long concentrateVilIssuecount;
	private long concentrateVilDealcount;
	private long concentrateVilFinishcount;

	private long totalUnIssuecount;
	private long totalUnDealcount;
	private long totalUnFinishcount;
	private String totalUnDealrate;
	private String totalUnFinishrate;
	private long religionUnIssuecount;
	private long religionUnDealcount;
	private long religionUnFinishcount;
	private long soldierUnIssuecount;
	private long soldierUnDealcount;
	private long soldierUnFinishcount;
	private long removeUnIssuecount;
	private long removeUnDealcount;
	private long landBoundariesUnIssuecount;
	// private long buildUnIssuecount;
	private long landBoundariesUnDealcount;
	private long buildUnDealcount;
	private long buildUnFinishcount;
	private long assetsUnIssuecount;
	private long assetsUnDealcount;
	private long assetsUnFinishcount;
	private long economyUnIssuecount;
	private long economyUnDealcount;
	private long economyUnFinishcount;
	private long labourUnIssuecount;
	private long labourUnDealcount;
	private long labourUnFinishcount;
	private long ltdUnIssuecount;
	private long ltdUnDealcount;
	private long ltdUnFinishcount;
	private long environUnIssuecount;
	private long environUnDealcount;
	private long environUnFinishcount;
	private long judicialUnIssuecount;
	private long judicialUnDealcount;
	private long judicialUnFinishcount;
	private long adminUnIssuecount;
	private long adminUnDealcount;
	private long adminUnFinishcount;
	private long schoolUnIssuecount;
	private long schoolUnDealcount;
	private long schoolUnFinishcount;
	private long seaUnIssuecount;
	private long seaUnDealcount;
	private long seaUnFinishcount;
	private long cadreUnIssuecount;
	private long cadreUnDealcount;
	private long cadreUnFinishcount;
	private long vilthUnIssuecount;
	private long vilthUnDealcount;
	private long vilthUnFinishcount;
	private long jobUnIssuecount;
	private long jobUnDealcount;
	private long jobUnFinishcount;
	private long familyUnIssuecount;
	private long familyUnDealcount;
	private long familyUnFinishcount;
	private long patientUnIssuecount;
	private long patientUnDealcount;
	private long patientUnFinishcount;
	private long gangsUnIssuecount;
	private long gangsUnDealcount;
	private long gangsUnFinishcount;
	private long issuelowUnStagescount;
	// private long issuelowUnDealcount;
	private long issuelowUnFinishcount;
	private long issuemidUnStagescount;
	// private long issuemidUnDealcount;
	private long issuemidUnFinishcount;
	private long issuehighUnStagescount;
	// private long issuehighUnDealcount;
	private long issuehighUnFinishcount;
	private long issuehigherUnStagescount;
	// private long issuehigherUnDealcount;
	private long issuehigherUnFinishcount;
	private long imptUnImptIssuecount;
	private long imptUnImptDealcount;
	private long imptUnImptFinishcount;
	private long imptUnImptDealrate;
	private String imptUnImptFinishrate;
	private long concentrateUnIssuecount;
	private long concentrateUnDealcount;
	private long concentrateUnFinishcount;

	private long totalYearIssuecount;
	private long totalYearDealcount;
	private long totalYearFinishcount;
	private String totalYearDealrate;
	private String totalYearFinishrate;
	private long religionYearIssuecount;
	private long religionYearDealcount;
	private long religionYearFinishcount;
	private long soldierYearIssuecount;
	private long soldierYearDealcount;
	private long soldierYearFinishcount;
	private long removeYearIssuecount;
	private long removeYearDealcount;
	private long landBoundariesYearIssuecount;
	// private long buildYearIssuecount;
	private long landBoundariesYearDealcount;
	private long buildYearDealcount;
	private long buildYearFinishcount;
	private long assetsYearIssuecount;
	private long assetsYearDealcount;
	private long assetsYearFinishcount;
	private long economyYearIssuecount;
	private long economyYearDealcount;
	private long economyYearFinishcount;
	private long labourYearIssuecount;
	private long labourYearDealcount;
	private long labourYearFinishcount;
	private long ltdYearIssuecount;
	private long ltdYearDealcount;
	private long ltdYearFinishcount;
	private long environYearIssuecount;
	private long environYearDealcount;
	private long environYearFinishcount;
	private long judicialYearIssuecount;
	private long judicialYearDealcount;
	private long judicialYearFinishcount;
	private long adminYearIssuecount;
	private long adminYearDealcount;
	private long adminYearFinishcount;
	private long schoolYearIssuecount;
	private long schoolYearDealcount;
	private long schoolYearFinishcount;
	private long seaYearIssuecount;
	private long seaYearDealcount;
	private long seaYearFinishcount;
	private long cadreYearIssuecount;
	private long cadreYearDealcount;
	private long cadreYearFinishcount;
	private long vilthYearIssuecount;
	private long vilthYearDealcount;
	private long vilthYearFinishcount;
	private long jobYearIssuecount;
	private long jobYearDealcount;
	private long jobYearFinishcount;
	private long familyYearIssuecount;
	private long familyYearDealcount;
	private long familyYearFinishcount;
	private long patientYearIssuecount;
	private long patientYearDealcount;
	private long patientYearFinishcount;
	private long gangsYearIssuecount;
	private long gangsYearDealcount;
	private long gangsYearFinishcount;
	private long issuelowYearStagescount;
	// private long issuelowYearDealcount;
	private long issuelowYearFinishcount;
	private long issuemidYearStagescount;
	// private long issuemidYearDealcount;
	private long issuemidYearFinishcount;
	private long issuehighYearStagescount;
	// private long issuehighYearDealcount;
	private long issuehighYearFinishcount;
	private long issuehigherYearStagescount;
	// private long issuehigherYearDealcount;
	private long issuehigherYearFinishcount;
	private long imptYearImptIssuecount;
	private long imptYearImptDealcount;
	private long imptYearImptFinishcount;
	private long imptYearImptDealrate;
	private String imptYearImptFinishrate;
	private long concentrateYearIssuecount;
	private long concentrateYearDealcount;
	private long concentrateYearFinishcount;

	public DisputEsexamine() {

	}

	public DisputEsexamine(ReportWorkingRecord record) throws Exception {
		// content转化成属性
		JSONObject jsonObject = new JSONObject();
		if (StringUtil.isStringAvaliable(record.getContent())) {
			jsonObject = (JSONObject) JSONSerializer.toJSON(record.getContent());
		}
		DisputEsexamine bean = (DisputEsexamine) JSONObject.toBean(jsonObject,
				DisputEsexamine.class);
		PropertyUtils.copyProperties(bean, record);
		PropertyUtils.copyProperties(this, bean);

	}

	public long getTotalCityIssuecount() {
		return totalCityIssuecount;
	}

	public void setTotalCityIssuecount(long totalCityIssuecount) {
		this.totalCityIssuecount = totalCityIssuecount;
	}

	public long getTotalCityDealcount() {
		return totalCityDealcount;
	}

	public void setTotalCityDealcount(long totalCityDealcount) {
		this.totalCityDealcount = totalCityDealcount;
	}

	public String getTotalCityDealrate() {
		return totalCityDealrate;
	}

	public void setTotalCityDealrate(String totalCityDealrate) {
		this.totalCityDealrate = totalCityDealrate;
	}

	public long getTotalCityFinishcount() {
		return totalCityFinishcount;
	}

	public void setTotalCityFinishcount(long totalCityFinishcount) {
		this.totalCityFinishcount = totalCityFinishcount;
	}

	public String getTotalCityFinishrate() {
		return totalCityFinishrate;
	}

	public void setTotalCityFinishrate(String totalCityFinishrate) {
		this.totalCityFinishrate = totalCityFinishrate;
	}

	public long getReligionCityIssuecount() {
		return religionCityIssuecount;
	}

	public void setReligionCityIssuecount(long religionCityIssuecount) {
		this.religionCityIssuecount = religionCityIssuecount;
	}

	public long getReligionCityDealcount() {
		return religionCityDealcount;
	}

	public void setReligionCityDealcount(long religionCityDealcount) {
		this.religionCityDealcount = religionCityDealcount;
	}

	public long getReligionCityFinishcount() {
		return religionCityFinishcount;
	}

	public void setReligionCityFinishcount(long religionCityFinishcount) {
		this.religionCityFinishcount = religionCityFinishcount;
	}

	public long getSoldierCityIssuecount() {
		return soldierCityIssuecount;
	}

	public void setSoldierCityIssuecount(long soldierCityIssuecount) {
		this.soldierCityIssuecount = soldierCityIssuecount;
	}

	public long getSoldierCityDealcount() {
		return soldierCityDealcount;
	}

	public void setSoldierCityDealcount(long soldierCityDealcount) {
		this.soldierCityDealcount = soldierCityDealcount;
	}

	public long getSoldierCityFinishcount() {
		return soldierCityFinishcount;
	}

	public void setSoldierCityFinishcount(long soldierCityFinishcount) {
		this.soldierCityFinishcount = soldierCityFinishcount;
	}

	public long getRemoveCityIssuecount() {
		return removeCityIssuecount;
	}

	public void setRemoveCityIssuecount(long removeCityIssuecount) {
		this.removeCityIssuecount = removeCityIssuecount;
	}

	public long getRemoveCityDealcount() {
		return removeCityDealcount;
	}

	public void setRemoveCityDealcount(long removeCityDealcount) {
		this.removeCityDealcount = removeCityDealcount;
	}

	public long getLandBoundariesCityIssuecount() {
		return landBoundariesCityIssuecount;
	}

	public void setLandBoundariesCityIssuecount(long landBoundariesCityIssuecount) {
		this.landBoundariesCityIssuecount = landBoundariesCityIssuecount;
	}

	public long getBuildCityDealcount() {
		return buildCityDealcount;
	}

	public void setBuildCityDealcount(long buildCityDealcount) {
		this.buildCityDealcount = buildCityDealcount;
	}

	public long getBuildCityFinishcount() {
		return buildCityFinishcount;
	}

	public void setBuildCityFinishcount(long buildCityFinishcount) {
		this.buildCityFinishcount = buildCityFinishcount;
	}

	public long getAssetsCityIssuecount() {
		return assetsCityIssuecount;
	}

	public void setAssetsCityIssuecount(long assetsCityIssuecount) {
		this.assetsCityIssuecount = assetsCityIssuecount;
	}

	public long getAssetsCityDealcount() {
		return assetsCityDealcount;
	}

	public void setAssetsCityDealcount(long assetsCityDealcount) {
		this.assetsCityDealcount = assetsCityDealcount;
	}

	public long getAssetsCityFinishcount() {
		return assetsCityFinishcount;
	}

	public void setAssetsCityFinishcount(long assetsCityFinishcount) {
		this.assetsCityFinishcount = assetsCityFinishcount;
	}

	public long getEconomyCityIssuecount() {
		return economyCityIssuecount;
	}

	public void setEconomyCityIssuecount(long economyCityIssuecount) {
		this.economyCityIssuecount = economyCityIssuecount;
	}

	public long getEconomyCityDealcount() {
		return economyCityDealcount;
	}

	public void setEconomyCityDealcount(long economyCityDealcount) {
		this.economyCityDealcount = economyCityDealcount;
	}

	public long getEconomyCityFinishcount() {
		return economyCityFinishcount;
	}

	public void setEconomyCityFinishcount(long economyCityFinishcount) {
		this.economyCityFinishcount = economyCityFinishcount;
	}

	public long getLabourCityIssuecount() {
		return labourCityIssuecount;
	}

	public void setLabourCityIssuecount(long labourCityIssuecount) {
		this.labourCityIssuecount = labourCityIssuecount;
	}

	public long getLabourCityDealcount() {
		return labourCityDealcount;
	}

	public void setLabourCityDealcount(long labourCityDealcount) {
		this.labourCityDealcount = labourCityDealcount;
	}

	public long getLabourCityFinishcount() {
		return labourCityFinishcount;
	}

	public void setLabourCityFinishcount(long labourCityFinishcount) {
		this.labourCityFinishcount = labourCityFinishcount;
	}

	public long getLtdCityIssuecount() {
		return ltdCityIssuecount;
	}

	public void setLtdCityIssuecount(long ltdCityIssuecount) {
		this.ltdCityIssuecount = ltdCityIssuecount;
	}

	public long getLtdCityDealcount() {
		return ltdCityDealcount;
	}

	public void setLtdCityDealcount(long ltdCityDealcount) {
		this.ltdCityDealcount = ltdCityDealcount;
	}

	public long getLtdCityFinishcount() {
		return ltdCityFinishcount;
	}

	public void setLtdCityFinishcount(long ltdCityFinishcount) {
		this.ltdCityFinishcount = ltdCityFinishcount;
	}

	public long getEnvironCityIssuecount() {
		return environCityIssuecount;
	}

	public void setEnvironCityIssuecount(long environCityIssuecount) {
		this.environCityIssuecount = environCityIssuecount;
	}

	public long getEnvironCityDealcount() {
		return environCityDealcount;
	}

	public void setEnvironCityDealcount(long environCityDealcount) {
		this.environCityDealcount = environCityDealcount;
	}

	public long getEnvironCityFinishcount() {
		return environCityFinishcount;
	}

	public void setEnvironCityFinishcount(long environCityFinishcount) {
		this.environCityFinishcount = environCityFinishcount;
	}

	public long getJudicialCityIssuecount() {
		return judicialCityIssuecount;
	}

	public void setJudicialCityIssuecount(long judicialCityIssuecount) {
		this.judicialCityIssuecount = judicialCityIssuecount;
	}

	public long getJudicialCityDealcount() {
		return judicialCityDealcount;
	}

	public void setJudicialCityDealcount(long judicialCityDealcount) {
		this.judicialCityDealcount = judicialCityDealcount;
	}

	public long getJudicialCityFinishcount() {
		return judicialCityFinishcount;
	}

	public void setJudicialCityFinishcount(long judicialCityFinishcount) {
		this.judicialCityFinishcount = judicialCityFinishcount;
	}

	public long getAdminCityIssuecount() {
		return adminCityIssuecount;
	}

	public void setAdminCityIssuecount(long adminCityIssuecount) {
		this.adminCityIssuecount = adminCityIssuecount;
	}

	public long getAdminCityDealcount() {
		return adminCityDealcount;
	}

	public void setAdminCityDealcount(long adminCityDealcount) {
		this.adminCityDealcount = adminCityDealcount;
	}

	public long getAdminCityFinishcount() {
		return adminCityFinishcount;
	}

	public void setAdminCityFinishcount(long adminCityFinishcount) {
		this.adminCityFinishcount = adminCityFinishcount;
	}

	public long getSchoolCityIssuecount() {
		return schoolCityIssuecount;
	}

	public void setSchoolCityIssuecount(long schoolCityIssuecount) {
		this.schoolCityIssuecount = schoolCityIssuecount;
	}

	public long getSchoolCityDealcount() {
		return schoolCityDealcount;
	}

	public void setSchoolCityDealcount(long schoolCityDealcount) {
		this.schoolCityDealcount = schoolCityDealcount;
	}

	public long getSchoolCityFinishcount() {
		return schoolCityFinishcount;
	}

	public void setSchoolCityFinishcount(long schoolCityFinishcount) {
		this.schoolCityFinishcount = schoolCityFinishcount;
	}

	public long getSeaCityIssuecount() {
		return seaCityIssuecount;
	}

	public void setSeaCityIssuecount(long seaCityIssuecount) {
		this.seaCityIssuecount = seaCityIssuecount;
	}

	public long getSeaCityDealcount() {
		return seaCityDealcount;
	}

	public void setSeaCityDealcount(long seaCityDealcount) {
		this.seaCityDealcount = seaCityDealcount;
	}

	public long getSeaCityFinishcount() {
		return seaCityFinishcount;
	}

	public void setSeaCityFinishcount(long seaCityFinishcount) {
		this.seaCityFinishcount = seaCityFinishcount;
	}

	public long getCadreCityIssuecount() {
		return cadreCityIssuecount;
	}

	public void setCadreCityIssuecount(long cadreCityIssuecount) {
		this.cadreCityIssuecount = cadreCityIssuecount;
	}

	public long getCadreCityDealcount() {
		return cadreCityDealcount;
	}

	public void setCadreCityDealcount(long cadreCityDealcount) {
		this.cadreCityDealcount = cadreCityDealcount;
	}

	public long getCadreCityFinishcount() {
		return cadreCityFinishcount;
	}

	public void setCadreCityFinishcount(long cadreCityFinishcount) {
		this.cadreCityFinishcount = cadreCityFinishcount;
	}

	public long getVilthCityIssuecount() {
		return vilthCityIssuecount;
	}

	public void setVilthCityIssuecount(long vilthCityIssuecount) {
		this.vilthCityIssuecount = vilthCityIssuecount;
	}

	public long getVilthCityDealcount() {
		return vilthCityDealcount;
	}

	public void setVilthCityDealcount(long vilthCityDealcount) {
		this.vilthCityDealcount = vilthCityDealcount;
	}

	public long getVilthCityFinishcount() {
		return vilthCityFinishcount;
	}

	public void setVilthCityFinishcount(long vilthCityFinishcount) {
		this.vilthCityFinishcount = vilthCityFinishcount;
	}

	public long getJobCityIssuecount() {
		return jobCityIssuecount;
	}

	public void setJobCityIssuecount(long jobCityIssuecount) {
		this.jobCityIssuecount = jobCityIssuecount;
	}

	public long getJobCityDealcount() {
		return jobCityDealcount;
	}

	public void setJobCityDealcount(long jobCityDealcount) {
		this.jobCityDealcount = jobCityDealcount;
	}

	public long getJobCityFinishcount() {
		return jobCityFinishcount;
	}

	public void setJobCityFinishcount(long jobCityFinishcount) {
		this.jobCityFinishcount = jobCityFinishcount;
	}

	public long getFamilyCityIssuecount() {
		return familyCityIssuecount;
	}

	public void setFamilyCityIssuecount(long familyCityIssuecount) {
		this.familyCityIssuecount = familyCityIssuecount;
	}

	public long getFamilyCityDealcount() {
		return familyCityDealcount;
	}

	public void setFamilyCityDealcount(long familyCityDealcount) {
		this.familyCityDealcount = familyCityDealcount;
	}

	public long getFamilyCityFinishcount() {
		return familyCityFinishcount;
	}

	public void setFamilyCityFinishcount(long familyCityFinishcount) {
		this.familyCityFinishcount = familyCityFinishcount;
	}

	public long getPatientCityIssuecount() {
		return patientCityIssuecount;
	}

	public void setPatientCityIssuecount(long patientCityIssuecount) {
		this.patientCityIssuecount = patientCityIssuecount;
	}

	public long getPatientCityDealcount() {
		return patientCityDealcount;
	}

	public void setPatientCityDealcount(long patientCityDealcount) {
		this.patientCityDealcount = patientCityDealcount;
	}

	public long getPatientCityFinishcount() {
		return patientCityFinishcount;
	}

	public void setPatientCityFinishcount(long patientCityFinishcount) {
		this.patientCityFinishcount = patientCityFinishcount;
	}

	public long getGangsCityIssuecount() {
		return gangsCityIssuecount;
	}

	public void setGangsCityIssuecount(long gangsCityIssuecount) {
		this.gangsCityIssuecount = gangsCityIssuecount;
	}

	public long getGangsCityDealcount() {
		return gangsCityDealcount;
	}

	public void setGangsCityDealcount(long gangsCityDealcount) {
		this.gangsCityDealcount = gangsCityDealcount;
	}

	public long getGangsCityFinishcount() {
		return gangsCityFinishcount;
	}

	public void setGangsCityFinishcount(long gangsCityFinishcount) {
		this.gangsCityFinishcount = gangsCityFinishcount;
	}

	public long getIssuelowCityFinishcount() {
		return issuelowCityFinishcount;
	}

	public void setIssuelowCityFinishcount(long issuelowCityFinishcount) {
		this.issuelowCityFinishcount = issuelowCityFinishcount;
	}

	public long getIssuemidCityFinishcount() {
		return issuemidCityFinishcount;
	}

	public void setIssuemidCityFinishcount(long issuemidCityFinishcount) {
		this.issuemidCityFinishcount = issuemidCityFinishcount;
	}

	public long getIssuehighCityFinishcount() {
		return issuehighCityFinishcount;
	}

	public void setIssuehighCityFinishcount(long issuehighCityFinishcount) {
		this.issuehighCityFinishcount = issuehighCityFinishcount;
	}

	public long getIssuehigherCityFinishcount() {
		return issuehigherCityFinishcount;
	}

	public void setIssuehigherCityFinishcount(long issuehigherCityFinishcount) {
		this.issuehigherCityFinishcount = issuehigherCityFinishcount;
	}

	public long getConcentrateCityIssuecount() {
		return concentrateCityIssuecount;
	}

	public void setConcentrateCityIssuecount(long concentrateCityIssuecount) {
		this.concentrateCityIssuecount = concentrateCityIssuecount;
	}

	public long getConcentrateCityDealcount() {
		return concentrateCityDealcount;
	}

	public void setConcentrateCityDealcount(long concentrateCityDealcount) {
		this.concentrateCityDealcount = concentrateCityDealcount;
	}

	public long getConcentrateCityFinishcount() {
		return concentrateCityFinishcount;
	}

	public void setConcentrateCityFinishcount(long concentrateCityFinishcount) {
		this.concentrateCityFinishcount = concentrateCityFinishcount;
	}

	public long getTotalCountyIssuecount() {
		return totalCountyIssuecount;
	}

	public void setTotalCountyIssuecount(long totalCountyIssuecount) {
		this.totalCountyIssuecount = totalCountyIssuecount;
	}

	public long getTotalCountyDealcount() {
		return totalCountyDealcount;
	}

	public void setTotalCountyDealcount(long totalCountyDealcount) {
		this.totalCountyDealcount = totalCountyDealcount;
	}

	public long getTotalCountyFinishcount() {
		return totalCountyFinishcount;
	}

	public void setTotalCountyFinishcount(long totalCountyFinishcount) {
		this.totalCountyFinishcount = totalCountyFinishcount;
	}

	public String getTotalCountyDealrate() {
		return totalCountyDealrate;
	}

	public void setTotalCountyDealrate(String totalCountyDealrate) {
		this.totalCountyDealrate = totalCountyDealrate;
	}

	public String getTotalCountyFinishrate() {
		return totalCountyFinishrate;
	}

	public void setTotalCountyFinishrate(String totalCountyFinishrate) {
		this.totalCountyFinishrate = totalCountyFinishrate;
	}

	public long getReligionCountyIssuecount() {
		return religionCountyIssuecount;
	}

	public void setReligionCountyIssuecount(long religionCountyIssuecount) {
		this.religionCountyIssuecount = religionCountyIssuecount;
	}

	public long getReligionCountyDealcount() {
		return religionCountyDealcount;
	}

	public void setReligionCountyDealcount(long religionCountyDealcount) {
		this.religionCountyDealcount = religionCountyDealcount;
	}

	public long getReligionCountyFinishcount() {
		return religionCountyFinishcount;
	}

	public void setReligionCountyFinishcount(long religionCountyFinishcount) {
		this.religionCountyFinishcount = religionCountyFinishcount;
	}

	public long getSoldierCountyIssuecount() {
		return soldierCountyIssuecount;
	}

	public void setSoldierCountyIssuecount(long soldierCountyIssuecount) {
		this.soldierCountyIssuecount = soldierCountyIssuecount;
	}

	public long getSoldierCountyDealcount() {
		return soldierCountyDealcount;
	}

	public void setSoldierCountyDealcount(long soldierCountyDealcount) {
		this.soldierCountyDealcount = soldierCountyDealcount;
	}

	public long getSoldierCountyFinishcount() {
		return soldierCountyFinishcount;
	}

	public void setSoldierCountyFinishcount(long soldierCountyFinishcount) {
		this.soldierCountyFinishcount = soldierCountyFinishcount;
	}

	public long getRemoveCountyIssuecount() {
		return removeCountyIssuecount;
	}

	public void setRemoveCountyIssuecount(long removeCountyIssuecount) {
		this.removeCountyIssuecount = removeCountyIssuecount;
	}

	public long getRemoveCountyDealcount() {
		return removeCountyDealcount;
	}

	public void setRemoveCountyDealcount(long removeCountyDealcount) {
		this.removeCountyDealcount = removeCountyDealcount;
	}

	public long getLandBoundariesCountyIssuecount() {
		return landBoundariesCountyIssuecount;
	}

	public void setLandBoundariesCountyIssuecount(long landBoundariesCountyIssuecount) {
		this.landBoundariesCountyIssuecount = landBoundariesCountyIssuecount;
	}

	public long getBuildCountyDealcount() {
		return buildCountyDealcount;
	}

	public void setBuildCountyDealcount(long buildCountyDealcount) {
		this.buildCountyDealcount = buildCountyDealcount;
	}

	public long getBuildCountyFinishcount() {
		return buildCountyFinishcount;
	}

	public void setBuildCountyFinishcount(long buildCountyFinishcount) {
		this.buildCountyFinishcount = buildCountyFinishcount;
	}

	public long getAssetsCountyIssuecount() {
		return assetsCountyIssuecount;
	}

	public void setAssetsCountyIssuecount(long assetsCountyIssuecount) {
		this.assetsCountyIssuecount = assetsCountyIssuecount;
	}

	public long getAssetsCountyDealcount() {
		return assetsCountyDealcount;
	}

	public void setAssetsCountyDealcount(long assetsCountyDealcount) {
		this.assetsCountyDealcount = assetsCountyDealcount;
	}

	public long getAssetsCountyFinishcount() {
		return assetsCountyFinishcount;
	}

	public void setAssetsCountyFinishcount(long assetsCountyFinishcount) {
		this.assetsCountyFinishcount = assetsCountyFinishcount;
	}

	public long getEconomyCountyIssuecount() {
		return economyCountyIssuecount;
	}

	public void setEconomyCountyIssuecount(long economyCountyIssuecount) {
		this.economyCountyIssuecount = economyCountyIssuecount;
	}

	public long getEconomyCountyDealcount() {
		return economyCountyDealcount;
	}

	public void setEconomyCountyDealcount(long economyCountyDealcount) {
		this.economyCountyDealcount = economyCountyDealcount;
	}

	public long getEconomyCountyFinishcount() {
		return economyCountyFinishcount;
	}

	public void setEconomyCountyFinishcount(long economyCountyFinishcount) {
		this.economyCountyFinishcount = economyCountyFinishcount;
	}

	public long getLabourCountyIssuecount() {
		return labourCountyIssuecount;
	}

	public void setLabourCountyIssuecount(long labourCountyIssuecount) {
		this.labourCountyIssuecount = labourCountyIssuecount;
	}

	public long getLabourCountyDealcount() {
		return labourCountyDealcount;
	}

	public void setLabourCountyDealcount(long labourCountyDealcount) {
		this.labourCountyDealcount = labourCountyDealcount;
	}

	public long getLabourCountyFinishcount() {
		return labourCountyFinishcount;
	}

	public void setLabourCountyFinishcount(long labourCountyFinishcount) {
		this.labourCountyFinishcount = labourCountyFinishcount;
	}

	public long getLtdCountyIssuecount() {
		return ltdCountyIssuecount;
	}

	public void setLtdCountyIssuecount(long ltdCountyIssuecount) {
		this.ltdCountyIssuecount = ltdCountyIssuecount;
	}

	public long getLtdCountyDealcount() {
		return ltdCountyDealcount;
	}

	public void setLtdCountyDealcount(long ltdCountyDealcount) {
		this.ltdCountyDealcount = ltdCountyDealcount;
	}

	public long getLtdCountyFinishcount() {
		return ltdCountyFinishcount;
	}

	public void setLtdCountyFinishcount(long ltdCountyFinishcount) {
		this.ltdCountyFinishcount = ltdCountyFinishcount;
	}

	public long getEnvironCountyIssuecount() {
		return environCountyIssuecount;
	}

	public void setEnvironCountyIssuecount(long environCountyIssuecount) {
		this.environCountyIssuecount = environCountyIssuecount;
	}

	public long getEnvironCountyDealcount() {
		return environCountyDealcount;
	}

	public void setEnvironCountyDealcount(long environCountyDealcount) {
		this.environCountyDealcount = environCountyDealcount;
	}

	public long getEnvironCountyFinishcount() {
		return environCountyFinishcount;
	}

	public void setEnvironCountyFinishcount(long environCountyFinishcount) {
		this.environCountyFinishcount = environCountyFinishcount;
	}

	public long getJudicialCountyIssuecount() {
		return judicialCountyIssuecount;
	}

	public void setJudicialCountyIssuecount(long judicialCountyIssuecount) {
		this.judicialCountyIssuecount = judicialCountyIssuecount;
	}

	public long getJudicialCountyDealcount() {
		return judicialCountyDealcount;
	}

	public void setJudicialCountyDealcount(long judicialCountyDealcount) {
		this.judicialCountyDealcount = judicialCountyDealcount;
	}

	public long getJudicialCountyFinishcount() {
		return judicialCountyFinishcount;
	}

	public void setJudicialCountyFinishcount(long judicialCountyFinishcount) {
		this.judicialCountyFinishcount = judicialCountyFinishcount;
	}

	public long getAdminCountyIssuecount() {
		return adminCountyIssuecount;
	}

	public void setAdminCountyIssuecount(long adminCountyIssuecount) {
		this.adminCountyIssuecount = adminCountyIssuecount;
	}

	public long getAdminCountyDealcount() {
		return adminCountyDealcount;
	}

	public void setAdminCountyDealcount(long adminCountyDealcount) {
		this.adminCountyDealcount = adminCountyDealcount;
	}

	public long getAdminCountyFinishcount() {
		return adminCountyFinishcount;
	}

	public void setAdminCountyFinishcount(long adminCountyFinishcount) {
		this.adminCountyFinishcount = adminCountyFinishcount;
	}

	public long getSchoolCountyIssuecount() {
		return schoolCountyIssuecount;
	}

	public void setSchoolCountyIssuecount(long schoolCountyIssuecount) {
		this.schoolCountyIssuecount = schoolCountyIssuecount;
	}

	public long getSchoolCountyDealcount() {
		return schoolCountyDealcount;
	}

	public void setSchoolCountyDealcount(long schoolCountyDealcount) {
		this.schoolCountyDealcount = schoolCountyDealcount;
	}

	public long getSchoolCountyFinishcount() {
		return schoolCountyFinishcount;
	}

	public void setSchoolCountyFinishcount(long schoolCountyFinishcount) {
		this.schoolCountyFinishcount = schoolCountyFinishcount;
	}

	public long getSeaCountyIssuecount() {
		return seaCountyIssuecount;
	}

	public void setSeaCountyIssuecount(long seaCountyIssuecount) {
		this.seaCountyIssuecount = seaCountyIssuecount;
	}

	public long getSeaCountyDealcount() {
		return seaCountyDealcount;
	}

	public void setSeaCountyDealcount(long seaCountyDealcount) {
		this.seaCountyDealcount = seaCountyDealcount;
	}

	public long getSeaCountyFinishcount() {
		return seaCountyFinishcount;
	}

	public void setSeaCountyFinishcount(long seaCountyFinishcount) {
		this.seaCountyFinishcount = seaCountyFinishcount;
	}

	public long getCadreCountyIssuecount() {
		return cadreCountyIssuecount;
	}

	public void setCadreCountyIssuecount(long cadreCountyIssuecount) {
		this.cadreCountyIssuecount = cadreCountyIssuecount;
	}

	public long getCadreCountyDealcount() {
		return cadreCountyDealcount;
	}

	public void setCadreCountyDealcount(long cadreCountyDealcount) {
		this.cadreCountyDealcount = cadreCountyDealcount;
	}

	public long getCadreCountyFinishcount() {
		return cadreCountyFinishcount;
	}

	public void setCadreCountyFinishcount(long cadreCountyFinishcount) {
		this.cadreCountyFinishcount = cadreCountyFinishcount;
	}

	public long getVilthCountyIssuecount() {
		return vilthCountyIssuecount;
	}

	public void setVilthCountyIssuecount(long vilthCountyIssuecount) {
		this.vilthCountyIssuecount = vilthCountyIssuecount;
	}

	public long getVilthCountyDealcount() {
		return vilthCountyDealcount;
	}

	public void setVilthCountyDealcount(long vilthCountyDealcount) {
		this.vilthCountyDealcount = vilthCountyDealcount;
	}

	public long getVilthCountyFinishcount() {
		return vilthCountyFinishcount;
	}

	public void setVilthCountyFinishcount(long vilthCountyFinishcount) {
		this.vilthCountyFinishcount = vilthCountyFinishcount;
	}

	public long getJobCountyIssuecount() {
		return jobCountyIssuecount;
	}

	public void setJobCountyIssuecount(long jobCountyIssuecount) {
		this.jobCountyIssuecount = jobCountyIssuecount;
	}

	public long getJobCountyDealcount() {
		return jobCountyDealcount;
	}

	public void setJobCountyDealcount(long jobCountyDealcount) {
		this.jobCountyDealcount = jobCountyDealcount;
	}

	public long getJobCountyFinishcount() {
		return jobCountyFinishcount;
	}

	public void setJobCountyFinishcount(long jobCountyFinishcount) {
		this.jobCountyFinishcount = jobCountyFinishcount;
	}

	public long getFamilyCountyIssuecount() {
		return familyCountyIssuecount;
	}

	public void setFamilyCountyIssuecount(long familyCountyIssuecount) {
		this.familyCountyIssuecount = familyCountyIssuecount;
	}

	public long getFamilyCountyDealcount() {
		return familyCountyDealcount;
	}

	public void setFamilyCountyDealcount(long familyCountyDealcount) {
		this.familyCountyDealcount = familyCountyDealcount;
	}

	public long getFamilyCountyFinishcount() {
		return familyCountyFinishcount;
	}

	public void setFamilyCountyFinishcount(long familyCountyFinishcount) {
		this.familyCountyFinishcount = familyCountyFinishcount;
	}

	public long getPatientCountyIssuecount() {
		return patientCountyIssuecount;
	}

	public void setPatientCountyIssuecount(long patientCountyIssuecount) {
		this.patientCountyIssuecount = patientCountyIssuecount;
	}

	public long getPatientCountyDealcount() {
		return patientCountyDealcount;
	}

	public void setPatientCountyDealcount(long patientCountyDealcount) {
		this.patientCountyDealcount = patientCountyDealcount;
	}

	public long getPatientCountyFinishcount() {
		return patientCountyFinishcount;
	}

	public void setPatientCountyFinishcount(long patientCountyFinishcount) {
		this.patientCountyFinishcount = patientCountyFinishcount;
	}

	public long getGangsCountyIssuecount() {
		return gangsCountyIssuecount;
	}

	public void setGangsCountyIssuecount(long gangsCountyIssuecount) {
		this.gangsCountyIssuecount = gangsCountyIssuecount;
	}

	public long getGangsCountyDealcount() {
		return gangsCountyDealcount;
	}

	public void setGangsCountyDealcount(long gangsCountyDealcount) {
		this.gangsCountyDealcount = gangsCountyDealcount;
	}

	public long getGangsCountyFinishcount() {
		return gangsCountyFinishcount;
	}

	public void setGangsCountyFinishcount(long gangsCountyFinishcount) {
		this.gangsCountyFinishcount = gangsCountyFinishcount;
	}

	public long getIssuelowCountyFinishcount() {
		return issuelowCountyFinishcount;
	}

	public void setIssuelowCountyFinishcount(long issuelowCountyFinishcount) {
		this.issuelowCountyFinishcount = issuelowCountyFinishcount;
	}

	public long getIssuemidCountyFinishcount() {
		return issuemidCountyFinishcount;
	}

	public void setIssuemidCountyFinishcount(long issuemidCountyFinishcount) {
		this.issuemidCountyFinishcount = issuemidCountyFinishcount;
	}

	public long getIssuehighCountyFinishcount() {
		return issuehighCountyFinishcount;
	}

	public void setIssuehighCountyFinishcount(long issuehighCountyFinishcount) {
		this.issuehighCountyFinishcount = issuehighCountyFinishcount;
	}

	public long getIssuehigherCountyFinishcount() {
		return issuehigherCountyFinishcount;
	}

	public void setIssuehigherCountyFinishcount(long issuehigherCountyFinishcount) {
		this.issuehigherCountyFinishcount = issuehigherCountyFinishcount;
	}

	public long getConcentrateCountyIssuecount() {
		return concentrateCountyIssuecount;
	}

	public void setConcentrateCountyIssuecount(long concentrateCountyIssuecount) {
		this.concentrateCountyIssuecount = concentrateCountyIssuecount;
	}

	public long getConcentrateCountyDealcount() {
		return concentrateCountyDealcount;
	}

	public void setConcentrateCountyDealcount(long concentrateCountyDealcount) {
		this.concentrateCountyDealcount = concentrateCountyDealcount;
	}

	public long getConcentrateCountyFinishcount() {
		return concentrateCountyFinishcount;
	}

	public void setConcentrateCountyFinishcount(long concentrateCountyFinishcount) {
		this.concentrateCountyFinishcount = concentrateCountyFinishcount;
	}

	public long getTotalTwonIssuecount() {
		return totalTwonIssuecount;
	}

	public void setTotalTwonIssuecount(long totalTwonIssuecount) {
		this.totalTwonIssuecount = totalTwonIssuecount;
	}

	public long getTotalTwonDealcount() {
		return totalTwonDealcount;
	}

	public void setTotalTwonDealcount(long totalTwonDealcount) {
		this.totalTwonDealcount = totalTwonDealcount;
	}

	public long getTotalTwonFinishcount() {
		return totalTwonFinishcount;
	}

	public void setTotalTwonFinishcount(long totalTwonFinishcount) {
		this.totalTwonFinishcount = totalTwonFinishcount;
	}

	public String getTotalTwonDealrate() {
		return totalTwonDealrate;
	}

	public void setTotalTwonDealrate(String totalTwonDealrate) {
		this.totalTwonDealrate = totalTwonDealrate;
	}

	public String getTotalTwonFinishrate() {
		return totalTwonFinishrate;
	}

	public void setTotalTwonFinishrate(String totalTwonFinishrate) {
		this.totalTwonFinishrate = totalTwonFinishrate;
	}

	public long getReligionTwonIssuecount() {
		return religionTwonIssuecount;
	}

	public void setReligionTwonIssuecount(long religionTwonIssuecount) {
		this.religionTwonIssuecount = religionTwonIssuecount;
	}

	public long getReligionTwonDealcount() {
		return religionTwonDealcount;
	}

	public void setReligionTwonDealcount(long religionTwonDealcount) {
		this.religionTwonDealcount = religionTwonDealcount;
	}

	public long getReligionTwonFinishcount() {
		return religionTwonFinishcount;
	}

	public void setReligionTwonFinishcount(long religionTwonFinishcount) {
		this.religionTwonFinishcount = religionTwonFinishcount;
	}

	public long getSoldierTwonIssuecount() {
		return soldierTwonIssuecount;
	}

	public void setSoldierTwonIssuecount(long soldierTwonIssuecount) {
		this.soldierTwonIssuecount = soldierTwonIssuecount;
	}

	public long getSoldierTwonDealcount() {
		return soldierTwonDealcount;
	}

	public void setSoldierTwonDealcount(long soldierTwonDealcount) {
		this.soldierTwonDealcount = soldierTwonDealcount;
	}

	public long getSoldierTwonFinishcount() {
		return soldierTwonFinishcount;
	}

	public void setSoldierTwonFinishcount(long soldierTwonFinishcount) {
		this.soldierTwonFinishcount = soldierTwonFinishcount;
	}

	public long getRemoveTwonIssuecount() {
		return removeTwonIssuecount;
	}

	public void setRemoveTwonIssuecount(long removeTwonIssuecount) {
		this.removeTwonIssuecount = removeTwonIssuecount;
	}

	public long getRemoveTwonDealcount() {
		return removeTwonDealcount;
	}

	public void setRemoveTwonDealcount(long removeTwonDealcount) {
		this.removeTwonDealcount = removeTwonDealcount;
	}

	public long getLandBoundariesTwonIssuecount() {
		return landBoundariesTwonIssuecount;
	}

	public void setLandBoundariesTwonIssuecount(long landBoundariesTwonIssuecount) {
		this.landBoundariesTwonIssuecount = landBoundariesTwonIssuecount;
	}

	public long getBuildTwonDealcount() {
		return buildTwonDealcount;
	}

	public void setBuildTwonDealcount(long buildTwonDealcount) {
		this.buildTwonDealcount = buildTwonDealcount;
	}

	public long getBuildTwonFinishcount() {
		return buildTwonFinishcount;
	}

	public void setBuildTwonFinishcount(long buildTwonFinishcount) {
		this.buildTwonFinishcount = buildTwonFinishcount;
	}

	public long getAssetsTwonIssuecount() {
		return assetsTwonIssuecount;
	}

	public void setAssetsTwonIssuecount(long assetsTwonIssuecount) {
		this.assetsTwonIssuecount = assetsTwonIssuecount;
	}

	public long getAssetsTwonDealcount() {
		return assetsTwonDealcount;
	}

	public void setAssetsTwonDealcount(long assetsTwonDealcount) {
		this.assetsTwonDealcount = assetsTwonDealcount;
	}

	public long getAssetsTwonFinishcount() {
		return assetsTwonFinishcount;
	}

	public void setAssetsTwonFinishcount(long assetsTwonFinishcount) {
		this.assetsTwonFinishcount = assetsTwonFinishcount;
	}

	public long getEconomyTwonIssuecount() {
		return economyTwonIssuecount;
	}

	public void setEconomyTwonIssuecount(long economyTwonIssuecount) {
		this.economyTwonIssuecount = economyTwonIssuecount;
	}

	public long getEconomyTwonDealcount() {
		return economyTwonDealcount;
	}

	public void setEconomyTwonDealcount(long economyTwonDealcount) {
		this.economyTwonDealcount = economyTwonDealcount;
	}

	public long getEconomyTwonFinishcount() {
		return economyTwonFinishcount;
	}

	public void setEconomyTwonFinishcount(long economyTwonFinishcount) {
		this.economyTwonFinishcount = economyTwonFinishcount;
	}

	public long getLabourTwonIssuecount() {
		return labourTwonIssuecount;
	}

	public void setLabourTwonIssuecount(long labourTwonIssuecount) {
		this.labourTwonIssuecount = labourTwonIssuecount;
	}

	public long getLabourTwonDealcount() {
		return labourTwonDealcount;
	}

	public void setLabourTwonDealcount(long labourTwonDealcount) {
		this.labourTwonDealcount = labourTwonDealcount;
	}

	public long getLabourTwonFinishcount() {
		return labourTwonFinishcount;
	}

	public void setLabourTwonFinishcount(long labourTwonFinishcount) {
		this.labourTwonFinishcount = labourTwonFinishcount;
	}

	public long getLtdTwonIssuecount() {
		return ltdTwonIssuecount;
	}

	public void setLtdTwonIssuecount(long ltdTwonIssuecount) {
		this.ltdTwonIssuecount = ltdTwonIssuecount;
	}

	public long getLtdTwonDealcount() {
		return ltdTwonDealcount;
	}

	public void setLtdTwonDealcount(long ltdTwonDealcount) {
		this.ltdTwonDealcount = ltdTwonDealcount;
	}

	public long getLtdTwonFinishcount() {
		return ltdTwonFinishcount;
	}

	public void setLtdTwonFinishcount(long ltdTwonFinishcount) {
		this.ltdTwonFinishcount = ltdTwonFinishcount;
	}

	public long getEnvironTwonIssuecount() {
		return environTwonIssuecount;
	}

	public void setEnvironTwonIssuecount(long environTwonIssuecount) {
		this.environTwonIssuecount = environTwonIssuecount;
	}

	public long getEnvironTwonDealcount() {
		return environTwonDealcount;
	}

	public void setEnvironTwonDealcount(long environTwonDealcount) {
		this.environTwonDealcount = environTwonDealcount;
	}

	public long getEnvironTwonFinishcount() {
		return environTwonFinishcount;
	}

	public void setEnvironTwonFinishcount(long environTwonFinishcount) {
		this.environTwonFinishcount = environTwonFinishcount;
	}

	public long getJudicialTwonIssuecount() {
		return judicialTwonIssuecount;
	}

	public void setJudicialTwonIssuecount(long judicialTwonIssuecount) {
		this.judicialTwonIssuecount = judicialTwonIssuecount;
	}

	public long getJudicialTwonDealcount() {
		return judicialTwonDealcount;
	}

	public void setJudicialTwonDealcount(long judicialTwonDealcount) {
		this.judicialTwonDealcount = judicialTwonDealcount;
	}

	public long getJudicialTwonFinishcount() {
		return judicialTwonFinishcount;
	}

	public void setJudicialTwonFinishcount(long judicialTwonFinishcount) {
		this.judicialTwonFinishcount = judicialTwonFinishcount;
	}

	public long getAdminTwonIssuecount() {
		return adminTwonIssuecount;
	}

	public void setAdminTwonIssuecount(long adminTwonIssuecount) {
		this.adminTwonIssuecount = adminTwonIssuecount;
	}

	public long getAdminTwonDealcount() {
		return adminTwonDealcount;
	}

	public void setAdminTwonDealcount(long adminTwonDealcount) {
		this.adminTwonDealcount = adminTwonDealcount;
	}

	public long getAdminTwonFinishcount() {
		return adminTwonFinishcount;
	}

	public void setAdminTwonFinishcount(long adminTwonFinishcount) {
		this.adminTwonFinishcount = adminTwonFinishcount;
	}

	public long getSchoolTwonIssuecount() {
		return schoolTwonIssuecount;
	}

	public void setSchoolTwonIssuecount(long schoolTwonIssuecount) {
		this.schoolTwonIssuecount = schoolTwonIssuecount;
	}

	public long getSchoolTwonDealcount() {
		return schoolTwonDealcount;
	}

	public void setSchoolTwonDealcount(long schoolTwonDealcount) {
		this.schoolTwonDealcount = schoolTwonDealcount;
	}

	public long getSchoolTwonFinishcount() {
		return schoolTwonFinishcount;
	}

	public void setSchoolTwonFinishcount(long schoolTwonFinishcount) {
		this.schoolTwonFinishcount = schoolTwonFinishcount;
	}

	public long getSeaTwonIssuecount() {
		return seaTwonIssuecount;
	}

	public void setSeaTwonIssuecount(long seaTwonIssuecount) {
		this.seaTwonIssuecount = seaTwonIssuecount;
	}

	public long getSeaTwonDealcount() {
		return seaTwonDealcount;
	}

	public void setSeaTwonDealcount(long seaTwonDealcount) {
		this.seaTwonDealcount = seaTwonDealcount;
	}

	public long getSeaTwonFinishcount() {
		return seaTwonFinishcount;
	}

	public void setSeaTwonFinishcount(long seaTwonFinishcount) {
		this.seaTwonFinishcount = seaTwonFinishcount;
	}

	public long getCadreTwonIssuecount() {
		return cadreTwonIssuecount;
	}

	public void setCadreTwonIssuecount(long cadreTwonIssuecount) {
		this.cadreTwonIssuecount = cadreTwonIssuecount;
	}

	public long getCadreTwonDealcount() {
		return cadreTwonDealcount;
	}

	public void setCadreTwonDealcount(long cadreTwonDealcount) {
		this.cadreTwonDealcount = cadreTwonDealcount;
	}

	public long getCadreTwonFinishcount() {
		return cadreTwonFinishcount;
	}

	public void setCadreTwonFinishcount(long cadreTwonFinishcount) {
		this.cadreTwonFinishcount = cadreTwonFinishcount;
	}

	public long getVilthTwonIssuecount() {
		return vilthTwonIssuecount;
	}

	public void setVilthTwonIssuecount(long vilthTwonIssuecount) {
		this.vilthTwonIssuecount = vilthTwonIssuecount;
	}

	public long getVilthTwonDealcount() {
		return vilthTwonDealcount;
	}

	public void setVilthTwonDealcount(long vilthTwonDealcount) {
		this.vilthTwonDealcount = vilthTwonDealcount;
	}

	public long getVilthTwonFinishcount() {
		return vilthTwonFinishcount;
	}

	public void setVilthTwonFinishcount(long vilthTwonFinishcount) {
		this.vilthTwonFinishcount = vilthTwonFinishcount;
	}

	public long getJobTwonIssuecount() {
		return jobTwonIssuecount;
	}

	public void setJobTwonIssuecount(long jobTwonIssuecount) {
		this.jobTwonIssuecount = jobTwonIssuecount;
	}

	public long getJobTwonDealcount() {
		return jobTwonDealcount;
	}

	public void setJobTwonDealcount(long jobTwonDealcount) {
		this.jobTwonDealcount = jobTwonDealcount;
	}

	public long getJobTwonFinishcount() {
		return jobTwonFinishcount;
	}

	public void setJobTwonFinishcount(long jobTwonFinishcount) {
		this.jobTwonFinishcount = jobTwonFinishcount;
	}

	public long getFamilyTwonIssuecount() {
		return familyTwonIssuecount;
	}

	public void setFamilyTwonIssuecount(long familyTwonIssuecount) {
		this.familyTwonIssuecount = familyTwonIssuecount;
	}

	public long getFamilyTwonDealcount() {
		return familyTwonDealcount;
	}

	public void setFamilyTwonDealcount(long familyTwonDealcount) {
		this.familyTwonDealcount = familyTwonDealcount;
	}

	public long getFamilyTwonFinishcount() {
		return familyTwonFinishcount;
	}

	public void setFamilyTwonFinishcount(long familyTwonFinishcount) {
		this.familyTwonFinishcount = familyTwonFinishcount;
	}

	public long getPatientTwonIssuecount() {
		return patientTwonIssuecount;
	}

	public void setPatientTwonIssuecount(long patientTwonIssuecount) {
		this.patientTwonIssuecount = patientTwonIssuecount;
	}

	public long getPatientTwonDealcount() {
		return patientTwonDealcount;
	}

	public void setPatientTwonDealcount(long patientTwonDealcount) {
		this.patientTwonDealcount = patientTwonDealcount;
	}

	public long getPatientTwonFinishcount() {
		return patientTwonFinishcount;
	}

	public void setPatientTwonFinishcount(long patientTwonFinishcount) {
		this.patientTwonFinishcount = patientTwonFinishcount;
	}

	public long getGangsTwonIssuecount() {
		return gangsTwonIssuecount;
	}

	public void setGangsTwonIssuecount(long gangsTwonIssuecount) {
		this.gangsTwonIssuecount = gangsTwonIssuecount;
	}

	public long getGangsTwonDealcount() {
		return gangsTwonDealcount;
	}

	public void setGangsTwonDealcount(long gangsTwonDealcount) {
		this.gangsTwonDealcount = gangsTwonDealcount;
	}

	public long getGangsTwonFinishcount() {
		return gangsTwonFinishcount;
	}

	public void setGangsTwonFinishcount(long gangsTwonFinishcount) {
		this.gangsTwonFinishcount = gangsTwonFinishcount;
	}

	public long getIssuelowTwonFinishcount() {
		return issuelowTwonFinishcount;
	}

	public void setIssuelowTwonFinishcount(long issuelowTwonFinishcount) {
		this.issuelowTwonFinishcount = issuelowTwonFinishcount;
	}

	public long getIssuemidTwonFinishcount() {
		return issuemidTwonFinishcount;
	}

	public void setIssuemidTwonFinishcount(long issuemidTwonFinishcount) {
		this.issuemidTwonFinishcount = issuemidTwonFinishcount;
	}

	public long getIssuehighTwonFinishcount() {
		return issuehighTwonFinishcount;
	}

	public void setIssuehighTwonFinishcount(long issuehighTwonFinishcount) {
		this.issuehighTwonFinishcount = issuehighTwonFinishcount;
	}

	public long getIssuehigherTwonFinishcount() {
		return issuehigherTwonFinishcount;
	}

	public void setIssuehigherTwonFinishcount(long issuehigherTwonFinishcount) {
		this.issuehigherTwonFinishcount = issuehigherTwonFinishcount;
	}

	public long getConcentrateTwonIssuecount() {
		return concentrateTwonIssuecount;
	}

	public void setConcentrateTwonIssuecount(long concentrateTwonIssuecount) {
		this.concentrateTwonIssuecount = concentrateTwonIssuecount;
	}

	public long getConcentrateTwonDealcount() {
		return concentrateTwonDealcount;
	}

	public void setConcentrateTwonDealcount(long concentrateTwonDealcount) {
		this.concentrateTwonDealcount = concentrateTwonDealcount;
	}

	public long getConcentrateTwonFinishcount() {
		return concentrateTwonFinishcount;
	}

	public void setConcentrateTwonFinishcount(long concentrateTwonFinishcount) {
		this.concentrateTwonFinishcount = concentrateTwonFinishcount;
	}

	public long getTotalVilIssuecount() {
		return totalVilIssuecount;
	}

	public void setTotalVilIssuecount(long totalVilIssuecount) {
		this.totalVilIssuecount = totalVilIssuecount;
	}

	public long getTotalVilDealcount() {
		return totalVilDealcount;
	}

	public void setTotalVilDealcount(long totalVilDealcount) {
		this.totalVilDealcount = totalVilDealcount;
	}

	public long getTotalVilFinishcount() {
		return totalVilFinishcount;
	}

	public void setTotalVilFinishcount(long totalVilFinishcount) {
		this.totalVilFinishcount = totalVilFinishcount;
	}

	public String getTotalVilDealrate() {
		return totalVilDealrate;
	}

	public void setTotalVilDealrate(String totalVilDealrate) {
		this.totalVilDealrate = totalVilDealrate;
	}

	public String getTotalVilFinishrate() {
		return totalVilFinishrate;
	}

	public void setTotalVilFinishrate(String totalVilFinishrate) {
		this.totalVilFinishrate = totalVilFinishrate;
	}

	public long getReligionVilIssuecount() {
		return religionVilIssuecount;
	}

	public void setReligionVilIssuecount(long religionVilIssuecount) {
		this.religionVilIssuecount = religionVilIssuecount;
	}

	public long getReligionVilDealcount() {
		return religionVilDealcount;
	}

	public void setReligionVilDealcount(long religionVilDealcount) {
		this.religionVilDealcount = religionVilDealcount;
	}

	public long getReligionVilFinishcount() {
		return religionVilFinishcount;
	}

	public void setReligionVilFinishcount(long religionVilFinishcount) {
		this.religionVilFinishcount = religionVilFinishcount;
	}

	public long getSoldierVilIssuecount() {
		return soldierVilIssuecount;
	}

	public void setSoldierVilIssuecount(long soldierVilIssuecount) {
		this.soldierVilIssuecount = soldierVilIssuecount;
	}

	public long getSoldierVilDealcount() {
		return soldierVilDealcount;
	}

	public void setSoldierVilDealcount(long soldierVilDealcount) {
		this.soldierVilDealcount = soldierVilDealcount;
	}

	public long getSoldierVilFinishcount() {
		return soldierVilFinishcount;
	}

	public void setSoldierVilFinishcount(long soldierVilFinishcount) {
		this.soldierVilFinishcount = soldierVilFinishcount;
	}

	public long getRemoveVilIssuecount() {
		return removeVilIssuecount;
	}

	public void setRemoveVilIssuecount(long removeVilIssuecount) {
		this.removeVilIssuecount = removeVilIssuecount;
	}

	public long getRemoveVilDealcount() {
		return removeVilDealcount;
	}

	public void setRemoveVilDealcount(long removeVilDealcount) {
		this.removeVilDealcount = removeVilDealcount;
	}

	public long getLandBoundariesVilIssuecount() {
		return landBoundariesVilIssuecount;
	}

	public void setLandBoundariesVilIssuecount(long landBoundariesVilIssuecount) {
		this.landBoundariesVilIssuecount = landBoundariesVilIssuecount;
	}

	public long getBuildVilDealcount() {
		return buildVilDealcount;
	}

	public void setBuildVilDealcount(long buildVilDealcount) {
		this.buildVilDealcount = buildVilDealcount;
	}

	public long getBuildVilFinishcount() {
		return buildVilFinishcount;
	}

	public void setBuildVilFinishcount(long buildVilFinishcount) {
		this.buildVilFinishcount = buildVilFinishcount;
	}

	public long getAssetsVilIssuecount() {
		return assetsVilIssuecount;
	}

	public void setAssetsVilIssuecount(long assetsVilIssuecount) {
		this.assetsVilIssuecount = assetsVilIssuecount;
	}

	public long getAssetsVilDealcount() {
		return assetsVilDealcount;
	}

	public void setAssetsVilDealcount(long assetsVilDealcount) {
		this.assetsVilDealcount = assetsVilDealcount;
	}

	public long getAssetsVilFinishcount() {
		return assetsVilFinishcount;
	}

	public void setAssetsVilFinishcount(long assetsVilFinishcount) {
		this.assetsVilFinishcount = assetsVilFinishcount;
	}

	public long getEconomyVilIssuecount() {
		return economyVilIssuecount;
	}

	public void setEconomyVilIssuecount(long economyVilIssuecount) {
		this.economyVilIssuecount = economyVilIssuecount;
	}

	public long getEconomyVilDealcount() {
		return economyVilDealcount;
	}

	public void setEconomyVilDealcount(long economyVilDealcount) {
		this.economyVilDealcount = economyVilDealcount;
	}

	public long getEconomyVilFinishcount() {
		return economyVilFinishcount;
	}

	public void setEconomyVilFinishcount(long economyVilFinishcount) {
		this.economyVilFinishcount = economyVilFinishcount;
	}

	public long getLabourVilIssuecount() {
		return labourVilIssuecount;
	}

	public void setLabourVilIssuecount(long labourVilIssuecount) {
		this.labourVilIssuecount = labourVilIssuecount;
	}

	public long getLabourVilDealcount() {
		return labourVilDealcount;
	}

	public void setLabourVilDealcount(long labourVilDealcount) {
		this.labourVilDealcount = labourVilDealcount;
	}

	public long getLabourVilFinishcount() {
		return labourVilFinishcount;
	}

	public void setLabourVilFinishcount(long labourVilFinishcount) {
		this.labourVilFinishcount = labourVilFinishcount;
	}

	public long getLtdVilIssuecount() {
		return ltdVilIssuecount;
	}

	public void setLtdVilIssuecount(long ltdVilIssuecount) {
		this.ltdVilIssuecount = ltdVilIssuecount;
	}

	public long getLtdVilDealcount() {
		return ltdVilDealcount;
	}

	public void setLtdVilDealcount(long ltdVilDealcount) {
		this.ltdVilDealcount = ltdVilDealcount;
	}

	public long getLtdVilFinishcount() {
		return ltdVilFinishcount;
	}

	public void setLtdVilFinishcount(long ltdVilFinishcount) {
		this.ltdVilFinishcount = ltdVilFinishcount;
	}

	public long getEnvironVilIssuecount() {
		return environVilIssuecount;
	}

	public void setEnvironVilIssuecount(long environVilIssuecount) {
		this.environVilIssuecount = environVilIssuecount;
	}

	public long getEnvironVilDealcount() {
		return environVilDealcount;
	}

	public void setEnvironVilDealcount(long environVilDealcount) {
		this.environVilDealcount = environVilDealcount;
	}

	public long getEnvironVilFinishcount() {
		return environVilFinishcount;
	}

	public void setEnvironVilFinishcount(long environVilFinishcount) {
		this.environVilFinishcount = environVilFinishcount;
	}

	public long getJudicialVilIssuecount() {
		return judicialVilIssuecount;
	}

	public void setJudicialVilIssuecount(long judicialVilIssuecount) {
		this.judicialVilIssuecount = judicialVilIssuecount;
	}

	public long getJudicialVilDealcount() {
		return judicialVilDealcount;
	}

	public void setJudicialVilDealcount(long judicialVilDealcount) {
		this.judicialVilDealcount = judicialVilDealcount;
	}

	public long getJudicialVilFinishcount() {
		return judicialVilFinishcount;
	}

	public void setJudicialVilFinishcount(long judicialVilFinishcount) {
		this.judicialVilFinishcount = judicialVilFinishcount;
	}

	public long getAdminVilIssuecount() {
		return adminVilIssuecount;
	}

	public void setAdminVilIssuecount(long adminVilIssuecount) {
		this.adminVilIssuecount = adminVilIssuecount;
	}

	public long getAdminVilDealcount() {
		return adminVilDealcount;
	}

	public void setAdminVilDealcount(long adminVilDealcount) {
		this.adminVilDealcount = adminVilDealcount;
	}

	public long getAdminVilFinishcount() {
		return adminVilFinishcount;
	}

	public void setAdminVilFinishcount(long adminVilFinishcount) {
		this.adminVilFinishcount = adminVilFinishcount;
	}

	public long getSchoolVilIssuecount() {
		return schoolVilIssuecount;
	}

	public void setSchoolVilIssuecount(long schoolVilIssuecount) {
		this.schoolVilIssuecount = schoolVilIssuecount;
	}

	public long getSchoolVilDealcount() {
		return schoolVilDealcount;
	}

	public void setSchoolVilDealcount(long schoolVilDealcount) {
		this.schoolVilDealcount = schoolVilDealcount;
	}

	public long getSchoolVilFinishcount() {
		return schoolVilFinishcount;
	}

	public void setSchoolVilFinishcount(long schoolVilFinishcount) {
		this.schoolVilFinishcount = schoolVilFinishcount;
	}

	public long getSeaVilIssuecount() {
		return seaVilIssuecount;
	}

	public void setSeaVilIssuecount(long seaVilIssuecount) {
		this.seaVilIssuecount = seaVilIssuecount;
	}

	public long getSeaVilDealcount() {
		return seaVilDealcount;
	}

	public void setSeaVilDealcount(long seaVilDealcount) {
		this.seaVilDealcount = seaVilDealcount;
	}

	public long getSeaVilFinishcount() {
		return seaVilFinishcount;
	}

	public void setSeaVilFinishcount(long seaVilFinishcount) {
		this.seaVilFinishcount = seaVilFinishcount;
	}

	public long getCadreVilIssuecount() {
		return cadreVilIssuecount;
	}

	public void setCadreVilIssuecount(long cadreVilIssuecount) {
		this.cadreVilIssuecount = cadreVilIssuecount;
	}

	public long getCadreVilDealcount() {
		return cadreVilDealcount;
	}

	public void setCadreVilDealcount(long cadreVilDealcount) {
		this.cadreVilDealcount = cadreVilDealcount;
	}

	public long getCadreVilFinishcount() {
		return cadreVilFinishcount;
	}

	public void setCadreVilFinishcount(long cadreVilFinishcount) {
		this.cadreVilFinishcount = cadreVilFinishcount;
	}

	public long getVilthVilIssuecount() {
		return vilthVilIssuecount;
	}

	public void setVilthVilIssuecount(long vilthVilIssuecount) {
		this.vilthVilIssuecount = vilthVilIssuecount;
	}

	public long getVilthVilDealcount() {
		return vilthVilDealcount;
	}

	public void setVilthVilDealcount(long vilthVilDealcount) {
		this.vilthVilDealcount = vilthVilDealcount;
	}

	public long getVilthVilFinishcount() {
		return vilthVilFinishcount;
	}

	public void setVilthVilFinishcount(long vilthVilFinishcount) {
		this.vilthVilFinishcount = vilthVilFinishcount;
	}

	public long getJobVilIssuecount() {
		return jobVilIssuecount;
	}

	public void setJobVilIssuecount(long jobVilIssuecount) {
		this.jobVilIssuecount = jobVilIssuecount;
	}

	public long getJobVilDealcount() {
		return jobVilDealcount;
	}

	public void setJobVilDealcount(long jobVilDealcount) {
		this.jobVilDealcount = jobVilDealcount;
	}

	public long getJobVilFinishcount() {
		return jobVilFinishcount;
	}

	public void setJobVilFinishcount(long jobVilFinishcount) {
		this.jobVilFinishcount = jobVilFinishcount;
	}

	public long getFamilyVilIssuecount() {
		return familyVilIssuecount;
	}

	public void setFamilyVilIssuecount(long familyVilIssuecount) {
		this.familyVilIssuecount = familyVilIssuecount;
	}

	public long getFamilyVilDealcount() {
		return familyVilDealcount;
	}

	public void setFamilyVilDealcount(long familyVilDealcount) {
		this.familyVilDealcount = familyVilDealcount;
	}

	public long getFamilyVilFinishcount() {
		return familyVilFinishcount;
	}

	public void setFamilyVilFinishcount(long familyVilFinishcount) {
		this.familyVilFinishcount = familyVilFinishcount;
	}

	public long getPatientVilIssuecount() {
		return patientVilIssuecount;
	}

	public void setPatientVilIssuecount(long patientVilIssuecount) {
		this.patientVilIssuecount = patientVilIssuecount;
	}

	public long getPatientVilDealcount() {
		return patientVilDealcount;
	}

	public void setPatientVilDealcount(long patientVilDealcount) {
		this.patientVilDealcount = patientVilDealcount;
	}

	public long getPatientVilFinishcount() {
		return patientVilFinishcount;
	}

	public void setPatientVilFinishcount(long patientVilFinishcount) {
		this.patientVilFinishcount = patientVilFinishcount;
	}

	public long getGangsVilIssuecount() {
		return gangsVilIssuecount;
	}

	public void setGangsVilIssuecount(long gangsVilIssuecount) {
		this.gangsVilIssuecount = gangsVilIssuecount;
	}

	public long getGangsVilDealcount() {
		return gangsVilDealcount;
	}

	public void setGangsVilDealcount(long gangsVilDealcount) {
		this.gangsVilDealcount = gangsVilDealcount;
	}

	public long getGangsVilFinishcount() {
		return gangsVilFinishcount;
	}

	public void setGangsVilFinishcount(long gangsVilFinishcount) {
		this.gangsVilFinishcount = gangsVilFinishcount;
	}

	public long getIssuelowVilFinishcount() {
		return issuelowVilFinishcount;
	}

	public void setIssuelowVilFinishcount(long issuelowVilFinishcount) {
		this.issuelowVilFinishcount = issuelowVilFinishcount;
	}

	public long getIssuemidVilFinishcount() {
		return issuemidVilFinishcount;
	}

	public void setIssuemidVilFinishcount(long issuemidVilFinishcount) {
		this.issuemidVilFinishcount = issuemidVilFinishcount;
	}

	public long getIssuehighVilFinishcount() {
		return issuehighVilFinishcount;
	}

	public void setIssuehighVilFinishcount(long issuehighVilFinishcount) {
		this.issuehighVilFinishcount = issuehighVilFinishcount;
	}

	public long getIssuehigherVilFinishcount() {
		return issuehigherVilFinishcount;
	}

	public void setIssuehigherVilFinishcount(long issuehigherVilFinishcount) {
		this.issuehigherVilFinishcount = issuehigherVilFinishcount;
	}

	public long getConcentrateVilIssuecount() {
		return concentrateVilIssuecount;
	}

	public void setConcentrateVilIssuecount(long concentrateVilIssuecount) {
		this.concentrateVilIssuecount = concentrateVilIssuecount;
	}

	public long getConcentrateVilDealcount() {
		return concentrateVilDealcount;
	}

	public void setConcentrateVilDealcount(long concentrateVilDealcount) {
		this.concentrateVilDealcount = concentrateVilDealcount;
	}

	public long getConcentrateVilFinishcount() {
		return concentrateVilFinishcount;
	}

	public void setConcentrateVilFinishcount(long concentrateVilFinishcount) {
		this.concentrateVilFinishcount = concentrateVilFinishcount;
	}

	public long getTotalUnIssuecount() {
		return totalUnIssuecount;
	}

	public void setTotalUnIssuecount(long totalUnIssuecount) {
		this.totalUnIssuecount = totalUnIssuecount;
	}

	public long getTotalUnDealcount() {
		return totalUnDealcount;
	}

	public void setTotalUnDealcount(long totalUnDealcount) {
		this.totalUnDealcount = totalUnDealcount;
	}

	public long getTotalUnFinishcount() {
		return totalUnFinishcount;
	}

	public void setTotalUnFinishcount(long totalUnFinishcount) {
		this.totalUnFinishcount = totalUnFinishcount;
	}

	public String getTotalUnDealrate() {
		return totalUnDealrate;
	}

	public void setTotalUnDealrate(String totalUnDealrate) {
		this.totalUnDealrate = totalUnDealrate;
	}

	public String getTotalUnFinishrate() {
		return totalUnFinishrate;
	}

	public void setTotalUnFinishrate(String totalUnFinishrate) {
		this.totalUnFinishrate = totalUnFinishrate;
	}

	public long getReligionUnIssuecount() {
		return religionUnIssuecount;
	}

	public void setReligionUnIssuecount(long religionUnIssuecount) {
		this.religionUnIssuecount = religionUnIssuecount;
	}

	public long getReligionUnDealcount() {
		return religionUnDealcount;
	}

	public void setReligionUnDealcount(long religionUnDealcount) {
		this.religionUnDealcount = religionUnDealcount;
	}

	public long getReligionUnFinishcount() {
		return religionUnFinishcount;
	}

	public void setReligionUnFinishcount(long religionUnFinishcount) {
		this.religionUnFinishcount = religionUnFinishcount;
	}

	public long getSoldierUnIssuecount() {
		return soldierUnIssuecount;
	}

	public void setSoldierUnIssuecount(long soldierUnIssuecount) {
		this.soldierUnIssuecount = soldierUnIssuecount;
	}

	public long getSoldierUnDealcount() {
		return soldierUnDealcount;
	}

	public void setSoldierUnDealcount(long soldierUnDealcount) {
		this.soldierUnDealcount = soldierUnDealcount;
	}

	public long getSoldierUnFinishcount() {
		return soldierUnFinishcount;
	}

	public void setSoldierUnFinishcount(long soldierUnFinishcount) {
		this.soldierUnFinishcount = soldierUnFinishcount;
	}

	public long getRemoveUnIssuecount() {
		return removeUnIssuecount;
	}

	public void setRemoveUnIssuecount(long removeUnIssuecount) {
		this.removeUnIssuecount = removeUnIssuecount;
	}

	public long getRemoveUnDealcount() {
		return removeUnDealcount;
	}

	public void setRemoveUnDealcount(long removeUnDealcount) {
		this.removeUnDealcount = removeUnDealcount;
	}

	public long getLandBoundariesUnIssuecount() {
		return landBoundariesUnIssuecount;
	}

	public void setLandBoundariesUnIssuecount(long landBoundariesUnIssuecount) {
		this.landBoundariesUnIssuecount = landBoundariesUnIssuecount;
	}

	public long getBuildUnDealcount() {
		return buildUnDealcount;
	}

	public void setBuildUnDealcount(long buildUnDealcount) {
		this.buildUnDealcount = buildUnDealcount;
	}

	public long getBuildUnFinishcount() {
		return buildUnFinishcount;
	}

	public void setBuildUnFinishcount(long buildUnFinishcount) {
		this.buildUnFinishcount = buildUnFinishcount;
	}

	public long getAssetsUnIssuecount() {
		return assetsUnIssuecount;
	}

	public void setAssetsUnIssuecount(long assetsUnIssuecount) {
		this.assetsUnIssuecount = assetsUnIssuecount;
	}

	public long getAssetsUnDealcount() {
		return assetsUnDealcount;
	}

	public void setAssetsUnDealcount(long assetsUnDealcount) {
		this.assetsUnDealcount = assetsUnDealcount;
	}

	public long getAssetsUnFinishcount() {
		return assetsUnFinishcount;
	}

	public void setAssetsUnFinishcount(long assetsUnFinishcount) {
		this.assetsUnFinishcount = assetsUnFinishcount;
	}

	public long getEconomyUnIssuecount() {
		return economyUnIssuecount;
	}

	public void setEconomyUnIssuecount(long economyUnIssuecount) {
		this.economyUnIssuecount = economyUnIssuecount;
	}

	public long getEconomyUnDealcount() {
		return economyUnDealcount;
	}

	public void setEconomyUnDealcount(long economyUnDealcount) {
		this.economyUnDealcount = economyUnDealcount;
	}

	public long getEconomyUnFinishcount() {
		return economyUnFinishcount;
	}

	public void setEconomyUnFinishcount(long economyUnFinishcount) {
		this.economyUnFinishcount = economyUnFinishcount;
	}

	public long getLabourUnIssuecount() {
		return labourUnIssuecount;
	}

	public void setLabourUnIssuecount(long labourUnIssuecount) {
		this.labourUnIssuecount = labourUnIssuecount;
	}

	public long getLabourUnDealcount() {
		return labourUnDealcount;
	}

	public void setLabourUnDealcount(long labourUnDealcount) {
		this.labourUnDealcount = labourUnDealcount;
	}

	public long getLabourUnFinishcount() {
		return labourUnFinishcount;
	}

	public void setLabourUnFinishcount(long labourUnFinishcount) {
		this.labourUnFinishcount = labourUnFinishcount;
	}

	public long getLtdUnIssuecount() {
		return ltdUnIssuecount;
	}

	public void setLtdUnIssuecount(long ltdUnIssuecount) {
		this.ltdUnIssuecount = ltdUnIssuecount;
	}

	public long getLtdUnDealcount() {
		return ltdUnDealcount;
	}

	public void setLtdUnDealcount(long ltdUnDealcount) {
		this.ltdUnDealcount = ltdUnDealcount;
	}

	public long getLtdUnFinishcount() {
		return ltdUnFinishcount;
	}

	public void setLtdUnFinishcount(long ltdUnFinishcount) {
		this.ltdUnFinishcount = ltdUnFinishcount;
	}

	public long getEnvironUnIssuecount() {
		return environUnIssuecount;
	}

	public void setEnvironUnIssuecount(long environUnIssuecount) {
		this.environUnIssuecount = environUnIssuecount;
	}

	public long getEnvironUnDealcount() {
		return environUnDealcount;
	}

	public void setEnvironUnDealcount(long environUnDealcount) {
		this.environUnDealcount = environUnDealcount;
	}

	public long getEnvironUnFinishcount() {
		return environUnFinishcount;
	}

	public void setEnvironUnFinishcount(long environUnFinishcount) {
		this.environUnFinishcount = environUnFinishcount;
	}

	public long getJudicialUnIssuecount() {
		return judicialUnIssuecount;
	}

	public void setJudicialUnIssuecount(long judicialUnIssuecount) {
		this.judicialUnIssuecount = judicialUnIssuecount;
	}

	public long getJudicialUnDealcount() {
		return judicialUnDealcount;
	}

	public void setJudicialUnDealcount(long judicialUnDealcount) {
		this.judicialUnDealcount = judicialUnDealcount;
	}

	public long getJudicialUnFinishcount() {
		return judicialUnFinishcount;
	}

	public void setJudicialUnFinishcount(long judicialUnFinishcount) {
		this.judicialUnFinishcount = judicialUnFinishcount;
	}

	public long getAdminUnIssuecount() {
		return adminUnIssuecount;
	}

	public void setAdminUnIssuecount(long adminUnIssuecount) {
		this.adminUnIssuecount = adminUnIssuecount;
	}

	public long getAdminUnDealcount() {
		return adminUnDealcount;
	}

	public void setAdminUnDealcount(long adminUnDealcount) {
		this.adminUnDealcount = adminUnDealcount;
	}

	public long getAdminUnFinishcount() {
		return adminUnFinishcount;
	}

	public void setAdminUnFinishcount(long adminUnFinishcount) {
		this.adminUnFinishcount = adminUnFinishcount;
	}

	public long getSchoolUnIssuecount() {
		return schoolUnIssuecount;
	}

	public void setSchoolUnIssuecount(long schoolUnIssuecount) {
		this.schoolUnIssuecount = schoolUnIssuecount;
	}

	public long getSchoolUnDealcount() {
		return schoolUnDealcount;
	}

	public void setSchoolUnDealcount(long schoolUnDealcount) {
		this.schoolUnDealcount = schoolUnDealcount;
	}

	public long getSchoolUnFinishcount() {
		return schoolUnFinishcount;
	}

	public void setSchoolUnFinishcount(long schoolUnFinishcount) {
		this.schoolUnFinishcount = schoolUnFinishcount;
	}

	public long getSeaUnIssuecount() {
		return seaUnIssuecount;
	}

	public void setSeaUnIssuecount(long seaUnIssuecount) {
		this.seaUnIssuecount = seaUnIssuecount;
	}

	public long getSeaUnDealcount() {
		return seaUnDealcount;
	}

	public void setSeaUnDealcount(long seaUnDealcount) {
		this.seaUnDealcount = seaUnDealcount;
	}

	public long getSeaUnFinishcount() {
		return seaUnFinishcount;
	}

	public void setSeaUnFinishcount(long seaUnFinishcount) {
		this.seaUnFinishcount = seaUnFinishcount;
	}

	public long getCadreUnIssuecount() {
		return cadreUnIssuecount;
	}

	public void setCadreUnIssuecount(long cadreUnIssuecount) {
		this.cadreUnIssuecount = cadreUnIssuecount;
	}

	public long getCadreUnDealcount() {
		return cadreUnDealcount;
	}

	public void setCadreUnDealcount(long cadreUnDealcount) {
		this.cadreUnDealcount = cadreUnDealcount;
	}

	public long getCadreUnFinishcount() {
		return cadreUnFinishcount;
	}

	public void setCadreUnFinishcount(long cadreUnFinishcount) {
		this.cadreUnFinishcount = cadreUnFinishcount;
	}

	public long getVilthUnIssuecount() {
		return vilthUnIssuecount;
	}

	public void setVilthUnIssuecount(long vilthUnIssuecount) {
		this.vilthUnIssuecount = vilthUnIssuecount;
	}

	public long getVilthUnDealcount() {
		return vilthUnDealcount;
	}

	public void setVilthUnDealcount(long vilthUnDealcount) {
		this.vilthUnDealcount = vilthUnDealcount;
	}

	public long getVilthUnFinishcount() {
		return vilthUnFinishcount;
	}

	public void setVilthUnFinishcount(long vilthUnFinishcount) {
		this.vilthUnFinishcount = vilthUnFinishcount;
	}

	public long getJobUnIssuecount() {
		return jobUnIssuecount;
	}

	public void setJobUnIssuecount(long jobUnIssuecount) {
		this.jobUnIssuecount = jobUnIssuecount;
	}

	public long getJobUnDealcount() {
		return jobUnDealcount;
	}

	public void setJobUnDealcount(long jobUnDealcount) {
		this.jobUnDealcount = jobUnDealcount;
	}

	public long getJobUnFinishcount() {
		return jobUnFinishcount;
	}

	public void setJobUnFinishcount(long jobUnFinishcount) {
		this.jobUnFinishcount = jobUnFinishcount;
	}

	public long getFamilyUnIssuecount() {
		return familyUnIssuecount;
	}

	public void setFamilyUnIssuecount(long familyUnIssuecount) {
		this.familyUnIssuecount = familyUnIssuecount;
	}

	public long getFamilyUnDealcount() {
		return familyUnDealcount;
	}

	public void setFamilyUnDealcount(long familyUnDealcount) {
		this.familyUnDealcount = familyUnDealcount;
	}

	public long getFamilyUnFinishcount() {
		return familyUnFinishcount;
	}

	public void setFamilyUnFinishcount(long familyUnFinishcount) {
		this.familyUnFinishcount = familyUnFinishcount;
	}

	public long getPatientUnIssuecount() {
		return patientUnIssuecount;
	}

	public void setPatientUnIssuecount(long patientUnIssuecount) {
		this.patientUnIssuecount = patientUnIssuecount;
	}

	public long getPatientUnDealcount() {
		return patientUnDealcount;
	}

	public void setPatientUnDealcount(long patientUnDealcount) {
		this.patientUnDealcount = patientUnDealcount;
	}

	public long getPatientUnFinishcount() {
		return patientUnFinishcount;
	}

	public void setPatientUnFinishcount(long patientUnFinishcount) {
		this.patientUnFinishcount = patientUnFinishcount;
	}

	public long getGangsUnIssuecount() {
		return gangsUnIssuecount;
	}

	public void setGangsUnIssuecount(long gangsUnIssuecount) {
		this.gangsUnIssuecount = gangsUnIssuecount;
	}

	public long getGangsUnDealcount() {
		return gangsUnDealcount;
	}

	public void setGangsUnDealcount(long gangsUnDealcount) {
		this.gangsUnDealcount = gangsUnDealcount;
	}

	public long getGangsUnFinishcount() {
		return gangsUnFinishcount;
	}

	public void setGangsUnFinishcount(long gangsUnFinishcount) {
		this.gangsUnFinishcount = gangsUnFinishcount;
	}

	public long getIssuelowUnFinishcount() {
		return issuelowUnFinishcount;
	}

	public void setIssuelowUnFinishcount(long issuelowUnFinishcount) {
		this.issuelowUnFinishcount = issuelowUnFinishcount;
	}

	public long getIssuemidUnFinishcount() {
		return issuemidUnFinishcount;
	}

	public void setIssuemidUnFinishcount(long issuemidUnFinishcount) {
		this.issuemidUnFinishcount = issuemidUnFinishcount;
	}

	public long getIssuehighUnFinishcount() {
		return issuehighUnFinishcount;
	}

	public void setIssuehighUnFinishcount(long issuehighUnFinishcount) {
		this.issuehighUnFinishcount = issuehighUnFinishcount;
	}

	public long getIssuehigherUnFinishcount() {
		return issuehigherUnFinishcount;
	}

	public void setIssuehigherUnFinishcount(long issuehigherUnFinishcount) {
		this.issuehigherUnFinishcount = issuehigherUnFinishcount;
	}

	public long getConcentrateUnIssuecount() {
		return concentrateUnIssuecount;
	}

	public void setConcentrateUnIssuecount(long concentrateUnIssuecount) {
		this.concentrateUnIssuecount = concentrateUnIssuecount;
	}

	public long getConcentrateUnDealcount() {
		return concentrateUnDealcount;
	}

	public void setConcentrateUnDealcount(long concentrateUnDealcount) {
		this.concentrateUnDealcount = concentrateUnDealcount;
	}

	public long getConcentrateUnFinishcount() {
		return concentrateUnFinishcount;
	}

	public void setConcentrateUnFinishcount(long concentrateUnFinishcount) {
		this.concentrateUnFinishcount = concentrateUnFinishcount;
	}

	public long getTotalYearIssuecount() {
		return totalYearIssuecount;
	}

	public void setTotalYearIssuecount(long totalYearIssuecount) {
		this.totalYearIssuecount = totalYearIssuecount;
	}

	public long getTotalYearDealcount() {
		return totalYearDealcount;
	}

	public void setTotalYearDealcount(long totalYearDealcount) {
		this.totalYearDealcount = totalYearDealcount;
	}

	public long getTotalYearFinishcount() {
		return totalYearFinishcount;
	}

	public void setTotalYearFinishcount(long totalYearFinishcount) {
		this.totalYearFinishcount = totalYearFinishcount;
	}

	public String getTotalYearDealrate() {
		return totalYearDealrate;
	}

	public void setTotalYearDealrate(String totalYearDealrate) {
		this.totalYearDealrate = totalYearDealrate;
	}

	public String getTotalYearFinishrate() {
		return totalYearFinishrate;
	}

	public void setTotalYearFinishrate(String totalYearFinishrate) {
		this.totalYearFinishrate = totalYearFinishrate;
	}

	public long getReligionYearIssuecount() {
		return religionYearIssuecount;
	}

	public void setReligionYearIssuecount(long religionYearIssuecount) {
		this.religionYearIssuecount = religionYearIssuecount;
	}

	public long getReligionYearDealcount() {
		return religionYearDealcount;
	}

	public void setReligionYearDealcount(long religionYearDealcount) {
		this.religionYearDealcount = religionYearDealcount;
	}

	public long getReligionYearFinishcount() {
		return religionYearFinishcount;
	}

	public void setReligionYearFinishcount(long religionYearFinishcount) {
		this.religionYearFinishcount = religionYearFinishcount;
	}

	public long getSoldierYearIssuecount() {
		return soldierYearIssuecount;
	}

	public void setSoldierYearIssuecount(long soldierYearIssuecount) {
		this.soldierYearIssuecount = soldierYearIssuecount;
	}

	public long getSoldierYearDealcount() {
		return soldierYearDealcount;
	}

	public void setSoldierYearDealcount(long soldierYearDealcount) {
		this.soldierYearDealcount = soldierYearDealcount;
	}

	public long getSoldierYearFinishcount() {
		return soldierYearFinishcount;
	}

	public void setSoldierYearFinishcount(long soldierYearFinishcount) {
		this.soldierYearFinishcount = soldierYearFinishcount;
	}

	public long getRemoveYearIssuecount() {
		return removeYearIssuecount;
	}

	public void setRemoveYearIssuecount(long removeYearIssuecount) {
		this.removeYearIssuecount = removeYearIssuecount;
	}

	public long getRemoveYearDealcount() {
		return removeYearDealcount;
	}

	public void setRemoveYearDealcount(long removeYearDealcount) {
		this.removeYearDealcount = removeYearDealcount;
	}

	public long getLandBoundariesYearIssuecount() {
		return landBoundariesYearIssuecount;
	}

	public void setLandBoundariesYearIssuecount(long landBoundariesYearIssuecount) {
		this.landBoundariesYearIssuecount = landBoundariesYearIssuecount;
	}

	public long getBuildYearDealcount() {
		return buildYearDealcount;
	}

	public void setBuildYearDealcount(long buildYearDealcount) {
		this.buildYearDealcount = buildYearDealcount;
	}

	public long getBuildYearFinishcount() {
		return buildYearFinishcount;
	}

	public void setBuildYearFinishcount(long buildYearFinishcount) {
		this.buildYearFinishcount = buildYearFinishcount;
	}

	public long getAssetsYearIssuecount() {
		return assetsYearIssuecount;
	}

	public void setAssetsYearIssuecount(long assetsYearIssuecount) {
		this.assetsYearIssuecount = assetsYearIssuecount;
	}

	public long getAssetsYearDealcount() {
		return assetsYearDealcount;
	}

	public void setAssetsYearDealcount(long assetsYearDealcount) {
		this.assetsYearDealcount = assetsYearDealcount;
	}

	public long getAssetsYearFinishcount() {
		return assetsYearFinishcount;
	}

	public void setAssetsYearFinishcount(long assetsYearFinishcount) {
		this.assetsYearFinishcount = assetsYearFinishcount;
	}

	public long getEconomyYearIssuecount() {
		return economyYearIssuecount;
	}

	public void setEconomyYearIssuecount(long economyYearIssuecount) {
		this.economyYearIssuecount = economyYearIssuecount;
	}

	public long getEconomyYearDealcount() {
		return economyYearDealcount;
	}

	public void setEconomyYearDealcount(long economyYearDealcount) {
		this.economyYearDealcount = economyYearDealcount;
	}

	public long getEconomyYearFinishcount() {
		return economyYearFinishcount;
	}

	public void setEconomyYearFinishcount(long economyYearFinishcount) {
		this.economyYearFinishcount = economyYearFinishcount;
	}

	public long getLabourYearIssuecount() {
		return labourYearIssuecount;
	}

	public void setLabourYearIssuecount(long labourYearIssuecount) {
		this.labourYearIssuecount = labourYearIssuecount;
	}

	public long getLabourYearDealcount() {
		return labourYearDealcount;
	}

	public void setLabourYearDealcount(long labourYearDealcount) {
		this.labourYearDealcount = labourYearDealcount;
	}

	public long getLabourYearFinishcount() {
		return labourYearFinishcount;
	}

	public void setLabourYearFinishcount(long labourYearFinishcount) {
		this.labourYearFinishcount = labourYearFinishcount;
	}

	public long getLtdYearIssuecount() {
		return ltdYearIssuecount;
	}

	public void setLtdYearIssuecount(long ltdYearIssuecount) {
		this.ltdYearIssuecount = ltdYearIssuecount;
	}

	public long getLtdYearDealcount() {
		return ltdYearDealcount;
	}

	public void setLtdYearDealcount(long ltdYearDealcount) {
		this.ltdYearDealcount = ltdYearDealcount;
	}

	public long getLtdYearFinishcount() {
		return ltdYearFinishcount;
	}

	public void setLtdYearFinishcount(long ltdYearFinishcount) {
		this.ltdYearFinishcount = ltdYearFinishcount;
	}

	public long getEnvironYearIssuecount() {
		return environYearIssuecount;
	}

	public void setEnvironYearIssuecount(long environYearIssuecount) {
		this.environYearIssuecount = environYearIssuecount;
	}

	public long getEnvironYearDealcount() {
		return environYearDealcount;
	}

	public void setEnvironYearDealcount(long environYearDealcount) {
		this.environYearDealcount = environYearDealcount;
	}

	public long getEnvironYearFinishcount() {
		return environYearFinishcount;
	}

	public void setEnvironYearFinishcount(long environYearFinishcount) {
		this.environYearFinishcount = environYearFinishcount;
	}

	public long getJudicialYearIssuecount() {
		return judicialYearIssuecount;
	}

	public void setJudicialYearIssuecount(long judicialYearIssuecount) {
		this.judicialYearIssuecount = judicialYearIssuecount;
	}

	public long getJudicialYearDealcount() {
		return judicialYearDealcount;
	}

	public void setJudicialYearDealcount(long judicialYearDealcount) {
		this.judicialYearDealcount = judicialYearDealcount;
	}

	public long getJudicialYearFinishcount() {
		return judicialYearFinishcount;
	}

	public void setJudicialYearFinishcount(long judicialYearFinishcount) {
		this.judicialYearFinishcount = judicialYearFinishcount;
	}

	public long getAdminYearIssuecount() {
		return adminYearIssuecount;
	}

	public void setAdminYearIssuecount(long adminYearIssuecount) {
		this.adminYearIssuecount = adminYearIssuecount;
	}

	public long getAdminYearDealcount() {
		return adminYearDealcount;
	}

	public void setAdminYearDealcount(long adminYearDealcount) {
		this.adminYearDealcount = adminYearDealcount;
	}

	public long getAdminYearFinishcount() {
		return adminYearFinishcount;
	}

	public void setAdminYearFinishcount(long adminYearFinishcount) {
		this.adminYearFinishcount = adminYearFinishcount;
	}

	public long getSchoolYearIssuecount() {
		return schoolYearIssuecount;
	}

	public void setSchoolYearIssuecount(long schoolYearIssuecount) {
		this.schoolYearIssuecount = schoolYearIssuecount;
	}

	public long getSchoolYearDealcount() {
		return schoolYearDealcount;
	}

	public void setSchoolYearDealcount(long schoolYearDealcount) {
		this.schoolYearDealcount = schoolYearDealcount;
	}

	public long getSchoolYearFinishcount() {
		return schoolYearFinishcount;
	}

	public void setSchoolYearFinishcount(long schoolYearFinishcount) {
		this.schoolYearFinishcount = schoolYearFinishcount;
	}

	public long getSeaYearIssuecount() {
		return seaYearIssuecount;
	}

	public void setSeaYearIssuecount(long seaYearIssuecount) {
		this.seaYearIssuecount = seaYearIssuecount;
	}

	public long getSeaYearDealcount() {
		return seaYearDealcount;
	}

	public void setSeaYearDealcount(long seaYearDealcount) {
		this.seaYearDealcount = seaYearDealcount;
	}

	public long getSeaYearFinishcount() {
		return seaYearFinishcount;
	}

	public void setSeaYearFinishcount(long seaYearFinishcount) {
		this.seaYearFinishcount = seaYearFinishcount;
	}

	public long getCadreYearIssuecount() {
		return cadreYearIssuecount;
	}

	public void setCadreYearIssuecount(long cadreYearIssuecount) {
		this.cadreYearIssuecount = cadreYearIssuecount;
	}

	public long getCadreYearDealcount() {
		return cadreYearDealcount;
	}

	public void setCadreYearDealcount(long cadreYearDealcount) {
		this.cadreYearDealcount = cadreYearDealcount;
	}

	public long getCadreYearFinishcount() {
		return cadreYearFinishcount;
	}

	public void setCadreYearFinishcount(long cadreYearFinishcount) {
		this.cadreYearFinishcount = cadreYearFinishcount;
	}

	public long getVilthYearIssuecount() {
		return vilthYearIssuecount;
	}

	public void setVilthYearIssuecount(long vilthYearIssuecount) {
		this.vilthYearIssuecount = vilthYearIssuecount;
	}

	public long getVilthYearDealcount() {
		return vilthYearDealcount;
	}

	public void setVilthYearDealcount(long vilthYearDealcount) {
		this.vilthYearDealcount = vilthYearDealcount;
	}

	public long getVilthYearFinishcount() {
		return vilthYearFinishcount;
	}

	public void setVilthYearFinishcount(long vilthYearFinishcount) {
		this.vilthYearFinishcount = vilthYearFinishcount;
	}

	public long getJobYearIssuecount() {
		return jobYearIssuecount;
	}

	public void setJobYearIssuecount(long jobYearIssuecount) {
		this.jobYearIssuecount = jobYearIssuecount;
	}

	public long getJobYearDealcount() {
		return jobYearDealcount;
	}

	public void setJobYearDealcount(long jobYearDealcount) {
		this.jobYearDealcount = jobYearDealcount;
	}

	public long getJobYearFinishcount() {
		return jobYearFinishcount;
	}

	public void setJobYearFinishcount(long jobYearFinishcount) {
		this.jobYearFinishcount = jobYearFinishcount;
	}

	public long getFamilyYearIssuecount() {
		return familyYearIssuecount;
	}

	public void setFamilyYearIssuecount(long familyYearIssuecount) {
		this.familyYearIssuecount = familyYearIssuecount;
	}

	public long getFamilyYearDealcount() {
		return familyYearDealcount;
	}

	public void setFamilyYearDealcount(long familyYearDealcount) {
		this.familyYearDealcount = familyYearDealcount;
	}

	public long getFamilyYearFinishcount() {
		return familyYearFinishcount;
	}

	public void setFamilyYearFinishcount(long familyYearFinishcount) {
		this.familyYearFinishcount = familyYearFinishcount;
	}

	public long getPatientYearIssuecount() {
		return patientYearIssuecount;
	}

	public void setPatientYearIssuecount(long patientYearIssuecount) {
		this.patientYearIssuecount = patientYearIssuecount;
	}

	public long getPatientYearDealcount() {
		return patientYearDealcount;
	}

	public void setPatientYearDealcount(long patientYearDealcount) {
		this.patientYearDealcount = patientYearDealcount;
	}

	public long getPatientYearFinishcount() {
		return patientYearFinishcount;
	}

	public void setPatientYearFinishcount(long patientYearFinishcount) {
		this.patientYearFinishcount = patientYearFinishcount;
	}

	public long getGangsYearIssuecount() {
		return gangsYearIssuecount;
	}

	public void setGangsYearIssuecount(long gangsYearIssuecount) {
		this.gangsYearIssuecount = gangsYearIssuecount;
	}

	public long getGangsYearDealcount() {
		return gangsYearDealcount;
	}

	public void setGangsYearDealcount(long gangsYearDealcount) {
		this.gangsYearDealcount = gangsYearDealcount;
	}

	public long getGangsYearFinishcount() {
		return gangsYearFinishcount;
	}

	public void setGangsYearFinishcount(long gangsYearFinishcount) {
		this.gangsYearFinishcount = gangsYearFinishcount;
	}

	public long getIssuelowYearFinishcount() {
		return issuelowYearFinishcount;
	}

	public void setIssuelowYearFinishcount(long issuelowYearFinishcount) {
		this.issuelowYearFinishcount = issuelowYearFinishcount;
	}

	public long getIssuemidYearFinishcount() {
		return issuemidYearFinishcount;
	}

	public void setIssuemidYearFinishcount(long issuemidYearFinishcount) {
		this.issuemidYearFinishcount = issuemidYearFinishcount;
	}

	public long getIssuehighYearFinishcount() {
		return issuehighYearFinishcount;
	}

	public void setIssuehighYearFinishcount(long issuehighYearFinishcount) {
		this.issuehighYearFinishcount = issuehighYearFinishcount;
	}

	public long getIssuehigherYearFinishcount() {
		return issuehigherYearFinishcount;
	}

	public void setIssuehigherYearFinishcount(long issuehigherYearFinishcount) {
		this.issuehigherYearFinishcount = issuehigherYearFinishcount;
	}

	public long getConcentrateYearIssuecount() {
		return concentrateYearIssuecount;
	}

	public void setConcentrateYearIssuecount(long concentrateYearIssuecount) {
		this.concentrateYearIssuecount = concentrateYearIssuecount;
	}

	public long getConcentrateYearDealcount() {
		return concentrateYearDealcount;
	}

	public void setConcentrateYearDealcount(long concentrateYearDealcount) {
		this.concentrateYearDealcount = concentrateYearDealcount;
	}

	public long getConcentrateYearFinishcount() {
		return concentrateYearFinishcount;
	}

	public void setConcentrateYearFinishcount(long concentrateYearFinishcount) {
		this.concentrateYearFinishcount = concentrateYearFinishcount;
	}

	public long getOtherCityIssuecount() {
		return otherCityIssuecount;
	}

	public void setOtherCityIssuecount(long otherCityIssuecount) {
		this.otherCityIssuecount = otherCityIssuecount;
	}

	public long getOtherCountyIssuecount() {
		return otherCountyIssuecount;
	}

	public void setOtherCountyIssuecount(long otherCountyIssuecount) {
		this.otherCountyIssuecount = otherCountyIssuecount;
	}

	public long getOtherTwonIssuecount() {
		return otherTwonIssuecount;
	}

	public void setOtherTwonIssuecount(long otherTwonIssuecount) {
		this.otherTwonIssuecount = otherTwonIssuecount;
	}

	public long getOtherVilIssuecount() {
		return otherVilIssuecount;
	}

	public void setOtherVilIssuecount(long otherVilIssuecount) {
		this.otherVilIssuecount = otherVilIssuecount;
	}

	public long getOtherUnIssuecount() {
		return otherUnIssuecount;
	}

	public void setOtherUnIssuecount(long otherUnIssuecount) {
		this.otherUnIssuecount = otherUnIssuecount;
	}

	public long getOtherYearIssuecount() {
		return otherYearIssuecount;
	}

	public void setOtherYearIssuecount(long otherYearIssuecount) {
		this.otherYearIssuecount = otherYearIssuecount;
	}

	public long getOtherCityDealcount() {
		return otherCityDealcount;
	}

	public void setOtherCityDealcount(long otherCityDealcount) {
		this.otherCityDealcount = otherCityDealcount;
	}

	public long getOtherCountyDealcount() {
		return otherCountyDealcount;
	}

	public void setOtherCountyDealcount(long otherCountyDealcount) {
		this.otherCountyDealcount = otherCountyDealcount;
	}

	public long getOtherTwonDealcount() {
		return otherTwonDealcount;
	}

	public void setOtherTwonDealcount(long otherTwonDealcount) {
		this.otherTwonDealcount = otherTwonDealcount;
	}

	public long getOtherVilDealcount() {
		return otherVilDealcount;
	}

	public void setOtherVilDealcount(long otherVilDealcount) {
		this.otherVilDealcount = otherVilDealcount;
	}

	public long getOtherUnDealcount() {
		return otherUnDealcount;
	}

	public void setOtherUnDealcount(long otherUnDealcount) {
		this.otherUnDealcount = otherUnDealcount;
	}

	public long getOtherYearDealcount() {
		return otherYearDealcount;
	}

	public void setOtherYearDealcount(long otherYearDealcount) {
		this.otherYearDealcount = otherYearDealcount;
	}

	public long getIssuelowCityStagescount() {
		return issuelowCityStagescount;
	}

	public void setIssuelowCityStagescount(long issuelowCityStagescount) {
		this.issuelowCityStagescount = issuelowCityStagescount;
	}

	public long getIssuemidCityStagescount() {
		return issuemidCityStagescount;
	}

	public void setIssuemidCityStagescount(long issuemidCityStagescount) {
		this.issuemidCityStagescount = issuemidCityStagescount;
	}

	public long getIssuehighCityStagescount() {
		return issuehighCityStagescount;
	}

	public void setIssuehighCityStagescount(long issuehighCityStagescount) {
		this.issuehighCityStagescount = issuehighCityStagescount;
	}

	public long getIssuehigherCityStagescount() {
		return issuehigherCityStagescount;
	}

	public void setIssuehigherCityStagescount(long issuehigherCityStagescount) {
		this.issuehigherCityStagescount = issuehigherCityStagescount;
	}

	public long getIssuelowCountyStagescount() {
		return issuelowCountyStagescount;
	}

	public void setIssuelowCountyStagescount(long issuelowCountyStagescount) {
		this.issuelowCountyStagescount = issuelowCountyStagescount;
	}

	public long getIssuemidCountyStagescount() {
		return issuemidCountyStagescount;
	}

	public void setIssuemidCountyStagescount(long issuemidCountyStagescount) {
		this.issuemidCountyStagescount = issuemidCountyStagescount;
	}

	public long getIssuehighCountyStagescount() {
		return issuehighCountyStagescount;
	}

	public void setIssuehighCountyStagescount(long issuehighCountyStagescount) {
		this.issuehighCountyStagescount = issuehighCountyStagescount;
	}

	public long getIssuehigherCountyStagescount() {
		return issuehigherCountyStagescount;
	}

	public void setIssuehigherCountyStagescount(long issuehigherCountyStagescount) {
		this.issuehigherCountyStagescount = issuehigherCountyStagescount;
	}

	public long getIssuelowTwonStagescount() {
		return issuelowTwonStagescount;
	}

	public void setIssuelowTwonStagescount(long issuelowTwonStagescount) {
		this.issuelowTwonStagescount = issuelowTwonStagescount;
	}

	public long getIssuemidTwonStagescount() {
		return issuemidTwonStagescount;
	}

	public void setIssuemidTwonStagescount(long issuemidTwonStagescount) {
		this.issuemidTwonStagescount = issuemidTwonStagescount;
	}

	public long getIssuehighTwonStagescount() {
		return issuehighTwonStagescount;
	}

	public void setIssuehighTwonStagescount(long issuehighTwonStagescount) {
		this.issuehighTwonStagescount = issuehighTwonStagescount;
	}

	public long getIssuehigherTwonStagescount() {
		return issuehigherTwonStagescount;
	}

	public void setIssuehigherTwonStagescount(long issuehigherTwonStagescount) {
		this.issuehigherTwonStagescount = issuehigherTwonStagescount;
	}

	public long getIssuelowVilStagescount() {
		return issuelowVilStagescount;
	}

	public void setIssuelowVilStagescount(long issuelowVilStagescount) {
		this.issuelowVilStagescount = issuelowVilStagescount;
	}

	public long getIssuemidVilStagescount() {
		return issuemidVilStagescount;
	}

	public void setIssuemidVilStagescount(long issuemidVilStagescount) {
		this.issuemidVilStagescount = issuemidVilStagescount;
	}

	public long getIssuehighVilStagescount() {
		return issuehighVilStagescount;
	}

	public void setIssuehighVilStagescount(long issuehighVilStagescount) {
		this.issuehighVilStagescount = issuehighVilStagescount;
	}

	public long getIssuehigherVilStagescount() {
		return issuehigherVilStagescount;
	}

	public void setIssuehigherVilStagescount(long issuehigherVilStagescount) {
		this.issuehigherVilStagescount = issuehigherVilStagescount;
	}

	public long getIssuelowUnStagescount() {
		return issuelowUnStagescount;
	}

	public void setIssuelowUnStagescount(long issuelowUnStagescount) {
		this.issuelowUnStagescount = issuelowUnStagescount;
	}

	public long getIssuemidUnStagescount() {
		return issuemidUnStagescount;
	}

	public void setIssuemidUnStagescount(long issuemidUnStagescount) {
		this.issuemidUnStagescount = issuemidUnStagescount;
	}

	public long getIssuehighUnStagescount() {
		return issuehighUnStagescount;
	}

	public void setIssuehighUnStagescount(long issuehighUnStagescount) {
		this.issuehighUnStagescount = issuehighUnStagescount;
	}

	public long getIssuehigherUnStagescount() {
		return issuehigherUnStagescount;
	}

	public void setIssuehigherUnStagescount(long issuehigherUnStagescount) {
		this.issuehigherUnStagescount = issuehigherUnStagescount;
	}

	public long getIssuelowYearStagescount() {
		return issuelowYearStagescount;
	}

	public void setIssuelowYearStagescount(long issuelowYearStagescount) {
		this.issuelowYearStagescount = issuelowYearStagescount;
	}

	public long getIssuemidYearStagescount() {
		return issuemidYearStagescount;
	}

	public void setIssuemidYearStagescount(long issuemidYearStagescount) {
		this.issuemidYearStagescount = issuemidYearStagescount;
	}

	public long getIssuehighYearStagescount() {
		return issuehighYearStagescount;
	}

	public void setIssuehighYearStagescount(long issuehighYearStagescount) {
		this.issuehighYearStagescount = issuehighYearStagescount;
	}

	public long getIssuehigherYearStagescount() {
		return issuehigherYearStagescount;
	}

	public void setIssuehigherYearStagescount(long issuehigherYearStagescount) {
		this.issuehigherYearStagescount = issuehigherYearStagescount;
	}

	public long getLandBoundariesCityDealcount() {
		return landBoundariesCityDealcount;
	}

	public void setLandBoundariesCityDealcount(long landBoundariesCityDealcount) {
		this.landBoundariesCityDealcount = landBoundariesCityDealcount;
	}

	public long getLandBoundariesCountyDealcount() {
		return landBoundariesCountyDealcount;
	}

	public void setLandBoundariesCountyDealcount(long landBoundariesCountyDealcount) {
		this.landBoundariesCountyDealcount = landBoundariesCountyDealcount;
	}

	public long getLandBoundariesTwonDealcount() {
		return landBoundariesTwonDealcount;
	}

	public void setLandBoundariesTwonDealcount(long landBoundariesTwonDealcount) {
		this.landBoundariesTwonDealcount = landBoundariesTwonDealcount;
	}

	public long getLandBoundariesVilDealcount() {
		return landBoundariesVilDealcount;
	}

	public void setLandBoundariesVilDealcount(long landBoundariesVilDealcount) {
		this.landBoundariesVilDealcount = landBoundariesVilDealcount;
	}

	public long getLandBoundariesUnDealcount() {
		return landBoundariesUnDealcount;
	}

	public void setLandBoundariesUnDealcount(long landBoundariesUnDealcount) {
		this.landBoundariesUnDealcount = landBoundariesUnDealcount;
	}

	public long getLandBoundariesYearDealcount() {
		return landBoundariesYearDealcount;
	}

	public void setLandBoundariesYearDealcount(long landBoundariesYearDealcount) {
		this.landBoundariesYearDealcount = landBoundariesYearDealcount;
	}

	public long getImptVilImptIssuecount() {
		return imptVilImptIssuecount;
	}

	public void setImptVilImptIssuecount(long imptVilImptIssuecount) {
		this.imptVilImptIssuecount = imptVilImptIssuecount;
	}

	public long getImptVilImptDealcount() {
		return imptVilImptDealcount;
	}

	public void setImptVilImptDealcount(long imptVilImptDealcount) {
		this.imptVilImptDealcount = imptVilImptDealcount;
	}

	public long getImptVilImptFinishcount() {
		return imptVilImptFinishcount;
	}

	public void setImptVilImptFinishcount(long imptVilImptFinishcount) {
		this.imptVilImptFinishcount = imptVilImptFinishcount;
	}

	public long getImptVilImptDealrate() {
		return imptVilImptDealrate;
	}

	public void setImptVilImptDealrate(long imptVilImptDealrate) {
		this.imptVilImptDealrate = imptVilImptDealrate;
	}

	public String getImptVilImptFinishrate() {
		return imptVilImptFinishrate;
	}

	public void setImptVilImptFinishrate(String imptVilImptFinishrate) {
		this.imptVilImptFinishrate = imptVilImptFinishrate;
	}

	public long getImptUnImptIssuecount() {
		return imptUnImptIssuecount;
	}

	public void setImptUnImptIssuecount(long imptUnImptIssuecount) {
		this.imptUnImptIssuecount = imptUnImptIssuecount;
	}

	public long getImptUnImptDealcount() {
		return imptUnImptDealcount;
	}

	public void setImptUnImptDealcount(long imptUnImptDealcount) {
		this.imptUnImptDealcount = imptUnImptDealcount;
	}

	public long getImptUnImptFinishcount() {
		return imptUnImptFinishcount;
	}

	public void setImptUnImptFinishcount(long imptUnImptFinishcount) {
		this.imptUnImptFinishcount = imptUnImptFinishcount;
	}

	public long getImptUnImptDealrate() {
		return imptUnImptDealrate;
	}

	public void setImptUnImptDealrate(long imptUnImptDealrate) {
		this.imptUnImptDealrate = imptUnImptDealrate;
	}

	public String getImptUnImptFinishrate() {
		return imptUnImptFinishrate;
	}

	public void setImptUnImptFinishrate(String imptUnImptFinishrate) {
		this.imptUnImptFinishrate = imptUnImptFinishrate;
	}

	public long getImptYearImptIssuecount() {
		return imptYearImptIssuecount;
	}

	public void setImptYearImptIssuecount(long imptYearImptIssuecount) {
		this.imptYearImptIssuecount = imptYearImptIssuecount;
	}

	public long getImptYearImptDealcount() {
		return imptYearImptDealcount;
	}

	public void setImptYearImptDealcount(long imptYearImptDealcount) {
		this.imptYearImptDealcount = imptYearImptDealcount;
	}

	public long getImptYearImptFinishcount() {
		return imptYearImptFinishcount;
	}

	public void setImptYearImptFinishcount(long imptYearImptFinishcount) {
		this.imptYearImptFinishcount = imptYearImptFinishcount;
	}

	public long getImptYearImptDealrate() {
		return imptYearImptDealrate;
	}

	public void setImptYearImptDealrate(long imptYearImptDealrate) {
		this.imptYearImptDealrate = imptYearImptDealrate;
	}

	public String getImptYearImptFinishrate() {
		return imptYearImptFinishrate;
	}

	public void setImptYearImptFinishrate(String imptYearImptFinishrate) {
		this.imptYearImptFinishrate = imptYearImptFinishrate;
	}

	public long getImptCityImptIssuecount() {
		return imptCityImptIssuecount;
	}

	public void setImptCityImptIssuecount(long imptCityImptIssuecount) {
		this.imptCityImptIssuecount = imptCityImptIssuecount;
	}

	public long getImptCityImptDealcount() {
		return imptCityImptDealcount;
	}

	public void setImptCityImptDealcount(long imptCityImptDealcount) {
		this.imptCityImptDealcount = imptCityImptDealcount;
	}

	public long getImptCityImptFinishcount() {
		return imptCityImptFinishcount;
	}

	public void setImptCityImptFinishcount(long imptCityImptFinishcount) {
		this.imptCityImptFinishcount = imptCityImptFinishcount;
	}

	public String getImptCityImptFinishrate() {
		return imptCityImptFinishrate;
	}

	public void setImptCityImptFinishrate(String imptCityImptFinishrate) {
		this.imptCityImptFinishrate = imptCityImptFinishrate;
	}

	public long getImptCountyImptIssuecount() {
		return imptCountyImptIssuecount;
	}

	public void setImptCountyImptIssuecount(long imptCountyImptIssuecount) {
		this.imptCountyImptIssuecount = imptCountyImptIssuecount;
	}

	public long getImptCountyImptDealcount() {
		return imptCountyImptDealcount;
	}

	public void setImptCountyImptDealcount(long imptCountyImptDealcount) {
		this.imptCountyImptDealcount = imptCountyImptDealcount;
	}

	public long getImptCountyImptFinishcount() {
		return imptCountyImptFinishcount;
	}

	public void setImptCountyImptFinishcount(long imptCountyImptFinishcount) {
		this.imptCountyImptFinishcount = imptCountyImptFinishcount;
	}

	public String getImptCountyImptFinishrate() {
		return imptCountyImptFinishrate;
	}

	public void setImptCountyImptFinishrate(String imptCountyImptFinishrate) {
		this.imptCountyImptFinishrate = imptCountyImptFinishrate;
	}

	public long getImptTwonImptIssuecount() {
		return imptTwonImptIssuecount;
	}

	public void setImptTwonImptIssuecount(long imptTwonImptIssuecount) {
		this.imptTwonImptIssuecount = imptTwonImptIssuecount;
	}

	public long getImptTwonImptDealcount() {
		return imptTwonImptDealcount;
	}

	public void setImptTwonImptDealcount(long imptTwonImptDealcount) {
		this.imptTwonImptDealcount = imptTwonImptDealcount;
	}

	public long getImptTwonImptFinishcount() {
		return imptTwonImptFinishcount;
	}

	public void setImptTwonImptFinishcount(long imptTwonImptFinishcount) {
		this.imptTwonImptFinishcount = imptTwonImptFinishcount;
	}

	public long getImptTwonImptDealrate() {
		return imptTwonImptDealrate;
	}

	public void setImptTwonImptDealrate(long imptTwonImptDealrate) {
		this.imptTwonImptDealrate = imptTwonImptDealrate;
	}

	public String getImptTwonImptFinishrate() {
		return imptTwonImptFinishrate;
	}

	public void setImptTwonImptFinishrate(String imptTwonImptFinishrate) {
		this.imptTwonImptFinishrate = imptTwonImptFinishrate;
	}

	public long getOtherProvinceIssuecount() {
		return otherProvinceIssuecount;
	}

	public void setOtherProvinceIssuecount(long otherProvinceIssuecount) {
		this.otherProvinceIssuecount = otherProvinceIssuecount;
	}

	public long getOtherProvinceDealcount() {
		return otherProvinceDealcount;
	}

	public void setOtherProvinceDealcount(long otherProvinceDealcount) {
		this.otherProvinceDealcount = otherProvinceDealcount;
	}

	public long getTotalProvinceIssuecount() {
		return totalProvinceIssuecount;
	}

	public void setTotalProvinceIssuecount(long totalProvinceIssuecount) {
		this.totalProvinceIssuecount = totalProvinceIssuecount;
	}

	public long getTotalProvinceDealcount() {
		return totalProvinceDealcount;
	}

	public void setTotalProvinceDealcount(long totalProvinceDealcount) {
		this.totalProvinceDealcount = totalProvinceDealcount;
	}

	public String getTotalProvinceDealrate() {
		return totalProvinceDealrate;
	}

	public void setTotalProvinceDealrate(String totalProvinceDealrate) {
		this.totalProvinceDealrate = totalProvinceDealrate;
	}

	public long getTotalProvinceFinishcount() {
		return totalProvinceFinishcount;
	}

	public void setTotalProvinceFinishcount(long totalProvinceFinishcount) {
		this.totalProvinceFinishcount = totalProvinceFinishcount;
	}

	public String getTotalProvinceFinishrate() {
		return totalProvinceFinishrate;
	}

	public void setTotalProvinceFinishrate(String totalProvinceFinishrate) {
		this.totalProvinceFinishrate = totalProvinceFinishrate;
	}

	public long getReligionProvinceIssuecount() {
		return religionProvinceIssuecount;
	}

	public void setReligionProvinceIssuecount(long religionProvinceIssuecount) {
		this.religionProvinceIssuecount = religionProvinceIssuecount;
	}

	public long getReligionProvinceDealcount() {
		return religionProvinceDealcount;
	}

	public void setReligionProvinceDealcount(long religionProvinceDealcount) {
		this.religionProvinceDealcount = religionProvinceDealcount;
	}

	public long getReligionProvinceFinishcount() {
		return religionProvinceFinishcount;
	}

	public void setReligionProvinceFinishcount(long religionProvinceFinishcount) {
		this.religionProvinceFinishcount = religionProvinceFinishcount;
	}

	public long getSoldierProvinceIssuecount() {
		return soldierProvinceIssuecount;
	}

	public void setSoldierProvinceIssuecount(long soldierProvinceIssuecount) {
		this.soldierProvinceIssuecount = soldierProvinceIssuecount;
	}

	public long getSoldierProvinceDealcount() {
		return soldierProvinceDealcount;
	}

	public void setSoldierProvinceDealcount(long soldierProvinceDealcount) {
		this.soldierProvinceDealcount = soldierProvinceDealcount;
	}

	public long getSoldierProvinceFinishcount() {
		return soldierProvinceFinishcount;
	}

	public void setSoldierProvinceFinishcount(long soldierProvinceFinishcount) {
		this.soldierProvinceFinishcount = soldierProvinceFinishcount;
	}

	public long getRemoveProvinceIssuecount() {
		return removeProvinceIssuecount;
	}

	public void setRemoveProvinceIssuecount(long removeProvinceIssuecount) {
		this.removeProvinceIssuecount = removeProvinceIssuecount;
	}

	public long getRemoveProvinceDealcount() {
		return removeProvinceDealcount;
	}

	public void setRemoveProvinceDealcount(long removeProvinceDealcount) {
		this.removeProvinceDealcount = removeProvinceDealcount;
	}

	public long getLandBoundariesProvinceIssuecount() {
		return landBoundariesProvinceIssuecount;
	}

	public void setLandBoundariesProvinceIssuecount(long landBoundariesProvinceIssuecount) {
		this.landBoundariesProvinceIssuecount = landBoundariesProvinceIssuecount;
	}

	public long getLandBoundariesProvinceDealcount() {
		return landBoundariesProvinceDealcount;
	}

	public void setLandBoundariesProvinceDealcount(long landBoundariesProvinceDealcount) {
		this.landBoundariesProvinceDealcount = landBoundariesProvinceDealcount;
	}

	public long getBuildProvinceDealcount() {
		return buildProvinceDealcount;
	}

	public void setBuildProvinceDealcount(long buildProvinceDealcount) {
		this.buildProvinceDealcount = buildProvinceDealcount;
	}

	public long getBuildProvinceFinishcount() {
		return buildProvinceFinishcount;
	}

	public void setBuildProvinceFinishcount(long buildProvinceFinishcount) {
		this.buildProvinceFinishcount = buildProvinceFinishcount;
	}

	public long getAssetsProvinceIssuecount() {
		return assetsProvinceIssuecount;
	}

	public void setAssetsProvinceIssuecount(long assetsProvinceIssuecount) {
		this.assetsProvinceIssuecount = assetsProvinceIssuecount;
	}

	public long getAssetsProvinceDealcount() {
		return assetsProvinceDealcount;
	}

	public void setAssetsProvinceDealcount(long assetsProvinceDealcount) {
		this.assetsProvinceDealcount = assetsProvinceDealcount;
	}

	public long getAssetsProvinceFinishcount() {
		return assetsProvinceFinishcount;
	}

	public void setAssetsProvinceFinishcount(long assetsProvinceFinishcount) {
		this.assetsProvinceFinishcount = assetsProvinceFinishcount;
	}

	public long getEconomyProvinceIssuecount() {
		return economyProvinceIssuecount;
	}

	public void setEconomyProvinceIssuecount(long economyProvinceIssuecount) {
		this.economyProvinceIssuecount = economyProvinceIssuecount;
	}

	public long getEconomyProvinceDealcount() {
		return economyProvinceDealcount;
	}

	public void setEconomyProvinceDealcount(long economyProvinceDealcount) {
		this.economyProvinceDealcount = economyProvinceDealcount;
	}

	public long getEconomyProvinceFinishcount() {
		return economyProvinceFinishcount;
	}

	public void setEconomyProvinceFinishcount(long economyProvinceFinishcount) {
		this.economyProvinceFinishcount = economyProvinceFinishcount;
	}

	public long getLabourProvinceIssuecount() {
		return labourProvinceIssuecount;
	}

	public void setLabourProvinceIssuecount(long labourProvinceIssuecount) {
		this.labourProvinceIssuecount = labourProvinceIssuecount;
	}

	public long getLabourProvinceDealcount() {
		return labourProvinceDealcount;
	}

	public void setLabourProvinceDealcount(long labourProvinceDealcount) {
		this.labourProvinceDealcount = labourProvinceDealcount;
	}

	public long getLabourProvinceFinishcount() {
		return labourProvinceFinishcount;
	}

	public void setLabourProvinceFinishcount(long labourProvinceFinishcount) {
		this.labourProvinceFinishcount = labourProvinceFinishcount;
	}

	public long getLtdProvinceIssuecount() {
		return ltdProvinceIssuecount;
	}

	public void setLtdProvinceIssuecount(long ltdProvinceIssuecount) {
		this.ltdProvinceIssuecount = ltdProvinceIssuecount;
	}

	public long getLtdProvinceDealcount() {
		return ltdProvinceDealcount;
	}

	public void setLtdProvinceDealcount(long ltdProvinceDealcount) {
		this.ltdProvinceDealcount = ltdProvinceDealcount;
	}

	public long getLtdProvinceFinishcount() {
		return ltdProvinceFinishcount;
	}

	public void setLtdProvinceFinishcount(long ltdProvinceFinishcount) {
		this.ltdProvinceFinishcount = ltdProvinceFinishcount;
	}

	public long getEnvironProvinceIssuecount() {
		return environProvinceIssuecount;
	}

	public void setEnvironProvinceIssuecount(long environProvinceIssuecount) {
		this.environProvinceIssuecount = environProvinceIssuecount;
	}

	public long getEnvironProvinceDealcount() {
		return environProvinceDealcount;
	}

	public void setEnvironProvinceDealcount(long environProvinceDealcount) {
		this.environProvinceDealcount = environProvinceDealcount;
	}

	public long getEnvironProvinceFinishcount() {
		return environProvinceFinishcount;
	}

	public void setEnvironProvinceFinishcount(long environProvinceFinishcount) {
		this.environProvinceFinishcount = environProvinceFinishcount;
	}

	public long getJudicialProvinceIssuecount() {
		return judicialProvinceIssuecount;
	}

	public void setJudicialProvinceIssuecount(long judicialProvinceIssuecount) {
		this.judicialProvinceIssuecount = judicialProvinceIssuecount;
	}

	public long getJudicialProvinceDealcount() {
		return judicialProvinceDealcount;
	}

	public void setJudicialProvinceDealcount(long judicialProvinceDealcount) {
		this.judicialProvinceDealcount = judicialProvinceDealcount;
	}

	public long getJudicialProvinceFinishcount() {
		return judicialProvinceFinishcount;
	}

	public void setJudicialProvinceFinishcount(long judicialProvinceFinishcount) {
		this.judicialProvinceFinishcount = judicialProvinceFinishcount;
	}

	public long getAdminProvinceIssuecount() {
		return adminProvinceIssuecount;
	}

	public void setAdminProvinceIssuecount(long adminProvinceIssuecount) {
		this.adminProvinceIssuecount = adminProvinceIssuecount;
	}

	public long getAdminProvinceDealcount() {
		return adminProvinceDealcount;
	}

	public void setAdminProvinceDealcount(long adminProvinceDealcount) {
		this.adminProvinceDealcount = adminProvinceDealcount;
	}

	public long getAdminProvinceFinishcount() {
		return adminProvinceFinishcount;
	}

	public void setAdminProvinceFinishcount(long adminProvinceFinishcount) {
		this.adminProvinceFinishcount = adminProvinceFinishcount;
	}

	public long getSchoolProvinceIssuecount() {
		return schoolProvinceIssuecount;
	}

	public void setSchoolProvinceIssuecount(long schoolProvinceIssuecount) {
		this.schoolProvinceIssuecount = schoolProvinceIssuecount;
	}

	public long getSchoolProvinceDealcount() {
		return schoolProvinceDealcount;
	}

	public void setSchoolProvinceDealcount(long schoolProvinceDealcount) {
		this.schoolProvinceDealcount = schoolProvinceDealcount;
	}

	public long getSchoolProvinceFinishcount() {
		return schoolProvinceFinishcount;
	}

	public void setSchoolProvinceFinishcount(long schoolProvinceFinishcount) {
		this.schoolProvinceFinishcount = schoolProvinceFinishcount;
	}

	public long getSeaProvinceIssuecount() {
		return seaProvinceIssuecount;
	}

	public void setSeaProvinceIssuecount(long seaProvinceIssuecount) {
		this.seaProvinceIssuecount = seaProvinceIssuecount;
	}

	public long getSeaProvinceDealcount() {
		return seaProvinceDealcount;
	}

	public void setSeaProvinceDealcount(long seaProvinceDealcount) {
		this.seaProvinceDealcount = seaProvinceDealcount;
	}

	public long getSeaProvinceFinishcount() {
		return seaProvinceFinishcount;
	}

	public void setSeaProvinceFinishcount(long seaProvinceFinishcount) {
		this.seaProvinceFinishcount = seaProvinceFinishcount;
	}

	public long getCadreProvinceIssuecount() {
		return cadreProvinceIssuecount;
	}

	public void setCadreProvinceIssuecount(long cadreProvinceIssuecount) {
		this.cadreProvinceIssuecount = cadreProvinceIssuecount;
	}

	public long getCadreProvinceDealcount() {
		return cadreProvinceDealcount;
	}

	public void setCadreProvinceDealcount(long cadreProvinceDealcount) {
		this.cadreProvinceDealcount = cadreProvinceDealcount;
	}

	public long getCadreProvinceFinishcount() {
		return cadreProvinceFinishcount;
	}

	public void setCadreProvinceFinishcount(long cadreProvinceFinishcount) {
		this.cadreProvinceFinishcount = cadreProvinceFinishcount;
	}

	public long getVilthProvinceIssuecount() {
		return vilthProvinceIssuecount;
	}

	public void setVilthProvinceIssuecount(long vilthProvinceIssuecount) {
		this.vilthProvinceIssuecount = vilthProvinceIssuecount;
	}

	public long getVilthProvinceDealcount() {
		return vilthProvinceDealcount;
	}

	public void setVilthProvinceDealcount(long vilthProvinceDealcount) {
		this.vilthProvinceDealcount = vilthProvinceDealcount;
	}

	public long getVilthProvinceFinishcount() {
		return vilthProvinceFinishcount;
	}

	public void setVilthProvinceFinishcount(long vilthProvinceFinishcount) {
		this.vilthProvinceFinishcount = vilthProvinceFinishcount;
	}

	public long getJobProvinceIssuecount() {
		return jobProvinceIssuecount;
	}

	public void setJobProvinceIssuecount(long jobProvinceIssuecount) {
		this.jobProvinceIssuecount = jobProvinceIssuecount;
	}

	public long getJobProvinceDealcount() {
		return jobProvinceDealcount;
	}

	public void setJobProvinceDealcount(long jobProvinceDealcount) {
		this.jobProvinceDealcount = jobProvinceDealcount;
	}

	public long getJobProvinceFinishcount() {
		return jobProvinceFinishcount;
	}

	public void setJobProvinceFinishcount(long jobProvinceFinishcount) {
		this.jobProvinceFinishcount = jobProvinceFinishcount;
	}

	public long getFamilyProvinceIssuecount() {
		return familyProvinceIssuecount;
	}

	public void setFamilyProvinceIssuecount(long familyProvinceIssuecount) {
		this.familyProvinceIssuecount = familyProvinceIssuecount;
	}

	public long getFamilyProvinceDealcount() {
		return familyProvinceDealcount;
	}

	public void setFamilyProvinceDealcount(long familyProvinceDealcount) {
		this.familyProvinceDealcount = familyProvinceDealcount;
	}

	public long getFamilyProvinceFinishcount() {
		return familyProvinceFinishcount;
	}

	public void setFamilyProvinceFinishcount(long familyProvinceFinishcount) {
		this.familyProvinceFinishcount = familyProvinceFinishcount;
	}

	public long getPatientProvinceIssuecount() {
		return patientProvinceIssuecount;
	}

	public void setPatientProvinceIssuecount(long patientProvinceIssuecount) {
		this.patientProvinceIssuecount = patientProvinceIssuecount;
	}

	public long getPatientProvinceDealcount() {
		return patientProvinceDealcount;
	}

	public void setPatientProvinceDealcount(long patientProvinceDealcount) {
		this.patientProvinceDealcount = patientProvinceDealcount;
	}

	public long getPatientProvinceFinishcount() {
		return patientProvinceFinishcount;
	}

	public void setPatientProvinceFinishcount(long patientProvinceFinishcount) {
		this.patientProvinceFinishcount = patientProvinceFinishcount;
	}

	public long getGangsProvinceIssuecount() {
		return gangsProvinceIssuecount;
	}

	public void setGangsProvinceIssuecount(long gangsProvinceIssuecount) {
		this.gangsProvinceIssuecount = gangsProvinceIssuecount;
	}

	public long getGangsProvinceDealcount() {
		return gangsProvinceDealcount;
	}

	public void setGangsProvinceDealcount(long gangsProvinceDealcount) {
		this.gangsProvinceDealcount = gangsProvinceDealcount;
	}

	public long getGangsProvinceFinishcount() {
		return gangsProvinceFinishcount;
	}

	public void setGangsProvinceFinishcount(long gangsProvinceFinishcount) {
		this.gangsProvinceFinishcount = gangsProvinceFinishcount;
	}

	public long getIssuelowProvinceStagescount() {
		return issuelowProvinceStagescount;
	}

	public void setIssuelowProvinceStagescount(long issuelowProvinceStagescount) {
		this.issuelowProvinceStagescount = issuelowProvinceStagescount;
	}

	public long getIssuelowProvinceFinishcount() {
		return issuelowProvinceFinishcount;
	}

	public void setIssuelowProvinceFinishcount(long issuelowProvinceFinishcount) {
		this.issuelowProvinceFinishcount = issuelowProvinceFinishcount;
	}

	public long getIssuemidProvinceStagescount() {
		return issuemidProvinceStagescount;
	}

	public void setIssuemidProvinceStagescount(long issuemidProvinceStagescount) {
		this.issuemidProvinceStagescount = issuemidProvinceStagescount;
	}

	public long getIssuemidProvinceFinishcount() {
		return issuemidProvinceFinishcount;
	}

	public void setIssuemidProvinceFinishcount(long issuemidProvinceFinishcount) {
		this.issuemidProvinceFinishcount = issuemidProvinceFinishcount;
	}

	public long getIssuehighProvinceStagescount() {
		return issuehighProvinceStagescount;
	}

	public void setIssuehighProvinceStagescount(long issuehighProvinceStagescount) {
		this.issuehighProvinceStagescount = issuehighProvinceStagescount;
	}

	public long getIssuehighProvinceFinishcount() {
		return issuehighProvinceFinishcount;
	}

	public void setIssuehighProvinceFinishcount(long issuehighProvinceFinishcount) {
		this.issuehighProvinceFinishcount = issuehighProvinceFinishcount;
	}

	public long getIssuehigherProvinceStagescount() {
		return issuehigherProvinceStagescount;
	}

	public void setIssuehigherProvinceStagescount(long issuehigherProvinceStagescount) {
		this.issuehigherProvinceStagescount = issuehigherProvinceStagescount;
	}

	public long getIssuehigherProvinceFinishcount() {
		return issuehigherProvinceFinishcount;
	}

	public void setIssuehigherProvinceFinishcount(long issuehigherProvinceFinishcount) {
		this.issuehigherProvinceFinishcount = issuehigherProvinceFinishcount;
	}

	public long getImptProvinceImptIssuecount() {
		return imptProvinceImptIssuecount;
	}

	public void setImptProvinceImptIssuecount(long imptProvinceImptIssuecount) {
		this.imptProvinceImptIssuecount = imptProvinceImptIssuecount;
	}

	public long getImptProvinceImptDealcount() {
		return imptProvinceImptDealcount;
	}

	public void setImptProvinceImptDealcount(long imptProvinceImptDealcount) {
		this.imptProvinceImptDealcount = imptProvinceImptDealcount;
	}

	public long getImptProvinceImptFinishcount() {
		return imptProvinceImptFinishcount;
	}

	public void setImptProvinceImptFinishcount(long imptProvinceImptFinishcount) {
		this.imptProvinceImptFinishcount = imptProvinceImptFinishcount;
	}

	public String getImptProvinceImptFinishrate() {
		return imptProvinceImptFinishrate;
	}

	public void setImptProvinceImptFinishrate(String imptProvinceImptFinishrate) {
		this.imptProvinceImptFinishrate = imptProvinceImptFinishrate;
	}

	public long getConcentrateProvinceIssuecount() {
		return concentrateProvinceIssuecount;
	}

	public void setConcentrateProvinceIssuecount(long concentrateProvinceIssuecount) {
		this.concentrateProvinceIssuecount = concentrateProvinceIssuecount;
	}

	public long getConcentrateProvinceDealcount() {
		return concentrateProvinceDealcount;
	}

	public void setConcentrateProvinceDealcount(long concentrateProvinceDealcount) {
		this.concentrateProvinceDealcount = concentrateProvinceDealcount;
	}

	public long getConcentrateProvinceFinishcount() {
		return concentrateProvinceFinishcount;
	}

	public void setConcentrateProvinceFinishcount(long concentrateProvinceFinishcount) {
		this.concentrateProvinceFinishcount = concentrateProvinceFinishcount;
	}

}
