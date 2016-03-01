--新农村模块
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '幸福美丽新村', 'newRuralConstructionManagement', 1, ' ', 1, '', '', '', '', 0, '');
--基础信息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '基础信息', 'basicInformationManage', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'),'', '/newCountryside/basesicInfo/basicInformationList.jsp','', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '编辑', 'editBasicInformation', 0, '基础信息', 1, (select id from permissions where ename='basicInformationManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteBasicInformation', 0, '基础信息', 1, (select id from permissions where ename='basicInformationManage'), '', '', '', 1, '');

--年度任务规划
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '年度任务规划', 'annualMissionPlanManage', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '规划采集', 'acquisitionPlan', 1, '年度任务规划', 1, (select id from permissions where ename='annualMissionPlanManage'), '','/newCountryside/newCountrysideInfo/newCountrysideInfoList.jsp?isPlaning=1','', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addAcquisitionPlan', 0, '规划采集', 1, (select id from permissions where ename='acquisitionPlan'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updAteacquisitionPlan', 0, '规划采集', 1, (select id from permissions where ename='acquisitionPlan'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'delAcquisitionPlan', 0, '规划采集', 1, (select id from permissions where ename='acquisitionPlan'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '上报', 'reportAcquisitionPlan', 0, '规划采集', 1, (select id from permissions where ename='acquisitionPlan'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '规划汇总', 'acquisitionSummary', 1, '年度任务规划', 1, (select id from permissions where ename='annualMissionPlanManage'), '','/newCountryside/newCountrySideInfoSummary/newCountrysideInfoSummaryList.jsp?isPlaning=1', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '审核', 'checkAcquisition', 0, '规划汇总', 1, (select id from permissions where ename='acquisitionSummary'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '上报', 'reportAcquisition', 0, '规划汇总', 1, (select id from permissions where ename='acquisitionSummary'), '', '', '', 1, '');

--信息上报管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '信息上报管理', 'newCountrysideInfoManage', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '信息采集', 'newCountrysideInfoCollect', 1, '信息上报管理', 1, (select id from permissions where ename='newCountrysideInfoManage'), '','/newCountryside/newCountrysideInfo/newCountrysideInfoList.jsp?isPlaning=0', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addNewCountrysideInfo', 0, '信息采集', 1, (select id from permissions where ename='newCountrysideInfoCollect'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'editNewCountrysideInfo', 0, '信息采集', 1, (select id from permissions where ename='newCountrysideInfoCollect'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteNewCountrysideInfo', 0, '信息采集', 1, (select id from permissions where ename='newCountrysideInfoCollect'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '上报', 'reportNewCountrysideInfo', 0, '信息采集', 1, (select id from permissions where ename='newCountrysideInfoCollect'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importNewCountrysideInfo', 0, '信息采集', 1, (select id from permissions where ename='newCountrysideInfoCollect'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '信息汇总', 'newCountrysideInfoSummary', 1, '信息上报管理', 1, (select id from permissions where ename='newCountrysideInfoManage'), '','/newCountryside/newCountrySideInfoSummary/newCountrysideInfoSummaryList.jsp?isPlaning=0', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '审核', 'checkData', 0, '信息汇总', 1, (select id from permissions where ename='newCountrysideInfoSummary'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '上报', 'reportData', 0, '信息汇总', 1, (select id from permissions where ename='newCountrysideInfoSummary'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewData', 0, '信息汇总', 1, (select id from permissions where ename='newCountrysideInfoSummary'), '', '', '', 2, '');

--专项工作管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '专项工作管理', 'specialWorkManagement', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '发文', 'sendWorkInfoManage', 1, '专项工作管理', 1, (select id from permissions where ename='specialWorkManagement'), '', '/newCountryside/workInfo/dispatchDocumentsList.jsp', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '收文', 'receiptsWorkInfo', 1, '专项工作管理', 1, (select id from permissions where ename='specialWorkManagement'), '', '/newCountryside/workInfo/receiveDocumentsList.jsp', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '写发文', 'addWorkInfo', 0, '发文', 1, (select id from permissions where ename='sendWorkInfoManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '发文', 'sendWorkInfo', 0, '发文', 1, (select id from permissions where ename='sendWorkInfoManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateWorkInfo', 0, '发文', 1, (select id from permissions where ename='sendWorkInfoManage'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteWorkInfo', 0, '发文', 1, (select id from permissions where ename='sendWorkInfoManage'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchWorkInfo', 0, '发文', 1,(select id from permissions where ename='sendWorkInfoManage'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewWorkInfo', 0, '发文', 1, (select id from permissions where ename='sendWorkInfoManage'), '', '', '', 5, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '收文件', 'receiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '阅读', 'readReceiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转发', 'transpondReceiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteReceiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchReceiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewReceiveWorkInfo', 0, '收文', 1, (select id from permissions where ename='receiptsWorkInfo'), '', '', '', 5, '');
--验收管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '验收管理', 'acceptanceManagement', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '','','', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '评分设置', 'scoreSetting', 1, '验收管理', 1, (select id from permissions where ename='acceptanceManagement'), '','/newCountryside/acceptanceManagement/scoreSetting.jsp', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '验收结果', 'acceptanceResultManage', 1, '验收管理', 1, (select id from permissions where ename='acceptanceManagement'), '','/newCountryside/statistics/newVillageAcceptance.jsp','', 1, '');


--龙头企业管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '龙头企业管理', 'leadingEnterpriseManage', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '','/newCountryside/leadingEnterprise/leadingEnterpriseList.jsp','', 5, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addLeadingEnterprise', 0, '龙头企业管理', 1, (select id from permissions where ename='leadingEnterpriseManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateLeadingEnterprise', 0, '龙头企业管理', 1, (select id from permissions where ename='leadingEnterpriseManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'delLeadingEnterprise', 0, '龙头企业管理', 1, (select id from permissions where ename='leadingEnterpriseManage'), '', '', '', 2, '');

--统计分析
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '统计分析', 'newRuralConstructionStatistics', 1, '幸福美丽新村', 1, (select id from permissions where ename='newRuralConstructionManagement'), '','/newCountryside/statistics/newRuralConstructionStatistics.jsp','', 6, '');

commit;