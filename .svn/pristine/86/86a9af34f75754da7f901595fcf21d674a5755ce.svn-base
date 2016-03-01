--添加 出租房住户管理 权限
--insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
--values (s_permissions.NEXTVAL,'住户管理', 'mantanceRentalHousePopulation', 0, '出租房', 1, ' ', (select id from permissions where ename='rentalHouseManagement'),13);
------------删除单位场所  管理治安负责人、管理巡场情况 权限前 删除其对应的角色中间表数据-------------------
--删除 单位场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newCompanyPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newCompanyPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 公共活动场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPublicPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPublicPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 交通场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTrafficPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTrafficPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 娱乐场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEntertainmentPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEntertainmentPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 商贸场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTradePlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTradePlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 上网服务场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newInternetServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newInternetServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 住宿服务场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newAccommodationServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newAccommodationServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 餐饮服务场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newFoodServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newFoodServicesPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 旅游场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTravelingPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newTravelingPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 施工场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newConstructionPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newConstructionPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 其他场所 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newOtherPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newOtherPlaceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 党政机关 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPartyGovernmentOrganCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPartyGovernmentOrganCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 教育 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEducationCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEducationCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 医疗卫生 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newMedicalHygieneCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newMedicalHygieneCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 危化品存放单位 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newDangerousStoreCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newDangerousStoreCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 其他单位 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newOtherCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newOtherCompanyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 安全生产重点 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newSafetyProductionKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newSafetyProductionKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 消防安全重点 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newFireSafetyKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newFireSafetyKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 治安重点 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newSecurityKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newSecurityKeyManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 污染源 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPollutionSourceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newPollutionSourceManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 规上企业 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEnterpriseManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEnterpriseManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
--删除 规下企业 管理治安负责人、管理巡场情况 权限
delete from rolehaspermissions where permissionid in(select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEnterpriseDownManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
delete permissions where id in (select p1.id from permissions p1,permissions p2 where p1.parentid=p2.id and p2.ename='newEnterpriseDownManagement' and p1.cname in('管理治安负责人','管理巡场情况'));
commit;