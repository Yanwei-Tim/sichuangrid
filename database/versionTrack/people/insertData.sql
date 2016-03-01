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
--HOUSEHOLDSTAFFS
insert into householdstaffs
  (id, orgid, orginternalcode, familyid, accountnumber, residencetype, relationshipwithhead, outgone, outreasons, reasonsdate, outprovince, outcity, outdistrict, gooutdetailedaddress, homephone, residentstatus, settletime, sourcesstate, oldcurrentaddress, immigrationdate, properstationcode, properstation, immigrationsort, immigrationcause, emigrationdate, emigrationcode, emigrationland, emigrationsort, emigrationcause, healthstate, militaryservice, annualincome, insured, outgonedirection, soldierdependents, lowincome, hardstate, abroaddependents, abroaddependentstype, status, ismoved, occupation, logout, logoutreason, logoutdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, familyid, accountnumber, residencetype, relationshipwithhead, outgone, outreasons, reasonsdate, outprovince, outcity, outdistrict, gooutdetailedaddress, homephone, residentstatus, settletime, sourcesstate, oldcurrentaddress, immigrationdate, properstationcode, properstation, immigrationsort, immigrationcause, emigrationdate, emigrationcode, emigrationland, emigrationsort, emigrationcause, healthstate, militaryservice, annualincome, insured, outgonedirection, soldierdependents, lowincome, hardstate, abroaddependents, abroaddependentstype, status, ismoved, occupation, logout, logoutreason, logoutdate, createuser, updateuser, createdate, updatedate from sichuan_temp.householdstaffs;
--FLOATINGPOPULATIONS
insert into floatingpopulations
  (id, orgid, orginternalcode, isinflowing, inflowingreason, inflowingdate, inflowingprovince, inflowingcity, inflowingdistrict, registrationtype, registerdate, expecteddatedue, residencetype, certificatenumber, staylocationtypeid, economysourceid, staytimelimitid, preparedstaytimelimitid, hasmarriedprove, settletime, sourcesstate, oldcurrentaddress, toberastate, householdtype, professionalqualifications, iscouplespeer, pregnancyandcontraceptioncase, fertilityno, boychildrenno, girlchildrenno, cartype, carcolor, carplate, logout, logoutreason, logoutdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, isinflowing, inflowingreason, inflowingdate, inflowingprovince, inflowingcity, inflowingdistrict, registrationtype, registerdate, expecteddatedue, residencetype, certificatenumber, staylocationtypeid, economysourceid, staytimelimitid, preparedstaytimelimitid, hasmarriedprove, settletime, sourcesstate, oldcurrentaddress, toberastate, householdtype, professionalqualifications, iscouplespeer, pregnancyandcontraceptioncase, fertilityno, boychildrenno, girlchildrenno, cartype, carcolor, carplate, logout, logoutreason, logoutdate, createuser, updateuser, createdate, updatedate from sichuan_temp.floatingpopulations;
--DRUGGYS
insert into druggys
  (id, orgid, orginternalcode, drugreason, drugsource, detoxicatecase, detoxicatecondition, isadanon, controlactuality, drugtype, ferretoutdate, drugfristdate, isundergo_treatment, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, drugreason, drugsource, detoxicatecase, detoxicatecondition, isadanon, controlactuality, drugtype, ferretoutdate, drugfristdate, isundergo_treatment, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.druggys;
--AIDNEEDPOPULATION
insert into aidneedpopulation
  (id, orgid, orginternalcode, aidreason, islowhouseholds, difficultcardno, difficulttype, subsidiesamount, subsidiesgettime, subsidiesstarttime, subsidiespopulation, subsidiesarea, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, aidreason, islowhouseholds, difficultcardno, difficulttype, subsidiesamount, subsidiesgettime, subsidiesstarttime, subsidiespopulation, subsidiesarea, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.aidneedpopulation;
--AIDSPOPULATIONS
insert into aidspopulations
  (id, orgid, orginternalcode, addressno, infectway, violationsofthelaw, crimetype, receivedorganization, receivedlevel, helpcircumstances, actualpopulationtype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, addressno, infectway, violationsofthelaw, crimetype, receivedorganization, receivedlevel, helpcircumstances, actualpopulationtype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.aidspopulations;
--DANGEROUSGOODSPRACTITIONERS
insert into dangerousgoodspractitioners
  (id, orgid, orginternalcode, dangerousworkingtype, legalperson, legalpersontelephone, legalpersonmobilenumber, workingcertificate, workingcertificateno, healthstate, periodofvaliditystart, periodofvalidityend, school, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, dangerousworkingtype, legalperson, legalpersontelephone, legalpersonmobilenumber, workingcertificate, workingcertificateno, healthstate, periodofvaliditystart, periodofvalidityend, school, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.dangerousgoodspractitioners;
--ELDERLYPEOPLE
insert into elderlypeople
  (id,orgid, orginternalcode, enterworkdate, retiredate, pension, livestatus, peopletype, spousestatus, currentstatus, incomesource, retireunitandduty, elderpeopleid, zhiwu, beizhu, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, enterworkdate, retiredate, pension, livestatus, peopletype, spousestatus, currentstatus, incomesource, retireunitandduty, elderpeopleid, zhiwu, beizhu, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.elderlypeople;
--HANDICAPPEDS
insert into handicappeds
  (id, orgid, orginternalcode, haddisabilitycard, disabilityreason, disabilitytype, disabilitycardno, disability, disabilitydate, workprofile, skillprofile, guardianname, guardiantelephone, guardianmobilenumber, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, haddisabilitycard, disabilityreason, disabilitytype, disabilitycardno, disability, disabilitydate, workprofile, skillprofile, guardianname, guardiantelephone, guardianmobilenumber, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.handicappeds;
--IDLEYOUTHS	
insert into idleyouths
  (id,orgid, orginternalcode, workcondition, stafftype, guardianname, guardiantelephone, guardianmobilenumber, isstayinschool, schoolname, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id,orgid, orginternalcode, workcondition, stafftype, guardianname, guardiantelephone, guardianmobilenumber, isstayinschool, schoolname, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.idleyouths;				
--MENTALPATIENTS		
insert into mentalpatients
  (id,orgid, orginternalcode, healthstate, address, psychosisname, psychosistype, dangerlevel, istreat, curedepartment, businessremark, guardian, guardiantelephone, guardianmobilenumber, diseasetime, treatmentstate, recoverytime, isundergo_treatment, school, oldcurrentaddress, residencetype, sourcesstate, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, healthstate, address, psychosisname, psychosistype, dangerlevel, istreat, curedepartment, businessremark, guardian, guardiantelephone, guardianmobilenumber, diseasetime, treatmentstate, recoverytime, isundergo_treatment, school, oldcurrentaddress, residencetype, sourcesstate, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.mentalpatients;		
--UNEMPLOYEDPEOPLE
insert into unemployedpeople
  (id,orgid, orginternalcode, residencetype, unemployedpeopletype, registernumber, originalworkunit, originalworkunittype, enterworkdate, unemploymentdate, technologylevel, specialtyskills, unemploymentreason, title, participatedprograms, workstate, skill, jobintention, monthlywages, isdealwithlow, isenjoyunemploymentmoney, enddateofunemploymentmoney, headname, familyid, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, residencetype, unemployedpeopletype, registernumber, originalworkunit, originalworkunittype, enterworkdate, unemploymentdate, technologylevel, specialtyskills, unemploymentreason, title, participatedprograms, workstate, skill, jobintention, monthlywages, isdealwithlow, isenjoyunemploymentmoney, enddateofunemploymentmoney, headname, familyid, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.unemployedpeople;				
--SUPERIORVISITS
insert into superiorvisits
  (id,orgid, orginternalcode, visitstate, visittype, visitreason, nativeplace, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, visitstate, visittype, visitreason, nativeplace, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate  from sichuan_temp.superiorvisits;				
--RECTIFICATIVEPERSONS	
insert into rectificativepersons
  (id,orgid, orginternalcode, executetype, penaltyterm, recentsituation, helpeducator, educatortelephone, educatormobilenumber, rectifystartdate, rectifyenddate, bussinessremark, accusation, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, executetype, penaltyterm, recentsituation, helpeducator, educatortelephone, educatormobilenumber, rectifystartdate, rectifyenddate, bussinessremark, accusation, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.rectificativepersons;		
--POSITIVEINFOS			
insert into positiveinfos
  (id,orgid, orginternalcode, isrepeat, imprisonmentdate, casereason, laboreduaddress, noresettlementreason, agoprofession, householdid, helpeducator, educatortelephone, educatormobilenumber, resettlementdate, releaseorbackdate, positiveinfotype, iscrime, crimedate, criminalbehavior, resettlement, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, isrepeat, imprisonmentdate, casereason, laboreduaddress, noresettlementreason, agoprofession, householdid, helpeducator, educatortelephone, educatormobilenumber, resettlementdate, releaseorbackdate, positiveinfotype, iscrime, crimedate, criminalbehavior, resettlement, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.positiveinfos;				
--OTHERATTENTIONPERSONNELS		
insert into otherattentionpersonnels
  (id,orgid, orginternalcode, ferretoutdate, drugfristdate, attentionreason, workcondition, meremark, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, ferretoutdate, drugfristdate, attentionreason, workcondition, meremark, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.otherattentionpersonnels;		
--OPTIMALOBJECTS	
insert into optimalobjects
  (id,orgid, orginternalcode, optimalcardno, optimalcardtype, laborability, abilityliving, employmentsituation, supportsituation, monthlivingexpenses, ferretoutdate, drugfristdate, inhabitantid, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate)
select id,orgid, orginternalcode, optimalcardno, optimalcardtype, laborability, abilityliving, employmentsituation, supportsituation, monthlivingexpenses, ferretoutdate, drugfristdate, inhabitantid, oldcurrentaddress, residencetype, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, createdate, updateuser, updatedate from sichuan_temp.optimalobjects;					
--NURTURESWOMEN	
insert into nurtureswomen
  (id, orgid, orginternalcode, fertilityservicesno, marriageregistrationtime, onechildofcouple, licensesituation, boynumber, girlnumber, licensetime, contraceptivemethod, contraceptivetime, manname, marriageordivorcetime, manmaritalstate, manidcardno, manfirstmarriagetime, manmobilenumber, mantelephone, manbirthday, mannation, manpoliticalbackground, manresidencetype, manschooling, mancareer, manworkunit, manprovince, mancity, mandistrict, mannativeplaceaddress, mancurrentaddress, mancurrentaddresstype, mancommunity, manblock, manunit, manroom, manremark, singlechildcardno, certifiedunit, isunmarriedpregnant, islevied, ismaternitycard, maternitycardunit, maternitycardno, maternitycardissuetime, maternitycardvaliditytime, maternitycardchecktime, maternitycardremark, pregnantphoto, pregnanthealthstatus, pregnantmotherhouse, husbandhealthstatus, conceptioncontroldate, pausimeniadate, issigncompact, childcount, drawlettercondition, canceldate, iscancel, cancelreason, persontype, residencenumber, housemastername, housemasterrelation, singlechildcdissuetime, firstmarriagetime, isdelete, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate)
select id, orgid, orginternalcode, fertilityservicesno, marriageregistrationtime, onechildofcouple, licensesituation, boynumber, girlnumber, licensetime, contraceptivemethod, contraceptivetime, manname, marriageordivorcetime, manmaritalstate, manidcardno, manfirstmarriagetime, manmobilenumber, mantelephone, manbirthday, mannation, manpoliticalbackground, manresidencetype, manschooling, mancareer, manworkunit, manprovince, mancity, mandistrict, mannativeplaceaddress, mancurrentaddress, mancurrentaddresstype, mancommunity, manblock, manunit, manroom, manremark, singlechildcardno, certifiedunit, isunmarriedpregnant, islevied, ismaternitycard, maternitycardunit, maternitycardno, maternitycardissuetime, maternitycardvaliditytime, maternitycardchecktime, maternitycardremark, pregnantphoto, pregnanthealthstatus, pregnantmotherhouse, husbandhealthstatus, conceptioncontroldate, pausimeniadate, issigncompact, childcount, drawlettercondition, canceldate, iscancel, cancelreason, persontype, residencenumber, housemastername, housemasterrelation, singlechildcdissuetime, firstmarriagetime, isdelete, residencetype, oldcurrentaddress, sourcesstate, attentionextent, isemphasis, isemphasisreason, isemphasisdate, createuser, updateuser, createdate, updatedate from sichuan_temp.nurtureswomen;
--baseinfo
alter table baseinfo add tempid number(10);
create index indexBaseinfoTempid on baseinfo (tempid);
--householdstaffs
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,id from sichuan_temp.householdstaffs;
-- update baseinfoid
MERGE INTO householdstaffs p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid;
--baseinfo
alter table baseinfo drop column tempid;
--
alter table baseinfo add tempid number(10);
create index indexBaseinfoTempid on baseinfo (tempid);
-- floatingpopulations
insert into baseinfo (id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,tempid)
select S_BASEINFO.NEXTVAL as id,name, fullpinyin, simplepinyin, usedname, idcardno, telephone, mobilenumber, birthday, gender, workunit, imgurl, email, isdeath, nation, politicalbackground, schooling, career, maritalstate, bloodtype, faith, stature, province, city, district, nativeplaceaddress, nativepolicestation, createuser, updateuser, createdate, updatedate,id from sichuan_temp.floatingpopulations;
-- update baseinfoid
MERGE INTO floatingpopulations p USING baseinfo b ON (p.id = b.tempid)    WHEN MATCHED THEN  
UPDATE  SET p.baseinfoid = b.id   WHERE p.id = b.tempid;
---drop 
alter table baseinfo drop column tempid;
-- updateDate
update baseinfo set updateDate = createDate where updateDate is null;



