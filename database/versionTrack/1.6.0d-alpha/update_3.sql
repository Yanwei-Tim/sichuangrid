/*==============================================================*/
/* sequence: skynet 天网序列                              				*/
/*==============================================================*/   
create sequence s_skynet
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: skynet 天网表                              					*/
/*==============================================================*/
 create table skynet(
 	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	code					VARCHAR2(60)    not null,
	address              	VARCHAR2(90),
	centerLon				VARCHAR2(32),
  	centerLat				VARCHAR2(32),
  	centerLon2				VARCHAR2(32),
  	centerLat2				VARCHAR2(32),
  	isEmphasis           	NUMBER(1)        default 0,
    logOutTime           	DATE,
    logOutReason         	VARCHAR2(300),
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
	type					VARCHAR2(32),
   	constraint PKSKYNET primary key (ID)
 	
 );
 -- Add comments to the table 
comment on table skynet
  is '天网表';
-- Add comments to the columns 
comment on column skynet.ID
  is '天网id';
comment on column skynet.orgId
  is '所属网格';
comment on column skynet.orgInternalCode
  is '所属网格编号';
comment on column skynet.code
  is '编号';  
comment on column skynet.address
  is '地址';  
comment on column skynet.centerLon
  is '2.5维经度';
comment on column skynet.centerLat
  is '2.5维纬度';
comment on column skynet.centerLon2
  is '2维经度';
comment on column skynet.centerLat2
  is '2维纬度';
comment on column skynet.isEmphasis 
  is '是否注销';
comment on column skynet.logOutTime 
  is '注销时间';
comment on column skynet.logOutReason 
  is '注销原因';
comment on column skynet.CREATEUSER
  is '创建人';
comment on column skynet.UPDATEUSER
  is '修改人';
comment on column skynet.CREATEDATE
  is '创建时间';
comment on column skynet.UPDATEDATE
  is '修改时间';
comment on column skynet.type
  is '公安类型'; 
  

 /*==============================================================*/
/* sequence: bayonet 卡口序列                              				*/
/*==============================================================*/   
create sequence s_bayonet
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: bayonet 卡口表                              					*/
/*==============================================================*/
 create table bayonet(
 	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	code					VARCHAR2(60)    not null,
	address              	VARCHAR2(90),
	centerLon				VARCHAR2(32),
  	centerLat				VARCHAR2(32),
  	centerLon2				VARCHAR2(32),
  	centerLat2				VARCHAR2(32),
  	isEmphasis           	NUMBER(1)        default 0,
    logOutTime           	DATE,
    logOutReason         	VARCHAR2(300),
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
	type					VARCHAR2(32),
   	constraint PKBAYONET primary key (ID)
 	
 );
 -- Add comments to the table 
comment on table bayonet
  is '卡口表';
-- Add comments to the columns 
comment on column bayonet.ID
  is '卡口id';
comment on column bayonet.orgId
  is '所属网格';
comment on column bayonet.orgInternalCode
  is '所属网格编号';
comment on column bayonet.code
  is '编号';  
comment on column bayonet.address
  is '地址';  
comment on column bayonet.centerLon
  is '2.5维经度';
comment on column bayonet.centerLat
  is '2.5维纬度';
comment on column bayonet.centerLon2
  is '2维经度';
comment on column bayonet.centerLat2
  is '2维纬度';
comment on column bayonet.CREATEUSER
  is '创建人';
comment on column bayonet.isEmphasis 
  is '是否注销';
comment on column bayonet.logOutTime 
  is '注销时间';
comment on column bayonet.logOutReason 
  is '注销原因';
comment on column bayonet.UPDATEUSER
  is '修改人';
comment on column bayonet.CREATEDATE
  is '创建时间';
comment on column bayonet.UPDATEDATE
  is '修改时间'; 
comment on column bayonet.type
  is '公安类型'; 
  
  
 /*==============================================================*/
/* sequence: snapshotSystem 抓拍系统序列                              				*/
/*==============================================================*/   
create sequence s_snapshotSystem
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
  /*==============================================================*/
/* Table: snapshotSystem 抓拍系统表                              					*/
/*==============================================================*/
 create table snapshotSystem(
 	id						NUMBER(10)   	not null,
	orgId           		NUMBER(10)      not null,
	orgInternalCode      	VARCHAR2(32)    not null,
	code					VARCHAR2(60)    not null,
	address              	VARCHAR2(90),
	centerLon				VARCHAR2(32),
  	centerLat				VARCHAR2(32),
  	centerLon2				VARCHAR2(32),
  	centerLat2				VARCHAR2(32),
  	isEmphasis           	NUMBER(1)        default 0,
    logOutTime           	DATE,
    logOutReason         	VARCHAR2(300),
	createUser				VARCHAR2(60) 	not null,
	updateUser				VARCHAR2(60),
	createDate				DATE 			not null,
	updateDate				DATE,
	type					VARCHAR2(32),
   	constraint PKSNAPSHOTSYSTEM primary key (ID)
 	
 );
 -- Add comments to the table 
comment on table snapshotSystem
  is '抓拍系统表';
-- Add comments to the columns 
comment on column snapshotSystem.ID
  is '抓拍系统id';
comment on column snapshotSystem.orgId
  is '所属网格';
comment on column snapshotSystem.orgInternalCode
  is '所属网格编号';
comment on column snapshotSystem.code
  is '编号';  
comment on column snapshotSystem.address
  is '地址';  
comment on column snapshotSystem.centerLon
  is '2.5维经度';
comment on column snapshotSystem.centerLat
  is '2.5维纬度';
comment on column snapshotSystem.centerLon2
  is '2维经度';
comment on column snapshotSystem.centerLat2
  is '2维纬度';
comment on column snapshotSystem.isEmphasis 
  is '是否注销';
comment on column snapshotSystem.logOutTime 
  is '注销时间';
comment on column snapshotSystem.logOutReason 
  is '注销原因';
comment on column snapshotSystem.CREATEUSER
  is '创建人';
comment on column snapshotSystem.UPDATEUSER
  is '修改人';
comment on column snapshotSystem.CREATEDATE
  is '创建时间';
comment on column snapshotSystem.UPDATEDATE
  is '修改时间';  
comment on column snapshotSystem.type
  is '公安类型';   
  
--公安部件权限--
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'公安部件','publicSecurityManagement',1,'数字城管',(select id from permissions where ename='digitalCityManagement'),1,0);
    
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'天网','skynetManagement',1,'公安部件',(select id from permissions where ename='publicSecurityManagement'),1,'/digitalCity/publicSecurity/skynetManagement/skynetList.jsp',0);
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedSkynet', 0, '天网', 1, ' ', (select id from permissions where ename='skynetManagement'));

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'卡口','bayonetManagement',1,'公安部件',(select id from permissions where ename='publicSecurityManagement'),1,'/digitalCity/publicSecurity/bayonetManagement/bayonetList.jsp',0);
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedBayonet', 0, '卡口', 1, ' ', (select id from permissions where ename='bayonetManagement'));
    
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'抓拍系统','snapshotSystemManagement',1,'公安部件',(select id from permissions where ename='publicSecurityManagement'),1,'/digitalCity/publicSecurity/snapshotSystemManagement/snapshotSystemList.jsp',0);
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedSnapshotSystem', 0, '抓拍系统', 1, ' ', (select id from permissions where ename='snapshotSystemManagement'));
    
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

commit;