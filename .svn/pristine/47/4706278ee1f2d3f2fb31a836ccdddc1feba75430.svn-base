--新建网格员配置清单表
create sequence s_gridConfigServiceList
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table gridConfigServiceList
(
       id  number(10),
       funOrgId number(10),
       areaOrgId number(10),
       areaOrgLevel number(10),
       areaParentId number(10),
       createUser varchar2(32),
       createDate Date,
       updateUser varchar2(32),
       updateDate Date,
       orgInternalCode varchar2(32),
       constraint pk_gridConfigServiceList primary key (id)
);

 comment on table gridConfigServiceList is '任务清单职能部门配置';
 comment on column gridConfigServiceList.funOrgId is '职能部门';
 comment on column gridConfigServiceList.areaOrgId is '行政部门';
 comment on column gridConfigServiceList.areaOrgLevel is '行政级别';
 comment on column gridConfigServiceList.areaParentId is '上级部门';
 comment on column gridConfigServiceList.orgInternalCode is '行政部门组织code'; 
