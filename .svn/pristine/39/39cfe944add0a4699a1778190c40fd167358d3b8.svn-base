create sequence S_issueRelatedPeople
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table issueRelatedPeople  (
   ID                   NUMBER(10)         not null,
   name                 VARCHAR2(60),
   telephone            VARCHAR2(15),           
   issueId              NUMBER(10)         not null,
   constraint PK_issueRelatedPeople primary key (ID)
);
comment on table issueRelatedPeople is
'事件相关人员表';
comment on column issueRelatedPeople.name is
'相关人员姓名';
comment on column issueRelatedPeople.telephone is
'联系电话';
comment on column issueRelatedPeople.issueId is
'事件ID';

alter table issues add emergencyLevel number(10);
alter table issues add importantPlace number(1);
comment on column issues.emergencyLevel is
'重大紧急等级 ';
comment on column issues.importantPlace is
'是否重点场所';