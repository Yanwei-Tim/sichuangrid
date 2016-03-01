delete  from issuesteps where issue not in (select id from issues); 

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
   values (s_propertydicts.nextval, (select id from propertydomains p where domainname='三本台账时限设置层级'), 0, 5, '台账办公室', 'tzbgs', 'taizhangbangongshi', 'admin', '', sysdate, null);  
commit;
   
  alter table accountsteps_xichang add reportToFunctionEnd number(10);
   comment on column accountSteps_xichang.reportToFunctionEnd
  is '流程职能部门';
 
  alter table accountsteps_xichang add REALORCIRCULATION number(10);
 comment on column accountSteps_xichang.REALORCIRCULATION
  is '区分办结和流转办结';
  
  update accountsteps_xichang a set REALORCIRCULATION=10 where isfinish =1;
  
  
  alter table orgLocationTracks modify(orglocationname varchar2(150));
  alter table keyplaces modify(name varchar2(150));
  
   commit;