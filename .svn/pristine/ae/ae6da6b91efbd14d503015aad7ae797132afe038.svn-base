alter table issues add publicltycass number(1) default 0;
comment on column issues.publicltycass
  is '是否宣传案例';
alter table issues_History add publicltycass number(1) default 0;
comment on column issues_History.publicltycass
  is '是否宣传案例';
  
update issues set publicltycass=1 where id in (select issueid from publicltycass);
update issues_History set publicltycass=1 where id in (select issueid from publicltycass);
commit;