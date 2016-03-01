/*==============================================================*/
/* Table: HOUSEHOLDSTAFFS    户籍人口拆分表                                         */
/*==============================================================*/

create table HOUSEHOLDSTAFFS_$shardCode$ 
(
   ID                   NUMBER(15)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(32),
   FAMILYID             NUMBER(10),
   ACCOUNTNUMBER        VARCHAR2(210),
   RESIDENCETYPE        NUMBER(10),
   RELATIONSHIPWITHHEAD NUMBER(10),
   RELATIONSHIPWITHHEADTEXT VARCHAR2(60),
   OUTGONE              NUMBER(1)            default 0,
   OUTREASONS           NUMBER(10),
   REASONSDATE          DATE,
   OUTPROVINCE          VARCHAR2(150),
   OUTCITY              VARCHAR2(150),
   OUTDISTRICT          VARCHAR2(150),
   GOOUTDETAILEDADDRESS VARCHAR2(900),
   HOMEPHONE            VARCHAR2(80),
   RESIDENTSTATUS       NUMBER(10),
   SETTLETIME           VARCHAR2(30),
   SOURCESSTATE         NUMBER(1)            default 1,
   OLDCURRENTADDRESS    VARCHAR2(150),
   IMMIGRATIONDATE      DATE,
   PROPERSTATIONCODE    VARCHAR2(18),
   PROPERSTATION        VARCHAR2(150),
   IMMIGRATIONSORT      NUMBER(10),
   IMMIGRATIONCAUSE     NUMBER(10),
   EMIGRATIONDATE       DATE,
   EMIGRATIONCODE       VARCHAR2(18),
   EMIGRATIONLAND       VARCHAR2(150),
   EMIGRATIONSORT       NUMBER(10),
   EMIGRATIONCAUSE      NUMBER(10),
   HEALTHSTATE          NUMBER(10),
   MILITARYSERVICE      NUMBER(10),
   ANNUALINCOME         NUMBER(15,2),
   INSURED              NUMBER(1)            default 0,
   OUTGONEDIRECTION     VARCHAR2(150),
   SOLDIERDEPENDENTS    NUMBER(1),
   LOWINCOME            NUMBER(1),
   HARDSTATE            VARCHAR2(150),
   ABROADDEPENDENTS     NUMBER(1),
   ABROADDEPENDENTSTYPE NUMBER(10),
   STATUS               NUMBER(1)            default 0,
   ISMOVED              NUMBER(1)            default 0,
   OCCUPATION           VARCHAR2(150),
   LOGOUT               NUMBER(1)            default 0 not null,
   LOGOUTREASON         VARCHAR2(300),
   LOGOUTDATE           DATE,
   CREATEUSER           VARCHAR2(60)         not null,
   UPDATEUSER           VARCHAR2(60),
   CREATEDATE           DATE                 not null,
   UPDATEDATE           DATE,
   MARKBIRTHDAY			DATE,
   MARKGENDER			NUMBER(10),
   constraint PK_HOUSEHOLDSTAFFS_ID_$shardCode$ primary key (ID),
   constraint FK_CENSUS_FAMILYID_$shardCode$ foreign key (FAMILYID)
   references CENSUSREGISTERFAMILYS (ID),
   constraint FK_ORGANIZATIONS_ORGID_$shardCode$ foreign key (ORGID)
   references ORGANIZATIONS (ID)
);

comment on column HOUSEHOLDSTAFFS_$shardCode$.ORGID is
'所属网格';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ORGINTERNALCODE is
'所属网格编号';

comment on column HOUSEHOLDSTAFFS_$shardCode$.FAMILYID is
'户籍家庭ID';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ACCOUNTNUMBER is
'户号';

comment on column HOUSEHOLDSTAFFS_$shardCode$.RESIDENCETYPE is
'户口类别';

comment on column HOUSEHOLDSTAFFS_$shardCode$.RELATIONSHIPWITHHEAD is
'与户主关系';

comment on column HOUSEHOLDSTAFFS_$shardCode$.RELATIONSHIPWITHHEADTEXT is
'与户主关系文本';

comment on column HOUSEHOLDSTAFFS_$shardCode$.HOMEPHONE is
'住宅电话';

