----事件对接Joint
-- Create sequence 事件表
create sequence s_issueJoints
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create sequence 日志表
create sequence S_issueJointLogs
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create sequence步骤表
create sequence s_issueJointSteps
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
-- Create sequence修改身份证日志表
create sequence s_updateIdcardNoLogs
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
 -- Create table 事件表
create table issueJoints
(
  id                    number(10) not null,
  serialnumber          varchar2(30) not null,
  subject               varchar2(150) not null,
  maincharacters		varchar2(60) not null,
  mobile				varchar2(60),
  telephone				varchar2(60),
  occurorg              number(10) not null,
  occurorginternalcode  varchar2(32) not null,
  createorg             number(10) not null,
  createorginternalcode varchar2(32) not null,
  lastorg               number(10),
  lastorginternalcode   varchar2(32),
  issuekind             number(10),
  issueJointType		number(10),
  issueJointTypeSub		number(10),
  relatepeoplecount     number(10),
  lastusername          varchar2(60),
  occurlocation         varchar2(150),
  status                varchar2(60),
  occurdate             date not null,
  hours                 varchar2(5),
  minute                varchar2(5),
  issuecontent          clob,
  importantplace        number(1),
  createuser            varchar2(32) not null,
  updateuser            varchar2(32),
  createdate            date not null,
  updatedate            date,
  feedbackTime			date,
  lastDealTime			date,
  dealUserName			varchar2(60),
  dealMobile			varchar2(60),
  dealContent			clob,
  impDate				date not null,
  constraint pkissueJoints primary key (id),
  constraint fkissueJointsOrg foreign key (occurOrg) references organizations (id)
);
-- add comments to the table 
comment on table issuejoints
  is '事件表';
-- add comments to the columns 
comment on column issuejoints.id
  is '事件id';
comment on column issuejoints.serialnumber
  is '事件的服务单号';
comment on column issuejoints.subject
  is '主题';
comment on column issuejoints.maincharacters
  is '主要当事人';
comment on column issuejoints.mobile
  is '主要当事人手机';
comment on column issuejoints.telephone
  is '主要当事人电话';
comment on column issuejoints.occurorg
  is '事件发生网格id';
comment on column issuejoints.occurorginternalcode
  is '发生网格内置编码';
comment on column issuejoints.createorg
  is '创建网格id';
comment on column issuejoints.createorginternalcode
  is '创建网格内置编码';
comment on column issuejoints.lastorg
  is '最后操作网格id';
comment on column issuejoints.lastorginternalcode
  is '最后操作网格内置编码';
comment on column issuejoints.issuekind
  is '事件性质';
comment on column issuejoints.issueJointType
  is '事件类别（大类）';
comment on column issuejoints.issueJointTypeSub
  is '事件类别（子类）';
comment on column issuejoints.relatepeoplecount
  is '涉及人数';
comment on column issuejoints.lastusername
  is '最后操作用户名称';
comment on column issuejoints.occurlocation
  is '发生地';  
comment on column issuejoints.status
  is '状态';
comment on column issuejoints.occurdate
  is '发生时间';
comment on column issuejoints.hours
  is '小时';
comment on column issuejoints.minute
  is '分钟';
comment on column issuejoints.issuecontent
  is '事件简述';  
comment on column issuejoints.importantplace
  is '是否重点场所';
comment on column issuejoints.feedbackTime
  is '冗余字段反馈时间';
comment on column issuejoints.lastDealTime
  is '冗余字段最后处理时间';
comment on column issuejoints.dealContent
  is '冗余字段处理意见';
comment on column issuejoints.dealUserName
  is '冗余字段处理用户名称';
comment on column issuejoints.dealMobile
  is '冗余字段处理人电话';
comment on column issuejoints.impDate
  is '冗余字段导入时间';
  
-- Create table 日志表
create table issueJointLogs
(
  id                   number(10) not null,
  issueid              number(10) not null,
  dealorgid            number(10) not null,
  dealtype             number(4),
  dealorginternalcode  varchar2(32),
  dealusername         varchar2(60) not null,
  mobile               varchar2(15),
  dealdescription      varchar2(600),
  createuser           varchar2(60) not null,
  updateuser           varchar2(60),
  dealtime             date not null,
  createdate           date not null,
  updatedate           date,
  content              clob,
  stepid               number(10),
  constraint PKissueJointLogs primary key (ID)
);
-- add comments to the table 
comment on table issuejointlogs
  is '事件处理日志表';
