--导入日志
insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, SPLIT, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'excelImportLogManagement', 1, '', 'EXCELIMPORTLOG', 'ORGANIZATIONID', 'ORGINTERNALCODE', null, 0, 0, 1, 1, '', '', '', '', 'admin', sysdate, '', sysdate);

commit;