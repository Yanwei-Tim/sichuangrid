update permissions set indexid=10,modulename='场所',normalurl='/baseinfo/companyPlace/companyPlaceList.jsp?modul=Logistics'||'&'||'modulType=place',
leaderurl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=Logistics'||'&'||'modulType=place',
gridurl='/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=Logistics'||'&'||'modulType=place',
parentid=(select id from permissions where ename='newPlaceInformation') where ename='newLogisticsManagement';

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'导入', 'newImportLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),13);
