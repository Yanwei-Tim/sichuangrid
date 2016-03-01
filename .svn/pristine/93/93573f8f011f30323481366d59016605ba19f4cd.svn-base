--------------------------------------------------------
-------------短信发送平台系统表，建表语句开始处--------------------
--------------------------------------------------------

/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2010/12/03 12:14:03                          */
/*==============================================================*/
create sequence S_MOBILESHORTMESSAGE
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

create sequence S_UPMOBILEMESSAGE
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

create sequence S_SMSTABLES
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: mobileshortmessage  短信发送表                                  */
/*==============================================================*/
create table MOBILESHORTMESSAGE  (
   ID                   NUMBER                          not null,
   fromId               NUMBER,
   fromSystem           VARCHAR2(50),
   serverId             VARCHAR2(20),
   message              CLOB,
   requestReport        CHAR(1) default 0,
   sender               VARCHAR2(32),
   receiver             VARCHAR2(32),
   status               NUMBER default 1,
   sentTime             DATE,
   createTime           DATE default sysdate,
   priority             NUMBER default 8,
   type                 NUMBER,
   constraint PK_MOBILESHORTMESSAGE primary key (ID)
);

-- Add comments to the table 
comment on table MOBILESHORTMESSAGE
  is '短信发送表';
-- Add comments to the columns 
comment on column MOBILESHORTMESSAGE.FROMID
  is '短信息来源的标识';
comment on column MOBILESHORTMESSAGE.FROMSYSTEM
  is '短信息来源系统';
comment on column MOBILESHORTMESSAGE.SERVERID
  is '短信息远端ID';
comment on column MOBILESHORTMESSAGE.MESSAGE
  is '消息体';
comment on column MOBILESHORTMESSAGE.REQUESTREPORT
  is '是否需要结果报告';
comment on column MOBILESHORTMESSAGE.SENDER
  is '发送人';
comment on column MOBILESHORTMESSAGE.RECEIVER
  is '接收人号码';
comment on column MOBILESHORTMESSAGE.STATUS
  is '下发状态。1：未下发到服务器,2: 系统处理中,3：下发到服务器,4：下发到终端成功,5：未成功发送到终端';
comment on column MOBILESHORTMESSAGE.SENTTIME
  is '上发时间';
comment on column MOBILESHORTMESSAGE.CREATETIME
  is '下发时间';
comment on column MOBILESHORTMESSAGE.PRIORITY
  is '短信优先级。系统消息默认为：0；优先级逐步递减：1，2，3，4，5，6';
comment on column MOBILESHORTMESSAGE.TYPE
  is '短信类型。移动 1，联通 2，小灵通 3，CDMA 4';

/*==============================================================*/
/* Table: upmobilemessage 上行短消息                                      */
/*==============================================================*/
create table UPMOBILEMESSAGE
(
  ID          NUMBER not null,
  TOID        NUMBER,
  TOSYSTEM    VARCHAR2(32),
  SERVERID    VARCHAR2(32),
  MESSAGE     CLOB,
  SENDER      VARCHAR2(32) not null,
  RECEIVER    VARCHAR2(32),
  RECEIVETIME DATE not null,
  STARTDEALTIME DATE,
  PROCESSTIME DATE,
  type  NUMBER
);

comment on table upmobilemessage is
'上行短消息';
comment on column UPMOBILEMESSAGE.TOID
  is '短信息出口';
comment on column UPMOBILEMESSAGE.TOSYSTEM
  is '短信息出口系统';
comment on column UPMOBILEMESSAGE.SERVERID
  is '短信息远端ID';
comment on column UPMOBILEMESSAGE.MESSAGE
  is '消息体';
comment on column UPMOBILEMESSAGE.SENDER
  is '发送人号码';
comment on column UPMOBILEMESSAGE.RECEIVER
  is '接收人号码';
comment on column UPMOBILEMESSAGE.RECEIVETIME
  is '上发时间';
comment on column UPMOBILEMESSAGE.STARTDEALTIME
  is '短信出列时间，用于控制超时等意外情况';
comment on column UPMOBILEMESSAGE.PROCESSTIME
  is '处理时间，表示该短信已经被具体业务系统读取处理完毕';
comment on column UPMOBILEMESSAGE.TYPE
  is '短信类型。移动 1，联通 2，小灵通 3，CDMA 4';


