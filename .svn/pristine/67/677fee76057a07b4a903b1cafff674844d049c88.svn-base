----文化程度的字典项增加一个“学龄前”
insert into propertydicts(id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate) 
values(s_propertyDicts.Nextval,(select id from propertydomains where domainname='文化程度'),0,9,'学龄前','xlq','xuelingqian','admin',sysdate);

commit;

update issuetypedomains set domainname = '矛盾劝解' where domainname ='矛盾化解';
update issuetypedomains set domainname = '参与治安防控' where domainname ='治安防控';
update issuetypedomains set domainname = '参与特殊人群服务管理' where domainname ='特殊人群服务管理报告';
commit;
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'合并', 'combineHouse', 0, '实有房屋', 1, ' ', (select id from permissions where ename='actualHouseManagement'));
commit;

