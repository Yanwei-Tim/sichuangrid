--四支队伍组织架构权限
update  permissions p  set p.normalurl='/fourTeamsOrgManage/queryFourTeamsOrgForList.action' where p.cname='组织架构';
commit ;
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
    values (s_permissions.NEXTVAL, '编辑组织队伍', 'editOrganizationChar', 0, '组织架构', 1, (select id from permissions where ename='organizationChartManagement'), '', '', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
    values (s_permissions.NEXTVAL, '清空组织队伍', 'emptyOrganizationChar', 0, '组织架构', 1, (select id from permissions where ename='organizationChartManagement'), '', '', '', 0);
commit ;   