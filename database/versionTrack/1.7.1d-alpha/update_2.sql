--是否提醒用户，矫正时间快到期  0否  1是--
alter table RECTIFICATIVEPERSONS add isRemind number(1) default 0;
alter table FLOATINGPOPULATIONS add isRemind number(1) default 0;

--创建序列--
CREATE sequence S_earlyWarning
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;

--创建时间预警表--
create table EARLYWARNING(
id          number(10)          not null,
name		VARCHAR2(32)        not null,
remindTime	number(2)			not null,
describe 	VARCHAR2(600),
CREATEUSER  VARCHAR2(32)        not null,
UPDATEUSER  VARCHAR2(32),
CREATEDATE  DATE                not null,
UPDATEDATE  DATE,
constraint PKEARLYWARNING primary key (ID)
 );
 comment on table EARLYWARNING
 is '预警提醒时间表';
 comment on column EARLYWARNING.ID
 is '编号';
 comment on column EARLYWARNING.NAME
 is '名称';
 comment on column EARLYWARNING.REMINDTIME
 is '提前几天提醒';
 comment on column EARLYWARNING.DESCRIBE
 is '对当前提醒的描述';
 


--默认添加的语句--
insert into EARLYWARNING (ID, NAME, REMINDTIME, DESCRIBE, CREATEUSER, CREATEDATE)
values (S_earlyWarning.Nextval,'rectifyEndDateRemind',2, '矫正人员矫正日期到期预警提前天数', 'admin', sysdate);

insert into EARLYWARNING (ID, NAME, REMINDTIME, DESCRIBE, CREATEUSER, CREATEDATE)
values (S_earlyWarning.Nextval,'expectedDatedueRemind',0, '流动人口到期日期预警提前天数', 'admin', sysdate);
commit;