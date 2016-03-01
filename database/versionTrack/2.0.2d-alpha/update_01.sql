--事件类型，“参与治安防控”大类下面增加一个子类“防毒控毒”。
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='对接事件类型（子类）'), 2, 55, '防毒控毒', 'fdkd', 'fangdukongdu', 'admin',sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, CREATEDATE)
values (s_issuetypes.NEXTVAL, null, (select id from issuetypedomains where domainname='参与治安防控'), 6, 0, 1, 6, '防毒控毒', '防毒控毒', 'fdkd', 'fangdukongdu', '', 'admin', sysdate);

commit;

--成员库组织类型表 创建索引
create index idx_primembersorgtype_priorgid on primarymembersorgtype (primaryorgid);
--组织机构  部门类型创建索引
create index idx_organizations_orgtype on organizations (orgtype);