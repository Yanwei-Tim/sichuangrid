--清洗脚本户籍人口人房关系
delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5116) and h.populationid like '5116%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5101) and h.populationid like '5101%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5103) and h.populationid like '5103%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5104) and h.populationid like '5104%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5105) and h.populationid like '5105%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5106) and h.populationid like '5106%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5107) and h.populationid like '5107%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5108) and h.populationid like '5108%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5109) and h.populationid like '5109%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5110) and h.populationid like '5110%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5111) and h.populationid like '5111%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5113) and h.populationid like '5113%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5114) and h.populationid like '5114%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5115) and h.populationid like '5115%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5117) and h.populationid like '5117%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5118) and h.populationid like '5118%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5119) and h.populationid like '5119%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5120) and h.populationid like '5120%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5132) and h.populationid like '5132%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5133) and h.populationid like '5133%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_5134) and h.populationid like '5134%';
commit;

delete from househasactualpopulation h where h.populationtype='householdStaff' and h.populationid not in(select id from householdstaffs_3301) and h.populationid like '3301%';
commit;


--清洗脚本流动人口人房关系
delete from househasactualpopulation h where h.populationtype='floatingPopulation' and h.populationid not in(select id from floatingpopulations);
commit;

--清洗脚本未落户人房关系
delete from househasactualpopulation h where h.populationtype='unsettledPopulation' and h.populationid not in(select id from unsettledpopulations);
commit;

--清洗境外人员人房关系
delete from househasactualpopulation h where h.populationtype='overseaStaff' and h.populationid not in(select id from overseapersonnel);
commit;