/*==============================================================*/
/* Table: SMSTables  根据流量来管理短信子表                                           */
/*==============================================================*/
create table SMSTables  (
   ID                   NUMBER                          not null,
   tabName              VARCHAR2(64),
   smsID_begin          NUMBER(20),
   smsID_end            NUMBER(20),
   type                 CHAR(2),
   createTime           DATE,
   status               NUMBER default 0,
   isenddeal          NUMBER default 0,
   constraint PK_SMSTABLES primary key (ID)
);

comment on table SMSTables is
'根据流量来管理短信子表';

comment on column SMSTables.type is
'1:发送表2:接收表';

comment on column SMSTables.isenddeal  is
'发送状态1:读取完毕,0:没有读取完毕';

comment on column SMSTables.status  is
'发送状态1:表已经满了,0:还没有满';

insert into SMSTABLES (ID, TABNAME, SMSID_BEGIN, SMSID_END, TYPE, CREATETIME, STATUS,ISENDDEAL)
values (-1, 'mobileshortmessage', 1, 1000000, '1 ', to_date('10-01-2011', 'dd-mm-yyyy'), 0,0);
insert into SMSTABLES (ID, TABNAME, SMSID_BEGIN, SMSID_END, TYPE, CREATETIME, STATUS,ISENDDEAL)
values (0, 'upmobilemessage', 1, 100000, '2 ', to_date('10-01-2011', 'dd-mm-yyyy'), 0,0);


create table SMSGlobalSettings (
	GlobalName			VARCHAR2(32)		not null,
	GlobalShowName		VARCHAR2(32),
	GlobalValue			VARCHAR2(64)		not null,
	Type				VARCHAR2(64),
	Describtion			VARCHAR2(2000)
);
comment on table SMSGlobalSettings is
'系统全局配置';
comment on column SMSGlobalSettings.GlobalName is
'全局参数名称';
comment on column SMSGlobalSettings.GlobalShowName is
'全局参数显示名称';
comment on column SMSGlobalSettings.GlobalValue is
'全局参数值';
comment on column SMSGlobalSettings.Type is
'数值类型,目前支持time,boolean,text';
comment on column SMSGlobalSettings.Describtion is
'描述信息';
 
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('startSendTime','发送开始时间','00:00:00','time','发送时间，发送时间之前不在允许发送短信');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('endSendTime','发送结束时间','23:59:59','time','结束时间，结束时间之后不在允许发送短信');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('isUrgent','是否紧急发送','1','boolean','是否紧急发送，就是取消发送时间的设置，允许1或者0');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('user','tianque','tianque','string','用户名和密码');

create sequence S_nosendmessage
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;
create table nosendmessage  (
   ID                   NUMBER         not null,
   phone                VARCHAR2(32),
   message              CLOB,
   reason               VARCHAR2(100),
   status               NUMBER default 1,
   sentTime             DATE,
   type                 NUMBER,
   constraint PK_NOSENDMESSAGE primary key (ID)
);
comment on table nosendmessage is
'发送失败表（手机黑名单表）';
comment on column nosendmessage.phone is
'电话号码';
comment on column nosendmessage.message is
'短信内容';
comment on column nosendmessage.reason is
'失败原因';
comment on column nosendmessage.reason is
'状态';
comment on column nosendmessage.sentTime is
'发送时间';

insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('emailSubject','zhoushangrid','舟山短信','string','邮件主题');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('emailHost','mail.hztianque.com','mail.hztianque.com','string','邮件服务器');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('emailFrom','buildmaster@hztianque.com','tianqueshuaige','string','邮件发送者的邮箱及密码');
insert into SMSGlobalSettings(GlobalName,GlobalShowName,GlobalValue,Type,Describtion)
values ('emailTo','',' ','string','邮件接收者的邮箱');

--------------------------------------------------------
-------------短信发送平台系统表，建表语句结束处--------------------
--------------------------------------------------------

--流量管理--
create sequence S_smsTrafficManage
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table smsTrafficManage
(
  ID                   NUMBER(10) not null,
  Traffic              NUMBER(10),
  ORGID                NUMBER(10) not null,
  ORGINTERNALCODE      VARCHAR2(32)                    not null,          
  CREATEUSER           VARCHAR2(32) not null,
  UPDATEUSER           VARCHAR2(32),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
  telecomTraffic	   NUMBER(10),
  mobileTraffic 	   NUMBER(10),
  chinaUnicomTraffic   NUMBER(10),
  smallUnicom 	       NUMBER(10),
  constraint PKsmsTrafficManage primary key (ID)
);

