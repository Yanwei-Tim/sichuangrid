--删除已办中的宣传事例权限
delete from rolehaspermissions where permissionid in (
select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassDoneIssue')
　　connect by prior p.id =  p.parentid
);
delete from moduelclickcounts where permissionid in  (
 select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassDoneIssue')
　　connect by prior p.id =  p.parentid
);
delete from permissions where id in (
   select p.id from permissions p
　　start with id=(select id from permissions where ename='publicltyCassDoneIssue')
　　connect by prior p.id =  p.parentid
);
commit;

--新增已办结的设为宣传案例权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '设为宣传案例', 'publicltyCassDoneIssue', 0, '已办结事项', 1, (select id from permissions where ename='completedIssueListManagement'), '', '', '', 5, '');
commit;

--新增宣传案例页面的权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '宣传案例', 'publicltyCassDoneIssueListManagement', 1, '事件处理', 1, (select id from permissions where ename='myIssueListManagement'), '', '/issue/issueManage/issueList.jsp?type=myIssueListManagement', '', 10, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchPublicltyCassDoneIssue', 0, '宣传案例', 1, (select id from permissions where ename='publicltyCassDoneIssueListManagement'), '', '', '', 0, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewPublicltyCassDoneIssue', 0, '宣传案例', 1, (select id from permissions where ename='publicltyCassDoneIssueListManagement'), '', '', '', 1, '');
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '取消宣传案例', 'cancelPublicltyCassDoneIssue', 0, '宣传案例', 1, (select id from permissions where ename='publicltyCassDoneIssueListManagement'), '', '', '', 2, '');
commit;

--事件模块，阅读事件的日志 历史数据的修改
update issuelogs  set dealDescription='阅读 本事件' where  dealdescription like '阅读%';
commit;

alter table ISSUES add fromserialnumber varchar2(20) unique;
comment on column ISSUES.fromserialnumber
  is '呼叫中心分流事件系列号';
alter table ISSUES add deadLine number(10);
comment on column issues.deadLine is '呼叫中心的办理期限';

--添加冗余字段
alter table ISSUES add createorglevel NUMBER(10);
comment on column ISSUES.createorglevel
  is '创建组织机构层级';
alter table ISSUES add lastorglevel NUMBER(10);
comment on column ISSUES.lastorglevel
  is '最后操作组织机构层级';
alter table issuesteps add targetorglevel NUMBER(10);
comment on column issuesteps.targetorglevel
  is '目标组织机构层级';
alter table ISSUES add createorgfunctionalorgType NUMBER(10) default 0;
comment on column ISSUES.createorgfunctionalorgType
  is '创建组织机构类型';
alter table ISSUES add lastorgfunctionalorgType NUMBER(10) default 0;
comment on column ISSUES.lastorgfunctionalorgType
  is '最后操作组织机构类型';
alter table issuesteps add targetorgfunctionalorgType NUMBER(10) default 0;
comment on column issuesteps.targetorgfunctionalorgType
  is '目标组织机构类型';
CREATE INDEX ISSUES_CREATEORGLEVEL ON ISSUES (CREATEORGLEVEL,CREATEORGFUNCTIONALORGTYPE,CREATEORGINTERNALCODE);
CREATE INDEX ISSUES_LASTORGLEVEL ON ISSUES (LASTORGLEVEL,LASTORGFUNCTIONALORGTYPE,LASTORGINTERNALCODE);
CREATE INDEX ISSUESTEPS_TARGETORGLEVEL ON ISSUESTEPS (TARGETORGLEVEL,TARGETORGFUNCTIONALORGTYPE,TARGETINTERNALCODE);

--数据迁移
update issues
   set (createorglevel,createorgfunctionalorgType) =
       (select orglevel,case when functionalorgType is null then 0 else functionalorgType end from organizations where id = issues.createorg),
       (lastorglevel,lastorgfunctionalorgType)  =
       (select orglevel,case when functionalorgType is null then 0 else functionalorgType end from organizations where id = issues.lastorg);
commit;

update issuesteps
   set (targetorglevel,targetorgfunctionalorgType) =
       (select orglevel,case when functionalorgType is null then 0 else functionalorgType end from organizations where id = issuesteps.target);
commit;



