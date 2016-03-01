package com.tianque.working.domain;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.beanutils.PropertyUtils;

import com.tianque.core.util.StringUtil;

@SuppressWarnings("serial")
public class InvestigationRemediation extends ReportWorkingRecord {
	/** 排查数 */
	private long investigationSum;// 单位总数
	private long investigationVillage;// 其中村（社区）
	private long investigationEnterprise;// 企业
	private long investigationUnit;// 单位
	private long investigationParticipant;// 参与人数
	private long investigationLeader;// 其中机关干部
	private long investigationStaff;// 单位职工
	private long investigationMasses;// 普通群众

	/** 矛盾纠纷 */
	private long disputeSum;// 排查数
	private long disputeSucces;// 化解成功数
	private long disputeBelongSum;// 土地山林矿山水利海事渔事等权属纠纷--排查数
	private long disputeBelongSuccess;// 土地山林矿山水利海事渔事等权属纠纷--化解成功数
	private long disputeLandSum;// “三改一拆”纠纷（征地、拆迁、安置）--排查数
	private long disputeLandSuccess;// “三改一拆”纠纷（征地、拆迁、安置）--化解成功数
	private long disputeLawSum;// 执法司法纠纷--排查数
	private long disputeLawSuccess;// 执法司法纠纷--化解成功数
	private long disputeEconomySum;// 劳资经济活动纠纷--排查数
	private long disputeEconomySuccess;// 劳资经济活动纠纷--化解成功数
	private long disputeEnvironmentSum;// 环境污染生态破坏纠纷--排查数
	private long disputeEnvironmentSuccess;// 环境污染生态破坏纠纷--化解成功数
	private long disputeDissensionSum;// 村（社）务管理纠纷--排查数
	private long disputeDissensionSuccess;// 村（社）务管理纠纷--化解成功数
	private long disputeEducationSum;// 教育卫生纠纷--排查数
	private long disputeEducationSuccess;// 教育卫生纠纷--化解成功数
	private long disputeMarriageSum;// 婚姻家庭邻里纠纷--排查数
	private long disputeMarriageSuccess;// 婚姻家庭邻里纠纷--化解成功数

	/** 安全隐患 */
	private long securitySum;// 排查数
	private long securityEliminate;// 消除数
	private long securityPoliceSum;// 治安安全隐患--排查数
	private long securityTrafficSum;// 交通安全隐患--排查数
	private long securityProduceSum;// 安全生产隐患--排查数
	private long securityRouteSum;// 线路管理隐患--排查数
	private long securityGrocerySum;// 食药安全隐患--排查数
	private long securityFireSum;// 消防安全隐患--排查数
	private long securityEconomySum;// 经济安全隐患--排查数
	private long securityOtherSum;// 其他安全隐患--排查数
	private long securityPoliceEliminate;// 治安安全隐患--消除数
	private long securityTrafficEliminate;// 交通安全隐患--消除数
	private long securityProduceEliminate;// 安全生产隐患--消除数
	private long securityRouteEliminate;// 线路管理隐患--消除数
	private long securityGroceryEliminate;// 食药安全隐患--消除数
	private long securityFireEliminate;// 消防安全隐患--消除数
	private long securityEconomyEliminate;// 经济安全隐患--消除数
	private long securityOtherEliminate;// 其他安全隐患--消除数

	/** 基层基础 */
	private long basicSum;// 排查数
	private long basicSolving;// 解决数
	private long basicDirectorSum;// 未按要求配备乡镇街道综治办专职副主任数--排查数
	private long basicIntegrateSum;// 乡镇街道社会服务管理中心运行机制未整合数--排查数
	private long basicCreationSum;// 未建立或未正常运行村（企业）服务管理站（室）数--排查数
	private long basicAllocatingSum;// 网格划分、管理人员配备不符合要求数--排查数
	private long basicComprehensiveSum;// 村（社区）未正常使用综治信息系统数--排查数
	private long basicDirectorSolving;// 未按要求配备乡镇街道综治办专职副主任数--解决数
	private long basicIntegrateSolving;// 乡镇街道社会服务管理中心运行机制未整合数--解决数
	private long basicCreationSolving;// 未建立或未正常运行村（企业）服务管理站（室）数--解决数
	private long basicAllocatingSolving;// 网格划分、管理人员配备不符合要求数--解决数
	private long basicComprehensiveSolving;// 村（社区）未正常使用综治信息系统数--解决数

	/** 流动人口和特殊人群管理 */
	private long specialtrampResident;// 流动人口数
	private long specialPositiveInfo;// 刑释解教人数
	private long specialSuperiorVisit;// 重点上访人数
	private long specialIdleYouth;// 闲散青少年数
	private long specialMentalPatient;// 严重精神障碍患者
	private long specialRectify;// 社区矫正人数
	private long specialDruggy;// 吸毒人数
	private long specialOther;// 其他特殊人员

