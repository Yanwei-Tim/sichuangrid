create sequence s_statistichistory
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create sequence s_orgLoginStanals
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 9999999999;

create sequence S_userActivateReports
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


create table orgLoginStanals  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   userName             VARCHAR2(32)                    not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   workday_month		NUMBER(10)                      default 0 not null,
   loggedday_month	    NUMBER(10)                      default 0 not null,
   workday_week1		NUMBER(10)                      default 0 not null,
   loggedday_week1	    NUMBER(10)                      default 0 not null,
   workday_week2		NUMBER(10)                      default 0 not null,
   loggedday_week2	    NUMBER(10)                      default 0 not null,
   workday_week3		NUMBER(10)                      default 0 not null,
   loggedday_week3	    NUMBER(10)                      default 0 not null,
   workday_week4		NUMBER(10)                      default 0 not null,
   loggedday_week4	    NUMBER(10)                      default 0 not null,
   workday_week5		NUMBER(10)                      default 0 not null,
   loggedday_week5	    NUMBER(10)                      default 0 not null,
   year                 NUMBER(10)                      not null,
   month                NUMBER(10)                      not null,
   loginStanalDate      Date                            not null,
   constraint pkOrgLoginStanals primary key (id),
   constraint fkOrgLoginStanalslsOrg foreign key (orgId)
         references organizations (id)
);

comment on table orgLoginStanals is
'部门登录统计';

comment on column orgLoginStanals.id is
'状态统计id';

comment on column orgLoginStanals.orgId is
'所属网格';

comment on column orgLoginStanals.orgInternalCode is
'所属责任区编号';

comment on column orgLoginStanals.workday_month is
'每个月工作日天数';

comment on column orgLoginStanals.loggedday_month is
'每个月登录天数';

comment on column orgLoginStanals.workday_week1 is
'第一周工作日天数';

comment on column orgLoginStanals.loggedday_week1 is
'第一周登录天数';

comment on column orgLoginStanals.workday_week2 is
'第二周工作日天数';

comment on column orgLoginStanals.loggedday_week2 is
'第二周登录天数';

comment on column orgLoginStanals.workday_week3 is
'第三周工作日天数';

comment on column orgLoginStanals.loggedday_week3 is
'第三周登录天数';

comment on column orgLoginStanals.workday_week4 is
'第四周工作日天数';

comment on column orgLoginStanals.loggedday_week4 is
'第四周登录天数';

comment on column orgLoginStanals.workday_week5 is
'第五周工作日天数';

comment on column orgLoginStanals.loggedday_week5 is
'第五周登录天数';

comment on column orgLoginStanals.year is
'年';

comment on column orgLoginStanals.month is
'月';

comment on column orgLoginStanals.loginStanalDate is
'统计时间';
/*==============================================================*/
/* Index: idx_orgLoginStanals_orgId                               */
/*==============================================================*/
create index idx_orgLoginStanals_orgId on orgLoginStanals (
   orgId ASC
);



create sequence s_baseInfoStatType
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

create table baseInfoStatType  (
   id                   NUMBER(10)                      not null,
   year                 NUMBER(10)                      not null,
   month                NUMBER(10)                      not null,
   total                NUMBER(10)                      not null,
   typeName             VARCHAR2(60)                    not null,
   baseinfoType         VARCHAR2(32)                    not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   startDate            DATE                            ,
   endDate              DATE                            ,
   createDate           DATE                            ,
   isHelp               NUMBER(10)                      ,
   noHelp               NUMBER(10)                      ,
   resited              NUMBER(10)                      ,
   sum                  NUMBER(10)                      ,
   recidivism           NUMBER(10)                      ,
   percentage           varchar2(60)                    not null,
   objectSum			 NUMBER(10)                      ,
   monthcreate			 NUMBER(10),
   constraint pkBaseInfoStatType primary key  (id)
);

comment on table baseInfoStatType  is
'基础信息类型统计';

comment on column baseInfoStatType.year is
'统计的年份';

comment on column baseInfoStatType.month is
'统计的月份';

comment on column baseInfoStatType.total is
'总数';

comment on column baseInfoStatType.typeName  is
'基础信息类型名称';

comment on column baseInfoStatType.baseinfoType is
'报表 类型';

comment on column baseInfoStatType.orgInternalCode is
'orgInternalCode';

