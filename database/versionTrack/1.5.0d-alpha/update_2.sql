-----区域党委成员
create sequence s_regionalPartyMembers
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;

-----区域联建情况
create sequence s_regionalBuildInfos
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
------区域联建情况附件
create sequence S_regionalBuildInfoAttachFiles
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20; 

------区域联建情况认领
create sequence S_claimRegionalBuildInfos
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20; 



--  区域党委成员表
create table regionalPartyMembers
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32),
  name				  VARCHAR2(60)  	not null,
  gender			  NUMBER(10)		not null,
  partyOrgDuty		  NUMBER(10),
  unit				  VARCHAR2(180),
  unitDuty			  VARCHAR2(180),
  telephone           VARCHAR2(20),
  mobileNumber        VARCHAR2(20),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint PKREGIONALPARTYMEMBERS primary key (id)
);

-- Add comments to the table 
comment on table regionalPartyMembers
  is '区域党委成员表';
-- Add comments to the columns 
comment on column regionalPartyMembers.ID
  is '区域党委成员表id';
comment on column regionalPartyMembers.orgId
  is '所属网格';
comment on column regionalPartyMembers.orgInternalCode
  is '所属网格编号';
comment on column regionalPartyMembers.name
  is '姓名';
comment on column regionalPartyMembers.gender
  is '性别(0男，1女，2未知)';   
comment on column regionalPartyMembers.partyOrgDuty
  is '区域党委职务';
comment on column regionalPartyMembers.unit
  is '所属单位'; 
comment on column regionalPartyMembers.unitDuty
  is '所属单位职务';
comment on column regionalPartyMembers.mobileNumber
  is '联系手机';  
comment on column regionalPartyMembers.telephone
  is '固定电话';  
comment on column regionalPartyMembers.CREATEUSER
  is '创建人';
comment on column regionalPartyMembers.UPDATEUSER
  is '修改人';
comment on column regionalPartyMembers.CREATEDATE
  is '创建时间';
comment on column regionalPartyMembers.UPDATEDATE
  is '修改时间';

------区域联建情况表  
create table regionalBuildInfos(
  ID                      NUMBER(10) not null,
  ORGID                   NUMBER(10) not null,
  ORGINTERNALCODE         VARCHAR2(50),
  SERVICEITEM			  VARCHAR2(200),
  ADVANCEMENTINFO		  CLOB,
  ISATTACHMENT    		  NUMBER(2) default 0,
  CREATEUSER              VARCHAR2(30) not null,
  UPDATEUSER              VARCHAR2(30),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint pkregionalBuildInfos primary key (ID)
);
comment on table regionalBuildInfos
  is '区域联建情况表';
comment on column regionalBuildInfos.ID
  is '区域联建情况表ID';
comment on column regionalBuildInfos.ORGID
  is '所属网格';
comment on column regionalBuildInfos.ORGINTERNALCODE
  is '部门内部编号';
comment on column regionalBuildInfos.SERVICEITEM
  is '服务项目名称';
comment on column regionalBuildInfos.ADVANCEMENTINFO
  is '推进情况';
comment on column regionalBuildInfos.ISATTACHMENT
  is '是否有附件';
comment on column regionalBuildInfos.CREATEUSER
  is '创建用户';
comment on column regionalBuildInfos.UPDATEUSER
  is '修改用户';
comment on column regionalBuildInfos.CREATEDATE
  is '创建时间';
comment on column regionalBuildInfos.UPDATEDATE
  is '修改时间';

-------区域联建情况附件表
create table regionalBuildInfoAttachFiles(
  ID                      NUMBER(10) not null,
  REGIONALBUILDINFOID	  NUMBER(10) not null,
  FILENAME			  	  VARCHAR2(300) not null,
  FILEACTUALURL 		  VARCHAR2(300) not null,
  CREATEUSER              VARCHAR2(30) not null,
  UPDATEUSER              VARCHAR2(30),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint pkregionalBuildInfoAttachFiles primary key (ID),
  constraint fkregionalBuildInfos foreign key (regionalBuildInfoId)
         references regionalBuildInfos (id)
);

