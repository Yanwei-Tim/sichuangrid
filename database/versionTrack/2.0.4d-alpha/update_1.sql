insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'账号统计管理','accountStatisticsManagement',1,'系统管理',1,(select id from permissions where ename = 'systemManagement'),
   '','','',2,'');

insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'账号登陆统计详情','accountLoginDetails',1,'账号统计管理',1,(select id from permissions where ename = 'accountStatisticsManagement'),
   '','/hotModuel/userCount/accountLoginDetailsList.jsp','', 0,'');

insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,
   '导出','detailsExport',0,'账号登陆统计详情',1,(select id from permissions where ename = 'accountLoginDetails'),
   '','','',0,'');

   --账号月登录统计详情job
insert into taskploy(id, cname, ename, type, description, code)values(s_TASKPLOY.nextval,'账号月登陆统计详情job','accountLoginDetailsDispatch',(select id from propertydicts where displayname = 'java方法'),'账号月登陆统计详情job','accountLoginDetailsDispatch.createAccountLoginDetails');
insert into task(id, name, taskgroup, description, ployId, config, closed)values(s_TASK.nextval,'accountLoginDetailsDispatch','accountLoginDetailsDispatch','accountLoginDetailsDispatch',(select id from taskploy where ename = 'accountLoginDetailsDispatch'),'44 44 11 L * ?', 1);


update permissions p set p.parentid = (
select id from permissions where ename='accountStatisticsManagement'
)
where p.ename = 'userCountManagement';

commit;

create sequence s_userLoginStatistics
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


--严重精神障碍患者表添加缺少的三个字段--
alter table  DM_mentalPatients_Temp add recoveryTime date;
alter table  DM_mentalPatients_Temp add diseaseTime date;
alter table  DM_mentalPatients_Temp add treatmentState number(10);
comment on column DM_mentalPatients_Temp.recoveryTime
  is '康复时间';
comment on column DM_mentalPatients_Temp.diseaseTime
  is '发病时间';
comment on column DM_mentalPatients_Temp.treatmentState
  is '治疗状态';
  
  alter table userhasdocuments add(APPROVALOPINION clob);
