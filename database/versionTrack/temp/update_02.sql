
--新增账号配置权限到岗位管理 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,indexid)
values (s_permissions.nextVal, '账号配置', 'usersConfigure', 0, '岗位管理', 1, ' ', 
                               (select id from permissions t where t.ename='roleManagement'),8);
commit;
