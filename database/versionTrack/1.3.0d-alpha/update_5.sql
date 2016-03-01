--组织机构新增模块基层党委，部门党委，政府部门，群团组织类别与职务字典项
insert into propertydomains values(s_propertyDomains.NEXTVAL ,'基层党委组织类别',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织类别'),
0,1,'区党委','qdw','qudangwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织类别'),
0,2,'镇党委','zdw','zhendangwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织类别'),
0,3,'党支部','dzb','dangzhibu','admin',sysdate);

insert into propertydomains values(s_propertyDomains.NEXTVAL ,'基层党委组织职务',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织职务'),
0,1,'书记','sj','shuji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织职务'),
0,2,'副书记','fsj','fushuji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织职务'),
0,3,'常委','cw','changwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='基层党委组织职务'),
0,4,'委员','wy','weiyuan','admin',sysdate);



insert into propertydomains values(s_propertyDomains.NEXTVAL ,'部门党委组织类别',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,1,'组织部','zzb','zuzhibu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,2,'宣传部','xcb','xuanchuanbu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,3,'统战部','tzb','tongzhanbu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,4,'政法委','zfw','zhengfawei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,5,'防邪办','fxb','fangxieban','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织类别'),
0,6,'信访办','xfb','xinfangban','admin',sysdate);

insert into propertydomains values(s_propertyDomains.NEXTVAL ,'部门党委组织职务',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织职务'),
0,1,'主任','zr','zhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织职务'),
0,2,'副主任','fzr','fuzhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织职务'),
0,3,'委员','wy','weiyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织职务'),
0,4,'处长','cz','chuzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='部门党委组织职务'),
0,5,'科长','kz','kezhang','admin',sysdate);


insert into propertydomains values(s_propertyDomains.NEXTVAL ,'政府部门组织类别',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,1,'行政部门','xzbm','xingzhengbumen','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,2,'发改局','fgj','fagaiju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,3,'经信局','jxj','jingxinju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,4,'教育局','jyj','jiaoyuju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,5,'公安局','gaj','gonganju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,6,'民政局','mzj','minzhengju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,7,'司法局','sfj','sifaju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,8,'人社局','rsj','rensheju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,9,'国土局','gtj','guotuju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,10,'环保局','hbj','huanbaoju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,11,'住建局','zjj','zhujianju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,12,'交通局','jtj','jiaotongju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,13,'水务局','swj','shuiwuju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,14,'农业局','nyj','nongyeju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,15,'林业局','lyj','linyeju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,16,'商务局','swj','shangwuju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,17,'文广新局','wgxj','wenguangxinju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,18,'卫生局','wsj','weishengju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,19,'计生局','jsj','jishengju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,20,'工商局','gsj','gongshangju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,21,'质监局','zjj','zhijianju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,22,'安监局','ajj','anjianju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,23,'旅游局','lyj','lvyouju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,24,'宗教局','zjj','zongjiaoju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,25,'城管局','cgj','chengguanju','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织类别'),
0,26,'食品药品监督管理局','spypjdglj','shipinyaopinjianduguanliju','admin',sysdate);


insert into propertydomains values(s_propertyDomains.NEXTVAL ,'政府部门组织职务',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,1,'县长','xz','xianzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,2,'副县长','fxz','fuxianzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,3,'镇长','zz','zhenzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,4,'副镇长','fzz','fuzhenzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,5,'局长','jz','juzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,6,'副局长','fjz','fujuzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,7,'主任','zr','zhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,8,'副主任','fzr','fuzhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,9,'处长','cz','chuzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,10,'科长','kz','kezhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='政府部门组织职务'),
0,11,'科员','ky','keyuan','admin',sysdate);


insert into propertydomains values(s_propertyDomains.NEXTVAL ,'群团组织职务',0,'');
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,1,'局长','jz','juzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,2,'副局长','fjz','fujuzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,3,'主任','zr','zhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,4,'副主任','fzr','fuzhuren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,5,'处长','cz','chuzhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,6,'科长','kz','kezhang','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='群团组织职务'),
0,7,'科员','ky','keyuan','admin',sysdate);


insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='组织大类'),
8,9,'基层党委','jcdw','jicengdangwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='组织大类'),
9,10,'部门党委','bmdw','bumendangwei','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='组织大类'),
10,11,'政府部门','zfbm','zhengfubumen','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='组织大类'),
11,12,'群团组织','qtzz','quntuanzuzhi','admin',sysdate);





