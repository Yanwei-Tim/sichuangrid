--任务清单权限和菜单
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '任务清单', 'taskListVisitManagement', 1, ' ', 1, null, null, null, null, null, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
	values (s_permissions.NEXTVAL, '流动人员管理', 'floatingPopulationForTast', 1, '任务清单', 1, (select id from permissions where ename='taskListVisitManagement'), null, null, null, 0, null);  

	
--流动人员管理下 宣传核查
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '宣传核查', 'propagandaAndVerification', 1, '流动人员管理', 1, (select id from permissions where ename='floatingPopulationForTast'), null, '/task/propagandaAndVerificationManage/propagandaAndVerificationList.jsp', '/task/propagandaAndVerificationManage/propagandaAndVerificationList.jsp', 0, null);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addPropagandaAndVerification', 0, '宣传核查', 1, (select id from permissions where ename='propagandaAndVerification'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updatePropagandaAndVerification', 0, '宣传核查', 1, (select id from permissions where ename='propagandaAndVerification'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'delPropagandaAndVerification', 0, '宣传核查', 1, (select id from permissions where ename='propagandaAndVerification'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'updatePropagandaAndVerificationSignDetail', 0, '宣传核查', 1, (select id from permissions where ename='propagandaAndVerification'), null, null, null, 3);

--流动人员管理下 民警带领下开展工作情况
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '民警带领下开展工作情况', 'workByPoliceManagement', 1, '流动人员管理', 1, (select id from permissions where ename='floatingPopulationForTast'), null, '/hotModuel/task/workingSituation/workingSituation.ftl', '/hotModuel/task/workingSituation/workingSituation.ftl', 1, null);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addWorkingSituation', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateWorkingSituation', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewWorkingSituation', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteWorkingSituation', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchWorkingSituation', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, null, null, 4);

--治安隐患    
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '发现治安隐患', 'hiddenDangerManagement', 1, '任务清单', 1, (select id from permissions where ename='taskListVisitManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl', '/hotModuel/task/hiddenDanger/hiddenDanger.ftl', 5, null);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDanger', 0, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDanger', 0, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDanger', 0, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDanger', 0, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDanger', 0, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, null, null, 4);

--社区服刑人员
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'社区服刑人员','termerRecordManage',1,'任务清单',1,(select id from permissions where ename = 'taskListVisitManagement'),
   '','/hotModuel/task/termerRecordList.ftl','', 3,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'新增','addTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 0,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查看','viewTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 1,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查询','searchTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 2,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'签收','signTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 3,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'删除','deleteTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 4,'');
   

--刑释人员
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'刑释人员','positiveInfoRecordManage',1,'任务清单',1,(select id from permissions where ename = 'taskListVisitManagement'),
   '','/hotModuel/task/positiveInfoRecordList.ftl','', 4,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'新增','addPositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 0,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查看','viewPositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 1,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查询','searchPositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 2,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'签收','signPositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 3,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'删除','deletePositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 4,'');

--吸毒人员、严重精神障碍患者
insert into permissions values(s_permissions.nextval,'吸毒人员','druggyTaskVisitmanagement',1,'任务清单',1,(select id from permissions where ename='taskListVisitManagement'),
    null,'/hotModuel/task/druggyTaskList.ftl',null,1,null);
insert into permissions values(s_permissions.nextval,'严重精神障碍患者','mentalPatientTaskVisitmanagement',1,'任务清单',1,(select id from permissions where ename='taskListVisitManagement'),
    null,'/hotModuel/task/mentalPatientTaskList.ftl',null,2,null);
    
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '查看','viewDruggyTask',0,'吸毒人员',1,(select id from permissions where ename='druggyTaskVisitmanagement'),'','','',0,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '新增','addDruggyTask',0,'吸毒人员',1,(select id from permissions where ename='druggyTaskVisitmanagement'),'','','',1,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '刷新','refreshDruggyTask',0,'吸毒人员',1,(select id from permissions where ename='druggyTaskVisitmanagement'),'','','',2,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '签收','signDruggyTask',0,'吸毒人员',1,(select id from permissions where ename='druggyTaskVisitmanagement'),'','','',3,'');

insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '查看','viewMentalPatientTask',0,'严重精神障碍患者',1,(select id from permissions where ename='mentalPatientTaskVisitmanagement'),'','','',0,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '新增','addMentalPatientTask',0,'严重精神障碍患者',1,(select id from permissions where ename='mentalPatientTaskVisitmanagement'),'','','',1,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '刷新','refreshMentalPatientTask',0,'严重精神障碍患者',1,(select id from permissions where ename='mentalPatientTaskVisitmanagement'),'','','',2,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '卫生所签收','mentalPatientJusticeTask',0,'严重精神障碍患者',1,(select id from permissions where ename='mentalPatientTaskVisitmanagement'),'','','',7,'');
insert into permissions
    (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '派出所签收','mentalPatientPoliceTask',0,'严重精神障碍患者',1,(select id from permissions where ename='mentalPatientTaskVisitmanagement'),'','','',8,'');
--加任务清单报表
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'任务清单报表','tasksListsVisitManagement',1,'任务清单',1,(select id from permissions where ename = 'taskListVisitManagement'),
   '','/hotModuel/task/taskVisitList.ftl','', 6,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '宣传核查报表','propagandaAndVerificationReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',0,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '民警带领开展工作报表','workingSituationReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',1,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '吸毒人员报表','druggyTaskReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',2,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '严重精神障碍患者卫生所报表','mentalPatientJusticeTaskReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',3,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '严重精神障碍患者派出所报表','mentalPatientPoliceTaskReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',4,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '社区服刑人员报表','termerRecordReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',5,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '刑释人员报表','positiveInfoRecordReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',6,'');

