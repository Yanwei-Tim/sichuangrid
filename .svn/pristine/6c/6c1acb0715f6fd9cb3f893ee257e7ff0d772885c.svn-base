--修改 失业人员中冲突的 “人员类型” 为 “失业人员类型”
update propertydomains pm set pm.domainname='失业人员类型'
 where pm.id = (
                
                select pd.propertydomainid
                  from propertydicts pd
                 where pd.displayname = '城镇新增劳动力');

--残疾人 类别  等级 多选关联表
CREATE SEQUENCE S_HANDICAPPEDSDISABILITYTYPE
MINVALUE 1
MAXVALUE 99999999999999999999
START WITH 1
INCREMENT BY 1
CACHE 20;

-- Create table
create table HANDICAPPEDSDISABILITYTYPE
(
  id                NUMBER not null,
  handicappedsid    NUMBER not null,
  handicappedstype  NUMBER not null,
  handicappedslevel NUMBER not null
);
-- Add comments to the table 
comment on table HANDICAPPEDSDISABILITYTYPE
  is '残疾人类别等级关联表';
-- Add comments to the columns 
comment on column HANDICAPPEDSDISABILITYTYPE.id
  is '主键';
comment on column HANDICAPPEDSDISABILITYTYPE.handicappedsid
  is '残疾人ID';
comment on column HANDICAPPEDSDISABILITYTYPE.handicappedstype
  is '残疾类型';
comment on column HANDICAPPEDSDISABILITYTYPE.handicappedslevel
  is '残疾等级';
-- Create/Recreate primary, unique and foreign key constraints 
alter table HANDICAPPEDSDISABILITYTYPE
  add constraint PK_HANDICAPPEDSDISABILITYTYPE primary key (ID);
-- Add/modify columns 
alter table HANDICAPPEDSDISABILITYTYPE add isdatamanage number(1) default 0;
-- Add comments to the columns 
comment on column HANDICAPPEDSDISABILITYTYPE.isdatamanage
  is '是否是数据认领数据 0 否 1是';
  
--修改重点青少年表结构，新增 是否在校住宿 和 学校名称 字段
--BEAN
alter table IDLEYOUTHS add ISSTAYINSCHOOL NUMBER(1) DEFAULT 0;
alter table IDLEYOUTHS add SCHOOLNAME VARCHAR2(90);
comment on column IDLEYOUTHS.ISSTAYINSCHOOL is '是否在校住宿';
comment on column IDLEYOUTHS.SCHOOLNAME is '学校名称';
--TEMP
alter table dm_idleyouths_temp add ISSTAYINSCHOOL NUMBER(1) DEFAULT 0;
alter table dm_idleyouths_temp add SCHOOLNAME VARCHAR2(90);
comment on column dm_idleyouths_temp.ISSTAYINSCHOOL is '是否在校住宿';
comment on column dm_idleyouths_temp.SCHOOLNAME is '学校名称';
--FIX BUG
alter table dm_idleyouths_temp add WORKCONDITION VARCHAR2(300);
comment on column dm_idleyouths_temp.WORKCONDITION is '工作情况';


-- Create table  实有单位从业人员关联表
create table ACTUALCOMPANYPRACTITIONERS
(
  actualcompanyid NUMBER(10) not null,
  personid        NUMBER(10) not null,
  persontype      VARCHAR2(20) not null
);
-- Add comments to the table 
comment on table ACTUALCOMPANYPRACTITIONERS
  is '实有单位从业人员关联表';
-- Add comments to the columns 
comment on column ACTUALCOMPANYPRACTITIONERS.actualcompanyid
  is '实有单位ID';
comment on column ACTUALCOMPANYPRACTITIONERS.personid
  is '人员ID';
