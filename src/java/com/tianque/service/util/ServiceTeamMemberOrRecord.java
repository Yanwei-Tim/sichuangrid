package com.tianque.service.util;

import com.tianque.core.util.BaseInfoTables;

public enum ServiceTeamMemberOrRecord {

	RECTIFICATIVEPERSON(BaseInfoTables.RECTIFICATIVEPERSON_KEY, "监管人员", "监管记录"), POSITIVEINFO(
			BaseInfoTables.POSITIVEINFO_KEY, "帮教人员", "帮教记录"), MENTALPATIENT(
			BaseInfoTables.MENTALPATIENT_KEY, "监护人员", "监护记录"), DRUGGY(
			BaseInfoTables.DRUGGY_KEY, "帮教人员", "帮教记录"), DANGEROUSGOODSPRACTITIONER(
			BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY, "管理人员", "管理记录"), superiorVisit(
			BaseInfoTables.SUPERIORVISIT_KEY, "管控责任人员", "管控记录"), IDLEYOUTH(
			BaseInfoTables.IDLEYOUTH_KEY, "服务管理人员", "服务管理记录"), NURTURESWOMEN(
			BaseInfoTables.NURTURESWOMEN_KEY, "服务管理人员", "服务管理记录"), ELDERLYPEOPLE(
			BaseInfoTables.ELDERLYPEOPLE_KEY, "服务人员", "服务记录"), HANDICAPPED(
			BaseInfoTables.HANDICAPPED_KEY, "服务人员", "服务记录"), OPTIMALOBJECT(
			BaseInfoTables.OPTIMALOBJECT_KEY, "服务人员", "服务记录"), AIDNEEDPOPULATION(
			BaseInfoTables.AIDNEEDPOPULATION_KEY, "帮扶人员", "帮扶记录"), UNEMPLOYEDPEOPLE(
			BaseInfoTables.UNEMPLOYEDPEOPLE_KEY, "服务管理人员", "服务管理记录"), DANGEROUSCHEMICALSUNIT(
			BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY, "治安负责人", "巡场情况"), INTERNETBAR(
			BaseInfoTables.INTERNETBAR_KEY, "治安负责人", "巡场情况"), PUBLICPLACE(
			BaseInfoTables.PUBLICPLACE_KEY, "治安负责人", "巡场情况"), ENTERPRISEKEY(
			BaseInfoTables.ENTERPRISEKEY_KEY, "治安负责人", "巡场情况"), FIRESAFETYKEY(
			BaseInfoTables.FIRESAFETYKEY_KEY, "消防安全负责人", "巡场情况"), SCHOOL(
			BaseInfoTables.SCHOOL_KEY, "治安负责人", "巡场情况"), OTHERLOCALE(
			BaseInfoTables.OTHERLOCALE_KEY, "治安负责人", "巡场情况"), FPERSONNEL(
			BaseInfoTables.FPERSONNEL_KEY, "服务管理人员", "服务管理记录"), QPERSONNEL(
			BaseInfoTables.QPERSONNEL_KEY, "服务管理人员", "服务管理记录"), MPERSONNEL(
			BaseInfoTables.MPERSONNEL_KEY, "服务管理人员", "服务管理记录");

	private String populationType;

	private String member;

	private String record;

	private ServiceTeamMemberOrRecord(String populationType, String member,
			String record) {
		this.setPopulationType(populationType);
		this.setMember(member);
		this.setRecord(record);
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getMember() {
		return member;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRecord() {
		return record;
	}

	public static String getMemberAlias(String populationType) {
		for (ServiceTeamMemberOrRecord stmr : ServiceTeamMemberOrRecord
				.values()) {
			if (stmr.getPopulationType().equals(populationType)) {
				return stmr.getMember();
			}
		}
		return "";
	}

	public static String getRecordAlias(String populationType) {
		for (ServiceTeamMemberOrRecord stmr : ServiceTeamMemberOrRecord
				.values()) {
			if (stmr.getPopulationType().equals(populationType)) {
				return stmr.getRecord();
			}
		}
		return "";
	}
}
