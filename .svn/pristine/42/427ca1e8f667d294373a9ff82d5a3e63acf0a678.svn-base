--任务清单研判分析报表job  每月月底晚上11点11分13秒执行 
insert into taskploy(id, cname, ename, type, description, code)values(s_TASKPLOY.nextval,'任务清单研判分析报表job','generalSituationByYeatTypeJob',(select id from propertydicts where displayname = 'java方法'),'任务清单研判分析报表job','generalSituationByYeatTypeJob.addGeneralSituationHistory');
insert into task (id, name, taskgroup, description, ployId, config, closed)values(s_TASK.nextval, 'generalSituationByYeatTypeJob','generalSituationByYeatTypeJob','generalSituationByYeatTypeJob',(select id from taskploy where ename = 'generalSituationByYeatTypeJob'),'13 11 23 L * ?', 1);

--任务清单研判分析权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '任务清单研判分析', 'taskListStatisticsManage', 1, '研判分析', 1, (select id from permissions where ename = 'judgmentAnalysisViewManage'), '', '', '', 8, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '任务清单总览', 'taskListAnalysisManagge', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/taskListAnalysis.ftl', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '流动人口', 'floatSituationManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/floatSituation.ftl', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '民警带领下开展工作', 'policeStatisticsManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/policeStatistics.ftl', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '异常情形报告', 'exceptionStatisticsManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/exceptionStatistics.ftl', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '特殊人群', 'specialPopulationsManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/specialPopulations.ftl', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '治安隐患', 'hiddengerStatisticsManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/task/statistics/hiddengerStatistics.ftl', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '地图分析', 'mapAnalysisManage', 1, '任务清单研判分析', 1, (select id from permissions where ename = 'taskListStatisticsManage'), '', '/hotModuel/judgmentAnalysis/chartPage/map/index.ftl', '', 4, '');

commit;