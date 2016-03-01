-- alter table
alter table addressinfo add tempid number(10);
alter table addressinfo add temporgid number(10);
alter table addressinfo add tempcardno varchar2(60);
-- householdstaffs
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid,temporgid,tempcardno) 
(select s_addressInfo.Nextval,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate,id,orgid,idcardno from sichuan_temp.householdstaffs);
--update householdstaffs
MERGE INTO householdstaffs p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid;
-- datas
--DRUGGYS
MERGE INTO druggys p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.druggys idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--AIDNEEDPOPULATION
MERGE INTO aidneedpopulation p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.aidneedpopulation idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--AIDSPOPULATIONS
MERGE INTO aidspopulations p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.aidspopulations idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--DANGEROUSGOODSPRACTITIONERS
MERGE INTO dangerousgoodspractitioners p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.dangerousgoodspractitioners idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--ELDERLYPEOPLE
MERGE INTO elderlypeople p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.elderlypeople idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--HANDICAPPEDS
MERGE INTO handicappeds p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.handicappeds idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--IDLEYOUTHS
MERGE INTO idleyouths p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.idleyouths idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--MENTALPATIENTS
MERGE INTO mentalpatients p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.mentalpatients idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--UNEMPLOYEDPEOPLE
MERGE INTO unemployedpeople p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.unemployedpeople idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--SUPERIORVISITS
MERGE INTO superiorvisits p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.superiorvisits idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--RECTIFICATIVEPERSONS
MERGE INTO rectificativepersons p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.rectificativepersons idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--POSITIVEINFOS
MERGE INTO positiveinfos p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.positiveinfos idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);			
--OTHERATTENTIONPERSONNELS
MERGE INTO otherattentionpersonnels p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.otherattentionpersonnels idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--OPTIMALOBJECTS	
MERGE INTO optimalobjects p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.optimalobjects idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--NURTURESWOMEN	
MERGE INTO nurtureswomen p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.nurtureswomen idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--
alter table addressinfo drop column tempid;
alter table addressinfo drop column temporgid;
alter table addressinfo drop column tempcardno;
--
alter table addressinfo add tempid number(10);
alter table addressinfo add temporgid number(10);
alter table addressinfo add tempcardno varchar2(60);
-- floatingpopulations
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid,temporgid,tempcardno) 
(select s_addressInfo.Nextval,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate,id,orgid,idcardno from sichuan_temp.floatingpopulations);
-- update floatingpopulations
MERGE INTO floatingpopulations p USING addressinfo np ON (p.id = np.tempid) WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid;
-- datas
--DRUGGYS
MERGE INTO druggys p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.druggys idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--AIDNEEDPOPULATION
MERGE INTO aidneedpopulation p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.aidneedpopulation idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--AIDSPOPULATIONS
MERGE INTO aidspopulations p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.aidspopulations idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--DANGEROUSGOODSPRACTITIONERS
MERGE INTO dangerousgoodspractitioners p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.dangerousgoodspractitioners idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--ELDERLYPEOPLE
MERGE INTO elderlypeople p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.elderlypeople idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--HANDICAPPEDS
MERGE INTO handicappeds p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.handicappeds idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--IDLEYOUTHS
MERGE INTO idleyouths p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.idleyouths idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--MENTALPATIENTS
MERGE INTO mentalpatients p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.mentalpatients idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--UNEMPLOYEDPEOPLE
MERGE INTO unemployedpeople p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.unemployedpeople idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--SUPERIORVISITS
MERGE INTO superiorvisits p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.superiorvisits idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--RECTIFICATIVEPERSONS
MERGE INTO rectificativepersons p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.rectificativepersons idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--POSITIVEINFOS
MERGE INTO positiveinfos p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.positiveinfos idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);			
--OTHERATTENTIONPERSONNELS
MERGE INTO otherattentionpersonnels p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.otherattentionpersonnels idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);	
--OPTIMALOBJECTS	
MERGE INTO optimalobjects p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.optimalobjects idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--NURTURESWOMEN	
MERGE INTO nurtureswomen p 
USING (select ad.id as id,idl.id as did from addressinfo ad,sichuan_temp.nurtureswomen idl 
where ad.temporgid = idl.orgid and ad.tempcardno = idl.idcardno ) np 
ON (p.id = np.did) WHEN MATCHED THEN UPDATE  SET p.addressinfoid = np.id  
WHERE p.id = np.did WHEN NOT MATCHED THEN  insert  (addressinfoid) values(np.id);
--drop
alter table addressinfo drop column tempid;
alter table addressinfo drop column temporgid;
alter table addressinfo drop column tempcardno;