comment on table smsTrafficManage
  is '流量管理';
comment on column smsTrafficManage.ID
  is '流量管理id';
comment on column smsTrafficManage.Traffic
  is '流量额度';
comment on column smsTrafficManage.ORGID
  is '所属网格';
comment on column smsTrafficManage.orgInternalCode 
  is '所属网格编号';
comment on column smsTrafficManage.CREATEUSER
  is '创建人';
comment on column smsTrafficManage.UPDATEUSER
  is '修改人';
comment on column smsTrafficManage.CREATEDATE
  is '创建时间';
comment on column smsTrafficManage.UPDATEDATE
  is '修改时间';
comment on column smsTrafficManage.TELECOMTRAFFIC
  is '电信流量';
comment on column smsTrafficManage.MOBILETRAFFIC
  is '移动流量';
comment on column smsTrafficManage.CHINAUNICOMTRAFFIC
  is '联通流量';
comment on column smsTrafficManage.SMALLUNICOM
  is '小灵通流量';
  
  
--短信发件箱sequence--
create sequence S_smsUplink
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--短信发件箱--
create table smsUplink(
       id                                  number(10)                      not null,
       parentId							   number(10),
       deptId							   number(10),
       messageId						   number(10),
       sender                              varchar2(60),
       senderName                          varchar2(90),
       senderOrgId                         number(10),
       senderOrgInternalCode               varchar2(32),
       receiverName                        varchar2(90),
       receiverMobile                      varchar2(11),
       receiverOrgId                       number(10),
       receiverOrgInternalCode             varchar2(32),
       status	                           number(2)					   default 1,
       content                             varchar2(600)                   not null,
       sendTime                            date                            not null,
       receiveTime                         date,
       counts							   number(10),
       mobileType						   number(1),
       smsLevel							   number(2),
       deletestatus						   number(1) 					   default 0,
       constraint pksmsUplink primary key (id),
       constraint fksmsUplink foreign key (parentId)
  		references smsuplink (id)
);
comment on table smsUplink is
'短信发件箱';
comment on column smsUplink.id is
'短信发件id';
comment on column smsUplink.deptId is
'所属部门';
comment on column smsUplink.sender is
'发送者';
comment on column smsUplink.senderName is
'发送者姓名';
comment on column smsUplink.senderOrgId is
'发送者所属网格';
comment on column smsUplink.senderOrgInternalCode is
'发送者所属网格编号';
comment on column smsUplink.receiverName is
'接收者姓名';
comment on column smsUplink.receiverMobile is
'接收者手机';
comment on column smsUplink.receiverOrgId is
'接收者所属网格';
comment on column smsUplink.receiverOrgInternalCode is
'接收者所属网格编号';
comment on column smsUplink.status is
'发送状态：1待处理，2处理中，3待发送，4发送成功，5发送失败,默认新增时为1';
comment on column smsUplink.content is
'发送内容';
comment on column smsUplink.sendTime is
'发送时间';
comment on column smsUplink.receiveTime is
'接收时间';
comment on column smsUplink.counts is
'发送总数';
comment on column smsuplink.messageid is 
'短信发送平台短信Id' ;
comment on column smsuplink.mobiletype is 
'手机类型 :1移动，2联通，3小灵通，4电信 ' ;
comment on column smsuplink.smsLevel is 
'短信优先级,优先级逐步递减：1，2，3 ' ;
comment on column smsuplink.deletestatus is 
'删除状态：默认0未删除，1已删除';

-- 短信模板s
create sequence S_smstemplate
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;

create table smstemplate
(
  ID          NUMBER(10) not null,
  CONTENT     VARCHAR2(600),
  NAME        VARCHAR(60) not null,
  KEY         VARCHAR(600) not null,
  CREATEUSER  VARCHAR2(32) not null,
  UPDATEUSER  VARCHAR2(32),
  CREATEDATE  DATE not null,
  UPDATEDATE  DATE,
  TYPE		  VARCHAR2(32)  not null,
  constraint PKsmstemplate primary key (ID)
);

comment on table smstemplate
  is '短信模板';
comment on column smstemplate.KEY
  is '短信模板key';
comment on column smstemplate.NAME
  is '模板名称';
comment on column smstemplate.CONTENT
  is '模板内容';
comment on column smstemplate.TYPE
  is '模板类型';
