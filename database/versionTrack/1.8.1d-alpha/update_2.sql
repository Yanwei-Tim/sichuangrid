--事件流转的权限
update permissions set normalUrl='' where cname='事件流转' and ename='eventFlowManagement';
commit;
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'基层便民专业化服务队','convenienceServiceTeamEventManagement',1,'事件流转',(select id from permissions where ename='eventFlowManagement'),1,'/fourTeamsManage/fourTeamsIssueManage/convenienceServiceTeam/convenienceFourTeamsIssueViewTabList.jsp','',0);
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
		values (s_permissions.NEXTVAL, '查询', 'convenienceServiceTeamEventSearchIssue', 0, '基层便民专业化服务队', 1, (select id from permissions where ename='convenienceServiceTeamEventManagement'), '', '', '', 0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'网格员服务队','gridServiceTeamEventManagement',1,'事件流转',(select id from permissions where ename='eventFlowManagement'),1,'/fourTeamsManage/fourTeamsIssueManage/gridServiceTeam/gridFourTeamsIssueViewTabList.jsp','',1);
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
		values (s_permissions.NEXTVAL, '查询', 'gridServiceTeamEventSearchIssue', 0, '网格员服务队', 1, (select id from permissions where ename='gridServiceTeamEventManagement'), '', '', '', 0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  	VALUES(s_permissions.NEXTVAL,'党员先锋服务队','partyMemberPioneerServiceTeamEventManagement',1,'事件流转',(select id from permissions where ename='eventFlowManagement'),1,'/fourTeamsManage/fourTeamsIssueManage/partyMemberPioneerServiceTeam/partyMemberPioneerFourTeamsIssueViewTabList.jsp','',2);
  	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
		values (s_permissions.NEXTVAL, '查询', 'partyMemberPioneerServiceTeamEventSearchIssue', 0, '党员先锋服务队', 1, (select id from permissions where ename='partyMemberPioneerServiceTeamEventManagement'), '', '', '', 0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
  	VALUES(s_permissions.NEXTVAL,'社会志愿者服务队','communityVolunteerServiceTeamEventManagement',1,'事件流转',(select id from permissions where ename='eventFlowManagement'),1,'/fourTeamsManage/fourTeamsIssueManage/communityVolunteerServiceTeam/communityVolunteerFourTeamsIssueViewTabList.jsp','',3);
  	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
		values (s_permissions.NEXTVAL, '查询', 'communityVolunteerServiceTeamEventSearchIssue', 0, '社会志愿者服务队', 1, (select id from permissions where ename='communityVolunteerServiceTeamEventManagement'), '', '', '', 0);
commit;
 