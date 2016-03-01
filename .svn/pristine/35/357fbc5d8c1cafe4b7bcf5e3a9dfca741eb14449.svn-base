--联系人表新增群组联系人总数字段
alter  table contacters add(member number(5)default 0);
commit;

--四支队伍修改字段长度 names departementName
alter table fourteams modify(names varchar2(100));
alter table fourteams modify(departementname varchar2(100));
commit;

--修改权限groupManagement的normalurl和leaderurl指向
update permissions  set normalurl='/hotModuel/interaction/contact/myGroup/newMyGroupList.jsp',
leaderurl='/hotModuel/interaction/contact/myGroup/newMyGroupList.jsp'
where id=(select id from permissions where ename='groupManagement');
commit;

--修改导入日志表excelimportlog的importmodulename的长度
alter table excelimportlog modify importmodulename varchar2(50);
commit;

--新增草稿箱模块
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '草稿箱', 'draftBoxManagement', 1, '平台消息', 1, 
(select id from permissions  where ename = 'pmManagement'), null,
 '/hotModuel/interaction/platformMessage/draftbox/draftBoxList.jsp', null, 2, null);
commit;
--草稿箱模块权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'发送', 'sendDraftBox', 0, '草稿箱', 1, ' ', (select id from permissions where ename='draftBoxManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'删除', 'deleteDraftBox', 0, '草稿箱', 1, ' ', (select id from permissions where ename='draftBoxManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteAllDraftBox', 0, '草稿箱', 1, ' ', (select id from permissions where ename='draftBoxManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'高级搜索', 'searchDraftBox', 0, '草稿箱', 1, ' ', (select id from permissions where ename='draftBoxManagement'));
commit;

--发件箱新增批量删除权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteAllPmOutbox', 0, '发件箱', 1, ' ', (select id from permissions where ename='pmOutboxManagement'));
commit;

--收件箱批量删除权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteAllPmInbox', 0, '收件箱', 1, ' ', (select id from permissions where ename='pmInboxManagement'));
commit;

--发件箱表新增一个字段isDraft以区别是否是草稿箱信息
alter table outboxplatformmessages add(ISDRAFT NUMBER(1) default 0);
--收/发件箱新增两个字段
alter table outboxplatformmessages add(ORIGINATORSNAME varchar2(100));
alter table outboxplatformmessages add(ORIGINATORSDATE Date);
alter table inboxplatformmessages add(ORIGINATORSNAME varchar2(100));
alter table inboxplatformmessages add(ORIGINATORSDATE Date);
alter table outboxplatformmessages add(RECIPIENTINFO clob);
commit;

--其他联系人模块名称改为我的联系人,并且将该模块移动到短信模块中作为子模块
update permissions p set p.cname='我的联系人' ,p.parentid =(
select id from permissions where ename='contactManagement'
) where id = (select id from permissions where ename='myContactManagement');
commit;
update permissions set parentid=(select id from permissions where ename='newPartyGovernmentOrganCompanyManagement')
where  ename='newTransferPartyGovernmentOrganCompany';
commit;

--单位场所基础表添加pc/手机字段
alter table COMPANYPLACEBASE add PCORMOBILE number(10);
commit;

--单位场所表字段长度修改:
alter table COMPANYPLACE modify USERNAME			NVARCHAR2(150);
alter table COMPANYPLACE modify REMARKS        		  NVARCHAR2(900);
alter table COMPANYPLACE modify FAXNUMBER			  NVARCHAR2(150);
alter table COMPANYPLACE modify NOWIP              NVARCHAR2(180);
commit;

--修改数据管理老年人表(DM_elderlyPeople_Temp)的退休单位字段的长度
alter table DM_elderlyPeople_Temp modify RETIREUNITANDDUTY  VARCHAR2(100);
commit;

--我的群组模块新增批量删除权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteAllGroup', 0, '我的群组', 1, ' ', (select id from permissions where ename='groupManagement'));
commit;