comment on column ACTUALCOMPANYPRACTITIONERS.persontype
  is '人员类型 户籍人口 流动人口 未落户人口';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ACTUALCOMPANYPRACTITIONERS
  add constraint PK_ACTUALCOMPANYPRACTITIONERS primary key (ACTUALCOMPANYID, PERSONTYPE, PERSONID);

  
  create sequence s_DM_builddatas_Temp
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table DM_builddatas_Temp
(
  ID      		  	NUMBER(10) not null,
  BUILDINGID      	VARCHAR2(32),
  ORGANIZATION      NUMBER(10) not null,
  ORGINTERNALCODE   VARCHAR2(32) not null,
  BUILDINGNAME      VARCHAR2(150),
  BUILDINGADDRESS   VARCHAR2(150),
  owner          VARCHAR2(60),
  responsibleperson VARCHAR2(60),
  phone        varchar2(15),
  buildingstructures number(32),
  CENTERX           NUMBER(10),
  CENTERY           NUMBER(10),
  isLayout      NUMBER(1)  default 0,
  CREATEUSER        VARCHAR2(32) not null,
  UPDATEUSER        VARCHAR2(32),
  CREATEDATE        DATE not null,
  UPDATEDATE        DATE,
  type        number(32) ,
  centerLon      VARCHAR2(32),
  centerLat      VARCHAR2(32),
  centerLon2    VARCHAR2(32),
  centerLat2    VARCHAR2(32),
  claimState             NUMBER(10) default 0,
  claimDate              DATE,
  claimUserName          VARCHAR2(4000),
  claimUserId            NUMBER(10),
  claimOrgId             NUMBER(10),
  importDepartmentId     NUMBER(10)   not null,
  oldOrgId    NUMBER(10),
  inId      NUMBER(10),
  dispose     NUMBER(10) default 0,
  claimavailable         NUMBER(10) default 0,
  importDate              DATE,
  districtOrgId          NUMBER(10),
  constraint pkDm_BuilddatasTemp primary key (id),
  constraint fkDm_BuilddatasTempOrg foreign key (ORGANIZATION)
         references organizations (id)
);
comment on table DM_builddatas_Temp
  is '数据管理_楼宇信息表';
comment on column DM_builddatas_Temp.id
  is '表ID';
comment on column DM_builddatas_Temp.BUILDINGID
  is '楼宇ID';
comment on column DM_builddatas_Temp.ORGANIZATION
  is '所属网格';
comment on column DM_builddatas_Temp.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_builddatas_Temp.BUILDINGNAME
  is '楼宇名称';
comment on column DM_builddatas_Temp.BUILDINGADDRESS
  is '楼宇地址';
comment on column DM_builddatas_Temp.owner
  is '业主';
comment on column DM_builddatas_Temp.responsibleperson
  is '负责人';
comment on column DM_builddatas_Temp.buildingstructures
  is '建筑结构';
comment on column DM_builddatas_Temp.phone
  is '联系电话';
comment on column DM_builddatas_Temp.CENTERX
  is '楼宇中心坐标X';
comment on column DM_builddatas_Temp.CENTERY
  is '楼宇中心坐标Y';
comment on column DM_builddatas_Temp.isLayout
  is '是否有楼宇布局';
comment on column DM_builddatas_Temp.CREATEUSER
  is '创建用户';
comment on column DM_builddatas_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_builddatas_Temp.CREATEDATE
  is '创建日期';
comment on column DM_builddatas_Temp.UPDATEDATE
  is '修改时间';
comment on column DM_builddatas_Temp.type
  is '楼宇类型';
comment on column DM_builddatas_Temp.centerLon
  is '楼宇经度';
comment on column DM_builddatas_Temp.centerLat
  is '楼宇纬度';
  comment on column DM_builddatas_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_builddatas_Temp.claimDate is
'认领日期';

comment on column DM_builddatas_Temp.claimUserName is
'认领人用户名';

comment on column DM_builddatas_Temp.claimUserId is
'认领人Id';

comment on column DM_builddatas_Temp.claimOrgId is
'认领人网格';

comment on column DM_builddatas_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_builddatas_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_builddatas_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_builddatas_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_builddatas_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_builddatas_Temp.importDate
  is '导入时间';
comment on column DM_builddatas_Temp.districtOrgId is
'导入时的到县区的组织机构';

-- 实有房屋添加房产证地址字段
alter table HOUSEINFO add houseaddress VARCHAR2(200);
-- Add comments to the columns 
comment on column HOUSEINFO.houseaddress
  is '房产证地址';
