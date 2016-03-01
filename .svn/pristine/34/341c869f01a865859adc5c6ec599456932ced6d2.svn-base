alter table populationtypes modify POPULATIONID NUMBER(15);   --业务人员和实口关联表    38517885  21450416
alter table househasimportantpopulation modify POPULATIONID NUMBER(15);     --业务人员与房屋关联表  5218317  3054513


alter table serviceMemberHasObject modify objectId NUMBER(15);    --服务对象与业务人员关联表  320284
alter table serviceRecordRelyObject modify objectId NUMBER(15);   --服务记录与业务人员关联表   123734

alter table Serviceguardershasobject modify objectId NUMBER(15);   --监护人与业务人员的关联表
alter table serviceTeamHasObject modify objectId NUMBER(15);       --服务团队与业务人员的关联表


alter table Dm_Householdstaffs_Temp modify inid NUMBER(15);
alter table Dm_Houseinfo_Temp modify inid NUMBER(15);
alter table Dm_Elderlypeople_Temp modify inid NUMBER(15);
alter table Dm_Nurtureswomen_Temp modify inid NUMBER(15);


create table houseHasImportantPopulation_b5 as
       select * from houseHasImportantPopulation;
       
create table populationtypes_bak05 as
       select * from populationtypes;


-------1
	create table nurtureswomen_5116 as
       select * from nurtureswomen 
   where orginternalcode like '.1.1.%';
   
-------2
   create table nurtureswomen_5101 as
       select * from nurtureswomen 
   where orginternalcode like '.1.2.%';
  
   
-------3
     create table nurtureswomen_5103 as
       select * from nurtureswomen 
   where orginternalcode like '.1.3.%';
  
   
--------4
     create table nurtureswomen_5104 as
       select * from nurtureswomen 
   where orginternalcode like '.1.4.%';
   
------5
     create table nurtureswomen_5105 as  
       select * from nurtureswomen 
   where orginternalcode like '.1.5.%';
   
-------6
    create table  nurtureswomen_5106 as 
       select * from nurtureswomen 
   where orginternalcode like '.1.6.%';
   
   
-------7
     create table nurtureswomen_5107 as
       select * from nurtureswomen 
   where orginternalcode like '.1.7.%';
   
------8
  create table nurtureswomen_5108 as
       select * from nurtureswomen 
   where orginternalcode like '.1.8.%';
   
------9
   create table nurtureswomen_5109 as
       select * from nurtureswomen 
   where orginternalcode like '.1.9.%';

   
-------10
    create table nurtureswomen_5110 as
       select * from nurtureswomen 
   where orginternalcode like '.1.10.%';
   
-------11
    create table nurtureswomen_5111 as
       select * from nurtureswomen 
   where orginternalcode like '.1.11.%';

------12
     create table nurtureswomen_5113 as
       select * from nurtureswomen 
   where orginternalcode like '.1.12.%';
   
------13
     create table nurtureswomen_5114 as
       select * from nurtureswomen 
   where orginternalcode like '.1.13.%';
   
   
-----14
    create table nurtureswomen_5115 as
       select * from nurtureswomen 
   where orginternalcode like '.1.14.%';
   
   
-----15
    create table nurtureswomen_5117 as 
       select * from nurtureswomen 
   where orginternalcode like '.1.15.%';
   
      
-----16
    create table nurtureswomen_5118 as
       select * from nurtureswomen 
   where orginternalcode like '.1.16.%';
   
        
-----17
    create table nurtureswomen_5119 as 
       select * from nurtureswomen 
   where orginternalcode like '.1.17.%';
   
   
-----18
     create table nurtureswomen_5120 as
       select * from nurtureswomen 
   where orginternalcode like '.1.18.%';
   
   
-----19
   create table nurtureswomen_5132 as 
       select * from nurtureswomen 
   where orginternalcode like '.1.19.%';
   
   
------20
     create table nurtureswomen_5133 as
       select * from nurtureswomen 
   where orginternalcode like '.1.20.%';
   
   
-----21
     create table nurtureswomen_5134 as
       select * from nurtureswomen 
   where orginternalcode like '.1.21.%';
   
   
