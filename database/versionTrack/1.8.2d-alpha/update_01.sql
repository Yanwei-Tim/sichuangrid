create sequence S_DM_COMPANYPLACE_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table DM_COMPANYPLACE_TEMP
(
	ID                  NUMBER(10) not null,
	BASEID             NUMBER(10),
	ORG                NUMBER(10) not null,
	ORGINTERNALCODE     NVARCHAR2(150) not null,
	NAME                 NVARCHAR2(150),
 	ATTENTIONEXTENT      NUMBER(10),
	ADDRESS              NVARCHAR2(150),
	ISEMPHASIS        NUMBER(10) default 0,
	ISEMPHASISDATE       DATE,
	ISEMPHASISREASON     VARCHAR2(300),
	centerLon            VARCHAR2(32),
	centerLat          VARCHAR2(32),
	centerLon2      VARCHAR2(32),
	centerLat2      VARCHAR2(32),
	featureId          VARCHAR2(32),
	buildDataId        NUMBER(10),
	SOURCESSTATE         NUMBER(10),
	PCORMOBILE			NUMBER(10),
	IMGURL               VARCHAR2(300),
	TYPE               NUMBER(10),
	CLASSIFICATION     NUMBER(10),
	CLASSIFICATIONEN    NVARCHAR2(150),
	CATEGORY           NUMBER(10),
	USERNAME           NVARCHAR2(150),
	MOBILENUMBER       NVARCHAR2(50),
	TELEPHONE          NVARCHAR2(80),
	FAXNUMBER          NVARCHAR2(150),
	AREA               NVARCHAR2(300),
	COVEREDAREA        NUMBER(15,5),
	REMARKS            NVARCHAR2(900),
	HASLICENSE         NUMBER(10) default 0 ,
	BUSINESSLICENSENO  NVARCHAR2(150),
	ORGCODE            NVARCHAR2(150),
	CLOAKROOM          NVARCHAR2(150),
	SCALETYPE          NUMBER(10),
	PARTYCOUNTNUMBER   NUMBER(10),
	networkCardNo    NUMBER(10),
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
	NOWIP              NVARCHAR2(180),
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
	CLAIMSTATE    NUMBER(10)  default 0,
	CLAIMDATE    DATE,
	CLAIMUSERNAME    VARCHAR2(4000),
	CLAIMUSERID    NUMBER(10),
	CLAIMORGID    NUMBER(10),
	IMPORTDEPARTMENTID  NUMBER(10)  not null,
	OLDORGID    NUMBER(10),
	INID      NUMBER(10),
	DISPOSE      NUMBER(10)  default 0,
	CLAIMAVAILABLE    NUMBER(10)  default 0,
	IMPORTDATE    DATE,
	DISTRICTORGID    NUMBER(10),
	CREATEUSER         VARCHAR2(60) not null,
	UPDATEUSER         VARCHAR2(60),
	CREATEDATE         DATE not null,
	UPDATEDATE         DATE ,
	constraint DM_COMPANYPLACE_TEMP primary key (ID)
);

commit;
comment on table DM_COMPANYPLACE_TEMP
  is '单位场所数据管理表';
comment on column DM_COMPANYPLACE_TEMP.ID
  is '主键';
comment on column DM_COMPANYPLACE_TEMP.BASEID
  is '基础单位场所ID';
comment on column DM_COMPANYPLACE_TEMP.ORG
  is '所属网格';
comment on column DM_COMPANYPLACE_TEMP.ORGINTERNALCODE
  is '内置编码';
comment on column DM_COMPANYPLACE_TEMP.NAME
  is '名称';
comment on column DM_COMPANYPLACE_TEMP.ATTENTIONEXTENT
  is '关注度：一般；中等；严重（字典项）';
comment on column DM_COMPANYPLACE_TEMP.ADDRESS
  is '地址';
comment on column DM_COMPANYPLACE_TEMP.ISEMPHASIS
  is '是否关注';
