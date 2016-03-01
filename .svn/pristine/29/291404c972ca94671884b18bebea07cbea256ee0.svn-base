--删除组织机构中的四支队伍--
delete from rolehaspermissions where permissionid = (select id from permissions where ename = 'fourTeamsManagement');
delete from permissions where ename = 'fourTeamsManagement';
----删除四个模块的新增，批量删除----
delete permissions where ename in('deletePioneerServiceTeam','deleteConvenienceServiceTeam','deleteCommunityServiceTeam','deleteGridServiceTeam','addPioneerServiceTeam','addConvenienceServiceTeam','addCommunityServiceTeam','addGridServiceTeam');

commit;

--四支队伍的权限--
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'四支队伍','fourTeamsManagement',1,' ',null,1,'',null);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'组织架构','organizationChartManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'/fourTeamsManage/organizationChartList.jsp','',0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'队伍状况','teamStatusManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'','',1);
   
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'基层便民专业化服务队','convenienceServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/serviceTeamList.jsp?teamType=convenienceServiceTeam','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'高级查询','searchConvenienceServiceTeam',0,'基层便民专业化服务队',(select id from permissions where ename='convenienceServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageConvenienceTeamMember',0,'基层便民专业化服务队',(select id from permissions where ename='convenienceServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'网格员服务队','gridServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/serviceTeamList.jsp?teamType=gridServiceTeam','',1);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'高级查询','searchGridServiceTeam',0,'网格员服务队',(select id from permissions where ename='gridServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageGridTeamMember',0,'网格员服务队',(select id from permissions where ename='gridServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'党员先锋服务队','partyMemberPioneerServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/serviceTeamList.jsp?teamType=partyMemberPioneerServiceTeam','',2);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'高级查询','searchPioneerServiceTeam',0,'党员先锋服务队',(select id from permissions where ename='partyMemberPioneerServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','managePioneerTeamMember',0,'党员先锋服务队',(select id from permissions where ename='partyMemberPioneerServiceTeamManagement'),1,'','',1);
		
 insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'社会志愿者服务队','communityVolunteerServiceTeamManagement',1,'队伍状况',(select id from permissions where ename='teamStatusManagement'),1,'/fourTeamsManage/serviceTeamList.jsp?teamType=communityVolunteerServiceTeam','',3);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'高级查询','searchCommunityServiceTeam',0,'社会志愿者服务队',(select id from permissions where ename='communityVolunteerServiceTeamManagement'),1,'','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'维护成员','manageCommunityTeamMember',0,'社会志愿者服务队',(select id from permissions where ename='communityVolunteerServiceTeamManagement'),1,'','',1);
COMMIT;
--四支队伍子页面的权限新增脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'新增', 'addPioneerServiceTeam', 0, '党员先锋服务队', 1, ' ', (select id from permissions where ename='partyMemberPioneerServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'新增', 'addConvenienceServiceTeam', 0, '基层便民专业化服务队', 1, ' ', (select id from permissions where ename='convenienceServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'新增', 'addCommunityServiceTeam', 0, '社会志愿者服务队', 1, ' ', (select id from permissions where ename='communityVolunteerServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'新增', 'addGridServiceTeam', 0, '网格员服务队', 1, ' ', (select id from permissions where ename='gridServiceTeamManagement'));

--四支队伍批量删除脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deletePioneerServiceTeam', 0, '党员先锋服务队', 1, ' ', (select id from permissions where ename='partyMemberPioneerServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteConvenienceServiceTeam', 0, '基层便民专业化服务队', 1, ' ', (select id from permissions where ename='convenienceServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteCommunityServiceTeam', 0, '社会志愿者服务队', 1, ' ', (select id from permissions where ename='communityVolunteerServiceTeamManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'批量删除', 'deleteGridServiceTeam', 0, '网格员服务队', 1, ' ', (select id from permissions where ename='gridServiceTeamManagement'));



insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
	VALUES(s_permissions.NEXTVAL,'事件流转','eventFlowManagement',1,'四支队伍',(select id from permissions where ename='fourTeamsManagement'),1,'/fourTeamsManage/fourTeamsList.jsp','',3);

alter table excelimportlog modify(IMPORTMODULENAME  varchar2(150));
COMMIT;
