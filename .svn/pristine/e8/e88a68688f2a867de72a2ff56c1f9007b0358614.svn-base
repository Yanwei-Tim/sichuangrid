CREATE SEQUENCE S_ISSUEAPPLYDELAY
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;

-- Create table 延期申请表
create table ISSUEAPPLYDELAY
(
  id                 NUMBER(10) not null,
  stepid             NUMBER(10) not null,
  delayinfo          VARCHAR2(1000) not null,
  delaydate          DATE not null,
  delayworkday       NUMBER,
  auditorg           NUMBER(10) not null,
  auditstate         NUMBER(2),
  permitdelaydate    DATE,
  permitdelayworkday NUMBER,
  auditinfo          VARCHAR2(1000),
  applyDate         DATE not null,
  applyUser         NUMBER(10) not null,
  auditdate          DATE,
  audituser          NUMBER(10),
  constraint pkIssueApplyDelay primary key (ID)
);
-- Add comments to the table 
comment on table ISSUEAPPLYDELAY
  is '延期申请';
-- Add comments to the columns 
comment on column ISSUEAPPLYDELAY.stepid
  is '流程节点id';
comment on column ISSUEAPPLYDELAY.delayinfo
  is '延期原因';
comment on column ISSUEAPPLYDELAY.delaydate
  is '延期时间';
comment on column ISSUEAPPLYDELAY.delayworkday
  is '延期工作日';
comment on column ISSUEAPPLYDELAY.auditorg
  is '审核组织';
comment on column ISSUEAPPLYDELAY.auditstate
  is '审核状态 0 同意 1 不同意';
comment on column ISSUEAPPLYDELAY.permitdelaydate
  is '允许延期时间';
comment on column ISSUEAPPLYDELAY.permitdelayworkday
  is '允许延期工作日';
comment on column ISSUEAPPLYDELAY.auditinfo
  is '审核意见（备注）';
comment on column ISSUEAPPLYDELAY.applyDate
  is '申请时间';
comment on column ISSUEAPPLYDELAY.applyUser
  is '申请人';
comment on column ISSUEAPPLYDELAY.auditdate
  is '审核时间';
comment on column ISSUEAPPLYDELAY.audituser
  is '审核人';

-- Add/modify columns 
alter table REGRADEDPOINTS add logid number(10);
-- Add comments to the columns 
comment on column REGRADEDPOINTS.logid
  is '日志ID';
  