------22
     create table nurtureswomen_3301 as
       select * from nurtureswomen 
   where orginternalcode like '.2.1.%';
   
   
alter table nurtureswomen_5116 modify id NUMBER(15); 
alter table nurtureswomen_5101 modify id NUMBER(15); 
alter table nurtureswomen_5103 modify id NUMBER(15); 
alter table nurtureswomen_5104 modify id NUMBER(15); 
alter table nurtureswomen_5105 modify id NUMBER(15); 
alter table nurtureswomen_5106 modify id NUMBER(15); 
alter table nurtureswomen_5107 modify id NUMBER(15); 
alter table nurtureswomen_5108 modify id NUMBER(15); 
alter table nurtureswomen_5109 modify id NUMBER(15); 
alter table nurtureswomen_5110 modify id NUMBER(15); 
alter table nurtureswomen_5111 modify id NUMBER(15); 
alter table nurtureswomen_5113 modify id NUMBER(15); 
alter table nurtureswomen_5114 modify id NUMBER(15); 
alter table nurtureswomen_5115 modify id NUMBER(15); 
alter table nurtureswomen_5117 modify id NUMBER(15); 
alter table nurtureswomen_5118 modify id NUMBER(15); 
alter table nurtureswomen_5119 modify id NUMBER(15); 
alter table nurtureswomen_5120 modify id NUMBER(15); 
alter table nurtureswomen_5132 modify id NUMBER(15); 
alter table nurtureswomen_5133 modify id NUMBER(15); 
alter table nurtureswomen_5134 modify id NUMBER(15); 
alter table nurtureswomen_3301 modify id NUMBER(15);


alter table nurtureswomen_5116 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5116 modify ISCANCEL default(0);
alter table nurtureswomen_5116 modify ISDELETE default(0);
alter table nurtureswomen_5116 modify SOURCESSTATE default(1);
alter table nurtureswomen_5116 modify ISEMPHASIS default(0);

alter table nurtureswomen_5101 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5101 modify ISCANCEL default(0);
alter table nurtureswomen_5101 modify ISDELETE default(0);
alter table nurtureswomen_5101 modify SOURCESSTATE default(1);
alter table nurtureswomen_5101 modify ISEMPHASIS default(0);

alter table nurtureswomen_5103 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5103 modify ISCANCEL default(0);
alter table nurtureswomen_5103 modify ISDELETE default(0);
alter table nurtureswomen_5103 modify SOURCESSTATE default(1);
alter table nurtureswomen_5103 modify ISEMPHASIS default(0);

alter table nurtureswomen_5104 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5104 modify ISCANCEL default(0);
alter table nurtureswomen_5104 modify ISDELETE default(0);
alter table nurtureswomen_5104 modify SOURCESSTATE default(1);
alter table nurtureswomen_5104 modify ISEMPHASIS default(0);

alter table nurtureswomen_5105 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5105 modify ISCANCEL default(0);
alter table nurtureswomen_5105 modify ISDELETE default(0);
alter table nurtureswomen_5105 modify SOURCESSTATE default(1);
alter table nurtureswomen_5105 modify ISEMPHASIS default(0);

alter table nurtureswomen_5106 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5106 modify ISCANCEL default(0);
alter table nurtureswomen_5106 modify ISDELETE default(0);
alter table nurtureswomen_5106 modify SOURCESSTATE default(1);
alter table nurtureswomen_5106 modify ISEMPHASIS default(0);

alter table nurtureswomen_5107 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5107 modify ISCANCEL default(0);
alter table nurtureswomen_5107 modify ISDELETE default(0);
alter table nurtureswomen_5107 modify SOURCESSTATE default(1);
alter table nurtureswomen_5107 modify ISEMPHASIS default(0);

alter table nurtureswomen_5108 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5108 modify ISCANCEL default(0);
alter table nurtureswomen_5108 modify ISDELETE default(0);
alter table nurtureswomen_5108 modify SOURCESSTATE default(1);
alter table nurtureswomen_5108 modify ISEMPHASIS default(0);

