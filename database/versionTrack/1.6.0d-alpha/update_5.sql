create sequence s_DM_hospitals_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_hospitals_Temp         数据管理医院                                    */
/*==============================================================*/
create table DM_hospitals_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   kind                 NUMBER(10),
   type                 NUMBER(10),
   orgInternalCode      VARCHAR2(32)                    not null,
   anotherName          VARCHAR2(60),
   name		        VARCHAR2(90)                    not null,
   manager              VARCHAR2(60),  
   address              VARCHAR2(150)                   not null,
   director             VARCHAR2(60),
   affiliatedUnit       VARCHAR2(150),
   contactName          VARCHAR2(60),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   email                VARCHAR2(50),
   fax                  VARCHAR2(15),
   remark               VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   attentionExtent      NUMBER(10),
   claimState           NUMBER(10) default 0,
   claimDate            DATE,
   claimUserName        VARCHAR2(4000),
   claimUserId          NUMBER(10),
   claimOrgId           NUMBER(10),
   importDepartmentId   NUMBER(10)   not null,
   oldOrgId		NUMBER(10),
   inId			NUMBER(10),
   dispose 		NUMBER(10) default 0,
   claimavailable       NUMBER(10) default 0,
   importDate           DATE,
   districtOrgId        NUMBER(10),
   constraint pkHospitalsTemp primary key (id),
   constraint fkHospitalsTempOrg foreign key (orgId) references organizations (id)
);

comment on table DM_hospitals_Temp is
'医院信息';

comment on column DM_hospitals_Temp.id is
'医院ID';

comment on column DM_hospitals_Temp.orgId is
'所属网格';

comment on column DM_hospitals_Temp.kind is
'医院性质';

comment on column DM_hospitals_Temp.type is
'医院类型';

comment on column DM_hospitals_Temp.orgInternalCode is
'所属网格编号';

comment on column DM_hospitals_Temp.anotherName is
'医院别名';

comment on column DM_hospitals_Temp.name is
'医院名称';

comment on column DM_hospitals_Temp.manager is
'法人代表';

comment on column DM_hospitals_Temp.address is
'医院地址';

comment on column DM_hospitals_Temp.director is
'医院院长';

comment on column DM_hospitals_Temp.affiliatedUnit is
'所属单位';

comment on column DM_hospitals_Temp.contactName is
'联系人姓名';

comment on column DM_hospitals_Temp.telephone is
'联系人固定电话';

comment on column DM_hospitals_Temp.mobileNumber is
'联系人手机号码';

comment on column DM_hospitals_Temp.email is
'联系人电子邮件';

comment on column DM_hospitals_Temp.fax is
'传真';

comment on column DM_hospitals_Temp.remark is
'备注';

comment on column DM_hospitals_Temp.createUser is
'创建人';

comment on column DM_hospitals_Temp.updateUser is
'修改人';

comment on column DM_hospitals_Temp.createDate is
'创建时间';

comment on column DM_hospitals_Temp.updateDate is
'修改时间';

comment on column DM_hospitals_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_hospitals_Temp.claimDate is
'认领日期';

comment on column DM_hospitals_Temp.claimUserName is
'认领人用户名';

comment on column DM_hospitals_Temp.claimUserId is
'认领人Id';

comment on column DM_hospitals_Temp.claimOrgId is
'认领人网格';

comment on column DM_hospitals_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_hospitals_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_hospitals_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_hospitals_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_hospitals_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_hospitals_Temp.importDate
  is '导入时间';
 comment on column DM_hospitals_Temp.districtOrgId is
'导入时的到县区的组织机构';


--数据管理医院 权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '医院', 'hospitalTemp', 1, '重点场所', 1, 
       (select id from permissions where ename='importantLocationInformation'), '', 
       '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=hospitalTemp', '', 
	   (select max(indexid) from permissions where parentid = 
       (select id from permissions where ename='importantLocationInformation'))+1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '医院导入', 'hospitalTempImport', 1, '医院', 1, 
       (select id from permissions where ename = 'hospitalTemp'), '',
       '/hotModuel/dataManage/location/hospitalTempManage/hospitalTempList.ftl', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'importSearchHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempImport'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'importAdvancedSearchHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempImport'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '导入', 'importHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempImport'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'importViewHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempImport'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '批量删除', 'importDeleteHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempImport'), ' ','', '', 4);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '医院认领', 'hospitalTempclaim', 1, '医院', 1, 
       (select id from permissions where ename = 'hospitalTemp'), '',
       '/hotModuel/dataManage/location/hospitalTempManage/hospitalTempList.ftl?mode=claimList', '', 1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'claimSearchHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'claimAdvancedSearchHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'claimViewHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '修改', 'claimUpdateHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '认领', 'claimHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '分步认领', 'stepClaimHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '撤销认领', 'unDoClaimHospitalTemp', 0, '医院', 1, 
       (select id from permissions where ename = 'hospitalTempclaim'), ' ','', '', 6);

-- 医院新增数据来源
alter table hospitals add sourcesState number(1) default 1;
comment on column hospitals.sourcesState is '数据来源';

update permissions set ename = 'downHospital' where ename = 'downHopital';

--艾滋病转移权限
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'转移','transferAidspopulations',0,'艾滋病人员',(select id from permissions where ename='aidspopulationsManagement'),1,null);

--系统操作日志表增加字段
alter table systemOperateLogs add dataBeforeOrgId NUMBER(10);
comment on column systemOperateLogs.dataBeforeOrgId is '数据操作前所属网格';

alter table systemOperateLogs add operateSource VARCHAR2(60);
comment on column systemOperateLogs.operateSource is '数据操作源（在哪个模块被操作）';

alter table systemOperateLogs add dataId NUMBER(10);
comment on column systemOperateLogs.dataId is '数据id';

/*==============================================================*/
/* index: indexSOperateLogsDataKeyword    操作日志表                             */
/*==============================================================*/
create index indexSOperateLogsDataKeyword on systemOperateLogs (dataKeyword);

create sequence s_statistichistory
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

alter table BUILDDATAS rename column organization to orgid;

commit;
