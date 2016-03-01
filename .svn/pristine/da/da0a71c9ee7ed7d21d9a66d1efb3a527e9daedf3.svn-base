insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '重点上访人员基本信息', 'superiorVisitManag', 1, '重点上访人员', 1, 
       (select id from permissions where ename = 'superiorVisitManagement'), ' ','', '', 1);

update permissions set PARENTID=(select id from permissions where ename = 'superiorVisitManag')
where PARENTID =(select id from permissions where ename = 'superiorVisitManagement') and ename != 'superiorVisitManag';

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查询上访历史', 'searchSuperiorVisitHistoryManagement', 1, '重点上访人员', 1, 
       (select id from permissions where ename = 'superiorVisitManagement'), ' ','', '', 2);
       
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查询上访历史', 'searchSuperiorVisitHistory', 0, '查询上访历史', 1, 
       (select id from permissions where ename = 'searchSuperiorVisitHistoryManagement'), ' ','', '',1);
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '新增上访历史', 'addSuperiorVisitHistory', 0, '查询上访历史', 1, 
       (select id from permissions where ename = 'searchSuperiorVisitHistoryManagement'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '修改上访历史', 'updateSuperiorVisitHistory', 0, '查询上访历史', 1, 
       (select id from permissions where ename = 'searchSuperiorVisitHistoryManagement'), ' ','', '', 3);
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '删除上访历史', 'deleteSuperiorVisitHistory', 0, '查询上访历史', 1, 
       (select id from permissions where ename = 'searchSuperiorVisitHistoryManagement'), ' ','', '', 4);
       

commit;
