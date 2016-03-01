create table nurtureswomentemp as
   select id as oldID,substr((select o.departmentno
      from organizations o
            where nu.orgid = o.id),0,4) || id as newId,
               nu.orgid as orgId
          from nurtureswomen nu
alter table nurtureswomentemp add constraint NURTUREPRIMARYID primary key (OLDID, NEWID);

delete 	from houseHasImportantPopulation_b5 where POPULATIONTYPE='nurturesWomen' 
	and POPULATIONID not in (select oldId from nurtureswomentemp);
commit;
	
declare 
cursor cur is select /*+ parallel(y,8) */ rowid,y.* from houseHasImportantPopulation_b5 y where y.populationtype = 'nurturesWomen';
counter number(10);
begin
counter:=0;
for rec in cur loop
update houseHasImportantPopulation_b5 p set POPULATIONID=(select newid from nurtureswomentemp t where rec.populationid=t.oldid) 
where  p.rowid = rec.rowid ;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;


delete from   populationtypes_bak05 where  populationtype='nurturesWomen' 
	and POPULATIONID not in (select oldId from nurtureswomentemp);
commit;

declare 
cursor cur is select /*+ parallel(y,8) */ rowid,y.* from populationtypes_bak05 y where y.populationtype = 'nurturesWomen';
counter number(10);
begin
counter:=0;
for rec in cur loop
update populationtypes_bak05 p set POPULATIONID=(select newid from nurtureswomentemp t where rec.populationid=t.oldid) 
where  p.rowid = rec.rowid ;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;


create table serviceMemberHasObject_b as select * from serviceMemberHasObject;
update serviceMemberHasObject smh
   set smh.objectid =
       (select newid from nurtureswomentemp t where smh.objectid = t.oldid)
 where smh.objecttype = 'nurturesWomen' and exists (select 1 from nurtureswomen where id = smh.objectid);
 
create table serviceRecordRelyObject_b as select * from serviceRecordRelyObject;
update serviceRecordRelyObject srr
   set srr.objectid =
       (select newid from nurtureswomentemp t where srr.objectid = t.oldid)
 where srr.objecttype = 'nurturesWomen' and exists (select 1 from nurtureswomen where id = srr.objectid);
 
create table Serviceguardershasobject_b as select * from Serviceguardershasobject;
update Serviceguardershasobject sgh
   set sgh.objectid =
       (select newid from nurtureswomentemp t where sgh.objectid = t.oldid)
 where sgh.objecttype = 'nurturesWomen' and exists (select 1 from nurtureswomen where id = sgh.objectid);
 
create table serviceTeamHasObject_b as select * from serviceTeamHasObject;
update serviceTeamHasObject sth
   set sth.objectid =
       (select newid from nurtureswomentemp t where sth.objectid = t.oldid)
 where sth.objecttype = 'nurturesWomen' and exists (select 1 from nurtureswomen where id = sth.objectid);

create table Dm_Nurtureswomen_Temp_b as select * from Dm_Nurtureswomen_Temp;
update Dm_Nurtureswomen_Temp dmt
   set dmt.inid =
       (select newid from nurtureswomentemp t where dmt.inid = t.oldid)
 where dmt.inid is not null and exists (select 1 from nurtureswomen sn where dmt.inid = sn.id);

commit;
