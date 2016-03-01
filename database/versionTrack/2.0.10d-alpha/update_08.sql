alter table issuehandlestat add verificationSum NUMBER(10);
alter table issuehandlestat add verificationedSum NUMBER(10);
comment on column issuehandlestat.verificationSum is '待验证事件数';
comment on column issuehandlestat.verificationedSum is '已验证事件数';