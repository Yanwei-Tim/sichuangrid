--创建中间表
create table ISNOTEMPHASISPOPULATION(
  POPULATIONTYPE VARCHAR2(100) not null,
  POPULATIONID   NUMBER(10) not null
);

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5116 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5101 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5103 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5104 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5105 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5106 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5107 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5108 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5109 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5110 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5111 h where h.logout=1);
commit;
insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5113 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5114 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5115 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5117 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5118 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5119 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5120 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5132 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5133 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_5134 h where h.logout=1);
commit;

insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'householdStaff',id from householdstaffs_3301 h where h.logout=1);
commit;

---------------------------------------------------流口
insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'floatingPopulation',id from floatingpopulations h where h.logout=1);
commit;


---------------------------未落户
insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'unsettledPopulation',id from unsettledpopulations h where h.logout=1);
commit;


---------------------------境外
insert into ISNOTEMPHASISPOPULATION (POPULATIONTYPE,POPULATIONID)
(select 'overseaStaff',id from overseapersonnel h where h.logout=1);
commit;



alter table houseinfo_5116 add MemberNum NUMBER(5) default 0;
update houseinfo_5116 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5101 add MemberNum NUMBER(5) default 0;
update houseinfo_5101 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5103 add MemberNum NUMBER(5) default 0;
update houseinfo_5103 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5104 add MemberNum NUMBER(5) default 0;
update houseinfo_5104 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5105 add MemberNum NUMBER(5) default 0;
update houseinfo_5105 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5106 add MemberNum NUMBER(5) default 0;
update houseinfo_5106 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5107 add MemberNum NUMBER(5) default 0;
update houseinfo_5107 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5108 add MemberNum NUMBER(5) default 0;
update houseinfo_5108 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5109 add MemberNum NUMBER(5) default 0;
update houseinfo_5109 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5110 add MemberNum NUMBER(5) default 0;
update houseinfo_5110 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5111 add MemberNum NUMBER(5) default 0;
update houseinfo_5111 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5113 add MemberNum NUMBER(5) default 0;
update houseinfo_5113 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5114 add MemberNum NUMBER(5) default 0;
update houseinfo_5114 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5115 add MemberNum NUMBER(5) default 0;
update houseinfo_5115 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5117 add MemberNum NUMBER(5) default 0;
update houseinfo_5117 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5118 add MemberNum NUMBER(5) default 0;
update houseinfo_5118 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5119 add MemberNum NUMBER(5) default 0;
update houseinfo_5119 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5120 add MemberNum NUMBER(5) default 0;
update houseinfo_5120 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5132 add MemberNum NUMBER(5) default 0;
update houseinfo_5132 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5133 add MemberNum NUMBER(5) default 0;
update houseinfo_5133 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_5134 add MemberNum NUMBER(5) default 0;
update houseinfo_5134 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;

alter table houseinfo_3301 add MemberNum NUMBER(5) default 0;
update houseinfo_3301 t set t.MemberNum = (select count(1) from househasactualpopulation  h where  h.populationid not in (select populationid from isnotemphasispopulation i where i.populationtype = h.populationtype) and h.houseid = t.id );
commit;