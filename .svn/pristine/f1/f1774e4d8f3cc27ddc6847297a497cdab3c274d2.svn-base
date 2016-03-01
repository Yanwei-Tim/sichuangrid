insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='终端类别'), 0, 6, '四川省', 'scs', 'sichuansheng', 'admin',sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='终端类别'), 0, 7, '四川省旧版', 'scsjb', 'sichuanshengjiuban', 'admin',sysdate);

--实有房屋中楼宇管理：是否有物管字段 
alter  table DM_builddatas_Temp add isPropertyManagement  number(1); 
commit;

----删除数据管理企业模块权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='enterprise')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='enterprise')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='enterprise')
　　connect by prior p.id =  p.parentid
);
commit;