comment on column smstemplate.CREATEUSER
  is '创建人';
comment on column smstemplate.UPDATEUSER
  is '修改人';
comment on column smstemplate.CREATEDATE
  is '创建时间';
comment on column smstemplate.UPDATEDATE
  is '修改时间';
  
-- sms发送对象管理表sequence --
create sequence S_smsSendObject
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;
-- sms发送对象管理表 -- 
create table smsSendObject(
       id                   number(10)                      not null,
       name                 varchar2(150)                   not null,
       description          varchar2(300)                   not null,
       template             clob,
       enable				number(1)						default 0,
       createUser           varchar2(32)                    not null,
       updateUser           varchar2(32),
       createDate           DATE                            not null,
       updateDate           DATE,
       constraint pksmsSendObject primary key (id)
);
comment on table smsSendObject is
'sms发送对象管理表';
comment on column smsSendObject.id is
'发送对象id';
comment on column smsSendObject.name is
'发送对象名称';
comment on column smsSendObject.description is
'描述';
comment on column smsSendObject.template is
'模板';
comment on column smssendobject.enable is 
'发送对象是否启用';
comment on column smsSendObject.createUser is
'创建人';
comment on column smsSendObject.updateUser is
'修改人';
comment on column smsSendObject.createDate is
'创建时间';
comment on column smsSendObject.updateDate is
'修改时间';

--查询条件管理表sequence--
create sequence S_smsQueryCondition
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--sms查询条件管理表--
create table smsQueryCondition(
       id                   number(10)                      not null,
       objectId             number(10)                      not null,
       key                  varchar2(60)                    not null,
       field                varchar2(60),
       sqlTemplate          varchar2(900)                   not null,
       description          varchar2(90),
       isNull               number(1)                       default   0,
       createUser           VARCHAR2(32)                    not null,
       updateUser           VARCHAR2(32),
       createDate           DATE                            not null,
       updateDate           DATE,
       type					VARCHAR2(30),
       constraint pksmsQueryCondition primary key (id),
       constraint fksmsQueryCondition foreign key (objectId)
         references smsSendObject (id)

);
comment on table smsQueryCondition is
'sms查询条件管理表';
comment on column smsQueryCondition.id is
'查询条件id';
comment on column smsQueryCondition.objectId is
'发送对象id';
comment on column smsQueryCondition.key is
'中间key';
comment on column smsQueryCondition.field is
'表字段';
comment on column smsQueryCondition.sqlTemplate is
'sql语句模板';
comment on column smsQueryCondition.description is
'描述';
comment on column smsQueryCondition.isNull is
'是否必填,默认否';
comment on column smsQueryCondition.createUser is
'创建人';
comment on column smsQueryCondition.updateUser is
'修改人';
comment on column smsQueryCondition.createDate is
'创建时间';
comment on column smsQueryCondition.updateDate is
'修改时间';
comment on column smsQueryCondition.type is 
'类型 ' ;

--job短信发送对象sequence--
create sequence S_smsJobSql
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--job短信发送对象sql--
create table smsJobSql(
       id                   number(10)                      not null,
       smsuplinkId			number(10),
       smslevel             number(2)                       default 1,
       sql                  varchar2(3000)                  not null,
       content              varchar2(600),
       description          varchar2(300),
       sender               number(10)                      not null,
       sendTime	            DATE                            not null,
       constraint pksmsJobSql primary key (id)
);
comment on table smsJobSql is
'job短信发送对象sql表';
comment on column smsJobSql.id is
'记录id';
comment on column smsJobSql.smsuplinkId is
'短信发件箱Id';
comment on column smsJobSql.smslevel is
'优先级状态,默认0,优先级逐步递减：1，2，3，4，5，6';
comment on column smsJobSql.content is
'内容';
comment on column smsJobSql.description is
'描述';
comment on column smsJobSql.sql is
'查询发送对象的sql';
comment on column smsJobSql.sender is
'发送人';
comment on column smsJobSql.sendTime is
'发送时间';

