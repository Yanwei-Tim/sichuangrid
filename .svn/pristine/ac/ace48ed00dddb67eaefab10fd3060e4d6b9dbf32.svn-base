--巡场情况数据关联
insert into serviceRecordRelyObject(id,recordid,objecttype,objectid,objectname,orgid,cardnoorname)
(select s_serviceRecordRelyObject.Nextval,sro.recordid+?,(SELECT ct.classificationen FROM  complaceInfo_temp ct,keyplaces kp
  where ct.id_key = kp.id_key and kp.id = sro.objectid and kp.type =CASE WHEN UPPER(sro.objecttype)='SCHOOL' THEN 'SCHOOLS' WHEN UPPER(sro.objecttype)='OTHERLOCALE' THEN 'OTHERLOCALES' ELSE UPPER(sro.objecttype) END),(SELECT ct.companyplaceId FROM  complaceInfo_temp ct,keyplaces kp
  where ct.id_key = kp.id_key and kp.id = sro.objectid and kp.type =CASE WHEN UPPER(sro.objecttype)='SCHOOL' THEN 'SCHOOLS' WHEN UPPER(sro.objecttype)='OTHERLOCALE' THEN 'OTHERLOCALES' ELSE UPPER(sro.objecttype) END),sro.objectname,sro.orgid,sro.cardnoorname from serviceRecordRelyObject sro,recordid_temp rt where sro.recordid =rt.recordId and sro.id = rt.id);
  
--服务记录和人员的关联关系
insert into serviceRecordRelyMember(id,recordid,memberid,membername)
(select s_serviceRecordRelyMember.Nextval,sr.recordid+?,sr.memberid,sr.membername from serviceRecordRelyMember sr,(select distinct(recordid) from recordid_temp) rt where sr.recordid =rt.recordId);


--服务记录和人员的关联关系
insert into serviceRecordHasAttachments(id,recordid,filesize,filename,fileactualurl,createuser,updateuser,createdate,updatedate)
(select s_serviceRecordHasAttachment.NEXTVAL,srh.recordid+?,srh.filesize,srh.filename,srh.fileactualurl,srh.createuser,srh.updateuser,srh.createdate,srh.updatedate from serviceRecordHasAttachments srh,(select distinct(recordid) from recordid_temp) rt where srh.recordid =rt.recordId);

--服务记录
insert into serviceRecords(id,orgcode,orgid,teamid,occurdate,occurplace,servicemembers,servicejoiners,serviceobjects,recordtype,servicecontent,createuser,updateuser,createdate,updatedate)
(select s.id+?,s.orgcode,s.orgid,s.teamid,s.occurdate,s.occurplace,s.servicemembers,s.servicejoiners,s.serviceobjects,s.recordtype,s.servicecontent,s.createuser,s.updateuser,s.createdate,s.updatedate from serviceRecords s,(select distinct(recordid) from recordid_temp) rt where s.id =rt.recordId);

