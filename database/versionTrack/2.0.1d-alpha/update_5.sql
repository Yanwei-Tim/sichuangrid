-----为用户签到表新增字段和索引
alter table userSign add orgId NUMBER(10);
alter table userSign add orgLevelId NUMBER(10);
alter table userSign add orgTypeId NUMBER(10);
alter table userSign add parentOrgId NUMBER(10);
alter table userSign add orgInternalCode VARCHAR2(32);
----index
create index IDX_USERSIGN_USERID on USERSIGN (USERID);
create index IDX_USERSIGN_CREATEDATE on USERSIGN (CREATEDATE);
create index idx_userSign_orgId on userSign(orgId);
create index idx_userSign_orgLevelId on userSign(orgLevelId);
create index idx_userSign_orgTypeId on userSign(orgTypeId);
create index idx_userSign_parentOrgId on userSign(parentOrgId);
create index idx_userSign_orgCode on userSign(orgInternalCode);

---用户表建索引
create index idx_User_state on users (state);
create index idx_User_activationtime on users (activationtime);
---服务记录和服务服务对象的关联关系表索引
create index idx_servObjRely_objecttype on serviceRecordRelyObject (objecttype);
create index idx_servObjRely_recordid on serviceRecordRelyObject (recordid);
create index idx_servicerecords_createdate on servicerecords (createdate);
create index idx_servicerecords_orgcode on servicerecords (orgcode);

create index idx_issuehandlestat_orgcode on issuehandlestat(orginternalcode,year,month);
create index idx_househaspopulation_type on househasimportantpopulation(houseid,populationtype);
create index idx_issuelogshistory_issueid on ISSUELOGS_HISTORY(issueId);
create index idx_servicerecordobject_objid on servicerecordrelyobject(objectid,objecttype);
create index idx_gis2Dlayers_org on gis2dlayers(organization);
create index idx_issueclassstat_orgcode on issueclassificationstat(orgInternalCode,year,month);
create index idx_completdeissue_createorg on completedIssue(createOrginternalCode,createOrgLevel,crateOrgFunctionalType);


update userSign set orgId=(select organizationid from users where id=userId);

update userSign
   set orgTypeId   = (select orgType from organizations where id = orgid),
       orgLevelId  = (select orgLevel from organizations where id = orgid),
       parentOrgId = (select parentid from organizations where id = orgid),
       ORGINTERNALCODE = (select ORGINTERNALCODE from organizations where id = orgid);
commit;
