--系统党建表结构
create sequence s_system_party
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table system_party
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32) not null,
  partyName           VARCHAR2(90) not null,
  partyOrgType        NUMBER(10) not null,
  contact             VARCHAR2(60),
  telephone           VARCHAR2(20),
  mobile              VARCHAR2(20),
  remark              VARCHAR2(600),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint pk_system_party primary key (ID)
);
comment on table system_party
  is '系统党建表';
comment on column system_party.id
  is '主键ID';
comment on column system_party.partyName
  is '党组织名称';
comment on column system_party.partyOrgType
  is '党组织类型';
comment on column system_party.contact
  is '联系人';
 comment on column system_party.telephone
  is '联系电话';
comment on column system_party.mobile
  is '联系手机';
comment on column system_party.remark
  is '备注';
  

/*==============================================================*/
/* sequence: VOLUNTEER_TEAM 党员志愿者队伍序列                              				*/
/*==============================================================*/   
create sequence s_VOLUNTEER_TEAM
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: VOLUNTEER_TEAM 党员志愿者队伍表                              					*/
/*==============================================================*/
create table VOLUNTEER_TEAM(
	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	name               	    VARCHAR2(90)    not null,
	serviceDirection        VARCHAR2(300),
	remark                  VARCHAR2(300),
	belongOrg               NUMBER(10),
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
   	constraint PK_VOLUNTEER_TEAM primary key (ID)
);
-- Add comments to the table 
comment on table VOLUNTEER_TEAM
  is '党员志愿者队伍表';
-- Add comments to the columns 
comment on column VOLUNTEER_TEAM.ID
  is 'id';
comment on column VOLUNTEER_TEAM.orgId
  is '所属网格';
comment on column VOLUNTEER_TEAM.orgInternalCode
  is '所属网格编号';
comment on column VOLUNTEER_TEAM.name
  is '组织名称';
comment on column VOLUNTEER_TEAM.serviceDirection
  is '服务方向';
comment on column VOLUNTEER_TEAM.remark
  is '备注';
comment on column VOLUNTEER_TEAM.belongOrg
  is '所属部门';
comment on column VOLUNTEER_TEAM.CREATEUSER
  is '创建人';
comment on column VOLUNTEER_TEAM.UPDATEUSER
  is '修改人';
comment on column VOLUNTEER_TEAM.CREATEDATE
  is '创建时间';
comment on column VOLUNTEER_TEAM.UPDATEDATE
  is '修改时间';  
  
/*==============================================================*/
/* sequence: VOLUNTEER_MEMBER 党员志愿者队伍成员序列                              				*/
/*==============================================================*/   
create sequence s_VOLUNTEER_MEMBER
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: VOLUNTEER_MEMBER 党员志愿者队伍成员表                              					*/
/*==============================================================*/
create table VOLUNTEER_MEMBER(
	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	teamId               	NUMBER(10)      not null,
	memberId                NUMBER(10)      not null,
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
   	constraint PK_VOLUNTEER_MEMBER primary key (ID)
);
-- Add comments to the table 
comment on table VOLUNTEER_MEMBER
  is '党员志愿者队伍成员表';
-- Add comments to the columns 
comment on column VOLUNTEER_MEMBER.ID
  is 'id';
comment on column VOLUNTEER_MEMBER.orgId
  is '所属网格';
comment on column VOLUNTEER_MEMBER.orgInternalCode
  is '所属网格编号';
comment on column VOLUNTEER_MEMBER.teamId
  is '队伍id';
comment on column VOLUNTEER_MEMBER.memberId
  is '成员id';
comment on column VOLUNTEER_MEMBER.CREATEUSER
  is '创建人';
comment on column VOLUNTEER_MEMBER.UPDATEUSER
  is '修改人';
comment on column VOLUNTEER_MEMBER.CREATEDATE
  is '创建时间';
comment on column VOLUNTEER_MEMBER.UPDATEDATE
  is '修改时间'; 
  
  
  
update permissions ps set ps.normalurl='/partyBuilding/systems/systemPartyTab.jsp' where ps.ename='systemPartyManagement';
commit;

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '事业单位党组织', 'businessOrgManagement', 1, '系统党建', 1, (select id from permissions where ename='systemPartyManagement'), '', '', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '基本信息', 'baseBusinessOrg', 1, '事业单位党组织', 1, (select id from permissions where ename='businessOrgManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addBusinessOrg', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateBusinessOrg', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteBusinessOrg', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchBusinessOrg', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewBusinessOrg', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '维护成员', 'manageBusinessOrgMember', 0, '基本信息', 1, (select id from permissions where ename='baseBusinessOrg'), '', '', '', 5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '党员信息', 'businessOrgMember', 1, '事业单位党组织', 1, (select id from permissions where ename='businessOrgManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'importBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'downBusinessOrgMember', 0, '党员信息', 1, (select id from permissions where ename='businessOrgMember'), '', '', '', 6);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '国有(集体党组织)', 'collectiveOrgManagement', 1, '系统党建', 1, (select id from permissions where ename='systemPartyManagement'), '', '', '', 1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '基本信息', 'baseCollectiveOrg', 1, '国有(集体党组织)', 1, (select id from permissions where ename='collectiveOrgManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addCollectiveOrg', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateCollectiveOrg', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteCollectiveOrg', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchCollectiveOrg', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewCollectiveOrg', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '维护成员', 'manageCollectiveOrgMember', 0, '基本信息', 1, (select id from permissions where ename='baseCollectiveOrg'), '', '', '', 5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '党员信息', 'collectiveOrgMember', 1, '国有(集体党组织)', 1, (select id from permissions where ename='collectiveOrgManagement'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'importCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'downCollectiveOrgMember', 0, '党员信息', 1, (select id from permissions where ename='collectiveOrgMember'), '', '', '', 6);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '两新组织党组织', 'twoNewPartyForSys', 1, '系统党建', 1, (select id from permissions where ename='systemPartyManagement'), '', '', '', 2);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '基本信息', 'baseTwoNewPartyForSys', 1, '两新组织党组织', 1, (select id from permissions where ename='twoNewPartyForSys'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addTwoNewPartyForSys', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateTwoNewPartyForSys', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteTwoNewPartyForSys', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchTwoNewPartyForSys', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewTwoNewPartyForSys', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '维护成员', 'manageTwoNewPartyForSysMember', 0, '基本信息', 1, (select id from permissions where ename='baseTwoNewPartyForSys'), '', '', '', 5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '党员信息', 'twoNewPartyForSysMember', 1, '两新组织党组织', 1, (select id from permissions where ename='twoNewPartyForSys'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导入', 'importTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '导出', 'downTwoNewPartyForSysMember', 0, '党员信息', 1, (select id from permissions where ename='twoNewPartyForSysMember'), '', '', '', 6);
commit;

update member_associate_partyorg ma   set ORGINTERNALCODE = (select org.orginternalcode
  from  organizations org where org.id=ma.orgid)  where ma.orginternalcode is null;
commit;