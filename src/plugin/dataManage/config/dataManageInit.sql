-- Create sequence
create sequence S_DM_HOUSEHOLDSTAFFS_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
/*==============================================================*/
/* Table: DM_HOUSEHOLDSTAFFS_TEMP    数据管理_户籍人口                                           */
/*==============================================================*/  
create table DM_HOUSEHOLDSTAFFS_TEMP
(
  ID                   NUMBER(10) not null,
  IDCARDNO             VARCHAR2(60) not null,
  NAME                 VARCHAR2(60) not null,
  USEDNAME             VARCHAR2(60),
  GENDER               NUMBER(10),
  BIRTHDAY             DATE,
  NATIVEPLACEADDRESS   VARCHAR2(150),
  CURRENTADDRESS       VARCHAR2(150),
  OTHERADDRESS         VARCHAR2(150),
  MOBILENUMBER         VARCHAR2(50),
  TELEPHONE            VARCHAR2(80),
  RELATIONSHIPWITHHEAD NUMBER(10),
  RELATIONSHIPWITHHEADTEXT VARCHAR2(60),
  ISDEATH              NUMBER(10) default 0,
  PROVINCE             VARCHAR2(150),
  CITY                 VARCHAR2(150),
  DISTRICT             VARCHAR2(150),
  ORGID                NUMBER(10),
  ORGINTERNALCODE      VARCHAR2(32),
  FAMILYID             NUMBER(10),
  POLITICALBACKGROUND  NUMBER(10),
  MARITALSTATE         NUMBER(10),
  RESIDENCETYPE        NUMBER(10),
  STATURE              NUMBER(10),
  NATION               NUMBER(10),
  FAITH                NUMBER(10),
  SCHOOLING            NUMBER(10),
  BLOODTYPE            NUMBER(10),
  EMAIL                VARCHAR2(150),
  WORKUNIT             VARCHAR2(150),
  REMARK               VARCHAR2(900),
  CAREER               NUMBER(10),
  ACCOUNTNUMBER        VARCHAR2(150),
  CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
  CURRENTADDRESSTYPE   NUMBER(10),
  COMMUNITY            VARCHAR2(150),
  BLOCK                VARCHAR2(150),
  UNIT                 VARCHAR2(150),
  ROOM                 VARCHAR2(150),
  LOGOUT               NUMBER(1) default 0,
  IMGURL               VARCHAR2(300),
  OUTGONE              NUMBER(1) default 0,
  OUTREASONS           NUMBER(10),
  REASONSDATE          DATE,
  OUTPROVINCE          VARCHAR2(150),
  OUTCITY              VARCHAR2(150),
  OUTDISTRICT          VARCHAR2(150),
  GOOUTDETAILEDADDRESS VARCHAR2(900),
  HOMEPHONE            VARCHAR2(80),
  FULLPINYIN           VARCHAR2(30),
  SIMPLEPINYIN         VARCHAR2(10),
  RESIDENTSTATUS       NUMBER(10),
  ISHAVEHOUSE          NUMBER(1) default 0,
  NOHOUSEREASON        VARCHAR2(800),
  LOGOUTREASON         VARCHAR2(300),
  LOGOUTDATE           DATE,
  SETTLETIME           VARCHAR2(30),
  CLAIMSTATE           NUMBER(10) default 0,
  CLAIMDATE            DATE,
  CLAIMUSERNAME        VARCHAR2(4000),
  CLAIMUSERID          NUMBER(10),
  CLAIMORGID           NUMBER(10),
  IMPORTDEPARTMENTID   NUMBER(10) not null,
  OLDORGID             NUMBER(10),
  INID                 NUMBER(15),
  importDate   DATE,
  dispose number(10) default 0,
  claimAvailable number(10) default 0,
   districtOrgId				  NUMBER(10),
   actualPopulationType VARCHAR2(50),
  constraint PKDM_HOUSEHOLDSTAFFTEMP primary key (ID),
  constraint FKDM_HOUSEHOLDSTAFFTEMPORG foreign key (ORGID)
  references ORGANIZATIONS (ID)
);

comment on column DM_HOUSEHOLDSTAFFS_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.NAME
  is '姓名';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.USEDNAME
  is '曾用名';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.GENDER
  is '性别 1男 2女 3不明';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.BIRTHDAY
  is '出生日期';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.NATIVEPLACEADDRESS
  is '户籍地详址';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CURRENTADDRESS
  is '常住地址';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OTHERADDRESS
  is '临时居所';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.MOBILENUMBER
  is '联系手机';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.TELEPHONE
  is '固定电话';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.RELATIONSHIPWITHHEAD
  is '与户主关系';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.RELATIONSHIPWITHHEADTEXT
  is '与户主关系文本';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ISDEATH
  is '是否死亡 0否 1是';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.PROVINCE
  is '省';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CITY
  is '市';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.DISTRICT
  is '区县';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ORGID
  is '所属网格';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.FAMILYID
  is '户籍家庭ID';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.MARITALSTATE
  is '婚姻状况';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.RESIDENCETYPE
  is '户口类别';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.STATURE
  is '身高';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.NATION
  is '民族';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.FAITH
  is '宗教信仰';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.SCHOOLING
  is '文化程度';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.BLOODTYPE
  is '血型';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.EMAIL
  is '电子邮箱';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.REMARK
  is '备注';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CAREER
  is '职业';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ACCOUNTNUMBER
  is '户号';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.UPDATEDATE
  is '修改日期';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.COMMUNITY
  is '常住地址商品房 小区';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.BLOCK
  is '常住地址商品房 幢';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.UNIT
  is '常住地址商品房 单元';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ROOM
  is '常住地址商品房  室';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.LOGOUT
  is '是否注销 0否 1是';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.IMGURL
  is '图像路径';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OUTGONE
  is '是否外出';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OUTREASONS
  is '外出原因';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.REASONSDATE
  is '外出时间';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OUTPROVINCE
  is '外出省';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OUTCITY
  is '外出市';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OUTDISTRICT
  is '外出县';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.GOOUTDETAILEDADDRESS
  is '外出详址';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.HOMEPHONE
  is '住宅电话';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.FULLPINYIN
  is '全拼';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.SIMPLEPINYIN
  is '简拼';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.RESIDENTSTATUS
  is '人户状态';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.ISHAVEHOUSE
  is '是否有房屋';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.NOHOUSEREASON
  is '无房原因';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.LOGOUTREASON
  is '注销原因';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.LOGOUTDATE
  is '注销时间';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.SETTLETIME
  is '落户时间';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.importDate
  is '导入时间';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.dispose is '是否经过处理(0:否)';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.claimAvailable is '是否可以认领(0:否)';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.districtOrgId is
'导入时的到县区的组织机构'; 
comment on column DM_HOUSEHOLDSTAFFS_TEMP.actualPopulationType is
'实口类型';

create sequence s_DM_floatingPopulations_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 9999999999;

 /*==============================================================*/
/* Table: DM_floatingPopulations_Temp       数据管理_流动人口                                     */
/*==============================================================*/
create table DM_floatingPopulations_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                     ,
   orgInternalCode		VARCHAR2(50),
   gender               NUMBER(10)                      ,
   idCardNo             VARCHAR2(60)                    not null,
   name                 VARCHAR2(60)                    not null,
   usedName             VARCHAR2(60),
   birthday             DATE,
   province             VARCHAR2(60)                    ,
   city                 VARCHAR2(60)                    ,
   district             VARCHAR2(60)                    ,
   nativePlaceAddress   VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   otherAddress         VARCHAR2(150),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(60),
   block 				VARCHAR2(24),
   unit 				VARCHAR2(18),
   room 				VARCHAR2(30),
   mobileNumber         VARCHAR2(50),
   telephone            VARCHAR2(80),
   career               NUMBER(10),
   isInflowing          NUMBER(1)                      default 0,
   inflowingReason      NUMBER(10),
   inflowingDate        DATE,
   inflowingProvince    VARCHAR2(60),
   inflowingCity        VARCHAR2(60),
   inflowingDistrict    VARCHAR2(60),
   registrationType		NUMBER(10),
   registerDate			DATE,
   expectedDatedue		DATE,
   politicalBackground  NUMBER(10),
   maritalState         NUMBER(10),
   residenceType        NUMBER(10),
   stature              NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   schooling            NUMBER(10),
   bloodType            NUMBER(10),
   email                VARCHAR2(150),
   workUnit             VARCHAR2(150),
   remark               VARCHAR2(900),
   isDeath              NUMBER(1)                       default 0 ,
   logOut               NUMBER(1)                       default 0,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   createUser           VARCHAR2(60)                    not null,
   imgUrl 			    VARCHAR2(300),
   updateUser           VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
   certificateNumber    VARCHAR2(60),
   stayLocationTypeId   NUMBER(10),
   economySourceId      NUMBER(10),
   stayTimeLimitId      NUMBER(10),
   preparedStayTimeLimitId      NUMBER(10),
   hasMarriedProve      NUMBER(1),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   logOutReason        VARCHAR2(300)  ,
   logOutDate        DATE  ,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
  importDate   DATE,
  dispose number(10) default 0,
  claimAvailable number(10) default 0,
   districtOrgId				  NUMBER(10),
   actualPopulationType VARCHAR2(50),
   constraint PKDM_FLOATINGPOPUTEMP primary key (id),
   constraint FKDM_FLOATINGPOPUTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_floatingPopulations_Temp is
'流动人口';

comment on column DM_floatingPopulations_Temp.orgId is
'所属网格';

comment on column DM_floatingPopulations_Temp.orgInternalCode is
'部门内部编码';

comment on column DM_floatingPopulations_Temp.gender is
'性别';

comment on column DM_floatingPopulations_Temp.idCardno is
'身份证id';

comment on column DM_floatingPopulations_Temp.name is
'姓名';

comment on column DM_floatingPopulations_Temp.usedName is
'曾用名';

comment on column DM_floatingPopulations_Temp.birthday is
'出生日期';

comment on column DM_floatingPopulations_Temp.province is
'省';

comment on column DM_floatingPopulations_Temp.city is
'市';

comment on column DM_floatingPopulations_Temp.district is
'区县';

comment on column DM_floatingPopulations_Temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_floatingPopulations_Temp.currentAddress is
'常住地址';

comment on column DM_floatingPopulations_Temp.currentAddressType is
'常住地址类型';

comment on column DM_floatingPopulations_Temp.community is
'常住地址商品房 小区';

comment on column DM_floatingPopulations_Temp.block is
'常住地址商品房 幢';

comment on column DM_floatingPopulations_Temp.unit is
'常住地址商品房 单元';

comment on column DM_floatingPopulations_Temp.room is
'常住地址商品房  室';

comment on column DM_floatingPopulations_Temp.otherAddress is
'临时居所';

comment on column DM_floatingPopulations_Temp.mobileNumber is
'手机号码';

comment on column DM_floatingPopulations_Temp.telephone is
'固定电话';

comment on column DM_floatingPopulations_Temp.career is
'职业';

comment on column DM_floatingPopulations_Temp.isInflowing is
'是否流入';

comment on column DM_floatingPopulations_Temp.inflowingReason is
'流入原因';

comment on column DM_floatingPopulations_Temp.inflowingDate is
'流入时间';

comment on column DM_floatingPopulations_Temp.inflowingProvince is
'流入来源省';

comment on column DM_floatingPopulations_Temp.inflowingCity is
'流入来源市';

comment on column DM_floatingPopulations_Temp.inflowingDistrict is
'流入来源县';

comment on column DM_floatingPopulations_Temp.registrationType is
'登记证情况';

comment on column DM_floatingPopulations_Temp.registerDate is
'登记日期';

comment on column DM_floatingPopulations_Temp.expectedDatedue is
'预期到期日期';

comment on column DM_floatingPopulations_Temp.politicalBackground is
'政治面貌';

comment on column DM_floatingPopulations_Temp.maritalState is
'婚姻状况';

comment on column DM_floatingPopulations_Temp.residenceType is
'户口类别';

comment on column DM_floatingPopulations_Temp.stature is
'身高';

comment on column DM_floatingPopulations_Temp.nation is
'民族';

comment on column DM_floatingPopulations_Temp.faith is
'宗教信仰';

comment on column DM_floatingPopulations_Temp.residenceType is
'文化程度';

comment on column DM_floatingPopulations_Temp.bloodType is
'血型';

comment on column DM_floatingPopulations_Temp.email is
'电子邮箱';

comment on column DM_floatingPopulations_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_floatingPopulations_Temp.remark is
'备注';

comment on column DM_floatingPopulations_Temp.isDeath is
'是否死亡';

comment on column DM_floatingPopulations_Temp.imgUrl is
'头像路径';

comment on column DM_floatingPopulations_Temp.logOut is
'是否注销';

comment on column DM_floatingPopulations_Temp.fullPinyin is
'全拼';

comment on column DM_floatingPopulations_Temp.simplePinyin is
'简拼';

comment on column DM_floatingPopulations_Temp.createUser is
'创建用户';

comment on column DM_floatingPopulations_Temp.updateUser is
'修改用户';

comment on column DM_floatingPopulations_Temp.createDate is
'创建日期';

comment on column DM_floatingPopulations_Temp.updateDate is
'修改时间';

comment on column DM_floatingPopulations_Temp.certificateNumber is
'证件编号';

comment on column DM_floatingPopulations_Temp.stayLocationTypeId is
'暂住处所';

comment on column DM_floatingPopulations_Temp.economySourceId is
'经济来源';

comment on column DM_floatingPopulations_Temp.stayTimeLimitId is
'居住时限';

comment on column DM_floatingPopulations_Temp.preparedStayTimeLimitId is
'预居住时限';

comment on column DM_floatingPopulations_Temp.hasMarriedProve is
'是否有婚育证明';

comment on column DM_floatingPopulations_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_floatingPopulations_Temp.noHouseReason is
'无房原因';

comment on column DM_floatingPopulations_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_floatingPopulations_Temp.claimDate is
'认领日期';

comment on column DM_floatingPopulations_Temp.claimUserName is
'认领人用户名';

comment on column DM_floatingPopulations_Temp.claimUserId is
'认领人Id';

comment on column DM_floatingPopulations_Temp.claimOrgId is
'认领人网格';

comment on column DM_floatingPopulations_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_floatingPopulations_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_floatingPopulations_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_floatingPopulations_Temp.importDate
  is '导入时间';
comment on column DM_floatingPopulations_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_floatingPopulations_Temp.claimAvailable is '是否可以认领(0:否)';
 comment on column DM_floatingPopulations_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_floatingPopulations_Temp.actualPopulationType is
'实口类型';

-- Create sequence
create sequence S_DM_unsettledPopulations_Temp
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* table DM_unsettledPopulations_Temp  数据管理_未落户人员                                                                                                         */
/*==============================================================*/
 create table DM_unsettledPopulations_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      ,
   orgInternalCode VARCHAR2(32),
   gender               NUMBER(10)                      not null,
   idCardNo             VARCHAR2(60),
   name                 VARCHAR2(60)                    not null,
   usedName             VARCHAR2(60),
   birthday             DATE,
   province             VARCHAR2(60),
   city                 VARCHAR2(60),
   district             VARCHAR2(60),
   nativePlaceAddress   VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   mobileNumber         VARCHAR2(50),
   telephone            VARCHAR2(80),
   politicalBackground  NUMBER(10),
   maritalState         NUMBER(10),
   stature              NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   bloodType            NUMBER(10),
   schooling            NUMBER(10),
   email                VARCHAR2(150),
   workUnit             VARCHAR2(150),
   isDeath              NUMBER(1)                      default 0 ,
   remark               VARCHAR2(900),
   otherAddress         VARCHAR2(150),
   certificateNo        VARCHAR2(150),
   unSettedTime         DATE,
   unSettedReason       NUMBER(10),
   certificateType      NUMBER(10),
   career               NUMBER(10),
   imgUrl               VARCHAR2(300),
   currentAddressType   NUMBER(10)                default 0,
   community            VARCHAR2(60),
   block          VARCHAR2(24),
   unit          VARCHAR2(18),
   room          VARCHAR2(30),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   logout               NUMBER(1) default 0,
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   logoutReason          VARCHAR2(300),
   logoutDate            DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
    districtOrgId				  NUMBER(10),
    actualPopulationType VARCHAR2(50),
   constraint PKDM_UNSETTLEDPOPUTEMP primary key (id),
   constraint FKDM_UNSETTLEDPOPUTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_unsettledPopulations_Temp is
'未落户人口';

comment on column DM_unsettledPopulations_Temp.orgId is
'所属网格';

comment on column DM_unsettledPopulations_Temp.gender is
'性别';

comment on column DM_unsettledPopulations_Temp.idCardno is
'身份证id';

comment on column DM_unsettledPopulations_Temp.name is
'姓名';

comment on column DM_unsettledPopulations_Temp.usedName is
'曾用名';

comment on column DM_unsettledPopulations_Temp.birthday is
'出生日期';

comment on column DM_unsettledPopulations_Temp.province is
'省';

comment on column DM_unsettledPopulations_Temp.city is
'市';

comment on column DM_unsettledPopulations_Temp.district is
'区县';

comment on column DM_unsettledPopulations_Temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_unsettledPopulations_Temp.currentAddress is
'常住地址';

comment on column DM_unsettledPopulations_Temp.politicalBackground is
'政治面貌';

comment on column DM_unsettledPopulations_Temp.mobileNumber is
'手机号码';

comment on column DM_unsettledPopulations_Temp.telephone is
'固定电话';

comment on column DM_unsettledPopulations_Temp.maritalState is
'婚姻状况';

comment on column DM_unsettledPopulations_Temp.stature is
'身高';

comment on column DM_unsettledPopulations_Temp.nation is
'民族';

comment on column DM_unsettledPopulations_Temp.faith is
'宗教信仰';

comment on column DM_unsettledPopulations_Temp.schooling is
'文化程度';

comment on column DM_unsettledPopulations_Temp.bloodType is
'血型';

comment on column DM_unsettledPopulations_Temp.email is
'电子邮箱';

comment on column DM_unsettledPopulations_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_unsettledPopulations_Temp.remark is
'备注';

comment on column DM_unsettledPopulations_Temp.isDeath is
'是否死亡';

comment on column DM_unsettledPopulations_Temp.createUser is
'创建用户';

comment on column DM_unsettledPopulations_Temp.updateUser is
'修改用户';

comment on column DM_unsettledPopulations_Temp.createDate is
'创建日期';

comment on column DM_unsettledPopulations_Temp.updateDate is
'修改时间';

comment on column DM_unsettledPopulations_Temp.certificateType is
'持证类别';

comment on column DM_unsettledPopulations_Temp.otherAddress is
'临时居所';

comment on column DM_unsettledPopulations_Temp.certificateNo is
'持证编号';

comment on column DM_unsettledPopulations_Temp.unSettedTime is
'未落户时间';

comment on column DM_unsettledPopulations_Temp.unSettedReason is
'未落户原因';

comment on column DM_unsettledPopulations_Temp.career is
'职业';

comment on column DM_unsettledPopulations_Temp.imgUrl is
'头像路径';

comment on column DM_unsettledPopulations_Temp.currentAddressType is
'常住地址类型';

comment on column DM_unsettledPopulations_Temp.community is
'常住地址商品房 小区';

comment on column DM_unsettledPopulations_Temp.block is
'常住地址商品房 幢';

comment on column DM_unsettledPopulations_Temp.unit is
'常住地址商品房 单元';

comment on column DM_unsettledPopulations_Temp.room is
'常住地址商品房  室';
comment on column DM_unsettledPopulations_Temp.simplePinyin is
'简拼';

comment on column DM_unsettledPopulations_Temp.fullPinyin is
'全拼';

comment on column DM_unsettledPopulations_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_unsettledPopulations_Temp.logout is
'是否注销（为与其它实有人口保持一致）';

comment on column DM_unsettledPopulations_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_unsettledPopulations_Temp.noHouseReason is
'无房原因';
comment on column DM_unsettledPopulations_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_unsettledPopulations_Temp.claimDate is
'认领日期';

comment on column DM_unsettledPopulations_Temp.claimUserName is
'认领人用户名';

comment on column DM_unsettledPopulations_Temp.claimUserId is
'认领人Id';

comment on column DM_unsettledPopulations_Temp.claimOrgId is
'认领人网格';

comment on column DM_unsettledPopulations_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_unsettledPopulations_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_unsettledPopulations_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_unsettledPopulations_Temp.importDate
  is '导入时间';
comment on column DM_unsettledPopulations_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_unsettledPopulations_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_unsettledPopulations_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_unsettledPopulations_Temp.actualPopulationType is
'实口类型';
-- Create sequence
create sequence S_DM_overseaPersonnel_Temp
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

 /*==============================================================*/
/* Table: DM_overseaPersonnel_Temp         数据管理-境外人员                             */
/*==============================================================*/
 create table DM_overseaPersonnel_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      ,
   name                 VARCHAR2(180),
   gender               NUMBER(10)                      not null,
   mobileNumber         VARCHAR2(50),
   telephone            VARCHAR2(80),
   birthday             DATE,
   profession           VARCHAR2(150),
   workUnit             VARCHAR2(150),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block  				VARCHAR2(150),
   unit  				VARCHAR2(150),
   room  				VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   nationality          VARCHAR2(60),
   certificateType      NUMBER(10)                     not null,
   certificateNo        VARCHAR2(60)                   not null,
   arrivalTime          DATE,
   leaveTime 			DATE,
   remark               VARCHAR2(600),
   imgUrl               VARCHAR2(300),
   orgInternalCode      VARCHAR2(32),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   logOut           	NUMBER(10)                      default 0,
   englishName          VARCHAR2(180)                   not null,
   certificateStrartDay DATE,
   certificateEndDay    DATE,
   inflowReason	        VARCHAR2(60),
   isHaveHouse      	NUMBER(1)  					default 0,
   noHouseReason	    VARCHAR2(800),
   logOutReason         VARCHAR2(300),
   logOutDate          date,
    claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
  importDate   DATE,
  dispose number(10) default 0,
  claimAvailable number(10) default 0,
  districtOrgId				  NUMBER(10),
  otherAddress         VARCHAR2(150),
  actualPopulationType VARCHAR2(50),
  EMAIL                VARCHAR2(150),
   constraint PKDM_OVERSEAPERSONNELTEMP primary key (id),
   constraint FKDM_OVERSEAPERSONNELTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_overseaPersonnel_Temp is
'境外人员对象表';

comment on column DM_overseaPersonnel_Temp.orgId is
'所属网格';

comment on column DM_overseaPersonnel_Temp.name is
'姓名';

comment on column DM_overseaPersonnel_Temp.gender is
'性别';

comment on column DM_overseaPersonnel_Temp.mobileNumber is
'手机号码';

comment on column DM_overseaPersonnel_Temp.telephone is
'固定电话';

comment on column DM_overseaPersonnel_Temp.birthday is
'出生日期';

comment on column DM_overseaPersonnel_Temp.profession is
'职业';

comment on column DM_overseaPersonnel_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_overseaPersonnel_Temp.currentAddressType is
'常住地址类型';

comment on column DM_overseaPersonnel_Temp.community is
'常住地址商品房 小区';

comment on column DM_overseaPersonnel_Temp.block is
'常住地址商品房 幢';

comment on column DM_overseaPersonnel_Temp.unit is
'常住地址商品房 单元';

comment on column DM_overseaPersonnel_Temp.room is
'常住地址商品房  室';

comment on column DM_overseaPersonnel_Temp.currentAddress is
'现在居住地';

comment on column DM_overseaPersonnel_Temp.nationality is
'国籍';

comment on column DM_overseaPersonnel_Temp.certificateType is
'证件种类';

comment on column DM_overseaPersonnel_Temp.certificateNo is
'证件号码';

comment on column DM_overseaPersonnel_Temp.arrivalTime is
'抵达时间';

comment on column DM_overseaPersonnel_Temp.leaveTime is
'离开时间';

comment on column DM_overseaPersonnel_Temp.remark is
'备注';

comment on column DM_overseaPersonnel_Temp.imgUrl is
'图片保存地址';

comment on column DM_overseaPersonnel_Temp.orgInternalCode is
'所属责任区编号，以.分开';

comment on column DM_overseaPersonnel_Temp.fullPinyin is
'全拼';

comment on column DM_overseaPersonnel_Temp.simplePinyin is
'简拼';

comment on column DM_overseaPersonnel_Temp.createUser is
'创建用户';

comment on column DM_overseaPersonnel_Temp.updateUser is
'修改用户';

comment on column DM_overseaPersonnel_Temp.createDate is
'创建日期';

comment on column DM_overseaPersonnel_Temp.updateDate is
'修改时间';

comment on column DM_overseaPersonnel_Temp.logOut is
'注销状态';
comment on column DM_overseaPersonnel_Temp.englishName is
'英文名称';
comment on column DM_overseaPersonnel_Temp.certificateStrartDay is
'有效期开始时间';
comment on column DM_overseaPersonnel_Temp.certificateEndDay is
'有效期结束时间';
comment on column DM_overseaPersonnel_Temp.inflowReason is
'流入原因';
comment on column DM_overseaPersonnel_Temp.isHaveHouse is
'是否有房屋';
comment on column DM_overseaPersonnel_Temp.noHouseReason is
'无房原因';
comment on column DM_overseaPersonnel_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_overseaPersonnel_Temp.claimDate is
'认领日期';

comment on column DM_overseaPersonnel_Temp.claimUserName is
'认领人用户名';

comment on column DM_overseaPersonnel_Temp.claimUserId is
'认领人Id';

comment on column DM_overseaPersonnel_Temp.claimOrgId is
'认领人网格';

comment on column DM_overseaPersonnel_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_overseaPersonnel_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_overseaPersonnel_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_overseaPersonnel_Temp.importDate
  is '导入时间';
comment on column DM_overseaPersonnel_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_overseaPersonnel_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_overseaPersonnel_Temp.districtOrgId is
'导入时的到县区的组织机构'; 
comment on column DM_overseaPersonnel_Temp.OTHERADDRESS
  is '临时居所';
comment on column DM_overseaPersonnel_Temp.actualPopulationType is
'实口类型';
comment on column DM_overseaPersonnel_Temp.EMAIL
  is '电子邮件';
-- Create sequence
create sequence S_DM_positiveInfos_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

 /*==============================================================*/
