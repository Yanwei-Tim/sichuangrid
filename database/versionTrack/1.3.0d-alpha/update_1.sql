CREATE SEQUENCE s_issuehandleStat
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;

create table issuehandleStat(
  id number(10) not null,
  year number(4) not null,
  month number(2) not null,
  parentorgid number(10) not null,
  orgid number(10) not null,
  orginternalcode varchar2(32) not null,
  funorgtype number(10),
  orglevel number(10) not null,
  addissuesum number(10),
  submitIssueSum number(10),
  assignIssueSum number(10),
  doingissuesum number(10),
  doneissuesum number(10),
  issuesum number(10),
  completionrate varchar2(32) default '0.0%',
  extendeddoingsum number(10),
  extendeddonesum number(10),
  extendedrate varchar2(32) default '0.0%',
  createdate date,
  createuser varchar2(60),
  constraint pkissuehandleStat primary key (ID)
);
comment on table issuehandleStat is
'各区域办理情况统计';
comment on column issuehandleStat.year is
'统计的年';
comment on column issuehandleStat.month is
'统计的月';
comment on column issuehandleStat.parentorgid is
'父组织机构id';
comment on column issuehandleStat.orgid is
'组织机构id';
comment on column issuehandleStat.orginternalcode is
'组织机构内置编码';
comment on column issuehandleStat.funorgtype is
'职能部门类型';
comment on column issuehandleStat.orgLevel is
'组织机构层级';
comment on column issuehandleStat.addissuesum is
'新增事件数';
comment on column issuehandleStat.submitIssueSum is
'上报事件数';
comment on column issuehandleStat.assignIssueSum is
'上级交办事件数';
comment on column issuehandleStat.doingissuesum is
'在办事件数';
comment on column issuehandleStat.doneissuesum is
'已办事件数';
comment on column issuehandleStat.issuesum is
'事件总数';
comment on column issuehandleStat.completionrate is
'事件办结率';
comment on column issuehandleStat.extendeddoingsum is
'超期在办数';
comment on column issuehandleStat.extendeddonesum is
'超期办结数';
comment on column issuehandleStat.extendedrate is
'超期办结率';

create index idx_year_month_handle on issuehandleStat (
   year ASC,
   month ASC
);

CREATE SEQUENCE s_issueClassificationStat
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;

create table issueClassificationStat(
  id number(10) not null,
  year number(4) not null,
  month number(2) not null,
  parentorgid number(10) not null,
  orgid number(10) not null,
  orginternalcode varchar2(32) not null,
  funorgtype number(10),
  orglevel number(10) not null,
  contradictionSum number(10),
  resolveTheContradictionSum number(10),
  securityPrecautionSum number(10),
  specialPopulationSum number(10),
  socialConditionSum number(10),
  policiesAndLawSum number(10),
  emergencieSum number(10),
  otherManageSum number(10),
  createdate date,
  createuser varchar2(60),
  constraint pkissueClassificationStat primary key (ID)
);
comment on table issueClassificationStat is
'各区域办理情况统计';
comment on column issueClassificationStat.year is
'统计的年';
comment on column issueClassificationStat.month is
'统计的月';
comment on column issueClassificationStat.parentorgid is
'父组织机构id';
comment on column issueClassificationStat.orgid is
'组织机构id';
comment on column issueClassificationStat.orginternalcode is
'组织机构内置编码';
comment on column issueClassificationStat.funorgtype is
'职能部门类型';
comment on column issueClassificationStat.orgLevel is
'组织机构层级';
comment on column issueClassificationStat.specialPopulationSum is
'特殊人群服务管理报告';
comment on column issueClassificationStat.contradictionSum is
'民生服务';
comment on column issueClassificationStat.resolveTheContradictionSum is
'矛盾化解';
comment on column issueClassificationStat.specialPopulationSum is
'治安防控';
comment on column issueClassificationStat.socialConditionSum is
'社情民意收集';
comment on column issueClassificationStat.policiesAndLawSum is
'政策法律宣传';
comment on column issueClassificationStat.emergencieSum is
'突发事件报告';
comment on column issueClassificationStat.otherManageSum is
'其它';

