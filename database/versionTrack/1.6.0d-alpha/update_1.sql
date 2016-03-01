---------社会志愿者类型增加
update propertydicts set DISPLAYSEQ=12 where PROPERTYDOMAINID=(select id from propertydomains where domainname='社会志愿者队伍类型') and DISPLAYNAME='其他';

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='社会志愿者队伍类型'), 0, 7, '养老服务队伍', 'ylfwdw', 'yanglaofuwuduiwu', 
       'admin', '', sysdate, null);


insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='社会志愿者队伍类型'), 0, 8, '助残服务队伍', 'zcfwdw', 'zhucanfuwuduiwu', 
       'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='社会志愿者队伍类型'), 0, 9, '助教服务队伍', 'zjfwdw', 'zhujiaofuwuduiwu', 
       'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='社会志愿者队伍类型'), 0, 10, '扶贫济困队伍', 'fpjkdw', 'fupinjikunduiwu', 
       'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='社会志愿者队伍类型'), 0, 11, '文体类服务队伍', 'wtlfwdw', 'wentileifuwuduiwu', 
       'admin', '', sysdate, null);

---------新增数据来源字段
alter table primaryOrganizations add  isSynchronize  NUMBER(2) default 0;
-------新增排序字段
alter table primaryOrganizations add  seq  NUMBER(10);
-------新增排序字段
alter table PrimaryOrgMembers add  seq  NUMBER(10);

comment on column primaryOrganizations.isSynchronize
  is '数据来源(0、录入，1、同步)'; 
comment on column primaryOrganizations.seq
  is '排序字段'; 
comment on column PrimaryOrgMembers.seq
  is '排序字段'; 

----社会志愿者组织 的同步群防群治 的权限添加 
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'同步群防群治','synchronizePrimaryOrgMembersToMasseduty',0,'社会志愿者组织',(select id from permissions where ename='PostulantdutyOrgManagement'),1,'','',11);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'手机账号库','mobileUserManageMent',1,'系统高级管理',(select id from permissions where ename='advancedSystemManagement'),1,'/hotModuel/sysadmin/mobileUserManage/mobileUserList.jsp','',14);
      
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'新增','addMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',0);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'修改','updateMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',1);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'删除','deleteMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',2);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'查看','viewMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',3);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'查询','searchMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',4);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'导入','importMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',5);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'导出','downMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',6);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'匹配岗位','matchupRole',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',7);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'匹配网格','matchupOrganization',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',8);
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
        VALUES(s_permissions.NEXTVAL,'账号回收','recycleMobileUser',0,'手机账号库',(select id from permissions where ename='mobileUserManageMent'),1,'','',9);

---------新增是否是否验证IMSI号
alter table USERS add  isValidatorImsi NUMBER(2) default 0;
---------新增IMSI号
alter table USERS add imsi VARCHAR2(60);
comment on column USERS.imsi is
'imsi号';
comment on column USERS.isValidatorImsi is
'是否验证imsi号';
---------时限考核成绩权限
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'三本台账时限考核成绩','statisticsAccountTimeoutManagement',1,'三本台账',(select id from permissions where ename='account'),1,'','',5);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'行政部门成绩','administrativePerformance',1,'三本台账时限考核成绩',(select id from permissions where ename='statisticsAccountTimeoutManagement'),1,'/account/statisticsAccountTimeoutManage/dispatch.action?internalId=0','',0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
      VALUES(s_permissions.NEXTVAL,'职能部门成绩','functionalPerformance',1,'三本台账时限考核成绩',(select id from permissions where ename='statisticsAccountTimeoutManagement'),1,'/account/statisticsAccountTimeoutManage/dispatch.action?internalId=1','',1);


----------时限考核成绩
-- Create sequence
create sequence S_statisticsAccountTimeouts
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

---------时限考核成绩表
create table statisticsAccountTimeouts (
 id 				number (10),
 orgid 				number(10) not null,
 orginternalcode 		varchar2(32) not null,
 parentorgid 			number(10) not null,
 orgtype 			number(10) not null,
 year 				varchar2(4),
 month 				varchar2(2),
 doing 				number(10,2) default 0.00,
 done 				number(10,2)  default 0.00,
 transfer 			number(10,2)  default 0.00,
 createuser 			varchar2(60),
 createdate 			date,
 constraint pkstatisticsAccountTimeouts primary key (ID)
);
-- Add comments to the table 
comment on table statisticsAccountTimeouts
  is '时限考核成绩表';
-- Add comments to the columns 
comment on column statisticsAccountTimeouts.orgid
  is '统计的组织机构id';  
