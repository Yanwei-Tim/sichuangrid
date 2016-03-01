insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextval, '落实情况', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where DOMAINNAME='落实情况'), 
       1, 1, '已落实', 'yls', 'yiluoshi', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where DOMAINNAME='落实情况'),
       0, 2, '未落实', 'wls', 'weiluoshi', 'admin', '', sysdate, null);
       
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextval, '走访情况', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where DOMAINNAME='走访情况'), 
       1, 1, '已走访', 'yzf', 'yizoufang', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains where DOMAINNAME='走访情况'),
       0, 2, '未走访', 'wzf', 'weizoufang', 'admin', '', sysdate, null);
 commit;