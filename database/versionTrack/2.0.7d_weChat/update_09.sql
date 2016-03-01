create sequence s_taskListReply
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--任务清单回复
create table taskListReply(
id number(10) not null,
moduleKey varchar2(32),
taskId number(10) not null,
replyContent VARCHAR2(2000),
replyUserId number(10),
replyUser varchar2(32),
replyDate date,
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE, 
    constraint pk_taskListReply primary key(id)
);
comment on table taskListReply is '任务清单回复';
comment on column taskListReply.moduleKey  is '功能模块';
comment on column taskListReply.taskId  is '任务id';
comment on column taskListReply.replyContent  is '回复内容';
comment on column taskListReply.replyUserId  is '回复用户id';
comment on column taskListReply.replyUser  is '回复用户';
comment on column taskListReply.replyDate  is '回复时间';



alter table exceptionalSituationRecord add HASREPLAY char(1) default 0;
alter table HiddenDanger add HASREPLAY char(1) default 0;
alter table druggyTask add HASREPLAY char(1) default 0;
alter table mentalPatientTask add HASREPLAY char(1) default 0;
alter table termerRecord add HASREPLAY char(1) default 0;
alter table positiveInfoRecord add HASREPLAY char(1) default 0;

--回复权限
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 6,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyDruggyTask',0,'吸毒人员',1,(select id from permissions where ename = 'druggyTaskVisitmanagement'),
   '','','', 7,'');

insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyMentalPatientTask',0,'严重精神障碍患者',1,(select id from permissions where ename = 'mentalPatientTaskVisitmanagement'),
   '','','', 8,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyTermerRecord',0,'社区服刑人员',1,(select id from permissions where ename = 'termerRecordManage'),
   '','','', 6,'');

insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyPositiveInfoRecord',0,'刑释人员',1,(select id from permissions where ename = 'positiveInfoRecordManage'),
   '','','', 6,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerAll',0,'总数',1,(select id from permissions where ename = 'hiddenDangerAllManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerViolence',0,'涉暴恐',1,(select id from permissions where ename = 'hiddenDangerViolenceManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerGun',0,'涉枪涉爆',1,(select id from permissions where ename = 'hiddenDangerGunManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerMakeDrug',0,'涉制毒',1,(select id from permissions where ename = 'hiddenDangerMakeDrugManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerPushDrug',0,'涉贩毒',1,(select id from permissions where ename = 'hiddenDangerPushDrugManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerTakeDrug',0,'涉吸毒',1,(select id from permissions where ename = 'hiddenDangerTakeDrugManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerCult',0,'邪教活动',1,(select id from permissions where ename = 'hiddenDangerCultManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerCounterfeit',0,'制假贩假',1,(select id from permissions where ename = 'hiddenDangerCounterfeitManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerErotica',0,'涉黄',1,(select id from permissions where ename = 'hiddenDangerEroticaManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerGamble',0,'涉赌',1,(select id from permissions where ename = 'hiddenDangerGambleManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerPyramidSale',0,'传销',1,(select id from permissions where ename = 'hiddenDangerPyramidSaleManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerFire',0,'火灾隐患',1,(select id from permissions where ename = 'hiddenDangerFireManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerReceiveLoot',0,'收赃',1,(select id from permissions where ename = 'hiddenDangerReceiveLootManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerPushLoot',0,'销赃',1,(select id from permissions where ename = 'hiddenDangerPushLootManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerNoTend',0,'无守楼护院人员',1,(select id from permissions where ename = 'hiddenDangerNoTendManagement'),
   '','','', 6,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'添加回复','replyHiddenDangerOther',0,'其他异常事件',1,(select id from permissions where ename = 'hiddenDangerOtherManagement'),
   '','','', 6,'');