-- Add/modify columns 
alter table RENTALHOUSE add houseaddress VARCHAR2(200);
-- Add comments to the columns 
comment on column RENTALHOUSE.houseaddress
  is '房产证地址';
-- Add/modify columns 
alter table DM_HOUSEINFO_TEMP add houseaddress VARCHAR2(200);
-- Add comments to the columns 
comment on column DM_HOUSEINFO_TEMP.houseaddress
  is '房产证地址';
-- Add/modify columns 
alter table DM_RENTALHOUSE_TEMP add houseaddress VARCHAR2(200);
-- Add comments to the columns 
comment on column DM_RENTALHOUSE_TEMP.houseaddress
  is '房产证地址';
alter table HOUSEINFO modify community VARCHAR2(200);
alter table RENTALHOUSE modify community VARCHAR2(200);
alter table DM_HOUSEINFO_TEMP modify community VARCHAR2(200);
alter table DM_RENTALHOUSE_TEMP modify community VARCHAR2(200);

  
-- 数据管理增加从业人员数字段
alter table DM_OTHERLOCALES_TEMP add PRACTITIONER_NUMBER NUMBER(10);
-- Add comments to the columns 
comment on column DM_OTHERLOCALES_TEMP.PRACTITIONER_NUMBER
  is '从业人员数';

--景区交通序列
create sequence S_SCENICTRAFFIC
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--景区交通表
create table SCENICTRAFFIC
(
  id                 NUMBER(10) not null,
  orgid              NUMBER(10) not null,
  orginternalcode    VARCHAR2(50),
  startaddress       VARCHAR2(60),
  endaddress         VARCHAR2(60),
  traffictype        NUMBER(10) not null,
  phone              VARCHAR2(32),
  trafficname        VARCHAR2(200),
  managerunit        VARCHAR2(200),
  managerpeople      VARCHAR2(30),
  managerpeoplephone VARCHAR2(32),
  aroundscenic       VARCHAR2(900),
  firstbustime       VARCHAR2(20),
  lastbustime        VARCHAR2(20),
  remark             VARCHAR2(900),
  imgurl             VARCHAR2(300),
  fullpinyin         VARCHAR2(30),
  simplepinyin       VARCHAR2(10),
  createuser         VARCHAR2(32) not null,
  updateuser         VARCHAR2(32),
  createdate         DATE not null,
  updatedate         DATE,
  sourcesstate       NUMBER(1) default 1,
  logoutreason       VARCHAR2(150),
  isemphasis         NUMBER(1) default 0,
  logouttime         DATE
);
comment on table SCENICTRAFFIC
  is '景区交通';
comment on column SCENICTRAFFIC.id
  is '主键ID';
comment on column SCENICTRAFFIC.orgid
  is '组织ID';
comment on column SCENICTRAFFIC.orginternalcode
  is '组织层级';
comment on column SCENICTRAFFIC.startaddress
  is '起点名称';
comment on column SCENICTRAFFIC.endaddress
  is '终点名称';
comment on column SCENICTRAFFIC.traffictype
  is '交通类型';
comment on column SCENICTRAFFIC.phone
  is '联系电话';
comment on column SCENICTRAFFIC.trafficname
  is '交通线路名称';
comment on column SCENICTRAFFIC.managerunit
  is '负责单位';
comment on column SCENICTRAFFIC.managerpeople
  is '负责人';
comment on column SCENICTRAFFIC.managerpeoplephone
  is '负责人电话';
comment on column SCENICTRAFFIC.aroundscenic
  is '周边景点';
comment on column SCENICTRAFFIC.firstbustime
  is '最早班车时间';
comment on column SCENICTRAFFIC.lastbustime
  is '最晚班车时间';
comment on column SCENICTRAFFIC.remark
  is '备注';
comment on column SCENICTRAFFIC.imgurl
  is '图片路径';
comment on column SCENICTRAFFIC.fullpinyin
  is '全拼音';
comment on column SCENICTRAFFIC.simplepinyin
  is '简拼音';
comment on column SCENICTRAFFIC.createuser
  is '创建人';
comment on column SCENICTRAFFIC.updateuser
  is '修改人';
comment on column SCENICTRAFFIC.createdate
  is '创建时间';
comment on column SCENICTRAFFIC.updatedate
  is '修改时间';