comment on column HOUSEHOLDSTAFFS_$shardCode$.RESIDENTSTATUS is
'人户状态';

comment on column HOUSEHOLDSTAFFS_$shardCode$.SETTLETIME is
'落户时间';

comment on column HOUSEHOLDSTAFFS_$shardCode$.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';

comment on column HOUSEHOLDSTAFFS_$shardCode$.IMMIGRATIONDATE is
'迁入日期';

comment on column HOUSEHOLDSTAFFS_$shardCode$.PROPERSTATIONCODE is
'原驻地代码';

comment on column HOUSEHOLDSTAFFS_$shardCode$.PROPERSTATION is
'原驻地';

comment on column HOUSEHOLDSTAFFS_$shardCode$.IMMIGRATIONSORT is
'迁入类别';

comment on column HOUSEHOLDSTAFFS_$shardCode$.IMMIGRATIONCAUSE is
'迁入原因';

comment on column HOUSEHOLDSTAFFS_$shardCode$.EMIGRATIONDATE is
'迁出日期';

comment on column HOUSEHOLDSTAFFS_$shardCode$.EMIGRATIONCODE is
'迁出地代码';

comment on column HOUSEHOLDSTAFFS_$shardCode$.EMIGRATIONLAND is
'迁出地';

comment on column HOUSEHOLDSTAFFS_$shardCode$.EMIGRATIONSORT is
'迁出类别';

comment on column HOUSEHOLDSTAFFS_$shardCode$.EMIGRATIONCAUSE is
'迁出原因';

comment on column HOUSEHOLDSTAFFS_$shardCode$.HEALTHSTATE is
'健康状况';

comment on column HOUSEHOLDSTAFFS_$shardCode$.MILITARYSERVICE is
'兵役状况';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ANNUALINCOME is
'年收入(万)';

comment on column HOUSEHOLDSTAFFS_$shardCode$.INSURED is
'是否参加保险';

comment on column HOUSEHOLDSTAFFS_$shardCode$.OUTGONEDIRECTION is
'外出去向';

comment on column HOUSEHOLDSTAFFS_$shardCode$.SOLDIERDEPENDENTS is
'是否军属';

comment on column HOUSEHOLDSTAFFS_$shardCode$.LOWINCOME is
'低保户';

comment on column HOUSEHOLDSTAFFS_$shardCode$.HARDSTATE is
'困难状况';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ABROADDEPENDENTS is
'是否侨属';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ABROADDEPENDENTSTYPE is
'侨属类别';

comment on column HOUSEHOLDSTAFFS_$shardCode$.STATUS is
'人员状态';

comment on column HOUSEHOLDSTAFFS_$shardCode$.ISMOVED is
'是否迁移';

comment on column HOUSEHOLDSTAFFS_$shardCode$.OCCUPATION is
'字符串类型职业';

comment on column HOUSEHOLDSTAFFS_$shardCode$.LOGOUT is
'是否注销 0否 1是';

create index INDEX_HOLD_MARKBIRTHDAY_$shardCode$ on HOUSEHOLDSTAFFS_$shardCode$ (MARKBIRTHDAY);
create index INDEX_HOLD_MARKGENDER_$shardCode$ on HOUSEHOLDSTAFFS_$shardCode$ (MARKGENDER);
Create index idx_HOLD_OrgCode_$shardCode$ on householdStaffs_$shardCode$(orginternalcode);
Create index idx_HhsOrgCodeCDate_$shardCode$ on householdStaffs_$shardCode$(orginternalcode,createdate);
create index IDX_HOLD_BASEINFOID_$shardCode$ on HOUSEHOLDSTAFFS_$shardCode$ (BASEINFOID);
create index IDX_HOLD_ADSID_$shardCode$ on HOUSEHOLDSTAFFS_$shardCode$ (ADDRESSINFOID);
create index IDX_HOLD_FAMILYID_$shardCode$  on  householdstaffs_$shardCode$ (FAMILYID ASC);
create bitmap index IDX_HOUSEHOLD_LOGOUT_$shardCode$ ON householdstaffs_$shardCode$(LOGOUT);