/* table DM_positiveInfos_TEMP  数据管理_刑释解教人员                                                                                                         */
/*==============================================================*/
create table DM_positiveInfos_TEMP
(
  ID                   NUMBER(10) not null,
  ORGID                NUMBER(10),
  GENDER               NUMBER(10),
  SCHOOLING            NUMBER(10),
  ISREPEAT             NUMBER(1),
  IMPRISONMENTDATE     VARCHAR2(32),
  CASEREASON           VARCHAR2(150),
  LABOREDUADDRESS      VARCHAR2(150),
  NORESETTLEMENTREASON VARCHAR2(150),
  AGOPROFESSION        NUMBER(10),
  CAREER               NUMBER(10),
  NATIVEPLACEADDRESS   VARCHAR2(150),
  NATIVEPOLICESTATION  VARCHAR2(90),
  CURRENTADDRESS       VARCHAR2(150),
  PROVINCE             VARCHAR2(30),
  CITY                 VARCHAR2(30),
  DISTRICT             VARCHAR2(30),
  NAME                 VARCHAR2(60) not null,
  USEDNAME             VARCHAR2(60),
  IDCARDNO             VARCHAR2(60) not null,
  TELEPHONE            VARCHAR2(80),
  MOBILENUMBER         VARCHAR2(50),
  ORGINTERNALCODE      VARCHAR2(32),
  FULLPINYIN           VARCHAR2(30),
  SIMPLEPINYIN         VARCHAR2(10),
  REMARK               VARCHAR2(900),
  BIRTHDAY             DATE,
  RESETTLEMENTDATE     DATE,
  RELEASEORBACKDATE    DATE,
  POSITIVEINFOTYPE     NUMBER(10),
  ISEMPHASIS           NUMBER(1) default 0,
  ISCRIME              NUMBER(1),
  CRIMEDATE            DATE,
  EMAIL                VARCHAR2(150),
  WORKUNIT             VARCHAR2(150),
  IMGURL               VARCHAR2(300),
  CURRENTADDRESSTYPE   NUMBER(10),
  COMMUNITY            VARCHAR2(60),
  BLOCK                VARCHAR2(24),
  UNIT                 VARCHAR2(18),
  ROOM                 VARCHAR2(30),
  POLITICALBACKGROUND  NUMBER(10),
  MARITALSTATE         NUMBER(10),
  RESIDENCETYPE        NUMBER(10),
  STATURE              NUMBER(10),
  NATION               NUMBER(10),
  FAITH                NUMBER(10),
  BLOODTYPE            NUMBER(10),
  CREATEUSER           VARCHAR2(32) not null,
  UPDATEUSER           VARCHAR2(32),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
  ISDEATH              NUMBER(1) default 0,
  OTHERADDRESS         VARCHAR2(150),
  CRIMINALBEHAVIOR     VARCHAR2(900),
  ISHAVEHOUSE          NUMBER(1) default 0,
  NOHOUSEREASON        VARCHAR2(800),
  ISEMPHASISREASON     VARCHAR2(300),
  ISEMPHASISDATE       DATE,
  CLAIMSTATE           NUMBER(10) default 0,
  CLAIMDATE            DATE,
  CLAIMUSERNAME        VARCHAR2(4000),
  CLAIMUSERID          NUMBER(10),
  CLAIMORGID           NUMBER(10),
  IMPORTDEPARTMENTID   NUMBER(10) not null,
  OLDORGID             NUMBER(10),
  INID                 NUMBER(10),
  ATTENTIONEXTENT      NUMBER(10),
  importDate   DATE,
  dispose number(10) default 0,
  claimAvailable number(10) default 0,
  actualPopulationType VARCHAR2(50),
  districtOrgId				  NUMBER(10),
  constraint PKDM_POSITIVETEMP primary key (ID),
  constraint FKDM_POSITIVETEMPORG foreign key (ORGID)
  				references ORGANIZATIONS (ID)
);
-- Add comments to the columns
comment on column DM_positiveInfos_TEMP.ORGID
  is '所属网格';
comment on column DM_positiveInfos_TEMP.GENDER
  is '性别';
comment on column DM_positiveInfos_TEMP.SCHOOLING
  is '文化水平';
comment on column DM_positiveInfos_TEMP.ISREPEAT
  is '是否累犯';
comment on column DM_positiveInfos_TEMP.IMPRISONMENTDATE
  is '刑期';
comment on column DM_positiveInfos_TEMP.CASEREASON
  is '原罪(错)';
comment on column DM_positiveInfos_TEMP.LABOREDUADDRESS
  is '劳教(服刑)场所';
comment on column DM_positiveInfos_TEMP.NORESETTLEMENTREASON
  is '未安置原因';
comment on column DM_positiveInfos_TEMP.AGOPROFESSION
  is '原职业';
comment on column DM_positiveInfos_TEMP.CAREER
  is '职业';
comment on column DM_positiveInfos_TEMP.NATIVEPLACEADDRESS
  is '户籍地详址';
comment on column DM_positiveInfos_TEMP.NATIVEPOLICESTATION
  is '户籍派出所';
comment on column DM_positiveInfos_TEMP.CURRENTADDRESS
  is '现在居住地';
comment on column DM_positiveInfos_TEMP.PROVINCE
  is '省';
comment on column DM_positiveInfos_TEMP.CITY
  is '市';
comment on column DM_positiveInfos_TEMP.DISTRICT
  is '区县';
comment on column DM_positiveInfos_TEMP.NAME
  is '姓名';
comment on column DM_positiveInfos_TEMP.USEDNAME
  is '曾用名';
comment on column DM_positiveInfos_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_positiveInfos_TEMP.TELEPHONE
  is '固定电话';
comment on column DM_positiveInfos_TEMP.MOBILENUMBER
  is '手机号码';
comment on column DM_positiveInfos_TEMP.ORGINTERNALCODE
  is '所属责任区编号';
comment on column DM_positiveInfos_TEMP.FULLPINYIN
  is '全拼';
comment on column DM_positiveInfos_TEMP.SIMPLEPINYIN
  is '简拼';
comment on column DM_positiveInfos_TEMP.REMARK
  is '备注';
comment on column DM_positiveInfos_TEMP.BIRTHDAY
  is '出生日期(有身份证号码获取)';
comment on column DM_positiveInfos_TEMP.RESETTLEMENTDATE
  is '安置时间';
comment on column DM_positiveInfos_TEMP.RELEASEORBACKDATE
  is '解教（刑释）日期';
comment on column DM_positiveInfos_TEMP.POSITIVEINFOTYPE
  is '解教类型';
comment on column DM_positiveInfos_TEMP.ISEMPHASIS
  is '是否关注';
comment on column DM_positiveInfos_TEMP.CRIMEDATE
  is '犯罪日期';
comment on column DM_positiveInfos_TEMP.EMAIL
  is '电子邮件';
comment on column DM_positiveInfos_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_positiveInfos_TEMP.IMGURL
  is '图片地址';
comment on column DM_positiveInfos_TEMP.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_positiveInfos_TEMP.COMMUNITY
  is '常住地址商品房 小区';
comment on column DM_positiveInfos_TEMP.BLOCK
  is '常住地址商品房 幢';
comment on column DM_positiveInfos_TEMP.UNIT
  is '常住地址商品房 单元';
comment on column DM_positiveInfos_TEMP.ROOM
  is '常住地址商品房  室';
comment on column DM_positiveInfos_TEMP.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_positiveInfos_TEMP.MARITALSTATE
  is '婚姻状况';
comment on column DM_positiveInfos_TEMP.RESIDENCETYPE
  is '户口类别';
comment on column DM_positiveInfos_TEMP.STATURE
  is '身高';
comment on column DM_positiveInfos_TEMP.NATION
  is '民族';
comment on column DM_positiveInfos_TEMP.FAITH
  is '宗教信仰';
comment on column DM_positiveInfos_TEMP.BLOODTYPE
  is '血型';
comment on column DM_positiveInfos_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_positiveInfos_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_positiveInfos_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_positiveInfos_TEMP.UPDATEDATE
  is '修改时间';
comment on column DM_positiveInfos_TEMP.ISDEATH
  is '是否死亡';
comment on column DM_positiveInfos_TEMP.OTHERADDRESS
  is '临时居所';
comment on column DM_positiveInfos_TEMP.CRIMINALBEHAVIOR
  is '犯罪行为';
comment on column DM_positiveInfos_TEMP.ISHAVEHOUSE
  is '是否有房屋';
comment on column DM_positiveInfos_TEMP.NOHOUSEREASON
  is '无房原因';
comment on column DM_positiveInfos_TEMP.ISEMPHASISREASON
  is '注销原因';
comment on column DM_positiveInfos_TEMP.ISEMPHASISDATE
  is '注销日期';
comment on column DM_positiveInfos_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_positiveInfos_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_positiveInfos_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_positiveInfos_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_positiveInfos_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_positiveInfos_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_positiveInfos_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_positiveInfos_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_positiveInfos_TEMP.ATTENTIONEXTENT
  is '关注程度';
comment on column DM_positiveInfos_TEMP.importDate
  is '导入时间';
comment on column DM_positiveInfos_TEMP.actualPopulationType is
'实口类型';
comment on column DM_positiveInfos_TEMP.dispose is '是否经过处理(0:否)';
comment on column DM_positiveInfos_TEMP.claimAvailable is '是否可以认领(0:否)';
comment on column DM_positiveInfos_TEMP.districtOrgId is
'导入时的到县区的组织机构';

create sequence s_DM_rectificativePersons_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;  
 /*==============================================================*/
/* Table: DM_rectificativePersons_Temp     数据管理_社区矫正人员                                  */
/*==============================================================*/
create table DM_rectificativePersons_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10),
   gender               NUMBER(10),   
   executeType          NUMBER(10),
   penaltyTerm          VARCHAR2(60),
   recentSituation      VARCHAR2(900),
   helpEducator         VARCHAR2(60),
   educatorTelephone    VARCHAR2(80),
   educatorMobileNumber VARCHAR2(50),
   nativePlaceAddress   VARCHAR2(150),
   nativePoliceStation  VARCHAR2(90),
   currentAddress       VARCHAR2(150),
   province             VARCHAR2(60),
   city                 VARCHAR2(60),
   district             VARCHAR2(60),
   name                 VARCHAR2(60)                    not null,
   idCardNo             VARCHAR2(60)                    not null,
   telephone            VARCHAR2(80),
   mobileNumber         VARCHAR2(50),
   orgInternalCode      VARCHAR2(32),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   remark               VARCHAR2(900),
   createUser           VARCHAR2(60)                    not null,
   updateUser           VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
   birthday             DATE,
   rectifyStartDate     DATE,
   rectifyEndDate       DATE,
   isEmphasis           NUMBER(1)                      default 0,
   isDeath              NUMBER(1)                      default 0,
   otherAddress         VARCHAR2(150),
   workUnit             VARCHAR2(150),
   usedName             VARCHAR2(60),
   career               NUMBER(10),
   politicalBackground  NUMBER(10),
   maritalState         NUMBER(10),
   residenceType        NUMBER(10),
   stature              NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   schooling            NUMBER(10),
   email                VARCHAR2(150),
   bloodType            NUMBER(10),
   bussinessRemark      VARCHAR2(600),
   imgUrl               VARCHAR2(300),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(60),
   block 		        VARCHAR2(24),
   unit 		        VARCHAR2(18),
   room 		        VARCHAR2(30),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   ATTENTIONEXTENT     	  NUMBER(10),
   ACCUSATION          	  VARCHAR2(150),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   constraint PKDM_RECTIFICATIVEPERTEMP primary key (id),
   constraint FKDM_RECTIFICATIVEPERTEMPORG foreign key (orgId)
         references organizations (id)
);

 create sequence s_DM_mentalPatients_Temp
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* Table: DM_mentalPatients_Temp     数据管理_严重精神障碍患者                                   */
/*==============================================================*/
create table DM_mentalPatients_Temp  (
   id                   NUMBER(10)                      not null,
   createUser           VARCHAR2(32)                    not null,
   createDate           DATE                            not null,
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   name                 VARCHAR2(60)                    not null,
   idCardNo             VARCHAR2(60)					not null,
   birthday             DATE,
   email                VARCHAR2(150),
   orgId                NUMBER(10)                      ,
   orgInternalCode      VARCHAR2(32)                    ,
   mobileNumber         VARCHAR2(50),
   workUnit             VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   remark               VARCHAR2(900),
   telephone            VARCHAR2(80),
   imgUrl               VARCHAR2(300),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   gender               NUMBER(10)                      ,
   nativePlaceAddress   VARCHAR2(150),
   stature              NUMBER(10),
   address              VARCHAR2(150),
   otherAddress       	VARCHAR2(150),
   career				NUMBER(10),
   isDeath              NUMBER(1)                       default 0 ,
   politicalBackground  NUMBER(10),
   maritalState         NUMBER(10),
   usedName             VARCHAR2(60),
   residenceType        NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   schooling            NUMBER(10),
   healthState          NUMBER(10),
   bloodType            NUMBER(10),
   school               VARCHAR2(150),
   nativePoliceStation  VARCHAR2(90),
   province             VARCHAR2(30),
   city                 VARCHAR2(30),
   district             VARCHAR2(30),
   isEmphasis           NUMBER(1)                       default 0,
   psychosisName        VARCHAR2(150),
   psychosisType 		NUMBER(10),
   dangerLevel          NUMBER(10)                      ,
   isTreat              NUMBER(1),
   cureDepartment 		VARCHAR2(150),
   businessRemark       VARCHAR2(900),
   guardian             VARCHAR2(60),
   guardianTelephone    VARCHAR2(80),
   guardianMobileNumber VARCHAR2(50),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block  VARCHAR2(150),
   unit  VARCHAR2(150),
   room  VARCHAR2(150),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason    VARCHAR2(300),
   isEmphasisDate      DATE,
   ATTENTIONEXTENT     	  NUMBER(10),
   claimState             NUMBER(10) default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   importDate   DATE,
   oldOrgId				  NUMBER(10),
   districtOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   isUndergo_treatment NUMBER(1),
   recoveryTime         DATE,
   diseaseTime          DATE,
   treatmentState       NUMBER(10),
   constraint PKDM_MENTALPATIENTSTEMP primary key (id),
   constraint FKDM_MENTALPATIENTSTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_mentalPatients_Temp is
'严重精神障碍患者';

comment on column DM_mentalPatients_Temp.id is
'ID';

comment on column DM_mentalPatients_Temp.orgId is
'所属网格';

comment on column DM_mentalPatients_Temp.name is
'姓名';

comment on column DM_mentalPatients_Temp.gender is
'性别';

comment on column DM_mentalPatients_Temp.idCardNo is
'身份证号码';

comment on column DM_mentalPatients_Temp.birthday is
'出生日期';

comment on column DM_mentalPatients_Temp.psychosisName is
'患病名称';

comment on column DM_mentalPatients_Temp.psychosisType is 
'精神病类型';

comment on column DM_mentalPatients_Temp.dangerLevel is
'发病危险等级';

comment on column DM_mentalPatients_Temp.cureDepartment is
'康复机构';

comment on column DM_mentalPatients_Temp.isTreat is
'是否接受过精神病治疗';

comment on column DM_mentalPatients_Temp.businessRemark is
'业务信息备注';

comment on column DM_mentalPatients_Temp.guardian is
'监护人';

comment on column DM_mentalPatients_Temp.guardianTelephone is
'监护人联系电话';

comment on column DM_mentalPatients_Temp.guardianMobileNumber is
'监护人移动电话';

comment on column DM_mentalPatients_Temp.currentAddress is
'常住地址';

comment on column DM_mentalPatients_Temp.otherAddress is
'其它地址';

comment on column DM_mentalPatients_Temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_mentalPatients_Temp.nativePoliceStation is
'户籍派出所';

comment on column DM_mentalPatients_Temp.province is
'省';

comment on column DM_mentalPatients_Temp.city is
'市';

comment on column DM_mentalPatients_Temp.district is
'区县';

comment on column DM_mentalPatients_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_mentalPatients_Temp.telephone is
'固定电话';

comment on column DM_mentalPatients_Temp.mobileNumber is
'手机号码';

comment on column DM_mentalPatients_Temp.orgInternalCode is
'所属责任区编号';

comment on column DM_mentalPatients_Temp.fullPinyin is
'全拼';

comment on column DM_mentalPatients_Temp.simplePinyin is
'简拼';

comment on column DM_mentalPatients_Temp.remark is
'备注';

comment on column DM_mentalPatients_Temp.createUser is
'创建用户';

comment on column DM_mentalPatients_Temp.updateUser is
'修改用户';

comment on column DM_mentalPatients_Temp.createDate is
'创建日期';

comment on column DM_mentalPatients_Temp.updateDate is
'修改时间';

comment on column DM_mentalPatients_Temp.isEmphasis is
'是否关注';

comment on column DM_mentalPatients_Temp.isDeath is
'是否死亡';

comment on column DM_mentalPatients_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_mentalPatients_Temp.usedName is
'曾用名';

comment on column DM_mentalPatients_Temp.career is
'职业';

comment on column DM_mentalPatients_Temp.politicalBackground is
'政治面貌';

comment on column DM_mentalPatients_Temp.maritalState is
'婚姻状况';

comment on column DM_mentalPatients_Temp.residenceType is
'户口类别';

comment on column DM_mentalPatients_Temp.stature is
'身高';

comment on column DM_mentalPatients_Temp.nation is
'民族';

comment on column DM_mentalPatients_Temp.schooling is
'学历';

comment on column DM_mentalPatients_Temp.healthState is
'健康状况';

comment on column DM_mentalPatients_Temp.email is
'电子邮件';

comment on column DM_mentalPatients_Temp.school is
'就读学校';

comment on column DM_mentalPatients_Temp.currentAddressType is
'常住地址类型';

comment on column DM_mentalPatients_Temp.community is
'小区';

comment on column DM_mentalPatients_Temp.block is
'幢';

comment on column DM_mentalPatients_Temp.unit is
'单元';

comment on column DM_mentalPatients_Temp.room is
'室';

comment on column DM_mentalPatients_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_mentalPatients_Temp.noHouseReason is
'无房原因';

comment on column DM_mentalPatients_Temp.actualPopulationType is
'实口类型';

comment on column DM_mentalPatients_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_mentalPatients_Temp.claimDate is
'认领日期';

comment on column DM_mentalPatients_Temp.claimUserName is
'认领人用户名';

comment on column DM_mentalPatients_Temp.claimUserId is
'认领人Id';

comment on column DM_mentalPatients_Temp.claimOrgId is
'认领人网格';

comment on column DM_mentalPatients_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_mentalPatients_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_mentalPatients_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_mentalPatients_Temp.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_mentalPatients_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_mentalPatients_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_mentalPatients_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_mentalPatients_Temp.isUndergo_treatment is
'目前是否在接受治疗';
comment on column DM_mentalPatients_Temp.recoveryTime is '康复时间';
comment on column DM_mentalPatients_Temp.diseaseTime is '发病时间';
comment on column DM_mentalPatients_Temp.treatmentState is '治疗状态';

create sequence s_DM_druggys_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

 /*==============================================================*/
/* Table: DM_druggys_Temp     数据管理_吸毒人员                                  */
/*==============================================================*/
 create table DM_druggys_Temp (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      ,
   gender               NUMBER(10)                      ,
   drugReason           NUMBER(10),
   drugSource           NUMBER(10),
   detoxicateCase       NUMBER(10),
   detoxicateCondition  NUMBER(10),
   isAdanon             NUMBER(1)                      default 0,
   controlActuality     VARCHAR2(150),
   drugType             VARCHAR2(150),
   workUnit             VARCHAR2(150),
   province             VARCHAR2(30),
   city                 VARCHAR2(30),
   district             VARCHAR2(30),
   nativePlaceAddress   VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   name                 VARCHAR2(60)                    not null,
   usedName		        VARCHAR2(60),
   idCardNo             VARCHAR2(60)                    not null,
   telephone            VARCHAR2(80),
   mobileNumber         VARCHAR2(50),
   orgInternalCode      VARCHAR2(32)                    ,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   remark               VARCHAR2(900),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   birthday             DATE,
   ferretOutDate        DATE,
   drugfristDate        DATE,
   imgUrl               VARCHAR2(300),
   email                VARCHAR2(150),
   isEmphasis           NUMBER(1)                      default 0,
   isDeath              NUMBER(1)                      default 0,
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block 				VARCHAR2(150),
   unit 				VARCHAR2(150),
   room 				VARCHAR2(150),
   nation               NUMBER(10),
   politicalBackground  NUMBER(10),
   schooling			NUMBER(10),
   career               NUMBER(10),
   maritalState         NUMBER(10),
   bloodType            NUMBER(10),
   faith                NUMBER(10),
   otherAddress         VARCHAR2(150),
   stature              NUMBER(10),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   ATTENTIONEXTENT     	  NUMBER(10),
   
   claimState             NUMBER(10) default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   importDate   DATE,
   oldOrgId				  NUMBER(10),
   districtOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   isUndergo_treatment NUMBER(1),
   constraint PKDM_DRUGGYSTEMP primary key (id),
   constraint FKDM_DRUGGYSTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_druggys_Temp is
'吸毒者信息';

comment on column DM_druggys_Temp.id is
'人员id';

comment on column DM_druggys_Temp.detoxicateCondition is
'是否在吸';

comment on column DM_druggys_Temp.orgId is
'所属网格';

comment on column DM_druggys_Temp.gender is
'性别';

comment on column DM_druggys_Temp.drugReason is
'吸毒原因';

comment on column DM_druggys_Temp.drugSource is
'毒品来源';

comment on column DM_druggys_Temp.detoxicateCase is
'戒毒情况';

comment on column DM_druggys_Temp.isAdanon is
'是否服美沙酮戒毒';

comment on column DM_druggys_Temp.controlActuality is
'管控现状';

comment on column DM_druggys_Temp.drugType is
'滥用毒品种类';

comment on column DM_druggys_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_druggys_Temp.province is
'省';

comment on column DM_druggys_Temp.city is
'市';

comment on column DM_druggys_Temp.district is
'区县';

comment on column DM_druggys_Temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_druggys_Temp.currentAddress is
'现在居住地';

comment on column DM_druggys_Temp.name is
'吸毒员姓名';

comment on column DM_druggys_Temp.usedName is
'曾用名';

comment on column DM_druggys_Temp.idCardNo is
'身份证号码';

comment on column DM_druggys_Temp.telephone is
'固定电话';

comment on column DM_druggys_Temp.mobileNumber is
'手机号码';

comment on column DM_druggys_Temp.orgInternalCode is
'所属责任区编号';

comment on column DM_druggys_Temp.fullPinyin is
'全拼';

comment on column DM_druggys_Temp.simplePinyin is
'简拼';

comment on column DM_druggys_Temp.remark is
'备注';

comment on column DM_druggys_Temp.createUser is
'创建用户';

comment on column DM_druggys_Temp.updateUser is
'修改用户';

comment on column DM_druggys_Temp.createDate is
'创建日期';

comment on column DM_druggys_Temp.updateDate is
'修改时间';

comment on column DM_druggys_Temp.birthday is
'出生日期';

comment on column DM_druggys_Temp.ferretOutDate is
'查获日期';

comment on column DM_druggys_Temp.isEmphasis is
'是否关注';

comment on column DM_druggys_Temp.isDeath is
'是否死亡';

comment on column DM_druggys_Temp.imgUrl is
'图片链接地址';

comment on column DM_druggys_Temp.email is
'电子邮箱';

comment on column DM_druggys_Temp.currentAddressType is
'常住地址类型';

comment on column DM_druggys_Temp.community is
'常住地址商品房 小区';

comment on column DM_druggys_Temp.block is
'常住地址商品房 幢';

comment on column DM_druggys_Temp.unit is
'常住地址商品房 单元';

comment on column DM_druggys_Temp.room is
'常住地址商品房  室';

comment on column DM_druggys_Temp.nation is
'民族';

comment on column DM_druggys_Temp.politicalBackground is
'政治面貌';

comment on column DM_druggys_Temp.schooling is
'文化程度';

comment on column DM_druggys_Temp.career is
'职业';

comment on column DM_druggys_Temp.maritalState is
'婚姻状况';

comment on column DM_druggys_Temp.bloodType is
'血型';

comment on column DM_druggys_Temp.faith is
'宗教信仰';

comment on column DM_druggys_Temp.otherAddress is
'临时居所';

comment on column DM_druggys_Temp.stature is
'身高';

comment on column DM_druggys_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_druggys_Temp.noHouseReason is
'无房原因';
comment on column DM_druggys_Temp.actualPopulationType is
'实口类型';

comment on column DM_druggys_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_druggys_Temp.claimDate is
'认领日期';

comment on column DM_druggys_Temp.claimUserName is
'认领人用户名';

comment on column DM_druggys_Temp.claimUserId is
'认领人Id';

comment on column DM_druggys_Temp.claimOrgId is
'认领人网格';

comment on column DM_druggys_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_druggys_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_druggys_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_druggys_Temp.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_druggys_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_druggys_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_druggys_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_druggys_Temp.isUndergo_treatment is
'目前是否在接受治疗';


create sequence s_DM_idleYouths_temp
start with 1
maxvalue 9999999999
minvalue 1
cache 20;
 /*==============================================================*/
/* Table: DM_idleYouths_temp     数据管理_重点青少年                                  */
/*==============================================================*/
create table DM_idleYouths_temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32),
   gender               NUMBER(10),
   idCardNo             VARCHAR2(60),
   name                 VARCHAR2(60)                    not null,
   usedName             VARCHAR2(60),
   birthday             DATE,
   province             VARCHAR2(60),
   city                 VARCHAR2(60),
   district             VARCHAR2(60),
   nativePlaceAddress   VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   politicalBackground  NUMBER(10),
   mobileNumber         VARCHAR2(50),
   telephone            VARCHAR2(80),
   maritalState         NUMBER(10),
   stature              NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   bloodType            NUMBER(10),
   schooling            NUMBER(10),
   career               NUMBER(10),
   email                VARCHAR2(150),
   workUnit             VARCHAR2(150),
   remark               VARCHAR2(900),
   isDeath              NUMBER(1)                      default 0,
   imgUrl               VARCHAR2(300),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(60),
   block                VARCHAR2(24),
   unit                 VARCHAR2(18),
   room                 VARCHAR2(30),
   isEmphasis           NUMBER(1)                      default 0,
   createUser           VARCHAR2(32)                   not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                           not null,
   updateDate           DATE,
   otherAddress         VARCHAR2(150),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   isHaveHouse      	NUMBER(1)		       default 0,
   noHouseReason	VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   attentionExtent        NUMBER(10),
   HELPINGSITUATION		NUMBER(10),
   GUARDIANNAME         VARCHAR2(60),
   GUARDIANTELEPHONE    VARCHAR2(15),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   ISSTAYINSCHOOL NUMBER(1) DEFAULT 0,
   SCHOOLNAME VARCHAR2(90),
   WORKCONDITION VARCHAR2(300),
   constraint PKDM_IDLEYOUTHSTEMP primary key (id),
   constraint FKDM_IDLEYOUTHSTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_idleYouths_temp is
'重点青少年';

comment on column DM_idleYouths_temp.orgId is
'所属网格';

comment on column DM_idleYouths_temp.orgInternalCode is
'所属网格编号';

comment on column DM_idleYouths_temp.gender is
'性别';

comment on column DM_idleYouths_temp.idCardno is
'身份证id';

comment on column DM_idleYouths_temp.name is
'姓名';

comment on column DM_idleYouths_temp.usedName is
'曾用名';

comment on column DM_idleYouths_temp.birthday is
'出生日期';

comment on column DM_idleYouths_temp.province is
'省';

comment on column DM_idleYouths_temp.city is
'市';

comment on column DM_idleYouths_temp.district is
'区县';

comment on column DM_idleYouths_temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_idleYouths_temp.currentAddress is
'常住地址';

comment on column DM_idleYouths_temp.politicalBackground is
'政治面貌';

comment on column DM_idleYouths_temp.mobileNumber is
'手机号码';

comment on column DM_idleYouths_temp.telephone is
'固定电话';

comment on column DM_idleYouths_temp.maritalState is
'婚姻状况';

comment on column DM_idleYouths_temp.stature is
'身高';

comment on column DM_idleYouths_temp.nation is
'民族';

comment on column DM_idleYouths_temp.faith is
'宗教信仰';

comment on column DM_idleYouths_temp.schooling is
'文化程度';

comment on column DM_idleYouths_temp.bloodType is
'血型';

comment on column DM_idleYouths_temp.email is
'电子邮箱';

comment on column DM_idleYouths_temp.workUnit is
'工作单位或就读学校';

comment on column DM_idleYouths_temp.remark is
'备注';

comment on column DM_idleYouths_temp.isDeath is
'是否死亡';

comment on column DM_idleYouths_temp.createUser is
'创建用户';

comment on column DM_idleYouths_temp.updateUser is
'修改用户';

comment on column DM_idleYouths_temp.createDate is
'创建日期';

comment on column DM_idleYouths_temp.updateDate is
'修改时间';

comment on column DM_idleYouths_temp.otherAddress is
'临时居所';

comment on column DM_idleYouths_temp.career is
'职业';

comment on column DM_idleYouths_temp.imgUrl is
'头像路径';

comment on column DM_idleYouths_temp.currentAddressType is
'常住地址类型';

comment on column DM_idleYouths_temp.community is
'常住地址商品房 小区';

comment on column DM_idleYouths_temp.block is
'常住地址商品房 幢';

comment on column DM_idleYouths_temp.unit is
'常住地址商品房 单元';

comment on column DM_idleYouths_temp.room is
'常住地址商品房  室';

comment on column DM_idleYouths_temp.isEmphasis is
'是否关注';

comment on column DM_idleYouths_temp.simplePinyin is
'简拼';

comment on column DM_idleYouths_temp.fullPinyin is
'全拼';

comment on column DM_idleYouths_temp.isHaveHouse is
'是否有房屋';

comment on column DM_idleYouths_temp.noHouseReason is
'无房原因';

comment on column DM_idleYouths_temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_idleYouths_temp.claimDate is
'认领日期';

comment on column DM_idleYouths_temp.claimUserName is
'认领人用户名';

comment on column DM_idleYouths_temp.claimUserId is
'认领人Id';

comment on column DM_idleYouths_temp.claimOrgId is
'认领人网格';

comment on column DM_idleYouths_temp.importDepartmentId is
'导入部门Id';

comment on column DM_idleYouths_temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_idleYouths_temp.inId is
'认领时插入到原库中数据id';

comment on column DM_idleYouths_temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_idleYouths_temp.importDate
  is '导入时间';
comment on column DM_idleYouths_temp.actualPopulationType is
'实口类型';
comment on column DM_idleYouths_temp.dispose is '是否经过处理(0:否)';
comment on column DM_idleYouths_temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_idleYouths_temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column dm_idleyouths_temp.ISSTAYINSCHOOL is '是否在校住宿';
comment on column dm_idleyouths_temp.SCHOOLNAME is '学校名称';
comment on column dm_idleyouths_temp.WORKCONDITION is '工作情况';

comment on column dm_idleyouths_temp.HELPINGSITUATION is
'帮扶情况';
comment on column dm_idleyouths_temp.GUARDIANNAME is
'监护人';
comment on column dm_idleyouths_temp.GUARDIANTELEPHONE is
'联系方式';

create sequence s_DM_superiorVisits_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

/*==============================================================*/
/* Table: DM_superiorVisits_Temp              数据管理_重点上访                           */
/*==============================================================*/
create table DM_superiorVisits_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32),
   name                 VARCHAR2(60)                    not null,
   gender               NUMBER(10) ,
   idCardNo             VARCHAR2(60)                     not null,
   birthday             DATE,
   email                VARCHAR2(150),
   mobileNumber         VARCHAR2(50),
   workUnit             VARCHAR2(150),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block 				VARCHAR2(150),
   unit 				VARCHAR2(150),
   room 				VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   nativePlaceAddress   VARCHAR2(150),
   telephone            VARCHAR2(80),
   imgUrl               VARCHAR2(300),
   stature              NUMBER(10),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   remark               VARCHAR2(900),
   otherAddress         VARCHAR2(150),
   career               NUMBER(10),
   isDeath              NUMBER(1)                      default 0,
   politicalBackground  NUMBER(10),
   maritalState         NUMBER(10),
   usedName		        VARCHAR2(60),
   residenceType        NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   schooling			NUMBER(10),
   bloodType            NUMBER(10),
   province             VARCHAR2(30),
   city                 VARCHAR2(30),
   district             VARCHAR2(30),
   isEmphasis           NUMBER(1)                      default 0,
   visitState			NUMBER(10),
   visitType			NUMBER(10),
   visitReason			VARCHAR2(600) ,
   createUser           VARCHAR2(32)                      not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                       not null,
   updateDate           DATE,
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                      not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   ATTENTIONEXTENT     	  NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   constraint PKDM_SUPERIORVISITSTEMP primary key (id),
   constraint FKDM_SUPERIORVISITSSTEMPORG foreign key (orgId)
         references organizations (id)
);

