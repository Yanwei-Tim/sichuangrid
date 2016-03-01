--单位场所区县以下领导视图权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'单位场所镇级领导视图', 'NEWCOMPANYPLACETownLeaderView', 0, '单位场所', 1, ' ', (select id from permissions where ename='newCompanyPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'单位场所社区级领导视图', 'NEWCOMPANYPLACEVillageLeaderView', 0, '单位场所', 1, ' ', (select id from permissions where ename='newCompanyPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'单位场所网格级领导视图', 'NEWCOMPANYPLACEGridLeaderView', 0, '单位场所', 1, ' ', (select id from permissions where ename='newCompanyPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=CompanyPlace' 
where id=(select id from permissions where ename = 'newCompanyPlaceManagement');

--公共活动场所区县以下领导视图
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'公共活动场所镇级领导视图', 'NEWPUBLICPLACETownLeaderView', 0, '公共活动场所', 1, ' ', (select id from permissions where ename='newPublicPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'公共活动场所社区级领导视图', 'NEWPUBLICPLACEVillageLeaderView', 0, '公共活动场所', 1, ' ', (select id from permissions where ename='newPublicPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'公共活动场所网格级领导视图', 'NEWPUBLICPLACEGridLeaderView', 0, '公共活动场所', 1, ' ', (select id from permissions where ename='newPublicPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=PublicPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newPublicPlaceManagement');

--交通场所区县以下领导视图权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'交通场所镇级领导视图', 'NEWTRAFFICPLACETownLeaderView', 0, '交通场所', 1, ' ', (select id from permissions where ename='newTrafficPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'交通场所社区级领导视图', 'NEWTRAFFICPLACEVillageLeaderView', 0, '交通场所', 1, ' ', (select id from permissions where ename='newTrafficPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'交通场所网格级领导视图', 'NEWTRAFFICPLACEGridLeaderView', 0, '交通场所', 1, ' ', (select id from permissions where ename='newTrafficPlaceManagement'));

--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=TrafficPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newTrafficPlaceManagement');
--娱乐场所区县以下领导视图权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'娱乐场所镇级领导视图', 'NEWENTERTAINMENTPLACETownLeaderView', 0, '娱乐场所', 1, ' ', (select id from permissions where ename='newEntertainmentPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'娱乐场所社区级领导视图', 'NEWENTERTAINMENTPLACEVillageLeaderView', 0, '娱乐场所', 1, ' ', (select id from permissions where ename='newEntertainmentPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'娱乐场所网格级领导视图', 'NEWENTERTAINMENTPLACEGridLeaderView', 0, '娱乐场所', 1, ' ', (select id from permissions where ename='newEntertainmentPlaceManagement'));

--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=EntertainmentPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newEntertainmentPlaceManagement');
--商贸场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'商贸场所镇级领导视图', 'NEWTRADEPLACETownLeaderView', 0, '商贸场所', 1, ' ', (select id from permissions where ename='newTradePlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'商贸场所社区级领导视图', 'NEWTRADEPLACEVillageLeaderView', 0, '商贸场所', 1, ' ', (select id from permissions where ename='newTradePlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'商贸场所网格级领导视图', 'NEWTRADEPLACEGridLeaderView', 0, '商贸场所', 1, ' ', (select id from permissions where ename='newTradePlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=TradePlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newTradePlaceManagement');
--网吧
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'网吧镇级领导视图', 'NEWINTERNETSERVICESPLACETownLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newInternetServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'网吧社区级领导视图', 'NEWINTERNETSERVICESPLACEVillageLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newInternetServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'网吧网格级领导视图', 'NEWINTERNETSERVICESPLACEGridLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newInternetServicesPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=InternetServicesPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newInternetServicesPlaceManagement');
--住宿服务场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'住宿服务场所镇级领导视图', 'NEWACCOMMODATIONSERVICESPLACETownLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newAccommodationServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'住宿服务场所社区级领导视图', 'NEWACCOMMODATIONSERVICESPLACEVillageLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newAccommodationServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'住宿服务场所网格级领导视图', 'NEWACCOMMODATIONSERVICESPLACEGridLeaderView', 0, '网吧', 1, ' ', (select id from permissions where ename='newAccommodationServicesPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=AccommodationServicesPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newAccommodationServicesPlaceManagement');
--餐饮服务场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'餐饮服务场所镇级领导视图', 'NEWFOODSERVICESPLACETownLeaderView', 0, '餐饮服务场所', 1, ' ', (select id from permissions where ename='newFoodServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'餐饮服务场所社区级领导视图', 'NEWFOODSERVICESPLACEVillageLeaderView', 0, '餐饮服务场所', 1, ' ', (select id from permissions where ename='newFoodServicesPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'餐饮服务场所网格级领导视图', 'NEWFOODSERVICESPLACEGridLeaderView', 0, '餐饮服务场所', 1, ' ', (select id from permissions where ename='newFoodServicesPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=FoodServicesPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newFoodServicesPlaceManagement');
--旅游场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'旅游场所镇级领导视图', 'NEWTRAVELINGPLACETownLeaderView', 0, '旅游场所', 1, ' ', (select id from permissions where ename='newTravelingPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'旅游场所社区级领导视图', 'NEWTRAVELINGPLACEVillageLeaderView', 0, '旅游场所', 1, ' ', (select id from permissions where ename='newTravelingPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'旅游场所网格级领导视图', 'NEWTRAVELINGPLACEGridLeaderView', 0, '旅游场所', 1, ' ', (select id from permissions where ename='newTravelingPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=TravelingPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newTravelingPlaceManagement');
--施工场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'施工场所镇级领导视图', 'NEWCONSTRUCTIONPLACETownLeaderView', 0, '施工场所', 1, ' ', (select id from permissions where ename='newConstructionPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'施工场所社区级领导视图', 'NEWCONSTRUCTIONPLACEVillageLeaderView', 0, '施工场所', 1, ' ', (select id from permissions where ename='newConstructionPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'施工场所网格级领导视图', 'NEWCONSTRUCTIONPLACEGridLeaderView', 0, '施工场所', 1, ' ', (select id from permissions where ename='newConstructionPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=ConstructionPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newConstructionPlaceManagement');
--其他场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他场所镇级领导视图', 'NEWOTHERPLACETownLeaderView', 0, '其他场所', 1, ' ', (select id from permissions where ename='newOtherPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他场所社区级领导视图', 'NEWOTHERPLACEVillageLeaderView', 0, '其他场所', 1, ' ', (select id from permissions where ename='newOtherPlaceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他场所网格级领导视图', 'NEWOTHERPLACEGridLeaderView', 0, '其他场所', 1, ' ', (select id from permissions where ename='newOtherPlaceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=OtherPlace'||chr(38)||'modulType=place' 
where id=(select id from permissions where ename = 'newOtherPlaceManagement');
------------单位

--党政机关
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'党政机关镇级领导视图', 'NEWPARTYGOVERNMENTORGANCOMPANYTownLeaderView', 0, '党政机关', 1, ' ', (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'党政机关社区级领导视图', 'NEWPARTYGOVERNMENTORGANCOMPANYVillageLeaderView', 0, '党政机关', 1, ' ', (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'党政机关网格级领导视图', 'NEWPARTYGOVERNMENTORGANCOMPANYGridLeaderView', 0, '党政机关', 1, ' ', (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=PartyGovernmentOrganCompany'||chr(38)||'modulType=company' 
where id=(select id from permissions where ename = 'newPartyGovernmentOrganCompanyManagement');
--学校
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'学校镇级领导视图', 'NEWEDUCATIONCOMPANYTownLeaderView', 0, '学校', 1, ' ', (select id from permissions where ename='newEducationCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'学校社区级领导视图', 'NEWEDUCATIONCOMPANYVillageLeaderView', 0, '学校', 1, ' ', (select id from permissions where ename='newEducationCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'学校网格级领导视图', 'NEWEDUCATIONCOMPANYGridLeaderView', 0, '学校', 1, ' ', (select id from permissions where ename='newEducationCompanyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=EducationCompany'||chr(38)||'modulType=company' 
where id=(select id from permissions where ename = 'newEducationCompanyManagement');
--医院
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'医院镇级领导视图', 'NEWMEDICALHYGIENECOMPANYTownLeaderView', 0, '医院', 1, ' ', (select id from permissions where ename='newMedicalHygieneCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'医院社区级领导视图', 'NEWMEDICALHYGIENECOMPANYVillageLeaderView', 0, '医院', 1, ' ', (select id from permissions where ename='newMedicalHygieneCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'医院网格级领导视图', 'NEWMEDICALHYGIENECOMPANYGridLeaderView', 0, '医院', 1, ' ', (select id from permissions where ename='newMedicalHygieneCompanyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=MedicalHygieneCompany'||chr(38)||'modulType=company' 
where id=(select id from permissions where ename = 'newMedicalHygieneCompanyManagement');
--危化品存放单位
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危化品存放单位镇级领导视图', 'NEWDANGEROUSSTORECOMPANYTownLeaderView', 0, '危化品存放单位', 1, ' ', (select id from permissions where ename='newDangerousStoreCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危化品存放单位社区级领导视图', 'NEWDANGEROUSSTORECOMPANYVillageLeaderView', 0, '危化品存放单位', 1, ' ', (select id from permissions where ename='newDangerousStoreCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危化品存放单位网格级领导视图', 'NEWDANGEROUSSTORECOMPANYGridLeaderView', 0, '危化品存放单位', 1, ' ', (select id from permissions where ename='newDangerousStoreCompanyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=DangerousStoreCompany'||chr(38)||'modulType=company' 
where id=(select id from permissions where ename = 'newDangerousStoreCompanyManagement');
--其他单位
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他单位镇级领导视图', 'NEWOTHERCOMPANYTownLeaderView', 0, '其他单位', 1, ' ', (select id from permissions where ename='newOtherCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他单位社区级领导视图', 'NEWOTHERCOMPANYVillageLeaderView', 0, '其他单位', 1, ' ', (select id from permissions where ename='newOtherCompanyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他单位网格级领导视图', 'NEWOTHERCOMPANYGridLeaderView', 0, '其他单位', 1, ' ', (select id from permissions where ename='newOtherCompanyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=OtherCompany'||chr(38)||'modulType=company' 
where id=(select id from permissions where ename = 'newOtherCompanyManagement');
--重点单位场所
--安全生产重点
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'安全生产重点镇级领导视图', 'NEWSAFETYPRODUCTIONKEYTownLeaderView', 0, '安全生产重点', 1, ' ', (select id from permissions where ename='newSafetyProductionKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'安全生产重点社区级领导视图', 'NEWSAFETYPRODUCTIONKEYVillageLeaderView', 0, '安全生产重点', 1, ' ', (select id from permissions where ename='newSafetyProductionKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'安全生产重点网格级领导视图', 'NEWSAFETYPRODUCTIONKEYGridLeaderView', 0, '安全生产重点', 1, ' ', (select id from permissions where ename='newSafetyProductionKeyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=SafetyProductionKey' 
where id=(select id from permissions where ename = 'newSafetyProductionKeyManagement');
--消防安全重点
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'消防安全重点镇级领导视图', 'NEWFIRESAFETYKEYTownLeaderView', 0, '消防安全重点', 1, ' ', (select id from permissions where ename='newFireSafetyKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'消防安全重点社区级领导视图', 'NEWFIRESAFETYKEYVillageLeaderView', 0, '消防安全重点', 1, ' ', (select id from permissions where ename='newFireSafetyKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'消防安全重点网格级领导视图', 'NEWFIRESAFETYKEYGridLeaderView', 0, '消防安全重点', 1, ' ', (select id from permissions where ename='newFireSafetyKeyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=FireSafetyKey' 
where id=(select id from permissions where ename = 'newFireSafetyKeyManagement');
--治安重点
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'治安重点镇级领导视图', 'NEWSECURITYKEYTownLeaderView', 0, '治安重点', 1, ' ', (select id from permissions where ename='newSecurityKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'治安重点社区级领导视图', 'NEWSECURITYKEYVillageLeaderView', 0, '治安重点', 1, ' ', (select id from permissions where ename='newSecurityKeyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'治安重点网格级领导视图', 'NEWSECURITYKEYGridLeaderView', 0, '治安重点', 1, ' ', (select id from permissions where ename='newSecurityKeyManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=SecurityKey' 
where id=(select id from permissions where ename = 'newSecurityKeyManagement');
--污染源
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'污染源镇级领导视图', 'NEWPOLLUTIONSOURCETownLeaderView', 0, '污染源', 1, ' ', (select id from permissions where ename='newPollutionSourceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'污染源社区级领导视图', 'NEWPOLLUTIONSOURCEVillageLeaderView', 0, '污染源', 1, ' ', (select id from permissions where ename='newPollutionSourceManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'污染源网格级领导视图', 'NEWPOLLUTIONSOURCEGridLeaderView', 0, '污染源', 1, ' ', (select id from permissions where ename='newPollutionSourceManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=PollutionSource' 
where id=(select id from permissions where ename = 'newPollutionSourceManagement');
------规模企业
--规上企业
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规上企业镇级领导视图', 'NEWENTERPRISETownLeaderView', 0, '规上企业', 1, ' ', (select id from permissions where ename='newEnterpriseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规上企业社区级领导视图', 'NEWENTERPRISEVillageLeaderView', 0, '规上企业', 1, ' ', (select id from permissions where ename='newEnterpriseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规上企业网格级领导视图', 'NEWENTERPRISEGridLeaderView', 0, '规上企业', 1, ' ', (select id from permissions where ename='newEnterpriseManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=Enterprise' 
where id=(select id from permissions where ename = 'newEnterpriseManagement');
--规下企业
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规下企业镇级领导视图', 'NEWENTERPRISEDOWNTownLeaderView', 0, '规下企业', 1, ' ', (select id from permissions where ename='newEnterpriseDownManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规下企业社区级领导视图', 'NEWENTERPRISEDOWNVillageLeaderView', 0, '规下企业', 1, ' ', (select id from permissions where ename='newEnterpriseDownManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'规下企业网格级领导视图', 'NEWENTERPRISEDOWNGridLeaderView', 0, '规下企业', 1, ' ', (select id from permissions where ename='newEnterpriseDownManagement'));
--gridUrl
update permissions set gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=EnterpriseDown' 
where id=(select id from permissions where ename = 'newEnterpriseDownManagement');

commit;

---------组织机构---------------
--非公有制经济组织
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'非公有制经济组织镇级领导视图', 'NEWECONOMICORGANIZATIONSTownLeaderView', 0, '非公有制经济组织', 1, ' ', (select id from permissions where ename='newEconomicOrganizationsManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'非公有制经济组织社区级领导视图', 'NEWECONOMICORGANIZATIONSVillageLeaderView', 0, '非公有制经济组织', 1, ' ', (select id from permissions where ename='newEconomicOrganizationsManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'非公有制经济组织网格级领导视图', 'NEWECONOMICORGANIZATIONSGridLeaderView', 0, '非公有制经济组织', 1, ' ', (select id from permissions where ename='newEconomicOrganizationsManagement'));
---------房屋---------------
--1.实有房屋
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'实有房屋镇级领导视图', 'ACTUALHOUSETownLeaderView', 0, '实有房屋', 1, ' ', (select id from permissions where ename='actualHouseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'实有房屋社区级领导视图', 'ACTUALHOUSEVillageLeaderView', 0, '实有房屋', 1, ' ', (select id from permissions where ename='actualHouseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'实有房屋网格级领导视图', 'ACTUALHOUSEGridLeaderView', 0, '实有房屋', 1, ' ', (select id from permissions where ename='actualHouseManagement'));
--2.出租房
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'出租房镇级领导视图', 'RENTALHOUSETownLeaderView', 0, '出租房', 1, ' ', (select id from permissions where ename='rentalHouseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'出租房社区级领导视图', 'RENTALHOUSEVillageLeaderView', 0, '出租房', 1, ' ', (select id from permissions where ename='rentalHouseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'出租房网格级领导视图', 'RENTALHOUSEGridLeaderView', 0, '出租房', 1, ' ', (select id from permissions where ename='rentalHouseManagement'));
--3.楼宇信息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'楼宇信息镇级领导视图', 'BUILDDATASTownLeaderView', 0, '楼宇信息管理', 1, ' ', (select id from permissions where ename='builddatasManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'楼宇信息社区级领导视图', 'BUILDDATASVillageLeaderView', 0, '楼宇信息管理', 1, ' ', (select id from permissions where ename='builddatasManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'楼宇信息网格级领导视图', 'BUILDDATASGridLeaderView', 0, '楼宇信息管理', 1, ' ', (select id from permissions where ename='builddatasManagement'));



------------人口系统---------------------
--1.户籍人口
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'户籍人口镇级领导视图', 'HOUSEHOLDSTAFFTownLeaderView', 0, '户籍人口', 1, ' ', (select id from permissions where ename='householdStaffManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'户籍人口社区级领导视图', 'HOUSEHOLDSTAFFVillageLeaderView', 0, '户籍人口', 1, ' ', (select id from permissions where ename='householdStaffManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'户籍人口网格级领导视图', 'HOUSEHOLDSTAFFGridLeaderView', 0, '户籍人口', 1, ' ', (select id from permissions where ename='householdStaffManagement'));

--2.流动人口
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'流动人口镇级领导视图', 'FLOATINGPOPULATIONTownLeaderView', 0, '流动人口', 1, ' ', (select id from permissions where ename='floatingPopulationManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'流动人口社区级领导视图', 'FLOATINGPOPULATIONVillageLeaderView', 0, '流动人口', 1, ' ', (select id from permissions where ename='floatingPopulationManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'流动人口网格级领导视图', 'FLOATINGPOPULATIONGridLeaderView', 0, '流动人口', 1, ' ', (select id from permissions where ename='floatingPopulationManagement'));

--3.未落户人口
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'未落户人口镇级领导视图', 'UNSETTLEDPOPULATIONTownLeaderView', 0, '未落户人口', 1, ' ', (select id from permissions where ename='unsettledPopulation'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'未落户人口社区级领导视图', 'UNSETTLEDPOPULATIONVillageLeaderView', 0, '未落户人口', 1, ' ', (select id from permissions where ename='unsettledPopulation'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'未落户人口网格级领导视图', 'UNSETTLEDPOPULATIONGridLeaderView', 0, '未落户人口', 1, ' ', (select id from permissions where ename='unsettledPopulation'));

--4.境外人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'境外人员镇级领导视图', 'OVERSEAPERSONTownLeaderView', 0, '境外人员', 1, ' ', (select id from permissions where ename='overseaPersonManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'境外人员社区级领导视图', 'OVERSEAPERSONVillageLeaderView', 0, '境外人员', 1, ' ', (select id from permissions where ename='overseaPersonManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'境外人员网格级领导视图', 'OVERSEAPERSONGridLeaderView', 0, '境外人员', 1, ' ', (select id from permissions where ename='overseaPersonManagement'));

--5.刑释解教人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'刑释解教人员镇级领导视图', 'POSITIVEINFOTownLeaderView', 0, '刑释解教人员', 1, ' ', (select id from permissions where ename='positiveInfoManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'刑释解教人员社区级领导视图', 'POSITIVEINFOVillageLeaderView', 0, '刑释解教人员', 1, ' ', (select id from permissions where ename='positiveInfoManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'刑释解教人员网格级领导视图', 'POSITIVEINFOGridLeaderView', 0, '刑释解教人员', 1, ' ', (select id from permissions where ename='positiveInfoManagement'));

--6.社区矫正人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'社区矫正人员镇级领导视图', 'RECTIFICATIVEPERSONTownLeaderView', 0, '社区矫正人员', 1, ' ', (select id from permissions where ename='rectificativePersonManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'社区矫正人员社区级领导视图', 'RECTIFICATIVEPERSONVillageLeaderView', 0, '社区矫正人员', 1, ' ', (select id from permissions where ename='rectificativePersonManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'社区矫正人员网格级领导视图', 'RECTIFICATIVEPERSONGridLeaderView', 0, '社区矫正人员', 1, ' ', (select id from permissions where ename='rectificativePersonManagement'));

--7.严重精神障碍患者
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'严重精神障碍患者镇级领导视图', 'MENTALPATIENTTownLeaderView', 0, '严重精神障碍患者', 1, ' ', (select id from permissions where ename='mentalPatientManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'严重精神障碍患者社区级领导视图', 'MENTALPATIENTVillageLeaderView', 0, '严重精神障碍患者', 1, ' ', (select id from permissions where ename='mentalPatientManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'严重精神障碍患者网格级领导视图', 'MENTALPATIENTGridLeaderView', 0, '严重精神障碍患者', 1, ' ', (select id from permissions where ename='mentalPatientManagement'));

--8.吸毒人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'吸毒人员镇级领导视图', 'DRUGGYTownLeaderView', 0, '吸毒人员', 1, ' ', (select id from permissions where ename='druggyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'吸毒人员社区级领导视图', 'DRUGGYVillageLeaderView', 0, '吸毒人员', 1, ' ', (select id from permissions where ename='druggyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'吸毒人员网格级领导视图', 'DRUGGYGridLeaderView', 0, '吸毒人员', 1, ' ', (select id from permissions where ename='druggyManagement'));

--9.艾滋病人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'艾滋病人员镇级领导视图', 'AIDSPOPULATIONSTownLeaderView', 0, '艾滋病人员', 1, ' ', (select id from permissions where ename='aidspopulationsManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'艾滋病人员社区级领导视图', 'AIDSPOPULATIONSVillageLeaderView', 0, '艾滋病人员', 1, ' ', (select id from permissions where ename='aidspopulationsManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'艾滋病人员网格级领导视图', 'AIDSPOPULATIONSGridLeaderView', 0, '艾滋病人员', 1, ' ', (select id from permissions where ename='aidspopulationsManagement'));

--10.重点青少年
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点青少年镇级领导视图', 'IDLEYOUTHTownLeaderView', 0, '重点青少年', 1, ' ', (select id from permissions where ename='idleYouthManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点青少年社区级领导视图', 'IDLEYOUTHVillageLeaderView', 0, '重点青少年', 1, ' ', (select id from permissions where ename='idleYouthManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点青少年网格级领导视图', 'IDLEYOUTHGridLeaderView', 0, '重点青少年', 1, ' ', (select id from permissions where ename='idleYouthManagement'));

--11.重点上访人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点上访人员镇级领导视图', 'SUPERIORVISITTownLeaderView', 0, '重点上访人员', 1, ' ', (select id from permissions where ename='superiorVisitManag'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点上访人员社区级领导视图', 'SUPERIORVISITVillageLeaderView', 0, '重点上访人员', 1, ' ', (select id from permissions where ename='superiorVisitManag'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'重点上访人员网格级领导视图', 'SUPERIORVISITGridLeaderView', 0, '重点上访人员', 1, ' ', (select id from permissions where ename='superiorVisitManag'));

--12.危险品从业人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危险品从业人员镇级领导视图', 'DANGEROUSGOODSPRACTITIONERTownLeaderView', 0, '危险品从业人员', 1, ' ', (select id from permissions where ename='dangerousGoodsPractitionerManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危险品从业人员社区级领导视图', 'DANGEROUSGOODSPRACTITIONERVillageLeaderView', 0, '危险品从业人员', 1, ' ', (select id from permissions where ename='dangerousGoodsPractitionerManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'危险品从业人员网格级领导视图', 'DANGEROUSGOODSPRACTITIONERGridLeaderView', 0, '危险品从业人员', 1, ' ', (select id from permissions where ename='dangerousGoodsPractitionerManagement'));

--13.其他人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他人员镇级领导视图', 'OTHERATTENTIONPERSONNELTownLeaderView', 0, '其他人员', 1, ' ', (select id from permissions where ename='otherAttentionPersonnelManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他人员社区级领导视图', 'OTHERATTENTIONPERSONNELVillageLeaderView', 0, '其他人员', 1, ' ', (select id from permissions where ename='otherAttentionPersonnelManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'其他人员网格级领导视图', 'OTHERATTENTIONPERSONNELGridLeaderView', 0, '其他人员', 1, ' ', (select id from permissions where ename='otherAttentionPersonnelManagement'));

--14.老年人
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'老年人镇级领导视图', 'ELDERLYPEOPLETownLeaderView', 0, '老年人', 1, ' ', (select id from permissions where ename='elderlyPeopleManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'老年人社区级领导视图', 'ELDERLYPEOPLEVillageLeaderView', 0, '老年人', 1, ' ', (select id from permissions where ename='elderlyPeopleManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'老年人网格级领导视图', 'ELDERLYPEOPLEGridLeaderView', 0, '老年人', 1, ' ', (select id from permissions where ename='elderlyPeopleManagement'));

--15.残疾人
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'残疾人镇级领导视图', 'HANDICAPPEDTownLeaderView', 0, '残疾人', 1, ' ', (select id from permissions where ename='handicappedManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'残疾人社区级领导视图', 'HANDICAPPEDVillageLeaderView', 0, '残疾人', 1, ' ', (select id from permissions where ename='handicappedManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'残疾人网格级领导视图', 'HANDICAPPEDGridLeaderView', 0, '残疾人', 1, ' ', (select id from permissions where ename='handicappedManagement'));

--16.优抚对象
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'优抚对象镇级领导视图', 'OPTIMALOBJECTTownLeaderView', 0, '优抚对象', 1, ' ', (select id from permissions where ename='optimalObjectManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'优抚对象社区级领导视图', 'OPTIMALOBJECTVillageLeaderView', 0, '优抚对象', 1, ' ', (select id from permissions where ename='optimalObjectManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'优抚对象网格级领导视图', 'OPTIMALOBJECTGridLeaderView', 0, '优抚对象', 1, ' ', (select id from permissions where ename='optimalObjectManagement'));

--17.需救助人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'需救助人员镇级领导视图', 'AIDNEEDPOPULATIONTownLeaderView', 0, '需救助人员', 1, ' ', (select id from permissions where ename='aidNeedPopulationManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'需救助人员社区级领导视图', 'AIDNEEDPOPULATIONVillageLeaderView', 0, '需救助人员', 1, ' ', (select id from permissions where ename='aidNeedPopulationManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'需救助人员网格级领导视图', 'AIDNEEDPOPULATIONGridLeaderView', 0, '需救助人员', 1, ' ', (select id from permissions where ename='aidNeedPopulationManagement'));

--18.失业人员
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'失业人员镇级领导视图', 'UNEMPLOYEDPEOPLETownLeaderView', 0, '失业人员', 1, ' ', (select id from permissions where ename='unemployedPeopleManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'失业人员社区级领导视图', 'UNEMPLOYEDPEOPLEVillageLeaderView', 0, '失业人员', 1, ' ', (select id from permissions where ename='unemployedPeopleManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'失业人员网格级领导视图', 'UNEMPLOYEDPEOPLEGridLeaderView', 0, '失业人员', 1, ' ', (select id from permissions where ename='unemployedPeopleManagement'));

--18.育龄妇女
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'育龄妇女镇级领导视图', 'NURTURESWOMENTownLeaderView', 0, '育龄妇女', 1, ' ', (select id from permissions where ename='nurturesWomen'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'育龄妇女社区级领导视图', 'NURTURESWOMENVillageLeaderView', 0, '育龄妇女', 1, ' ', (select id from permissions where ename='nurturesWomen'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'育龄妇女网格级领导视图', 'NURTURESWOMENGridLeaderView', 0, '育龄妇女', 1, ' ', (select id from permissions where ename='nurturesWomen'));

--房屋系统
--实有房屋
update permissions set gridurl='/hotModuel/baseinfo/leaderView/house/gridActualHouse.jsp'
 where id=(select id from permissions where ename='actualHouseManagement');
--出租房
update permissions set gridurl='/hotModuel/baseinfo/leaderView/house/gridRentalHouse.jsp' 
 where id=(select id from permissions where ename='rentalHouseManagement');
--楼宇信息
update permissions set gridurl='/hotModuel/baseinfo/leaderView/house/gridBuilddataStatistics.jsp'
 where id=(select id from permissions where ename='builddatasManagement');

--组织机构
--非公有制经济组织
update permissions set gridurl='/hotModuel/baseinfo/leaderView/doubleNewOrganization.jsp/gridNewEconomic.jsp' 
 where id=(select id from permissions where ename='newEconomicOrganizationsManagement');

--人口系统
--户籍人口
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridHouseholdstaffStatistics.jsp' 
 where id=(select id from permissions where ename='householdStaffManagement');
--流动人口
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridFloatingPopulationStatistics.jsp'
 where id=(select id from permissions where ename='floatingPopulationManagement');
--未落户人口
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridUnsettedPopulationStatistics.jsp'
 where id=(select id from permissions where ename='unsettledPopulation');
--境外人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridOverseaPersonnelStatistics.jsp' 
 where id=(select id from permissions where ename='overseaPersonManagement');
--刑释解教人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/positiveInfo/statisticsAddCount.jsp?isGrid=true' 
 where id=(select id from permissions where ename='positiveInfoManagement');
--社区矫正人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/rectifiyStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='rectificativePersonManagement');
--严重精神障碍患者
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/mentalPatientStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='mentalPatientManagement');
--吸毒人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/druggyStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='druggyManagement');
--艾滋病人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/aidspopulationsStatistics.jsp' 
 where id=(select id from permissions where ename='aidspopulationsManagement');
--重点青少年
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/idleYouthStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='idleYouthManagement');
--重点上访人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/superiorVisitStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='superiorVisitManagement');
--危险品从业人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/dangerousGoodsPractitionerStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='dangerousGoodsPractitionerManagement');
--其他人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/otherAttentionPersonnestatiStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='otherAttentionPersonnelManagement');
--老年人
update permissions set gridurl='/hotModuel/baseinfo/leaderView/civil/elderlyPeopleStatistics.jsp?isGrid=true'
 where id=(select id from permissions where ename='elderlyPeopleManagement');
--残疾人
update permissions set gridurl='/hotModuel/baseinfo/leaderView/civil/handicappedStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='handicappedManagement');
--优抚对象
update permissions set gridurl='/hotModuel/baseinfo/leaderView/civil/optimalObjectStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='optimalObjectManagement');
--需要救助人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/civil/aidneedpopulationStatistics.jsp?isGrid=true'
 where id=(select id from permissions where ename='aidNeedPopulationManagement');
--失业人员
update permissions set gridurl='/hotModuel/baseinfo/leaderView/importPersonStatistics/unemployedPeopleStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='unemployedPeopleManagement');
--育龄妇女
update permissions set gridurl='/hotModuel/baseinfo/leaderView/birth/nurturesWomenStatistics.jsp?isGrid=true' 
 where id=(select id from permissions where ename='nurturesWomen');

--岗位新增配置领导视图权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'配置领导视图岗位', 'addLeaderViewRole', 0, '岗位管理', 1, ' ', (select id from permissions where ename='roleManagement'));

commit;

--青少年 少先队员 共青团员区县一下领导视图权限
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '青少年镇级领导视图','YOUNGSTERSTownLeaderView',0,
   '青少年',1, ' ',(select id from permissions where ename = 'youngstersManagement'),'',2);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '青少年社区领导视图','YOUNGSTERSVillageLeaderView',0,
   '青少年',1, ' ',(select id from permissions where ename = 'youngstersManagement'),'',3);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '青少年网格级领导视图','YOUNGSTERSGridLeaderView',0,
   '青少年',1, ' ',(select id from permissions where ename = 'youngstersManagement'),'',4);

insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '少先队员镇级领导视图','YOUNGPIONEERTownLeaderView',0,
   '少先队员',1, ' ',(select id from permissions where ename = 'youngpioneerManagement'),'',2);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '少先队员社区领导视图','YOUNGPIONEERVillageLeaderView',0,
   '少先队员',1, ' ',(select id from permissions where ename = 'youngpioneerManagement'),'',3);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '少先队员网格级领导视图','YOUNGPIONEERGridLeaderView',0,
   '少先队员',1, ' ',(select id from permissions where ename = 'youngpioneerManagement'),'',4);

insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '共青团员镇级领导视图','LEAGUEMEMBERTownLeaderView',0,
   '共青团员',1, ' ',(select id from permissions where ename = 'leaguememberManagement'),'',2);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '共青团员社区领导视图','LEAGUEMEMBERVillageLeaderView',0,
   '共青团员',1, ' ',(select id from permissions where ename = 'leaguememberManagement'),'',3);
   
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID,NORMALURL,INDEXID)
values(s_permissions.NEXTVAL, '共青团员网格级领导视图','LEAGUEMEMBERGridLeaderView',0,
   '共青团员',1, ' ',(select id from permissions where ename = 'leaguememberManagement'),'',4);
   
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridYouthsStatistics.jsp?keyType=youngsters' where ename = 'youngstersManagement';
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridYouthsStatistics.jsp?keyType=youngpioneer' where ename = 'youngpioneerManagement';
update permissions set gridurl='/hotModuel/baseinfo/leaderView/populationStatistics/gridYouthsStatistics.jsp?keyType=leaguemember' where ename = 'leaguememberManagement';
commit;  



-----三本台对账建表类型为空的数据处理
----稳定工作台账
update steadyworks t
   set t.createtabletype = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '建表类型')
                               and displayname = '新建')
 where t.createtabletype is null
    or t.createtabletype = '';
-----困难工作台账
update poorpeoples t
   set t.createtabletype = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '建表类型')
                               and displayname = '新建')
 where t.createtabletype is null
    or t.createtabletype = '' ;  
-----民生诉求台账
update peopleaspirations t
   set t.createtabletype = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '建表类型')
                               and displayname = '新建')
 where t.createtabletype is null
    or t.createtabletype = '';
commit;









