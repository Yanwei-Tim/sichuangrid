-- 网格地图网格员队伍管理详情脚本
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

commit;