alter table  accountsteps_xichang add finishType NUMBER(10);
alter table  accountsteps_xichang add reportToTownEnd NUMBER(10);
alter table  accountsteps_xichang add reportToCityEnd  NUMBER(10);

comment on column accountSteps_xichang.finishType
  is '办结类型';
  comment on column accountSteps_xichang.reportToTownEnd
  is '呈报乡镇';
comment on column accountSteps_xichang.reportToCityEnd
  is '呈报市';

create sequence S_parameterConfig
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table parameterConfig
(
  id                    NUMBER(10) not null,
  handleScore           NUMBER(13,3),
  transferredScore      NUMBER(13,3),
  circulationScore      NUMBER(13,3),
  constraint PK_parameterConfig primary key (ID)
);

comment on table parameterConfig
  is '三本台账扣分表';
comment on column parameterConfig.handleScore
  is '办理扣分';
comment on column parameterConfig.transferredScore
  is '办结扣分';
comment on column parameterConfig.circulationScore
  is '流转扣分';

insert into parameterConfig (ID, handleScore, transferredScore, circulationScore)
values (S_parameterConfig.Nextval, 1, 2, 3);
commit;

create sequence S_parameterTimeLimit 
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table parameterTimeLimit 
(
  id                    NUMBER(10) not null,
  departmentLevel       NUMBER(10),
  useLevel              NUMBER(10),
  orgType               NUMBER(10),
  handleLimit           NUMBER(10),
  transferredLimit      NUMBER(10),
  circulationLimit      NUMBER(10),
  createUser VARCHAR2(60)  not null,
  updateUser VARCHAR2(60),
  createDate DATE  not null,
  updateDate DATE,
  constraint PK_parameterTimeLimit primary key (ID)
);

comment on table parameterTimeLimit
  is '三本台账时限标准表';
comment on column parameterTimeLimit.departmentLevel
  is '部门';
 comment on column parameterTimeLimit.useLevel
  is '应用层级';
comment on column parameterTimeLimit.orgType
  is '部门类型';
comment on column parameterTimeLimit.handleLimit
  is '办理时限';
 comment on column parameterTimeLimit.transferredLimit
  is '办结时限';
comment on column parameterTimeLimit.circulationLimit
  is '流转时限';

 insert into parameterTimeLimit (ID, departmentLevel, useLevel, orgType, handleLimit,transferredLimit ,circulationLimit,createUser,createDate)
values (S_parameterTimeLimit.Nextval, null,null,null,1, 2, 3,  'admin',sysdate);
commit;

insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'三本台账时限设置层级');
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='三本台账时限设置层级'), 0, 1, '村、社区', 'csq', 'cunshequ', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='三本台账时限设置层级'), 0, 2, '乡镇、街道', 'xzjd', 'xiangzhenjiedao', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='三本台账时限设置层级'), 0, 3, '区县市职能部门', 'qxsznbm', 'quxianshizhinengbume', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='三本台账时限设置层级'), 0, 4, '乡镇街道职能部门', 'xzjdznbm', 'xiangzhenjiedaozhine', 'admin', '', sysdate, null);
commit;


  update permissions set  normalUrl='/partyBuilding/organsParty/organsPartyManage/organsPartyList.jsp' where ename='organPartyOrganizationManagement';
   
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  VALUES(s_permissions.NEXTVAL,'机关党组织基本信息','organPartyOrganizationBaseManagement',1,'机关党组织',(select id from permissions where ename='organPartyOrganizationManagement'),1,'','',0);
  
  
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addOrgansParty',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',0);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updateOrgansParty',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',1);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteOrgansParty',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',2);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','viewOrgansParty',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',3);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'查询','searchOrgansParty',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',4);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'维护党员','manageOrgansPartyMember',0,'机关党组织基本信息',(select id from permissions where ename='organPartyOrganizationBaseManagement'),1,'','',6);

  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  VALUES(s_permissions.NEXTVAL,'党员信息','organsPartyMemberManagement',1,'机关党组织',(select id from permissions where ename='organPartyOrganizationManagement'),1,'','',1);
  
  
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',0);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updateOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',1);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',2);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','viewOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',3);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'查询','searchOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',4);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'导入','importOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',6);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'导出','downOrgansPartyMember',0,'党员信息',(select id from permissions where ename='organsPartyMemberManagement'),1,'','',7);
    
    
  
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  VALUES(s_permissions.NEXTVAL,'三本台账考核管理','parameterEvaluationManagement',1,'三本台账',(select id from permissions where ename='account'),1,'','',4);
  
   insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  VALUES(s_permissions.NEXTVAL,'扣分标准','deductionStandard',1,'三本台账考核管理',(select id from permissions where ename='parameterEvaluationManagement'),1,'/parameterConfigManage/dispatch.action','',0);
  
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'保存','saveDeductionStandard',0,'党员信息',(select id from permissions where ename='deductionStandard'),1,'','',0);
  
   insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  VALUES(s_permissions.NEXTVAL,'时限设置','timeLimit',1,'三本台账考核管理',(select id from permissions where ename='parameterEvaluationManagement'),1,'/xichang/working/parameterEvaluation/timeLimit/parametertimelimitList.jsp','',1);
  
     insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addTimeLimit',0,'时限设置',(select id from permissions where ename='timeLimit'),1,'','',0);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updateTimeLimit',0,'时限设置',(select id from permissions where ename='timeLimit'),1,'','',1);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteTimeLimit',0,'时限设置',(select id from permissions where ename='timeLimit'),1,'','',2);
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','viewTimeLimit',0,'时限设置',(select id from permissions where ename='timeLimit'),1,'','',3); 
    commit;

    
    create sequence S_organsParty
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
create table organs_Party
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32) not null,
  department          NUMBER(10) not null,
  name                VARCHAR2(90) not null,
  type                NUMBER(10) not null,
  contact             VARCHAR2(60),
  telephone           VARCHAR2(20),
  superior            NUMBER(10),
  remark              VARCHAR2(600),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint pk_organs_Party primary key (ID)
);
comment on table organs_Party
  is '机关党组织表';
comment on column organs_Party.id
  is '主键ID';
comment on column organs_Party.department
  is '所属部门';
comment on column organs_Party.name
  is '党组织名称';
comment on column organs_Party.type
  is '党组织类型';
comment on column organs_Party.contact
  is '联系人';
 comment on column organs_Party.telephone
  is '联系电话';
comment on column organs_Party.superior
  is '上级党组织';
comment on column organs_Party.remark
  is '备注';
