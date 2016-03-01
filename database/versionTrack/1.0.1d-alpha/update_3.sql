 /*==============================================================*/
/* Sequence: s_aidsPopulations         艾滋病人员序列                          */
/*==============================================================*/
create sequence s_aidsPopulations
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 /*==============================================================*/
/* Table: aidsPopulations              艾滋病人员信息表                     */
/*==============================================================*/
create table aidsPopulations(
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
   constraint pkAidsPopulations primary key (id),
   constraint fkAidsPopulationsOrg foreign key (orgId)
         references organizations (id)  
);
-- Add comments to the columns
comment on table aidsPopulations
  is '艾滋病人员';
comment on column aidsPopulations.ID
  is 'ID';
comment on column aidsPopulations.orgId
  is '所属网格id';
comment on column aidsPopulations.orgInternalCode
  is '所属网格code';
comment on column aidsPopulations.name
  is '姓名';
comment on column aidsPopulations.gender
  is '性别';
comment on column aidsPopulations.idCardNo
  is '身份证号';
comment on column aidsPopulations.usedName
  is '曾用名';
comment on column aidsPopulations.birthday
  is '出生日期';
comment on column aidsPopulations.maritalState
  is '婚姻状况';
comment on column aidsPopulations.nation
  is '民族';
comment on column aidsPopulations.province
  is '户籍地址(省)';
comment on column aidsPopulations.city
  is '户籍地址(市)';
comment on column aidsPopulations.district
  is '户籍地址(县)';
comment on column aidsPopulations.nativePlaceAddress
  is '户籍详址';
comment on column aidsPopulations.addressNo
  is '地址编号';
comment on column aidsPopulations.currentAddress
  is '现居地址';
comment on column aidsPopulations.currentAddressType
  is '现居地址类型';
comment on column aidsPopulations.community
  is '现居地址商品房小区';
comment on column aidsPopulations.block
  is '现居地址商品房 幢';
comment on column aidsPopulations.unit
  is '现居地址商品房 单元';
comment on column aidsPopulations.room
  is '现居地址商品房  室';
comment on column aidsPopulations.otherAddress
  is '其他地址';
comment on column aidsPopulations.politicalBackground
  is '政治面貌';
comment on column aidsPopulations.schooling
  is '文化程度';
comment on column aidsPopulations.faith
  is '宗教信仰';
 comment on column aidsPopulations.career
  is '职业';
comment on column aidsPopulations.mobileNumber
  is '联系方式(手机号)';
comment on column aidsPopulations.telephone
  is '联系方式(电话)'; 
comment on column aidsPopulations.fullPinyin is
'全拼';

comment on column aidsPopulations.simplePinyin is
'简拼';

comment on column aidsPopulations.remark is
'备注';

comment on column aidsPopulations.createUser is
'创建用户';

comment on column aidsPopulations.updateUser is
'修改用户';

comment on column aidsPopulations.createDate is
'创建日期';

comment on column aidsPopulations.updateDate is
'修改时间';
comment on column aidsPopulations.isDeath is
'是否死亡';
comment on column aidsPopulations.isEmphasisReason
  is '关注原因';
comment on column aidsPopulations.isEmphasisDate
  is '关注日期';
comment on column aidsPopulations.sourcesState is 
'数据来源：1.录入；2.认领；3.已核实';
comment on column aidsPopulations.workUnit is
'工作单位';
comment on column aidsPopulations.isHaveHouse is
'是否有房屋';

comment on column aidsPopulations.noHouseReason is
'无房原因';
comment on column aidsPopulations.IMGURL
  is '头像';
comment on column aidsPopulations.stature is
'身高';
comment on column aidsPopulations.bloodType is
'血型';
comment on column aidsPopulations.email is
'电子邮箱';
comment on column aidsPopulations.actualPopulationType
  is '实口类型';
 comment on column aidsPopulations.infectWay
  is '感染途径';
comment on column aidsPopulations.violationsofthelaw
  is '违法情况';
comment on column aidsPopulations.crimeType
  is '犯罪类型'; 
comment on column aidsPopulations.receivedOrganization
  is '收治机构'; 
comment on column aidsPopulations.receivedLevel
  is '收治机构所属层级';
 comment on column aidsPopulations.isEmphasis
  is '是否关注';
comment on column aidsPopulations.attentionextent
  is '关注程度';
comment on column aidsPopulations.helpCircumstances
  is '帮扶情况';  
comment on column aidsPopulations.nativepolicestation 
  is '户籍派出所';
  
  
  
--艾滋病人员的权限添加--

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
	values (s_permissions.nextval, '艾滋病人员', 'aidspopulationsManagement', 1, '重点人员', 1, (select id from permissions where ename='peopleInformation'), '', '/hotModuel/aidsPopulations/aidsPopulationsList.ftl', '/hotModuel/aidsPopulations/aidspopulationsStatistics.ftl', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '新增', 'addAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '修改', 'updateAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '查看', 'viewAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '删除', 'deleteAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '查询', 'searchAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '导入', 'importAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '导出', 'downAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '取消关注', 'cancelAttendedAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '重新关注', 'attendedAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
	values (s_permissions.nextVal, '取消死亡', 'cancelDeathAidspopulations', 0, '艾滋病人员', 1, (select id from permissions where ename = 'aidspopulationsManagement'), ' ','', '', null);


--艾滋病人员的字典项--
insert into propertydomains values(s_propertyDomains.NEXTVAL ,'感染途径',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='感染途径'),
0,1,'体液传播','tycb','tiyechuanbo','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='感染途径'),
0,2,'血液传播','xycb','xueyechuanbo','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='感染途径'),
0,3,'母婴垂直传播','myczcb','muyingchuizhichuanbo','admin',sysdate);

insert into propertydomains values(s_propertyDomains.NEXTVAL ,'违法情况',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,1,'扰乱公共秩序','rlggzx','raoluangonggongzhixu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,2,'妨害公共安全','fhggaq','fanghaigonggonganquan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,3,'侵犯人身权利、财产权利','qfrsql','qinfanrenshenquanli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,4,'妨害社会管理','fhshgl','fanghaishehuiguanli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,5,'具有社会危害性','jyshwhx','juyoushehuiweihaixing','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='违法情况'),
0,6,'无','w','wu','admin',sysdate);

insert into propertydomains values(s_propertyDomains.NEXTVAL ,'犯罪类型',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,1,'危害国家安全罪','whgjaqz','weihaiguojiaanquanzui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,2,'危害公共安全罪','whggaqz','weihaigonggonganquanzui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,3,'破坏社会主义市场经济秩序罪','phscjjzxz','pohuaishhzyscjingjizhixuzui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,4,'侵犯公民人身权利、民主权利罪','qfgmrsql','qinfangongminrenshenquanli','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,5,'侵犯财产罪','qfccz','qinfancaichanzui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,6,'妨害社会管理秩序罪','fhshglzxz','fanghaishehuiguanlizhixuzui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,7,'危害国防利益罪','whgflyz','weihaiguofangliyizui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,8,'贪污贿赂罪','twhlz','贪污贿赂罪','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,9,'渎职罪','dzz','duzhizui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='犯罪类型'),
0,10,'无','w','wu','admin',sysdate);


insert into propertydomains values(s_propertyDomains.NEXTVAL ,'收治机构所属层级',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='收治机构所属层级'),
0,1,'省级','sj','shengji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='收治机构所属层级'),
0,2,'市级','sj','shiji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='收治机构所属层级'),
0,3,'县(区)级','x(q)j','xian(qu)ji','admin',sysdate);

commit;
  