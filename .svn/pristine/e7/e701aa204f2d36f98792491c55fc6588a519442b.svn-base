delete from propertydicts  where propertydomainid = (select id from propertydomains  where domainname = '重点青少年年龄区间');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='重点青少年年龄区间'),
0,1,'0~6岁','06s','06sui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='重点青少年年龄区间'),
0,2,'6~18岁','618s','618sui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='重点青少年年龄区间'),
0,3,'18~25岁','1825s','1825sui','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='重点青少年年龄区间'),
0,4,'25~35岁','2535s','2535sui','admin',sysdate);

commit;