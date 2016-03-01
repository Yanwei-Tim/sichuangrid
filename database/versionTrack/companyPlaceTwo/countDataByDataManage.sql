--DM_ACTUALCOMPANY_Temp
select count(*) from DM_ACTUALCOMPANY_Temp a where a.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_safetyProduct_Temp
select count(*) from DM_safetyProduct_Temp s where s.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_FireSafetyE_Temp
select count(*) from DM_FireSafetyE_Temp f where f.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_SecurityE_Temp
select count(*) from DM_SecurityE_Temp s where s.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_schools_Temp
select count(*) from DM_schools_Temp s where s.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_hospitals_Temp
select count(*) from DM_hospitals_Temp h where h.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_dangeCUnit_Temp
select count(*) from DM_dangeCUnit_Temp d where d.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_internetBar_Temp
select count(*) from DM_internetBar_Temp i where i.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_publicPlace_Temp
select count(*) from DM_publicPlace_Temp p where p.createdate > to_date('2014-7-19','yyyy-mm-dd');
--DM_otherLocales_Temp
select count(*) from DM_otherLocales_Temp o where o.createdate > to_date('2014-7-19','yyyy-mm-dd');
--dm_enterprisekey_temp
select count(*) from dm_enterprisekey_temp;
--dm_enterprisedownkey_temp
select count(*) from dm_enterprisedownkey_temp;
--DM_COMPANYPLACE_TEMP
select count(*) from DM_COMPANYPLACE_TEMP;

