--户籍地修改脚本
--update permanentaddress set addressname = '巴音郭楞蒙古自治州' where addressno='652800';
--update permanentaddress set addressname = '南川区' where addressno='500384';
--update permanentaddress set addressname = '合川区' where addressno='500382';
--update permanentaddress set addressname = '永川区' where addressno='500383';
--update permanentaddress set addressname = '长寿区' where addressno='500221';
--update permanentaddress set addressname = '滨州市' where addressno='372300';
--update permanentaddress set addressname = '菏泽市' where addressno='372900';
insert into permanentaddress(addressno,addressname,parentid,addresslevel) values('659101','阿拉尔市','659100','3');
insert into permanentaddress(addressno,addressname,parentid,addresslevel) values('513827','岷东新区','513800','3');
commit;

----更改系统导航的ENAME
----有系统两个字都去掉  实有人口要改为人口信息    实有房屋改为房屋信息  屏蔽掉：服务审批 应急指挥 社情民意
update permissions p set p.cname = '人口信息' where p.ename = 'peopleManageSystem';
update permissions p set p.cname = '房屋信息' where p.ename = 'houseManageSystem';
update permissions p set p.cname = '单位场所' where p.ename = 'newCompanyPlaceManageSystem';
update permissions p set p.cname = '事件处理' where p.ename = 'serviceWork';
update permissions p set p.cname = '服务审批' where p.ename = 'approvalManageSystem';
update permissions p set p.cname = '应急指挥' where p.ename = 'incidentSystem';
update permissions p set p.cname = '城市管理' where p.ename = 'digitalCityManagement';
update permissions p set p.cname = '互动交流' where p.ename = 'interactionManagement';
update permissions p set p.cname = '考核评估' where p.ename = 'evaluateManagement';
update permissions p set p.cname = '系统管理' where p.ename = 'systemManagement';
update permissions p set p.cname = '事件对接' where p.ename = 'issueAbutmentJointManagement';
update permissions p set p.cname = '数据管理' where p.ename = 'dataManagePluginManagement';
update permissions p set p.cname = '社情民意' where p.ename = 'commandCenterManagement';
commit;

----将“刑释解教人员”改为“刑释人员” 将“实有人口”改为“人口信息” 将“实有房屋”改为“房屋信息”
update permissions p set p.cname = '刑释人员' where p.ename = 'positiveInfoManagement';
update permissions p set p.modulename = '刑释人员' where p.parentid = (select p.id from permissions p where p.ename = 'positiveInfoManagement');
update permissions p set p.cname = '刑释人员镇级领导视图' where p.ename = 'POSITIVEINFOTownLeaderView';
update permissions p set p.cname = '刑释人员社区级领导视图' where p.ename = 'POSITIVEINFOVillageLeaderView';
update permissions p set p.cname = '刑释人员网格级领导视图' where p.ename = 'POSITIVEINFOGridLeaderView';

update permissions p set p.cname = '刑释人员' where p.ename = 'positiveInfoTemp';
update permissions p set p.cname = '刑释人员导入',p.modulename = '刑释人员' where p.ename = 'positiveInfoTempImport';
update permissions p set p.modulename = '刑释人员导入' where p.parentid = (select p.id from permissions p where p.ename = 'positiveInfoTempImport');
update permissions p set p.cname = '刑释人员认领',p.modulename = '刑释人员' where p.ename = 'positiveInfoTempclaim';
update permissions p set p.modulename = '刑释人员认领' where p.parentid = (select p.id from permissions p where p.ename = 'positiveInfoTempclaim');

update permissions p set p.cname = '刑释人员' where p.ename = 'sapPositiveInfo'; 
update permissions p set p.cname = '刑释人员' where p.ename = 'positiveInfoIntegrativeQuery'; 
update permissions p set p.cname = '刑释人员' where p.ename = 'positiveinfoGis'; 

update permissions p set p.cname = '人员信息' where p.ename = 'populationGis';
update permissions p set p.modulename = '人员信息' where p.parentid = (select p.id from permissions p where p.ename = 'populationGis');
update permissions p set p.cname = '房屋信息' where p.ename = 'importantHouseGis'; 
update permissions p set p.modulename = '房屋信息' where p.parentid = (select p.id from permissions p where p.ename = 'importantHouseGis');

