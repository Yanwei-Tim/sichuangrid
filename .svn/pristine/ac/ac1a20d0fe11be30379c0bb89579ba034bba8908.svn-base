
-- 任务清单考核时间标准
create sequence S_tasklistTimeStandard
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table taskListTimeStandard(
id number(10) not null,
  orgId number(10) not null,
  orgCode varchar2(32) not null,
  orgLevel number(10),
  itemNameDict number(10) not null,
  signYellowLimit number(10),
  signRedLimit number(10),
  replayYellowLimit number(10),
  replayRedLimit number(10),
  remark varchar2(600),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint pk_tasklistTimeStandard primary key (ID)
);

comment on table tasklistTimeStandard is '任务清单考核时间标准';
comment on column tasklistTimeStandard.itemNameDict is '项目名字典id';
comment on column tasklistTimeStandard.signYellowLimit is '签收黄牌时间（小时）';
comment on column tasklistTimeStandard.signRedLimit is '签收红牌时间（小时）';
comment on column tasklistTimeStandard.replayYellowLimit is '回复黄牌时间（小时）';
comment on column tasklistTimeStandard.replayRedLimit is '回复红牌时间（小时）';