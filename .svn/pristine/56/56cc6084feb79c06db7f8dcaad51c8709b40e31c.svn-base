CREATE SEQUENCE S_goodSamaritan
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;

-- 见义勇为表
create table goodSamaritan  (
   id      NUMBER(10)      not null,
   baseinfoid       NUMBER(10),
   addressinfoid    NUMBER(10),
   orgid            NUMBER(10) not null,
   orginternalcode  VARCHAR2(32),
   disableGradeType    NUMBER(10),
   sacrificeType    NUMBER(10),
   insureSituationType        NUMBER(10),
   insureSituationSmallType    NUMBER(10),
   actOccurred    VARCHAR2(300),
   awardYear   VARCHAR2(10),
   awardType    NUMBER(10),
   difficultType        NUMBER(10),
   confirmUnit    VARCHAR2(300),
   heroicDeeds    CLOB,
   sourcesstate     NUMBER(1) default 1,
   attentionextent  NUMBER(10),
   isemphasis       NUMBER(1) default 0,
   isemphasisreason VARCHAR2(300),
   isemphasisdate   DATE,
   createuser       VARCHAR2(32) not null,
   createdate       DATE not null,
   updateuser       VARCHAR2(32),
   updatedate       DATE,
   CONSTRAINT pk_goodSamaritan PRIMARY KEY (ID)
);
-- Add comments to the table 
comment on table goodSamaritan
  is '见义勇为表';
-- Add comments to the columns 
comment on column goodSamaritan.id
  is '人员id';
  comment on column goodSamaritan.baseinfoid
  is '基本信息id';
  comment on column goodSamaritan.addressinfoid
  is '地址id';
comment on column goodSamaritan.orgid
  is '所属网格';
comment on column goodSamaritan.disableGradeType
  is '伤残等级';
comment on column goodSamaritan.sacrificeType
  is '是否牺牲';
comment on column goodSamaritan.insureSituationType
  is '参保情况大类';
comment on column goodSamaritan.insureSituationSmallType
  is '参保情况小类';
comment on column goodSamaritan.actOccurred
  is '行为发生地';
comment on column goodSamaritan.awardYear
  is '获奖年份';
comment on column goodSamaritan.awardType
  is '获奖级别';
comment on column goodSamaritan.difficultType
  is '困难类型';
  comment on column goodSamaritan.confirmUnit
  is '确认单位';
  comment on column goodSamaritan.heroicDeeds
  is '见义勇为事迹';
  comment on column goodSamaritan.sourcesstate
  is '数据来源：1.录入；2.认领；3.已核实';
comment on column goodSamaritan.attentionextent
  is '关注程度：1.一般；2.中等；3.严重';
comment on column goodSamaritan.isemphasis
  is '是否关注';
comment on column goodSamaritan.createuser
  is '创建用户';
  comment on column goodSamaritan.createdate
  is '创建日期';
comment on column goodSamaritan.updateuser
  is '修改用户';
comment on column goodSamaritan.updatedate
  is '修改时间';
  

  
  
CREATE SEQUENCE S_personAttachFile
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;
---- 见义勇为业务附件表
create table personAttachFile
(
  id               NUMBER(10) not null,
  businessid       NUMBER(10),
  businessType     NVARCHAR2(150),
  filename     NVARCHAR2(150),
  annexaddress NVARCHAR2(500),
  createuser   VARCHAR2(60) not null,
  updateuser   VARCHAR2(60),
  createdate   DATE not null,
  updatedate   DATE,
  CONSTRAINT pk_personAttachFile PRIMARY KEY (ID)
);

comment on table personAttachFile
  is '人口模块附件表';
-- Add comments to the columns 
comment on column personAttachFile.id
  is '附件ID';
  comment on column personAttachFile.businessid
  is '人口业务信息ID';
  comment on column personAttachFile.businessType
  is '人口业务信息类型';
  comment on column personAttachFile.filename
  is '附件名称';
comment on column personAttachFile.annexaddress
  is '附件地址';
  comment on column personAttachFile.createuser
  is '创建用户';
  comment on column personAttachFile.createdate
  is '创建日期';
comment on column personAttachFile.updateuser
  is '修改用户';
comment on column personAttachFile.updatedate
  is '修改时间';
commit;