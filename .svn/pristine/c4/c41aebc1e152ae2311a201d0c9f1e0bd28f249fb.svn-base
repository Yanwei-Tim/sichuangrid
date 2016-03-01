--任务清单模块是否身份证隐藏权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isPropagandaNotHidCard', 0, '宣传核查', 1, (select id from permissions where ename='propagandaAndVerification'), null, '', null, 4, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isPoliceNotHidCard', 0, '民警带领下开展工作情况', 1, (select id from permissions where ename='workByPoliceManagement'), null, '', null, 6, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isDruggyTaskNotHidCard', 0, '吸毒人员', 1, (select id from permissions where ename='druggyTaskVisitmanagement'), null, '', null, 8, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isMentalTaskNotHidCard', 0, '严重精神障碍患者', 1, (select id from permissions where ename='mentalPatientTaskVisitmanagement'), null, '', null, 9, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isTermerRecordNotHidCard', 0, '社区服刑人员', 1, (select id from permissions where ename='termerRecordManage'), null, '', null, 7, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isPositiveInfoRecordNotHidCard', 0, '刑释人员', 1, (select id from permissions where ename='positiveInfoRecordManage'), null, '', null, 7, null);

  commit;