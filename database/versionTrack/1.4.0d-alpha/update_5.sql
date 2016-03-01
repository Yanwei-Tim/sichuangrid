-----区域内主要党组织资源
create sequence s_partyResource
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;

 create table partyResource (
  id                   NUMBER(10)                      not null,
  orgId                NUMBER(10)                      not null,
  orgInternalCode      VARCHAR2(32)                    not null,
  name                 VARCHAR2(150)           not null,
  address              VARCHAR2(150)          ,
  manager              VARCHAR2(60),
  telephone            VARCHAR2(15) ,
  remark               VARCHAR2(900),
  createUser           VARCHAR2(32)                    not null,
  updateUser           VARCHAR2(32),
  createDate           DATE                            not null,
  updateDate           DATE,
  constraint       pkPartyResource primary key(id)
);
comment on table partyResource is
'区域内主要党组织资源';
comment on column partyResource.id is
'ID';
comment on column partyResource.orgId is
'所属网格';
comment on column partyResource.orgInternalCode is
'所属责任区编号';
comment on column partyResource.name is
'组织名称';
comment on column partyResource.address is
'地点';
comment on column partyResource.manager is
'负责人';
comment on column partyResource.telephone is
'联系电话';
comment on column partyResource.remark is
'备注';


create sequence S_activityRecords
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20; 

create sequence S_activityRecordsAttachFiles
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20; 

create table activityRecords(
  ID                      NUMBER(10) not null,
  ORGID                   NUMBER(10) not null,
  ORGINTERNALCODE         VARCHAR2(50),
  ORGANIZATIONID      	  NUMBER(10) not null,
  ORGANIZATIONTYPE	  	  VARCHAR2(50),
  ACTIVITYDATE			  DATE,
  ACTIVITYPLACE			  VARCHAR2(600),
  ACTIVITYTHEME			  VARCHAR2(300),
  ORGANIZER				  VARCHAR2(500),
  PARTICIPANT			  VARCHAR2(500),
  DETAILS				  CLOB,
  ISATTACHMENT    		  NUMBER(2) default 0,
  CREATEUSER              VARCHAR2(30) not null,
  UPDATEUSER              VARCHAR2(30),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint pkactivityRecords primary key (ID)
);
comment on table activityRecords
  is '组织活动';
comment on column activityRecords.ID
  is '组织活动ID';
comment on column activityRecords.ORGID
  is '所属网格';
comment on column activityRecords.ORGINTERNALCODE
  is '部门内部编号';
comment on column activityRecords.ORGANIZATIONID
  is '组织id';
comment on column activityRecords.ORGANIZATIONTYPE
  is '组织类型';
comment on column activityRecords.ACTIVITYDATE
  is '活动时间';
comment on column activityRecords.ACTIVITYPLACE
  is '活动地点';
comment on column activityRecords.ACTIVITYTHEME
  is '活动主题';
comment on column activityRecords.ORGANIZER
  is '组织者';
comment on column activityRecords.PARTICIPANT
  is '参与者';
comment on column activityRecords.DETAILS
  is '活动内容';
comment on column activityRecords.ISATTACHMENT
  is '是否有附件';
comment on column activityRecords.CREATEUSER
  is '创建用户';
comment on column activityRecords.UPDATEUSER
  is '修改用户';
comment on column activityRecords.CREATEDATE
  is '创建时间';
comment on column activityRecords.UPDATEDATE
  is '修改时间';

create table activityRecordsAttachFiles(
  ID                      NUMBER(10) not null,
  activityRecordId	  	  NUMBER(10) not null,
  fileName			  	  VARCHAR2(300) not null,
  FILEACTUALURL 		  VARCHAR2(300) not null,
  CREATEUSER              VARCHAR2(30) not null,
  UPDATEUSER              VARCHAR2(30),
  CREATEDATE              DATE not null,
  UPDATEDATE              DATE,
  constraint pkactivityRecordsAttachFiles primary key (ID),
  constraint fkactivityRecords foreign key (activityRecordId)
         references activityRecords (id)
);

-- Add comments to the table 
comment on table activityRecordsAttachFiles
  is '组织活动附件表';
-- Add comments to the columns 
comment on column activityRecordsAttachFiles.ID
  is '组织活动ID';
comment on column activityRecordsAttachFiles.activityRecordId
  is '组织活动ID';
comment on column activityRecordsAttachFiles.FILENAME
  is '组织活动附件名';
comment on column activityRecordsAttachFiles.FILEACTUALURL
  is '组织活动附件路径';
comment on column activityRecordsAttachFiles.CREATEUSER
  is '创建用户';
comment on column activityRecordsAttachFiles.UPDATEUSER
  is '修改用户';
comment on column activityRecordsAttachFiles.CREATEDATE
  is '创建时间';
comment on column activityRecordsAttachFiles.UPDATEDATE
  is '修改时间';
  
  
    
/*==============================================================*/
/* sequence: DISTRICT_BASICCASE 社区基本情况序列                              		*/
/*==============================================================*/
create sequence s_DISTRICT_BASICCASE
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

/*==============================================================*/
/* Table: DISTRICT_BASICCASE 社区基本情况表                              			*/
/*==============================================================*/ 
create table DISTRICT_BASICCASE  (
	id			 			NUMBER(10)                      not null,
	orgId           		NUMBER(10)                      not null,
	orgInternalCode      	VARCHAR2(32)                    not null,
	partyOrgsAndMembersCase		CLOB,
	administrativeCases			CLOB,
	streetPartySituation		CLOB,
	systemConstruction			CLOB,
	regionalPartySituation		CLOB,
	volunteersSituation			CLOB,
	doubleRegistrationSituation	CLOB,
	imgUrl               	VARCHAR2(50),
	createUser           	VARCHAR2(32)                    not null,
    updateUser           	VARCHAR2(32),
	createDate           	DATE                            not null,
   	updateDate          	DATE,
   	constraint PKDISTRICT_BASICCASE primary key (ID)
);
-- Add comments to the table 
comment on table DISTRICT_BASICCASE
  is '社区基本情况表';
