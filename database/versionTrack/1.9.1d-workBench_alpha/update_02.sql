--领导视图缓存建表以及序列增量脚本
-- Create table
create table LEADERVIEWCACHE
(
  ID             NUMBER(10) not null,
  ORGID          NUMBER(10),
  MODULENAME     VARCHAR2(60),
  INVALIDATETIME NUMBER(30) not null,
  CACHEKEY       VARCHAR2(90),
  CACHEVALUE     CLOB,
  CREATEUSER     VARCHAR2(32),
  UPDATEUSER     VARCHAR2(32),
  CREATEDATE     DATE,
  UPDATEDATE     DATE
);
-- Add comments to the table 
comment on table LEADERVIEWCACHE
  is '领导视图缓存文件表';
-- Add comments to the columns 
comment on column LEADERVIEWCACHE.ID
  is '主键ID';
comment on column LEADERVIEWCACHE.ORGID
  is '组织机构ID';
comment on column LEADERVIEWCACHE.MODULENAME
  is '模块名称';
comment on column LEADERVIEWCACHE.INVALIDATETIME
  is '失效时间';
comment on column LEADERVIEWCACHE.CACHEKEY
  is 'key值';
comment on column LEADERVIEWCACHE.CACHEVALUE
  is '缓存数据';
-- Create/Recreate primary, unique and foreign key constraints 
alter table LEADERVIEWCACHE
  add constraint LEADERVIEWCACHE_ID primary key (ID)
  using index ;
create index LEADERVIEWCACHE_CACHEKEY on LEADERVIEWCACHE (CACHEKEY);
  
  -- Create sequence 
create sequence S_LEADERVIEWCACHE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

commit;


--添加SQL优化的索引
----备注：以下索引线上已执行
create index idx_contacters_fromuserid on contacters(fromUserId);
create index idx_keyplaces_id_key on KEYPLACES (id_key);
create index idx_regradedpoints_logid on regradedpoints(logid);
create index IDX_ISSUES_selnum on issues(serialnumber);
create index ind_unsetlpoptns_orgcode on unsettledPopulations(orgInternalCode);
create index idx_issueStepGroups_issue on issueStepGroups(issue);
create index idx_IssueRelatedPeople_issue on IssueRelatedPeople(Issueid);
create index idx_issuehasattfile_issueid on IssueHasAttachFiles(issueid);
create index idx_attachFiles_type on attachFiles(moduleKey,moduleObjectId);
create index idx_issues_createorgcode on issues(createorginternalcode);
create index IDX_USERHMPROFILEPMSION on userHasMyProfilePermissions(Orgid);
create bitmap index idx_myProfiles_underjsdiction on myProfiles(underjurisdiction);
create index idx_dm_COMPANYPL_TEMP_DIORGID on DM_COMPANYPLACE_TEMP(DISTRICTORGID);
create index idx_workDiarys_orgid on workDiarys(orgId);
create index IDX_ISSUE_CREATEORG on ISSUES (CREATEORG);
create index IDX_RECOVERDATATA_CREATEDATE on RECOVERDATAINFOS (CREATEDATE);


--修改人口状态为null的值
update householdstaffs set logout = 0 where logout is null;
update floatingpopulations set logout = 0 where logout is null;
update unsettledPopulations set logout = 0 where logout is null;
update overseaPersonnel set logout = 0 where logout is null;

update positiveinfos set isemphasis = 0 where isemphasis is null;
update rectificativepersons set isemphasis = 0 where isemphasis is null;
update mentalpatients set isemphasis = 0 where isemphasis is null;
update druggys set isemphasis = 0 where isemphasis is null;
update aidspopulations set isemphasis = 0 where isemphasis is null;
update idleyouths set isemphasis = 0 where isemphasis is null;
update superiorvisits set isemphasis = 0 where isemphasis is null;
update dangerousgoodspractitioners set isemphasis = 0 where isemphasis is null;
update otherattentionpersonnels set isemphasis = 0 where isemphasis is null;
update elderlypeople e set e.isemphasis = 0 where e.isemphasis is null;
update handicappeds h set h.isemphasis = 0 where h.isemphasis is null;
update optimalobjects o set o.isemphasis = 0 where o.isemphasis is null;
update aidneedpopulation a set a.isemphasis = 0 where a.isemphasis is null;
update unemployedpeople set isemphasis = 0 where isemphasis is null;
update nurtureswomen set isemphasis = 0 where isemphasis is null;

commit;


