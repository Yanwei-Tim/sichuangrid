--delete from permissions where ename = 'comprehensiveQueryManagement';

--insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
--values (s_permissions.NEXTVAL, '综合查询', 'comprehensiveQueryManagement', 1, '新三本台账', 1, (select id from permissions where ename='serviceWorkThreeRecords'),
-- '', '/account/query/comprehensiveQuery.jsp', '', 6, '');

update permissions set parentid = (select id from permissions where cname = '新三本台账') where ename = 'comprehensiveQueryManagement';
 
alter table ledgertraffic modify OTHERCONTENT varchar2(500);

alter table LEDGEROTHER modify OTHERCONTENT varchar2(500);


create sequence s_threeRecordsYearTurn
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;

--接转记录表
create table threeRecordsYearTurn(
     ID                   NUMBER(10)		   not null,
     year	              NUMBER(10)           not null,
     turnOrg              NUMBER(10)           not null,
     ledgerType			  NUMBER(10)           not null,
     newLedgerId          NUMBER(30)           not null,
     oldLedgerId          NUMBER(10)           not null,
     createOrg            NUMBER(10)           not null,
     createUser           VARCHAR2(32),
     updateUser           VARCHAR2(32),
     createDate           DATE,
     updateDate           DATE,
     constraint pk_threeRecordsYearTurn primary key (id)
);


--三本台账上年接转job，每年的1月1日凌晨12:05执行
insert into taskploy(id, cname, ename, type, description, code)values(s_TASKPLOY.nextval,
'三本台账上年接转job','threeRecordsLastYearTurnJob',(select id from propertydicts where displayname = 'java方法'),      
'三本台账上年接转job','threeRecordsLastYearTurnJob.lastYearTurn');

insert into task (id, name, taskgroup, description, ployId, config, closed)values(s_TASK.nextval,
 'threeRecordsLastYearTurnJob','threeRecordsLastYearTurnJob','threeRecordsLastYearTurnJob',
 (select id from taskploy where ename = 'threeRecordsLastYearTurnJob'),'0 5 1 1 1 ?', 1);
 
 --三本台账月报表job，每月最后一天23:55执行
 insert into taskploy(id, cname, ename, type, description, code)values(s_TASKPLOY.nextval,
'三本台账月报表job','threeRecordsMonthReportJob',(select id from propertydicts where displayname = 'java方法'),      
'三本台账月报表job','threeRecordsMonthReportJob.initMonthReport');

insert into task (id, name, taskgroup, description, ployId, config, closed)values(s_TASK.nextval,
 'threeRecordsMonthReportJob','threeRecordsMonthReportJob','threeRecordsMonthReportJob',
 (select id from taskploy where ename = 'threeRecordsMonthReportJob'),'0 55 23 L * ?', 1);
 
commit;
