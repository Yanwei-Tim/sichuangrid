--删除组织机构中的四支队伍--
delete from rolehaspermissions where permissionid = (select id from permissions where ename = 'fourTeamsManagement');
delete from permissions where ename = 'fourTeamsManagement';

--四支队伍的权限--
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'四支队伍','fourTeamsManagement',1,' ',null,1,'',null);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'组织架构','organizationChartManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'/fourTeamsManage/organizationChartList.jsp','',0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'队伍状况','teamStatusManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'','',1);
   
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'基层便民专业化服务队','convenienceServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/convenienceServiceTeamList.jsp','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'查询','searchConvenienceServiceTeam',0,'基层便民专业化服务队',(select id from permissions where ename='convenienceServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageConvenienceTeamMember',0,'基层便民专业化服务队',(select id from permissions where ename='convenienceServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'村（社区）网格员服务队','gridServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/gridServiceTeamList.jsp','',1);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'查询','searchGridServiceTeam',0,'村（社区）网格员服务队',(select id from permissions where ename='gridServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageGridTeamMember',0,'村（社区）网格员服务队',(select id from permissions where ename='gridServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'党员先锋服务队','partyMemberPioneerServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/partyMemberPioneerServiceTeamList.jsp','',2);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'查询','searchPioneerServiceTeam',0,'党员先锋服务队',(select id from permissions where ename='partyMemberPioneerServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','managePioneerTeamMember',0,'党员先锋服务队',(select id from permissions where ename='partyMemberPioneerServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'社会志愿者服务队','communityVolunteerServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/communityServiceTeamList.jsp','',3);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'查询','searchCommunityServiceTeam',0,'社会志愿者服务队',(select id from permissions where ename='communityVolunteerServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageCommunityTeamMember',0,'社会志愿者服务队',(select id from permissions where ename='communityVolunteerServiceTeamManagement'),1,'','',1);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'队伍管理','teamManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'/fourTeamsManage/teamManagementList.jsp','',2);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'查询','searchTeam',0,'队伍管理',(select id from permissions where ename='teamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护队伍','maintenanceTeam',0,'队伍管理',(select id from permissions where ename='teamManagement'),1,'','',1);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'事件流转','eventFlowManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'/fourTeamsManage/fourTeamsList.jsp','',3);
commit;