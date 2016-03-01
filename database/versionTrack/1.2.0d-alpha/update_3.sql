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
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='SCENICTRAFFIC'),
'线路名称','name','/baseinfo/scenicTrafficManage/dispatchOperate.action?superviceRecordName=undefined'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=undefined'||'&'||'id=',
'/resource/openLayersMap/images/greenBubble',1,'负责地址','address',
'名称','name','地址','address','refineSearch','景区交通绑定',1,'解除景区交通绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'线路名称','name');

commit;