alter table nurtureswomen_5109 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5109 modify ISCANCEL default(0);
alter table nurtureswomen_5109 modify ISDELETE default(0);
alter table nurtureswomen_5109 modify SOURCESSTATE default(1);
alter table nurtureswomen_5109 modify ISEMPHASIS default(0);

alter table nurtureswomen_5110 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5110 modify ISCANCEL default(0);
alter table nurtureswomen_5110 modify ISDELETE default(0);
alter table nurtureswomen_5110 modify SOURCESSTATE default(1);
alter table nurtureswomen_5110 modify ISEMPHASIS default(0);

alter table nurtureswomen_5111 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5111 modify ISCANCEL default(0);
alter table nurtureswomen_5111 modify ISDELETE default(0);
alter table nurtureswomen_5111 modify SOURCESSTATE default(1);
alter table nurtureswomen_5111 modify ISEMPHASIS default(0);

alter table nurtureswomen_5113 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5113 modify ISCANCEL default(0);
alter table nurtureswomen_5113 modify ISDELETE default(0);
alter table nurtureswomen_5113 modify SOURCESSTATE default(1);
alter table nurtureswomen_5113 modify ISEMPHASIS default(0);

alter table nurtureswomen_5114 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5114 modify ISCANCEL default(0);
alter table nurtureswomen_5114 modify ISDELETE default(0);
alter table nurtureswomen_5114 modify SOURCESSTATE default(1);
alter table nurtureswomen_5114 modify ISEMPHASIS default(0);

alter table nurtureswomen_5115 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5115 modify ISCANCEL default(0);
alter table nurtureswomen_5115 modify ISDELETE default(0);
alter table nurtureswomen_5115 modify SOURCESSTATE default(1);
alter table nurtureswomen_5115 modify ISEMPHASIS default(0);

alter table nurtureswomen_5117 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5117 modify ISCANCEL default(0);
alter table nurtureswomen_5117 modify ISDELETE default(0);
alter table nurtureswomen_5117 modify SOURCESSTATE default(1);
alter table nurtureswomen_5117 modify ISEMPHASIS default(0);

alter table nurtureswomen_5118 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5118 modify ISCANCEL default(0);
alter table nurtureswomen_5118 modify ISDELETE default(0);
alter table nurtureswomen_5118 modify SOURCESSTATE default(1);
alter table nurtureswomen_5118 modify ISEMPHASIS default(0);

alter table nurtureswomen_5119 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5119 modify ISCANCEL default(0);
alter table nurtureswomen_5119 modify ISDELETE default(0);
alter table nurtureswomen_5119 modify SOURCESSTATE default(1);
alter table nurtureswomen_5119 modify ISEMPHASIS default(0);

alter table nurtureswomen_5120 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5120 modify ISCANCEL default(0);
alter table nurtureswomen_5120 modify ISDELETE default(0);
alter table nurtureswomen_5120 modify SOURCESSTATE default(1);
alter table nurtureswomen_5120 modify ISEMPHASIS default(0);

alter table nurtureswomen_5132 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5132 modify ISCANCEL default(0);
alter table nurtureswomen_5132 modify ISDELETE default(0);
alter table nurtureswomen_5132 modify SOURCESSTATE default(1);
alter table nurtureswomen_5132 modify ISEMPHASIS default(0);

alter table nurtureswomen_5133 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5133 modify ISCANCEL default(0);
alter table nurtureswomen_5133 modify ISDELETE default(0);
alter table nurtureswomen_5133 modify SOURCESSTATE default(1);
alter table nurtureswomen_5133 modify ISEMPHASIS default(0);

alter table nurtureswomen_5134 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_5134 modify ISCANCEL default(0);
alter table nurtureswomen_5134 modify ISDELETE default(0);
alter table nurtureswomen_5134 modify SOURCESSTATE default(1);
alter table nurtureswomen_5134 modify ISEMPHASIS default(0);

alter table nurtureswomen_3301 modify ISSIGNCOMPACT default(0);
alter table nurtureswomen_3301 modify ISCANCEL default(0);
alter table nurtureswomen_3301 modify ISDELETE default(0);
alter table nurtureswomen_3301 modify SOURCESSTATE default(1);
alter table nurtureswomen_3301 modify ISEMPHASIS default(0);