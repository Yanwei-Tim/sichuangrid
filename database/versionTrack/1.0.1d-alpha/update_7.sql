--艾滋病人员权限修改(数据管理)--
update permissions set NORMALURL='/hotModuel/dataManage/population/aidspopulationsTempManage/aidsPopulationsTempList.ftl' ,
					   LEADERURL='/dataManage/aidspopulationsTempManage/aidsPopulationsTempList.jsp'
				 where ENAME = 'aidspopulationsTempImport';

update permissions set NORMALURL='/hotModuel/dataManage/population/aidspopulationsTempManage/aidsPopulationsTempList.ftl?mode=claimList' ,
					   LEADERURL='/dataManage/aidspopulationsTempManage/aidsPopulationsTempList.jsp?mode=claimList'
				 where ENAME = 'aidspopulationsTempclaim';
				 
commit;			 
