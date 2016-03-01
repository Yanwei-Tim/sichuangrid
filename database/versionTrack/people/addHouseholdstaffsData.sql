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
alter table druggys add tempid number(10);
alter table baseinfo add tempid number(10);
update druggys set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.DRUGGYS a,druggys b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO druggys p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.druggys a,druggys b
where a.id=b.id and b.tempid=0);
MERGE INTO druggys p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from druggys where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'druggy' from householdstaffs h,druggys d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table druggys drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--AIDNEEDPOPULATION
alter table aidneedpopulation add tempid number(10);
alter table baseinfo add tempid number(10);
update aidneedpopulation set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.aidneedpopulation a,aidneedpopulation b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO aidneedpopulation p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.aidneedpopulation a,aidneedpopulation b
where a.id=b.id and b.tempid=0);
MERGE INTO aidneedpopulation p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from aidneedpopulation where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'aidNeedPopulation' from householdstaffs h,aidneedpopulation d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table aidneedpopulation drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--AIDSPOPULATIONS
alter table aidspopulations add tempid number(10);
alter table baseinfo add tempid number(10);
update aidspopulations set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.aidspopulations a,aidspopulations b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO aidspopulations p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.aidspopulations a,aidspopulations b
where a.id=b.id and b.tempid=0);
MERGE INTO aidspopulations p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from aidspopulations where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'aidspopulation' from householdstaffs h,aidspopulations d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table aidspopulations drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--DANGEROUSGOODSPRACTITIONERS
alter table dangerousgoodspractitioners add tempid number(10);
alter table baseinfo add tempid number(10);
update dangerousgoodspractitioners set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.dangerousgoodspractitioners a,dangerousgoodspractitioners b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO dangerousgoodspractitioners p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.dangerousgoodspractitioners a,dangerousgoodspractitioners b
where a.id=b.id and b.tempid=0);
MERGE INTO dangerousgoodspractitioners p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from dangerousgoodspractitioners where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'dangerousGoodsPractitioner' from householdstaffs h,dangerousgoodspractitioners d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table dangerousgoodspractitioners drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--ELDERLYPEOPLE
alter table elderlypeople add tempid number(10);
alter table baseinfo add tempid number(10);
update elderlypeople set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.elderlypeople a,elderlypeople b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO elderlypeople p USING baseinfo b ON (p.id = b.tempid)  WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.elderlypeople a,elderlypeople b
where a.id=b.id and b.tempid=0);
MERGE INTO elderlypeople p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from elderlypeople where tempid=0;
--insert
insert into populationTypes   (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'elderlyPeople' from householdstaffs h,elderlypeople d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table elderlypeople drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--HANDICAPPEDS
alter table handicappeds add tempid number(10);
alter table baseinfo add tempid number(10);
update handicappeds set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.handicappeds a,handicappeds b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO handicappeds p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.handicappeds  a,handicappeds b
where a.id=b.id and b.tempid=0);
MERGE INTO handicappeds p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from handicappeds where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'handicapped' from householdstaffs h,handicappeds d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table handicappeds drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--IDLEYOUTHS	
alter table idleyouths add tempid number(10);
alter table baseinfo add tempid number(10);
update idleyouths set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.idleyouths a,idleyouths b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO idleyouths p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.idleyouths a,idleyouths b
where a.id=b.id and b.tempid=0);
MERGE INTO idleyouths p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from idleyouths where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'idleYouth' from householdstaffs h,idleyouths d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table idleyouths drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--UNEMPLOYEDPEOPLE
alter table unemployedpeople add tempid number(10);
alter table baseinfo add tempid number(10);
update unemployedpeople set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.unemployedpeople a,unemployedpeople b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO unemployedpeople p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.unemployedpeople a,unemployedpeople b
where a.id=b.id and b.tempid=0);
MERGE INTO unemployedpeople p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from unemployedpeople where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'unemployedPeople' from householdstaffs h,unemployedpeople d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table unemployedpeople drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--SUPERIORVISITS
alter table superiorvisits add tempid number(10);
alter table baseinfo add tempid number(10);
update superiorvisits set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.superiorvisits a,superiorvisits b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO superiorvisits p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.superiorvisits a,superiorvisits b
where a.id=b.id and b.tempid=0);
MERGE INTO superiorvisits p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from superiorvisits where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'superiorVisit' from householdstaffs h,superiorvisits d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table superiorvisits drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--RECTIFICATIVEPERSONS
alter table rectificativepersons add tempid number(10);
alter table baseinfo add tempid number(10);
update rectificativepersons set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.rectificativepersons a,rectificativepersons b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO rectificativepersons p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.rectificativepersons a,rectificativepersons b
where a.id=b.id and b.tempid=0);
MERGE INTO rectificativepersons p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from rectificativepersons where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'rectificativePerson' from householdstaffs h,rectificativepersons d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table rectificativepersons drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--POSITIVEINFOS		
alter table positiveinfos add tempid number(10);
alter table baseinfo add tempid number(10);
update positiveinfos set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.positiveinfos a,positiveinfos b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO positiveinfos p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.positiveinfos a,positiveinfos b
where a.id=b.id and b.tempid=0);
MERGE INTO positiveinfos p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from positiveinfos where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'positiveInfo' from householdstaffs h,positiveinfos d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table positiveinfos drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--OTHERATTENTIONPERSONNELS	
alter table otherattentionpersonnels add tempid number(10);
alter table baseinfo add tempid number(10);
update otherattentionpersonnels set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.otherattentionpersonnels a,otherattentionpersonnels b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO otherattentionpersonnels p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.otherattentionpersonnels a,otherattentionpersonnels b
where a.id=b.id and b.tempid=0);
MERGE INTO otherattentionpersonnels p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from otherattentionpersonnels where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'otherAttentionPersonnel' from householdstaffs h,otherattentionpersonnels d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table otherattentionpersonnels drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--OPTIMALOBJECTS	
alter table optimalobjects add tempid number(10);
alter table baseinfo add tempid number(10);
update optimalobjects set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.optimalobjects a,optimalobjects b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO optimalobjects p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.optimalobjects a,optimalobjects b
where a.id=b.id and b.tempid=0);
MERGE INTO optimalobjects p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from optimalobjects where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'optimalObject' from householdstaffs h,optimalobjects d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table optimalobjects drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--NURTURESWOMEN	
alter table nurtureswomen add tempid number(10);
alter table baseinfo add tempid number(10);
update nurtureswomen set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.nurtureswomen a,nurtureswomen b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO nurtureswomen p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.nurtureswomen a,nurtureswomen b
where a.id=b.id and b.tempid=0);
MERGE INTO nurtureswomen p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and p.tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from nurtureswomen where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'nurturesWomen' from householdstaffs h,nurtureswomen d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table nurtureswomen drop column tempid;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
---
--MENTALPATIENTS	
alter table mentalpatients add tempid number(10);
alter table mentalpatients add tempflag number(10);
alter table baseinfo add tempid number(10);
-- 
MERGE INTO mentalpatients a 
USING (select b.id as id,c.id as cid from baseinfo b,sichuan_temp.mentalpatients c where b.idcardno = c.idcardno )c
 ON (a.id=c.cid)     WHEN MATCHED THEN  
