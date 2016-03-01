----表结构修改
alter table mentalPatients add diseaseTime DATE;
alter table mentalPatients add treatmentState NUMBER(10);
alter table mentalPatients add recoveryTime DATE;
 
comment on column mentalPatients.treatmentState
is '治疗状态';
comment on column mentalPatients.diseaseTime
is '发病时间';
comment on column mentalPatients.recoveryTime
is '康复时间';
   ----字典项
insert into propertydomains(id,domainname,systemsensitive,systemrestrict)
values(s_propertyDomains.NEXTVAL,'治疗状态',0,'');
 
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='治疗状态'),0,1,'已康复','ykf','yikangfu','admin',sysdate);
 
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
values(s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='治疗状态'),1,2,'治疗中','zlz','zhiliaozhong','admin',sysdate);
commit;


---------青少年增量脚本
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '青少年', 'youthsManagement', 1, ' ', 1, 
       (select id from permissions where ename = 'peopleManageSystem'), ' ','', '', 1);
       
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'青少年','youngstersManagement',1,'青少年',
    (select id from permissions where ename='youthsManagement'),1,
    '/hotModuel/baseinfo/youths/youthsList.jsp?keyType=youngsters',0);
    
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchYoungsters', 0, '青少年', 1, ' ',
     (select id from permissions where ename='youngstersManagement'),1);
     
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'导出', 'downYoungsters', 0, '青少年', 1, ' ',
     (select id from permissions where ename='youngstersManagement'),2);
     
     
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'少先队员','youngpioneerManagement',1,'青少年',
    (select id from permissions where ename='youthsManagement'),1,
    '/hotModuel/baseinfo/youths/youthsList.jsp?keyType=youngpioneer',1);
     
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchYoungpioneer', 0, '少先队员', 1, ' ',
     (select id from permissions where ename='youngpioneerManagement'),1);
     
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'导出', 'downYoungpioneer', 0, '少先队员', 1, ' ',
     (select id from permissions where ename='youngpioneerManagement'),2);
     
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'共青团员','leaguememberManagement',1,'青少年',
    (select id from permissions where ename='youthsManagement'),1,
    '/hotModuel/baseinfo/youths/youthsList.jsp?keyType=leaguemember',2);
    
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchLeaguemember', 0, '共青团员', 1, ' ',
     (select id from permissions where ename='leaguememberManagement'),1);
     
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'导出', 'downLeaguemembe', 0, '共青团员', 1, ' ',
     (select id from permissions where ename='leaguememberManagement'),2);
commit;

--新建 研判分析系统 青少年 权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '青少年', 'youthStatistic', 1, '研判分析', 1, 
       (select id from permissions where ename='statAnalyseManage'), '', 
       '/hotModuel/statAnalyse/baseInfoStat/youth/index.ftl', '', 
       (select max(indexid) from permissions where parentid = 
       (select id from permissions where ename='statAnalyseManage'))+1);

insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'青少年年龄区间');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='青少年年龄区间'), 0, 1, 
       '0~6岁', '06s', '06sui', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='青少年年龄区间'), 0, 2, 
       '6~14岁', '618s', '618sui', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='青少年年龄区间'), 0, 3, 
       '14~18岁', '1418s', '1418sui', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='青少年年龄区间'), 0, 4, 
       '18~28岁', '1828s', '1828sui', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='青少年年龄区间'), 0, 5, 
       '28~35岁', '2835s', '2835sui', 'admin', '', sysdate, null);

commit;