create sequence s_DM_dangerous_Temp
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 

/*==============================================================*/
/* Table: DM_dangerousPractitioners_Temp         数据管理_危险品从业人员                  */
/*==============================================================*/
   create table DM_dangerousPractitioners_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   gender               NUMBER(10) ,
   dangerousWorkingType NUMBER(10),
   workUnit             VARCHAR2(150)                   ,
   legalPerson          VARCHAR2(60)                    ,
   legalPersonTelephone VARCHAR2(80)                   ,
   legalPersonMobileNumber VARCHAR2(50)     ,
   workingCertificate   VARCHAR2(150),
   workingCertificateNo VARCHAR2(150),
   nativePlaceAddress   VARCHAR2(150),
   nativePoliceStation  VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   otherAddress       VARCHAR2(150),
   province             VARCHAR2(60),
   city                 VARCHAR2(60),
   district             VARCHAR2(60),
   name                 VARCHAR2(60)                    not null,
   idCardNo             VARCHAR2(60)                    not null,
   telephone            VARCHAR2(80),
   mobileNumber         VARCHAR2(50),
   orgInternalCode      VARCHAR2(32) ,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   remark               VARCHAR2(900),
   birthday             DATE,
   isEmphasis           NUMBER(1)                      default 0,
   usedname  VARCHAR2(60),
   career  NUMBER(10),
   isdeath              NUMBER(1)     default 0,
   politicalbackground  NUMBER(10),
   maritalstate         NUMBER(10),
   residencetype        NUMBER(10),
   stature              NUMBER(10),
   nation               NUMBER(10),
   faith                NUMBER(10),
   schooling            NUMBER(10),
   healthstate          NUMBER(10),
   bloodtype            NUMBER(10),
   email                VARCHAR2(150),
   school               VARCHAR2(150),
   imgurl      VARCHAR2(300),
   periodOfValidityStart DATE,
   periodOfValidityEnd   DATE,
   createUser           VARCHAR2(60)                    not null,
   updateUser           VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block  VARCHAR2(150),
   unit  VARCHAR2(150),
   room  VARCHAR2(150),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   ATTENTIONEXTENT     	  NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   constraint PKDM_DANGERPRACTITIONERTEMP primary key (id),
   constraint FKDM_DANGERPRACTITIONERTEMPORG foreign key (orgId)
         references organizations (id)
);


create sequence s_DM_elderlyPeople_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 9999999999;

/*==============================================================*/
/* Table: DM_elderlyPeople_Temp(实体表无s)    数据管理_老年人                                 */
/*==============================================================*/
create table DM_elderlyPeople_Temp (
	ID                  NUMBER(10) not null,
  NAME                VARCHAR2(60) not null,
  IDCARDNO            VARCHAR2(18) not null,
  GENDER              NUMBER(10) ,
  BIRTHDAY            DATE,
  CURRENTADDRESS      VARCHAR2(150),
  TELEPHONE           VARCHAR2(80),
  MOBILENUMBER        VARCHAR2(11),
  ORGID               NUMBER(10) ,
  SCHOOLING           NUMBER(10),
  ENTERWORKDATE       DATE,
  RETIREDATE          DATE,
  PENSION             NUMBER(11,2),
  LIVESTATUS          NUMBER(10),
  PEOPLETYPE          NUMBER(10),
  SPOUSESTATUS        NUMBER(10),
  CURRENTSTATUS       NUMBER(10),
  INCOMESOURCE        NUMBER(10),
  RETIREUNITANDDUTY   VARCHAR2(100),
  REMARK              VARCHAR2(600),
  FULLPINYIN          VARCHAR2(30),
  SIMPLEPINYIN        VARCHAR2(10),
  ORGINTERNALCODE     VARCHAR2(32) ,
  CREATEUSER          VARCHAR2(32) not null,
  CREATEDATE          DATE not null,
  UPDATEUSER          VARCHAR2(32),
  UPDATEDATE          DATE,
  USEDNAME            VARCHAR2(60),
  NATION              NUMBER(10),
  POLITICALBACKGROUND NUMBER(10),
  CAREER              NUMBER(10),
  WORKUNIT            VARCHAR2(150),
  MARITALSTATE        NUMBER(10),
  ISDEATH             NUMBER(1) default 0,
  STATURE             NUMBER(10) default 0,
  BLOODTYPE           NUMBER(10),
  FAITH               NUMBER(10),
  EMAIL               VARCHAR2(60),
  RESIDENCETYPE       NUMBER(10),
  IMGURL              VARCHAR2(300),
  PROVINCE            VARCHAR2(60),
  CITY                VARCHAR2(60),
  DISTRICT            VARCHAR2(60),
  NATIVEPLACEADDRESS  VARCHAR2(150),
  OTHERADDRESS        VARCHAR2(150),
  ZHIWU               VARCHAR2(60),
  ELDERPEOPLEID       VARCHAR2(60),
  CURRENTADDRESSTYPE  NUMBER(10),
  COMMUNITY            VARCHAR2(150),
  BLOCK 				VARCHAR2(150),
  UNIT 				VARCHAR2(150),
  ROOM 				VARCHAR2(150),
  BEIZHU              VARCHAR2(160),
  ISEMPHASIS          NUMBER(1) default 0,
  isHaveHouse      	NUMBER(1)  						default 0,
  noHouseReason	    VARCHAR2(800),
  isEmphasisReason     VARCHAR2(300),
  isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(15),
   attentionExtent      NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
	constraint PKDM_ELDERLYPEOPLETEMP primary key (id),
	constraint FKDM_ELDERLYPEOPLETEMPORG foreign key (orgId)
		references organizations (id)
);

-- Add comments to the columns
comment on table DM_elderlyPeople_Temp
  is '数据管理老年人';
-- Add comments to the columns
comment on column DM_elderlyPeople_Temp.ID
  is 'ID';
comment on column DM_elderlyPeople_Temp.NAME
  is '姓名';
comment on column DM_elderlyPeople_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_elderlyPeople_Temp.GENDER
  is '性别';
comment on column DM_elderlyPeople_Temp.BIRTHDAY
  is '出生日期';
comment on column DM_elderlyPeople_Temp.CURRENTADDRESS
  is '常住地址';
comment on column DM_elderlyPeople_Temp.TELEPHONE
  is '固定电话';
comment on column DM_elderlyPeople_Temp.MOBILENUMBER
  is '手机号码';
comment on column DM_elderlyPeople_Temp.ORGID
  is '所属网格';
comment on column DM_elderlyPeople_Temp.SCHOOLING
  is '文化程度';
comment on column DM_elderlyPeople_Temp.ENTERWORKDATE
  is '参加工作日期';
comment on column DM_elderlyPeople_Temp.RETIREDATE
  is '离退休日期';
comment on column DM_elderlyPeople_Temp.PENSION
  is '退休金';
comment on column DM_elderlyPeople_Temp.LIVESTATUS
  is '居住情况';
comment on column DM_elderlyPeople_Temp.PEOPLETYPE
  is '特殊人员';
comment on column DM_elderlyPeople_Temp.SPOUSESTATUS
  is '配偶情况';
comment on column DM_elderlyPeople_Temp.CURRENTSTATUS
  is '目前情况';
comment on column DM_elderlyPeople_Temp.INCOMESOURCE
  is '经济来源';
comment on column DM_elderlyPeople_Temp.RETIREUNITANDDUTY
  is '离退休单位';
comment on column DM_elderlyPeople_Temp.REMARK
  is '备注';
comment on column DM_elderlyPeople_Temp.FULLPINYIN
  is '全拼';
comment on column DM_elderlyPeople_Temp.SIMPLEPINYIN
  is '简拼';
comment on column DM_elderlyPeople_Temp.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_elderlyPeople_Temp.CREATEUSER
  is '创建用户';
comment on column DM_elderlyPeople_Temp.CREATEDATE
  is '创建时间';
comment on column DM_elderlyPeople_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_elderlyPeople_Temp.UPDATEDATE
  is '修改时间';
comment on column DM_elderlyPeople_Temp.USEDNAME
  is '别名';
comment on column DM_elderlyPeople_Temp.NATION
  is '民族';
comment on column DM_elderlyPeople_Temp.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_elderlyPeople_Temp.CAREER
  is '职业';
comment on column DM_elderlyPeople_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_elderlyPeople_Temp.MARITALSTATE
  is '婚姻状况';
comment on column DM_elderlyPeople_Temp.ISDEATH
  is '是否死亡';
comment on column DM_elderlyPeople_Temp.STATURE
  is '身高';
comment on column DM_elderlyPeople_Temp.BLOODTYPE
  is '血型';
comment on column DM_elderlyPeople_Temp.FAITH
  is '宗教信仰';
comment on column DM_elderlyPeople_Temp.EMAIL
  is '邮件';
comment on column DM_elderlyPeople_Temp.RESIDENCETYPE
  is '户口类别';
comment on column DM_elderlyPeople_Temp.IMGURL
  is '头像';
comment on column DM_elderlyPeople_Temp.PROVINCE
  is '户籍地-省';
comment on column DM_elderlyPeople_Temp.CITY
  is '户籍地-市';
comment on column DM_elderlyPeople_Temp.DISTRICT
  is '户籍地-县';
comment on column DM_elderlyPeople_Temp.NATIVEPLACEADDRESS
  is '户籍地详细地址';
comment on column DM_elderlyPeople_Temp.OTHERADDRESS
  is '临时居所';
comment on column DM_elderlyPeople_Temp.ZHIWU
  is '离退休单位职位';
comment on column DM_elderlyPeople_Temp.ELDERPEOPLEID
  is '老年人证号';
comment on column DM_elderlyPeople_Temp.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_elderlyPeople_Temp.COMMUNITY
  is '小区';
comment on column DM_elderlyPeople_Temp.BLOCK
  is '幢';
comment on column DM_elderlyPeople_Temp.UNIT
  is '单元';
comment on column DM_elderlyPeople_Temp.ROOM
  is '室';
comment on column DM_elderlyPeople_Temp.BEIZHU
  is '业务信息备注';
comment on column DM_elderlyPeople_Temp.ISEMPHASIS
  is '是否注销';
comment on column DM_elderlyPeople_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_elderlyPeople_Temp.noHouseReason is
'无房原因';

comment on column DM_elderlyPeople_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_elderlyPeople_Temp.claimDate is
'认领日期';

comment on column DM_elderlyPeople_Temp.claimUserName is
'认领人用户名';

comment on column DM_elderlyPeople_Temp.claimUserId is
'认领人Id';

comment on column DM_elderlyPeople_Temp.claimOrgId is
'认领人网格';

comment on column DM_elderlyPeople_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_elderlyPeople_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_elderlyPeople_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_elderlyPeople_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_elderlyPeople_Temp.importDate
  is '导入时间';
comment on column DM_elderlyPeople_Temp.actualPopulationType is
'实口类型';
comment on column DM_elderlyPeople_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_elderlyPeople_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_elderlyPeople_Temp.districtOrgId is
'导入时的到县区的组织机构';

 create sequence s_DM_handicappeds_Temp
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

/*==============================================================*/
/* Table: DM_handicappeds_Temp    数据管理_残疾人                                 */
/*==============================================================*/
create table DM_handicappeds_Temp  (
   ID                   NUMBER(10)                      not null,
   ORGID                NUMBER(10)                      ,
   ORGINTERNALCODE 		VARCHAR2(32),
   GENDER               NUMBER(10)                      ,
   IDCARDNO				VARCHAR2(60)                    not null,
   NAME                 VARCHAR2(60)                    not null,
   USEDNAME				VARCHAR2(60),
   BIRTHDAY             DATE,
   PROVINCE				VARCHAR2(60),
   CITY					VARCHAR2(60),
   DISTRICT				VARCHAR2(60),
   NATIVEPLACEADDRESS   VARCHAR2(150),
   CURRENTADDRESS       VARCHAR2(150),
   MOBILENUMBER         VARCHAR2(50),
   TELEPHONE            VARCHAR2(80),
   POLITICALBACKGROUND  NUMBER(10),
   MARITALSTATE         NUMBER(10),
   STATURE              NUMBER(10),
   NATION               NUMBER(10),
   FAITH                NUMBER(10),
   BLOODTYPE            NUMBER(10),
   SCHOOLING            NUMBER(10),
   CAREER               NUMBER(10),
   EMAIL                VARCHAR2(150),
   WORKUNIT             VARCHAR2(150),
   ISDEATH              NUMBER(1)                      default 0 ,
   OTHERADDRESS         VARCHAR2(150),
   IMGURL               VARCHAR2(300),
   CURRENTADDRESSTYPE   NUMBER(10),
   COMMUNITY            VARCHAR2(60),
   BLOCK                VARCHAR2(24),
   UNIT                 VARCHAR2(18),
   ROOM                 VARCHAR2(30),
   LOGOUT               NUMBER(1)                       default 0,
   RESIDENCETYPE        NUMBER(10),
   DISABILITYREASON		VARCHAR2(150)					,
   DISABILITYTYPE		NUMBER(10)                      ,
   HADDISABILITYCARD  NUMBER(1),
   DISABILITYCARDNO		VARCHAR2(90)				,
   DISABILITY			NUMBER(10),
   DISABILITYDATE       DATE,
   WORKPROFILE          NUMBER(10),
   SKILLPROFILE			NUMBER(10),
   GUARDIANNAME         VARCHAR2(60),

   FULLPINYIN           VARCHAR2(30),
   SIMPLEPINYIN         VARCHAR2(10),
   REMARK               VARCHAR2(600),
   CREATEUSER           VARCHAR2(32)                    not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                            not null,
   UPDATEDATE           DATE,
   ISEMPHASIS          NUMBER(1) default 0,
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   attentionExtent      NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   constraint PKDM_HANDICAPPEDSTEMP primary key (id),
   constraint FKDM_HANDICAPPEDSTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_handicappeds_Temp is
'残疾人';

comment on column DM_handicappeds_Temp.ID is
'残疾人ID';

comment on column DM_handicappeds_Temp.ORGID is
'所属网格 ';
comment on column DM_handicappeds_Temp.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_handicappeds_Temp.GENDER is
'性别 ';
comment on column DM_handicappeds_Temp.IDCARDNO is
'身份证ID';
comment on column DM_handicappeds_Temp.NAME is
'姓名';
comment on column DM_handicappeds_Temp.USEDNAME is
'姓名';
comment on column DM_handicappeds_Temp.BIRTHDAY is
'出生日期';
comment on column DM_handicappeds_Temp.PROVINCE
  is '户籍地-省';
comment on column DM_handicappeds_Temp.CITY
  is '户籍地-市';
comment on column DM_handicappeds_Temp.DISTRICT
  is '户籍地-县';
comment on column DM_handicappeds_Temp.NATIVEPLACEADDRESS
  is '户籍地详细地址';
comment on column DM_handicappeds_Temp.OTHERADDRESS
  is '临时居所';
  comment on column DM_handicappeds_Temp.CURRENTADDRESS
  is '住址';
  comment on column DM_handicappeds_Temp.TELEPHONE is
'固定电话';
comment on column DM_handicappeds_Temp.MOBILENUMBER is
'手机号码 ';
comment on column DM_handicappeds_Temp.POLITICALBACKGROUND
  is '政治面貌';
  comment on column DM_handicappeds_Temp.MARITALSTATE
  is '婚姻状况';
comment on column DM_handicappeds_Temp.STATURE
  is '身高';
 comment on column DM_handicappeds_Temp.NATION is
'民族';
comment on column DM_handicappeds_Temp.FAITH
  is '宗教信仰';
comment on column DM_handicappeds_Temp.BLOODTYPE
  is '血型';
  comment on column DM_handicappeds_Temp.SCHOOLING
  is '受教育程度';
  comment on column DM_handicappeds_Temp.CAREER
  is '职业';
comment on column DM_handicappeds_Temp.EMAIL
  is '邮件';
comment on column DM_handicappeds_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_handicappeds_Temp.ISDEATH
  is '是否死亡';
comment on column DM_handicappeds_Temp.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_handicappeds_Temp.IMGURL
  is '头像';
  comment on column DM_handicappeds_Temp.COMMUNITY
  is '小区';
comment on column DM_handicappeds_Temp.BLOCK
  is '幢';
comment on column DM_handicappeds_Temp.UNIT
  is '单元';
comment on column DM_handicappeds_Temp.ROOM
  is '室';
comment on column DM_handicappeds_Temp.LOGOUT is
'是否注销';
comment on column DM_handicappeds_Temp.RESIDENCETYPE
  is '户口类别';
  comment on column DM_handicappeds_Temp.CREATEUSER
  is '创建用户';
comment on column DM_handicappeds_Temp.CREATEDATE
  is '创建时间';
comment on column DM_handicappeds_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_handicappeds_Temp.UPDATEDATE
  is '修改时间';
comment on column DM_handicappeds_Temp.DISABILITYREASON
  is '残疾原因';
comment on column DM_handicappeds_Temp.DISABILITYTYPE
  is '残疾类别';
 comment on column DM_handicappeds_Temp.HADDISABILITYCARD
  is '是否有残疾证';
 comment on column DM_handicappeds_Temp.DISABILITYDATE
  is '残疾时间';
comment on column DM_handicappeds_Temp.GUARDIANNAME is
'监护人名字';
comment on column DM_handicappeds_Temp.DISABILITYCARDNO is
'残疾证号';
comment on column DM_handicappeds_Temp.DISABILITY is
'残疾状况';
comment on column DM_handicappeds_Temp.SKILLPROFILE is
'技能特长';
comment on column DM_handicappeds_Temp.WORKPROFILE is
'劳动能力';
comment on column DM_handicappeds_Temp.FULLPINYIN is
'全拼';
comment on column DM_handicappeds_Temp.SIMPLEPINYIN is
'简拼';
comment on column DM_handicappeds_Temp.REMARK is
'备注 ';
comment on column DM_handicappeds_Temp.ISEMPHASIS
  is '是否注销';
comment on column DM_handicappeds_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_handicappeds_Temp.noHouseReason is
'无房原因';

comment on column DM_handicappeds_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_handicappeds_Temp.claimDate is
'认领日期';

comment on column DM_handicappeds_Temp.claimUserName is
'认领人用户名';

comment on column DM_handicappeds_Temp.claimUserId is
'认领人Id';

comment on column DM_handicappeds_Temp.claimOrgId is
'认领人网格';

comment on column DM_handicappeds_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_handicappeds_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_handicappeds_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_handicappeds_Temp.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_handicappeds_Temp.importDate
  is '导入时间';
comment on column DM_handicappeds_Temp.actualPopulationType is
'实口类型';
comment on column DM_handicappeds_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_handicappeds_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_handicappeds_Temp.districtOrgId is
'导入时的到县区的组织机构';

create sequence s_DM_optimalObjects_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_optimalObjects_Temp        数据管理_优抚对象                                       */
/*==============================================================*/
create table DM_optimalObjects_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      ,
   optimalCardNo        VARCHAR2(90),
   optimalCardType      NUMBER(10),
   laborAbility      	NUMBER(10),
   abilityLiving      	NUMBER(10),
   employmentSituation  NUMBER(10),
   supportSituation     NUMBER(10),
   monthLivingExpenses  NUMBER(10)                      default 0,
   gender               NUMBER(10)                      ,
   workUnit             VARCHAR2(150),
   province             VARCHAR2(30),
   city                 VARCHAR2(30),
   district             VARCHAR2(30),
   nativePlaceAddress   VARCHAR2(150),
   currentAddress       VARCHAR2(150),
   name                 VARCHAR2(60)                    not null,
   usedName		        VARCHAR2(60),
   idCardNo             VARCHAR2(60)                    not null,
   telephone            VARCHAR2(80),
   mobileNumber         VARCHAR2(50),
   orgInternalCode      VARCHAR2(32)                   ,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   remark               VARCHAR2(900),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   birthday             DATE,
   ferretOutDate        DATE,
   drugfristDate        DATE,
   imgUrl               VARCHAR2(300),
   email                VARCHAR2(150),
   isEmphasis           NUMBER(1)                      default 0,
   isDeath              NUMBER(1)                      default 0,
   currentAddressType   NUMBER(10),
   community            VARCHAR2(150),
   block 				VARCHAR2(150),
   unit 				VARCHAR2(150),
   room 				VARCHAR2(150),
   nation               NUMBER(10),
   politicalBackground  NUMBER(10),
   schooling			NUMBER(10),
   career               NUMBER(10),
   maritalState         NUMBER(10),
   bloodType            NUMBER(10),
   faith                NUMBER(10),
   residenceType        NUMBER(10),
   otherAddress         VARCHAR2(150),
   stature              NUMBER(10),
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   attentionExtent      NUMBER(10),
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   importDate   DATE,
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   actualPopulationType VARCHAR2(50),
   districtOrgId				  NUMBER(10),
   constraint PKDM_OPTIMALOBJECTSTEMP primary key (id),
   constraint FKDM_OPTIMALOBJECTSTEMPORG foreign key (orgId)
         references organizations (id)
);
comment on table DM_optimalObjects_Temp is
'优抚对象信息';

