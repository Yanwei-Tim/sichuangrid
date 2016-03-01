--特殊人群重点青少年：帮扶情况字典项
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertyDomains.NEXTVAL, '帮扶情况', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='帮扶情况'), 0, 1, '关爱救助', 'gajz', 'guanaijiuzhu', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='帮扶情况'), 0, 2, '心理疏导', 'xlsd', 'xinlishudao', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='帮扶情况'), 0, 3, '就业指导', 'jyzd', 'jiuyezhidao', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='帮扶情况'), 0, 4, '个案访谈', 'gaft', 'geanfangtan', 'admin', sysdate);
commit;

-- 人口信息：特殊人群重点青少年改造脚本， 增加帮扶情况字段
ALTER TABLE IDLEYOUTHS add HELPINGSITUATION	NUMBER(10);
-- 数据管理：特殊人群重点青少年改造脚本， 增加帮扶情况字段
ALTER TABLE DM_idleYouths_temp add HELPINGSITUATION		NUMBER(10);
-- 数据管理：特殊人群重点青少年改造脚本， 增加监护人字段
ALTER TABLE DM_idleYouths_temp add GUARDIANNAME         VARCHAR2(60);
-- 数据管理：特殊人群重点青少年改造脚本， 增加联系方式字段
ALTER TABLE DM_idleYouths_temp add GUARDIANTELEPHONE    VARCHAR2(15);

--互动交流改造脚本，增加字段 文件信息ID
alter table inboxplatformmessages add(FILEINFOIDS CLOB);
alter table outboxplatformmessages add(FILEINFOIDS CLOB);

