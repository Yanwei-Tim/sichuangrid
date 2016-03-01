----删除teamid的非空约束
alter table serviceRecords modify teamid number(10) NULL; 
----删除表SERVICERECORDRELYMEMBER 对于SERVICETEAMMEMBERDETAILS的外键
alter table SERVICERECORDRELYMEMBER drop constraint FKSERVICERECORDRELYMEMBER;
