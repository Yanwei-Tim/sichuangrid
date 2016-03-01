alter table excelimportlog add importModuleName varchar2(32) ;
alter table excelimportlog add organizationId number(10) ;
alter table primarymembersorgtype add seq number(10) ;
commit ;

insert into permissions  (id,cname,ename,permissiontype,modulename,enable,parentid,description,normalurl,leaderurl,indexid,gridurl)
values(s_permissions.nextval,'高级查询','searchExcelImportLog',0,'导入日志',1,(select id from permissions where ename='excelImportLogManagement'),'','','','','');
commit ; 

update permissions set parentid=(select id from permissions where ename='interactionManagement'), modulename='互动交流系统'
where id=(select id from permissions where ename='excelImportLogManagement');  
commit ;