/*==============================================================*/
/* Table: NURTURESWOMEN_$shardCode$  育龄妇女拆表                                        */
/*==============================================================*/
create table NURTURESWOMEN_$shardCode$
(
   ID                   NUMBER(15)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(50),
   FERTILITYSERVICESNO  VARCHAR2(150),
   MARRIAGEREGISTRATIONTIME DATE,
   ONECHILDOFCOUPLE     NUMBER(10),
   LICENSESITUATION     NUMBER(10),
   BOYNUMBER            NUMBER(2),
   GIRLNUMBER           NUMBER(2),
   LICENSETIME          DATE,
   CONTRACEPTIVEMETHOD  VARCHAR2(150),
   CONTRACEPTIVETIME    DATE,
   MANNAME              VARCHAR2(60),
   MARRIAGEORDIVORCETIME DATE,
   MANMARITALSTATE      NUMBER(10),
   MANIDCARDNO          VARCHAR2(60),
   MANFIRSTMARRIAGETIME DATE,
   MANMOBILENUMBER      VARCHAR2(50),
   MANTELEPHONE         VARCHAR2(80),
   MANBIRTHDAY          DATE,
   MANNATION            NUMBER(10),
   MANPOLITICALBACKGROUND NUMBER(10),
   MANRESIDENCETYPE     NUMBER(10),
   MANSCHOOLING         NUMBER(10),
   MANCAREER            NUMBER(10),
   MANWORKUNIT          VARCHAR2(150),
   MANPROVINCE          VARCHAR2(60),
   MANCITY              VARCHAR2(60),
   MANDISTRICT          VARCHAR2(60),
   MANNATIVEPLACEADDRESS VARCHAR2(60),
   MANCURRENTADDRESS    VARCHAR2(150),
   MANCURRENTADDRESSTYPE NUMBER(10),
   MANCOMMUNITY         VARCHAR2(60),
   MANBLOCK             VARCHAR2(24),
   MANUNIT              VARCHAR2(18),
   MANROOM              VARCHAR2(30),
   MANREMARK            VARCHAR2(900),
   SINGLECHILDCARDNO    VARCHAR2(150),
   CERTIFIEDUNIT        VARCHAR2(150),
   ISUNMARRIEDPREGNANT  NUMBER(1),
   ISLEVIED             NUMBER(1),
   ISMATERNITYCARD      NUMBER(1),
   MATERNITYCARDUNIT    VARCHAR2(150),
   MATERNITYCARDNO      VARCHAR2(90),
   MATERNITYCARDISSUETIME DATE,
   MATERNITYCARDVALIDITYTIME DATE,
   MATERNITYCARDCHECKTIME DATE,
   MATERNITYCARDREMARK  VARCHAR2(900),
   PREGNANTPHOTO        VARCHAR2(200),
   PREGNANTHEALTHSTATUS VARCHAR2(10),
   PREGNANTMOTHERHOUSE  VARCHAR2(150),
   HUSBANDHEALTHSTATUS  VARCHAR2(10),
   CONCEPTIONCONTROLDATE DATE,
   PAUSIMENIADATE       DATE,
   ISSIGNCOMPACT        NUMBER               default 0,
   CHILDCOUNT           NUMBER,
   DRAWLETTERCONDITION  NUMBER,
   CANCELDATE           DATE,
   ISCANCEL             NUMBER               default 0,
   CANCELREASON         VARCHAR2(4000),
   PERSONTYPE           VARCHAR2(10),
   RESIDENCENUMBER      VARCHAR2(32),
   HOUSEMASTERNAME      VARCHAR2(20),
   HOUSEMASTERRELATION  VARCHAR2(10),
   SINGLECHILDCDISSUETIME DATE,
   FIRSTMARRIAGETIME    DATE,
   ISDELETE             NUMBER(1)            default 0,
   RESIDENCETYPE        NUMBER(10),
   OLDCURRENTADDRESS    VARCHAR2(150),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(60)         not null,
   UPDATEUSER           VARCHAR2(60),
   CREATEDATE           DATE                 not null,
   UPDATEDATE           DATE,
   constraint PKNURTURESWOMEN_$shardCode$ primary key (ID),
   constraint FKNURTURESWOMEN_$shardCode$ORG foreign key (ORGID)
   references ORGANIZATIONS (ID)
);

