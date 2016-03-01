create sequence S_PRIMARYMEMBERS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: primarymembers                                     */
/*==============================================================*/
create table PRIMARYMEMBERS  (
   ID                   NUMBER(10)                      not null,
   ORGID                NUMBER(10)                      not null,
   ORGCODE				VARCHAR2(32)                    not null,
   NAME                 VARCHAR2(32)                    not null,
   GENDER               NUMBER(10)                      not null,
   BIRTHDAY             DATE,
   DUTYID               NUMBER(10),
   IDCARDNO				VARCHAR2(18),
   POSITION             VARCHAR2(32),
   MOBILE               VARCHAR2(20),
   TELEPHONE            VARCHAR2(20),
   CREATEUSER           VARCHAR2(32)                    not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                            not null,
   UPDATEDATE           DATE,
   ISHAVEJOB            NUMBER(1)                       default 0,
   SEQ  				NUMBER(10),
   REMARK               VARCHAR2(600),
   ORGNAME              VARCHAR2(60)                    not null,
   NATION               NUMBER(10),
   POLITICALBACKGROUND  NUMBER(10),
   SCHOOLING            NUMBER(10),
   DEPARTMENTPARTYLEVEL NUMBER(10),
   constraint PKPRIMARYMEMBERS primary key (ID)
);

comment on table PRIMARYMEMBERS is
'成员库表';

comment on column PRIMARYMEMBERS.ID is
'成员库成员ID';

comment on column PRIMARYMEMBERS.ORGID is
'网格ID';

comment on column PRIMARYMEMBERS.ORGCODE is
'区域编码';

comment on column PRIMARYMEMBERS.NAME is
'成员姓名';

comment on column PRIMARYMEMBERS.GENDER is
'性别';

comment on column PRIMARYMEMBERS.BIRTHDAY is
'出生年月';

comment on column PRIMARYMEMBERS.DUTYID is
'职务ID';

comment on column PRIMARYMEMBERS.IDCARDNO is 
'身份证号码';

comment on column PRIMARYMEMBERS.POSITION is
'职位';

comment on column PRIMARYMEMBERS.MOBILE is
'手机号';

comment on column PRIMARYMEMBERS.TELEPHONE is
'电话号';

comment on column PRIMARYMEMBERS.CREATEUSER is
'创建用户';

comment on column PRIMARYMEMBERS.UPDATEUSER is
'修改用户';

comment on column PRIMARYMEMBERS.CREATEDATE is
'创建日期';

comment on column PRIMARYMEMBERS.UPDATEDATE is
'修改日期';

comment on column PRIMARYMEMBERS.ISHAVEJOB is
'曾经任职0.在职；1.卸任';

comment on column PRIMARYMEMBERS.SEQ
  is '排序字段'; 

comment on column PRIMARYMEMBERS.REMARK is
'备注';

comment on column PRIMARYMEMBERS.ORGNAME is '所属区域';

comment on column PRIMARYMEMBERS.NATION is '民族';

comment on column PRIMARYMEMBERS.POLITICALBACKGROUND is '政治面貌';

comment on column PRIMARYMEMBERS.SCHOOLING is '文化程度';

comment on column PRIMARYMEMBERS.DEPARTMENTPARTYLEVEL is '部门党委等级';

create sequence S_PRIMARYMEMBERSORGTYPE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
/*==============================================================*/
/* Table: primarymembersOrgType                                    */
/*==============================================================*/
create table PRIMARYMEMBERSORGTYPE  (
   	ID                   NUMBER(10)                      not null,
   	PRIMARYMEMBERSID     NUMBER(10)                      not null,
   	PRIMARYORGID         NUMBER(10)                      not null,
   	ISFOURLEVELPLATFORM  NUMBER(1),
	constraint PRIMARYMEMBERSORGTYPE primary key (ID)
);
comment on table PRIMARYMEMBERSORGTYPE is
'成员库组织类型表';

comment on column PRIMARYMEMBERSORGTYPE.ID is
'成员库组织类型ID';

comment on column PRIMARYMEMBERSORGTYPE.PRIMARYMEMBERSID is
'成员库成员ID';

comment on column PRIMARYMEMBERSORGTYPE.PRIMARYORGID is
'所属组织ID';

comment on column PRIMARYMEMBERSORGTYPE.ISFOURLEVELPLATFORM is
'是否是四级平台';

--添加成员库的权限--
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'成员库','primaryMembersManagement',1,'组织机构',(select id from permissions where ename='gridManageSystem'),1,'/hotModuel/baseinfo/primaryMembersManage/primaryMemersTab.jsp','',13);
    insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'新增','addPrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',0);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'修改','updatePrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',1);
    insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'删除','deletePrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',2);
    insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'高级搜索','searchPrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',3);
	
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'检测查重','combinePrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',4);
      
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'任职成员','holdPrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',5);

--党委部门的级别字典项--
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'部门党委级别');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 0, 1, '省部级正职', 'sbjzz', 'shengbujizhengzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 1, 2, '省部级副职', 'sbjfz', 'shengbujifuzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 2, 3, '厅局级正职', 'tjjzz', 'tingjujizhengzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 3, 4, '厅局级副职', 'tjjfz', 'tingjujifuzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 4, 5, '县处级正职', 'xcjzz', 'xianchujizhengzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 5, 6, '县处级副职', 'xcjfz', 'xianchujifuzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 6, 7, '乡科级正职', 'xkjzz', 'xiangkejizhengzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 7, 8, '乡科级副职', 'xkjfz', 'xiangkejifuzhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 8, 9, '科员级', 'kyj', 'keyuanji', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委级别'), 9, 10, '办事员级', 'bsyj', 'banshiyuanji', 'admin', '', sysdate, null);

--添加组织的字段--
alter table  primaryorganizations add guidanceDepartment  varchar2(100);
comment on column primaryorganizations.guidanceDepartment  is '业务指导部门';
alter table  primaryorganizations add mainFunction  varchar2(600);
comment on column primaryorganizations.mainFunction  is '主要功能';


commit;

/*综治组织权限删除*/
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='primaryOrgManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='primaryOrgManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='primaryOrgManagement')
　　connect by prior p.id =  p.parentid
);
/*专项工作领导小组权限删除*/
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='LeadergroupOrgManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='LeadergroupOrgManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='LeadergroupOrgManagement')
　　connect by prior p.id =  p.parentid
);

--添加成员库查看权限--
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'查看','viewPrimaryMembers',0,'成员库',(select id from permissions where ename='primaryMembersManagement'),1,'','',6);

commit;
