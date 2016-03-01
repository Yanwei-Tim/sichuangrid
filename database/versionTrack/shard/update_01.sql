alter table househasactualpopulation modify POPULATIONID NUMBER(15);
alter table populationtypes modify ACTUALID NUMBER(15);
alter table recoverdatainfos  modify businessid  NUMBER(15);
alter table systemoperatelogs  modify dataid  NUMBER(15);
alter table GroupFamilyHasPopulation  modify populationid NUMBER(15);

create table householdstafftemp as
   select id as oldID,substr((select o.departmentno
      from organizations o
            where hs.orgid = o.id),0,4) || id as newId,
               hs.orgid as orgId
          from householdstaffs hs where hs.id<=75275215; 
alter table HOUSEHOLDSTAFFTEMP add constraint PRIMARYID primary key (OLDID, NEWID);



	-------1
	create table householdstaffs_5116 as
       select * from householdstaffs 
   where orginternalcode like '.1.1.%';
   
   -----2
   create table householdstaffs_5101 as
       select * from householdstaffs 
   where orginternalcode like '.1.2.%';
  
   
   ----3
     create table householdstaffs_5103 as
       select * from householdstaffs 
   where orginternalcode like '.1.3.%';
  
   
   -----4
     create table householdstaffs_5104 as
       select * from householdstaffs 
   where orginternalcode like '.1.4.%';
   
   ---5
     create table householdstaffs_5105 as  
       select * from householdstaffs 
   where orginternalcode like '.1.5.%';
   
   ----6
    create table  householdstaffs_5106 as 
       select * from householdstaffs 
   where orginternalcode like '.1.6.%';
   
   
   ----7
     create table householdstaffs_5107 as
       select * from householdstaffs 
   where orginternalcode like '.1.7.%';
   
   ---8
  create table householdstaffs_5108 as
       select * from householdstaffs 
   where orginternalcode like '.1.8.%';
   
   ---9
   create table householdstaffs_5109 as
       select * from householdstaffs 
   where orginternalcode like '.1.9.%';

   
   ----10
    create table householdstaffs_5110 as
       select * from householdstaffs 
   where orginternalcode like '.1.10.%';
   
   ----11
    create table householdstaffs_5111 as
       select * from householdstaffs 
   where orginternalcode like '.1.11.%';

   
   ---12
     create table householdstaffs_5113 as
       select * from householdstaffs 
   where orginternalcode like '.1.12.%';
   
   ---13
     create table householdstaffs_5114 as
       select * from householdstaffs 
   where orginternalcode like '.1.13.%';
   
   
   --14
    create table householdstaffs_5115 as
       select * from householdstaffs 
   where orginternalcode like '.1.14.%';
   
   
   --15
    create table householdstaffs_5117 as 
       select * from householdstaffs 
   where orginternalcode like '.1.15.%';
   
      
   --16
    create table householdstaffs_5118 as
       select * from householdstaffs 
   where orginternalcode like '.1.16.%';
   
        
   --17
    create table householdstaffs_5119 as 
       select * from householdstaffs 
   where orginternalcode like '.1.17.%';
   
   
   --18
     create table householdstaffs_5120 as
       select * from householdstaffs 
   where orginternalcode like '.1.18.%';
   
   
   --19
   create table householdstaffs_5132 as 
       select * from householdstaffs 
   where orginternalcode like '.1.19.%';
   
   
   ---20
     create table householdstaffs_5133 as
       select * from householdstaffs 
   where orginternalcode like '.1.20.%';
   
   
   --21
     create table householdstaffs_5134 as
       select * from householdstaffs 
   where orginternalcode like '.1.21.%';
   
   
   ---22
     create table householdstaffs_3301 as
       select * from householdstaffs 
   where orginternalcode like '.2.1.%';
   
   
alter table householdstaffs_5116 modify id NUMBER(15); 
alter table householdstaffs_5101 modify id NUMBER(15); 
alter table householdstaffs_5103 modify id NUMBER(15); 
alter table householdstaffs_5104 modify id NUMBER(15); 
alter table householdstaffs_5105 modify id NUMBER(15); 
alter table householdstaffs_5106 modify id NUMBER(15); 
alter table householdstaffs_5107 modify id NUMBER(15); 
alter table householdstaffs_5108 modify id NUMBER(15); 
alter table householdstaffs_5109 modify id NUMBER(15); 
alter table householdstaffs_5110 modify id NUMBER(15); 
alter table householdstaffs_5111 modify id NUMBER(15); 
alter table householdstaffs_5113 modify id NUMBER(15); 
alter table householdstaffs_5114 modify id NUMBER(15); 
alter table householdstaffs_5115 modify id NUMBER(15); 
alter table householdstaffs_5117 modify id NUMBER(15); 
alter table householdstaffs_5118 modify id NUMBER(15); 
alter table householdstaffs_5119 modify id NUMBER(15); 
alter table householdstaffs_5120 modify id NUMBER(15); 
alter table householdstaffs_5132 modify id NUMBER(15); 
alter table householdstaffs_5133 modify id NUMBER(15); 
alter table householdstaffs_5134 modify id NUMBER(15); 
alter table householdstaffs_3301 modify id NUMBER(15); 
   