-- Add comments to the table 
comment on table regionalBuildInfoAttachFiles
  is '区域联建情况附件表';
-- Add comments to the columns 
comment on column regionalBuildInfoAttachFiles.ID
  is '区域联建情况附件ID';
comment on column regionalBuildInfoAttachFiles.regionalBuildInfoId
  is '区域联建情况ID';
comment on column regionalBuildInfoAttachFiles.FILENAME
  is '区域联建情况附件名';
comment on column regionalBuildInfoAttachFiles.FILEACTUALURL
  is '区域联建情况附件路径';
comment on column regionalBuildInfoAttachFiles.CREATEUSER
  is '创建用户';
comment on column regionalBuildInfoAttachFiles.UPDATEUSER
  is '修改用户';
comment on column regionalBuildInfoAttachFiles.CREATEDATE
  is '创建时间';
comment on column regionalBuildInfoAttachFiles.UPDATEDATE
  is '修改时间';
  
  
---------区域联建情况认领表
create table claimRegionalBuildInfos(
  ID                      NUMBER(10) not null,
  regionalBuildInfoId	  NUMBER(10) not null,
  claimDepartment 		  VARCHAR2(200) not null,
  claimDate			  	  DATE not null,
  CREATEUSER              VARCHAR2(30) not null,
  UPDATEUSER              VARCHAR2(30),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint pkclaimRegionalBuildInfos primary key (ID),
  constraint fkclaimRegionalBuildInfos foreign key (regionalBuildInfoId)
         references regionalBuildInfos (id)
);

-- Add comments to the table 
comment on table claimRegionalBuildInfos
  is '区域联建情况认领表';
-- Add comments to the columns 
comment on column claimRegionalBuildInfos.ID
  is '区域联建情况认领ID';
comment on column claimRegionalBuildInfos.regionalBuildInfoId
  is '区域联建情况ID';
comment on column claimRegionalBuildInfos.claimDepartment
  is '认领单位';
comment on column claimRegionalBuildInfos.claimDate
  is '认领时间';
comment on column claimRegionalBuildInfos.CREATEUSER
  is '创建用户';
comment on column claimRegionalBuildInfos.UPDATEUSER
  is '修改用户';
comment on column claimRegionalBuildInfos.CREATEDATE
  is '创建时间';
comment on column claimRegionalBuildInfos.UPDATEDATE
  is '修改时间';
-----区域党建区域联建情况权限修改为工作动态权限
update permissions set cname='工作动态' where cname='区域联建情况' ; 
update permissions set modulename='工作动态' where modulename='区域联建情况';

----新增区域党委职务数据字典
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '区域党委职务', 1, '[{"displayName":"书记","identify":0},{"displayName":"副书记","identify":1}
                                                      ,{"displayName":"委员","identify":2}]');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='区域党委职务'), 0, 1, '书记', 'sj', 'shuji', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='区域党委职务'), 1, 2, '副书记', 'fsj', 'fushuji', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='区域党委职务'), 2, 3, '委员', 'wy', 'weiyuan', 
       'admin', '', sysdate, null);

--------区域联建权限
  insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'区域联建情况','regionalBuildInfoManagement',1,'区域党建',(select id from permissions where ename='regionalPartyManagement'),1,'','',1);
      
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'新增','addRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',0);
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'修改','updateRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',1);
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'删除','deleteRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',2);
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'查看','viewRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',3);
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'查询','searchRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',4);
      insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'认领','claimRegionalBuildInfo',0,'区域联建情况',(select id from permissions where ename='regionalBuildInfoManagement'),1,'','',6);
    
--增加户籍信息表里地址的长度
alter table CENSUSREGISTERFAMILYS modify(currentaddress varchar2(150));

alter table  BASIC_CASE     add baseInfoType VARCHAR2(32);

alter table  CaseImageUpload     add baseInfoType VARCHAR2(32);

alter table partywork_members add baseInfoType    VARCHAR2(32);
commit;
        