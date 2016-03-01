--新建网格员配置清单权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '网格员配置清单', 'gridConfigTaskManage', 1, '任务清单', 1, (select id from permissions where ename='taskListVisitManagement'), null, '/hotModuel/task/gridConfigTaskList.ftl', null, 7, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '配置', 'addGridConfigTask', 0, '网格员配置清单', 1, (select id from permissions where ename='gridConfigTaskManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改配置', 'updateGridConfigTask', 0, '网格员配置清单', 1, (select id from permissions where ename='gridConfigTaskManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除配置', 'deleteGridConfigTask', 0, '网格员配置清单', 1, (select id from permissions where ename='gridConfigTaskManage'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewGridConfigTask', 0, '网格员配置清单', 1, (select id from permissions where ename='gridConfigTaskManage'), '', '', '', 3, '');

commit;


--新建网格员配置清单表
create sequence s_gridConfigTask
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table gridConfigTask
(
       id  number(10),
       funOrgId number(10),
       areaOrgId number(10),
       areaOrgLevel number(10),
       areaParentId number(10),
       createUser varchar2(32),
	   createDate Date,
       updateUser varchar2(32),
	   updateDate Date,
	   orgInternalCode varchar2(32),
       constraint pk_gridConfigTask primary key (id)
);

 comment on table gridConfigTask is '任务清单职能部门配置';
 comment on column gridConfigTask.funOrgId is '职能部门';
 comment on column gridConfigTask.areaOrgId is '行政部门';
 comment on column gridConfigTask.areaOrgLevel is '行政级别';
 comment on column gridConfigTask.areaParentId is '上级部门';
 comment on column gridConfigTask.orgInternalCode is '行政部门组织code'; 