insert into permissions
   (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values (s_permissions.nextval, '发现治安隐患报表','hiddenDangerReportForm',0,'任务清单报表',1,(select id from permissions where ename='tasksListsVisitManagement'),'','','',7,'');


commit;

--社区服刑人员记录
create table termerRecord(
    id   number(10),
    orgId  number(10),
    orgCode varchar2(32),
    recordDate date,
    address     varchar2(150),
    name  varchar2(32),
    ishome number(2),
    mark  varchar2(900),
    status  number(2) default 0,
    attitude  varchar2(150),
    signDate  date,
    signMemberName  varchar2(32),
    gridMemberPhone  varchar2(15),
    createDate  date,
    createUser  varchar2(32),
    updateDate  date,
    updateUser  varchar2(32),
    constraint  pk_termerRecord primary key(id)
);

--序列
create sequence s_termerRecord
Minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

comment on table termerRecord is
'社区服刑人员记录(任务清单使用)';
comment on column termerRecord.recordDate is
'时间';
comment on column termerRecord.address is
'地点';
comment on column termerRecord.name is
'姓名';
comment on column termerRecord.ishome is
'是否在家';
comment on column termerRecord.mark is
'备注';
comment on column termerRecord.status is
'签收的状态(0未签收，1已签收)';
comment on column termerRecord.attitude is
'签收意见';
comment on column termerRecord.signDate is
'签收的时间';
comment on column termerRecord.signMemberName is
'签收人姓名';
comment on column termerRecord.gridMemberPhone is
'新增记录的用户联系电话';

--刑释人员记录
create table positiveInfoRecord(
    id   number(10),
    orgId  number(10),
    orgCode varchar2(32),
    recordDate date,
    address     varchar2(150),
    name  varchar2(32),
    ishome number(2),
    isLivelihood number(2),
    familyPhone  varchar2(50),
    mark  varchar2(900),
    status  number(2) default 0,
    attitude  varchar2(150),
    signDate  date,
    signMemberName  varchar2(32),
    gridMemberPhone  varchar2(15),
    createDate  date,
    createUser  varchar2(32),
    updateDate  date,
    updateUser  varchar2(32),
    constraint  pk_positiveInfoRecord primary key(id)
);

--序列
create sequence s_positiveInfoRecord
Minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

comment on table positiveInfoRecord is
'刑释人员记录(任务清单使用)';
comment on column positiveInfoRecord.recordDate is
'时间';
comment on column positiveInfoRecord.address is
'地点';
comment on column positiveInfoRecord.name is
'姓名';
comment on column positiveInfoRecord.ishome is
'是否在家';
comment on column positiveInfoRecord.isLivelihood is
'有无生活来源';
comment on column positiveInfoRecord.familyPhone is
'家属联系电话';
comment on column positiveInfoRecord.mark is
'备注';
comment on column positiveInfoRecord.status is
'签收的状态(0未签收，1已签收)';
comment on column positiveInfoRecord.attitude is
'签收的意见';
comment on column positiveInfoRecord.signDate is
'签收的日期';
comment on column positiveInfoRecord.signMemberName is
'签收人名字';
comment on column positiveInfoRecord.gridMemberPhone is
'新增记录的用户联系电话';


create sequence S_hiddenDanger
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create sequence S_workingSituation
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create sequence S_propagandaAndVerification
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


create table hiddenDanger(
  id                 number(10) not null,
  orgid              number(10),
  orgInternalCode     varchar2(32),
  discoverDate        date not null,
  address             varchar2(100),
  exceptionType       number(10),
  exceptionSituation  varchar2(100),
  remark              varchar2(100),
  ishandle            number(1) default 0,
  advice              varchar2(100),
  signDate            date,
  imgUrl             varchar2(300),
  signUserName       varchar2(32),
  cellName             varchar2(32),
  telephone             varchar2(32),
  createuser          varchar2(32) not null,
  updateuser          varchar2(32),
  createdate          date not null,
  updatedate          date,
  constraint PKhiddenDanger primary key (ID)
);

comment on table hiddenDanger is
'发现治安隐患(任务清单使用)';
comment on column hiddenDanger.id is
'id';
comment on column hiddenDanger.orgid is
'组织id';
comment on column hiddenDanger.orgInternalCode is
'组织编码';
comment on column hiddenDanger.address is
'地址';
comment on column hiddenDanger.discoverDate is
'发现时间';
comment on column hiddenDanger.exceptionType is
'异常类型';
comment on column hiddenDanger.exceptionSituation is
'异常情况';
comment on column hiddenDanger.remark is
'备注';
comment on column hiddenDanger.ishandle is
'0为签收 1为签收';
comment on column hiddenDanger.advice is
'签收意见';
comment on column hiddenDanger.signDate is
'签收时间';
comment on column hiddenDanger.signUserName is
'签收人';
comment on column hiddenDanger.cellName is
'网格员姓名';
comment on column hiddenDanger.telephone is
'网格员手机号码';
comment on column hiddenDanger.imgUrl is
'图片链接地址';
 



create table workingSituation(
   id                 number(10) not null,
    orgid              number(10),
   orgInternalCode     varchar2(32),
   occurrenceDate     date,
   workcontent         number(10),
   remark              varchar2(100),
   ishandle          number(1) default 0,
   advice             varchar2(100),
   signDate            date,
   signUserName       varchar2(32),
   cellName             varchar2(32),
   telephone             varchar2(32),
   createuser          varchar2(32) not null,
   updateuser          varchar2(32),
   createdate          date not null,
   updatedate          date,
   constraint PKworkingSituation primary key (ID)
  );
  
  
comment on table workingSituation is
'民警带领下的开展工作情况(任务清单使用)';
comment on column workingSituation.id is
'id';
comment on column workingSituation.orgid is
'组织id';
comment on column workingSituation.orgInternalCode is
'组织编码';
comment on column workingSituation.occurrenceDate is
'时间';
comment on column workingSituation.workcontent is
'工作内容';
comment on column workingSituation.remark is
'备注';
comment on column workingSituation.ishandle is
'0为签收 1为签收';
comment on column workingSituation.advice is
'签收意见';
comment on column workingSituation.signDate is
'签收时间';
comment on column workingSituation.signUserName is
'签收人';
comment on column workingSituation.cellName is
'网格员姓名';
comment on column workingSituation.telephone is
'网格员手机号码';



create table propagandaAndVerification(
   id                 number(10) not null,
    orgid              number(10),
   orgInternalCode     varchar2(32),
   occurrenceDate     date,
   address             varchar2(100),
   name                 varchar2(32),
   propaganda          number(1),
   verificationReport  number(1),
   exceptionSituation  number(10),
   ishandle          number(1) default 0,
   advice             varchar2(100),
   signDate            date,
   signUserName       varchar2(32),
   cellName             varchar2(32),
   telephone             varchar2(32),
   remark              varchar2(100),
   createuser          varchar2(32) not null,
   updateuser          varchar2(32),
   createdate          date not null,
   updatedate          date,
   constraint PKpropagandaAndVerification primary key (ID)
);
comment on table propagandaAndVerification is
'宣传核查(任务清单使用)';
comment on column propagandaAndVerification.id is
'id';
comment on column propagandaAndVerification.orgid is
'组织id';
comment on column propagandaAndVerification.orgInternalCode is
'组织编码';
comment on column propagandaAndVerification.address is
'地址';
comment on column propagandaAndVerification.name is
'姓名';
comment on column propagandaAndVerification.occurrenceDate is
'发生时间';
comment on column propagandaAndVerification.propaganda is
'是否宣传';
comment on column propagandaAndVerification.verificationReport is
'是否核查';
comment on column propagandaAndVerification.exceptionSituation is
'异常情况';
comment on column propagandaAndVerification.remark is
'备注';
comment on column propagandaAndVerification.ishandle is
'0为签收 1为签收';
comment on column propagandaAndVerification.advice is
'签收意见';
comment on column propagandaAndVerification.signDate is
'签收时间';
comment on column propagandaAndVerification.signUserName is
'签收人';
comment on column propagandaAndVerification.cellName is
'网格员姓名';
comment on column propagandaAndVerification.telephone is
'网格员手机号码';




insert into propertydomains values(s_propertydomains.nextval,'宣传核查异常情况',0,null);


insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 1, '无异常', 'wyc', 'wuyichang', 'admin', sysdate);
insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 2, '大量聚集', 'dljj', 'daliangjuji', 'admin', sysdate);
insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 3, '异常气味', 'ycqw', 'yichangqiwei', 'admin', sysdate);
insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 4, '异常声音', 'ycsy', 'yichangshengyin', 'admin', sysdate);
 insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 5, '无身份证', 'wsfz', 'wushenfenzheng', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='宣传核查异常情况'), 0, 6, '群租房人员来往复杂', 'qzflyfz', 'qunzufangrenyuanlaiwangfuza', 'admin', sysdate);

 
 
