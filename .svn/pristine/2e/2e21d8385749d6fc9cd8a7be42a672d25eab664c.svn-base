drop sequence s_gis2DLayers;
drop table gis2DLayers;
drop sequence hourse_squ;
drop table hourseInfo;


--------------二维地图区域管理表--------------------
create sequence s_gis2DLayers
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
create table gis2DLayers
(
	id					number(10)		not null,
	organization    	number(10) 		not null,
	orgInternalCode 	varchar2(15) 	not null,
	centerX             varchar2(20) 	not null,
	centerY             varchar2(20) 	not null,
 	zoom                number(2)    	not null,
  	points              CLOB         	not null,
  	minLon             	varchar2(20) 	not null,
  	maxLon             	varchar2(20) 	not null,
  	minLat             	varchar2(20) 	not null,
  	maxLat             	varchar2(20) 	not null,
  	centerX2             varchar2(20) 	,
	centerY2             varchar2(20) 	,
  	points2              CLOB         	,
  	minLon2             varchar2(20) 	,
  	maxLon2             varchar2(20) 	,
  	minLat2             varchar2(20) 	,
  	maxLat2             varchar2(20) 	,
   	remark          	varchar2(600),
  	createUser      	varchar2(20) 	not null,
  	createDate      	date not null,
  	updateUser      	varchar2(20),
  	updateDate      	date,
  	constraint pkGis2DLayers primary key (id)
);
comment on table gis2DLayers
  is '二维地图区域管理表';
comment on column gis2DLayers.organization
  is '所属网格ID';
comment on column gis2DLayers.orgInternalCode
  is '网格编号';
comment on column gis2DLayers.centerX 
  is '中心点X';
comment on column gis2DLayers.centerY 
  is '中心点Y';
comment on column gis2DLayers.zoom 
  is '地图层级';
comment on column gis2DLayers.points 
  is '坐标集';
comment on column gis2DLayers.remark 
  is '备注';
comment on column gis2DLayers.createUser 
  is '创建用户';
comment on column gis2DLayers.createDate 
  is '创建时间';
comment on column gis2DLayers.updateUser 
  is '更新用户';
comment on column gis2DLayers.updateDate 
  is '更新时间';
comment on column gis2DLayers.minLon 
  is '最小经度';
comment on column gis2DLayers.maxLon 
  is '最大经度';
comment on column gis2DLayers.minLat 
  is '最小纬度';
comment on column gis2DLayers.maxLat 
  is '最大纬度';
comment on column gis2DLayers.centerX2 
  is '2维中心点X';
comment on column gis2DLayers.centerY2 
  is '2维中心点Y';
comment on column gis2DLayers.points2 
  is '2维坐标集';
comment on column gis2DLayers.minLon2 
  is '2维最小经度';
comment on column gis2DLayers.maxLon2 
  is '2维最大经度';
comment on column gis2DLayers.minLat2 
  is '2维最小纬度';
comment on column gis2DLayers.maxLat2 
  is '2维最大纬度';
  
create index idx_gis2Dlayers_org on gis2dlayers(organization);

 create sequence hourse_squ
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;


---------------二维地图建筑物（房屋）表-----------------
create table hourseInfo 
(
    id 				 number(10)    		not null,
    createUser       varchar2(32)      	not null,  
    createDate       date              	not null,
    updateUser       varchar2(32),
    updateDate       date,
    centerLon        varchar2(32)      	not null,
    centerLat        varchar2(32)      	not null,
    orgid			 number(10)			not null,
    orginternalcode  varchar2(32)		not null,
    featureId        varchar2(60),
    centerLon2        varchar2(32),
    centerLat2        varchar2(32),
    constraint pkHourseInfo primary key (id)
);
comment on table hourseInfo
is '二维地图建筑物（房屋）表';
comment on column hourseInfo.id
is '表id';
comment on column hourseInfo.createUser
is '创建人';
comment on column hourseInfo.createDate
is '创建时间';
comment on column hourseInfo.updateUser
is '修改人';
comment on column hourseInfo.updateDate
is '修改日期';
comment on column hourseInfo.centerLon
is '经度';
comment on column hourseInfo.centerLat
is '纬度';
comment on column hourseInfo.orgid
is '所属网格';
comment on column hourseInfo.orginternalcode
is '所属责任区编号';

create sequence gisTypeManages_squ
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table gisTypeManages
(
  id         			number(10) 		not null,
  name       			varchar2(60) 	not null,
  tableName  			varchar2(90) 	not null,
  key        			varchar2(60) 	not null,
  InnerName  			varchar2(60),
  innerKey   			varchar2(60),
  orgFiled				varchar2(60),
  checked    			number(1) 		default 0,
  createUser 			varchar2(32),
  createDate 			date,
  updateuser 			varchar2(32),
  updateDate 			date,
  permissionName 		VARCHAR2(100),
  constraint pkGisTypeManages primary key (id)
);
comment on table gisTypeManages
is 'gis类型管理表';
comment on column gisTypeManages.id
is '表id';
comment on column gisTypeManages.name
is '名称';
comment on column gisTypeManages.tableName
is '表名';
comment on column gisTypeManages.key
is 'key';
comment on column gisTypeManages.InnerName
is '父类类型名称';
comment on column gisTypeManages.innerKey
is '父类类型key';
comment on column gisTypeManages.checked
is '选中状态';
comment on column gisTypeManages.orgFiled 
is '所属网格字段';
comment on column gisTypeManages.permissionName 
is '权限名称';


--------------gis二维地图模块管理表--------------------
create sequence s_gisModuleManages
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table gisModuleManages
(
  id                    number(10) 						not null,
  tableName             varchar2(60) 					not null,
  moduleName            varchar2(60) 					not null,
  isHasSonClass        	number(1) 						default 0,
  isSystemAttribute		number(1) 						default 0,		
  isBusinessLayer		number(1)						default 0,	
  isPopulation			number(1)						default 0,	
  modetype				varchar2(300),
  orgFiled				varchar2(60),
  createUser           	VARCHAR2(32)                    not null,
  updateUser           	VARCHAR2(32),
  createDate           	DATE                            not null,
  updateDate           	DATE,
  permissionName 		VARCHAR2(100),
  constraint 	pkgisModuleManages primary key (id)
);
comment on table gisModuleManages
is 'gis父类管理表';
comment on column gisModuleManages.id
is '表id';
comment on column gisModuleManages.tableName
is '表名';
comment on column gisModuleManages.moduleName
is '模块名';
comment on column gisModuleManages.isHasSonClass
is '是否存在子类';
comment on column gismodulemanages.isSystemAttribute
is '是否系统属性';
comment on column gismodulemanages.isBusinessLayer
is '是否业务图层(0基础图层 1业务图层)';
comment on column gismodulemanages.isPopulation
is '是否常住人口';
comment on column gisModuleManages.modetype 
is '决定进入servcie的类型(处理现在特殊类型)';
comment on column gisModuleManages.orgFiled 
is '所属网格字段';
comment on column gisModuleManages.permissionName 
is '权限名称';



