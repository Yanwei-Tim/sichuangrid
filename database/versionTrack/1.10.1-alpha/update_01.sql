--基本信息统计报表拆分为 重点数据统计 和 特殊人群概况 原有报表直接做重点数据统计 特殊人群概况另作新表
update permissions set cname='基本情况统计' where ename = 'baseSituationStatement';
---帐号覆盖统计菜单名称修改
update permissions set cname='帐号覆盖统计' where ename = 'userActivateReport';
--特殊人群概况权限
insert into permissions
  (ID,
   CNAME,
   ENAME,
   PERMISSIONTYPE,
   MODULENAME,
   ENABLE,
   PARENTID,
   DESCRIPTION,
   NORMALURL,
   LEADERURL,
   INDEXID,
   GRIDURL)
values
  (s_permissions.nextval,
   '基本情况统计（特殊人群）',
   'specialCrowdReports',
   1,
   '研判分析',
   1,
   (select id from permissions where ename = 'statAnalyseManage'),
   '',
   '/statAnalyse/baseSituation/specialCrowdReports.jsp',
   '',
   17,
   '');
commit;