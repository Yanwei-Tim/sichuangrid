
--实有人口FXJ模块序列和表
--F人员表序列
create sequence S_FPERSONNEL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--F人员表
create table FPERSONNELS
(
   ID                   NUMBER(10)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(32)         not null,
   FERRETOUTDATE        DATE,
   BUSINESSREMARK       VARCHAR2(300),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(32)         not null,
   CREATEDATE           DATE                 not null,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PK_FPERSONNELS primary key (ID)
);

comment on table FPERSONNELS is
'F人员信息';
comment on column FPERSONNELS.ID is
'人员id';
comment on column FPERSONNELS.ORGID is
'所属网格';
comment on column FPERSONNELS.ORGINTERNALCODE is
'所属责任区编号';
comment on column FPERSONNELS.FERRETOUTDATE is
'查获日期';
comment on column FPERSONNELS.BUSINESSREMARK is
'业务备注';
comment on column FPERSONNELS.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';
comment on column FPERSONNELS.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column FPERSONNELS.ISEMPHASIS is
'是否关注';
comment on column FPERSONNELS.CREATEUSER is
'创建用户';
comment on column FPERSONNELS.CREATEDATE is
'创建日期';
comment on column FPERSONNELS.UPDATEUSER is
'修改用户';
comment on column FPERSONNELS.UPDATEDATE is
'修改时间';

--F人员表索引
create index index_fp_orgcodeIsemphasis on FPERSONNELS (ORGINTERNALCODE, ISEMPHASIS);
create index IDX_F_BASEINFOID on FPERSONNELS (BASEINFOID);
create index IDX_F_ADSID on FPERSONNELS (ADDRESSINFOID);


--Q人员表序列
create sequence S_QPERSONNEL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--Q人员表
create table QPERSONNELS
(
   ID                   NUMBER(10)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(32)         not null,
   FERRETOUTDATE        DATE,
   BUSINESSREMARK       VARCHAR2(300),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(32)         not null,
   CREATEDATE           DATE                 not null,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PK_QPERSONNELS primary key (ID)
);

comment on table QPERSONNELS is
'Q人员信息';
comment on column QPERSONNELS.ID is
'人员id';
comment on column QPERSONNELS.ORGID is
'所属网格';
comment on column QPERSONNELS.ORGINTERNALCODE is
'所属责任区编号';
comment on column QPERSONNELS.FERRETOUTDATE is
'查获日期';
comment on column QPERSONNELS.BUSINESSREMARK is
'业务备注';
comment on column QPERSONNELS.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';
comment on column QPERSONNELS.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column QPERSONNELS.ISEMPHASIS is
'是否关注';
comment on column QPERSONNELS.CREATEUSER is
'创建用户';
comment on column QPERSONNELS.CREATEDATE is
'创建日期';
comment on column QPERSONNELS.UPDATEUSER is
'修改用户';
comment on column QPERSONNELS.UPDATEDATE is
'修改时间';

--Q人员表索引
create index index_qp_orgcodeIsemphasis on QPERSONNELS (ORGINTERNALCODE, ISEMPHASIS);
create index IDX_Q_BASEINFOID on QPERSONNELS (BASEINFOID);
create index IDX_Q_ADSID on QPERSONNELS (ADDRESSINFOID);


--M人员表序列
create sequence S_MPERSONNEL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--M人员表
create table MPERSONNELS
(
   ID                   NUMBER(10)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(32)         not null,
   FERRETOUTDATE        DATE,
   BUSINESSREMARK       VARCHAR2(300),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(32)         not null,
   CREATEDATE           DATE                 not null,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PK_MPERSONNELS primary key (ID)
);

comment on table MPERSONNELS is
'M人员信息';
comment on column MPERSONNELS.ID is
'人员id';
comment on column MPERSONNELS.ORGID is
'所属网格';
comment on column MPERSONNELS.ORGINTERNALCODE is
'所属责任区编号';
comment on column MPERSONNELS.FERRETOUTDATE is
'查获日期';
comment on column MPERSONNELS.BUSINESSREMARK is
'业务备注';
comment on column MPERSONNELS.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';
comment on column MPERSONNELS.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column MPERSONNELS.ISEMPHASIS is
'是否关注';
comment on column MPERSONNELS.CREATEUSER is
'创建用户';
comment on column MPERSONNELS.CREATEDATE is
'创建日期';
comment on column MPERSONNELS.UPDATEUSER is
'修改用户';
comment on column MPERSONNELS.UPDATEDATE is
'修改时间';

--M人员表索引
create index index_mp_orgcodeIsemphasis on MPERSONNELS (ORGINTERNALCODE, ISEMPHASIS);
create index IDX_M_BASEINFOID on MPERSONNELS (BASEINFOID);
create index IDX_M_ADSID on MPERSONNELS (ADDRESSINFOID);