comment on table NURTURESWOMEN_$shardCode$ is
'育妇对象表';

comment on column NURTURESWOMEN_$shardCode$.ORGID is
'所属网格';

comment on column NURTURESWOMEN_$shardCode$.ORGINTERNALCODE is
'部门内部编号';

comment on column NURTURESWOMEN_$shardCode$.FERTILITYSERVICESNO is
'生育服务证号';

comment on column NURTURESWOMEN_$shardCode$.MARRIAGEREGISTRATIONTIME is
'离婚时间';

comment on column NURTURESWOMEN_$shardCode$.ONECHILDOFCOUPLE is
'夫妻双方独生子女情况';

comment on column NURTURESWOMEN_$shardCode$.LICENSESITUATION is
'领证情况';

comment on column NURTURESWOMEN_$shardCode$.BOYNUMBER is
'男孩数';

comment on column NURTURESWOMEN_$shardCode$.GIRLNUMBER is
'女孩数';

comment on column NURTURESWOMEN_$shardCode$.LICENSETIME is
'领证时间';

comment on column NURTURESWOMEN_$shardCode$.CONTRACEPTIVEMETHOD is
'避孕方法';

comment on column NURTURESWOMEN_$shardCode$.CONTRACEPTIVETIME is
'避孕起始时间';

comment on column NURTURESWOMEN_$shardCode$.MANNAME is
'丈夫姓名';

comment on column NURTURESWOMEN_$shardCode$.MARRIAGEORDIVORCETIME is
'最近再婚时间';

comment on column NURTURESWOMEN_$shardCode$.MANMARITALSTATE is
'丈夫婚姻状况';

comment on column NURTURESWOMEN_$shardCode$.MANIDCARDNO is
'丈夫身份证号码';

comment on column NURTURESWOMEN_$shardCode$.MANFIRSTMARRIAGETIME is
'丈夫初婚时间';

comment on column NURTURESWOMEN_$shardCode$.MANMOBILENUMBER is
'丈夫联系手机';

comment on column NURTURESWOMEN_$shardCode$.MANTELEPHONE is
'丈夫固定电话';

comment on column NURTURESWOMEN_$shardCode$.MANBIRTHDAY is
'丈夫出生日期';

comment on column NURTURESWOMEN_$shardCode$.MANNATION is
'丈夫民族';

comment on column NURTURESWOMEN_$shardCode$.MANPOLITICALBACKGROUND is
'丈夫政治面貌';

comment on column NURTURESWOMEN_$shardCode$.MANRESIDENCETYPE is
'丈夫户口类型';

comment on column NURTURESWOMEN_$shardCode$.MANSCHOOLING is
'丈夫文化程度';

comment on column NURTURESWOMEN_$shardCode$.MANCAREER is
'丈夫职业';

comment on column NURTURESWOMEN_$shardCode$.MANWORKUNIT is
'丈夫工作单位或就读学校';

comment on column NURTURESWOMEN_$shardCode$.MANPROVINCE is
'丈夫户籍地 省';

comment on column NURTURESWOMEN_$shardCode$.MANCITY is
'丈夫户籍地 市';

comment on column NURTURESWOMEN_$shardCode$.MANDISTRICT is
'丈夫户籍地 区县';

comment on column NURTURESWOMEN_$shardCode$.MANNATIVEPLACEADDRESS is
'丈夫户籍地详址';

comment on column NURTURESWOMEN_$shardCode$.MANCURRENTADDRESS is
'丈夫常住地址';

comment on column NURTURESWOMEN_$shardCode$.MANCURRENTADDRESSTYPE is
'丈夫常住地址类型';

comment on column NURTURESWOMEN_$shardCode$.MANCOMMUNITY is
'丈夫常住地址 商品房 小区';

comment on column NURTURESWOMEN_$shardCode$.MANBLOCK is
'丈夫常住地址 商品房 幢';

comment on column NURTURESWOMEN_$shardCode$.MANUNIT is
'丈夫常住地址 商品房  单元';

comment on column NURTURESWOMEN_$shardCode$.MANROOM is
'丈夫常住地址 商品房 室';

comment on column NURTURESWOMEN_$shardCode$.MANREMARK is
'丈夫备注';