comment on column baseInfoStatType.startDate is
'统计开始时间';
comment on column baseInfoStatType.endDate is
'统计结束时间';
comment on column baseInfoStatType.createDate is
'创建时间';
comment on column baseInfoStatType.percentage is
'所占百分比';
comment on column baseInfoStatType.objectSum is
'本月前所有数据';
comment on column baseInfoStatType.monthcreate is
'本月新增数据';
/*================================*/
/*     统计的历史记录报表类                             */
/*================================*/
create sequence s_baseInfoStatisticHistory
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

create table baseInfoStatisticHistory  (
   id                   NUMBER(10)                      not null,
   year                 NUMBER(10)                      not null,
   month                NUMBER(10)                      not null,
   sum                  NUMBER(10)                      not null,
   countValue           NUMBER(10)                   ,
   typeName             VARCHAR2(60)                    not null,
   baseinfoType         VARCHAR2(32)                    not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   orgName              VARCHAR2(60) ,
   orgId                NUMBER(10),
   isHelp               NUMBER(10)                      ,
   noHelp               NUMBER(10)                      ,
   resited              NUMBER(10)                      ,
   recidivism           NUMBER(10)                      ,
   createuser           VARCHAR2(32)                    not null,
   createDate           DATE,
   startDate            DATE                            ,
   endDate              DATE                            ,
   MONTHCREATE     NUMBER(10),
   ATTENTIONNUM    NUMBER(10),
   TOTAL           NUMBER(10),
   constraint pkBaseInfoStatisticHistory primary key  (id)
);

comment on table baseInfoStatisticHistory  is
'基础信息类型统计';

comment on column baseInfoStatisticHistory.year is
'统计的年份';

comment on column baseInfoStatisticHistory.month is
'统计的月份';

comment on column baseInfoStatisticHistory.sum is
'本组总数';
comment on column baseInfoStatisticHistory.typeName  is
'分类的名称';
comment on column baseInfoStatisticHistory.baseinfoType is
'基础信息类型名称';
comment on column baseInfoStatisticHistory.orgInternalCode is
'orgInternalCode';
comment on column baseInfoStatisticHistory.createDate is
'创建时间';
comment on column baseInfoStatisticHistory.countValue is
'创建时间';
comment on column BASEINFOSTATISTICHISTORY.MONTHCREATE
  is '本月新增';
comment on column BASEINFOSTATISTICHISTORY.ATTENTIONNUM
  is '截至本月底的关注人数';
comment on column BASEINFOSTATISTICHISTORY.TOTAL
  is '截至本月底的总人数';
  
  
  
create sequence s_baseInfoStat
increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* Table: baseInfoStat                                          */
/*==============================================================*/
create table baseInfoStat  (
   id                   NUMBER(10)                      not null,
   year                 NUMBER(10)                      not null,
   month                NUMBER(10)                      not null,
   total                NUMBER(10)                      not null,
   typeTableName        VARCHAR2(60)                    not null,
   types                VARCHAR2(32)                    not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   startDate            DATE                            not null,
   endDate              DATE                            not null,
   createDate			DATE,
   constraint pkBaseInfoStat primary key (id)
);

comment on table baseInfoStat is
'基础信息统计';

comment on column baseInfoStat.year is
'统计的年份';

comment on column baseInfoStat.month is
'统计的月份';

comment on column baseInfoStat.total is
'总数';

comment on column baseInfoStat.typeTableName is
'基础信息类型表名称';

comment on column baseInfoStat.types is
'报表 类型(年报，月报)';

comment on column baseInfoStat.orgInternalCode is
'orgInternalCode';

comment on column baseInfoStat.startDate is
'统计开始时间';

comment on column baseInfoStat.endDate is
'统计结束时间';

comment on column baseInfoStat.createDate is
'创建时间';

/**
 * 实有人口统计表
 */
create sequence s_populationStatType
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

create table populationStatType  (
   id                   NUMBER(10)                      not null,
   year                 NUMBER(10)                      not null,
   month                NUMBER(10)                      not null,
   total                NUMBER(10)                      not null,
   typeName             VARCHAR2(60)                    ,
   populationType       VARCHAR2(32)                    not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   startDate            DATE                            ,
   endDate              DATE                            ,
   createDate           DATE                            ,
   createUser           varchar2(60)                    ,
   sum                  NUMBER(10)                      ,
   percentage           varchar2(60)                    ,
   objectSum			 NUMBER(10)                      ,
   monthcreate			 NUMBER(10),
   constraint pkpopulationStatType primary key  (id)
);

CREATE INDEX indexBaseinfosStatsOrgCode ON baseInfoStatisticHistory (orginternalcode,year,month,baseinfotype);

----青少年统计序列
create sequence s_youthStatType
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;