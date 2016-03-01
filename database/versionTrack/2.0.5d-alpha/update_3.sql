--数据字典
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertyDomains.NEXTVAL, '红袖套队伍类型', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertyDicts.NEXTVAL, (select id from propertydomains where domainname='红袖套队伍类型'), 0, 2, '门卫保安', 'mwba', 'menweibaoan', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertyDicts.NEXTVAL, (select id from propertydomains where domainname='红袖套队伍类型'), 0, 3, '公益志愿者', 'gyzyz', 'gongyizhiyuanzhe', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertyDicts.NEXTVAL,(select id from propertydomains where domainname='红袖套队伍类型'),0, 1, '综治巡逻队员', 'zzxldy', 'zongzhixunluoduiyuan', 'admin', '', sysdate, null);
commit;
--权限脚本
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '红袖套队伍管理', 'redCuffTeamManage', 1, '组织机构', 1, (select id from permissions where ename='gridManageSystem'), '', '/baseinfo/redCuffTeam/redCuffTeamList.jsp', '', 9, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '新增', 'addRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改', 'updateRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '导入', 'importRedCuffMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='redCuffTeamManage'), '', '', '', 5, '');

commit;
