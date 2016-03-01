--网格员相关数据字典
insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'网格员专兼职情况');
  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='网格员专兼职情况'), 0, 1, '专职', 'zz', 'zhuanzhi', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='网格员专兼职情况'), 0, 2, '兼职', 'jz', 'jianzhi', 'admin', '', sysdate, null);

commit; 

--网格员权限
--网格员队伍
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '网格员队伍', 'gridTeam', 1, '组织机构', 1, (select id from permissions where ename='gridManageSystem'),'', '','', 10, '');
--网格员队伍管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '网格员队伍管理', 'gridTeamManage', 1, '网格员队伍', 1, (select id from permissions where ename='gridTeam'), '', '/baseinfo/gridTeam/gridTeamList.jsp', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '新增', 'addGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改', 'updateGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchGridMemeber', 0, '红袖套队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '激活', 'activateGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 5, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '导入', 'importGridMemeber', 0, '网格员队伍管理', 1, (select id from permissions where ename='gridTeamManage'), '', '', '', 6, '');

--网格员统计表
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '网格员统计表', 'gridTeamStatistics', 1, '网格员队伍', 1, (select id from permissions where ename='gridTeam'), '', '/baseinfo/gridTeam/gridTeamStatistics.jsp', '', 1, '');

commit;







