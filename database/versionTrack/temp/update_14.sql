
--人口信息模块是否身份证隐藏权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isFloatingPopulationNotHidCard', 0, '流动人口', 1, (select id from permissions where ename='floatingPopulationManagement'), null, null, null, 17, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isUnsettledPopulationNotHidCard', 0, '*未落户人口', 1, (select id from permissions where ename='unsettledPopulation'), null, null, null, 15, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isPositiveInfoManagementNotHidCard', 0, '刑释人员', 1, (select id from permissions where ename='positiveInfoManagement'), null, null, null, 15, null);
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isRectificativePersonManagementNotHidCard', 0, '社区矫正人员', 1, (select id from permissions where ename='rectificativePersonManagement'), null, null, null, 16, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isMentalPatientManagementNotHidCard', 0, '精神病人员', 1, (select id from permissions where ename='mentalPatientManagement'), null, null, null, 16, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isDruggyManagementNotHidCard', 0, '吸毒人员', 1, (select id from permissions where ename='druggyManagement'), null, null, null, 16, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isAidspopulationsManagementNotHidCard', 0, '艾滋病人员', 1, (select id from permissions where ename='aidspopulationsManagement'), null, null, null, 16, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isIdleYouthManagementNotHidCard', 0, '重点青少年', 1, (select id from permissions where ename='idleYouthManagement'), null, null, null, 16, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isDangerousGoodsPractitionerManagementNotHidCard', 0, '危险品从业人员', 1, (select id from permissions where ename='dangerousGoodsPractitionerManagement'), null, null, null, 16, null);
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isSuperiorVisitManagementNotHidCard', 0, '重点上访人员', 1, (select id from permissions where ename='superiorVisitManag'), null, null, null, 14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isOtherAttentionPersonnelManagementNotHidCard', 0, '其他人员', 1, (select id from permissions where ename='otherAttentionPersonnelManagement'), null, null, null, 14, null);
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isElderlyPeopleManagementNotHidCard', 0, '老年人', 1, (select id from permissions where ename='elderlyPeopleManagement'), null, null, null, 14, null);
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isHandicappedManagementNotHidCard', 0, '残疾人', 1, (select id from permissions where ename='handicappedManagement'), null, null, null, 14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isOptimalObjectManagementNotHidCard', 0, '优抚对象', 1, (select id from permissions where ename='optimalObjectManagement'), null, null, null, 14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isAidNeedPopulationManagementNotHidCard', 0, '需救助人员', 1, (select id from permissions where ename='aidNeedPopulationManagement'), null, null, null, 14, null);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isUnemployedPeopleManagementNotHidCard', 0, '失业人员', 1, (select id from permissions where ename='unemployedPeopleManagement'), null, null, null, 14, null);
 
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isNurturesWomenNotHidCard', 0, '育龄妇女', 1, (select id from permissions where ename='nurturesWomen'), null, null, null, 14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isYoungstersManagementNotHidCard', 0, '青少年', 1, (select id from permissions where ename='youngstersManagement'), null, null, null, 5, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isFPersonnelManagementNotHidCard', 0, 'F', 1, (select id from permissions where ename='fPersonnelManagement'), null, null, null,14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isQPersonnelManagementNotHidCard', 0, 'Q', 1, (select id from permissions where ename='qPersonnelManagement'), null, null, null,14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isMPersonnelManagementNotHidCard', 0, 'M', 1, (select id from permissions where ename='mPersonnelManagement'), null, null, null,14, null);
  
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
  values (s_permissions.NEXTVAL, '是否身份证不隐藏', 'isGoodSamaritanManagementNotHidCard', 0, '见义勇为', 1, (select id from permissions where ename='goodSamaritanManagement'), null, null, null,14, null);
  
  commit; 