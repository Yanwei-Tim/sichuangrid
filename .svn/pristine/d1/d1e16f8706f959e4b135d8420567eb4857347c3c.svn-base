insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'所属接警中心');
  
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='所属接警中心'), 0, 1, '街道公安局', 'jdgaj', 'jiedaogonganju', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='所属接警中心'), 0, 2, '区协警处', 'qxjc', 'quxiejingchu', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='所属接警中心'), 0, 3, '警卫室', 'ljws', 'lujingweishi', 'admin', '', sysdate, null);


insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'警务室');
  
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='警务室'), 0, 1, '小区保安室', 'xqbas', 'xiaoqubaoanshi', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='警务室'), 0, 2, '民卫处', 'xxqxjc', 'minweichu', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='警务室'), 0, 3, '警备室', 'xxljws', 'jingbeishi', 'admin', '', sysdate, null);

insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'消息类型');
  
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='消息类型'), 0, 1, '01-火警', '01-hj', '01-huojing', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='消息类型'), 0, 2, '02-事警', 'tz', 'tongzhi', 'admin', '', sysdate, null);
  insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='消息类型'), 0, 3, '03-通知', 'w', 'wu', 'admin', '', sysdate, null);

commit;