insert into propertydomains values(s_propertydomains.nextval,'民警带领下工作内容',0,null);

insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='民警带领下工作内容'), 0, 1, '督促申报流动人口信息', 'dcsbldrkxx', 'ducushenbaoliudongrenkouxinxi', 'admin', sysdate);
 insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='民警带领下工作内容'), 0, 2, '其他', 'qt', 'qita', 'admin', sysdate);
 
insert into propertydomains values(s_propertydomains.nextval,'治安隐患异常类型',0,null);


insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 1, '涉暴恐', 'sbk', 'shebaokong', 'admin', sysdate);
 insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 2, '涉枪涉爆', 'sqsb', 'sheqiangshebao', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 3, '涉制毒', 'szd', 'shezhidu', 'admin', sysdate);
   insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 4, '涉贩毒', 'sfd', 'shefandu', 'admin', sysdate);
   insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 5, '涉吸毒', 'sxd', 'shexidu', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 6, '邪教活动', 'xjhd', 'xiejiaohuodong', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 7, '制假贩假', 'zjfj', 'zhijiafanjia', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 8, '涉黄', 'sh', 'shehuang', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 9, '涉赌', 'sd', 'shedu', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 10, '传销', 'cx', 'chuanxiao', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 11, '火灾隐患', 'hzyh', 'huozaiyinhuan', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 12, '收赃', 'sz', 'shouzang', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0, 13, '销赃', 'xz', 'xiaozang', 'admin', sysdate);
  insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 0,14, '其他异常事件', 'qtycsj', 'qitayichangshijian', 'admin', sysdate);
 
 
commit;

create table druggyTask(
  id number(10) not null,
  orgId number(10) not null,
  orgInternalCode varchar2(20),
  time  date,
  place varchar2(60),
  name  varchar2(50),
  isLifeResource number(1) default 0,
  familyTel varchar2(30),
  lifeResource number(10),
  isException number(1) default 0,
  remark varchar2(100),
  status number(1) default 0,
  attitude varchar2(100),
  signDate date,
  signMemberName   varchar2(30),
  reporter varchar2(30),
  reporterTel varchar2(30),
  createDate  date,
  createUser  varchar2(32),
  updateDate  date,
  updateUser  varchar2(32),
  constraint PKDRUGGYTASK primary key(id)
);
comment on table druggyTask is '吸毒人员走访记录表';
comment on column druggyTask.orgId is '网格id';
comment on column druggyTask.orgInternalCode is '组织结构内置编码';
comment on column druggyTask.time is '时间';
comment on column druggyTask.name is '姓名';
comment on column druggyTask.place is '地点';
comment on column druggyTask.isLifeResource is '有无生活来源';
comment on column druggyTask.familyTel is '家属联系电话';
comment on column druggyTask.lifeResource is '生活来源方式';
comment on column druggyTask.isException is '有无异常';
comment on column druggyTask.status is '是否签收（0未签收，1已签收）';
comment on column druggyTask.attitude is '签收备注';
comment on column druggyTask.signDate is '签收日期';
comment on column druggyTask.signMemberName is '签收人';
comment on column druggyTask.reporter is '上报人（默认就是网格员）';
comment on column druggyTask.reporterTel is '上报人联系电话（默认网格员联系电话）';
comment on column druggyTask.remark is '备注';