UPDATE  SET a.baseinfoid = c.id ,tempflag=1  WHERE a.id=c.cid and  a.baseinfoid is null;
----
MERGE INTO mentalpatients p USING householdstaffs np ON (p.baseinfoid = np.baseinfoid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.addressinfoid   WHERE p.baseinfoid = np.baseinfoid and p.tempflag=1;
--
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'mentalPatient' from householdstaffs h,mentalpatients d 
where h.baseinfoid = d.baseinfoid and d.tempflag=1;
--
update mentalpatients set tempid=0 where baseinfoid is null;
--insert
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,a.name, a.fullpinyin, a.simplepinyin, a.usedname, a.idcardno, a.telephone, a.mobilenumber, a.birthday, a.gender, a.workunit, a.imgurl, a.email, a.isdeath, a.nation, a.politicalbackground, a.schooling, a.career, a.maritalstate, a.bloodtype, a.faith, a.stature, a.province, a.city, a.district, a.nativeplaceaddress, a.nativepolicestation, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.mentalpatients a,mentalpatients b
where a.id=b.id and b.tempid=0;
--update
MERGE INTO mentalpatients p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid and p.tempid=0;
--add 
alter table addressinfo add tempid number(10);
insert into addressinfo (id,ishavehouse, nohousereason, currentaddress, otheraddress, remark, createuser, updateuser, createdate, updatedate, tempid) 
(select s_addressInfo.Nextval,a.ishavehouse, a.nohousereason, a.currentaddress, a.otheraddress, a.remark, a.createuser, a.updateuser, a.createdate, a.updatedate,a.id from sichuan_temp.mentalpatients a,mentalpatients b
where a.id=b.id and b.tempid=0);
MERGE INTO mentalpatients p USING addressinfo np ON (p.id = np.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.addressinfoid = np.id   WHERE p.id = np.tempid and tempid=0;
--insert
insert into householdstaffs(id,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate)
select S_HOUSEHOLDSTAFF.NEXTVAL,baseinfoid,addressinfoid,orgid,orginternalcode,sourcesstate,createuser,updateuser,createdate,updatedate from mentalpatients where tempid=0;
--insert
insert into populationTypes
    (id,actualId,actualType,populationId,populationType) 
select s_populationTypes.NEXTVAL as id,h.id,'householdStaff',d.id,'mentalPatient' from householdstaffs h,mentalpatients d 
where h.baseinfoid = d.baseinfoid and d.tempid=0;
--
alter table mentalpatients drop column tempid;
alter table mentalpatients drop column tempflag;
alter table addressinfo drop column tempid;
alter table baseinfo drop column tempid;
--- isdeath
MERGE INTO householdstaffs p 
USING (SELECT f.id FROM householdstaffs f left join baseinfo  b on f.baseinfoid=b.id 
where b.isdeath=1 and f.logout!=1) np 
ON (p.id = np.id) WHEN MATCHED THEN UPDATE  SET p.logout = 1,p.logoutreason='修改为死亡,注销' WHERE p.id = np.id;
----isdeath
MERGE INTO floatingpopulations p 
USING (SELECT f.id FROM floatingpopulations f left join baseinfo  b on f.baseinfoid=b.id 
where b.isdeath=1 and f.logout!=1) np 
ON (p.id = np.id) WHEN MATCHED THEN UPDATE  SET p.logout = 1,p.logoutreason='修改为死亡,注销' WHERE p.id = np.id;
-----ELDERLYPEOPLE
MERGE INTO ELDERLYPEOPLE p 
USING (SELECT f.id FROM ELDERLYPEOPLE f left join baseinfo  b on f.baseinfoid=b.id 
where b.isdeath=1 and f.isemphasis!=1) np 
ON (p.id = np.id) WHEN MATCHED THEN UPDATE  SET p.isemphasis = 1,p.ISEMPHASISREASON='修改为死亡,注销' WHERE p.id = np.id;
----NURTURESWOMEN
MERGE INTO NURTURESWOMEN p 
USING (SELECT f.id FROM NURTURESWOMEN f left join baseinfo  b on f.baseinfoid=b.id 
where b.isdeath=1 and f.isemphasis!=1) np 
ON (p.id = np.id) WHEN MATCHED THEN UPDATE  SET p.isemphasis = 1,p.ISEMPHASISREASON='修改为死亡,注销' WHERE p.id = np.id;