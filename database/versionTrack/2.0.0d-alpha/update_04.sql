--已办结表新增，事件规模，发生地点，涉及人数三个字段
alter table completedissue add (issueKind    		 NUMBER(10) );
alter table completedissue add (occurLocation        VARCHAR2(150) );
alter table completedissue add (relatePeopleCount    NUMBER(10) );
--迁移标志
alter table completedissue add (moveMark number(1) default 0 );

--把事件表中事件规模，发生地点，涉及人数三个字段数据同步到已办结事件中
update completedIssue ci set (ci.issuekind, ci.occurlocation, ci.relatepeoplecount )= (select iu.issuekind, iu.occurlocation, iu.relatepeoplecount from issues iu where iu.id=ci.issueid);
commit;

--复制主要表结构
create table completedissue_History AS SELECT * FROM completedissue  where 1<>1;
create table issues_History AS SELECT * FROM issues  where 1<>1;
create table issuesteps_History AS SELECT * FROM issuesteps  where 1<>1;
create table issuelogs_History AS SELECT * FROM issuelogs  where 1<>1;
create table issuestepgroups_History AS SELECT * FROM issuestepgroups  where 1<>1;

--主键或约束
alter table ISSUES_HISTORY
  add constraint PK_ISSUES_HISTORY primary key (ID);
alter table ISSUESTEPS_HISTORY
  add constraint PK_ISSUESTEPS_HISTORY primary key (ID);
alter table ISSUELOGS_HISTORY
  add constraint PK_ISSUELOGS_HISTORY primary key (ID);
alter table ISSUESTEPGROUPS_HISTORY
  add constraint PK_ISSUESTEPGROUPS_HISTORY primary key (ID);
alter table COMPLETEDISSUE_HISTORY
  add constraint PK_COMPLETEDISSUE_HISTORY primary key (ISSUEID);



--删除issues相关外键
alter table historicalissues drop constraint fkhistoricalissuesissue;
alter table issueevaluate drop constraint fkissueevaluateissue;
alter table issuehasattachfiles drop constraint fkissuehasattachfiles;
alter table issuehastypes drop constraint fkissue ;
alter table overtimeissuelog drop constraint fkovertimeissuelogissue ;
alter table publicltycass drop constraint fkpublicltycassissue;
alter table relatepersons drop constraint fkrelatepersonsesissue;
alter table relateplaces drop constraint fkrelateplacesesissue ;
--删除issueLogs外键
alter table historicalissues drop constraint fkhistoricalissuesissuelog;
alter table overtimeissuelog drop constraint fkovertimeissuelogissuelog;
alter table publicltycass drop constraint fkpublicltycassissuelog;


--备份表索引
CREATE INDEX IDX_ISSUESTEPS_HISTORY_ISSUE ON ISSUESTEPS_HISTORY(ISSUE);
CREATE INDEX IDX_ISSUESTEPS_HISTORY_CODE ON ISSUESTEPS_HISTORY(TARGETINTERNALCODE);
CREATE INDEX IDX_ISSUESTEPS_HISTORY_TYPE ON ISSUESTEPS_HISTORY(TARGETORGFUNCTIONALORGTYPE);
CREATE INDEX IDX_ISSUESTEPS_HISTORY_LEVEL ON ISSUESTEPS_HISTORY(TARGETORGLEVEL);
CREATE INDEX IDX_ISSUESTEPS_HISTORY_ASSIGN ON ISSUESTEPS_HISTORY(ASSIGN);

CREATE INDEX IDX_ISSUELOGS_HISTORY_STEPID ON ISSUELOGS_HISTORY(STEPID);


--迁移主表
INSERT INTO ISSUES_HISTORY 
SELECT IU.* FROM ISSUES IU ,COMPLETEDISSUE CI  
WHERE IU.ID = CI.ISSUEID
AND  CI.DEALTIME <= add_months(sysdate,-3) AND CI.MOVEMARK = 0 ;
--迁移事件步骤表
INSERT INTO ISSUESTEPS_HISTORY 
SELECT STEPS.* FROM ISSUESTEPS STEPS,COMPLETEDISSUE CI  
WHERE STEPS.ISSUE  = CI.ISSUEID
AND  CI.DEALTIME <= add_months(sysdate,-3) AND CI.MOVEMARK = 0 ;
--迁移事件日志表
INSERT INTO ISSUELOGS_HISTORY SELECT IULOG.* FROM ISSUELOGS IULOG , COMPLETEDISSUE CI
WHERE IULOG.ISSUEID = CI.ISSUEID
AND  CI.DEALTIME <= add_months(sysdate, -3) AND CI.MOVEMARK = 0 ;
--迁移事件步骤组表
INSERT INTO ISSUESTEPGROUPS_HISTORY SELECT IUGROUP.* FROM ISSUESTEPGROUPS IUGROUP,COMPLETEDISSUE CI
WHERE IUGROUP.ISSUE = CI.ISSUEID
AND  CI.DEALTIME <= add_months(sysdate, -3) AND CI.MOVEMARK = 0 ;
--跟新迁移状态
UPDATE COMPLETEDISSUE CI SET CI.MOVEMARK = 1 WHERE CI.DEALTIME <= add_months(sysdate, -3) AND CI.MOVEMARK = 0 ;

---删除事件主表
DELETE FROM ISSUES IU
 WHERE  EXISTS (SELECT 1 FROM ISSUES_HISTORY IUH WHERE IU.ID = IUH.ID);
DELETE FROM ISSUESTEPS STEPS
 WHERE EXISTS (SELECT 1 FROM ISSUESTEPS_HISTORY STEPSH WHERE STEPS.ID = STEPSH.ID);
DELETE FROM ISSUELOGS IULOG
 WHERE EXISTS (SELECT 1 FROM ISSUELOGS_HISTORY IULOGH WHERE IULOG.ID = IULOGH.ID);
DELETE FROM ISSUESTEPGROUPS IUGROUP
 WHERE EXISTS (SELECT 1 FROM ISSUESTEPGROUPS_HISTORY IUGROUPH WHERE IUGROUP.ID = IUGROUPH.ID);
 

