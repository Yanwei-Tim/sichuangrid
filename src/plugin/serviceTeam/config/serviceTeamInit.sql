--服务管理团队
--序列
create sequence s_serviceTeams
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 --建表
create table serviceTeams (
   id                   	NUMBER(10)                      not null,
   teamType             	NUMBER(10),
   orgId                	NUMBER(10)                      not null,
   orgCode      	VARCHAR2(32)                    not null,
   name                 	VARCHAR2(150)                   not null,
   simplePinyin         	VARCHAR2(10),
   fullPinyin           	VARCHAR2(30),
   buildDate        	DATE,
   createUser           	VARCHAR2(32)                    not null,
   updateUser           	VARCHAR2(32),
   createDate           	DATE                            not null,
   updateDate           	DATE,
   remark               	VARCHAR2(600),
   logOut          			NUMBER(1)   	default 0 		not null,
   logoutReason         	VARCHAR2(300),
   logoutTime           		DATE,
   constraint pkserviceTeams primary key (id),
   constraint fkserviceTeams foreign key (orgId)
   references organizations (id)
);
--注释
comment on table  serviceTeams
  is '服务团队表';
comment on column serviceTeams.id
  is '主键';
comment on column serviceTeams.teamType
  is '团队类型';
comment on column serviceTeams.orgId
  is '区域id';
comment on column serviceTeams.orgCode
  is '区域编码';
comment on column serviceTeams.name
  is '团队名称';
comment on column serviceTeams.simplepinyin
  is '拼音简码';
comment on column serviceTeams.fullpinyin
  is '拼音全拼';
comment on column serviceTeams.buildDate
  is '成立时间';
comment on column serviceTeams.remark
  is '备注';
comment on column serviceTeams.logOut
  is '是否解散';
comment on column serviceTeams.logoutReason
  is '解散原因';
comment on column serviceTeams.logoutTime
  is '解散时间';
  
  
--服务记录
--序列
create sequence s_servicerecord
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table servicerecords
(
  id               		NUMBER(10) 					not null,
  orgCode       		VARCHAR2(32)  				not null,
  orgId       			NUMBER(10)  				not null,
  teamId				NUMBER(10) 					,
  occurDate    			DATE 						not null,
  occurPlace     		VARCHAR2(300)				not null,
  serviceMembers     	VARCHAR2(300)				not null,
  serviceJoiners	    VARCHAR2(300)				,
  serviceObjects    	VARCHAR2(300)				not null,
  recordType    		number(10),
  serviceContent  		CLOB,
  CREATEUSER       		VARCHAR2(32) 				not null,
  UPDATEUSER       		VARCHAR2(32),
  CREATEDATE       		DATE 						not null,
  UPDATEDATE      		DATE,
  constraint PKSERVICERECORD primary key (id)
);
-- 注释
comment on table servicerecords
  is '服务记录';
comment on column servicerecords.Id
  is '服务记录id';
comment on column servicerecords.orgCode
  is '所属网格编号';
  comment on column servicerecords.orgId
  is '所属网格Id';
comment on column servicerecords.teamId
  is '服务团队id';
comment on column servicerecords.occurDate
  is '服务时间';
comment on column servicerecords.occurPlace
  is '服务地点';
  comment on column servicerecords.serviceMembers
  is '服务成员(记录所属人)';
comment on column servicerecords.serviceJoiners
  is '参与人员';
comment on column servicerecords.serviceObjects
  is '服务对象';
  comment on column servicerecords.recordType
    is '记录类型（排查类、整改类等）';
comment on column servicerecords.serviceContent
  is '服务内容';
  
---服务记录索引
create index idx_servicerecords_orgid on servicerecords (orgid);
create index idx_servicerecords_createdate on servicerecords (createdate);
  
--服务团队和服务对象的关联关系
--序列
create sequence s_serviceTeamHasObject
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceTeamHasObject(
	id NUMBER(10) not null,
	memberId 			NUMBER(10) 		not null,
	teamId NUMBER(10) not null,
	objectType VARCHAR2(32) not null,
	objectId NUMBER(15) not null,
	objectLogOut	        NUMBER(1)   default 1,
	onDuty               		NUMBER(1)                       default 1 ,
	constraint pkserviceTeamHasObject primary key (id),
	constraint fkserviceTeamHasObjectTeam foreign key (teamId)
  references serviceTeams (id)
);
--注释
comment on table  serviceTeamHasObject
  is '服务团队和服务对象的关联关系';
comment on column serviceTeamHasObject.teamId
  is '服务团队的id';
  comment on column serviceTeamHasObject.memberId
  is '服务人员的id';
comment on column serviceTeamHasObject.objectType
  is '服务对象的类型';
comment on column serviceTeamHasObject.objectId
  is '服务对象的id';
  comment on column serviceTeamHasObject.objectLogOut
  is '对象是否注销';
  comment on column serviceTeamHasObject.onDuty
  is '成员是否在职';
  
