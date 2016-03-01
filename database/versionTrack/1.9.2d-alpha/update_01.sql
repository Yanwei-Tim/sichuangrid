drop table LEADERVIEWCACHE;
create table LEADERVIEWCACHE
(
  CACHEKEY       VARCHAR2(90),
  CACHETYPE       NUMBER(4),
  INVALIDATETIME NUMBER(30) not null,
  CACHEVALUE     CLOB,
  CREATEUSER     VARCHAR2(32),
  UPDATEUSER     VARCHAR2(32),
  CREATEDATE     DATE,
  UPDATEDATE     DATE
);
-- Add comments to the table 
comment on table LEADERVIEWCACHE
  is '领导视图缓存文件表';
-- Add comments to the columns 
comment on column LEADERVIEWCACHE.INVALIDATETIME
  is '失效时间';
comment on column LEADERVIEWCACHE.CACHEKEY
  is 'key值';
comment on column LEADERVIEWCACHE.CACHEVALUE
  is '缓存数据';
comment on column LEADERVIEWCACHE.CACHETYPE
  is '缓存类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table LEADERVIEWCACHE
  add constraint PK_LEADERVIEWCACHE primary key (CACHEKEY);
  
create table allorganizations  (
   id                   NUMBER(10)                      not null,
   parentFunOrgId       NUMBER(10),
   parentId             NUMBER(10),
   orgType              NUMBER(10)                      not null,
   orgLevel             NUMBER(10),
   subCount             NUMBER(10)                     default 0 not null,
   seq                  NUMBER(10)                     default 0 not null,
   maxCode              NUMBER(10)                     default 0 not null,
   subCountFun          NUMBER(10)                     default 0 not null,
   departmentNo         VARCHAR2(32),
   orgName              VARCHAR2(60)                    not null,
   contactWay           VARCHAR2(15),
   orgInternalCode      VARCHAR2(32)                    not null,
   simplePinyin         VARCHAR2(10)                    not null,
   fullpinyin           VARCHAR2(30)                    not null,
   remark               VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   buildingId      		VARCHAR2(30),
   centerX 				NUMBER(10),
   centerY 				NUMBER(10),
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   createDate           DATE                            not null,
   functionalOrgType              NUMBER(10),
   changeType           NUMBER(1)                       default 0 not null,
   changeDate           DATE,
   constraint pkallorganizations primary key (id)
);

comment on column allorganizations.parentFunOrgId is
'所属职能部门';

comment on column allorganizations.orgType is
'部门类型';

comment on column allorganizations.orgLevel is
'组织机构层级';

comment on column allorganizations.subCount is
'子部门数';

comment on column allorganizations.seq is
'序号';

comment on column allorganizations.maxCode is
'分配子部门最大编码';

comment on column allorganizations.subCountFun is
'子职能部门数';

comment on column allorganizations.departmentNo is
'部门编号';

comment on column allorganizations.orgName is
'部门名称';

comment on column allorganizations.contactWay is
'部门联系方式';

comment on column allorganizations.orgInternalCode is
'部门内置类型码';

comment on column allorganizations.simplePinyin is
'部门名称简拼';

comment on column allorganizations.fullpinyin is
'部门名称全拼';

comment on column allorganizations.remark is
'备注';

comment on column allorganizations.createUser is
'创建用户';

comment on column allorganizations.updateUser is
'更新用户名';

comment on column allorganizations.updateDate is
'更新用户';

comment on column allorganizations.createDate is
'创建日期';

comment on column allorganizations.changeType is
'变更类型 0：未变更 1：迁移2：删除';

comment on column allorganizations.changeDate is
'变更日期';

INSERT INTO allorganizations
  select id,
         parentFunOrgId,
         parentId,
         orgType,
         orgLevel,
         subCount,
         seq,
         maxCode,
         subCountFun,
         departmentNo,
         orgName,
         contactWay,
         orgInternalCode,
         simplePinyin,
         fullpinyin,
         remark,
         createUser,
         buildingId,
         centerX,
         centerY,
         updateUser,
         updateDate,
         createDate,
         functionalOrgType,
         0,
         null
    from organizations;

 commit;