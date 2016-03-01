--台账目录 临时台账目录权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除目录', 'deleteTempDisrectory', 0, '我的台账', 1, (select id from permissions where ename='myWorkingRecordManagement'), '', '', '', 
(select max(indexid)+1 from permissions where parentid=(select id from permissions where ename='myWorkingRecordManagement')), '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转移', 'transferWorkingRecord', 0, '我的台账', 1, (select id from permissions where ename='myWorkingRecordManagement'), '', '', '', 
(select max(indexid)+1 from permissions where parentid=(select id from permissions where ename='myWorkingRecordManagement')), '');
--模块表业务权限脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'模块业务表管理', 'moduleTableManagement', 1, '系统高级管理', 1, ' ',
 (select id from permissions where ename='advancedSystemManagement'),'/hotModuel/moduleTableManage/moduleTableList.ftl',16);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'新增', 'addModuleTable', 0, '模块业务表管理', 1, ' ',
 (select id from permissions where ename='moduleTableManagement'),'',0);
 
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'修改', 'updateModuleTable', 0, '模块业务表管理', 1, ' ',
 (select id from permissions where ename='moduleTableManagement'),'',1);
 
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'删除', 'deleteModuleTable', 0, '模块业务表管理', 1, ' ',
 (select id from permissions where ename='moduleTableManagement'),'',2);
 
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'启用/禁用', 'enableModuleTable', 0, '模块业务表管理', 1, ' ',
 (select id from permissions where ename='moduleTableManagement'),'',3);
  
 
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'迁移合并日志', 'orgChangeLogMenagement', 1, '系统高级管理', 1, ' ',
 (select id from permissions where ename='advancedSystemManagement'),'/hotModuel/orgchangeLogInfo/orgChangeLogInfo.ftl',17);

 
  insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'迁移合并备份数据', 'orgChangeBackupInfoMenagement', 1, '系统高级管理', 1, ' ',
 (select id from permissions where ename='advancedSystemManagement'),'/hotModuel/orgchangeBackupInfo/backupList.ftl',18);
 
 commit;
create table allorganizations  (
   id                   NUMBER(10)                      not null,
   parentFunOrgId       NUMBER(10),
   parentId             NUMBER(10),
   orgType              NUMBER(10)                      not null,
   orgLevel             NUMBER(10),
   subCount             NUMBER(10)                     default 0 not null,
   seq                  NUMBER(10)                     default 0 not null,
   maxCode              NUMBER(10)                     default 0 not null,
   subCountFun          NUMBER(10)                     default 0 not null,
   departmentNo         VARCHAR2(32),
   orgName              VARCHAR2(60)                    not null,
   contactWay           VARCHAR2(15),
   orgInternalCode      VARCHAR2(32)                    not null,
   simplePinyin         VARCHAR2(10)                    not null,
   fullpinyin           VARCHAR2(30)                    not null,
   remark               VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   buildingId      		VARCHAR2(30),
   centerX 				NUMBER(10),
   centerY 				NUMBER(10),
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   createDate           DATE                            not null,
   functionalOrgType              NUMBER(10),
   changeType           NUMBER(1)                       default 0 not null,
   changeDate           DATE,
   constraint pkallorganizations primary key (id)
);

comment on column allorganizations.parentFunOrgId is
'所属职能部门';

comment on column allorganizations.orgType is
'部门类型';

comment on column allorganizations.orgLevel is
'组织机构层级';

comment on column allorganizations.subCount is
'子部门数';

comment on column allorganizations.seq is
'序号';

comment on column allorganizations.maxCode is
'分配子部门最大编码';

comment on column allorganizations.subCountFun is
'子职能部门数';

comment on column allorganizations.departmentNo is
'部门编号';

comment on column allorganizations.orgName is
'部门名称';

comment on column allorganizations.contactWay is
'部门联系方式';

comment on column allorganizations.orgInternalCode is
'部门内置类型码';

comment on column allorganizations.simplePinyin is
'部门名称简拼';

comment on column allorganizations.fullpinyin is
'部门名称全拼';

comment on column allorganizations.remark is
'备注';

comment on column allorganizations.createUser is
'创建用户';

comment on column allorganizations.updateUser is
'更新用户名';

comment on column allorganizations.updateDate is
'更新用户';

comment on column allorganizations.createDate is
'创建日期';

comment on column allorganizations.changeType is
'变更类型 0：未变更 1：迁移2：删除';

comment on column allorganizations.changeDate is
'变更日期';