-- add comments to the columns 
comment on column issuejointlogs.issueid
  is '服务办事编号';
comment on column issuejointlogs.dealorgid
  is '处理部门编号';
comment on column issuejointlogs.dealtype
  is '处理类型';
comment on column issuejointlogs.dealorginternalcode
  is '处理部门内部编号';
comment on column issuejointlogs.dealusername
  is '处理用户';
comment on column issuejointlogs.mobile
  is '处理人手机';
comment on column issuejointlogs.dealdescription
  is '处理描述';
comment on column issuejointlogs.createuser
  is '创建人';
comment on column issuejointlogs.updateuser
  is '修改人';
comment on column issuejointlogs.dealtime
  is '处理时间';
comment on column issuejointlogs.createdate
  is '创建时间';
comment on column issuejointlogs.updatedate
  is '修改时间';
comment on column issuejointlogs.content
  is '内容';
comment on column issuejointlogs.stepid
  is '处理步骤id';
-----index
create index indexissuejointlogsissueid on issuejointlogs (issueid);

-- Create table 步骤表
create table issueJointSteps
(
  id                 number(10) not null,
  source             number(10) not null,
  sourceinternalcode varchar2(32) not null,
  target             number(10) not null,
  targetinternalcode varchar2(32) not null,
  entrydate          date,
  enddate            date,
  lastdealdate       date,
  statecode          number(10) not null,
  issue              number(10) not null,
  createuser         varchar2(32) not null,
  updateuser         varchar2(32),
  createdate         date not null,
  updatedate         date,
  constraint PKissueJointSteps primary key (ID)
);
-- add comments to the table 
comment on table issuejointsteps
  is '事件处理步骤表';
-- add comments to the columns 
comment on column issuejointsteps.id
  is '处理步骤id';
comment on column issuejointsteps.source
  is '该步骤来源部门id';
comment on column issuejointsteps.sourceinternalcode
  is '该步骤来源部门orgcode';
comment on column issuejointsteps.target
  is '该步骤的目标处理部门';
comment on column issuejointsteps.entrydate
  is '进入该处理部门的时间';
comment on column issuejointsteps.enddate
  is '该步骤的结束时间';
comment on column issuejointsteps.lastdealdate
  is '该步骤上一次处理的时间';
comment on column issuejointsteps.statecode
  is '该步骤状态码';
comment on column issuejointsteps.issue
  is '该步骤所属事件的id';
  
-- Create table 修改身份证日志表
create table updateIdcardNoLogs
(
  id                 number(10) not null,
  dataOrgId          number(10) not null,
  dataInternalcode 	 varchar2(32) not null,
  dataId			 number(10) not null,
  dataType			 varchar2(60) not null,
  dataBeforeOperate  varchar2(60) not null,
  dataAfterOperate	 varchar2(60) not null,
  operateUser        varchar2(60) not null,
  operateDate		 date not null,
  createuser         varchar2(32) not null,
  updateuser         varchar2(32),
  createdate         date not null,
  updatedate         date,
  constraint PKupdateIdcardNoLogs primary key (ID)
);
-- add comments to the table 
comment on table updateIdcardNoLogs
  is '修改身份证日志表';
-- add comments to the columns 
comment on column updateIdcardNoLogs.id
  is '修改身份证日志id';
comment on column updateIdcardNoLogs.dataOrgId
  is '被修改的数据的组织机构id';
comment on column updateIdcardNoLogs.dataInternalcode
  is '被修改的数据的组织机构组织机构编码';
comment on column updateIdcardNoLogs.dataId
  is '被修改的数据的id';
comment on column updateIdcardNoLogs.dataType
  is '被修改的数据的类型';
comment on column updateIdcardNoLogs.dataBeforeOperate
  is '被修改前身份证号码';
comment on column updateIdcardNoLogs.dataAfterOperate
  is '被修改后身份证号码';
comment on column updateIdcardNoLogs.operateUser
  is '修改身份证号码的用户';
comment on column updateIdcardNoLogs.operateDate
  is '修改身份证号码的时间';
commit;