--------gis功能表-----------
create sequence s_gisFunction
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table gisFunction
(
  id                     number(10)       not null,
  boundEventName         varchar2(60),
  unBoundEventName       varchar2(60),
  event                  number(10),
  boundEventIsValid      number(1)        default 0,
  unBoundEventIsValid    number(1)        default 0,
  sonClassId             number(10),   
  moduleId               number(10),
  titleName  			 varchar2(60),
  titleValue  			 varchar2(60),
  detailsUrl   			 varchar2(600),
  iconUrl				 varchar2(600),
  isViewIcon			 number(1) 		  default 0,
  searchFieldAName		 VARCHAR2(60),
  searchFieldA			 VARCHAR2(60),
  searchFieldBName		 VARCHAR2(60),
  searchFieldB			 VARCHAR2(60),
  searchFieldCName		 VARCHAR2(60),
  searchFieldC			 VARCHAR2(60),
  fieldNameA			 VARCHAR2(60),
  fieldA				 VARCHAR2(60),
  fieldNameB			 VARCHAR2(60),
  fieldB				 VARCHAR2(60),
  fieldNameC			 VARCHAR2(60),
  fieldC				 VARCHAR2(60),
  fieldNameD			 VARCHAR2(60),
  fieldD				 VARCHAR2(60),
  fieldNameE			 VARCHAR2(60),
  fieldE				 VARCHAR2(60),  
  createUser             VARCHAR2(32)     not null,
  updateUser             VARCHAR2(32),
  createDate             DATE             not null,
  updateDate             DATE,
  functionType           varchar2(60)     not null,
  detailFieldNameA       VARCHAR2(60),
  detailFieldA			 VARCHAR2(60),
  detailFieldNameB       VARCHAR2(60),
  detailFieldB			 VARCHAR2(60),
  detailFieldNameC       VARCHAR2(60),
  detailFieldC			 VARCHAR2(60),
  detailFieldNameD       VARCHAR2(60),
  detailFieldD			 VARCHAR2(60),
  detailFieldNameE       VARCHAR2(60),
  detailFieldE			 VARCHAR2(60),
  constraint pkgisFunction primary key (id)
);
comment on table gisFunction
is 'gis功能表';
comment on column gisFunction.id
is '表id';
comment on column gisFunction.boundEventName
is '绑定事件中文名称 ';
comment on column gisFunction.unBoundEventName
is '未绑事件中文名称 ';
comment on column gisFunction.event
is '事件';
comment on column gisFunction.boundEventIsValid
is '绑定事件是否有效';
comment on column gisFunction.unBoundEventIsValid
is '未绑事件是否有效';
comment on column gisFunction.moduleId
is '主表id';
comment on column gisFunction.sonClassId
is '子类表id' ;
comment on column gisFunction.titleName
is '标题名字';
comment on column gisFunction.titleValue
is '标题内容';
comment on column gisFunction.detailsUrl
is '详情链接';
comment on column gisFunction.iconUrl
is '图片路径';
comment on column gisFunction.isViewIcon
is '是否显示图片';
comment on column gisFunction.searchFieldAName
is '查询条件A中文名';
comment on column gisFunction.searchFieldBName
is '查询条件B中文名';
comment on column gisFunction.searchFieldCName
is '查询条件C中文名';
comment on column gisFunction.searchFieldA
is '查询条件A';
comment on column gisFunction.searchFieldB
is '查询条件B';
comment on column gisFunction.searchFieldC
is '查询条件C';
comment on column gisFunction.fieldNameA
is '要显示的字段名称';
comment on column gisFunction.fieldA
is '字段';
comment on column gisFunction.fieldNameB
is '要显示的字段名称';
comment on column gisFunction.fieldB
is '字段';
comment on column gisFunction.fieldNameC
is '要显示的字段名称';
comment on column gisFunction.fieldC
is '字段';
comment on column gisFunction.fieldNameD
is '要显示的字段名称';
comment on column gisFunction.fieldD
is '字段';
comment on column gisFunction.fieldNameE
is '要显示的字段名称';
comment on column gisFunction.fieldE
is '字段';
comment on column gisFunction.functionType
is '功能类型';
comment on column gisFunction.detailFieldNameA 
is '要显示的详情查看内容字段名称';
comment on column gisFunction.detailFieldA
is '详情查看内容字段';
comment on column gisFunction.detailFieldNameB 
is '要显示的详情查看内容字段名称';
comment on column gisFunction.detailFieldB
is '详情查看内容字段';
comment on column gisFunction.detailFieldNameC 
is '要显示的详情查看内容字段名称';
comment on column gisFunction.detailFieldC
is '详情查看内容字段';
comment on column gisFunction.detailFieldNameD 
is '要显示的详情查看内容字段名称';
comment on column gisFunction.detailFieldD
is '详情查看内容字段';
comment on column gisFunction.detailFieldNameE 
is '要显示的详情查看内容字段名称';
comment on column gisFunction.detailFieldE
is '详情查看内容字段';



/*==============================================================*/
/* Table: deviceInformation  设备信息                            */
/*==============================================================*/
create table deviceInformation  (
   deviceNumber			VARCHAR2(90)					not null,
   deviceType           VARCHAR2(90),
   model				VARCHAR2(90),
   brands				VARCHAR2(90),
   userName				VARCHAR2(90)					not null,
   createDate           DATE                            not null,
   updateDate           DATE,
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   constraint pkDeviceInformation primary key (deviceNumber)
);

comment on table deviceInformation is
'设备信息';

comment on column deviceInformation.deviceNumber is
'设备编号';

comment on column deviceInformation.deviceType is
'设备类型';

comment on column deviceInformation.model is
'型号';

comment on column deviceInformation.brands is
'品牌';

comment on column deviceInformation.userName is
'使用用户名';

comment on column deviceInformation.createDate is
'新增时间';

comment on column deviceInformation.updateDate is
'修改时间';

comment on column deviceInformation.fullPinyin is
'全拼';

comment on column deviceInformation.simplePinyin is
'简拼';

create sequence s_positioningTrajectory
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

/*==============================================================*/
/* Table: positioningTrajectory  定位轨迹信息              			    */
/*==============================================================*/
create table positioningTrajectory  (
   id					NUMBER(10)                      not null,
   longitude			VARCHAR2(60)					not null,
   latitude				VARCHAR2(60)					not null,
   locateDate			DATE                            not null,
   direction			VARCHAR2(30),
   speed				VARCHAR2(30),
   positionDescription	VARCHAR2(60),
   createDate           DATE                            not null,
   updateDate           DATE,
   deviceNumber			VARCHAR2(90),
   userName			    VARCHAR2(90)					not null,
   longitude2			VARCHAR2(32),
   latitude2			VARCHAR2(32),
   trackInfo    		VARCHAR2(600),
   constraint pkPositioningTrajectory primary key (id)
);

comment on table positioningTrajectory is
'定位轨迹信息  ';

comment on column positioningTrajectory.id is
'定位轨迹id';

comment on column positioningTrajectory.longitude is
'经度';

comment on column positioningTrajectory.latitude is
'纬度';