--短信收件箱sequence--
create sequence S_smsDownlink
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--短信收件箱--
create table smsDownlink(
       id                                  number(10)                      not null,
       sender                              varchar2(60),
       senderName                          varchar2(90),
       receiverName                        varchar2(90),
       receiverMobile                      varchar2(60),
       receiverOrgId                       number(10),
       receiverOrgInternalCode             varchar2(32),
       status                              number(2),
       content                             varchar2(600)                   not null,
       sendTime                            date,
       receiveTime                         date,
       isRead 							   number(1)  default 0,
       deletestatus						   number(1)  default 0,
       constraint pksmsDownlink primary key (id)
);
comment on table smsDownlink is
'短信收件箱';
comment on column smsDownlink.id is
'短信收件id';
comment on column smsDownlink.sender is
'发送者';
comment on column smsDownlink.senderName is
'发送者姓名';
comment on column smsDownlink.receiverName is
'接收者姓名';
comment on column smsDownlink.receiverMobile is
'接收者手机';
comment on column smsDownlink.receiverOrgId is
'接收者所属网格';
comment on column smsDownlink.receiverOrgInternalCode is
'接收者所属网格编号';
comment on column smsDownlink.status is
'发送状态';
comment on column smsDownlink.content is
'发送内容';
comment on column smsDownlink.sendTime is
'发送时间';
comment on column smsDownlink.receiveTime is
'接收时间';
comment on column smsDownlink.isRead is
'是否阅读 0未读 1已读';
comment on column smsdownlink.deletestatus is 
'删除状态：0未删除，1已删除。默认状态0';

--- 收件箱最大Id ---
create table smsdownlinkBigId(
       bigId          number(10)
);


insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID)
  values (s_PERMISSIONS.Nextval, '短信管理', 'OASmsManagementSystem', 1, '互动交流系统', 1, 
  (select id from permissions where ename='interactionManagement'), '', 
  (select max(INDEXID)+1 from permissions t where t.PARENTID = (select id from permissions where ename='interactionManagement')));
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '发送对象管理', 'smsSendObjectManagement', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 0,
  '/hotModuel/interaction/newSMS/sendObjectManagement/sendObjectList.jsp');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '新增', 'addSmsSendObject', 0, '发送对象管理', 0, ' ', 
  (select id from permissions where ename='smsSendObjectManagement'), 0 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '修改', 'updateSmsSendObject', 0, '发送对象管理', 0, ' ', 
  (select id from permissions where ename='smsSendObjectManagement'), 1 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '删除', 'deleteSmsSendObject', 0, '发送对象管理', 0, ' ', 
  (select id from permissions where ename='smsSendObjectManagement'), 2 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '查看', 'viewSmsSendObject', 0, '发送对象管理', 0, ' ', 
  (select id from permissions where ename='smsSendObjectManagement'), 3 ); 
  
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '发件箱', 'smsUplinkManagement', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 1,
  '/hotModuel/interaction/newSMS/smsUplink/smsUplinkList.jsp');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '新增', 'addSmsUplink', 0, '发件箱', 0, ' ', 
  (select id from permissions where ename='smsUplinkManagement'), 0 ); 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '删除', 'deleteSmsUplink', 0, '发件箱', 0, ' ', 
  (select id from permissions where ename='smsUplinkManagement'), 1); 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '查看其他层级短信', 'viewOtherSmsUplink', 0, '发件箱', 0, ' ', 
  (select id from permissions where ename='smsUplinkManagement'), 2); 
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '收件箱', 'smsDownlinkManagement', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 2,
  '/hotModuel/interaction/newSMS/smsDownlink/smsdownlinkList.jsp' );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '删除', 'deleteSmsDownlink', 0, '收件箱', 0, ' ', 
  (select id from permissions where ename='smsDownlinkManagement'), 0 ); 
  
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '垃圾箱', 'smstrashManagement', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 3,
  '/hotModuel/interaction/newSMS/smstrashManage/smstrashList.jsp');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '删除', 'deleteSmstrash', 0, '垃圾箱', 0, ' ', 
  (select id from permissions where ename='smstrashManagement'), 0 ); 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '还原', 'restoreSmstrash', 0, '垃圾箱', 0, ' ', 
  (select id from permissions where ename='smstrashManagement'), 1 ); 

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '流量监控', 'smsTrafficManage', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 4,
  '/hotModuel/interaction/newSMS/smstrafficmanageManage/smstrafficmanageList.jsp' );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '新增', 'addSmstrafficmanage', 0, '流量监控', 0, ' ', 
  (select id from permissions where ename='smsTrafficManage'), 0 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '修改', 'updateSmstrafficmanage', 0, '流量监控', 0, ' ', 
  (select id from permissions where ename='smsTrafficManage'), 1 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '删除', 'deleteSmstrafficmanage', 0, '流量监控', 0, ' ', 
  (select id from permissions where ename='smsTrafficManage'), 2 );
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID, INDEXID)
  values (s_permissions.nextVal, '查看', 'viewSmstrafficmanage', 0, '流量监控', 0, ' ', 
  (select id from permissions where ename='smsTrafficManage'), 3 );    
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '短信统计', 'countSmsMessage', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 5,
  '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=countSmsMessage');
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '短信接收统计', 'countReceiveMessage', 1, '短信统计', 1, 
  (select id from permissions t where t.ename='countSmsMessage'), '', 0,
  '/hotModuel/interaction/newSMS/smstrafficmanageManage/countReceiveMessageList.jsp'
  );
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '短信发送统计', 'countSendMessage', 1, '短信统计', 1, 
  (select id from permissions t where t.ename='countSmsMessage'), '', 1,
  '/hotModuel/interaction/newSMS/smstrafficmanageManage/countSendMessageList.jsp'
  );
  
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, INDEXID,NORMALURL)
  values (s_PERMISSIONS.Nextval, '短信发送管理', 'smsTrafficQuery', 1, '短信管理', 1, 
  (select id from permissions t where t.ename='OASmsManagementSystem'), '', 6,
  '/hotModuel/interaction/newSMS/smstrafficmanageManage/smsManagementList.jsp');
  
  
