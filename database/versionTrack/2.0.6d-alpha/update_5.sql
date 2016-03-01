--总数签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 5);
--涉暴恐签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerViolence', 0, '总数', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 5);
--涉枪涉爆签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerGun', 0, '总数', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 5);
--涉制毒签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerMakeDrug', 0, '总数', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 5);
--涉贩毒签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerPushDrug', 0, '总数', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 5);
--涉吸毒签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerTakeDrug', 0, '总数', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 5);
--邪教活动签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerCult', 0, '总数', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 5);
--制假贩假签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerCounterfeit', 0, '总数', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 5);
--涉黄签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerErotica', 0, '总数', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 5);
--涉赌签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerGamble', 0, '总数', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 5);
--传销签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerPyramidSale', 0, '总数', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 5);
--火灾隐患签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerFire', 0, '总数', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 5);
--收赃签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerReceiveLoot', 0, '总数', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 5);
--销赃签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerPushLoot', 0, '总数', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 5);
--无守楼护院人员签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerNoTend', 0, '总数', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 5);
--其他异常事件签收权限
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '签收', 'signHiddenDangerOther', 0, '总数', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 5);