--成员和对象关联关系
--序列
create sequence s_serviceMemberHasObject
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceMemberHasObject(
	id NUMBER(10) not null,
	memberId 			NUMBER(10) 		not null,
	teamId NUMBER(10) not null,
	objectType 		VARCHAR2(32) 	not null,
	objectName    varchar2(150) not null,
	objectId 		NUMBER(15) 		not null,
	teamMember number(10) default 0,
	onDuty               		NUMBER(1)                       default 1 ,
	objectLogOut	        NUMBER(1)   default 1,
	constraint pkserviceMemberHasObject primary key (id)
);
--注释
comment on table  serviceMemberHasObject
  is '服务人员和服务服务对象的关联关系';
comment on column serviceMemberHasObject.memberId
  is '服务人员的id';
comment on column serviceMemberHasObject.objectType
  is '服务对象的类型';
  comment on column serviceMemberHasObject.objectName
  is '服务对象的名称';
comment on column serviceMemberHasObject.objectId
  is '服务对象的id';
 comment on column serviceMemberHasObject.teamMember
   is '是否是团队成员';   
comment on column serviceMemberHasObject.onDuty
  is '是否在职';
  comment on column serviceMemberHasObject.objectLogOut
  is '对象是否注销';
--监护人和对象关联关系
--序列
create sequence s_serviceGuardersHasObject
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceGuardersHasObject(
	id NUMBER(10) not null,
	guardersId 			NUMBER(10) 		not null,
	objectType 		VARCHAR2(32) 	not null,
	objectName    varchar2(150) not null,
	objectId 		NUMBER(15) 		not null,
	teamMember number(10) default 0,
	constraint pkserviceGuardersHasObject primary key (id)
);
--注释
comment on table  serviceGuardersHasObject
  is '监护人和服务服务对象的关联关系';
comment on column serviceGuardersHasObject.guardersId
  is '服务人员的id';
comment on column serviceGuardersHasObject.objectType
  is '服务对象的类型';
  comment on column serviceGuardersHasObject.objectName
  is '服务对象的名称';
comment on column serviceGuardersHasObject.objectId
  is '服务对象的id';
 comment on column serviceGuardersHasObject.teamMember
   is '是否是团队成员';   
  --服务记录和对象关联关系
--序列
create sequence s_serviceRecordRelyObject
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceRecordRelyObject(
 	id number(10) not null,
	recordId 			NUMBER(10) 		not null,
	objectType 		VARCHAR2(32) 	not null,
	objectId 		NUMBER(15) 		not null,
	objectName		VARCHAR2(150)	not null,
	orgId     NUMBER(10),
	cardNoOrName    VARCHAR2(150),
	constraint pkserviceRecordRelyObject primary key (id)
);
--注释
comment on table  serviceRecordRelyObject
  is '服务记录和服务服务对象的关联关系';
comment on column serviceRecordRelyObject.recordId
  is '服务记录的id';
comment on column serviceRecordRelyObject.objectType
  is '服务对象的类型';
comment on column serviceRecordRelyObject.objectType
  is '服务对象的id';
comment on column serviceRecordRelyObject.orgId
  is '目标重复数据的orgId';
comment on column serviceRecordRelyObject.cardNoOrName
  is '身份证号码或者场所名';
  
 ---服务记录和服务服务对象的关联关系表索引
create index idx_serviceRcdRelyObj_recordid on serviceRecordRelyObject (recordid);
create index idx_servicerecordobject_objid on servicerecordrelyobject(objectid,objecttype);
create index idx_servObjRely_objecttype on serviceRecordRelyObject (objecttype);

  
--服务记录附件
create sequence s_serviceRecordHasAttachment
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceRecordHasAttachments
(
  id                NUMBER(10) 			not null,
  recordId       	NUMBER(10),
  fileSize       	NUMBER(10)			not null,
  fileName       	VARCHAR2(150) 		not null,
  fileActualUrl  	VARCHAR2(250) 		not null,
  CREATEUSER     	VARCHAR2(32) 		not null,
  UPDATEUSER     	VARCHAR2(32),
  CREATEDATE     	DATE 				not null,
  UPDATEDATE     	DATE,
  constraint PKSERVICERECORDHASATTACHMENT primary key (id)
);
--注释
comment on table serviceRecordHasAttachments
  is '服务记录附件';
comment on column serviceRecordHasAttachments.id
  is '服务记录附件id';
comment on column serviceRecordHasAttachments.recordId
  is '服务记录id';
comment on column serviceRecordHasAttachments.fileSize
  is '附件大小';
comment on column serviceRecordHasAttachments.fileName
  is '附件名称';
comment on column serviceRecordHasAttachments.fileActualUrl
  is '附件路径';
  
  
