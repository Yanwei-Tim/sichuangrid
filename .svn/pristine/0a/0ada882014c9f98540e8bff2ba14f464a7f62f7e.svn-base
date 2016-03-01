package com.tianque.solr.init;

import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.School;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.service.EnterpriseService;
import com.tianque.service.HospitalService;
import com.tianque.service.OtherLocaleService;
import com.tianque.service.SchoolService;
import com.tianque.service.vo.BaseInfoTableTypes;
import com.tianque.solr.domain.DocumentType;

public class PageInfoHelper {
	public static PageInfo getPageInfo(DocumentType documentType,
			Organization rootOrg, int page) {
		if (DocumentType.DRUGGY.equals(documentType)) {
			DruggyService druggyService = (DruggyService) ServiceFactory
					.getService("druggyService");
			return druggyService.findDruggyForPageByOrgInternalCode(
					rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.DANGEROUS_GOODS_PRACTITIONER
				.equals(documentType)) {
			DangerousGoodsPractitionerService dangerousGoodsPractitionerService = (DangerousGoodsPractitionerService) ServiceFactory
					.getService("dangerousGoodsPractitionerService");
			return dangerousGoodsPractitionerService
					.findDangerousGoodsPractitionersForPageByOrgId(
							rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.SUPERIOR_VISIT.equals(documentType)) {
			SuperiorVisitService superiorVisitService = (SuperiorVisitService) ServiceFactory
					.getService("superiorVisitService");
			return superiorVisitService
					.findSuperiorVisitForPageByOrgInternalCode(rootOrg.getId(),
							page, 100, "id", "", null);

		} else if (DocumentType.IDLE_YOUTH.equals(documentType)) {
			IdleYouthService idleYouthService = (IdleYouthService) ServiceFactory
					.getService("idleYouthService");
			return idleYouthService.findIdleYouthsForPageByOrganizationId(
					rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.DANGEROUS_GOODS_PRACTITIONER
				.equals(documentType)) {
			DangerousGoodsPractitionerService dangerousGoodsPractitionerService = (DangerousGoodsPractitionerService) ServiceFactory
					.getService("dangerousGoodsPractitionerService");
			return dangerousGoodsPractitionerService
					.findDangerousGoodsPractitionersForPageByOrgId(
							rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.POSITIVE_INFO.equals(documentType)) {
			PositiveInfoService posiviteInfoService = (PositiveInfoService) ServiceFactory
					.getService("posiviteInfoService");
			PositiveInfo positiveInfos = new PositiveInfo();
			positiveInfos.setOrgInternalCode(rootOrg.getOrgInternalCode());
			return posiviteInfoService.findPositiveInfoForPage(positiveInfos,
					page, 100, "id", "", null);
		} else if (DocumentType.RECTIFICATIVEPERSON.equals(documentType)) {
			RectificativePersonService rectificativePersonService = (RectificativePersonService) ServiceFactory
					.getService("rectificativePersonService");
			return rectificativePersonService
					.findRectificativePersonsForPageByOrgInternalCode(
							rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.MENTAL_PATIENT.equals(documentType)) {
			MentalPatientService mentalPatientService = (MentalPatientService) ServiceFactory
					.getService("mentalPatientService");
			return mentalPatientService
					.findMentalPatientsForPageByOrgInternalCode(
							rootOrg.getId(), page, 100, "id", "", null);
		} else if (DocumentType.ENTERPRISEKEY.equals(documentType)
				|| DocumentType.PROTECTIONKEY.equals(documentType)
				|| DocumentType.SAFETYPRODUCTIONKEY.equals(documentType)
				|| DocumentType.FIRESAFETYKEY.equals(documentType)
				|| DocumentType.SECURITYKEY.equals(documentType)
				|| DocumentType.ENTERPRISEDOWNKEY.equals(documentType)) {
			EnterpriseService enterpriseService = (EnterpriseService) ServiceFactory
					.getService("enterpriseService");
			return enterpriseService
					.findEnterprisesForListPageByOrganizationIdAndKeyType(
							rootOrg.getId(), BaseInfoTableTypes
									.toString(documentType.toString()), page,
							100, "id", "", null);
		} else if (DocumentType.SCHOOL.equals(documentType)) {
			SchoolService schoolService = (SchoolService) ServiceFactory
					.getService("schoolService");
			School school = new School();
			school.setOrgInternalCode(rootOrg.getOrgInternalCode());
			return schoolService.finallSchoolList(school, page, 100, "id", "",
					null);
		} else if (DocumentType.HOSPITAL.equals(documentType)) {
			HospitalService hospitalService = (HospitalService) ServiceFactory
					.getService("hospitalService");
			SearchHospitalVo hospital = new SearchHospitalVo();
			hospital.setOrgInternalCode(rootOrg.getOrgInternalCode());
			return hospitalService.searchHospitalForPage(page, 100, "id", "",
					hospital);
		} else if (DocumentType.OTHER_LOCALE.equals(documentType)) {
			OtherLocaleService otherLocaleService = (OtherLocaleService) ServiceFactory
					.getService("otherLocaleService");
			return otherLocaleService.findOtherLocalesForPage(rootOrg.getId(),
					page, 100, "id", "", null);
		} else {
			return null;
		}
	}
}