comment on column positioningTrajectory.locateDate is
'定位时间';

comment on column positioningTrajectory.direction is
'方向';

comment on column positioningTrajectory.speed is
'速度';

comment on column positioningTrajectory.positionDescription is
'位置描述';

comment on column positioningTrajectory.createDate is
'新增时间';

comment on column positioningTrajectory.updateDate is
'修改时间';

comment on column positioningTrajectory.deviceNumber is
'设备编号';
comment on column positioningTrajectory.userName is
'使用用户的用户名';

comment on column positioningTrajectory.longitude2 is
'二维经度';

comment on column positioningTrajectory.latitude2 is
'二维纬度';

comment on column positioningTrajectory.trackInfo is
'轨迹信息';

create index idx_position_locatedate on positioningtrajectory(username,locatedate);

--绑定类型--
--insert into propertydomains 
--values (  s_propertyDomains.NEXTVAL ,'gis绑定类型',0,'');
--
--insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,
--simplepinyin,fullpinyin,createuser,createdate)
--values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='gis绑定类型'),
--0,1,'绑定地图','bddt','bangdingditu','admin',sysdate);
--insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,
--simplepinyin,fullpinyin,createuser,createdate)
--values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='gis绑定类型'),
--0,2,'绑定房屋','bdfw','bangdingfangwu','admin',sysdate);

-- 网格员队伍管理
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'gridTeamMember','网格员队伍',
0,1,1,0,'gridTeamMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,
searchfieldbname,searchfieldb,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='gridTeamMember'),
'姓名','memeberName','/baseinfo/gridTeamManage/dispatchOperate.action?mode=view'||'&'||'gridTeam.id=',
'/resource/openLayersMap/images/blueBubble',1,'组织机构','orgFullName','姓名','memeberName','refineSearch',sysdate,'admin',
'证件号码','idCardNo','业务类型','businessTypeName');


--实有人口--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'person','人口信息',
1,1,1,1,'personMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,detailFieldNameC,detailFieldC,
searchfieldbname,searchfieldb,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='person'),
'姓名','name','/baseinfo/householdStaff/dispathForGis.action?mode=view'||'&'||'population.id=',
'/resource/openLayersMap/images/blueBubble',1,'证件号码','idCardNo','姓名','name','refineSearch',sysdate,'admin','地址','address',
'姓名','name','身份证','idCardNo','地址','address',
'证件号码','idCardNo','人口类型','personTypeName','性别','gender','性别','gender','业务类型','businessTypeName');

--人口子类--
--常住人口--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'户籍人口','HOUSEHOLDSTAFFS','householdStaff','person',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldbname,searchfieldb,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='HOUSEHOLDSTAFFS'),
'姓名','name','/baseinfo/householdStaff/dispathForGis.action?mode=view'||'&'||'population.id=',
'/resource/openLayersMap/images/blueBubble',1,'证件号码','idCardNo','姓名','name','refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','地址','address',
'性别','genderName','人员类别','personTypeName','性别','genderName','证件号码','idCardNo','业务类型','businessTypeName');

--流动人口--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'流动人口','FLOATINGPOPULATIONS','floatingPopulation','person',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldbname,searchfieldb,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='FLOATINGPOPULATIONS'),
'姓名','name','/baseinfo/floatingPopulationManage/dispathForGis.action?mode=view'||'&'||'population.id=',
'/resource/openLayersMap/images/blueBubble',1,'证件号码','idCardNo','姓名','name','refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','地址','address',
'性别','genderName','人员类别','personTypeName','性别','genderName','证件号码','idCardNo','业务类型','businessTypeName');

--未落户人口--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'未落户人口','UNSETTLEDPOPULATIONS','unsettledPopulation','person',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldbname,searchfieldb,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='UNSETTLEDPOPULATIONS'),
'姓名','name','/baseinfo/unsettledPopulationManage/dispatchOperateForGis.action?mode=view'||'&'||'unsettledPopulation.id=',
'/resource/openLayersMap/images/blueBubble',1,'证件号码','idCardNo','姓名','name','refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','地址','address',
'性别','genderName','人员类别','personTypeName','性别','genderName','证件号码','idCardNo','业务类型','businessTypeName');

--境外人员--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'境外人员','OVERSEAPERSONNEL','overseaStaff','person',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldbname,searchfieldb,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='OVERSEAPERSONNEL'),
'姓名','name','/baseinfo/overseaPersonnelManage/dispatchForGis.action?mode=view'||'&'||'isHiddenPersonnelTrack=1'||'&'||'overseaPersonnel.id=',
'/resource/openLayersMap/images/blueBubble',1,'证件号码','idCardNo','姓名','name','refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','地址','address',
'性别','genderName','人员类别','personTypeName','性别','genderName','证件号码','idCardNo','业务类型','businessTypeName');


--建筑物信息--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'buildDatas','楼宇信息',
0,1,0,1,'buildDataMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,createdate,createuser,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='buildDatas'),
'楼宇名称','name','/builddatasManage/dispatchForGis.action?mode=view'||'&'||'builddatas.id=',
'/resource/openLayersMap/images/blueBubble',1,'地址','address',
'楼宇名称','name','地址','address','refineSearch',sysdate,'admin',
'楼宇名称','name','地址','address');



--建筑物
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'hourseInfo','建筑物',
0,1,0,0,'hourseInfoMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='hourseInfo'),
'名称','name','/resource/openLayersMap/images/blueBubble',1,
'地址','address','refineSearch',sysdate,'admin');

--网格层--
--insert into gismodulemanages
--(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
--isPopulation,modeType,createdate,createuser) values
--(s_gisModuleManages.Nextval,'gis2dlayers','网格层',
--0,1,0,0,'gridLayerMap',sysdate,'admin');

--insert into gisfunction
--(id,moduleId,titlename,titlevalue,iconurl,isviewicon,
--functionType,createdate,createuser) values 
--(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='gis2dlayers'),
--'网格名称','orgName','/resource/openLayersMap/images/yellowBubble',0,
--'refineSearch',sysdate,'admin');

--住房信息--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'houseInfo','房屋信息',
1,1,1,0,'housePropertyMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldBname,searchfieldB,functionType,createdate,createuser,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldAname,searchfieldA,fieldnameB,fieldB,fieldnameC,fieldC,fieldnameD,fieldD,fieldnameE,fieldE) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='houseInfo'),
'产权人','name','/baseinfo/actualHouseManage/dispatchHouseInfoByHouseId.action?houseInfo.id=',
'/resource/openLayersMap/images/redBubble',1,'地址','address','地址','address','refineSearch',sysdate,'admin',
'地址','address','产权人','name',
'产权人','name','产权人','name','所在楼宇','buildingname','隐患等级','hiddendangerLevel','是否出租房','isRentalHouse');

--出租房
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'出租房','rentalHouse','RENTALHOUSE','houseInfo',1,sysdate,'admin');

