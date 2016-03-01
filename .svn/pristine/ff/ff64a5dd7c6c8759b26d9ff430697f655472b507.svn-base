----重点场所中7月19日后认领进来的数据迁移
--创建单位场所基础表临时表(7月19日后数据迁移)
create table companyplacebase_temp
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
  PCORMOBILE			NUMBER(10),
  IMGURL           		VARCHAR2(300),
  CREATEUSER       		VARCHAR2(60) not null,
  UPDATEUSER       		VARCHAR2(60),
  CREATEDATE       		DATE not null,
  UPDATEDATE       		DATE,
  constraint companyplacebase_temp primary key (ID)
);
----------------------------------------------------------------
--创建单位场所临时表(7月19日后数据迁移)
  create table companyplace_temp
(
  ID               	 	NUMBER(10) not null,		
  BASEID           		NUMBER(10),			
  TYPE              	NUMBER(10),			
  CLASSIFICATION     	NUMBER(10),			
  CLASSIFICATIONEN 	 	NVARCHAR2(150),		
  CATEGORY           	NUMBER(10),			
  USERNAME           	NVARCHAR2(150),			
  MOBILENUMBER       	NVARCHAR2(50),			
  TELEPHONE          	NVARCHAR2(80),			
  FAXNUMBER          	NVARCHAR2(150),			
  AREA               	NVARCHAR2(300),			
  COVEREDAREA        	NUMBER(15,5),			
  REMARKS            	NVARCHAR2(900),			
  HASLICENSE         	NUMBER(10) default 0 ,			
  BUSINESSLICENSENO  	NVARCHAR2(150),			
  ORGCODE            	NVARCHAR2(150),			
  CLOAKROOM          	NVARCHAR2(150),			
  SCALETYPE          	NUMBER(10),			
  PARTYCOUNTNUMBER   	NUMBER(10),			
  networkCardNo 	 	NUMBER(10),		
  REGISTEREDCAPITAL  	NUMBER(15,5),			
  registeredCapitalNo   NUMBER(15,5),			
  BEGINTIME          	DATE,			
  ENDTIME            	DATE,			
  BUSINESSAREA       	NUMBER(15,5),			
  DIGVOLUME          	NUMBER(15,5),			
  FILLVOLUME         	NUMBER(15,5),			
  EMAIL              	NVARCHAR2(150),			
  HOSPITALNATURE     	NUMBER(10),			
  GENERALSTORAGE     	NVARCHAR2(150),			
  GENERALTYPE        	NVARCHAR2(150),			
  FAXNNO             	NVARCHAR2(80),			
  NOWIP              	NVARCHAR2(180),			
  COUNTNO            	NUMBER(10),			
  GENERALMANAGE      	NVARCHAR2(150),			
  GENERALMENTE       	NVARCHAR2(150),			
  FIREAUDITOPINIONNO 	NVARCHAR2(150),			
  INTERNETBARNO      	NVARCHAR2(60),			
  LEGALVICEPRINCIPAL 	NVARCHAR2(60),			
  SCHOOLNATURE       	NUMBER(10),			
  SCHOOLNAMEEN       	NVARCHAR2(200),			
  SCHOOLWEBSITE      	NVARCHAR2(200),			
  ECONOMICNATURE     	NUMBER(10),			
  MANAGEMENTLEVEL    	NUMBER(10),			
  FIRELEVEL          	NUMBER(10),			
  CREATEUSER         	VARCHAR2(60) not null,			
  UPDATEUSER         	VARCHAR2(60),			
  CREATEDATE         	DATE not null,			
  UPDATEDATE         	DATE,			
  constraint companyplace_temp primary key (ID)
);
 ------------------------------------------------------
-- 创建单位场所业务临时表(7月19日后数据迁移)
create table companyplacebusiness_temp
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
 constraint companyplacebusiness_temp primary key (ID)
);
------------------------------------------------------------
--单位场所信息与业务关联临时表(7月19日后数据迁移)
create table companyplacebusslation_temp
(
  BASEID        NUMBER(10),
  BUSINESSID    NUMBER(10)
);

--单位场所基础表新增KeyPlace表关联字段Id_Key
ALTER TABLE companyplacebase_temp ADD (id_key number(10));
--单位场业务表新增单位场所表关联字段baseID
ALTER TABLE companyplacebusiness_temp ADD (companyplacebaseid number(10));

--增加冗余新学校类型字段
ALTER TABLE schools ADD (newtype number(10));
--把学校类型新类型字段更新为新单位场所的字典项ID
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '小学' AND p.internalid = 10201) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '小学' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '幼儿园' AND p.internalid = 10202) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '幼儿园' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '托管机构' AND p.internalid = 10203) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '托管机构' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '中学' AND p.internalid = 10204) WHERE s.type =(SELECT id FROM  propertydicts p WHERE p.displayname = '中学' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '大专院校' AND p.internalid = 10205) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '大专院校' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '成人教育、职高、技校等' AND p.internalid = 10206) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '职业学校' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));
update schools s set s.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '教育-其他' AND p.internalid = 10210) WHERE s.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '其它' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='学校类型'));

--增加冗余新医院类型字段
ALTER TABLE hospitals ADD (newtype number(10));
--把医院类型新类型字段更新为新单位场所的字典项ID
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '综合医院' AND p.internalid = 10301) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '综合医院' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '专科医院' AND p.internalid = 10305) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '专科医院' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type in (SELECT id FROM  propertydicts p WHERE p.displayname = '其他' AND p.propertydomainid = (SELECT id FROM propertydomains pd WHERE pd.domainname='医院类型'));
update hospitals h set h.newtype = (SELECT id FROM  propertydicts p WHERE p.displayname = '门诊部、医务室、诊所' AND p.internalid = 10308) WHERE h.type is null;

--创建单位场所信息临时表
create table complaceInfo_temp (
   id_key               number(10)      not null,
   classificationen     NVARCHAR2(150),
   companyplaceId       NUMBER(10)      not null,
   scaletype            NUMBER(10)
);
--创建单位场所信息临时表id_key索引
create index idx_complaceInfo_temp_idkey on complaceInfo_temp(id_key);