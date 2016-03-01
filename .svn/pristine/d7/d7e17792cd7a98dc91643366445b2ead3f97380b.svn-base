--keyplace 表中的负责人长度与出租房中保存的字段长度一致修改(房东姓名)
alter table keyplaces modify CHARGEPERSON VARCHAR2(150);

--与户主关系新增 兄弟姐妹 选项
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,
   SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname = '来源方式'),
   11,12,'同步','tb','tongbu','admin','', sysdate,null);
commit;  
---删除之前的用户覆盖率的统计的表（表结构有改变）发布时要看下线上有多少张这样的表，都删除掉
drop table useractivatereports_2015_1;