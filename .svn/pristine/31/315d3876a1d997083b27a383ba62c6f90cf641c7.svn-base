-- 2013-11-25 17:50:44 JEFFREY 事件考核结果  。  时间范围类型
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'时间范围类型');
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='时间范围类型'), 0, 1, 
       '按月份统计', 'ayftj', 'anyuefentongji', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='时间范围类型'), 1, 2, 
       '按季度统计', 'ajdtj', 'anjidutongji', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='时间范围类型'), 2, 3, 
       '按年度统计', 'andtj', 'anniandutongji', 'admin', '', sysdate, null);
commit;
-- 2013-11-25 17:50:44 JEFFREY 考核评估系统  。  绩效考核  。  增加导出功能
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'导出','downAdministrativeRegradedPoints',0,'行政部门绩效考核',
       (select id from permissions where ename='statAdministrativeRegradedPointManagement'),1,
       (select max(indexId) from permissions where parentId = (select id from permissions where ename='statAdministrativeRegradedPointManagement')) + 1);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'导出','downFunctionalRegradedPoints',0,'职能部门绩效考核',
       (select id from permissions where ename='statFunctionalRegradedPointManagement'),1,
       (select max(indexId) from permissions where parentId = (select id from permissions where ename='statFunctionalRegradedPointManagement')) + 1);
commit;
-- 2013-11-25 17:52:58 JEFFREY 移动  事件处理系统  。  绩效考核模块  。 到  。  考核评估系统
update permissions set 
       cName = '事件考核标准管理',
       parentId = (select id from permissions where ename = 'evaluateManagement'), 
       moduleName = (select cname from permissions where ename = 'evaluateManagement'),
       normalUrl = '',
       indexId = (select max(indexId) from permissions where parentId = (select id from permissions where ename = 'evaluateManagement') + 1)
       where ename = 'issueAccessConfigManagement';

update permissions set
       moduleName = '事件考核标准管理'
       where ename = 'functionOrgTimeLimitStandardManagement';

update permissions set
       moduleName = '事件考核标准管理'
       where ename = 'administrativeOrgTimeLimitStandardManagement';

update permissions set 
       cName = '督办扣分标准',
       moduleName = '事件考核标准管理',
       permissionType = 1,
       indexId = 3
       where ename = 'addSet';

update permissions set 
       cName = '事件考核结果',
       parentId = (select id from permissions where ename = 'evaluateManagement'), 
       moduleName = (select cname from permissions where ename = 'evaluateManagement'),
       indexId = (select max(indexId) from permissions where parentId = (select id from permissions where ename = 'evaluateManagement') + 1)
       where ename = 'performanceAppraisalManagement';

update permissions set 
       cName = '行政部门考核结果',
       moduleName = '事件考核结果'
       where ename = 'statAdministrativeRegradedPointManagement';

update permissions set 
       cName = '职能部门考核结果',
       moduleName = '事件考核结果'
       where ename = 'statFunctionalRegradedPointManagement';

commit;

-- 2013-11-25 17:54:45 JEFFREY 修改办理时限为4项必填一项
alter table issueStandardForFunOrg modify Yellowlimitaccept null;
alter table issueStandardForFunOrg modify Yellowlimithandle null;
alter table issueStandardForFunOrg modify Redlimitaccept null;
alter table issueStandardForFunOrg modify Redlimithandle null;

alter table administrativeStandard modify Yellowlimitaccept null;
alter table administrativeStandard modify Yellowlimithandle null;
alter table administrativeStandard modify Redlimitaccept null;
alter table administrativeStandard modify Redlimithandle null;

-- 2013-11-26 14:49:12 JEFFREY 修改分数为3位小数
alter table issueAccessConfig modify yellowcardscores number(13,3);
alter table issueAccessConfig modify redcardscores number(13,3);
alter table issueAccessConfig modify normalhandle number(13,3);

alter table regradedPoints modify points number(13,3);

-- 2013-11-28 14:27:37 JEFFREY 修改申请延期和审核延期
alter table issuesteps drop column lastendDate;
alter table issuesteps add delayWorkday number(10) default 0;
comment on column issuesteps.delayWorkday is '允许延长工作日数';

alter table ISSUEAPPLYDELAY modify DELAYDATE null;
alter table ISSUEAPPLYDELAY modify DELAYWORKDAY not null;
