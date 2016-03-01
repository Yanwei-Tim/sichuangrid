CREATE INDEX ledgerPeople_createDate ON ledgerpeopleaspirations (createDate);
CREATE INDEX ledgerPoorPeople_createDate ON ledgerpoorpeople (createDate);
CREATE INDEX ledgerSteadyPeople_createDate ON ledgersteadywork (createDate);
create index serialnumber_ledgerPeopleAspi on ledgerPeopleAspirations(serialnumber);
create index serialnumber_ledgerPoorAspi on ledgerpoorpeople(serialnumber);
create index serialnumber_ledgerSteadyAspi on ledgersteadywork(serialnumber);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '信息分析', 'informationAnalysis', 1, '新三本台账', 1, (select id from permissions where ename='serviceWorkThreeRecords'),
 '', '', '', 7, '');
 
 insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '年度累计收集情况', 'yearCollectAnalysis', 1, '台账信息分析', 1, (select id from permissions where ename='informationAnalysis'),
 '', '/account/informationAnalysis/yearCollectAnalysis.jsp', '', 0, '');
 
  insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '当月收集情况', 'monthCollectAnalysis', 1, '台账信息分析', 1, (select id from permissions where ename='informationAnalysis'),
 '', '/account/informationAnalysis/monthCollectAnalysis.jsp', '', 1, '');
 
ALTER TABLE account_2016 ADD (orgLevel number(10));

commit;
 
