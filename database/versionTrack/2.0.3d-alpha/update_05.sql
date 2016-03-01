-- Create table
create table MOBILEDICTIONARY
(
  ID         NUMBER(10) not null,
  FILEURL    VARCHAR2(200),
  RENEWDATE  DATE,
  VERSION    NUMBER(10),
  CREATEUSER VARCHAR2(30),
  CREATEDATE DATE,
  UPDATEUSER VARCHAR2(30),
  UPDATEDATE DATE
);

-- Add comments to the table 
comment on table MOBILEDICTIONARY
  is '手机端字典项文件表';
-- Add comments to the columns 
comment on column MOBILEDICTIONARY.ID
  is 'id';
comment on column MOBILEDICTIONARY.FILEURL
  is '文件路径';
comment on column MOBILEDICTIONARY.RENEWDATE
  is '更新时间';
comment on column MOBILEDICTIONARY.VERSION
  is '版本号';
comment on column MOBILEDICTIONARY.CREATEUSER
  is '创建人';
comment on column MOBILEDICTIONARY.CREATEDATE
  is '创建日期';
comment on column MOBILEDICTIONARY.UPDATEUSER
  is '更新人';
comment on column MOBILEDICTIONARY.UPDATEDATE
  is '更新日期';



-- Create sequence 
create sequence S_MOBILEDICTIONARY
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;