create sequence S_DRUGGYTASK
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table mentalPatientTask(
  id number(10) not null,
  orgId number(10) not null,
  orgInternalCode varchar2(20) not null,
  time  date,
  place varchar2(60),
  name  varchar2(50),
  guardian varchar2(100),
  guardianName varchar2(50),
  guardianTel varchar2(30),
  guardianIsHasCome number(1) default 0,
  guardianIsHasCompare number(1) default 0,
  isDanger number(1) default 0,
  isDriinked number(1) default 0,
  isException number(1) default 0,
  exception varchar2(40),
  isout number(1) default 0,  
  outReason number(10),
  remark varchar2(100),
  statusPolice number(1) default 0,
  statusJustice number(1) default 0,
  attitudePolice varchar2(100),
  signDatePolice date,
  signMemberNamePolice  varchar2(30),
  attitudeJustice varchar2(100),
  signDateJustice date,
  signMemberNameJustice  varchar2(30),
  reporter varchar2(30),
  reporterTel varchar2(30),
  createDate  date,
  createUser  varchar2(32),
  updateDate  date,
  updateUser  varchar2(32),
  constraint PKMENTALPATIENTTASK primary key(id)
);
comment on table mentalPatientTask is '严重精神障碍患者走访表';
comment on column mentalPatientTask.orgId is '组织机构id';
comment on column mentalPatientTask.orgInternalCode is '组织机构编码';
comment on column mentalPatientTask.time is '时间';
comment on column mentalPatientTask.place is '地点';
comment on column mentalPatientTask.name is '姓名';
comment on column mentalPatientTask.guardian is '监护人与病人关系';
comment on column mentalPatientTask.guardianName is '监护人姓名';
comment on column mentalPatientTask.guardianTel is '监护人电话';
comment on column mentalPatientTask.guardianIsHasCome is '监护人有无生活来源';
comment on column mentalPatientTask.guardianIsHasCompare is '监护人有无监护能力';
comment on column mentalPatientTask.isDanger is '病人是否进行危险性评估';
comment on column mentalPatientTask.isDriinked is '服药情况';
comment on column mentalPatientTask.isException is '有无异常';
comment on column mentalPatientTask.exception is '异常情况';
comment on column mentalPatientTask.isout is '是否外出';
comment on column mentalPatientTask.outReason is '外出原因';
comment on column mentalPatientTask.remark is '备注';
comment on column mentalPatientTask.statusPolice is '派出所是否签收（0未签收，1已签收）';
comment on column mentalPatientTask.statusJustice is '司法所是否签收（0未签收，1已签收）';
comment on column mentalPatientTask.attitudeJustice is '司法所签收备注';
comment on column mentalPatientTask.signDateJustice is '司法所签收日期';
comment on column mentalPatientTask.signMemberNamePolice is '派出所签收人';
comment on column mentalPatientTask.attitudePolice is '派出所签收备注';
comment on column mentalPatientTask.signDatePolice is '派出所签收日期';
comment on column mentalPatientTask.signMemberNameJustice is '签收人';
comment on column mentalPatientTask.reporter is '上报人（默认就是网格员）';
comment on column mentalPatientTask.reporterTel is '上报人联系电话（默认网格员联系电话）';


create sequence S_MENTALPATIENTTASK
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertyDomains.NEXTVAL, '精神病外出原因', 0, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='精神病外出原因'), 0, 1, '外出务工', 'wcwg', 'waichuwugong', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='精神病外出原因'), 0, 2, '投靠亲友', 'tkqy', 'toukaoqinyou', 'admin', sysdate);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertyDomains.NEXTVAL, '吸毒人员生活来源', 0, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='吸毒人员生活来源'), 0, 1, '务农', 'wg', 'wugong', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='吸毒人员生活来源'), 0, 2, '打工', 'dg', 'dagong', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='吸毒人员生活来源'), 0, 3, '低保', 'db', 'dibao', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='吸毒人员生活来源'), 0, 4, '其他', 'qt', 'qita', 'admin', sysdate);

update propertydicts  set displayseq = 15 where propertydomainid=(select id from propertydomains where domainname='治安隐患异常类型')
 and displayname='其他异常事件';

insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='治安隐患异常类型'), 14,14, '有无守楼护院人员', 'ywslhyry', 'youwushoulouhuyuanrenyuan', 'admin', sysdate);

UPDATE permissions set PARENTID = (select id from permissions where ename='mentalPatientTaskVisitmanagement') where CNAME = '卫生所签收';

UPDATE permissions set PARENTID = (select id from permissions where ename='mentalPatientTaskVisitmanagement') where CNAME = '派出所签收';
commit;
alter table HIDDENDANGER  add (isHasAbnormal number(1) default 0 not null);