comment on column DM_COMPANYPLACE_TEMP.ISEMPHASISDATE
  is '关注日期';
comment on column DM_COMPANYPLACE_TEMP.ISEMPHASISREASON
  is '关注原因';
comment on column DM_COMPANYPLACE_TEMP.centerLon
  is '经度(三维)';
comment on column DM_COMPANYPLACE_TEMP.centerLat
  is '纬度(三维)';
comment on column DM_COMPANYPLACE_TEMP.centerLon2
  is '经度(二维)';
comment on column DM_COMPANYPLACE_TEMP.centerLat2
  is '纬度(二维)';
comment on column DM_COMPANYPLACE_TEMP.featureId
  is '热点ID';
comment on column DM_COMPANYPLACE_TEMP.buildDataId
  is '楼宇id';
comment on column DM_COMPANYPLACE_TEMP.SOURCESSTATE
  is '数据来源(认领，录入)';
comment on column DM_COMPANYPLACE_TEMP.PCORMOBILE
  is '数据来源(pc，手机)';
comment on column DM_COMPANYPLACE_TEMP.IMGURL
  is '图片路径';
comment on column DM_COMPANYPLACE_TEMP.TYPE
  is '类型一级(单位，场所)字典项';
comment on column DM_COMPANYPLACE_TEMP.CLASSIFICATION
  is '分类二级 字典项';
comment on column DM_COMPANYPLACE_TEMP.CLASSIFICATIONEN
  is '类型(单位、场所)二级 英文 字典项';
comment on column DM_COMPANYPLACE_TEMP.CATEGORY
  is '类别三级字典项';
comment on column DM_COMPANYPLACE_TEMP.USERNAME
  is '负责人（校长，法人等）';
comment on column DM_COMPANYPLACE_TEMP.MOBILENUMBER
  is '联系手机';
comment on column DM_COMPANYPLACE_TEMP.TELEPHONE
  is '联系电话';
comment on column DM_COMPANYPLACE_TEMP.FAXNUMBER
  is '传真号码';
comment on column DM_COMPANYPLACE_TEMP.AREA
  is '通用字段：所在区域（主管单位，通道口、施工单位、周边情况、所属单位、副本许可范围、经营范围、所在派出所、主管单位）';
comment on column DM_COMPANYPLACE_TEMP.COVEREDAREA
  is '面积（占地面积，施工面积）';
comment on column DM_COMPANYPLACE_TEMP.REMARKS
  is '备注';
comment on column DM_COMPANYPLACE_TEMP.HASLICENSE
  is '是否有证照（0：无，1：有）';
comment on column DM_COMPANYPLACE_TEMP.BUSINESSLICENSENO
  is '营业执照号码';
comment on column DM_COMPANYPLACE_TEMP.ORGCODE
  is '组织机构码';
comment on column DM_COMPANYPLACE_TEMP.CLOAKROOM
  is '小件寄存处';
comment on column DM_COMPANYPLACE_TEMP.SCALETYPE
  is '规模类型（规上，规下 字典项）';
comment on column DM_COMPANYPLACE_TEMP.PARTYCOUNTNUMBER
  is '通用字段（党员数，临时上网卡数）';
comment on column DM_COMPANYPLACE_TEMP.networkCardNo
  is '临时上网卡数';
comment on column DM_COMPANYPLACE_TEMP.REGISTEREDCAPITAL
  is '注册资金';
comment on column DM_COMPANYPLACE_TEMP.registeredCapitalNo
  is '注册资本'; 
comment on column DM_COMPANYPLACE_TEMP.BEGINTIME
  is '开始时间（施工，注册）';
comment on column DM_COMPANYPLACE_TEMP.ENDTIME
  is '结束时间（施工，注册）';
comment on column DM_COMPANYPLACE_TEMP.BUSINESSAREA
  is '营业面积（平方米 ）';
