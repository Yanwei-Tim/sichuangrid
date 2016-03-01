
---三本台帐
alter table STEADYWORKS add orgType NUMBER(10);

update STEADYWORKS s set s.orgType = (select o.orgtype from organizations o where o.id =s.orgid); 
commit;

alter table POORPEOPLES add orgType NUMBER(10);

update POORPEOPLES s set s.orgType = (select o.orgtype from organizations o where o.id =s.orgid);
commit;

alter table PEOPLEASPIRATIONS add orgType NUMBER(10);

update PEOPLEASPIRATIONS s set s.orgType = (select o.orgtype from organizations o where o.id =s.orgid);
commit;

alter table accountsteps_xichang add orgType NUMBER(10);

update accountsteps_xichang s set s.orgType = (select o.orgtype from organizations o where o.id =s.targetorgid);
commit;

----组织机构
alter table PrimaryOrganizations add  parentId NUMBER(10);
update PrimaryOrganizations s set s.parentId = (select o.parentId from organizations o where o.id =s.orgid);
commit;
----缓存表修改
alter table leaderviewcache modify (cachetype NUMBER(4) default 0);
---权限中文名称修改
update permissions set cname='群防群治组织' where ename='massedutyOrgManagement';
commit;