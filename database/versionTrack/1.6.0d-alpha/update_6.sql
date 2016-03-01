--三本台账的编号字段长度修改--
alter table  peopleAspirations modify serialNumber VARCHAR2(90);
alter table  poorPeoples modify serialnumber VARCHAR2(90);
alter table  steadyWorks modify serialnumber VARCHAR2(90);



update permissions set normalUrl='/partyBuilding/baseInfos/organsParty/basicCaseList.jsp' where ename='organsPartyBasicCaseManagement';
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'编辑党建情况','editOrgansPartyCase',0,'基本情况',(select id from permissions where ename='organsPartyBasicCaseManagement'),1,'','',0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'新增党工委成员','addOrgansPartyMembers',0,'基本情况',(select id from permissions where ename='organsPartyBasicCaseManagement'),1,'','',1);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'修改党工委成员','updateOrgansPartyMembers',0,'基本情况',(select id from permissions where ename='organsPartyBasicCaseManagement'),1,'','',2);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'删除党工委成员','deleteOrgansPartyMembers',0,'基本情况',(select id from permissions where ename='organsPartyBasicCaseManagement'),1,'','',3);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'排序党工委成员','sortOrgansPartyMembers',0,'基本情况',(select id from permissions where ename='organsPartyBasicCaseManagement'),1,'','',4);

update permissions set normalUrl='/partyBuilding/volunteerTeam/volunteerTeamList.jsp' where ename='partyMemberVolunteerTeamManagement';          
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'新增','addVolunteerTeam',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'修改','updateVolunteerTeam',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',1);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'删除','deleteVolunteerTeam',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',2);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'查看','viewVolunteerTeam',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',3);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'导出','downVolunteerTeam',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',4);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'维护成员','manageVolunteerTeamMember',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',5);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'新增成员','addVolunteerTeamMember',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',6);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'修改成员','updateVolunteerTeamMember',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',7);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'删除成员','deleteVolunteerTeamMember',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',8);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
          VALUES(s_permissions.NEXTVAL,'查看成员','viewVolunteerTeamMember',0,'党员志愿者队伍',(select id from permissions where ename='partyMemberVolunteerTeamManagement'),1,'','',9);
          

commit;