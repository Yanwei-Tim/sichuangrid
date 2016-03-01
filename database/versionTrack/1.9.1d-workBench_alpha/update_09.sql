--删除事件表的历史数据字段的索引
drop  index  idx_issues_historic;
--为事件表的历史数据字段建立位图索引
create bitmap index idx_issues_historic on issues(historic);

--系统日志分表之后,将11月份数据从systemlogs中抓取出来
create table systemLogs_2014_11  (
   id                   NUMBER(10)                      not null primary key,
   orgId                NUMBER(10),
   operationContent     clob,
   logLevel             NUMBER(10),
   operation            VARCHAR2(500),
   moduleName           VARCHAR2(200),
   username VARCHAR2(60),
   clientIp             VARCHAR2(32),
   orgInternalCode      VARCHAR2(32),
   operateTime          DATE                            not null,
   operationType     NUMBER(10),
   beforekey       varchar2(150),
   afterkey       varchar2(150),
   beforename       varchar2(150),
   aftername       varchar2(150)
);
create table systemLogs_2014_12  (
   id                   NUMBER(10)                      not null primary key,
   orgId                NUMBER(10),
   operationContent     clob,
   logLevel             NUMBER(10),
   operation            VARCHAR2(500),
   moduleName           VARCHAR2(200),
   username VARCHAR2(60),
   clientIp             VARCHAR2(32),
   orgInternalCode      VARCHAR2(32),
   operateTime          DATE                            not null,
   operationType     NUMBER(10),
   beforekey       varchar2(150),
   afterkey       varchar2(150),
   beforename       varchar2(150),
   aftername       varchar2(150)
);

--数据抓取脚本
INSERT INTO Systemlogs_2014_11
  (id,
   orgId,
   operation,
   logLevel,
   operationType,
   moduleName,
   username,
   clientIp,
   orgInternalCode,
   operateTime,
   operationContent,
   beforeKey,
   afterKey,
   beforeName,
   afterName)
   select s_systemLogs.NEXTVAL ,
   orgId,
   operation,
   logLevel,
   operationType,
   moduleName,
   username,
   clientIp,
   orgInternalCode,
   operateTime,
   operationContent,
   beforeKey,
   afterKey,
   beforeName,
   afterName
   from systemlogs s where  to_char(operatetime,'yyyy-MM') = '2014-11';
commit;
--数据抓取脚本 12月份
INSERT INTO Systemlogs_2014_12
  (id,
   orgId,
   operation,
   logLevel,
   operationType,
   moduleName,
   username,
   clientIp,
   orgInternalCode,
   operateTime,
   operationContent,
   beforeKey,
   afterKey,
   beforeName,
   afterName)
   select s_systemLogs.NEXTVAL ,
   orgId,
   operation,
   logLevel,
   operationType,
   moduleName,
   username,
   clientIp,
   orgInternalCode,
   operateTime,
   operationContent,
   beforeKey,
   afterKey,
   beforeName,
   afterName
   from systemlogs s where  to_char(operatetime,'yyyy-MM') = '2014-12';
   commit;
   
   
insert into permissions
  (ID,
   CNAME,
   ENAME,
   PERMISSIONTYPE,
   MODULENAME,
   ENABLE,
   PARENTID,
   DESCRIPTION,
   NORMALURL,
   LEADERURL,
   INDEXID,
   GRIDURL)
values
  (s_permissions.nextval,
   '基本情况统计',
   'baseSituationStatement',
   1,
   '研判分析',
   1,
   (select id from permissions where ename = 'statAnalyseManage'),
   '',
   '/statAnalyse/baseSituation/baseSituationStatementStatistics.jsp',
   '',
   15,
   '');
commit;

create sequence S_userActivateReports
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--添加 覆盖率权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, NORMALURL,PARENTID,indexid)
values (s_permissions.nextVal, '账号覆盖率统计', 'userActivateReport', 1, '研判分析', 1, ' ','/hotModuel/statAnalyse/userActivateReport/userActivateReportList.ftl', 
                               (select id from permissions t where t.ename='statAnalyseManage'),16);
commit;  
----创建systemLog11月份表的索引
create index idx_syslog_2014_11_loglevel on Systemlogs_2014_11 (loglevel);
create index idx_syslog_2014_11_modulename on Systemlogs_2014_11 (modulename);
create index idx_syslog_2014_11_operatetime on Systemlogs_2014_11 (operatetime);
create index idx_syslog_2014_11_orgcode on Systemlogs_2014_11 (orginternalcode);
create index idx_syslog_2014_11_username on Systemlogs_2014_11 (username);
----创建systemLog12月份表的索引
create index idx_syslog_2014_12_loglevel on Systemlogs_2014_12 (loglevel);
create index idx_syslog_2014_12_modulename on Systemlogs_2014_12 (modulename);
create index idx_syslog_2014_12_operatetime on Systemlogs_2014_12 (operatetime);
create index idx_syslog_2014_12_orgcode on Systemlogs_2014_12 (orginternalcode);
create index idx_syslog_2014_12_username on Systemlogs_2014_12 (username);

create table systemLogs_2015_1  (
   id                   NUMBER(10)                      not null primary key,
   orgId                NUMBER(10),
   operationContent     clob,
   logLevel             NUMBER(10),
   operation            VARCHAR2(500),
   moduleName           VARCHAR2(200),
   username VARCHAR2(60),
   clientIp             VARCHAR2(32),
   orgInternalCode      VARCHAR2(32),
   operateTime          DATE                            not null,
   operationType     NUMBER(10),
   beforekey       varchar2(150),
   afterkey       varchar2(150),
   beforename       varchar2(150),
   aftername       varchar2(150)
);
----创建systemLog15年1月份表的索引
create index idx_syslog_2015_1_loglevel on Systemlogs_2015_1 (loglevel);
create index idx_syslog_2015_1_modulename on Systemlogs_2015_1 (modulename);
create index idx_syslog_2015_1_operatetime on Systemlogs_2015_1 (operatetime);
create index idx_syslog_2015_1_orgcode on Systemlogs_2015_1 (orginternalcode);
create index idx_syslog_2015_1_username on Systemlogs_2015_1 (username);