comment on column DM_optimalObjects_Temp.id is
'人员id';

comment on column DM_optimalObjects_Temp.orgId is
'所属网格';

comment on column DM_optimalObjects_Temp.optimalCardNo is
'优待证号';

comment on column DM_optimalObjects_Temp.optimalCardType is
'优抚类型';

comment on column DM_optimalObjects_Temp.laborAbility is
'劳动能力';

comment on column DM_optimalObjects_Temp.abilityLiving is
'生活能力';

comment on column DM_optimalObjects_Temp.employmentSituation is
'就业情况';

comment on column DM_optimalObjects_Temp.supportSituation is
'供养情况';

comment on column DM_optimalObjects_Temp.monthLivingExpenses is
'月生活费';

comment on column DM_optimalObjects_Temp.gender is
'性别';

comment on column DM_optimalObjects_Temp.workUnit is
'工作单位或就读学校';

comment on column DM_optimalObjects_Temp.province is
'省';

comment on column DM_optimalObjects_Temp.city is
'市';

comment on column DM_optimalObjects_Temp.district is
'区县';

comment on column DM_optimalObjects_Temp.nativePlaceAddress is
'户籍地详址';

comment on column DM_optimalObjects_Temp.currentAddress is
'现在居住地';

comment on column DM_optimalObjects_Temp.name is
'优抚对象姓名';

comment on column DM_optimalObjects_Temp.usedName is
'曾用名';

comment on column DM_optimalObjects_Temp.idCardNo is
'身份证号码';

comment on column DM_optimalObjects_Temp.telephone is
'固定电话';

comment on column DM_optimalObjects_Temp.mobileNumber is
'手机号码';

comment on column DM_optimalObjects_Temp.orgInternalCode is
'所属责任区编号';

comment on column DM_optimalObjects_Temp.fullPinyin is
'全拼';

comment on column DM_optimalObjects_Temp.simplePinyin is
'简拼';

comment on column DM_optimalObjects_Temp.remark is
'备注';

comment on column DM_optimalObjects_Temp.createUser is
'创建用户';

comment on column DM_optimalObjects_Temp.updateUser is
'修改用户';

comment on column DM_optimalObjects_Temp.createDate is
'创建日期';

comment on column DM_optimalObjects_Temp.updateDate is
'修改时间';

comment on column DM_optimalObjects_Temp.birthday is
'出生日期';

comment on column DM_optimalObjects_Temp.ferretOutDate is
'查获日期';

comment on column DM_optimalObjects_Temp.isEmphasis is
'是否关注';

comment on column DM_optimalObjects_Temp.isDeath is
'是否死亡';

comment on column DM_optimalObjects_Temp.imgUrl is
'图片链接地址';

comment on column DM_optimalObjects_Temp.email is
'电子邮箱';

comment on column DM_optimalObjects_Temp.currentAddressType is
'常住地址类型';

comment on column DM_optimalObjects_Temp.community is
'常住地址商品房 小区';

comment on column DM_optimalObjects_Temp.block is
'常住地址商品房 幢';

comment on column DM_optimalObjects_Temp.unit is
'常住地址商品房 单元';

comment on column DM_optimalObjects_Temp.room is
'常住地址商品房  室';

comment on column DM_optimalObjects_Temp.nation is
'民族';

comment on column DM_optimalObjects_Temp.politicalBackground is
'政治面貌';

comment on column DM_optimalObjects_Temp.schooling is
'文化程度';

comment on column DM_optimalObjects_Temp.career is
'职业';

comment on column DM_optimalObjects_Temp.maritalState is
'婚姻状况';

comment on column DM_optimalObjects_Temp.bloodType is
'血型';

comment on column DM_optimalObjects_Temp.faith is
'宗教信仰';

comment on column DM_optimalObjects_Temp.otherAddress is
'临时居所';

comment on column DM_optimalObjects_Temp.residenceType  is
'户口类别';

comment on column DM_optimalObjects_Temp.stature is
'身高';
comment on column DM_optimalObjects_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_optimalObjects_Temp.noHouseReason is
'无房原因';
comment on column DM_optimalObjects_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_optimalObjects_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_optimalObjects_Temp.claimDate is
'认领日期';

comment on column DM_optimalObjects_Temp.claimUserName is
'认领人用户名';

comment on column DM_optimalObjects_Temp.claimUserId is
'认领人Id';

comment on column DM_optimalObjects_Temp.claimOrgId is
'认领人网格';

comment on column DM_optimalObjects_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_optimalObjects_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_optimalObjects_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_optimalObjects_Temp.importDate
  is '导入时间';
comment on column DM_optimalObjects_Temp.actualPopulationType is
'实口类型';
comment on column DM_optimalObjects_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_optimalObjects_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_optimalObjects_Temp.districtOrgId is
'导入时的到县区的组织机构';

-- Create sequence
create sequence S_DM_AIDNEEDPOPULATION_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: DM_AIDNEEDPOPULATION_TEMP(实体表无s)       数据管理_需救助人员                              */
/*==============================================================*/
create table DM_AIDNEEDPOPULATION_TEMP
(
  ID                  NUMBER(10) not null,
  ORGID               NUMBER(10),
  ORGINTERNALCODE     VARCHAR2(32),
  GENDER              NUMBER(10),
  IDCARDNO            VARCHAR2(60) not null,
  NAME                VARCHAR2(60) not null,
  USEDNAME            VARCHAR2(60),
  BIRTHDAY            DATE,
  PROVINCE            VARCHAR2(60),
  CITY                VARCHAR2(60),
  DISTRICT            VARCHAR2(60),
  NATIVEPLACEADDRESS  VARCHAR2(150),
  CURRENTADDRESS      VARCHAR2(150),
  MOBILENUMBER        VARCHAR2(50),
  TELEPHONE           VARCHAR2(80),
  POLITICALBACKGROUND NUMBER(10),
  MARITALSTATE        NUMBER(10),
  STATURE             NUMBER(10),
  NATION              NUMBER(10),
  FAITH               NUMBER(10),
  BLOODTYPE           NUMBER(10),
  SCHOOLING           NUMBER(10),
  CAREER              NUMBER(10),
  EMAIL               VARCHAR2(150),
  WORKUNIT            VARCHAR2(150),
  ISDEATH             NUMBER(1) default 0,
  OTHERADDRESS        VARCHAR2(150),
  IMGURL              VARCHAR2(300),
  CURRENTADDRESSTYPE  NUMBER(10),
  COMMUNITY           VARCHAR2(60),
  BLOCK               VARCHAR2(24),
  UNIT                VARCHAR2(18),
  ROOM                VARCHAR2(30),
  ISEMPHASIS          NUMBER(1) default 0,
  AIDREASON           NUMBER(10),
  ISLOWHOUSEHOLDS     NUMBER(1) default 0,
  DIFFICULTCARDNO     VARCHAR2(90),
  DIFFICULTTYPE       NUMBER(10),
  SUBSIDIESAMOUNT     NUMBER(10,4),
  SUBSIDIESGETTIME    DATE,
  SUBSIDIESSTARTTIME  DATE,
  SUBSIDIESPOPULATION NUMBER(3),
  SUBSIDIESAREA       VARCHAR2(150),
  REMARK              VARCHAR2(900),
  CREATEUSER          VARCHAR2(32) not null,
  UPDATEUSER          VARCHAR2(32),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  FULLPINYIN          VARCHAR2(30),
  SIMPLEPINYIN        VARCHAR2(10),
  ISHAVEHOUSE         NUMBER(1) default 0,
  NOHOUSEREASON       VARCHAR2(800),
  ISEMPHASISREASON    VARCHAR2(300),
  ISEMPHASISDATE      DATE,
  ATTENTIONEXTENT     NUMBER(10),
  CLAIMSTATE          NUMBER(10) default 0,
  CLAIMDATE           DATE,
  CLAIMUSERNAME       VARCHAR2(4000),
  CLAIMUSERID         NUMBER(10),
  CLAIMORGID          NUMBER(10),
  IMPORTDEPARTMENTID  NUMBER(10) not null,
  OLDORGID            NUMBER(10),
  INID                NUMBER(10),
  importDate   DATE,
  dispose number(10) default 0,
  claimAvailable number(10) default 0,
  actualPopulationType VARCHAR2(50),
  districtOrgId				  NUMBER(10),
  constraint PKDM_AIDNEEDPOPULATIONTEMP primary key (ID),
  constraint FKDM_AIDNEEDPOPULATIONTEMPORG foreign key (ORGID)
  references ORGANIZATIONS (ID)
);

comment on column DM_AIDNEEDPOPULATION_TEMP.ORGID
  is '所属网格';
comment on column DM_AIDNEEDPOPULATION_TEMP.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_AIDNEEDPOPULATION_TEMP.GENDER
  is '性别';
comment on column DM_AIDNEEDPOPULATION_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_AIDNEEDPOPULATION_TEMP.NAME
  is '姓名';
comment on column DM_AIDNEEDPOPULATION_TEMP.USEDNAME
  is '曾用名';
comment on column DM_AIDNEEDPOPULATION_TEMP.BIRTHDAY
  is '出生日期';
comment on column DM_AIDNEEDPOPULATION_TEMP.PROVINCE
  is '省';
comment on column DM_AIDNEEDPOPULATION_TEMP.CITY
  is '市';
comment on column DM_AIDNEEDPOPULATION_TEMP.DISTRICT
  is '区县';
comment on column DM_AIDNEEDPOPULATION_TEMP.NATIVEPLACEADDRESS
  is '户籍地详址';
comment on column DM_AIDNEEDPOPULATION_TEMP.CURRENTADDRESS
  is '常住地址';
comment on column DM_AIDNEEDPOPULATION_TEMP.MOBILENUMBER
  is '手机号码';
comment on column DM_AIDNEEDPOPULATION_TEMP.TELEPHONE
  is '固定电话';
comment on column DM_AIDNEEDPOPULATION_TEMP.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_AIDNEEDPOPULATION_TEMP.MARITALSTATE
  is '婚姻状况';
comment on column DM_AIDNEEDPOPULATION_TEMP.STATURE
  is '身高';
comment on column DM_AIDNEEDPOPULATION_TEMP.NATION
  is '民族';
comment on column DM_AIDNEEDPOPULATION_TEMP.FAITH
  is '宗教信仰';
comment on column DM_AIDNEEDPOPULATION_TEMP.BLOODTYPE
  is '血型';
comment on column DM_AIDNEEDPOPULATION_TEMP.SCHOOLING
  is '文化程度';
comment on column DM_AIDNEEDPOPULATION_TEMP.CAREER
  is '职业';
comment on column DM_AIDNEEDPOPULATION_TEMP.EMAIL
  is '电子邮箱';
comment on column DM_AIDNEEDPOPULATION_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISDEATH
  is '是否死亡';
comment on column DM_AIDNEEDPOPULATION_TEMP.OTHERADDRESS
  is '临时居所';
comment on column DM_AIDNEEDPOPULATION_TEMP.IMGURL
  is '头像路径';
comment on column DM_AIDNEEDPOPULATION_TEMP.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_AIDNEEDPOPULATION_TEMP.COMMUNITY
  is '常住地址商品房 小区';
comment on column DM_AIDNEEDPOPULATION_TEMP.BLOCK
  is '常住地址商品房 幢';
comment on column DM_AIDNEEDPOPULATION_TEMP.UNIT
  is '常住地址商品房 单元';
comment on column DM_AIDNEEDPOPULATION_TEMP.ROOM
  is '常住地址商品房  室';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISEMPHASIS
  is '是否关注';
comment on column DM_AIDNEEDPOPULATION_TEMP.AIDREASON
  is '救助原因';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISLOWHOUSEHOLDS
  is '是否低保户';
comment on column DM_AIDNEEDPOPULATION_TEMP.DIFFICULTCARDNO
  is '困难证号';
comment on column DM_AIDNEEDPOPULATION_TEMP.DIFFICULTTYPE
  is '困难类型';
comment on column DM_AIDNEEDPOPULATION_TEMP.SUBSIDIESAMOUNT
  is '领取金额';
comment on column DM_AIDNEEDPOPULATION_TEMP.SUBSIDIESGETTIME
  is '领取时间';
comment on column DM_AIDNEEDPOPULATION_TEMP.SUBSIDIESSTARTTIME
  is '享受起始时间';
comment on column DM_AIDNEEDPOPULATION_TEMP.SUBSIDIESPOPULATION
  is '享受人数';
comment on column DM_AIDNEEDPOPULATION_TEMP.SUBSIDIESAREA
  is '享受地区';
comment on column DM_AIDNEEDPOPULATION_TEMP.REMARK
  is '备注';
comment on column DM_AIDNEEDPOPULATION_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_AIDNEEDPOPULATION_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_AIDNEEDPOPULATION_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_AIDNEEDPOPULATION_TEMP.UPDATEDATE
  is '修改时间';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISHAVEHOUSE
  is '是否有房屋';
comment on column DM_AIDNEEDPOPULATION_TEMP.NOHOUSEREASON
  is '无房原因';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISEMPHASISREASON
  is '关注原因';
comment on column DM_AIDNEEDPOPULATION_TEMP.ISEMPHASISDATE
  is '关注日期';
comment on column DM_AIDNEEDPOPULATION_TEMP.ATTENTIONEXTENT
  is '关注程度：1.一般；2.中等；3.严重';
comment on column DM_AIDNEEDPOPULATION_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_AIDNEEDPOPULATION_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_AIDNEEDPOPULATION_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_AIDNEEDPOPULATION_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_AIDNEEDPOPULATION_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_AIDNEEDPOPULATION_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_AIDNEEDPOPULATION_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_AIDNEEDPOPULATION_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_AIDNEEDPOPULATION_TEMP.importDate
  is '导入时间';
comment on column DM_AIDNEEDPOPULATION_TEMP.actualPopulationType is
'实口类型';
comment on column DM_AIDNEEDPOPULATION_TEMP.dispose is '是否经过处理(0:否)';
comment on column DM_AIDNEEDPOPULATION_TEMP.claimAvailable is '是否可以认领(0:否)'; 
comment on column DM_AIDNEEDPOPULATION_TEMP.districtOrgId is
'导入时的到县区的组织机构';

create sequence s_DM_unemployedPeople_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 9999999999;
/*==============================================================*/
/* Table:DM_unemployedPeople_Temp（实体表无s）数据管理_失业人员                                    */
/*==============================================================*/
create table DM_unemployedPeople_Temp (
  ID                  NUMBER(10) not null,
  NAME                VARCHAR2(60) not null,
  IDCARDNO            VARCHAR2(18) not null,
  GENDER              NUMBER(10) ,
  BIRTHDAY            DATE,
  CURRENTADDRESS      VARCHAR2(150),
  TELEPHONE           VARCHAR2(80),
  MOBILENUMBER        VARCHAR2(11),
  ORGID               NUMBER(10),
  SCHOOLING           NUMBER(10),
  REMARK              VARCHAR2(600),
  FULLPINYIN          VARCHAR2(30),
  SIMPLEPINYIN        VARCHAR2(10),
  ORGINTERNALCODE     VARCHAR2(32),
  CREATEUSER          VARCHAR2(32) not null,
  CREATEDATE          DATE not null,
  UPDATEUSER          VARCHAR2(32),
  UPDATEDATE          DATE,
  USEDNAME            VARCHAR2(60),
  NATION              NUMBER(10),
  POLITICALBACKGROUND NUMBER(10),
  CAREER              NUMBER(10),
  WORKUNIT            VARCHAR2(150),
  MARITALSTATE        NUMBER(10),
  ISDEATH             NUMBER(1) default 0,
  STATURE             NUMBER(10) default 0,
  BLOODTYPE           NUMBER(10),
  FAITH               NUMBER(10),
  EMAIL               VARCHAR2(60),
  RESIDENCETYPE       NUMBER(10),
  IMGURL              VARCHAR2(300),
  PROVINCE            VARCHAR2(60),
  CITY                VARCHAR2(60),
  DISTRICT            VARCHAR2(60),
  NATIVEPLACEADDRESS  VARCHAR2(150),
  OTHERADDRESS        VARCHAR2(150),
  CURRENTADDRESSTYPE  NUMBER(10),
  COMMUNITY           VARCHAR2(30),
  BLOCK               VARCHAR2(30),
  UNIT                VARCHAR2(30),
  ROOM                VARCHAR2(30),
  ISEMPHASIS          NUMBER(1) default 0,
  unemployedPeopleTempType  NUMBER(10),
  registerNumber      VARCHAR2(60),
  originalWorkUnit    VARCHAR2(60),
  originalWorkUnitType           VARCHAR2(60),
  enterWorkDate       DATE,
  unemploymentDate    DATE,
  technologyLevel     NUMBER(10),
  specialtySkills     VARCHAR2(150),
  unemploymentReason  NUMBER(10),
  TITLE               VARCHAR2(30),
  participatedPrograms varchar2(600),
  isHaveHouse      	NUMBER(1)  						default 0,
  noHouseReason	    VARCHAR2(800),
  isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   attentionExtent      NUMBER(10),
   
   claimState             NUMBER(10) default 0,
   claimDate              Date,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
	importDate   DATE,
	dispose number(10) default 0,
	claimAvailable number(10) default 0,
	actualPopulationType VARCHAR2(50),  
	districtOrgId				  NUMBER(10),
	constraint PKDM_UNEMPLOYEDPEOPLETEMP primary key (id),
	constraint FKDM_UNEMPLOYEDPEOPLETEMPORG foreign key (orgId)
		references organizations (id)
);

comment on table DM_unemployedPeople_Temp
  is '失业人员';
-- Add comments to the columns
comment on column DM_unemployedPeople_Temp.ID
  is 'ID';
comment on column DM_unemployedPeople_Temp.NAME
  is '姓名';
comment on column DM_unemployedPeople_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_unemployedPeople_Temp.GENDER
  is '性别';
comment on column DM_unemployedPeople_Temp.BIRTHDAY
  is '出生日期';
comment on column DM_unemployedPeople_Temp.CURRENTADDRESS
  is '常住地址';
comment on column DM_unemployedPeople_Temp.TELEPHONE
  is '固定电话';
comment on column DM_unemployedPeople_Temp.MOBILENUMBER
  is '手机号码';
comment on column DM_unemployedPeople_Temp.ORGID
  is '所属网格';
comment on column DM_unemployedPeople_Temp.SCHOOLING
  is '文化程度';
comment on column DM_unemployedPeople_Temp.REMARK
  is '备注';
comment on column DM_unemployedPeople_Temp.FULLPINYIN
  is '全拼';
comment on column DM_unemployedPeople_Temp.SIMPLEPINYIN
  is '简拼';
comment on column DM_unemployedPeople_Temp.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_unemployedPeople_Temp.CREATEUSER
  is '创建用户';
comment on column DM_unemployedPeople_Temp.CREATEDATE
  is '创建时间';
comment on column DM_unemployedPeople_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_unemployedPeople_Temp.UPDATEDATE
  is '修改时间';
comment on column DM_unemployedPeople_Temp.USEDNAME
  is '别名';
comment on column DM_unemployedPeople_Temp.NATION
  is '民族';
comment on column DM_unemployedPeople_Temp.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_unemployedPeople_Temp.CAREER
  is '职业';
comment on column DM_unemployedPeople_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_unemployedPeople_Temp.MARITALSTATE
  is '婚姻状况';
comment on column DM_unemployedPeople_Temp.ISDEATH
  is '是否死亡';
comment on column DM_unemployedPeople_Temp.STATURE
  is '身高';
comment on column DM_unemployedPeople_Temp.BLOODTYPE
  is '血型';
comment on column DM_unemployedPeople_Temp.FAITH
  is '宗教信仰';
comment on column DM_unemployedPeople_Temp.EMAIL
  is '邮件';
comment on column DM_unemployedPeople_Temp.RESIDENCETYPE
  is '户口类别';
comment on column DM_unemployedPeople_Temp.IMGURL
  is '头像';
comment on column DM_unemployedPeople_Temp.PROVINCE
  is '户籍地-省';
comment on column DM_unemployedPeople_Temp.CITY
  is '户籍地-市';
comment on column DM_unemployedPeople_Temp.DISTRICT
  is '户籍地-县';
comment on column DM_unemployedPeople_Temp.NATIVEPLACEADDRESS
  is '户籍地详细地址';
comment on column DM_unemployedPeople_Temp.OTHERADDRESS
  is '临时居所';
comment on column DM_unemployedPeople_Temp.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_unemployedPeople_Temp.COMMUNITY
  is '小区';
comment on column DM_unemployedPeople_Temp.BLOCK
  is '幢';
comment on column DM_unemployedPeople_Temp.UNIT
  is '单元';
comment on column DM_unemployedPeople_Temp.ROOM
  is '室';
comment on column DM_unemployedPeople_Temp.ISEMPHASIS
  is '是否注销';

comment on column DM_unemployedPeople_Temp.unemployedPeopleTempType
  is '人员类型';
comment on column DM_unemployedPeople_Temp.REGISTERNUMBER
  is '就业/失业登记证号';
comment on column DM_unemployedPeople_Temp.ORIGINALWORKUNIT
  is '原工作单位';
comment on column DM_unemployedPeople_Temp.ORIGINALWORKUNITTYPE
  is '原工作单位类型';
comment on column DM_unemployedPeople_Temp.ENTERWORKDATE
  is '参加工作时间';
comment on column DM_unemployedPeople_Temp.UNEMPLOYMENTDATE
  is '失业时间';
comment on column DM_unemployedPeople_Temp.TECHNOLOGYLEVEL
  is '技术等级';
comment on column DM_unemployedPeople_Temp.specialtySkills
  is '特长技能';

comment on column DM_unemployedPeople_Temp.unemploymentReason
  is '失业原因';

comment on column DM_unemployedPeople_Temp.TITLE
  is '职称';
comment on column DM_unemployedPeople_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_unemployedPeople_Temp.noHouseReason is
'无房原因';
comment on column DM_unemployedPeople_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_unemployedPeople_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_unemployedPeople_Temp.claimDate is
'认领日期';

comment on column DM_unemployedPeople_Temp.claimUserName is
'认领人用户名';

comment on column DM_unemployedPeople_Temp.claimUserId is
'认领人Id';

comment on column DM_unemployedPeople_Temp.claimOrgId is
'认领人网格';

comment on column DM_unemployedPeople_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_unemployedPeople_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_unemployedPeople_Temp.inId is
'认领时插入到原库中数据id';
comment on column DM_unemployedPeople_Temp.importDate
  is '导入时间';
comment on column DM_unemployedPeople_Temp.actualPopulationType is
'实口类型';
comment on column DM_unemployedPeople_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_unemployedPeople_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_unemployedPeople_Temp.districtOrgId is
'导入时的到县区的组织机构';

create sequence s_DM_NURTURESWOMEN_TEMP
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 99999999999;
/*==============================================================*/
/* Table: DM_NURTURESWOMEN_TEMP（实体表无s）     数据管理_育龄妇女                                   */
/*==============================================================*/

  create table DM_NURTURESWOMEN_TEMP(
  ID                       NUMBER(10) not null,
  ORGID                    NUMBER(10) ,
  ORGINTERNALCODE          VARCHAR2(50),
  GENDER                   NUMBER(10) ,
  IDCARDNO                 VARCHAR2(60) not null,
  NAME                     VARCHAR2(60) not null,
  USEDNAME                 VARCHAR2(60),
  BIRTHDAY                 DATE,
  PROVINCE                 VARCHAR2(60) ,
  CITY                     VARCHAR2(60) ,
  DISTRICT                 VARCHAR2(60) ,
  NATIVEPLACEADDRESS       VARCHAR2(150),
  CURRENTADDRESS           VARCHAR2(150) ,
  OTHERADDRESS             VARCHAR2(150),
  CURRENTADDRESSTYPE       NUMBER(10),
  COMMUNITY                VARCHAR2(60),
  BLOCK                    VARCHAR2(24),
  UNIT                     VARCHAR2(18),
  ROOM                     VARCHAR2(30),
  MOBILENUMBER             VARCHAR2(50),
  TELEPHONE                VARCHAR2(80),
  CAREER                   NUMBER(10),
  POLITICALBACKGROUND      NUMBER(10),
  MARITALSTATE             NUMBER(10),
  RESIDENCETYPE            NUMBER(10),
  STATURE                  NUMBER(10),
  NATION                   NUMBER(10),
  FAITH                    NUMBER(10),
  SCHOOLING                 NUMBER(10),
  BLOODTYPE                NUMBER(10),
  EMAIL                    VARCHAR2(150),
  WORKUNIT                 VARCHAR2(150),
  REMARK                   VARCHAR2(900),
  ISDEATH                  NUMBER(1) default 0,
  ISEMPHASIS               NUMBER(1) default 0,
  FIRSTMARRIAGETIME        DATE,
  MARRIAGEREGISTRATIONTIME DATE,
  LICENSESITUATION         NUMBER(10),
  MARRIAGEORDIVORCETIME    DATE,
  FERTILITYSERVICESNO      VARCHAR2(150),
  LICENSETIME              DATE,
  CONTRACEPTIVEMETHOD      VARCHAR2(150),
  CONTRACEPTIVETIME        DATE,
  BOYNUMBER                NUMBER(2),
  GIRLNUMBER               NUMBER(2),
  ONECHILDOFCOUPLE         NUMBER(10),
  MANIDCARDNO              VARCHAR2(60),
  MANNAME                  VARCHAR2(60),
  MANMARITALSTATE          NUMBER(10),
  MANFIRSTMARRIAGETIME     DATE,
  MANMOBILENUMBER          VARCHAR2(50),
  MANTELEPHONE             VARCHAR2(80),
  MANBIRTHDAY              DATE,
  MANNATION                NUMBER(10),
  MANPOLITICALBACKGROUND   NUMBER(10),
  MANRESIDENCETYPE         NUMBER(10),
  MANSCHOOLING             NUMBER(10),
  MANCAREER                NUMBER(10),
  MANWORKUNIT              VARCHAR2(150),
  MANPROVINCE              VARCHAR2(60),
  MANCITY                  VARCHAR2(60),
  MANDISTRICT              VARCHAR2(60),
  MANNATIVEPLACEADDRESS    VARCHAR2(60),
  MANCURRENTADDRESS        VARCHAR2(150),
  MANCURRENTADDRESSTYPE    NUMBER(10),
  MANCOMMUNITY             VARCHAR2(60),
  MANBLOCK                 VARCHAR2(24),
  MANUNIT                  VARCHAR2(18),
  MANROOM                  VARCHAR2(30),
  MANREMARK                VARCHAR2(900),
  FULLPINYIN               VARCHAR2(30),
  SIMPLEPINYIN             VARCHAR2(10),
  CREATEUSER               VARCHAR2(60) not null,
  IMGURL                   VARCHAR2(300),
  UPDATEUSER               VARCHAR2(60),
  CREATEDATE               DATE not null,
  UPDATEDATE               DATE,
  SINGLECHILDCDISSUETIME   DATE,
  SINGLECHILDCARDNO        VARCHAR2(30),
  CERTIFIEDUNIT            VARCHAR2(180),
  ISUNMARRIEDPREGNANT      NUMBER(1),
  ISLEVIED                 NUMBER(1),
  ISMATERNITYCARD   NUMBER(1),
  MATERNITYCARDUNIT VARCHAR2(180),
  MATERNITYCARDNO   VARCHAR2(90),
  MATERNITYCARDISSUETIME         DATE,
  MATERNITYCARDVALIDITYTIME      DATE,
  MATERNITYCARDCHECKTIME            date,
  isHaveHouse      	NUMBER(1)  						default 0,
  noHouseReason	    VARCHAR2(800),
  MATERNITYCARDREMARK    VARCHAR2(900),
  isEmphasisReason     VARCHAR2(300),
  isEmphasisDate       DATE,
  attentionExtent      NUMBER(10),
  CLAIMSTATE          NUMBER(10) default 0,
  CLAIMDATE           DATE,
  CLAIMUSERNAME       VARCHAR2(4000),
  CLAIMUSERID         NUMBER(10),
  CLAIMORGID          NUMBER(10),
  IMPORTDEPARTMENTID  NUMBER(10) not null,
  OLDORGID            NUMBER(10),
  INID                NUMBER(15),
	importDate   DATE,
	dispose number(10) default 0,
	claimAvailable number(10) default 0,
	actualPopulationType VARCHAR2(50),
	districtOrgId				  NUMBER(10),
  constraint PKDM_NURTURESWOMENTEMP primary key (ID),
  constraint FKDM_NURTURESWOMENTEMPORG foreign key (ORGID)
  references ORGANIZATIONS (ID)
);

