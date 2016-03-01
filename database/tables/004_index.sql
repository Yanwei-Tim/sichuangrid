Create index indexRentalHouseOrgCode on RentalHouse(orginternalcode);
Create index indexRentalHouseHouseCodeOrgId on RentalHouse(houseCode,orgId);
Create index indexRHOrgCodeCreateDate on RentalHouse(createdate,orginternalcode);

Create index indexFloatingPopulationOrgCode on floatingpopulations(orginternalcode);
Create index indexFpOrgCodeCreateDate on floatingpopulations(orginternalcode,createdate);


create index IDX_WRED_ORGCODEDDID on WORKINGRECORDS (orginternalcode, dailydirectoryid);
----
create index IDX_FLOATING_BASEINFOID on FLOATINGPOPULATIONS (BASEINFOID);
create index IDX_FLOATING_ADSID on FLOATINGPOPULATIONS (ADDRESSINFOID);
create index IDX_DRUGGYS_BASEINFOID on DRUGGYS (BASEINFOID);
create index IDX_DRUGGYS_ADSID on DRUGGYS (ADDRESSINFOID);
create index IDX_AIDNEED_BASEINFOID on AIDNEEDPOPULATION (BASEINFOID);
create index IDX_AIDNEED_ADSID on AIDNEEDPOPULATION (ADDRESSINFOID);
create index IDX_DANGEROUS_BASEINFOID on DANGEROUSGOODSPRACTITIONERS (BASEINFOID);
create index IDX_DANGEROUS_ADSID on DANGEROUSGOODSPRACTITIONERS (ADDRESSINFOID);
create index IDX_HANDICAPPED_BASEINFOID on HANDICAPPEDS (BASEINFOID);
create index IDX_HANDICAPPED_ADSID on HANDICAPPEDS (ADDRESSINFOID);
create index IDX_IDLEYOUTH_BASEINFOID on IDLEYOUTHS (BASEINFOID);
create index IDX_IDLEYOUTH_ADSID on IDLEYOUTHS (ADDRESSINFOID);
create index IDX_MENTALPATIENT_BASEINFOID on MENTALPATIENTS (BASEINFOID);
create index IDX_MENTALPATIENT_ADSID on MENTALPATIENTS (ADDRESSINFOID);
create index IDX_UNEMPLOYED_BASEINFOID on UNEMPLOYEDPEOPLE (BASEINFOID);
create index IDX_UNEMPLOYED_ADSID on UNEMPLOYEDPEOPLE (ADDRESSINFOID);
create index IDX_SUPERIORVISIT_BASEINFOID on SUPERIORVISITS (BASEINFOID);
create index IDX_SUPERIORVISIT_ADSID on SUPERIORVISITS (ADDRESSINFOID);
create index IDX_RECTIFICATIVE_BASEINFOID on RECTIFICATIVEPERSONS (BASEINFOID);
create index IDX_RECTIFICATIVE_ADSID on RECTIFICATIVEPERSONS (ADDRESSINFOID);
create index IDX_POSITIVEINFO_BASEINFOID on POSITIVEINFOS (BASEINFOID);
create index IDX_POSITIVEINFO_ADSID on POSITIVEINFOS (ADDRESSINFOID);
create index IDX_OTHERPERSONNEL_BASEINFOID on OTHERATTENTIONPERSONNELS (BASEINFOID);
create index IDX_OTHERPERSONNEL_ADSID on OTHERATTENTIONPERSONNELS (ADDRESSINFOID);
create index IDX_OPTIMALOBJECT_BASEINFOID on OPTIMALOBJECTS (BASEINFOID);
create index IDX_OPTIMALOBJECT_ADSID on OPTIMALOBJECTS (ADDRESSINFOID);
-----------
create index  IDX_CENSUFAMILY_ORGCODE  on  censusregisterfamilys (orginternalcode);



---------------
create index idx_issuelogs_stepid on issuelogs(stepId);

create index idx_issuehastype_domainid on issuehastypes(ISSUETYPEDOMAINID);

create index idx_issuesteps_issue on issuesteps(issue);

create index idx_issueEvaluate_issueid on issueEvaluate(issueid);

create index idx_platformessgfile_pmid on platformMessagesAttachFiles(pmId);

create index idx_moduelClickCounts_userid on moduelClickCounts(userid);

create index idx_permanettaddr_addrname on PermanentAddress(addressname);

create index idx_permanettaddr_parentid on PermanentAddress(parentid);

create index idx_issueapplydelay_orgid on  issueapplydelay(auditorg);

create index IND_ISSTEP_TARGET on issuesteps(target,stateCode);

create bitmap index idx_issues_historic on issues(historic);

create index idx_organizations_orgname on organizations(orgname);

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
create index idx_workDiarys_orgid on workDiarys(orgId);
create index IDX_ISSUE_CREATEORG on ISSUES (CREATEORG);
create index IDX_RECOVERDATATA_CREATEDATE on RECOVERDATAINFOS (CREATEDATE);

