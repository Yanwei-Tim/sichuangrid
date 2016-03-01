--见义勇为 权限脚本
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '见义勇为', 'goodSamaritanManagement', 1, '见义勇为', 1, (select id from permissions where ename='peopleManageSystem'), '', '/hotModuel/baseinfo/goodSamaritan/goodSamaritanList.jsp', '/hotModuel/baseinfo/leaderView/goodSamaritan/goodSamaritanStatistics.jsp', 7, '/hotModuel/baseinfo/leaderView/goodSamaritan/goodSamaritanStatistics.jsp?isGrid=true');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 0, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 1, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 2, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 3, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 4, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 5, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 6, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消关注', 'cancelAttendedGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 7, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '重新关注', 'attendedGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 8, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '取消死亡', 'cancelDeathGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 9, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转移', 'transferGoodSamaritan', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 10, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '见义勇为镇级领导视图', 'GOODSAMARITANTownLeaderView', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 11, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '见义勇为社区级领导视图', 'GOODSAMARITANVillageLeaderView', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 12, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '见义勇为网格级领导视图', 'GOODSAMARITANGridLeaderView', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), '', '', '', 13, '');

--升级内容权限脚本
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '升级内容', 'upgradeContentManagement', 1, '系统管理', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'baseSystemManagement'), '', '/hotModuel/sysadmin/upgradeContent/upgradeContentList.jsp', '', 10, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 0, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 1, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 2, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 3, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 4, '');

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '是否展示', 'isUpgradUpgradeContent', 0, '升级内容', 1, (SELECT P.ID FROM PERMISSIONS P WHERE P.ENAME = 'upgradeContentManagement'), '', '', '', 5, '');

COMMIT;

--基本信息统计报表序列
create sequence S_UPGRADECONTENTINFO
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--升级内容信息表
CREATE TABLE UPGRADECONTENTINFO(
   ID                   NUMBER(10)                      not null,
   UPGRADEDATE          DATE       ,
   UPGRADECONTENT       CLOB       ,
   ISUPGRAD       		NUMBER(2) 						default 0,
   createUser           VARCHAR2(60)                    not null,
   updateUser           VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint PkUPGRADECONTENTINFO primary key (id)
);
comment on table UPGRADECONTENTINFO is
'升级内容信息表';
comment on column UPGRADECONTENTINFO.UPGRADEDATE is
'升级内容信息表';
comment on column UPGRADECONTENTINFO.UPGRADECONTENT is
'升级内容信息表';
comment on column UPGRADECONTENTINFO.ISUPGRAD is 
'是否显示升级内容 【默认为0 不显示 1为显示】';
comment on column UPGRADECONTENTINFO.createUser is
'创建用户';
comment on column UPGRADECONTENTINFO.updateUser is
'修改用户';
comment on column UPGRADECONTENTINFO.createDate is
'创建时间';
comment on column UPGRADECONTENTINFO.updateDate is
'修改时间';

--升级内容关联表统计报表序列
create sequence S_USERCHECKUPGRADECONTENTINFO
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--用户查看升级内容关联表
CREATE TABLE USERCHECKUPGRADECONTENTINFO(
   ID                   NUMBER(10)                      not null,
   USERID               NUMBER(10),
   UPGRADECONTENTID     NUMBER(10)      ,
   CHECKDATE            DATE,
   CHECKSTATE            NUMBER(2)                       default 0,
   createUser           VARCHAR2(60)                    not null,
   updateUser           VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
    constraint PkUSERCHECKUPGRADECONTENTINFO primary key (id)
);
comment on table USERCHECKUPGRADECONTENTINFO is
'用户查看升级内容关联表';
comment on column USERCHECKUPGRADECONTENTINFO.UPGRADECONTENTID is
'升级内容ID';
comment on column USERCHECKUPGRADECONTENTINFO.CHECKDATE is
'查看日期';
comment on column USERCHECKUPGRADECONTENTINFO.CHECKSTATE is
'查看状态';
comment on column USERCHECKUPGRADECONTENTINFO.createUser is
'创建用户';
comment on column USERCHECKUPGRADECONTENTINFO.updateUser is
'修改用户';
comment on column USERCHECKUPGRADECONTENTINFO.createDate is
'创建时间';
comment on column USERCHECKUPGRADECONTENTINFO.updateDate is
'修改时间';

--创建索引
create index IDX_USERCUPGRADECONTIN_USERID on USERCHECKUPGRADECONTENTINFO (USERID);
create index IDX_USERCUPGRADECONTIN_UCONTID on USERCHECKUPGRADECONTENTINFO (UPGRADECONTENTID);