	public InvestigationRemediation() {

	}

	public InvestigationRemediation(ReportWorkingRecord record) throws Exception {
		// content转化成属性
		JSONObject jsonObject = new JSONObject();
		if (StringUtil.isStringAvaliable(record.getContent())) {
			jsonObject = (JSONObject) JSONSerializer.toJSON(record.getContent());
		}
		InvestigationRemediation bean = (InvestigationRemediation) JSONObject.toBean(jsonObject,
				InvestigationRemediation.class);
		PropertyUtils.copyProperties(bean, record);
		PropertyUtils.copyProperties(this, bean);
	}

	public long getInvestigationSum() {
		return investigationSum;
	}

	public void setInvestigationSum(long investigationSum) {
		this.investigationSum = investigationSum;
	}

	public long getInvestigationVillage() {
		return investigationVillage;
	}

	public void setInvestigationVillage(long investigationVillage) {
		this.investigationVillage = investigationVillage;
	}

	public long getInvestigationEnterprise() {
		return investigationEnterprise;
	}

	public void setInvestigationEnterprise(long investigationEnterprise) {
		this.investigationEnterprise = investigationEnterprise;
	}

	public long getInvestigationUnit() {
		return investigationUnit;
	}

	public void setInvestigationUnit(long investigationUnit) {
		this.investigationUnit = investigationUnit;
	}

	public long getInvestigationParticipant() {
		return investigationParticipant;
	}

	public void setInvestigationParticipant(long investigationParticipant) {
		this.investigationParticipant = investigationParticipant;
	}

	public long getInvestigationLeader() {
		return investigationLeader;
	}

	public void setInvestigationLeader(long investigationLeader) {
		this.investigationLeader = investigationLeader;
	}

	public long getInvestigationStaff() {
		return investigationStaff;
	}

	public void setInvestigationStaff(long investigationStaff) {
		this.investigationStaff = investigationStaff;
	}

	public long getInvestigationMasses() {
		return investigationMasses;
	}

	public void setInvestigationMasses(long investigationMasses) {
		this.investigationMasses = investigationMasses;
	}

	public long getDisputeSum() {
		return disputeSum;
	}

	public void setDisputeSum(long disputeSum) {
		this.disputeSum = disputeSum;
	}

	public long getDisputeSucces() {
		return disputeSucces;
	}

	public void setDisputeSucces(long disputeSucces) {
		this.disputeSucces = disputeSucces;
	}

	public long getDisputeBelongSum() {
		return disputeBelongSum;
	}

	public void setDisputeBelongSum(long disputeBelongSum) {
		this.disputeBelongSum = disputeBelongSum;
	}

	public long getDisputeBelongSuccess() {
		return disputeBelongSuccess;
	}

	public void setDisputeBelongSuccess(long disputeBelongSuccess) {
		this.disputeBelongSuccess = disputeBelongSuccess;
	}

	public long getDisputeLandSum() {
		return disputeLandSum;
	}

	public void setDisputeLandSum(long disputeLandSum) {
		this.disputeLandSum = disputeLandSum;
	}

	public long getDisputeLandSuccess() {
		return disputeLandSuccess;
	}

	public void setDisputeLandSuccess(long disputeLandSuccess) {
		this.disputeLandSuccess = disputeLandSuccess;
	}

	public long getDisputeLawSum() {
		return disputeLawSum;
	}

	public void setDisputeLawSum(long disputeLawSum) {
		this.disputeLawSum = disputeLawSum;
	}

	public long getDisputeLawSuccess() {
		return disputeLawSuccess;
	}

	public void setDisputeLawSuccess(long disputeLawSuccess) {
		this.disputeLawSuccess = disputeLawSuccess;
	}

	public long getDisputeEconomySum() {
		return disputeEconomySum;
	}

	public void setDisputeEconomySum(long disputeEconomySum) {
		this.disputeEconomySum = disputeEconomySum;
	}

	public long getDisputeEconomySuccess() {
		return disputeEconomySuccess;
	}

	public void setDisputeEconomySuccess(long disputeEconomySuccess) {
		this.disputeEconomySuccess = disputeEconomySuccess;
	}

	public long getDisputeEnvironmentSum() {
		return disputeEnvironmentSum;
	}

	public void setDisputeEnvironmentSum(long disputeEnvironmentSum) {
		this.disputeEnvironmentSum = disputeEnvironmentSum;
	}

	public long getDisputeEnvironmentSuccess() {
		return disputeEnvironmentSuccess;
	}

