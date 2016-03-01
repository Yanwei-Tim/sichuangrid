 
 create sequence s_publicComplexPlaces
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

/*==============================================================*/
/* Table: publicComplexPlaces                                          */
/*==============================================================*/
create table publicComplexPlaces (
	id                   NUMBER(10)                      not null,
	orgId                NUMBER(10)                      not null,
	orgInternalCode      VARCHAR2(32)                    not null,
	placeName      		 VARCHAR2(150)  				 not null,
	placeAddress         VARCHAR2(150)  				 not null,
	manager		 		 VARCHAR2(32),
	managerMobile 		 VARCHAR2(30),
	managerTelephone  	 VARCHAR2(30),
	type				 NUMBER(10),
	detailedType		 NUMBER(10),
	hiddenCases          VARCHAR2(300),
	hiddenRectification  VARCHAR2(300),
	logOutReason         VARCHAR2(150),
	isEmphasis    		 NUMBER(1)   	           	    default 0,
	logOutTime  		 DATE,
	remark     			 VARCHAR2(900),
	imgUrl               VARCHAR2(300),
	fullPinyin           VARCHAR2(30),
    simplePinyin         VARCHAR2(10),
    createUser           VARCHAR2(32)                    not null,
    updateUser           VARCHAR2(32),
    createDate           DATE                            not null,
    buildingId      	 VARCHAR2(30),
    centerX 			 NUMBER(10),
    centerY 			 NUMBER(10),
    updateDate           DATE,
    attentionExtent      NUMBER(10),
    SOURCESSTATE NUMBER(1)  default 1 ,
	constraint			 pkPublicComplexPlace primary key(id),
	constraint			 fkPublicComplexPlace foreign key(orgId)
		references organizations(id)
);
comment on table publicComplexPlaces is
'公共复杂场所信息';
comment on column publicComplexPlaces.id is
'公共复杂场所Id';
comment on column publicComplexPlaces.orgId is
'所属网格';
comment on column publicComplexPlaces.orgInternalCode is
'所属责任区编号';
comment on column publicComplexPlaces.placeName is
'场所名称';
comment on column publicComplexPlaces.placeAddress is
'场所地址';
comment on column publicComplexPlaces.manager is
'负责人';
comment on column publicComplexPlaces.managerMobile is
'负责人手机';
comment on column publicComplexPlaces.managerTelephone is
'负责人电话';
comment on column publicComplexPlaces.type is
'公共复杂场所类别';
comment on column publicComplexPlaces.detailedType is
'公共复杂场所细类';
comment on column publicComplexPlaces.hiddenCases is
'存在隐患';
comment on column publicComplexPlaces.hiddenRectification is
'隐患整改情况';
comment on column publicComplexPlaces.logOutReason is
'注销时间';
comment on column publicComplexPlaces.isEmphasis is
'注销标识';
comment on column publicComplexPlaces.logOutTime is
'注销时间';
comment on column publicComplexPlaces.remark is
'备注';
comment on column publicComplexPlaces.attentionExtent is
'关注程度：1.一般；2.中等；3.严重';


/*==============================================================*/
/* Index: idx_pcp_orgIdAndPlaceName                    */
/*==============================================================*/
create unique index idx_pcp_orgIdAndPlaceName on publicComplexPlaces (
   orgId ASC,
   placeName ASC
);




insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'公共复杂场所分类');
insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'公共复杂场所细类');

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所分类'),0,1,'公共娱乐场所','ggylcs','gonggongyulechangsuo','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所分类'),1,2,'景点','jd','jingdian','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所分类'),2,3,'车站码头','jd','jingdian','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所分类'),3,4,'市场','sc','shichang','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,1,'KTV','KTV','KTV','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,2,'舞厅','wt','wuting','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,3,'迪吧','db','diba','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,4,'酒吧','jb','jiuba','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,5,'网吧','wb','wangba','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,6,'球馆','qg','qiuguan','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,7,'电玩','dw','dianwan','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,8,'棋牌室','qps','qipaishi','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),0,9,'其他','qt','qita','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),3,10,'商场','sc','shangchang','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),3,11,'超市','cs','chaoshi','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),3,12,'菜市场','csc','caishichang','admin',sysdate);

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='公共复杂场所细类'),3,13,'其它','qt','qita','admin',sysdate);


insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'公共复杂场所','allPublicComplexPlacesStatistic',1,'研判分析',(select id from permissions where ename='importantLocaltionStat_stat'),1,
    '/hotModuel/statAnalyse/baseInfoStat/publicComplexPlaces/index.ftl?keyTpe=PUBLICCOMPLEXPLACES',(select max(indexId)+1 from permissions where parentId = (select id from permissions where ename='importantLocaltionStat_stat')));  

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'公共复杂场所','publicComplexPlacesManagement',1,'重点场所',(select id from permissions where ename='locationInformation'),1,'/hotModuel/baseinfo/siteinfo/publicComplexPlaces/publicComplexPlacesList.jsp',
	'/hotModuel/baseinfo/leaderView/importPlaceStatistics/publicComplexPlacesStatistics.jsp',(select max(indexId)+1 from permissions where parentId = (select id from permissions where ename='locationInformation')));

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,0);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updatePublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,1);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','viewPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,2);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deletePublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,3);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'查询','searchPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,4);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'导入','importPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,5);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'导出','downPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,6);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'取消关注','cancelAttendedPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,7);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'重新关注','attendedPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,8);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'管理巡场情况','recordPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,9);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'转移','transferPublicComplexPlaces',0,'公共复杂场所',(select id from permissions where ename='publicComplexPlacesManagement'),1,10);
   
commit;