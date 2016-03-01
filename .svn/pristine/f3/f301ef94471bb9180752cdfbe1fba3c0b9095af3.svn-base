insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '全年考评', 'annualEvaluation', 1, '考核评估', 1, (select id from permissions where 
ename='evaluateManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '日常考评', 'routineEvaluation', 1, '考核评估', 1, (select id from permissions where 
ename='evaluateManagement'), '', '', '', 1, '');

commit;

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '网格化服务管理年终考核表', 'annualAssessment', 1, '全年考评', 1, (select id from permissions where 
ename='annualEvaluation'), '', '/hotModuel/statAnalyse/evaluation/annualAssessment.ftl', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '网格化服务管理日常考核表', 'dailyWorkReport', 1, '日常考评', 1, (select id from permissions where 
ename='routineEvaluation'), '', '/hotModuel/statAnalyse/evaluation/dailyWorkReport.ftl', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '各市(州)重点数据情况统计', 'emphasesSituationReport', 1, '日常考评', 1, (select id from 
permissions where ename='routineEvaluation'), '', '/statAnalyse/baseSituation/specialCrowdReports.jsp', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '信息系统使用情况表', 'systemUsedReport', 1, '日常考评', 1, (select id from permissions where 
ename='routineEvaluation'), '', '/hotModuel/statAnalyse/evaluation/systemUsedReport.ftl', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, 
INDEXID, GRIDURL)
values (s_permissions.nextval, '网格化服务管理工作情况', 'workSituationReport', 1, '日常考评', 1, (select id from permissions 
where ename='routineEvaluation'), '', '/hotModuel/statAnalyse/evaluation/userActivateReportList.ftl', '', 0, '');
commit;


