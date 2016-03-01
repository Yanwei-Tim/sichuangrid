drop table dustbin;

/* Table: dustbin                                               */
/*==============================================================*/
create table dustbin(
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32),
   partType        NUMBER(10)                      not null,
   partName        NUMBER(10)                      not null,
   dustbinCode          VARCHAR2(60)                    not null,
   address              VARCHAR2(90),
   deptName             VARCHAR2(60)                   not null,
   hasVideo            NUMBER(1)              default 0,
   ownershipUnitName    VARCHAR2(60),
   maintenanceUnitName  VARCHAR2(60),
   remark        VARCHAR2(600),
   IMGURL               VARCHAR2(300),
   ISEMPHASIS           NUMBER(1)             default 0,
   logOutTime           DATE,
   logOutReason         VARCHAR2(300),
   centerLon            VARCHAR2(32),
   centerLat        VARCHAR2(32),
   centerLon2           VARCHAR2(32),
   centerLat2        VARCHAR2(32),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint pkdustbin primary key (id),
   constraint fkdustbinOrg foreign key (orgId)
         references organizations (id)
);

comment on table dustbin is
'部件信息';

comment on column dustbin.id is
'部件信息id';

comment on column dustbin.orgId is
'所属网格';

comment on column dustbin.dustbinCode is
'部件标识码';

comment on column dustbin.address is
'地址';

comment on column dustbin.hasVideo is
'是否有视频流';

comment on column dustbin.deptName is
'主管部门名称';

comment on column dustbin.ownershipUnitName is
'权属单位名称';

comment on column dustbin.maintenanceUnitName is
'养护单位名称';

comment on column dustbin.createUser is
'创建用户';

comment on column dustbin.updateUser is
'修改用户';

comment on column dustbin.createDate is
'创建日期';

comment on column dustbin.updateDate is
'修改时间';

comment on column dustbin.remark is
'备注';

comment on column dustbin.IMGURL is 
'图片路径';

comment on column dustbin.ISEMPHASIS is 
'是否注销';

comment on column dustbin.logOutTime is 
'注销时间';

comment on column dustbin.logOutReason is 
'注销原因';

comment on column dustbin.centerLon is 
'部件信息经度';

comment on column dustbin.centerLat is 
'部件信息纬度';

/* permissions */
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='DigitalUrbanManagement')
　　connect by prior p.id =  p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='DigitalUrbanManagement')
　　connect by prior p.id =  p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='DigitalUrbanManagement')
　　connect by prior p.id =  p.parentid
);

commit;

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'部件管理','digitalUrbanManagement',1,'数字城管',(select id from permissions where ename='digitalCityManagement'),1,0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'部件信息','dustbinManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp',0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增', 'addDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'updateDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导入', 'importDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'downloadDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedDustbin', 0, '部件信息', 1, ' ', (select id from permissions where ename='dustbinManagement'));
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'公共设施类','communalFacilitiesManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=publicFacilities',1);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'道路交通类','roadTrafficManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=roadTraffic',2);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'市容环境类','cityEnvironmrntManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=cityEnvironmrnt',3);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'园林绿化类','landscapingManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=landscaping',4);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'房屋土地类','houseLandManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=houseLand',5);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'其他设施类','otherFacilitiesManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=otherFacilities',6);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'扩展部件类','expansionComponentsManagement',1,'部件管理',(select id from permissions where ename='digitalUrbanManagement'),1,'/hotModuel/digitalCity/dustbinManagement/dustbinList.jsp?partType=expansionComponents',7);
commit; 


insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'部件类型');
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'部件名称');
commit; 

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 0, 1, '公共设施类', 'gyssl', 'gongyongsheshilei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 1, 2, '道路交通类', 'dljtl', 'daolujiaotonglei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 2, 3, '市容环境类', 'srhjl', 'shironghuanjinglei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 3, 4, '园林绿化类', 'yllhl', 'yuanlinlvhualei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 4, 5, '房屋土地类', 'fftdl', 'fangwutudilei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 5, 6, '其他设施类', 'qtssl', 'qitasheshilei', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件类型'), 6, 7, '扩展部件类', 'kzbjl', 'kuozhanbujianlei', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 1, '上水井盖',  'ssjg', 'shangshuijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 2, '污水井盖',  'wsjg', 'wushuijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 3, '雨水井盖',  'ysjg', 'yushuijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 4, '雨水算子',  'yssz', 'yushuisuanzi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 5, '电力井盖',  'dljg', 'dianlijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 6, '路灯井盖',  'ldjg', 'ludengjinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 7, '通讯井盖',  'txjg', 'tongxunjinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 8, '电视井盖',  'dsjg', 'dianshijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 9, '网络井盖',  'wljg', 'wangluojinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 10, '热力井盖',  'rljg', 'relijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 11, '燃气井盖',  'rqjg', 'ranqijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 12, '公安井盖',  'gajg', 'gonganjinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 13, '消防设施',  'xfss', 'xiaofangsheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 14, '无主井盖',  'wzjg', 'wuzhujinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 15, '通讯交接箱',  'txjjx', 'tongxunjiaojiexiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 16, '电力设施',  'dlss', 'dianlisheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 17, '立杆',  'lg', 'ligan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 18, '路灯',  'ld', 'ludeng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 19, '地灯',  'dd', 'dideng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 20, '景观灯',  'jgd', 'jingguandeng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 21, '报刊亭',  'bkt', 'baokanting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 22, '电话亭',  'dht', 'dianhuating', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 23, '邮筒',  'yt', 'youtong', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 24, '信息亭',  'xxt', 'xinxiting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 25, '自动售货机',  'zdshj', 'zidongshouhuoji', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 26, '健身设施',  'jsss', 'jianshensheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 27, '中水井盖',  'zsjg', 'zhongshuijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 28, '公交井盖',  'gjjg', 'gongjiaojinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 29, '输油（气）井盖',  'syqjg', 'shuyouqijinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 30, '特殊井盖',  'tsjg', 'teshujinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 31, '民用水井',  'mysj', 'minyongshuijing', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 32, '供水器',  'gsq', 'gongshuiqi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 33, '高压线铁塔',  'gyxtt', 'gaoyaxiantieta', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 34, '变压器（箱）',  'byqx', 'bianyaqixiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 35, '燃气调压站（箱）',  'rqdyzx', 'ranqidiaoyazhanxiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 36, '监控电子眼',  'jkdzy', 'jiankongdianziyan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 37, '售货亭',  'sht', 'shouhuoting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 0, 38, '治安岗亭',  'zagt', 'zhiangangting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 39, '停车场',  'tcc', 'tingchechang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 40, '停车咪表',  'tcmb', 'tingchemibiao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 41, '公交站亭',  'gjzt', 'gongjiaozhanting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 42, '出租车站牌',  'czczp', 'chuzuchezhanpai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 43, '过街天桥',  'gjtq', 'guojietianqiao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 44, '地下通道',  'dxtd', 'dixiatongdao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 45, '高架立交桥',  'gjljq', 'gaojialijiaoqiao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 46, '跨河桥',  'khq', 'kuaheqiao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 47, '交通标志牌',  'jtbzp', 'jiaotongbiaozhipai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 48, '交通信号灯',  'jtxhd', 'jiaotongxinhaodeng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 49, '交通护栏',  'jthl', 'jiaotonghulan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 50, '存车支架',  'cczj', 'cunchezhijia', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 51, '路名牌',  'lmp', 'lumingpai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 52, '交通信号设施',  'jtxhss', 'jiaotongxinhaosheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 53, '道路信息显示屏',  'dlxxxsp', 'daoluxinxixianshipin', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 54, '道路隔音屏',  'dlgyp', 'daolugeyinping', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 1, 55, '交通岗亭',  'jtgt', 'jiaotonggangting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 56, '公共厕所',  'ggcs', 'gonggongcesuo', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 57, '化粪池',  'hfc', 'huafenchi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 58, '公厕指示牌',  'gczsp', 'gongcezhishipai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 59, '垃圾间（楼）',  'ljjl', 'lajijianlou', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 60, '垃圾箱',  'ljx', 'lajixiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 61, '灯箱霓虹灯',  'dxnhd', 'dengxiangnihongdeng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 62, '广告牌匾',  'ggpb', 'guanggaopaibian', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 63, '环保监测站',  'hbjcz', 'huanbaojiancezhan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 64, '气象监测站',  'qxjcz', 'qixiangjiancezhan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 65, '污水口监测站',  'wskjcz', 'wushuikoujiancezhan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 2, 66, '噪声显示屏',  'zsxsp', 'zaoshengxianshiping', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 67, '古树名木',  'gsmm', 'gushumingmu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 68, '行道树',  'xds', 'xingdaoshu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 69, '护树设施',  'hsss', 'hushusheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 70, '花架花钵',  'hjhb', 'huajiahuabo', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 71, '绿地',  'ld', 'lvdi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 72, '雕塑',  'ds', 'diaosu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 73, '街头坐椅',  'jtzy', 'jietouzuoyi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 74, '绿地护栏',  'ldhl', 'lvdihulan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 75, '绿地维护设施',  'ldwhss', 'lvdiweihusheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 3, 76, '喷泉',  'pq', 'penquan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 4, 77, '宣传栏',  'xcl', 'xuanchuanlan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 4, 78, '人防工事',  'rfgs', 'renfanggongshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 4, 79, '公房地下室',  'gfdxs', 'gongfangdixiashi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 80, '重大危险源',  'zdwxy', 'zhongdaweixianyuan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 81, '工地',  'gd', 'gongdi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 82, '水域附属设施',  'syfsss', 'shuiyufushusheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 83, '水域护栏',  'syhl', 'shuiyuhulan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 84, '港监设施',  'gjss', 'gangjiansheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 5, 85, '防汛墙',  'fxq', 'fangxunqiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 86, '屋（楼）顶广告',  'wldgg', 'wuloudingguanggao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 87, '墙面广告',  'qmgg', 'qiangmianguanggao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 88, '高立柱（擎天柱）广告',  'glzqtzgg', 'gaolizhuqingtianzhug', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 89, '大型支架广告',  'dxzjgg', 'daxingzhijiaguanggao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 90, '跨街桥梁广告',  'kjqlgg', 'kuajieqiaoliangguang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 91, '围墙（栏）广告',  'wqlgg', 'weiqianglanguanggao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 92, '小型立式广告',  'xxlsgg', 'xiaoxinglishiguangga', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 93, '悬挂广告',  'xggg', 'xuanguaguanggao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 94, '牌匾标识',  'pbbs', 'paibianbiaoshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 95, '街路',  'jl', 'jielu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 96, '巷道',  'xd', 'xiangdao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 97, '无名街巷',  'wmjx', 'wumingjiexiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 98, '在建道路',  'zjdl', 'zaijiandaolu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 99, '便道',  'bd', 'biandao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 100, '公交调度站亭',  'gjddzt', 'gongjiaodiaoduzhanti', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 101, '食品销售亭',  'spxst', 'shipinxiaoshouting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 102, '菜篮子销售亭',  'clzxst', 'cailanzixiaoshouting', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 103, '停车场看护亭',  'tcckht', 'tingchechangkanhutin', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 104, '园林井盖',  'yljg', 'yuanlinjinggai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 105, '大门',  'dm', 'damen', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 106, '门牌',  'mp', 'menpai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 107, '跨河管道',  'khgd', 'kuaheguandao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 108, '示范街牌',  'sfjp', 'shifanjiepai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 109, '果皮箱',  'gpx', 'guopixiang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 110, '垃圾中转站',  'ljzzz', 'lajizhongzhuanzhan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 111, '单位指示牌',  'dwzsp', 'danweizhishipai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 112, '自行车停车场',  'zxctcc', 'zixingchetingchechan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 113, '自助取款机',  'zzqkj', 'zizhuqukuanji', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 114, '自助售电机',  'zzsdj', 'zizhushoudianji', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 115, '台阶',  'tj', 'taijie', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 116, '旅游设施',  'lyss', 'lvyousheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 117, '文物保护设施',  'wwbhss', 'wenwubaohusheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 118, '水域标示牌',  'sybsp', 'shuiyubiaoshipai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 119, '张贴栏',  'ztl', 'zhangtielan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 120, '减速带',  'jsd', 'jiansudai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 121, '防撞柱',  'fzz', 'fangzhuangzhu', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 122, '充电桩',  'cdz', 'chongdianzhuang', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 123, '交通隔离墩',  'jtgld', 'jiaotonggelidun', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 124, '公用旗杆',  'gyqg', 'gongyongqigan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 125, '单位旗杆',  'dwqg', 'danweiqigan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 126, '盲道',  'md', 'mangdao', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 127, '小公园',  'xgy', 'xiaogongyuan', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 128, '铁道口设施',  'tdkss', 'tiedaokousheshi', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 129, '自行车租赁点',  'zxczld', 'zixingchezulindian', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 130, '公交站牌',  'gjzp', 'gongjiaozhanpai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 131, '射灯',  'sd', 'shedeng', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 132, '无障碍设施指示牌',  'wzasszsp', 'wuzhangaisheshizhish', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 133, '公安指示牌',  'gazsp', 'gonganzhishipai', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='部件名称'), 6, 134, '电信电力指示牌',  'dxdlzsp', 'dianxindianlizhiship', 'admin', '', to_date('03-07-2013 09:47:34', 'dd-mm-yyyy hh24:mi:ss'), null);

commit; 