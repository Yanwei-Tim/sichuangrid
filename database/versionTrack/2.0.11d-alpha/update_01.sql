
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '手机客户端错误日志采集', 'mobileErrorLogsList', 1, '系统高级管理', 1, (select id from permissions where ename='advancedSystemManagement'), '', '/hotModuel/sysadmin/mobileErrorLogsManage/mobileErrorLogsList.jsp', '', 20, '');

--手机客户端错误日志采集表--
create sequence s_mobileErrorLogs
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table mobileErrorLogs
(
  id                NUMBER(10) not null,
  orgId  			number(10),
  orgCode 			varchar2(32),
  name              VARCHAR2(32),
  errorLogsName     VARCHAR2(32),         
  occurDate         date,
  errorLogsPath		VARCHAR2(250),
  constraint PK_mobileErrorLogs primary key (id)
);

comment on table mobileErrorLogs
  is '手机客户端错误日志采集表';
comment on column mobileErrorLogs.id
  is '表数据id';
comment on column mobileErrorLogs.orgId
  is '组织机构id';
comment on column mobileErrorLogs.orgCode
  is '组织机构code';  
comment on column mobileErrorLogs.name
  is '上传错误用户的用户名';
comment on column mobileErrorLogs.errorLogsName
  is '错误标题';
comment on column mobileErrorLogs.occurDate
  is '错误发生时间';
comment on column mobileErrorLogs.errorLogsPath
  is '错误文件所放置的路径';

  
alter table users add mobileSystemVersion varchar2(32);
alter table users add mobileType varchar2(32);
alter table users add imei varchar2(60);
alter table users add newVersionFirstLoginTime date;

comment on column users.mobileSystemVersion
  is '手机操作系统版本号';
comment on column users.mobileType
  is '手机型号';
comment on column users.imei
  is '手机串号';
comment on column users.newVersionFirstLoginTime
  is '新版本首次登录时间';
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '清空手机用户IMSI号', 'clearMobileUserImsi', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), '', '', '', 16, '');

