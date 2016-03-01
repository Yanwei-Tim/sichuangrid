  ----行政部门绩效考核
create sequence s_administrativeStandard
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;


create table administrativeStandard(
   id                   NUMBER(10)                      not null,
   useLevel		NUMBER(10)                      ,
   orgInternalId	NUMBER(10)                     	,
   yellowLimitAccept    NUMBER(10)                      not null,
   yellowLimitHandle	NUMBER(10)			not null,
   redLimitAccept	NUMBER(10)			not null,
   redLimitHandle	NUMBER(10)			not null,
   remark            	VARCHAR2(600), 
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   createDate           DATE                            not null,
   constraint pkadministrativeStandard primary key (id)
);
comment on table administrativeStandard is
'行政部门时限标准表';
comment on column administrativeStandard.id is
'主键';
comment on column administrativeStandard.useLevel is
'应用层级';
comment on column administrativeStandard.orgInternalId is
'应用层级内置编码';
comment on column administrativeStandard.yellowLimitAccept is
'黄牌受理时限';
comment on column administrativeStandard.yellowLimitHandle is
'黄牌办理时限';
comment on column administrativeStandard.redLimitAccept is
'红牌受理时限';
comment on column administrativeStandard.redLimitHandle is
'红牌办理时限';
comment on column administrativeStandard.remark is
'备注';
comment on column administrativeStandard.createUser is
'创建用户';
comment on column administrativeStandard.updateUser is
'修改用户';
comment on column administrativeStandard.createDate is
'创建日期';
comment on column administrativeStandard.updateDate is
'修改时间';

insert into administrativeStandard (ID, yellowLimitAccept, yellowLimitHandle, redLimitAccept, redLimitHandle,remark ,createUser,createDate)
values (s_administrativeStandard.Nextval, 1, 1, 2, 2,'该时限标准为默认时限标准', 'admin',sysdate);
commit;


create sequence s_issueStandardForFunOrg
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table issueStandardForFunOrg(
   id                   NUMBER(10)                      not null,
   userLevel		NUMBER(10)                       ,
   orgId    NUMBER(10)                      ,
   yellowLimitAccept    NUMBER(10)                      not null,
   yellowLimitHandle  NUMBER(10)      not null,
   redLimitAccept  NUMBER(10)      not null,
   redLimitHandle  NUMBER(10)      not null,
   remark              VARCHAR2(600), 
   itemname        number(10),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   createDate           DATE                            not null,
   constraint pkissueStandardForFunOrg primary key (id),
   constraint ORGID_ITEMNAME_UNIQUE unique (ORGID, ITEMNAME)
);
comment on table issueStandardForFunOrg is
'职能部门时限标准表';
comment on column issueStandardForFunOrg.id is
'主键';
comment on column issueStandardForFunOrg.userLevel is
'当前用户所处层级';
comment on column issueStandardForFunOrg.orgId is
'职能部门id';
comment on column issueStandardForFunOrg.yellowLimitAccept is
'黄牌受理时限';
comment on column issueStandardForFunOrg.yellowLimitHandle is
'黄牌办理时限';
comment on column issueStandardForFunOrg.redLimitAccept is
'红牌受理时限';
comment on column issueStandardForFunOrg.redLimitHandle is
'红牌办理时限';
comment on column issueStandardForFunOrg.itemname is
'项目名称';
comment on column issueStandardForFunOrg.remark is
'备注';
comment on column issueStandardForFunOrg.createUser is
'创建用户';
comment on column issueStandardForFunOrg.updateUser is
'修改用户';
comment on column issueStandardForFunOrg.createDate is
'创建日期';
comment on column issueStandardForFunOrg.updateDate is
'修改时间';

alter table issuesteps add itemtype number(10);
comment on column issuesteps.itemtype is
'项目类型';

insert into issueStandardForFunOrg (ID, YELLOWLIMITACCEPT, YELLOWLIMITHANDLE, REDLIMITACCEPT, REDLIMITHANDLE, CREATEUSER, CREATEDATE,remark)
values (s_issueStandardForFunOrg.Nextval, 1, 5, 2, 10, 'admin', sysdate,'该时限标准为默认时限标准');
commit;