insert into gisfunction
  (ID, SONCLASSID, TITLENAME, 
   TITLEVALUE, DETAILSURL, ICONURL, ISVIEWICON,
   SEARCHFIELDANAME, SEARCHFIELDA, SEARCHFIELDBNAME, SEARCHFIELDB,
   FIELDNAMEA, FIELDA, FIELDNAMEB, FIELDB,
   CREATEUSER,  CREATEDATE, FUNCTIONTYPE,
   DETAILFIELDNAMEA, DETAILFIELDA, DETAILFIELDNAMEB, DETAILFIELDB,
   DETAILFIELDNAMEC, DETAILFIELDC, DETAILFIELDNAMED, DETAILFIELDD,
   DETAILFIELDNAMEE, DETAILFIELDE)
values
  (s_gisFunction.Nextval, (select id from gisTypeManages where name='出租房'),
   '出租人',
   'rentalPerson', '/baseinfo/actualHouseManage/dispatchHouseInfoByHouseId.action?houseType=rentalHouse'||'&'||'houseInfo.id=', '/resource/openLayersMap/images/blueBubble', 1,
   '出租人', 'rentalPerson', '地址', 'address',
   '隐患程度', 'hiddendangerLevel', '地址', 'address',
   'admin',  sysdate, 'refineSearch',
   '房屋编号', 'houseCode','建筑结构', 'buildStruct', 
   '出租人', 'rentalPerson', '隐患程度', 'hiddendangerLevel',
   '地址', 'address');
   

--非出租房
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'非出租房','houseInfo','HOUSEINFO','houseInfo',1,sysdate,'admin');

insert into gisfunction
  (ID, SONCLASSID, TITLENAME, 
   TITLEVALUE, DETAILSURL, ICONURL, ISVIEWICON,
   SEARCHFIELDANAME, SEARCHFIELDA, SEARCHFIELDBNAME, SEARCHFIELDB,
   FIELDNAMEA, FIELDA,
   CREATEUSER,  CREATEDATE, FUNCTIONTYPE,
   DETAILFIELDNAMEA, DETAILFIELDA, DETAILFIELDNAMEB, DETAILFIELDB,
   DETAILFIELDNAMEC, DETAILFIELDC, DETAILFIELDNAMED, DETAILFIELDD)
values
  (s_gisFunction.Nextval, (select id from gisTypeManages where name='非出租房'),
   '产权人',
   'name', '/baseinfo/actualHouseManage/dispatchHouseInfoByHouseId.action?houseInfo.id=', '/resource/openLayersMap/images/blueBubble', 1,
   '产权人', 'name', '地址', 'address',
   '地址', 'address',
   'admin',  sysdate, 'refineSearch',
   '房屋编号', 'houseCode', '建筑结构', 'buildStruct',
   '产权人', 'name', '地址', 'address');


--服务办事--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,orgFiled,createdate,createuser) values
(s_gisModuleManages.Nextval,'issues','我的事项',
1,1,1,0,'issueMap','orgId',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='issues'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');


--我的事项的子类--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'我的待办','personForThing','PERSONFORTHING','issues',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='personForThing'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'我的已办','personAlreadyThing','PERSONALREADYTHING','issues',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='personAlreadyThing'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');


insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'我的已办结','goneThrough','GONETHROUGH','issues',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='goneThrough'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');


--下辖事项的子类--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'jurisdictionsIssue','下辖事项',
1,1,1,0,'issueMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='jurisdictionsIssue'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'下辖待办','forThing','FORTHING','jurisdictionsIssue',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='forThing'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'下辖已办结','alreadyThing','ALREADYTHING','jurisdictionsIssue',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='alreadyThing'),
'事件主题','subject','/issues/issueManage/dispatch.action?mode=viewNew'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');


--重点人员--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'keyPerson','特殊人群',
1,1,1,0,'keyPersonMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='keyPerson'),
'姓名','name','/resource/openLayersMap/images/greenBubble',1,'身份证','idCardNo','人员类型','typeName',
'姓名','name','身份证','idCardNo','refineSearch',sysdate,'admin');

--重点人员的子类--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'吸毒人员','DRUGGYS','DRUGGY','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='DRUGGYS'),
'姓名','name',
'/baseinfo/druggyManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=druggy'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类别','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---社区矫正人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'社区矫正人员','RECTIFICATIVEPERSONS','RECTIFICATIVEPERSON','keyPerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='RECTIFICATIVEPERSONS'),
'姓名','name',
'/baseinfo/rectificativePersonManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=rectificativePerson'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---严重精神障碍患者---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'严重精神障碍患者','MENTALPATIENTS','MENTALPATIENT','keyPerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='MENTALPATIENTS'),
'姓名','name',
'/baseinfo/mentalPatientManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=mentalPatient'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---刑释人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'刑释人员','POSITIVEINFOS','POSITIVEINFO','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='POSITIVEINFOS'),
'姓名','name',
'/baseinfo/positiveInfoManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=positiveInfo'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName' ,'性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---重点上访人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'重点上访人员','SUPERIORVISITS','SUPERIORVISIT','keyPerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='SUPERIORVISITS'),
'姓名','name',
'/baseinfo/superiorVisitManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=superiorVisit'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---危险品从业人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'危险品从业人员','DANGEROUSGOODSPRACTITIONERS','DANGEROUSGOODSPRACTITIONER','keyPerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='DANGEROUSGOODSPRACTITIONERS'),
'姓名','name',
'/baseinfo/dangerousGoodsPractitionerManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=dangerousGoodsPractitioner'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---重点青少年---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'重点青少年','IDLEYOUTHS','IDLEYOUTH','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='IDLEYOUTHS'),
'姓名','name',
'/baseinfo/idleYouthManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=idleYouth'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');


---其他人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'其他人员','OTHERATTENTIONPERSONNELS','OTHERATTENTIONPERSONNEL','keyPerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='OTHERATTENTIONPERSONNELS'),
'姓名','name',
'/baseinfo/otherAttentionPersonnelManage/dispatchOperate.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=otherAttentionPersonnel'||'&'||'id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');




---艾滋病人员---
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

---涉嫌参与邪教人员---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'涉嫌参与邪教人员','suspicionHeresyPerson','SUSPICIONHERESYPERSON','keyPerson',1,sysdate,'admin');


--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='suspicionHeresyPerson'),
--'姓名','name',
--'/baseinfo/suspicionHeresyPersonManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=suspicionHeresyPerson'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---涉法涉诉人员---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'涉法涉诉人员','legalLitigation','LEGALLITIGATION','keyPerson',1,sysdate,'admin');


--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='legalLitigation'),
--'姓名','name',
--'/baseinfo/legalLitigationManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=legalLitigation'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---正在服刑人员---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'正在服刑人员','inmatess','INMATES','keyPerson',1,sysdate,'admin');


--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='inmatess'),
--'姓名','name',
--'/baseinfo/inmatesManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=inmatess'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

--关怀对象--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'carePerson','关怀对象',
1,1,1,0,'keyPersonMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='carePerson'),
'姓名','name','/resource/openLayersMap/images/greenBubble',1,'身份证','idCardNo','人员类型','typeName',
'姓名','name','身份证','idCardNo','refineSearch',sysdate,'admin');

