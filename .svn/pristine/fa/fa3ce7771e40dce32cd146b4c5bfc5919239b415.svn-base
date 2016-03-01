--新建网格员配置清单权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '食药工商配置清单', 'servicListConfigurationManage', 1, '食药工商', 1, (select id from permissions where ename='serviceListVisitManagement'), null, '/hotModuel/task/gridConfigTaskList.ftl?type=ServiceList', null, 3, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '配置', 'addGridConfigServiceList', 0, '食药工商配置清单', 1, (select id from permissions where ename='servicListConfigurationManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改配置', 'updateGridConfigServiceList', 0, '食药工商配置清单', 1, (select id from permissions where ename='servicListConfigurationManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除配置', 'deleteGridConfigServiceList', 0, '食药工商配置清单', 1, (select id from permissions where ename='servicListConfigurationManage'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewGridConfigServiceList', 0, '食药工商配置清单', 1, (select id from permissions where ename='servicListConfigurationManage'), '', '', '', 3, '');

--权限修改
update permissions set NORMALURL='/hotModuel/task/gridConfigTaskList.ftl?type=Task' where ename='gridConfigTaskManage';
commit;
