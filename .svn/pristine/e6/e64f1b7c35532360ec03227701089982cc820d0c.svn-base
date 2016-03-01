create sequence S_VIDEOSYSTEM
increment by 1
start with 1
maxvalue 999999999;


--监控视频表
create table VIDEOSYSTEM  (
   ID                   NUMBER(10),
   orgid         NUMBER(10),                     
   ORGINTERNALCODE      VARCHAR2(32),
   code                 VARCHAR2(60)    not null,
   address              VARCHAR2(90),
   centerLon            VARCHAR2(32),
   centerLat            VARCHAR2(32),
   centerLon2           VARCHAR2(32),
   centerLat2           VARCHAR2(32),
   isEmphasis           NUMBER(1)        default 0,
   logOutTime           DATE,
   logOutReason         VARCHAR2(300),
   type                 VARCHAR2(32),
   VIDEONAME            VARCHAR2(120),
   url              VARCHAR2(120),
   ACCOUNT              VARCHAR2(120),
   PASSWORD             VARCHAR2(120),
   PUID                 VARCHAR2(120),
   CHANNEL              VARCHAR2(120),
   ISDELETE             NUMBER(1)                     default 0,
   CREATEUSER           VARCHAR2(32)                    not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                            not null,
   UPDATEDATE           DATE,
   constraint PK_VIDEOSYSTEM primary key (ID)
);


comment on table VIDEOSYSTEM is
'监控视频信息表';

comment on column VIDEOSYSTEM.orgid is
'所属网格';

comment on column VIDEOSYSTEM.ORGINTERNALCODE is
'所属网格编号';

comment on column VIDEOSYSTEM.code is
'视频真实编号';

comment on column VIDEOSYSTEM.VIDEONAME is
'视频名称';

comment on column VIDEOSYSTEM.ADDRESS is
'视频地址';

comment on column VIDEOSYSTEM.ACCOUNT is
'视频账号名';

comment on column VIDEOSYSTEM.PASSWORD is
'视频密码';

comment on column VIDEOSYSTEM.PUID is
'视频PUID';

comment on column VIDEOSYSTEM.CHANNEL is
'视频通道编号';

comment on column VIDEOSYSTEM.CENTERLON is
'视频监控点经度';

comment on column VIDEOSYSTEM.CENTERLAT is
'视频监控点维度';

comment on column VIDEOSYSTEM.ISDELETE is
'是否已删除0.未删除 1.已删除';



--监控视频权限
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'监控视频','videoSystemManagement',1,'公安部件',(select id from permissions where ename='publicSecurityManagement'),1,'/digitalCity/publicSecurity/videoSystemManagement/videoSystemList.jsp',3);
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
    insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
      insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));
     insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedVideoSystem', 0, '监控视频', 1, ' ', (select id from permissions where ename='videoSystemManagement'));

insert into gisTypeManages
(id,name,tableName,key,innerKey,checked,createdate,createuser) values 
(gisTypeManages_squ.Nextval,'监控视频','VIDEOSYSTEM','VIDEOSYSTEM','publicSecurity',1,sysdate,'admin');

insert into gisfunction
(id,sonclassid,titlename,titlevalue,detailsurl,iconurl,isviewicon,fieldnameA,fieldA,
functionType,createdate,createuser,searchfieldAname,searchfieldA,searchfieldBname,searchfieldB,
detailFieldNameA,detailFieldA,detailFieldNameB,detailFieldB,boundeventname,boundeventisvalid,
unboundeventname,unboundeventisvalid,event) values 
(s_gisFunction.Nextval,(select id from gisTypeManages where tablename='VIDEOSYSTEM'),'编号','code',
'/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=videoSystem'||'&'||'mode=view'||'&'||'id=',
'/resource/openLayersMap/images/redBubble',1,'地址','address',
'refineSearch',sysdate,'admin','编号','code','地址','address',
'编号','code','地址','address',
'监控视频绑定',1,'解除监控视频绑定',1,(select id from propertydicts p where p.displayname='绑定地图'));
--修改pop显示字段
update gisfunction set titlename='地址',titlevalue='address' where sonclassid=(select id from gisTypeManages where tablename='VIDEOSYSTEM');
update gisfunction set fieldNameA='编号',fieLdA='code' where sonclassid=(select id from gisTypeManages where tablename='VIDEOSYSTEM');
commit;