--成员基本信息
--序列
create sequence s_serviceTeamMemberBase
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 --建表
create table serviceTeamMemberBase  (
    id                   NUMBER(10)                      not null,
   name                 VARCHAR2(60)                    not null,
   gender               NUMBER(10),
   mobile               VARCHAR2(15),
   homePhone            VARCHAR2(32),
   job        varchar2(60),
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   remark               VARCHAR2(600),
   simplePinyin         VARCHAR2(10),
   fullPinyin           VARCHAR2(30),
   birthday				VARCHAR2(10),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE ,
   constraint pkserviceTeamMemberBase primary key (id),
   constraint fkserviceTeamMemberBase foreign key (orgId)
         references organizations (id)
);
--注释
comment on column serviceTeamMemberBase.id
  is '主键';
comment on column serviceTeamMemberBase.name
  is '姓名';
comment on column serviceTeamMemberBase.gender
  is '性别';
comment on column serviceTeamMemberBase.mobile
  is '手机';
comment on column serviceTeamMemberBase.homephone
  is '住宅电话';
  comment on column serviceTeamMemberBase.job
  is '职位';
comment on column serviceTeamMemberBase.orgid
  is '区域id';
comment on column serviceTeamMemberBase.orginternalcode
  is '区域编码';
comment on column serviceTeamMemberBase.remark
  is '备注';
comment on column serviceTeamMemberBase.birthday
  is '出生日期';

-- 服务团队成员详细信息表
--序列
create sequence s_serviceTeamMemberDetails
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 --建表
create table serviceTeamMemberDetails  (
   id                   		NUMBER(10)                      not null,
   teamId						NUMBER(10)  					not null,
   position             		NUMBER(10),
   baseId               		NUMBER(10) not null,
   onDuty               		NUMBER(1)                       default 1 not null,
   shiftDutyReason				VARCHAR2(600),
   shiftDutyDate				DATE,
   createUser           		VARCHAR2(32)                    not null,
   updateUser           		VARCHAR2(32),
   createDate           		DATE                            not null,
   updateDate           		DATE,
   constraint pkserviceTeamMemberDetails primary key (id),
   constraint fkserviceTeamMemberDetails foreign key (baseID) references serviceTeamMemberBase (id)
);
-- 注释
comment on column SERVICETEAMMEMBERDETAILS.id
  is '主键';
comment on column SERVICETEAMMEMBERDETAILS.teamid
  is '团队主键';
comment on column serviceTeamMemberDetails.position
  is '职务';
comment on column SERVICETEAMMEMBERDETAILS.onDuty
  is '是否在职';
comment on column SERVICETEAMMEMBERDETAILS.shiftDutyReason
  is '离职/再重新担任原因';
comment on column SERVICETEAMMEMBERDETAILS.shiftDutyDate
  is '离职/再重新担任日期';
comment on column SERVICETEAMMEMBERDETAILS.baseid
  is '成员基本信息表主键';

  --服务记录和成员关联关系
--序列
create sequence s_serviceRecordRelyMember
increment by 1
start with 1
minvalue 1
cache 10
maxvalue 9999999999;
--建表
create table serviceRecordRelyMember(
	id number(10) not null,
	recordId NUMBER(10) not null,
	memberId number(10) not null,
	memberName varchar2(32) not null,
	constraint pkserviceRecordRelyMember primary key (id)
	
	-------外键关联不需要
	-----,
	-----constraint fkserviceRecordRelyMember foreign key (memberId)
  	-----references serviceTeamMemberDetails (ID)
);
-- 注释
comment on table  serviceRecordRelyMember
  is '服务记录和人员的关联关系';
comment on column serviceRecordRelyMember.recordId
  is '服务记录id';
comment on column serviceRecordRelyMember.memberId
  is '服务人员id';


--服务团队监护人
  --序列
create sequence s_serviceTeamGuarders
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 --建表
create table serviceTeamGuarders  (
	id number(10) not null,
	guarderName varchar2(60) not null,
	gender number(10) not null,
	relation varchar2(60),
	idCardNo varchar2(32),
	mobile varchar2(32),
	phone varchar2(32),
	remark varchar2(3000),
   constraint pkserviceTeamGuarders primary key (id)
);
-- 注释
comment on table serviceTeamGuarders
is '服务团队监护人';
comment on column serviceTeamGuarders.guarderName
is '监护人姓名';
comment on column serviceTeamGuarders.gender
is '性别';
comment on column serviceTeamGuarders.relation
is '身份（与被服务人员关系）';
comment on column serviceTeamGuarders.idCardNo
is '身份证号码';
comment on column serviceTeamGuarders.mobile
is '手机号';
comment on column serviceTeamGuarders.phone
is '固定电话';
comment on column serviceTeamGuarders.remark
is '备注';

--服务成员索引
create index idx_servicememobj_objid on serviceMemberHasObject(objectId);

