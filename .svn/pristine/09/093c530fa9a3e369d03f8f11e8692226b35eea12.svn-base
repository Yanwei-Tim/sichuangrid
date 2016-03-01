delete from gisfunction where titlevalue='subject';

update gisTypeManages set key='PERSONFORTHING' where tablename='personForThing';
update gisTypeManages set key='PERSONALREADYTHING' where tablename='personAlreadyThing';
update gisTypeManages set key='GONETHROUGH' where tablename='goneThrough';
update gisTypeManages set key='FORTHING' where tablename='forThing';
update gisTypeManages set key='ALREADYTHING' where tablename='alreadyThing';
commit;


insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='issues'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='personForThing'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='personAlreadyThing'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');


insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='goneThrough'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisfunction
(id,moduleId,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gismodulemanages where tablename='jurisdictionsIssue'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='forThing'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
searchfieldaname,searchfielda,functionType,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event,createdate,createuser,fieldnameB,fieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,
searchfieldBname,searchfieldB,fieldnameC,fieldC,fieldnameD,fieldD) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='alreadyThing'),
'事件主题','subject','/issues/issueManage/dispatchForGis.action?mode=view'||'&'||'keyId=',
'/resource/openLayersMap/images/yellowBubble',1,'服务单号','serialNumber','事件主题','subject','refineSearch',
'事件绑定',1,'解除事件绑定',1,(select id from propertydicts p where p.displayname='绑定地图'),sysdate,'admin','发生地址','occurLocation',
'事件主题','subject','服务单号','serialNumber',
'服务单号','serialNumber','事件类型','issueType','解决状态','status');
commit;