----删除事件原有的岗位权限
delete rolehaspermissions r where r.permissionid in
(select p.id from permissions p where p.parentid in
(select p.id from permissions p where p.ename in ('unDoMyIssueListManagement', 'doneMyIssueListManagement', 'myCompletedListManagement')));
delete rolehaspermissions r where r.permissionid in
(select p.id from permissions p where p.ename in ('unDoMyIssueListManagement', 'doneMyIssueListManagement', 'myCompletedListManagement'));
  
----删除事件原有的权限
delete permissions p where p.parentid in 
(select p.id from permissions p where p.ename in ('unDoMyIssueListManagement','doneMyIssueListManagement','myCompletedListManagement'));
delete permissions p where p.id in 
(select p.id from permissions p where p.ename in ('unDoMyIssueListManagement','doneMyIssueListManagement','myCompletedListManagement'));

delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='historicalIssueManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='historicalIssueManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='historicalIssueManagement')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassManagement')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='myIssueReportListManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='myIssueReportListManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='myIssueReportListManagement')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='contradiction')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='contradiction')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='contradiction')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='resolveTheContradictions')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='resolveTheContradictions')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='resolveTheContradictions')
　　connect by prior p.id =  p.parentid
);

delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='securityPrecautions')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='securityPrecautions')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='securityPrecautions')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='specialPopulations')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='specialPopulations')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='specialPopulations')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='socialConditions')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='socialConditions')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='socialConditions')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='policiesAndLaws')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='policiesAndLaws')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='policiesAndLaws')
　　connect by prior p.id =  p.parentid
);


delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='emergencies')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='emergencies')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='emergencies')
　　connect by prior p.id =  p.parentid
);



delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='otherManage')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='otherManage')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='otherManage')
　　connect by prior p.id =  p.parentid
);

delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='jurisdictionsIssue')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='jurisdictionsIssue')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='jurisdictionsIssue')
　　connect by prior p.id =  p.parentid
);

delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='issueProccess')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='issueProccess')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='issueProccess')
　　connect by prior p.id =  p.parentid
);

delete from rolehaspermissions where permissionid in (
select p.id from permissions p where p.ename in ('mySubmitContradiction','myAssginContradiction')
);
delete from permissions where ename in ('mySubmitContradiction','myAssginContradiction');
commit;

----新增事件的按钮权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '待办事项', 'needIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '新增', 'addNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改', 'updateNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 4, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '延期', 'delayNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 5, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '申请延期', 'applyDelayNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 6, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '延期详情', 'viewDelayNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 7, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 8, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '打印', 'printNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 9, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '阅读', 'readNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 10, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '受理', 'conceptNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 11, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '办理', 'dealNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 12, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '督办', 'overseeNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 13, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '普通督办', 'normalIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 14, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '黄牌督办', 'yellowCardIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 15, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '红牌督办', 'redCardIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 16, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '取消督办', 'cancleSuperviseIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 17, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '加急', 'urgentNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 18, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '取消加急', 'cancelUrgentNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 19, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '领导批示', 'commandNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 20, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '历史遗留', 'historicalNeedIssue', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 21, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '待审核事项', 'checkPendingIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewCheckPendingIssue', 0, '待审核事项', 1, (select id from permissions where ename='checkPendingIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '审核延期', 'auditDelayCheckPendingIssue', 0, '待审核事项', 1, (select id from permissions where ename='checkPendingIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '延期详情', 'delayDetailsCheckPendingIssue', 0, '待审核事项', 1, (select id from permissions where ename='checkPendingIssueListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '待验证事项', 'verificationIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '验证', 'evaluateVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 4, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessVerificationIssue', 0, '待验证事项', 1, (select id from permissions where ename='verificationIssueListManagement'), '', '', '', 5, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '待评分事项', 'checkGradeIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '评分', 'gradeCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '评分详情', 'gradeHistoryCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 4, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 5, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessCheckGradeIssue', 0, '待评分事项', 1, (select id from permissions where ename='checkGradeIssueListManagement'), '', '', '', 6, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '待跟进事项', 'followIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 4, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchFollowIssue', 0, '待跟进事项', 1, (select id from permissions where ename='followIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewFollowIssue', 0, '待跟进事项', 1, (select id from permissions where ename='followIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topFollowIssue', 0, '待跟进事项', 1, (select id from permissions where ename='followIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessFollowIssue', 0, '待跟进事项', 1, (select id from permissions where ename='followIssueListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '已办事项', 'doneIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 5, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchDoneIssue', 0, '已办事项', 1, (select id from permissions where ename='doneIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewDoneIssue', 0, '已办事项', 1, (select id from permissions where ename='doneIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topDoneIssue', 0, '已办事项', 1, (select id from permissions where ename='doneIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessDoneIssue', 0, '已办事项', 1, (select id from permissions where ename='doneIssueListManagement'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '宣传案例', 'publicltyCassDoneIssue', 0, '已办事项', 1, (select id from permissions where ename='doneIssueListManagement'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '已办结事项', 'completedIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 6, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 4, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '评分详情', 'gradeHistoryCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 5, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '转为台账', 'convertToWorkingRecordCompletedIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 6, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '上报事项', 'submitIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 7, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchSubmitIssue', 0, '上报事项', 1, (select id from permissions where ename='submitIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewSubmitIssue', 0, '上报事项', 1, (select id from permissions where ename='submitIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topSubmitIssue', 0, '上报事项', 1, (select id from permissions where ename='submitIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessSubmitIssue', 0, '上报事项', 1, (select id from permissions where ename='submitIssueListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '上级交办事项', 'assignIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 8, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchAssignIssue', 0, '上级交办事项', 1, (select id from permissions where ename='assignIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewAssignIssue', 0, '上级交办事项', 1, (select id from permissions where ename='assignIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topAssignIssue', 0, '上级交办事项', 1, (select id from permissions where ename='assignIssueListManagement'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '展示', 'viewProcessAssignIssue', 0, '上级交办事项', 1, (select id from permissions where ename='assignIssueListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '越级事项', 'skipIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 9, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchSkipIssue', 0, '越级事项', 1, (select id from permissions where ename='skipIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewSkipIssue', 0, '越级事项', 1, (select id from permissions where ename='skipIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '置顶', 'topSkipIssue', 0, '越级事项', 1, (select id from permissions where ename='skipIssueListManagement'), '', '', '', 2, '');


----删除原本的事件的所有用户的权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='serviceWork')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='serviceWork')
　　connect by prior p.id =  p.parentid
);

----给admin岗位所有新的事件的权限
insert into rolehaspermissions  (roleid,permissionid)
select (select id from roles where rolename='admin') , p.id from permissions p
　　start with id=(select id from permissions where ename='serviceWork')
　　connect by prior p.id =  p.parentid ;
commit;