comment on column NURTURESWOMEN_$shardCode$.SINGLECHILDCARDNO is
'独生子女证号';

comment on column NURTURESWOMEN_$shardCode$.CERTIFIEDUNIT is
'发证单位';

comment on column NURTURESWOMEN_$shardCode$.ISUNMARRIEDPREGNANT is
'是否未婚先孕';

comment on column NURTURESWOMEN_$shardCode$.ISLEVIED is
'是否征收';

comment on column NURTURESWOMEN_$shardCode$.ISMATERNITYCARD is
'是否有婚育证';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDUNIT is
'孕育证发证单位';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDNO is
'证书编号';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDISSUETIME is
'发放证书时间';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDVALIDITYTIME is
'孕育证有效期';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDCHECKTIME is
'查验时间';

comment on column NURTURESWOMEN_$shardCode$.MATERNITYCARDREMARK is
'查验情况';

comment on column NURTURESWOMEN_$shardCode$.PREGNANTPHOTO is
'照片';

comment on column NURTURESWOMEN_$shardCode$.PREGNANTHEALTHSTATUS is
'健康状况';

comment on column NURTURESWOMEN_$shardCode$.PREGNANTMOTHERHOUSE is
'娘家地址';

comment on column NURTURESWOMEN_$shardCode$.HUSBANDHEALTHSTATUS is
'丈夫健康状况';

comment on column NURTURESWOMEN_$shardCode$.CONCEPTIONCONTROLDATE is
'节育年月';

comment on column NURTURESWOMEN_$shardCode$.PAUSIMENIADATE is
'绝经年月';

comment on column NURTURESWOMEN_$shardCode$.ISSIGNCOMPACT is
'已签订计生合同';

comment on column NURTURESWOMEN_$shardCode$.CHILDCOUNT is
'现有子女数';

comment on column NURTURESWOMEN_$shardCode$.DRAWLETTERCONDITION is
'独生证领证情况';

comment on column NURTURESWOMEN_$shardCode$.CANCELDATE is
'注销年月';

comment on column NURTURESWOMEN_$shardCode$.ISCANCEL is
'是否注销';

comment on column NURTURESWOMEN_$shardCode$.CANCELREASON is
'注销原因';

comment on column NURTURESWOMEN_$shardCode$.PERSONTYPE is
'人户状况';

comment on column NURTURESWOMEN_$shardCode$.RESIDENCENUMBER is
'户口簿号';

comment on column NURTURESWOMEN_$shardCode$.HOUSEMASTERNAME is
'户主姓名';

comment on column NURTURESWOMEN_$shardCode$.HOUSEMASTERRELATION is
'与户主关系';

comment on column NURTURESWOMEN_$shardCode$.FIRSTMARRIAGETIME is
'初婚时间';

comment on column NURTURESWOMEN_$shardCode$.ISDELETE is
'是否删除';

comment on column NURTURESWOMEN_$shardCode$.RESIDENCETYPE is
'户口类型';

comment on column NURTURESWOMEN_$shardCode$.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';

comment on column NURTURESWOMEN_$shardCode$.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';

comment on column NURTURESWOMEN_$shardCode$.ISEMPHASIS is
'是否关注';

comment on column NURTURESWOMEN_$shardCode$.CREATEUSER is
'创建用户';

comment on column NURTURESWOMEN_$shardCode$.UPDATEUSER is
'修改用户';

comment on column NURTURESWOMEN_$shardCode$.CREATEDATE is
'创建时间';

comment on column NURTURESWOMEN_$shardCode$.UPDATEDATE is
'修改时间';

create index idx_nu_$shardCode$_orgIntCodeAndisE on NURTURESWOMEN_$shardCode$ (
   orgInternalCode ASC,
   isEmphasis ASC
);

create index IDX_NUWOMEN_$shardCode$_BASEINFOID on NURTURESWOMEN_$shardCode$ (BASEINFOID);
create index IDX_NURTURESWOMEN_$shardCode$_ADSID on NURTURESWOMEN_$shardCode$ (ADDRESSINFOID);

