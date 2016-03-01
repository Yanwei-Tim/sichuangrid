-- Create sequence
create sequence S_DM_DUSTBIN_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table DM_DUSTBIN_TEMP
(
  ID                  NUMBER(10) not null,
  ORGID               NUMBER(10) not null,
  ORGINTERNALCODE     VARCHAR2(32),
  PARTTYPE            NUMBER(10) not null,
  PARTNAME            NUMBER(10) not null,
  DUSTBINCODE         VARCHAR2(60) not null,
  ADDRESS             VARCHAR2(90),
  DEPTNAME            VARCHAR2(60) not null,
  HASVIDEO            NUMBER(1) default 0,
  OWNERSHIPUNITNAME   VARCHAR2(60),
  MAINTENANCEUNITNAME VARCHAR2(60),
  REMARK              VARCHAR2(600),
  IMGURL              VARCHAR2(300),
  ISEMPHASIS          NUMBER(1) default 0,
  LOGOUTTIME          DATE,
  LOGOUTREASON        VARCHAR2(300),
  CENTERLON           VARCHAR2(32),
  CENTERLAT           VARCHAR2(32),
  CENTERLON2          VARCHAR2(32),
  CENTERLAT2          VARCHAR2(32),
  CREATEUSER          VARCHAR2(32) not null,
  UPDATEUSER          VARCHAR2(32),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  CLAIMSTATE            NUMBER(10) default 0,
  CLAIMDATE             DATE,
  CLAIMUSERNAME         VARCHAR2(4000),
  CLAIMUSERID           NUMBER(10),
  CLAIMORGID            NUMBER(10),
  IMPORTDEPARTMENTID    NUMBER(10) not null,
  OLDORGID              NUMBER(10),
  INID                  NUMBER(10),
  DISPOSE               NUMBER(10) default 0,
  CLAIMAVAILABLE        NUMBER(10) default 0,
  IMPORTDATE            DATE,
  DISTRICTORGID         NUMBER(10)
);
-- Add comments to the table 
comment on table DM_DUSTBIN_TEMP
  is '部件信息';
-- Add comments to the columns 
comment on column DM_DUSTBIN_TEMP.ID
  is '部件信息id';
comment on column DM_DUSTBIN_TEMP.ORGID
  is '所属网格';
comment on column DM_DUSTBIN_TEMP.DUSTBINCODE
  is '部件标识码';
comment on column DM_DUSTBIN_TEMP.ADDRESS
  is '地址';
comment on column DM_DUSTBIN_TEMP.DEPTNAME
  is '主管部门名称';
comment on column DM_DUSTBIN_TEMP.HASVIDEO
  is '是否有视频流';
comment on column DM_DUSTBIN_TEMP.OWNERSHIPUNITNAME
  is '权属单位名称';
comment on column DM_DUSTBIN_TEMP.MAINTENANCEUNITNAME
  is '养护单位名称';
comment on column DM_DUSTBIN_TEMP.REMARK
  is '备注';
comment on column DM_DUSTBIN_TEMP.IMGURL
  is '图片路径';
comment on column DM_DUSTBIN_TEMP.ISEMPHASIS
  is '是否注销';
comment on column DM_DUSTBIN_TEMP.LOGOUTTIME
  is '注销时间';
comment on column DM_DUSTBIN_TEMP.LOGOUTREASON
  is '注销原因';
comment on column DM_DUSTBIN_TEMP.CENTERLON
  is '部件信息经度';
comment on column DM_DUSTBIN_TEMP.CENTERLAT
  is '部件信息纬度';
comment on column DM_DUSTBIN_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_DUSTBIN_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_DUSTBIN_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_DUSTBIN_TEMP.UPDATEDATE
  is '修改时间';
comment on column DM_DUSTBIN_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_DUSTBIN_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_DUSTBIN_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_DUSTBIN_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_DUSTBIN_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_DUSTBIN_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_DUSTBIN_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_DUSTBIN_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_DUSTBIN_TEMP.DISPOSE
  is '是否经过处理 0.未处理；1.处理过';
comment on column DM_DUSTBIN_TEMP.CLAIMAVAILABLE
  is '是否可认领 0.不可认领；1.可认领';
comment on column DM_DUSTBIN_TEMP.IMPORTDATE
  is '导入时间';
comment on column DM_DUSTBIN_TEMP.DISTRICTORGID
  is '导入时的到县区的组织机构';

-- Create/Recreate primary, unique and foreign key constraints 
alter table DM_DUSTBIN_TEMP
  add constraint PKDUSTBIN primary key (ID);
alter table DM_DUSTBIN_TEMP
  add constraint FKDUSTBINORG foreign key (ORGID)
  references ORGANIZATIONS (ID);

--部件信息 权限

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '部件管理', 'dustbinDataManagement', 1, '数据管理子系统', 1, 
       (select id from permissions where ename='dataManagePluginManagement'), '', '', '', 
       (select max(indexid) from permissions where modulename = '数据管理子系统')+1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '部件信息', 'dustbinTemp', 1, '部件管理', 1, 
       (select id from permissions where ename='dustbinDataManagement'), '', 
       '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=dustbinTemp', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '部件信息导入', 'dustbinTempImport', 1, '部件信息', 1, 
       (select id from permissions where ename = 'dustbinTemp'), '',
       '/hotModuel/dataManage/dustbin/dustbinManage/dustbinTempList.ftl', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'importSearchDustbinTemp', 0, '部件信息导入', 1, 
       (select id from permissions where ename = 'dustbinTempImport'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'importAdvancedSearchDustbinTemp', 0, '部件信息导入', 1, 
       (select id from permissions where ename = 'dustbinTempImport'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '导入', 'importDustbinTemp', 0, '部件信息导入', 1, 
       (select id from permissions where ename = 'dustbinTempImport'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'importViewDustbinTemp', 0, '部件信息导入', 1, 
       (select id from permissions where ename = 'dustbinTempImport'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '批量删除', 'importDeleteDustbinTemp', 0, '部件信息导入', 1, 
       (select id from permissions where ename = 'dustbinTempImport'), ' ','', '', 4);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '部件信息认领', 'dustbinTempclaim', 1, '部件信息', 1, 
       (select id from permissions where ename = 'dustbinTemp'), '',
       '/hotModuel/dataManage/dustbin/dustbinManage/dustbinTempList.ftl?mode=claimList', '', 1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'claimSearchDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'claimAdvancedSearchDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'claimViewDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '修改', 'claimUpdateDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '认领', 'claimDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '分步认领', 'stepClaimDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '撤销认领', 'unDoClaimDustbinTemp', 0, '部件信息认领', 1, 
       (select id from permissions where ename = 'dustbinTempclaim'), ' ','', '', 6);

update permissions set indexid = 0 where ename = 'aidspopulationsTempImport';
update permissions set indexid = 1 where ename = 'aidspopulationsTempclaim';

-- 部件管理新增数据来源
alter table dustbin add sourcesState number(1) default 1;
comment on column dustbin.sourcesState is '数据来源';

commit;