	public void setDisputeEnvironmentSuccess(long disputeEnvironmentSuccess) {
		this.disputeEnvironmentSuccess = disputeEnvironmentSuccess;
	}

	public long getDisputeDissensionSum() {
		return disputeDissensionSum;
	}

	public void setDisputeDissensionSum(long disputeDissensionSum) {
		this.disputeDissensionSum = disputeDissensionSum;
	}

	public long getDisputeDissensionSuccess() {
		return disputeDissensionSuccess;
	}

	public void setDisputeDissensionSuccess(long disputeDissensionSuccess) {
		this.disputeDissensionSuccess = disputeDissensionSuccess;
	}

	public long getDisputeEducationSum() {
		return disputeEducationSum;
	}

	public void setDisputeEducationSum(long disputeEducationSum) {
		this.disputeEducationSum = disputeEducationSum;
	}

	public long getDisputeEducationSuccess() {
		return disputeEducationSuccess;
	}

	public void setDisputeEducationSuccess(long disputeEducationSuccess) {
		this.disputeEducationSuccess = disputeEducationSuccess;
	}

	public long getDisputeMarriageSum() {
		return disputeMarriageSum;
	}

	public void setDisputeMarriageSum(long disputeMarriageSum) {
		this.disputeMarriageSum = disputeMarriageSum;
	}

	public long getDisputeMarriageSuccess() {
		return disputeMarriageSuccess;
	}

	public void setDisputeMarriageSuccess(long disputeMarriageSuccess) {
		this.disputeMarriageSuccess = disputeMarriageSuccess;
	}

	public long getSecuritySum() {
		return securitySum;
	}

	public void setSecuritySum(long securitySum) {
		this.securitySum = securitySum;
	}

	public long getSecurityEliminate() {
		return securityEliminate;
	}

	public void setSecurityEliminate(long securityEliminate) {
		this.securityEliminate = securityEliminate;
	}

	public long getSecurityPoliceSum() {
		return securityPoliceSum;
	}

	public void setSecurityPoliceSum(long securityPoliceSum) {
		this.securityPoliceSum = securityPoliceSum;
	}

	public long getSecurityTrafficSum() {
		return securityTrafficSum;
	}

	public void setSecurityTrafficSum(long securityTrafficSum) {
		this.securityTrafficSum = securityTrafficSum;
	}

	public long getSecurityProduceSum() {
		return securityProduceSum;
	}

	public void setSecurityProduceSum(long securityProduceSum) {
		this.securityProduceSum = securityProduceSum;
	}

	public long getSecurityRouteSum() {
		return securityRouteSum;
	}

	public void setSecurityRouteSum(long securityRouteSum) {
		this.securityRouteSum = securityRouteSum;
	}

	public long getSecurityGrocerySum() {
		return securityGrocerySum;
	}

	public void setSecurityGrocerySum(long securityGrocerySum) {
		this.securityGrocerySum = securityGrocerySum;
	}

	public long getSecurityFireSum() {
		return securityFireSum;
	}

	public void setSecurityFireSum(long securityFireSum) {
		this.securityFireSum = securityFireSum;
	}

	public long getSecurityEconomySum() {
		return securityEconomySum;
	}

	public void setSecurityEconomySum(long securityEconomySum) {
		this.securityEconomySum = securityEconomySum;
	}

	public long getSecurityOtherSum() {
		return securityOtherSum;
	}

	public void setSecurityOtherSum(long securityOtherSum) {
		this.securityOtherSum = securityOtherSum;
	}

	public long getSecurityPoliceEliminate() {
		return securityPoliceEliminate;
	}

	public void setSecurityPoliceEliminate(long securityPoliceEliminate) {
		this.securityPoliceEliminate = securityPoliceEliminate;
	}

	public long getSecurityTrafficEliminate() {
		return securityTrafficEliminate;
	}

	public void setSecurityTrafficEliminate(long securityTrafficEliminate) {
		this.securityTrafficEliminate = securityTrafficEliminate;
	}

	public long getSecurityProduceEliminate() {
		return securityProduceEliminate;
	}

	public void setSecurityProduceEliminate(long securityProduceEliminate) {
		this.securityProduceEliminate = securityProduceEliminate;
	}

	public long getSecurityRouteEliminate() {
		return securityRouteEliminate;
	}

	public void setSecurityRouteEliminate(long securityRouteEliminate) {
		this.securityRouteEliminate = securityRouteEliminate;
	}

	public long getSecurityGroceryEliminate() {
		return securityGroceryEliminate;
	}

	public void setSecurityGroceryEliminate(long securityGroceryEliminate) {
		this.securityGroceryEliminate = securityGroceryEliminate;
	}

	public long getSecurityFireEliminate() {
		return securityFireEliminate;
	}