comment on column statisticsAccountTimeouts.orginternalcode
  is '统计的组织机构orginternalcode'; 
comment on column statisticsAccountTimeouts.parentorgid
  is '统计的组织机构父id';
comment on column statisticsAccountTimeouts.orgtype
  is '组织机构类型';
comment on column statisticsAccountTimeouts.year
  is '统计的年份';
comment on column statisticsAccountTimeouts.month
  is '统计的月份';
comment on column statisticsAccountTimeouts.doing
  is '办理扣分';
comment on column statisticsAccountTimeouts.done
  is '办结扣分';  
comment on column statisticsAccountTimeouts.transfer
  is '流转扣分';  
comment on column statisticsAccountTimeouts.createuser
  is '创建人';
comment on column statisticsAccountTimeouts.createdate
  is '创建时间';

-------'党委书记', '纪委书记', '党支部书记'改为书记
update primaryorgmembers
   set dutyid = (select id
                   from propertydicts
                  where propertydomainid =
                        (select id
                           from propertydomains
                          where domainname = '基层党委组织职务')
                    and displayname = '书记')
 where dutyid in
       (select id
          from propertydicts
         where propertydomainid =
               (select id
                  from propertydomains
                 where domainname = '党组织职务')
           and displayname in ('党委书记', '纪委书记', '党支部书记'))
   and primaryorgid in
       (select id
          from primaryOrganizations
         where teamclass = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '组织大类')
                               and displayName = '基层党组织'));

-------党委副书记、党支部副书记 改为副书记
update primaryorgmembers
   set dutyid = (select id
                   from propertydicts
                  where propertydomainid =
                        (select id
                           from propertydomains
                          where domainname = '基层党委组织职务')
                    and displayname = '副书记')
 where dutyid in (select id
                    from propertydicts
                   where propertydomainid =
                         (select id
                            from propertydomains
                           where domainname = '党组织职务')
                     and displayname in ('党委副书记', '党支部副书记'))
   and primaryorgid in
       (select id
          from primaryOrganizations
         where teamclass = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '组织大类')
                               and displayName = '基层党组织'));

-------党委委员改为常委
update primaryorgmembers
   set dutyid = (select id
                   from propertydicts
                  where propertydomainid =
                        (select id
                           from propertydomains
                          where domainname = '基层党委组织职务')
                    and displayname = '常委')
 where dutyid in (select id
                    from propertydicts
                   where propertydomainid =
                         (select id
                            from propertydomains
                           where domainname = '党组织职务')
                     and displayname = '党委委员')
   and primaryorgid in
       (select id
          from primaryOrganizations
         where teamclass = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '组织大类')
                               and displayName = '基层党组织'));

-------成员改为委员
update primaryorgmembers
   set dutyid = (select id
                   from propertydicts
                  where propertydomainid =
                        (select id
                           from propertydomains
                          where domainname = '基层党委组织职务')
                    and displayname = '委员')
 where dutyid in (select id
                    from propertydicts
                   where propertydomainid =
                         (select id
                            from propertydomains
                           where domainname = '党组织职务')
                     and displayname = '成员')
   and primaryorgid in
       (select id
          from primaryOrganizations
         where teamclass = (select id
                              from propertydicts
                             where propertydomainid =
                                   (select id
                                      from propertydomains
                                     where domainname = '组织大类')
                               and displayName = '基层党组织'));
                               
--------基层党组织数据迁移至党委组织下的基层党委
update primaryOrganizations set 
       teamclass=(select id from propertydicts where propertydomainid=(select id from propertydomains where domainname='组织大类') and displayName='基层党委'  ),
       teamtype=(select id from propertydicts where propertydomainid=(select id from propertydomains where domainname='基层党委组织类别') and displayName='党支部'  )
where  
       teamclass=(select id from propertydicts where propertydomainid=(select id from propertydomains where domainname='组织大类') and displayName='基层党组织'  );
          

------删除基层党组织权限（sql顺序不能改变）

delete from roleHasPermissions where Permissionid in(select id from permissions where parentid=(select id from  permissions where ename='basePartyOrgManagement'));
delete from roleHasPermissions where Permissionid =(select id from  permissions where ename='basePartyOrgManagement');
delete from permissions where parentid=(select id from  permissions where ename='basePartyOrgManagement');
delete from  permissions where ename='basePartyOrgManagement';

-----修改社会志愿者组织为社会志愿者队伍
update permissions set cname='社会志愿者队伍'  where cname='社会志愿者组织' and parentid=(select id from permissions where cname='组织机构' );
commit;