comment on table DM_NURTURESWOMEN_TEMP
  is '育妇对象表';
comment on column DM_NURTURESWOMEN_TEMP.ORGID
  is '所属网格';
comment on column DM_NURTURESWOMEN_TEMP.ORGINTERNALCODE
  is '部门内部编号';
comment on column DM_NURTURESWOMEN_TEMP.GENDER
  is '性别';
comment on column DM_NURTURESWOMEN_TEMP.IDCARDNO
  is '身份证id';
comment on column DM_NURTURESWOMEN_TEMP.NAME
  is '姓名';
comment on column DM_NURTURESWOMEN_TEMP.USEDNAME
  is '曾用名';
comment on column DM_NURTURESWOMEN_TEMP.BIRTHDAY
  is '出生日期';
comment on column DM_NURTURESWOMEN_TEMP.PROVINCE
  is '省';
comment on column DM_NURTURESWOMEN_TEMP.CITY
  is '市';
comment on column DM_NURTURESWOMEN_TEMP.DISTRICT
  is '区县';
comment on column DM_NURTURESWOMEN_TEMP.NATIVEPLACEADDRESS
  is '户籍地详址';
comment on column DM_NURTURESWOMEN_TEMP.CURRENTADDRESS
  is '常住地址';
comment on column DM_NURTURESWOMEN_TEMP.OTHERADDRESS
  is '临时居所';
comment on column DM_NURTURESWOMEN_TEMP.CURRENTADDRESSTYPE
  is '常住地址类型';
comment on column DM_NURTURESWOMEN_TEMP.COMMUNITY
  is '常住地址商品房小区';
comment on column DM_NURTURESWOMEN_TEMP.BLOCK
  is '常住地址商品房幢';
comment on column DM_NURTURESWOMEN_TEMP.UNIT
  is '常住地址商品房单元';
comment on column DM_NURTURESWOMEN_TEMP.ROOM
  is '常住地址商品房室';
comment on column DM_NURTURESWOMEN_TEMP.MOBILENUMBER
  is '手机电话';
comment on column DM_NURTURESWOMEN_TEMP.TELEPHONE
  is '固定电话';
comment on column DM_NURTURESWOMEN_TEMP.CAREER
  is '职业';
comment on column DM_NURTURESWOMEN_TEMP.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_NURTURESWOMEN_TEMP.MARITALSTATE
  is '婚姻状况';
comment on column DM_NURTURESWOMEN_TEMP.RESIDENCETYPE
  is '户口类型';
comment on column DM_NURTURESWOMEN_TEMP.STATURE
  is '身高';
comment on column DM_NURTURESWOMEN_TEMP.NATION
  is '民族';
comment on column DM_NURTURESWOMEN_TEMP.FAITH
  is '宗教信仰';
comment on column DM_NURTURESWOMEN_TEMP.SCHOOLING
  is '文化程度';
comment on column DM_NURTURESWOMEN_TEMP.BLOODTYPE
  is '血型';
comment on column DM_NURTURESWOMEN_TEMP.EMAIL
  is '电子邮箱';
comment on column DM_NURTURESWOMEN_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_NURTURESWOMEN_TEMP.REMARK
  is '备注';
comment on column DM_NURTURESWOMEN_TEMP.ISDEATH
  is '是否死亡';
comment on column DM_NURTURESWOMEN_TEMP.ISEMPHASIS
  is '是否关注';
comment on column DM_NURTURESWOMEN_TEMP.FIRSTMARRIAGETIME
  is '初婚时间';
comment on column DM_NURTURESWOMEN_TEMP.MARRIAGEREGISTRATIONTIME
  is '离婚时间';
comment on column DM_NURTURESWOMEN_TEMP.LICENSESITUATION
  is '领证情况';
comment on column DM_NURTURESWOMEN_TEMP.MARRIAGEORDIVORCETIME
  is '最近再婚时间';
comment on column DM_NURTURESWOMEN_TEMP.FERTILITYSERVICESNO
  is '生育服务证号';
comment on column DM_NURTURESWOMEN_TEMP.LICENSETIME
  is '领证时间';
comment on column DM_NURTURESWOMEN_TEMP.CONTRACEPTIVEMETHOD
  is '避孕方法';
comment on column DM_NURTURESWOMEN_TEMP.CONTRACEPTIVETIME
  is '避孕起始时间';
comment on column DM_NURTURESWOMEN_TEMP.BOYNUMBER
  is '男孩数';
comment on column DM_NURTURESWOMEN_TEMP.GIRLNUMBER
  is '女孩数';
comment on column DM_NURTURESWOMEN_TEMP.ONECHILDOFCOUPLE
  is '夫妻双方独生子女情况';
comment on column DM_NURTURESWOMEN_TEMP.MANIDCARDNO
  is '丈夫身份证号码';
comment on column DM_NURTURESWOMEN_TEMP.MANNAME
  is '丈夫姓名';
comment on column DM_NURTURESWOMEN_TEMP.MANMARITALSTATE
  is '丈夫婚姻状况';
comment on column DM_NURTURESWOMEN_TEMP.MANFIRSTMARRIAGETIME
  is '丈夫初婚时间';
comment on column DM_NURTURESWOMEN_TEMP.MANMOBILENUMBER
  is '丈夫联系手机';
comment on column DM_NURTURESWOMEN_TEMP.MANTELEPHONE
  is '丈夫固定电话';
comment on column DM_NURTURESWOMEN_TEMP.MANBIRTHDAY
  is '丈夫出生日期';
comment on column DM_NURTURESWOMEN_TEMP.MANNATION
  is '丈夫民族';
comment on column DM_NURTURESWOMEN_TEMP.MANPOLITICALBACKGROUND
  is '丈夫政治面貌';
comment on column DM_NURTURESWOMEN_TEMP.MANRESIDENCETYPE
  is '丈夫户口类型';
comment on column DM_NURTURESWOMEN_TEMP.MANSCHOOLING
  is '丈夫文化程度';
comment on column DM_NURTURESWOMEN_TEMP.MANCAREER
  is '丈夫职业';
comment on column DM_NURTURESWOMEN_TEMP.MANWORKUNIT
  is '丈夫工作单位或就读学校';
comment on column DM_NURTURESWOMEN_TEMP.MANPROVINCE
  is '丈夫户籍地 省';
comment on column DM_NURTURESWOMEN_TEMP.MANCITY
  is '丈夫户籍地 市';
comment on column DM_NURTURESWOMEN_TEMP.MANDISTRICT
  is '丈夫户籍地 区县';
comment on column DM_NURTURESWOMEN_TEMP.MANNATIVEPLACEADDRESS
  is '丈夫户籍地详址';
comment on column DM_NURTURESWOMEN_TEMP.MANCURRENTADDRESS
  is '丈夫常住地址';
comment on column DM_NURTURESWOMEN_TEMP.MANCURRENTADDRESSTYPE
  is '丈夫常住地址类型';
comment on column DM_NURTURESWOMEN_TEMP.MANCOMMUNITY
  is '丈夫常住地址 商品房 小区';
comment on column DM_NURTURESWOMEN_TEMP.MANBLOCK
  is '丈夫常住地址 商品房 幢';
comment on column DM_NURTURESWOMEN_TEMP.MANUNIT
  is '丈夫常住地址 商品房  单元';
comment on column DM_NURTURESWOMEN_TEMP.MANROOM
  is '丈夫常住地址 商品房 室';
comment on column DM_NURTURESWOMEN_TEMP.MANREMARK
  is '丈夫备注';
comment on column DM_NURTURESWOMEN_TEMP.FULLPINYIN
  is '全拼';
comment on column DM_NURTURESWOMEN_TEMP.SIMPLEPINYIN
  is '简拼';
comment on column DM_NURTURESWOMEN_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_NURTURESWOMEN_TEMP.IMGURL
  is '头像路径';
comment on column DM_NURTURESWOMEN_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_NURTURESWOMEN_TEMP.CREATEDATE
  is '创建时间';
comment on column DM_NURTURESWOMEN_TEMP.UPDATEDATE
  is '修改时间';
  comment on column DM_NURTURESWOMEN_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_NURTURESWOMEN_TEMP.SINGLECHILDCARDNO
  is '独生子女证号';
comment on column DM_NURTURESWOMEN_TEMP.CERTIFIEDUNIT
  is '发证单位';
  comment on column DM_NURTURESWOMEN_TEMP.ISUNMARRIEDPREGNANT
  is '是否未婚先孕';
comment on column DM_NURTURESWOMEN_TEMP.ISLEVIED
  is '是否征收';
comment on column DM_NURTURESWOMEN_TEMP.ISMATERNITYCARD
  is '是否有婚育证';
comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDUNIT
  is '孕育证发证单位';
comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDNO
  is '证书编号';
comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDISSUETIME
  is '发放证书时间';
  comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDVALIDITYTIME
  is '孕育证有效期';
comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDCHECKTIME
  is '查验时间';
comment on column DM_NURTURESWOMEN_TEMP.MATERNITYCARDREMARK
  is '查验情况';
comment on column DM_NURTURESWOMEN_TEMP.isHaveHouse is
'是否有房屋';

comment on column DM_NURTURESWOMEN_TEMP.noHouseReason is
'无房原因';
comment on column DM_NURTURESWOMEN_TEMP.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_NURTURESWOMEN_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_NURTURESWOMEN_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_NURTURESWOMEN_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_NURTURESWOMEN_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_NURTURESWOMEN_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_NURTURESWOMEN_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_NURTURESWOMEN_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_NURTURESWOMEN_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_NURTURESWOMEN_TEMP.importDate
  is '导入时间';
comment on column DM_NURTURESWOMEN_TEMP.actualPopulationType is
'实口类型';
comment on column DM_NURTURESWOMEN_TEMP.dispose is '是否经过处理(0:否)';
comment on column DM_NURTURESWOMEN_TEMP.claimAvailable is '是否可以认领(0:否)';  
comment on column DM_NURTURESWOMEN_TEMP.districtOrgId is
'导入时的到县区的组织机构';
  

create sequence s_DM_rentalhouse_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 99999999999;
/*==============================================================*/
/* Table: DM_rentalhouse_Temp       数据管理_出租房                                  */
/*==============================================================*/
create table DM_rentalhouse_Temp  (
   id                   NUMBER(10)                      not null ,
   orgId				NUMBER(10)			not null,
   orgInternalCode      VARCHAR2(32)                    ,
   houseCode            VARCHAR2(150)                  ,
   addressType          NUMBER(10),
   address         		VARCHAR2(150)                   not null,
   block 				VARCHAR2(24),
   unit 				VARCHAR2(18),
   room 				VARCHAR2(30),
   community            VARCHAR2(60),
   rentalPerson           VARCHAR2(150),
   hiddenDangerLevel    NUMBER(10),
   buildingName      	VARCHAR2(300),
   buildingUses         NUMBER(10),
   propertyName      	VARCHAR2(300),
   manager           VARCHAR2(150),
   houseOwnerIdCardNo   VARCHAR2(18),
   telephone            VARCHAR2(80),
   houseDoorModel      	VARCHAR2(100),
   builtYear            VARCHAR2(4),
   houseArea            NUMBER(15,5),
   houseStructure       NUMBER(10),
   houseUses            NUMBER(10),
   houseSource          NUMBER(10),
   ownProperty          NUMBER(10),
   rentalBuildings      NUMBER(10),
   housingVouchers      NUMBER(10),
   houseRightNumber   	VARCHAR2(100),
   houseRightNumberDate DATE,
   landDocuments        NUMBER(10),
   landRightsNumbe   	VARCHAR2(100),
   landRightsNumbeDate  DATE,
   propertyTypes        NUMBER(10),
   name  	            VARCHAR2(60),
   certificateType      NUMBER(10),
   certificateNumbe     VARCHAR2(50),
   propertyPersonTel    VARCHAR2(80),
   propertyPersonMobile VARCHAR2(50),
   usage                NUMBER(10),
   houseFileNum         VARCHAR2(150),
   lessorType           NUMBER(10),
   rentalCertificateType NUMBER(10),
   rentalCertificateNumbe  VARCHAR2(50),
   rentalTelephone         VARCHAR2(80),
   rentalMobileNumber      VARCHAR2(50),
   lessorAddress        VARCHAR2(150),
   rentalType		NUMBER(10),
   rentalHouseProperty  NUMBER(10),
   mangerTypes          NUMBER(10),
   registDate   DATE,
   lessorDate           DATE,
   roomNumber           NUMBER(10),
   limitPersons    NUMBER(10),
   rentMonth            NUMBER(10),
   hiddenRectification  VARCHAR2(150),
   isFireChannels       NUMBER(2) default 0,
   isSafetyChannel      NUMBER(2) default 0,
   isSignGuarantee      NUMBER(2) default 0,
   remark               VARCHAR2(900),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
	imgUrl					VARCHAR2(300),
   constraint PKDM_RENTALHOUSETEMP primary key (ID),
   constraint FKDM_RENTALHOUSETEMPORG foreign key (orgId)  references organizations (id)
);
comment on table DM_rentalhouse_Temp is
'数据管理_出租房';

comment on column DM_rentalhouse_Temp.id is
'主键';

comment on column DM_rentalhouse_Temp.orgId is
'所属网格';

comment on column DM_rentalhouse_Temp.orgInternalCode is
'网格编号';

comment on column DM_rentalhouse_Temp.houseCode is
'住房编号';
comment on column DM_rentalhouse_Temp.addressType is
'常住地址类型';
comment on column DM_rentalhouse_Temp.address is
'常住地址';
comment on column DM_rentalhouse_Temp.block is
'常住地址商品房 幢';

comment on column DM_rentalhouse_Temp.unit is
'常住地址商品房 单元';

comment on column DM_rentalhouse_Temp.room is
'常住地址商品房  室';
comment on column DM_rentalhouse_Temp.community is
'常住地址商品房 小区';
comment on column DM_rentalhouse_Temp.rentalPerson is
'出租人';
comment on column DM_rentalhouse_Temp.hiddenDangerLevel is
'隐患等级';
comment on column DM_rentalhouse_Temp.buildingName is
'建筑物名称';
comment on column DM_rentalhouse_Temp.buildingUses is
'建筑物用途 ';
comment on column DM_rentalhouse_Temp.propertyName is
'物业管理单位名称 ';
comment on column DM_rentalhouse_Temp.manager is
'代表人/业主';
comment on column DM_rentalhouse_Temp.houseOwnerIdCardNo is
'业主身份证号码';
comment on column DM_rentalhouse_Temp.telephone is
'业主联系电话';
comment on column DM_rentalhouse_Temp.houseDoorModel is
'房屋户型';
comment on column DM_rentalhouse_Temp.builtYear is
'建成年份';
comment on column DM_rentalhouse_Temp.houseArea is
'住房面积';
comment on column DM_rentalhouse_Temp.houseStructure is
'住房结构';
comment on column DM_rentalhouse_Temp.houseUses is
'房屋用途';

comment on column DM_rentalhouse_Temp.houseSource is
'房屋来源';

comment on column DM_rentalhouse_Temp.ownProperty is
'自有产权';

comment on column DM_rentalhouse_Temp.rentalBuildings is
'租赁公房';

comment on column DM_rentalhouse_Temp.housingVouchers is
'房屋凭证';
comment on column DM_rentalhouse_Temp.houseRightNumber is
'房屋权证号';
comment on column DM_rentalhouse_Temp.houseRightNumberDate is
'房屋权证发证时间';
comment on column DM_rentalhouse_Temp.landDocuments is
'土地凭证';

comment on column DM_rentalhouse_Temp.landRightsNumbe is
'土地权证号';
comment on column DM_rentalhouse_Temp.landRightsNumbeDate is
'土地权证发证时间';
comment on column DM_rentalhouse_Temp.propertyTypes is
'产权人类型';

comment on column DM_rentalhouse_Temp.name is
'产权人名称';

comment on column DM_rentalhouse_Temp.certificateType is
'证件类型';

comment on column DM_rentalhouse_Temp.certificateNumbe is
'证件号码';

comment on column DM_rentalhouse_Temp.propertyPersonTel is
'产权人联系电话';

comment on column DM_rentalhouse_Temp.propertyPersonMobile is
'产权人联系手机';
comment on column DM_rentalhouse_Temp.usage is
'出租房用途';

comment on column DM_rentalhouse_Temp.houseFileNum is
'租赁备案证号';

comment on column DM_rentalhouse_Temp.lessorType is
'出租人类型';
comment on column DM_rentalhouse_Temp.rentalCertificateType is
'出租人证件类型';

comment on column DM_rentalhouse_Temp.rentalCertificateNumbe is
'出租人证件号码';

comment on column DM_rentalhouse_Temp.rentalTelephone is
'出租人联系电话';
comment on column DM_rentalhouse_Temp.rentalMobileNumber is
'出租人联系手机';

comment on column DM_rentalhouse_Temp.lessorAddress is
'出租人联系地址';

comment on column DM_rentalhouse_Temp.rentalType is
'出租房类别';

comment on column DM_rentalhouse_Temp.rentalHouseProperty is
'出租房性质';
comment on column DM_rentalhouse_Temp.mangerTypes is
'管理类别';
comment on column DM_rentalhouse_Temp.registDate is
'登记日期';
comment on column DM_rentalhouse_Temp.lessorDate is
'出租时间';

comment on column DM_rentalhouse_Temp.roomNumber is
'出租间数';
comment on column DM_rentalhouse_Temp.limitPersons is
'限住人数';
comment on column DM_rentalhouse_Temp.rentMonth is
'月租金';
comment on column DM_rentalhouse_Temp.hiddenRectification is
'隐患情况';
comment on column DM_rentalhouse_Temp.isFireChannels is
'有无消防通道';

comment on column DM_rentalhouse_Temp.isSafetyChannel is
'有无安全通道';

comment on column DM_rentalhouse_Temp.isSignGuarantee is
'是否签订治安责任保证书';
comment on column DM_rentalhouse_Temp.remark is
'备注';
comment on column DM_rentalhouse_Temp.CREATEUSER
  is '创建用户';
comment on column DM_rentalhouse_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_rentalhouse_Temp.CREATEDATE
  is '创建日期';
comment on column DM_rentalhouse_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_rentalhouse_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_rentalhouse_Temp.claimDate is
'认领日期';

comment on column DM_rentalhouse_Temp.claimUserName is
'认领人用户名';

comment on column DM_rentalhouse_Temp.claimUserId is
'认领人Id';

comment on column DM_rentalhouse_Temp.claimOrgId is
'认领人网格';

comment on column DM_rentalhouse_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_rentalhouse_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_rentalhouse_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_rentalhouse_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_rentalhouse_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_rentalhouse_Temp.importDate
  is '导入时间';
comment on column dm_rentalhouse_temp.IMGURL is '出租房图片';



/*==============================================================*/
/* Table: DM_idleYouthsHasDicts_temp    数据管理_重点青少年 用于存放 人员类型                                  */
/*==============================================================*/
create table DM_idleYouthsHasDicts_temp  (
   idleYouthsTempId              NUMBER(10)                      not null,
   propertyDictId            NUMBER(10)                      not null,
   constraint PKDM_IDLEYOUTHSHASDICTSTEMP primary key (idleYouthsTempId, propertyDictId),
   constraint FKDM_IDLEYOUTHSHASDICTSTEMP foreign key (idleYouthsTempId)
         references DM_idleYouths_temp(id)
);

  /* Table: uPeopleTempHasEIntention                                    */
/*==============================================================*/
create table DM_uPeopleHasEIntention_TEMP (
   unemployedPeopleTempId               NUMBER(10)                      not null,
   propertyDictId                   NUMBER(10)                      not null,
   constraint PKDM_UPEOPLEHASEINTENTIONTEMP primary key (unemployedPeopleTempId, propertyDictId),
   constraint FKDM_UPEOPLEHASEINTENTIONTEMP foreign key (unemployedPeopleTempId)
         references DM_unemployedPeople_Temp (id)
);

  /* Table: uPeopleTempHasTIntention                                    */
/*==============================================================*/
create table dm_uPeopleHasTIntention_Temp (
   unemployedPeopleTempId               NUMBER(10)                      not null,
   propertyDictId                   NUMBER(10)                      not null,
   constraint PKDM_UPEOPLEHASTINTENTIONTEMP primary key (unemployedPeopleTempId, propertyDictId),
   constraint FKDM_UPEOPLEHASTINTENTIONTEMP foreign key (unemployedPeopleTempId)
         references DM_unemployedPeople_Temp (id)
);

/*==============================================================*/
/* Table: visitTypesTemp                                            */
/*==============================================================*/
create table dm_visitTypes_Temp  (
   superiorVisitId      NUMBER(10)                      not null,
   superiorVisitType    NUMBER(10),
   constraint FKDM_VISITTYPESTEMPSUPTEMP foreign key (superiorVisitId)
         references DM_superiorVisits_Temp (id)
);

comment on table dm_visitTypes_Temp is
'访问类型（数据管理）';

comment on column dm_visitTypes_Temp.superiorVisitId is
'信访上访人员ID';

comment on column dm_visitTypes_Temp.superiorVisitType is
'上访类型';


