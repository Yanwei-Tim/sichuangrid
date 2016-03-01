--组织机构迁移合并创建表脚本
/*==============================================================*/
/* Table: moduletable                                           */
/*==============================================================*/
create table moduletable  (
   id                   number(6)                       not null,
   ename                varchar2(100),
   active               number(1),
   beanName		varchar2(100),
   tablename            varchar2(30),
   orgidcolumn          varchar(30),
   orgcodecolumn        varchar(30),
   split                number(1),
   executetype          number(1),
   ismaintable          number(1),
   executelevel         number(2),
   operatemode          number(1),
   countscript          varchar(2000),
   selectscript         varchar2(2000),
   updatescript         varchar2(2000),
   deletescript         varchar2(2000),
   createuser           varchar2(32),
   createdate           date,
   updateuser           varchar2(32),
   updatedate           date,
   constraint pk_moduletable primary key (id)
);

comment on table moduletable is
'功能模块业务表';

comment on column moduletable.id is
'主键';

comment on column moduletable.ename is
'所属模块权限名称';

comment on column moduletable.active is
'是否生效1:有效0:无效';

comment on column moduletable.tablename is
'业务表名';

comment on column moduletable.orgidcolumn is
'业务表中部门ID字段名称';

comment on column moduletable.orgcodecolumn is
'业务表中部门CODE字段名称';

comment on column moduletable.executetype is
'执行方式0:默认1:自定义';

comment on column moduletable.ismaintable is
'是否主表0:否1:是';

comment on column moduletable.executelevel is
'执行优先级，可以重复，数值越小优先级越高';

comment on column moduletable.operatemode is
'操作类型0:删除操作 1:迁移合并操作';

comment on column moduletable.countscript is
'统计数量脚本';

comment on column moduletable.selectscript is
'查询脚本';

comment on column moduletable.updatescript is
'更新脚本';

comment on column moduletable.createuser is
'创建用户';

comment on column moduletable.createdate is
'创建时间';

comment on column moduletable.updateuser is
'更新用户';

comment on column moduletable.updatedate is
'更新时间';


/*==============================================================*/
/* Table: orgchangelog                                          */
/*==============================================================*/
create table orgchangelog  (
   id                   number(6)                       not null,
   orgchangeid          number(10),
   modulename           varchar2(100),
   ename                varchar2(100),
   tablename            varchar2(30),
   operatetype          number(1),
   starttime            date,
   endtime              date,
   logtype              number(3),
   description          clob,
   createuser           varchar2(32),
   createdate           date,
   updateuser           varchar2(32),
   updatedate           date,
   constraint pk_orgchangelog primary key (id)
);

comment on table orgchangelog is
'组织机构变更日志';

comment on column orgchangelog.id is
'主键';

comment on column orgchangelog.orgchangeid is
'部门变更ID';

comment on column orgchangelog.modulename is
'所属模块名称';

comment on column orgchangelog.ename is
'所属模块标记';

comment on column orgchangelog.tablename is
'表名';

comment on column orgchangelog.operatetype is
'操作类型1:合并2:迁移';

comment on column orgchangelog.starttime is
'开始时间';

comment on column orgchangelog.endtime is
'结束时间';

comment on column orgchangelog.logtype is
'日志类型0表示成功日志其它表示错误日志';

comment on column orgchangelog.description is
'日志描述';

comment on column orgchangelog.createuser is
'创建用户';

comment on column orgchangelog.createdate is
'创建时间';

comment on column orgchangelog.updateuser is
'更新用户';

comment on column orgchangelog.updatedate is
'更新时间';


/*==============================================================*/
/* Table: orgmapping                                            */
/*==============================================================*/
create table orgmapping  (
   orgchangeid          numeric(10),
   oldorgid             char(10),
   oldorgcode           varchar2(100),
   neworgid             number(10),
   neworgcode           varchar2(100),
   createdate           date
);

comment on table orgmapping is
'组织机构变更映射';

comment on column orgmapping.orgchangeid is
'变更ID';

comment on column orgmapping.oldorgid is
'旧ORGID';

comment on column orgmapping.oldorgcode is
'旧ORGCODE';

comment on column orgmapping.neworgid is
'新ORGID';

comment on column orgmapping.neworgcode is
'新ORGCODE';

comment on column orgmapping.createdate is
'写入时间';


/*==============================================================*/
/* Table: orgchangeinfo                                         */
/*==============================================================*/
create table orgchangeinfo  (
   id                   number(10)                      not null,
   sourceorgid          number(10)                      not null,
   sourceorgname        varchar2(600),
   departmentno         varchar2(32),
   targetorgid          number(10)                      not null,
   targetorgcode        varchar2(32),
   targetorgName        varchar2(300),
   operatetype          number(1),
   isdeal               number(1),
   createuser           varchar2(32),
   createdate           date,
   updateuser           varchar2(32),
   updatedate           date,
   constraint pk_orgchangeinfo primary key (id)
);

comment on table orgchangeinfo is
'组织机构变更信息';

comment on column orgchangeinfo.id is
'主键';

comment on column orgchangeinfo.sourceorgid is
'源部门ID';

comment on column orgchangeinfo.sourceorgname is
'源部门名称';

comment on column orgchangeinfo.departmentno is
'源部门编号';

comment on column orgchangeinfo.targetorgid is
'目标部门ID';

comment on column orgchangeinfo.targetorgcode is
'目标部门CODE';

comment on column orgchangeinfo.targetorgName   is
'目标部门组织机构名称';
      
comment on column orgchangeinfo.operatetype is
'操作类型1:迁移2:合并';

comment on column orgchangeinfo.isdeal is
'是否处理0:未处理1:处理';

comment on column orgchangeinfo.createuser is
'操作人员';

comment on column orgchangeinfo.createdate is
'操作时间';

comment on column orgchangeinfo.updateuser is
'处理用户';

comment on column orgchangeinfo.updatedate is
'处理时间';

--迁移合并数据备份表
create table backupbasedata(
id number(10) not null,
dataid number(15) not null,
orgchangeid number(10),
tablename varchar2(30) not null,
dataorgid number(10),
expansionData clob,
createuser           VARCHAR2(32)         not null,
updateuser           VARCHAR2(32),
createdate           DATE                 not null,
updatedate           DATE,
constraint pk_backupbasedata primary key (id)
);
comment on table backupbasedata is
'迁移合并备份信息';

comment on column backupbasedata.id is
'主键';

comment on column backupbasedata.dataid is
'数据ID';

comment on column backupbasedata.orgchangeid is
'迁移合并信息Id';

comment on column backupbasedata.tablename is
'数据操作表名';

comment on column backupbasedata.dataorgid is
'被影响数据组织机构Id';

comment on column backupbasedata.expansionData is
'扩展字段，用于存储关联关系';

--backupbasedata表序列
create sequence s_backupbasedata
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--moduleTable表序列
create sequence s_MODULETABLE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--OrgChangeLog表序列
create sequence s_OrgChangeLog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--OrgMapping表序列
create sequence s_OrgMapping
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

--CHANGEINFO表序列
create sequence s_CHANGEINFO
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;