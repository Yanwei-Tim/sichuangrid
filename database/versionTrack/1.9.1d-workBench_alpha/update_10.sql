-- 岗位修改日志记录表
create table ROLEUPDATELOGS
(
  ID             NUMBER(10) not null,
  POSITIONID     NUMBER(10),
  POSITIONNAME   VARCHAR2(60),
  PERMISSIONID   NUMBER(10),
  PERMISSIONNAME VARCHAR2(100),
  OPERATETYPE    NUMBER(1),
  OPERATEDATE    DATE,
  CREATEDATE     DATE,
  CREATEUSER     VARCHAR2(100)
);

alter table ROLEUPDATELOGS
  add constraint PK_ROLEUPDATELOG primary key (ID);
  
comment on table ROLEUPDATELOGS
  is '岗位权限修改日志表';
comment on column ROLEUPDATELOGS.POSITIONID
  is '岗位ID';
comment on column ROLEUPDATELOGS.POSITIONNAME
  is '岗位中文名称';
comment on column ROLEUPDATELOGS.PERMISSIONID
  is '权限ID';
comment on column ROLEUPDATELOGS.PERMISSIONNAME
  is '权限中文名称';
comment on column ROLEUPDATELOGS.OPERATETYPE
  is '操作类别 0：删除 1：新增';
comment on column ROLEUPDATELOGS.OPERATEDATE
  is '操作时间';

create sequence s_roleUpdateLog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;




--增加出租房认领图片字段
alter table dm_rentalhouse_temp add IMGURL VARCHAR2(300);
comment on column dm_rentalhouse_temp.IMGURL is '出租房图片';

--增加社会组织认领图片字段
alter table DM_newSocieOrg_Temp add IMGURL VARCHAR2(300);
comment on column DM_newSocieOrg_Temp.IMGURL is '图片路径';

--增加新经济组织认领图片字段
alter table DM_newEconoOrg_Temp add IMGURL VARCHAR2(300);
comment on column DM_newEconoOrg_Temp.IMGURL is '图片路径';

--基本信息统计报表序列
create sequence s_baseSituationStatement
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


--建立索引
create bitmap index INDEX_HOUSEHOLD_LOGOUT ON HOUSEHOLDSTAFFS(LOGOUT);
create bitmap index INDEX_FLOATING_LOGOUT ON FLOATINGPOPULATIONS(LOGOUT);