create sequence s_DM_ACTUALCOMPANY_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 99999999999;
/*==============================================================*/
/* Table: DM_ACTUALCOMPANY_Temp（实体表无s）       数据管理_实有单位                                  */
/*==============================================================*/
create table DM_ACTUALCOMPANY_Temp(
  ID                      NUMBER(10) not null,
  ORGID                   NUMBER(10) not null,
  ORGINTERNALCODE         VARCHAR2(50),
  name             VARCHAR2(150) not null,
  ADDRESS          VARCHAR2(150),
  BUSINESSLICENSENO       VARCHAR2(150),
  ORGCODE                 VARCHAR2(150)  not null,
  COMPANYTYPE             NUMBER(10),
  manager  VARCHAR2(150),
  SUPERVISORYLEVEL        NUMBER(10),
  SUPERVISORYDEPARTMENT   VARCHAR2(90),
  SECURITYCHIEF           VARCHAR2(150),
  ISKEY                   NUMBER(1) default 0,
  FIREFIGHTINGLEVEL       NUMBER(10),
  TELEPHONE               VARCHAR2(80),
  FAX               VARCHAR2(150),
  REGISTEREDCAPITAL       NUMBER(10,4),
  ECONOMICNATURE         NUMBER(10),
  EXPIRYDATE              DATE,
  REGISTRATIONDATE        DATE,
  BUSINESSSCOPE           VARCHAR2(150),
  REGISTRATIONADDRESS     VARCHAR2(150),
  EMPLOYEESNUM            NUMBER(10),
  COMPETENTDEPARTMENT     VARCHAR2(90),
  REMARK                  VARCHAR2(900),
  IDCARDNO             VARCHAR2(60),
  CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
  claimState             NUMBER(10) default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose 				  NUMBER(10) default 0,
   claimavailable         NUMBER(10) default 0,
   importDate              DATE,
   districtOrgId				  NUMBER(10),
   constraint PKDM_ACTUALCOMPANYTEMP primary key (id),
   constraint FKDM_ACTUALCOMPANYTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_ACTUALCOMPANY_Temp
  is '数据管理_实有单位';

comment on column DM_ACTUALCOMPANY_Temp.ORGID
  is '所属网格';

comment on column DM_ACTUALCOMPANY_Temp.ORGINTERNALCODE
  is '部门内部编号';

comment on column DM_ACTUALCOMPANY_Temp.name
  is '单位名称';

comment on column DM_ACTUALCOMPANY_Temp.ADDRESS
  is '单位地址';
  
comment on column DM_ACTUALCOMPANY_Temp.BUSINESSLICENSENO
  is '营业执照号码';
 
comment on column DM_ACTUALCOMPANY_Temp.ORGCODE
  is '组织机构代码'; 

comment on column DM_ACTUALCOMPANY_Temp.COMPANYTYPE
  is '单位类别';

comment on column DM_ACTUALCOMPANY_Temp.manager
  is '法人代表';
  
comment on column DM_ACTUALCOMPANY_Temp.SUPERVISORYLEVEL
  is '管理级别';

comment on column DM_ACTUALCOMPANY_Temp.SUPERVISORYDEPARTMENT
  is '管理部门'; 

comment on column DM_ACTUALCOMPANY_Temp.SECURITYCHIEF
  is '治安负责人';
  
comment on column DM_ACTUALCOMPANY_Temp.ISKEY
  is '是否重点单位';
  
comment on column DM_ACTUALCOMPANY_Temp.FIREFIGHTINGLEVEL
  is '消防等级';
  
comment on column DM_ACTUALCOMPANY_Temp.TELEPHONE
  is '单位电话';

comment on column DM_ACTUALCOMPANY_Temp.fax
  is '单位传真';
  
comment on column DM_ACTUALCOMPANY_Temp.REGISTEREDCAPITAL
  is '注册资本';

comment on column DM_ACTUALCOMPANY_Temp.ECONOMICNATURE
  is '经济性质';
  
comment on column DM_ACTUALCOMPANY_Temp.EXPIRYDATE
  is '有效期至';

comment on column DM_ACTUALCOMPANY_Temp.REGISTRATIONDATE
  is '注册日期';

comment on column DM_ACTUALCOMPANY_Temp.BUSINESSSCOPE
  is '经营范围';

comment on column DM_ACTUALCOMPANY_Temp.REGISTRATIONADDRESS
  is '注册地址';
  
comment on column DM_ACTUALCOMPANY_Temp.EMPLOYEESNUM
  is '从业人数';

comment on column DM_ACTUALCOMPANY_Temp.COMPETENTDEPARTMENT
  is '主管部门';
  
comment on column DM_ACTUALCOMPANY_Temp.REMARK
  is '备注';
 comment on column DM_ACTUALCOMPANY_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_ACTUALCOMPANY_Temp.CREATEUSER
  is '创建用户';
comment on column DM_ACTUALCOMPANY_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_ACTUALCOMPANY_Temp.CREATEDATE
  is '创建日期';
comment on column DM_ACTUALCOMPANY_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_ACTUALCOMPANY_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_ACTUALCOMPANY_Temp.claimDate is
'认领日期';

comment on column DM_ACTUALCOMPANY_Temp.claimUserName is
'认领人用户名';

comment on column DM_ACTUALCOMPANY_Temp.claimUserId is
'认领人Id';

comment on column DM_ACTUALCOMPANY_Temp.claimOrgId is
'认领人网格';

comment on column DM_ACTUALCOMPANY_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_ACTUALCOMPANY_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_ACTUALCOMPANY_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_ACTUALCOMPANY_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_ACTUALCOMPANY_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领'; 
comment on column DM_ACTUALCOMPANY_Temp.importDate
  is '导入时间';
comment on column DM_ACTUALCOMPANY_Temp.districtOrgId is
'导入时的到县区的组织机构'; 


create sequence s_DM_dangeCUnit_Temp
increment by 1
start with 1
minvalue 1
cache 20
maxvalue 99999999999;
/*==============================================================*/
/* Table: DM_dangeCUnit_Temp（实体表无s）       数据管理_危险化学品单位                                  */
/*==============================================================*/
create table DM_dangeCUnit_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      ,
   orgInternalCode      VARCHAR2(32),
   Name             VARCHAR2(60),
   Address          VARCHAR2(150),
   manager       VARCHAR2(60),
   telephone            VARCHAR2(80),
   unitType             VARCHAR2(150),
   commodityType        VARCHAR2(150),
   copyScope            VARCHAR2(150),
   storageDevice        VARCHAR2(150),
   attentionExtent      NUMBER(10),
   remark               VARCHAR2(900),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose 				  NUMBER(10) default 0,
   claimavailable         NUMBER(10) default 0,
   importDate              DATE,
   districtOrgId				  NUMBER(10),
   constraint PKDM_DANGERCUTEMP primary key (id),
   constraint FKDM_DANGERCUTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_dangeCUnit_Temp is
'数据管理_危险化学品单位 ';

comment on column DM_dangeCUnit_Temp.id is
'危险化学品单位id';

comment on column DM_dangeCUnit_Temp.orgId is
'所属网格';

comment on column DM_dangeCUnit_Temp.Name is
'单位名称';

comment on column DM_dangeCUnit_Temp.Address is
'单位地址';

comment on column DM_dangeCUnit_Temp.manager is
'负责人';

comment on column DM_dangeCUnit_Temp.telephone is
'联系电话';

comment on column DM_dangeCUnit_Temp.unitType is
'单位类别';

comment on column DM_dangeCUnit_Temp.commodityType is
'货物类别';

comment on column DM_dangeCUnit_Temp.copyScope is
'副本许可范围';

comment on column DM_dangeCUnit_Temp.storageDevice is
'存储设备';

comment on column DM_dangeCUnit_Temp.remark is
'备注';
comment on column DM_dangeCUnit_Temp.CREATEUSER
  is '创建用户';
comment on column DM_dangeCUnit_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_dangeCUnit_Temp.CREATEDATE
  is '创建日期';
comment on column DM_dangeCUnit_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_dangeCUnit_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_dangeCUnit_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_dangeCUnit_Temp.claimDate is
'认领日期';

comment on column DM_dangeCUnit_Temp.claimUserName is
'认领人用户名';

comment on column DM_dangeCUnit_Temp.claimUserId is
'认领人Id';

comment on column DM_dangeCUnit_Temp.claimOrgId is
'认领人网格';

comment on column DM_dangeCUnit_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_dangeCUnit_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_dangeCUnit_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_dangeCUnit_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_dangeCUnit_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领'; 
comment on column DM_dangeCUnit_Temp.importDate
  is '导入时间';
comment on column DM_dangeCUnit_Temp.districtOrgId is
'导入时的到县区的组织机构';
  
create sequence s_DM_internetBar_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_internetBar_Temp（实体表无s）       数据管理_网吧                                  */
/*==============================================================*/
 create table DM_internetBar_Temp (
	id                   NUMBER(10)                      not null,
	orgId                NUMBER(10)                      not null,
	orgInternalCode      VARCHAR2(32)                    ,
	Name      		 VARCHAR2(150)  				 not null,
	manager           	 VARCHAR2(60),
	mobile               VARCHAR2(12) ,
	Address         VARCHAR2(150)  				,
	stationPolice         VARCHAR2(150),
	barCode               VARCHAR2(60),
	operationArea 		  NUMBER(10,2),
	tempNetCardNum        NUMBER(10),
	totalComputerNum      NUMBER(10),
	netAccessProviders   VARCHAR2(60),
	internetAccessType   VARCHAR2(60),
	useIP                 VARCHAR2(180),
	netCultureLicenceNo  VARCHAR2(60),
	netSecurityAuditNo   VARCHAR2(60),
	fireAuditDocumentNo   VARCHAR2(60),
	attentionExtent      NUMBER(10),
	remark     			 VARCHAR2(900),
	CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
	 claimState             NUMBER(10)  default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   oldOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose 				  NUMBER(10) default 0,
   claimavailable         NUMBER(10) default 0,
   importDate              DATE,
   districtOrgId				  NUMBER(10),
	constraint			 PKDM_INTERNETBARTEMP primary key(id),
	constraint			 FKDM_INTERNETBARTEMPORG foreign key(orgId)
		references organizations(id)
);
comment on table DM_internetBar_Temp is
'数据管理_网吧';
comment on column DM_internetBar_Temp.id is
'网吧Id';
comment on column DM_internetBar_Temp.orgId is
'所属网格';
comment on column DM_internetBar_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_internetBar_Temp.Name is
'场所名称';
comment on column DM_internetBar_Temp.Address is
'场所地址';
comment on column DM_internetBar_Temp.manager is
'负责人';
comment on column DM_internetBar_Temp.mobile is
'负责人联系电话';
comment on column DM_internetBar_Temp.stationPolice is
'所在地派出所名称';
comment on column DM_internetBar_Temp.barCode is
'网吧编号';
comment on column DM_internetBar_Temp.operationArea is
'营业面积';
comment on column DM_internetBar_Temp.tempNetCardNum is
'临时上网卡数量';
comment on column DM_internetBar_Temp.totalComputerNum is
'电脑台数';
comment on column DM_internetBar_Temp.netAccessProviders is
'宽带接入服务商';
comment on column DM_internetBar_Temp.internetAccessType is
'接入方式';
comment on column DM_internetBar_Temp.useIP is
'现使用ip';
comment on column DM_internetBar_Temp.netCultureLicenceNo is
'网络文化经营许可证号';
comment on column DM_internetBar_Temp.netSecurityAuditNo is
'网络安全审核意见书号';
comment on column DM_internetBar_Temp.fireAuditDocumentNo is
'消防审核意见证书号';
comment on column DM_internetBar_Temp.remark is
'备注';
comment on column DM_internetBar_Temp.CREATEUSER
  is '创建用户';
comment on column DM_internetBar_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_internetBar_Temp.CREATEDATE
  is '创建日期';
comment on column DM_internetBar_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_internetBar_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_internetBar_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_internetBar_Temp.claimDate is
'认领日期';

comment on column DM_internetBar_Temp.claimUserName is
'认领人用户名';

comment on column DM_internetBar_Temp.claimUserId is
'认领人Id';

comment on column DM_internetBar_Temp.claimOrgId is
'认领人网格';

comment on column DM_internetBar_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_internetBar_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_internetBar_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_internetBar_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_internetBar_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_internetBar_Temp.importDate
  is '导入时间';
comment on column DM_internetBar_Temp.districtOrgId is
'导入时的到县区的组织机构';


create sequence s_DM_publicPlace_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_publicPlace_Temp（实体表无s）       数据管理_公共场所                                  */
/*==============================================================*/


create table DM_publicPlace_Temp (
	id                   NUMBER(10)                      not null,
	orgId                NUMBER(10)                      not null,
	orgInternalCode      VARCHAR2(32)                    ,
	Name      		 VARCHAR2(150)  				 not null,
	Address         VARCHAR2(150)  				 not null,
	manager           	 VARCHAR2(60),
	licenseDate      	 DATE,
	openingDate      	 DATE,
	category    	     VARCHAR2(60),
	registrationNumber   VARCHAR2(150),
	placeLevel  		 VARCHAR2(60),
	buildingStructure    VARCHAR2(60),
	totalArea     		 NUMBER(10,2),
	operationArea 		 NUMBER(10,2),
	cloakroom  			 NUMBER(10),
	vouchedPeopleCount   NUMBER(10),
	privateRoomCount     NUMBER(10),
	surrounding          VARCHAR2(450),
	passageway  		 VARCHAR2(90),
	innerStructure  	 VARCHAR2(60),
	remark     			 VARCHAR2(900),
	attentionExtent      NUMBER(10),
	CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
	claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
	constraint			 PKDM_PUBLICPLACETEMP primary key(id),
	constraint			 FKDM_PUBLICPLACETEMPORG foreign key(orgId)
		references organizations(id)
);
comment on table DM_publicPlace_Temp is
'数据管理_公共场所';
comment on column DM_publicPlace_Temp.id is
'公共场所Id';
comment on column DM_publicPlace_Temp.orgId is
'所属网格';
comment on column DM_publicPlace_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_publicPlace_Temp.Name is
'场所名称';
comment on column DM_publicPlace_Temp.Address is
'场所地址';
comment on column DM_publicPlace_Temp.manager is
'负责人';
comment on column DM_publicPlace_Temp.LicenseDate is
'领取执照时间';
comment on column DM_publicPlace_Temp.openingDate is
'开业时间';
comment on column DM_publicPlace_Temp.category is
'公共场所类别';
comment on column DM_publicPlace_Temp.registrationNumber is
'备案登记号码';
comment on column DM_publicPlace_Temp.placeLevel is
'场所等级';
comment on column DM_publicPlace_Temp.buildingStructure is
'建筑物结构';
comment on column DM_publicPlace_Temp.totalArea is
'总面积';
comment on column DM_publicPlace_Temp.operationArea is
'营业面积';
comment on column DM_publicPlace_Temp.cloakroom is
'小件寄存处';
comment on column DM_publicPlace_Temp.vouchedPeopleCount is
'核定人数';
comment on column DM_publicPlace_Temp.privateRoomCount is
'包间数';
comment on column DM_publicPlace_Temp.surrounding is
'周围环境';
comment on column DM_publicPlace_Temp.passageway is
'通道口';
comment on column DM_publicPlace_Temp.innerStructure is
'内部结构';
comment on column DM_publicPlace_Temp.remark is
'备注';
comment on column DM_publicPlace_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_publicPlace_Temp.CREATEUSER
  is '创建用户';
comment on column DM_publicPlace_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_publicPlace_Temp.CREATEDATE
  is '创建日期';
comment on column DM_publicPlace_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_publicPlace_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_publicPlace_Temp.claimDate is
'认领日期';

comment on column DM_publicPlace_Temp.claimUserName is
'认领人用户名';

comment on column DM_publicPlace_Temp.claimUserId is
'认领人Id';

comment on column DM_publicPlace_Temp.claimOrgId is
'认领人网格';

comment on column DM_publicPlace_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_publicPlace_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_publicPlace_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_publicPlace_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_publicPlace_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_publicPlace_Temp.importDate
  is '导入时间';
comment on column DM_publicPlace_Temp.districtOrgId is
'导入时的到县区的组织机构';



create sequence s_DM_otherLocales_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_otherLocales_Temp       数据管理_其他场所                                  */
/*==============================================================*/
 create table DM_otherLocales_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   name                 VARCHAR2(60)                    not null,
   type                 NUMBER(10),
   address              VARCHAR2(150),
   manager        		VARCHAR2(60),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   attentionExtent      NUMBER(10),
   remark               VARCHAR2(600),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           	VARCHAR2(60),
  CREATEDATE           	DATE not null,
  UPDATEDATE           	DATE,
   claimState           NUMBER(10) default 0,
	claimDate           DATE,
	claimUserName       VARCHAR2(4000),
	claimUserId         NUMBER(10),
	claimOrgId          NUMBER(10),
	importDepartmentId  NUMBER(10)   not null,
	oldOrgId			NUMBER(10),
	inId				NUMBER(10),
	dispose 			NUMBER(10) default 0,
	claimavailable      NUMBER(10) default 0,
	importDate          DATE,
	districtOrgId		NUMBER(10),
	PRACTITIONER_NUMBER NUMBER(10),
   constraint PKDM_OTHERLOCALESTEMP primary key (id),
   constraint FKDM_OTHERLOCALESTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_otherLocales_Temp is
'数据管理_其他场所 ';

comment on column DM_otherLocales_Temp.id is
'主键';

comment on column DM_otherLocales_Temp.orgId is
'所属网格';

comment on column DM_otherLocales_Temp.orgInternalCode is
'所属网格内置编码';

comment on column DM_otherLocales_Temp.name is
'场所名称';

comment on column DM_otherLocales_Temp.type is
'场所类型';

comment on column DM_otherLocales_Temp.address is
'场所地址';

comment on column DM_otherLocales_Temp.manager is
'联系人';

comment on column DM_otherLocales_Temp.telephone is
'联系电话';

comment on column DM_otherLocales_Temp.mobileNumber is
'联系手机号码';

comment on column DM_otherLocales_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_otherLocales_Temp.remark is
'备注';

comment on column DM_otherLocales_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_otherLocales_Temp.CREATEUSER
  is '创建用户';
comment on column DM_otherLocales_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_otherLocales_Temp.CREATEDATE
  is '创建日期';
comment on column DM_otherLocales_Temp.UPDATEDATE
  is '修改日期';
  
comment on column DM_otherLocales_Temp.claimDate is
'认领日期';

comment on column DM_otherLocales_Temp.claimUserName is
'认领人用户名';

comment on column DM_otherLocales_Temp.claimUserId is
'认领人Id';

comment on column DM_otherLocales_Temp.claimOrgId is
'认领人网格';

comment on column DM_otherLocales_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_otherLocales_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_otherLocales_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_otherLocales_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_otherLocales_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_otherLocales_Temp.importDate
  is '导入时间';
comment on column DM_otherLocales_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_otherLocales_Temp.PRACTITIONER_NUMBER is
'从业人员数';



create sequence s_DM_schools_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_schools_Temp       数据管理_学校                                 */
/*==============================================================*/
create table DM_schools_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   Name          VARCHAR2(90),
   manager            VARCHAR2(60)                    not null,
   kind                 NUMBER(10)                      not null,
   address              VARCHAR2(150)                   not null, 
   type                 NUMBER(10)                      not null,
   vicePresident    VARCHAR2(60),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   webSite              VARCHAR2(60),
   atSchoolHeadcount    NUMBER(10),
   englishName          VARCHAR2(90),
   fax                  VARCHAR2(15),
   email                VARCHAR2(32),
   attentionExtent      NUMBER(10),
   remark                   VARCHAR2(600),
    CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_SCHOOLSTEMP primary key (id),
   constraint FKDM_SCHOOLSTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_schools_Temp is
'数据管理_学校';

comment on column DM_schools_Temp.id is
'编号';

comment on column DM_schools_Temp.orgId is
'所属网格';

comment on column DM_schools_Temp.orgInternalCode is
'所属网格编号';

comment on column DM_schools_Temp.Name is
'学校名称';

comment on column DM_schools_Temp.manager is
'校长';

comment on column DM_schools_Temp.kind is
'学校性质';

comment on column DM_schools_Temp.address is
'学校地址';

comment on column DM_schools_Temp.type is
'学校类型';

comment on column DM_schools_Temp.vicePresident is
'法制副校长';

comment on column DM_schools_Temp.telephone is
'联系人固定电话';

comment on column DM_schools_Temp.mobileNumber is
'联系人手机号码';

comment on column DM_schools_Temp.webSite is
'学校网址';

comment on column DM_schools_Temp.atSchoolHeadcount is
'在校总人数';

comment on column DM_schools_Temp.englishName is
'英文名称';

comment on column DM_schools_Temp.fax is
'传真';

comment on column DM_schools_Temp.email is
'联系人电子邮件';

comment on column DM_schools_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

comment on column DM_schools_Temp.remark is
'周边情况';

comment on column DM_schools_Temp.CREATEUSER
  is '创建用户';
comment on column DM_schools_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_schools_Temp.CREATEDATE
  is '创建日期';
comment on column DM_schools_Temp.UPDATEDATE
  is '修改日期';

comment on column DM_schools_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_schools_Temp.claimDate is
'认领日期';

comment on column DM_schools_Temp.claimUserName is
'认领人用户名';

comment on column DM_schools_Temp.claimUserId is
'认领人Id';

comment on column DM_schools_Temp.claimOrgId is
'认领人网格';

comment on column DM_schools_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_schools_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_schools_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_schools_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_schools_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_schools_Temp.importDate
  is '导入时间';
 comment on column DM_schools_Temp.districtOrgId is
'导入时的到县区的组织机构';

create sequence s_DM_newEconoOrg_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_newEconoOrg_Temp       数据管理_新经济组织                                 */
/*==============================================================*/
create table DM_newEconoOrg_Temp (
  ID                  NUMBER(10) not null,
  ORGID               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32) ,
  NAME                VARCHAR2(210) not null,
  address           VARCHAR2(150) not null,
  type               NUMBER(10),
  manager               VARCHAR2(60),
  MOBILENUMBER        VARCHAR2(11),
  TELEPHONE           VARCHAR2(60),
  partyNum   number(20),
  introduction        VARCHAR2(600),
  honor               VARCHAR2(600),
  REMARK              VARCHAR2(600),
  validityStartDate           DATE,
  validityEndDate             DATE,
  licenseNumber       VARCHAR2(60) not null,
  foxNumber           VARCHAR2(30),
  area                NUMBER(20,2),
  employeeNumber      number(20),
  CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
  claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
	imgUrl					VARCHAR2(300),
	constraint PKDM_NEWECONOORGTEMP primary key (id),
	constraint FKDM_NEWECONOORGTEMPORG foreign key (orgId)
		references organizations (id)
);

comment on table DM_newEconoOrg_Temp
  is '数据管理_新经济组织';
comment on column DM_newEconoOrg_Temp.ID
  is 'ID';
comment on column DM_newEconoOrg_Temp.ORGID
  is '所属网格';  
comment on column DM_newEconoOrg_Temp.ORGINTERNALCODE
  is '所属网格编号';
comment on column DM_newEconoOrg_Temp.NAME
  is '名称';  
comment on column DM_newEconoOrg_Temp.address
  is '住所';  
comment on column DM_newEconoOrg_Temp.type
  is '类别';  
comment on column DM_newEconoOrg_Temp.manager
  is '负责人';  
comment on column DM_newEconoOrg_Temp.TELEPHONE
  is '固定电话';
comment on column DM_newEconoOrg_Temp.MOBILENUMBER
  is '手机号码';  
comment on column DM_newEconoOrg_Temp.partyNum
  is '党员人数';
comment on column DM_newEconoOrg_Temp.introduction
  is '简介';
comment on column DM_newEconoOrg_Temp.honor
  is '获得荣耀';  
comment on column DM_newEconoOrg_Temp.REMARK
  is '备注';  
comment on column DM_newEconoOrg_Temp.validityStartDate
  is '有效期开始';
comment on column DM_newEconoOrg_Temp.validityEndDate
  is '有效期结束'; 
comment on column DM_newEconoOrg_Temp.licenseNumber
  is '营业执照号码';
comment on column DM_newEconoOrg_Temp.foxNumber
  is '传真号码';
comment on column DM_newEconoOrg_Temp.AREA
  is '面积';
comment on column DM_newEconoOrg_Temp.employeeNumber
  is '从业人数';
  comment on column DM_newEconoOrg_Temp.CREATEUSER
  is '创建用户';
comment on column DM_newEconoOrg_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_newEconoOrg_Temp.CREATEDATE
  is '创建日期';
comment on column DM_newEconoOrg_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_newEconoOrg_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_newEconoOrg_Temp.claimDate is
'认领日期';

comment on column DM_newEconoOrg_Temp.claimUserName is
'认领人用户名';

comment on column DM_newEconoOrg_Temp.claimUserId is
'认领人Id';

comment on column DM_newEconoOrg_Temp.claimOrgId is
'认领人网格';

comment on column DM_newEconoOrg_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_newEconoOrg_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_newEconoOrg_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_newEconoOrg_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_newEconoOrg_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_newEconoOrg_Temp.importDate
  is '导入时间';
comment on column DM_newEconoOrg_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_newEconoOrg_Temp.IMGURL is '图片路径';


create sequence s_DM_newSocieOrg_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_newSocieOrg_Temp       数据管理_新社会组织                                 */
/*==============================================================*/
 create table DM_newSocieOrg_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   name                 VARCHAR2(60)                    not null,
   address              VARCHAR2(150)                   ,
   type                 NUMBER(10)                      not null,
   manager          VARCHAR2(60),
   Telephone VARCHAR2(15),
   MobileNumber VARCHAR2(11),
   partyNum				NUMBER(10),
   introduction         VARCHAR2(600),
   honor                VARCHAR2(600),
   remark               VARCHAR2(600),
   validityStartDate    DATE,
   validityEndDate      DATE,
   chargeUnit           VARCHAR2(60),
   registrationNumber   NUMBER(32),
   personNum            NUMBER(10),
   mainResponsibilities   VARCHAR2(200),
    subtype                 NUMBER(10),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
	imgUrl					VARCHAR2(300),
   constraint PKDM_NEWSOCIEORGTEMP primary key (id),
   constraint FKDM_NEWSOCIEORGTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_newSocieOrg_Temp is
'数据管理_新社会组织';

comment on column DM_newSocieOrg_Temp.id is
'人员id';

comment on column DM_newSocieOrg_Temp.orgId is
'所属网格';
comment on column DM_newSocieOrg_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_newSocieOrg_Temp.name is
'组织名称';
comment on column DM_newSocieOrg_Temp.address is
'住所';
comment on column DM_newSocieOrg_Temp.type is
'组织类别';
comment on column DM_newSocieOrg_Temp.manager is
'法定代表人';
comment on column DM_newSocieOrg_Temp.Telephone is
'固定电话';
comment on column DM_newSocieOrg_Temp.MobileNumber is
'联系手机';
comment on column DM_newSocieOrg_Temp.partyNum is
'党员人数';
comment on column DM_newSocieOrg_Temp.introduction is
'简 介';

comment on column DM_newSocieOrg_Temp.honor is
'获得荣誉';
comment on column DM_newSocieOrg_Temp.remark is
'备注';
comment on column DM_newSocieOrg_Temp.validityStartDate is
'有效期开始日期';

comment on column DM_newSocieOrg_Temp.validityEndDate is
'有效期结束日期';
comment on column DM_newSocieOrg_Temp.chargeUnit is
'业务主管单位';
comment on column DM_newSocieOrg_Temp.registrationNumber is
'登记证号码';
comment on column DM_newSocieOrg_Temp.personNum is
'成员数';
comment on column DM_newSocieOrg_Temp.mainResponsibilities is
'主要职责';
comment on column DM_newSocieOrg_Temp.subtype
  is '组织子类别';
comment on column DM_newSocieOrg_Temp.CREATEUSER
  is '创建用户';
comment on column DM_newSocieOrg_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_newSocieOrg_Temp.CREATEDATE
  is '创建日期';
comment on column DM_newSocieOrg_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_newSocieOrg_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_newSocieOrg_Temp.claimDate is
'认领日期';

comment on column DM_newSocieOrg_Temp.claimUserName is
'认领人用户名';

comment on column DM_newSocieOrg_Temp.claimUserId is
'认领人Id';

comment on column DM_newSocieOrg_Temp.claimOrgId is
'认领人网格';

comment on column DM_newSocieOrg_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_newSocieOrg_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_newSocieOrg_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_newSocieOrg_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_newSocieOrg_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_newSocieOrg_Temp.importDate
  is '导入时间';
comment on column DM_newSocieOrg_Temp.districtOrgId is
'导入时的到县区的组织机构'; 
comment on column DM_newSocieOrg_Temp.IMGURL is '图片路径';

create sequence s_DM_safetyProduct_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_safetyProduct_Temp       数据管理_安全生产重点                                 */
/*==============================================================*/
 create table DM_safetyProduct_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   name                 VARCHAR2(150)                   not null,
   type                 NUMBER(10)                      not null,
   manager          VARCHAR2(60)                    not null,
   address              VARCHAR2(150)                   not null,
   industry             NUMBER(10),
   businessLicense      VARCHAR2(60),
   area                 NUMBER(15,5),
   registeredCapital    NUMBER(15,5),
   employeeAmount       NUMBER(10),
   partyMemberAmount    NUMBER(10),
   enterpriseTelephone  VARCHAR2(15),
   fax                  VARCHAR2(15),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   attentionExtent      NUMBER(10),
   isRiskEnterprise     NUMBER(1)                      default 0,
   hiddenCases          VARCHAR2(300),
   hiddenRectification  VARCHAR2(300),
   remark               VARCHAR2(600),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_SAFETYPRODUCTTEMP primary key (id),
   constraint FKDM_SAFETYPRODUCTTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_safetyProduct_Temp is