comment on column DM_COMPANYPLACE_TEMP.DIGVOLUME
  is '施工挖方量（立方米）';
comment on column DM_COMPANYPLACE_TEMP.FILLVOLUME
  is '施工填方量（立方米）';
comment on column DM_COMPANYPLACE_TEMP.EMAIL
  is '电子邮件';
comment on column DM_COMPANYPLACE_TEMP.HOSPITALNATURE
  is '医院性质（公立，私立，合资，外资，其他 字典项）';
comment on column DM_COMPANYPLACE_TEMP.GENERALSTORAGE
  is '通用字段（存储设备，注册地址，接入方式）';
comment on column DM_COMPANYPLACE_TEMP.GENERALTYPE
  is '通用字段（货物类别，宽带接入方式）';
comment on column DM_COMPANYPLACE_TEMP.FAXNNO
  is '传真号码2（）';
comment on column DM_COMPANYPLACE_TEMP.NOWIP
  is '现在使用IP';
comment on column DM_COMPANYPLACE_TEMP.COUNTNO
  is '通用字段（电脑台数，从业人数）';
comment on column DM_COMPANYPLACE_TEMP.GENERALMANAGE
  is '通用字段（网络文化经营许可证号，主管部门）';
comment on column DM_COMPANYPLACE_TEMP.GENERALMENTE
  is '通用字段（网络安全审核意见书号，管理部门）';
comment on column DM_COMPANYPLACE_TEMP.FIREAUDITOPINIONNO
  is '消防审核意见书号';
comment on column DM_COMPANYPLACE_TEMP.INTERNETBARNO
  is '网吧编号';
comment on column DM_COMPANYPLACE_TEMP.LEGALVICEPRINCIPAL
  is '法制副校长';
comment on column DM_COMPANYPLACE_TEMP.SCHOOLNATURE
  is '学校性质（0公办，1民办，2其他 字典项）';
comment on column DM_COMPANYPLACE_TEMP.SCHOOLNAMEEN
  is '学校英文名称';
comment on column DM_COMPANYPLACE_TEMP.SCHOOLWEBSITE
  is '学校网址';
comment on column DM_COMPANYPLACE_TEMP.ECONOMICNATURE
  is '经济性质（0：非公司企业法人，1：）[字典？]';
comment on column DM_COMPANYPLACE_TEMP.MANAGEMENTLEVEL
  is '管理等级（0：A，1：B，2：C）';
comment on column DM_COMPANYPLACE_TEMP.FIRELEVEL
  is '消防等级（1：一级…………）';
comment on column DM_COMPANYPLACE_TEMP.CLAIMSTATE    
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_COMPANYPLACE_TEMP.CLAIMDATE    
  is '认领日期';
comment on column DM_COMPANYPLACE_TEMP.CLAIMUSERNAME    
  is '认领人用户名';
comment on column DM_COMPANYPLACE_TEMP.CLAIMUSERID    
  is '认领人Id';
comment on column DM_COMPANYPLACE_TEMP.CLAIMORGID    
  is '认领人网格';
comment on column DM_COMPANYPLACE_TEMP.IMPORTDEPARTMENTID  
  is '导入部门Id';
comment on column DM_COMPANYPLACE_TEMP.OLDORGID    
  is '刚导入时的所属网格Id';
comment on column DM_COMPANYPLACE_TEMP.INID      
  is '认领时插入到原库中数据id';
comment on column DM_COMPANYPLACE_TEMP.DISPOSE      
  is '是否经过处理 0.未处理；1.处理过';
comment on column DM_COMPANYPLACE_TEMP.CLAIMAVAILABLE    
  is '是否可认领 0.不可认领；1.可认领';
comment on column DM_COMPANYPLACE_TEMP.IMPORTDATE    
  is '导入时间';
comment on column DM_COMPANYPLACE_TEMP.DISTRICTORGID    
  is '导入时的到县区的组织机构';
 commit;
 
  