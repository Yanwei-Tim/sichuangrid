--更改字符串长度
alter table recoverdatainfos modify name varchar2(200);

--临时附件存放表序列
create sequence S_SYS_ATTACHMENT
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--临时附件存放表
create table SYS_ATTACHMENT
(
  ID                    NUMBER not null,
  FILE_NAME             VARCHAR2(100),
  FILE_PATH             VARCHAR2(512),
  TARGET_TYPE           VARCHAR2(30),
  TARGET_ID             NUMBER,
  FILE_TYPE             VARCHAR2(30),
  REAL_NAME             VARCHAR2(100),
  CREATE_USER           VARCHAR2(32),
  UPDATE_USER           VARCHAR2(32),
  UPDATE_DATE           DATE,
  CREATE_DATE           DATE
);
-- Add comments to the columns 
comment on column SYS_ATTACHMENT.FILE_NAME
  is '实际保存文件名';
comment on column SYS_ATTACHMENT.FILE_PATH
  is '实际保存路径';
comment on column SYS_ATTACHMENT.TARGET_TYPE
  is '附件所属模块';
  comment on column SYS_ATTACHMENT.TARGET_ID
  is '目标类型，附件关联的目标ID';
comment on column SYS_ATTACHMENT.FILE_TYPE
  is '文件类型 .doc,.txt,avi...';
comment on column SYS_ATTACHMENT.REAL_NAME
  is '原文件名';
comment on column SYS_ATTACHMENT.CREATE_USER
  is '创建用户';
comment on column SYS_ATTACHMENT.UPDATE_USER
  is '更新用户名';
comment on column SYS_ATTACHMENT.UPDATE_DATE
  is '更新用户';
comment on column SYS_ATTACHMENT.CREATE_DATE
  is '创建日期';
  
 commit;