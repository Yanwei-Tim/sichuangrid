-- Create 创建单位场所基础表主键索引
create sequence s_COMPANYPLACEBASE
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
commit;
-- Create 创建单位场所基础表
create table COMPANYPLACEBASE
(
  ID               		NUMBER(10) not null,
  ORG            		NUMBER(10) not null,
  ORGINTERNALCODE 		NVARCHAR2(150) not null,
  NAME             		NVARCHAR2(150),
  ATTENTIONEXTENT  		NUMBER(10),
  ADDRESS          		NVARCHAR2(150),
  ISEMPHASIS  			NUMBER(10) default 0,
  ISEMPHASISDATE   		DATE,
  ISEMPHASISREASON 		VARCHAR2(300),
  centerLon          	VARCHAR2(32),
  centerLat		  		VARCHAR2(32),
  centerLon2			VARCHAR2(32),
  centerLat2			VARCHAR2(32),
  featureId		  		VARCHAR2(32),
  buildDataId		  	NUMBER(10),
  SOURCESSTATE     		NUMBER(10),
  IMGURL           		VARCHAR2(300),
  CREATEUSER       		VARCHAR2(60) not null,
  UPDATEUSER       		VARCHAR2(60),
  CREATEDATE       		DATE not null,
  UPDATEDATE       		DATE,
  constraint PKCOMPANYPLACEBASE primary key (ID)
);
commit;
-- Add comments to the table 
comment on table COMPANYPLACEBASE
  is '单位场所基础表';
-- Add comments to the columns 

comment on column COMPANYPLACEBASE.ID
  is '主键';
--comment on column COMPANYPLACEBASE.model
--is '模块名称';
comment on column COMPANYPLACEBASE.ORG
  is '所属网格';
comment on column COMPANYPLACEBASE.ORGINTERNALCODE
  is '内置编码';
comment on column COMPANYPLACEBASE.NAME
  is '名称';
comment on column COMPANYPLACEBASE.ATTENTIONEXTENT
  is '关注度：一般；中等；严重（字典项）';
comment on column COMPANYPLACEBASE.ADDRESS
  is '地址';
comment on column COMPANYPLACEBASE.ISEMPHASIS
  is '是否关注';
comment on column COMPANYPLACEBASE.ISEMPHASISDATE
  is '关注日期';
comment on column COMPANYPLACEBASE.ISEMPHASISREASON
  is '关注原因';
comment on column COMPANYPLACEBASE.centerLon
  is '经度(三维)';
comment on column COMPANYPLACEBASE.centerLat
  is '纬度(三维)';
comment on column COMPANYPLACEBASE.centerLon2
  is '经度(二维)';
comment on column COMPANYPLACEBASE.centerLat2
  is '纬度(二维)';
comment on column COMPANYPLACEBASE.featureId
  is '热点ID';
comment on column COMPANYPLACEBASE.buildDataId
  is '楼宇id';
comment on column COMPANYPLACEBASE.SOURCESSTATE
  is '数据来源(pc，手机)';
comment on column COMPANYPLACEBASE.IMGURL
  is '图片路径';  
commit;
----------------------------------------------------------------
-- Create 创建单位场所主键索引
create sequence s_COMPANYPLACE
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
commit;
  -- create 创建单位场所表
  create table COMPANYPLACE
(
  ID               	 NUMBER(10) not null,
  BASEID             NUMBER(10),
  TYPE               NUMBER(10),
  CLASSIFICATION     NUMBER(10),
  CLASSIFICATIONEN 	 NVARCHAR2(150),
  CATEGORY           NUMBER(10),
  USERNAME           NVARCHAR2(60),
  MOBILENUMBER       NVARCHAR2(50),
  TELEPHONE          NVARCHAR2(80),
  FAXNUMBER          NVARCHAR2(80),
  AREA               NVARCHAR2(300),
  COVEREDAREA        NUMBER(15,5),
  REMARKS            NVARCHAR2(600),
  HASLICENSE         NUMBER(10) default 0 ,
  BUSINESSLICENSENO  NVARCHAR2(150),
  ORGCODE            NVARCHAR2(150),
  CLOAKROOM          NVARCHAR2(150),
  SCALETYPE          NUMBER(10),
  PARTYCOUNTNUMBER   NUMBER(10),
  networkCardNo 	 NUMBER(10),
  REGISTEREDCAPITAL  NUMBER(15,5),
  registeredCapitalNo  NUMBER(15,5),
  BEGINTIME          DATE,
  ENDTIME            DATE,
  BUSINESSAREA       NUMBER(15,5),
  DIGVOLUME          NUMBER(15,5),
  FILLVOLUME         NUMBER(15,5),
  EMAIL              NVARCHAR2(150),
  HOSPITALNATURE     NUMBER(10),
  GENERALSTORAGE     NVARCHAR2(150),
  GENERALTYPE        NVARCHAR2(150),
  FAXNNO             NVARCHAR2(80),
  NOWIP              NVARCHAR2(60),
  COUNTNO            NUMBER(10),
  GENERALMANAGE      NVARCHAR2(150),
  GENERALMENTE       NVARCHAR2(150),
  FIREAUDITOPINIONNO NVARCHAR2(150),
  INTERNETBARNO      NVARCHAR2(60),
  LEGALVICEPRINCIPAL NVARCHAR2(60),
  SCHOOLNATURE       NUMBER(10),
  SCHOOLNAMEEN       NVARCHAR2(200),
  SCHOOLWEBSITE      NVARCHAR2(200),
  ECONOMICNATURE     NUMBER(10),
  MANAGEMENTLEVEL    NUMBER(10),
  FIRELEVEL          NUMBER(10),
  CREATEUSER         VARCHAR2(60) not null,
  UPDATEUSER         VARCHAR2(60),
  CREATEDATE         DATE not null,
  UPDATEDATE         DATE ,
  constraint PKCOMPANYPLACE primary key (ID)
);
commit;
-- Add comments to the columns 
comment on column COMPANYPLACE.BASEID
  is '基础单位场所ID';