/*==============================================================*/
/* Table: ELDERLYPEOPLE_$shardCode$  老年人拆表                     				*/
/*==============================================================*/
create table ELDERLYPEOPLE_$shardCode$ 
(
   ID                   NUMBER(15)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(32)         not null,
   ENTERWORKDATE        DATE,
   RETIREDATE           DATE,
   PENSION              NUMBER(11,2),
   LIVESTATUS           NUMBER(10),
   PEOPLETYPE           NUMBER(10),
   SPOUSESTATUS         NUMBER(10),
   CURRENTSTATUS        NUMBER(10),
   INCOMESOURCE         NUMBER(10),
   RETIREUNITANDDUTY    VARCHAR2(100),
   ELDERPEOPLEID        VARCHAR2(60),
   ZHIWU                VARCHAR2(60),
   BEIZHU               VARCHAR2(160),
   OLDCURRENTADDRESS    VARCHAR2(150),
   RESIDENCETYPE        NUMBER(10),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(32)         not null,
   CREATEDATE           DATE                 not null,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PKELDERLYPEOPLE_$shardCode$ primary key (ID),
   constraint fkELDERLYPEOPLE_$shardCode$Org foreign key (orgId)
		references organizations (id)
);

comment on table ELDERLYPEOPLE_$shardCode$ is
'老年人';

comment on column ELDERLYPEOPLE_$shardCode$.ID is
'ID';

comment on column ELDERLYPEOPLE_$shardCode$.ORGID is
'所属网格';

comment on column ELDERLYPEOPLE_$shardCode$.ORGINTERNALCODE is
'所属网格编号';

comment on column ELDERLYPEOPLE_$shardCode$.ENTERWORKDATE is
'参加工作日期';

comment on column ELDERLYPEOPLE_$shardCode$.RETIREDATE is
'离退休日期';

comment on column ELDERLYPEOPLE_$shardCode$.PENSION is
'退休金';

comment on column ELDERLYPEOPLE_$shardCode$.LIVESTATUS is
'居住情况';

comment on column ELDERLYPEOPLE_$shardCode$.PEOPLETYPE is
'特殊人员';

comment on column ELDERLYPEOPLE_$shardCode$.SPOUSESTATUS is
'配偶情况';

comment on column ELDERLYPEOPLE_$shardCode$.CURRENTSTATUS is
'目前情况';

comment on column ELDERLYPEOPLE_$shardCode$.INCOMESOURCE is
'经济来源';

comment on column ELDERLYPEOPLE_$shardCode$.RETIREUNITANDDUTY is
'离退休单位';

comment on column ELDERLYPEOPLE_$shardCode$.ELDERPEOPLEID is
'老年人证号';

comment on column ELDERLYPEOPLE_$shardCode$.ZHIWU is
'离退休单位职位';

comment on column ELDERLYPEOPLE_$shardCode$.BEIZHU is
'业务信息备注';

comment on column ELDERLYPEOPLE_$shardCode$.RESIDENCETYPE is
'户口类别';

comment on column ELDERLYPEOPLE_$shardCode$.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';

comment on column ELDERLYPEOPLE_$shardCode$.ATTENTIONEXTENT is
'关注程度：1.一般；2.中等；3.严重';

comment on column ELDERLYPEOPLE_$shardCode$.ISEMPHASIS is
'是否注销';

comment on column ELDERLYPEOPLE_$shardCode$.CREATEUSER is
'创建用户';

comment on column ELDERLYPEOPLE_$shardCode$.CREATEDATE is
'创建时间';

comment on column ELDERLYPEOPLE_$shardCode$.UPDATEUSER is
'修改用户';

comment on column ELDERLYPEOPLE_$shardCode$.UPDATEDATE is
'修改时间';


/*==============================================================*/
/* Index: idx_IY_orgIntCodeAndisEmphasis                        */
/*==============================================================*/
create index idx_el__$shardCode$orgIntCodeAndisE on ELDERLYPEOPLE_$shardCode$ (
   orgInternalCode ASC,
   isEmphasis ASC
);
create index IDX_ELDERLY_$shardCode$_BASEINFOID on ELDERLYPEOPLE_$shardCode$ (BASEINFOID);
create index IDX_ELDERLY_$shardCode$_ADSID on ELDERLYPEOPLE_$shardCode$ (ADDRESSINFOID);