insert into smssendobject (ID, NAME, DESCRIPTION, TEMPLATE, ENABLE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_smssendobject.nextval , '吸毒人员', '吸毒人员查询', '<div id="searchTemplate" class="container container_24" style="margin-top:5px;height:130px;border: 1px solid #ccc;">    <div class="grid_4 lable-right">    <label class="form-lbl">身份证号：</label>   </div>   <div class="grid_8">       <input type="text" name="{身份证}" id="idCardNo"  class="form-txt" maxlength="18"/>',
 1, 'admin', 'admin', to_date('10-07-2013 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 16:02:33', 'dd-mm-yyyy hh24:mi:ss'));

update smssendobject set TEMPLATE = TEMPLATE||'</div>   <div class="grid_4 lable-right">    <label class="form-lbl">姓名：</label>   </div>   <div class="grid_8">    <input type="text" id="name" name="{姓名}" class="form-txt" />   </div>   <div class="grid_4 lable-right">             <label class="form-lbl">戒毒情况：</label>         </div>         <div class="grid_8">             <select name="{戒毒情况}" class="form-txt">                 <option value=""></option>                 <option value="482">强制戒毒</option>                 <option value="483">劳教戒毒</option>                 <option value="484">限期戒毒</option>                 <option value="485">自愿戒毒</option>                 <option value="486">社区戒毒</option>                 <option value="487">其他</option>             </select>         </div>         <div class="grid_4 lable-right">    <label class="form-lbl">性别：</label>   </div>   <div class="grid_8">'||'<select id="conditionGender" name="{性别}" class="form-txt">                 <option value=""></option>                 <option value="102">未知的性别</option>                 <option value="103">男性</option>                 <option value="104">女性</option>                 <option value="105">未说明的性别</option> </select>   </div>                  <div class=''clearLine''></div>            <div class="grid_4 lable-right">             <label class="form-lbl">吸毒原因： </label>         </div>         <div class="grid_8">             <select id="conditionDrugReason" name="{吸毒原因}" class="form-txt">                  <option value=""></option>                 <option value="469">受亲友吸毒影响</option>                 <option value="470">被人诱骗</option>'||'<option value="471">被人逼迫</option>                 <option value="472">好奇</option>                 <option value="473">为治病</option>                 <option value="474">人生受挫</option>                 <option value="475">寻求刺激</option>                 <option value="476">其他</option>             </select>         </div>            <div class="grid_4 lable-right">    <label class="form-lbl">吸毒状态：</label>   </div>   <div class="grid_8">             <select id="detoxicateCondition" name="{吸毒状态}" class="form-txt"> '||'<option value=""></option>                 <option value="488">在吸</option>                 <option value="489">停吸</option>             </select>         </div>   <div class=''clearLine''></div>    <div class="grid_4 lable-right">    <label class="form-lbl">出生日期 ：</label>   </div>   <div class="grid_3">    <input type="text" id="conditionBirthday" name="{出生日期起始时间}" readonly="readonly" class="form-txt" />   </div>   <div class="grid_2" align="center" style="">—</div>   <div class="grid_3">    <input type="text" id="endConditionBirthday" name="{出生日期结束时间}" readonly="readonly" class="form-txt" />   </div>       <div class="grid_4 lable-right"> '||'<label class="form-lbl">查获日期 ：</label>   </div>   <div class="grid_3">    <input type="text" id="conditionFerretOutDate" name="{查获日期起始时间}" readonly="readonly" class="form-txt" />   </div>   <div class="grid_2" align="center" style="">—</div>   <div class="grid_3">    <input type="text" id="endConditionFerretOutDate" name="{查获日期结束时间}" readonly="readonly" class="form-txt" />   </div>   </div> <script type="text/javascript">  $(document).ready(function(){  $(''#conditionFerretOutDate'').datePicker({   yearRange:''1930:2060'',   maxDate:''+0d''  });  $(''#endConditionFerretOutDate'').datePicker({   yearRange:''1930:2060'',   maxDate:''+0d''  });  $(''#conditionBirthday'').datePicker({   yearRange:''1930:2060'',   maxDate:''+0d''  });  $(''#endConditionBirthday'').datePicker({   yearRange:''1930:2060'',   maxDate:''+0d''  }); });  </script>'       
where name= '吸毒人员' ;

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'table', null, 'select name as name, mobilenumber as mobile, orgid , orginternalcode from druggys where 1=1 and mobilenumber is not null', '基础sql语句', 1, 'admin', null, to_date('10-07-2013 08:39:24', 'dd-mm-yyyy hh24:mi:ss'), null, null);

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'gender', null, 'and gender={gender}', '性别', null, 'admin', null, to_date('10-07-2013 09:06:37', 'dd-mm-yyyy hh24:mi:ss'), null, null);

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'startBirthday', null, 'and birthday > to_date(''{startBirthday}'',''yyyy-mm-dd'')', '出生日期起始时间', 0, 'admin', 'admin', to_date('10-07-2013 10:58:53', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 15:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'date');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'name', null, 'and name like ''{name}%''', '姓名', 0, 'admin', 'admin', to_date('10-07-2013 13:31:04', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:49', 'dd-mm-yyyy hh24:mi:ss'), 'varchar2');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'endBirthday', null, 'and birthday < to_date(''{endBirthday}'',''yyyy-mm-dd'')', '出生日期结束时间', 0, 'admin', 'admin', to_date('10-07-2013 15:28:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 15:28:13', 'dd-mm-yyyy hh24:mi:ss'), 'date');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'idcardno', null, 'and idcardno like ''{idcardno}%''', '身份证', 0, 'admin', 'admin', to_date('10-07-2013 15:37:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:33', 'dd-mm-yyyy hh24:mi:ss'), 'varchar2');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'startFerretoutdate', null, 'and ferretoutdate > to_date(''{startFerretoutdate}'',''yyyy-mm-dd'')', '查获日期起始时间', 0, 'admin', 'admin', to_date('10-07-2013 15:39:45', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:26', 'dd-mm-yyyy hh24:mi:ss'), 'date');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'endFerretoutdate', null, 'and ferretoutdate < to_date(''{endFerretoutdate}'',''yyyy-mm-dd'')', '查获日期结束时间', 0, 'admin', 'admin', to_date('10-07-2013 15:41:33', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:22', 'dd-mm-yyyy hh24:mi:ss'), 'date');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'drugreason', null, 'and drugreason = {drugreason}', '吸毒原因', 0, 'admin', 'admin', to_date('10-07-2013 15:44:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:16', 'dd-mm-yyyy hh24:mi:ss'), 'number');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'detoxicatecondition', null, 'and detoxicatecondition = {detoxicatecondition}', '吸毒状态', 0, 'admin', 'admin', to_date('10-07-2013 15:45:35', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:10', 'dd-mm-yyyy hh24:mi:ss'), 'number');

insert into smsquerycondition (ID, OBJECTID, KEY, FIELD, SQLTEMPLATE, DESCRIPTION, ISNULL, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE, TYPE)
values (s_smsquerycondition.nextval, ( select id from smssendobject s where s.name='吸毒人员' ) , 'detoxicatecase', null, 'and detoxicatecase = {detoxicatecase}', '戒毒情况', 0, 'admin', 'admin', to_date('10-07-2013 15:46:46', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-07-2013 17:26:04', 'dd-mm-yyyy hh24:mi:ss'), 'number');
