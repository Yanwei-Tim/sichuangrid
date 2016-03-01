insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '群团组织类型', 1, '[{"displayName":"工会","identify":0},{"displayName":"团委","identify":1}
                                                      ,{"displayName":"妇联","identify":2}]');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='群团组织类型'), 0, 1, '工会', 'gh', 'gonghui', 
       'admin', '', sysdate, null);
       
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='群团组织类型'), 0, 1, '团委', 'tw', 'tuanwei', 
       'admin', '', sysdate, null);
       
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='群团组织类型'), 0, 1, '妇联', 'fl', 'fulian', 
       'admin', '', sysdate, null);