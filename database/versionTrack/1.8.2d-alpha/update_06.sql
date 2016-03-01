----删除单位场所改造前的不需要的
delete gisfunction where moduleId in( select id from gismodulemanages where 
	tablename in('keyPlaces','actualCompany','enterprise') ) or
       sonclassid in(select id from gisTypeManages where 
       	key in(select key from gistypemanages where innerkey in('keyPlaces','actualCompany','enterprise')) ); 

delete gistypemanages where innerkey in('keyPlaces','actualCompany','enterprise');
delete gismodulemanages where tablename in('keyPlaces','actualCompany','enterprise');

-----场所--
insert into gismodulemanages
(id,tablename,modulename,ishassonclass,isSystemAttribute,isBusinessLayer,
isPopulation,modeType,createdate,createuser) values
(s_gisModuleManages.Nextval,'companyPlaceBase','场所',1,1,1,0,'keyPlaceMap',sysdate,'admin');

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