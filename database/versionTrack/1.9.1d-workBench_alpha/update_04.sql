-- Add columns 
alter table users add updatepswtime Date;
-- Add comments to the columns 
comment on column users.updatepswtime is '密码修改时间';
--修改 原有用户密码修改时间为创建时间
update users t set updatepswtime=(select createdate from users where id=t.id);
--插入规上企业新增权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'新增', 'newAddEnterprise', 0, '规上企业', 1, ' ', (select id from permissions where ename='newEnterpriseManagement'),'',8);
--插入规下企业新增权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'新增', 'newAddEnterpriseDown', 0, '规下企业', 1, ' ', (select id from permissions where ename='newEnterpriseDownManagement'),'',8);

commit; 

--事件已办结界面新增打印按钮权限脚本
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID)values
  (s_permissions.NEXTVAL,'打印','printCompletedIssue',0,'已办结事项',1,' ',
  (select id from permissions where ename = 'completedIssueListManagement'));
  
--与户主关系新增 兄弟姐妹 选项
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,
   SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname = '与户主关系'),
   2,18,'兄弟姐妹','xdjm','xiongdijiemei','admin','', sysdate,null);
commit;  
--与户主关系新增子类 手动输入文本
alter table householdStaffs add(relationShipWithHeadText varchar2(60));
alter table dm_householdstaffs_temp add(relationShipWithHeadText varchar2(60));   

--系统日志中前后字段长度修改
alter table systemlogs modify beforeKey varchar2(150);
alter table systemlogs modify afterKey varchar2(150);
alter table systemlogs modify beforeName varchar2(150);
alter table systemlogs modify afterName varchar2(150);
alter table systemlogs modify(OPERATION varchar2(500));


--user表：添加：账号状态（待激活1，正常2，停用3），激活时间
alter table users add activationTime DATE;
alter table users add state number(10);
comment on column users.activationTime is
'账号激活时间';
comment on column users.state  is
'账号状态（待激活1，正常2，停用3）';

--历史数据处理（历史账号都改为正常状态，激活时间默认为账号新增时间）
update users  set state=2,activationtime=createdate where state is null;
commit;

--workcalendars表：添加：日历类型（默认日历0，地区特色日历1），orgId(地区特色日历对应地区的组织机构ID，默认日历时此值为空)
-- Add columns 
alter table workcalendars add (calendarType number(10) default 0 ,orgId number(10)) ;
-- Add comments to the columns 
comment on column workcalendars.calendarType is '日历类型，0代表默认，1代表地区特色';
comment on column workcalendars.orgId is '地区特色日历对应地区的组织机构ID，默认日历时此值为空';

commit;


--添加账号统计的权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '账号统计', 'userCountManagement', 1, '系统管理', 1, (select id from permissions where ename='baseSystemManagement'), null, 
'/hotModuel/userCount/userCountList.jsp', null, 9, null);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导出', 'downUserCount', 0, '账号统计', 1, (select id from permissions where ename='userCountManagement'), null, null, null, 1, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchUserCount', 0, '账号统计', 1, (select id from permissions where ename='userCountManagement'), null, null, null, 0, 
null);

commit;

--激活权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '激活', 'activationUser', 0, '用户管理', 1, (select id from permissions where ename='userManagement'), null, null, null, 12, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '激活', 'usersListActivation', 0, '用户列表', 1, (select id from permissions where ename='usersListManagement'), null, null, null, 8, 
null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '激活', 'activationMobileUser', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), null, null, null, 
14, null);
commit;


--停用权限

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '停用', 'disableUser', 0, '用户管理', 1, (select id from permissions where ename='userManagement'), null, null, null, 13, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '停用', 'usersListDisable', 0, '用户列表', 1, (select id from permissions where ename='usersListManagement'), null, null, null, 9, 
null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '停用', 'disableMobileUser', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), null, null, null, 15, 
null);
commit;