-- Add comments to the columns 
comment on column DISTRICT_BASICCASE.ID
  is '社区基本情况id';
comment on column DISTRICT_BASICCASE.orgId
  is '所属网格';
comment on column DISTRICT_BASICCASE.orgInternalCode
  is '所属网格编号';
comment on column DISTRICT_BASICCASE.partyOrgsAndMembersCase
  is '全区党组织及党员情况';
comment on column DISTRICT_BASICCASE.administrativeCases
  is '机关党建情况';  
comment on column DISTRICT_BASICCASE.streetPartySituation
  is '街道党建情况'; 
comment on column DISTRICT_BASICCASE.systemConstruction
  is '系统党建情况';  
comment on column DISTRICT_BASICCASE.regionalPartySituation
  is '区域党建情况';   
comment on column DISTRICT_BASICCASE.volunteersSituation
  is '志愿者情况';   
comment on column DISTRICT_BASICCASE.doubleRegistrationSituation
  is '双报到情况'; 
comment on column DISTRICT_BASICCASE.imgUrl
  is '社区基本情况图片地址';   
comment on column DISTRICT_BASICCASE.CREATEUSER
  is '创建人';
comment on column DISTRICT_BASICCASE.UPDATEUSER
  is '修改人';
comment on column DISTRICT_BASICCASE.CREATEDATE
  is '创建时间';
comment on column DISTRICT_BASICCASE.UPDATEDATE
  is '修改时间';
  
/*==============================================================*/
/* sequence: BASIC_CASE 基本情况序列                              		*/
/*==============================================================*/
create sequence s_BASICCASE
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
/*==============================================================*/
/* sequence: BASIC_CASE 基本情况序列                              					*/
/*==============================================================*/
 create table BASIC_CASE  (
	id			 			NUMBER(10)                      not null,
	orgId           		NUMBER(10)                      not null,
	orgInternalCode      	VARCHAR2(32)                    not null,
	baseCase				CLOB,
	imgUrl               	VARCHAR2(50),
	createUser           	VARCHAR2(32)                    not null,
    updateUser           	VARCHAR2(32),
	createDate           	DATE                            not null,
   	updateDate          	DATE,
   	constraint PKBASIC_CASE primary key (ID)
);
-- Add comments to the table 
comment on table BASIC_CASE
  is '基本情况表';
-- Add comments to the columns 
comment on column BASIC_CASE.ID
  is '基本情况id';
comment on column BASIC_CASE.orgId
  is '所属网格';
comment on column BASIC_CASE.orgInternalCode
  is '所属网格编号';
comment on column BASIC_CASE.baseCase
  is '基本情况';
comment on column BASIC_CASE.imgUrl
  is '基本情况图片地址';   
comment on column BASIC_CASE.CREATEUSER
  is '创建人';
comment on column BASIC_CASE.UPDATEUSER
  is '修改人';
comment on column BASIC_CASE.CREATEDATE
  is '创建时间';
comment on column BASIC_CASE.UPDATEDATE
  is '修改时间';
  
  
/*==============================================================*/
/* sequence: PARTYWORK_MEMBERS 党工委成员序列                              					*/
/*==============================================================*/ 
create sequence s_PARTYWORK_MEMBERS
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
 /*==============================================================*/
/* Table: PARTYWORK_MEMBERS 党工委成员表                              				*/
/*==============================================================*/
create table PARTYWORK_MEMBERS(
	id				NUMBER(10)   	not null,
	orgId			NUMBER(10),
	orgCode			VARCHAR2(30),
	name			VARCHAR2(60)  	not null,
	gender			NUMBER(10)		not null,
	duty			VARCHAR2(64)	not null,
	introduction	CLOB			not null,
	imageUrl		VARCHAR2(128),
	sort			NUMBER(10),
	createUser		VARCHAR2(60) 	not null,
	updateUser		VARCHAR2(60),
	createDate		DATE 			not null,
	updateDate		DATE,
   	constraint PKPARTYWORK_MEMBERS primary key (ID)
);

-- Add comments to the table 
comment on table PARTYWORK_MEMBERS
  is '党工委成员表';
-- Add comments to the columns 
comment on column PARTYWORK_MEMBERS.ID
  is '党工委成员id';
comment on column PARTYWORK_MEMBERS.orgId
  is '所属网格';
comment on column PARTYWORK_MEMBERS.orgCode
  is '所属网格编号';
comment on column PARTYWORK_MEMBERS.name
  is '姓名';
comment on column PARTYWORK_MEMBERS.gender
  is '性别(0男，1女，2未知)';   
comment on column PARTYWORK_MEMBERS.duty
  is '职位';
comment on column PARTYWORK_MEMBERS.introduction
  is '介绍'; 
comment on column PARTYWORK_MEMBERS.imageUrl
  is '图片地址';
comment on column PARTYWORK_MEMBERS.sort
  is '排序值';  
comment on column PARTYWORK_MEMBERS.CREATEUSER
  is '创建人';
comment on column PARTYWORK_MEMBERS.UPDATEUSER
  is '修改人';
comment on column PARTYWORK_MEMBERS.CREATEDATE
  is '创建时间';
comment on column PARTYWORK_MEMBERS.UPDATEDATE
  is '修改时间';