comment on column SCENICTRAFFIC.sourcesstate
  is '数据来源';
comment on column SCENICTRAFFIC.logoutreason
  is '取消关注原因';
comment on column SCENICTRAFFIC.isemphasis
  is '是否关注';
comment on column SCENICTRAFFIC.logouttime
  is '取消关注时间';
alter table SCENICTRAFFIC
  add primary key (ID);
alter table SCENICTRAFFIC
  add constraint FKSCENICTRAFFIC foreign key (ORGID)
  references ORGANIZATIONS (ID);
  
  create sequence s_scenicEquipment
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

 create table scenicEquipment (
  id                   NUMBER(10)                      not null,
  orgId                NUMBER(10)                      not null,
  orgInternalCode      VARCHAR2(32)                    not null,
  equipName           VARCHAR2(150)           not null,
  equipAddress         VARCHAR2(150)          ,
  equipType            NUMBER(10),
  manager              VARCHAR2(60),
  managerMobile         VARCHAR2(12) ,
  aroundScenic         VARCHAR2(900)  , 
  mobile               VARCHAR2(12) ,
  remark            VARCHAR2(900),
  imgUrl               VARCHAR2(300),
  fullPinyin           VARCHAR2(30),
    simplePinyin         VARCHAR2(10),
    createUser           VARCHAR2(32)                    not null,
    updateUser           VARCHAR2(32),
    createDate           DATE                            not null,
    updateDate           DATE,
    sourcesstate NUMBER(1)  default 1 ,
    logoutreason	VARCHAR2(150)	,
isemphasis          NUMBER(1) default 0,
logouttime	DATE	,
  constraint       pkScenicEquipment primary key(id),
  constraint       fkScenicEquipment foreign key(orgId)
    references organizations(id)
);
comment on table scenicEquipment is
'景区配套设施信息';
comment on column scenicEquipment.id is
'设施Id';
comment on column scenicEquipment.orgId is
'所属网格';
comment on column scenicEquipment.orgInternalCode is
'所属责任区编号';
comment on column scenicEquipment.equipName is
'名称';
comment on column scenicEquipment.equipAddress is
'地址';
comment on column scenicEquipment.manager is
'负责人';
comment on column scenicEquipment.mobile is
'联系电话';
comment on column scenicEquipment.aroundScenic is
'周边景点';
comment on column scenicEquipment.managerMobile is
'负责人联系电话';
comment on column scenicEquipment.remark is
'备注';

--修改育龄妇女 户籍地址字段
alter table dm_nurtureswomen_temp modify NATIVEPLACEADDRESS VARCHAR2(150);

-- Create sequence
create sequence S_SCENICSPOTS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table SCENICSPOTS
(
  ID                       NUMBER(10) not null,
  ORGID                    NUMBER(10) not null,
  ORGINTERNALCODE          VARCHAR2(32) not null,
  NAME                     VARCHAR2(90) not null,
  ADDRESS                  VARCHAR2(150),
  GRADE                    VARCHAR2(60),
  TELEPHONE                VARCHAR2(15),
  PERSONLIABLE             VARCHAR2(60),
  PERSONLIABLETELEPHONE    VARCHAR2(15),
  INTRODUCTION             VARCHAR2(900),
  REMARK                   VARCHAR2(600),
  IMGURL                   VARCHAR2(300),
  FULLPINYIN               VARCHAR2(30),
  SIMPLEPINYIN             VARCHAR2(10),
  CREATEUSER               VARCHAR2(32) not null,
  UPDATEUSER               VARCHAR2(32),
  CREATEDATE               DATE not null,
  UPDATEDATE               DATE,
  ISEMPHASIS               NUMBER(1) default 0,
  ISEMPHASISREASON         VARCHAR2(300),
  ISEMPHASISDATE           DATE,
  ATTENTIONEXTENT          NUMBER(10),
  SOURCESSTATE             NUMBER(1) default 1
);
-- Add comments to the table 
comment on table SCENICSPOTS
  is '旅游景点';
-- Add comments to the columns 
comment on column SCENICSPOTS.ID
  is '编号';
comment on column SCENICSPOTS.ORGID
  is '所属网格';
