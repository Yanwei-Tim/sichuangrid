----删除实有人口重复数据记录
-- Create sequence 删除实口记录
create sequence s_poepleDuplicateRemovalLogs
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table 删除实口记录证日志表
create table poepleDuplicateRemovalLogs
(
  id                 number(10) not null,
  dataOrgId          number(10),
  dataInternalcode 	 varchar2(32), 
  dataId			 number(10) not null,
  idcardNo  		 varchar2(60) not null,
  name				 varchar2(60),
  dataType			 varchar2(60) not null,
  baseInfoId			 number(10) not null,
  actualId			 number(10),
  actualType		 varchar2(32),
  populationType	 varchar2(32),
  operateType		 varchar2(32) not null,
  operateDate		 date not null,
  baseInfo			 clob,
  constraint PKpoepleDuplicateRemovalLogs primary key (ID)
);
-- add comments to the table 
comment on table poepleDuplicateRemovalLogs
  is '删除实口记录证日志表';
-- add comments to the columns 
comment on column poepleDuplicateRemovalLogs.id
  is '删除实口记录证日志id';
comment on column poepleDuplicateRemovalLogs.dataOrgId
  is '数据的所在组织机构id';
comment on column poepleDuplicateRemovalLogs.dataInternalcode
  is '数据的所在组织机构编码';
comment on column poepleDuplicateRemovalLogs.dataId
  is '数据的id';
comment on column poepleDuplicateRemovalLogs.idcardNo
  is '数据身份证号码';
comment on column poepleDuplicateRemovalLogs.name
  is '数据的姓名';
comment on column poepleDuplicateRemovalLogs.dataType
  is '数据的类型(对应的表名称)';
comment on column poepleDuplicateRemovalLogs.baseInfoId
  is '数据所对应的baseid';
comment on column poepleDuplicateRemovalLogs.actualId
  is '数据所对应的实口id户籍、流动（针对业务人员）';
comment on column poepleDuplicateRemovalLogs.actualType
  is '数据所对应的实口类型';
comment on column poepleDuplicateRemovalLogs.populationType
  is '数据所对应的业务人员类型';
comment on column poepleDuplicateRemovalLogs.operateType
  is '数据所对应的的操作类型（update,reader,delete,error）';
comment on column poepleDuplicateRemovalLogs.operateDate
  is '修改身份证号码的时间';
comment on column poepleDuplicateRemovalLogs.baseInfo
  is '对应删除baseinfo操作的备份记录的json记录';
commit;