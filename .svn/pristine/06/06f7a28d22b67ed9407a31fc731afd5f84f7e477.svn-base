/*==============================================================*/
/* sequence: partyOrg_Report 党组织报到序列                           				*/
/*==============================================================*/   
create sequence s_partyOrg_Report
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
/*==============================================================*/
/* Table: partyOrg_Report 党组织报到表                              					*/
/*==============================================================*/
create table partyOrg_Report(
  id                  		NUMBER(10) not null,
  orgId               		NUMBER(10) not null,
  orgInternalCode     		VARCHAR2(32),
  name                		VARCHAR2(60) not null,
  partyOrgType        		NUMBER(10) not null,
  address             		VARCHAR2(300),
  contractor		  		VARCHAR2(60),
  telephone			  		VARCHAR2(30),
  remark              		VARCHAR2(600),
  isEmphasis           		NUMBER(1)        default 0,
  logOutTime           		DATE,
  logOutReason         		VARCHAR2(300),
  type 						VARCHAR2(60),
  CREATEUSER          		VARCHAR2(60) not null,
  UPDATEUSER          		VARCHAR2(60),
  CREATEDATE          		DATE not null,
  UPDATEDATE          		DATE,
  constraint partyOrg_Report primary key (ID)
  
);

 -- Add comments to the table 
comment on table partyOrg_Report
  is '党组织报到表';
-- Add comments to the columns 
comment on column partyOrg_Report.ID
  is '党组织报到id';
comment on column partyOrg_Report.orgId
  is '所属网格';
comment on column partyOrg_Report.orgInternalCode
  is '所属网格编号';
comment on column partyOrg_Report.name
  is '单位名称';
comment on column partyOrg_Report.partyOrgType
  is '党组织类别';
comment on column partyOrg_Report.address
  is '地址';
comment on column partyOrg_Report.contractor
  is '联系人';
comment on column partyOrg_Report.telephone
  is '联系电话';
comment on column partyOrg_Report.remark
  is '备注'; 
comment on column partyOrg_Report.isEmphasis 
  is '是否注销';
comment on column partyOrg_Report.logOutTime 
  is '注销时间';
comment on column partyOrg_Report.logOutReason 
  is '注销原因'; 
comment on column partyOrg_Report.type 
  is '类型'; 
comment on column partyOrg_Report.CREATEUSER
  is '创建人';
comment on column partyOrg_Report.UPDATEUSER
  is '修改人';
comment on column partyOrg_Report.CREATEDATE
  is '创建时间';
comment on column partyOrg_Report.UPDATEDATE
  is '修改时间'; 
  
/*==============================================================*/
/* sequence: serviceProject 服务项目序列                           				*/
/*==============================================================*/   
create sequence s_serviceProject
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
/*==============================================================*/
/* Table: serviceProject 服务项目表                              					*/
/*==============================================================*/
create table serviceProject(
  id                  		NUMBER(10) not null,
  orgId               		NUMBER(10) not null,
  orgInternalCode     		VARCHAR2(32),
  projectName               VARCHAR2(60) not null,
  claimPlans        		NUMBER(10),
  projectContent            CLOB,
  contractor		  		VARCHAR2(60),
  telephone			  		VARCHAR2(30),
  remark              		VARCHAR2(600),
  CREATEUSER          		VARCHAR2(60) not null,
  UPDATEUSER          		VARCHAR2(60),
  CREATEDATE          		DATE not null,
  UPDATEDATE          		DATE,
  constraint serviceProject primary key (ID)
);  
 -- Add comments to the table 
comment on table serviceProject
  is '服务项目表';
-- Add comments to the columns 
comment on column serviceProject.ID
  is '服务项目id';
comment on column serviceProject.orgId
  is '所属网格';
comment on column serviceProject.orgInternalCode
  is '所属网格编号';
comment on column serviceProject.projectName
  is '项目名称';
comment on column serviceProject.claimPlans
  is '拟认领数';
comment on column serviceProject.projectContent
  is '项目内容';
comment on column serviceProject.contractor
  is '联系人';
comment on column serviceProject.telephone
  is '联系电话';
comment on column serviceProject.remark
  is '备注';
comment on column serviceProject.CREATEUSER
  is '创建人';
comment on column serviceProject.UPDATEUSER
  is '修改人';
comment on column serviceProject.CREATEDATE
  is '创建时间';
comment on column serviceProject.UPDATEDATE
  is '修改时间'; 
  
  
/*==============================================================*/
/* sequence: volunteerJobs 志愿者岗位序列                           				*/
/*==============================================================*/   
create sequence s_volunteerJobs
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
/*==============================================================*/
/* Table: volunteerJobs 志愿者岗位表                              					*/
/*==============================================================*/
create table volunteerJobs(
  id                  		NUMBER(10) not null,
  orgId               		NUMBER(10) not null,
  orgInternalCode     		VARCHAR2(32),
  name               		VARCHAR2(60) not null,
  claimPlans        		NUMBER(10),
  content            		CLOB,
  contractor		  		VARCHAR2(60),
  telephone			  		VARCHAR2(30),
  remark              		VARCHAR2(600),
  CREATEUSER          		VARCHAR2(60) not null,
  UPDATEUSER          		VARCHAR2(60),
  CREATEDATE          		DATE not null,
  UPDATEDATE          		DATE,
  constraint volunteerJobs primary key (ID)
);  
 -- Add comments to the table 
