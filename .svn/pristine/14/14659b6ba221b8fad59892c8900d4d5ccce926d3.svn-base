--实有房屋中楼宇管理：是否有物管字段 
alter  table builddatas add isPropertyManagement  number(1); 

update permissions set cname='组织机构' where ename='gridManageSystem';
update permissions set cname='网格地图' where ename='gisManagement';
commit;

--网格管理系统中组织机构专项工作领导小组的组织类别
delete from propertydicts where propertydomainid=(select id from propertydomains where domainname = '专项工作小组类别');
commit;
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,1,'预防青少年犯罪','yfqsnfz','yufangqingshaonianfa','admin',sysdate);
  insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,2,'特殊人群','tsrq','teshurenqun','admin',sysdate);
  insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,3,'实有人口','syrk','shiyourenkou','admin',sysdate);
  insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,4,'校园及周边治理','xyjzbaq','xiaoyuanjizhoubianan','admin',sysdate);
  insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,5,'铁路护路','tlhlhx','tieluhuluhuxian','admin',sysdate);
    insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,6,'两新组织','lxzz','liangxinzuzhi','admin',sysdate);
    insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,7,'三电专项组','sdzxz','sandianzhuanxiangzu','admin',sysdate);
    insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,8,'社会治安综合治理','shzazhzl','shehuizhianzonghezhi','admin',sysdate);
      insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='专项工作小组类别'),0,9,'军地共建平安','jdgjpa','jundigongjianpingan','admin',sysdate);
commit;

--组织场所中两新组织社会组织的组织类别
delete from propertydicts where propertydomainid=(select id from propertydomains where domainname = '社会组织类型');
commit;
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),0,1,'慈善类','csl','cishanlei','admin',sysdate);
   insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),1,2,'科技类','kjl','kejilei','admin',sysdate);
   insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),2,3,'社区公益类','sqgyl','shequgongyilei','admin',sysdate);
   insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),3,4,'文体类','wtl','wentilei','admin',sysdate);
   insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),4,5,'法律服务','flfw','falvfuwu','admin',sysdate);
   insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
  values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='社会组织类型'),5,6,'民生服务类','msfwl','minshengfuwulei','admin',sysdate);
commit;

alter table PrimaryOrgMembers add idcardNo varchar2(18);
comment on column PrimaryOrgMembers.Idcardno is '身份证号码';

