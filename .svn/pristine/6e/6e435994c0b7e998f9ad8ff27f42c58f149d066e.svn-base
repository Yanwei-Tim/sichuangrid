--------------------------------------------
-- Table: TABLENAME						ROW
--------------------------------------------
-- Table: BASEINFO						28		*
-- Table: HOUSEHOLDSTAFFS				184		*
-- Table: FLOATINGPOPULATIONS			345		*
-- Table: DRUGGYS						506		*
-- Table: AIDNEEDPOPULATION				599		*
-- Table: AIDSPOPULATIONS				688		*
-- Table: DANGEROUSGOODSPRACTITIONERS	781		*
-- Table: ELDERLYPEOPLE					875		*
-- Table: HANDICAPPEDS					983		*
-- Table: IDLEYOUTHS					1087	*
-- Table: MENTALPATIENTS				1165	*
-- Table: UNEMPLOYEDPEOPLE				1282	*
-- Table: SUPERIORVISITS				1419	*
-- Table: RECTIFICATIVEPERSONS			1492	*
-- Table: POSITIVEINFOS							*
-- Table: OTHERATTENTIONPERSONNELS				*
-- Table: OPTIMALOBJECTS						*
-- Table: NURTURESWOMEN	
--DRUGGYS
MERGE INTO druggys a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.druggys c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--AIDNEEDPOPULATION
MERGE INTO aidneedpopulation a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.aidneedpopulation c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--AIDSPOPULATIONS
MERGE INTO aidspopulations a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.aidspopulations c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--DANGEROUSGOODSPRACTITIONERS
MERGE INTO dangerousgoodspractitioners a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.dangerousgoodspractitioners c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--ELDERLYPEOPLE
MERGE INTO elderlypeople a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.elderlypeople c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--HANDICAPPEDS
MERGE INTO handicappeds a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.handicappeds c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--IDLEYOUTHS	
MERGE INTO idleyouths a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.idleyouths c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--MENTALPATIENTS	
MERGE INTO mentalpatients a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.mentalpatients c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--UNEMPLOYEDPEOPLE
MERGE INTO unemployedpeople a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.unemployedpeople c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--SUPERIORVISITS
MERGE INTO superiorvisits a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.superiorvisits c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--RECTIFICATIVEPERSONS
MERGE INTO rectificativepersons a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.rectificativepersons c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--POSITIVEINFOS		
MERGE INTO positiveinfos a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.positiveinfos c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);	
--OTHERATTENTIONPERSONNELS	
MERGE INTO otherattentionpersonnels a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.otherattentionpersonnels c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--OPTIMALOBJECTS	
MERGE INTO optimalobjects a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.optimalobjects c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
--NURTURESWOMEN	
MERGE INTO nurtureswomen a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.nurtureswomen c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id   WHERE a.id=c.cid 
when not matched then  insert (baseinfoid) values(c.id);
---address



