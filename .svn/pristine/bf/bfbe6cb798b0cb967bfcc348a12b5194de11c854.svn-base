/*==============================================================*/
/* sequence: CASEIMAGEUPLOAD 图片上传序列                              				*/
/*==============================================================*/   
create sequence s_CASEIMAGEUPLOAD
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: CASEIMAGEUPLOAD 图片上传表                              					*/
/*==============================================================*/
create table CASEIMAGEUPLOAD(
	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	imgUrl               	VARCHAR2(128),
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
   	constraint PKCASEIMAGEUPLOAD primary key (ID)
);
-- Add comments to the table 
comment on table CASEIMAGEUPLOAD
  is '基本情况图片上传表';
-- Add comments to the columns 
comment on column CASEIMAGEUPLOAD.ID
  is '图片上传id';
comment on column CASEIMAGEUPLOAD.orgId
  is '所属网格';
comment on column CASEIMAGEUPLOAD.orgInternalCode
  is '所属网格编号';
comment on column CASEIMAGEUPLOAD.imgUrl
  is '图片地址';  
comment on column CASEIMAGEUPLOAD.CREATEUSER
  is '创建人';
comment on column CASEIMAGEUPLOAD.UPDATEUSER
  is '修改人';
comment on column CASEIMAGEUPLOAD.CREATEDATE
  is '创建时间';
comment on column CASEIMAGEUPLOAD.UPDATEDATE
  is '修改时间'; 
  

/*==============================================================*/
/* Sequence: s_peopleAspirations   台账报表序列	                */
/*==============================================================*/
create sequence s_accountReports
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
/*==============================================================*/
/* Table: peopleAspirations   台账报表表	                        */
/*==============================================================*/
 create table accountReports(
 	id					NUMBER(10)                      not null,
 	orgId				NUMBER(10)                      not null,
 	orgInternalCode 	VARCHAR2(32)                    not null,
 	reportYear			VARCHAR2(4),
 	reportMonth			VARCHAR2(2),
 	content				CLOB,
 	accountType			VARCHAR2(100),
 	reportType			NUMBER(10),
 	createUser          VARCHAR2(60) 					not null,
	updateUser          VARCHAR2(60),
	createDate          DATE         					not null,
	updateDate          DATE,
 	constraint ACCOUNTREPORTS primary key (ID)
);
 
  -- Add comments to the table 
comment on table accountReports
  is '台账报表表';
-- Add comments to the columns 
comment on column accountReports.id
  is '台账报表id'; 
comment on column accountReports.orgId
  is '所属网格'; 
comment on column accountReports.orgInternalCode
  is '所属网格编号'; 
comment on column accountReports.reportYear
  is '统计的年份'; 
comment on column accountReports.reportMonth
  is '统计的月份'; 
comment on column accountReports.content
  is '报表明细及内容描述'; 
comment on column accountReports.accountType
  is '台账类型'; 
comment on column accountReports.reportType
  is '报表类型'; 
comment on column accountReports.createUser
  is '创建用户';
comment on column accountReports.updateUser 
  is '修改用户';
comment on column accountReports.createDate 
  is '创建日期';
comment on column accountReports.updateDate 
  is '修改时间';
 
 /*==============================================================*/
/* Table: peopleAspirations   台账报表--时间索引                             		 */
/*==============================================================*/ 
create index idx_year_month_accountReports on accountReports (
   reportYear ASC,
   reportMonth ASC
);


alter table BuildLayout modify room VARCHAR2(150);
