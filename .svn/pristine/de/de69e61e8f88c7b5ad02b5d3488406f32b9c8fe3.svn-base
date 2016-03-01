CREATE SEQUENCE S_IssueAnalysis
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;
   
create table IssueAnalysis (
 id number (10),
 year varchar2(4),
 month varchar2(2),
 orgid number(10) not null,
 orgCode VARCHAR2(32) not null,
 issueTypeId number(10) ,
 issueTypeDomainId number(10),
 issueSum number(10) default 0,
 createuser varchar2(60),
 createdate date,
 constraint pkIssueAnalysis primary key (ID)
);
comment on table IssueAnalysis
  is '事件研判分析表';
comment on column IssueAnalysis.year
  is '统计的年份';
comment on column IssueAnalysis.month
  is '统计的月份';
comment on column IssueAnalysis.orgid
  is '统计的组织机构id';  
comment on column IssueAnalysis.orgCode
  is '统计的组织机构编码';
comment on column IssueAnalysis.issueTypeId
  is '事件小类ID';
comment on column IssueAnalysis.issueTypeDomainId
  is '事件大类ID';
comment on column IssueAnalysis.issueSum
  is '数量';  
comment on column IssueAnalysis.createuser
  is '创建人';
comment on column IssueAnalysis.createdate
  is '创建时间';

  create index idx_year_month_IssueAnalysis on IssueAnalysis (
   year ASC,
   month ASC
);