create index idx_year_month_Classification on issueClassificationStat (
   year ASC,
   month ASC
);

CREATE SEQUENCE s_issueStepStat
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;

create table issueStepStat(
  id number(10) not null,
  year number(4) not null,
  month number(2) not null,
  parentorgid number(10) not null,
  orgid number(10) not null,
  orginternalcode varchar2(32) not null,
  resolveGeneralSum number(10),
  resolveSkipSum number(10),
  securityGeneralSum number(10),
  securitySkipSum number(10),
  emergencieGeneralSum number(10),
  emergencieSkipSum number(10),
  otherManageGeneralSum number(10),
  otherManageSkipSum number(10),
  createdate date,
  createuser varchar2(60),
  constraint pkissueStepStat primary key (ID)
);
comment on table issueStepStat is
'各区域办理情况统计';
comment on column issueStepStat.year is
'统计的年';
comment on column issueStepStat.month is
'统计的月';
comment on column issueStepStat.parentorgid is
'父组织机构id';
comment on column issueStepStat.orgid is
'组织机构id';
comment on column issueStepStat.orginternalcode is
'组织机构内置编码';
comment on column issueStepStat.resolveGeneralSum is
'矛盾化解-普通流程';
comment on column issueStepStat.resolveSkipSum is
'矛盾化解-越级流程';
comment on column issueStepStat.securityGeneralSum is
'治安防控-普通流程';
comment on column issueStepStat.securitySkipSum is
'治安防控-越级流程';
comment on column issueStepStat.emergencieGeneralSum is
'突发事件报告-普通流程';
comment on column issueStepStat.emergencieSkipSum is
'突发事件报告-越级流程';
comment on column issueStepStat.otherManageGeneralSum is
'其它-普通流程';
comment on column issueStepStat.otherManageSkipSum is
'其它-越级流程';

create index idx_year_month_step on issueStepStat (
   year ASC,
   month ASC
);

alter table issuesteps add isextended number(1) default 0;
comment on column issuesteps.isextended is '是否超期';

create sequence S_STEADYWORKS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table STEADYWORKS
(
  ID               NUMBER(10) not null,
  ORGID            NUMBER(10) not null,
  ORGINTERNALCODE  VARCHAR2(32) not null,
  OCCURORGID            NUMBER(10) not null,
  OCCURORGINTERNALCODE  VARCHAR2(32) not null,
  gridno  VARCHAR2(200),
  SERIALNUMBER     VARCHAR2(30) not null,
  involvingSteadyNum  NUMBER(10),
  involvingSteadyType  NUMBER(10),
  involvingSteadyInfo    VARCHAR2(200) not null,
  previousSteadyInfo     VARCHAR2(1000),
  previousResolveInfo  VARCHAR2(1000),
  steadyUnit VARCHAR2(200),
  steadyUser  VARCHAR2(30),
  resolveUnit        VARCHAR2(200),
  resolveUser       VARCHAR2(30),
  aspirationType       VARCHAR2(1000),
  steadyInfo       VARCHAR2(1000),
  warningType       NUMBER(1),
  bookingUnit varchar2(200),
  registrant varchar2(30),
  registrationTime DATE,
  CREATETABLETYPE  NUMBER(10),
  SERVERCONTRACTOR VARCHAR2(60),
  SERVERTELEPHONE  VARCHAR2(30),
  SERVERJOB        VARCHAR2(60),
  SERVERUNIT       VARCHAR2(150),
  CREATEUSER       VARCHAR2(60) not null,
  UPDATEUSER       VARCHAR2(60),
  CREATEDATE       DATE not null,
  UPDATEDATE       DATE,
  constraint PKSTEADYWORKS primary key (ID)
);
-- Add comments to the table 
comment on table STEADYWORKS
  is '稳定工作台账表';
-- Add comments to the columns 
comment on column STEADYWORKS.ID
  is '稳定工作台账id';
comment on column STEADYWORKS.ORGID
  is '所属网格';
comment on column STEADYWORKS.ORGINTERNALCODE
  is '所属网格编号';
comment on column STEADYWORKS.OCCURORGID
  is '发生网格';
