--更改原来研判分析的名称
update PERMISSIONS p set p.cname = '统计报表' where P.ENAME = 'statAnalyseManage';

--研判分析的升级脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '统计分析', 'judgmentAnalysisManage', 1, ' ', 1, null, null, null, null, 22);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '业务模型配置', 'businessModelManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/judgmentAnalysis/businessModel/businessModelList.ftl', null, 1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '业务描述', 'dimensionBusinessDescriptionManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/judgmentAnalysis/businessDescription/businessDescriptionList.ftl', null, 2);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '维度配置', 'dimensionManagement', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/hotModuel/judgmentAnalysis/dimension/dimensionList.ftl', null, 3);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '维度组合配置', 'dimensionCombinationManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/judgmentAnalysis/dimensionCombination/dimensionCombinationList.ftl', null, 4);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '任务日志', 'scheduleLogManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/judgmentAnalysis/scheduleLog/scheduleLogList.ftl', null, 5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '任务详情表', 'scheduleJobInfoManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/judgmentAnalysis/scheduleJobInfo/scheduleJobInfoList.ftl', null, 6);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '调度作业表', 'scheduleJobManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/hotModuel/judgmentAnalysis/scheduleJob/scheduleJobList.ftl', null, 7);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '调度异常详细表', 'scheduleJobExceptionManage', 1, '统计分析', 1, (select id from permissions where ename='judgmentAnalysisManage'), null, '/hotModuel/judgmentAnalysis/scheduleJobException/scheduleJobExceptionList.ftl', null, 8);

--研判分析统计页
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '研判分析', 'judgmentAnalysisViewManage', 1, ' ', 1, null, null, null, null, 23);

commit;