'数据管理_安全生产重点';
comment on column DM_safetyProduct_Temp.orgId is
'所属网格';
comment on column DM_safetyProduct_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_safetyProduct_Temp.name is
'企业名称';
comment on column DM_safetyProduct_Temp.type is
'类型';
comment on column DM_safetyProduct_Temp.manager is
'法人代表';
comment on column DM_safetyProduct_Temp.address is
'地址';
comment on column DM_safetyProduct_Temp.industry is
'所属行业';
comment on column DM_safetyProduct_Temp.businessLicense is
'工商执照号码';
comment on column DM_safetyProduct_Temp.area is
'面积（单位为‘平方米’）';
comment on column DM_safetyProduct_Temp.registeredCapital is
'注册资金（单位为‘万’）';
comment on column DM_safetyProduct_Temp.employeeAmount is
'员工数量';
comment on column DM_safetyProduct_Temp.partyMemberAmount is
'党员数量';
comment on column DM_safetyProduct_Temp.enterpriseTelephone is
'企业电话';
comment on column DM_safetyProduct_Temp.fax is
'传真';
comment on column DM_safetyProduct_Temp.telephone is
'法人电话';
comment on column DM_safetyProduct_Temp.mobileNumber is
'法人手机号码';
comment on column DM_safetyProduct_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_safetyProduct_Temp.isRiskEnterprise is
'是否危化企业（0为‘否’，1为''是''）';
comment on column DM_safetyProduct_Temp.hiddenCases is
'隐患情况';

comment on column DM_safetyProduct_Temp.hiddenRectification is
'隐患整改情况';

comment on column DM_safetyProduct_Temp.remark is
'备注';
comment on column DM_safetyProduct_Temp.CREATEUSER
  is '创建用户';
comment on column DM_safetyProduct_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_safetyProduct_Temp.CREATEDATE
  is '创建日期';
comment on column DM_safetyProduct_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_safetyProduct_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_safetyProduct_Temp.claimDate is
'认领日期';

comment on column DM_safetyProduct_Temp.claimUserName is
'认领人用户名';

comment on column DM_safetyProduct_Temp.claimUserId is
'认领人Id';

comment on column DM_safetyProduct_Temp.claimOrgId is
'认领人网格';

comment on column DM_safetyProduct_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_safetyProduct_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_safetyProduct_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_safetyProduct_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_safetyProduct_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领'; 
comment on column DM_safetyProduct_Temp.importDate
  is '导入时间';
comment on column DM_safetyProduct_Temp.districtOrgId is
'导入时的到县区的组织机构';
  
create sequence s_DM_FireSafetyE_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_FireSafetyE_Temp       数据管理_消防安全重点                                 */
/*==============================================================*/
 create table DM_FireSafetyE_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   name                 VARCHAR2(150)                   not null,
   type                 NUMBER(10)                      not null,
   manager          VARCHAR2(60)                    not null,
   address              VARCHAR2(150)                   not null,
   attentionExtent      NUMBER(10),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   isRiskEnterprise     NUMBER(1)                      default 0,
   hiddenCases          VARCHAR2(300),
   hiddenRectification  VARCHAR2(300),
   remark               VARCHAR2(600),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_FIRESAFETYETEMP primary key (id),
   constraint FKDM_FIRESAFETYETEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_FireSafetyE_Temp is
'数据管理_消防安全重点';
comment on column DM_FireSafetyE_Temp.orgId is
'所属网格';
comment on column DM_FireSafetyE_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_FireSafetyE_Temp.name is
'企业名称';
comment on column DM_FireSafetyE_Temp.type is
'类型';
comment on column DM_FireSafetyE_Temp.manager is
'法人代表';
comment on column DM_FireSafetyE_Temp.address is
'地址';
comment on column DM_FireSafetyE_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_FireSafetyE_Temp.telephone is
'法人电话';
comment on column DM_FireSafetyE_Temp.mobileNumber is
'法人手机号码';
comment on column DM_FireSafetyE_Temp.isRiskEnterprise is
'是否危化企业（0为‘否’，1为''是''）';
comment on column DM_FireSafetyE_Temp.hiddenCases is
'隐患情况';

comment on column DM_FireSafetyE_Temp.hiddenRectification is
'隐患整改情况';

comment on column DM_FireSafetyE_Temp.remark is
'备注';
comment on column DM_FireSafetyE_Temp.CREATEUSER
  is '创建用户';
comment on column DM_FireSafetyE_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_FireSafetyE_Temp.CREATEDATE
  is '创建日期';
comment on column DM_FireSafetyE_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_FireSafetyE_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_FireSafetyE_Temp.claimDate is
'认领日期';

comment on column DM_FireSafetyE_Temp.claimUserName is
'认领人用户名';

comment on column DM_FireSafetyE_Temp.claimUserId is
'认领人Id';

comment on column DM_FireSafetyE_Temp.claimOrgId is
'认领人网格';

comment on column DM_FireSafetyE_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_FireSafetyE_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_FireSafetyE_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_FireSafetyE_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_FireSafetyE_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领'; 
comment on column DM_FireSafetyE_Temp.importDate
  is '导入时间';
comment on column DM_FireSafetyE_Temp.districtOrgId is
'导入时的到县区的组织机构';  
  
create sequence s_DM_SecurityE_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_SecurityE_Temp       数据管理_治安重点                                 */
/*==============================================================*/
 create table DM_SecurityE_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    ,
   name                 VARCHAR2(150)                   not null,
   type                 NUMBER(10)                      not null,
   manager          VARCHAR2(60)                    not null,
   address              VARCHAR2(150)                   not null,
   attentionExtent      NUMBER(10),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   isRiskEnterprise     NUMBER(1)                      default 0,
   hiddenCases          VARCHAR2(300),
   hiddenRectification  VARCHAR2(300),
   remark               VARCHAR2(600),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER           VARCHAR2(60),
  CREATEDATE           DATE not null,
  UPDATEDATE           DATE,
   claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_SECURITYETEMP primary key (id),
   constraint FKDM_SECURITYETEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_SecurityE_Temp is
'数据管理_治安重点 ';
comment on column DM_SecurityE_Temp.orgId is
'所属网格';
comment on column DM_SecurityE_Temp.orgInternalCode is
'所属责任区编号';
comment on column DM_SecurityE_Temp.name is
'企业名称';
comment on column DM_SecurityE_Temp.type is
'类型';
comment on column DM_SecurityE_Temp.manager is
'法人代表';
comment on column DM_SecurityE_Temp.address is
'地址';
comment on column DM_SecurityE_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';
comment on column DM_SecurityE_Temp.telephone is
'法人电话';
comment on column DM_SecurityE_Temp.mobileNumber is
'法人手机号码';
comment on column DM_SecurityE_Temp.isRiskEnterprise is
'是否危化企业（0为‘否’，1为''是''）';
comment on column DM_SecurityE_Temp.hiddenCases is
'隐患情况';

comment on column DM_SecurityE_Temp.hiddenRectification is
'隐患整改情况';

comment on column DM_SecurityE_Temp.remark is
'备注';
comment on column DM_SecurityE_Temp.CREATEUSER
  is '创建用户';
comment on column DM_SecurityE_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_SecurityE_Temp.CREATEDATE
  is '创建日期';
comment on column DM_SecurityE_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_SecurityE_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_SecurityE_Temp.claimDate is
'认领日期';

comment on column DM_SecurityE_Temp.claimUserName is
'认领人用户名';

comment on column DM_SecurityE_Temp.claimUserId is
'认领人Id';

comment on column DM_SecurityE_Temp.claimOrgId is
'认领人网格';

comment on column DM_SecurityE_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_SecurityE_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_SecurityE_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_SecurityE_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_SecurityE_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领'; 
comment on column DM_SecurityE_Temp.importDate
  is '导入时间';
comment on column DM_SecurityE_Temp.districtOrgId is
'导入时的到县区的组织机构';
  
create sequence s_DM_houseInfo_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_houseInfo_Temp       数据管理_实有房屋                              */
/*==============================================================*/
 create table DM_houseInfo_Temp  (
   id                   NUMBER(10)                      not null,
   orgId				NUMBER(10)			not null,
   orgInternalCode      VARCHAR2(32)                    ,
   houseCode            VARCHAR2(150)                   ,
   addressType          NUMBER(10),
   address         		VARCHAR2(150)                   not null,
   block 				VARCHAR2(24),
   unit 				VARCHAR2(18),
   room 				VARCHAR2(30),
   community            VARCHAR2(60),
   isRentalHouse        NUMBER(2)			  default 0 not null,
   rentalPerson           VARCHAR2(150),
   hiddenDangerLevel    NUMBER(10),
   buildingName      	VARCHAR2(300),
   buildingUses         NUMBER(10),
   propertyName      	VARCHAR2(300),
   manager           VARCHAR2(150),
   houseOwnerIdCardNo   VARCHAR2(18),
   telephone            VARCHAR2(80),
   houseDoorModel      	VARCHAR2(100),
   builtYear            VARCHAR2(4),
   houseArea            NUMBER(15,5),
   houseStructure       NUMBER(10),
   houseUses            NUMBER(10),
   houseSource          NUMBER(10),
   ownProperty          NUMBER(10),
   rentalBuildings      NUMBER(10),
   housingVouchers      NUMBER(10),
   houseRightNumber   	VARCHAR2(100),
   houseRightNumberDate DATE,
   landDocuments        NUMBER(10),
   landRightsNumbe   	VARCHAR2(100),
   landRightsNumbeDate  DATE,
   propertyTypes        NUMBER(10),
   name  	            VARCHAR2(60),
   certificateType      NUMBER(10),
   certificateNumbe     VARCHAR2(50),
   propertyPersonTel    VARCHAR2(80),
   propertyPersonMobile VARCHAR2(50),
   usage                NUMBER(10),
   houseFileNum         VARCHAR2(150),
   lessorType           NUMBER(10),
   rentalCertificateType NUMBER(10),
   rentalCertificateNumbe  VARCHAR2(50),
   rentalTelephone         VARCHAR2(80),
   rentalMobileNumber      VARCHAR2(50),
   lessorAddress        VARCHAR2(150),
   rentalType		NUMBER(10),
   rentalHouseProperty  NUMBER(10),
   mangerTypes          NUMBER(10),
   registDate   DATE,
   lessorDate           DATE,
   roomNumber           NUMBER(10),
   limitPersons    NUMBER(10),
   rentMonth            NUMBER(10),
   hiddenRectification  VARCHAR2(150),
   isFireChannels       NUMBER(2) default 0,
   isSafetyChannel      NUMBER(2) default 0,
   isSignGuarantee      NUMBER(2) default 0,
   remark               VARCHAR2(900),
   CREATEUSER           VARCHAR2(60) not null,
  UPDATEUSER            VARCHAR2(60),
  CREATEDATE            DATE not null,
  UPDATEDATE            DATE,
   claimState           NUMBER(10) default 0,
	claimDate           DATE,
	claimUserName       VARCHAR2(4000),
	claimUserId         NUMBER(10),
	claimOrgId          NUMBER(10),
	importDepartmentId  NUMBER(10)   not null,
	oldOrgId		    NUMBER(10),
	inId			    NUMBER(15),
	dispose 		    NUMBER(10) default 0,
	claimavailable      NUMBER(10) default 0,
	importDate          DATE,
	districtOrgId	    NUMBER(10),
	IMGURL              VARCHAR2(300),
   constraint PKDM_HOUSEINFOTEMP primary key (id),
   constraint FKDM_HOUSEINFOTEMPORG foreign key (orgId)
         references organizations (id)
);

comment on table DM_houseInfo_Temp is
'数据管理_实有房屋';

comment on column DM_houseInfo_Temp.id is
'主键';

comment on column DM_houseInfo_Temp.orgId is
'所属网格';

comment on column DM_houseInfo_Temp.orgInternalCode is
'网格编号';

comment on column DM_houseInfo_Temp.houseCode is
'住房编号';
comment on column DM_houseInfo_Temp.addressType is
'常住地址类型';
comment on column DM_houseInfo_Temp.address is
'常住地址';
comment on column DM_houseInfo_Temp.block is
'常住地址商品房 幢';

comment on column DM_houseInfo_Temp.unit is
'常住地址商品房 单元';

comment on column DM_houseInfo_Temp.room is
'常住地址商品房  室';
comment on column DM_houseInfo_Temp.community is
'常住地址商品房 小区';
comment on column DM_houseInfo_Temp.isRentalHouse is
'是否出租房';
comment on column DM_houseInfo_Temp.rentalPerson is
'出租人';
comment on column DM_houseInfo_Temp.hiddenDangerLevel is
'隐患等级';
comment on column DM_houseInfo_Temp.buildingName is
'建筑物名称';
comment on column DM_houseInfo_Temp.buildingUses is
'建筑物用途 ';
comment on column DM_houseInfo_Temp.propertyName is
'物业管理单位名称 ';
comment on column DM_houseInfo_Temp.manager is
'代表人/业主';
comment on column DM_houseInfo_Temp.houseOwnerIdCardNo is
'业主身份证号码';
comment on column DM_houseInfo_Temp.telephone is
'业主联系电话';
comment on column DM_houseInfo_Temp.houseDoorModel is
'房屋户型';
comment on column DM_houseInfo_Temp.builtYear is
'建成年份';
comment on column DM_houseInfo_Temp.houseArea is
'住房面积';
comment on column DM_houseInfo_Temp.houseStructure is
'住房结构';
comment on column DM_houseInfo_Temp.houseUses is
'房屋用途';

comment on column DM_houseInfo_Temp.houseSource is
'房屋来源';

comment on column DM_houseInfo_Temp.ownProperty is
'自有产权';

comment on column DM_houseInfo_Temp.rentalBuildings is
'租赁公房';

comment on column DM_houseInfo_Temp.housingVouchers is
'房屋凭证';
comment on column DM_houseInfo_Temp.houseRightNumber is
'房屋权证号';
comment on column DM_houseInfo_Temp.houseRightNumberDate is
'房屋权证发证时间';
comment on column DM_houseInfo_Temp.landDocuments is
'土地凭证';

comment on column DM_houseInfo_Temp.landRightsNumbe is
'土地权证号';
comment on column DM_houseInfo_Temp.landRightsNumbeDate is
'土地权证发证时间';
comment on column DM_houseInfo_Temp.propertyTypes is
'产权人类型';

comment on column DM_houseInfo_Temp.name is
'产权人名称';

comment on column DM_houseInfo_Temp.certificateType is
'证件类型';

comment on column DM_houseInfo_Temp.certificateNumbe is
'证件号码';

comment on column DM_houseInfo_Temp.propertyPersonTel is
'产权人联系电话';

comment on column DM_houseInfo_Temp.propertyPersonMobile is
'产权人联系手机';
comment on column DM_houseInfo_Temp.usage is
'出租房用途';

comment on column DM_houseInfo_Temp.houseFileNum is
'租赁备案证号';

comment on column DM_houseInfo_Temp.lessorType is
'出租人类型';
comment on column DM_houseInfo_Temp.rentalCertificateType is
'出租人证件类型';

comment on column DM_houseInfo_Temp.rentalCertificateNumbe is
'出租人证件号码';

comment on column DM_houseInfo_Temp.rentalTelephone is
'出租人联系电话';
comment on column DM_houseInfo_Temp.rentalMobileNumber is
'出租人联系手机';

comment on column DM_houseInfo_Temp.lessorAddress is
'出租人联系地址';

comment on column DM_houseInfo_Temp.rentalType is
'出租房类别';

comment on column DM_houseInfo_Temp.rentalHouseProperty is
'出租房性质';
comment on column DM_houseInfo_Temp.mangerTypes is
'管理类别';
comment on column DM_houseInfo_Temp.registDate is
'登记日期';
comment on column DM_houseInfo_Temp.lessorDate is
'出租时间';

comment on column DM_houseInfo_Temp.roomNumber is
'出租间数';
comment on column DM_houseInfo_Temp.limitPersons is
'限住人数';
comment on column DM_houseInfo_Temp.rentMonth is
'月租金';
comment on column DM_houseInfo_Temp.hiddenRectification is
'隐患情况';
comment on column DM_houseInfo_Temp.isFireChannels is
'有无消防通道';

comment on column DM_houseInfo_Temp.isSafetyChannel is
'有无安全通道';

comment on column DM_houseInfo_Temp.isSignGuarantee is
'是否签订治安责任保证书';
comment on column DM_houseInfo_Temp.remark is
'备注';
comment on column DM_houseInfo_Temp.CREATEUSER
  is '创建用户';
comment on column DM_houseInfo_Temp.UPDATEUSER
  is '修改用户';
comment on column DM_houseInfo_Temp.CREATEDATE
  is '创建日期';
comment on column DM_houseInfo_Temp.UPDATEDATE
  is '修改日期';
comment on column DM_houseInfo_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_houseInfo_Temp.claimDate is
'认领日期';

comment on column DM_houseInfo_Temp.claimUserName is
'认领人用户名';

comment on column DM_houseInfo_Temp.claimUserId is
'认领人Id';

comment on column DM_houseInfo_Temp.claimOrgId is
'认领人网格';

comment on column DM_houseInfo_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_houseInfo_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_houseInfo_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_houseInfo_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_houseInfo_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_houseInfo_Temp.importDate  is 
'导入时间';
comment on column DM_houseInfo_Temp.districtOrgId is
'导入时的到县区的组织机构';
comment on column DM_HOUSEINFO_TEMP.IMGURL is 
'房屋图片';

create sequence s_DM_enterpriseKey_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_enterpriseKey_Temp       数据管理_规上企业                              */
/*==============================================================*/

create table DM_enterpriseKey_Temp  (
    id                   NUMBER(10)                      not null,
    orgId                NUMBER(10)                      not null,
    employeeAmount       NUMBER(10),
    partyMemberAmount    NUMBER(10),
    industry             NUMBER(10),
    isRiskEnterprise     NUMBER(1)                      default 0,
    area                 NUMBER(15,5),
    registeredCapital    NUMBER(15,5),
    name                 VARCHAR2(150)                   not null,
    type                 NUMBER(10)                      not null,
    businessLicense      VARCHAR2(60),
    manager          VARCHAR2(60)                    not null,
    hiddenCases          VARCHAR2(300),
    hiddenRectification  VARCHAR2(300),
    remark               VARCHAR2(600),
    createUser           VARCHAR2(32),
    updateUser           VARCHAR2(32),
    fullPinyin           VARCHAR2(30),
    simplePinyin         VARCHAR2(10),
    orgInternalCode      VARCHAR2(32)                    ,
    telephone            VARCHAR2(15),
    enterpriseTelephone  VARCHAR2(15),
    mobileNumber         VARCHAR2(11),
    fax                  VARCHAR2(15),
    imgUrl               VARCHAR2(300),
    address              VARCHAR2(150)                   not null,
    createDate           DATE                            not null,
    updateDate           DATE,
    isEmphasis           NUMBER(1)                      default 0,
    isEmphasisReason     VARCHAR2(300),
    isEmphasisDate       DATE,
    attentionExtent      NUMBER(10),
   
    claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_ENTERPRISETEMP primary key (id),
   constraint FKDM_ENTERPRISESORGTEMP foreign key (orgId)
         references organizations (id)
);
comment on table DM_enterpriseKey_Temp is
'规上企业';

comment on column DM_enterpriseKey_Temp.orgId is
'所属网格';

comment on column DM_enterpriseKey_Temp.employeeAmount is
'员工数量';

comment on column DM_enterpriseKey_Temp.partyMemberAmount is
'党员数量';

comment on column DM_enterpriseKey_Temp.industry is
'所属行业';


comment on column DM_enterpriseKey_Temp.isRiskEnterprise is
'是否危化企业（0为‘否’，1为''是''）';

comment on column DM_enterpriseKey_Temp.area is
'面积（单位为‘平方米’）';

comment on column DM_enterpriseKey_Temp.registeredCapital is
'注册资金（单位为‘万’）';

comment on column DM_enterpriseKey_Temp.name is
'企业名称';

comment on column DM_enterpriseKey_Temp.businessLicense is
'工商执照号码';

comment on column DM_enterpriseKey_Temp.manager is
'法人代表';

comment on column DM_enterpriseKey_Temp.hiddenCases is
'隐患情况';

comment on column DM_enterpriseKey_Temp.hiddenRectification is
'隐患整改情况';

comment on column DM_enterpriseKey_Temp.remark is
'备注';

comment on column DM_enterpriseKey_Temp.createUser is
'创建用户';

comment on column DM_enterpriseKey_Temp.updateUser is
'修改用户';

comment on column DM_enterpriseKey_Temp.fullPinyin is
'全拼';

comment on column DM_enterpriseKey_Temp.simplePinyin is
'简拼';

comment on column DM_enterpriseKey_Temp.orgInternalCode is
'所属责任区编号';

comment on column DM_enterpriseKey_Temp.telephone is
'法人电话';

comment on column DM_enterpriseKey_Temp.enterpriseTelephone is
'企业电话';

comment on column DM_enterpriseKey_Temp.mobileNumber is
'法人手机号码';

comment on column DM_enterpriseKey_Temp.fax is
'传真';

comment on column DM_enterpriseKey_Temp.address is
'地址';

comment on column DM_enterpriseKey_Temp.createDate is
'创建日期';

comment on column DM_enterpriseKey_Temp.updateDate is
'修改时间';

comment on column DM_enterpriseKey_Temp.isEmphasis is
'是否关注';
comment on column DM_enterpriseKey_Temp.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';


create sequence s_DM_enterpriseDownKey_TEMP
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_enterpriseDownKey_TEMP       数据管理_规下企业                              */
/*==============================================================*/

create table DM_enterpriseDownKey_TEMP  (
    id                   NUMBER(10)                      not null,
    orgId                NUMBER(10)                      not null,
    employeeAmount       NUMBER(10),
    partyMemberAmount    NUMBER(10),
    industry             NUMBER(10),
    isRiskEnterprise     NUMBER(1)                      default 0,
    area                 NUMBER(15,5),
    registeredCapital    NUMBER(15,5),
    name                 VARCHAR2(150)                   not null,
    type                 NUMBER(10)                      not null,
    businessLicense      VARCHAR2(60),
    manager          VARCHAR2(60)                    not null,
    hiddenCases          VARCHAR2(300),
    hiddenRectification  VARCHAR2(300),
    remark               VARCHAR2(600),
    createUser           VARCHAR2(32),
    updateUser           VARCHAR2(32),
    fullPinyin           VARCHAR2(30),
    simplePinyin         VARCHAR2(10),
    orgInternalCode      VARCHAR2(32)                    ,
    telephone            VARCHAR2(15),
    enterpriseTelephone  VARCHAR2(15),
    mobileNumber         VARCHAR2(11),
    fax                  VARCHAR2(15),
    imgUrl               VARCHAR2(300),
    address              VARCHAR2(150)                   not null,
    createDate           DATE                            not null,
    updateDate           DATE,
    isEmphasis           NUMBER(1)                      default 0,
    isEmphasisReason     VARCHAR2(300),
    isEmphasisDate       DATE,
    attentionExtent      NUMBER(10),
   
    claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
   constraint PKDM_ENTERPRISEDOWNTEMP primary key (id),
   constraint FKDM_ENTERPRISESDOWNORGTEMP foreign key (orgId)
         references organizations (id)
);
comment on table DM_enterpriseDownKey_TEMP is
'规下企业';

comment on column DM_enterpriseDownKey_TEMP.orgId is
'所属网格';

comment on column DM_enterpriseDownKey_TEMP.employeeAmount is
'员工数量';

comment on column DM_enterpriseDownKey_TEMP.partyMemberAmount is
'党员数量';

comment on column DM_enterpriseDownKey_TEMP.industry is
'所属行业';


comment on column DM_enterpriseDownKey_TEMP.isRiskEnterprise is
'是否危化企业（0为‘否’，1为''是''）';

comment on column DM_enterpriseDownKey_TEMP.area is
'面积（单位为‘平方米’）';

comment on column DM_enterpriseDownKey_TEMP.registeredCapital is
'注册资金（单位为‘万’）';

comment on column DM_enterpriseDownKey_TEMP.name is
'企业名称';

comment on column DM_enterpriseDownKey_TEMP.businessLicense is
'工商执照号码';

comment on column DM_enterpriseDownKey_TEMP.manager is
'法人代表';

comment on column DM_enterpriseDownKey_TEMP.hiddenCases is
'隐患情况';

comment on column DM_enterpriseDownKey_TEMP.hiddenRectification is
'隐患整改情况';

comment on column DM_enterpriseDownKey_TEMP.remark is
'备注';

comment on column DM_enterpriseDownKey_TEMP.createUser is
'创建用户';

comment on column DM_enterpriseDownKey_TEMP.updateUser is
'修改用户';

comment on column DM_enterpriseDownKey_TEMP.fullPinyin is
'全拼';

comment on column DM_enterpriseDownKey_TEMP.simplePinyin is
'简拼';

comment on column DM_enterpriseDownKey_TEMP.orgInternalCode is
'所属责任区编号';

comment on column DM_enterpriseDownKey_TEMP.telephone is
'法人电话';

comment on column DM_enterpriseDownKey_TEMP.enterpriseTelephone is
'企业电话';

comment on column DM_enterpriseDownKey_TEMP.mobileNumber is
'法人手机号码';

comment on column DM_enterpriseDownKey_TEMP.fax is
'传真';

comment on column DM_enterpriseDownKey_TEMP.address is
'地址';

comment on column DM_enterpriseDownKey_TEMP.createDate is
'创建日期';

comment on column DM_enterpriseDownKey_TEMP.updateDate is
'修改时间';

comment on column DM_enterpriseDownKey_TEMP.isEmphasis is
'是否关注';
comment on column DM_enterpriseDownKey_TEMP.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';

