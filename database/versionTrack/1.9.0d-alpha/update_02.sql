update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=CompanyPlace' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newCompanyPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=PublicPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newPublicPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=TrafficPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newTrafficPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=EntertainmentPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newEntertainmentPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=TradePlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newTradePlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=InternetServicesPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newInternetServicesPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=AccommodationServicesPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newAccommodationServicesPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=FoodServicesPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newFoodServicesPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=TravelingPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newTravelingPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=ConstructionPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newConstructionPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=OtherPlace'||chr(38)||'modulType=place' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newOtherPlaceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=PartyGovernmentOrganCompany'||chr(38)||'modulType=company' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newPartyGovernmentOrganCompanyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=EducationCompany'||chr(38)||'modulType=company' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newEducationCompanyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=MedicalHygieneCompany'||chr(38)||'modulType=company' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newMedicalHygieneCompanyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=DangerousStoreCompany'||chr(38)||'modulType=company' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newDangerousStoreCompanyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=OtherCompany'||chr(38)||'modulType=company' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newOtherCompanyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=SafetyProductionKey' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newSafetyProductionKeyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=FireSafetyKey' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newFireSafetyKeyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=SecurityKey' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newSecurityKeyManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=PollutionSource' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newPollutionSourceManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=Enterprise' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newEnterpriseManagement'
);
update permissions p set p.leaderUrl='/baseinfo/leaderView/companyPlace/companyPlaceStatistics.jsp?modul=EnterpriseDown' where p.id =(
       select ps.id from permissions ps where ps.ename = 'newEnterpriseDownManagement'
);

commit;




----单位场所研判分析权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'单位场所', 'companyPlaceStanalsManage', 1, '研判分析', 1, ' ', (select id from permissions where ename='statAnalyseManage'),'',16);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'总况', 'companyPlaceStatistic', 1, '单位场所', 1, ' ', (select id from permissions where ename='companyPlaceStanalsManage'),'/hotModuel/statAnalyse/companyPlaceStanalsManage/index.jsp',0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'单位', 'companyStatistic', 1, '单位场所', 1, ' ', (select id from permissions where ename='companyPlaceStanalsManage'),'/hotModuel/statAnalyse/companyPlaceStanalsManage/commonCompanyPlace.jsp?moduleType=company',1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'场所', 'placeStatistic', 1, '单位场所', 1, ' ', (select id from permissions where ename='companyPlaceStanalsManage'),'/hotModuel/statAnalyse/companyPlaceStanalsManage/commonCompanyPlace.jsp?moduleType=place',2);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'重点单位场所', 'keyCompanyPlaceStatistic', 1, '单位场所', 1, ' ', (select id from permissions where ename='companyPlaceStanalsManage'),'/hotModuel/statAnalyse/companyPlaceStanalsManage/commonCompanyPlace.jsp?moduleType=keyCompanyPlace',3);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,NORMALURL,INDEXID)
values (s_permissions.NEXTVAL,'规模企业', 'enterpriseStatistic', 1, '单位场所', 1, ' ', (select id from permissions where ename='companyPlaceStanalsManage'),'/hotModuel/statAnalyse/companyPlaceStanalsManage/commonCompanyPlace.jsp?moduleType=enterprise',4);

commit;

/*为手机端版本更新增加两个参数 orgId  departmentNo*/
ALTER TABLE mobileupdate ADD (orgid number(10));
COMMENT ON COLUMN mobileupdate.orgid IS '组织机构id';

ALTER TABLE mobileupdate ADD (departmentNo varchar2(32));
COMMENT ON COLUMN mobileupdate.departmentNo IS '部门编号';
ALTER TABLE mobileupdate MODIFY CATEGORY varchar2(50) NULL; 

--日志中增加前后修改字段
alter table systemlogs add beforekey varchar2(60);
comment on column systemlogs.beforekey is '修改之前的key';
alter table systemlogs add afterkey varchar2(60);
comment on column systemlogs.afterkey is '修改之后的key';
alter table systemlogs add beforename varchar2(60);
comment on column systemlogs.beforename is '修改之前的name';
alter table systemlogs add aftername varchar2(60);
comment on column systemlogs.aftername is '修改之后的name';

---提醒或者删除的设定时间的表
alter table EARLYWARNING
  add unique (NAME);
----修改平台消息和导入日志的过期时间
update earlywarning e
   set e.remindtime = 30
 where e.name in ('platformMessageIndividual', 'platformMessageDepartment',
        'excelImportLogClean');
commit;
--四支队架构权限更新脚本
update permissions p set p.normalurl='/fourTeamsManage/fourTeamsOrgCharList.jsp' where p.ename='organizationChartManagement' and p.cname='组织架构';
commit;
--事件中置顶表中issuetag字段扩容
alter table topissues modify (issuetag number(2));