--组织结构模块的层级改变
update permissions p set p.parentid = (select t.id from permissions t where ename = 'gridManageSystem')
       where p.parentid = (select t.id from permissions t where ename = 'organizationalStructure');

update permissions p set p.indexid = 0 
       where p.parentid = (select t.id from permissions t where ename = 'gridManageSystem');

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
       VALUES(s_permissions.NEXTVAL,'党委组织','partyOrgManagement',1,'组织机构',
            (select id from permissions where ename='gridManageSystem'),1,1);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId,normalurl,leaderurl)
       VALUES(s_permissions.NEXTVAL,'基层党委','basicLevelPartyManagement',1,'党委组织',
            (select id from permissions where ename='partyOrgManagement'),1,0,
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=BasicLevelParty',
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=BasicLevelParty');

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId,normalurl,leaderurl)
       VALUES(s_permissions.NEXTVAL,'部门党委','departmentPartyManagement',1,'党委组织',
            (select id from permissions where ename='partyOrgManagement'),1,1,
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=DepartmentParty',
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=DepartmentParty');

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId,normalurl,leaderurl)
       VALUES(s_permissions.NEXTVAL,'政府部门','governmentDepartment',1,'组织机构',
            (select id from permissions where ename='gridManageSystem'),1,2,
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=GovernmentDepartment',
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=GovernmentDepartment');

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId,normalurl,leaderurl)
       VALUES(s_permissions.NEXTVAL,'群团组织','massOrgManagement',1,'组织机构',
            (select id from permissions where ename='gridManageSystem'),1,3,
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=MassOrganization',
            '/baseinfo/primaryOrganization/primaryOrgList.jsp?name=MassOrganization');

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'newSocietyOrganizationsManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'newEconomicOrganizationsManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'baseAutonomyOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem')),
       cname = '群防群治组织'
       where ename = 'massedutyOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem')),
       cname = '社会志愿者组织'
       where ename = 'PostulantdutyOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'basePartyOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'primaryOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem'))
       where ename = 'LeadergroupOrgManagement';

update permissions p set p.indexid = (
       select max(indexid)+1 from permissions where parentid = 
              (select id from permissions where ename = 'gridManageSystem')) 
       where ename = 'serviceTeamManage';

-- 组织机构 下的 组织机构 列表中，所有子项都已经移至顶层，有权限的人点击会报错，故删除之。
delete from roleHasPermissions r where r.permissionid = 
	(select p.id from permissions p where ename = 'organizationalStructure');
delete from permissions where ename = 'organizationalStructure';


--组织机构新增模块基层党委，部门党委，政府部门，群团组织权限   
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增组织', 'addBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'维护成员', 'maintainPrimaryOrgMembersBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'editBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'exportBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增成员', 'addPromaryOrgMemberBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改成员', 'editPrimaryOrgMemberBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'任职成员', 'holdPrimaryOrgMemberBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除成员', 'deletePrimaryOrgMemberBasicLevelParty', 0, '基层党委', 1, ' ', (select id from permissions where ename='basicLevelPartyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增组织', 'addDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'维护成员', 'maintainPrimaryOrgMembersDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'editDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'exportDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增成员', 'addPromaryOrgMemberDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改成员', 'editPrimaryOrgMemberDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'任职成员', 'holdPrimaryOrgMemberDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除成员', 'deletePrimaryOrgMemberDepartmentParty', 0, '部门党委', 1, ' ', (select id from permissions where ename='departmentPartyManagement'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增组织', 'addGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'维护成员', 'maintainPrimaryOrgMembersGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'editGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'exportGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增成员', 'addPromaryOrgMemberGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改成员', 'editPrimaryOrgMemberGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'任职成员', 'holdPrimaryOrgMemberGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除成员', 'deletePrimaryOrgMemberGovernmentDepartment', 0, '政府部门', 1, ' ', (select id from permissions where ename='governmentDepartment'));

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查询', 'searchMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增组织', 'addMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'维护成员', 'maintainPrimaryOrgMembersMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改', 'editMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除', 'deleteMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'查看', 'viewMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'导出', 'exportMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'新增成员', 'addPromaryOrgMemberMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'修改成员', 'editPrimaryOrgMemberMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'任职成员', 'holdPrimaryOrgMemberMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
    values (s_permissions.NEXTVAL,'删除成员', 'deletePrimaryOrgMemberMassOrganization', 0, '群团组织', 1, ' ', (select id from permissions where ename='massOrgManagement'));

commit;