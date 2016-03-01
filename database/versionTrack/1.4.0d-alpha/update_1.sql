drop sequence S_ACCOUNTSTEPS_XICHANG;

drop table accountSteps_xichang;

create sequence S_ACCOUNTSTEPS_XICHANG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
--  三本台账处理步骤表
create table accountSteps_xichang
(
  id                  NUMBER(10) not null,
  accountid           NUMBER(10) not null,
  accounttype         VARCHAR2(100),
  targetOrgid         NUMBER(10) not null,
  targetOrgcode       VARCHAR2(32),
  isFinish            NUMBER(1) default 0,
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint PKaccountSteps_xichang primary key (ID)
);
-- Add comments to the table 
comment on table accountSteps_xichang
  is '三本台账处理步骤表';
-- Add comments to the columns 
comment on column accountSteps_xichang.id
  is '主键ID';
comment on column accountSteps_xichang.accountid
  is '台账ID';
comment on column accountSteps_xichang.accounttype
  is '台账类型';
comment on column accountSteps_xichang.targetOrgid
  is '目标处理部门';
comment on column accountSteps_xichang.targetOrgcode
  is '目标处理部门内置编码';
comment on column accountSteps_xichang.isFinish
  is '是否已办';
comment on column accountSteps_xichang.createuser
  is '创建用户';
comment on column accountSteps_xichang.updateuser
  is '修改用户';
comment on column accountSteps_xichang.createdate
  is '创建时间';
comment on column accountSteps_xichang.updatedate
  is '修改时间';
  
alter table propertydicts modify SIMPLEPINYIN varchar2(30);
  
insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'诉求类别');
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 1, '党纪政纪', 'djzj', 'dangjizhengji', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 2, '组织人事', 'zzrs', 'zuzhirenshi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 3, '涉法涉诉', 'sfss', 'shefashesu', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 4, '土地、林权', 'tdlq', 'tudilinquan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 5, '征地拆迁', 'zdcq', 'zhengdichaiqian', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 6, '水利电力', 'sldl', 'shuilidianli', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 7, '环保', 'hb', 'huanbao', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 8, '扶贫济困', 'fpjk', 'fupinjikun', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 9, '惠农政策及村(社区)政务、财务', 'hnzcjcsqzwcw', 'huinongzhengcejicunshequzhengwucaiwu', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 10, '人口与医疗卫生', 'rkyylws', 'renkouyuyiliaoweisheng', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 11, '劳动保障', 'ldbz', 'laodongbaozhang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 12, '交通运输', 'jtys', 'jiaotongyunshu', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 13, '城建', 'cj', 'chengjian', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 14, '安全生产', 'aqsc', 'anquanshengchan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 15, '旅游', 'ly', 'lvyou', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 16, '教育', 'jy', 'jiaoyu', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 17, '企业改制', 'qygz', 'qiyegaizhi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 18, '移民', 'ym', 'yimin', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 19, '涉军', 'sj', 'shejun', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='诉求类别'), 0, 20, '其他', 'qt', 'qita', 'admin', '', sysdate, null);

   
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
   VALUES(s_permissions.NEXTVAL,'统计报表','reportForAccountLogs',1,'统计报表',(select id from permissions where ename='account'),1,'',0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
   VALUES(s_permissions.NEXTVAL,'民生诉求台账','reportForPeopleAspiration',1,'统计报表',(select id from permissions where ename='reportForAccountLogs'),1,'/hotModuel/xichang/working/report/index.jsp?accountType=PEOPLEASPIRATION',0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
   VALUES(s_permissions.NEXTVAL,'困难群众台账','reportForPoorPeople',1,'统计报表',(select id from permissions where ename='reportForAccountLogs'),1,'/hotModuel/xichang/working/report/index.jsp?accountType=POORPEOPLE',0);
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
   VALUES(s_permissions.NEXTVAL,'稳定工作台账','reportForSteadyWork',1,'统计报表',(select id from permissions where ename='reportForAccountLogs'),1,'/hotModuel/xichang/working/report/index.jsp?accountType=STEADYWORK',0);
   
commit;                                                                                                                
 