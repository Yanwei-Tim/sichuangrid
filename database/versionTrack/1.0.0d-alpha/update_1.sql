--用户导入权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,indexid)
values (s_permissions.nextVal, '导入', 'importUser', 0, '用户管理', 1, ' ', 
                               (select id from permissions t where t.ename='userManagement'),10);
commit;