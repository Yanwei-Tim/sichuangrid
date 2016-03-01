--事件待办页面转为三本台账权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '转为三本台账', 'toChangeIntoThreeRecords', 0, '待办事项', 1, (select id from permissions where ename='needIssueListManagement'), '', '', '', 22, '');

commit;

--事件表状态字段增加  待办 已办 页面
alter table issues add (eventState number(1) default 0 not null);
 
--事件带评分  待评分 已办结
alter table completedIssue  add (eventState number(1) default 0 not null);


--事件统计列表新旧权限控制
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '列表信息(旧)', 'oldIssueStatReport', 0, '事件处理统计', 1, (select id from permissions where ename='issueDealStatReport'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '列表信息(新)', 'newIssueStatReport', 0, '事件处理统计', 1, (select id from permissions where ename='issueDealStatReport'), '', '', '', 1, '');

commit;

--综治办
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'综治办','comprehensiveManagement',1,'组织机构',1,(select id from permissions where ename = 'partyOrgManagement'),
   '','/baseinfo/primaryOrganization/primaryOrgList.jsp?name=DepartmentParty'||'&'||'isCommissionOrganization=comprehensive','',3,'');
   
--综治委
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'综治委','commissionManagement',1,'组织机构',1,(select id from permissions where ename = 'partyOrgManagement'),'',
   '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=DepartmentParty'||'&'||'isCommissionOrganization=commission','',2,'');
   
--综治成员
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)values
  (s_permissions.nextval,'综治成员单位','commissionMemberManagement',1,'组织机构',1,(select id from permissions where ename = 'partyOrgManagement'),
   '','/baseinfo/primaryOrganization/primaryOrgList.jsp?name=DepartmentParty'||'&'||'isCommissionOrganization=commissionMember','',
   4,'');
commit;


--在事件处理步骤表和事件处理步骤表历史表中添加字段
alter table issuesteps add isStayStep number(1);
alter table issuesteps_history add isStayStep number(1);

comment on column issueSteps.isStayStep is '用于对已办步骤去重';
comment on column issuesteps_history.isStayStep is '用于对已办步骤去重';
