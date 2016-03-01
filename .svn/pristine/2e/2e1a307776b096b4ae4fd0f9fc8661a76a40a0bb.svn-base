create sequence S_PEACE_VILLAGE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table PEACE_VILLAGE
(
  ID                      NUMBER(10) not null,
  ORG_ID                  NUMBER(10) not null,
  ORG_INTERNAL_CODE       VARCHAR2(32),
  YEAR                    NUMBER(10) not null,
  MONTH                   NUMBER(10) not null,
  NO_CRIMINAL             NUMBER(1) default 1 not null,
  NO_DRUGS                NUMBER(1) default 1 not null,
  NO_ACCIDENT             NUMBER(1) default 1 not null,
  NO_GROUP_EVENTS         NUMBER(1) default 1 not null,
  NO_DIRTY                NUMBER(1) default 1 not null,
  HAS_ORGANIZATION        NUMBER(1) default 1 not null,
  HAS_MEASURES            NUMBER(1) default 1 not null,
  HAS_LEGAL_ADVOCACY      NUMBER(1) default 1 not null,
  HAS_SOCIAL_FORCES       NUMBER(1) default 1 not null,
  HAS_CULTURAL_ACTIVITIES NUMBER(1) default 1 not null,
  CREATEUSER              VARCHAR2(32) not null,
  UPDATEUSER              VARCHAR2(32),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint PEACE_VILLAGE_PK primary key (ID),
  constraint PEACE_VILLAGE_UN unique (ORG_ID, YEAR, MONTH)
);

comment on table PEACE_VILLAGE
  is '村社区平安三联创';
comment on column PEACE_VILLAGE.ORG_ID
  is '所属网格';
comment on column PEACE_VILLAGE.ORG_INTERNAL_CODE
  is '所属网格编号';
comment on column PEACE_VILLAGE.YEAR
  is '年';
comment on column PEACE_VILLAGE.MONTH
  is '月';
comment on column PEACE_VILLAGE.NO_CRIMINAL
  is '无刑事案件';
comment on column PEACE_VILLAGE.NO_DRUGS
  is '无毒品';
comment on column PEACE_VILLAGE.NO_ACCIDENT
  is '无安全事故';
comment on column PEACE_VILLAGE.NO_GROUP_EVENTS
  is '无群体性事件';
comment on column PEACE_VILLAGE.NO_DIRTY
  is '无脏乱差';
comment on column PEACE_VILLAGE.HAS_ORGANIZATION
  is '有自治组织';
comment on column PEACE_VILLAGE.HAS_MEASURES
  is '有三防措施';
comment on column PEACE_VILLAGE.HAS_LEGAL_ADVOCACY
  is '有法制宣传';
comment on column PEACE_VILLAGE.HAS_SOCIAL_FORCES
  is '有社会力量参与';
comment on column PEACE_VILLAGE.HAS_CULTURAL_ACTIVITIES
  is '有群众文化活动';
  
create sequence S_peace_high_village
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table peace_high_village
(
  id number(10) primary key,
  orgid number(10) not null,
  orginternalcode varchar2(32) not null,
  year number(10) not null,
  January number(1) default 1 not null,
  February number(1) default 1 not null, 
  March number(1) default 1 not null, 
  April number(1) default 1 not null, 
  May  number(1) default 1 not null,
  June  number(1) default 1 not null,
  July  number(1) default 1 not null,
  August  number(1) default 1 not null,
  September number(1) default 1 not null,
  October  number(1) default 1 not null,
  November  number(1) default 1 not null,
  December  number(1) default 1 not null,
  createdate date,
  createuser varchar2(32),
  updatedate date,
  updateuser varchar2(32)
);
comment on table peace_high_village is
'村级以上平安联创表';
comment on column peace_high_village.id is
'id';
comment on column peace_high_village.orgid is
'组织机构id';
comment on column peace_high_village.orginternalcode is
'组织机构内置编码';
comment on column peace_high_village.year is
'年份';