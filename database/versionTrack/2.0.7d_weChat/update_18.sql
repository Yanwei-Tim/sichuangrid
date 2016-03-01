insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,indexid,normalurl,leaderurl,gridurl)
values (s_permissions.nextVal, '寄递物流点', 'newLogisticsManagement', 1, '重点单位场所', 1, ' ', 
                               (select id from permissions t where t.ename='newKeyCompanyPlaceManagement'),4,'/baseinfo/companyPlace/companyPlaceList.jsp?modul=Logistics','/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=Logistics','/baseinfo/leaderView/companyPlace/gridCompanyPlaceStatistics.jsp?modul=Logistics');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'新增', 'newAddLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'修改', 'newUpdateLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'查看', 'newViewLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),2);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'删除', 'newDeleteLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),3);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'查询', 'newSearchLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),4);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'导出', 'newDownLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'取消关注', 'newCancelAttendedLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),6);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'重新关注', 'newAttendedLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),7);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'转移', 'newTransferLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),8);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'是寄递物流重点', 'newWhetherLogistics', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),9);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'寄递物流点镇级领导视图', 'NEWLOGISTICSTownLeaderView', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),10);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'寄递物流点社区级领导视图', 'NEWLOGISTICSVillageLeaderView', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),11);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
values (s_permissions.NEXTVAL,'寄递物流点网格级领导视图', 'NEWLOGISTICSGridLeaderView', 0, '寄递物流点', 1, ' ', (select id from permissions where ename='newLogisticsManagement'),12);

update permissions set parentid=(select id from permissions where ename='newCompanyInformation') where ename='newLogisticsManagement';

