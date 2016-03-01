
--用户权限缓存建表以及序列增量脚本
-- Create table
create table ENAMEPERMISSIONCACHE
(
  CACHEKEY       VARCHAR2(90),
  INVALIDATETIME NUMBER(30) not null,
  CACHEVALUE     CLOB,
  CREATEUSER     VARCHAR2(32),
  UPDATEUSER     VARCHAR2(32),
  CREATEDATE     DATE,
  UPDATEDATE     DATE
);
-- Add comments to the table 
comment on table ENAMEPERMISSIONCACHE
  is '用户权限缓存文件表';
-- Add comments to the columns 
comment on column ENAMEPERMISSIONCACHE.INVALIDATETIME
  is '失效时间';
comment on column ENAMEPERMISSIONCACHE.CACHEKEY
  is 'key值';
comment on column ENAMEPERMISSIONCACHE.CACHEVALUE
  is '缓存数据';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ENAMEPERMISSIONCACHE
  add constraint PK_ENAMEPERMISSIONCACHE primary key (CACHEKEY);

  
  
  --用户权限缓存job  每天早上八点五十五执行 
insert into taskploy(id, cname, ename, type, description, code)values(s_TASKPLOY.nextval,'用户权限缓存job','enamePermissionCacheDispatch',(select id from propertydicts where displayname = 'java方法'),'用户权限缓存job','enamePermissionCacheDispatch.handleEnamePermissionCache');
insert into task (id, name, taskgroup, description, ployId, config, closed)values(s_TASK.nextval, 'enamePermissionCacheDispatch','enamePermissionCacheDispatch','enamePermissionCacheDispatch',(select id from taskploy where ename = 'enamePermissionCacheDispatch'),'0 55 8 * * ?', 1);
