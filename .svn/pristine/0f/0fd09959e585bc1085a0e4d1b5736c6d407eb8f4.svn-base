--单位场所系统权限脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '单位场所系统', 'newCompanyPlaceManageSystem', 1, ' ', 1, null, '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '单位场所', 'newCompanyPlaceManagement', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=CompanyPlace', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferCompanyPlace', 0, '单位场所', 1, (select id from permissions where ename='newCompanyPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '场所', 'newPlaceInformation', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '公共活动场所', 'newPublicPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=PublicPlace'||chr(38)||'modulType=place', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdatePublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeletePublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferPublicPlace', 0, '公共活动场所', 1, (select id from permissions where ename='newPublicPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '交通场所', 'newTrafficPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=TrafficPlace'||chr(38)||'modulType=place', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferTrafficPlace', 0, '交通场所', 1, (select id from permissions where ename='newTrafficPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '娱乐场所', 'newEntertainmentPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=EntertainmentPlace'||chr(38)||'modulType=place', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddEntertainmentPlace', 0, '娱乐场所', 1,  (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferEntertainmentPlace', 0, '娱乐场所', 1, (select id from permissions where ename='newEntertainmentPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '商贸场所', 'newTradePlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=TradePlace'||chr(38)||'modulType=place', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferTradePlace', 0, '商贸场所', 1, (select id from permissions where ename='newTradePlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '上网服务场所', 'newInternetServicesPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=InternetServicesPlace'||chr(38)||'modulType=place', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferInternetServicesPlace', 0, '上网服务场所', 1, (select id from permissions where ename='newInternetServicesPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '住宿服务场所', 'newAccommodationServicesPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=AccommodationServicesPlace'||chr(38)||'modulType=place', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferAccommodationServicesPlace', 0, '住宿服务场所', 1, (select id from permissions where ename='newAccommodationServicesPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '餐饮服务场所', 'newFoodServicesPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=FoodServicesPlace'||chr(38)||'modulType=place', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferFoodServicesPlace', 0, '餐饮服务场所', 1, (select id from permissions where ename='newFoodServicesPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '旅游场所', 'newTravelingPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=TravelingPlace'||chr(38)||'modulType=place', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferTravelingPlace', 0, '旅游场所', 1, (select id from permissions where ename='newTravelingPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '施工场所', 'newConstructionPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=ConstructionPlace'||chr(38)||'modulType=place', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferConstructionPlace', 0, '施工场所', 1, (select id from permissions where ename='newConstructionPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '其他场所', 'newOtherPlaceManagement', 1, '场所', 1, (select id from permissions where ename='newPlaceInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=OtherPlace'||chr(38)||'modulType=place', '', 9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferOtherPlace', 0, '其他场所', 1, (select id from permissions where ename='newOtherPlaceManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '单位', 'newCompanyInformation', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '党政机关', 'newPartyGovernmentOrganCompanyManagement', 1, '单位', 1, (select id from permissions where ename='newCompanyInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=PartyGovernmentOrganCompany'||chr(38)||'modulType=company', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdatePartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeletePartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferPartyGovernmentOrganCompany', 0, '党政机关', 1, (select id from permissions where ename='newPartyGovernmentOrganCompanyManagements'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '教育', 'newEducationCompanyManagement', 1, '单位', 1, (select id from permissions where ename='newCompanyInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=EducationCompany'||chr(38)||'modulType=company', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferEducationCompany', 0, '教育', 1, (select id from permissions where ename='newEducationCompanyManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '医疗卫生', 'newMedicalHygieneCompanyManagement', 1, '单位', 1, (select id from permissions where ename='newCompanyInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=MedicalHygieneCompany'||chr(38)||'modulType=company', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferMedicalHygieneCompany', 0, '医疗卫生', 1, (select id from permissions where ename='newMedicalHygieneCompanyManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '危化品存放单位', 'newDangerousStoreCompanyManagement', 1, '单位', 1, (select id from permissions where ename='newCompanyInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=DangerousStoreCompany'||chr(38)||'modulType=company', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferDangerousStoreCompany', 0, '危化品存放单位', 1, (select id from permissions where ename='newDangerousStoreCompanyManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '其他单位', 'newOtherCompanyManagement', 1, '单位', 1, (select id from permissions where ename='newCompanyInformation'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=OtherCompany'||chr(38)||'modulType=company', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferOtherCompany', 0, '其他单位', 1, (select id from permissions where ename='newOtherCompanyManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重点单位场所', 'newKeyCompanyPlace', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '安全生产重点', 'newSafetyProductionKeyManagement', 1, '重点单位场所', 1, (select id from permissions where ename='newKeyCompanyPlace'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=SafetyProductionKey', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferSafetyProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '是否安全重点', 'newWhetherProductionKey', 0, '安全生产重点', 1, (select id from permissions where ename='newSafetyProductionKeyManagement'), '', '', '', 10);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '消防安全重点', 'newFireSafetyKeyManagement', 1, '重点单位场所', 1, (select id from permissions where ename='newKeyCompanyPlace'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=FireSafetyKey', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '是否消防安全重点', 'newWhetherFireSafetyKey', 0, '消防安全重点', 1, (select id from permissions where ename='newFireSafetyKeyManagement'), '', '', '', 10);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '治安重点', 'newSecurityKeyManagement', 1, '重点单位场所', 1, (select id from permissions where ename='newKeyCompanyPlace'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=SecurityKey', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '是否治安重点', 'newWhetherSecurityKey', 0, '治安重点', 1, (select id from permissions where ename='newSecurityKeyManagement'), '', '', '', 10);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '污染源', 'newPollutionSourceManagement', 1, '重点单位场所', 1, (select id from permissions where ename='newKeyCompanyPlace'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=PollutionSource', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdatePollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeletePollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '是否污染源', 'newWhetherPollutionSource', 0, '污染源', 1, (select id from permissions where ename='newPollutionSourceManagement'), '', '', '', 10);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '规模企业', 'newEnterprisTtopManagement', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '规上企业', 'newEnterpriseManagement', 1, '基础信息', 1, (select id from permissions where ename='newEnterprisTtopManagement'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=Enterprise', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newAbolishEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAnewEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferEnterprise', 0, '规上企业', 1, (select id from permissions where ename='newEnterpriseManagement'), '', '', '', 8);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '规下企业', 'newEnterpriseDownManagement', 1, '基础信息', 1, (select id from permissions where ename='newEnterprisTtopManagement'), '', '/baseinfo/companyPlace/companyPlaceList.jsp?modul=EnterpriseDown', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newAbolishEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAnewEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '转移', 'newTransferEnterpriseDown', 0, '规下企业', 1, (select id from permissions where ename='newEnterpriseDownManagement'), '', '', '', 8);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '景区管理', 'newScenicManagement', 1, '单位场所系统', 1, (select id from permissions where ename='newCompanyPlaceManageSystem'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '旅游景点', 'newScenicSpotManagement', 1, '景区管理', 1, (select id from permissions where ename='newScenicManagement'), '', '/common/notHasComplete.jsp', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '投诉/表扬', 'newPraiseScenicSpot', 0, '旅游景点', 1, (select id from permissions where ename='newScenicSpotManagement'), '', '', '', 9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '配套设施', 'newScenicEquipmentManagement', 1, '景区管理', 1, (select id from permissions where ename='newScenicManagement'), '', '/common/notHasComplete.jsp', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedScenicEquipment', 0, '配套设施', 1, (select id from permissions where ename='newScenicEquipmentManagement'), '', '', '', 8);



insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '景区交通', 'newScenicTrafficManagement', 1, '景区交通', 1, (select id from permissions where ename='newScenicManagement'), '', '/common/notHasComplete.jsp', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'newAddScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'newUpdateScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'newViewScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'newDeleteScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'newSearchScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'newImportScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'newDownScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 6);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '取消关注', 'newCancelAttendedScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 7);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '重新关注', 'newAttendedScenicTraffic', 0, '景区交通', 1, (select id from permissions where ename='newScenicTrafficManagement'), '', '', '', 8);

commit;
