--其他场所，组织类型增加字典项--
update  propertydicts set displayseq = 9 where propertydomainid = (select id from propertydomains where domainname='其他场所类型') and displayname = '其他';

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='其他场所类型'),
0,5,'餐饮服务','cyfw','canyinfuwu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='其他场所类型'),
0,6,'化妆品','hzp','huazhuangpin','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='其他场所类型'),
0,7,'药品','yp','yaopin','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='其他场所类型'),
0,8,'医疗器械','ylqx','yiliaoqixie','admin',sysdate);

commit;

-- 其他场所增加从业人员数字段 --
alter table OTHERLOCALES add practitioner_number NUMBER(10);
-- Add comments to the columns 
comment on column OTHERLOCALES.practitioner_number
  is '从业人员数';
 
commit;


-- 职业类别修改 --
delete propertydicts where propertydomainid = (select id from propertydomains where domainname='职业');

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,1,'国家工作人员','gjgzry','guojiagongzuorenyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,2,'教师','js','jiaoshi','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,3,'军人','jr','junren','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,4,'生产企业工作人员','scqygzry','shengchanqiyegongzuorenyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,5,'流通企业工作人员','ltqygzry','liutongqiyegongzuorenyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,6,'服务业从业人员','fwycyry','fuwuyecongyerenyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,7,'专业技术人员','cyjsry','congyejishurenyuan','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,8,'个体工商户','gtgsh','getigongshanghu','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,9,'农民','nm','nongmin','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,10,'学生','xs','xuesheng','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,11,'待业','dy','daiye','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='职业'),
0,12,'其他','qt','qita','admin',sysdate);

commit;


-- 身份证号  改为 身份证号码 --
comment on column HOUSEINFO.HOUSEOWNERIDCARDNO
  is '业主身份证号码';
comment on column RENTALHOUSE.HOUSEOWNERIDCARDNO
  is '业主身份证号码';
comment on column attentionObjects.idCardNo
  is '身份证号码';
comment on column dangerousGoodsPractitioners.idCardNo
  is '身份证号码';
comment on column druggys.idCardNo
  is '身份证号码';
comment on column UNEMPLOYEDPEOPLE.IDCARDNO
  is '身份证号码';
comment on column optimalObjects.idCardNo
  is '身份证号码';
comment on column partyOrgInfos.idCardNo is
'身份证号码';
comment on column partyMemberInfos.idCardNo is
'身份证号码';
comment on column lettingHouses.idCardNo is
'身份证号码';
comment on column mentalPatients.idCardNo is
'身份证号码';
comment on column poorpeople.idCardNo is
'身份证号码';
comment on column positiveInfos.idCardNo is
'身份证号码';
comment on column positiveInfos.birthday is
'出生日期(有身份证号码获取)';
comment on column superiorVisits.idCardNo is
'身份证号码';
comment on column superiorVisits.birthday is
'出生日期(有身份证号码获取)';
comment on column specialCareGroups.idCardNo is
'身份证号码';
comment on column personnelTracks.idCardno is
'人员身份证号码';
comment on column ELDERLYPEOPLE.IDCARDNO
  is '身份证号码';
comment on column CENSUSREGISTERFAMILYS.IDCARDNO
  is '户主身份证号码';
comment on column HOUSEHOLDSTAFFS.IDCARDNO
  is '身份证号码';
comment on column HOUSINGINFO.OWNERCARD
  is '房主身份证号码';
comment on column NURTURESWOMEN.MANIDCARDNO
  is '丈夫身份证号码';
comment on column RESIDENTPOPULATIONS.IDCARD
  is '身份证号码';
comment on column ACTUALCOMPANY.IDCARDNO
  is '身份证号码';
comment on column LaborEmploymentUnit.IDCARDNO
  is '身份证号码';
comment on column religion.idCardNo is
'负责人身份证号码';
comment on column religionBelief.idCardNo is
'身份证号码';
comment on column RELATEPERSONS.cardNoOrName
  is '身份证号码或者场所名';
comment on column RELATEPLACES.cardNoOrName
  is '身份证号码或者场所名';
comment on column otherAttentionPersonnels.IDCARDNO
  is '身份证号码';
comment on column aidsPopulations.idCardNo
  is '身份证号码';
comment on column aidsPopulations.idCardNo
  is '身份证号码';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_positiveInfos_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_positiveInfos_TEMP.BIRTHDAY
  is '出生日期(有身份证号码获取)';
comment on column DM_mentalPatients_Temp.idCardNo is
'身份证号码';
comment on column DM_druggys_Temp.idCardNo is
'身份证号码';
comment on column DM_elderlyPeople_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_optimalObjects_Temp.idCardNo is
'身份证号码';
comment on column DM_AIDNEEDPOPULATION_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_unemployedPeople_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_NURTURESWOMEN_TEMP.MANIDCARDNO
  is '丈夫身份证号码';
