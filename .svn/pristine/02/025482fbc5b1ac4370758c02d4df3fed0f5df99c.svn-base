--短信账号
create sequence s_SMS_account
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table SMS_account(
  id            NUMBER(10)    not null,
  orgId         NUMBER(10)      not null,
  orgCode       VARCHAR2(32)    not null,
  name          VARCHAR2(60)    not null,
  pwd           VARCHAR2(32)    not null,
  type          NUMBER(10)     ,
  callbackPwd   varchar2(32),
  createUser        VARCHAR2(60)  not null,
  updateUser        VARCHAR2(60),
  createDate        DATE      not null,
  updateDate        DATE,
  constraint pk_SMS_account primary key (id)
);
comment on table SMS_account is '短信账号';
comment on column SMS_account.name is '短信账号名字';
comment on column SMS_account.pwd is '短信账号密码';
comment on column SMS_account.type is '短信账号类型';
comment on column SMS_account.callbackPwd is '短信状态通知回调密码';

--短信发送组
create sequence s_SMS_send_group
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table SMS_send_group(
  id            NUMBER(10)    not null,
  orgId         NUMBER(10)      not null,
  orgCode       VARCHAR2(32)    not null,
  smsSendId     VARCHAR2(32),
  senderAccountId NUMBER(10)    not null,
  senderAccountName VARCHAR2(60)    not null,
  senderUserId  NUMBER(10)    not null,
  senderUserName        VARCHAR2(60)  not null,
  smscontent   varchar(500) not null,
  receiverRedCuffTeamType varchar(300),
  receiverMobile     varchar(4000) not null,
  totalNumber number(10),
  successNumber number(10),
  failNumber number(10),
  lastResultDate date,
  createUser        VARCHAR2(60)  not null,
  updateUser        VARCHAR2(60),
  createDate        DATE      not null,
  updateDate        DATE,
  constraint pk_SMS_send_group primary key (id)
);
comment on table SMS_send_group is '短信发送记录';
comment on column SMS_send_group.smsSendId is '短信发送id';
comment on column SMS_send_group.senderAccountId is '发送账号id';
comment on column SMS_send_group.senderAccountName is '发送账号名';
comment on column SMS_send_group.senderUserId is '发送者用户id';
comment on column SMS_send_group.senderUserName is '发送者用户名';
comment on column SMS_send_group.smscontent is '短信内容';
comment on column SMS_send_group.receiverRedCuffTeamType is '接收者类型（红袖套类型）';
comment on column SMS_send_group.receiverMobile is '接收短信手机';
comment on column SMS_send_group.totalNumber is '发送总数';
comment on column SMS_send_group.successNumber is '发送成功数';
comment on column SMS_send_group.failNumber is '发送失败数';
comment on column SMS_send_group.lastResultDate is '最后结果通知时间';

--短信发送结果
create sequence s_SMS_send_result
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
create table SMS_send_result(
  id            NUMBER(10)    not null,
  groupId      NUMBER(10) ,
  smsSendId     VARCHAR2(32),
  mobile        varchar(11) not null,
  state         varchar(20),
  createUser        VARCHAR2(60)  not null,
  updateUser        VARCHAR2(60),
  createDate        DATE      not null,
  updateDate        DATE,
  constraint pk_SMS_send_result primary key (id)
);
comment on table SMS_send_result is '短信发送结果';
comment on column SMS_send_result.groupId is '短信发送组id';
comment on column SMS_send_result.smsSendId is '短信发送id（和发送记录中一样）';
comment on column SMS_send_result.mobile is '手机号';
comment on column SMS_send_result.state is '状态';

commit;