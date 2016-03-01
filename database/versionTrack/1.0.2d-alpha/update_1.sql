--研判分析(艾滋病人员的权限)--
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,parentId,DESCRIPTION,NORMALURL,LEADERURL,INDEXID)
	values(s_permissions.nextval,'艾滋病人员','aidspopulationsStatistic',1,'研判分析',1,(select id from permissions where ename = 'importantPersonelStat_stat'),'','/hotModuel/statAnalyse/baseInfoStat/aidsPopulation/index.ftl','',null);
   
commit;