	--数据管理模块，单位场所的权限
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '单位场所', 'newCompanyPlaceInformationTempManagement', 1, '数据管理子系统', 1, (select id from permissions where ename='dataManagePluginManagement'), null, null, null, 8, null);
	commit;

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '公共活动场所', 'newPublicPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newPublicPlaceTempManagement', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '交通场所', 'newTrafficPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newTrafficPlaceTempManagement', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '娱乐场所', 'newEntertainmentPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newEntertainmentPlaceTempManagement', null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '商贸场所', 'newTradePlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newTradePlaceTempManagement', null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '上网服务场所', 'newInternetServicesPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newInternetServicesPlaceTempManagement', null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '住宿服务场所', 'newAccommodationServicesPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newAccommodationServicesPlaceTempManagement', null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '餐饮服务场所', 'newFoodServicesPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newFoodServicesPlaceTempManagement', null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '旅游场所', 'newTravelingPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newTravelingPlaceTempManagement', null, 7, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '施工场所', 'newConstructionPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newConstructionPlaceTempManagement', null, 8, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他场所', 'newOtherPlaceTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newOtherPlaceTempManagement', null, 9, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '党政机关', 'newPartyGovernmentOrganCompanyTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newPartyGovernmentOrganCompanyTempManagement', null, 10, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '教育', 'newEducationCompanyTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newEducationCompanyTempManagement', null, 11, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '医疗卫生', 'newMedicalHygieneCompanyTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newMedicalHygieneCompanyTempManagement', null, 12, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '危化品存放单位', 'newDangerousStoreCompanyTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newDangerousStoreCompanyTempManagement', null, 13, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他单位', 'newOtherCompanyTempManagement', 1, '单位场所', 1, (select id from permissions  where ename = 'newCompanyPlaceInformationTempManagement'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=newOtherCompanyTempManagement', null, 14, null);
	commit;

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '公共活动场所导入', 'newPublicPlaceTempImport', 1, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewPublicPlaceTemp'||chr(38)||'module=NEWPUBLICPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '公共活动场所认领', 'newPublicPlaceTempclaim', 1, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewPublicPlaceTemp'||chr(38)||'module=NEWPUBLICPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '交通场所导入', 'newTrafficPlaceTempImport', 1, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTrafficPlaceTemp'||chr(38)||'module=TRAFFICPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '交通场所认领', 'newTrafficPlaceTempclaim', 1, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTrafficPlaceTemp'||chr(38)||'module=TRAFFICPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '娱乐场所导入', 'newEntertainmentPlaceTempImport', 1, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewEntertainmentPlaceTemp'||chr(38)||'module=ENTERTAINMENTPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '娱乐场所认领', 'newEntertainmentPlaceTempclaim', 1, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewEntertainmentPlaceTemp'||chr(38)||'module=ENTERTAINMENTPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '商贸场所导入', 'newTradePlaceTempImport', 1, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTradePlaceTemp'||chr(38)||'module=TRADEPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '商贸场所认领', 'newTradePlaceTempclaim', 1, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTradePlaceTemp'||chr(38)||'module=TRADEPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '上网服务场所导入', 'newInternetServicesPlaceTempImport', 1, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewInternetServicesPlaceTemp'||chr(38)||'module=NEWINTERNETBAR', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '上网服务场所认领', 'newInternetServicesPlaceTempclaim', 1, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewInternetServicesPlaceTemp'||chr(38)||'module=NEWINTERNETBAR'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '住宿服务场所导入', 'newAccommodationServicesPlaceTempImport', 1, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewAccommodationServicesPlaceTemp'||chr(38)||'module=ACCOMMODATIONSERVICESPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '住宿服务场所认领', 'newAccommodationServicesPlaceTempclaim', 1, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewAccommodationServicesPlaceTemp'||chr(38)||'module=ACCOMMODATIONSERVICESPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '餐饮服务场所导入', 'newFoodServicesPlaceTempImport', 1, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewFoodServicesPlaceTemp'||chr(38)||'module=NEWFOODSERVICESPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '餐饮服务场所认领', 'newFoodServicesPlaceTempclaim', 1, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewFoodServicesPlaceTemp'||chr(38)||'module=NEWFOODSERVICESPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '旅游场所导入', 'newTravelingPlaceTempImport', 1, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTravelingPlaceTemp'||chr(38)||'module=TRAVELINGPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '旅游场所认领', 'newTravelingPlaceTempclaim', 1, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewTravelingPlaceTemp'||chr(38)||'module=TRAVELINGPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '施工场所导入', 'newConstructionPlaceTempImport', 1, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewConstructionPlaceTemp'||chr(38)||'module=CONSTRUCTIONPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '施工场所认领', 'newConstructionPlaceTempclaim', 1, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewConstructionPlaceTemp'||chr(38)||'module=CONSTRUCTIONPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他场所导入', 'newOtherPlaceTempImport', 1, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewOtherPlaceTemp'||chr(38)||'module=OTHERPLACE', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他场所认领', 'newOtherPlaceTempclaim', 1, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewOtherPlaceTemp'||chr(38)||'module=OTHERPLACE'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '党政机关导入', 'newPartyGovernmentOrganCompanyTempImport', 1, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewPartyGovernmentOrganCompanyTemp'||chr(38)||'module=PARTYGOVERNMENTORGANCOMPANY', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '党政机关认领', 'newPartyGovernmentOrganCompanyTempclaim', 1, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewPartyGovernmentOrganCompanyTemp'||chr(38)||'module=PARTYGOVERNMENTORGANCOMPANY'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '教育导入', 'newEducationCompanyTempImport', 1, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewEducationCompanyTemp'||chr(38)||'module=NEWSCHOOLS', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '教育认领', 'newEducationCompanyTempclaim', 1, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewEducationCompanyTemp'||chr(38)||'module=NEWSCHOOLS'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '医疗卫生导入', 'newMedicalHygieneCompanyTempImport', 1, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewMedicalHygieneCompanyTemp'||chr(38)||'module=NEWHOSPITAL', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '医疗卫生认领', 'newMedicalHygieneCompanyTempclaim', 1, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewMedicalHygieneCompanyTemp'||chr(38)||'module=NEWHOSPITAL'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '危化品存放单位导入', 'newDangerousStoreCompanyTempImport', 1, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewDangerousStoreCompanyTemp'||chr(38)||'module=NEWDANGEROUSCHEMICALSUNIT', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '危化品存放单位认领', 'newDangerousStoreCompanyTempclaim', 1, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewDangerousStoreCompanyTemp'||chr(38)||'module=NEWDANGEROUSCHEMICALSUNIT'||chr(38)||'mode=claimList', null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他单位导入', 'newOtherCompanyTempImport', 1, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewOtherCompanyTemp'||chr(38)||'module=OTHERCOMPANY', null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '其他单位认领', 'newOtherCompanyTempclaim', 1, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempManagement'), null, '/hotModuel/dataManage/location/companyPlaceTempManage/companyPlaceTempList.ftl?moduleName=NewOtherCompanyTemp'||chr(38)||'module=OTHERCOMPANY'||chr(38)||'mode=claimList', null, 1, null);
	commit;
	
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewPublicPlaceTemp', 0, '公共活动场所', 1, (select id from permissions  where ename = 'newPublicPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewTrafficPlaceTemp', 0, '交通场所', 1,(select id from permissions  where ename = 'newTrafficPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewTrafficPlaceTemp', 0, '交通场所', 1, (select id from permissions  where ename = 'newTrafficPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewEntertainmentPlaceTemp', 0, '娱乐场所', 1, (select id from permissions  where ename = 'newEntertainmentPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewTradePlaceTemp', 0, '商贸场所', 1, (select id from permissions  where ename = 'newTradePlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewInternetServicesPlaceTemp', 0, '上网服务场所', 1,(select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewInternetServicesPlaceTemp', 0, '上网服务场所', 1, (select id from permissions  where ename = 'newInternetServicesPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewAccommodationServicesPlaceTemp', 0, '住宿服务场所', 1, (select id from permissions  where ename = 'newAccommodationServicesPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1,(select id from permissions  where ename = 'newFoodServicesPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewFoodServicesPlaceTemp', 0, '餐饮服务场所', 1, (select id from permissions  where ename = 'newFoodServicesPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewTravelingPlaceTemp', 0, '旅游场所', 1, (select id from permissions  where ename = 'newTravelingPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewConstructionPlaceTemp', 0, '施工场所', 1, (select id from permissions  where ename = 'newConstructionPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewOtherPlaceTemp', 0, '其他场所', 1, (select id from permissions  where ename = 'newOtherPlaceTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewPartyGovernmentOrganCompanyTemp', 0, '党政机关', 1, (select id from permissions  where ename = 'newPartyGovernmentOrganCompanyTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewEducationCompanyTemp', 0, '教育', 1,(select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewEducationCompanyTemp', 0, '教育', 1,(select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewEducationCompanyTemp', 0, '教育', 1, (select id from permissions  where ename = 'newEducationCompanyTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewMedicalHygieneCompanyTemp', 0, '医疗卫生', 1, (select id from permissions  where ename = 'newMedicalHygieneCompanyTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewDangerousStoreCompanyTemp', 0, '危化品存放单位', 1, (select id from permissions  where ename = 'newDangerousStoreCompanyTempclaim'), null, null, null, 6, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'importSearchNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempImport'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'importAdvancedSearchNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempImport'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '导入', 'importNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempImport'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'importViewNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempImport'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '批量删除', 'importDeleteNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempImport'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '搜索', 'claimSearchNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 0, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '高级搜索', 'claimAdvancedSearchNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 1, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '查看', 'claimViewNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 2, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '修改', 'claimUpdateNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 3, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '认领', 'claimNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 4, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '分步认领', 'stepClaimNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 5, null);

	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '撤销认领', 'unDoClaimNewOtherCompanyTemp', 0, '其他单位', 1, (select id from permissions  where ename = 'newOtherCompanyTempclaim'), null, null, null, 6, null);
	commit;






