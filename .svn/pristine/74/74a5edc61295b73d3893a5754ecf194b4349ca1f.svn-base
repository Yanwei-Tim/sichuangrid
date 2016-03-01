create table PERMANENTADDRESS
(
  addressno varchar2(60) unique,
  addressname       varchar2(60),
  parentid      varchar2(60),
  addressLevel  varchar2(5)
);
comment on column PERMANENTADDRESS.addressno is '地区编号';
comment on column PERMANENTADDRESS.addressname is '地区名称';
comment on column PERMANENTADDRESS.parentid is '上级ID';
comment on column PERMANENTADDRESS.addressLevel is '地区级别:1、省级   2、市级  3、区县级';

create sequence S_PERMANENTADDRESSLOG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table PERMANENTADDRESSLOG (
 id          number(10)          not null primary key,
 province    VARCHAR2(60),
 city        VARCHAR2(60),
 district    VARCHAR2(60),
 addslevel   number(1),
 addressNo   VARCHAR2(60),
 changedName VARCHAR2(60),
 optionType  number(1),
 jobState    number(1) 			 default 0,
 CREATEUSER  VARCHAR2(32)        not null,
 UPDATEUSER  VARCHAR2(32),
 CREATEDATE  DATE                not null,
 UPDATEDATE  DATE
);

comment on table PERMANENTADDRESSLOG
 is '省市县变更Job';
comment on column PERMANENTADDRESSLOG.Id
 is 'ID';
comment on column PERMANENTADDRESSLOG.Province
 is '原省';
comment on column PERMANENTADDRESSLOG.City
 is '原市';
comment on column PERMANENTADDRESSLOG.District
 is '原县';
comment on column PERMANENTADDRESSLOG.Addslevel
 is '变更地址层级';
comment on column PERMANENTADDRESSLOG.Addressno
 is '变更地址的编号';
comment on column PERMANENTADDRESSLOG.Changedname
 is '变更名称';
comment on column PERMANENTADDRESSLOG.Optiontype
 is '操作类型 1 as 新增 2 as 修改 3 as 删除';
comment on column PERMANENTADDRESSLOG.Jobstate
 is 'Job处理类型 0 as 初始化,新增  1 as 修改,删除  2 as 已处理';


insert into permissions  (id,cname,ename,permissionType,moduleName,normalUrl,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'户籍地管理','permanentAddrManagement',1,'系统高级管理','/hotModuel/sysadmin/permanentAddressManage/permanentAddressList.jsp',(select id  from permissions where ename='advancedSystemManagement'),1,0);

insert into permissions  (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addPermanentAddr',0,'户籍地管理',(select id  from permissions where ename='permanentAddrManagement'),1,1);
insert into permissions  (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updatePermanentAddr',0,'户籍地管理',(select  id from permissions where ename='permanentAddrManagement'),1,2);
   
insert into permissions  (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deletePermanentAddr',0,'户籍地管理',(select  id from permissions where ename='permanentAddrManagement'),1,3);
   
insert into permissions  (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','searchPermanentAddr',0,'户籍地管理',(select  id from permissions where ename='permanentAddrManagement'),1,4);

commit;