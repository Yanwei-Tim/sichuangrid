update permissions set normalurl = '',LEADERURL = '' where ename = 'hiddenDangerManagement';

delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with parentid=(select id from permissions where ename='hiddenDangerManagement')
　　connect by prior p.id = p.parentid
);

delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with parentid=(select id from permissions where ename='hiddenDangerManagement')
　　connect by prior p.id = p.parentid
);

delete from permissions where id in (
   select p.id from permissions p
　　start with parentid=(select id from permissions where ename='hiddenDangerManagement')
　　connect by prior p.id = p.parentid
);

insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '总数', 'hiddenDangerAllManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=all', null, 5);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerAll', 0, '总数', 1, (select id from permissions where ename='hiddenDangerAllManagement'), null, null, null, 4);


insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉暴恐', 'hiddenDangerViolenceManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=violence', null, 6);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerViolence', 0, '涉暴恐', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerViolence', 0, '涉暴恐', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerViolence', 0, '涉暴恐', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerViolence', 0, '涉暴恐', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerViolence', 0, '涉暴恐', 1, (select id from permissions where ename='hiddenDangerViolenceManagement'), null, null, null, 4);


insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉枪涉爆', 'hiddenDangerGunManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=gun', null, 7);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerGun', 0, '涉枪涉爆', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerGun', 0, '涉枪涉爆', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerGun', 0, '涉枪涉爆', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerGun', 0, '涉枪涉爆', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerGun', 0, '涉枪涉爆', 1, (select id from permissions where ename='hiddenDangerGunManagement'), null, null, null, 4);


insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉制毒', 'hiddenDangerMakeDrugManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=makeDrug', null, 8);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerMakeDrug', 0, '涉制毒', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerMakeDrug', 0, '涉制毒', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerMakeDrug', 0, '涉制毒', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerMakeDrug', 0, '涉制毒', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerMakeDrug', 0, '涉制毒', 1, (select id from permissions where ename='hiddenDangerMakeDrugManagement'), null, null, null, 4);




insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉贩毒', 'hiddenDangerPushDrugManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=pushDrug', null, 9);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerPushDrug', 0, '涉贩毒', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerPushDrug', 0, '涉贩毒', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerPushDrug', 0, '涉贩毒', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerPushDrug', 0, '涉贩毒', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerPushDrug', 0, '涉贩毒', 1, (select id from permissions where ename='hiddenDangerPushDrugManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉吸毒', 'hiddenDangerTakeDrugManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=takeDrug', null, 10);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerTakeDrug', 0, '涉吸毒', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerTakeDrug', 0, '涉吸毒', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerTakeDrug', 0, '涉吸毒', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerTakeDrug', 0, '涉吸毒', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerTakeDrug', 0, '涉吸毒', 1, (select id from permissions where ename='hiddenDangerTakeDrugManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '邪教活动', 'hiddenDangerCultManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=cult', null, 11);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerCult', 0, '邪教活动', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerCult', 0, '邪教活动', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerCult', 0, '邪教活动', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerCult', 0, '邪教活动', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerCult', 0, '邪教活动', 1, (select id from permissions where ename='hiddenDangerCultManagement'), null, null, null, 4);




insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '制假贩假', 'hiddenDangerCounterfeitManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=counterfeit', null, 12);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerCounterfeit', 0, '制假贩假', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerCounterfeit', 0, '制假贩假', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerCounterfeit', 0, '制假贩假', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerCounterfeit', 0, '制假贩假', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerCounterfeit', 0, '制假贩假', 1, (select id from permissions where ename='hiddenDangerCounterfeitManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉黄', 'hiddenDangerEroticaManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=erotica', null, 13);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerErotica', 0, '涉黄', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerErotica', 0, '涉黄', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerErotica', 0, '涉黄', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerErotica', 0, '涉黄', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerErotica', 0, '涉黄', 1, (select id from permissions where ename='hiddenDangerEroticaManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '涉赌', 'hiddenDangerGambleManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=gamble', null, 14);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerGamble', 0, '涉赌', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerGamble', 0, '涉赌', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerGamble', 0, '涉赌', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerGamble', 0, '涉赌', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerGamble', 0, '涉赌', 1, (select id from permissions where ename='hiddenDangerGambleManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '传销', 'hiddenDangerPyramidSaleManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=pyramidSale', null, 15);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerPyramidSale', 0, '传销', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerPyramidSale', 0, '传销', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerPyramidSale', 0, '传销', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerPyramidSale', 0, '传销', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerPyramidSale', 0, '传销', 1, (select id from permissions where ename='hiddenDangerPyramidSaleManagement'), null, null, null, 4);


insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '火灾隐患', 'hiddenDangerFireManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=fire', null, 16);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerFire', 0, '火灾隐患', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerFire', 0, '火灾隐患', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerFire', 0, '火灾隐患', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerFire', 0, '火灾隐患', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerFire', 0, '火灾隐患', 1, (select id from permissions where ename='hiddenDangerFireManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '收赃', 'hiddenDangerReceiveLootManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=receiveLoot', null, 17);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerReceiveLoot', 0, '收赃', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerReceiveLoot', 0, '收赃', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerReceiveLoot', 0, '收赃', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerReceiveLoot', 0, '收赃', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerReceiveLoot', 0, '收赃', 1, (select id from permissions where ename='hiddenDangerReceiveLootManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '销赃', 'hiddenDangerPushLootManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=pushLoot', null, 18);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerPushLoot', 0, '销赃', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerPushLoot', 0, '销赃', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerPushLoot', 0, '销赃', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerPushLoot', 0, '销赃', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerPushLoot', 0, '销赃', 1, (select id from permissions where ename='hiddenDangerPushLootManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '无守楼护院人员', 'hiddenDangerNoTendManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=noTend', null, 19);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerNoTend', 0, '无守楼护院人员', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerNoTend', 0, '无守楼护院人员', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerNoTend', 0, '无守楼护院人员', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerNoTend', 0, '无守楼护院人员', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerNoTend', 0, '无守楼护院人员', 1, (select id from permissions where ename='hiddenDangerNoTendManagement'), null, null, null, 4);



insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '其他异常事件', 'hiddenDangerOtherManagement', 1, '发现治安隐患', 1, (select id from permissions where ename='hiddenDangerManagement'), null, '/hotModuel/task/hiddenDanger/hiddenDanger.ftl?type=other', null, 20);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '新增', 'addHiddenDangerOther', 0, '其他异常事件', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 0);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '修改', 'updateHiddenDangerOther', 0, '其他异常事件', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 1);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查看', 'viewHiddenDangerOther', 0, '其他异常事件', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 2);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '删除', 'deleteHiddenDangerOther', 0, '其他异常事件', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 3);
insert into PERMISSIONS (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '查询', 'searchHiddenDangerOther', 0, '其他异常事件', 1, (select id from permissions where ename='hiddenDangerOtherManagement'), null, null, null, 4);

