--实有模块配置信息
insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE,SPLIT)
values (s_MODULETABLE.NEXTVAL, 'householdStaffManagement', 1, 'actualPopulationHandler', 'HOUSEHOLDSTAFFS', 'ORGID', 'ORGINTERNALCODE', 1, 0, 2, 1, '', '', '', '', 'admin', SYSDATE, '', SYSDATE, 1);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'unsettledPopulation', 1, 'actualPopulationHandler', 'UNSETTLEDPOPULATIONS', 'ORGID', 'ORGINTERNALCODE', 1, 0, 2, 1, '', '', '', '', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'overseaPersonManagement', 1, 'actualPopulationHandler', 'OVERSEAPERSONNEL', 'ORGID', 'ORGINTERNALCODE', 1, 0, 2, 1, '', '', '', '', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'positiveInfoManagement', 1, '', 'POSITIVEINFOS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'floatingPopulationManagement', 1, 'actualPopulationHandler', 'FLOATINGPOPULATIONS', 'ORGID', 'ORGINTERNALCODE', 1, 0, 2, 1, '', '', '', '', 'admin',SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'groupFamilyInfo', 1, 'actualPopulationHandler', 'GROUPFAMILY', '', 'ORGINTERNALCODE', 1, 0, 2, 1, 'SELECT COUNT(*) FROM #TABLENAME# WHERE #ORGCODECOLUMN#=#OLDORGCODE#', '', 'UPDATE #TABLENAME# SET #ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGCODECOLUMN#=#OLDORGCODE#', '', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'houseFamilyInfo', 1, 'actualPopulationHandler', 'CENSUSREGISTERFAMILYS', 'ORGID', 'ORGINTERNALCODE', 1, 0, 1, 1, '', '', '', '', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'druggyManagement', 1, '', 'DRUGGYS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin',SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'otherAttentionPersonnelManagement', 1, '', 'OTHERATTENTIONPERSONNELS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'fPersonnelManagement', 1, '', 'FPERSONNELS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'qPersonnelManagement', 1, '', 'QPERSONNELS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'mPersonnelManagement', 1, '', 'MPERSONNELS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'idleYouthManagement', 1, '', 'IDLEYOUTHS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'mentalPatientManagement', 1, '', 'MENTALPATIENTS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'dangerousGoodsPractitionerManagement', 1, '', 'DANGEROUSGOODSPRACTITIONERS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'aidNeedPopulationManagement', 1, '', 'AIDNEEDPOPULATION', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'rectificativePersonManagement', 1, '', 'RECTIFICATIVEPERSONS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'aidspopulationsManagement', 1, '', 'AIDSPOPULATIONS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'superiorVisitManagement', 1, '', 'SUPERIORVISITS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE,SPLIT)
values (s_MODULETABLE.NEXTVAL, 'elderlyPeopleManagement', 1, '', 'ELDERLYPEOPLE', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE,1);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'handicappedManagement', 1, '', 'HANDICAPPEDS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'optimalObjectManagement', 1, '', 'OPTIMALOBJECTS', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE)
values (s_MODULETABLE.NEXTVAL, 'unemployedPeopleManagement', 1, '', 'UNEMPLOYEDPEOPLE', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin',SYSDATE, '', SYSDATE);

insert into moduletable (ID, ENAME, ACTIVE, BEANNAME, TABLENAME, ORGIDCOLUMN, ORGCODECOLUMN, EXECUTETYPE, ISMAINTABLE, EXECUTELEVEL, OPERATEMODE, COUNTSCRIPT, SELECTSCRIPT, UPDATESCRIPT, DELETESCRIPT, CREATEUSER, CREATEDATE, UPDATEUSER, UPDATEDATE,SPLIT)
values (s_MODULETABLE.NEXTVAL, 'nurturesWomen', 1, '', 'NURTURESWOMEN', 'ORGID', 'ORGINTERNALCODE', 0, 0, 3, 1, '', '', '', 'delete #TABLENAME# t1 where  t1.orgId = #OLDORGID# and exists (select 1 from #TABLENAME# t2 where t2.BASEINFOID=t1.BASEINFOID and t2.orgId = #NEWORGID#) and #OLDORGID#<>#NEWORGID#', 'admin', SYSDATE, '', SYSDATE,1);

commit;
