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

update gismodulemanages set modulename='楼宇信息' where tablename='buildDatas';
update gisfunction set titlename='楼宇名称',searchfieldaname='楼宇名称',detailfieldnamea='楼宇名称' where moduleId=(select id from gismodulemanages where tablename='buildDatas');

commit;