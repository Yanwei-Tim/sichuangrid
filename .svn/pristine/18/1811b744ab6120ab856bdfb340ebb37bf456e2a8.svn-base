-- 任务清单添加回复时间
alter table exceptionalSituationRecord add REPLAYDATE date;
alter table HiddenDanger add REPLAYDATE date;
alter table druggyTask add REPLAYDATE date;
alter table mentalPatientTask add REPLAYDATE date;
alter table termerRecord add REPLAYDATE date;
alter table positiveInfoRecord add REPLAYDATE date;

--任务清单考核权限配置
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'任务清单考核','taskListTimeStandardManagement',1,'考核评估系统',1,(select id from permissions where ename = 'evaluateManagement'),
   '','','', 7,'');

insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'职能部门时限标准','taskListTimeStandardConfig',1,'任务清单考核',1,(select id from permissions where ename = 'taskListTimeStandardManagement'),
   '','/task/taskListTimeStandard/taskListTimeStandard.jsp','', 1,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'新增','addTaskListTimeStandard',0,'职能部门时限标准',1,(select id from permissions where ename = 'taskListTimeStandardConfig'),
   '','','', 1,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'修改','updateTaskListTimeStandard',0,'职能部门时限标准',1,(select id from permissions where ename = 'taskListTimeStandardConfig'),
   '','','', 1,'');
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'删除','deleteTaskListTimeStandard',0,'职能部门时限标准',1,(select id from permissions where ename = 'taskListTimeStandardConfig'),
   '','','', 1,'');

--任务清单项目名字典
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'任务清单项目名称');


insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 1, 1, '宣传核查', 'xchc', 'xuanchuanhecha', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 2, 2, '民警带领下开展工作情况', 'mjdlxkzgz', 'minjingdailingxiakaizhangongzuo', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 3, 3, '异常情况报告', 'ycqkbg', 'yichangqingkuangbaogao', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 4, 4, '吸毒人员', 'xdry', 'xidurenyuan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 5, 5, '严重精神障碍患者', 'yzjszahz', 'yanzhongjingshenzhangaihuanzhe', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 6, 6, '社区服刑人员', 'sqfxry', 'shequfuxingrenyuan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 7, 7, '刑释人员', 'xsry', 'xingshirenyuan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='任务清单项目名称'), 8, 8, '发现治安隐患', 'fxzayh', 'faxianzhianyinhuan', 'admin', '', sysdate, null);


-- 任务清单的严重精神障碍患者的回复时间拆分为派出所回复时间和卫生所回复时间
ALTER TABLE mentalPatientTask add  replayDatePolice date ;
ALTER TABLE mentalPatientTask add replayDateJustic date;
update mentalPatientTask set replayDatePolice= replayDate,replayDateJustic= replayDate;