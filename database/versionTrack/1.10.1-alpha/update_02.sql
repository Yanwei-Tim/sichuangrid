--十户联防
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '十户联防', 'tenHouseholdsJointManagement', 1, ' ', 1, null, '', '', '', 0, '');

--联防管理
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'联防管理','jointManage', 1, '十户联防', 1,(select id from permissions where ename = 'tenHouseholdsJointManagement'),
'', '', '',1,'');

--用户资料
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'用户资料','familyCondition', 1, '联防管理', 1,(select id from permissions where ename = 'jointManage'),
'', '/hotModuel/tenHouseholdsJoint/familyCondition/familyInfo.ftl', '',0,'');
--用户资料新增
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'新增','addFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',0,'');
--用户资料修改
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'修改','updateFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',1,'');
--用户资料查询
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'查询','searchFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',2,'');
--用户资料查看
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'查看','viewFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',3,'');
--用户资料删除
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'删除','deleteFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',4,'');
--用户资料注销
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'注销','logoutFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',5,'');
--用户资料取消注销
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'取消注销','cancelLogoutFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',6,'');
--用户资料导入
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'导入','importFamilyInfo', 0, '用户资料', 1,(select id from permissions where ename = 'familyCondition'),
'', '', '',7,'');

--分组信息
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'分组列表','groupingCondition', 1, '联防管理', 1,(select id from permissions where ename = 'jointManage'),
'', '/hotModuel/tenHouseholdsJoint/groupingCondition/familyTeam.ftl', '',1,'');
--分组新增
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'新增','addGroupingCondition', 0, '分组列表', 1,(select id from permissions where ename = 'groupingCondition'),
'', '', '',0,'');
--分组修改
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'修改','updateGroupingCondition', 0, '分组列表', 1,(select id from permissions where ename = 'groupingCondition'),
'', '', '',1,'');
--分组查询
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'查询','searchGroupingCondition', 0, '分组列表', 1,(select id from permissions where ename = 'groupingCondition'),
'', '', '',2,'');
--分组删除
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'删除','deleteGroupingCondition', 0, '分组列表', 1,(select id from permissions where ename = 'groupingCondition'),
'', '', '',3,'');
--分组导入
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'导入','importGroupingCondition', 0, '分组列表', 1,(select id from permissions where ename = 'groupingCondition'),
'', '', '',4,'');
--分组 管理组员
insert into permissions  (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'管理组员','managementGroupingCondition',0,'分组列表',(select id  from permissions where ename='groupingCondition'),1,5);

--短信中心
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'短信中心','smsManagement', 1, '十户联防', 1,(select id from permissions where ename = 'tenHouseholdsJointManagement'),
'', '', '',2,'');

--收件箱
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'收件箱','receiveBoxCondition', 1, '短信中心', 1,(select id from permissions where ename = 'smsManagement'),
'', '/hotModuel/tenHouseholdsJoint/sms/receiveBox.ftl', '',0,'');
--发件箱
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID, GRIDURL)
values(s_permissions.nextval,'发件箱','sendBoxCondition', 1, '短信中心', 1,(select id from permissions where ename = 'smsManagement'),
'', '/hotModuel/tenHouseholdsJoint/sms/sendBox.ftl', '',1,'');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '联防用户告警', 'indexCondition', 1, '十户联防', 1, (select id from permissions where ename='tenHouseholdsJointManagement'), '','/hotModuel/tenHouseholdsJoint/gis/gisIndex.ftl', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '受理', 'dealReceiveBoxCondition', 0, '收信箱', 1, (select id from permissions where ename='receiveBoxCondition'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteReceiveBoxCondition', 0, '收信箱', 1, (select id from permissions where ename='receiveBoxCondition'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewReceiveBoxCondition', 0, '收信箱', 1, (select id from permissions where ename='receiveBoxCondition'), '', '', '', 2, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchReceiveBoxCondition', 0, '收信箱', 1, (select id from permissions where ename='receiveBoxCondition'), '', '', '', 3, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查看', 'viewSendBoxCondition', 0, '发信箱', 1, (select id from permissions where ename='sendBoxCondition'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'searchSendBoxCondition', 0, '发信箱', 1, (select id from permissions where ename='sendBoxCondition'), '', '', '', 1, '');


commit;