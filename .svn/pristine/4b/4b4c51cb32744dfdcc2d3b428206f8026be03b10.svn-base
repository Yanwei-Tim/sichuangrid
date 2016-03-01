--事件录入类型PC录入增加
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='来源方式'), 10, 11, 'PC录入', 'PClr', 'PCluru', 'admin',sysdate);
commit;
--事件录入类型手机录入增加
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='来源方式'), 10, 11, '手机录入', 'sjlr', 'shoujiluru', 'admin',sysdate);
commit;


--新增字典项脚本
insert into propertydicts
  (ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values
  (s_propertydicts.nextval,(select id from propertydomains p where domainname = '组织大类'),12,13,'社会组织','shdzz',
   'shehuidangzuzhi','admin','',sysdate,null);
   
   insert into propertydicts
  (ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values
  (s_propertydicts.nextval,(select id from propertydomains p where domainname = '组织大类'),13,14,'非公有制经济组织','fgyzjjzz',
   'feigongyouzhijingjizuzhi','admin','',sysdate,null);

--研判分析新增组织机构总况权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,normalUrl,INDEXID)
values (s_permissions.NEXTVAL,'组织机构总况', 'organizationStat', 1, '研判分析', 1, ' ', (select id from permissions where ename='statAnalyseManage'),'/hotModuel/statAnalyse/baseInfoStat/organizationStat/organizationStat.jsp',2);

commit;
--创建组织机构历史数据表序列
create sequence S_PRIMARYORGANIZATIONSTYPE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

alter table issuesteps add emergencylevel number(10);
comment on column issuesteps.emergencylevel is '重大紧急等级';

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='参与治安防控'), 6, 0, 1, 6, '其他', '其他', 'qt', 'qita', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='民生服务'), 13, 0, 1, 13, '其他', '其他', 'qt', 'qita', null, 'admin', null, sysdate, sysdate);
commit;






---单位场所研判分析历史记录表
create sequence s_statisticCompanyPlaceHistory
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
commit;

---三本台账民生诉求台账索引
create index idx_peopleaspirations_orgid on peopleaspirations(orgid);
---三本台账困难群众台账索引         
create index idx_poorpeoples_orgid on poorpeoples(orgid);
---三本台账稳定工作台账索引        
create index idx_steadyworks_orgid on steadyworks(orgid);
---三本台账步骤表索引                  
create index idx_accountsteps_xc_accountid on accountsteps_xichang(accountid);
---三本台账步骤表索引          
create index idx_accountsteps_xc_targetid on accountsteps_xichang(targetorgid);


---青少年统计序列
create sequence s_youthStatType
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
---青少年领导视图
update permissions set leaderurl='/hotModuel/baseinfo/leaderView/populationStatistics/youthsStatistics.jsp?keyType=youngsters' where ename = 'youngstersManagement';
update permissions set leaderurl='/hotModuel/baseinfo/leaderView/populationStatistics/youthsStatistics.jsp?keyType=youngpioneer' where ename = 'youngpioneerManagement';
update permissions set leaderurl='/hotModuel/baseinfo/leaderView/populationStatistics/youthsStatistics.jsp?keyType=leaguemember' where ename = 'leaguememberManagement';
commit;