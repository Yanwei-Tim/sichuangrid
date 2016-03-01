create table elderlypeopletemp as
   select id as oldID,substr((select o.departmentno
      from organizations o
            where nu.orgid = o.id),0,4) || id as newId,
               nu.orgid as orgId
          from elderlypeople nu;
alter table elderlypeopletemp add constraint ELDERPRIMARYID primary key (OLDID, NEWID);
	   

delete 	from houseHasImportantPopulation_b5 where POPULATIONTYPE='elderlyPeople' 
	and POPULATIONID not in (select oldId from elderlypeopletemp);
commit;

declare 
cursor cur is select /*+ parallel(y,8) */ rowid,y.* from houseHasImportantPopulation_b5 y where y.populationtype = 'elderlyPeople';
counter number(10);
begin
counter:=0;
for rec in cur loop
update houseHasImportantPopulation_b5 p set POPULATIONID=(select newid from elderlypeopletemp t where rec.populationid=t.oldid) 
where  p.rowid = rec.rowid ;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;

delete from   populationtypes_bak05 where  populationtype='elderlyPeople' 
	and POPULATIONID not in (select oldId from elderlypeopletemp);
commit;

declare 
cursor cur is select /*+ parallel(y,8) */ rowid,y.* from populationtypes_bak05 y where y.populationtype = 'elderlyPeople';
counter number(10);
begin
counter:=0;
for rec in cur loop
update populationtypes_bak05 p set POPULATIONID=(select newid from elderlypeopletemp t where rec.populationid=t.oldid) 
where  p.rowid = rec.rowid ;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;



ALTER TABLE houseHasImportantPopulation rename to  houseHasImportantPopulation_b;
ALTER TABLE houseHasImportantPopulation_b5 rename to  houseHasImportantPopulation;

create index IDX_HOUSEHASPOPULATION_TYPE_B on houseHasImportantPopulation (HOUSEID, POPULATIONTYPE);
create index IND_HSEIMPTPUP_POPID_B on houseHasImportantPopulation (POPULATIONID);


ALTER TABLE populationtypes rename to  populationtypes_bak;
ALTER TABLE populationtypes_bak05 rename to  populationtypes;

create index IDX_ACTUALTYPE_ACTUALID_B on populationtypes (ACTUALID, ACTUALTYPE);
create unique index I_POPULATIONTYPES_BRUCE_ID_B on populationtypes (ID);
create index I_POPULATIONTYPES_PID_B on populationtypes (POPULATIONID);

update serviceMemberHasObject smh
   set smh.objectid =
       (select newid from elderlypeopletemp t where smh.objectid = t.oldid)
 where smh.objecttype = 'elderlyPeople' and exists (select 1 from elderlypeople where id = smh.objectid);
 
update serviceRecordRelyObject srr
   set srr.objectid =
       (select newid from elderlypeopletemp t where srr.objectid = t.oldid)
 where srr.objecttype = 'elderlyPeople' and exists (select 1 from elderlypeople where id = srr.objectid);
 
update Serviceguardershasobject sgh
   set sgh.objectid =
       (select newid from elderlypeopletemp t where sgh.objectid = t.oldid)
 where sgh.objecttype = 'elderlyPeople' and exists (select 1 from elderlypeople where id = sgh.objectid);
 
update serviceTeamHasObject sth
   set sth.objectid =
       (select newid from elderlypeopletemp t where sth.objectid = t.oldid)
 where sth.objecttype = 'elderlyPeople' and exists (select 1 from elderlypeople where id = sth.objectid);
 
create table Dm_elderlypeople_Temp_b as select * from Dm_elderlypeople_Temp;
update Dm_elderlypeople_Temp dmt
   set dmt.inid =
       (select newid from elderlypeopletemp t where dmt.inid = t.oldid)
 where dmt.inid is not null and exists (select 1 from elderlypeople sn where dmt.inid = sn.id);

commit;
