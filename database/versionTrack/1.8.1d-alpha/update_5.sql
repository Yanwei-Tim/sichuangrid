-- Create sequence
create sequence s_fourTeamsOrg
increment by 1
start with 9
maxvalue 9999999999
minvalue 1
cache 20;
commit;

-- Create table
create table fourTeamsOrg
(
  id                  number(10),
  organization        NUMBER(10),
  ORGINTERNALCODE 		NVARCHAR2(150),
  parentid       number(10),
  leve           number(5),
  levesort       number(5),
  teamTitle      varchar2(60),
  username       varchar2(60),
  Position       varchar2(60),
  phonenumber    varchar2(60),
  headimage      varchar2(300),
  CREATEUSER     VARCHAR2(32),
  UPDATEUSER     VARCHAR2(32),
  CREATEDATE     DATE not null,
  UPDATEDATE     DATE,
  constraint PKfourTeamsOrg primary key (ID)
);
commit ;
-- Add comments to the table 
comment on table fourTeamsOrg
  is '四支队伍组织机构';
-- Add comments to the columns 
comment on column fourTeamsOrg.id
  is '主键ID';
comment on column fourTeamsOrg.organization
  is '组织机构';
comment on column fourTeamsOrg.ORGINTERNALCODE
  is '组织机构内置代码';
comment on column fourTeamsOrg.parentid
  is '父节点';
comment on column fourTeamsOrg.leve
  is '层级(1，2，3)';
comment on column fourTeamsOrg.leveSort
  is '排序';
comment on column fourTeamsOrg.teamTitle
  is '队伍标题';
comment on column fourTeamsOrg.username
  is '成员姓名';
comment on column fourTeamsOrg.Position
  is '职务';
comment on column fourTeamsOrg.phonenumber
  is '电话';
comment on column fourTeamsOrg.headimage
  is '图像';
commit ;
--添加默认数据
insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (1, null, 1, 1, '', '', '', '', '', '', '', sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (2, 1, 2, 1, '', '', '', '', '', '', '',sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (3, 1, 2, 2, '', '', '', '', '', '', '', sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (4, 1, 2, 3, '', '', '', '', '', '', '',sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (5, 1, 2, 4, '', '', '', '', '', '', '',sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (6, 2, 3, 1, '', '', '', '', '', '', '', sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (7, 3, 3, 2, '', '', '', '', '', '', '', sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (8, 4, 3, 3, '', '', '', '', '', '', '', sysdate, null);

insert into Fourteamsorg (ID, PARENTID, LEVE, LEVESORT, TEAMTITLE, USERNAME, POSITION, PHONENUMBER, HEADIMAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (9, 5, 3, 4, '', '', '', '', '', '', '', sysdate, null);
commit ;
