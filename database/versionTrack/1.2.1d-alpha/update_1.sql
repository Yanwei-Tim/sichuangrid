--事件步骤的最后到期时间
alter table issuesteps add lastenddate date;
comment on column issuesteps.lastenddate is '最后到期时间';

--行政部门考核规则
alter table administrativestandard add issuedomainid number(10);
comment on column administrativestandard.issuedomainid is '事件类型';
alter table administrativestandard add issuetypeid number(10);
comment on column administrativestandard.issuetypeid is '事件子类';

alter table issuesteps add delaystate number(2) default 0;
comment on column issuesteps.delaystate is '延期状态(0 未申请 1 审核中 2 审核通过 3 审核不通过)';