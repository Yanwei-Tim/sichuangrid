--alter table houseHasImportantPopulation ADD (flag number(2) default 0);
--alter table househasactualpopulation ADD (flag number(2) default 0);

--create index Idx_Important_flag on houseHasImportantPopulation (HOUSEID, flag);
--create index Idx_actual_flag on househasactualpopulation (HOUSEID, flag);


alter table houseHasActualPopulation modify HOUSEID NUMBER(15);
alter table houseHasImportantPopulation modify HOUSEID NUMBER(15);
alter table rentalhouse modify HOUSEID NUMBER(15);

create table househasactualpopulation_bak03 as select * from househasactualpopulation;
create table houseHasImportantPopulation_bk as select * from houseHasImportantPopulation;	
create table rentalhouse_bak03 as select * from rentalhouse;
	

create table houseinfotemp as
   select id as oldID,substr((select o.departmentno
      from organizations o
            where hs.orgid = o.id),0,4) || id as newId,
               hs.orgid as orgId
          from houseinfo hs; 
alter table HOUSEINFOTEMP add constraint PRIMARY_HousetempID primary key (OLDID, NEWID);


	-------1
	create table houseinfo_5116 as
       select * from houseinfo 
   where orginternalcode like '.1.1.%';
   
   -----2
   create table houseinfo_5101 as
       select * from houseinfo 
   where orginternalcode like '.1.2.%';
  
   
   ----3
     create table houseinfo_5103 as
       select * from houseinfo 
   where orginternalcode like '.1.3.%';
  
   
   -----4
     create table houseinfo_5104 as
       select * from houseinfo 
   where orginternalcode like '.1.4.%';
   
   ---5
     create table houseinfo_5105 as  
       select * from houseinfo 
   where orginternalcode like '.1.5.%';
   
   ----6
    create table  houseinfo_5106 as 
       select * from houseinfo 
   where orginternalcode like '.1.6.%';
   
   
   ----7
     create table houseinfo_5107 as
       select * from houseinfo 
   where orginternalcode like '.1.7.%';
   
   ---8
  create table houseinfo_5108 as
       select * from houseinfo 
   where orginternalcode like '.1.8.%';
   
   ---9
   create table houseinfo_5109 as
       select * from houseinfo 
   where orginternalcode like '.1.9.%';

   
   ----10
    create table houseinfo_5110 as
       select * from houseinfo 
   where orginternalcode like '.1.10.%';
   
   ----11
    create table houseinfo_5111 as
       select * from houseinfo 
   where orginternalcode like '.1.11.%';

   
   ---12
     create table houseinfo_5113 as
       select * from houseinfo 
   where orginternalcode like '.1.12.%';
   
   ---13
     create table houseinfo_5114 as
       select * from houseinfo 
   where orginternalcode like '.1.13.%';
   
   
   --14
    create table houseinfo_5115 as
       select * from houseinfo 
   where orginternalcode like '.1.14.%';
   
   
   --15
    create table houseinfo_5117 as 
       select * from houseinfo 
   where orginternalcode like '.1.15.%';
   
      
   --16
    create table houseinfo_5118 as
       select * from houseinfo 
   where orginternalcode like '.1.16.%';
   
        
   --17
    create table houseinfo_5119 as 
       select * from houseinfo 
   where orginternalcode like '.1.17.%';
   
   
   --18
     create table houseinfo_5120 as
       select * from houseinfo 
   where orginternalcode like '.1.18.%';
   
   
   --19
   create table houseinfo_5132 as 
       select * from houseinfo 
   where orginternalcode like '.1.19.%';
   
   
   ---20
     create table houseinfo_5133 as
       select * from houseinfo 
   where orginternalcode like '.1.20.%';
   
   
   --21
     create table houseinfo_5134 as
       select * from houseinfo 
   where orginternalcode like '.1.21.%';
   
   
   ---22
     create table houseinfo_3301 as
       select * from houseinfo 
   where orginternalcode like '.2.1.%';
   
   
alter table houseinfo_5116 modify id NUMBER(15); 
alter table houseinfo_5101 modify id NUMBER(15); 
alter table houseinfo_5103 modify id NUMBER(15); 
alter table houseinfo_5104 modify id NUMBER(15); 
alter table houseinfo_5105 modify id NUMBER(15); 
alter table houseinfo_5106 modify id NUMBER(15); 
alter table houseinfo_5107 modify id NUMBER(15); 
alter table houseinfo_5108 modify id NUMBER(15); 
alter table houseinfo_5109 modify id NUMBER(15); 
alter table houseinfo_5110 modify id NUMBER(15); 
alter table houseinfo_5111 modify id NUMBER(15); 
alter table houseinfo_5113 modify id NUMBER(15); 
alter table houseinfo_5114 modify id NUMBER(15); 
alter table houseinfo_5115 modify id NUMBER(15); 
alter table houseinfo_5117 modify id NUMBER(15); 
alter table houseinfo_5118 modify id NUMBER(15); 
alter table houseinfo_5119 modify id NUMBER(15); 
alter table houseinfo_5120 modify id NUMBER(15); 
alter table houseinfo_5132 modify id NUMBER(15); 
alter table houseinfo_5133 modify id NUMBER(15); 
alter table houseinfo_5134 modify id NUMBER(15); 
alter table houseinfo_3301 modify id NUMBER(15); 
   