---老年人---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'老年人','elderlyPeople','ELDERLYPEOPLE','carePerson',1,sysdate,'admin');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='elderlyPeople'),
'姓名','name',
'/baseinfo/elderlyPeopleManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=elderlyPeople'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---残疾人---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'残疾人','handicappeds','HANDICAPPED','carePerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='handicappeds'),
'姓名','name',
'/baseinfo/handicappedManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=handicapped'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');


---优抚对象---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'优抚对象','optimalObjects','OPTIMALOBJECT','carePerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='optimalObjects'),
'姓名','name',
'/baseinfo/optimalObjectManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=optimalObject'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');


---需救助人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'需救助人员','aidNeedPopulation','AIDNEEDPOPULATION','carePerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='aidNeedPopulation'),
'姓名','name',
'/baseinfo/aidNeedPopulationManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=aidNeedPopulation'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---失业人员---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'失业人员','unemployedPeople','UNEMPLOYEDPEOPLE','carePerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='unemployedPeople'),
'姓名','name',
'/baseinfo/unemployedPeopleManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=unemployedPeople'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---低保人员--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'低保人员','minimumLivingPersons','MINIMUMLIVINGPERSON','carePerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='minimumLivingPersons'),
--'姓名','name',
--'/baseinfo/minimumLivingPersonManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=minimumLivingPerson'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---退役人员--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'退役人员','retireSoldier','RETIRESOLDIER','carePerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='retireSoldier'),
--'姓名','name',
--'/baseinfo/retireSoldierManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=retireSoldier'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---军转干人员--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'军转干人员','demobilizedDry','DEMOBILIZEDDRY','carePerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='demobilizedDry'),
--'姓名','name',
--'/baseinfo/demobilizedDryManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=demobilizedDry'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');



--其他关注对象--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'otherPerson','其他关注对象',
1,1,1,0,'keyPersonMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='otherPerson'),
'姓名','name','/resource/openLayersMap/images/greenBubble',1,'身份证','idCardNo','人员类型','typeName',
'姓名','name','身份证','idCardNo','refineSearch',sysdate,'admin');

---流浪乞讨人员--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'流浪乞讨人员','homelessBeggarss','HOMELESSBEGGARS','otherPerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='homelessBeggarss'),
--'姓名','name',
--'/baseinfo/homelessBeggarsManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=homelessBeggars'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---社保人员--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'社保人员','socialSecurityPersonnels','SOCIALSECURITYPERSONNEL','otherPerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='socialSecurityPersonnels'),
--'姓名','name',
--'/baseinfo/socialSecurityPersonnelManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=socialSecurityPersonnel'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---育龄妇女---
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'育龄妇女','nurturesWomen','NURTURESWOMEN','otherPerson',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='nurturesWomen'),
'姓名','name',
'/baseinfo/nurturesWomenManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=nurturesWomen'||'&'||'population.id=',
'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
'refineSearch',sysdate,'admin','地址','address',
'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');

---党员--
---insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'党员','partyMembers','PARTYMEMBER','otherPerson',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--functionType,createdate,createuser,fieldnameB,fieldB,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,fieldnameC,fieldC,fieldnameD,fieldD,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,detailFieldNameE,detailFieldE) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='partyMembers'),
--'姓名','name',
--'/baseinfo/partyMemberManage/dispatchOperateForGis.action?superviceRecordName=服务记录'||'&'||'supervisorName=服务人员'||'&'||'mode=view'||'&'||'populationType=partyMember'||'&'||'population.id=',
--'/resource/openLayersMap/images/greenBubble',1,'证件号码','idCardNo',
--'refineSearch',sysdate,'admin','地址','address',
--'姓名','name','地址','address','性别','genderName','人员类型','typeName','性别','genderName','证件号码','idCardNo','业务类型','bussinessType');


--组织场所--
--insert into gismodulemanages
--(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
--isPopulation,modeType,createdate,createuser) values
--(s_gisModuleManages.Nextval,'keyPlaces','重点场所',
--1,1,1,0,'keyPlaceMap',sysdate,'admin');

--insert into gisfunction
--(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser) values 
--(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='keyPlaces'),
--'场所名称','name','/resource/openLayersMap/images/greenBubble',1,'场所地址','address','场所类型','typeName',
--'名称','name','地址','address','refineSearch','场所绑定',1,'解除场所绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');


--组织场所的子类--
---企业单位---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'企业单位','keyPlaces','ENTERPRISELEGAL','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISELEGAL'),
--'单位名称','name','/baseinfo/enterpriseLegalManage/dispatchOperate.action?mode=view'||'&'||'locationType=ENTERPRISELEGAL'||'&'||'keyType='||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'单位地址','address',
--'名称','name','单位地址','address','refineSearch','企业单位绑定',1,'解除企业单位绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'单位名称','name');

---事业单位---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'事业单位','keyPlaces','LEGALINSTITUTION','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='LEGALINSTITUTION'),
--'单位名称','name','/baseinfo/legalInstitutionManage/dispatchOperate.action?mode=view'||'&'||'locationType=LEGALINSTITUTION'||'&'||'keyType='||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'单位地址','address',
--'名称','name','单位地址','address','refineSearch','事业单位绑定',1,'解除事业单位绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'单位名称','name');

---非企业、社会团体---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'非企业、社会团体','keyPlaces','SOCIALGROUPLEGAL','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='SOCIALGROUPLEGAL'),
--'单位名称','name','/baseinfo/socialGroupLegalManage/dispatchOperate.action?mode=view'||'&'||'locationType=SOCIALGROUPLEGAL'||'&'||'keyType='||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'单位地址','address',
--'名称','name','单位地址','address','refineSearch','非企业、社会团体绑定',1,'解除非企业、社会团体绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'单位名称','name');


---学校---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'学校','keyPlaces','SCHOOLS','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='SCHOOLS'),
--'学校名称','name','/baseinfo/schoolManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=SCHOOL'||'&'||'keyType='||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'学校地址','address',
--'名称','name','地址','address','refineSearch','学校绑定',1,'解除学校绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'学校名称','name');

---治安重点---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'治安重点','keyPlaces','SECURITYKEY','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='SECURITYKEY'),
--'治安重点','name','/baseinfo/safetyProductionKeyManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=SECURITYKEY'||'&'||'keyType=securityKey'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'治安重点地址','address',
--'名称','name','地址','address','refineSearch','治安重点绑定',1,'解除治安重点绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'治安重点','name');


---安全生产重点---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'安全生产重点','keyPlaces','SAFETYPRODUCTIONKEY','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='SAFETYPRODUCTIONKEY'),
--'安全生产重点','name','/baseinfo/safetyProductionKeyManage/dispatchOperateForGis.action?supervisorName=治安负责人'||'&'||'superviceRecordName=巡场情况'||'&'||'mode=view'||'&'||'keyType=safetyProductionKey'||'&'||'locationType=SAFETYPRODUCTIONKEY'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'安全生产地址','address',
--'名称','name','地址','address','refineSearch','安全生产绑定',1,'解除安全生产绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'安全生产重点','name');