comment on table volunteerJobs
  is '志愿者岗位表';
-- Add comments to the columns 
comment on column volunteerJobs.ID
  is '志愿者岗位id';
comment on column volunteerJobs.orgId
  is '所属网格';
comment on column volunteerJobs.orgInternalCode
  is '所属网格编号';
comment on column volunteerJobs.name
  is '志愿者岗位';
comment on column volunteerJobs.claimPlans
  is '拟认领数';
comment on column volunteerJobs.content
  is '专长要求';
comment on column volunteerJobs.contractor
  is '联系人';
comment on column volunteerJobs.telephone
  is '联系电话';
comment on column volunteerJobs.remark
  is '备注';
comment on column volunteerJobs.CREATEUSER
  is '创建人';
comment on column volunteerJobs.UPDATEUSER
  is '修改人';
comment on column volunteerJobs.CREATEDATE
  is '创建时间';
comment on column volunteerJobs.UPDATEDATE
  is '修改时间';
  
  

/*==============================================================*/
/* Table: reportHasProject 党组织报到和服务项目关联表                                   			*/
/*==============================================================*/
create table reportHasProject  (
   reportId           NUMBER(10)                      not null,
   projectId          NUMBER(10)                      not null,
   constraint fkReportHasProject foreign key (projectId)
   references serviceProject (id)
);
comment on table reportHasProject
  is '党组织报到和服务项目关联表      ';
comment on column reportHasProject.reportId
  is '党组织报到id';
comment on column reportHasProject.projectId
  is '服务项目id';


  
--党组织报到情况--
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updatePartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deletePartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导入', 'importPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'downPartyOrgReport', 0, '党组织报到情况', 1, ' ', (select id from permissions where ename='partyOrganizationToReportForDutyManagement'));
 
--提供的服务项目-- 
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'downServiceProject', 0, '提供的服务项目', 1, ' ', (select id from permissions where ename='customizedServicesManagement'));
    
    
    
--提供的志愿者岗位--
	insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'downVolunteerJobs', 0, '提供的志愿者岗位', 1, ' ', (select id from permissions where ename='provideVolunteerPositionsManagement'));
    
    update permissions set normalUrl='/partyBuilding/doubleRegActivities/partyorgReportManage/partyOrgReportTab.jsp' where ename='partyOrganizationToReportForDutyManagement';
    update permissions set normalUrl='/partyBuilding/doubleRegActivities/serviceProjectManage/serviceProjectList.jsp' where ename='customizedServicesManagement';
    update permissions set normalUrl='/partyBuilding/doubleRegActivities/volunteerJobsManage/volunteerJobsList.jsp' where ename='provideVolunteerPositionsManagement';
    
--四川党建成员报名详情表
create sequence S_party_members_enroll
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: reportHasProject 党员报到详情表           */
/*==============================================================*/
create table party_members_enroll
(
  id number(10) primary key,
  orgId number(10),
  orgInternalCode varchar2(32),
  memberId number(10),
  regActivitiesType VARCHAR2(60),
  voluntaryPostId number(10),
  isEnroll number(10),
  isTurnOut number(10),
  createdate date not null,
  createuser VARCHAR2(32) not null,
  updatedate date,
  updateuser VARCHAR2(32)
);
comment on table party_members_enroll                      is '四川党建成员报名详情表';
comment on column party_members_enroll.id                  is '四川党建成员报名详情表id';
comment on column party_members_enroll.orgId               is '组织机构id';
comment on column party_members_enroll.orgInternalCode     is '组织机构内置编码';
comment on column party_members_enroll.memberId            is '党员ID';
comment on column party_members_enroll.regActivitiesType   is '类型';
comment on column party_members_enroll.voluntaryPostId     is '志愿者岗位';
comment on column party_members_enroll.isEnroll            is '是否报到【0、已报到 1、未报到】';
comment on column party_members_enroll.isTurnOut           is '是否转出【0是】';
comment on column party_members_enroll.createDate          is '创建时间';
comment on column party_members_enroll.createUser          is '创建人';
comment on column party_members_enroll.updateDate          is '修改时间';
comment on column party_members_enroll.updateUser          is '修改人';


/*==============================================================*/
/* Table: reportHasProject 党员报到和志愿者岗位关联表           */
/*==============================================================*/
create table memberHasVolunteerJobs  (
   memberId           NUMBER(10)                      not null,
   volunteerJobsId    NUMBER(10)                      not null,
   constraint fkMemberHasVolunteerJobs foreign key (volunteerJobsId)
   references volunteerJobs (id)
);
comment on table memberHasVolunteerJobs
  is '党员报到和志愿者岗位关联表';
comment on column memberHasVolunteerJobs.memberId
  is '党y员报到id';
comment on column memberHasVolunteerJobs.volunteerJobsId
  is '志愿者岗位id';

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'新增', 'addMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'修改', 'updateMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'删除', 'deleteMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'查询', 'searchMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'查看', 'viewMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'导出', 'downMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'报到', 'signMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'刷新', 'refreshMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));

update permissions set normalUrl='/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollTab.jsp' where ename='partyMemberRegistrationSituationManagement';

commit;