create bitmap index INDEX_FLOATING_LOGOUT ON FLOATINGPOPULATIONS(LOGOUT);

create index idx_primemetype_memberid on primarymembersorgtype(primarymembersid);
create index idx_issue_currentstep on issues (currentstep);  
create bitmap  index INDEX_BASEINFO_BIRTHDAY on baseinfo(BIRTHDAY,politicalBackground);
---- index inboxplatformmessages
create index in_inbox_MESSAGETYPE on inboxplatformmessages (MESSAGETYPE);
create index in_inbox_senddate on inboxplatformmessages (senddate);
create index in_inbox_senderid on inboxplatformmessages (senderid);
create index in_inbox_readState on inboxplatformmessages (readState);
----index userhasplatformmessages
create index in_uhasm_readState on userhasplatformmessages (readState);
create index in_uhasm_deleteState on userhasplatformmessages (deleteState);
create index in_uhasm_senderid on userhasplatformmessages (senderid);
create index in_uhasm_userId on userhasplatformmessages (userId);
---index userHasPlatformMessageTypes
create index in_uhast_userId on userHasPlatformMessageTypes (userId);
---index outboxPlatformMessage
create index in_outbox_senderId on outboxPlatformMessages (senderId);
create index in_outbox_receivertype on outboxPlatformMessages (receivertype);
create index in_outbox_isDraft on outboxPlatformMessages (isDraft);
create index in_outbox_senddate on outboxPlatformMessages (senddate);
--签到表 加索引
create index IDX_USERSIGN_USERID on USERSIGN (USERID);
create index IDX_USERSIGN_CREATEDATE on USERSIGN (CREATEDATE);
create index IDX_USERSIGN_orgid on USERSIGN (orgid);
create index IDX_USERSIGN_orgLevelId on USERSIGN (orgLevelId);
create index IDX_USERSIGN_orgTypeId on USERSIGN (orgTypeId);
create index idx_userSign_parentOrgId on userSign(parentOrgId);
create index IDX_USERSIGN_ORGCODE on userSign(ORGINTERNALCODE);
---index moduelClickCounts
create index IDX_modCliCo_permissionid on moduelClickCounts (permissionid);

----组织机构模块组织主表索引
create index idx_primaryorg_orgid on primaryorganizations (orgid);
create index idx_primaryorg_parentid on primaryorganizations (parentid);
create index idx_primaryorg_orgCode on primaryorganizations (ORGINTERNALCODE);
create index idx_primaryorg_createDate on primaryorganizations (createDate);
create index idx_primaryorg_TEAMTYPE on primaryorganizations (TEAMTYPE);
create index idx_primaryorg_teamclass on primaryorganizations (teamclass);


create index idx_issuehandlestat_orgcode on issuehandlestat(orginternalcode,year,month);
create index idx_househaspopulation_type on househasimportantpopulation(houseid,populationtype);
create index idx_issuelogshistory_issueid on ISSUELOGS_HISTORY(issueId);
create index idx_issueclassstat_orgcode on issueclassificationstat(orgInternalCode,year,month);
create index idx_completdeissue_createorg on completedIssue(createOrginternalCode,createOrgLevel,crateOrgFunctionalType);

----楼宇索引
create index idx_builddatas_orgId on builddatas(orgId);
create index idx_builddatas_createdate on builddatas(createdate);

--成员库组织类型表 创建索引
create index idx_primembersorgtype_priorgid on primarymembersorgtype (primaryorgid);
--组织机构  部门类型创建索引
create index idx_organizations_orgtype on organizations (orgtype);

--部件信息索引
create index idx_dustbin_orgcode_imp on dustbin(orgInternalCode,partType,isemphasis);
create index idx_dustbin_orgcode_type on dustbin(orgInternalCode,partType);

Create index idx_peopleLog_userId on peopleLog(userId);

---加索引
create index IDX_usedinfoweek_orgId on usedinfoweek (orgId);
create index IDX_usedinfoweek_parentOrgId on usedinfoweek (parentOrgId);
create index IDX_usedinfomonth_orgId on usedinfomonth (orgId);
create index IDX_usedinfomonth_parentOrgId on usedinfomonth (parentOrgId);

--签到历史表 加索引
create index IDX_USERSIGN_H_USERID on USERSIGN_HISTORY (USERID);
create index IDX_USERSIGN_H_CREATEDATE on USERSIGN_HISTORY (CREATEDATE);
create index IDX_USERSIGN_H_orgid on USERSIGN_HISTORY (orgid);
create index IDX_USERSIGN_H_orgLevelId on USERSIGN_HISTORY (orgLevelId);
create index IDX_USERSIGN_H_orgTypeId on USERSIGN_HISTORY (orgTypeId);
create index idx_USERSIGN_H_parentOrgId on USERSIGN_HISTORY(parentOrgId);
create index IDX_USERSIGN_H_ORGCODE on USERSIGN_HISTORY(ORGINTERNALCODE);