create sequence s_DM_otherAttentionPers_TEMP
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_otherAttentionPers_TEMP       其他人员                              */
/*==============================================================*/
create table DM_otherAttentionPers_TEMP
(
  ID                  NUMBER(10) not null,
  ORGID               NUMBER(10) not null,
  GENDER              NUMBER(10) not null,
  WORKUNIT            VARCHAR2(150),
  PROVINCE            VARCHAR2(30),
  CITY                VARCHAR2(30),
  DISTRICT            VARCHAR2(30),
  NATIVEPLACEADDRESS  VARCHAR2(150),
  CURRENTADDRESS      VARCHAR2(150),
  NAME                VARCHAR2(60) not null,
  USEDNAME            VARCHAR2(60),
  IDCARDNO            VARCHAR2(60),
  TELEPHONE           VARCHAR2(80),
  MOBILENUMBER        VARCHAR2(50),
  ORGINTERNALCODE     VARCHAR2(32),
  FULLPINYIN          VARCHAR2(30),
  SIMPLEPINYIN        VARCHAR2(10),
  REMARK              VARCHAR2(900),
  CREATEUSER          VARCHAR2(32) not null,
  UPDATEUSER          VARCHAR2(32),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  BIRTHDAY            DATE,
  FERRETOUTDATE       DATE,
  DRUGFRISTDATE       DATE,
  IMGURL              VARCHAR2(300),
  EMAIL               VARCHAR2(150),
  ISEMPHASIS          NUMBER(1) default 0,
  ISDEATH             NUMBER(1) default 0,
  CURRENTADDRESSTYPE  NUMBER(10),
  COMMUNITY           VARCHAR2(150),
  BLOCK               VARCHAR2(150),
  UNIT                VARCHAR2(150),
  ROOM                VARCHAR2(150),
  NATION              NUMBER(10),
  POLITICALBACKGROUND NUMBER(10),
  SCHOOLING           NUMBER(10),
  CAREER              NUMBER(10),
  MARITALSTATE        NUMBER(10),
  BLOODTYPE           NUMBER(10),
  FAITH               NUMBER(10),
  OTHERADDRESS        VARCHAR2(150),
  STATURE             NUMBER(10),
  ISHAVEHOUSE         NUMBER(1) default 0,
  NOHOUSEREASON       VARCHAR2(800),
  ISEMPHASISREASON    VARCHAR2(300),
  ISEMPHASISDATE      DATE,
  ATTENTIONEXTENT     NUMBER(10),
  ATTENTIONREASON     VARCHAR2(300),  --关注原因
  NATIVEPOLICESTATION  VARCHAR2(160),--户籍派出所
  oldcurrentaddress VARCHAR2(150),
  WORKCONDITION  VARCHAR2(300),
  meRemark  VARCHAR2(900),
  claimState             NUMBER(10) default 0,
	claimDate              DATE,
	claimUserName          VARCHAR2(4000),
	claimUserId            NUMBER(10),
	claimOrgId             NUMBER(10),
	importDepartmentId     NUMBER(10)   not null,
	oldOrgId		NUMBER(10),
	inId			NUMBER(10),
	dispose 		NUMBER(10) default 0,
	claimavailable         NUMBER(10) default 0,
	importDate              DATE,
	districtOrgId				  NUMBER(10),
	actualPopulationType VARCHAR2(50),
   constraint PKDM_otherAttentionPersTEMP primary key (id),
   constraint FKDM_otherAttentionPersTEMPORG foreign key (orgId)
         references organizations (id)
);

-- Add comments to the table 
comment on table DM_otherAttentionPers_TEMP
  is '其他人员信息';
-- Add comments to the columns 
comment on column DM_otherAttentionPers_TEMP.ID
  is '人员id';
comment on column DM_otherAttentionPers_TEMP.ORGID
  is '所属网格';
comment on column DM_otherAttentionPers_TEMP.GENDER
  is '性别';
comment on column DM_otherAttentionPers_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_otherAttentionPers_TEMP.PROVINCE
  is '省';
comment on column DM_otherAttentionPers_TEMP.CITY
  is '市';
comment on column DM_otherAttentionPers_TEMP.DISTRICT
  is '区县';
comment on column DM_otherAttentionPers_TEMP.NATIVEPLACEADDRESS
  is '户籍地详址';
comment on column DM_otherAttentionPers_TEMP.CURRENTADDRESS
  is '现在居住地';
comment on column DM_otherAttentionPers_TEMP.NAME
  is '其他人员姓名';
comment on column DM_otherAttentionPers_TEMP.USEDNAME
  is '曾用名';
comment on column DM_otherAttentionPers_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_otherAttentionPers_TEMP.TELEPHONE
  is '固定电话';
comment on column DM_otherAttentionPers_TEMP.MOBILENUMBER
  is '手机号码';
comment on column DM_otherAttentionPers_TEMP.ORGINTERNALCODE
  is '所属责任区编号';
comment on column DM_otherAttentionPers_TEMP.FULLPINYIN
  is '全拼';
comment on column DM_otherAttentionPers_TEMP.SIMPLEPINYIN
  is '简拼';
comment on column DM_otherAttentionPers_TEMP.REMARK
  is '备注';
comment on column DM_otherAttentionPers_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_otherAttentionPers_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_otherAttentionPers_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_otherAttentionPers_TEMP.UPDATEDATE
  is '修改时间';
comment on column DM_otherAttentionPers_TEMP.BIRTHDAY
  is '出生日期';
comment on column DM_otherAttentionPers_TEMP.FERRETOUTDATE
  is '查获日期';
comment on column DM_otherAttentionPers_TEMP.IMGURL
  is '图片链接地址';
comment on column DM_otherAttentionPers_TEMP.EMAIL
  is '电子邮箱';
  
comment on column DM_otherAttentionPers_TEMP.ISEMPHASIS
  is '是否关注';
  
comment on column DM_otherAttentionPers_TEMP.ISDEATH
  is '是否死亡';
  
comment on column DM_otherAttentionPers_TEMP.CURRENTADDRESSTYPE
  is '常住地址类型'; 
comment on column DM_otherAttentionPers_TEMP.COMMUNITY
  is '常住地址商品房小区';
comment on column DM_otherAttentionPers_TEMP.BLOCK
  is '常住地址商品房幢';
comment on column DM_otherAttentionPers_TEMP.UNIT
  is '常住地址商品房单元';
comment on column DM_otherAttentionPers_TEMP.ROOM
  is '常住地址商品房室';
comment on column DM_otherAttentionPers_TEMP.NATION
  is '民族';
comment on column DM_otherAttentionPers_TEMP.POLITICALBACKGROUND
  is '政治面貌';
comment on column DM_otherAttentionPers_TEMP.SCHOOLING
  is '文化程度';
comment on column DM_otherAttentionPers_TEMP.CAREER
  is '职业';
comment on column DM_otherAttentionPers_TEMP.MARITALSTATE
  is '婚姻状况';
comment on column DM_otherAttentionPers_TEMP.BLOODTYPE
  is '血型';
comment on column DM_otherAttentionPers_TEMP.FAITH
  is '宗教信仰';
comment on column DM_otherAttentionPers_TEMP.OTHERADDRESS
  is '临时居所';
comment on column DM_otherAttentionPers_TEMP.STATURE
  is '身高';
comment on column DM_otherAttentionPers_TEMP.ISHAVEHOUSE
  is '是否有房屋';
comment on column DM_otherAttentionPers_TEMP.NOHOUSEREASON
  is '无房原因';
comment on column DM_otherAttentionPers_TEMP.ATTENTIONEXTENT
  is '关注程度：1.一般；2.中等；3.严重';
comment on column DM_otherAttentionPers_TEMP.ATTENTIONREASON
  is '关注原因';
comment on column DM_otherAttentionPers_TEMP.NATIVEPOLICESTATION
  is '户籍派出所';      
  comment on column DM_otherAttentionPers_TEMP.WORKCONDITION
  is '工作情况';
  
  
  
 /*==============================================================*/
/* Sequence: s_DM_aidsPopulations_Temp         数据管理_艾滋病人员                          */
/*==============================================================*/
create sequence s_DM_aidsPopulations_Temp
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 /*==============================================================*/
/* Table: DM_aidsPopulations_Temp              数据管理_艾滋病人员                */
/*==============================================================*/
create table DM_aidsPopulations_Temp(
   id                   NUMBER(10)                      not null, 
   orgId                NUMBER(10)                      not null,
   orgInternalCode    VARCHAR2(50), 
   name               VARCHAR2(60)                      not null,
   gender               NUMBER(10)                      not null,
   idCardNo             VARCHAR2(60)                    not null,
   usedName             VARCHAR2(60),
   birthday             DATE,
   maritalState         NUMBER(10),
   nation               NUMBER(10),
   province             VARCHAR2(60)                    not null,   
   city                 VARCHAR2(60)                    not null,
   district             VARCHAR2(60)                    not null,
   nativePlaceAddress   VARCHAR2(150),   
   addressNo            VARCHAR2(60),
   currentAddress       VARCHAR2(150),
   currentAddressType   NUMBER(10),
   community            VARCHAR2(60),
   block         VARCHAR2(24),
   unit         VARCHAR2(18),
   room         VARCHAR2(30),
   otherAddress         VARCHAR2(150),
   politicalBackground  NUMBER(10),
   schooling        	NUMBER(10),
   faith                NUMBER(10),
   career               NUMBER(10),
   mobileNumber         VARCHAR2(50),
   telephone            VARCHAR2(80),
   simplePinyin         VARCHAR2(10)                    ,
   fullpinyin           VARCHAR2(30)                    ,
   remark               VARCHAR2(600),
   workUnit             VARCHAR2(150),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   isDeath              NUMBER(1)                      default 0,
   isEmphasisReason     VARCHAR2(300),
   isEmphasisDate       DATE,
   sourcesState         NUMBER(1) default 1,
   isHaveHouse      	NUMBER(1)  						default 0,
   noHouseReason	    VARCHAR2(800),
   IMGURL              VARCHAR2(300),
   STATURE             NUMBER(10) default 0,
   BLOODTYPE           NUMBER(10),
   email                VARCHAR2(150),
   actualPopulationType  varchar2(50),
   infectWay            number(10)                     ,
   violationsofthelaw   number(10)                     ,
   crimeType            number(10)                     ,
   receivedOrganization varchar2(60)                   ,
   receivedLevel        number(10)                     ,
   isEmphasis        	NUMBER(1)                      default 0,
   attentionextent		number(10),
   helpCircumstances    number(10),
   nativepolicestation  VARCHAR2(160),
   
    claimState             NUMBER(10) default 0,
   claimDate              DATE,
   claimUserName          VARCHAR2(4000),
   claimUserId            NUMBER(10),
   claimOrgId             NUMBER(10),
   importDepartmentId     NUMBER(10)                     not null,
   importDate   DATE,
   oldOrgId				  NUMBER(10),
   districtOrgId				  NUMBER(10),
   inId					  NUMBER(10),
   dispose number(10) default 0,
   claimAvailable number(10) default 0,
   
   constraint PKDM_AIDSPOPULATIONSTMP primary key (id),
   constraint FKDM_AIDSPOPULATIONSTEMPORG foreign key (orgId)
         references organizations (id)  
);
-- Add comments to the columns
comment on table DM_aidsPopulations_Temp
  is '艾滋病人员';
comment on column DM_aidsPopulations_Temp.ID
  is 'ID';
comment on column DM_aidsPopulations_Temp.orgId
  is '所属网格id';
comment on column DM_aidsPopulations_Temp.orgInternalCode
  is '所属网格code';
comment on column DM_aidsPopulations_Temp.name
  is '姓名';
comment on column DM_aidsPopulations_Temp.gender
  is '性别';
comment on column DM_aidsPopulations_Temp.idCardNo
  is '身份证号码';
comment on column DM_aidsPopulations_Temp.usedName
  is '曾用名';
comment on column DM_aidsPopulations_Temp.birthday
  is '出生日期';
comment on column DM_aidsPopulations_Temp.maritalState
  is '婚姻状况';
comment on column DM_aidsPopulations_Temp.nation
  is '民族';
comment on column DM_aidsPopulations_Temp.province
  is '户籍地址(省)';
comment on column DM_aidsPopulations_Temp.city
  is '户籍地址(市)';
comment on column DM_aidsPopulations_Temp.district
  is '户籍地址(县)';
comment on column DM_aidsPopulations_Temp.nativePlaceAddress
  is '户籍详址';
comment on column DM_aidsPopulations_Temp.addressNo
  is '地址编号';
comment on column DM_aidsPopulations_Temp.currentAddress
  is '常住地址';
comment on column DM_aidsPopulations_Temp.currentAddressType
  is '常住地址类型';
comment on column DM_aidsPopulations_Temp.community
  is '常住地址商品房小区';
comment on column DM_aidsPopulations_Temp.block
  is '常住地址商品房 幢';
comment on column DM_aidsPopulations_Temp.unit
  is '常住地址商品房 单元';
comment on column DM_aidsPopulations_Temp.room
  is '常住地址商品房  室';
comment on column DM_aidsPopulations_Temp.otherAddress
  is '临时居所';
comment on column DM_aidsPopulations_Temp.politicalBackground
  is '政治面貌';
comment on column DM_aidsPopulations_Temp.schooling
  is '文化程度';
comment on column DM_aidsPopulations_Temp.faith
  is '宗教信仰';
 comment on column DM_aidsPopulations_Temp.career
  is '职业';
comment on column DM_aidsPopulations_Temp.mobileNumber
  is '联系方式(手机号)';
comment on column DM_aidsPopulations_Temp.telephone
  is '联系方式(电话)'; 
comment on column DM_aidsPopulations_Temp.fullPinyin is
'全拼';

comment on column DM_aidsPopulations_Temp.simplePinyin is
'简拼';

comment on column DM_aidsPopulations_Temp.remark is
'备注';

comment on column DM_aidsPopulations_Temp.createUser is
'创建用户';

comment on column DM_aidsPopulations_Temp.updateUser is
'修改用户';

comment on column DM_aidsPopulations_Temp.createDate is
'创建日期';

comment on column DM_aidsPopulations_Temp.updateDate is
'修改时间';
comment on column DM_aidsPopulations_Temp.isDeath is
'是否死亡';
comment on column DM_aidsPopulations_Temp.isEmphasisReason
  is '关注原因';
comment on column DM_aidsPopulations_Temp.isEmphasisDate
  is '关注日期';
comment on column DM_aidsPopulations_Temp.sourcesState is 
'数据来源：1.录入；2.认领；3.已核实';
comment on column DM_aidsPopulations_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_aidsPopulations_Temp.isHaveHouse is
'是否有房屋';

comment on column DM_aidsPopulations_Temp.noHouseReason is
'无房原因';
comment on column DM_aidsPopulations_Temp.IMGURL
  is '头像';
comment on column DM_aidsPopulations_Temp.stature is
'身高';
comment on column DM_aidsPopulations_Temp.bloodType is
'血型';
comment on column DM_aidsPopulations_Temp.email is
'电子邮箱';
comment on column DM_aidsPopulations_Temp.actualPopulationType
  is '实口类型';
 comment on column DM_aidsPopulations_Temp.infectWay
  is '感染途径';
comment on column DM_aidsPopulations_Temp.violationsofthelaw
  is '违法情况';
comment on column DM_aidsPopulations_Temp.crimeType
  is '犯罪类型'; 
comment on column DM_aidsPopulations_Temp.receivedOrganization
  is '收治机构'; 
comment on column DM_aidsPopulations_Temp.receivedLevel
  is '收治机构所属层级';
 comment on column DM_aidsPopulations_Temp.isEmphasis
  is '是否关注';
comment on column DM_aidsPopulations_Temp.attentionextent
  is '关注程度';
comment on column DM_aidsPopulations_Temp.helpCircumstances
  is '帮扶情况';  
comment on column DM_aidsPopulations_Temp.nativepolicestation 
  is '户籍派出所';
  
comment on column DM_aidsPopulations_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_aidsPopulations_Temp.claimDate is
'认领日期';

comment on column DM_aidsPopulations_Temp.claimUserName is
'认领人用户名';

comment on column DM_aidsPopulations_Temp.claimUserId is
'认领人Id';

comment on column DM_aidsPopulations_Temp.claimOrgId is
'认领人网格';

comment on column DM_aidsPopulations_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_aidsPopulations_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_aidsPopulations_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_aidsPopulations_Temp.dispose is '是否经过处理(0:否)';
comment on column DM_aidsPopulations_Temp.claimAvailable is '是否可以认领(0:否)';
comment on column DM_aidsPopulations_Temp.districtOrgId is
'导入时的到县区的组织机构';

--数据管理子系统--部件管理--部件信息表
-- Create sequence
create sequence S_DM_DUSTBIN_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table DM_DUSTBIN_TEMP
(
  ID                  NUMBER(10) not null,
  ORGID               NUMBER(10) not null,
  ORGINTERNALCODE     VARCHAR2(32),
  PARTTYPE            NUMBER(10) not null,
  PARTNAME            NUMBER(10) not null,
  DUSTBINCODE         VARCHAR2(60) not null,
  ADDRESS             VARCHAR2(90),
  DEPTNAME            VARCHAR2(60) not null,
  HASVIDEO            NUMBER(1) default 0,
  OWNERSHIPUNITNAME   VARCHAR2(60),
  MAINTENANCEUNITNAME VARCHAR2(60),
  REMARK              VARCHAR2(600),
  IMGURL              VARCHAR2(300),
  ISEMPHASIS          NUMBER(1) default 0,
  LOGOUTTIME          DATE,
  LOGOUTREASON        VARCHAR2(300),
  CENTERLON           VARCHAR2(32),
  CENTERLAT           VARCHAR2(32),
  CENTERLON2          VARCHAR2(32),
  CENTERLAT2          VARCHAR2(32),
  CREATEUSER          VARCHAR2(32) not null,
  UPDATEUSER          VARCHAR2(32),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  CLAIMSTATE            NUMBER(10) default 0,
  CLAIMDATE             DATE,
  CLAIMUSERNAME         VARCHAR2(4000),
  CLAIMUSERID           NUMBER(10),
  CLAIMORGID            NUMBER(10),
  IMPORTDEPARTMENTID    NUMBER(10) not null,
  OLDORGID              NUMBER(10),
  INID                  NUMBER(10),
  DISPOSE               NUMBER(10) default 0,
  CLAIMAVAILABLE        NUMBER(10) default 0,
  IMPORTDATE            DATE,
  DISTRICTORGID         NUMBER(10)
);
-- Add comments to the table 
comment on table DM_DUSTBIN_TEMP
  is '部件信息';
-- Add comments to the columns 
comment on column DM_DUSTBIN_TEMP.ID
  is '部件信息id';
comment on column DM_DUSTBIN_TEMP.ORGID
  is '所属网格';
comment on column DM_DUSTBIN_TEMP.DUSTBINCODE
  is '部件标识码';
comment on column DM_DUSTBIN_TEMP.ADDRESS
  is '地址';
comment on column DM_DUSTBIN_TEMP.DEPTNAME
  is '主管部门名称';
comment on column DM_DUSTBIN_TEMP.HASVIDEO
  is '是否有视频流';
comment on column DM_DUSTBIN_TEMP.OWNERSHIPUNITNAME
  is '权属单位名称';
comment on column DM_DUSTBIN_TEMP.MAINTENANCEUNITNAME
  is '养护单位名称';
comment on column DM_DUSTBIN_TEMP.REMARK
  is '备注';
comment on column DM_DUSTBIN_TEMP.IMGURL
  is '图片路径';
comment on column DM_DUSTBIN_TEMP.ISEMPHASIS
  is '是否注销';
comment on column DM_DUSTBIN_TEMP.LOGOUTTIME
  is '注销时间';
comment on column DM_DUSTBIN_TEMP.LOGOUTREASON
  is '注销原因';
comment on column DM_DUSTBIN_TEMP.CENTERLON
  is '部件信息经度';
comment on column DM_DUSTBIN_TEMP.CENTERLAT
  is '部件信息纬度';
comment on column DM_DUSTBIN_TEMP.CREATEUSER
  is '创建用户';
comment on column DM_DUSTBIN_TEMP.UPDATEUSER
  is '修改用户';
comment on column DM_DUSTBIN_TEMP.CREATEDATE
  is '创建日期';
comment on column DM_DUSTBIN_TEMP.UPDATEDATE
  is '修改时间';
comment on column DM_DUSTBIN_TEMP.CLAIMSTATE
  is '认领状态：0未认领；1被未认领；10已认领；11被认领';
comment on column DM_DUSTBIN_TEMP.CLAIMDATE
  is '认领日期';
comment on column DM_DUSTBIN_TEMP.CLAIMUSERNAME
  is '认领人用户名';
comment on column DM_DUSTBIN_TEMP.CLAIMUSERID
  is '认领人Id';
comment on column DM_DUSTBIN_TEMP.CLAIMORGID
  is '认领人网格';
comment on column DM_DUSTBIN_TEMP.IMPORTDEPARTMENTID
  is '导入部门Id';
comment on column DM_DUSTBIN_TEMP.OLDORGID
  is '刚导入时的所属网格Id';
comment on column DM_DUSTBIN_TEMP.INID
  is '认领时插入到原库中数据id';
comment on column DM_DUSTBIN_TEMP.DISPOSE
  is '是否经过处理 0.未处理；1.处理过';
comment on column DM_DUSTBIN_TEMP.CLAIMAVAILABLE
  is '是否可认领 0.不可认领；1.可认领';
comment on column DM_DUSTBIN_TEMP.IMPORTDATE
  is '导入时间';
comment on column DM_DUSTBIN_TEMP.DISTRICTORGID
  is '导入时的到县区的组织机构';

-- Create/Recreate primary, unique and foreign key constraints 
alter table DM_DUSTBIN_TEMP
  add constraint PKDM_DUSTBIN_TEMP primary key (ID);
alter table DM_DUSTBIN_TEMP
  add constraint FK_DM_DUSTBIN_TEMP_ORG foreign key (ORGID)
  references ORGANIZATIONS (ID);

-- 实有房屋添加房产证地址字段
alter table DM_HOUSEINFO_TEMP add houseaddress VARCHAR2(200);
comment on column DM_HOUSEINFO_TEMP.houseaddress
  is '房产证地址';
alter table DM_RENTALHOUSE_TEMP add houseaddress VARCHAR2(200);
comment on column DM_RENTALHOUSE_TEMP.houseaddress
  is '房产证地址';
alter table DM_HOUSEINFO_TEMP modify community VARCHAR2(200);
alter table DM_RENTALHOUSE_TEMP modify community VARCHAR2(200);



create sequence s_DM_hospitals_Temp
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
/*==============================================================*/
/* Table: DM_hospitals_Temp         数据管理医院                                    */
/*==============================================================*/
create table DM_hospitals_Temp  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   kind                 NUMBER(10),
   type                 NUMBER(10),
   orgInternalCode      VARCHAR2(32)                    not null,
   anotherName          VARCHAR2(60),
   name		        VARCHAR2(90)                    not null,
   manager              VARCHAR2(60),  
   address              VARCHAR2(150)                   not null,
   director             VARCHAR2(60),
   affiliatedUnit       VARCHAR2(150),
   contactName          VARCHAR2(60),
   telephone            VARCHAR2(15),
   mobileNumber         VARCHAR2(11),
   email                VARCHAR2(50),
   fax                  VARCHAR2(15),
   remark               VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   attentionExtent      NUMBER(10),
   claimState           NUMBER(10) default 0,
   claimDate            DATE,
   claimUserName        VARCHAR2(4000),
   claimUserId          NUMBER(10),
   claimOrgId           NUMBER(10),
   importDepartmentId   NUMBER(10)   not null,
   oldOrgId		NUMBER(10),
   inId			NUMBER(10),
   dispose 		NUMBER(10) default 0,
   claimavailable       NUMBER(10) default 0,
   importDate           DATE,
   districtOrgId        NUMBER(10),
   constraint pkHospitalsTemp primary key (id),
   constraint fkHospitalsTempOrg foreign key (orgId) references organizations (id)
);

comment on table DM_hospitals_Temp is
'医院信息';

comment on column DM_hospitals_Temp.id is
'医院ID';

comment on column DM_hospitals_Temp.orgId is
'所属网格';

comment on column DM_hospitals_Temp.kind is
'医院性质';

comment on column DM_hospitals_Temp.type is
'医院类型';

comment on column DM_hospitals_Temp.orgInternalCode is
'所属网格编号';

comment on column DM_hospitals_Temp.anotherName is
'医院别名';

comment on column DM_hospitals_Temp.name is
'医院名称';

comment on column DM_hospitals_Temp.manager is
'法人代表';

comment on column DM_hospitals_Temp.address is
'医院地址';

comment on column DM_hospitals_Temp.director is
'医院院长';

comment on column DM_hospitals_Temp.affiliatedUnit is
'所属单位';

comment on column DM_hospitals_Temp.contactName is
'联系人姓名';

comment on column DM_hospitals_Temp.telephone is
'联系人固定电话';

comment on column DM_hospitals_Temp.mobileNumber is
'联系人手机号码';

comment on column DM_hospitals_Temp.email is
'联系人电子邮件';

comment on column DM_hospitals_Temp.fax is
'传真';

comment on column DM_hospitals_Temp.remark is
'备注';

comment on column DM_hospitals_Temp.createUser is
'创建人';

comment on column DM_hospitals_Temp.updateUser is
'修改人';

comment on column DM_hospitals_Temp.createDate is
'创建时间';

comment on column DM_hospitals_Temp.updateDate is
'修改时间';

comment on column DM_hospitals_Temp.claimState is
'认领状态：0未认领；1被未认领；10已认领；11被认领';

comment on column DM_hospitals_Temp.claimDate is
'认领日期';

comment on column DM_hospitals_Temp.claimUserName is
'认领人用户名';

comment on column DM_hospitals_Temp.claimUserId is
'认领人Id';

comment on column DM_hospitals_Temp.claimOrgId is
'认领人网格';

comment on column DM_hospitals_Temp.importDepartmentId is
'导入部门Id';

comment on column DM_hospitals_Temp.oldOrgId is
'刚导入时的所属网格Id';

comment on column DM_hospitals_Temp.inId is
'认领时插入到原库中数据id';

comment on column DM_hospitals_Temp.dispose  is
'是否经过处理 0.未处理；1.处理过';

comment on column DM_hospitals_Temp.claimavailable is
'是否可认领 0.不可认领；1.可认领';  
comment on column DM_hospitals_Temp.importDate
  is '导入时间';
 comment on column DM_hospitals_Temp.districtOrgId is
'导入时的到县区的组织机构';


create sequence S_DM_COMPANYPLACE_TEMP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: DM_COMPANYPLACE_TEMP         数据管理单位场所                                   */
/*==============================================================*/

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

  --添加索引
create index idx_dm_COMPANYPL_TEMP_DIORGID on DM_COMPANYPLACE_TEMP(DISTRICTORGID);
