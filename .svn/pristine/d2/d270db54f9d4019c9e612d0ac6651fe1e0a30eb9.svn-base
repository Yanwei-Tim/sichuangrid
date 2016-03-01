
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '任务清单统计表', 'taskListReportForm', 1, '任务清单', 1, (select id from permissions where ename='taskListVisitManagement'), null, null, null, 6, null);  

update permissions set PARENTID = (select id from permissions where ename='taskListReportForm' ),indexid=0, modulename='报表',
NORMALURL='/hotModuel/task/reportForm/taskVisitList.ftl'
  where ename='tasksListsVisitManagement';


insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'流动人口统计表','floatingPopulationVisitManagement',1,'任务清单统计表',1,(select id from permissions where ename = 'taskListReportForm'),
   '','/hotModuel/task/reportForm/floatingPopulationVisitList.ftl','', 1,'');
  
   insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'特殊人群统计表','specialGroupVisitManagement',1,'任务清单统计表',1,(select id from permissions where ename = 'taskListReportForm'),
   '','/hotModuel/task/reportForm/specialGroupVisitList.ftl','', 2,'');
   
    insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'发现治安隐患统计表','hiddenDangerVisitManagement',1,'任务清单统计表',1,(select id from permissions where ename = 'taskListReportForm'),
   '','/hotModuel/task/reportForm/hiddenDangerVisitList.ftl','', 3,'');