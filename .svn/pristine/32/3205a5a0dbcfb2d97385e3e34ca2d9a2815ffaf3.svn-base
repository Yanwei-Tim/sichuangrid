---  实有人口  老年人  配偶情况选项中,'个体生产' 改为 '自由职业'
update propertydicts set displayname = '自由职业', simplepinyin = 'zyzy', fullpinyin = 'ziyouzhiye'
       where propertydomainid = (select id from propertydomains where domainname = '配偶情况')
       and displayname = '个体生产';
---  实有人口  老年人  目前情况选项中,'欢度晚年' 改为 '继续工作', '料理家务' 改为 '在家休养', '个体开业' 改为 '个体经营'
update propertydicts set displayname = '继续工作', simplepinyin = 'jxgz', fullpinyin = 'jixugongzuo'
       where propertydomainid = (select id from propertydomains where domainname = '目前情况')
       and displayname = '欢度晚年';
update propertydicts set displayname = '在家休养', simplepinyin = 'zjxy', fullpinyin = 'zaijiaxiuyang'
       where propertydomainid = (select id from propertydomains where domainname = '目前情况')
       and displayname = '料理家务';
update propertydicts set displayname = '个体经营', simplepinyin = 'gtjy', fullpinyin = 'getijingying'
       where propertydomainid = (select id from propertydomains where domainname = '目前情况')
       and displayname = '个体开业';
---  实有人口  失业人员  失业原因选项中,'刑满释放或假释、监外执行的' 改为 '刑满释放'
---  新增选项  '假释、缓刑、暂予监外外执行、管制、剥夺政治权利等社区矫正人员'
update propertydicts set displayname = '刑满释放', simplepinyin = 'xmsf', fullpinyin = 'xingmanshifang'
       where propertydomainid = (select id from propertydomains where domainname = '失业原因')
       and displayname = '刑满释放或假释、监外执行的';
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where domainname = '失业原因'), 13, 13, 
       '假释、缓刑、暂予监外外执行、管制、剥夺政治权利等社区矫正人员',  'jshxzyjwzx', 'jishihuanxingzanyuji', 'admin', '', sysdate, null);

---  实有房屋  实有房屋  房屋来源选项中的租赁公房选项添加子项 '个人私有'
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where domainname = '租赁公房'), 0, 3,
       '个人私有',  'grsy', 'gerensiyou', 'admin', '', sysdate, null);

---------------------------------------------
-----------------------------------------------

-- update permissionConfig.xml
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationManagement';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationManagement');
update permissions set modulename = '失业人员' where ename = 'transferUnemployedPeople';
update permissions set modulename = '育妇' where ename = 'transferNurturesWomen';
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationIntegrativeQuery';
update permissions set cname = '需救助人员' where ename = 'sapAidNeedPopulation';
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationGis';
-- update permissionConfig_xncx.xml
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationStatistic';
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationManagement';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationManagement');
-- update permissionConfig_yfd.xml
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationManagement';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationManagement');
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationStatistic';
-- update dataManagePermissionInit.xml
update permissions set cname = '需救助人员' where ename = 'aidNeedPopulationTemp';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationTemp');
update permissions set cname = '需救助人员导入' where ename = 'aidNeedPopulationTempImport';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationTempImport');
update permissions set cname = '需救助人员认领' where ename = 'aidNeedPopulationclaim';
update permissions set modulename = '需救助人员' 
       where parentid = (select id from permissions where ename = 'aidNeedPopulationclaim');
-- update analyzing_plugin_permissionConfig.xml, analyzingPermissionInit_1.xml, analyzingPermissionInit.xml
update permissions set cname = '需救助人员认领' where ename = 'aidNeedPopulationStatistic';

commit;