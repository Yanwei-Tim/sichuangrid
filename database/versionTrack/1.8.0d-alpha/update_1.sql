--终端ip--
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'移动终端ip地址');
 commit;
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='移动终端ip地址'), 0, 1, 'http://10.0.188.188', 'hl', 'localhost', 'admin', '', sysdate, null);
commit;