comment on column COMPANYPLACE.TYPE
  is '类型一级(单位，场所)字典项';
comment on column COMPANYPLACE.CLASSIFICATION
  is '分类二级 字典项';
comment on column COMPANYPLACE.CLASSIFICATIONEN
  is '类型(单位、场所)二级 英文 字典项';
comment on column COMPANYPLACE.CATEGORY
  is '类别三级字典项';
comment on column COMPANYPLACE.USERNAME
  is '负责人（校长，法人等）';
comment on column COMPANYPLACE.MOBILENUMBER
  is '联系手机';
comment on column COMPANYPLACE.TELEPHONE
  is '联系电话';
comment on column COMPANYPLACE.FAXNUMBER
  is '传真号码';
comment on column COMPANYPLACE.AREA
  is '通用字段：所在区域（主管单位，通道口、施工单位、周边情况、所属单位、副本许可范围、经营范围、所在派出所、主管单位）';
comment on column COMPANYPLACE.COVEREDAREA
  is '面积（占地面积，施工面积）';
comment on column COMPANYPLACE.REMARKS
  is '备注';
comment on column COMPANYPLACE.HASLICENSE
  is '是否有证照（0：无，1：有）';
comment on column COMPANYPLACE.BUSINESSLICENSENO
  is '营业执照号码';
comment on column COMPANYPLACE.ORGCODE
  is '组织机构码';
comment on column COMPANYPLACE.CLOAKROOM
  is '小件寄存处';
comment on column COMPANYPLACE.SCALETYPE
  is '规模类型（规上，规下 字典项）';
comment on column COMPANYPLACE.PARTYCOUNTNUMBER
  is '通用字段（党员数，临时上网卡数）';
comment on column COMPANYPLACE.networkCardNo
  is '临时上网卡数';
comment on column COMPANYPLACE.REGISTEREDCAPITAL
  is '注册资金';
comment on column COMPANYPLACE.registeredCapitalNo
  is '注册资本'; 
comment on column COMPANYPLACE.BEGINTIME
  is '开始时间（施工，注册）';
comment on column COMPANYPLACE.ENDTIME
  is '结束时间（施工，注册）';
comment on column COMPANYPLACE.BUSINESSAREA
  is '营业面积（平方米 ）';
comment on column COMPANYPLACE.DIGVOLUME
  is '施工挖方量（立方米）';
comment on column COMPANYPLACE.FILLVOLUME
  is '施工填方量（立方米）';
comment on column COMPANYPLACE.EMAIL
  is '电子邮件';
comment on column COMPANYPLACE.HOSPITALNATURE
  is '医院性质（公立，私立，合资，外资，其他 字典项）';
comment on column COMPANYPLACE.GENERALSTORAGE
  is '通用字段（存储设备，注册地址，接入方式）';
comment on column COMPANYPLACE.GENERALTYPE
  is '通用字段（货物类别，宽带接入方式）';
comment on column COMPANYPLACE.FAXNNO
  is '传真号码2（）';
comment on column COMPANYPLACE.NOWIP
  is '现在使用IP';
comment on column COMPANYPLACE.COUNTNO
  is '通用字段（电脑台数，从业人数）';
comment on column COMPANYPLACE.GENERALMANAGE
  is '通用字段（网络文化经营许可证号，主管部门）';
comment on column COMPANYPLACE.GENERALMENTE
  is '通用字段（网络安全审核意见书号，管理部门）';
comment on column COMPANYPLACE.FIREAUDITOPINIONNO
  is '消防审核意见书号';
comment on column COMPANYPLACE.INTERNETBARNO
  is '网吧编号';
