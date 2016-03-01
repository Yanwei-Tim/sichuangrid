Create index idx_peopleLog_userId on peopleLog(userId);

--用户签到表历史表
create table USERSIGN_HISTORY
(
   ID               NUMBER(10)  not null,
   orgId             NUMBER(10),
   USERID           NUMBER(10)  not null,
   orgLevelId       NUMBER(10),
   orgTypeId        NUMBER(10),
   parentOrgId      NUMBER(10),
   orgInternalCode VARCHAR2(32),
   CREATEDATE       DATE        not null,
   constraint PK_USERSIGN_HISTORY primary key (ID)
);

comment on table USERSIGN_HISTORY is
'用户签到表历史表';
comment on column USERSIGN_HISTORY.ID is
'签到表id';
comment on column USERSIGN_HISTORY.orgId is
'签到用户的组织机构id';
comment on column USERSIGN_HISTORY.USERID is
'签到用户的id';
comment on column USERSIGN_HISTORY.orgLevelId is
'签到用户的组织机构层级';
comment on column USERSIGN_HISTORY.orgTypeId is
'签到用户的组织机构类型';
comment on column USERSIGN_HISTORY.parentOrgId is
'签到用户的组织机构类型父id';
comment on column USERSIGN_HISTORY.CREATEDATE is
'签到日期';

--签到表 加索引
create index IDX_USERSIGN_H_USERID on USERSIGN_HISTORY (USERID);
create index IDX_USERSIGN_H_CREATEDATE on USERSIGN_HISTORY (CREATEDATE);
create index IDX_USERSIGN_H_orgid on USERSIGN_HISTORY (orgid);
create index IDX_USERSIGN_H_orgLevelId on USERSIGN_HISTORY (orgLevelId);
create index IDX_USERSIGN_H_orgTypeId on USERSIGN_HISTORY (orgTypeId);
create index idx_USERSIGN_H_parentOrgId on USERSIGN_HISTORY(parentOrgId);
create index IDX_USERSIGN_H_ORGCODE on USERSIGN_HISTORY(ORGINTERNALCODE);


--信息系统使用情况周表序列
create sequence S_usedinfoweek
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--信息系统使用情况周表
create table usedinfoweek
(
   ID               	NUMBER(10)  not null,
   orgId            	NUMBER(10),
   parentOrgId      	NUMBER(10),
   orgInternalCode  	VARCHAR2(32),
   activeSum	    	NUMBER(10),
   accountSum	    	NUMBER(10),
   specialCrowdCount 	NUMBER(10),
   CREATEDATE       	DATE        not null,
   createuser			VARCHAR2(60),
   constraint PK_usedinfoweek primary key (ID)
);

comment on table usedinfoweek is
'信息系统使用情况周表';
comment on column usedinfoweek.ID is
'表id';
comment on column usedinfoweek.orgId is
'组织机构id';
comment on column usedinfoweek.parentOrgId is
'父组织机构id';
comment on column usedinfoweek.orgInternalCode is
'组织机构编码';
comment on column usedinfoweek.activeSum is
'当前组织机构登陆数';
comment on column usedinfoweek.accountSum is
'当前组织机构截止当前的激活账号数';
comment on column usedinfoweek.specialCrowdCount is
'当前组织机构的走访服务量';

--信息系统使用情况月表序列
create sequence S_usedinfomonth
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--信息系统使用情况月表
create table usedinfomonth
(
   ID               	NUMBER(10)  not null,
   orgId            	NUMBER(10),
   parentOrgId      	NUMBER(10),
   orgInternalCode  	VARCHAR2(32),
   activeSum	    	NUMBER(10),
   accountSum	    	NUMBER(10),
   specialCrowdCount 	NUMBER(10),
   CREATEDATE       	DATE        not null,
   createuser			VARCHAR2(60),
   constraint PK_usedinfomonth primary key (ID)
);

comment on table usedinfomonth is
'信息系统使用情况周表';
comment on column usedinfomonth.ID is
'表id';
comment on column usedinfomonth.orgId is
'组织机构id';
comment on column usedinfomonth.parentOrgId is
'父组织机构id';
comment on column usedinfomonth.orgInternalCode is
'组织机构编码';
comment on column usedinfomonth.activeSum is
'当前组织机构登陆数';
comment on column usedinfomonth.accountSum is
'当前组织机构截止当前的激活账号数';
comment on column usedinfomonth.specialCrowdCount is
'当前组织机构的走访服务量';
---加索引
create index IDX_usedinfoweek_orgId on usedinfoweek (orgId);
create index IDX_usedinfoweek_parentOrgId on usedinfoweek (parentOrgId);
create index IDX_usedinfomonth_orgId on usedinfomonth (orgId);
create index IDX_usedinfomonth_parentOrgId on usedinfomonth (parentOrgId);

-----网格化服务管理信息系统使用情况每天统计(优化后的)job
insert into taskploy(id,cname,ename,type,description,code) values(s_TASKPLOY.nextval,'网格化服务管理信息系统使用情况每天统计(优化后的)job','usedInfoDataEverDayCountOptmizeDispatch',(select id from propertydicts where displayname='java方法'),'网格化服务管理信息系统使用情况每天统计(优化后的)job','usedInfoDataEverDayCountOptmizeDispatch.createUsedInfoEverDayCountCache');
insert into task(id,name,taskgroup,description,ployId,config,closed) values(s_TASK.nextval,'usedInfoDataEverDayCountOptmizeDispatch','usedInfoDataEverDayCountOptmizeDispatch','usedInfoDataEverDayCountOptmizeDispatch',(select id from taskploy where ename='usedInfoDataEverDayCountOptmizeDispatch'),'11 3 4 * * ?',1);
-----信息系统使用情况月数据生成
insert into taskploy(id,cname,ename,type,description,code) values(s_TASKPLOY.nextval,'信息系统使用情况月数据生成','usedInfoMonthDataDispatch',(select id from propertydicts where displayname='java方法'),'信息系统使用情况月数据生成','usedInfoMonthDataDispatch.createUsedInfoMonthData');
insert into task(id,name,taskgroup,description,ployId,config,closed) values(s_TASK.nextval,'usedInfoMonthDataDispatch','usedInfoMonthDataDispatch','usedInfoMonthDataDispatch',(select id from taskploy where ename='usedInfoMonthDataDispatch'),'11 3 1 * 1 ?',1);
-----信息系统使用情况周数据生成
insert into taskploy(id,cname,ename,type,description,code) values(s_TASKPLOY.nextval,'信息系统使用情况周数据生成','usedInfoWeekDataDispatch',(select id from propertydicts where displayname='java方法'),'信息系统使用情况周数据生成','usedInfoWeekDataDispatch.createUsedInfoWeekData');
insert into task(id,name,taskgroup,description,ployId,config,closed) values(s_TASK.nextval,'usedInfoWeekDataDispatch','usedInfoWeekDataDispatch','usedInfoWeekDataDispatch',(select id from taskploy where ename='usedInfoWeekDataDispatch'),'35 1 1 ? * 2',1);
update task set closed=0 where name='usedInfoEverDayCountDispatch';
commit;
