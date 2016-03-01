--把组织场所名称改成重点场所--
update gismodulemanages set  modulename ='重点场所' where tablename='keyPlaces';  

---实有单位---
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'actualCompany','实有单位',
0,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'实有单位','keyPlaces','ACTUALCOMPANY','keyPlaces',1,sysdate,'admin');

insert into gisfunction
(id,moduleId,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='actualCompany'),(select id from gisTypeManages where key='ACTUALCOMPANY'),
'单位名称','name','/baseinfo/actualCompanyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=actualCompany'||'&'||'location.id=',
'/resource/openLayersMap/images/greenBubble',1,'实有单位地址','address',
'名称','name','地址','address','refineSearch','实有单位绑定',1,'解除实有单位绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'实有单位名称','name');

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
'名称','name','地址','address','refineSearch','社会组织绑定',1,'解除社会组织绑定',1,
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
'名称','name','地址','address','refineSearch','新经济组织绑定',1,'解除新经济组织绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'新经济组织名称','name');

--企业--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'enterprise','企业',
1,1,1,0,'keyPlaceMap',sysdate,'admin');

insert into gisfunction
(id,moduleId,titlename,titlevalue,iconurl,isviewicon,fieldnameA,fieldA,fieldnameB,fieldB,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='enterprise'),
'企业名称','name','/resource/openLayersMap/images/greenBubble',1,'企业地址','address','企业类型','typeName',
'名称','name','地址','address','refineSearch','企业绑定',1,'解除企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin');


--企业子类--
--规上企业--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'规上企业','keyPlaces','ENTERPRISEKEY','enterprise',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISEKEY'),
'规上企业名称','name','/baseinfo/safetyProductionKeyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=ENTERPRISEKEY'||'&'||'keyType=enterpriseKey'||'&'||'location.id=',
'/resource/openLayersMap/images/greenBubble',1,'规上企业地址','address',
'名称','name','地址','address','refineSearch','规上企业绑定',1,'解除规上企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'规上企业名称','name');

--规下企业--
insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'规下企业','keyPlaces','ENTERPRISEDOWNKEY','enterprise',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,searchfieldbname,searchfieldb,functionType,
boundeventname,boundeventisvalid,unboundeventname,unboundeventisvalid,event,
createdate,createuser,
detailFieldNameA,detailFieldA) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where key='ENTERPRISEDOWNKEY'),
'规下企业名称','name','/baseinfo/safetyProductionKeyManage/dispatchOperate.action?superviceRecordName=巡场情况'||'&'||'supervisorName=治安负责人'||'&'||'mode=view'||'&'||'locationType=ENTERPRISEDOWNKEY'||'&'||'keyType=enterpriseDownKey'||'&'||'location.id=',
'/resource/openLayersMap/images/greenBubble',1,'规下企业地址','address',
'名称','name','地址','address','refineSearch','规下企业绑定',1,'解除规下企业绑定',1,
(select id from propertydicts p where p.displayname='绑定房屋'),sysdate,'admin',
'规下企业名称','name');


commit;