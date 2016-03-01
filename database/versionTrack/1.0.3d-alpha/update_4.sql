insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,parentId,DESCRIPTION,NORMALURL,LEADERURL,INDEXID)
  values(s_permissions.nextval,'服务监管措施','serviceSupervisionMeasuresDruggy',0,'吸毒人员',1,(select id from permissions where ename = 'druggyManagement'),'','','',null);
commit;

-- Add/modify columns 
alter table SERVICESUPERVISIONMEASURES add type varchar2(60);
-- Add comments to the columns 
comment on column SERVICESUPERVISIONMEASURES.type
  is '类型';
