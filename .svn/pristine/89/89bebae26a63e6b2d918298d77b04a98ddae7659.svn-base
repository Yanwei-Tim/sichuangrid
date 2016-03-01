
---事件处理绩效考核配置表---
create table issueAccessConfig(
       yellowCardScores          number(10),
       redCardScores             number(10),
       yellowCardAccepted        number(10),
       redCardAccepted           number(10),
       yellowCardHandle          number(10),
       redCardHandle             number(10),
       normalHandle              number(10)
);
comment on table issueAccessConfig is 
'事件处理绩效考核配置表';
comment on column issueAccessConfig.yellowCardScores is
'黄牌扣分';
comment on column issueAccessConfig.redCardScores is
'红牌扣分';
comment on column issueAccessConfig.yellowCardAccepted is
'黄牌受理期限';
comment on column issueAccessConfig.redCardAccepted is
'红牌受理期限';
comment on column issueAccessConfig.yellowCardHandle is
'黄牌处理期限';
comment on column issueAccessConfig.redCardHandle is
'红牌处理期限';
comment on column issueAccessConfig.normalHandle is
'正常处理加分';

----    绩效考核权限添加    ----           
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '绩效考核', 'performanceAppraisalManagement', 1, '事件处理系统', 1, (select id from permissions where ename='serviceWork'), '', '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=performanceAppraisalManagement', '', null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '行政部门绩效考核', 'statAdministrativeRegradedPointManagement', 1, '绩效考核', 1, (select id from permissions where ename='performanceAppraisalManagement'), '', '/statAnalyse/statRegradedPointManage/dispatch.action?internalId=0', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '添加打分记录', 'addAdministrativeRegradedPoint', 0, '行政部门绩效考核', 1, (select id from permissions where ename = 'statAdministrativeRegradedPointManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看详情', 'viewAdministrativeRegradedPoints', 0, '行政部门绩效考核', 1, (select id from permissions where ename = 'statAdministrativeRegradedPointManagement'), ' ','', '', null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '职能部门绩效考核', 'statFunctionalRegradedPointManagement', 1, '绩效考核', 1, (select id from permissions where ename='performanceAppraisalManagement'), '', '/statAnalyse/statRegradedPointManage/dispatch.action?internalId=1', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '添加打分记录', 'addFunctionalRegradedPoint', 0, '职能部门绩效考核', 1, (select id from permissions where ename = 'statFunctionalRegradedPointManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看详情', 'viewFunctionalRegradedPoints', 0, '职能部门绩效考核', 1, (select id from permissions where ename = 'statFunctionalRegradedPointManagement'), ' ','', '', null);


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '绩效考核设置', 'issueAccessConfigManagement', 1, '绩效考核', 1, (select id from permissions where ename='performanceAppraisalManagement'), '', '/issueAccessConfigManage/dispatch.action', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '设置', 'addSet', 0, '绩效考核设置', 1, (select id from permissions where ename = 'issueAccessConfigManagement'), ' ','', '', null);




commit;

