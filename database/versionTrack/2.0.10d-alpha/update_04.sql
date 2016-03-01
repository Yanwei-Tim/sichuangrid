alter table completedIssue add currentOrginternalCode varchar2(32);
alter table completedIssue add currentOrgLevel NUMBER(10);
alter table completedIssue add currentOrgFunctionalType NUMBER(10);
create index INDEX_CURRENTORGINTERNALCODE on completedIssue(CURRENTORGINTERNALCODE);
update completedIssue ce
   set currentOrginternalCode             =
       (select org.orginternalcode
          from organizations org
         where ce.currentorg = org.id),
       ce.Currentorglevel         =
       (select org.orglevel
          from organizations org
         where ce.currentorg = org.id),
       ce.Currentorgfunctionaltype =
       (select nvl(org.functionalorgtype,0)
          from organizations org
         where ce.currentorg = org.id);
         commit;