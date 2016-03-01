create sequence s_smsMessageMark
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
-- 短信提示语表
create table smsMessageMark
(
  ID                      NUMBER(10)    not null,
  operationtType          NUMBER(10)    not null,
  dealType                NUMBER(10)    ,
  model					  varchar2(250) not null,
  CREATEUSER              VARCHAR2(30)  not null,
  UPDATEUSER              VARCHAR2(30)  ,
  CREATEDATE              DATE          not null,
  UPDATEDATE              DATE
);

comment on column smsMessageMark.ID
  is '短信提示语ID';
comment on column smsMessageMark.operationtType
  is '事件操作类型（办理，领导批示，阅读，受理，抄告等）';
comment on column smsMessageMark.dealType
  is '事件办理类型（上报，交办，越级上报，验证等）';
comment on column smsMessageMark.model
  is '消息提示语的模板';
comment on column smsMessageMark.CREATEUSER
  is '创建用户';
comment on column smsMessageMark.UPDATEUSER
  is '修改用户';
comment on column smsMessageMark.CREATEDATE
  is '创建时间';
comment on column smsMessageMark.UPDATEDATE
  is '修改时间';
  
  
create sequence s_smsMessageFun
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
-- 短信功能控制表
create table smsMessageFun
(
  ID                      NUMBER(10)    not null,
  orgId                   NUMBER(10)    not null,
  orgCode                 VARCHAR2(32)    not null,
  isOpenSms               NUMBER(1)     default 0 not null,
  CREATEUSER              VARCHAR2(30)  not null,
  UPDATEUSER              VARCHAR2(30)  ,
  CREATEDATE              DATE          not null,
  UPDATEDATE              DATE
);

comment on column smsMessageFun.ID
  is '短信功能ID';
comment on column smsMessageFun.orgId
  is '组织机构id';
comment on column smsMessageFun.orgCode
  is '组织机构code';
comment on column smsMessageFun.isOpenSms
  is '短信功能是否开启，默认为0（不开启），1开启';
comment on column smsMessageFun.CREATEUSER
  is '创建用户';
comment on column smsMessageFun.UPDATEUSER
  is '修改用户';
comment on column smsMessageFun.CREATEDATE
  is '创建时间';
comment on column smsMessageFun.UPDATEDATE
  is '修改时间';
  
--广元市短信功能开启  
insert into smsMessageFun (ID, orgId, orgCode, isOpenSms, CREATEUSER, CREATEDATE)
values (s_smsMessageFun.NEXTVAL,14155, '.1.8.', 1, 'admin', sysdate);
--测试市短信功能开启  
insert into smsMessageFun (ID, orgId, orgCode, isOpenSms, CREATEUSER, CREATEDATE)
values (s_smsMessageFun.NEXTVAL,62528, '.2.1.', 1, 'admin', sysdate);

commit;