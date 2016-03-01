--党建权限
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
   VALUES(s_permissions.NEXTVAL,'党建','partyBuilding',1,' ',null,1,'',null);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'基本情况','basicCaseManagement',1,'党建',(select id from permissions where ename='partyBuilding'),1,'/partyBuilding/baseInfos/basicCaseList.jsp','/partyBuilding/baseInfos/districtBasiccaseList.jsp',0);
   
    insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
		VALUES(s_permissions.NEXTVAL,'编辑','editCase',0,'基本情况',(select id from permissions where ename='basicCaseManagement'),1,'',0);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
		VALUES(s_permissions.NEXTVAL,'新增党工委成员','addPartyWorkMembers',0,'基本情况',(select id from permissions where ename='basicCaseManagement'),1,'',1);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
		VALUES(s_permissions.NEXTVAL,'修改党工委成员','updatePartyWorkMembers',0,'基本情况',(select id from permissions where ename='basicCaseManagement'),1,'',2);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
		VALUES(s_permissions.NEXTVAL,'删除党工委成员','deletePartyWorkMembers',0,'基本情况',(select id from permissions where ename='basicCaseManagement'),1,'',3);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
		VALUES(s_permissions.NEXTVAL,'党工委成员列表排序','sortPartyWorkMembers',0,'基本情况',(select id from permissions where ename='basicCaseManagement'),1,'',4);
   
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'党组织党员情况','partyOrgandPartyMembersCaseManagement',1,'党建',(select id from permissions where ename='partyBuilding'),1,'','',1);
   
   insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'机关党建','organsPartyManagement',1,'党组织党员情况',(select id from permissions where ename='partyOrgandPartyMembersCaseManagement'),1,'','',0);
		
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'基本情况','organsPartyBasicCaseManagement',1,'机关党建',(select id from permissions where ename='organsPartyManagement'),1,'/partyBuilding/notHasComplete.jsp','',0);
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'机关党组织','organPartyOrganizationManagement',1,'机关党建',(select id from permissions where ename='organsPartyManagement'),1,'/partyBuilding/notHasComplete.jsp','',1);
			
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'街道党建','streetPartyManagement',1,'党组织党员情况',(select id from permissions where ename='partyOrgandPartyMembersCaseManagement'),1,'','',1);
		
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'街道社区党组织','streetCommunityPartyOrganizationManagement',1,'街道党建',(select id from permissions where ename='streetPartyManagement'),1,'/partyBuilding/organizations/townPartyOrgManage/townPartyOrgTab.jsp','',0);
			
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'党组织信息','townPartyOrgManagement',1,'街道社区党组织',(select id from permissions where ename='streetCommunityPartyOrganizationManagement'),1,'','',0);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addTownPartyOrg',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateTownPartyOrg',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteTownPartyOrg',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewTownPartyOrg',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchTownPartyOrg',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'维护党员','manageTownPartyMember',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',5);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'维护活动记录','manageTownPartyRecord',0,'党组织信息',(select id from permissions where ename='townPartyOrgManagement'),1,'','',6);
				
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'党员信息','streetPartyMemberManagement',1,'街道社区党组织',(select id from permissions where ename='streetCommunityPartyOrganizationManagement'),1,'','',1);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导入','importStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',5);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导出','downStreetPartyMember',0,'党员信息',(select id from permissions where ename='streetPartyMemberManagement'),1,'','',6);
					
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'党组织活动记录','townPartyOrgActivityManagement',1,'街道社区党组织',(select id from permissions where ename='streetCommunityPartyOrganizationManagement'),1,'','',2);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导出','downStreetPartyActivity',0,'党组织活动记录',(select id from permissions where ename='townPartyOrgActivityManagement'),1,'','',6);
			
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'两新组织党组织','twoNewPartyOrganizationManagement',1,'街道党建',(select id from permissions where ename='streetPartyManagement'),1,'/partyBuilding/organizations/newPartyOrgManage/newPartyOrgTab.jsp','',1);
			
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'两新组织信息','newPartyOrgManagement',1,'两新组织党组织',(select id from permissions where ename='twoNewPartyOrganizationManagement'),1,'','',0);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导出','downNewPartyOrg',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',5);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'维护党员','manageNewPartyMember',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',6);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'维护活动记录','manageNewPartyRecord',0,'两新组织信息',(select id from permissions where ename='newPartyOrgManagement'),1,'','',7);
					
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'两新组织成员信息','twoNewPartyOrgMemberManagement',1,'两新组织党组织',(select id from permissions where ename='twoNewPartyOrganizationManagement'),1,'','',0);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导入','importTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',5);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导出','downTwoNewPartyOrgMember',0,'两新组织成员信息',(select id from permissions where ename='twoNewPartyOrgMemberManagement'),1,'','',6);
					
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'两新组织活动记录','newPartyOrgActivityManagement',1,'两新组织党组织',(select id from permissions where ename='twoNewPartyOrganizationManagement'),1,'','',0);
				
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',0);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'修改','updateTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',1);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'删除','deleteTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',2);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查看','viewTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',3);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'查询','searchTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',4);
				insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'导出','downTwoNewPartyOrgActivity',0,'两新组织活动记录',(select id from permissions where ename='newPartyOrgActivityManagement'),1,'','',6);
		
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'系统党建','systemPartyManagement',1,'党组织党员情况',(select id from permissions where ename='partyOrgandPartyMembersCaseManagement'),1,'/partyBuilding/notHasComplete.jsp','',2);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'区域党建','regionalPartyManagement',1,'党组织党员情况',(select id from permissions where ename='partyOrgandPartyMembersCaseManagement'),1,'/partyBuilding/organizations/partyConstruction.jsp','',3);
		
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'党员信息','regionalPartyMemberManagement',1,'区域党建',(select id from permissions where ename='regionalPartyManagement'),1,'','',0);
			
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
					VALUES(s_permissions.NEXTVAL,'新增','addRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',0);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'修改','updateRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',1);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'删除','deleteRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',2);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查看','viewRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',3);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查询','searchRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',4);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'导入','importRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',5);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'导出','downRegionalPartyMember',0,'党员信息',(select id from permissions where ename='regionalPartyMemberManagement'),1,'','',6);
					
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'区域联建情况','regionalActivityManagement',1,'区域党建',(select id from permissions where ename='regionalPartyManagement'),1,'','',1);
			
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'新增','addRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',0);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'修改','updateRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',1);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'删除','deleteRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',2);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查看','viewRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',3);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查询','searchRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',4);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'导出','downRegionalPartyActivity',0,'区域联建情况',(select id from permissions where ename='regionalActivityManagement'),1,'','',6);
					
		insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
			VALUES(s_permissions.NEXTVAL,'区域内主要党组织资源','partyresourceManagement',1,'区域党建',(select id from permissions where ename='regionalPartyManagement'),1,'','',2);
			
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'新增','addPartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',0);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'修改','updatePartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',1);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'删除','deletePartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',2);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查看','viewPartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',3);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'查询','searchPartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',4);
			insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
				VALUES(s_permissions.NEXTVAL,'导出','downPartyresource',0,'区域内主要党组织资源',(select id from permissions where ename='partyresourceManagement'),1,'','',6);
   
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'党员志愿者队伍','partyMemberVolunteerTeamManagement',1,'党建',(select id from permissions where ename='partyBuilding'),1,'/partyBuilding/notHasComplete.jsp','',2);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
   VALUES(s_permissions.NEXTVAL,'双报到活动','doubleRegistrationActivitiesManagement',1,'党建',(select id from permissions where ename='partyBuilding'),1,'','',3);
   
   insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'党组织报到情况','partyOrganizationToReportForDutyManagement',1,'双报到活动',(select id from permissions where ename='doubleRegistrationActivitiesManagement'),1,'/partyBuilding/notHasComplete.jsp','',0);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'党员报到情况','partyMemberRegistrationSituationManagement',1,'双报到活动',(select id from permissions where ename='doubleRegistrationActivitiesManagement'),1,'/partyBuilding/notHasComplete.jsp','',1);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'提供的服务项目','customizedServicesManagement',1,'双报到活动',(select id from permissions where ename='doubleRegistrationActivitiesManagement'),1,'/partyBuilding/notHasComplete.jsp','',2);
	insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
		VALUES(s_permissions.NEXTVAL,'提供的志愿者岗位','provideVolunteerPositionsManagement',1,'双报到活动',(select id from permissions where ename='doubleRegistrationActivitiesManagement'),1,'/partyBuilding/notHasComplete.jsp','',3);

commit;