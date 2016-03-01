alter table issues add ISSUETYPEID number(10);
alter table issues add ISSUETYPEDOMAINID number(10);
comment on column issues.ISSUETYPEID is '事件小类';
comment on column issues.ISSUETYPEDOMAINID is '事件大类';

alter table issues_History add ISSUETYPEID number(10);
alter table issues_History add ISSUETYPEDOMAINID number(10);
comment on column issues_History.ISSUETYPEID is '事件小类';
comment on column issues_History.ISSUETYPEDOMAINID is '事件大类';

alter table completedissue add ISSUETYPEID number(10);
alter table completedissue add ISSUETYPEDOMAINID number(10);
comment on column completedissue.ISSUETYPEID is '事件小类';
comment on column completedissue.ISSUETYPEDOMAINID is '事件大类';


create table issuehastype_temp as select min(rowid) as rowkey,issueid from issuehastypes group by issueid having count(1)>1;
delete from issuehastypes where rowid in (select rowkey from issuehastype_temp);
commit;

update issues i
   set (i.issuetypeid, i.issuetypedomainid) = (select it.issuetypeid,
                                                      it.issuetypedomainid
                                                 from issuehastypes it
                                                where i.id = it.issueid);

update issues_history ih set (ih.issuetypeid, ih.issuetypedomainid) = (select it.issuetypeid,
                                                      it.issuetypedomainid
                                                 from issuehastypes it
                                                where ih.id = it.issueid) ;
update completedissue ci set (ci.issuetypeid, ci.issuetypedomainid) = (select it.issuetypeid,
                                                      it.issuetypedomainid
                                                 from issuehastypes it
                                                where ci.issueid = it.issueid) ;
commit;

