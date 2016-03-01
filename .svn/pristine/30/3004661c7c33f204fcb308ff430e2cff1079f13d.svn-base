
--TBSchedule日志序列新增
CREATE SEQUENCE S_TBSCHEDULELOG
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE SEQUENCE S_TBSCHEDULELOGEXCEPTION
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;

--任务调度日志表
CREATE TABLE TBSCHEDULELOG  (
   ID                 NUMBER(10)                      NOT NULL,
   JOBNAME            VARCHAR2(4000),
   TASKPARAMETER      VARCHAR2(4000),
   OWNSIGN            VARCHAR2(40),
   TASKITEMNUM        NUMBER(6),
   TASKITEMS          VARCHAR2(2000),
   EACHFETCHDATANUM   NUMBER(10),
   STARTTIME          DATE,
   ENDTIME            DATE,
   REMARK             VARCHAR2(4000),
   APPNAME            VARCHAR2(40),
   LISTSIZE           NUMBER(10),
   EXCEPTIONNUM       NUMBER(10),
   CONSTRAINT PK_TBSCHEDULELOG PRIMARY KEY (ID)
);

--任务调度异常信息表
CREATE TABLE TBSCHEDULELOGEXCEPTION
(
  ID        NUMBER(10) NOT NULL,
  LOGID       NUMBER(10) NOT NULL,
  ERRORMSG  VARCHAR2(4000) NOT NULL,
  ERRORTIME DATE NOT NULL,
   CONSTRAINT PK_TBSCHEDULELOGEXCEPTION PRIMARY KEY (ID)
);
-- 外键关联
ALTER TABLE TBSCHEDULELOGEXCEPTION
  ADD CONSTRAINT FK_TBSCHEDULELOG_EXCEPTION FOREIGN KEY (LOGID)
  REFERENCES TBSCHEDULELOG (ID);
  
--不合法身份证表
CREATE TABLE IdCardNoExpLog(
  expMessage  VARCHAR2(4000) NOT NULL,
  expIdCardNo  varchar2(20) NOT NULL
);

create index IdCardNoExpLog_idCardNo on IdCardNoExpLog(expIdCardNo);
create index idx_primemetype_memberid on primarymembersorgtype(primarymembersid);
create index idx_issue_currentstep on issues (currentstep);  
create index idx_position_locatedate on positioningtrajectory(username,locatedate);
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
---index moduelClickCounts
create index IDX_modCliCo_permissionid on moduelClickCounts (permissionid);

--各州市重点数据统计情况历史表索引
----2015_3
create index idx_bss_2015_3_statisticsType on Basesituationstatement_2015_3 (statisticsType);
create index idx_bss_2015_3_orgId on Basesituationstatement_2015_3 (orgId);
----2015_2
create index idx_bss_2015_2_statisticsType on Basesituationstatement_2015_2 (statisticsType);
create index idx_bss_2015_2_orgId on Basesituationstatement_2015_2 (orgId);
----2015_1
create index idx_bss_2015_1_statisticsType on Basesituationstatement_2015_1 (statisticsType);
create index idx_bss_2015_1_orgId on Basesituationstatement_2015_1 (orgId);
----2014_12
create index idx_bss_2014_12_statisticsType on Basesituationstatement_2014_12 (statisticsType);
create index idx_bss_2014_12_orgId on Basesituationstatement_2014_12 (orgId);
----2014_11
create index idx_bss_2014_11_statisticsType on Basesituationstatement_2014_11 (statisticsType);
create index idx_bss_2014_11_orgId on Basesituationstatement_2014_11 (orgId);

--服务记录
create index idx_serviceRcdRelyObj_recordid on serviceRecordRelyObject (recordid);
create index idx_servicerecords_orgid on servicerecords (orgid);

----组织机构模块组织主表索引
create index idx_primaryorg_orgid on primaryorganizations (orgid);
create index idx_primaryorg_parentid on primaryorganizations (parentid);
create index idx_primaryorg_orgCode on primaryorganizations (ORGINTERNALCODE);
create index idx_primaryorg_createDate on primaryorganizations (createDate);
create index idx_primaryorg_TEAMTYPE on primaryorganizations (TEAMTYPE);
create index idx_primaryorg_teamclass on primaryorganizations (teamclass);

--党委部门 数据统计情况历史表索引
-----2015_3
create index idx_dparty_2015_3_pOrgtype on Departmentparty_2015_3 (primaryorgtype);
create index idx_dparty_2015_3_orgCode on Departmentparty_2015_3 (ORGINTERNALCODE);
------2015_2
create index idx_dparty_2015_2_pOrgtype on Departmentparty_2015_2 (primaryorgtype);
create index idx_dparty_2015_2_orgCode on Departmentparty_2015_2 (ORGINTERNALCODE);
----2015_1
create index idx_dparty_2015_1_pOrgtype on Departmentparty_2015_1 (primaryorgtype);
create index idx_dparty_2015_1_orgCode on Departmentparty_2015_1 (ORGINTERNALCODE);

---事件步骤表和事件历史步骤表
create index idx_issuesteps_createdate on issuesteps (createdate);
create index idx_step_history_createdate on issuesteps_history (createdate);