comment on column DM_rentalhouse_Temp.houseOwnerIdCardNo is
'业主身份证号码';
comment on column DM_ACTUALCOMPANY_Temp.IDCARDNO
  is '身份证号码';
comment on column DM_houseInfo_Temp.houseOwnerIdCardNo is
'业主身份证号码';
comment on column DM_otherAttentionPers_TEMP.IDCARDNO
  is '身份证号码';
comment on column DM_aidsPopulations_Temp.idCardNo
  is '身份证号码';
comment on column serviceRecordRelyObject.cardNoOrName
  is '身份证号码或者场所名';
comment on column serviceTeamGuarders.idCardNo
is '身份证号码';

commit;

-- 工作单位  改为  工作单位或就读学校 --
comment on column attentionObjects.workUnit is
'工作单位或就读学校';
comment on column dangerousGoodsPractitioners.workUnit is
'工作单位或就读学校';
comment on column druggys.workUnit is
'工作单位或就读学校';
comment on column UNEMPLOYEDPEOPLE.WORKUNIT
  is '工作单位或就读学校';
comment on column optimalObjects.workUnit is
'工作单位或就读学校';
comment on column partyMemberInfos.workUnit is
'工作单位或就读学校';
comment on column idleYouths.workUnit is
'工作单位或就读学校';
comment on column mentalPatients.workUnit is
'工作单位或就读学校';
comment on column mentalPatients.workUnit is
'工作单位或就读学校';
comment on column positiveInfos.workUnit is
'工作单位或就读学校';
comment on column superiorVisits.workUnit is
'工作单位或就读学校';
comment on column workingRecords.workingUnit is
'工作单位或就读学校';
comment on column overseaPersonnel.workUnit is
'工作单位或就读学校';
comment on column ELDERLYPEOPLE.WORKUNIT
  is '工作单位或就读学校';
comment on column HANDICAPPEDS.WORKUNIT
  is '工作单位或就读学校';
comment on column floatingPopulations.workUnit is
'工作单位或就读学校';
comment on column unsettledPopulations.workUnit is
'工作单位或就读学校';
comment on column HOUSEHOLDSTAFFS.workUnit
  is '工作单位或就读学校';
comment on column NURTURESWOMEN.WORKUNIT
  is '工作单位或就读学校';
comment on column NURTURESWOMEN.MANWORKUNIT
  is '丈夫工作单位或就读学校';
comment on column aidNeedPopulation.workUnit is
'工作单位或就读学校';
comment on column partyAbilityPerson.position is
'（工作单位或就读学校）职务';
comment on column partyAbilityPerson.workingOrg is
'工作单位或就读学校';
comment on column religionBelief.jobPost is
'工作单位或就读学校及职务';
comment on column otherAttentionPersonnels.WORKUNIT
  is '工作单位或就读学校';
comment on column aidsPopulations.workUnit is
'工作单位或就读学校';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_floatingPopulations_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_unsettledPopulations_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_overseaPersonnel_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_positiveInfos_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_mentalPatients_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_mentalPatients_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_druggys_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_idleYouths_temp.workUnit is
'工作单位或就读学校';
comment on column DM_elderlyPeople_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_handicappeds_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_optimalObjects_Temp.workUnit is
'工作单位或就读学校';
comment on column DM_AIDNEEDPOPULATION_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_unemployedPeople_Temp.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_NURTURESWOMEN_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_NURTURESWOMEN_TEMP.MANWORKUNIT
  is '丈夫工作单位或就读学校';
comment on column DM_otherAttentionPers_TEMP.WORKUNIT
  is '工作单位或就读学校';
comment on column DM_aidsPopulations_Temp.workUnit is
'工作单位或就读学校';

commit;

-- 家庭电话  改为  住宅电话 --
comment on column CENSUSREGISTERFAMILYS.HOMEPHONE
  is '住宅电话';
comment on column HOUSEHOLDSTAFFS.homePhone
  is '住宅电话';
comment on column DM_HOUSEHOLDSTAFFS_TEMP.HOMEPHONE
  is '住宅电话';
comment on column serviceTeamMemberBase.homephone
  is '住宅电话';

commit;

-- 结婚登记时间  改为  离婚时间 --
comment on column NURTURESWOMEN.MARRIAGEREGISTRATIONTIME
  is '离婚时间';
comment on column DM_NURTURESWOMEN_TEMP.MARRIAGEREGISTRATIONTIME
  is '离婚时间';

  commit;
  
-- 婚变时间  改为  再婚时间 --
comment on column NURTURESWOMEN.MARRIAGEORDIVORCETIME
  is '最近再婚时间';
comment on column DM_NURTURESWOMEN_TEMP.MARRIAGEORDIVORCETIME
  is '最近再婚时间';

commit;