	public void setSecurityFireEliminate(long securityFireEliminate) {
		this.securityFireEliminate = securityFireEliminate;
	}

	public long getSecurityEconomyEliminate() {
		return securityEconomyEliminate;
	}

	public void setSecurityEconomyEliminate(long securityEconomyEliminate) {
		this.securityEconomyEliminate = securityEconomyEliminate;
	}

	public long getSecurityOtherEliminate() {
		return securityOtherEliminate;
	}

	public void setSecurityOtherEliminate(long securityOtherEliminate) {
		this.securityOtherEliminate = securityOtherEliminate;
	}

	public long getBasicSum() {
		return basicSum;
	}

	public void setBasicSum(long basicSum) {
		this.basicSum = basicSum;
	}

	public long getBasicSolving() {
		return basicSolving;
	}

	public void setBasicSolving(long basicSolving) {
		this.basicSolving = basicSolving;
	}

	public long getBasicDirectorSum() {
		return basicDirectorSum;
	}

	public void setBasicDirectorSum(long basicDirectorSum) {
		this.basicDirectorSum = basicDirectorSum;
	}

	public long getBasicIntegrateSum() {
		return basicIntegrateSum;
	}

	public void setBasicIntegrateSum(long basicIntegrateSum) {
		this.basicIntegrateSum = basicIntegrateSum;
	}

	public long getBasicCreationSum() {
		return basicCreationSum;
	}

	public void setBasicCreationSum(long basicCreationSum) {
		this.basicCreationSum = basicCreationSum;
	}

	public long getBasicAllocatingSum() {
		return basicAllocatingSum;
	}

	public void setBasicAllocatingSum(long basicAllocatingSum) {
		this.basicAllocatingSum = basicAllocatingSum;
	}

	public long getBasicComprehensiveSum() {
		return basicComprehensiveSum;
	}

	public void setBasicComprehensiveSum(long basicComprehensiveSum) {
		this.basicComprehensiveSum = basicComprehensiveSum;
	}

	public long getBasicDirectorSolving() {
		return basicDirectorSolving;
	}

	public void setBasicDirectorSolving(long basicDirectorSolving) {
		this.basicDirectorSolving = basicDirectorSolving;
	}

	public long getBasicIntegrateSolving() {
		return basicIntegrateSolving;
	}

	public void setBasicIntegrateSolving(long basicIntegrateSolving) {
		this.basicIntegrateSolving = basicIntegrateSolving;
	}

	public long getBasicCreationSolving() {
		return basicCreationSolving;
	}

	public void setBasicCreationSolving(long basicCreationSolving) {
		this.basicCreationSolving = basicCreationSolving;
	}

	public long getBasicAllocatingSolving() {
		return basicAllocatingSolving;
	}

	public void setBasicAllocatingSolving(long basicAllocatingSolving) {
		this.basicAllocatingSolving = basicAllocatingSolving;
	}

	public long getBasicComprehensiveSolving() {
		return basicComprehensiveSolving;
	}

	public void setBasicComprehensiveSolving(long basicComprehensiveSolving) {
		this.basicComprehensiveSolving = basicComprehensiveSolving;
	}

	public long getSpecialtrampResident() {
		return specialtrampResident;
	}

	public void setSpecialtrampResident(long specialtrampResident) {
		this.specialtrampResident = specialtrampResident;
	}

	public long getSpecialPositiveInfo() {
		return specialPositiveInfo;
	}

	public void setSpecialPositiveInfo(long specialPositiveInfo) {
		this.specialPositiveInfo = specialPositiveInfo;
	}

	public long getSpecialSuperiorVisit() {
		return specialSuperiorVisit;
	}

	public void setSpecialSuperiorVisit(long specialSuperiorVisit) {
		this.specialSuperiorVisit = specialSuperiorVisit;
	}

	public long getSpecialIdleYouth() {
		return specialIdleYouth;
	}

	public void setSpecialIdleYouth(long specialIdleYouth) {
		this.specialIdleYouth = specialIdleYouth;
	}

	public long getSpecialMentalPatient() {
		return specialMentalPatient;
	}

	public void setSpecialMentalPatient(long specialMentalPatient) {
		this.specialMentalPatient = specialMentalPatient;
	}

	public long getSpecialRectify() {
		return specialRectify;
	}

	public void setSpecialRectify(long specialRectify) {
		this.specialRectify = specialRectify;
	}

	public long getSpecialDruggy() {
		return specialDruggy;
	}

	public void setSpecialDruggy(long specialDruggy) {
		this.specialDruggy = specialDruggy;
	}

	public long getSpecialOther() {
		return specialOther;
	}

	public void setSpecialOther(long specialOther) {
		this.specialOther = specialOther;
	}

}
