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
select count(id) from householdstaffs;
--FLOATINGPOPULATIONS
select count(id) from floatingpopulations;
--DRUGGYS
select count(id) from druggys;
--AIDNEEDPOPULATION
select count(id) from aidneedpopulation;
--AIDSPOPULATIONS
select count(id) from aidspopulations;
--DANGEROUSGOODSPRACTITIONERS
select count(id) from dangerousgoodspractitioners;
--ELDERLYPEOPLE
select count(id) from elderlypeople;
--HANDICAPPEDS
select count(id) from handicappeds;
--IDLEYOUTHS	
select count(id) from idleyouths;
--MENTALPATIENTS		
select count(id) from mentalpatients;
--UNEMPLOYEDPEOPLE
select count(id) from unemployedpeople;
--SUPERIORVISITS
select count(id) from superiorvisits;
--RECTIFICATIVEPERSONS	
select count(id) from rectificativepersons;
--POSITIVEINFOS			
select count(id) from positiveinfos;
--OTHERATTENTIONPERSONNELS
select count(id) from otherattentionpersonnels;		
--OPTIMALOBJECTS	
select count(id) from optimalobjects;	
--NURTURESWOMEN	
select count(id) from nurtureswomen;
--baseinfo
select count(id) from baseinfo;
--addressinfo
select count(id) from addressinfo;
--
--DRUGGYS
select count(id) from druggys where baseinfoid is null;
--AIDNEEDPOPULATION
select count(id) from aidneedpopulation where baseinfoid is null;
--AIDSPOPULATIONS
select count(id) from aidspopulations where baseinfoid is null;
--DANGEROUSGOODSPRACTITIONERS
select count(id) from dangerousgoodspractitioners where baseinfoid is null;
--ELDERLYPEOPLE
select count(id) from elderlypeople where baseinfoid is null; 
--HANDICAPPEDS
select count(id) from handicappeds where baseinfoid is null;
--IDLEYOUTHS	
select count(id) from idleyouths where baseinfoid is null;
--MENTALPATIENTS		
select count(id) from mentalpatients where baseinfoid is null;
--UNEMPLOYEDPEOPLE
select count(id) from unemployedpeople where baseinfoid is null;
--SUPERIORVISITS
select count(id) from superiorvisits where baseinfoid is null;
--RECTIFICATIVEPERSONS	
select count(id) from rectificativepersons where baseinfoid is null;
--POSITIVEINFOS			
select count(id) from positiveinfos where baseinfoid is null;
--OTHERATTENTIONPERSONNELS
select count(id) from otherattentionpersonnels where baseinfoid is null;
--OPTIMALOBJECTS	
select count(id) from optimalobjects where baseinfoid is null;	
--NURTURESWOMEN	
select count(id) from nurtureswomen where baseinfoid is null;


