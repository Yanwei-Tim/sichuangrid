--修改流动人口表和临时表登记证编号字段长度 从原来的60改成150
alter table dm_floatingpopulations_temp modify(certificatenumber varchar2(150));
alter table floatingpopulations modify(certificatenumber varchar2(150));

--境外人员表和临时表 流入原因字段长度从60改成120
alter table overseapersonnel modify(inflowreason varchar2(120));
alter table dm_overseapersonnel_temp modify(inflowreason varchar2(120));

--刑释解教表和临时表 刑期字段长度从32改为48
alter table Dm_Positiveinfos_Temp modify(imprisonmentDate varchar2(48));
alter table Positiveinfos modify(imprisonmentDate varchar2(48));

--平台消息关联表增加字段，用于平台消息改造后的查询
alter table userhasPlatformMessages add (MESSAGETYPE NUMBER(2));
alter table userhasPlatformMessages add (SENDERID NUMBER(10));
alter table userhasPlatformMessages add (CREATEDATE DATE);
comment on column userhasPlatformMessages.MESSAGETYPE is
'消息类型';
comment on column userhasPlatformMessages.SENDERID is
'发件人id';
comment on column userhasPlatformMessages.CREATEDATE is
'创建时间';