update permissions p set p.cname = '统计分析-人口信息' where p.ename = 'statAnalyseactualPopulation'; 
update permissions p set p.cname = '总况-人口信息' where p.ename = 'sapActualPopulation'; 
update permissions p set p.modulename = '统计分析-人口信息' where p.parentid = (select p.id from permissions p where p.ename = 'statAnalyseactualPopulation');

update permissions p set p.cname = '人口信息' where p.ename = 'actualPopulation';
update permissions p set p.cname = '刑释人员' where p.ename = 'positiveinfoStatistic';
update permissions p set p.cname = '人口信息' where p.ename = 'actualPopulationDataManagement';
update permissions p set p.modulename = '人口信息' where p.parentid = (select p.id from permissions p where p.ename = 'actualPopulationDataManagement');
update permissions p set p.cname = '房屋信息' where p.ename = 'actualHouseDataManagement';
update permissions p set p.cname = '房屋信息',p.modulename = '房屋信息' where p.ename = 'actualHouseTemp';
update permissions p set p.modulename = '房屋信息' where p.parentid = (select p.id from permissions p where p.ename = 'actualHouseTemp');
update permissions p set p.cname = '房屋信息导入' where p.ename = 'actualHouseTempImport';
update permissions p set p.modulename = '房屋信息导入' where p.parentid = (select p.id from permissions p where p.ename = 'actualHouseTempImport');
update permissions p set p.cname = '房屋信息认领' where p.ename = 'actualHouseTempclaim';
update permissions p set p.modulename = '房屋信息认领' where p.parentid = (select p.id from permissions p where p.ename = 'actualHouseTempclaim');
update permissions p set p.modulename = '房屋信息' where p.parentid = (select p.id from permissions p where p.ename = 'actualHouseDataManagement');

update permissions p set p.cname = '房屋信息' where p.ename = 'sapActualHouse';
update permissions p set p.cname = '房屋信息' where p.ename = 'actualHouseManagement';
update permissions p set p.modulename = '房屋信息' where p.parentid = (select p.id from permissions p where p.ename = 'actualHouseManagement');
update permissions p set p.cname = '房屋信息镇级领导视图' where p.ename = 'ACTUALHOUSETownLeaderView';
update permissions p set p.cname = '房屋信息社区级领导视图' where p.ename = 'ACTUALHOUSEVillageLeaderView';
update permissions p set p.cname = '房屋信息网格级领导视图' where p.ename = 'ACTUALHOUSEGridLeaderView';
update permissions p set p.cname = '房屋信息' where p.ename = 'actualHouseStatistic';
update permissions p set p.cname = '人口信息' where p.ename = 'populationStat_stat';
update permissions p set p.cname = '人口信息' where p.ename = 'importPopulationCondition';
comment on table POSITIVEINFOS is '刑释人员';
comment on column societySafeCheck.hitArrestLiberate is '打击整治目录下的抓获犯罪嫌疑人目录下的刑释人员';
commit;

update permissions p set p.cname='户籍人口' where p.ename='householdStaffStatistic';
update permissions p set p.cname='流动人口' where p.ename='floatingPopulationStatistic';
update permissions p set p.cname='*未落户人口' where p.ename='unsettedPopulationStatistic';
update permissions p set p.cname='*未落户人口' where p.ename='unsettedPopulationGis';
update permissions p set p.cname='*未落户人口' where p.ename='unsettledPopulationTemp';
update permissions p set p.cname='未落户人口导入',p.modulename='未落户人口' where p.ename='unsettledPopulationTempImport';

update permissions p set p.modulename='未落户人口' where p.ename='importSearchUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='importAdvancedSearchUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='importUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='importViewUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='importDeleteUnsettledPopulationTemp';
update permissions p set p.cname='未落户人口认领',p.modulename='未落户人口' where p.ename='unsettledPopulationTempclaim';
update permissions p set p.modulename='未落户人口' where p.ename='claimSearchUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='claimAdvancedSearchUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='claimViewUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='claimUpdateUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='claimUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='stepClaimUnsettledPopulationTemp';
update permissions p set p.modulename='未落户人口' where p.ename='unDoClaimUnsettledPopulationTemp';
commit;
 --删除20140630前数据恢复中心数据
 delete Recoverdatainfos r where  r.createdate <= to_date('2014-6-30','yyyy-MM-dd');
 
 commit;

 --房屋认领表 添加字段
alter table DM_HOUSEINFO_TEMP add IMGURL VARCHAR2(300);
comment on column DM_HOUSEINFO_TEMP.IMGURL is '房屋图片';

