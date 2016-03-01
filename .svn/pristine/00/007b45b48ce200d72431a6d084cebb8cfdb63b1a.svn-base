--登陆情况添加索引
create index IDX_LOGINMANAGE_2014_12_ORGID on LOGINMANAGE_2014_12(ORGID);

--create sequence s_issueJointTemps
--minvalue 1
--maxvalue 9999999999
--start with 1
--increment by 1
--cache 20;

create table issueJointTemps
(
  id            	varchar2(90) not null,
  issueId        number(10),
  serialnumber          varchar2(30) not null,
  subject               varchar2(150) not null,
  maincharacters    varchar2(60) not null,
  mobile        varchar2(60),
  telephone        varchar2(60),
  occurorg              number(10) not null,
  occurorginternalcode  varchar2(32) not null,
  createorg             number(10) not null,
  createorginternalcode varchar2(32) not null,
  issuekind             number(10),
  issueJointType    number(10),
  issueJointTypeSub    number(10),
  relatepeoplecount     number(10),
  occurlocation         varchar2(150),
  occurdate             date not null,
  hours                 varchar2(5),
  minute                varchar2(5),
  issuecontent          clob,
  importantplace        number(1),
  feedbackTime    date,
  ATTACHFILENAME  varchar2(300),
  State                  number(2),
  createuser            varchar2(32) not null,
  updateuser            varchar2(32),
  createdate            date not null,
  updatedate            date,
  lastDealTime    date,
  STATUS    number(10),
  STATUScode    VARCHAR2(300),
  LASTUSERNAME    varchar2(60),
  LASTORGINTERNALCODE  varchar2(60),
  LASTORG    number(10),
  attachFileName_uuid    VARCHAR2(150),
  data_source    VARCHAR2(32),
  constraint pkissueJointTemps primary key (id)
);
-- add comments to the table 
comment on table issueJointTemps
  is '对接事件临时表';
-- add comments to the columns 
comment on column issueJointTemps.id
  is '事件id';
  comment on column issueJointTemps.issueId
  is '事件issueId';
comment on column issueJointTemps.serialnumber
  is '事件的服务单号';
comment on column issueJointTemps.subject
  is '主题';
comment on column issueJointTemps.maincharacters
  is '主要当事人';
comment on column issueJointTemps.mobile
  is '主要当事人手机';
comment on column issueJointTemps.telephone
  is '主要当事人电话';
comment on column issueJointTemps.occurorg
  is '事件发生网格id';
comment on column issueJointTemps.occurorginternalcode
  is '发生网格内置编码';
comment on column issueJointTemps.createorg
  is '创建网格id';
comment on column issueJointTemps.createorginternalcode
  is '创建网格内置编码';
comment on column issueJointTemps.issuekind
  is '事件性质';
comment on column issueJointTemps.issueJointType
  is '事件类别（大类）';
comment on column issueJointTemps.issueJointTypeSub
  is '事件类别（子类）';
comment on column issueJointTemps.relatepeoplecount
  is '涉及人数';
comment on column issueJointTemps.occurlocation
  is '发生地';  
comment on column issueJointTemps.occurdate
  is '发生时间';
comment on column issueJointTemps.hours
  is '小时';
comment on column issueJointTemps.minute
  is '分钟';
comment on column issueJointTemps.issuecontent
  is '事件简述';  
comment on column issueJointTemps.importantplace
  is '是否重点场所';
comment on column issueJointTemps.ATTACHFILENAME
  is '附件名称'; 
comment on column issueJointTemps.State
  is '数据处理状态（默认0为刚刚进入社管的状态）';
comment on column issueJointTemps.lastDealTime
  is '冗余字段最后处理时间';  
comment on column issueJointTemps.STATUS
  is '状态（事件的状态）冗余';  
comment on column issueJointTemps.LASTUSERNAME
  is '最后操作用户名称（冗余）';  
comment on column issueJointTemps.LASTORGINTERNALCODE
  is '最后操作网格内置编码(冗余)';  
comment on column issueJointTemps.LASTORG
  is '最后操作网格id(冗余)';  
comment on column issueJointTemps.attachFileName_uuid
  is '附件的uuid'; 
comment on column issueJointTemps.STATUScode
  is '开放给事件所在状态';
comment on column issueJointTemps.data_source
  is '数据来源（用于区分是青阳还是攀枝花）';