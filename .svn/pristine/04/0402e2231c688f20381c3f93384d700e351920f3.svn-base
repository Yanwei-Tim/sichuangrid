
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

--轨迹添加轨迹信息字段--
alter table positioningTrajectory  add trackInfo VARCHAR2(600);

--工作日历添加工作时间字段--
alter table workcalendars add startWorkTimeAM VARCHAR2(32) not null;
alter table workcalendars add endWorkTimeAM  VARCHAR2(32) not null;
alter table workcalendars add startWorkTimePM VARCHAR2(32) not null;
alter table workcalendars add endWorkTimePM VARCHAR2(32) not null;
commit;
commit;