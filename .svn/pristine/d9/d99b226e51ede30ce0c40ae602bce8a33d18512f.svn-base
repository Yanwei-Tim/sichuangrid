--新建已办结的表

create table completedIssue(
	publicltycass  number(10),
	issueId  number(10),
	topState  number(10),
	delayState  number(10),
	serialNumber  varchar(30),
	subject  varchar(150),
	status  number(10),
	occurDate  date,
	currentOrg  number(10),
	occurOrg  number(10),
	createOrg  number(10),
	targetOrg  number(10),
	dealTime  date,
	sourcePerson  varchar(60),
	sourceKind  number(10),
	sourceMobile  varchar(11),
	urgent  number(10),
	stepId  number(10),
	superviseLevel  number(10),
	lastOrg  number(10),
	createUser  varchar(50),
	dealState  number(10),
	evaluateTime  date,
	evaluateType  number(10),
	evaluateContent  varchar(600),
	hours  varchar(10),
	minute  varchar(10),
	topDate date,
	porgId  number(10),
	torgId  number(10),
	createOrginternalCode  varchar(32),
	crateOrgFunctionalType number(10),
	createOrgLevel number(10)
);


--将已办结的时间迁移到单表
insert into completedissue
  (publicltycass,
   issueId,
   serialNumber,
   subject,
   status,
   occurDate,
   currentOrg,
   occurOrg,
   createOrg,
   targetOrg,
   dealTime,
   sourcePerson,
   sourceKind,
   sourceMobile,
   urgent,
   stepId,
   superviseLevel,
   lastOrg,
   createUser,
   dealState,
   evaluateType,
   evaluateContent,
   evaluateTime,
   hours,
   minute,
   delayState,
   topState,
   topDate,
   createOrgLevel,
   createOrginternalCode,
   crateOrgFunctionalType,
   porgId,
   torgId)
  (select case
            when p.id is null then
             0
            else
             1
          end as publicltycass,
          iu.id as issueId,
          iu.serialnumber,
          iu.subject,
          iu.status,
          iu.occurdate,
          cstep.source currentOrg,
          iu.occurorg,
          iu.createOrg,
          cstep.target targetOrg,
          cstep.lastdealdate,
          iu.sourceperson,
          iu.sourcekind,
          iu.sourcemobile,
          iu.urgent,
          cstep.id as stepId,
          cstep.superviselevel,
          iu.lastOrg,
          iu.CREATEUSER,
          cstep.statecode,
          ie.evaluatetype,
          ie.evaluatecontent,
          ie.evaluatetime,
          iu.hours,
          iu.minute,
          cstep.delayState,
          ti.topState,
          ti.topdate,
          iu.CREATEORGLEVEL createOrgLevel,
          iu.createorginternalcode createOrginternalCode,
          iu.createorgfunctionalorgtype crateOrgFunctionalType,
          p.orgid porgId,
          ti.orgid torgId
     from issuesteps cstep, issues iu
     left join issueEvaluate ie
       on ie.issueid = iu.id
     left join publicltycass p
       on iu.id = p.issueid
   
     left join topIssues ti
       on ti.issueId = iu.id
         
      and ti.issueTag = 3
    where cstep.issue = iu.id
      and cstep.stateCode >= 1000
      and iu.historic = 0);
commit;

--已办结表，置顶的字段设置默认值为0
alter table completedIssue modify topState default(0);

--清洗已办结表中置顶字段为null的数据，并设置为0
update completedIssue ci set ci.topState=0 where ci.topState is null;

commit;