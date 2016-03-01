alter table ENTERPRISES add renovatetype NUMBER(10); 
comment on column ENTERPRISES.renovatetype
  is '挂牌整治:1 省挂牌整治、2 市挂牌整治、3县挂牌整治';
  
  create sequence s_visithistory
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;
create table SuperiorVisitHistory  (
   id          NUMBER(10) not null,
  createuser  VARCHAR2(32),
  createdate  DATE,
  updatedate  DATE,
  visitdate   DATE,
  visitreason VARCHAR2(1000),
  visitstate  NUMBER(10),
  visittype   NUMBER(10),
  updateuser  VARCHAR2(32),
  visitid     NUMBER(10) not null,
   constraint PK_SuperiorVisitHistory primary key (ID)
);
comment on table SuperiorVisitHistory is
'上访历史记录表';
comment on column SuperiorVisitHistory.visitdate
  is '上访时间';
comment on column SuperiorVisitHistory.visitreason
  is '上访原因';
comment on column SuperiorVisitHistory.visitstate
  is '上访状态';
comment on column SuperiorVisitHistory.visittype
  is '上访类型';

create table visitHisTypes  (
   superiorVisitHistoryId      NUMBER(10)                      not null,
   superiorVisitType    NUMBER(10)
);

comment on table visitHisTypes is
'上访历史类型';

comment on column visitHisTypes.superiorVisitHistoryId is
'信访历史ID';

comment on column visitHisTypes.superiorVisitType is
'上访类型';
commit;