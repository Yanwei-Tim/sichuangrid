-- Add/modify columns 
alter table ORGLOGINSTANALS add username VARCHAR2(32);
-- Add comments to the columns 
comment on column ORGLOGINSTANALS.username is '用户名';

-- Add/modify columns 
alter table ISSUESTANDARDFORFUNORG add YELLOWLIMITverify NUMBER(10);
alter table ISSUESTANDARDFORFUNORG add redLIMITverify NUMBER(10);
-- Add comments to the columns 
comment on column ISSUESTANDARDFORFUNORG.YELLOWLIMITverify
  is '黄牌验证时限';
comment on column ISSUESTANDARDFORFUNORG.redLIMITverify
  is '红牌验证时限';
  

-- Add/modify columns 
alter table ADMINISTRATIVESTANDARD add YELLOWLIMITverify NUMBER(10);
alter table ADMINISTRATIVESTANDARD add redLIMITverify NUMBER(10);
-- Add comments to the columns 
comment on column ADMINISTRATIVESTANDARD.YELLOWLIMITverify
  is '黄牌验证时限';
comment on column ADMINISTRATIVESTANDARD.redLIMITverify
  is '红牌验证时限';
  
  
-- Add/modify columns 
alter table ISSUE_SKIPRULE add status number(2) default 1 not null;
-- Add comments to the columns 
comment on column ISSUE_SKIPRULE.status
  is '是否启用 1:是   0:否';  
  
--添加 越级规则权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,indexid)
values (s_permissions.nextVal, '启用', 'startIssueSkiprule', 0, '越级规则', 1, ' ', 
                               (select id from permissions t where t.ename='issueSkipruleManagement'),5);
commit;                               

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,indexid)
values (s_permissions.nextVal, '停用', 'stopIssueSkiprule', 0, '越级规则', 1, ' ', 
                               (select id from permissions t where t.ename='issueSkipruleManagement'),6);  
commit;                               