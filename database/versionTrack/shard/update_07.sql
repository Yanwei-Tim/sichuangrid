--create table househasactualpopulation_2015 as select * from HOUSEHASACTUALPOPULATION;
--create table populationtypes_2015 as select * from POPULATIONTYPES;
--create table relatepersons_2015 as select * from RELATEPERSONS;
--create table propertydicts_2015 as select * from PROPERTYDICTS;
--create table issuesteps_2015 as select * from ISSUESTEPS;


create table householdstafftemp_second as
   select id as oldID,substr((select o.departmentno
      from organizations o
            where hs.orgid = o.id),0,4) || id as newId,
               hs.orgid as orgId
          from householdstaffs hs; 
alter table householdstafftemp_second add constraint PRIMARYID_second primary key (OLDID, NEWID);


	
	delete from   populationtypes where  ACTUALTYPE='householdStaff' 
	and ACTUALID not in (select oldId from householdstafftemp_second);
	commit;
	 
	---清洗populationtypes的ACTUALID
     update populationtypes h set h.ACTUALID=(select newid from householdstafftemp_second t where h.ACTUALID=t.oldid )
	 where  ACTUALTYPE='householdStaff';
     commit;
 