	delete 	from househasactualpopulation where POPULATIONTYPE='householdStaff' 
	and POPULATIONID not in (select oldId from householdstafftemp) and POPULATIONID<=75275215;
	commit;
	
	delete from   populationtypes where  ACTUALTYPE='householdStaff' 
	and ACTUALID not in (select oldId from householdstafftemp) and ACTUALID<=75275215;
	commit;
	 

	---清洗househasactualpopulation的POPULATIONID
     update househasactualpopulation h set h.POPULATIONID=(select newid from householdstafftemp t where h.POPULATIONID=t.oldid )
	 where  POPULATIONTYPE='householdStaff' and POPULATIONID<=75275215;
     commit;
  

	---清洗populationtypes的ACTUALID
     update populationtypes h set h.ACTUALID=(select newid from householdstafftemp t where h.ACTUALID=t.oldid )
	 where  ACTUALTYPE='householdStaff' and ACTUALID<=75275215;
     commit;
 
     
     