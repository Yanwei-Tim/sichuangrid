--创建任务清单附件表索引
create sequence s_TASKLISTATTACHFILES
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;

-- Create 创建附件表
create table TASKLISTATTACHFILES
(
  ID            number(10),
  BUSINESSID    NUMBER(10),
  FILENAME 		NVARCHAR2(150),
  PHYSICSFULLFILENAME  NVARCHAR2(500),
  MODULEKEY     NVARCHAR2(150),
  CREATEUSER    VARCHAR2(60) not null,
  UPDATEUSER    VARCHAR2(60),
  CREATEDATE    DATE not null,
  UPDATEDATE    DATE,
  constraint PK_TASKLISTATTACHFILES primary key (ID)
);

-- Add comments to the table 
comment on table TASKLISTATTACHFILES
  is '任务清单附件表';
-- Add comments to the columns 
comment on column TASKLISTATTACHFILES.BUSINESSID
  is '使用到该文件的功能模块对象ID';
comment on column TASKLISTATTACHFILES.FILENAME
  is '附件名';
comment on column TASKLISTATTACHFILES.PHYSICSFULLFILENAME
  is '附件地址';
comment on column TASKLISTATTACHFILES.MODULEKEY
  is '使用到该文件的功能模块';

  --权限和菜单
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'异常情形报告','exceptionalSituationRecordManage',1,'流动人员管理',1,(select id from permissions where ename = 'floatingPopulationForTast'),
   '','/hotModuel/task/exceptionalSituationRecordList.ftl','', 2,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'新增','addExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 0,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查看','viewExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 1,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'查询','searchExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 2,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'删除','deleteExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 3,'');
   
insert into permissions
  (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values
  (s_permissions.nextval,'签收','signExceptionalSituationRecord',0,'异常情形报告',1,(select id from permissions where ename = 'exceptionalSituationRecordManage'),
   '','','', 4,'');

--流动人口->异常情况
create table exceptionalSituationRecord(
    id   number(10),
    orgId  number(10),
    orgCode varchar2(32),
    recordDate date,
    address     varchar2(150),
    exceptionSituation  number(10),
    mark  varchar2(900),
    status  number(2) default 0,
    attitude  varchar2(600),
    signDate  date,
    signMemberName  varchar2(32),
    gridMemberPhone  varchar2(15),
    createDate  date,
    createUser  varchar2(32),
    updateDate  date,
    updateUser  varchar2(32),
    constraint  pk_exceptionalSituationRecord primary key(id)
);

--序列
create sequence s_exceptionalSituationRecord
Minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

comment on table exceptionalSituationRecord is
'异常情况记录(任务清单使用)';
comment on column exceptionalSituationRecord.recordDate is
'时间';
comment on column exceptionalSituationRecord.address is
'地点';
comment on column exceptionalSituationRecord.exceptionSituation is
'异常情况';
comment on column exceptionalSituationRecord.mark is
'备注';
comment on column exceptionalSituationRecord.status is
'签收的状态(0未签收，1已签收)';
comment on column exceptionalSituationRecord.attitude is
'签收意见';
comment on column exceptionalSituationRecord.signDate is
'签收的时间';
comment on column exceptionalSituationRecord.signMemberName is
'签收人姓名';
comment on column exceptionalSituationRecord.gridMemberPhone is
'新增记录的用户联系电话';

--删除属性字典项，"宣传核查异常情况"小类"无异常"
delete from propertydicts where propertydomainid = (
	select id from propertydomains where domainname = '宣传核查异常情况') and displayname = '无异常';
--新增属性字典项，"宣传核查异常情况"小类"已搬走"
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='宣传核查异常情况'), 0, 7, '已搬走', 'ybz', 'yibanzou', 'admin',sysdate);	

--删除社区服刑人员记录中的是否在家字段，新增"异常情况"字段
update termerrecord set ishome = null;
alter table termerrecord drop(ishome);
alter table termerrecord add(exceptionSituationInfo varchar2(750));
comment on column termerrecord.exceptionSituationInfo is
'异常情况';

--刑释人员记录新增"异常情况"字段、"生活来源方式"字段
alter table positiveinforecord add(exceptionSituationInfo varchar2(750));
alter table positiveinforecord add(livelihoodWay number(10));
comment on column positiveinforecord.exceptionSituationInfo is
'异常情况';
comment on column positiveinforecord.livelihoodWay is
'生活来源方式';

--刑释人员记录删除"有无生活来源"、是否在家字段
update positiveinforecord set ishome = null;
update positiveinforecord set isLivelihood = null;
alter table positiveinforecord drop(ishome);
alter table positiveinforecord drop(isLivelihood);

--修改签收意见字段长度
alter table positiveinforecord modify(attitude  varchar2(600));
alter table termerrecord modify(attitude  varchar2(600));

commit;