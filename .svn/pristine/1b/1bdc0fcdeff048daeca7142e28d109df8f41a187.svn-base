-----表和sequence权限要给dataChange
----sequence 
CREATE SEQUENCE S_issueJointAttachfiles
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;
-- create table
create table issueJointAttachfiles
(
  id			number(10) not null,
  filename            	varchar2(150) not null,
  physicsfullfilename 	varchar2(250) not null,
  modulekey           	varchar2(150) not null,
  issueid      			number(10) not null,
  constraint pkissueJointAttachfiles primary key (id)
);

comment on table issueJointAttachfiles is
'事件对接已办结附件表';
comment on column issueJointAttachfiles.physicsfullfilename is
'文件路径包含文件名（真实名称）';
comment on column issueJointAttachfiles.filename is
'文件名';
comment on column issueJointAttachfiles.modulekey is
'是新增还是办结';
comment on column issueJointAttachfiles.issueid is
'事件主表id';