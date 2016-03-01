/*==============================================================*/
/* sequence: fourLevelPlatform 四级平台序列                           				*/
/*==============================================================*/
create sequence S_FOURLEVELPLATFORM
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: fourLevelPlatform 四级平台表                              					*/
/*==============================================================*/
 create table fourLevelPlatform(
 	 id                   	NUMBER(10)                      not null,
   	 orgId                	NUMBER(10)                      not null,
   	 orgInternalCode      	VARCHAR2(32)                    not null,
   	 name                 	VARCHAR2(100)                   not null,
   	 teamType			  	NUMBER(10)						not null,
   	 seq  				  	NUMBER(10),
   	 remark					VARCHAR2(600),
   	 CREATEUSER           	VARCHAR2(32)                    not null,
     UPDATEUSER           	VARCHAR2(32),
     CREATEDATE           	DATE                            not null,
     UPDATEDATE          	DATE,
   	 constraint PKFOURLEVELPLATFORM primary key (ID)
 );
 
 -- Add comments to the table 
comment on table fourLevelPlatform
  is '四级平台表';
-- Add comments to the columns 
comment on column fourLevelPlatform.ID
  is '四级平台id';
comment on column fourLevelPlatform.orgId
  is '所属网格';
comment on column fourLevelPlatform.orgInternalCode
  is '所属网格编号';
comment on column fourLevelPlatform.name
  is '平台名称';
comment on column fourLevelPlatform.teamType
  is '组织类型';  
comment on column fourLevelPlatform.seq
  is '排序';
comment on column fourLevelPlatform.remark
  is '备注'; 
comment on column fourLevelPlatform.CREATEUSER
  is '创建人';
comment on column fourLevelPlatform.UPDATEUSER
  is '修改人';
comment on column fourLevelPlatform.CREATEDATE
  is '创建时间';
comment on column fourLevelPlatform.UPDATEDATE
  is '修改时间';
  --删除成员表的外键约束--
 alter table PRIMARYORGMEMBERS drop constraint FKPRIMARYORGMEMBERSORGID;
 --维护成员表中加isFourLevelPlatform字段--
 alter table  PRIMARYORGMEMBERS add isFourLevelPlatform NUMBER(1);

 
--四支队伍
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'*四支队伍','fourTeamsManagement',1,'组织机构',(select id from permissions where ename='gridManageSystem'),1,'/baseinfo/fourTeamsManage/fourTeamsList.jsp',0);
 --四级平台的权限-- 
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'四级平台','fourLevelPlatformManagement',1,'组织机构',(select id from permissions where ename='gridManageSystem'),1,'/baseinfo/fourLevelPlatformManage/fourLevelPlatformList.jsp',0);
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增组织', 'addFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'editFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'exportFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
  	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'维护成员', 'maintainPrimaryOrgMembersFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增成员', 'addPromaryOrgMemberFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改成员', 'editPrimaryOrgMemberFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'任职成员', 'holdPrimaryOrgMemberFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除成员', 'deletePrimaryOrgMemberFourLevelPlatform', 0, '四级平台', 1, ' ', (select id from permissions where ename='fourLevelPlatformManagement'));


 
--四级平台维护成员的职务的字典项-- 
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'四级平台职务');
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 1, '县长', 'xz', 'xianzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 2, '副县长', 'fxz', 'fuxianzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 3, '镇长', 'zz', 'zhenzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 4, '副镇长', 'fzz', 'fuzhenzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 5, '局长', 'jz', 'juzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 6, '副局长', 'fjz', 'fujuzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 7, '主任', 'zr', 'zhuren', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 8, '副主任', 'fzr', 'fuzhuren', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 9, '处长', 'cz', 'chuzhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 10, '科长', 'kz', 'kezhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='四级平台职务'), 0, 11, '科员', 'ky', 'keyuan', 'admin', '', sysdate, null);
 

--公安部门转移权限--
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'转移', 'transferSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'转移', 'transferBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'转移', 'transferSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));

    
alter table users add isFourthAccount number(2) default 0 ;
comment on column users.isFourthAccount
  is '是否四级平台账号'; 
  
alter table PRIMARYORGANIZATIONS add DEPARTMENTTYPE NUMBER(10);
comment on column primaryOrganizations.DEPARTMENTTYPE is 
'党委部门(子类)'; 

--党委部门字典项--
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'综治办');
  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委组织类别'), 0, 7, '综治办', 'zzb', 'zongzhiban', 'admin', '', sysdate, null);  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委组织类别'), 0, 8, '综治成员单位', 'zzcydw', 'zongzhichengyuandanwei', 'admin', '', sysdate, null);  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部门党委组织类别'), 1, 9, '专项工作领导小组', 'zxldxz', 'zhuanxianglingdaoxiaozhu', 'admin', '', sysdate, null);  

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='综治办'), 0, 1, '综治办', 'zzb', 'zongzhiban', 'admin', '', sysdate, null);  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='综治办'), 0, 2, '综治工作中心', 'zzgzzx', 'zongzhigongzuozhongxin', 'admin', '', sysdate, null);  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='综治办'), 0, 3, '综治工作站', 'zzgzz', 'zongzhigongzuozhan', 'admin', '', sysdate, null);  


update propertydicts set INTERNALID=1 where propertydomainid = (select p.id from propertydomains p where p.domainname='专项工作小组类别');
 commit;
--删除区域党建党员
delete from member_associate_partyorg mg where mg.partyorgtype=5;
commit;