--实有人口模块FXJ模块整套权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'FXJ', 'FXJPeopleInfoManagement', 1, '基础信息', 1, (select id from permissions where ename='peopleManageSystem'), null, null, null, 6, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'F', 'fPersonnelManagement', 1, 'FXJ', 1, (select id from permissions where ename='FXJPeopleInfoManagement'), null, '/hotModuel/baseinfo/fPersonnel/fPersonnelList.jsp', 

'/hotModuel/baseinfo/leaderView/fxjStatistics/fPersonnelStatistics.jsp', 0, '/hotModuel/baseinfo/leaderView/fxjStatistics/fPersonnelStatistics.jsp?isGrid=true');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'Q', 'qPersonnelManagement', 1, 'FXJ', 1, (select id from permissions where ename='FXJPeopleInfoManagement'), null, '/hotModuel/baseinfo/qPersonnel/qPersonnelList.jsp', 

'/hotModuel/baseinfo/leaderView/fxjStatistics/qPersonnelStatistics.jsp', 1, '/hotModuel/baseinfo/leaderView/fxjStatistics/qPersonnelStatistics.jsp?isGrid=true');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'M', 'mPersonnelManagement', 1, 'FXJ', 1, (select id from permissions where ename='FXJPeopleInfoManagement'), null, '/hotModuel/baseinfo/mPersonnel/mPersonnelList.jsp', 

'/hotModuel/baseinfo/leaderView/fxjStatistics/mPersonnelStatistics.jsp', 2, '/hotModuel/baseinfo/leaderView/fxjStatistics/mPersonnelStatistics.jsp?isGrid=true');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 0, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 2, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 4, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 6, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '重新关注', 'attendedFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 8, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转移', 'transferFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 10, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 1, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 3, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 5, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消关注', 'cancelAttendedFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 7, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消死亡', 'cancelDeathFPersonnel', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 9, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'F网格级领导视图', 'FPERSONNELGridLeaderView', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 13, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'F镇级领导视图', 'FPERSONNELTownLeaderView', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 11, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'F社区级领导视图', 'FPERSONNELVillageLeaderView', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null, 12, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 1, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 3, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 5, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消关注', 'cancelAttendedQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 7, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消死亡', 'cancelDeathQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 9, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 0, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 2, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 4, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 6, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '重新关注', 'attendedQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 8, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转移', 'transferQPersonnel', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 10, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'Q网格级领导视图', 'QPERSONNELGridLeaderView', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 13, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'Q镇级领导视图', 'QPERSONNELTownLeaderView', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 11, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'Q社区级领导视图', 'QPERSONNELVillageLeaderView', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null, 12, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 0, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 2, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 4, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 6, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '重新关注', 'attendedMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 8, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转移', 'transferMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 10, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 1, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 3, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 5, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消关注', 'cancelAttendedMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 7, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消死亡', 'cancelDeathMPersonnel', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 9, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'M网格级领导视图', 'MPERSONNELGridLeaderView', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 13, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'M镇级领导视图', 'MPERSONNELTownLeaderView', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 11, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'M社区级领导视图', 'MPERSONNELVillageLeaderView', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null, 12, null);

commit; 


--FXJ对应人口综合查询权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'FXJ', 'fxjPeopleIntegrativeQuery', 0, 'FXJ', 1, (select id from permissions where ename='integrativeQueryPeopleManagement'), null, null, null, 5, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'F', 'fPersonnelIntegrativeQuery', 0, 'FXJ', 1, (select id from permissions where ename='fxjPeopleIntegrativeQuery'), null, null, null, 0, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'M', 'mPersonnelIntegrativeQuery', 0, 'FXJ', 1, (select id from permissions where ename='fxjPeopleIntegrativeQuery'), null, null, null, 2, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, 'Q', 'qPersonnelIntegrativeQuery', 0, 'FXJ', 1, (select id from permissions where ename='fxjPeopleIntegrativeQuery'), null, null, null, 1, null);

commit;


--用户签到序列和表
create sequence S_USERSIGN
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--用户签到表
create table USERSIGN
(
   ID               NUMBER(10)  not null,
   USERID           NUMBER(10)  not null,
   CREATEDATE       DATE        not null,
   constraint PK_USERSIGN primary key (ID)
);

comment on table USERSIGN is
'用户签到表';
comment on column USERSIGN.ID is
'签到表id';
comment on column USERSIGN.USERID is
'签到用户的id';
comment on column USERSIGN.CREATEDATE is
'签到日期';

--新增超时事件页面的权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '超时事件', 'timeOutIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 11, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchTimeOutIssue', 0, '超时事件', 1, (select id from permissions where ename='timeOutIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewTimeOutIssue', 0, '超时事件', 1, (select id from permissions where ename='timeOutIssueListManagement'), '', '', '', 1, '');

commit;

----删除历史工作台待办中，tab页的配置文件页签
--还原工作台该脚本不执行
--delete from patelConfiger where keyname='middleWorkBench' and (TABKEYNAME='workBenchPhonesms' or TABKEYNAME='workBenchSubPeopleLogAll');
--commit;