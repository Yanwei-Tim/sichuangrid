--地图加入艾滋病人员--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'艾滋病人员','AIDSPOPULATIONS','AIDSPOPULATION','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='AIDSPOPULATIONS'),
'姓名','name',
'/baseinfo/aidspopulationsManage/dispatchOperate.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=aidspopulations'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');


--数据管理加入艾滋病人员--

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
  is '身份证号';
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
  is '现居地址';
comment on column DM_aidsPopulations_Temp.currentAddressType
  is '现居地址类型';
comment on column DM_aidsPopulations_Temp.community
  is '现居地址商品房小区';
comment on column DM_aidsPopulations_Temp.block
  is '现居地址商品房 幢';
comment on column DM_aidsPopulations_Temp.unit
  is '现居地址商品房 单元';
comment on column DM_aidsPopulations_Temp.room
  is '现居地址商品房  室';
comment on column DM_aidsPopulations_Temp.otherAddress
  is '其他地址';
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
'工作单位';
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

--艾滋病人员(数据管理)的权限--
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
	values (s_permissions.nextval, '艾滋病人员', 'aidspopulationsTemp', 1, '重点人员', 1, (select id from permissions where ename='attentionPopulationDataManagement'), '', '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=aidspopulationsTemp', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
	values (s_permissions.nextval, '艾滋病人员导入', 'aidspopulationsTempImport', 1, '艾滋病人员', 1, (select id from permissions where ename='aidspopulationsTemp'), '', '/hotModuel/dataManage/population/aidspopulationsTempManage/aidsPopulationsTempList.ftl', '/dataManage/aidspopulationsTempManage/aidsPopulationsTempList.jsp', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '搜索', 'importSearchAidspopulationsTemp', 0, '艾滋病人员导入', 1, (select id from permissions where ename = 'aidspopulationsTempImport'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '高级搜索', 'importAdvancedSearchAidspopulationsTemp', 0, '艾滋病人员导入', 1, (select id from permissions where ename = 'aidspopulationsTempImport'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '导入', 'importAidspopulationsTemp', 0, '艾滋病人员导入', 1, (select id from permissions where ename = 'aidspopulationsTempImport'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '查看', 'importViewAidspopulationsTemp', 0, '艾滋病人员导入', 1, (select id from permissions where ename = 'aidspopulationsTempImport'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '批量删除', 'importDeleteAidspopulationsTemp', 0, '艾滋病人员导入', 1, (select id from permissions where ename = 'aidspopulationsTempImport'), ' ','', '', null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
	values (s_permissions.nextval, '艾滋病人员认领', 'aidspopulationsTempclaim', 1, '艾滋病人员', 1, (select id from permissions where ename='aidspopulationsTemp'), '', '/hotModuel/dataManage/population/aidspopulationsTempManage/aidsPopulationsTempList.ftl?mode=claimList', '/dataManage/aidspopulationsTempManage/aidsPopulationsTempList.jsp?mode=claimList', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '搜索', 'claimSearchAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '高级搜索', 'claimAdvancedSearchAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '查看', 'claimViewAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '修改', 'claimUpdateAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '认领', 'claimAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '分步认领', 'stepClaimAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '撤销认领', 'unDoClaimAidspopulationsTemp', 0, '艾滋病人员认领', 1, (select id from permissions where ename = 'aidspopulationsTempclaim'), ' ','', '', null);

commit;


-- Add/modify columns 
alter table DM_RECTIFICATIVEPERSONS_TEMP add accusation varchar2(150);
-- Add comments to the columns 
comment on column DM_RECTIFICATIVEPERSONS_TEMP.accusation
  is '罪名';
