insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'数据恢复中心','recoverDatasManagement',1,'系统管理',(select id from permissions where ename='baseSystemManagement'),1,'/hotModuel/recoverDatas/searchFrom.jsp','',8);
    
    
    
create sequence s_systemOperateLogs
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
create table systemOperateLogs  (
   id                   NUMBER(10)                      not null,
   dataOrgId            NUMBER(10),
   dataOrgCode          VARCHAR2(32),
   operatePerson        VARCHAR2(60),
   dataKeyword          VARCHAR2(300),
   moduleType           VARCHAR2(60),
   businessType			VARCHAR2(60),
   operateDate          DATE,
   operateType 		    NUMBER(10),
   dataBeforeOperate	clob,
   dataAfterOperate		clob,
   operateDescribe      clob,
   contrastState		NUMBER(1),
   constraint pksystemOperateLogs primary key (id),
   constraint fksystemOperateLogs foreign key (dataOrgId)
         references organizations (id)
);
comment on table systemOperateLogs is
'系统操作日志';
comment on column systemOperateLogs.dataOrgId is
'数据所属网格';
comment on column systemOperateLogs.dataOrgCode is
'数据所属网格编号';
comment on column systemOperateLogs.operatePerson is
'操作人';
comment on column systemOperateLogs.dataKeyword is
'数据关键字';
comment on column systemOperateLogs.moduleType is
'模块类型';
comment on column systemOperateLogs.businessType is
'业务类型';
comment on column systemOperateLogs.operateDate is
'操作时间';
comment on column systemOperateLogs.operateType is
'操作类型';
comment on column systemOperateLogs.dataBeforeOperate is
'操作前数据';
comment on column systemOperateLogs.dataAfterOperate is
'操作后数据';
comment on column systemOperateLogs.operateDescribe is
'具体操作内容';
comment on column systemOperateLogs.contrastState is
'数据操作前后对比状态';



insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'系统操作日志','systemOperateLogManagement',1,'系统高级管理',(select id from permissions where ename='advancedSystemManagement'),1,'/hotModuel/sysadmin/systemOperateLogManage/systemOperateLogList.jsp','',13);
