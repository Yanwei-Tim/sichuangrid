----删除研判分析重点单位场所权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='importantLocaltionStat_stat')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='importantLocaltionStat_stat')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='importantLocaltionStat_stat')
　　connect by prior p.id =  p.parentid
);

------删除研判分析实有单位权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='actualCompany_stat')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='actualCompany_stat')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='actualCompany_stat')
　　connect by prior p.id =  p.parentid
);
------删除研判分析原有企业权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='enterpriseStat_stat')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='enterpriseStat_stat')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='enterpriseStat_stat')
　　connect by prior p.id =  p.parentid
);
-----删除研判分析总况下的重点单位场所权限
delete from rolehaspermissions where permissionid in (
	 select id from permissions where ename='importPlaceGeneralCondition'
);
delete from moduelclickcounts where permissionid in (
	 select id from permissions where ename='importPlaceGeneralCondition'
);
delete from permissions where id in (
	 select id from permissions where ename='importPlaceGeneralCondition'
);

------研判分析总况加入单位场所
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'单位场所', 'companyPlaceCondition', 1, '总况', 1, ' ', (select id from permissions where ename='generalCondition'),'/hotModuel/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=CompanyPlace',4);
commit;

----删除考核评估辖区考核权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='popedomEvaluate')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='popedomEvaluate')
　　connect by prior p.id =  p.parentid
);

update permissions set enable=0 where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='popedomEvaluate')
　　connect by prior p.id =  p.parentid
);

----删除考核评估我的考核权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='higherEvaluate')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='higherEvaluate')
　　connect by prior p.id =  p.parentid
);

update permissions set enable=0 where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='higherEvaluate')
　　connect by prior p.id =  p.parentid
);
----删除考核评估考核标准管理权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='standardEvaluateManage')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='standardEvaluateManage')
　　connect by prior p.id =  p.parentid
);

update permissions set enable=0 where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='standardEvaluateManage')
　　connect by prior p.id =  p.parentid
);
----删除外键关联的表的数据
delete from workingRecords where dailyDirectoryId in (  select p.id from DailyDirectorys p
　　start with id in(select id from DailyDirectorys where shortname='报表上报')
　　connect by prior p.id =  p.parentid) ;
------删除日常办公所有的报表上报
delete from DailyDirectorys where id in (
   select p.id from DailyDirectorys p
　　start with id in(select id from DailyDirectorys where shortname='报表上报')
　　connect by prior p.id =  p.parentid
);


----删除日常办公辖区报表上报统计权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='statementsDirectoryManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='statementsDirectoryManagement')
　　connect by prior p.id =  p.parentid
);

update permissions set enable=0 where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='statementsDirectoryManagement')
　　connect by prior p.id =  p.parentid
);
commit;