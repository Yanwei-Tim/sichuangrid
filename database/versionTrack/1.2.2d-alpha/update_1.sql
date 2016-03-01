CREATE SEQUENCE S_IntegratedIndicator
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999999
MINVALUE 1
CACHE 20;
  
create table IntegratedIndicator (
 id number (10),
 year varchar2(4),
 month varchar2(2),
 orgid number(10) not null,
 parentorgid number(10) not null,
 orgtype number(10) not null,
 scord number(10,3) default 0.000,
 grade number(1) default 0,
 coefficient number(1) default 0,
 issueSum number(10) default 0,
 dealissueSum number(10) default 0,
 createuser varchar2(60),
 createdate date,
 constraint pkIntegratedIndicator primary key (ID)
);
-- Add comments to the table 
comment on table IntegratedIndicator
  is '事件绩效综合指标表';
-- Add comments to the columns 
comment on column IntegratedIndicator.year
  is '统计的年份';
comment on column IntegratedIndicator.month
  is '统计的月份';
comment on column IntegratedIndicator.orgid
  is '统计的组织机构id';  
comment on column IntegratedIndicator.parentorgid
  is '统计的组织机构父id';
comment on column IntegratedIndicator.orgtype
  is '组织机构类型';
comment on column IntegratedIndicator.scord
  is '分数';
comment on column IntegratedIndicator.grade
  is '扣分档次';  
comment on column IntegratedIndicator.coefficient
  is '扣分系数';  
comment on column IntegratedIndicator.issueSum
  is '事件接件数量';
comment on column IntegratedIndicator.dealissueSum
  is '事件处理数量';
comment on column IntegratedIndicator.createuser
  is '创建人';
comment on column IntegratedIndicator.createdate
  is '创建时间';