comment on column STEADYWORKS.OCCURORGINTERNALCODE
  is '发生网格编号';
comment on column STEADYWORKS.gridno
  is '网格号';
comment on column STEADYWORKS.SERIALNUMBER
  is '编号';
comment on column STEADYWORKS.involvingSteadyNum
  is '涉稳群体人数';
comment on column STEADYWORKS.involvingSteadyType
  is '涉稳人群类别';
comment on column STEADYWORKS.involvingSteadyInfo
  is '涉稳事项';
comment on column STEADYWORKS.previousSteadyInfo
  is '历次涉稳情况';
comment on column STEADYWORKS.previousResolveInfo
  is '历次化解情况';
comment on column STEADYWORKS.steadyUnit
  is '稳控责任单位';
comment on column STEADYWORKS.steadyUser
  is '稳控责任人';
comment on column STEADYWORKS.resolveUnit
  is '化解责任部门';
comment on column STEADYWORKS.resolveUser
  is '化解责任人';
comment on column STEADYWORKS.aspirationType
  is '诉求类别';
comment on column STEADYWORKS.steadyInfo
  is '涉稳人员或群体稳定现状';
comment on column STEADYWORKS.warningType
  is '预警级别';
comment on column STEADYWORKS.SERVERCONTRACTOR
  is '服务联系人';
comment on column STEADYWORKS.SERVERTELEPHONE
  is '服务联系电话';
comment on column STEADYWORKS.SERVERJOB
  is '服务职务';
comment on column STEADYWORKS.SERVERUNIT
  is '服务联系人单位';
comment on column STEADYWORKS.bookingUnit
  is '登记单位';
comment on column STEADYWORKS.registrant
  is '登记人';
comment on column STEADYWORKS.registrationTime
  is '登记时间';
comment on column STEADYWORKS.CREATEUSER
  is '创建人';
comment on column STEADYWORKS.UPDATEUSER
  is '修改人';
comment on column STEADYWORKS.CREATEDATE
  is '创建时间';
comment on column STEADYWORKS.UPDATEDATE
  is '修改时间';
comment on column STEADYWORKS.CREATETABLETYPE
  is '建表类型';
  
create sequence S_STEADYWORK_peopleinfo
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table STEADYWORK_peopleinfo
(
  ID               NUMBER(10) not null,
  steadyworkid     NUMBER(10) not null,
  NAME             VARCHAR2(60),
  IDCARDNO         VARCHAR2(60),
  MOBILENUMBER     VARCHAR2(15),
  GENDER           NUMBER(10),
  BIRTHDAY         DATE,
  PERMANENTADDRESS VARCHAR2(150),
  ISPARTYMEMBER    NUMBER(1) default 0,
  POSITION         NUMBER(10),
  constraint PKpeopleinfoS primary key (ID)
);
-- Add comments to the table 
comment on table STEADYWORK_peopleinfo
  is '稳定工作台账人员表';
-- Add comments to the columns 
comment on column STEADYWORK_peopleinfo.ID
  is '稳定工作台账人员表id';
comment on column STEADYWORK_peopleinfo.NAME
  is '姓名';
comment on column STEADYWORK_peopleinfo.IDCARDNO
  is '身份证';
comment on column STEADYWORK_peopleinfo.MOBILENUMBER
  is '联系电话';
comment on column STEADYWORK_peopleinfo.GENDER
  is '性别';
comment on column STEADYWORK_peopleinfo.BIRTHDAY
  is '出生年月';
comment on column STEADYWORK_peopleinfo.PERMANENTADDRESS
  is '常住地址';
comment on column STEADYWORK_peopleinfo.ISPARTYMEMBER
  is '是否党员，0否 1是';
comment on column STEADYWORK_peopleinfo.POSITION
  is '职业或身份';
  
  
  
/**涉稳人群类别字典项*/
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'涉稳人群类别',0,'');


insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='涉稳人群类别'),0,1, '涉稳人', 'ser', 'shewenren','admin',sysdate);
 
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='涉稳人群类别'),1,2, '涉稳群体代表', 'swqtdb', 'shewenquntidaibiao','admin',sysdate);

commit;
  