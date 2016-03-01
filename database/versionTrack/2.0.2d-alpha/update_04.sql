----楼宇索引
create index idx_builddatas_orgId on builddatas(orgId);
create index idx_builddatas_createdate on builddatas(createdate);


--组织机构外键关联去除
alter table attentionObjects  drop constraint  fkAttentionObjectsOrg;
alter table servicePersons  drop constraint  fkServicePersonsOrg;
alter table dailyLogs  drop constraint  fkDailyLogOrg;
alter table partyOrgActivitys  drop constraint  fkPartyOrgActivitys;
alter table partyMemberInfos  drop constraint  fkPartyMemberInfos;
alter table examineScores  drop constraint  fkExamineScoresOrg;
alter table issues  drop constraint  fkIssuesOrg;
alter table historicalIssues  drop constraint  fkHistoricalIssuesOrg;
alter table publicltyCass  drop constraint  fkPublicltyCassOrg;
alter table issueStatueStanals  drop constraint  fkIssueStatueStanalsOrg;
alter table issueTypeStanals  drop constraint  fkIssueTypeStanalsOrg;
alter table lettingHouses  drop constraint  fkLettingHousesOrg;
alter table constructionUnits  drop constraint  fkconstructionUnits;
alter table overTimeIssueLog  drop constraint  fkOvertimeissuelogOrg;
alter table poorpeople  drop constraint  fkpoorpeopleOrg;
alter table smsReceivedBoxs  drop constraint  fkSmsReceivedBoxsOrg;
alter table statementStatistics  drop constraint  fkStatementStatisticsOrg;
alter table systemLogs  drop constraint  fkSystemLogsOrg;
alter table userHasMultizones  drop constraint  fkUserHasOrganizationOrg;
alter table workBulletins  drop constraint  fkWorkBulletinsOrg;
alter table evaluates  drop constraint  fkOrganizations_evaluates;
alter table specialCareGroups  drop constraint  fkspecialCareGroupsOrg;
alter table ISSUEDEALSTATS  drop constraint  FK_ISSUEDEALSTATORGID;
alter table systemLogs_Archive  drop constraint  fkSystemLogsArchiveOrg;
alter table keyAreasOfInvestigationInfos  drop constraint  fkKeyAreasOfInvestigationInfos;
alter table significantIssuedeals  drop constraint  fkSignificantIssuedeals;
alter table HOUSINGINFO  drop constraint  FK_HOUSINGINFO_ORGID;
alter table HOUSINGINFO  drop constraint  FK_HOUSINGINFO_TOWNID;
alter table BUILDINGDATAS  drop constraint  fkBuildingDatasOrg;
alter table undergroundSpaces  drop constraint  fkUndergroundSpaceOrg;
alter table partyConstructionFiles  drop constraint  fkpartyConstructionFilesOrg;
alter table speech  drop constraint  fkSpeechOrg;
alter table news  drop constraint  fkNewsOrg;
alter table oldTrees  drop constraint  fkoldTreeOrg;
alter table cleaners  drop constraint  fkcleanerOrg;
alter table communalFacilities  drop constraint  fkcommunalFacilitiesOrg;
alter table roadTraffic  drop constraint  fkroadTrafficOrg;
alter table landscaping  drop constraint  fklandscapingOrg;
alter table housingAndLand  drop constraint  fkhousingAndLandOrg;
alter table otherFacilities  drop constraint  fkotherFacilitiesOrg;
alter table partyAbilityPerson  drop constraint  fkpartyAbilityPersonOrg;
alter table teamInfos  drop constraint  fkteamInfosOrg;
alter table operateLogs  drop constraint  fkoperateLogs;
alter table LaborEmploymentUnit  drop constraint  fkLaborEmploymentUnit;
alter table EmergencyShelter  drop constraint  fkEmergencyShelter;
alter table documentsHasOrg  drop constraint  fkdocumentsHasOrgs;
alter table otherLocalesTemp  drop constraint  fkOtherLocalesTempOrg;
alter table religion  drop constraint  fkReligion;
alter table religionBelief  drop constraint  fkReligionBelief;
alter table administrativeInstitution  drop constraint  fkadm_InstitutionOrg;
alter table enterprisesTemp  drop constraint  fkEnterprisesTempOrg;
alter table SecurityPropaganda  drop constraint  fkSecurityPropaganda;
alter table societySecurity  drop constraint  fkSocietySecurity;
alter table seriesSecurity  drop constraint  fkSeriesSecurity;
alter table gridManagementNormal  drop constraint  fkgridManagementNormal;
alter table systemOperateLogs  drop constraint  fksystemOperateLogs;
alter table hospitals  drop constraint  fkHospitalsOrg;
alter table schools  drop constraint  fkSchoolsOrg;
alter table otherLocales  drop constraint  fkOtherLocalesOrg;
alter table ACTUALCOMPANY  drop constraint  fkactualCompany;
alter table publicPlace  drop constraint  fkPublicPlace;
alter table publicComplexPlaces  drop constraint  fkPublicComplexPlace;
alter table dangerousChemicalsUnit  drop constraint  fkDangerousChemicalsUnitOrg;
alter table internetBar  drop constraint  fkinternetBar;
alter table publicComplexPlaces  drop constraint  fkPublicComplexPlace;
alter table enterprises  drop constraint  fkEnterprisesOrg;
alter table DM_hospitals_Temp  drop constraint  fkHospitalsTempOrg;
alter table DM_ACTUALCOMPANY_Temp  drop constraint  FKDM_ACTUALCOMPANYTEMPORG;
alter table DM_dangeCUnit_Temp  drop constraint  FKDM_DANGERCUTEMPORG;
alter table DM_internetBar_Temp  drop constraint  FKDM_INTERNETBARTEMPORG;
alter table DM_publicPlace_Temp  drop constraint  FKDM_PUBLICPLACETEMPORG;
alter table DM_otherLocales_Temp  drop constraint  FKDM_OTHERLOCALESTEMPORG;
alter table DM_schools_Temp  drop constraint  FKDM_SCHOOLSTEMPORG;
alter table DM_safetyProduct_Temp  drop constraint  FKDM_SAFETYPRODUCTTEMPORG;
alter table DM_FireSafetyE_Temp  drop constraint  FKDM_FIRESAFETYETEMPORG;
alter table DM_SecurityE_Temp  drop constraint  FKDM_SECURITYETEMPORG;
alter table DM_enterpriseKey_Temp  drop constraint  FKDM_ENTERPRISESORGTEMP;
alter table DM_enterpriseDownKey_TEMP  drop constraint  FKDM_ENTERPRISESDOWNORGTEMP;
alter table DM_hospitals_Temp  drop constraint  fkHospitalsTempOrg;




--备份表ID变大
alter table backupBaseData modify DATAID number(15);