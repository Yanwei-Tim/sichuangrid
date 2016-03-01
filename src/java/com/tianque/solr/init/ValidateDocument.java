package com.tianque.solr.init;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Hospital;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.School;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.service.EnterpriseService;
import com.tianque.service.HospitalService;
import com.tianque.service.OtherLocaleService;
import com.tianque.service.SchoolService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.solr.domain.KeyPlaceDocument;
import com.tianque.solr.domain.KeyPopulationDocument;
import com.tianque.solr.service.SolrKeyPlaceService;
import com.tianque.solr.service.SolrKeyPopulationService;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class ValidateDocument {
	/**
	 * @param args
	 */
	// ----吸毒人员(Druggs)的索引校验----
	public void validateDruggy() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		DruggyService druggyService = (DruggyService) ServiceFactory
				.getService("druggyService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}

		// 从数据库查出来Document
		PageInfo<Druggy> pageInfo = druggyService
				.findDruggyForPageByOrgInternalCode(rootOrg.getId(), 1, 100,
						"id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			Druggy druggy = (Druggy) ite.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(druggy.getIdCardNo());

			assertEquals(keyPopulationDocument.getName(), druggy.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					druggy.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					druggy.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					druggy.getSimplePinyin());
			System.out.println("Druggy索引添加校验成功!");
		}
	}

	// ----危险品从业人员(DangerousGoodsPractitioner)的索引校验----
	public void validateDangerousGoodsPractitioner() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		DangerousGoodsPractitionerService dangerousGoodsPractitionerService = (DangerousGoodsPractitionerService) ServiceFactory
				.getService("dangerousGoodsPractitionerService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PageInfo<DangerousGoodsPractitioner> pageInfo = dangerousGoodsPractitionerService
				.findDangerousGoodsPractitionersForPageByOrgId(rootOrg.getId(),
						1, 100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			DangerousGoodsPractitioner dangerousGoodsPractitioner = (DangerousGoodsPractitioner) ite
					.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(dangerousGoodsPractitioner
							.getIdCardNo());

			assertEquals(keyPopulationDocument.getName(),
					dangerousGoodsPractitioner.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					dangerousGoodsPractitioner.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					dangerousGoodsPractitioner.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					dangerousGoodsPractitioner.getSimplePinyin());
			System.out.println("DangerousGoodsPractitioner索引添加校验成功!");
		}
	}

	// ----信访上访人员(SuperiorVisit)的索引校验----
	public void validateSuperiorVisit() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		SuperiorVisitService superiorVisitService = (SuperiorVisitService) ServiceFactory
				.getService("superiorVisitService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PageInfo<SuperiorVisit> pageInfo = superiorVisitService
				.findSuperiorVisitForPageByOrgInternalCode(rootOrg.getId(), 1,
						100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			SuperiorVisit superiorVisit = (SuperiorVisit) ite.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(superiorVisit
							.getIdCardNo());

			assertEquals(keyPopulationDocument.getName(),
					superiorVisit.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					superiorVisit.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					superiorVisit.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					superiorVisit.getSimplePinyin());
			System.out.println("SuperiorVisit索引添加校验成功!");
		}
	}

	// ----闲散青少年（IdleYouth）的索引校验----
	public void validateIdleYouth() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		IdleYouthService idleYouthService = (IdleYouthService) ServiceFactory
				.getService("idleYouthService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PageInfo<IdleYouth> pageInfo = idleYouthService
				.findIdleYouthsForPageByOrganizationId(rootOrg.getId(), 1, 100,
						"id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			IdleYouth idleYouth = (IdleYouth) ite.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(idleYouth.getIdCardNo());
			assertEquals(keyPopulationDocument.getName(), idleYouth.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					idleYouth.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					idleYouth.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					idleYouth.getSimplePinyin());
			System.out.println("IdleYouth索引添加校验成功!");
		}
	}

	// ----武疯子（MentalPatient）的索引校验----
	public void validateMentalPatient() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		MentalPatientService mentalPatientService = (MentalPatientService) ServiceFactory
				.getService("mentalPatientService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PageInfo<MentalPatient> pageInfo = mentalPatientService
				.findMentalPatientsForPageByOrgInternalCode(rootOrg.getId(), 1,
						100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			MentalPatient mentalPatient = (MentalPatient) ite.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(mentalPatient
							.getIdCardNo());
			assertEquals(keyPopulationDocument.getName(),
					mentalPatient.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					mentalPatient.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					mentalPatient.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					mentalPatient.getSimplePinyin());
			System.out.println("MentalPatient索引添加校验成功!");
		}
	}

	// ----归正人员（PositiveInfos）的索引校验----
	public void validatePositiveInfos() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		PositiveInfoService posiviteInfoService = (PositiveInfoService) ServiceFactory
				.getService("posiviteInfoService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PositiveInfo positiveInfo = new PositiveInfo();
		positiveInfo.setOrgInternalCode(rootOrg.getOrgInternalCode());
		PageInfo<PositiveInfo> pageInfo = posiviteInfoService
				.findPositiveInfoForPage(positiveInfo, 1, 100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			positiveInfo = (PositiveInfo) ite.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(positiveInfo
							.getIdCardNo());

			assertEquals(keyPopulationDocument.getName(),
					positiveInfo.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					positiveInfo.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					positiveInfo.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					positiveInfo.getSimplePinyin());
			System.out.println("PositiveInfos索引添加校验成功!");
		}
	}

	// ----矫正人员（RectificativePerson）的索引校验----
	public void validateRectificativePerson() {
		SolrKeyPopulationService keyPopulationService = (SolrKeyPopulationService) ServiceFactory
				.getService("keyPopulationService");
		RectificativePersonService rectificativePersonService = (RectificativePersonService) ServiceFactory
				.getService("rectificativePersonService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}
		PageInfo<RectificativePerson> pageInfo = rectificativePersonService
				.findRectificativePersonsForPageByOrgInternalCode(
						rootOrg.getId(), 1, 100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			RectificativePerson rectificativePerson = (RectificativePerson) ite
					.next();
			KeyPopulationDocument keyPopulationDocument = keyPopulationService
					.getKeyPopulationDocumentByIdCardNo(rectificativePerson
							.getIdCardNo());
			assertEquals(keyPopulationDocument.getName(),
					rectificativePerson.getName());
			assertEquals(keyPopulationDocument.getIdCardNo(),
					rectificativePerson.getIdCardNo());
			assertEquals(keyPopulationDocument.getFullPinyin(),
					rectificativePerson.getFullPinyin());
			assertEquals(keyPopulationDocument.getSimplePinyin(),
					rectificativePerson.getSimplePinyin());
			System.out.println("RectificativePerson索引添加校验成功!");
		}
	}

	// ----困难群众（PoorPeople）的索引校验----

	// ----企业(Enterprise)的索引校验----
	public void validateEnterprise() {
		SolrKeyPlaceService keyPlaceService = (SolrKeyPlaceService) ServiceFactory
				.getService("keyPlaceService");
		EnterpriseService enterpriseService = (EnterpriseService) ServiceFactory
				.getService("enterpriseService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}

		// 从数据库查出来Document
		PageInfo<Enterprise> pageInfo = enterpriseService
				.findEnterprisesForListPageByOrganizationId(rootOrg.getId(), 1,
						100, "id", "");
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			Enterprise enterprise = (Enterprise) ite.next();
			KeyPlaceDocument keyPlaceDocument = keyPlaceService
					.getKeyPlaceDocumentByIdName(enterprise.getName(),
							DocumentType.ENTERPRISEKEY);

			assertEquals(keyPlaceDocument.getName(), enterprise.getName());
			assertEquals(keyPlaceDocument.getAddress(), enterprise.getAddress());
			assertEquals(keyPlaceDocument.getFullPinyin(),
					enterprise.getFullPinyin());
			assertEquals(keyPlaceDocument.getSimplePinyin(),
					enterprise.getSimplePinyin());
			assertNotNull(keyPlaceDocument.getCreateDate());
			System.out.println("Enterprise索引添加校验成功!");
		}
	}

	// ----学校(School)的索引校验----
	public void validateSchool() {
		SolrKeyPlaceService keyPlaceService = (SolrKeyPlaceService) ServiceFactory
				.getService("keyPlaceService");
		SchoolService schoolService = (SchoolService) ServiceFactory
				.getService("schoolService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}

		// 从数据库查出来Document
		School school = new School();
		school.setOrgInternalCode(rootOrg.getOrgInternalCode());
		PageInfo<School> pageInfo = schoolService.finallSchoolList(school, 1,
				100, "id", "", null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			school = (School) ite.next();
			KeyPlaceDocument keyPlaceDocument = keyPlaceService
					.getKeyPlaceDocumentByIdName(school.getChineseName(),
							DocumentType.SCHOOL);

			assertEquals(keyPlaceDocument.getName(), school.getChineseName());
			assertEquals(keyPlaceDocument.getAddress(), school.getAddress());
			assertEquals(keyPlaceDocument.getFullPinyin(),
					school.getFullPinyin());
			assertEquals(keyPlaceDocument.getSimplePinyin(),
					school.getSimplePinyin());
			assertNotNull(keyPlaceDocument.getCreateDate());
			System.out.println("school索引添加校验成功!");
		}
	}

	// ----医院(Hospital)的索引校验----
	public void validateHospital() {
		SolrKeyPlaceService keyPlaceService = (SolrKeyPlaceService) ServiceFactory
				.getService("keyPlaceService");
		HospitalService hospitalService = (HospitalService) ServiceFactory
				.getService("hospitalService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}

		// 从数据库查出来Document
		SearchHospitalVo hospitals = new SearchHospitalVo();
		hospitals.setOrgInternalCode(rootOrg.getOrgInternalCode());
		PageInfo<Hospital> pageInfo = hospitalService.searchHospitalForPage(1,
				100, "id", "", hospitals);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			Hospital hospital = (Hospital) ite.next();
			KeyPlaceDocument keyPlaceDocument = keyPlaceService
					.getKeyPlaceDocumentByIdName(hospital.getHospitalName(),
							DocumentType.HOSPITAL);

			assertEquals(keyPlaceDocument.getName(), hospital.getHospitalName());
			assertEquals(keyPlaceDocument.getAddress(), hospital.getAddress());
			assertEquals(keyPlaceDocument.getFullPinyin(),
					hospital.getFullPinyin());
			assertEquals(keyPlaceDocument.getSimplePinyin(),
					hospital.getSimplePinyin());
			assertNotNull(keyPlaceDocument.getCreateDate());
			System.out.println("Hospital索引添加校验成功!");
		}
	}

	// ----其他场所(OtherLocale)的索引校验----
	public void validateOtherLocale() {
		SolrKeyPlaceService keyPlaceService = (SolrKeyPlaceService) ServiceFactory
				.getService("keyPlaceService");
		OtherLocaleService otherLocaleService = (OtherLocaleService) ServiceFactory
				.getService("otherLocaleService");
		Organization rootOrg = new Organization();
		OrganizationDubboService organizationDubboService = (OrganizationDubboService) ServiceFactory
				.getService("organizationDubboService");
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(null);
		if (organizations.size() > 0) {
			rootOrg = organizations.get(0);
		}

		// 从数据库查出来Document
		PageInfo<OtherLocale> pageInfo = otherLocaleService
				.findOtherLocalesForPage(rootOrg.getId(), 1, 100, "id", "",
						null);
		List arrayList = pageInfo.getResult();
		Iterator ite = arrayList.iterator();
		while (ite.hasNext()) {
			OtherLocale otherLocale = (OtherLocale) ite.next();
			KeyPlaceDocument keyPlaceDocument = keyPlaceService
					.getKeyPlaceDocumentByIdName(otherLocale.getName(),
							DocumentType.OTHER_LOCALE);

			assertEquals(keyPlaceDocument.getName(), otherLocale.getName());
			assertEquals(keyPlaceDocument.getAddress(),
					otherLocale.getAddress());
			assertEquals(keyPlaceDocument.getFullPinyin(),
					otherLocale.getFullPinyin());
			assertEquals(keyPlaceDocument.getSimplePinyin(),
					otherLocale.getSimplePinyin());
			assertNotNull(keyPlaceDocument.getCreateDate());
			System.out.println("OtherLocale索引添加校验成功!");
		}
	}

	public static void main(String[] args) {
		// 运行环境...
		GlobalValue.environment = "development";
		ValidateDocument validateDocument = new ValidateDocument();
		// 重点人员模块
		validateDocument.validateDruggy();// ---吸毒人员
		validateDocument.validateDangerousGoodsPractitioner();// ---危险品从业人员
		validateDocument.validateSuperiorVisit();// ---信访上访人员
		validateDocument.validateIdleYouth();// ---闲散少青年
		validateDocument.validateMentalPatient();// ---武疯子
		validateDocument.validatePositiveInfos();// ---归正人员
		validateDocument.validateRectificativePerson();// ---矫正人员

		// 重点场所模块
		validateDocument.validateEnterprise();// 企业
		validateDocument.validateSchool();// 学校
		validateDocument.validateHospital();// 医院
		validateDocument.validateOtherLocale();// 其他场所
	}

}
