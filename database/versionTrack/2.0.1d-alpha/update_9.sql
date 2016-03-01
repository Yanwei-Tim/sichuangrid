--本地不用执行
delete  from issues where id not in (select issueid from issuehastypes);
commit;
 
 ALTER TABLE issues rename to issues_t; 
 ALTER TABLE issuesteps rename to issuesteps_t; 
 ALTER TABLE issuelogs rename to issuelogs_t; 
 ALTER TABLE issuestepgroups rename to issuestepgroups_t;  
 
 insert into issues  select * from issues_t;
 insert into issuesteps  select * from issuesteps_t;
 insert into issuelogs  select * from issuelogs_t;
 insert into issuestepgroups  select * from issuestepgroups_t;
 commit;
 
 alter table ISSUES
  add constraint PKISSUES_N primary key (ID);
 create index IX_ISSUES_CREATEORGCODE on ISSUES (CREATEORGINTERNALCODE);
 create index IX_ISSUES_SELNUM on ISSUES (SERIALNUMBER);
 create index IX_ISSUE_CREATEORG on ISSUES (CREATEORG);
 create index IX_ISSUE_CURRENTSTEP on ISSUES (CURRENTSTEP);
 create index IX_ISSUES_OCCURORG on ISSUES (OCCURORG);
 create index ISSUES_N_CREATEORGLEVEL on ISSUES (CREATEORGLEVEL, CREATEORGFUNCTIONALORGTYPE, CREATEORGINTERNALCODE);
 create index ISSUES_N_LASTORGLEVEL on ISSUES (LASTORGLEVEL, LASTORGFUNCTIONALORGTYPE, LASTORGINTERNALCODE);
 
 create index IX_ISSUES_CREATEORGCODE_h on issues_history (CREATEORGINTERNALCODE);
 create index IX_ISSUES_SELNU_h on issues_history (SERIALNUMBER);
 create index IX_ISSUE_CREATEORG_h on issues_history (CREATEORG);
 create index IX_ISSUE_CURRENTSTEP_h on issues_history (CURRENTSTEP);
 create index IX_ISSUES_OCCURORG_h on issues_history (OCCURORG);
 create index ISSUES_CREATEORGLEVEL_h on issues_history (CREATEORGLEVEL, CREATEORGFUNCTIONALORGTYPE, CREATEORGINTERNALCODE);
 create index ISSUES_LASTORGLEVEL_h on issues_history (LASTORGLEVEL, LASTORGFUNCTIONALORGTYPE, LASTORGINTERNALCODE);
 
 alter table ISSUESTEPS
  add constraint PKISSUESTEPS_N primary key (ID);
  create index IX_ISSUESTEPS_CREATEDATE on ISSUESTEPS (CREATEDATE);
  create index IX_ISSUESTEPS_ISSUE on ISSUESTEPS (ISSUE);
  create index IX_ISSTEP_TARGET on ISSUESTEPS (TARGET, STATECODE);
  create index IX_ISSTEP_TICODESTCODE on ISSUESTEPS (TARGETINTERNALCODE, STATECODE);
  create index ISSUESTEPS_N_TARGETORGLEVEL on ISSUESTEPS (TARGETORGLEVEL, TARGETORGFUNCTIONALORGTYPE, TARGETINTERNALCODE);
  
  alter table ISSUELOGS
  add constraint PKISSUELOGS_n primary key (ID);
  create index IX_ISSUELOGS_STEPID on ISSUELOGS (STEPID);
  create index IDXISSUELOGSISSUEID on ISSUELOGS (ISSUEID);
  
  alter table ISSUESTEPGROUPS
  add constraint PKISSUESTEPGROUPS_N primary key (ID);
  create index IX_ISSUESTEPGROUPS_ISSUE on ISSUESTEPGROUPS (ISSUE);
  
 create index h_ISSUESTEPGROUPS_ISSUE on ISSUESTEPGROUPS_HISTORY (ISSUE);

 
 
 
 