---消防安全重点---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'消防安全重点','keyPlaces','FIRESAFETYKEY','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='FIRESAFETYKEY'),
--'消防安全重点','name','/baseinfo/fireSafetyEnterpriseManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=消防安全负责人'||'&'||'mode=view'||'&'||'locationType=FIRESAFETYKEY'||'&'||'keyType=fireSafetyKey'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'消防安全地址','address',
--'名称','name','地址','address','refineSearch','消防安全绑定',1,'解除消防安全绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'消防安全重点','name');


---危险化学品单位---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'危险化学品单位','keyPlaces','DANGEROUSCHEMICALSUNIT','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='DANGEROUSCHEMICALSUNIT'),
--'危险化学品单位','name','/baseinfo/dangerousChemicalsUnitManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=DANGEROUSCHEMICALSUNIT'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'危险化学品地址','address',
--'名称','name','地址','address','refineSearch','危险化学品绑定',1,'解除危险化学品绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'危险化学品单位','name');
--
--
-----网吧---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'网吧','keyPlaces','INTERNETBAR','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='INTERNETBAR'),
--'上网服务名称','name','/baseinfo/internetBarManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=INTERNETBAR'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'上网服务地址','address',
--'名称','name','地址','address','refineSearch','上网服务绑定',1,'解除上网服务绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'上网服务名称','name');


---公共场所---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'公共场所','keyPlaces','PUBLICPLACE','keyPlaces',1,sysdate,'admin');

--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='PUBLICPLACE'),
--'公共场所名称','name','/baseinfo/publicPlaceManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=PUBLICPLACE'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'公共场所地址','address',
--'名称','name','地址','address','refineSearch','公共场所绑定',1,'解除公共场所绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'公共场所名称','name');


---其他场所---
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'其他场所','keyPlaces','OTHERLOCALES','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='OTHERLOCALES'),
--'其他场所名称','name','/baseinfo/otherLocaleManage/dispatchOperateForGis.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=OTHERLOCALE'||'&'||'keyType='||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'其他场所地址','address',
--'名称','name','地址','address','refineSearch','其他场所绑定',1,'解除其他场所绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'其他场所名称','name');

---城市部件---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'dustbin','城市部件',
0,1,1,0,'cityComponentsMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='dustbin'),
'编号','dustbinCode','/baseinfo/dustbinManage/dispatchOperateByEncrypt.action?mode=view'||'&'||'location.id=',
'/resource/openLayersMap/images/redBubble',1,'部件名称','name','地址','address','refineSearch',
'绑定',1,'解除绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin',
'编号','dustbinCode','部件名称','name','地址','address','主管部门','deptName',
'编号','dustbinCode','地址','address','主管部门','deptName');

---视频信息---
--insert into gismodulemanages
--(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
--isPopulation,modeType,createdate,createuser) values
--(s_gisModuleManages.Nextval,'dustbinHasVideo','视频监控',
--0,1,1,0,'cityComponentsMap',sysdate,'admin');

--insert into gisfunction
--(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
--unboundeventname,unboundeventisvalid,event,createdate,createuser,
--detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,detailFieldNameC,detailFieldC,detailFieldNameD,detailFieldD,
--searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
--(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='dustbinHasVideo'),
--'编号','dustbinCode','/resource/openLayersMap/images/video',1,'部件名称','name','地址','address','refineSearch',
--'绑定',1,'解除绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin',
--'编号','dustbinCode','部件名称','name','地址','address','主管部门','deptName',
--'编号','dustbinCode','地址','address','主管部门','deptName');

---网格员Gps定位---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,isPopulation,modeType,createdate,createuser)
values (s_gisModuleManages.Nextval,'deviceInformation','网格员定位',0,1,1,0,'gpsMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,
iconurl,isviewicon,functionType,createdate,createuser,
fieldnameA,fieldA,fieldnameB,fieldB,fieldnameC,fieldC,fieldnameD,fieldD,
searchfieldAName,searchfieldA,searchfieldBName,searchfieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB
) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='deviceInformation'),
'用户名','userName','/baseinfo/positioningTrajectoryManage/dispatch.action?mode=view'||'&'||'positionId=',
'/resource/openLayersMap/images/redBubble',1,'refineSearch',sysdate,'admin',
'姓名','name','联系电话','telephone','部门','orgName','定位时间','locateDate',
'用户名','userName','姓名','userName',
'用户名','userName','设备编号','code'
);

---调整详情查看，改iframe远程调用的链接为本地调用的链接---
update gisfunction g set g.detailsurl = REPLACE( g.detailsurl, 'ForGis', '');
update gisfunction g set g.detailsurl = REPLACE( g.detailsurl, '_gis', '');

---实有单位---
--insert into gismodulemanages
--(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
--isPopulation,modeType,createdate,createuser) values
--(s_gisModuleManages.Nextval,'actualCompany','实有单位',
--0,1,1,0,'keyPlaceMap',sysdate,'admin');
--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'实有单位','keyPlaces','ACTUALCOMPANY','keyPlaces',1,sysdate,'admin');
--
--insert into gisfunction
--(id,moduleId,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='actualCompany'),(select id from gisTypeManages where key='ACTUALCOMPANY'),
--'单位名称','name','/baseinfo/actualCompanyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=actualCompany'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'实有单位地址','address',
--'名称','name','地址','address','refineSearch','实有单位绑定',1,'解除实有单位绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'实有单位名称','name');

--两新组织--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'twoNewGroup','两新组织',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='twoNewGroup'),
'组织名称','name','/resource/openLayersMap/images/greenBubble',1,'组织地址','address','组织类型','typeName',
'名称','name','地址','address','refineSearch','组织绑定',1,'解除组织绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');

--两新组织子类--
--社会组织--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'社会组织','keyPlaces','NEWSOCIETYFEDERATIONS','twoNewGroup',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWSOCIETYFEDERATIONS'),
'社会组织名称','name','/baseinfo/newSocietyOrganizationsManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=NEWSOCIETYORGANIZATIONS'||'&'||'location.id=',
'/resource/openLayersMap/images/greenBubble',1,'社会组织地址','address',
'名称','name','地址','address','refineSearch','社会组织绑定',1,'解除学校绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'社会组织名称','name');

--新经济组织--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'新经济组织','keyPlaces','NEWECONOMICORGANIZATIONS','twoNewGroup',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWECONOMICORGANIZATIONS'),
'新经济组织名称','name','/baseinfo/newEconomicOrganizationsManage/dispath.action?mode=view'||'&'||'newEconomicOrganizations.id=',
'/resource/openLayersMap/images/greenBubble',1,'新经济组织地址','address',
'名称','name','地址','address','refineSearch','新经济组织绑定',1,'解除学校绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'新经济组织名称','name');


