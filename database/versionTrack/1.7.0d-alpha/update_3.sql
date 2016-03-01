--添加二维码权限--
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'生成二维码', 'generatedQrcodeByUser', 0, '用户管理', 1, ' ', (select id from permissions where ename='userManagement'));


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'生成二维码', 'generatedQrcodeByHouse', 0, '实有房屋', 1, ' ', (select id from permissions where ename='actualHouseManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'生成二维码', 'generatedQrcodeByUserList', 0, '用户列表', 1, ' ', (select id from permissions where ename='usersListManagement'));


commit;