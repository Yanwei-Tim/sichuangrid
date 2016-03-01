--单位场所，分类字典项修改
update propertydicts p set p.displayname = '学校',p.simplepinyin='xx',p.fullpinyin='xuexiao' where p.displayname = '教育'
and p.propertydomainid = (select id from propertydomains pd where pd.domainname = '单位场所分类');
update propertydicts p set p.displayname = '医院',p.simplepinyin='yy',p.fullpinyin='yiyuan' where p.displayname = '医疗卫生'
and p.propertydomainid = (select id from propertydomains pd where pd.domainname = '单位场所分类');
update propertydicts p set p.displayname = '网吧',p.simplepinyin='wb',p.fullpinyin='wangba' where p.displayname = '上网服务场所'
and p.propertydomainid = (select id from propertydomains pd where pd.domainname = '单位场所分类');
commit;

--单位场所权限按钮控制修改
update permissions p set p.modulename = '学校'  where p.modulename = '教育' and p.parentid = (select  id  from permissions p where p.cname = '教育' and p.modulename = '单位');
update permissions p set p.modulename = '医院'  where p.modulename = '医疗卫生' and p.parentid = (select  id  from permissions p where p.cname = '医疗卫生' and p.modulename = '单位');
update permissions p set p.modulename = '网吧'  where p.modulename = '上网服务场所' and p.parentid = (select  id  from permissions p where p.cname = '上网服务场所' and p.modulename = '场所');
commit;
--单位场所权限修改
update permissions p set p.cname = '学校' where p.cname = '教育' and p.modulename = '单位' ;
update permissions p set p.cname = '医院' where p.cname = '医疗卫生' and p.modulename = '单位';
update permissions p set p.cname = '网吧'  where p.cname = '上网服务场所' and p.modulename = '场所';
commit;
--------------------------------------------------------------------------------------------------------
--单位场所数据管理权限修改
update permissions p set p.cname = '学校' where p.cname = '教育' and p.modulename = '单位场所' ;
update permissions p set p.cname = '学校认领',p.modulename='学校' where p.cname = '教育认领' and p.modulename = '教育' ;
update permissions p set p.cname = '学校导入',p.modulename='学校' where p.cname = '教育导入' and p.modulename = '教育' ;

update permissions p set p.cname = '医院' where p.cname = '医疗卫生' and p.modulename = '单位场所';
update permissions p set p.cname = '医院导入',p.modulename = '医院' where p.cname = '医疗卫生导入' and p.modulename = '医疗卫生';
update permissions p set p.cname = '医院认领',p.modulename = '医院' where p.cname = '医疗卫生认领' and p.modulename = '医疗卫生';

update permissions p set p.cname = '网吧'  where p.cname = '上网服务场所' and p.modulename = '单位场所';
update permissions p set p.cname = '网吧导入',p.modulename = '网吧'  where p.cname = '上网服务场所导入' and p.modulename = '上网服务场所';
update permissions p set p.cname = '网吧认领',p.modulename = '网吧'   where p.cname = '上网服务场所认领' and p.modulename = '上网服务场所';

update permissions p set p.modulename = '学校'  where p.modulename = '教育';
update permissions p set p.modulename = '医院'  where p.modulename = '医疗卫生';
update permissions p set p.modulename = '网吧'  where p.modulename = '上网服务场所';
commit;

--新单位场所临时的景区管理权限删除
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='newScenicManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='newScenicManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='newScenicManagement')
　　connect by prior p.id =  p.parentid
);
commit;

--旧重点场所景区管理权限迁移至单位场所下
update permissions p set p.modulename='单位场所系统',p.parentid=(select id from permissions ps where ps.ename ='newCompanyPlaceManageSystem') where p.ename ='scenicManagement';
commit;


--重点场所系统删除
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='orgLocationManageSystem')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='orgLocationManageSystem')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='orgLocationManageSystem')
　　connect by prior p.id =  p.parentid
);
commit;

------单位场所删除多余导入权限
delete from rolehaspermissions where permissionid in (select id from permissions where ename in
('newImportCompanyPlace', 'newImportSafetyProductionKey', 'newImportFireSafetyKey', 'newImportSecurityKey', 'newImportPollutionSource', 'newImportEnterprise', 'newImportEnterpriseDown'));

delete from moduelclickcounts where permissionid in (select id from permissions where ename in
('newImportCompanyPlace', 'newImportSafetyProductionKey', 'newImportFireSafetyKey', 'newImportSecurityKey',  'newImportPollutionSource', 'newImportEnterprise','newImportEnterpriseDown'));

delete from permissions where id in (select id from permissions  where ename in
('newImportCompanyPlace', 'newImportSafetyProductionKey', 'newImportFireSafetyKey', 'newImportSecurityKey', 'newImportPollutionSource', 'newImportEnterprise','newImportEnterpriseDown'));
commit;


