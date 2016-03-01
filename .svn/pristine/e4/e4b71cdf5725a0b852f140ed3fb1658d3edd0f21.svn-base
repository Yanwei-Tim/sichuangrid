insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '危险化学品单位', 'allDangerousChemicalsUnitStatistic', 1, '研判分析', 1, (select id from permissions where ename='importantLocaltionStat_stat'), '', '/hotModuel/statAnalyse/baseInfoStat/keyPlace/index.ftl?keyTpe=DANGEROUSCHEMICALSUNIT', '', 5);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '网吧', 'allInternetBarStatistic', 1, '研判分析', 1, (select id from permissions where ename='importantLocaltionStat_stat'), '', '/hotModuel/statAnalyse/baseInfoStat/keyPlace/index.ftl?keyTpe=INTERNETBAR', '', 6);

update permissions set INDEXID=7 where ename='allPublicPlaceStatistic';
update permissions set INDEXID=8 where ename='allOtherLocales';

commit;