comment on column COMPANYPLACE.LEGALVICEPRINCIPAL
  is '法制副校长';
comment on column COMPANYPLACE.SCHOOLNATURE
  is '学校性质（0公办，1民办，2其他 字典项）';
comment on column COMPANYPLACE.SCHOOLNAMEEN
  is '学校英文名称';
comment on column COMPANYPLACE.SCHOOLWEBSITE
  is '学校网址';
comment on column COMPANYPLACE.ECONOMICNATURE
  is '经济性质（0：非公司企业法人，1：）[字典？]';
comment on column COMPANYPLACE.MANAGEMENTLEVEL
  is '管理等级（0：A，1：B，2：C）';
comment on column COMPANYPLACE.FIRELEVEL
  is '消防等级（1：一级…………）';
commit;
 ------------------------------------------------------
--创建单位场所业务表索引
create sequence s_COMPANYPLACEBUSINESS
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
commit;
-- Create 创建单位场所业务表
create table COMPANYPLACEBUSINESS
(
  ID                   NUMBER(10),
  ISKEYTYPE            NUMBER(10),
  HIDDANGERINFO        NVARCHAR2(300),
  CORRECTIONINFO       NVARCHAR2(300),
  LISTEDCORRECTION     NUMBER(10),
  LISTEDLEVE           NUMBER(10),
  CORRECTIONBEIGINDATE DATE,
  CORRECTIONENDDATE    DATE,
  EFFECTASSESSMENT     NUMBER(10),
  POLLUTIONTYPE        NUMBER(10),
  POLLUTIONINFO        NVARCHAR2(300),
  CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE ,
 constraint PKCOMPANYPLACEBUSINESS primary key (ID)
);
commit;
-- Add comments to the table 
comment on table COMPANYPLACEBUSINESS
  is '单位场所业务表';
-- Add comments to the columns 
comment on column COMPANYPLACEBUSINESS.ID
  is '业务ID';
comment on column COMPANYPLACEBUSINESS.ISKEYTYPE
  is '是否安全生产重点（是否消防安全重点、是否治安重点、是否染污源）';
comment on column COMPANYPLACEBUSINESS.HIDDANGERINFO
  is '隐患情况';
comment on column COMPANYPLACEBUSINESS.CORRECTIONINFO
  is '隐患整改情况';
comment on column COMPANYPLACEBUSINESS.LISTEDCORRECTION
  is '挂牌整治（单选   0：省，1：市，2：县）';
comment on column COMPANYPLACEBUSINESS.LISTEDLEVE
  is '挂牌等级（下拉   0：黄牌，1：红牌）';
comment on column COMPANYPLACEBUSINESS.CORRECTIONBEIGINDATE
  is '整改开始时间';
comment on column COMPANYPLACEBUSINESS.CORRECTIONENDDATE
  is '整改结束时间';
comment on column COMPANYPLACEBUSINESS.EFFECTASSESSMENT
  is '效果评估（下拉：0：好，1：较好，2：差，3：较差）';
comment on column COMPANYPLACEBUSINESS.POLLUTIONTYPE
  is '污染类型（下拉：字典项）';
comment on column COMPANYPLACEBUSINESS.POLLUTIONINFO
  is '污染情况';
commit;
------------------------------------------------------------
-- Create 单位场所信息与业务关联表
create table COMPANYPLACEBUSINESSRELATION
(
  BASEID        NUMBER(10),
  BUSINESSID    NUMBER(10)
);
commit;
-- Add comments to the columns 
comment on column COMPANYPLACEBUSINESSRELATION.BASEID
  is '基础信息ID';
comment on column COMPANYPLACEBUSINESSRELATION.BUSINESSID
  is '业务表ID';
commit;
 ------------------------------------------------------
--创建单位场所业务表索引
create sequence s_COMPANYPLACEANNEX
increment by 1
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
commit;
-- Create 创建附件表
create table COMPANYPLACEANNEX
(
  ID            number(10),
  BUSINESSID    NUMBER(10),
  FILENAME 		NVARCHAR2(150),
  ANNEXADDRESS  NVARCHAR2(500),
  CREATEUSER    VARCHAR2(60) not null,
  UPDATEUSER    VARCHAR2(60),
  CREATEDATE    DATE not null,
  UPDATEDATE    DATE,
  constraint PKCOMPANYPLACEANNEX primary key (ID)
);
commit;
-- Add comments to the table 
comment on table COMPANYPLACEANNEX
  is '单位场所附件表';
-- Add comments to the columns 
comment on column COMPANYPLACEANNEX.BUSINESSID
  is '业务信息ID';
comment on column COMPANYPLACEANNEX.ANNEXADDRESS
  is '附件地址';
commit;


--权限表 新增网格视图路径字段
alter table permissions add (gridUrl varchar2(200));

