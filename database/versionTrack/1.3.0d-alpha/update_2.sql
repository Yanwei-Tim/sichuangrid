--三本台账权限
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '三本台账', 'account', 1, ' ', 1, null, null, null, null, 16);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '我的台账', 'myAccount', 1, '三本台账', 1, (select id from permissions where ename = 'account'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '民生诉求台账', 'peopleAspirationListManagement', 1, '我的台账', 1, (select id from permissions where ename = 'myAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=peopleAspirationListManagement', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoPeopleAspirationListManagement', 1, '民生诉求台账', 1, (select id from permissions where ename = 'peopleAspirationListManagement'), null, '/account/peopleAspirationManage/unDoPeopleAspirationList.action', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '新增', 'addPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '修改', 'updatePeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '删除', 'deletePeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 3);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 4);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '情况记录', 'recordsPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPeopleAspirationListManagement'), null, null, null, 5);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'donePeopleAspirationListManagement', 1, '民生诉求台账', 1, (select id from permissions where ename = 'peopleAspirationListManagement'), null, '/account/peopleAspirationManage/donePeopleAspirationList.action', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDonePeopleAspiration', 0, '已办台账', 1, (select id from permissions where ename = 'donePeopleAspirationListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDonePeopleAspiration', 0, '已办台账', 1, (select id from permissions where ename = 'donePeopleAspirationListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '评价反馈', 'evaluatePeopleAspiration', 0, '已办台账', 1, (select id from permissions where ename = 'donePeopleAspirationListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '困难群众台账', 'poorPeopleManagement', 1, '我的台账', 1, (select id from permissions where ename = 'myAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=poorPeopleManagement', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoPoorPeopleListManagement', 1, '困难群众台账', 1, (select id from permissions where ename = 'poorPeopleManagement'), null, '/account/poorPeopleManage/unDoPoorPeopleList.action?module=poorPeopleManagement'||'&'||'listType=unDo', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '新增', 'addPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '修改', 'updatePoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '删除', 'deletePoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 3);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 4);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '情况记录', 'recordsPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 5);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '维护家庭成员', 'maintainMember', 0, '待办台账', 1, (select id from permissions where ename = 'unDoPoorPeopleListManagement'), null, null, null, 6);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'donePoorPeopleListManagement', 1, '困难群众台账', 1, (select id from permissions where ename = 'poorPeopleManagement'), null, '/account/poorPeopleManage/donePoorPeopleList.action?module=poorPeopleManagement'||'&'||'listType=done', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDonePoorPeople', 0, '已办台账', 1, (select id from permissions where ename = 'donePoorPeopleListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDonePoorPeople', 0, '已办台账', 1, (select id from permissions where ename = 'donePoorPeopleListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '评价反馈', 'evaluatePoorPeople', 0, '已办台账', 1, (select id from permissions where ename = 'donePoorPeopleListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '稳定工作台账', 'steadyWorkManagement', 1, '我的台账', 1, (select id from permissions where ename = 'myAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=steadyWorkManagement', null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoSteadyWorkListManagement', 1, '稳定工作台账', 1, (select id from permissions where ename = 'steadyWorkManagement'), null, '/account/steadyWorkManage/unDoSteadyWorkList.action', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '新增', 'addSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '修改', 'updateSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '删除', 'deleteSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 3);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 4);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '情况记录', 'recordsSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoSteadyWorkListManagement'), null, null, null, 5);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'doneSteadyWorkListManagement', 1, '稳定工作台账', 1, (select id from permissions where ename = 'steadyWorkManagement'), null, '/account/steadyWorkManage/doneSteadyWorkList.action', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDoneSteadyWork', 0, '已办台账', 1, (select id from permissions where ename = 'doneSteadyWorkListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDoneSteadyWork', 0, '已办台账', 1, (select id from permissions where ename = 'doneSteadyWorkListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '评价反馈', 'evaluateSteadyWork', 0, '已办台账', 1, (select id from permissions where ename = 'doneSteadyWorkListManagement'), null, null, null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '下辖台账', 'chlidAccount', 1, '三本台账', 1, (select id from permissions where ename = 'account'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '民生诉求台账', 'chlidPeopleAspirationManagement', 1, '下辖台账', 1, (select id from permissions where ename = 'chlidAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=chlidPeopleAspirationManagement', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoChlidPeopleAspirationListManagement', 1, '民生诉求台账', 1, (select id from permissions where ename = 'chlidPeopleAspirationManagement'), null, '/account/peopleAspirationManage/unDoChlidPeopleAspirationList.action', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchChlidPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChlidPeopleAspirationListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalChlidPeopleAspiration', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChlidPeopleAspirationListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'doneChlidPeopleAspirationListManagement', 1, '民生诉求台账', 1, (select id from permissions where ename = 'chlidPeopleAspirationManagement'), null, '/account/peopleAspirationManage/doneChlidPeopleAspirationList.action', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDoneChlidPeopleAspiration', 0, '已办台账', 1, (select id from permissions where ename = 'doneChlidPeopleAspirationListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDoneChlidPeopleAspiration', 0, '已办台账', 1, (select id from permissions where ename = 'doneChlidPeopleAspirationListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '困难群众台账', 'childPoorPeopleManagement', 1, '下辖台账', 1, (select id from permissions where ename = 'chlidAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=childPoorPeopleManagement', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoChildPoorPeopleListManagement', 1, '困难群众台账', 1, (select id from permissions where ename = 'childPoorPeopleManagement'), null, '/account/poorPeopleManage/unDoChildPoorPeopleList.action?module=childPoorPeopleManagement'||'&'||'listType=unDo', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchChildPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChildPoorPeopleListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalChildPoorPeople', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChildPoorPeopleListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'doneChildPoorPeopleListManagement', 1, '困难群众台账', 1, (select id from permissions where ename = 'childPoorPeopleManagement'), null, '/account/poorPeopleManage/doneChildPoorPeopleList.action?module=childPoorPeopleManagement'||'&'||'listType=done', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDoneChildPoorPeople', 0, '已办台账', 1, (select id from permissions where ename = 'doneChildPoorPeopleListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDoneChildPoorPeople', 0, '已办台账', 1, (select id from permissions where ename = 'doneChildPoorPeopleListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '稳定工作台账', 'childSteadyWorkManagement', 1, '下辖台账', 1, (select id from permissions where ename = 'chlidAccount'), null, '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=childSteadyWorkManagement', null, 2);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '待办台账', 'unDoChildSteadyWorkListManagement', 1, '稳定工作台账', 1, (select id from permissions where ename = 'childSteadyWorkManagement'), null, '/account/steadyWorkManage/unDoChildSteadyWorkList.action', null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchChildSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChildSteadyWorkListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'normalChildSteadyWork', 0, '待办台账', 1, (select id from permissions where ename = 'unDoChildSteadyWorkListManagement'), null, null, null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '已办台账', 'doneChildSteadyWorkListManagement', 1, '稳定工作台账', 1, (select id from permissions where ename = 'childSteadyWorkManagement'), null, '/account/steadyWorkManage/doneChildSteadyWorkList.action', null, 1);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查询', 'searchDoneChildSteadyWork', 0, '已办台账', 1, (select id from permissions where ename = 'doneChildSteadyWorkListManagement'), null, null, null, 0);
insert into PERMISSIONS (id, cname, ename, permissiontype, modulename, enable, parentid, description, normalurl, leaderurl, indexid)
values (s_permissions.NEXTVAL, '查看', 'viewDoneChildSteadyWork', 0, '已办台账', 1, (select id from permissions where ename = 'doneChildSteadyWorkListManagement'), null, null, null, 1);
commit;


create sequence S_ACCOUNTSTEPS_XICHANG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table  台账步骤
create table ACCOUNTSTEPS_XICHANG
(
  id                    NUMBER(10) not null,
  accountid             NUMBER(10) not null,
  orgid                 NUMBER(10) not null,
  orginternalcode       VARCHAR2(32) not null,
  targetorgid           NUMBER(10),
  targetorginternalcode VARCHAR2(32),
  startdate             DATE default sysdate,
  endate                DATE,
  lastdealdate          DATE,
  backto                NUMBER(10),
  state                 VARCHAR2(300),
  statecode             NUMBER(10),
  createuser            VARCHAR2(32) not null,
  updateuser            VARCHAR2(32),
  createdate            DATE not null,
  updatedate            DATE,
  logid                 NUMBER(10)
);
-- Add comments to the columns 
comment on column ACCOUNTSTEPS_XICHANG.id
  is '主键ID';
comment on column ACCOUNTSTEPS_XICHANG.accountid
  is '台账ID';
comment on column ACCOUNTSTEPS_XICHANG.orgid
  is '创建组织ID';
comment on column ACCOUNTSTEPS_XICHANG.orginternalcode
  is '创建组织CODE';
comment on column ACCOUNTSTEPS_XICHANG.targetorgid
  is '交办组织ID';
comment on column ACCOUNTSTEPS_XICHANG.targetorginternalcode
  is '交办组织CODE';
comment on column ACCOUNTSTEPS_XICHANG.startdate
  is '开始时间';
comment on column ACCOUNTSTEPS_XICHANG.endate
  is '结束时间';
comment on column ACCOUNTSTEPS_XICHANG.lastdealdate
  is '上一条节点结束时间';
comment on column ACCOUNTSTEPS_XICHANG.backto
  is '退回节点';
comment on column ACCOUNTSTEPS_XICHANG.state
  is '状态';
comment on column ACCOUNTSTEPS_XICHANG.statecode
  is '状态CODE';
comment on column ACCOUNTSTEPS_XICHANG.createuser
  is '创建用户';
comment on column ACCOUNTSTEPS_XICHANG.updateuser
  is '修改用户';
comment on column ACCOUNTSTEPS_XICHANG.createdate
  is '创建时间';
comment on column ACCOUNTSTEPS_XICHANG.updatedate
  is '修改时间';
comment on column ACCOUNTSTEPS_XICHANG.logid
  is '日志ID';
comment on table ACCOUNTSTEPS_XICHANG
  is '台账步骤表';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ACCOUNTSTEPS_XICHANG
  add primary key (ID);

create sequence S_ACCOUNTLOGS_XICHANG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
-- Create table  台账日志
create table ACCOUNTLOGS_XICHANG
(
  id                  NUMBER(10) not null,
  accountid           NUMBER(10) not null,
  accounttype       VARCHAR2(100),
  logdate             DATE,
  dealdate              DATE,
  dealuser            VARCHAR2(60),
  dealorgid         NUMBER(10) not null,
  dealorgcode     VARCHAR2(32),
  content             VARCHAR2(4000),
  site                VARCHAR2(120),
  opinion             VARCHAR2(1200),
  isfinish            NUMBER(1) default 0,
  finishdate          DATE,
  finishtype   NUMBER(10),
  finishcontent       VARCHAR2(2000),
  reportto            NUMBER(10),
  CREATEUSER       VARCHAR2(60) not null,
  UPDATEUSER       VARCHAR2(60),
  CREATEDATE       DATE not null,
  UPDATEDATE       DATE,
  isSysOperate		NUMBER(1) default 0
);
-- Add comments to the table 
comment on table ACCOUNTLOGS_XICHANG
  is '台账日志表';
-- Add comments to the columns 
comment on column ACCOUNTLOGS_XICHANG.id
  is '主键ID';
comment on column ACCOUNTLOGS_XICHANG.accountid
  is '台账ID';
comment on column ACCOUNTLOGS_XICHANG.logdate
  is '日志时间';
comment on column ACCOUNTLOGS_XICHANG.dealdate
  is '处理时间';
comment on column ACCOUNTLOGS_XICHANG.accounttype
  is '台账类型';
comment on column ACCOUNTLOGS_XICHANG.dealuser
  is '处理人';
comment on column ACCOUNTLOGS_XICHANG.dealorgid
  is '处理部门';
comment on column ACCOUNTLOGS_XICHANG.dealorgcode
  is '处理部门内置编码';
comment on column ACCOUNTLOGS_XICHANG.content
  is '工作内容';
comment on column ACCOUNTLOGS_XICHANG.site
  is '地点';
comment on column ACCOUNTLOGS_XICHANG.opinion
  is '当事人意见';
comment on column ACCOUNTLOGS_XICHANG.isfinish
  is '是否办结 0 否 1 是';
comment on column ACCOUNTLOGS_XICHANG.finishdate
  is '办结时间';
comment on column ACCOUNTLOGS_XICHANG.finishtype
  is '办结方式';
comment on column ACCOUNTLOGS_XICHANG.finishcontent
  is '办结结果';
comment on column ACCOUNTLOGS_XICHANG.reportto
  is '呈报单位';
comment on column ACCOUNTLOGS_XICHANG.createuser
  is '创建用户';
comment on column ACCOUNTLOGS_XICHANG.updateuser
  is '修改用户';
comment on column ACCOUNTLOGS_XICHANG.createdate
  is '创建时间';
comment on column ACCOUNTLOGS_XICHANG.updatedate
  is '修改时间';
comment on column ACCOUNTLOGS_XICHANG.isSysOperate
  is '是否系统级别的日志 0 否 1 是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ACCOUNTLOGS_XICHANG
  add primary key (ID);


/*==============================================================*/
/* Sequence: s_peopleAspirations   困难群众台账                 */
/*==============================================================*/
create sequence s_poorPeoples
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* Table: peopleAspirations   困难群众台账表                     */
/*===============================================================*/
 create table poorPeoples(
   id                   NUMBER(10) not null,
  orgid                NUMBER(10) not null,
  orginternalcode      VARCHAR2(32) not null,
  serialnumber         VARCHAR2(30) not null,
  name                 VARCHAR2(60) not null,
  idcardno             VARCHAR2(60) not null,
  mobilenumber         VARCHAR2(15),
  gender               NUMBER(10),
  birthday             DATE,
  permanentaddress     VARCHAR2(150),
  ispartymember        NUMBER(1) default 0,
  position             NUMBER(10),
  schooling            NUMBER(10),
  insurancetype        NUMBER(10),
  membernum            NUMBER(10),
  lastyearmemberincome NUMBER(8,2),
  poorbiginfo          NUMBER(10),
  poorinfo             NUMBER(10),
  helpinfo             VARCHAR2(150) not null,
  yearhelpinfo         VARCHAR2(600),
  servercontractor     VARCHAR2(60),
  servertelephone      VARCHAR2(30),
  serverjob            VARCHAR2(60),
  serverunit           VARCHAR2(150),
  createuser           VARCHAR2(60) not null,
  updateuser           VARCHAR2(60),
  createdate           DATE not null,
  updatedate           DATE,
  constraint pkpoorPeoples primary key (ID)
);
-- Add comments to the table 
comment on table POORPEOPLES
  is '困难群众台账';
-- Add comments to the columns 
comment on column POORPEOPLES.id
  is '主键ID';
comment on column POORPEOPLES.orgid
  is '组织机构ID';
comment on column POORPEOPLES.orginternalcode
  is '组织机构CODE';
comment on column POORPEOPLES.serialnumber
  is '编号';
comment on column POORPEOPLES.name
  is '姓名';
comment on column POORPEOPLES.idcardno
  is '身份证号码';
comment on column POORPEOPLES.mobilenumber
  is '联系电话';
comment on column POORPEOPLES.gender
  is '性别';
comment on column POORPEOPLES.birthday
  is '出生年月';
comment on column POORPEOPLES.permanentaddress
  is '常驻地址';
comment on column POORPEOPLES.ispartymember
  is '是否党员 0否  1是';
comment on column POORPEOPLES.position
  is '职业或身份';
comment on column POORPEOPLES.schooling
  is '学历';
comment on column POORPEOPLES.insurancetype
  is '纳入保险';
comment on column POORPEOPLES.membernum
  is '家庭人口';
comment on column POORPEOPLES.lastyearmemberincome
  is '上年度人均收入';
comment on column POORPEOPLES.poorbiginfo
  is '困难原因大类';
comment on column POORPEOPLES.poorinfo
  is '困难原因小类';
comment on column POORPEOPLES.helpinfo
  is '帮扶需求';
comment on column POORPEOPLES.yearhelpinfo
  is '年度帮扶项目';
comment on column POORPEOPLES.servercontractor
  is '服务联系人';
comment on column POORPEOPLES.servertelephone
  is '联系电话';
comment on column POORPEOPLES.serverjob
  is '职务';
comment on column POORPEOPLES.serverunit
  is '服务联系人单位';
comment on column POORPEOPLES.createuser
  is '创建用户';
comment on column POORPEOPLES.updateuser
  is '修改用户';
comment on column POORPEOPLES.createdate
  is '创建时间';
comment on column POORPEOPLES.updatedate
  is '修改时间';


-- 添加台账类型字段
alter table ACCOUNTSTEPS_XICHANG add accounttype NUMBER(10);
comment on column ACCOUNTSTEPS_XICHANG.accounttype
  is '台账类型';

alter table ACCOUNTLOGS_XICHANG add accounttype NUMBER(10);
comment on column ACCOUNTLOGS_XICHANG.accounttype
  is '台账类型';
  
/*字典项*/
  /*困难原因（大类）*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'困难原因（大类）',0,'');
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),0,1, '生活', 'sh', 'shenghuo','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),1,2, '生产', 'sc', 'shengchan','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),2,3, '医疗', 'yl', 'yiliao','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),3,4, '住房', 'zf', 'zhufang','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),4,5, '就学', 'jx', 'jiuxue','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（大类）'),5,6, '就业', 'jy', 'jiuye','admin',sysdate);
commit;

  /*困难原因（子类）*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'困难原因（子类）',0,'');
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),0,1, '因病', 'yb', 'yinbin','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),0,2, '因残', 'yc', 'yincan','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),0,3, '因灾', 'yz', 'yinzai','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),0,4, '缺乏劳动能力', 'qfldl', 'quefalaodongli','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),0,5, '生活-其他', 'qt', 'qita','admin',sysdate);
commit;
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),1,6, '缺乏资金', 'qfzj', 'quefazijin','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),1,7, '缺乏技术', 'qfjs', 'quefajishu','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),1,8, '缺乏劳动力', 'qfldl', 'quefalaodongli','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),1,9, '生产-其他', 'qt', 'qita','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),2,10, '重大疾病', 'zdjb', 'zhongdajibin','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),2,11, '医疗-其他', 'qt', 'qita','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,12, '危房改造', 'wfgz', 'weifanggaizao','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,13, '水灾', 'sz', 'shuizai','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,14, '地灾', 'dz', 'dizai','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,15, '火灾', 'hz', 'huozai','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,16, '贫困', 'pk', 'pinkun','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),3,17, '住房-其他', 'qt', 'qita','admin',sysdate);
commit;
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,18, '学前教育', 'xqjy', 'xueqianjiaoyu','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,19, '小学', 'xx', 'xiaoxue','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,20, '初中', 'cz', 'chuzhong','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,21, '高中职高', 'gzzg', 'gaozhongzhigao','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,22, '大学', 'dx', 'daxue','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),4,23, '就学-其他', 'qt', 'qita','admin',sysdate);

insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),5,24, '困难群体就业', 'knqtjy', 'kunnanquetijiuye','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),5,25, '新增就业', 'xzjy', 'xinzengjiuye','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='困难原因（子类）'),5,26, '就业-其他', 'qt', 'qita','admin',sysdate);
commit;

  /*纳入保险*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'纳入保险',0,'');
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='纳入保险'),0,1, '城镇低保', 'czdb', 'chengzhendibao','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='纳入保险'),1,2, '农村低保', 'ncdb', 'nongcundibao','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='纳入保险'),2,3, '农村五保', 'ncwb', 'nongcunwubao','admin',sysdate);
commit;

  /*职业或身份2*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'职业或身份2',0,'');
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份2'),0,1, '城镇居民', 'czjm', 'chengzhenjuming','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份2'),1,2, '农民', 'nm', 'nongming','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份2'),2,3, '企业人员', 'qyry', 'qiyerenyuan','admin',sysdate);
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='职业或身份2'),3,4, '其他', 'qt', 'qita','admin',sysdate);
commit;



-- 添加字段
alter table poorPeoples add occurOrgId NUMBER(10) not null;
comment on column poorPeoples.occurOrgId
  is '发生网格id';
alter table poorPeoples add occurOrgInternalCode VARCHAR2(32) not null;
comment on column poorPeoples.occurOrgInternalCode
  is '发生网格编号';
alter table poorPeoples add gridNo VARCHAR2(60);  
comment on column poorPeoples.gridNo
  is '网格号'; 
alter table poorPeoples add bookingUnit VARCHAR2(60);   
comment on column poorPeoples.bookingUnit
  is '登记单位'; 
alter table poorPeoples add registrant VARCHAR2(32) not null; 
comment on column poorPeoples.registrant
  is '登记人'; 
alter table poorPeoples add registrationTime DATE;   
comment on column poorPeoples.registrationTime
  is '登记时间'; 
alter table poorPeoples add CREATETABLETYPE NUMBER(10);   
comment on column poorPeoples.CREATETABLETYPE
  is '建表类型'; 
  

  
-- 修改台账类型字段类型
alter table ACCOUNTSTEPS_XICHANG modify accounttype VARCHAR2(100);
comment on column ACCOUNTSTEPS_XICHANG.accounttype
  is '台账类型';

alter table ACCOUNTLOGS_XICHANG modify accounttype VARCHAR2(100);
comment on column ACCOUNTLOGS_XICHANG.accounttype
  is '台账类型';
commit;

--困难群众台账 家庭成员表
create sequence S_poorpeopleMembers
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table POORPEOPLEMEMBERS
(
  id                   NUMBER(10) not null,
  name                 VARCHAR2(20) not null,
  gender               NUMBER(10),
  birthday             DATE,
  politicalbackground  NUMBER(10),
  schooling            NUMBER(10),
  nation               NUMBER(10),
  career               VARCHAR2(150),
  healthstate          NUMBER(10),
  relationshipwithhead NUMBER(10) not null,
  insurancetype        NUMBER(10) not null,
  createuser           VARCHAR2(32) not null,
  updateuser           VARCHAR2(32),
  createdate           DATE not null,
  updatedate           DATE,
  accountid            NUMBER(10) not null
);
comment on table POORPEOPLEMEMBERS
  is '困难群众台账 家庭成员表';
comment on column POORPEOPLEMEMBERS.id
  is '主键ID';
comment on column POORPEOPLEMEMBERS.name
  is '姓名';
comment on column POORPEOPLEMEMBERS.gender
  is '性别';
comment on column POORPEOPLEMEMBERS.birthday
  is '生日';
comment on column POORPEOPLEMEMBERS.politicalbackground
  is '政治面貌';
comment on column POORPEOPLEMEMBERS.schooling
  is '学历';
comment on column POORPEOPLEMEMBERS.nation
  is '民族';
comment on column POORPEOPLEMEMBERS.career
  is '职业';
comment on column POORPEOPLEMEMBERS.healthstate
  is '健康状况';
comment on column POORPEOPLEMEMBERS.relationshipwithhead
  is '与户主关系';
comment on column POORPEOPLEMEMBERS.insurancetype
  is '纳入低保（五保）情况';
comment on column POORPEOPLEMEMBERS.createuser
  is '创建用户';
comment on column POORPEOPLEMEMBERS.updateuser
  is '修改用户';
comment on column POORPEOPLEMEMBERS.createdate
  is '创建时间';
comment on column POORPEOPLEMEMBERS.updatedate
  is '修改时间';
comment on column POORPEOPLEMEMBERS.accountid
  is '台账ID';
alter table POORPEOPLEMEMBERS
  add primary key (ID);