--企业--
--insert into gismodulemanages
--(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
--isPopulation,modeType,createdate,createuser) values
--(s_gisModuleManages.Nextval,'enterprise','企业',
--1,1,1,0,'keyPlaceMap',sysdate,'admin');
--
--insert into gisfunction
--(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser) values 
--(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='enterprise'),
--'企业名称','name','/resource/openLayersMap/images/greenBubble',1,'企业地址','address','企业类型','typeName',
--'名称','name','地址','address','refineSearch','企业绑定',1,'解除企业绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');
--
--
----企业子类--
----规上企业--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'规上企业','keyPlaces','ENTERPRISEKEY','enterprise',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISEKEY'),
--'规上企业名称','name','/baseinfo/safetyProductionKeyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=ENTERPRISEKEY'||'&'||'keyType=enterpriseKey'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'规上企业地址','address',
--'名称','name','地址','address','refineSearch','规上企业绑定',1,'解除规上企业绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'规上企业名称','name');
--
----规下企业--
--insert into gisTypeManages
--(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
--(gisTypeManages_squ.Nextval,'规下企业','keyPlaces','ENTERPRISEDOWNKEY','enterprise',1,sysdate,'admin');
--
--insert into gisfunction
--(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
--searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
--boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
--createdate,createuser,
--detailFieldNameA,detailFieldA) values 
--(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISEDOWNKEY'),
--'规下企业名称','name','/baseinfo/safetyProductionKeyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=ENTERPRISEDOWNKEY'||'&'||'keyType=enterpriseDownKey'||'&'||'location.id=',
--'/resource/openLayersMap/images/greenBubble',1,'规下企业地址','address',
--'名称','name','地址','address','refineSearch','规下企业绑定',1,'解除规下企业绑定',1,
--(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
--'规下企业名称','name');

--景区管理--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'scenicsManage','景区管理',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='scenicsManage'),
'景区管理名称','name','/resource/openLayersMap/images/greenBubble',1,'景区管理地址','address','景区管理类型','typeName',
'名称','name','地址','address','refineSearch','景区管理绑定',1,'解除景区管理绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');

--景区管理子类--
--旅游景点--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'旅游景点','keyPlaces','SCENICSPOTS','scenicsManage',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='SCENICSPOTS'),
'旅游景点名称','name','/baseinfo/scenicSpotManage/dispatchOperate.action?superviceRecordName=undefined'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=undefined'||'&'||'location.id=',
'/resource/openLayersMap/images/greenBubble',1,'旅游景点地址','address',
'名称','name','地址','address','refineSearch','旅游景点绑定',1,'解除旅游景点绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'旅游景点名称','name');

--配套设施--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'配套设施','keyPlaces','SCENICEQUIPMENT','scenicsManage',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='SCENICEQUIPMENT'),
'配套设施名称','name','/baseinfo/scenicEquipmentManage/dispatchOperate.action?superviceRecordName=undefined'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=undefined'||'&'||'id=',
'/resource/openLayersMap/images/greenBubble',1,'配套设施地址','address',
'名称','name','地址','address','refineSearch','配套设施绑定',1,'解除配套设施绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'配套设施名称','name');

--景区交通--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'景区交通','keyPlaces','SCENICTRAFFIC','scenicsManage',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='SCENICTRAFFIC'),
'线路名称','name','/baseinfo/scenicTrafficManage/dispatchOperate.action?superviceRecordName=undefined'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=undefined'||'&'||'id=',
'/resource/openLayersMap/images/greenBubble',1,'负责地址','address',
'名称','name','地址','address','refineSearch','景区交通绑定',1,'解除景区交通绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'线路名称','name','负责地址','address');

   
--Gis 公安部件--    
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'publicSecurity','公安部件',
1,1,1,0,'publicSecurityMap',sysdate,'admin');


insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,createdate,createuser,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='publicSecurity'),
'编号','code','/resource/openLayersMap/images/redBubble',1,'地址','address','部件类型','typeName',
'编号','code','地址','address','refineSearch',sysdate,'admin',
'公安部件绑定',1,'解除公安部件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'));


--Gis天网--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'天网','SKYNET','SKYNET','publicSecurity',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,searchfieldAname,searchfieldA,searchfieldBname,searchfieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='SKYNET'),'编号','code',
'/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=skynet'||'&'||'mode=view'||'&'||'id=',
'/resource/openLayersMap/images/redBubble',1,'地址','address',
'refineSearch',sysdate,'admin','编号','code','地址','address',
'编号','code','地址','address',
'天网绑定',1,'解除天网绑定',1,(select id from propertydicts p where p.displayname='绑定地图'));

--Gis 卡口--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'卡口','BAYONET','BAYONET','publicSecurity',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,searchfieldAname,searchfieldA,searchfieldBname,searchfieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='BAYONET'),'编号','code',
'/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=bayonet'||'&'||'mode=view'||'&'||'id=',
'/resource/openLayersMap/images/redBubble',1,'地址','address',
'refineSearch',sysdate,'admin','编号','code','地址','address',
'编号','code','地址','address',
'卡口绑定',1,'解除卡口绑定',1,(select id from propertydicts p where p.displayname='绑定地图'));

--Gis 抓拍系统--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'抓拍系统','SNAPSHOTSYSTEM','SNAPSHOTSYSTEM','publicSecurity',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,searchfieldAname,searchfieldA,searchfieldBname,searchfieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='SNAPSHOTSYSTEM'),'编号','code',
'/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=snapshotSystem'||'&'||'mode=view'||'&'||'id=',
'/resource/openLayersMap/images/redBubble',1,'地址','address',
'refineSearch',sysdate,'admin','编号','code','地址','address',
'编号','code','地址','address',
'抓拍系统绑定',1,'解除抓拍系统绑定',1,(select id from propertydicts p where p.displayname='绑定地图'));

----改造后的单位场所
-----场所--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'companyPlaceBase','场所',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='companyPlaceBase'),
'场所名称','name','/resource/openLayersMap/images/greenBubble',1,'场所地址','address','场所类型','typeName',
'名称','name','地址','address','refineSearch','场所绑定',1,'解除场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');