/*==============================================================*/
/* Table: houseInfo       房屋拆分表                                  */
/*==============================================================*/
create table houseInfo_$shardCode$  (
   id                   NUMBER(15)                      not null,
   orgId				NUMBER(10)						not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   buildingId      		VARCHAR2(30),
   houseCode            VARCHAR2(150),
   addressType          NUMBER(10),
   community            VARCHAR2(200),
   block 				VARCHAR2(24),
   unit 				VARCHAR2(18),
   room 				VARCHAR2(30),
   address         		VARCHAR2(150)                   not null,
   isRentalHouse        NUMBER(2)			  			default 0 not null,
   buildingName      	VARCHAR2(300),
   buildingUses         NUMBER(10),
   propertyName      	VARCHAR2(300),
   houseOwner           VARCHAR2(150),
   houseOwnerIdCardNo   VARCHAR2(18),
   telephone            VARCHAR2(80),
   mobileNumber         VARCHAR2(50),
   houseDoorModel      	VARCHAR2(100),
   builtYear            VARCHAR2(4),
   houseStructure       NUMBER(10),
   houseArea            NUMBER(15,5),
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
   fullPinyin	        VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   certificateType      NUMBER(10),
   certificateNumbe     VARCHAR2(60),
   propertyPersonTel    VARCHAR2(80),
   propertyPersonMobile VARCHAR2(50),
   imgUrl               VARCHAR2(300),
   remark               VARCHAR2(900),
   centerX 				NUMBER(10),
   centerY 				NUMBER(10),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   builddatasId			NUMBER(10),
   centerLon            VARCHAR2(32),
   centerLat		    VARCHAR2(32),
   centerLon2			VARCHAR2(32),
   centerLat2   		VARCHAR2(32),
   featureId		    VARCHAR2(32),
   houseaddress         VARCHAR2(200),
   FLOORAREA 			NUMBER(10,2),
   ISHASTAPWATER 		NUMBER(1)  						default 0,
   ISHASNET 			NUMBER(1)  						default 0,
   ISHASTOILET 			NUMBER(1)  						default 0,
   ISDANGEROUSBUILDING 	NUMBER(1)  						default 0,
   OCCUPIEDNO 			VARCHAR2(30),
   OCCUPIEDADDRESS 		VARCHAR2(150),   
   SOURCESSTATE 		NUMBER(1)  						default 1,
   MemberNum           	NUMBER(5)            			default 0,
   constraint pkHouseInfos_$shardCode$ primary key (id),
   constraint fkHouseInfoOrg_$shardCode$ foreign key (orgId)
   references organizations (id)
);

comment on table houseInfo_$shardCode$ is
'住房信息';

comment on column houseInfo_$shardCode$.id is
'主键';

comment on column houseInfo_$shardCode$.orgId is
'所属网格';

comment on column houseInfo_$shardCode$.orgInternalCode is
'网格编号';

comment on column houseInfo_$shardCode$.houseCode is
'住房编号';

comment on column houseInfo_$shardCode$.address is
'住房地址';

comment on column houseInfo_$shardCode$.isRentalHouse is
'是否出租房';

comment on column houseInfo_$shardCode$.addressType is
'常住地址类型';

comment on column houseInfo_$shardCode$.community is
'常住地址商品房 小区';

comment on column houseInfo_$shardCode$.block is
'常住地址商品房 幢';

comment on column houseInfo_$shardCode$.unit is
'常住地址商品房 单元';

comment on column houseInfo_$shardCode$.room is
'常住地址商品房  室';

comment on column houseInfo_$shardCode$.houseOwner is
'代表人/业主';

comment on column houseInfo_$shardCode$.houseOwnerIdCardNo is
'业主身份证号码';

comment on column houseInfo_$shardCode$.mobileNumber is
'业主联系手机';

comment on column houseInfo_$shardCode$.telephone is
'业主联系电话';

comment on column houseInfo_$shardCode$.houseStructure is
'住房结构';

comment on column houseInfo_$shardCode$.houseArea is
'住房面积';

comment on column houseInfo_$shardCode$.remark is
'备注';

comment on column houseInfo_$shardCode$.buildingName is
'建筑物名称';

