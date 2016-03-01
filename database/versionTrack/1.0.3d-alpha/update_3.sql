--重点上访人员>>异常访>>新增“进市访”、“进县访”、“个人访”  2013-10-22 15:24:32
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where domainname = '异常上访'), 0, 
       (select max(displayseq) from propertydicts where propertydomainid = (
               select id from propertydomains where domainname = '异常上访'))+1, 
       '进市访',  'jsf', 'jinshifang', 'admin', '', sysdate, null);
       
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where domainname = '异常上访'), 0, 
       (select max(displayseq) from propertydicts where propertydomainid = (
               select id from propertydomains where domainname = '异常上访'))+1, 
       '进县访',  'jxf', 'jinxianfang', 'admin', '', sysdate, null);
       
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME,  SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where domainname = '异常上访'), 0, 
       (select max(displayseq) from propertydicts where propertydomainid = (
               select id from propertydomains where domainname = '异常上访'))+1, 
       '个人访',  'grf', 'gerenfang', 'admin', '', sysdate, null);
commit;

--严重精神障碍患者 添加 服务监管措施录入功能
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,parentId,DESCRIPTION,NORMALURL,LEADERURL,INDEXID)
  values(s_permissions.nextval,'服务监管措施','serviceSupervisionMeasuresMentalPatient',0,'严重精神障碍患者',1,(select id from permissions where ename = 'mentalPatientManagement'),'','','',null);
commit;

CREATE SEQUENCE S_SERVICESUPERVISIONMEASURES
MINVALUE 1
MAXVALUE 99999999999999999999
START WITH 1
INCREMENT BY 1
CACHE 20;
CREATE TABLE SERVICESUPERVISIONMEASURES  (
   ID                     NUMBER  NOT NULL,
   MENTALPATIENTID        NUMBER  NOT NULL,
   UPTIME                 DATE,
   SUPERVISEPERSONNEL     VARCHAR2(60),
   SUPERVISEINFO          VARCHAR2(400),
   CONSTRAINT PK_SERVICESUPERVISIONMEASURES PRIMARY KEY (ID)
);
COMMENT ON TABLE EXCELIMPORTLOG IS
'严重精神障碍患者服务监管措施';

-- Add/modify columns 
alter table SERVICESUPERVISIONMEASURES modify uptime not null;
alter table SERVICESUPERVISIONMEASURES modify supervisepersonnel not null;
alter table SERVICESUPERVISIONMEASURES modify superviseinfo not null;
-- Add comments to the columns 
comment on column SERVICESUPERVISIONMEASURES.mentalpatientid
  is '严重精神障碍患者ID';
comment on column SERVICESUPERVISIONMEASURES.uptime
  is '时间';
comment on column SERVICESUPERVISIONMEASURES.supervisepersonnel
  is '监管人员';
comment on column SERVICESUPERVISIONMEASURES.superviseinfo
  is '监管措施';