-----公共活动场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'公共活动场所','keyPlaces','NEWPUBLICPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWPUBLICPLACE'),
'公共活动场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWPUBLICPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'公共活动场所地址','address',
'名称','name','地址','address','refineSearch','公共活动场所绑定',1,'解除公共活动场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'公共活动场所名称','name');
-----交通场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'交通场所','keyPlaces','TRAFFICPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='TRAFFICPLACE'),
'交通场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=TRAFFICPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'交通场所地址','address',
'名称','name','地址','address','refineSearch','交通场所绑定',1,'解除交通场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'交通场所名称','name');
-----娱乐场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'娱乐场所','keyPlaces','ENTERTAINMENTPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERTAINMENTPLACE'),
'娱乐场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=ENTERTAINMENTPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'娱乐场所地址','address',
'名称','name','地址','address','refineSearch','娱乐场所绑定',1,'解除娱乐场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'娱乐场所名称','name');
-----商贸场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'商贸场所','keyPlaces','TRADEPLACE','companyPlaceBase',1,sysdate,'admin');
insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='TRADEPLACE'),
'商贸场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=TRADEPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'商贸场所地址','address',
'名称','name','地址','address','refineSearch','商贸场所绑定',1,'解除商贸场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'商贸场所名称','name');
-----网吧
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'网吧','keyPlaces','NEWINTERNETBAR','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWINTERNETBAR'),
'网吧名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWINTERNETBAR'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'网吧地址','address',
'名称','name','地址','address','refineSearch','网吧绑定',1,'解除网吧绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'网吧名称','name');
-----住宿服务场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'住宿服务场所','keyPlaces','ACCOMMODATIONSERVICESPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ACCOMMODATIONSERVICESPLACE'),
'住宿服务场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=ACCOMMODATIONSERVICESPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'住宿服务场所地址','address',
'名称','name','地址','address','refineSearch','住宿服务场所绑定',1,'解除住宿服务场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'住宿服务场所名称','name');
-----餐饮服务场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'餐饮服务场所','keyPlaces','NEWFOODSERVICESPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWFOODSERVICESPLACE'),
'餐饮服务场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWFOODSERVICESPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'餐饮服务场所地址','address',
'名称','name','地址','address','refineSearch','餐饮服务场所绑定',1,'解除餐饮服务场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'餐饮服务场所名称','name');
-----旅游场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'旅游场所','keyPlaces','TRAVELINGPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='TRAVELINGPLACE'),
'旅游场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=TRAVELINGPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'旅游场所地址','address',
'名称','name','地址','address','refineSearch','旅游场所绑定',1,'解除旅游场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'旅游场所名称','name');
-----施工场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'施工场所','keyPlaces','CONSTRUCTIONPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='CONSTRUCTIONPLACE'),
'施工场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=CONSTRUCTIONPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'施工场所地址','address',
'名称','name','地址','address','refineSearch','施工场所绑定',1,'解除施工场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'施工场所名称','name');
-----其他场所
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'其他场所','keyPlaces','OTHERPLACE','companyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='OTHERPLACE'),
'其他场所名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=OTHERPLACE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'其他场所地址','address',
'名称','name','地址','address','refineSearch','其他场所绑定',1,'解除其他场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'其他场所名称','name');
commit;



----单位---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'unitPlaceBase','单位',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='unitPlaceBase'),
'单位名称','name','/resource/openLayersMap/images/greenBubble',1,'单位地址','address','单位类型','typeName',
'名称','name','地址','address','refineSearch','单位绑定',1,'解除单位绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');
-----党政机关
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'党政机关','keyPlaces','PARTYGOVERNMENTORGANCOMPANY','unitPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='PARTYGOVERNMENTORGANCOMPANY'),
'党政机关名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=PARTYGOVERNMENTORGANCOMPANY'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'党政机关地址','address',
'名称','name','地址','address','refineSearch','党政机关绑定',1,'解除党政机关绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'党政机关名称','name');
-----学校
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'学校','keyPlaces','NEWSCHOOLS','unitPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWSCHOOLS'),
'学校名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWSCHOOLS'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'学校地址','address',
'名称','name','地址','address','refineSearch','学校绑定',1,'解除学校绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'学校名称','name');
-----医院
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'医院','keyPlaces','NEWHOSPITAL','unitPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWHOSPITAL'),
'医院名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWHOSPITAL'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'医院地址','address',
'名称','name','地址','address','refineSearch','医院绑定',1,'解除医院绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'医院名称','name');
-----危险化学品单位
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'危险化学品单位','keyPlaces','NEWDANGEROUSCHEMICALSUNIT','unitPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWDANGEROUSCHEMICALSUNIT'),
'危险化学品单位名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWDANGEROUSCHEMICALSUNIT'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'危险化学品单位地址','address',
'名称','name','地址','address','refineSearch','危险化学品单位绑定',1,'解除危险化学品单位绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'危险化学品单位名称','name');
-----其他单位 
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'其他单位','keyPlaces','OTHERCOMPANY','unitPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='OTHERCOMPANY'),
'其他单位名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=OTHERCOMPANY'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'其他单位地址','address',
'名称','name','地址','address','refineSearch','其他单位绑定',1,'解除其他单位绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'其他单位名称','name');
commit;

----重点单位场所---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'keyCompanyPlaceBase','重点单位场所',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='keyCompanyPlaceBase'),
'重点单位场所名称','name','/resource/openLayersMap/images/greenBubble',1,'重点单位场所地址','address','重点单位场所类型','typeName',
'名称','name','地址','address','refineSearch','重点单位场所绑定',1,'解除重点单位场所绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');
-----安全生产重点--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'安全生产重点','keyPlaces','NEWSAFETYPRODUCTIONKEY','keyCompanyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWSAFETYPRODUCTIONKEY'),
'安全生产重点名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWSAFETYPRODUCTIONKEY'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'安全生产重点地址','address',
'名称','name','地址','address','refineSearch','安全生产重点绑定',1,'解除安全生产重点绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'安全生产重点名称','name');
-----消防安全重点--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'消防安全重点','keyPlaces','NEWFIRESAFETYKEY','keyCompanyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWFIRESAFETYKEY'),
'消防安全重点名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWFIRESAFETYKEY'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'消防安全重点地址','address',
'名称','name','地址','address','refineSearch','消防安全重点绑定',1,'解除消防安全重点绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'消防安全重点名称','name');
-----治安重点--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'治安重点','keyPlaces','NEWSECURITYKEY','keyCompanyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='NEWSECURITYKEY'),
'治安重点名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=NEWSECURITYKEY'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'治安重点地址','address',
'名称','name','地址','address','refineSearch','治安重点绑定',1,'解除治安重点绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'治安重点名称','name');
-----污染源--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'污染源','keyPlaces','POLLUTIONSOURCE','keyCompanyPlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='POLLUTIONSOURCE'),
'污染源名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=POLLUTIONSOURCE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'污染源地址','address',
'名称','name','地址','address','refineSearch','污染源绑定',1,'解除污染源绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'污染源名称','name');
commit;
----规模企业---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'sizedEnterprisePlaceBase','规模企业',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='sizedEnterprisePlaceBase'),
'规模企业名称','name','/resource/openLayersMap/images/greenBubble',1,'规模企业地址','address','规模企业类型','typeName',
'名称','name','地址','address','refineSearch','规模企业绑定',1,'解除规模企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');
-----规上企业--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'规上企业','keyPlaces','ENTERPRISE','sizedEnterprisePlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISE'),
'规上企业名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=ENTERPRISE'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'规上企业地址','address',
'名称','name','地址','address','refineSearch','规上企业绑定',1,'解除规上企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'规上企业名称','name');
-----规下企业--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'规下企业','keyPlaces','ENTERPRISEDOWN','sizedEnterprisePlaceBase',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISEDOWN'),
'规下企业名称','name','/baseinfo/companyPlace/companyPlaceViewDlg.jsp?modulKey=ENTERPRISEDOWN'||'&'||'cid=',
'/resource/openLayersMap/images/greenBubble',1,'规下企业地址','address',
'名称','name','地址','address','refineSearch','规下企业绑定',1,'解除规下企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'规下企业名称','name');
commit;