comment on column houseInfo_$shardCode$.buildingUses is
'建筑物用途 ';

comment on column houseInfo_$shardCode$.propertyName is
'物业管理单位名称 ';

comment on column houseInfo_$shardCode$.houseDoorModel is
'房屋户型';

comment on column houseInfo_$shardCode$.builtYear is
'建成年份';

comment on column houseInfo_$shardCode$.houseUses is
'房屋用途';

comment on column houseInfo_$shardCode$.houseSource is
'房屋来源';

comment on column houseInfo_$shardCode$.ownProperty is
'自有产权';

comment on column houseInfo_$shardCode$.rentalBuildings is
'租赁公房';

comment on column houseInfo_$shardCode$.housingVouchers is
'房屋凭证';

comment on column houseInfo_$shardCode$.houseRightNumber is
'房屋权证号';

comment on column houseInfo_$shardCode$.houseRightNumberDate is
'房屋权证发证时间';

comment on column houseInfo_$shardCode$.landDocuments is
'土地凭证';

comment on column houseInfo_$shardCode$.landRightsNumbe is
'土地权证号';

comment on column houseInfo_$shardCode$.landRightsNumbeDate is
'土地权证发证时间';

comment on column houseInfo_$shardCode$.propertyTypes is
'产权人类型';

comment on column houseInfo_$shardCode$.name is
'产权人名称';

comment on column houseInfo_$shardCode$.certificateType is
'证件类型';

comment on column houseInfo_$shardCode$.certificateNumbe is
'证件号码';

comment on column houseInfo_$shardCode$.propertyPersonTel is
'产权人联系电话';

comment on column houseInfo_$shardCode$.propertyPersonMobile is
'产权人联系手机';

comment on column houseInfo_$shardCode$.createUser is
'创建用户';

comment on column houseInfo_$shardCode$.updateUser is
'更新用户名';

comment on column houseInfo_$shardCode$.updateDate is
'更新用户';

comment on column houseInfo_$shardCode$.createDate is
'创建日期';

comment on column houseInfo_$shardCode$.builddatasId is
'楼宇编号';

comment on column houseInfo_$shardCode$.centerlon is
'二维地图经度';

comment on column houseInfo_$shardCode$.centerlat is
'二维地图纬度';

comment on column houseInfo_$shardCode$.featureid is
'二维地图热区Id';
comment on column houseInfo_$shardCode$.FLOORAREA is
'占地面积';
comment on column houseInfo_$shardCode$.ISHASTAPWATER is
'自来水是否已入户';
comment on column houseInfo_$shardCode$.ISHASNET is
'互连网是否已入户';
comment on column houseInfo_$shardCode$.ISHASTOILET is
'是否有卫生厕所';
comment on column houseInfo_$shardCode$.ISDANGEROUSBUILDING is
'是否危房';
comment on column houseInfo_$shardCode$.OCCUPIEDNO is
'出租房房产证';
comment on column houseInfo_$shardCode$.OCCUPIEDADDRESS is
'出租房地址';
comment on column houseInfo_$shardCode$.houseaddress is 
'房产证地址';
comment on column houseInfo_$shardCode$.MemberNum is 
'居住人数';
create index INDEXHIORGCODECREATEDATE_$shardCode$ on HOUSEINFO_$shardCode$ (ORGINTERNALCODE, CREATEDATE);
create index INDEXHIHOUSECODEORGID_$shardCode$ on HOUSEINFO_$shardCode$ (HOUSECODE, ORGID);
create index INDEXHIORGINTERNALCODE_$shardCode$ on HOUSEINFO_$shardCode$ (ORGINTERNALCODE);
create index INDEXHOUSEINFOADDRESS_$shardCode$ on HOUSEINFO_$shardCode$ (ADDRESS);
create index IND_HOUSEINFO_ORGID_$shardCode$ on HOUSEINFO_$shardCode$ (ORGID);
create index IND_HSEINFO_BDIDHUSE_$shardCode$ on HOUSEINFO_$shardCode$ (BUILDDATASID, HOUSEUSES);
create index IND_HSEINFO_FTID_$shardCode$ on HOUSEINFO_$shardCode$ (FEATUREID);
