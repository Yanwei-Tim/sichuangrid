--单位场所配套设施
insert into moduletable (ID, ENAME, ACTIVE, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE, BEANNAME)
values (s_moduletable.nextval, 'scenicEquipmentManagement', 1, 'SCENICEQUIPMENT', 'ORGID', 'ORGINTERNALCODE', 0, 0, 1, 1, '', '', '', '', 'admin', sysdate, '',sysdate, '');
--景区交通
insert into moduletable (ID, ENAME, ACTIVE, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE, BEANNAME)
values (s_moduletable.nextval, 'scenicTrafficManagement', 1, 'SCENICTRAFFIC', 'ORGID', 'ORGINTERNALCODE', 0, 0, 1, 1, '', '', '', '', 'admin', sysdate, '', sysdate, '');
--旅游景点
insert into moduletable (ID, ENAME, ACTIVE, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE, BEANNAME)
values (s_moduletable.nextval, 'scenicSpotManagement', 1, 'SCENICSPOTS', 'ORGID', 'ORGINTERNALCODE', 1, 0, 1, 1, '', '', '', '', 'admin',sysdate, '', sysdate, 'scenicspotsHandler');
--单位场所
insert into moduletable (ID, ENAME, ACTIVE, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE, BEANNAME)
values (s_moduletable.nextval, 'newCompanyPlaceManagement', 1, 'COMPANYPLACEBASE', 'ORG', 'ORGINTERNALCODE', 1, 0, 1, 1, 'SELECT COUNT(*) FROM #TABLENAME# WHERE #ORGIDCOLUMN# = #OLDORGID#', 'select ID AS RESULT from (select cp.id,cpb.name,cpb.org,cpb.orginternalcode,cp.classification from companyplacebase cpb,companyplace cp where  cpb.id = cp.baseid) SourceTable
where exists(
         select ''X'' from (select cp.id,cpb.name,cpb.org,cpb.orginternalcode,cp.classification from companyplacebase cpb,companyplace cp where  cpb.id = cp.baseid) TargetTable
         where SourceTable.classification = TargetTable.classification and SourceTable.Name = TargetTable.name and TargetTable.org = #NEWORGID#
     )
and SourceTable.org = #OLDORGID#
and  #OLDORGID# <>  #NEWORGID#', '', '', 'admin', sysdate, 'admin', sysdate, 'companyPlaceHander');

commit;
