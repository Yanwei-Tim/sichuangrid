--公文所属用户ID
alter table documents add(userId Number(10));
--用户界面主送人反填
alter table documents add(receiversNames clob);
--用户界面抄送人反填
alter table documents add(receiversNamesCopy clob);
--主送的用户ID
alter table documents add(sendUserIds clob);
--抄送的用户ID
alter table documents add(copyUserIds clob);

create table userHasDocuments(
id number(10),
documentId number(10) not null,
userId number(10) not null,
isSign number(1),
signDate Date,
signUserId number(10),
signContent varchar2(300),
postObj number(10),
readState number(1),
readDate Date,
signer varchar2(60),
isReminder number(1) default 0,
 constraint pkuserHasDocuments primary key (ID)
);

comment on table userHasDocuments is
'公文与人员关系表';
comment on column userHasDocuments.ID is
'主键';
comment on column userHasDocuments.documentId is
'公文ID';
comment on column userHasDocuments.userId is
'用户Id';
comment on column userHasDocuments.isSign is
'是否签收 0：未签收 1：已签收';
comment on column userHasDocuments.signDate is
'签收时间';
comment on column userHasDocuments.signUserId is
'签收人';
comment on column userHasDocuments.signContent is
'签收回执内容';
comment on column userHasDocuments.postObj is
'主送还是抄送 0：主送 1：抄送';  
comment on column userHasDocuments.readState is
'阅读状态 0:未阅读 1：已阅读';
comment on column userHasDocuments.readDate  is
'阅读时间';
comment on column userHasDocuments.signer  is
'签收人姓名';
comment on column userHasDocuments.isReminder  is
'是否催收';



create sequence S_userHasDocuments
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--公文查询和发送对象管理菜单取消（架构更改后，不需要该两个模块）
update permissions set enable='0' where ename='searchDocuments';
update permissions set enable='0' where ename='sendDocumentObjectsManagement';
commit;