comment on column SCENICSPOTS.ORGINTERNALCODE
  is '所属网格编号';
comment on column SCENICSPOTS.NAME
  is '景点名称';
comment on column SCENICSPOTS.ADDRESS
  is '景点地址';
comment on column SCENICSPOTS.GRADE
  is '景点等级';
comment on column SCENICSPOTS.TELEPHONE
  is '景点电话';
comment on column SCENICSPOTS.PERSONLIABLE
  is '景点负责人';
comment on column SCENICSPOTS.PERSONLIABLETELEPHONE
  is '景点负责人电话';
comment on column SCENICSPOTS.INTRODUCTION
  is '景点介绍';
comment on column SCENICSPOTS.REMARK
  is '备注';
comment on column SCENICSPOTS.IMGURL
  is '图片URL';
comment on column SCENICSPOTS.FULLPINYIN
  is '全拼';
comment on column SCENICSPOTS.SIMPLEPINYIN
  is '简拼';
comment on column SCENICSPOTS.CREATEUSER
  is '创建人';
comment on column SCENICSPOTS.UPDATEUSER
  is '修改人';
comment on column SCENICSPOTS.CREATEDATE
  is '创建时间';
comment on column SCENICSPOTS.UPDATEDATE
  is '修改时间';
comment on column SCENICSPOTS.ISEMPHASIS
  is '是否关注';
comment on column SCENICSPOTS.ISEMPHASISREASON
  is '取消关注原因';
comment on column SCENICSPOTS.ISEMPHASISDATE
  is '取消关注时间';
comment on column SCENICSPOTS.ATTENTIONEXTENT
  is '关注程度：1.一般；2.中等；3.严重';
comment on column SCENICSPOTS.SOURCESSTATE
  is '数据来源：1.录入；2.认领；3.已核实';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SCENICSPOTS
  add constraint PKSCENICSPOTS primary key (ID);
alter table SCENICSPOTS
  add constraint FKSCENICSPOTSORG foreign key (ORGID)
  references ORGANIZATIONS (ID);




-- 新建表 旅游景点 投诉表扬
-- Create sequence
create sequence S_praiseScenicSpot
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table praiseScenicSpot
(
  ID                       NUMBER(10) not null,
  scenicSpotId             NUMBER(10) not null,
  feedbackType             NUMBER(10),
  feedbackTime             DATE,
  feedbackPerson           VARCHAR2(30),
  feedbackPersonTelephone  VARCHAR2(20),
  introduction             VARCHAR2(900),
  FULLPINYIN               VARCHAR2(30),
  SIMPLEPINYIN             VARCHAR2(10),
  CREATEUSER               VARCHAR2(32),
  UPDATEUSER               VARCHAR2(32),
  CREATEDATE               DATE,
  UPDATEDATE               DATE
);
-- Add comments to the table 
comment on table praiseScenicSpot
  is '旅游景点投诉表扬';
-- Add comments to the columns 
comment on column praiseScenicSpot.ID
  is '编号';
comment on column praiseScenicSpot.scenicSpotId
  is '投诉景点编号';
comment on column praiseScenicSpot.feedbackType
  is '反馈类型';
comment on column praiseScenicSpot.feedbackTime
  is '反馈时间';
comment on column praiseScenicSpot.feedbackPerson
  is '反馈人';
comment on column praiseScenicSpot.feedbackPersonTelephone
  is '反馈人电话';
comment on column praiseScenicSpot.introduction
  is '情况描述';
comment on column praiseScenicSpot.FULLPINYIN
  is '全拼';
comment on column praiseScenicSpot.SIMPLEPINYIN
  is '简拼';
comment on column praiseScenicSpot.CREATEUSER
  is '创建人';
comment on column praiseScenicSpot.UPDATEUSER
  is '修改人';
comment on column praiseScenicSpot.CREATEDATE
  is '创建时间';
comment on column praiseScenicSpot.UPDATEDATE
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table praiseScenicSpot
  add constraint pkpraiseScenicSpot primary key (ID);
alter table praiseScenicSpot
  add constraint fkpraiseScenicSpotOrg foreign key (scenicSpotId)
  references scenicspots (ID);
