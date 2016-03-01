----数据恢复中心 添加权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '恢复', 'recoverDatas', 0, '数据恢复中心', 1, (select id from permissions where ename='recoverDatasManagement'), null, null, null, 0, null);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteRecoverDatas', 0, '数据恢复中心', 1, (select id from permissions where ename='recoverDatasManagement'), null, null, null, 1, null);
commit;

----研判分析对应的升级脚本
update permissions p set p.cname = '组织机构',p.MODULENAME='研判分析',p.NORMALURL='' where p.ename = 'organizationStat';
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '总况', 'organizationStatistic', 1, '统计分析', 1, (select id from  permissions p where p.ename = 'organizationStat'), '', '/statAnalyse/baseInfoStat/organizationStat/organizationStat.jsp', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '党委部门', 'departmentPartyStatistic', 1, '统计分析', 1, (select id from  permissions p where p.ename = 'organizationStat'), '', 'statAnalyse/baseInfoStat/departmentParty/departmentPartyStat.jsp', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '登录情况', 'loginManage', 1, '研判分析', 1, (select id from  permissions p where p.ename = 'orgLoginStanalsManage'), '', '/statAnalyse/orgLoginStanalsManager/dispatch.action?mode=loginManage', '', 1, '');

--该字典项 线上已经存在
--insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
--values (s_propertydicts.nextval, (select id from propertydomains pd where pd.domainname = '部门党委组织类别'), 0, 10, '综治委', 'zzw', 'zongzhiwei', 'admin', '', to_date('02-01-2015 05:13:29', 'dd-mm-yyyy hh24:mi:ss'), null);


-- 创建登录情况的序列
create sequence S_LOGINMANGE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

commit;

