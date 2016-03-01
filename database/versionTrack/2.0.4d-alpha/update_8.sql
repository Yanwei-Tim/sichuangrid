insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '批量删除', 'deleteDruggyTask', 0, '吸毒人员', 1, (select id from permissions where ename='druggyTaskVisitmanagement'), null, null, null, 5);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '高级搜索', 'searchDruggyTask', 0, '吸毒人员', 1, (select id from permissions where ename='druggyTaskVisitmanagement'), null, null, null, 6);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '批量删除', 'deleteMentalPatientTask', 0, '严重精神障碍患者', 1, (select id from permissions where ename='mentalPatientTaskVisitmanagement'), null, null, null, 5);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '高级搜索', 'searchMentalPatientTask', 0, '严重精神障碍患者', 1, (select id from permissions where ename='mentalPatientTaskVisitmanagement'), null, null, null, 6);