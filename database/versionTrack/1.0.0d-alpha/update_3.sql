--加二维经度纬度的字段--
alter table keyplaces  add centerLon2 varchar2(32);
alter table keyplaces  add centerLat2 varchar2(32);
alter table keyplaces  add buildDataId	NUMBER(10);

alter table builddatas  add centerLon2 varchar2(32);
alter table builddatas  add centerLat2 varchar2(32);

alter table houseInfo  add centerLon2 varchar2(32);
alter table houseInfo  add centerLat2 varchar2(32);

alter table Issues  add centerLon2 varchar2(32);
alter table Issues  add centerLat2 varchar2(32);

--修改gisTypeManages的其他场所的值--
update gisTypeManages  set key='OTHERLOCALES' where name='其他场所';

--修改gisfunction的住房信息的详情url--
update gisfunction set detailsurl='/baseinfo/actualHouseManage/dispatchHouseInfoByHouseId.action?houseInfo.id=' 
	where moduleId=(select id from gismodulemanages where tablename='houseInfo');
	
--修改gisfunction的城市部件的详情url--
update gisfunction set detailsurl='/baseinfo/dustbinManage/dispatchOperate.action?mode=view'||'&'||'location.id=' 
	where moduleId = (select id from gismodulemanages where tablename='dustbin');

commit;